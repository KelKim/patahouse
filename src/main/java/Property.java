import org.sql2o.*;
import java.util.List;

public class Property{
    private int id;
    private String propertyname;
    private String location;
    private String phone;
    private int vacancies;

    public Property(String propertyname,String location,String phone, int vacancies){
        this.propertyname =  propertyname;
        this.location = location;
        this.phone = phone;
        this.id = id;
        this.vacancies = vacancies;
    }

    public String getPropertyName(){
        return propertyname;
    }

    public String getLocation(){
        return location;
    }

    public String getPhone(){
        return phone;
    }

    public int getId(){
        return id;
    }

    public int getVacancies(){
        return vacancies;
    }

    public static List<Property> all() {
        String sql = "SELECT id, propertyname,vacancies,location,phone FROM properties";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Property.class);
        }
    }

    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO properties (propertyname, location, vacancies, phone) VALUES (:propertyname,:location, :vacancies, :phone)";
            this.id = (int) con.createQuery(sql, true).addParameter("propertyname", this.propertyname).addParameter("vacancies", this.vacancies)
                    .addParameter("location", this.location).addParameter("phone", this.phone)
                    .executeUpdate().getKey();
        }
    }

    public static Property find(int id) {
        try(Connection con = DB.sql2o.open()) {
          String sql = "SELECT * FROM properties where id=:id";
          Property property = con.createQuery(sql)
            .addParameter("id", id)
            .throwOnMappingFailure(false)
            .executeAndFetchFirst(Property.class);
          if(property == null){
            throw new IndexOutOfBoundsException("I'm sorry, I think this property does not exist");
          }
          return property;
        }
      }

    public static Property findByLocation(String phone) {
        try(Connection con = DB.sql2o.open()) {
          String sql = "SELECT * FROM properties where phone=:phone";
          Property property = con.createQuery(sql)
            .addParameter("phone", phone)
            .throwOnMappingFailure(false)
            .executeAndFetchFirst(Property.class);
          return property;
        }
      }

      public void delete() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM properties WHERE id = :id;";
            con.createQuery(sql).addParameter("id", id).executeUpdate();
        }
    }
}