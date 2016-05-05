import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class RestaurantTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void restaurant_instantiatesCorrectly() {
    Restaurant newRestaurant = new Restaurant("Restaurant 1", "Portland", 1);
    assertEquals(true, newRestaurant instanceof Restaurant);
  }

  @Test
  public void getName_returnsNameOfRestaurant_String() {
    Restaurant newRestaurant = new Restaurant("Restaurant 1", "Portland", 1);
    assertEquals("Restaurant 1", newRestaurant.getName());
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Restaurant.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfBothNamesAreTheSame_True() {
    Restaurant firstRestaurant = new Restaurant("Restaurant 1", "Portland", 1);
    Restaurant secondRestaurant = new Restaurant("Restaurant 1", "Portland", 1);

    assertTrue(firstRestaurant.equals(secondRestaurant));
  }

  @Test
  public void all_takesRestaurants_2() {
    Restaurant firstRestaurant = new Restaurant("Restaurant 1", "Portland", 1);
    Restaurant secondRestaurant = new Restaurant("Restaurant 2", "Portland", 1);
    firstRestaurant.save();
    secondRestaurant.save();
    assertEquals(Restaurant.all().size(), 2);
  }

  @Test
  public void save_savesInstanceToDBWithId_True() {
    Restaurant newRestaurant = new Restaurant("Restaurant 1", "Portland", 1);

    newRestaurant.save();

    Restaurant savedRestaurant = Restaurant.all().get(0);
    assertEquals(newRestaurant.getId(), savedRestaurant.getId());
  }

  @Test
  public void find_returnsCorrectInstanceOfRestaurant_True() {
    Restaurant newRestaurant = new Restaurant("Restaurant 1", "Portland", 1);
    newRestaurant.save();
    Restaurant foundRestaurant = Restaurant.find(newRestaurant.getId());
    assertTrue(newRestaurant.equals(foundRestaurant));
  }

}
