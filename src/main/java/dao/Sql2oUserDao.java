package dao;

import models.User;
import org.sql2o.Connection;
import org.sql2o.*;

import java.util.List;

public class Sql2oUserDao implements UserDao {
    private final Sql2o sql2o;


    public Sql2oUserDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void save(User user) {
        String saveUser="INSERT INTO users (name, email, username, password) VALUES (:name, :email, :userName, :password)";
        try(Connection conn=sql2o.open()) {
            int id=(int) conn.createQuery(saveUser,true)
                    .bind(user)
                    .executeUpdate()
                    .getKey();
            user.setId(id);
        }catch (Sql2oException ex)
        {
            System.out.println(ex);
        }
    }

    @Override
    public List<User> getAll() {
        try (Connection conn=sql2o.open()){
            return conn.createQuery("SELECT * FROM users")
                    .executeAndFetch(User.class);
        }
    }

    @Override
    public User findById(int id) {
        try(Connection conn=sql2o.open()) {
            return conn.createQuery("SELECT * FROM users WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(User.class);
        }
    }

    @Override
    public void update(int id,String name, String email, String userName, String password) {
        String update="UPDATE users SET (name, email, username, password) = (:name, :email, :userName, :password) WHERE id=:id";
        try(Connection conn=sql2o.open()) {
            conn.createQuery(update)
                    .addParameter("name",name)
                    .addParameter("email",email)
                    .addParameter("userName",userName)
                    .addParameter("password",password)
                    .addParameter("id",id)
                    .executeUpdate();
        }catch (Sql2oException ex)
        {
            System.out.println(ex);
        }
    }


    @Override
    public void deleteById(int id) {
        String removeById="DELETE FROM users WHERE id=:id";
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
    public void clearAll() {
        String removeAll="DELETE FROM users";
        try(Connection conn=sql2o.open()) {
            conn.createQuery(removeAll)
                    .executeUpdate();
        }catch (Sql2oException ex)
        {
            System.out.println(ex);
        }
    }
}
