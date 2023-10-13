package model;

// represents all the information we have about a university volunteer
public class UniversityVolunteer extends Volunteer {

    public UniversityVolunteer(String name, String major, int id, int year) {
        this.name = name;
        this.major = major;
        this.id = id;
        this.year = year;
    }
}
