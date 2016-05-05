import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ReviewTest {
  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/restaurants_test", null, null);
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM reviews *;";
      con.createQuery(sql).executeUpdate();
    }
  }

  @Test
  public void reviews_instantiatesCorrectly() {
    Review newReview = new Review("Review 1", 4, 1);
    assertEquals(true, newReview instanceof Review);
  }
}
