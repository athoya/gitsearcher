package com.toya.gitsearcher.overview;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.toya.gitsearcher.models.OverviewModel;
import com.toya.gitsearcher.models.RepositoryModel;

import java.util.List;

/**
 * Created by AXIOO on 10/30/2017.
 */

public class OverviewActivityPresenter {

    OverviewView mView;

    public OverviewActivityPresenter(OverviewView mView) {
        this.mView = mView;
    }

    public void callOverviewApi(final Context context, String query){
        RequestQueue queue = Volley.newRequestQueue(context);
        //https://api.github.com/users/athoya
        //https://api.github.com/users/athoya/repos
        String url ="https://api.github.com/users/"+query;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        OverviewModel o = new Gson().fromJson(response, OverviewModel.class);
                        mView.onOverviewSuccess(o);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //mTextView.setText("That didn't work!");
                Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(stringRequest);
    }

    public void callRepositoryApi(final Context context, String query){
        RequestQueue queue = Volley.newRequestQueue(context);
        String url ="https://api.github.com/users/"+query+"/repos";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //SearchResultModel s = new Gson().fromJson(response, SearchResultModel.class);
                        //mView.onSearchSuccess(s);
                        List<RepositoryModel> r = new Gson().fromJson(response, new TypeToken<List<RepositoryModel>>(){}.getType());
                        mView.onRepositorySuccess(r);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //mTextView.setText("That didn't work!");
                Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(stringRequest);
    }
}
