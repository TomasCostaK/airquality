package com.tom1k.airquality;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.json.*;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Repository
public class CityDAOImpl implements CityDAO {

    private static final String GET_URL = "https://api.waqi.info/feed";
    private static final String USER_AGENT = "Mozilla/5.0";
    //Have this token removed later, so people are not making use of my API
    private static final String TOKEN = "76cd24d48b0fd37719669178c449821326315463";

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<City> getCities() {
        Session currSession = entityManager.unwrap(Session.class);
        Query<City> query = currSession.createQuery("from City",City.class);
        return query.getResultList();
    }


    // Custom get city from the name on it, can be repeated so get first?
    @Override
    public City get(String name) {
        Session currSession = entityManager.unwrap(Session.class);
        Query query = currSession.createQuery("select c from City c where name = ?1");
        query.setParameter(1,name);
        City city = null;
        try{
            city = (City) query.getSingleResult();
            return city;
        }catch (NoResultException e) {
            //Call à api
            //If the code didnt have in cache, we must run a call for our api and then return the city we have created
            try {
                String url_final = GET_URL + "/" + name + "/?token=" + TOKEN;
                URL obj = new URL(url_final);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("User-Agent", USER_AGENT);
                int responseCode = con.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) { // success
                    BufferedReader in = new BufferedReader(new InputStreamReader(
                            con.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    // print result

                    //Parse to JSON
                    JSONObject jsonObj = new JSONObject(response.toString());
                    int aqi = jsonObj.getJSONObject("data").getInt("aqi");
                    String dominentpol = jsonObj.getJSONObject("data").getString("dominentpol");
                    int pm25;
                    int pm10;

                    try{
                        pm25 = jsonObj.getJSONObject("data").getJSONObject("iaqi").getJSONObject("pm25").getInt("v");
                        pm10 = jsonObj.getJSONObject("data").getJSONObject("iaqi").getJSONObject("pm10").getInt("v");
                    } catch (JSONException jsonE){
                        pm25 = 0;
                        pm10 = 0;
                    }

                    //Chamar
                    City cityJson = createCity(name,aqi,pm10,pm25,dominentpol);
                    save(cityJson);
                    return cityJson;
                }
            }catch (Exception e2){ //there was a problem getting the city, so it doesnt exist in the external API, so we return an error
            }
            //The database should clear itself to keep fewer items in cache
        }
        //return null caso nao haja cidade na API
        return city;
    }

    public City createCity(String name, int aqi, int pm10, int pm25, String dominentpol){
        City cityJson = new City();
        cityJson.setName(name);
        cityJson.setAqi(aqi);
        cityJson.setPm10(pm10);
        cityJson.setPm25(pm25);
        cityJson.setDominentpol(dominentpol);
        return cityJson;
    }

    @Override
    public City save(City city) throws Exception{
        Session currSession = entityManager.unwrap(Session.class);
        currSession.saveOrUpdate(city);
        return city;
    }

    @Override
    public boolean delete(int id) throws Exception{
        try {
            Session currSession = entityManager.unwrap(Session.class);
            City city = currSession.get(City.class,id);
            currSession.delete(city);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}