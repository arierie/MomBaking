package id.arieridwan.mombaking.data.api;

import java.util.List;

import id.arieridwan.mombaking.data.api.response.RecipeResponse;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by arieridwan on 27/08/2017.
 */

public interface ApiServices {
    @GET("topher/2017/May/59121517_baking/baking.json")
    Observable<List<RecipeResponse>> getAllRecipe();
}