package id.arieridwan.mombaking.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.arieridwan.mombaking.R;
import id.arieridwan.mombaking.data.api.response.RecipeResponse;

/**
 * Created by arie on 10/20/17.
 */

public class WidgetRecipeAdapter extends RecyclerView.Adapter<WidgetRecipeAdapter.ViewHolder> {

    private List<RecipeResponse> mData;
    private RecipeOnClickListener mClickListener;

    public WidgetRecipeAdapter(List<RecipeResponse> mRecipeResponses, RecipeOnClickListener mClickListener) {
        this.mData = mRecipeResponses;
        this.mClickListener = mClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.widget_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(mData.get(holder.getAdapterPosition()).getName());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public interface RecipeOnClickListener {
        void onRecipeSelected(RecipeResponse recipeResponse);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tv_title)
        TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            title.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mClickListener.onRecipeSelected(mData.get(getAdapterPosition()));
        }
    }

}