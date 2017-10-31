package com.toya.gitsearcher.search;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.toya.gitsearcher.models.SearchResultModel;

/**
 * Created by AXIOO on 10/30/2017.
 */

public class SearchActivityPresenter {

    SearchActivityView mView;

    //https://api.github.com/users/athoya
    //https://api.github.com/users/athoya/repos

    public SearchActivityPresenter(SearchActivityView mView) {
        this.mView = mView;
    }

    public void searchUser(final Context context, String query){
        RequestQueue queue = Volley.newRequestQueue(context);
        String url ="https://api.github.com/search/users?q="+query;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        SearchResultModel s = new Gson().fromJson(response, SearchResultModel.class);
                        mView.onSearchSuccess(s);
                        mView.stopLoading();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //mTextView.setText("That didn't work!");
                Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
                mView.stopLoading();
                mView.errorLoading("Unreachable");
            }
        });

        mView.showLoading();
        queue.add(stringRequest);
    }
}
