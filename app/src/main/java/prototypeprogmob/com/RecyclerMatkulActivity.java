package prototypeprogmob.com;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import prototypeprogmob.com.Adapter.MatkulAdapter;
import prototypeprogmob.com.Model.Matkul;

public class RecyclerMatkulActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MatkulAdapter matkulAdapter;
    private ArrayList<Matkul> MatkulArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_matkul);

        addData();

        recyclerView = findViewById(R.id.rvMatkul);
        matkulAdapter = new MatkulAdapter(MatkulArrayList);

        RecyclerView.LayoutManager layoutManager = new
                LinearLayoutManager(RecyclerMatkulActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(matkulAdapter);
    }
    private void addData(){
        MatkulArrayList = new ArrayList<>();
        MatkulArrayList.add(new Matkul("MH2043",
                "Pendidikan HAM", "Jumat", "3","3"));
        MatkulArrayList.add(new Matkul("SE4323",
                "Data Mining","Kamis","1","3"));
        MatkulArrayList.add(new Matkul("SI2233",
                "MLTI", "Senin","1","3"));
        MatkulArrayList.add(new Matkul("SI3323",
                "Manajemen Proyek", "Rabu","3","3"));
        MatkulArrayList.add(new Matkul("SP5353",
                "Audir SI", "Selasa","4","3"));
        MatkulArrayList.add(new Matkul("SE3313",
                "E-Commerce", "Kamis","4","3"));

    }

}
