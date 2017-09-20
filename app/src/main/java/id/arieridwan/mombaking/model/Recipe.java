package id.arieridwan.mombaking.model;

import org.parceler.Parcel;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by arieridwan on 27/08/2017.
 */

@Getter
@Setter
@Parcel
public class Recipe {

    private int id;
    private String name;
    private int servings;
    private String image;
    private List<IngredientsBean> ingredients;
    private List<StepsBean> steps;

    public Recipe(int id, String name, int servings, String image, List<IngredientsBean> ingredients, List<StepsBean> steps) {
        this.id = id;
        this.name = name;
        this.servings = servings;
        this.image = image;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    public Recipe() {}

    @Getter
    @Setter
    @Parcel
    public static class IngredientsBean {

        private double quantity;
        private String measure;
        private String ingredient;

        public IngredientsBean(double quantity, String measure, String ingredient) {
            this.quantity = quantity;
            this.measure = measure;
            this.ingredient = ingredient;
        }

        public IngredientsBean() {}

    }

    @Getter
    @Setter
    @Parcel
    public static class StepsBean {

        private int id;
        private String shortDescription;
        private String description;
        private String videoURL;
        private String thumbnailURL;

        public StepsBean(int id, String shortDescription, String description, String videoURL, String thumbnailURL) {
            this.id = id;
            this.shortDescription = shortDescription;
            this.description = description;
            this.videoURL = videoURL;
            this.thumbnailURL = thumbnailURL;
        }

        public StepsBean() {}
    }
}
