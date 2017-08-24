package kr.soen.moa.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindDimen;
import butterknife.BindString;
import butterknife.BindView;
import kr.soen.moa.R;
import kr.soen.moa.ui.activity.BaseActivity;
import kr.soen.moa.ui.utils.CircleTransformation;

/**
 * Created by manggi on 2017. 8. 16..
 */

public class BaseDrawerActivity extends BaseActivity {

    //drawer 레이아웃
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    //drawer에 나오는 navigation view
    @BindView(R.id.vNavigation)
    NavigationView vNavigation;

    @BindDimen(R.dimen.global_menu_avatar_size)
    int avatarSize;
    @BindString(R.string.user_profile_photo)
    String profilePhoto;

    private TextView ivMenuUserProfilePhoto;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentViewWithoutInject(R.layout.activity_drawer);
        ViewGroup viewGroup = (ViewGroup)findViewById(R.id.flContentRoot);
        LayoutInflater.from(this).inflate(layoutResID,viewGroup,true);
        bindViews();
        setupHeader();

    }

    //삼지창 툴바를 열면 툴바가 열림
    @Override
    protected void setupToolbar() {
        super.setupToolbar();
        if(getToolbar() !=null){
            getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    drawerLayout.openDrawer(Gravity.LEFT);
                }
            });
        }
    }

    //
    private void setupHeader(){

        View headerView = vNavigation.getHeaderView(0);
        ivMenuUserProfilePhoto = (TextView)headerView.findViewById(R.id.ivMenuUserProfilePhoto);

        //나의 프로필 클릭시 이동
        headerView.findViewById(R.id.vGlobalMenuHeader).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onGlobalMenuHeaderClick(view);
            }
        });

        Picasso.with(this)
                .load("https://www.google.co.kr/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png")
                .placeholder(R.drawable.img_circle_placeholder)
                .resize(avatarSize, avatarSize)
                .centerCrop()
                .transform(new CircleTransformation());
               // .into(ivMenuUserProfilePhoto);
    }

    public void onGlobalMenuHeaderClick(final View v){
        drawerLayout.closeDrawer(Gravity.LEFT);

        Intent intent = new Intent(getApplication(), MyPageActivity.class);
        startActivity(intent);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                int[] startingLocation = new int[2];
//                v.getLocationOnScreen(startingLocation);
//                startingLocation[0] += v.getWidth()/2;
//                MyPageActivity.startUserProfileFromLocation(startingLocation,BaseDrawerActivity.this);
//                overridePendingTransition(0,0);
//            }
//        },200);
    }
}
