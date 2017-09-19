package id.arieridwan.mombaking.screen.recipe;

import id.arieridwan.mombaking.model.Recipe;

/**
 * Created by arieridwan on 27/08/2017.
 */

public interface RecipeContract {
    interface View {
        void startLoading();
        void stopAndHide();
        void stopAndError();
        void getDataSuccess(Recipe item, String filter);

    }
    interface Presenter {
        void getAllRecipe();
    }
}
