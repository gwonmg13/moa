package kr.soen.moa.ui.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import kr.soen.moa.R;
import kr.soen.moa.ui.data.MyPageListViewItem;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * Created by manggi on 2017. 8. 19..
 */

public class MyPageViewAdapter extends BaseAdapter  {

   // private Context context;
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<MyPageListViewItem> myPageItemList = new ArrayList<MyPageListViewItem>();
    boolean ChechComments = FALSE;
    LayoutInflater inflater;


    public MyPageViewAdapter() {

    }

    private class ViewHolder{
        TextView comment_regdate;
        TextView comment_content;
        ImageView btn_Comment;


    }


    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return myPageItemList.size();
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
            convertView = inflater.inflate(R.layout.mypage_listview_item,parent,false);

            //화면에 표시될 View(Layout이 inflate된) 위젯에 대한 참조 획득

            holder.comment_regdate = (TextView)convertView.findViewById(R.id.comment_regdate);
            holder.comment_content = (TextView)convertView.findViewById(R.id.title_content);
            holder.btn_Comment = (ImageView)convertView.findViewById(R.id.btnLike);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }


        /**
         * 댓글 보여주는 프레그먼트 생성
         * */
        final LinearLayout linearComment = (LinearLayout)convertView.findViewById(R.id.linearComment);


        ViewPager pager4 = (ViewPager)convertView.findViewById(R.id.pager4);

        //pager4.setAdapter(new CommentsAdapter(getSupportFragmentManager()));

        //DATA SET에서 position에 위치한 데이터 참조 획득
//        TitleListViewItem titleListViewItem = titleListItemList.get(position);
//
//        //아이템 내 각 위젯에 데이터 반영
//        holder.titleContent.setText(titleListViewItem.getContent());
//        //int likeNum = titleListViewItem.getLikeNum();
//
//        holder.tsLikesCounter.setText(Integer.toString(titleListViewItem.getLikeNum()));
//
//
//=
        MyPageListViewItem myPageListViewItem = myPageItemList.get(position);
        holder.comment_regdate.setText(myPageListViewItem.getReg_date());
        holder.comment_content.setText(myPageListViewItem.getComment());
        holder.btn_Comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"좋아효>.<",Toast.LENGTH_SHORT).show();
            }
        });

//
//        holder.btnComments.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(ChechComments==FALSE){
//                    linearComment.setVisibility(View.VISIBLE);
//                    ChechComments=TRUE;
//
//                }else if(ChechComments==TRUE){
//                    linearComment.setVisibility(View.GONE);
//                    ChechComments=FALSE;
//
//
//                }
//            }
//        });
        return convertView;
    }


    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(String comment_regdate, String comment_content) {
        MyPageListViewItem item = new MyPageListViewItem();

        item.setReg_date(comment_regdate);
        item.setTitle(comment_content);


     //   titleListItemList.add(item);
    }







}
