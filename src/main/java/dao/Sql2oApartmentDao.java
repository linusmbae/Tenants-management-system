package dao;

import models.BedsitterApartment;
import models.OneBedroomApartment;
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
            int id=(int) conn.createQuery(save)
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
            int id=(int) conn.createQuery(save)
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
    public void updateBedsitter(BedsitterApartment bedsitterApartment) {
        String update="UPDATE apartments SET (name, location, type, numberofrooms, numberoffloors)=(:name, :location, :type, :numberOfRooms, :numberOfFloors) WHERE id=:id";
        try(Connection conn=sql2o.open()) {
            conn.createQuery(update)
                    .bind(bedsitterApartment)
                    .executeUpdate();
        }catch (Sql2oException ex)
        {
            System.out.println(ex);
        }
    }

    @Override
    public void updateOneBedroom(OneBedroomApartment oneBedroomApartment) {
        String update="UPDATE apartments SET (name, location, type, numberofrooms, numberoffloors)=(:name, :location, :type, :numberOfRooms, :numberOfFloors) WHERE id=:id";
        try(Connection conn=sql2o.open()) {
            conn.createQuery(update)
                    .bind(oneBedroomApartment)
                    .executeUpdate();
        }catch (Sql2oException ex)
        {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
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
    public void deleteByApartmentType(BedsitterApartment bedsitterApartment, OneBedroomApartment oneBedroomApartment) {
        String removeByTypeBedsitter="DELETE FROM apartments WHERE type=:type";
        String removeByTypeOneBedroom="DELETE FROM apartments WHERE type=:type";
        try(Connection conn=sql2o.open()) {
            conn.createQuery(removeByTypeBedsitter)
                    .addParameter("type",bedsitterApartment.getType())
                    .executeUpdate();

            conn.createQuery(removeByTypeOneBedroom)
                    .addParameter("type",oneBedroomApartment.getType())
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
