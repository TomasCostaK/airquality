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
    public List<City> getCities() {
        return cityDAO.getCities();
    }

    //aqui verificar em cache primeiro
    @Transactional
    @Override
    public City get(String name) {
        return cityDAO.get(name);
    }

    @Transactional
    @Override
    public City save(City city) throws Exception{
        return cityDAO.save(city);
    }

    @Transactional
    @Override
    public boolean delete(int id) throws Exception{
        return cityDAO.delete(id);
    }
}
