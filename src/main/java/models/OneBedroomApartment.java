package models;

public class OneBedroomApartment extends Apartment {
    private final String TYPE="One Bedroom";

    public OneBedroomApartment(String name, String  location, int numberOfRooms, int numberOfFloors) {
        this.name=name;
        this.location=location;
        this.numberOfRooms=numberOfRooms;
        this.numberOfFloors=numberOfFloors;
        this.type=TYPE;
    }
}
