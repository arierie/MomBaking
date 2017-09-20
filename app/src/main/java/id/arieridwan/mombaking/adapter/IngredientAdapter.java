package id.arieridwan.mombaking.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import id.arieridwan.mombaking.R;
import id.arieridwan.mombaking.model.Recipe;

/**
 * Created by arie on 9/20/17.
 */

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {

    private Recipe.IngredientsBean mData;
    private List<Recipe.IngredientsBean> mList = new ArrayList<>();

    public IngredientAdapter(List<Recipe.IngredientsBean> mList) {
        this.mList = mList;
    }

    @Override
    public IngredientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ingredient_item, parent, false);
        return new IngredientViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(IngredientViewHolder holder, int position) {
        mData = mList.get(position);
        holder.mTvIngredient.setText(mData.getIngredient().toUpperCase());
        holder.mTvMeasure.setText(mData.getQuantity() + " " + mData.getMeasure().toUpperCase().toUpperCase());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class IngredientViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_ingredient)
        TextView mTvIngredient;

        @BindView(R.id.tv_measure)
        TextView mTvMeasure;

        public IngredientViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}