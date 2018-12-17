package edu.uoc.android.currentweek;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import edu.uoc.android.currentweek.utils.DateUtils;
import java.util.Calendar;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_WEEK = "weekNumber";

    private TextView tvResult;
    private Button btnTryAgain;

    private MediaPlayer mediaPlayer;

    /**
     * Method to create the Intent and to send data to ResultActivity
     *
     * @param context: previous activity
     * @param weekNumber: week number of the year
     *
     * @return intent to be used by startActivity
     */
    public static Intent makeIntent(Context context, int weekNumber) {
        Intent intent = new Intent(context, ResultActivity.class);
        intent.putExtra(EXTRA_WEEK, weekNumber);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Get the week number send by previous activity
        int weekNumber = getIntent().getIntExtra(EXTRA_WEEK, 0);
        setViews();
        checkWeekNumber(weekNumber);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Stopping the sound if user leaves the screen and it is playing
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.release();
        }
    }

    private void setViews() {
        tvResult = findViewById(R.id.result_tv_message);
        btnTryAgain = findViewById(R.id.result_btn_try_again);
        btnTryAgain.setOnClickListener(this);
    }

    private void checkWeekNumber(int weekNumber) {
        DateUtils dateUtils = new DateUtils(Calendar.getInstance());
        boolean isTheResultCorrect = dateUtils.isTheCurrentWeekNumber(weekNumber);
        setTextDependingTheResult(isTheResultCorrect);
        setButtonTextDependingTheResult(isTheResultCorrect);
        setAudioDependingTheResult(isTheResultCorrect);
    }

    private void setTextDependingTheResult(boolean isTheResultCorrect) {
        if (isTheResultCorrect) {
            tvResult.setText(getResources().getString(R.string.right_result));
            tvResult.setTextColor(Color.GREEN);
        } else {
            tvResult.setText(getResources().getString(R.string.fail_result));
            tvResult.setTextColor(Color.RED);
        }
    }

    private void setButtonTextDependingTheResult(boolean isTheResultCorrect) {
        if (isTheResultCorrect) {
            btnTryAgain.setText(getResources().getString(R.string.button_start_again));
        } else {
            btnTryAgain.setText(getResources().getString(R.string.button_try_again));
        }
    }

    private void setAudioDependingTheResult(boolean isTheResultCorrect) {
        mediaPlayer = MediaPlayer.create(this, isTheResultCorrect ? R.raw.audio_yes_message : R.raw.audio_no_message);
        mediaPlayer.start(); // no need to call prepare(); create() does that for you
    }

    @Override
    public void onClick(View v) {
        if (v == btnTryAgain) {
            // Finish this activity to start again the game.
            finish();
        }
    }
}
