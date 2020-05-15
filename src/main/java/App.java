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
    static int getHerokuAsignedPort() {
        ProcessBuilder process = new ProcessBuilder();
        if (process.environment().get("PORT") != null) {
            return Integer.parseInt(process.environment().get("PORT"));
        } else {
            return 4567;
        }
    }
    public  static void main(String[] args) {

        port(getHerokuAsignedPort());
        staticFileLocation("/public");
        Connection conn;
        Sql2oUserDao userDao;
        Sql2oApartmentDao apartmentDao;
        Sql2oIssuesDao issuesDao;
        Sql2oTenantsDao tenantsDao;
//        String connectionString = "jdbc:postgresql://localhost:5432/tenants_manager";
//        Sql2o sql2o = new Sql2o(connectionString, "alphania", "2020");
        String connectionString = "jdbc:postgresql://ec2-34-230-149-169.compute-1.amazonaws.com:5432/d87uolhc64tb4l";
        Sql2o sql2o = new Sql2o(connectionString,"bkgumqoczgjnwb", "1ce511fafa472a9c733c86627dd69c8fe4c7db52693b06cd5d965fcfba2fdc2e");
        userDao=new Sql2oUserDao(sql2o);
        apartmentDao=new Sql2oApartmentDao(sql2o);
        issuesDao=new Sql2oIssuesDao(sql2o);
        tenantsDao=new Sql2oTenantsDao(sql2o);
        conn=sql2o.open();

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());
        get("/home", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "home.hbs");
        }, new HandlebarsTemplateEngine());

        // Users
        get("/Profile", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            int id = 1;
            User found= userDao.findById(id);
            model.put("found",found);
            return new ModelAndView(model, "profile.hbs");
        }, new HandlebarsTemplateEngine());

        get("/user/sign-up", (request, response) ->{
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "sign-up.hbs");
        }, new HandlebarsTemplateEngine() );

        post("/users/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String email = request.queryParams("email");
            String username = request.queryParams("username");
            String password = request.queryParams("password");
            User user = new User(name,email,username,password);
            if (user != null){
                userDao.save(user);
                User foundUser = userDao.findById(user.getId());
                System.out.println("Your user id is: " + foundUser.getId());
            }else {
                System.out.println("Failed");
            }
            return new ModelAndView(model, "successful.hbs");
        }, new HandlebarsTemplateEngine());

        post("/user/login", (request, response) ->{
            Map<String, Object> model = new HashMap<>();
            String userName = request.queryParams("username");
            String password = request.queryParams("password");
            int id = Integer.parseInt(request.params("id"));
            User user = userDao.findById(id);
            if (userName.equals(user.getUserName()) && password.equals(user.getPassword()) && id==user.getId()) {
                return new ModelAndView(model, "home.hbs");
            } else {
                System.out.println("Your username or password is incorrect!");
                return new ModelAndView(model, "index.hbs");
            }
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
        get("/Tenants/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "tenant-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/Tenants/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String phone = request.queryParams("phone");
            int roomNumber = Integer.parseInt(request.queryParams("roomNumber"));
            int floor = Integer.parseInt(request.queryParams("floor"));
            int apartmentId = Integer.parseInt(request.queryParams("apartmentId"));
            Tenants tenants = new Tenants(name,phone,roomNumber,floor,apartmentId);
            tenantsDao.saveTenant(tenants);
            response.redirect("/Tenants");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/Tenants", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Tenants> tenants = tenantsDao.getAll();
            model.put("tenants",tenants);
            return new ModelAndView(model, "tenants.hbs");
        }, new HandlebarsTemplateEngine());

        get("/tenants/:id",(request, response) ->
        {
            Map<String,Object>model=new HashMap<String, Object>();
            int id = Integer.parseInt(request.params("id"));
            Tenants found=tenantsDao.findById(id);
            model.put("found",found);
            return new ModelAndView(model,"");
        },new HandlebarsTemplateEngine());

        //apartments
        get("/Apartments/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "apartment-form.hbs");
        }, new HandlebarsTemplateEngine());


        //one bed room apartment
        post("/Apartments/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String location = request.queryParams("location");
            String type = request.queryParams("type");
            int numberOfRooms = Integer.parseInt(request.queryParams("numberOfRooms"));
            int numberOfFloors = Integer.parseInt(request.queryParams("numberOfFloors"));
            if (type.equals("One Bedroom")) {
                OneBedroomApartment oneBedroomApartment = new OneBedroomApartment(name, location, numberOfRooms, numberOfFloors);
                apartmentDao.saveOneBedroomApartment(oneBedroomApartment);
            } else {
                BedsitterApartment bedsitterApartment = new BedsitterApartment(name, location, numberOfRooms, numberOfFloors);
                apartmentDao.saveBedSitterApartment(bedsitterApartment);
            }
            return new ModelAndView(model, "home.hbs");
        }, new HandlebarsTemplateEngine());

        get("/Apartments", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String type = "One Bedroom";
            if (type.equals("One Bedroom")) {
                List<OneBedroomApartment> apartments = apartmentDao.getAllWithTypeOneBedroom(type);
                model.put("apartments", apartments);
            }
            return new ModelAndView(model, "apartments.hbs");
        }, new HandlebarsTemplateEngine());

        get("/bedsitters",(request, response) ->
        {
            Map<String,Object>model= new HashMap<>();
            String type1="Bedsitter";
            if(type1.equals("Bedsitter")){
            List<BedsitterApartment>bedsitterApartments=apartmentDao.getAllWithTypeBedsitter(type1);
            model.put("bedsitterApartments",bedsitterApartments);
        }
            return new ModelAndView(model, "bedsitter.hbs");
        },new HandlebarsTemplateEngine());
        get("/OneBedroomApartment/:id",(request, response) ->
        {
            Map<String,Object>model=new HashMap<String, Object>();
            int id = Integer.parseInt(request.params("id"));
            OneBedroomApartment found=apartmentDao.findOneBedroomById(id);
            model.put("found",found);
            return new ModelAndView(model,"");
        },new HandlebarsTemplateEngine());

        get("/BedsitterApartment/:id",(request, response) ->
        {
            Map<String,Object>model=new HashMap<String, Object>();
            int id = Integer.parseInt(request.params("id"));
            BedsitterApartment found=apartmentDao.findBedsitterById(id);
            model.put("found",found);
            return new ModelAndView(model,"");
        },new HandlebarsTemplateEngine());

        //issues
        get("/Issues/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String type ="Bedsitter";
            List<BedsitterApartment>bedsitterApartments=apartmentDao.getAllWithTypeBedsitter(type);
            model.put("bedsitterApartments",bedsitterApartments);
            return new ModelAndView(model, "issue-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/Issues/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String type = request.queryParams("issue");
            String content = request.queryParams("content");
            int apartmentId = Integer.parseInt(request.queryParams("apartmentId"));
            int roomId = Integer.parseInt(request.queryParams("roomId"));
            Issues issues = new Issues(type,content,apartmentId,roomId);
            issuesDao.saveIssue(issues);
            response.redirect("/Issues");
            return  null;
        }, new HandlebarsTemplateEngine());

        get("/Issues/new/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String type ="One Bedroom";
            List<OneBedroomApartment>oneBedroomApartments=apartmentDao.getAllWithTypeOneBedroom(type);
            model.put("oneBedroomApartments",oneBedroomApartments);
            return new ModelAndView(model, "oneBedroomIssues.hbs");
        }, new HandlebarsTemplateEngine());

        post("/Issues/new/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String type = request.queryParams("issue");
            String content = request.queryParams("content");
            int apartmentId = Integer.parseInt(request.queryParams("apartmentId"));
            int roomId = Integer.parseInt(request.queryParams("roomId"));
            Issues issues = new Issues(type,content,apartmentId,roomId);
            issuesDao.saveIssue(issues);
            response.redirect("/Issues");
            return  null;
        }, new HandlebarsTemplateEngine());


        get("/Issues", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Issues> issues = issuesDao.getAll();
            model.put("issues",issues);
            return new ModelAndView(model, "issue.hbs");
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
            String type=request.queryParams("type");
            int numberOfRooms = Integer.parseInt(request.queryParams("numberOfRooms"));
            int numberOfFloors = Integer.parseInt(request.queryParams("numberOfFloors"));
            int idToUpdate = Integer.parseInt(request.queryParams("id"));
                    apartmentDao.updateOneBedroom(name,location,type,numberOfRooms,numberOfFloors,idToUpdate);
            return new ModelAndView(model, "");
        }, new HandlebarsTemplateEngine());


      get("/OneBedroomApartment/:id/update",(request, response) ->
        {
            Map<String,Object>model=new HashMap<String, Object>();
            int id = Integer.parseInt(request.params("id"));
            OneBedroomApartment found=apartmentDao.findOneBedroomById(id);
            model.put("found",found);
            return new ModelAndView(model,"");
        },new HandlebarsTemplateEngine());



        //update bedsitter
        post("/BedsitterApartment/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String location = request.queryParams("location");
            String type=request.queryParams("type");
            int numberOfRooms = Integer.parseInt(request.queryParams("numberOfRooms"));
            int numberOfFloors = Integer.parseInt(request.queryParams("numberOfFloors"));
            int id=Integer.parseInt(request.params("id"));
            apartmentDao.updateBedsitter(name,location,type,numberOfRooms,numberOfFloors,id);
            return new ModelAndView(model, "");
        }, new HandlebarsTemplateEngine());

        get("/BedsitterApartment/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int id=Integer.parseInt(request.params("id"));
            BedsitterApartment bedsitterApartments=apartmentDao.findBedsitterById(id);
            model.put("bedsitterApartment",bedsitterApartments);
            return new ModelAndView(model, "");
        }, new HandlebarsTemplateEngine());


        // User update
        post("/Profile/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String email = request.queryParams("email");
            String username = request.queryParams("username");
            String password = request.queryParams("password");
            int id = 1;
            userDao.update(id, name,email,username,password);
            response.redirect("/Profile");
            return null;
        }, new HandlebarsTemplateEngine());

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
            String type = request.queryParams("issue");
            String content = request.queryParams("content");
            int apartmentId = Integer.parseInt(request.queryParams("apartmentId"));
            int roomId = Integer.parseInt(request.queryParams("roomId"));
            int id = Integer.parseInt(request.queryParams("id"));
            issuesDao.update(id,type,content,apartmentId,roomId);
            response.redirect("/Issues");
            return null;
        }, new HandlebarsTemplateEngine());

//        get("/Issues/:id/update", (request, response) -> {
//            Map<String, Object> model = new HashMap<>();
//            List<Issues> issues = issuesDao.getAll();
//            model.put("issues",issues);
//            return new ModelAndView(model, "");
//        }, new HandlebarsTemplateEngine());

        get("/Issues/:id/updateMe",(request, response) ->
        {
            Map<String,Object>model=new HashMap<String, Object>();
            int id = Integer.parseInt(request.params("id"));
            Issues found=issuesDao.findById(id);
            model.put("found",found);
            return new ModelAndView(model,"editIssue.hbs");
        },new HandlebarsTemplateEngine());

        post("/user/:id/delete",(request, response) ->
        {
            Map<String,Object>model=new HashMap<String, Object>();
            int id =Integer.parseInt(request.params("id"));
            userDao.deleteById(id);
            response.redirect("/users");
            return null;
        },new HandlebarsTemplateEngine());
        post("/tenants/:id/delete",(request, response) ->
        {
            Map<String,Object>model=new HashMap<String, Object>();
            int id =Integer.parseInt(request.params("id"));
            tenantsDao.deleteById(id);
            response.redirect("/tenants");
            return null;
        },new HandlebarsTemplateEngine());

        post("/issues/:id/deleteMe",(request, response) ->
        {
            Map<String,Object>model=new HashMap<String, Object>();
            int id =Integer.parseInt(request.params("id"));
            issuesDao.deleteById(id);
            response.redirect("/Issues");
            return null;
        },new HandlebarsTemplateEngine());

        post("/bedsitter/:id/delete",(request, response) ->
        {
            Map<String ,Object>model=new HashMap<String,Object>();
            int id =Integer.parseInt(request.params("id"));
            apartmentDao.deleteByBedsitterId(id);
            response.redirect("");
            return null;
        },new HandlebarsTemplateEngine());

        post("/onebedroom/:id/delete",(request, response) ->
        {
            Map<String ,Object>model=new HashMap<String,Object>();
            int id =Integer.parseInt(request.params("id"));
            apartmentDao.deleteByOneBedroomId(id);
            response.redirect("");
            return null;
        },new HandlebarsTemplateEngine());

        post("/apartments/bedsitter/clear",(request, response) ->
        {
            Map<String,Object>model=new HashMap<String, Object>();
            String type=request.queryParams("type");
            apartmentDao.deleteBedsitter(type);
            response.redirect("/");
            return null;
        },new HandlebarsTemplateEngine());

        post("/apartments/oneBedroom/clear",(request, response) ->
        {
            Map<String,Object>model=new HashMap<String, Object>();
            String type=request.queryParams("type");
            apartmentDao.deleteOneBedroom(type);
            response.redirect("/");
            return null;
        },new HandlebarsTemplateEngine());
    }
}
