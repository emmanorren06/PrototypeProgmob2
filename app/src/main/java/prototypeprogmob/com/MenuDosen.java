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

public class MenuDosen extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                AlertDialog.Builder builder = new AlertDialog.Builder(MenuDosen.this);
                builder.setMessage("Apakah anda yakin untuk Log Out?").setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MenuDosen.this, "Tidak jadi Log Out",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(MenuDosen.this, MainActivity2.class);
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
        setContentView(R.layout.menu_dosen);
        this.setTitle("SI KRS - Hai {Nama Dosen}");

        ImageButton imgBtn = (ImageButton) findViewById(R.id.BtnDataDiri);
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuDosen.this, RecyclerDsnActivity.class);
                startActivity(i);
            }
        });
        ImageButton btnimg = (ImageButton) findViewById(R.id.BtnKrs);
        btnimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuDosen.this, RecyclerKrsActivity.class);
                startActivity(i);
            }
        });
        ImageButton imgbtn = (ImageButton) findViewById(R.id.BtnKelas);
        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuDosen.this,RecyclerKls.class);
                startActivity(i);
            }
        });

//        ImageButton logoutButton = (ImageButton) findViewById(R.id.btnLogout);
//        logoutButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(MenuDosen.this);
//
//                builder.setMessage("Apakah anda yakin untuk Logout?")
//                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                Toast.makeText(MenuDosen.this, "Tidak jadi logout", Toast.LENGTH_SHORT).show();
//                            }
//                        })
//
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                Toast.makeText(MenuDosen.this, "Melakukan logout !!", Toast.LENGTH_SHORT).show();
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

//        private View.OnClickListener myBtnLogoutClick = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MenuDosen.this, MainActivity.class);
//                startActivity(intent);
//            }
//
//            ;
//        };

    }
}
