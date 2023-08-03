package fr.iavotiana.travel.model;

public class Destination {
    public String title;
    public String description;
    public Integer imageId;
    public Integer note;

    public Destination(String title, String description, Integer imageId, Integer note) {
        this.title = title;
        this.description = description;
        this.imageId = imageId;
        this.note= note;
    }
}
