package com.toya.gitsearcher.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.toya.gitsearcher.R;
import com.toya.gitsearcher.base.BaseActivity;
import com.toya.gitsearcher.models.SearchItem;
import com.toya.gitsearcher.models.SearchResultModel;
import com.toya.gitsearcher.overview.OverviewActivity;
import com.toya.gitsearcher.utils.CircleTransformation;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by AXIOO on 10/30/2017.
 */

public class SearchActivity extends BaseActivity implements SearchActivityView{

    SearchActivityPresenter mPresenter;
    ArrayList<SearchItem> searchItems = new ArrayList<>();
    UserAdapter userAdapter;

    EditText etSearch;
    Button btSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mPresenter =  new SearchActivityPresenter(this);
        //mPresenter.searchUser(this, "octocat");

        RecyclerView userRecyclerView = (RecyclerView)findViewById(R.id.user_recycler_view);
        userRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        userAdapter = new UserAdapter(searchItems);
        userRecyclerView.setAdapter(userAdapter);

        etSearch = (EditText) findViewById(R.id.etSearchUser);
        etSearch.setText("octocat");

        btSearch = (Button) findViewById(R.id.btSearch);
        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.searchUser(SearchActivity.this, etSearch.getText().toString());
            }
        });
    }

    @Override
    public void onSearchSuccess(SearchResultModel result) {
        searchItems.clear();
        searchItems.addAll(result.getItems());
        userAdapter.notifyDataSetChanged();
    }

    public void goToOverview(SearchItem item){
        Intent intent = new Intent(this, OverviewActivity.class);
        intent.putExtra("user", item.getLogin());
        startActivity(intent);
    }

    @Override
    public void showLoading() {
        startProgress();
    }

    @Override
    public void stopLoading() {
        stopProgress();
    }

    @Override
    public void errorLoading(String message) {
        errorProgress(message);
    }

    private class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{

        ArrayList<SearchItem> items;

        public UserAdapter(ArrayList<SearchItem> items) {
            this.items = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_user, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            holder.tvName.setText(items.get(position).getLogin());
            holder.llUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goToOverview(items.get(position));
                }
            });

            try {
                Picasso.with(SearchActivity.this).load(items.get(position).getAvatarUrl()).transform(new CircleTransformation()).into(holder.ivUser);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            ImageView ivUser;
            TextView tvName;
            LinearLayout llUser;

            public ViewHolder(View itemView) {
                super(itemView);
                ivUser = (ImageView) itemView.findViewById(R.id.list_item_user_iv);
                tvName = (TextView) itemView.findViewById(R.id.list_item_user_name_tv);
                llUser = itemView.findViewById(R.id.list_item_user_ll_holder);
            }
        }

    }
}
