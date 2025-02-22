package com.example.pandaeat.customerFoodPanel;

public class chefprofileclass {
    private String Area, City, ConfirmPassword, EmailId, Fname, House, Lname, Mobile, Password, Postcode, State;

    public String getArea() {
        return Area;
    }

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

    public chefprofileclass(String area, String city, String confirmPassword, String emailId, String fname, String house, String lname, String mobile, String password, String postcode, String state) {
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
