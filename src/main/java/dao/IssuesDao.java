package dao;

import models.Apartment;
import models.Issues;
import models.User;

import java.util.List;

public interface IssuesDao {

//    CREATE
    void saveIssue(Issues issues);


    //    READ
    List<Issues> getAll();
    Issues findById(int id);
    List<Issues> getByApartment(int apartmentId);


    //    UPDATE

    void update(int id, String type, String content, int apartmentid, int roomid);

    //    DESTROY
    void clearAll();
    void deleteById(int id);

}