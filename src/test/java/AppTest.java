import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import static org.assertj.core.api.Assertions.assertThat;
import org.sql2o.*; // for DB support
import org.junit.*; // for @Before and @After

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Restaurants");
  }

  @Test
  public void routeToAddCuisineIsFunctioning() {
    goTo("http://localhost:4567/");
    click("a", withText("Add A New Cuisine"));
    fill("#cuisineInput").with("Mexican");
    submit(".btn", withText("Add"));
    assertThat(pageSource()).contains("Here is a list of Cuisines");
  }
  //
  // @Test
  // public void doctorIdIsSavedToPatient() {
  //   Doctor newDoctor = new Doctor("thisdoctor", "thisspecialty");
  //   newDoctor.save();
  //   Patient newPatient = new Patient("thispatient", "00/00/0000");
  //   newPatient.save();
  //   String categoryPath = String.format("http://localhost:4567/patientInfo/%d", newPatient.getId());
  //   goTo(categoryPath);
  //   browser.$("#"+newDoctor.getId()).click();
  //   submit(".btn");
  //   assertThat(pageSource()).contains("thisdoctor");
  // }

}
