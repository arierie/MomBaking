package id.arieridwan.mombaking.features.recipe;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import id.arieridwan.mombaking.MomBakingApp;
import id.arieridwan.mombaking.R;
import id.arieridwan.mombaking.adapter.RecipeAdapter;
import id.arieridwan.mombaking.model.Recipe;
import id.arieridwan.mombaking.utils.ViewUtils;

public class RecipeActivity extends AppCompatActivity
        implements RecipeContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.rv_recipe)
    RecyclerView mRvRecipe;

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;

    @BindView(R.id.error_view)
    FrameLayout mErrorView;

    private List<Recipe> mList = new ArrayList<>();
    private RecipeAdapter mAdapter;
    private GridLayoutManager mLayoutManager;

    @Inject
    RecipePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        ButterKnife.bind(this);
        setUpDagger();

        mAdapter = new RecipeAdapter(mList);
        mLayoutManager = new GridLayoutManager(this, ViewUtils.calculateNoOfColumns(this),GridLayoutManager.VERTICAL, false);

        mRvRecipe.setAdapter(mAdapter);
        mRvRecipe.setLayoutManager(mLayoutManager);

        mPresenter.getAllRecipe();
        mSwipeRefresh.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        mPresenter.getAllRecipe();
    }

    @Override
    public void startLoading() {
        mSwipeRefresh.setRefreshing(true);
    }

    @Override
    public void stopAndHide() {
        mSwipeRefresh.setRefreshing(false);
    }

    @Override
    public void stopAndError() {
        mSwipeRefresh.setRefreshing(false);
        mRvRecipe.setVisibility(View.GONE);
        mErrorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void getDataSuccess(List<Recipe> item) {
        mList.clear();
        mList.addAll(item);
        mAdapter.notifyDataSetChanged();
        mErrorView.setVisibility(View.GONE);
        mRvRecipe.setVisibility(View.VISIBLE);
    }

    private void setUpDagger() {
        DaggerRecipeComponent.builder()
                .appComponent(((MomBakingApp) getApplicationContext()).getAppComponent())
                .recipeModule(new RecipeModule(this))
                .build().inject(this);
    }

}
