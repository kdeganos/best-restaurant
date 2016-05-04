import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App{
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) ->{
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/addCuisine", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/add-cuisine.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/listCuisines", (request, response) ->{
      Map<String, Object> model = new HashMap<String, Object>();
      String type = request.queryParams("cuisineInput");
      Cuisine newCuisine = new Cuisine(type);
      newCuisine.save();
      Boolean addedNewCuisine = true;

      model.put("cuisines", Cuisine.all());
      model.put("addedNewCuisine", addedNewCuisine);
      model.put("template", "templates/list-cuisines.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/addRestaurant/:id", (request, response) ->{
      Map<String, Object> model = new HashMap<String, Object>();

      Cuisine cuisine = Cuisine.find(Integer.parseInt(request.params(":id")));

      model.put("cuisine", cuisine);
      model.put("template", "templates/add-restaurant.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/addRestaurant", (request,response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String restaurantName = request.queryParams("restaurantName");
      String restaurantCity = request.queryParams("restaurantCity");
      String restaurantRating = request.queryParams("restaurantRating");
      int cuisine_id = Integer.parseInt(request.queryParams("cuisine_id"));

      Restaurant newRestaurant = new Restaurant(restaurantName, restaurantCity, restaurantRating, cuisine_id);
      newRestaurant.save();

      Cuisine cuisine = Cuisine.find(cuisine_id);

      Boolean addedNewRestaurant = true;
      model.put("addedNewRestaurant", addedNewRestaurant);
      model.put("cuisine", cuisine);
      model.put("template", "templates/add-restaurant.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/restaurant/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Restaurant restaurant = Restaurant.find(Integer.parseInt(request.params(":id")));
      Cuisine cuisine = Cuisine.find(restaurant.getCuisineId());
      model.put("restaurant", restaurant);
      model.put("cuisine", cuisine);
      model.put("template", "templates/restaurant.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/addRestaurant", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/add-restaurant.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/listCuisines", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("cuisines", Cuisine.all());
      model.put("template", "templates/list-cuisines.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/listRestaurants", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("restaurants", Restaurant.all());
      model.put("template", "templates/list-restaurants.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/removeCuisine/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Cuisine cuisine = Cuisine.find(Integer.parseInt(request.params(":id")));
      cuisine.removeCuisine();
      model.put("template", "templates/add-cuisine.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/removeRestaurant/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Cuisine cuisine = Cuisine.find(Integer.parseInt(request.queryParams("cuisineId")));
      Restaurant restaurant = Restaurant.find(Integer.parseInt(request.params(":id")));
      restaurant.removeRestaurant();

      model.put("cuisine", cuisine);
      model.put("template", "templates/add-restaurant.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
