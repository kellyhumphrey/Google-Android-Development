package khumphrey.com.exercise_81.ui.main;

public class Animal {
    public final static String MAMMAL = "mammal";
    public final static String BIRD = "bird";
    public final static String REPTILE = "reptile";
    private String name="";
    private String location = "";
    private String type="";
    public String getName() {return(name);}

    public void setName(String name) {this.name=name;}
    public String getLocation() {return(location);}
    public void setLocation(String location) {this.location=location;}
    public String getType() {return(type);}
    public void setType(String type) {this.type=type;}

    protected long id = 0;
    public long getId() {return(id);}
    public void setId(long id) {this.id=id;}

    @Override
    public String toString() {
        return "Animal{name='" + name + '\'' + ", location='" + location + '\'' + ", type='" + type + '\'' +'}';
    }


}
