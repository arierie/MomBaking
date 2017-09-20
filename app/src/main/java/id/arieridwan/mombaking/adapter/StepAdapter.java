package id.arieridwan.mombaking.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.arieridwan.mombaking.R;
import id.arieridwan.mombaking.model.Recipe;

/**
 * Created by arie on 9/20/17.
 */

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepViewHolder> {


    private Recipe.StepsBean mData;
    private List<Recipe.StepsBean> mList = new ArrayList<>();

    public StepAdapter(List<Recipe.StepsBean> mList) {
        this.mList = mList;
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
        Context context = holder.itemView.getContext();
        if(!TextUtils.isEmpty(mData.getVideoURL())) {
            holder.mIvPlay.setVisibility(View.VISIBLE);
            try {
                Picasso.with(context)
                        .load(mData.getThumbnailURL())
                        .placeholder(R.drawable.image_placeholder)
                        .error(R.drawable.image_placeholder)
                        .into(holder.mIvBackground);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            holder.mIvPlay.setVisibility(View.GONE);
        }
        holder.mTvTitle.setText(mData.getShortDescription());
        holder.mTvDesc.setText(mData.getDescription());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class StepViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_background)
        ImageView mIvBackground;

        @BindView(R.id.iv_play)
        ImageView mIvPlay;

        @BindView(R.id.tv_title)
        TextView mTvTitle;

        @BindView(R.id.tv_desc)
        TextView mTvDesc;

        public StepViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
