package com.toya.gitsearcher.overview;

import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.toya.gitsearcher.R;
import com.toya.gitsearcher.models.OverviewModel;
import com.toya.gitsearcher.models.RepositoryModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AXIOO on 10/30/2017.
 */

public class OverviewActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener, ViewPager.OnPageChangeListener, OverviewView {

    TabLayout tabLayout;
    ViewPager viewPager;

    OverviewFragment overviewFragment;
    RepositoryFragment repositoryFragment;

    OverviewActivityPresenter mPresenter;
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);

        user = getIntent().getStringExtra("user");

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        tabLayout.addTab(tabLayout.newTab().setText("Overview"));
        tabLayout.addTab(tabLayout.newTab().setText("Repository"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) findViewById(R.id.pager);

        overviewFragment = new OverviewFragment();
        repositoryFragment = new RepositoryFragment();
        mPresenter = new OverviewActivityPresenter(this);

        Pager adapter = new Pager(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        //viewPager.addOnPageChangeListener(this);

        tabLayout.addOnTabSelectedListener(this);

        try {
            ActionBar ab = getSupportActionBar();
            ab.setTitle(user);
            ab.setHomeButtonEnabled(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCurrentItem (int item, boolean smoothScroll) {
        viewPager.setCurrentItem(item, smoothScroll);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.callOverviewApi(this, user);
        mPresenter.callRepositoryApi(this, user);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        setCurrentItem(tab.getPosition(), true);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                //mPresenter.callOverviewApi(this, user);
                break;
            case 1:
                //mPresenter.callRepositoryApi(this, user);
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onOverviewSuccess(OverviewModel overviewModel) {
        overviewFragment.invalidate(overviewModel);
    }

    @Override
    public void onRepositorySuccess(List<RepositoryModel> repositoryModelList) {
        repositoryFragment.invalidate((ArrayList<RepositoryModel>) repositoryModelList);
    }


    public class Pager extends FragmentStatePagerAdapter {
        int tabCount;

        public Pager(FragmentManager fm, int tabCount) {
            super(fm);
            this.tabCount= tabCount;
        }

        @Override
        public Fragment getItem(int position) {
            //Returning the current tabs
            switch (position) {
                case 0:
                    return overviewFragment;
                case 1:
                    return repositoryFragment;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return tabCount;
        }
    }
}