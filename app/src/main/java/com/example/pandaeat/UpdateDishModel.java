package com.example.pandaeat;

public class UpdateDishModel {
    String Dishes,RandomUID,Description,Quantity,Price,ImageURL,Chefid,Rating;


    public UpdateDishModel()
    {

    }

    public String getRating() {return Rating;}
    public void setRating(String rating){Rating=rating;}
    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getRandomUID() {

        return RandomUID;
    }

    public void setRandomUID(String randomUID) {

        RandomUID = randomUID;
    }

    public String getDishes()
    {
        return Dishes;
    }

    public void setDishes(String dishes) {

        Dishes = dishes;
    }

    public String getChefId() {
        return Chefid;
    }

    public void setChefId(String chefid) {
        Chefid = chefid;
    }
}
