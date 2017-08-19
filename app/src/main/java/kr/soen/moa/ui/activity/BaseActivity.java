package kr.soen.moa.ui.activity;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.soen.moa.R;


/**
 * Created by manggi on 2017. 8. 16..
 */

/**
 * drawer activity 에 쓰는 base Acitivity
 * */
public class BaseActivity extends AppCompatActivity{

    @Nullable
    @BindView(R.id.toolbar)
    Toolbar toolbar;

//    @Nullable
//    @BindView(R.id.ivLogo)
//    ImageView ivLogo;


    private MenuItem inboxMenuItem;

    //뷰에 세팅할 컨텐츠 선택
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        bindViews();
    }

    protected void bindViews(){
        ButterKnife.bind(this);
        setupToolbar();

    }

    public void setContentViewWithoutInject(int layoutResId){
        super.setContentView(layoutResId);
    }

    //툴바 네비게이션 삼지창 표시
    protected void setupToolbar(){
        if(toolbar !=null){
            setSupportActionBar(toolbar);
            toolbar.setNavigationIcon(R.drawable.menu_bar_black);
        }
    }

    //오른 쪽에 메뉴 박스 표시
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        inboxMenuItem = menu.findItem(R.id.action_inbox);


        return true;
    }
    public Toolbar getToolbar(){return toolbar;}
    public MenuItem getInboxMenuItem(){ return inboxMenuItem;}
   // public ImageView getIvLogo() {return ivLogo;}
}
