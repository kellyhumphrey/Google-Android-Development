package khumphrey.com.khumphrey_restaurantlist.ui.main;

public class Restaurant {
    public int logo;
    public String name;
    public String location;
    public String type;
    public String cost;
    public String recommendation;
    public String funFact;

    public Restaurant()
    {
        super();
    }

    public Restaurant(int logo, String name, String location, String type, String cost, String recommendation, String funFact)
    {
        super();
        this.logo = logo;
        this.name = name;
        this.location = location;
        this.type = type;
        this.cost = cost;
        this.recommendation = recommendation;
        this.funFact = funFact;
    }

    public int getLogo() {return logo;}
    public String getName() {return name;}
    public String getLocation() {return location;}
    public String getType() {return type;}
    public String getCost() {return cost;}
    public String getRecommendation() {return recommendation;}
    public String getFunFact() {return funFact;}

}