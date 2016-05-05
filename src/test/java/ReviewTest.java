import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ReviewTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void reviews_instantiatesCorrectly() {
    Review newReview = new Review("Reviewer 1", "Review 1", 4, 1);
    assertEquals(true, newReview instanceof Review);
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Review.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfBothReviewsAreTheSame_True() {
    Review firstReview = new Review("Reviewer 1", "Review 1", 4, 1);
    Review secondReview = new Review("Reviewer 1", "Review 1", 4, 1);
    assertTrue(firstReview.equals(secondReview));
  }

  @Test
  public void save_savesInstanceToDBWithId_True() {
    Review newReview = new Review("Reviewer 1", "Review 1", 4, 1);

    newReview.save();

    Review savedReview = Review.all().get(0);
    assertEquals(newReview.getId(), savedReview.getId());
  }

  @Test
  public void find_returnsCorrectInstanceOfReview_True() {
    Review newReview = new Review("Reviewer 1", "Review 1", 5, 1);
    newReview.save();
    Review foundReview = Review.find(newReview.getId());
    assertTrue(newReview.equals(foundReview));
  }
}
