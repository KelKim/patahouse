import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  @Override
  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/patahouse", "licio", "9353");
  }

  @Override
  protected void after() {
    try (Connection con = DB.sql2o.open()) {
      String deletePropertiesQuery = "DELETE FROM properties *;";
      String deleteTenantsQuery = "DELETE FROM tenants *;";
      con.createQuery(deleteTenantsQuery).executeUpdate();
      con.createQuery(deletePropertiesQuery).executeUpdate();
    }
  }
}
