package prototypeprogmob.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MenuAdmin extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                AlertDialog.Builder builder = new AlertDialog.Builder(MenuAdmin.this);
                builder.setMessage("Apakah anda yakin untuk Log Out?").setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MenuAdmin.this, "Tidak jadi Log Out",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(MenuAdmin.this,MainActivity2.class);
                                startActivity(i);
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);
        this.setTitle("SI KRS - Hai {Nama Admin}");

        ImageButton ImgBtn = (ImageButton) findViewById(R.id.btnDataDiri);
        ImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuAdmin.this, CRUDDosen.class);
                startActivity(i);
            }
        });
        ImageButton Imgdsn = (ImageButton) findViewById(R.id.btnDsn);
        Imgdsn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuAdmin.this, RecyclerDsnActivity.class);
                startActivity(i);
            }
        });
        ImageButton Imgmatkul = (ImageButton) findViewById(R.id.btnMatkul);
        Imgmatkul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuAdmin.this, RecyclerMatkulActivity.class);
                startActivity(i);
            }
        });
        ImageButton Imgkelkrs = (ImageButton) findViewById(R.id.btnKrs);
        Imgmatkul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuAdmin.this, RecyclerKrsActivity.class);
                startActivity(i);
            }
        });
        ImageButton Imgmhs = (ImageButton) findViewById(R.id.btnMhs);
        Imgmatkul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuAdmin.this, RecyclerMhsActivity.class);
                startActivity(i);
            }
        });

//        ImageButton logoutButton = (ImageButton) findViewById(R.id.btnLogout);
//        logoutButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(MenuAdmin.this);
//
//                builder.setMessage("Apakah anda yakin untuk Logout?")
//                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                Toast.makeText(MenuAdmin.this, "Tidak jadi logout", Toast.LENGTH_SHORT).show();
//                            }
//                        })
//
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                Toast.makeText(MenuAdmin.this, "Melakukan logout !!", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//
//                AlertDialog dialog = builder.create();
//                dialog.show();
//            }
//
//        });
//
//        ImageButton btnlogout = (ImageButton)findViewById(R.id.btnLogout);
//        btnlogout.setOnClickListener(myBtnLogoutClick);
//    }

//    private View.OnClickListener myBtnLogoutClick = new View.OnClickListener() {
//        @Override         public void onClick(View v) {
//            Intent intent = new Intent(MenuAdmin.this, MainActivity.class);
//            startActivity(intent);
//        };
//    };
    }

    }