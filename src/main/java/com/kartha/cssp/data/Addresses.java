package com.kartha.cssp.data;

import java.io.Serializable;

public class Addresses implements Serializable {

    private String houseNumber1;
    private String houseNumber2;
    private String street;
    private String city;
    private String region;
    private String zipCode;

    public String getHouseNumber1() {
        return houseNumber1;
    }

    public void setHouseNumber1(String houseNumber1) {
        this.houseNumber1 = houseNumber1;
    }

    public String getHouseNumber2() {
        return houseNumber2;
    }

    public void setHouseNumber2(String houseNumber2) {
        this.houseNumber2 = houseNumber2;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
