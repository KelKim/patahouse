import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.HashMap;
import java.util.Map;

public class App {

    public static void main(String[] args) {
        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }

        setPort(port);

        staticFileLocation("/public");
        String layout = "templates/layout.vtl";
        

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/index.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/dashboard", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/dashboard.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        // get("/loginerror", (request, response) -> {
        //     Map<String, Object> model = new HashMap<String, Object>();
        //     model.put("template", "templates/loginerror.vtl");
        //     return new ModelAndView(model, layout);
        // }, new VelocityTemplateEngine());


        post("/dashboard", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String propertyname = request.queryParams("propertyname");
            int vacancies = Integer.parseInt(request.queryParams("vacancies"));
            String phone = request.queryParams("phone");
            String location = request.queryParams("location");
            
            Property newProperty = new Property(propertyname, phone, location,vacancies);
            newProperty.save();
            model.put("template", "templates/flat-success.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/flats", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("properties", Property.all());
            model.put("template", "templates/flats.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/properties/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Property property = Property.find(Integer.parseInt(request.params(":id")));
            model.put("property", property);
            model.put("template", "templates/flat.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/properties/:id/delete", (request, response) -> {
            HashMap<String, Object> model = new HashMap<String, Object>();
            Property property = Property.find(Integer.parseInt(request.params("id")));
            // Stylist stylist = Stylist.find(stylist.getId());
            property.delete();
            response.redirect("/flats");
            return null;
        });



    }
}
