package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Prospect {

    private String name;
    private String position;
    private String college;

    public Prospect(String name, String position, String college) {
        this.name = name;
        this.position = position;
        this.college = college;
    }


    public String getName() {return name;}

    public String getPosition() {
        return position;
    }

    public String getCollege() {
        return college;
    }

    @Override
    public String toString() {
        return name + ", " + position + ", " + college;
    }

    public static void getAllAvailableProspects(List<Prospect> prospects) {
        for (Prospect prospect : prospects) {
            System.out.println(prospect);
        }
    }

    public static void getProspectByPosition(List<Prospect> prospects, String position) {
        for (Prospect prospect : prospects) {
            if (prospect.getPosition().equalsIgnoreCase(position)) {
                System.out.println(prospect);
            }
        }
    }

    public static void getProspectBySchool(List<Prospect> prospects, String school) {
        for (Prospect prospect : prospects) {
            if (prospect.getCollege().equalsIgnoreCase(school)) {
                System.out.println(prospect);
            }
        }
    }


}



