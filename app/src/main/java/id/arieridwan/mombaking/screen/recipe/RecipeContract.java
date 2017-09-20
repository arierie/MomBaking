package id.arieridwan.mombaking.screen.recipe;

import java.util.List;
import id.arieridwan.mombaking.model.Recipe;

/**
 * Created by arieridwan on 27/08/2017.
 */

public interface RecipeContract {
    interface View {
        void startLoading();
        void stopAndHide();
        void stopAndError();
        void getDataSuccess(List<Recipe> item);

    }
    interface Presenter {
        void getAllRecipe();
    }
}
