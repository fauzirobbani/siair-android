package com.example.siair.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.siair.R;
import com.example.siair.model.Tagihan;

import java.util.ArrayList;

public class TagihanAdapter extends RecyclerView.Adapter<TagihanAdapter.TagihanViewHolder> {

    ArrayList<Tagihan> tagihans;
    Context context;

    public TagihanAdapter(Context context) {
        this.context = context;
    }

    public void setTagihan(ArrayList<Tagihan> tagihan) {
        this.tagihans = tagihan;
    }

    @NonNull
    @Override
    public TagihanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_tagihan, parent, false);
        return new TagihanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TagihanViewHolder holder, int position) {
        holder.tvNama.setText(tagihans.get(position).getPelanggan().getNama());
        holder.tvRekening.setText(String.valueOf(tagihans.get(position).getPelanggan().getRekening()));
        holder.tvTagihan.setText("Rp." + tagihans.get(position).getTagihan()  + ",-");
    }

    @Override
    public int getItemCount() {
        return tagihans.size();
    }

    class TagihanViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvNama, tvRekening, tvTagihan;

        public TagihanViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvNama = (TextView) itemView.findViewById(R.id.tv_item_tagihan_nama);
            tvRekening = (TextView) itemView.findViewById(R.id.tv_item_tagihan_rekening);
            tvTagihan = (TextView) itemView.findViewById(R.id.tv_item_tagihan_tagihan);
        }

        @Override
        public void onClick(View v) {
//            Tagihan tagihan = tagihans.get(getAdapterPosition());
//            Toast.makeText(context, "Id " + tagihan.getId(), Toast.LENGTH_SHORT).show();
        }
    }

}
