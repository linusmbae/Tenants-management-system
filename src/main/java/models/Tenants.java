package models;

import java.util.Objects;

public class Tenants {
    private int id;
    private String name;
    private String phone;
    private int roomNumber;
    private int floor;
    private int apartmentId;

    public Tenants(String name, String phone, int roomNumber, int floor, int apartmentId) {
        this.name = name;
        this.phone = phone;
        this.roomNumber = roomNumber;
        this.floor = floor;
        this.apartmentId=apartmentId;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(int apartmentId) {
        this.apartmentId = apartmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tenants tenants = (Tenants) o;
        return roomNumber == tenants.roomNumber &&
                floor == tenants.floor &&
                apartmentId==tenants.apartmentId &&
                Objects.equals(name, tenants.name) &&
                Objects.equals(phone, tenants.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone, roomNumber, floor, apartmentId);
    }
}
