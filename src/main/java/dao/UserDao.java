package dao;

import models.User;

import java.util.List;

public interface UserDao {
//    CREATE
    void save(User user);

//    READ
    List<User>getAll();
    User findById(int id);

//    UPDATE
    void update(User user);

//    DESTROY
    void deleteById(int id);
    void clearAll();
}
