package dao;

import models.Apartment;
import models.Issues;
import org.sql2o.*;

import java.util.List;

public class Sql2oIssuesDao implements IssuesDao{
    private final Sql2o sql2o;

    public Sql2oIssuesDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void saveIssue(Issues issues, Apartment apartment) {
        String save="INSERT INTO issues (type, content, apartmentid, roomid) VALUES (:type, :content, :apartmentId, :roomId)";
        try(Connection conn=sql2o.open()) {
            int id=(int)conn.createQuery(save)
                    .addParameter("type",issues.getType())
                    .addParameter("content",issues.getContent())
                    .addParameter("apartmentId",apartment.getId())
                    .addParameter("roomId",issues.getRoomId())
                    .executeUpdate()
                    .getKey();
            issues.setId(id);
        }catch (Sql2oException ex)
        {
            System.out.println(ex);
        }
    }

    @Override
    public List<Issues> getAll() {
        try(Connection conn=sql2o.open()) {
            return conn.createQuery("SELECT * FROM issues")
                    .executeAndFetch(Issues.class);
        }
    }

    @Override
    public Issues getById(int id) {
        try(Connection conn=sql2o.open()) {
            return conn.createQuery("SELECT * FROM issues WHERE id=:id")
                    .addParameter("id",id)
                    .executeAndFetchFirst(Issues.class);
        }
    }

    @Override
    public List<Issues> getByApartmentId(Apartment apartment) {
        try(Connection conn=sql2o.open()) {
            return conn.createQuery("SELECT * FROM issues WHERE apartmentid=:apartmentId")
                    .addParameter("apartmentId",apartment.getId())
                    .executeAndFetch(Issues.class);
        }
    }

    @Override
    public void update(Issues issues, Apartment apartment) {
        String update="UPDATE issues SET (type, content, apartmentid, roomid) = (:type, :content, :apartmentId, :roomId) WHERE id=:id";
        try(Connection conn=sql2o.open()) {
            conn.createQuery(update)
                    .addParameter("type",issues.getType())
                    .addParameter("content",issues.getContent())
                    .addParameter("apartmentId",apartment.getId())
                    .addParameter("roomId",issues.getRoomId())
                    .executeUpdate();
        }catch (Sql2oException ex)
        {
            System.out.println(ex);
        }
    }

    @Override
    public void clearAll() {
        String removeAll="DELETE FROM issues";
        try(Connection conn=sql2o.open()) {
            conn.createQuery(removeAll)
                    .executeUpdate();
        }catch (Sql2oException ex)
        {
            System.out.println(ex);
        }
    }

    @Override
    public void removeById(Issues issues) {
        String removeById="DELETE FROM issues WHERE id=:id";
        try(Connection conn=sql2o.open()) {
            conn.createQuery(removeById)
                    .addParameter("id",issues.getId())
                    .executeUpdate();
        }catch (Sql2oException ex)
        {
            System.out.println(ex);
        }
    }

    @Override
    public void removeByApartmentId(Apartment apartment) {
        String removeByApartmentId="DELETE FROM issues WHERE apartmentid=:apartmentId";
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
