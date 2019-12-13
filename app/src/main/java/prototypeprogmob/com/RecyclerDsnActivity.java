package prototypeprogmob.com;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import prototypeprogmob.com.Adapter.DosenAdapter;
import prototypeprogmob.com.Model.DSN;
import prototypeprogmob.com.Model.DefaultResult;
import prototypeprogmob.com.Network.GetDataService;
import prototypeprogmob.com.Network.RetrofitClientInstance;
import prototypeprogmob.com.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RecyclerDsnActivity extends AppCompatActivity {
    //RecyclerView rvDsn;
    private RecyclerView recyclerView;
    private DosenAdapter dsnAdapter;
    //private ArrayList<DSN> dsnList;
    private ArrayList<DSN> dsnArrayList;
    ProgressDialog progressDialog;
    ImageButton imgButDosen;

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        //return super.onContextItemSelected(item);
        DSN dosen = dsnArrayList.get(item.getGroupId());
        if (item.getTitle() == "Ubah Data Dosen") {
            //Toast.makeText(this, "Ubah ID" + dosen.getId(),Toast.LENGTH_LONG).show();
            Intent intent = new Intent(RecyclerDsnActivity.this, CRUDDosen.class);
            intent.putExtra("id_dosen", dosen.getId());
            intent.putExtra("nama", dosen.getNama());
            intent.putExtra("nidn", dosen.getNidn());
            intent.putExtra("alamat", dosen.getAlamat());
            intent.putExtra("email", dosen.getEmail());
            intent.putExtra("gelar", dosen.getGelar());
            intent.putExtra("foto", dosen.getFoto());
            intent.putExtra("is_update", true);
            startActivity(intent);
        } else if (item.getTitle() == "Hapus Data Dosen") {
            progressDialog = new ProgressDialog(RecyclerDsnActivity.this);
            progressDialog.show();

            GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
            Call<DefaultResult> call = service.delete_dosen(
                    dosen.getId(),
                    "72170097"
            );
            call.enqueue(new Callback<DefaultResult>() {
                @Override
                public void onResponse(Call<DefaultResult> call, Response<DefaultResult> response) {
                    progressDialog.dismiss();
                    Toast.makeText(RecyclerDsnActivity.this, "Berhasil Menghapus Data Dosen", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(RecyclerDsnActivity.this, RecyclerDsnActivity.class);
                    finish();
                    startActivity(i);
                }

                @Override
                public void onFailure(Call<DefaultResult> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(RecyclerDsnActivity.this, "Gagal Menghapus Data Dosen", Toast.LENGTH_LONG).show();
                }
            });
        }
        return super.onContextItemSelected(item);
    }

    //    @Override
//    public boolean onContextItemSelected(@NonNull MenuItem item) {
//        DSN dosen = dsnList.get(item.getGroupId());
//        if(item.getTitle() == "Ubah Data Dosen"){
//            Intent intent = new Intent(RecyclerDsnActivity.this, CRUDDosen.class);
//            intent.putExtra("id", dosen.getId());
//            intent.putExtra("nidn", dosen.getNidn());
//            intent.putExtra("nama", dosen.getNama());
//            intent.putExtra("email", dosen.getEmail());
//            intent.putExtra("alamat", dosen.getAlamat());
//            intent.putExtra("gelar", dosen.getGelar());
//            intent.putExtra("foto", dosen.getFoto());
//            intent.putExtra("update", true);
//            startActivity(intent);
//        }
//        else if (item.getTitle()=="Hapus Data Dosen")
//    {
//            ProgressDialog = new ProgressDialog(RecyclerDsnActivity.this);
//            ProgressDialog.show();
//
//            GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService);
//            Call<DefaultResult> call = service.delete_dosen(
//                    dosen.getId(),
//                    "72170097"
//            );
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_dsn);
        this.setTitle("SI KRS - Hai Dosen");
        //addData();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ArrayList<DSN>> call = service.getDosenAll("72170097");
        call.enqueue(new Callback<ArrayList<DSN>>(){
            @Override
            public void onResponse(Call<ArrayList<DSN>> call, Response<ArrayList<DSN>> response) {
                progressDialog.dismiss();
                dsnArrayList = response.body();
                recyclerView = (RecyclerView) findViewById(R.id.rvDsn);
                dsnAdapter = new DosenAdapter(response.body());
              //  dsnArrayList = response.body();
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RecyclerDsnActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(dsnAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<DSN>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(RecyclerDsnActivity.this,"Login gagal,coba lagi",Toast.LENGTH_SHORT);
            }
        });
        registerForContextMenu(recyclerView);

        dsnAdapter = new DosenAdapter(dsnArrayList);
        List<DSN> dsnList;

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RecyclerDsnActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(dsnAdapter);

    }
    public void klikTombol(View view){
        Intent i = new Intent(RecyclerDsnActivity.this,CRUDDosen.class);
        startActivity(i);
    }
    /*private void addData(){
        dsnArrayList = new ArrayList<>();
        dsnArrayList.add(new DSN("",
                "123456789","Hendra Sigalingging", "hendra12@gmail.com", "jl.Mangga 12A Yogyakarta","S.Kom", R.drawable.hendra));
        dsnArrayList.add(new DSN("",
                "234567890","Yetli Oslan","yetli_oslan@gmail.com", "JL.Kamboja 78 Yogyakarta", "S.Kom",R.drawable.yetli));
//        R.drawable.yetli
        dsnArrayList.add(new DSN("",
                "345678901","Wimmie H", "wimmiesyalala@gmail.com", "jl.Naga mas 66 Yogyakarta","S.Kom", R.drawable.wimmie));

    }*/

}
