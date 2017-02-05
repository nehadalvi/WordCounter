package com.mad.wordcounter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class WordFrequencyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_frequency);


        ArrayList<WordCount> wc = (ArrayList<WordCount>) getIntent().getExtras().getSerializable(MainActivity.CODE_LIST);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linear2);
        LinearLayout linear;
        linearLayout.removeAllViews();


        int i;
        for(i=0;i<wc.size();i++){
            linear = new LinearLayout(this);
            linearLayout.addView(linear);
            TextView tv = new TextView(this);
            tv.setText(String.valueOf(wc.get(i).getWrd()+":"+wc.get(i).getFreq()));
            linear.addView(tv);

        }

        findViewById(R.id.buttonFinish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent i = new Intent(WordFrequencyActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

    }
}
