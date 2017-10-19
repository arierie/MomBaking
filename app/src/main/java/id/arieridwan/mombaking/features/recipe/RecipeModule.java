package id.arieridwan.mombaking.features.recipe;

import dagger.Module;
import dagger.Provides;
import id.arieridwan.mombaking.PerActivity;

/**
 * Created by arieridwan on 27/08/2017.
 */

@Module
public class RecipeModule {

    private final RecipeContract.View mView;

    public RecipeModule(RecipeContract.View mView) {
        this.mView = mView;
    }

    @Provides
    @PerActivity
    RecipeContract.View provideView(){
        return mView;
    }

}
