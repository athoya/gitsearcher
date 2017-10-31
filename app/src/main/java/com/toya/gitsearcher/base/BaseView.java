package com.toya.gitsearcher.base;

/**
 * Created by AXIOO on 10/31/2017.
 */

public interface BaseView {
    void showLoading();
    void stopLoading();
    void errorLoading(String message);
}

