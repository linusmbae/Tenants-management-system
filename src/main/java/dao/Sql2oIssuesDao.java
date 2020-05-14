package dao;

import models.Issues;
import org.sql2o.*;

import java.util.List;

public class Sql2oIssuesDao implements IssuesDao {
    private final Sql2o sql2o;

    public Sql2oIssuesDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void saveIssue(Issues issues) {
        String save = "INSERT INTO issues (type, content, apartmentid, roomid) VALUES (:type, :content, :apartmentId, :roomId)";
        try (Connection conn = sql2o.open()) {
            int id = (int) conn.createQuery(save,true)
                    .bind(issues)
                    .executeUpdate()
                    .getKey();
            issues.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }


   @Override
    public List<Issues> getAll() {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM issues")
                    .executeAndFetch(Issues.class);
        }
    }

    @Override
    public Issues findById(int id) {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM issues WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Issues.class);
        }
    }

    @Override
    public List<Issues> getByApartment(int apartmentId) {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM issues WHERE apartmentid=:apartmentId")
                    .addParameter("apartmentId", apartmentId)
                    .executeAndFetch(Issues.class);
        }
    }

    @Override
    public void update(int id, String type, String content, int apartmentid, int roomid) {
        String update = "UPDATE issues SET (type, content, apartmentid, roomid) = (:type, :content, :apartmentId, :roomId) WHERE id=:id";
        try (Connection conn = sql2o.open()) {
            conn.createQuery(update)
                    .addParameter("type", type)
                    .addParameter("content", content)
                    .addParameter("apartmentId", apartmentid)
                    .addParameter("roomId", roomid)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void clearAll() {
        String removeAll = "DELETE FROM issues";
        try (Connection conn = sql2o.open()) {
            conn.createQuery(removeAll)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String removeById = "DELETE FROM issues WHERE id=:id";
        try (Connection conn = sql2o.open()) {
            conn.createQuery(removeById)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}