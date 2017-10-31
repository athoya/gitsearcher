package com.toya.gitsearcher.overview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.toya.gitsearcher.R;
import com.toya.gitsearcher.models.RepositoryModel;
import com.toya.gitsearcher.utils.DateUtils;
import com.toya.gitsearcher.utils.StringUtils;

import java.util.ArrayList;

/**
 * Created by AXIOO on 10/30/2017.
 */

public class RepositoryFragment extends Fragment{

    ArrayList<RepositoryModel> repoList = new ArrayList<>();
    RepositoryAdapter repositoryAdapter;

    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_repositories, container, false);

        RecyclerView repoRecyclerView = (RecyclerView) rootView.findViewById(R.id.repo_recycler_view);
        repoRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        repositoryAdapter = new RepositoryAdapter(repoList);
        repoRecyclerView.setAdapter(repositoryAdapter);

        return rootView;
    }

    public void invalidate(ArrayList<RepositoryModel> r){
        repoList.clear();
        repoList.addAll(r);
        repositoryAdapter.notifyDataSetChanged();
    }

    public void setRepoList(ArrayList<RepositoryModel> r){
        repoList.clear();
        repoList.addAll(r);
    }

    private class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.ViewHolder>{

        ArrayList<RepositoryModel> items;

        public RepositoryAdapter(ArrayList<RepositoryModel> items) {
            this.items = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_repo, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.tvName.setText(items.get(position).getName());
            holder.tvPush.setText(DateUtils.convertDate(items.get(position).getDate()));
            holder.tvStar.setText(""+ StringUtils.kConverter(items.get(position).getStar()));
            holder.tvForks.setText(""+StringUtils.kConverter(items.get(position).getForks()));

            String unit = " KB";
            int value = items.get(position).getSize();
            if (value > 1000){
                value = value / 1000;
                unit = " MB";
            }
            holder.tvSize.setText(""+ value + unit);
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

            TextView tvName;
            TextView tvStar;
            TextView tvForks;
            TextView tvPush;
            TextView tvSize;

            public ViewHolder(View itemView) {
                super(itemView);
                tvName = (TextView) itemView.findViewById(R.id.tvName);
                tvStar = (TextView) itemView.findViewById(R.id.tvStar);
                tvForks = (TextView) itemView.findViewById(R.id.tvFork);
                tvPush = (TextView) itemView.findViewById(R.id.tvPush);
                tvSize = (TextView) itemView.findViewById(R.id.tvSize);
            }
        }


    }
}
