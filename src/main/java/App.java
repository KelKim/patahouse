import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

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








     get("/login", (request, response) -> {
     Map<String, Object> model = new HashMap<String, Object>();
     model.put("template", "templates/landlord_portal.vtl");
     return new ModelAndView(model, layout);
   }, new VelocityTemplateEngine());


   post("/login", (request, response) -> {
       Map<String, Object> model = new HashMap<String, Object>();
       String name = request.queryParams("name").toUpperCase();
       String password = request.queryParams("password");
       // Gen gen = new Gen();
       int found = Gen.LandlordChecker(name,password);

       if(found == 0){
         //Wrong username & Password
         model.put("fontColor", "red");
         model.put("msg", "Wrong Password");
         model.put("link", "/login");
         model.put("linkto", "Back to Login Page");
         model.put("template", "templates/success.vtl");
       }else{
         Landlord landlord = Landlord.login(name, password);
         int landlordid = landlord.getId();
         response.redirect("/landlord/"+landlordid);
       }
       return new ModelAndView(model, layout);
       }, new VelocityTemplateEngine());


       // Saving new landlord
 post("/landlords", (request, response) -> {
   Map<String, Object> model = new HashMap<String, Object>();
   String name = request.queryParams("name");
   String password = request.queryParams("password");
   String email = request.queryParams("email");
   String phone = request.queryParams("phone");
   Seller newLandlord = new Landlord(name, password, email, phone);
   newLandlord.save();
   model.put("fontColor", "green");
   model.put("msg", "Added Successfuly!");
   model.put("link", "/login");
   model.put("linkto", "Back to Login");
   model.put("template", "templates/success.vtl");
   return new ModelAndView(model, layout);
}, new VelocityTemplateEngine());



      // Updating Landlord
      post("/landlord", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        int landlordId = Integer.parseInt(request.queryParams("landlordid"));
        Landlord landlord = Landlord.find(landlordId);
        String name = request.queryParams("name").toUpperCase();
        String password = request.queryParams("password");
        String email = request.queryParams("email");
        String phone = request.queryParams("phone");
        landlord.update(name, password, email, phone);

        response.redirect("/landlord/"+landlordId);

        return new ModelAndView(model, layout);
}, new VelocityTemplateEngine());


//Loading LandlordPage
        get("/landlord/:id", (request, response) -> {
          Map<String, Object> model = new HashMap<String, Object>();
          Landlord landlord = Landlord.find(Integer.parseInt(request.params(":id")));
          model.put("landlord", landlord);
          model.put("template", "templates/landlordpage.vtl");

          return new ModelAndView(model, layout);
}, new VelocityTemplateEngine());

//DELETING Landlord
            post("/landlord/:id/delete", (request, response) -> {
              Map<String, Object> model = new HashMap<String, Object>();
              Landlord landlord = Landlord.find(Integer.parseInt(request.params(":id")));
              landlord.delete();
              // response.redirect("/login");
              model.put("fontColor", "white");
              model.put("msg", "Account Deleted Successfuly!!");
              model.put("link", "/login");
              model.put("linkto", "Back to Login Page");
              model.put("template", "templates/success.vtl");
              return new ModelAndView(model, layout);
}, new VelocityTemplateEngine());



// Canceling LandlordEdit
post("/clandlord", (request, response) -> {
Map<String, Object> model = new HashMap<String, Object>();
int landlordId = Integer.parseInt(request.queryParams("landlordid"));
response.redirect("/landlord/"+landlordId);

return new ModelAndView(model, layout);
}, new VelocityTemplateEngine());
