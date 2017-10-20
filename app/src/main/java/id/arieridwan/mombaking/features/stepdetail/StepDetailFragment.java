package id.arieridwan.mombaking.features.stepdetail;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;
import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import id.arieridwan.mombaking.R;
import id.arieridwan.mombaking.data.api.response.RecipeResponse;
import static id.arieridwan.mombaking.utils.Constants.RECIPE_STEP_DETAIL;
import static id.arieridwan.mombaking.utils.Constants.RECIPE_STEP_POS;

public class StepDetailFragment extends Fragment {

    @BindView(R.id.video_view)
    SimpleExoPlayerView mVideoView;

    @BindView(R.id.tv_title)
    TextView mTvTitle;

    @BindView(R.id.tv_desc)
    TextView mTvDesc;

    @BindView(R.id.iv_background)
    ImageView mIvBackground;

    Unbinder unbinder;

    private SimpleExoPlayer player;
    private boolean playWhenReady;
    private int currentWindow;

    private long playbackPosition;

    private RecipeResponse mItem;
    private List<RecipeResponse.StepsBean> mList = new ArrayList<>();
    private RecipeResponse.StepsBean mData;
    private int position;

    public StepDetailFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(RECIPE_STEP_DETAIL)) {
            mItem = Parcels.unwrap(getArguments().getParcelable(RECIPE_STEP_DETAIL));
            position = getArguments().getInt(RECIPE_STEP_POS, 0);
            mList.addAll(mItem.getSteps());
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
        setData();
        initializePlayer();
    }

    private void setData() {
        mData = mList.get(position);
        mTvTitle.setText(mData.getShortDescription());
        mTvDesc.setText(mData.getDescription());
        if (!TextUtils.isEmpty(mData.getThumbnailURL())) {
            mIvBackground.setVisibility(View.VISIBLE);
            Picasso.with(getActivity())
                    .load(mData.getThumbnailURL())
                    .placeholder(R.drawable.image_placeholder)
                    .error(R.drawable.image_placeholder)
                    .into(mIvBackground);
        } else {
            mIvBackground.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(mData.getVideoURL())) {
            mVideoView.setVisibility(View.VISIBLE);
        } else {
            mVideoView.setVisibility(View.GONE);
        }
    }

    private void initializePlayer() {
        player = ExoPlayerFactory.newSimpleInstance(
                new DefaultRenderersFactory(getActivity()),
                new DefaultTrackSelector(), new DefaultLoadControl());

        mVideoView.setPlayer(player);

        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPosition);

        Uri uri = Uri.parse(mData.getVideoURL());
        MediaSource mediaSource = buildMediaSource(uri);
        player.prepare(mediaSource, true, false);
    }

    private MediaSource buildMediaSource(Uri uri) {
        return new ExtractorMediaSource(uri,
                new DefaultHttpDataSourceFactory("ua"),
                new DefaultExtractorsFactory(), null, null);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            initializePlayer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if ((Util.SDK_INT <= 23 || player == null)) {
            initializePlayer();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }

    private void releasePlayer() {
        if (player != null) {
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            playWhenReady = player.getPlayWhenReady();
            player.release();
            player = null;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}