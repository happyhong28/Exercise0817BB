package com.example.exercise0817bb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText et_lat;
    private EditText et_logt;
    private Button btn_sendagain;
    String latArrayB,logtArrayB,markerIdB,nmArrayB,roadnmArrayB,zipArrayB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_lat = (EditText) findViewById(R.id.et_lat);
        et_logt = (EditText) findViewById(R.id.et_logt);

        // Get intent, action and MIME type
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                handleSendText(intent); // Handle text being sent
            } else {
            }
        }

        btn_sendagain =findViewById(R.id.btn_sendagain);
        btn_sendagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sendToA;

                latArrayB = et_lat.getText().toString();
                logtArrayB = et_logt.getText().toString();

                sendToA = markerIdB+"#"+latArrayB+"#"+
                        logtArrayB+"#"+nmArrayB+"#"+nmArrayB+"#"+zipArrayB;

                Intent sendIntent1 = new Intent();
                sendIntent1.setAction(Intent.ACTION_SEND);
                sendIntent1.putExtra(Intent.EXTRA_TEXT, sendToA);
                sendIntent1.setType("text/plain");

                Intent shareIntent1 = Intent.createChooser(sendIntent1, null);
                startActivity(shareIntent1);

            }
        });
    }//

    void handleSendText(Intent intent) {
        String sharedText1 = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText1 != null) {
            // Update UI to reflect text being shared
            Log.w("",sharedText1);
            String[] splitText1 = sharedText1.split("#");
            et_lat.setText(splitText1[1]);//위도
            et_logt.setText(splitText1[2]);//경도

            markerIdB = splitText1[0];
            nmArrayB = splitText1[3];
            roadnmArrayB = splitText1[4];
            zipArrayB = splitText1[5];
        }
    }//





}