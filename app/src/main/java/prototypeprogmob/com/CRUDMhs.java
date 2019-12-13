package prototypeprogmob.com;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;

import prototypeprogmob.com.Model.DefaultResult;
import prototypeprogmob.com.Network.GetDataService;
import prototypeprogmob.com.Network.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.core.content.PermissionChecker.PERMISSION_GRANTED;

public class CRUDMhs extends AppCompatActivity {

    ProgressDialog progressDialog;
    EditText nim, Nama, alamat, email, foto;
    String image;
    GetDataService service;
    ImageView ImgMhs;
    private static final int GALLERY_REQUEST_CODE = 58;
    private static final int FILE_ACCESS_REQUEST_CODE = 58;//permission codenya 58
    private boolean isUpdate = false;
    private String idMhs = "";
    private String stringImg = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crudmhs);
        this.setTitle("SI KRS - HALLO MAHASISWA");

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE}, FILE_ACCESS_REQUEST_CODE);
        }

        nim = findViewById(R.id.editNim);
        Nama = findViewById(R.id.editName);
        alamat = findViewById(R.id.editAlamat);
        email = findViewById(R.id.editEmail);
        foto = (EditText) findViewById(R.id.txtfoto);
        ImgMhs = findViewById(R.id.ImgMhs);

        Button btnUpload = findViewById(R.id.BtnUpload);
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType("image/*");
                String[] mimeTypes = {"image/jpeg"};
                i.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
                startActivityForResult(i, GALLERY_REQUEST_CODE);
            }
        });

        progressDialog = new ProgressDialog(this);

        checkUpdate();
        Button btnSimpan = (Button) findViewById(R.id.BtnSimpan);
        if (isUpdate) {
            btnSimpan.setText("Update");
        }

  //      Button btnSimpan = findViewById(R.id.BtnSimpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nim.getText().toString().length()==0){
                    nim.setError("Nama harus diisi!");
                }
                if (Nama.getText().toString().length()==0){
                    Nama.setError("NIDN harus diisi!");
                }
                if (alamat.getText().toString().length()==0){
                    alamat.setError("ALAMAT harus diisi!");
                }
                if (email.getText().toString().length()==0){
                    email.setError("Email harus diisi!");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(CRUDMhs.this);
                builder.setMessage("Mengubah data?").setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(CRUDMhs.this, "Tidak jadi Diubah", Toast.LENGTH_SHORT).show();
                    }
                }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!isUpdate) {
                            progressDialog.setMessage("Send Data");
                            progressDialog.show();

                            GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                            Call<DefaultResult> call = service.insert_foto_mahasiswa(
                                    idMhs.toString(),
                                    nim.getText().toString(),
                                    Nama.getText().toString(),
                                    alamat.getText().toString(),
                                    email.getText().toString(),
                                    //gelar.getText().toString(),
                                    /*foto.getText().toString()*/
                                    stringImg,
                                    "72170097");
                            call.enqueue(new Callback<DefaultResult>() {
                                @Override
                                public void onResponse(Call<DefaultResult> call, Response<DefaultResult> response) {
                                    progressDialog.dismiss();
                                    Toast.makeText(CRUDMhs.this, "Data Tersimpan",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(CRUDMhs.this, RecyclerDsnActivity.class);
                                    startActivity(intent);
                                }

                                @Override
                                public void onFailure(Call<DefaultResult> call, Throwable t) {
                                    Toast.makeText(CRUDMhs.this, "Tidak jadi simpan", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }else {
                            progressDialog.setMessage("Send Data");
                            progressDialog.show();

                            GetDataService service =RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                            Call<DefaultResult> call = service.update_foto_mhs(
                                    idMhs,
                                    Nama.getText().toString(),
                                    nim.getText().toString(),
                                    alamat.getText().toString(),
                                    email.getText().toString(),
                                    //gelar.getText().toString(),
                                    /*foto.getText().toString()*/
                                    stringImg,
                                    "72170097");
                            call.enqueue(new Callback<DefaultResult>() {
                                @Override
                                public void onResponse(Call<DefaultResult> call, Response<DefaultResult> response) {
                                    progressDialog.dismiss();
                                    Toast.makeText(CRUDMhs.this, "Data Terubah",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(CRUDMhs.this, RecyclerDsnActivity.class);
                                    startActivity(intent);
                                }

                                @Override
                                public void onFailure(Call<DefaultResult> call, Throwable t) {
                                    Toast.makeText(CRUDMhs.this, "Gagal Terubah",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
    void checkUpdate() {
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }
        isUpdate = extras.getBoolean("is_update");
        idMhs = extras.getString("id_dosen");
        Nama.setText(extras.getString("nama"));
        nim.setText(extras.getString("nidn"));
        alamat.setText(extras.getString("alamat"));
        email.setText(extras.getString("email"));
        //gelar.setText(extras.getString("gelar"));
        foto.setText(extras.getString("foto"));
        stringImg = extras.getString("foto");
        Picasso.with(CRUDMhs.this).load("https://kpsi.fti.ukdw.ac.id/progmob/%7B%7Bfotoanda%7D%7D%22" + extras.getString("foto"));
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case FILE_ACCESS_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PERMISSION_GRANTED) {
                    //PERMISSION_GRANTED
                }
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode) {
                case GALLERY_REQUEST_CODE:
                    Uri selectedImage = data.getData();
                    ImgMhs.setImageURI(selectedImage);
                    //proses konversi
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectedImage,
                            /*mendapatkan realpath*/   filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String imgDecodeableString = cursor.getString(columnIndex);
                    foto.setText(imgDecodeableString);
                    cursor.close();
                    //convert ke bitmap, lalu array, lalu stringnya pakai base64
                    Bitmap bm = BitmapFactory.decodeFile(imgDecodeableString);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
                    byte[] b = baos.toByteArray();

                    stringImg = Base64.encodeToString(b, Base64.DEFAULT);
                    break;
    }
    //                AlertDialog.Builder builder=new AlertDialog.Builder(CRUDMhs.this);
//                builder.setMessage("Apakah anda yakin akan menyimpan data? ")
//                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                Toast.makeText(CRUDMhs.this, "Data tidak tersimpan",
//                                        Toast.LENGTH_SHORT).show();
//                            }
//                        })
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//
//                                Intent i = new Intent(CRUDMhs.this, MenuDosen.class);
//                                startActivity(i);
//                            }
//                        });
//                AlertDialog dialog = builder.create();
//                dialog.show();
//            }
//        });
    }
}
