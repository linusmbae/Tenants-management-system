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
void update(int id,String name, String email, String userName, String password);

//    DESTROY
    void deleteById(int id);
    void clearAll();
}
