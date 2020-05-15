package dao;

import models.Apartment;
import models.BedsitterApartment;
import models.OneBedroomApartment;
import models.Tenants;
import org.sql2o.*;

import java.util.List;

public class Sql2oApartmentDao implements ApartmentDao {
    private final Sql2o sql2o;

    public Sql2oApartmentDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void saveBedSitterApartment(BedsitterApartment bedsitterApartment) {
        String save="INSERT INTO apartments (name, location, type, numberofrooms, numberoffloors)VALUES(:name, :location, :type, :numberOfRooms, :numberOfFloors)";
        try(Connection conn=sql2o.open()) {
            int id=(int) conn.createQuery(save,true)
                    .bind(bedsitterApartment)
                    .executeUpdate()
                    .getKey();
            bedsitterApartment.setId(id);
        }catch (Sql2oException ex)
        {
            System.out.println(ex);
        }
    }

    @Override
    public void saveOneBedroomApartment(OneBedroomApartment oneBedroomApartment) {
        String save="INSERT INTO apartments (name, location, type, numberofrooms, numberoffloors)VALUES(:name, :location, :type, :numberOfRooms, :numberOfFloors)";
        try(Connection conn=sql2o.open()) {
            int id=(int) conn.createQuery(save,true)
                    .bind(oneBedroomApartment)
                    .executeUpdate()
                    .getKey();
            oneBedroomApartment.setId(id);
        }catch (Sql2oException ex)
        {
            System.out.println(ex);
        }
    }

    @Override
    public List<BedsitterApartment> getAllWithTypeBedsitter(String type) {
        try(Connection conn=sql2o.open()) {
            return conn.createQuery("SELECT * FROM apartments WHERE type = :type")
                    .addParameter("type",type)
                    .executeAndFetch(BedsitterApartment.class);
        }
    }

    @Override
    public List<OneBedroomApartment> getAllWithTypeOneBedroom(String type) {
        try(Connection conn=sql2o.open()) {
            return conn.createQuery("SELECT * FROM apartments WHERE type = :type")
                    .addParameter("type",type)
                    .executeAndFetch(OneBedroomApartment.class);
        }
    }

    @Override
    public BedsitterApartment findBedsitterById(int id) {
        try(Connection conn=sql2o.open())
        {
            return conn.createQuery("SELECT *FROM apartments WHERE id =:id")
                    .addParameter("id",id)
                    .executeAndFetchFirst(BedsitterApartment.class);
        }
    }

    @Override
    public OneBedroomApartment findOneBedroomById(int id) {
        try(Connection conn=sql2o.open())
        {
            return conn.createQuery("SELECT *FROM apartments WHERE id =:id")
                    .addParameter("id",id)
                    .executeAndFetchFirst(OneBedroomApartment.class);
        }
    }

    @Override
    public void updateBedsitter(String name, String  location,String type, int numberOfRooms, int numberOfFloors,int id) {
        String update="UPDATE apartments SET (name, location, type, numberofrooms, numberoffloors)=(:name, :location, :type, :numberOfRooms, :numberOfFloors) WHERE id=:id";
        try(Connection conn=sql2o.open()) {
            conn.createQuery(update).addParameter("name",name)
                    .addParameter("location", location)
                    .addParameter("type",type)
                    .addParameter("numberOfRooms", numberOfRooms)
                    .addParameter("numberOfFloors",numberOfFloors)
                    .addParameter("id",id)
                    .executeUpdate();
        }catch (Sql2oException ex)
        {
            System.out.println(ex);
        }
    }

    @Override
    public void updateOneBedroom(String name, String  location,String type, int numberOfRooms, int numberOfFloors,int id) {
        String update="UPDATE apartments SET (name, location, type, numberofrooms, numberoffloors)=(:name, :location, :type, :numberOfRooms, :numberOfFloors) WHERE id=:id";
        try(Connection conn=sql2o.open()) {
            conn.createQuery(update)
                    .addParameter("name",name)
                    .addParameter("location", location)
                    .addParameter("type",type)
                    .addParameter("numberOfRooms", numberOfRooms)
                    .addParameter("numberOfFloors",numberOfFloors)
                    .addParameter("id",id)
                    .executeUpdate();
        }catch (Sql2oException ex)
        {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteByBedsitterId(int id) {
        String removeById="DELETE FROM apartments WHERE id=:id";
        try(Connection conn=sql2o.open()) {
            conn.createQuery(removeById)
                    .addParameter("id",id)
                    .executeUpdate();
        }catch (Sql2oException ex)
        {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteByOneBedroomId(int id) {
        String removeById="DELETE FROM apartments WHERE id=:id";
        try(Connection conn=sql2o.open()) {
            conn.createQuery(removeById)
                    .addParameter("id",id)
                    .executeUpdate();
        }catch (Sql2oException ex)
        {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteBedsitter(String type) {
        String removeByType="DELETE FROM apartments WHERE type=:type";
        try(Connection conn=sql2o.open()) {
            conn.createQuery(removeByType)
                    .addParameter("type",type)
                    .executeUpdate();
        }catch (Sql2oException ex)
        {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteOneBedroom(String type) {
        String removeByType="DELETE FROM apartments WHERE type=:type";
        try(Connection conn=sql2o.open()) {
            conn.createQuery(removeByType)
                    .addParameter("type",type)
                    .executeUpdate();
        }catch (Sql2oException ex)
        {
            System.out.println(ex);
        }
    }


    @Override
    public void clearAll() {
        String removeAll="DELETE FROM apartments";
        try(Connection conn=sql2o.open()) {
            conn.createQuery(removeAll)
                    .executeUpdate();
        }catch (Sql2oException ex)
        {
            System.out.println(ex);
        }
    }
}