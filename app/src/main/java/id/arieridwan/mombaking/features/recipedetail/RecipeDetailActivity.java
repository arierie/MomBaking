package id.arieridwan.mombaking.features.recipedetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import org.parceler.Parcels;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.arieridwan.mombaking.R;
import id.arieridwan.mombaking.adapter.StepAdapter;
import id.arieridwan.mombaking.model.Recipe;
import id.arieridwan.mombaking.features.stepdetail.StepDetailActivity;
import id.arieridwan.mombaking.features.stepdetail.StepDetailFragment;

import static id.arieridwan.mombaking.utils.Constants.RECIPE;
import static id.arieridwan.mombaking.utils.Constants.RECIPE_STEP;
import static id.arieridwan.mombaking.utils.Constants.RECIPE_STEP_DETAIL;
import static id.arieridwan.mombaking.utils.Constants.RECIPE_STEP_POS;

public class RecipeDetailActivity extends AppCompatActivity implements StepAdapter.StepListener {

    @BindView(R.id.detail_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.rv_step)
    RecyclerView mRvStep;

    @BindView(R.id.tv_ingredient)
    TextView mTvIngredient;

    private Recipe mData;

    private boolean mTwoPane;

    private List<Recipe.StepsBean> mList = new ArrayList<>();
    private LinearLayoutManager mLinearLayoutManager;
    private StepAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (findViewById(R.id.main_detail_container) != null) {
            mTwoPane = true;
        }
        if(getIntent().getExtras().containsKey(RECIPE)) {
            mData = Parcels.unwrap(getIntent().getParcelableExtra(RECIPE));
        }
        mAdapter = new StepAdapter(mList, mTwoPane, this);
        mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRvStep.setAdapter(mAdapter);
        mRvStep.setLayoutManager(mLinearLayoutManager);

        setData();

    }

    private void setData() {
        int qty;
        String measure;
        String ingredient;
        if (mData != null) {
            getSupportActionBar().setTitle(mData.getName());
            getSupportActionBar().setSubtitle("Servings "+mData.getServings());
            mList.addAll(mData.getSteps());
            mAdapter.notifyDataSetChanged();
            for (int i = 0; i < mData.getIngredients().size(); i++) {
                qty = (int) mData.getIngredients().get(i).getQuantity();
                measure = mData.getIngredients().get(i).getMeasure().toLowerCase();
                ingredient = mData.getIngredients().get(i).getIngredient().toLowerCase();
                if (i == mData.getIngredients().size()) {
                    mTvIngredient.append("- " + qty + " " + measure + " of " + ingredient);
                } else {
                    mTvIngredient.append("- " + qty + " " + measure + " of " + ingredient + "\n\n");
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.recipe_detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        } else if(id == R.id.menu_add) {
            addToWidget();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addToWidget() {

    }

    @Override
    public void navigateToStepDetail(int stepPosition, boolean isTwoPane) {
        if (isTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putParcelable(RECIPE_STEP_DETAIL, Parcels.wrap(mList));
            StepDetailFragment fragment = new StepDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_detail_container, fragment)
                    .commit();
        } else {
            Intent intent = new Intent(this, StepDetailActivity.class);
            intent.putExtra(RECIPE_STEP, Parcels.wrap(mData));
            intent.putExtra(RECIPE_STEP_POS, stepPosition);
            startActivity(intent);
        }
    }

}