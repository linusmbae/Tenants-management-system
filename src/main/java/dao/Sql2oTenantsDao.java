package dao;

import models.Apartment;
import models.Tenants;
import org.sql2o.*;

import java.util.List;

public class Sql2oTenantsDao implements TenantsDao{
    private final Sql2o sql2o;

    public Sql2oTenantsDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void saveTenant(Tenants tenants, Apartment apartment) {
        String save ="INSERT INTO tenants (name, phone, roomnumber, floor, apartmentid) VALUES (:name, :phone, :roomNumber, :floor, :apartmentId)";
        try(Connection conn = sql2o.open()) {
            int id = (int)conn.createQuery(save,true)
                    .addParameter("name",tenants.getName())
                    .addParameter("phone",tenants.getPhone())
                    .addParameter("roomNumber",tenants.getRoomNumber())
                    .addParameter("floor",tenants.getFloor())
                    .addParameter("apartmentId",apartment.getId())
                    .executeUpdate()
                    .getKey();
            tenants.setId(id);
        }catch (Sql2oException ex)
        {
            System.out.println(ex);
        }
    }

    @Override
    public List<Tenants> getAll() {
        try(Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM tenants")
                    .executeAndFetch(Tenants.class);
        }
    }

    @Override
    public Tenants findById(int id) {
        try(Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM tenants WHERE id=:id")
                    .addParameter("id",id)
                    .executeAndFetchFirst(Tenants.class);
        }
    }

    @Override
    public List<Tenants> geAllByApartmentId(Apartment apartment) {
        try(Connection conn =sql2o.open()) {
          return conn.createQuery("SELECT * FROM tenants WHERE apartmentid=:apartmentId")
                  .addParameter("apartmentId",apartment.getId())
                  .executeAndFetch(Tenants.class);

        }
    }

    @Override
    public void update(Tenants tenants, Apartment apartment) {
        String update = "UPDATE tenants SET (name, phone, roomnumber, floor, apartmentid)=(:name, :phone, :roomNumber, :floor, :apartmentId) WHERE id =:id";
        try(Connection conn =sql2o.open()) {
            conn.createQuery(update)
                    .addParameter("name",tenants.getName())
                    .addParameter("phone",tenants.getPhone())
                    .addParameter("roomNumber",tenants.getRoomNumber())
                    .addParameter("floor",tenants.getFloor())
                    .addParameter("apartmentId",apartment.getId())
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

    @Override
    public void removeById(Tenants tenants) {
        String removeById="DELETE FROM apartments WHERE id=:id";
        try(Connection conn=sql2o.open()) {
            conn.createQuery(removeById)
                    .addParameter("id",tenants.getId())
                    .executeUpdate();
        }catch (Sql2oException ex)
        {
            System.out.println(ex);
        }
    }

    @Override
    public void removeByApartmentId(Apartment apartment) {
        String removeByApartmentId="DELETE FROM apartments WHERE apartmentid=:apartmentId";
        try(Connection conn=sql2o.open()) {
            conn.createQuery(removeByApartmentId)
                    .addParameter("apartmentId",apartment.getId())
                    .executeUpdate();
        }catch (Sql2oException ex)
        {
            System.out.println(ex);
        }
    }
}
