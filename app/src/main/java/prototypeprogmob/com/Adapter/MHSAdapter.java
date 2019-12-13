package prototypeprogmob.com.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import prototypeprogmob.com.Model.MhsSI;
import prototypeprogmob.com.R;

public class MHSAdapter extends RecyclerView.Adapter<MHSAdapter.ViewHolder> {

    private ArrayList<MhsSI> mhsSIArrayList;
    Context context;

    public MHSAdapter(ArrayList<MhsSI> mhsSIArrayList){
        this.mhsSIArrayList = mhsSIArrayList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_view_mhs,parent,false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txtNim.setText(mhsSIArrayList.get(position).getNim());
        holder.txtNama.setText(mhsSIArrayList.get(position).getNama());
        holder.txtEmail.setText(mhsSIArrayList.get(position).getEmail());
        holder.txtAlamat.setText(mhsSIArrayList.get(position).getAlamat());
       // holder.ImgMhs.setImageResource(mhsSIArrayList.get(position).getImgMhs());
        holder.ImgMhs.getLayoutParams().width = 100;
        holder.ImgMhs.getLayoutParams().height = 100;
        if(mhsSIArrayList.get(position).getFoto() !=null){
            Picasso.with(this.context).load("https://kpsi.fti.ukdw.ac.id/progmob/"+mhsSIArrayList.get(position).getFoto())
                    .into(holder.ImgMhs);
        }
        holder.ImgMhs.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(context != null){
                    String nama = mhsSIArrayList.get(position).getNama().toString();
                    Toast.makeText(context, nama + " is selected", Toast.LENGTH_SHORT).show();}
            }
        });
    }

    @Override
    public int getItemCount() { //untuk menghitung jumlah data yang ada//
        return (mhsSIArrayList != null) ?mhsSIArrayList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNim, txtNama, txtEmail, txtAlamat;
         ImageView ImgMhs;

        public ViewHolder(View view){
            super(view);
            txtNim = view.findViewById(R.id.txtNim);
            txtNama = view.findViewById(R.id.txtNama);
            txtEmail = view.findViewById(R.id.txtEmail);
            txtAlamat = view.findViewById(R.id.txtAlamat);
            ImgMhs = view.findViewById(R.id.ImgMhs);
        }
    }


}
