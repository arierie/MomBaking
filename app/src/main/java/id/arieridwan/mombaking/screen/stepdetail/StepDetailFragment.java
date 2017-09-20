package id.arieridwan.mombaking.screen.stepdetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.parceler.Parcels;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import id.arieridwan.mombaking.R;
import id.arieridwan.mombaking.adapter.IngredientAdapter;
import id.arieridwan.mombaking.adapter.StepAdapter;
import id.arieridwan.mombaking.model.Recipe;
import static id.arieridwan.mombaking.utils.Constants.ARG_ITEM_ID;

public class StepDetailFragment extends Fragment {

    @BindView(R.id.rv_ingredient)
    RecyclerView mRvIngredient;

    @BindView(R.id.rv_step)
    RecyclerView mRvStep;

    Unbinder unbinder;

    private Recipe mItem;
    private List<Recipe.IngredientsBean> mList = new ArrayList<>();
    private LinearLayoutManager mLinearLayoutManager;
    private IngredientAdapter mAdapter;

    private List<Recipe.StepsBean> mList2 = new ArrayList<>();
    private LinearLayoutManager mLinearLayoutManager2;
    private StepAdapter mAdapter2;

    public StepDetailFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            mItem = Parcels.unwrap(getArguments().getParcelable(ARG_ITEM_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_step_detail, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAdapter = new IngredientAdapter(mList);
        mLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRvIngredient.setAdapter(mAdapter);
        mRvIngredient.setLayoutManager(mLinearLayoutManager);
        mAdapter2 = new StepAdapter(mList2);
        mLinearLayoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRvStep.setAdapter(mAdapter2);
        mRvStep.setLayoutManager(mLinearLayoutManager2);
        setData();
    }

    private void setData() {
        if (mItem != null) {
            mList.addAll(mItem.getIngredients());
            mAdapter.notifyDataSetChanged();
            mList2.addAll(mItem.getSteps());
            mAdapter2.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}