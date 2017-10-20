package id.arieridwan.mombaking.features.recipe;

import java.util.List;
import javax.inject.Inject;
import id.arieridwan.mombaking.data.api.ApiServices;
import id.arieridwan.mombaking.data.api.response.RecipeResponse;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
        mView.startLoading();
        apiServices.getAllRecipe()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<RecipeResponse>>() {
                    @Override
                    public void onCompleted() {
                        mView.stopAndHide();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.stopAndError();
                    }

                    @Override
                    public void onNext(List<RecipeResponse> recipeResponses) {
                        mView.getDataSuccess(recipeResponses);
                    }
                });
    }

}
