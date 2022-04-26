package Model;

public class Team {

    private String location;
    private String name;



    public Team(String location, String name) {
        this.location = location;
        this.name = name;

    }

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return location.trim() + " " + name;
    }
}