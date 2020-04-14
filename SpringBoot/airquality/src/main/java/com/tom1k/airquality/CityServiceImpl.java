package com.tom1k.airquality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CityServiceImpl implements CityService{

    @Autowired
    private CityDAO cityDAO;

    @Transactional
    @Override
    public List<City> get() {
        return cityDAO.get();
    }

    //aqui verificar em cache primeiro
    @Transactional
    @Override
    public City get(String name) {
        return cityDAO.get(name);
    }

    @Transactional
    @Override
    public void save(City city) {
        cityDAO.save(city);
    }

    @Transactional
    @Override
    public void delete(int id) {
        cityDAO.delete(id);
    }
}
