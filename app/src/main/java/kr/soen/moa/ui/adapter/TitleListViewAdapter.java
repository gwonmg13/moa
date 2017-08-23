package kr.soen.moa.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.util.ArrayList;

import butterknife.BindView;
import kr.soen.moa.R;
import kr.soen.moa.ui.activity.Main2Activity;
import kr.soen.moa.ui.data.TitleListViewItem;
import kr.soen.moa.ui.fragment.Title1Fragment;
import kr.soen.moa.ui.fragment.TitleTab1Fragment;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * Created by manggi on 2017. 8. 19..
 */

public class TitleListViewAdapter extends BaseAdapter  {

   // private Context context;
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<TitleListViewItem> titleListItemList = new ArrayList<TitleListViewItem>();
    boolean ChechComments = FALSE;
    LayoutInflater inflater;


    public TitleListViewAdapter() {

    }

    private class ViewHolder{

        TextView titleContent;
        TextView tsLikesCounter;
        ImageView btnLike;
        ImageView btnComments;
    }


    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return titleListItemList.size();
    }

    //지정한 위치에 있는 데이터 리턴
    @Override
    public Object getItem(int position) {
        return null;
    }

    //지정한 위치에 있는 데이터와 관계된 아이템의 id 리턴함
    @Override
    public long getItemId(int position) {
        return position;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView==null){
            holder = new ViewHolder();
            inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item,parent,false);

            //화면에 표시될 View(Layout이 inflate된) 위젯에 대한 참조 획득

            holder.titleContent = (TextView)convertView.findViewById(R.id.title_content);
            holder.tsLikesCounter = (TextView)convertView.findViewById(R.id.tsLikesCounter);
            holder.btnLike = (ImageView)convertView.findViewById(R.id.btnLike);
            holder.btnComments = (ImageView)convertView.findViewById(R.id.btnComments);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }


        //"listview_item" Layout을 inflate 해서 convertView 참조 획득
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item,parent,false);
        }



        /**
         * 댓글 보여주는 프레그먼트 생성
         * */
        final LinearLayout linearComment = (LinearLayout)convertView.findViewById(R.id.linearComment);

//        ImageView btnLike = (ImageView)convertView.findViewById(R.id.btnLike);
//        ImageView btnComments = (ImageView)convertView.findViewById(R.id.btnComments);


        ViewPager pager4 = (ViewPager)convertView.findViewById(R.id.pager4);

        //pager4.setAdapter(new CommentsAdapter(getSupportFragmentManager()));

        //DATA SET에서 position에 위치한 데이터 참조 획득
        TitleListViewItem titleListViewItem = titleListItemList.get(position);

        //아이템 내 각 위젯에 데이터 반영
        holder.titleContent.setText(titleListViewItem.getContent());
        //int likeNum = titleListViewItem.getLikeNum();

        holder.tsLikesCounter.setText(Integer.toString(titleListViewItem.getLikeNum()));



        holder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"좋아효>.<",Toast.LENGTH_SHORT).show();
            }
        });


        holder.btnComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ChechComments==FALSE){
                    linearComment.setVisibility(View.VISIBLE);
                    ChechComments=TRUE;

                }else if(ChechComments==TRUE){
                    linearComment.setVisibility(View.GONE);
                    ChechComments=FALSE;


                }
            }
        });
        return convertView;
    }


    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(String titleContent, int tsLikesCounter) {
        TitleListViewItem item = new TitleListViewItem();

        item.setContent(titleContent);
        item.setLikeNum(tsLikesCounter);


        titleListItemList.add(item);
    }







}
