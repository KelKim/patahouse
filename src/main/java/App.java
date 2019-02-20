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
        

        // before("/dashboard", (request, response) -> {
        //     String email= request.session().attribute("email");
        //     if(email==null){
        //       response.redirect("/");
        //       halt();
        //     }
        //   });

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

        get("/loginerror", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/loginerror.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
    }
}
