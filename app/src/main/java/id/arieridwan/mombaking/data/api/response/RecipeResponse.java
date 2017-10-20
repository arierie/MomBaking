package id.arieridwan.mombaking.data.api.response;

import org.parceler.Parcel;
import java.util.List;

/**
 * Created by arieridwan on 27/08/2017.
 */

@Parcel
public class RecipeResponse {

    public int id;
    public String name;
    public int servings;
    public String image;
    public List<IngredientsBean> ingredients;
    public List<StepsBean> steps;

    public RecipeResponse(int id, String name, int servings, String image, List<IngredientsBean> ingredients, List<StepsBean> steps) {
        this.id = id;
        this.name = name;
        this.servings = servings;
        this.image = image;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    public RecipeResponse() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<IngredientsBean> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientsBean> ingredients) {
        this.ingredients = ingredients;
    }

    public List<StepsBean> getSteps() {
        return steps;
    }

    public void setSteps(List<StepsBean> steps) {
        this.steps = steps;
    }

    @Parcel
    public static class IngredientsBean {

        public double quantity;
        public String measure;
        public String ingredient;

        public IngredientsBean(double quantity, String measure, String ingredient) {
            this.quantity = quantity;
            this.measure = measure;
            this.ingredient = ingredient;
        }

        public IngredientsBean() {

        }

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }

        public String getMeasure() {
            return measure;
        }

        public void setMeasure(String measure) {
            this.measure = measure;
        }

        public String getIngredient() {
            return ingredient;
        }

        public void setIngredient(String ingredient) {
            this.ingredient = ingredient;
        }
    }

    @Parcel
    public static class StepsBean {

        public int id;
        public String shortDescription;
        public String description;
        public String videoURL;
        public String thumbnailURL;

        public StepsBean(int id, String shortDescription, String description, String videoURL, String thumbnailURL) {
            this.id = id;
            this.shortDescription = shortDescription;
            this.description = description;
            this.videoURL = videoURL;
            this.thumbnailURL = thumbnailURL;
        }

        public StepsBean() {

        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getShortDescription() {
            return shortDescription;
        }

        public void setShortDescription(String shortDescription) {
            this.shortDescription = shortDescription;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getVideoURL() {
            return videoURL;
        }

        public void setVideoURL(String videoURL) {
            this.videoURL = videoURL;
        }

        public String getThumbnailURL() {
            return thumbnailURL;
        }

        public void setThumbnailURL(String thumbnailURL) {
            this.thumbnailURL = thumbnailURL;
        }

    }
}