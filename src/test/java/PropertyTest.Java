import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class PropertyTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
  public void Property_instantiatesCorrectly_true() {
          Property myProperty = new Property("House","Roysambu","0701", 4, 1);
          assertEquals(true, myProperty instanceof Property);
  }
  @Test
  public void Property_instantiatesWithName_String() {
          Property myProperty = new Property("House","Roysambu","0701", 4, 1);
          assertEquals("House", myProperty.getName());
  }
  @Test
public void location_instantiatesCorrectly_true() {
  assertEquals("Here", testProperty.getLocation());
}
@Test
public void phone_instantiatesCorrectly_true() {
  assertEquals("0701", testProperty.getPhone());
}
@Test
public void vacancies_instantiatesCorrectly_true() {
  assertEquals("4", testProperty.getVacancies());
}
@Test
public void getId_propertiesInstantiateWithAnID() {
  Property myProperty = new Property("House");
  myProperty.save();
  assertTrue(myProperty.getId() > 0);
}
@Test
public void all_returnsAllInstancesOfProperty_true() {
        Property firstProperty = new Property("House");
        firstProperty.save();
        Property secondProperty = new Property("room");
        secondProperty.save();
        assertEquals(true,Property.all().get(0).equals(firstProperty));
        assertEquals(true,Property.all().get(1).equals(secondProperty));
}
public void equals_returnsTrueIfNamesAretheSame() {
  Property firstProperty = new Property("house");
  Property secondProperty = new Property("house");
  assertTrue(firstProperty.equals(secondProperty));
}
@Test
public void save_returnsTrueIfNamesAretheSame() {
  Property myProperty = new Property("house");
  myProperty.save();
  assertTrue(Property.all().get(0).equals(myProperty));
}
@Test
public void save_assignsIdToObject() {
  Property myProperty = new Property("house");
  myProperty.save();
  Property savedProperty = Property.all().get(0);
  assertEquals(myProperty.getId(), savedProperty.getId());
}
@Test
public void find_returnsPropertyWithSameId_secondProperty() {
  Property firstProperty = new Property("house");
  firstProperty.save();
  Property secondProperty = new Property("room");
  secondProperty.save();
  assertEquals(Property.find(secondProperty.getId()), secondProperty);
}
