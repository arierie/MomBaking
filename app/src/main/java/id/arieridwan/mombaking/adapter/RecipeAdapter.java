package id.arieridwan.mombaking.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.arieridwan.mombaking.R;
import id.arieridwan.mombaking.adapter.holder.RecipeViewHolder;

/**
 * Created by arieridwan on 27/08/2017.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeViewHolder> {

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_item, parent, false);
        return new RecipeViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
