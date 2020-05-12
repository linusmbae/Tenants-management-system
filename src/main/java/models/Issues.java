package models;

import java.util.Objects;

public class Issues {
private int id;
private String type;
private String content;
private int apartmentId;
private int roomId;

    public Issues(String type, String content, int apartmentId, int roomId) {
        this.type = type;
        this.content = content;
        this.apartmentId = apartmentId;
        this.roomId = roomId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(int apartmentId) {
        this.apartmentId = apartmentId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issues issues = (Issues) o;
        return apartmentId == issues.apartmentId &&
                roomId == issues.roomId &&
                Objects.equals(type, issues.type) &&
                Objects.equals(content, issues.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, content, apartmentId, roomId);
    }
}
