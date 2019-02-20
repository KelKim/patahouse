import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class PropertyTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
  public void Property_instantiatesCorrectly_true() {
          Property myProperty = new Property("House","Roysambu","0701", 1);
          assertEquals(true, myProperty instanceof Property);
  }
  @Test
  public void Property_instantiatesWithName_String() {
          Property myProperty = new Property("House","Roysambu","0701", 1);
          assertEquals("House", myProperty.getName());
  }
