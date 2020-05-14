package dao;


import models.Tenants;
import org.sql2o.*;

import java.util.List;

public class Sql2oTenantsDao implements TenantsDao{
    private final Sql2o sql2o;

    public Sql2oTenantsDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void saveTenant(Tenants tenants) {
        String save ="INSERT INTO tenants (name, phone, roomnumber, floor, apartmentid) VALUES (:name, :phone, :roomNumber, :floor, :apartmentId)";
        try(Connection conn=sql2o.open()) {
            int id=(int) conn.createQuery(save,true)
                    .bind(tenants)
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
            return conn.createQuery("SELECT * FROM tenants ORDER BY name ASC;")
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
    public void update(int id,String name, String phone, int roomNumber, int floor, int apartmentId) {
        String update = "UPDATE tenants SET (name, phone, roomnumber, floor, apartmentid)=(:name, :phone, :roomNumber, :floor, :apartmentId) WHERE id =:id";
        try(Connection conn =sql2o.open()) {
            conn.createQuery(update)
                    .addParameter("name",name)
                    .addParameter("phone",phone)
                    .addParameter("roomNumber",roomNumber)
                    .addParameter("floor",floor)
                    .addParameter("apartmentId",apartmentId)
                    .addParameter("id",id)
                    .executeUpdate();
        }catch (Sql2oException ex)
        {
            System.out.println(ex);
        }
    }
    @Override
    public void clearAll() {
        String removeAll="DELETE FROM tenants";
        try(Connection conn=sql2o.open()) {
            conn.createQuery(removeAll)
                    .executeUpdate();
        }catch (Sql2oException ex)
        {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from tenants WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

}