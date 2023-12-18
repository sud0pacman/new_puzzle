package com.example.newpuzzle;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MenuActivity extends AppCompatActivity {
    private FrameLayout exitScreen;
    private SharedPreferences pref;
    private String[] top3;
    private FrameLayout statisticsScreen;
    private ViewGroup dialogMenuButtonsView;
    private MyShared myShared;
    private LinearLayout playScreen;


    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        int orientation = getResources().getConfiguration().orientation;

        dialogMenuButtonsView = findViewById(R.id.dialogMenuButtonsView);
        playScreen = findViewById(R.id.play_screen);

        Intent mainActivity = new Intent(MenuActivity.this, MainActivity.class);


        findViewById(R.id.play_btn).setOnClickListener(v -> {
            playScreen.setVisibility(View.VISIBLE);
            dialogMenuButtonsView.setVisibility(View.GONE);

            findViewById(R.id.newGameBtn).setOnClickListener(n -> {
                mainActivity.putExtra("SAVE", false);
                startActivity(mainActivity);

            });


            findViewById(R.id.continueBtn).setOnClickListener(c -> {
                mainActivity.putExtra("SAVE", true);
                startActivity(mainActivity);
            });

            findViewById(R.id.cancel_btn).setOnClickListener(cancel -> {
                playScreen.setVisibility(View.GONE);
                dialogMenuButtonsView.setVisibility(View.VISIBLE);
            });
        });

        FrameLayout statisticsBtn = findViewById(R.id.statisticsBtn);
        myShared = MyShared.getInstance(this);
//        myShared.cleaner();

        FrameLayout infoBtn = dialogMenuButtonsView.findViewById(R.id.infoBtn);

        infoBtn.setOnClickListener(v -> {

            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                // App is in landscape mode
                findViewById(R.id.introView).setVisibility(View.GONE);
            }
            findViewById(R.id.infoView).setVisibility(View.VISIBLE);
            dialogMenuButtonsView.setVisibility(View.GONE);
        });


        findViewById(R.id.quitBtn).setOnClickListener(v -> {

            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                // App is in landscape mode
                findViewById(R.id.introView).setVisibility(View.VISIBLE);
            }

            findViewById(R.id.infoView).setVisibility(View.GONE);
            dialogMenuButtonsView.setVisibility(View.VISIBLE);
        });


        statisticsBtn.setOnClickListener(v -> {
            statisticsScreen = findViewById(R.id.statisticsScreen);

            statisticsScreen.setVisibility(View.VISIBLE);
            dialogMenuButtonsView.setVisibility(View.GONE);

            int[] res = myShared.getResults();

            TextView first = findViewById(R.id.tv_first_step);
            TextView second = findViewById(R.id.tv_second_step);
            TextView third = findViewById(R.id.tv_third_steps);

            int max = Integer.MAX_VALUE;

//            Toast.makeText(this, Arrays.toString(myShared.getResults()), Toast.LENGTH_SHORT).show();

            first.setText("1) " + (res[0] == max ? "No" : String.valueOf(res[0])));
            second.setText("2) " + (res[1] == max ? "No" : String.valueOf(res[1])));
            third.setText("3) " + (res[2] == max ? "No" : String.valueOf(res[2])));

            ImageView closeStatistics = findViewById(R.id.btn_close_statistics);

            closeStatistics.setOnClickListener(v1 -> {
                statisticsScreen.setVisibility(View.GONE);
                dialogMenuButtonsView.setVisibility(View.VISIBLE);
            });
        });


        exitScreen = findViewById(R.id.exitScreen);  // yes no  otasi


        findViewById(R.id.exitBtn).setOnClickListener(v -> {
            dialogMenuButtonsView.setVisibility(View.GONE);
            exitScreen.setVisibility(View.VISIBLE);

            TextView yes = findViewById(R.id.yesBtn);
            TextView no = findViewById(R.id.noBtn);

            yes.setOnClickListener(s -> {
                finish();
            });


            no.setOnClickListener(p -> {
                findViewById(R.id.exitScreen).setVisibility(View.GONE);
                dialogMenuButtonsView.setVisibility(View.VISIBLE);
            });
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}