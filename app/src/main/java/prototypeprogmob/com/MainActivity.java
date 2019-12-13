package prototypeprogmob.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SharedPreferences prefs = MainActivity.this.getSharedPreferences("preft_file",MODE_PRIVATE);
        String statusLogin = prefs.getString("isLogin", null);
        if(statusLogin != null){
            if(statusLogin.contains("Dosen"))
            {
                Intent intent = new Intent(MainActivity.this, MenuDosen.class);
                startActivity(intent);
            }else if (statusLogin.equals("Admin")) {
                Intent intent = new Intent(MainActivity.this,MenuAdmin.class);
                startActivity(intent);
            }
        }else{
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(getApplicationContext(), MainActivity2.class));
                    finish();
                }
            }, 900L); //300 l = 3 detik
        }

//       ImageButton imgBtn =(ImageButton) findViewById(R.id.img_btn);
//        imgBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(MainActivity.this,Main3Activity.class);
//                startActivity(i);
//            }
//        });

    }

}

