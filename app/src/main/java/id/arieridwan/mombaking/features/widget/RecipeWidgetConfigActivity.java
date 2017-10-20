package id.arieridwan.mombaking.features.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RemoteViews;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import id.arieridwan.mombaking.R;
import id.arieridwan.mombaking.adapter.WidgetRecipeAdapter;
import id.arieridwan.mombaking.features.recipe.RecipeActivity;
import id.arieridwan.mombaking.data.api.response.RecipeResponse;
import static id.arieridwan.mombaking.utils.Constants.RECIPE_WIDGET;

/**
 * Created by arie on 10/20/17.
 */

public class RecipeWidgetConfigActivity extends AppCompatActivity
        implements WidgetRecipeAdapter.RecipeOnClickListener {

    @BindView(R.id.rv_recipe)
    RecyclerView mBakingRecipeList;

    private WidgetRecipeAdapter mAdapter;
    private int mAppWidgetId;

    private List<RecipeResponse> mList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_widget_config);

        initView();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mBakingRecipeList.setLayoutManager(layoutManager);
        mBakingRecipeList.setHasFixedSize(true);

        mAdapter = new WidgetRecipeAdapter(mList, this);
        mBakingRecipeList.setAdapter(mAdapter);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mAppWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }
    }

    private void initView() {
        ButterKnife.bind(this);
    }

    @Override
    public void onRecipeSelected(RecipeResponse recipeResponse) {
        putRecipeToWidget(recipeResponse);
    }

    private void putRecipeToWidget(RecipeResponse recipeResponse) {
        Intent intent = new Intent(this, RecipeActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(RECIPE_WIDGET, "");
        intent.putExtras(bundle);

        PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(), 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getBaseContext());

        RemoteViews views = new RemoteViews(getBaseContext().getPackageName(), R.layout.recipe_widget);

        views.setTextViewText(R.id.tv_title, recipeResponse.getName() + " Ingredient :");

        String strIngredient = "";
        for (RecipeResponse.IngredientsBean ingredient : recipeResponse.getIngredients()) {
            DecimalFormat format = new DecimalFormat("#.##");

            strIngredient += "- " + format.format(ingredient.getQuantity())
                    + " " + ingredient.getMeasure() + " of " + ingredient.getIngredient() + ".";
            strIngredient += "\n";
        }

        views.setTextViewText(R.id.tv_ingredient, strIngredient);

        views.setOnClickPendingIntent(R.id.tv_ingredient, pendingIntent);

        appWidgetManager.updateAppWidget(mAppWidgetId, views);

        Intent resultValue = new Intent();
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
        setResult(RESULT_OK, resultValue);
        finish();
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }

}