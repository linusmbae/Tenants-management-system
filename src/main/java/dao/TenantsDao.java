package dao;

import models.Apartment;
import models.Tenants;

import java.util.List;

public interface TenantsDao {
//    CREATE
    void saveTenant(Tenants tenants,Apartment apartment);

//    READ
    List<Tenants> getAll();
    Tenants findById(int id);
    List<Tenants> geAllByApartmentId(Apartment apartment);

//    UPDATE
    void update(Tenants tenants,Apartment apartment);

//    DESTROY
    void clearAll();
    void removeById(Tenants tenants);
    void removeByApartmentId(Apartment apartment);
}
