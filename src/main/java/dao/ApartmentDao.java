package dao;

import models.BedsitterApartment;
import models.OneBedroomApartment;
import models.Tenants;


import java.util.List;

public interface ApartmentDao {
    //    CREATE
    void saveBedSitterApartment(BedsitterApartment bedsitterApartment);
    void saveOneBedroomApartment(OneBedroomApartment oneBedroomApartment);

    //    READ
    List<BedsitterApartment> getAllWithTypeBedsitter(String type);
    List<OneBedroomApartment> getAllWithTypeOneBedroom(String type);
    BedsitterApartment findBedsitterById(int id);
    OneBedroomApartment findOneBedroomById(int id);


    //    UPDATE
    void updateBedsitter(String name, String  location,String type, int numberOfRooms, int numberOfFloors,int id);
    void updateOneBedroom(String name, String  location,String type, int numberOfRooms, int numberOfFloors,int id);

    //    DESTROY
    void deleteByBedsitterId(int id);
    void deleteByOneBedroomId(int id);
    void deleteBedsitter(String type);
    void deleteOneBedroom(String type);
    void clearAll();
}
