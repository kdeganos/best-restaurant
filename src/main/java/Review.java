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

  public int getId() {
    return id;
  }

  public static List<Review> all() {
    String sql = "SELECT id, review, rating, restaurant_id FROM reviews";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Review.class);
    }
  }

  @Override
  public boolean equals(Object review) {
    if (!(review instanceof Review)) {
      return false;
    } else {
      Review newReview = (Review) review;
      return this.getReview().equals(newReview.getReview());
    }
  }
  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO reviews(review, rating, restaurant_id) VALUES (:review, :rating, :restaurant_id)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("review", this.review)
        .addParameter("rating", this.rating)
        .addParameter("restaurant_id", this.restaurant_id)
        .executeUpdate()
        .getKey();
    }
  }
  public static Review find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM reviews WHERE id=:id";
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Review.class);
    }
  }
}
