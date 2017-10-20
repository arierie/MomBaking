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
import id.arieridwan.mombaking.data.api.response.RecipeResponse;

/**
 * Created by arie on 9/20/17.
 */

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepViewHolder> {

    private RecipeResponse.StepsBean mData;
    private List<RecipeResponse.StepsBean> mList = new ArrayList<>();

    private boolean isTwoPane;
    private StepListener mListener;

    public StepAdapter(List<RecipeResponse.StepsBean> mList, boolean isTwoPane, StepListener mListener) {
        this.mList = mList;
        this.isTwoPane = isTwoPane;
        this.mListener = mListener;
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

    class StepViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tv_title)
        TextView mTvTitle;

        public StepViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mListener != null)
                mListener.navigateToStepDetail(getAdapterPosition(), isTwoPane);
        }
    }

    public interface StepListener {
        void navigateToStepDetail(int stepPosition, boolean isTwoPane);
    }

}