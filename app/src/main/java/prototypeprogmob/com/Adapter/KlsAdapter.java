package prototypeprogmob.com.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


import prototypeprogmob.com.R;
import prototypeprogmob.com.Model.Kls;

public class KlsAdapter extends RecyclerView.Adapter<KlsAdapter.ViewHolder> {

    private ArrayList<Kls> KlsArrayList;

    public KlsAdapter(ArrayList<Kls> KlsArrayList){
        this.KlsArrayList = KlsArrayList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_view_kelas,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtHari.setText(KlsArrayList.get(position).getHari());
        holder.txtSesi.setText(KlsArrayList.get(position).getSesi());
        holder.txtDsn.setText(KlsArrayList.get(position).getDosen());
        holder.txtJumMhs.setText(KlsArrayList.get(position).getJumMhs());
    }

    @Override
    public int getItemCount() { //untuk menghitung jumlah data yang ada//
        return (KlsArrayList != null) ?KlsArrayList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtHari, txtSesi, txtDsn, txtJumMhs;

        public ViewHolder(View view){
            super(view);
            txtHari = view.findViewById(R.id.txtHari);
            txtSesi = view.findViewById(R.id.txtSesi);
            txtDsn = view.findViewById(R.id.txtDsn);
            txtJumMhs = view.findViewById(R.id.txtJumMhs);
        }
    }


}
