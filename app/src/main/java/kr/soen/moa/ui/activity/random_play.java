package kr.soen.moa.ui.activity;

import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RawRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import kr.soen.moa.R;

public class random_play extends AppCompatActivity {
    private SeekBar seekBar;
    private TextView textView;
    private MediaPlayer mp;
    private ArrayList<info> info = new ArrayList<>();
    private boolean soundon = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String q[] = new String[100];
        String g[] = new String[100];
        int f[] = new int[100];
        q[0] = "여러분이 살아 오면서 겪었던 가장 슬픈일은 무엇인가요?\n 그때 어떤 기분이 들었나요?\n 그 슬픔을 어떻게 극복했나요?";
        g[0] = "아이가 조심스럽게 이야기를 하면\n “슬펐겠구나, 하지만 잘 이겨내서 자랑스럽다”고 격려해주세요.";
        f[0] = R.raw.test1;
        q[1] = "내가 어린이라는 사실이 참 싫다고 느낄 때는 언제였나요?";
        g[1] = "평가보다는 아이의 마음에 공감해 주세요.\n “그래서 어린이인게 싫었구나” 하면서 공감해주는 게 도움이 돼요.";
        f[1] = R.raw.test1;
        q[2] = " 여러분은 어떤 성격인가요?\n고집이 센가요? 다정한가요? 웃음이 많나요?\n 어떤 점이 가장 마음에 드나요?";
        g[2] = "아이가 자신의 성격을 다 묘사할 때 까지 기다려주세요.\n 아이가 스스로 자신에 대해 생각해 보는 기회를 주는 시간이 될거예요.";
        f[2] = R.raw.test1;
        for(int i=0;i<100;i++){
            if(i%3==0){
                info.add(new info(q[2],g[2],f[2]));
            }else if(i%2==0){
                info.add(new info(q[1],g[1],f[1]));
            }else{
                info.add(new info(q[0],g[0],f[0]));
            }

        }

//                음성 파일 - 101
//        어느날 눈이 보이지 않는 친구가 우리 동네에 이사를 오게 되었어요! (슬픈 목소리)그런데 이
//        친구는 길에서 잘 부딪히고, 동화책도 읽을 수 없어요. 음식을 먹을 때도 숟가락이 어디 있는
//        지 알기 힘들어요. 또 어떤 점이 불편할까요?
//        -가이드라인: 타인의 감정과 생각을 이해하기 힘든 아이가 남들을 이해하도록 훈련 할 수 있
//        는 질문이에요. 이런 대화를 통해 아이의 사회성을 기를 수 있어요.
//        음성 파일 - 102
//        콩콩이는 착한 개미 친구에요. 그런데 사람들은 콩콩이의 목소리가 너무 작아서 콩콩이의 말
//        을 들을 수 없답니다. 콩콩이의 말을 들을 수 있는건 여러분밖에 없어요! 콩콩이를 위해서 무
//        엇을 해줄 수 있을까요?
//        -가이드라인: 콩콩이가 목소리가 너무 작아서 불편한 점이 무엇인지 질문해 주세요. 아이의
//        대답에 맞추어 콩콩이에게 어떻게 하면 도움이 될지 대답을 유도해주세요.
//                음성 파일 - 104
//        (즐거운 목소리)이웃 아주머니께서 우리 집에 맛있는 빵을 선물해주셨어요. 그런데 빵이 부족
//        해서 우리 가족이 모두 먹을 수 없어요. 어떻게 하면 좋을까요?
//                -가이드라인: 아이의 대답이 엉뚱하더라도 잘 생각했다고 칭찬해주세요. 그 다음엔 가족들은
//        서로 도와줘야 한다고 말해주세요.


        setContentView(R.layout.activity_random_play);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        textView = (TextView) findViewById(R.id.textView);
        seekBar.setMax(100);//질문 DB 에서의 총 질문 개수
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //SeekBar의 값이 바뀔 때마다 호출

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //SeekBar에서 Touch 했을 때 호출
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //SeekBar에서 Touch 떼었을 때 호출
                if(soundon){
                    mp.pause();
                    soundon = false;
                }
                int num = seekBar.getProgress();
                Toast.makeText(getApplicationContext(), "stop : " + num, Toast.LENGTH_SHORT).show();
                textView.setText(info.get(num).question);

                mp = MediaPlayer.create(random_play.this, info.get(num).file_name);
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
}
