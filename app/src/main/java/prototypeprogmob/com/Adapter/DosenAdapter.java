package prototypeprogmob.com.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import prototypeprogmob.com.CRUDDosen;
import prototypeprogmob.com.Model.DSN;
import prototypeprogmob.com.R;

public class DosenAdapter extends RecyclerView.Adapter<DosenAdapter.ViewHolder>{

    ArrayList<DSN> dsnArrayList;
    Context context;

    public DosenAdapter(ArrayList<DSN> dsnArrayList) {
        this.dsnArrayList = dsnArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_view_dosen,parent,false);
        context = parent.getContext();
        return new DosenAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txtNidn.setText(dsnArrayList.get(position).getNidn());
        holder.txtNama.setText(dsnArrayList.get(position).getNama());
        holder.txtEmail.setText(dsnArrayList.get(position).getEmail());
        holder.txtAlamat.setText(dsnArrayList.get(position).getAlamat());
        holder.txtGelar.setText(dsnArrayList.get(position).getGelar());
        //holder.ImgDsn.setImageResource(dsnArrayList.get(position).getFoto());
        holder.ImgDsn.getLayoutParams().width = 100;
        holder.ImgDsn.getLayoutParams().height = 100;
        if(dsnArrayList.get(position).getFoto() !=null){
            Picasso.with(this.context).load("https://kpsi.fti.ukdw.ac.id/progmob/"+dsnArrayList.get(position).getFoto())
                    .into(holder.ImgDsn);
        }
        holder.ImgDsn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(context != null){
                    String nama = dsnArrayList.get(position).getNama().toString();
                    Toast.makeText(context, nama + " is selected", Toast.LENGTH_SHORT).show();}
            }
        });
    }

    @Override
    public int getItemCount() { //untuk menghitung jumlah data yang ada//
        return (dsnArrayList != null) ?dsnArrayList.size() : 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder
             implements View.OnCreateContextMenuListener{
            private TextView txtNidn, txtNama, txtEmail, txtAlamat, txtGelar;
            private ImageView ImgDsn;

        public ViewHolder(View view) {
            super(view);
            txtNidn = view.findViewById(R.id.txtNidn);
            txtNama = view.findViewById(R.id.txtNama);
            txtEmail = view.findViewById(R.id.txtEmail);
            txtAlamat = view.findViewById(R.id.txtAlamat);
            txtGelar = view.findViewById(R.id.txtGelar);
            ImgDsn = view.findViewById(R.id.ImgDsn);
            view.setOnCreateContextMenuListener(this);
         }


        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Pilih Aksi");
            menu.add(this.getAdapterPosition(), v.getId(), 0, "Ubah Data Dosen");
            menu.add(this.getAdapterPosition(), v.getId(), 0, "Hapus Data Dosen");
        }
    }
}

