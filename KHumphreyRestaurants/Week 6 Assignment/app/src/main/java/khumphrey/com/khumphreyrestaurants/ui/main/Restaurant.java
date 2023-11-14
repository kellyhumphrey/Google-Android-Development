package khumphrey.com.khumphreyrestaurants.ui.main;

public class Restaurant {

    public int icon;
    public String name;
    public String location;
    public String type;
    public String cost;
    public String recommendation;

    public Restaurant() {
        super();
    }

    public Restaurant(int icon, String name, String location, String type, String cost, String recommendation){
        super();
        this.icon = icon;
        this.name = name;
        this.location = location;
        this.type = type;
        this.cost = cost;
        this.recommendation = recommendation;
    }
}
