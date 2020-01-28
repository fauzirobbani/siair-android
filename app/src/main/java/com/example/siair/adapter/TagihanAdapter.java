package com.example.siair.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.siair.R;
import com.example.siair.model.Tagihan;

import java.util.ArrayList;

public class TagihanAdapter extends RecyclerView.Adapter<TagihanAdapter.TagihanViewHolder> {

    ArrayList<Tagihan> tagihan;

    public TagihanAdapter() {
        this.tagihan = null;
    }

    public TagihanAdapter(ArrayList<Tagihan> tagihan) {
        this.tagihan = tagihan;
    }

    public void setTagihan(ArrayList<Tagihan> tagihan) {
        this.tagihan = tagihan;
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
        holder.tvNama.setText(tagihan.get(position).getPelanggan().getNama());
        holder.tvRekening.setText(String.valueOf(tagihan.get(position).getPelanggan().getRekening()));
        holder.tvTagihan.setText("Rp." + tagihan.get(position).getTagihan()  + ",-");
    }

    @Override
    public int getItemCount() {
        return tagihan.size();
    }

    class TagihanViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNama, tvRekening, tvTagihan;

        public TagihanViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = (TextView) itemView.findViewById(R.id.tv_item_tagihan_nama);
            tvRekening = (TextView) itemView.findViewById(R.id.tv_item_tagihan_rekening);
            tvTagihan = (TextView) itemView.findViewById(R.id.tv_item_tagihan_tagihan);
        }
    }

}
