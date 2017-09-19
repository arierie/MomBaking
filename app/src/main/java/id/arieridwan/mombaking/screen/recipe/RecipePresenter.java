package id.arieridwan.mombaking.screen.recipe;

import javax.inject.Inject;
import id.arieridwan.mombaking.data.ApiServices;

/**
 * Created by arieridwan on 27/08/2017.
 */

public class RecipePresenter implements RecipeContract.Presenter{

    private RecipeContract.View mView;
    private ApiServices apiServices;

    @Inject
    public RecipePresenter(RecipeContract.View mView, ApiServices apiServices) {
        this.mView = mView;
        this.apiServices = apiServices;
    }

    @Override
    public void getAllRecipe() {

    }

}
