package prototypeprogmob.com;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import prototypeprogmob.com.Adapter.DosenAdapter;
import prototypeprogmob.com.Adapter.MHSAdapter;
import prototypeprogmob.com.Model.DSN;
import prototypeprogmob.com.Model.DefaultResult;
import prototypeprogmob.com.Model.MhsSI;
import prototypeprogmob.com.Network.GetDataService;
import prototypeprogmob.com.Network.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerMhsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MHSAdapter mhsAdapter;
    private ArrayList<MhsSI> mhsSIArrayList;
    ProgressDialog progressDialog;
    ImageButton imgButMhs;

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        //return super.onContextItemSelected(item);
        MhsSI mhsSI = mhsSIArrayList.get(item.getGroupId());
        if (item.getTitle() == "Ubah Data Mhs") {
            //Toast.makeText(this, "Ubah ID" + dosen.getId(),Toast.LENGTH_LONG).show();
            Intent intent = new Intent(RecyclerMhsActivity.this, CRUDDosen.class);
            intent.putExtra("id_mhs", mhsSI.getId());
            intent.putExtra("nama", mhsSI.getNama());
            intent.putExtra("nidn", mhsSI.getNim());
            intent.putExtra("alamat", mhsSI.getAlamat());
            intent.putExtra("email", mhsSI.getEmail());
            intent.putExtra("foto", mhsSI.getFoto());
            intent.putExtra("is_update", true);
            startActivity(intent);
        } else if (item.getTitle() == "Hapus Data Dosen") {
            progressDialog = new ProgressDialog(RecyclerMhsActivity.this);
            progressDialog.show();

            GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
            Call<DefaultResult> call = service.delete_mhs(
                    mhsSI.getId(),
                    "72170097"
            );
            call.enqueue(new Callback<DefaultResult>() {
                @Override
                public void onResponse(Call<DefaultResult> call, Response<DefaultResult> response) {
                    progressDialog.dismiss();
                    Toast.makeText(RecyclerMhsActivity.this, "Berhasil Menghapus Data Dosen", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(RecyclerMhsActivity.this, RecyclerMhsActivity.class);
                    finish();
                    startActivity(i);
                }

                @Override
                public void onFailure(Call<DefaultResult> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(RecyclerMhsActivity.this, "Gagal Menghapus Data Dosen", Toast.LENGTH_LONG).show();
                }
            });
        }
        return super.onContextItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_mhs);
       // addData();
//        Button resetButton = (Button) findViewById(R.id.BtnSimpan);
//        resetButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(RecyclerMhsActivity.this);
//
//                builder.setMessage("Apakah anda yakin untuk mereset nilai prototype?")
//                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                Toast.makeText(RecyclerMhsActivity.this, "Tidak jadi reset", Toast.LENGTH_SHORT).show();
//                            }
//                        })
//
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                Intent i = new Intent(RecyclerMhsActivity.this,MenuAdmin.class);
//                                startActivity(i);
//                            }
//                        });
//
//                AlertDialog dialog = builder.create();
//                dialog.show();
//            }
//
//        });
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ArrayList<MhsSI>> call =service.getMhsAll("72170097");
        call.enqueue(new Callback<ArrayList<MhsSI>>() {
            @Override
            public void onResponse(Call<ArrayList<MhsSI>> call, Response<ArrayList<MhsSI>> response) {
                progressDialog.dismiss();
                mhsSIArrayList = response.body();
                recyclerView = (RecyclerView) findViewById(R.id.rvDsn);
               // mhsSIArrayList = new DosenAdapter(response.body());
                //  dsnArrayList = response.body();
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RecyclerMhsActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(mhsAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<MhsSI>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(RecyclerMhsActivity.this,"Login gagal,coba lagi",Toast.LENGTH_SHORT);
            }
        });

        registerForContextMenu(recyclerView);

        mhsAdapter = new MHSAdapter(mhsSIArrayList);
        List<DSN> dsnList;

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RecyclerMhsActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mhsAdapter);

    }
    public void klikTombol(View view){
        Intent i = new Intent(RecyclerMhsActivity.this,CRUDMhs.class);
        startActivity(i);
    }
//    private  void addData(){
//        mhsSIArrayList = new ArrayList<>();
//        mhsSIArrayList.add(new MhsSI("72170157","Frizk F.Nainggolan",
//                "jl.Mawar 45 Yogyakarta","frizka12@gmail.com",R.drawable.frizka));
//        mhsSIArrayList.add(new MhsSI("72170101","Cynthia Kumalasari",
//                "jl.Melati 22 Yogyakarta","cynku.syalala@gmail.com",R.drawable.cyntia));
//        mhsSIArrayList.add(new MhsSI("72170149","Ivan Bernov",
//                "jl.Anggrek 23 Yogyakerta","ivan55@gmail.com",R.drawable.ivan));
//        mhsSIArrayList.add(new MhsSI("72170117","Daniel Surya Nugraha",
//                "jl.Kamboja 67 Yogyakarta","damen@gmail.com",R.drawable.daniel));
//    }

}
