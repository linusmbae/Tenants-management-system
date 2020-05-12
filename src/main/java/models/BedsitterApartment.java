package models;

public class BedsitterApartment extends Apartment {
    private final String TYPE="Bedsitter";

    public BedsitterApartment(String name, String  location, int numberOfRooms, int numberOfFloors) {
        this.name=name;
        this.location=location;
        this.numberOfRooms=numberOfRooms;
        this.numberOfFloors=numberOfFloors;
        this.type=TYPE;
    }
}
