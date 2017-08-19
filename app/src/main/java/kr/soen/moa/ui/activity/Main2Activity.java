package kr.soen.moa.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;

import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;

import butterknife.BindView;
import kr.soen.moa.R;
import kr.soen.moa.ui.activity.BaseDrawerActivity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        pager2 = (ViewPager)findViewById(R.id.pager2);
        pager2.setAdapter(new MyAdapter2(getSupportFragmentManager()));
        tabs2 = (PagerSlidingTabStrip)findViewById(R.id.tabs2);
        tabs2.setViewPager(pager2);


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
