import java.util.List;
import org.sql2o.*;
import java.util.Arrays;

public class Restaurant {
  private String name;
  private String city;
  private int id;
  private int cuisine_id;

  public Restaurant(String name, String city, int cuisine_id){
    this.name = name;
    this.city = city;
    this.cuisine_id = cuisine_id;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getCity() {
    return city;
  }

  public int getCuisineId() {
    return cuisine_id;
  }

  public static List<Restaurant> all() {
    String sql = "SELECT name, city, cuisine_id, id FROM restaurants";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Restaurant.class);
    }
  }

  @Override
  public boolean equals(Object restaurant) {
    if (!(restaurant instanceof Restaurant)) {
      return false;
    } else {
      Restaurant newRestaurant = (Restaurant) restaurant;
      return this.getName().equals(newRestaurant.getName()) &&
             this.getId() == newRestaurant.getId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO restaurants(name, city, cuisine_id) VALUES (:name, :city, :cuisine_id)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("city", this.city)
        .addParameter("cuisine_id", this.cuisine_id)
        .executeUpdate()
        .getKey();
    }
  }

  public static Restaurant find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM restaurants WHERE id=:id";
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Restaurant.class);
    }
  }

  public void removeRestaurant() {
    String sql = "DELETE FROM reviews WHERE restaurant_id = :id";
    try (Connection con = DB.sql2o.open()) {
      con.createQuery(sql).addParameter("id", this.id).executeUpdate();
    }
    String sql2 = "DELETE FROM restaurants WHERE id = :id";
    try (Connection con = DB.sql2o.open()) {
      con.createQuery(sql2).addParameter("id", this.id).executeUpdate();
    }
  }

  public List<Review> allRestaurantReviews() {
    String sql = "SELECT id, reviewer, review, rating FROM reviews WHERE restaurant_id=:id";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).addParameter("id", id).executeAndFetch(Review.class);
    }
  }
}
