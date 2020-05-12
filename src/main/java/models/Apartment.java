package models;

import java.util.Objects;

public abstract class Apartment {
    private int id;
    private String name;
    private String location;
    private String type;
    private int numberOfRooms;
    private int numberOfFloors;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public void setNumberOfFloors(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apartment apartment = (Apartment) o;
        return numberOfRooms == apartment.numberOfRooms &&
                numberOfFloors == apartment.numberOfFloors &&
                Objects.equals(name, apartment.name) &&
                Objects.equals(location, apartment.location) &&
                Objects.equals(type, apartment.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, location, type, numberOfRooms, numberOfFloors);
    }
}
