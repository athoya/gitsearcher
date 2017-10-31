package com.toya.gitsearcher.search;

import com.toya.gitsearcher.base.BaseView;
import com.toya.gitsearcher.models.SearchResultModel;

/**
 * Created by AXIOO on 10/30/2017.
 */

public interface SearchActivityView extends BaseView{

    void onSearchSuccess(SearchResultModel result);
}
