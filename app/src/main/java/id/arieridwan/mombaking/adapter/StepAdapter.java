package id.arieridwan.mombaking.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import org.parceler.Parcels;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import id.arieridwan.mombaking.R;
import id.arieridwan.mombaking.model.Recipe;
import id.arieridwan.mombaking.screen.recipedetail.RecipeDetailActivity;
import id.arieridwan.mombaking.screen.stepdetail.StepDetailActivity;
import id.arieridwan.mombaking.screen.stepdetail.StepDetailFragment;
import static id.arieridwan.mombaking.utils.Constants.RECIPE_STEP;
import static id.arieridwan.mombaking.utils.Constants.RECIPE_STEP_DETAIL;
import static id.arieridwan.mombaking.utils.Constants.RECIPE_STEP_POS;

/**
 * Created by arie on 9/20/17.
 */

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepViewHolder> {

    private Recipe.StepsBean mData;
    private List<Recipe.StepsBean> mList = new ArrayList<>();
    private boolean isTwoPane;

    public StepAdapter(List<Recipe.StepsBean> mList,boolean isTwoPane) {
        this.mList = mList;
        this.isTwoPane = isTwoPane;
    }

    @Override
    public StepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.step_item, parent, false);
        return new StepViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(StepViewHolder holder, int position) {
        mData = mList.get(position);
        holder.mTvTitle.setText(mData.getShortDescription());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class StepViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title)
        TextView mTvTitle;

        public StepViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                if (isTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putParcelable(RECIPE_STEP_DETAIL, Parcels.wrap(mList.get(getAdapterPosition())));
                    StepDetailFragment fragment = new StepDetailFragment();
                    fragment.setArguments(arguments);
                    ((RecipeDetailActivity) itemView.getContext())
                            .getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_detail_container, fragment)
                            .commit();
                } else {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, StepDetailActivity.class);
                    intent.putExtra(RECIPE_STEP, Parcels.wrap(mList.get(getAdapterPosition())));
                    intent.putExtra(RECIPE_STEP_POS, getAdapterPosition());
                    context.startActivity(intent);
                }
            });
        }
    }
}