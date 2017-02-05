/*
Homework 3
Chinmay Rawool
Neha Kishor Dalvi



 */




package com.mad.wordcounter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //boolean flag = false;

    LinearLayout linearLayout, l1;

    ArrayList<Integer> c = new ArrayList<Integer>();
    ArrayList<WordCount> words = new ArrayList<WordCount>();
    ArrayList<MyLayout> myLayoutArrayList = new ArrayList<MyLayout>();
    ProgressDialog pg;
    static int count = 0, incr=1;

    public final static String CODE_LIST="ARRAYLIST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        linearLayout = (LinearLayout) findViewById(R.id.linear1);
        addElement();


        findViewById(R.id.wc_buttonSearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean blanks=true;
                for(int k=0; k<c.size();k++){
                    if(myLayoutArrayList.get(k).getEt().getText().toString().equals("") || myLayoutArrayList.get(k).getEt().getText().toString().matches(" ")){
                        Log.d("inside for loop",String.valueOf(myLayoutArrayList.get(k).getEt().getText()+""));
                        Toast.makeText(MainActivity.this,"Please enter text",Toast.LENGTH_SHORT).show();
                        blanks=false;
                    }
                }
                if(blanks) {
                    pg = new ProgressDialog(MainActivity.this);
                    pg.setMax(c.size());
                    pg.setProgress(0);
                    pg.show();
                    Log.d("MAIN", " inside main");
                    Log.d("MAIN", c.toString());
                    Log.d("MAIN", myLayoutArrayList.toString());
                    Log.d("MAIN", words.size() + "");
                    Log.d("MAIN", c.size() + "");
                    String str;

                    /*for (int i = 0; i < myLayoutArrayList.size(); i++) {
                        Log.d("for loop iteration", i + "" + myLayoutArrayList.get(i).getEt().getText().toString());
                        if (myLayoutArrayList.get(i).getEt().getText().toString().equals("")) {
                            Log.d("inside for loop", String.valueOf(myLayoutArrayList.get(i).getEt().getText() + ""));
                            Toast.makeText(MainActivity.this, "Please enter text", Toast.LENGTH_SHORT).show();
                            blanks = true;
                        }
                    }*/


                    for (int j = 0; j < c.size(); j++) {
                        Log.d("MAIN", c.get(j).toString());

                        EditText et = myLayoutArrayList.get(j).getEt();
                        str = et.getText().toString();
                        AsyncWordCount thread = new AsyncWordCount();
                        if (((CheckBox) findViewById(R.id.wc_checkBoxMatchCases)).isChecked()) {
                            Log.d("match cases", "=" + ((CheckBox) findViewById(R.id.wc_checkBoxMatchCases)).isChecked());
                            thread.execute(str, "true");
                        } else {
                            Log.d("match cases", "=" + ((CheckBox) findViewById(R.id.wc_checkBoxMatchCases)).isChecked());
                            thread.execute(str, "false");
                        }
                        Log.d("MAIN", str);


                    }

                }
                //Log.d("demo","after threading"+pg.getProgress()+""+pg.getMax());

                //Log.d("MAIN", words.toString().trim());
            }
        });

    }


    void addElement() {

        l1 = new LinearLayout(this);
        //l1.add(l);
        linearLayout.addView(l1);
        final MyLayout ml = new MyLayout(new Button(this), new EditText(this));
        ml.setId(count);
        c.add(count);
        l1.setId(1000 + count);
        l1.addView(ml.getEt());
        l1.addView(ml.getBtn());
        myLayoutArrayList.add(ml);

        ml.getBtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d("neha",ml.getEt().getText()+"");
                if (ml.getFlag()) {

                    Log.d("demo","incr="+incr);
                    if(incr<20) {
                        int i = view.getId();
                        Log.d("ADD", "Id is " + i);
                        ml.getBtn().setText("-");
                        ml.setFlag(false);
                        count++;
                        incr++;
                        addElement();
                    }else{
                        Toast.makeText(MainActivity.this, "Limit reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    //ml.setFlag(true);
                    --incr;
                    int i = view.getId();
                    Log.d("REMOVE", "Id is " + i);
                    c.remove((Integer) i);
                    LinearLayout linearLayout = (LinearLayout) findViewById(1000 + i);
                    myLayoutArrayList.remove(myLayoutArrayList.indexOf(ml));
                    linearLayout.removeAllViews();
                    Log.d("Array", c.toString());

                }

            }
        });
    }


    class AsyncWordCount extends AsyncTask<String, Void, Void> {
        String str;
        int count;


        @Override
        protected Void doInBackground(String... strings) {
            int wordCounter=0;
            try {
                InputStream stream = getAssets().open("textfile.txt");
                int size = stream.available();
                byte[] buffer = new byte[size];
                stream.read(buffer);
                stream.close();
                String text=new String(buffer);
                str = strings[0];
                if(strings[1]== "true"){
                    String[] words = text.split("[-;(),\\s+_.:\"?!--#]");

                    for(int i=0;i<words.length;i++){
                        if(strings[0].equals(words[i])){
                            wordCounter++;
                        }
                    }
                    publishProgress();
                    count = wordCounter;
                    Log.d("demo","count="+wordCounter);
                }else{
                    String[] words = text.split("[-;(),\\s+_.:\"?!--#]");

                    for(int i=0;i<words.length;i++){
                        if(strings[0].equalsIgnoreCase(words[i])){
                            wordCounter++;
                        }
                    }
                    count = wordCounter;
                    publishProgress();
                    Log.d("demo","count="+wordCounter);
                }
                //Log.d("demo",text);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected void onPostExecute(Void avoid) {
            super.onPostExecute(avoid);
            words.add(new WordCount(str,count));
            Log.d("MAIN", "word size:"+words.size()+"");
            if(words.size()== c.size()){
                pg.dismiss();
                Intent intent = new Intent(MainActivity.this, WordFrequencyActivity.class);
                intent.putExtra(MainActivity.CODE_LIST, words);
                startActivity(intent);
            }
        }

        @Override
        protected void onProgressUpdate(Void... avoid) {
            super.onProgressUpdate(avoid);
            pg.setProgress(pg.getProgress()+1);
        }
    }
}