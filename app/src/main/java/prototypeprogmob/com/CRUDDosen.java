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

import prototypeprogmob.com.Model.DefaultResult;
import prototypeprogmob.com.Network.GetDataService;
import prototypeprogmob.com.Network.RetrofitClientInstance;
import retrofit2.Call;

import java.io.ByteArrayOutputStream;

import retrofit2.Callback;
import retrofit2.Response;

import static androidx.core.content.PermissionChecker.PERMISSION_GRANTED;

public class CRUDDosen extends AppCompatActivity {

    ProgressDialog progressDialog;
    EditText nidn, NamaDsn, alamat, email, gelar, foto;
    String image;
    GetDataService service;
    ImageView ImgDsn;
    private static final int GALLERY_REQUEST_CODE = 58;
    private static final int FILE_ACCESS_REQUEST_CODE = 58;//permission codenya 58
    private boolean isUpdate = false;
    private String idDosen = "";
    private String stringImg = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_dosen);
        this.setTitle(" HALLO DOSEN ");

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE}, FILE_ACCESS_REQUEST_CODE);
        }

        NamaDsn = findViewById(R.id.NamaDsn);
        nidn = findViewById(R.id.nidn);
        alamat = findViewById(R.id.alamat);
        email = findViewById(R.id.email);
        gelar = findViewById(R.id.gelar);
        foto = (EditText) findViewById(R.id.txtfoto);
        ImgDsn = findViewById(R.id.ImgDsn);

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

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NamaDsn.getText().toString().length()==0){
                    NamaDsn.setError("Nama harus diisi!");
                }
                if (nidn.getText().toString().length()==0){
                    nidn.setError("NIDN harus diisi!");
                }
                if (alamat.getText().toString().length()==0){
                    alamat.setError("ALAMAT harus diisi!");
                }
                if (email.getText().toString().length()==0){
                    email.setError("Email harus diisi!");
                }
                if (gelar.getText().toString().length()==0){
                    gelar.setError("GELAR harus diisi!");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(CRUDDosen.this);

                builder.setMessage("Mengubah data?").setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(CRUDDosen.this, "Tidak jadi Diubah", Toast.LENGTH_SHORT).show();
                    }
                }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!isUpdate) {
                            progressDialog.setMessage("Send Data");
                            progressDialog.show();


                            GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                            Call<DefaultResult> call = service.insert_foto_dosen(
                                    NamaDsn.getText().toString(),
                                    nidn.getText().toString(),
                                    alamat.getText().toString(),
                                    email.getText().toString(),
                                    gelar.getText().toString(),
                                    /*foto.getText().toString()*/
                                    stringImg,
                                    "72170097");
                            call.enqueue(new Callback<DefaultResult>() {
                                @Override
                                public void onResponse(Call<DefaultResult> call, Response<DefaultResult> response) {
                                    progressDialog.dismiss();
                                    Toast.makeText(CRUDDosen.this, "Data Tersimpan",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(CRUDDosen.this, RecyclerDsnActivity.class);
                                    startActivity(intent);
                                }

                                @Override
                                public void onFailure(Call<DefaultResult> call, Throwable t) {
                                    Toast.makeText(CRUDDosen.this, "Tidak jadi simpan", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            progressDialog.setMessage("Send Data");
                            progressDialog.show();

                            GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                            Call<DefaultResult> call = service.update_foto_dosen(
                                    idDosen,
                                    NamaDsn.getText().toString(),
                                    nidn.getText().toString(),
                                    alamat.getText().toString(),
                                    email.getText().toString(),
                                    gelar.getText().toString(),
                                    /*foto.getText().toString()*/
                                    stringImg,
                                    "72170097");
                            call.enqueue(new Callback<DefaultResult>() {
                                @Override
                                public void onResponse(Call<DefaultResult> call, Response<DefaultResult> response) {
                                    progressDialog.dismiss();
                                    Toast.makeText(CRUDDosen.this, "Data Terubah",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(CRUDDosen.this, RecyclerDsnActivity.class);
                                    startActivity(intent);
                                }

                                @Override
                                public void onFailure(Call<DefaultResult> call, Throwable t) {
                                    Toast.makeText(CRUDDosen.this, "Gagal Terubah",
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
        idDosen = extras.getString("id_dosen");
        NamaDsn.setText(extras.getString("nama"));
        nidn.setText(extras.getString("nidn"));
        alamat.setText(extras.getString("alamat"));
        email.setText(extras.getString("email"));
        gelar.setText(extras.getString("gelar"));
        foto.setText(extras.getString("foto"));
        stringImg = extras.getString("foto");
        Picasso.with(CRUDDosen.this).load("https://kpsi.fti.ukdw.ac.id/progmob/%7B%7Bfotoanda%7D%7D%22" + extras.getString("foto"));
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
                    ImgDsn.setImageURI(selectedImage);
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
    }
}


