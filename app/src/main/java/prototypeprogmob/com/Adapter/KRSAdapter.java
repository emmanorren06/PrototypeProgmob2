package prototypeprogmob.com.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import prototypeprogmob.com.Model.MhsSI;
import prototypeprogmob.com.R;
import prototypeprogmob.com.Model.KRSSI;
import prototypeprogmob.com.R;

public class KRSAdapter extends RecyclerView.Adapter<KRSAdapter.ViewHolder> {

    private ArrayList<KRSSI> KRSSIArrayList;

    public KRSAdapter(ArrayList<KRSSI> KRSSIArrayList){ this.KRSSIArrayList = KRSSIArrayList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_view_daftar_krs,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtKode.setText(KRSSIArrayList.get(position).getKode());
        holder.txtMatkul.setText(KRSSIArrayList.get(position).getMatkul());
        holder.txtHari.setText(KRSSIArrayList.get(position).getHari());
        holder.txtSesi.setText(KRSSIArrayList.get(position).getSesi());
        holder.txtJmlSks.setText(KRSSIArrayList.get(position).getJmlSks());
        holder.txtDosen.setText(KRSSIArrayList.get(position).getDosen());
        holder.txtJmlMhs.setText(KRSSIArrayList.get(position).getJmlMhs());
    }

    @Override
    public int getItemCount() { //untuk menghitung jumlah data yang ada//
        return (KRSSIArrayList != null) ?KRSSIArrayList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtKode, txtMatkul, txtHari, txtSesi, txtJmlSks, txtDosen, txtJmlMhs;

        public ViewHolder(View view){
            super(view);
            txtKode = view.findViewById(R.id.txtKode);
            txtMatkul = view.findViewById(R.id.txtMatkul);
            txtHari = view.findViewById(R.id.txtHari);
            txtSesi = view.findViewById(R.id.txtSesi);
            txtJmlSks = view.findViewById(R.id.txtJumlahsks);
            txtDosen = view.findViewById(R.id.txtDsn);
            txtJmlMhs = view.findViewById(R.id.txtJmlMhs);
        }
    }


}
