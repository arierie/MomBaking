package id.arieridwan.mombaking.features.stepdetail;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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

    @BindView(R.id.tv_prev)
    TextView mTvPrev;

    @BindView(R.id.tv_next)
    TextView mTvNext;

    private Recipe mItem;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Step");

        if (getIntent().getExtras().containsKey(RECIPE_STEP) && getIntent().getExtras().containsKey(RECIPE_STEP_POS)) {
            mItem = Parcels.unwrap(getIntent().getParcelableExtra(RECIPE_STEP));
            position = getIntent().getIntExtra(RECIPE_STEP_POS, 0);
            setUpFragment();
        }

    }

    public void nextStep() {
        mTvPrev.setEnabled(true);
        position = position + 1;
        if (position > (mItem.getSteps().size()-1)) {
            position = position - 1;
            mTvNext.setEnabled(false);
        } else {
            mTvNext.setEnabled(true);
        }
        setUpFragment();
    }

    public void prevStep() {
        mTvNext.setEnabled(true);
        position = position - 1;
        if (position < 0) {
            position = 0;
            mTvPrev.setEnabled(false);
        } else {
            mTvPrev.setEnabled(true);
        }
        setUpFragment();
    }

    private void setUpFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        Bundle arguments = new Bundle();
        arguments.putParcelable(RECIPE_STEP_DETAIL, Parcels.wrap(mItem));
        arguments.putInt(RECIPE_STEP_POS, position);

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

    @OnClick(R.id.tv_next)
    public void onNextClick() {
        nextStep();
    }

    @OnClick(R.id.tv_prev)
    public void onPrevClick() {
        prevStep();
    }

}