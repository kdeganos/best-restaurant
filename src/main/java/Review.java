import java.util.List;
import org.sql2o.*;
import java.util.Arrays;

public class Review {
  private String review;
  private int rating;
  private int id;
  private int restaurant_id;

  public Review(String review, int rating, int restaurant_id) {
    this.review = review;
    this.rating = rating;
    this.restaurant_id = restaurant_id;
  }

  public String getReview() {
    return review;
  }

  public int getRating(){
    return rating;
  }

  public int getRestaurantId() {
    return restaurant_id;
  }

  public static List<Review> all() {
    String sql = "SELECT id, review, rating, restaurant_id FROM reviews";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Review.class);
    }
  }
}
