package id.arieridwan.mombaking.screen.stepdetail;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import org.parceler.Parcels;
import butterknife.BindView;
import butterknife.ButterKnife;
import id.arieridwan.mombaking.R;
import id.arieridwan.mombaking.model.Recipe;
import static id.arieridwan.mombaking.utils.Constants.RECIPE_STEP;
import static id.arieridwan.mombaking.utils.Constants.RECIPE_STEP_DETAIL;
import static id.arieridwan.mombaking.utils.Constants.RECIPE_STEP_POS;
import static id.arieridwan.mombaking.utils.Constants.STEP_FRAGMENT_TAG;

public class StepDetailActivity extends AppCompatActivity {

    @BindView(R.id.detail_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.container)
    NestedScrollView container;

    private Recipe.StepsBean mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Step");

        if (getIntent().getExtras().containsKey(RECIPE_STEP) && getIntent().getExtras().containsKey(RECIPE_STEP_POS)) {
            mData = Parcels.unwrap(getIntent().getParcelableExtra(RECIPE_STEP));
            setUpFragment();
        }

    }

    private void setUpFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        Bundle arguments = new Bundle();
        arguments.putParcelable(RECIPE_STEP_DETAIL, Parcels.wrap(mData));

        StepDetailFragment fragment = (StepDetailFragment) getSupportFragmentManager().findFragmentByTag(STEP_FRAGMENT_TAG);

        if (fragment != null) {
            StepDetailFragment frg = new StepDetailFragment();
            frg.setArguments(arguments);
            ft.replace(R.id.container, frg, STEP_FRAGMENT_TAG)
                    .commit();
        } else {
            StepDetailFragment frg = new StepDetailFragment();
            frg.setArguments(arguments);
            ft.add(R.id.container, frg, STEP_FRAGMENT_TAG)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}