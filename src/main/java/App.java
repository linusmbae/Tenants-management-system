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
            int roomnumber = Integer.parseInt(request.queryParams("roomnumber"));
            int apartmentId = Integer.parseInt(request.queryParams("apartmentId"));
            Tenants tenants = new Tenants(name,phone,roomnumber,apartmentId,);
            //user.save();
            return new ModelAndView(model, "");
        }, new HandlebarsTemplateEngine());

        get("/Tenants", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String phone = request.queryParams("phone");
            int roomnumber = Integer.parseInt(request.queryParams("roomnumber"));
            int apartmentId = Integer.parseInt(request.queryParams("apartmentId"));
            Tenants tenants = new Tenants(name,phone,roomnumber,apartmentId,);
            //user.save();
            return new ModelAndView(model, "");
        }, new HandlebarsTemplateEngine());

    }
}
