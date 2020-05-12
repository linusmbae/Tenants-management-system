package dao;

import models.Apartment;
import models.Issues;

import java.util.List;

public interface IssuesDao {
//    CREATE
    void saveIssue(Issues issues, Apartment apartment);

//    READ
    List<Issues> getAll();
    Issues getById(int id);
    List<Issues> getByApartmentId(Apartment apartment);

//    UPDATE
    void update(Issues issues,Apartment apartment);

//    DESTROY
    void clearAll();
    void removeById(Issues issues);
    void removeByApartmentId(Apartment apartment);
}
