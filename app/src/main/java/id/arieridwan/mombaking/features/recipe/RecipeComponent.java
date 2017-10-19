package id.arieridwan.mombaking.features.recipe;

import dagger.Component;
import id.arieridwan.mombaking.PerActivity;
import id.arieridwan.mombaking.di.AppComponent;

/**
 * Created by arieridwan on 27/08/2017.
 */

@PerActivity
@Component(dependencies = AppComponent.class, modules = RecipeModule.class)
public interface RecipeComponent {
    void inject(RecipeActivity recipeActivity);
}
