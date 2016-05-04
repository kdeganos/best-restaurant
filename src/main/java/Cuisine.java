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

  @Override
  public boolean equals(Object cuisine) {
    if (!(cuisine instanceof Cuisine)) {
      return false;
    } else {
      Cuisine newCuisine = (Cuisine) cuisine;
      return this.getType().equals(newCuisine.getType());
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
}
