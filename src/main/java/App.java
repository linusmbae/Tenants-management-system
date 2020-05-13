import models.Apartment;
import models.Issues;
import models.Tenants;
import models.User;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
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
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        post("/users", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String email = request.queryParams("email");
            String username = request.queryParams("username");
            String password = request.queryParams("password");
            User user = new User(name,email,username,password);
            //user.save();
            return new ModelAndView(model, "");
        }, new HandlebarsTemplateEngine());

        get("/users", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String email = request.queryParams("email");
            String username = request.queryParams("username");
            String password = request.queryParams("password");
            User user = new User(name,email,username,password);
            //user.save();
            return new ModelAndView(model, "");
        }, new HandlebarsTemplateEngine());

        post("/Tenants", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String phone = request.queryParams("phone");
            int roomNumber = Integer.parseInt(request.queryParams("roomNumber"));
            int floor = Integer.parseInt(request.queryParams("floor"));
            String apartmentId =  request.queryParams("apartmentId");
            Tenants tenants = new Tenants(name,phone,roomNumber,floor,apartmentId);
            //user.save();
            return new ModelAndView(model, "");
        }, new HandlebarsTemplateEngine());

        get("/Tenants", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String phone = request.queryParams("phone");
            int roomNumber = Integer.parseInt(request.queryParams("roomNumber"));
            int floor = Integer.parseInt(request.queryParams("floor"));
            String apartmentId =  request.queryParams("apartmentId");
            Tenants tenants = new Tenants(name,phone,roomNumber,floor,apartmentId);
            //user.save();
            return new ModelAndView(model, "");
        }, new HandlebarsTemplateEngine());

        post("/issues", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String type = request.queryParams("type");
            String content = request.queryParams("content");
            int  apartmentId = Integer.parseInt(request.queryParams("apartmentId "));
            int roomId = Integer.parseInt(request.queryParams("roomId"));
            Issues issues = new Issues(type,content,apartmentId,roomId);
            //user.save();
            return new ModelAndView(model, "");
        }, new HandlebarsTemplateEngine());

        get("/issues", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String type = request.queryParams("type");
            String content = request.queryParams("content");
            int  apartmentId = Integer.parseInt(request.queryParams("apartmentId "));
            int roomId = Integer.parseInt(request.queryParams("roomId"));
            Issues issues = new Issues(type,content,apartmentId,roomId);
            //user.save();
            return new ModelAndView(model, "");
        }, new HandlebarsTemplateEngine());

        post("/apartment", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String location = request.queryParams("location");
            String type = request.queryParams("type");
            int  numberOfRooms = Integer.parseInt(request.queryParams("numberOfRooms"));
            int numberOfFloors = Integer.parseInt(request.queryParams("numberOfFloors"));
            Apartment apartment = new Apartment() {
            };
            //user.save();
            return new ModelAndView(model, "");
        }, new HandlebarsTemplateEngine());

        get("/apartment", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String location = request.queryParams("location");
            String type = request.queryParams("type");
            int  numberOfRooms = Integer.parseInt(request.queryParams("numberOfRooms"));
            int numberOfFloors = Integer.parseInt(request.queryParams("numberOfFloors"));
            Apartment apartment = new Apartment() {
            };
            //user.save();
            return new ModelAndView(model, "");
        }, new HandlebarsTemplateEngine());

        post("/OneBedroomApartment", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String location = request.queryParams("location");
            int  numberOfRooms = Integer.parseInt(request.queryParams("numberOfRooms"));
            int numberOfFloors = Integer.parseInt(request.queryParams("numberOfFloors"));
            Apartment apartment = new Apartment() {
            };
            //user.save();
            return new ModelAndView(model, "");
        }, new HandlebarsTemplateEngine());

        get("/OneBedroomApartment", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String location = request.queryParams("location");
            int  numberOfRooms = Integer.parseInt(request.queryParams("numberOfRooms"));
            int numberOfFloors = Integer.parseInt(request.queryParams("numberOfFloors"));
            Apartment apartment = new Apartment() {
            };
            //user.save();
            return new ModelAndView(model, "");
        }, new HandlebarsTemplateEngine());

    }
}
