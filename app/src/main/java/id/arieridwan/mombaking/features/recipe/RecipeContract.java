package id.arieridwan.mombaking.features.recipe;

import java.util.List;
import id.arieridwan.mombaking.data.api.response.RecipeResponse;

/**
 * Created by arieridwan on 27/08/2017.
 */

public interface RecipeContract {
    interface View {
        void startLoading();
        void stopAndHide();
        void stopAndError();
        void getDataSuccess(List<RecipeResponse> item);

    }
    interface Presenter {
        void getAllRecipe();
    }
}
