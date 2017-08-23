package kr.soen.moa.ui.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

import com.astuetz.PagerSlidingTabStrip;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import kr.soen.moa.R;
import kr.soen.moa.ui.fragment.MyPage1Fragment;
import kr.soen.moa.ui.fragment.MyPage2Fragment;

import kr.soen.moa.ui.utils.CircleTransformation;

public class MyPageActivity extends BaseDrawerActivity {

    public static final String ARG_REVEAL_START_LOCATION = "reveal_start_location";

    private static final int USER_OPTIONS_ANIMATION_DELAY = 300;
    private static final Interpolator INTERPOLATOR = new DecelerateInterpolator();


    private int avatarSize;
    private String profilePhoto;
    //private UserProfileAdapter userPhotosAdapter;

    @BindView(R.id.content)
    CoordinatorLayout vRevealBackground;
    @BindView(R.id.rvUserProfile)
    RecyclerView rvUserProfile;
    @BindView(R.id.tlUserProfileTabs)
    PagerSlidingTabStrip tlUserProfileTabs;

    @BindView(R.id.vUserProfileRoot)
    View vUserProfileRoot;

    private ViewPager pager;
    private TabLayout tab;


    public static void startUserProfileFromLocation(int[] startingLocation, Activity startingActivity) {
        Intent intent = new Intent(startingActivity, MyPageActivity.class);
        intent.putExtra(ARG_REVEAL_START_LOCATION, startingLocation);
        startingActivity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);
        this.avatarSize = getResources().getDimensionPixelSize(R.dimen.user_profile_avatar_size);
        this.profilePhoto = getString(R.string.user_profile_photo);

        Picasso.with(this)
                .load(profilePhoto)
                .placeholder(R.drawable.img_circle_placeholder)
                .resize(avatarSize, avatarSize)
                .centerCrop()
                .transform(new CircleTransformation());
               // .into(ivUserProfilePhoto);

       // setupTabs();
        setupUserProfileGrid();




        pager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(new MyPageActivity.MyPageAdapter(getSupportFragmentManager()));

//        tab = (PagerSlidingTabStrip)findViewById(R.id.tlUserProfileTabs);
//        tab.setViewPager(pager);

    }
//    private void setupTabs() {
//        tlUserProfileTabs.addTab(tlUserProfileTabs.newTab().setIcon(R.drawable.ic_grid_on_white));
//        tlUserProfileTabs.addTab(tlUserProfileTabs.newTab().setIcon(R.drawable.ic_list_white));
//    }
    private void setupUserProfileGrid() {
//        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
//        rvUserProfile.setLayoutManager(layoutManager);
//        rvUserProfile.setOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                userPhotosAdapter.setLockedAnimations(true);
//            }
//        });
    }

//    private void setupRevealBackground(Bundle savedInstanceState) {
//        vRevealBackground.setOnStateChangeListener(this);
//        if (savedInstanceState == null) {
//            final int[] startingLocation = getIntent().getIntArrayExtra(ARG_REVEAL_START_LOCATION);
//            vRevealBackground.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//                @Override
//                public boolean onPreDraw() {
//                    vRevealBackground.getViewTreeObserver().removeOnPreDrawListener(this);
//                    vRevealBackground.startFromLocation(startingLocation);
//                    return true;
//                }
//            });
//        } else {
//            vRevealBackground.setToFinishedFrame();
//            //userPhotosAdapter.setLockedAnimations(true);
//        }
//    }

//    private void animateUserProfileOptions() {
//        tlUserProfileTabs.setTranslationY(-tlUserProfileTabs.getHeight());
//        tlUserProfileTabs.animate().translationY(0).setDuration(300).setStartDelay(USER_OPTIONS_ANIMATION_DELAY).setInterpolator(INTERPOLATOR);
//    }
//
//    private void animateUserProfileHeader() {
//        vUserProfileRoot.setTranslationY(-vUserProfileRoot.getHeight());
//
//        vUserProfileRoot.animate().translationY(0).setDuration(300).setInterpolator(INTERPOLATOR);
//        }

//    @Override
//    public void onStateChange(int state) {
//        if (RevealBackgroundView.STATE_FINISHED == state) {
//            rvUserProfile.setVisibility(View.VISIBLE);
//            tlUserProfileTabs.setVisibility(View.VISIBLE);
//            vUserProfileRoot.setVisibility(View.VISIBLE);
////            userPhotosAdapter = new UserProfileAdapter(this);
////            rvUserProfile.setAdapter(userPhotosAdapter);
//            animateUserProfileOptions();
//            animateUserProfileHeader();
//        } else {
//            tlUserProfileTabs.setVisibility(View.INVISIBLE);
//            rvUserProfile.setVisibility(View.INVISIBLE);
//            vUserProfileRoot.setVisibility(View.INVISIBLE);
//        }
 //   }

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
