package com.example.pandaeat.deliveryFoodPanel;

public class Delivery {
    private String Area, City, ConfirmPassword, EmailId, Fname, House, Lname, Mobile, Password, Postcode, State;

    public void setArea(String area) {
        Area = area;
    }

    public void setCity(String city) {
        City = city;
    }

    public void setConfirmPassword(String confirmPassword) {
        ConfirmPassword = confirmPassword;
    }

    public void setEmailId(String emailId) {
        EmailId = emailId;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public void setHouse(String house) {
        House = house;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setPostcode(String postcode) {
        Postcode = postcode;
    }

    public void setState(String state) {
        State = state;
    }

    public String getArea() {
        return Area;
    }

    public String getCity() {
        return City;
    }

    public String getConfirmPassword() {
        return ConfirmPassword;
    }

    public String getEmailId() {
        return EmailId;
    }

    public String getFname() {
        return Fname;
    }

    public String getHouse() {
        return House;
    }

    public String getLname() {
        return Lname;
    }

    public String getMobile() {
        return Mobile;
    }

    public String getPassword() {
        return Password;
    }

    public String getPostcode() {
        return Postcode;
    }

    public String getState() {
        return State;
    }

    public Delivery(String area, String city, String confirmPassword, String emailId, String fname, String house, String lname, String mobile, String password, String postcode, String state) {

        Area = area;
        City = city;
        ConfirmPassword = confirmPassword;
        EmailId = emailId;
        Fname = fname;
        House = house;
        Lname = lname;
        Mobile = mobile;
        Password = password;
        Postcode = postcode;
        State = state;
    }
}
