package dao;

import models.Apartment;
import models.Tenants;

import java.util.List;

public interface TenantsDao {

    //    CREATE

    void saveTenant(Tenants tenants);

    //    READ
    List<Tenants> getAll();
    Tenants findById(int id);

//    UPDATE
    void update(int id,String name, String phone, int roomNumber, int floor, int apartmentId);

    //    DESTROY
    void clearAll();
    void deleteById(int id);

}

