package com.example.gaston.shakeit;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ShakeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake);

        ((Vibrator)getSystemService(Context.VIBRATOR_SERVICE)).vibrate(500);
        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.coin);
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {

                        mp.release();
                    }
                });

        CountDownTimer cntr_aCounter = new CountDownTimer(1100, 1000) {
            public void onTick(long millisUntilFinished) {
                mp.start();
            }

            @Override
            public void onFinish() {
                mp.stop();
                mp.release();
            }
        }.start();

            Bundle extras = getIntent().getExtras();

        if(extras != null){
            String value = extras.getString("RESULTADO");
            ((TextView) findViewById(R.id.t_resultado)).setText(value);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shake, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
