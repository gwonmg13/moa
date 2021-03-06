package kr.soen.moa.ui.fragment;

import android.app.FragmentTransaction;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import kr.soen.moa.R;
import kr.soen.moa.ui.adapter.TitleListViewAdapter;
import kr.soen.moa.ui.data.TitleListViewItem;
import kr.soen.moa.ui.data.talk_list_Info;

import static android.graphics.Color.*;
import static kr.soen.moa.R.color.background_tab_pressed;
import static kr.soen.moa.R.color.text;

/**
 * Created by manggi on 2017. 8. 18..
 */

public class TitleTab1Fragment extends ListFragment{

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_POSITION = "position";
    private static final String LOG_TAG = TitleTab1Fragment.class.getSimpleName();
    public ArrayList<TitleListViewItem> data = new ArrayList<>();
    //fragment view controls

    TitleListViewAdapter adapter;


    public static TitleTab1Fragment newInstance(int position) {

        TitleTab1Fragment f = new TitleTab1Fragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION,position);
        f.setArguments(b);

        return f;
    }

    public TitleTab1Fragment(){

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        //Adapter 생성 및 Adapter 지정


        //첫 번째 아이템 추가
        data.add(new TitleListViewItem("여러분이 살아 오면서 겪었던 가장 슬픈일은 무엇인가요? 그때 어떤 기분이 들었나 요? 그 슬픔을 어떻게 극복했나요?",53,false,false));
        data.add(new TitleListViewItem("내가 어린이라는 사실이 참 싫다고 느낄 때는 언제였나요?",73,false,false));
        data.add(new TitleListViewItem("이웃 아주머니께서 우리 집에 맛있는 빵을 선물해주셨어요. 그런데 빵이 부족 해서 우리 가족이 모두 먹을 수 없어요. 어떻게 하면 좋을까요?",3,false,false));
        data.add(new TitleListViewItem("대대로 이어져 오는 여러분 가족의 전통은 무엇인가요?",11,false,false));
        data.add(new TitleListViewItem("콩콩이는 착한 개미 친구에요. 그런데 사람들은 콩콩이의 목소리가 너무 작아서 콩콩이의 말 을 들을 수 없답니다. 콩콩이의 말을 들을 수 있는건 여러분밖에 없어요! 콩콩이를 위해서 무 엇을 해줄 수 있을까요?",43,false,false));
        data.add(new TitleListViewItem("단 하루동안 유명해질 수 있다면 무엇을 할 건가요?",22,false,false));
        data.add(new TitleListViewItem("여러분 기억 속에 가장 오래된 겨울은 언제예요? 어떤 기억 인가요?",42,false,false));
        data.add(new TitleListViewItem("색맹이라 빨간색을 모르는 사람에게 빨간색을 설명해 보세요.",50,false,false));
        data.add(new TitleListViewItem("예전에 하고 싶었는데 하지 못해서 지금까지도 아쉬운 일은 무엇인가요?",2,false,false));
        data.add(new TitleListViewItem("여러분이 선택한 세 나라를 합쳐서 새로운 국가를 만들 수 있다면, 어떤 나라들을 선택할 건",22,false,false));
        data.add(new TitleListViewItem("여름에 냉장고를 쓰지 않고 음식을 보관하려면 어떤 방법이 있을까요?",22,false,false));
        data.add(new TitleListViewItem("여러분은 어떤 성격인가요? 고집이 센가요? 다정한가요? 웃음이 많나요? 어떤 점이 가장 마음에 드나요?",2,false,false));
        data.add(new TitleListViewItem("어느날 눈이 보이지 않는 친구가 우리 동네에 이사를 오게 되었어요! 그런데 이 친구는 길에서 잘 부딪히고, 동화책도 읽을 수 없어요. 음식을 먹을 때도 숟가락이 어디 있는 지 알기 힘들어요. 또 어떤 점이 불편할까요?",10,false,false));
        data.add(new TitleListViewItem("이웃 아주머니께서 우리 집에 맛있는 빵을 선물해주셨어요. 그런데 빵이 부족 해서 우리 가족이 모두 먹을 수 없어요. 어떻게 하면 좋을까요?",20,false,false));
        data.add(new TitleListViewItem("우산이 없는데 비가 내리면 어떻게 해야 비를 맞지 않고 집에 갈 수 있을까요?",30,false,false));

        adapter = new TitleListViewAdapter(data,this);
        setListAdapter(adapter);
        setHasOptionsMenu(true);
        return super.onCreateView(inflater,container,savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, final int position, long id) {
        super.onListItemClick(l, v, position, id);

        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if(data.get(i).getChecked()==false){
                    data.get(i).setChecked(true);
                }else{
                    data.get(i).setChecked(false);
                }
                adapter.notifyDataSetChanged();

            }

        });

    }
}
