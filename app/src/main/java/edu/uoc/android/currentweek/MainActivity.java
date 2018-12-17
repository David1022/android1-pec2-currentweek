package edu.uoc.android.currentweek;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSend;
    private EditText etDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViews();
    }

    private void setViews() {
        btnSend = findViewById(R.id.main_btn_check);
        btnSend.setOnClickListener(this);
        etDate = findViewById(R.id.main_ed_week_number);
    }

    private void goToResultActivity() {
        startActivity(ResultActivity.makeIntent(this, getWeekNumber()));
    }

    private int getWeekNumber() {
        return Integer.valueOf(etDate.getText().toString());
    }

    private boolean hasWeekNumber() {
        return !etDate.getText().toString().isEmpty();
    }

    @Override
    public void onClick(View v) {
        if (v == btnSend) {
            if (!hasWeekNumber()) {
                etDate.setError(getResources().getString(R.string.error_empty_date));
            } else {
                goToResultActivity();
            }
        }
    }
}
