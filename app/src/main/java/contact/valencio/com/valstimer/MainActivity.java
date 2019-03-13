package contact.valencio.com.valstimer;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button button;
    TextView textView;
    SurfaceView surfaceView;
    int timeout_count;
    private int timeout_sound;
    private SoundPool soundPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.et_display_no);
        button = findViewById(R.id.bt_play);
        textView = findViewById(R.id.tv_display);

        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        timeout_sound = soundPool.load(getApplicationContext(), R.raw.timeout_sound, 1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                if (!text.equalsIgnoreCase("")) {
                    int seconds = Integer.valueOf(text);
                    CountDownTimer countDownTimer = new CountDownTimer(seconds * 1000, 1000) {
                        @Override
                        public void onTick(long millis) {
                            textView.setVisibility(View.VISIBLE);
                            timeout_count = (int) (millis / 1000);
                            textView.setText("" + timeout_count);
                        }

                        @Override
                        public void onFinish() {
                            textView.setText("TIMEOUT");
                            soundPool.play(timeout_sound, 1, 1, 0, 0, 1);
                        }
                    }.start();
                }
            }
        });
    }
}
