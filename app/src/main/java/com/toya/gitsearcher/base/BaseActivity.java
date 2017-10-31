package com.toya.gitsearcher.base;

import android.support.v7.app.AppCompatActivity;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by AXIOO on 10/31/2017.
 */

public class BaseActivity extends AppCompatActivity {
    protected SweetAlertDialog mProgressDialog;

    public void startProgress() {
        mProgressDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                .setTitleText("Loading");
        mProgressDialog.show();
    }

    public void stopProgress() {
        mProgressDialog.dismiss();
    }

    public void errorProgress(String message) {
        mProgressDialog = new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("")
                .setContentText(message);
        mProgressDialog.show();

    }
}
