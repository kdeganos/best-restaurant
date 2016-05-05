import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class CuisineTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void cuisine_instantiatesCorrectly() {
    Cuisine newCuisine = new Cuisine("Italian");
    assertEquals(true, newCuisine instanceof Cuisine);
  }

  @Test
  public void getType_returnsTypeOfCuisine_String() {
    Cuisine newCuisine = new Cuisine("Mexican");
    assertEquals("Mexican", newCuisine.getType());
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Cuisine.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfBothTypesAreTheSame_True() {
    Cuisine firstCuisine = new Cuisine("Mexican");
    Cuisine secondCuisine = new Cuisine("Mexican");

    assertTrue(firstCuisine.equals(secondCuisine));
  }

  @Test
  public void save_savesInstanceToDBWithId_True() {
    Cuisine newCuisine = new Cuisine("American");
    newCuisine.save();
    Cuisine savedCuisine = Cuisine.all().get(0);
    assertEquals(newCuisine.getId(), savedCuisine.getId());
  }

  @Test
  public void find_returnsCorrectInstanceOfCuisine_True() {
    Cuisine newCuisine = new Cuisine("Thai");
    newCuisine.save();
    Cuisine foundCuisine = Cuisine.find(newCuisine.getId());
    assertTrue(newCuisine.equals(foundCuisine));
  }

}
