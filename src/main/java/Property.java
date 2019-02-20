import org.sql2o.*;
import java.util.List;

public class Property{
    private int id;
    private String propertyName;
    private String location;
    private String phone;
    private int vacancies;

    public Property(String propertyName,String location,String phone, int vacancies){
        this.propertyName =  propertyName;
        this.location = location;
        this.phone = phone;
        this.id = id;
        this.vacancies = vacancies;
    }

    public String getPropertyName(){
        return propertyName;
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
        String sql = "SELECT id, propertyName,location,phone FROM properties";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Property.class);
        }
    }

    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO properties (propertyName, location, phone,vacancies) VALUES (:propertyName, :location, :phone, :vacancies)";
            this.id = (int) con.createQuery(sql, true).addParameter("propertyName", this.propertyName)
                    .addParameter("location", this.location).addParameter("phone", this.phone).addParameter("vacancies", this.vacancies)
                    .executeUpdate().getKey();
        }
    }
}