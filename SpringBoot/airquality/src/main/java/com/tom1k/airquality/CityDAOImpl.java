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
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
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
    public List<City> get() {
        Session currSession = entityManager.unwrap(Session.class);
        Query<City> query = currSession.createQuery("from City",City.class);
        List<City> list = query.getResultList();
        return list;
    }

    @Override
    public City get(int id) {
        Session currSession = entityManager.unwrap(Session.class);
        City city = currSession.get(City.class,id);
        return city;
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
            System.out.println("City not on Cache, fetching.");

            //If the code didnt have in cache, we must run a call for our api and then return the city we have created
            try {
                sendGET(name);
            }catch (Exception e2){ //there was a problem getting the city, so it doesnt exist in the external API, so we return an error
                System.out.println(e2);
            }
            //The database should clear itself to keep fewer items in cache
        }
        return city;
    }

    private void sendGET(String name) throws Exception {
        String URL_FINAL = GET_URL + "/" + name + "/?token=" + TOKEN;
        System.out.println(URL_FINAL);
        URL obj = new URL(URL_FINAL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result

            //Parse to JSON
            JSONObject json_obj = new JSONObject(response.toString());
            int aqi = json_obj.getJSONObject("data").getInt("aqi");
            String dominentpol = json_obj.getJSONObject("data").getString("dominentpol");
            int pm25, pm10;

            try{
                pm25 = json_obj.getJSONObject("data").getJSONObject("iaqi").getJSONObject("pm25").getInt("v");
                pm10 = json_obj.getJSONObject("data").getJSONObject("iaqi").getJSONObject("pm10").getInt("v");
            } catch (JSONException e){
                pm25 = 0;
                pm10 = 0;
            }

            //Chamar
            City city_json = new City();
            city_json.setName(name);
            city_json.setAqi(aqi);
            city_json.setPm10(pm10);
            city_json.setPm25(pm25);
            city_json.setDominentpol(dominentpol);
            save(city_json);

        } else {
            System.out.println("GET request didnt work");
        }

    }

    @Override
    public void save(City city) {
        Session currSession = entityManager.unwrap(Session.class);
        currSession.saveOrUpdate(city);
    }

    @Override
    public void delete(int id) {
        Session currSession = entityManager.unwrap(Session.class);
        City city = currSession.get(City.class,id);
        currSession.delete(city);
    }
}