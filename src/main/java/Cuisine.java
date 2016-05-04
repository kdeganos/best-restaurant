import java.util.List;
import org.sql2o.*;
import java.util.Arrays;

public class Cuisine {
  private String type;
  private int id;

  public Cuisine(String type){
    this.type = type;
  }

  public int getId() {
    return id;
  }

  public String getType() {
    return type;
  }

  public static List<Cuisine> all() {
    String sql = "SELECT id, type FROM cuisines";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Cuisine.class);
    }
  }

  public void removeCuisine() {

    String sql = "DELETE FROM cuisines WHERE id = :id";
    try (Connection con = DB.sql2o.open()) {
      con.createQuery(sql).addParameter("id", this.id).executeUpdate();
    }
    String sql2 = "DELETE FROM restaurants WHERE cuisine_id = :id";
    try (Connection con = DB.sql2o.open()) {
      con.createQuery(sql2).addParameter("id", this.id).executeUpdate();
    }
  }

  @Override
  public boolean equals(Object cuisine) {
    if (!(cuisine instanceof Cuisine)) {
      return false;
    } else {
      Cuisine newCuisine = (Cuisine) cuisine;
      return this.getType().equals(newCuisine.getType()) &&
             this.getId() == newCuisine.getId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO cuisines(type) VALUES (:type)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("type", this.type)
        .executeUpdate()
        .getKey();
    }
  }

  public static Cuisine find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM cuisines WHERE id=:id";
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Cuisine.class);
    }
  }

  public List<Restaurant> allCuisineRestaurants() {
    String sql = "SELECT id, name, city, rating FROM restaurants WHERE cuisine_id=" + this.getId();
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Restaurant.class);
    }
  }
}
