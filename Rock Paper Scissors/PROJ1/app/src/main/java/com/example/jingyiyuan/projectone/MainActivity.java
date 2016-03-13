package com.example.jingyiyuan.projectone;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends Activity {

        String playerChoice = null;
        String computerChoice = null;
        private TextView count_tv;
        int count = 0;
        int wcount = 0;
        double pro = 0;
        MediaPlayer music;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0099ff")));
        bar.setTitle(Html.fromHtml("<font color='#ffffff' face='Segoe Script'>Minigame</font>"));
        bar.setLogo(R.drawable.icon);
        music = MediaPlayer.create(MainActivity.this,R.raw.tom_and_jerry_theme);
        music.start();
        final MediaPlayer elesound = MediaPlayer.create(MainActivity.this,R.raw.ele);
        final MediaPlayer jerrysound = MediaPlayer.create(MainActivity.this,R.raw.jerry);
        final MediaPlayer tomsound = MediaPlayer.create(MainActivity.this,R.raw.tom);

        ImageButton rock = (ImageButton)findViewById(R.id.btnRock);
        ImageButton paper = (ImageButton)findViewById(R.id.btnPaper);
        ImageButton scissor = (ImageButton)findViewById(R.id.btnSci);
        TextView replay = (TextView)findViewById(R.id.click);
        TextView refresh = (TextView)findViewById(R.id.refresh);


        replay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                music.start();
            }
        });

        refresh.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){
            count = 0;
            wcount = 0;
            pro = 0;
            //result.setText("Result reset");
            count_tv.setText("Round: reset");
        }
    });

                rock.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //click button rock
                elesound.start();
                count++;
                playerChoice = "rock";
                computerChoice = Computer();
                Compare(playerChoice, computerChoice);
            }
        });

        paper.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //click button paper
                jerrysound.start();
                count++;
                playerChoice = "paper";
                computerChoice = Computer();
                Compare(playerChoice, computerChoice);
            }
        });

        scissor.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //click button scissor
                tomsound.start();
                count++;
                playerChoice = "scissor";
                computerChoice = Computer();
                Compare(playerChoice, computerChoice);
            }
        });

    }

    public String Computer(){
        // create a choice of computer randomly
        final ImageView imgView = (ImageView)findViewById(R.id.ViewCmp);
        String cmpChoice = null;
        String[] comp = {"rock","paper","scissor"};
        Random rad = new Random();
        int index = rad.nextInt(comp.length);
        cmpChoice = comp[index];
        if (cmpChoice == "rock")
            imgView.setBackgroundResource(R.drawable.elephant);
        else if (cmpChoice == "paper")
            imgView.setBackgroundResource(R.drawable.jerry);
        else imgView.setBackgroundResource(R.drawable.tom);
        return cmpChoice;
    }


    public String Compare(String s1, String s2){
        //compare the computer's choice and the player's choice
        String pla = s1;
        String com = s2;
        String rst = null;
        TextView result =  (TextView)findViewById(R.id.textResult);
        count_tv = (TextView) findViewById(R.id.textCount);
        if((pla == "paper" & com =="rock")|(pla == "rock" & com =="scissor")|(pla == "scissor" & com =="paper")){
            rst = "win";
            wcount++;

            result.setText("Result: Win!");
        }
        if(pla == com){
            rst = "tie";
            result.setText("Result: Tie!");
        }
        if((pla == "paper" & com =="scissor")|(pla == "rock" & com =="paper")|(pla == "scissor" & com =="rock")){
            rst = "lose";
            result.setText("Result: Lose!");
        }
        pro = wcount*100/count;
        count_tv.setText("Round: " + count + " Prob:" + pro +"%");
        return rst;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    protected void onPause() {
        super.onPause();
        music.release();
    }
}
