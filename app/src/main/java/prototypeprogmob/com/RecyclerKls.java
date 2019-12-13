package prototypeprogmob.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import prototypeprogmob.com.Adapter.KlsAdapter;
import prototypeprogmob.com.Adapter.MatkulAdapter;
import prototypeprogmob.com.Model.Kls;
import prototypeprogmob.com.Model.Matkul;

public class RecyclerKls extends AppCompatActivity {

    private RecyclerView recyclerView;
    private KlsAdapter KlsAdapter;
    private ArrayList<Kls> KlsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_kls);

        addData();

        recyclerView = findViewById(R.id.rvKls);
        KlsAdapter = new KlsAdapter(KlsArrayList);

        RecyclerView.LayoutManager layoutManager = new
                LinearLayoutManager(RecyclerKls.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(KlsAdapter);
    }
    private void addData(){
        KlsArrayList = new ArrayList<>();
        KlsArrayList.add(new Kls("Jumat",
                "Pendidikan HAM", "Hendra Sigalingging", "3"));
        KlsArrayList.add(new Kls("Kamis",
                "Data Mining","Yetli Oslan","3"));
        KlsArrayList.add(new Kls("Senin",
                "MLTI", "Wimmie","3"));
        KlsArrayList.add(new Kls("Rabu",
                "Manajemen Proyek", "Yrtli Oslan","3"));
        KlsArrayList.add(new Kls("Selasa",
                "Audir SI", "Lusy","3"));
        KlsArrayList.add(new Kls("Kamis",
                "E-Commerce", "Budi Sutedjo","3"));

    }

}



