package kr.soen.moa.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import kr.soen.moa.R;

/**
 * Created by manggi on 2017. 8. 20..
 */

public class Comment1Fragment extends Fragment implements View.OnClickListener {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_POSITION = "position";
    private static final String LOG_TAG = Comment1Fragment.class.getSimpleName();
    private int position;

    private TextView commentUser, commentContent;


    public static Comment1Fragment newInstance(int position) {

        Comment1Fragment f = new Comment1Fragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION,position);
        f.setArguments(b);

        return f;
    }

    public Comment1Fragment(){

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt(ARG_POSITION);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View titleView = inflater.inflate(R.layout.comment_fragment,container,false);

        commentUser = (TextView)titleView.findViewById(R.id.comment_user);
        commentContent = (TextView)titleView.findViewById(R.id.comment_content);

        return titleView;
    }

    @Override
    public void onClick(View view) {

    }
}
