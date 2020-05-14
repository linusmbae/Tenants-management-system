import dao.Sql2oApartmentDao;
import dao.Sql2oIssuesDao;
import dao.Sql2oTenantsDao;
import dao.Sql2oUserDao;
import models.*;
import org.sql2o.Connection;
import org.sql2o.*;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public  static void main(String[] args) {
        ProcessBuilder process = new ProcessBuilder();
        Integer port;

        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }
        port(port);
        staticFileLocation("/public");
        Connection conn;
        Sql2oUserDao userDao;
        Sql2oApartmentDao apartmentDao;
        Sql2oIssuesDao issuesDao;
        Sql2oTenantsDao tenantsDao;
        String connectionString = "jdbc:postgresql://localhost:5432/tenants_manager";
        Sql2o sql2o = new Sql2o(connectionString, "postgres", "1234");
        userDao=new Sql2oUserDao(sql2o);
        apartmentDao=new Sql2oApartmentDao(sql2o);
        issuesDao=new Sql2oIssuesDao(sql2o);
        tenantsDao=new Sql2oTenantsDao(sql2o);
        conn=sql2o.open();

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        // Users
        post("/users", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String email = request.queryParams("email");
            String username = request.queryParams("username");
            String password = request.queryParams("password");
            User user = new User(name,email,username,password);
            userDao.save(user);
            return new ModelAndView(model, "");
        }, new HandlebarsTemplateEngine());

        get("/users", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<User> user = userDao.getAll();
            model.put("user",user);
            return new ModelAndView(model, "");
        }, new HandlebarsTemplateEngine());

        get("/users/:id",(request, response) ->
        {
            Map<String,Object>model=new HashMap<String, Object>();
            int id = Integer.parseInt(request.params("id"));
            User found=userDao.findById(id);
            model.put("found",found);
            return new ModelAndView(model,"");
        },new HandlebarsTemplateEngine());

        //Tenants
        post("/Tenants", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String phone = request.queryParams("phone");
            int roomNumber = Integer.parseInt(request.queryParams("roomNumber"));
            int floor = Integer.parseInt(request.queryParams("floor"));
            int apartmentId = Integer.parseInt(request.queryParams("apartmentId"));
            Tenants tenants = new Tenants(name,phone,roomNumber,floor,apartmentId);
            tenantsDao.saveTenant(tenants);
            return new ModelAndView(model, "");
        }, new HandlebarsTemplateEngine());

        get("/Tenants", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Tenants> tenants = tenantsDao.getAll();
            model.put("tenants",tenants);
            return new ModelAndView(model, "");
        }, new HandlebarsTemplateEngine());

        get("/tenants/:id",(request, response) ->
        {
            Map<String,Object>model=new HashMap<String, Object>();
            int id = Integer.parseInt(request.params("id"));
            Tenants found=tenantsDao.findById(id);
            model.put("found",found);
            return new ModelAndView(model,"");
        },new HandlebarsTemplateEngine());


        //one bed room apartment
        post("/OneBedroomApartment/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String location = request.queryParams("location");
            int numberOfRooms = Integer.parseInt(request.queryParams("numberOfRooms"));
            int numberOfFloors = Integer.parseInt(request.queryParams("numberOfFloors"));
            OneBedroomApartment oneBedroomApartment = new OneBedroomApartment(name,location,numberOfRooms,numberOfFloors);
            apartmentDao.saveOneBedroomApartment(oneBedroomApartment);
            return new ModelAndView(model, "");
        }, new HandlebarsTemplateEngine());

        get("/OneBedroomApartment", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String type = request.queryParams("type");
            List<OneBedroomApartment> oneBedroomApartment = apartmentDao.getAllWithTypeOneBedroom(type);
            model.put("oneBedroomApartment",oneBedroomApartment);
            return new ModelAndView(model, "");
        }, new HandlebarsTemplateEngine());

        /*get("/OneBedroomApartment:id",(request, response) ->
        {
            Map<String,Object>model=new HashMap<String, Object>();
            int id = Integer.parseInt(request.params("id"));
            OneBedroomApartment found=apartmentDao.findById(id);
            model.put("found",found);
            return new ModelAndView(model,"");
        },new HandlebarsTemplateEngine());

         */

        //issues

        post("/Issues", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String type = request.queryParams("type");
            String content = request.queryParams("content");
            int apartmentId = Integer.parseInt(request.queryParams("apartmentId"));
            int roomId = Integer.parseInt(request.queryParams("roomId"));
            Issues issues = new Issues(type,content,apartmentId,roomId);
            issuesDao.saveIssue(issues);
            return new ModelAndView(model, "");
        }, new HandlebarsTemplateEngine());

        get("/Issues", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Issues> issues = issuesDao.getAll();
            model.put("issues",issues);
            return new ModelAndView(model, "");
        }, new HandlebarsTemplateEngine());

        get("/Issues:id",(request, response) ->
        {
            Map<String,Object>model=new HashMap<String, Object>();
            int id = Integer.parseInt(request.params("id"));
            Issues found=issuesDao.findById(id);
            model.put("found",found);
            return new ModelAndView(model,"");
        },new HandlebarsTemplateEngine());

        //Bedsitter
        post("/BedsitterApartment", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String location = request.queryParams("location");
            int numberOfRooms = Integer.parseInt(request.queryParams("numberOfRooms"));
            int numberOfFloors = Integer.parseInt(request.queryParams("numberOfFloors"));
            BedsitterApartment bedsitterApartment= new BedsitterApartment(name,location,numberOfRooms,numberOfFloors);
            apartmentDao.saveBedSitterApartment(bedsitterApartment);
            return new ModelAndView(model, "");
        }, new HandlebarsTemplateEngine());

        get("/BedsitterApartment", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String type = request.queryParams("type");
            List<BedsitterApartment> bedsitterApartments= apartmentDao.getAllWithTypeBedsitter(type);
            model.put("bedsitterApartment",bedsitterApartments);
            return new ModelAndView(model, "");
        }, new HandlebarsTemplateEngine());

        get("/Issues:id",(request, response) ->
        {
            Map<String,Object>model=new HashMap<String, Object>();
            int id = Integer.parseInt(request.params("id"));
           Issues found = issuesDao.findById(id);
            model.put("found",found);
            return new ModelAndView(model,"");
        },new HandlebarsTemplateEngine());

        //update

        post("/OneBedroomApartment/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String location = request.queryParams("location");
            int numberOfRooms = Integer.parseInt(request.queryParams("numberOfRooms"));
            int numberOfFloors = Integer.parseInt(request.queryParams("numberOfFloors"));
            OneBedroomApartment oneBedroomApartment = new OneBedroomApartment(name,location,numberOfRooms,numberOfFloors);
            apartmentDao.saveOneBedroomApartment(oneBedroomApartment);
            return new ModelAndView(model, "");
        }, new HandlebarsTemplateEngine());


       /* get("/OneBedroomApartment/:id/update",(request, response) ->
        {
            Map<String,Object>model=new HashMap<String, Object>();
            int id = Integer.parseInt(request.params("id"));
            OneBedroomApartment found=apartmentDao.findById(id);
            model.put("found",found);
            return new ModelAndView(model,"");
        },new HandlebarsTemplateEngine());

        */

        //update bedsitter
        post("/BedsitterApartment/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String location = request.queryParams("location");
            int numberOfRooms = Integer.parseInt(request.queryParams("numberOfRooms"));
            int numberOfFloors = Integer.parseInt(request.queryParams("numberOfFloors"));
            BedsitterApartment bedsitterApartment= new BedsitterApartment(name,location,numberOfRooms,numberOfFloors);
            apartmentDao.saveBedSitterApartment(bedsitterApartment);
            return new ModelAndView(model, "");
        }, new HandlebarsTemplateEngine());

        get("/BedsitterApartment/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String type = request.queryParams("type");
            List<BedsitterApartment> bedsitterApartments= apartmentDao.getAllWithTypeBedsitter(type);
            model.put("bedsitterApartment",bedsitterApartments);
            return new ModelAndView(model, "");
        }, new HandlebarsTemplateEngine());


        // User update
        post("/users", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String email = request.queryParams("email");
            String username = request.queryParams("username");
            String password = request.queryParams("password");
            User user = new User(name,email,username,password);
            userDao.save(user);
            return new ModelAndView(model, "");
        }, new HandlebarsTemplateEngine());

        get("/users/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<User> user = userDao.getAll();
            model.put("user",user);
            return new ModelAndView(model, "");
        }, new HandlebarsTemplateEngine());

        get("/users/:id/update",(request, response) ->
        {
            Map<String,Object>model=new HashMap<String, Object>();
            int id = Integer.parseInt(request.params("id"));
            User found=userDao.findById(id);
            model.put("found",found);
            return new ModelAndView(model,"");
        },new HandlebarsTemplateEngine());

        //Tenants
        post("/Tenants/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String phone = request.queryParams("phone");
            int roomNumber = Integer.parseInt(request.queryParams("roomNumber"));
            int floor = Integer.parseInt(request.queryParams("floor"));
            int apartmentId = Integer.parseInt(request.queryParams("apartmentId"));
            Tenants tenants = new Tenants(name,phone,roomNumber,floor,apartmentId);
            tenantsDao.saveTenant(tenants);
            return new ModelAndView(model, "");
        }, new HandlebarsTemplateEngine());

        get("/Tenants/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Tenants> tenants = tenantsDao.getAll();
            model.put("tenants",tenants);
            return new ModelAndView(model, "");
        }, new HandlebarsTemplateEngine());

        get("/tenants/:id/update",(request, response) ->
        {
            Map<String,Object>model=new HashMap<String, Object>();
            int id = Integer.parseInt(request.params("id"));
            Tenants found=tenantsDao.findById(id);
            model.put("found",found);
            return new ModelAndView(model,"");
        },new HandlebarsTemplateEngine());


        // issues update
        post("/Issues/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String type = request.queryParams("type");
            String content = request.queryParams("content");
            int apartmentId = Integer.parseInt(request.queryParams("apartmentId"));
            int roomId = Integer.parseInt(request.queryParams("roomId"));
            Issues issues = new Issues(type,content,apartmentId,roomId);
            issuesDao.saveIssue(issues);
            return new ModelAndView(model, "");
        }, new HandlebarsTemplateEngine());

        get("/Issues/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Issues> issues = issuesDao.getAll();
            model.put("issues",issues);
            return new ModelAndView(model, "");
        }, new HandlebarsTemplateEngine());

        get("/Issues/:id/update",(request, response) ->
        {
            Map<String,Object>model=new HashMap<String, Object>();
            int id = Integer.parseInt(request.params("id"));
            Issues found=issuesDao.findById(id);
            model.put("found",found);
            return new ModelAndView(model,"");
        },new HandlebarsTemplateEngine());
    }
}
