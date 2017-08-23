package kr.soen.moa.ui.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.merhold.extensiblepageindicator.ExtensiblePageIndicator;

import kr.soen.moa.R;
import kr.soen.moa.ui.adapter.IndicatorFragmentAdapter;
import kr.soen.moa.ui.fragment.IndicatorFragment;

public class startpage extends AppCompatActivity {
    private IndicatorFragmentAdapter mSimpleFragmentAdapter;
    private ViewPager mViewPager;
    private ExtensiblePageIndicator extensiblePageIndicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startpage);
        String page1_a = "재미있는 주제로 말문을 열어요";
        String page1_b = "백여가지의 다양한 주제가 준비되어 있어요";
        String page2_a = "생각과 말을 틔워요";
        String page2_b = "특별한 주제들로 생각에 깊이를 더해요";
        String page3_a = "무작위로 대화를 시작해요";
        String page3_b = "대화시간의 집중도와 재미가 올라가요";
        String page4_a = "아이와 즐거운 대화를 하세요";
        String page4_b = "";

        extensiblePageIndicator = (ExtensiblePageIndicator) findViewById(R.id.flexibleIndicator);
        mSimpleFragmentAdapter = new IndicatorFragmentAdapter(getSupportFragmentManager());
        mSimpleFragmentAdapter.addFragment(IndicatorFragment.newInstance(R.color.btn_context_menu_normal,R.drawable.ex_01,page1_a,page1_b,1));
        mSimpleFragmentAdapter.addFragment(IndicatorFragment.newInstance(R.color.btn_context_menu_normal, R.drawable.ex_02,page2_a,page2_b,2));
        mSimpleFragmentAdapter.addFragment(IndicatorFragment.newInstance(R.color.btn_context_menu_normal, R.drawable.ex_03,page3_a,page3_b,3));
        mSimpleFragmentAdapter.addFragment(IndicatorFragment.newInstance(R.color.btn_context_menu_normal, R.drawable.ex_04,page4_a,page4_b,4));

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSimpleFragmentAdapter);
        extensiblePageIndicator.initViewPager(mViewPager);
    }
}
