import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class CuisineTest {
  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/restaurants_test", null, null);
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM restaurants *;";
      con.createQuery(sql).executeUpdate();
    }
  }

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


  // @Test
  // public void getId_returnsIdOfCuisine_1() {
  //   Cuisine newCuisine = newCuisine("Chinese");
  //   newCuisine.save();
  //   assertEquals(1, newCuisine.getId());
  // }

}
