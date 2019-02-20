import java.util.List;
import org.sql2o.*;


public class Landlord {
  private int id;
  private String email;
  private String name;
  private String password;
  private String phone;

  public Landlord(String name, String email,String password,String phone){
    this.name = name;
    this.email = email;
    this.pass = password;
    this.phoneNumber = phone;
  }

  public int getId(){
    return id;
  }

  public String getName(){
    return name;
  }

  public String getEmail() {
    return email;
  }
  public String getPassWord() {
    return pass;
  }
  public String getPhone(){
    return phone;
  }

  public static List<Landlord> all() {
      String sql = "SELECT id, name, password, email, phone FROM landlords ORDER BY name";
      try(Connection con = DB.sql2o.open()) {
        return con.createQuery(sql).executeAndFetch(Landlord.class) ;
      }
  }
  public void save() {
   try(Connection con = DB.sql2o.open()) {
     String sql = "INSERT INTO landlords(name, password, email, phone) VALUES (:name, :password, :email, :phone)";
     this.id = (int) con.createQuery(sql, true)
       .addParameter("name", this.name)
       .addParameter("password", this.password)
       .addParameter("email", this.email)
       .addParameter("phone", this.phone)
       .executeUpdate()
       .getKey();
    }
  }
  public static Landlord find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM landlords where id=:id";
      Landlord landlord = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Landlord.class);
      return landlord;
    }
  }
  public void update(String name, String password, String email, String phone) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE landlords SET name = :name, password = :password, email = :email , phone = :phone WHERE id = :id";
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("password", password)
        .addParameter("email", email)
        .addParameter("phone", phone)
        .addParameter("id", id)
        .executeUpdate();
    }
  }
  public void delete() {
    try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM landlords WHERE id = :id;";
    con.createQuery(sql).addParameter("id", id).executeUpdate();
    }
    //deleting houses posted by the landlords
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM houses WHERE landlordid = :id;";
      con.createQuery(sql).addParameter("id", id).executeUpdate();
    }
}
//login Landlord
   public static Landlord login(String name, String password) {
     try(Connection con = DB.sql2o.open()) {
       String sql = "SELECT * FROM landlords where name=:name and password=:password";
       Landlord landlord = con.createQuery(sql)
         .addParameter("name", name)
         .addParameter("password", password)
         .executeAndFetchFirst(Landlord.class);
       return landlord;
}

}
