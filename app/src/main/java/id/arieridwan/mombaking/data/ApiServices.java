package id.arieridwan.mombaking.data;

import id.arieridwan.mombaking.model.Recipe;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by arieridwan on 27/08/2017.
 */

public interface ApiServices {
    @GET("topher/2017/May/59121517_baking/baking.json")
    Observable<Recipe> getAllRecipe();
}
