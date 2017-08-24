package kr.soen.moa.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;

import butterknife.BindView;
import kr.soen.moa.R;
import kr.soen.moa.ui.activity.BaseDrawerActivity;
import kr.soen.moa.ui.adapter.TitleListViewAdapter;
import kr.soen.moa.ui.fragment.Title1Fragment;
import kr.soen.moa.ui.fragment.Title2Fragment;
import kr.soen.moa.ui.fragment.TitleTab1Fragment;

public class Main2Activity extends BaseDrawerActivity {

    @BindView(R.id.btnCreate)
    FloatingActionButton fabCreate;
    private static final String LOG_TAG = Main2Activity.class.getSimpleName();
    //메인에 들어가는 탭뷰

    private PagerSlidingTabStrip tabs2;
    private ViewPager pager2;
    private BottomBar bottomBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        pager2 = (ViewPager)findViewById(R.id.pager2);
        pager2.setAdapter(new MyAdapter2(getSupportFragmentManager()));
        tabs2 = (PagerSlidingTabStrip)findViewById(R.id.tabs2);
        tabs2.setViewPager(pager2);
        bottomBar = (BottomBar)findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.item1) {//공유

                }else if(tabId == R.id.item2){//보관함
                    Toast.makeText(getApplicationContext(),"해당 질문이 보관함에 저장됩니다.",Toast.LENGTH_SHORT).show();

                }else{//play

                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }






    public class MyAdapter2 extends FragmentPagerAdapter {

        private String[] titles = {getString(R.string.tab2_title1),
                getString(R.string.tab2_title2),getString(R.string.tab2_title3)};

        public MyAdapter2(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position){
                case 0:{

                    return TitleTab1Fragment.newInstance(position);
                }
                case 1:{
                    return TitleTab1Fragment.newInstance(position);
                }
                case 2:{
                    return TitleTab1Fragment.newInstance(position);
                }
            }

            return null;
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
    public Main2Activity() {
    }

}
