package dao;

import models.BedsitterApartment;
import models.OneBedroomApartment;

import java.util.List;

public interface ApartmentDao {
//    CREATE
    void saveBedSitterApartment(BedsitterApartment bedsitterApartment);
    void saveOneBedroomApartment(OneBedroomApartment oneBedroomApartment);

//    READ
    List<BedsitterApartment> getAllWithTypeBedsitter(String type);
    List<OneBedroomApartment> getAllWithTypeOneBedroom(String type);


//    UPDATE
    void updateBedsitter(BedsitterApartment bedsitterApartment);
    void updateOneBedroom(OneBedroomApartment oneBedroomApartment);

//    DESTROY
    void deleteById(int id);
    void deleteByApartmentType(BedsitterApartment bedsitterApartment, OneBedroomApartment oneBedroomApartment);
    void clearAll();
}
