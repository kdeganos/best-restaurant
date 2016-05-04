import java.util.List;
import org.sql2o.*;
import java.util.Arrays;

public class Cuisine {
  private String type;
  private int id;

  public Cuisine(String type){
    this.type = type;
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
}
