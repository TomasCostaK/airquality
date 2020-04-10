package com.tom1k.airquality;

import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CityDAOImpl implements CityDAO {

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
        Query<City> query = currSession.createQuery("select " + name + "from City",City.class);
        City city = query.getSingleResult();
        return city;
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