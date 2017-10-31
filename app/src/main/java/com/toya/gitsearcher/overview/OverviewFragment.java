package com.toya.gitsearcher.overview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.toya.gitsearcher.R;
import com.toya.gitsearcher.models.OverviewModel;
import com.toya.gitsearcher.search.SearchActivity;
import com.toya.gitsearcher.utils.CircleTransformation;
import com.toya.gitsearcher.utils.DateUtils;

/**
 * Created by AXIOO on 10/30/2017.
 */

public class OverviewFragment extends Fragment{

    ImageView ivAvatar;
    TextView tvName;
    TextView tvLogin;
    TextView tvLocation;
    TextView tvBio;
    TextView tvFollowers;
    TextView tvFollowing;
    TextView tvCreated;
    TextView tvBlog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_overview, container, false);
        ivAvatar = rootView.findViewById(R.id.ivAvatar);
        tvName = rootView.findViewById(R.id.tvName);
        tvLocation = rootView.findViewById(R.id.tvLocation);
        tvLogin = rootView.findViewById(R.id.tvLogin);
        tvBio = rootView.findViewById(R.id.tvBio);
        tvBlog = rootView.findViewById(R.id.tvBlog);
        tvFollowers = rootView.findViewById(R.id.tvFollowers);
        tvFollowing = rootView.findViewById(R.id.tvFollowing);
        tvCreated = rootView.findViewById(R.id.tvCreated);
        return rootView;
    }

    public void invalidate(OverviewModel overviewModel){
        try {
            Picasso.with(getActivity()).load(overviewModel.getAvatarUrl()).transform(new CircleTransformation()).into(ivAvatar);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tvName.setText(overviewModel.getName());
        tvLogin.setText(overviewModel.getLogin());
        tvLocation.setText(overviewModel.getLocation());
        tvBio.setText(overviewModel.getBio());
        tvBlog.setText(overviewModel.getBlog());
        tvFollowers.setText("Followers (" + overviewModel.getFollowers() + ")");
        tvFollowing.setText("Following (" + overviewModel.getFollowing() + ")");
        tvCreated.setText(DateUtils.convertDate(overviewModel.getCreatedAt()));
    }
}
