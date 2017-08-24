package kr.soen.moa.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import android.view.Menu;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

import com.astuetz.PagerSlidingTabStrip;

import butterknife.BindView;
import kr.soen.moa.R;
import kr.soen.moa.ui.fragment.MyPage1Fragment;
import kr.soen.moa.ui.fragment.MyPage2Fragment;


public class MyPageActivity extends BaseDrawerActivity {


    @BindView(R.id.pager6)
    ViewPager pager;
    @BindView(R.id.tab)
    PagerSlidingTabStrip tabs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

       // setupTabs();
      //  setupUserProfileGrid();
        pager.setAdapter(new MyPageAdapter(getSupportFragmentManager()));
        tabs.setViewPager(pager);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }


    public class MyPageAdapter extends FragmentPagerAdapter {

        private String[] titles = {"하고싶은 대화",
                "했던 대화"};

        public MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position){
                case 0:{
                    return MyPage1Fragment.newInstance(position);
                }
                case 1:{
                    return MyPage2Fragment.newInstance(position);
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
    public MyPageActivity() {
    }
}
