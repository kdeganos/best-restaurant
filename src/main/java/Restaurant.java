import java.util.List;
import org.sql2o.*;
import java.util.Arrays;

public class Restaurant {
  private String name;
  private String city;
  private String rating;
  private int id;
  private int cuisine_id;

  public Restaurant(String name, String city, String rating, int cuisine_id){
    this.name = name;
    this.city = city;
    this.rating = rating;
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

  public String getRating() {
    return rating;
  }

  public static List<Restaurant> all() {
    String sql = "SELECT name, city, rating, cuisine_id, id FROM restaurants";
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
      return this.getName().equals(newRestaurant.getName());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO restaurants(name, city, rating, cuisine_id) VALUES (:name, :city, :rating, :cuisine_id)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("city", this.city)
        .addParameter("rating", this.rating)
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
}
