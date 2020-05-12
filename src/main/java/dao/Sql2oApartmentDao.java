package dao;

import models.BedsitterApartment;
import models.OneBedroomApartment;

import java.util.List;

public class Sql2oApartmentDao implements ApartmentDao {
    @Override
    public void saveBedSitterApartment(BedsitterApartment bedsitterApartment) {

    }

    @Override
    public void saveOneBedroomAprtment(OneBedroomApartment oneBedroomApartment) {

    }

    @Override
    public List<BedsitterApartment> getAllWithTypeBedsitter(String type) {
        return null;
    }

    @Override
    public List<OneBedroomApartment> getAllWithTypeOneBedroom(String type) {
        return null;
    }

    @Override
    public void updateBedsitter(BedsitterApartment bedsitterApartment) {

    }

    @Override
    public void updateOneBedroom(OneBedroomApartment oneBedroomApartment) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void deleteByApartmentType(BedsitterApartment bedsitterApartment, OneBedroomApartment oneBedroomApartment) {

    }

    @Override
    public void clearAll() {

    }
}
