package id.arieridwan.mombaking.screen.recipe;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import id.arieridwan.mombaking.R;
import id.arieridwan.mombaking.model.Recipe;

public class RecipeActivity extends AppCompatActivity
        implements RecipeContract.View, SwipeRefreshLayout.OnRefreshListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void startLoading() {

    }

    @Override
    public void stopAndHide() {

    }

    @Override
    public void stopAndError() {

    }

    @Override
    public void getDataSuccess(Recipe item, String filter) {

    }
}
