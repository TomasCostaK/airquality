package com.tom1k.airquality;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class CityAir {
    private @Id @GeneratedValue Long id;
    @Size(min=3, max=25)
    private String name;
    private int aqi;
    // perceber como integrar as geo coords
    private double pm10;
    private String dominantpol;

    private CityAir(){}

    public CityAir(String name, int aqi, double pm10, String dominantpol){
        this.name = name;
        this.aqi = aqi;
        this.pm10 = pm10;
        this.dominantpol = dominantpol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAqi() {
        return aqi;
    }

    public void setAqi(int aqi) {
        this.aqi = aqi;
    }

    public double getPm10() {
        return pm10;
    }

    public void setPm10(double pm10) {
        this.pm10 = pm10;
    }

    public String getDominantpol() {
        return dominantpol;
    }

    public void setDominantpol(String dominantpol) {
        this.dominantpol = dominantpol;
    }

    @Override
    public String toString() {
        return "CityAir{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", aqi=" + aqi +
                ", pm10=" + pm10 +
                ", dominantpol='" + dominantpol + '\'' +
                '}';
    }
}
