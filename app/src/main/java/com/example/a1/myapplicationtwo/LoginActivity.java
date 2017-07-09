package com.example.a1.myapplicationtwo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this, LoginActivity.class));
    }

    public static class ActivityLogin extends AppCompatActivity implements View.OnClickListener {

        Button btn_button;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            btn_button = (Button) findViewById(R.id.button1);
            btn_button = (Button) findViewById(R.id.button2);
            btn_button = (Button) findViewById(R.id.button3);
            btn_button.setOnClickListener(this);


        }


        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button1:
                    Intent intent = new Intent(this, ProfileActivity.class);
                    startActivity(intent);

                case R.id.button2:
                    Intent intent1 = new Intent(this, RegisterActivity.class);
                    startActivity(intent1);

                case R.id.button3:
                    Intent intent3 = new Intent(this, LoginActivity.class);
                    startActivity(intent3);
                    break;
                default:
                    break;

            }
        }
    }
}
