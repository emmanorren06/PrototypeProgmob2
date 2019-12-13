package prototypeprogmob.com.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import prototypeprogmob.com.Model.Matkul;
import prototypeprogmob.com.R;

public class MatkulAdapter extends RecyclerView.Adapter<MatkulAdapter.ViewHolder> {

    private ArrayList<Matkul> MatkulArrayList;

    public MatkulAdapter(ArrayList<Matkul> MatkulArrayList){
        this.MatkulArrayList = MatkulArrayList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_view_matkul,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtKode.setText(MatkulArrayList.get(position).getKode());
        holder.txtMatkul.setText(MatkulArrayList.get(position).getMatkul());
        holder.txtHari.setText(MatkulArrayList.get(position).getHari());
        holder.txtSesi.setText(MatkulArrayList.get(position).getSesi());
        holder.txtJumSks.setText(MatkulArrayList.get(position).getJumsks());
    }

    @Override
    public int getItemCount() { //untuk menghitung jumlah data yang ada//
        return (MatkulArrayList != null) ?MatkulArrayList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtKode, txtMatkul, txtHari, txtSesi, txtJumSks;

        public ViewHolder(View view){
            super(view);
            txtKode = view.findViewById(R.id.txtKode);
            txtMatkul = view.findViewById(R.id.txtMatkul);
            txtHari = view.findViewById(R.id.txtHari);
            txtSesi = view.findViewById(R.id.txtSesi);
            txtJumSks = view.findViewById(R.id.txtJumlahsks);
        }
    }


}
