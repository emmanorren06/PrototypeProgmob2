package prototypeprogmob.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    private EditText Email;
    private EditText Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView email = findViewById(R.id.txt_Email);
        SharedPreferences prefs = MainActivity2.this.getSharedPreferences("prefs_file",MODE_PRIVATE);
        String statusLogin = prefs.getString("isLogin",null);
        Button btnmasuk = (Button) findViewById(R.id.sign_in);
        btnmasuk.setOnClickListener(myBtnLoginClick);

//        Button appBtn =(Button) findViewById(R.id.sign_in);
//        appBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(MainActivity2.this,MenuDosen.class);
//                startActivity(i);
//            }
//        });
//
//        Button Btnapp = (Button) findViewById(R.id.sign_in);
//        Btnapp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//               Intent i = new Intent(MainActivity2.this,MenuAdmin.class);
//               startActivity(i);
//            }
//       });


    }
    private View.OnClickListener myBtnLoginClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SharedPreferences prefs = MainActivity2.this.getSharedPreferences("prefs_file", MODE_PRIVATE);

            String statusLogin = prefs.getString("isLogin", null);
            SharedPreferences.Editor edit = prefs.edit();

            TextView EmailText = findViewById(R.id.txt_Email);
            if (EmailText.getText().toString().contains("@si.ukdw.ac.id")) {
                edit.putString("isLogin", "Dosen");
                edit.commit();
                Intent intent = new Intent(MainActivity2.this,MenuDosen.class);
                startActivity(intent);

            } else if (EmailText.getText().toString().contains("@staff.ukdw.ac.id")){
                edit.putString("isLogin", "Admin");
                edit.commit();
                Intent intent = new Intent(MainActivity2.this,MenuAdmin.class);
                startActivity(intent);
            }

        }
    };
}
