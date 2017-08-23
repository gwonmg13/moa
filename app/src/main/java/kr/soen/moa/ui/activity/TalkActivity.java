package kr.soen.moa.ui.activity;

import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import kr.soen.moa.R;
import kr.soen.moa.ui.adapter.talk_list_adapter;

public class TalkActivity extends AppCompatActivity {
    ListView listView;
    LinearLayout linear;
    ArrayList<talk_list_Info> data = new ArrayList<>();
    talk_list_adapter adapter;
    Button button;
    private MediaPlayer mp;
    boolean soundon = false;
    TextView textView_guide;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk);
        listView = (ListView) findViewById(R.id.listView);
        button = (Button) findViewById(R.id.button);
        textView_guide = (TextView)findViewById(R.id.textView_guide);
        linear = (LinearLayout)findViewById(R.id.linear);
        textView = (TextView)findViewById(R.id.textView2);
        String q1 = "여러분이 살아가면서 겪었던 가장 슬픈 일은무엇인가요?\n그때 어떤 기분이 들었나요?\n그 슬픔은 어떻게 극복했나요?";
        String g1 = "아이가 조심스럽게 이야기를 하면\n “슬펐겠구나, 하지만 잘 이겨내서 자랑스럽다”고 격려해주세요.";
        //database 에서 대화리스트에
        data.add(new talk_list_Info(0, q1, "url", g1, false));
        data.add(new talk_list_Info(1, q1, "url", g1, false));
        data.add(new talk_list_Info(2, q1, "url", null, false));
        data.add(new talk_list_Info(3, q1, "url", null, false));
        adapter = new talk_list_adapter(data, this);
        listView.setAdapter(adapter);
        setListView();

    }

    void setListView() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                return true;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //data.get(position).file -> play
                String guide = data.get(position).guide;
                System.out.println("minzi"+guide);
                textView_guide.setText(guide);
                if (TextUtils.isEmpty(guide)){
                    linear.setVisibility(View.GONE);
                    textView.setVisibility(View.GONE);
                }else {
                    textView_guide.setText(guide);
                    textView_guide.setVisibility(View.VISIBLE);
                    linear.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.VISIBLE);

                }
                if(soundon==true){
                    mp.pause();
                    soundon=false;
                }
                for(int i=0;i<data.size();i++){
                    data.get(i).check=false;
                }
                mp = MediaPlayer.create(TalkActivity.this, R.raw.test1);
                data.get(position).check = true;
                adapter.notifyDataSetChanged();
                new Thread() {
                    public void run() {
                        Message msg = handlermusic.obtainMessage();
                        handlermusic.sendMessage(msg);
                    }
                }.start();
            }
        });
    }

    final Handler handlermusic = new Handler()

    {

        public void handleMessage(Message msg)

        {
            soundon = true;
            mp.start();
        }
    };

//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.button:
//                if (button.getText().equals("p")) {
//                    button.setBackgroundDrawable((ContextCompat.getDrawable(this, R.drawable.category1_guide)));
////                    mp = MediaPlayer.create(TalkActivity.this, R.raw.mamabgm);
//                    button.setText("s");
//                } else {
//                    button.setBackgroundDrawable((ContextCompat.getDrawable(this, R.drawable.category1_border)));
//                }
//                break;
//        }
//
//    }
}
