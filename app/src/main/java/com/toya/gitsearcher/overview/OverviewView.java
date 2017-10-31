package com.toya.gitsearcher.overview;

import com.toya.gitsearcher.models.OverviewModel;
import com.toya.gitsearcher.models.RepositoryModel;

import java.util.List;

/**
 * Created by AXIOO on 10/30/2017.
 */

public interface OverviewView {
    void onRepositorySuccess(List<RepositoryModel> repositoryModelList);
    void onOverviewSuccess(OverviewModel overviewModel);
}
