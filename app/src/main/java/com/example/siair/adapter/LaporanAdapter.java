package com.example.siair.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.siair.R;
import com.example.siair.model.Laporan;

import java.util.ArrayList;

public class LaporanAdapter extends RecyclerView.Adapter<LaporanAdapter.LaporanViewHolder> {

    ArrayList<Laporan> laporans;

    public LaporanAdapter() {
        this.laporans = new ArrayList<Laporan>();
    }

    public LaporanAdapter(ArrayList<Laporan> laporans) {
        this.laporans = laporans;
    }

    public void setLaporans(ArrayList<Laporan> laporans) {
        this.laporans = laporans;
    }

    @NonNull
    @Override
    public LaporanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_laporan, parent, false);
        return new LaporanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LaporanViewHolder holder, int position) {
        holder.tvNama.setText(laporans.get(position).getPelanggan().getNama());
        holder.tvRekening.setText(laporans.get(position).getPelanggan().getRekening() + " - " + "Volume " + laporans.get(position).getTagihan().getVolume() + ", Meteran tertulis " + laporans.get(position).getTagihan().getMeteranBaru());
        holder.tvTagihan.setText("Lunas Rp. " + laporans.get(position).getTagihan().getTagihan() + " ,-");
        holder.tvTanggal.setText(String.valueOf(laporans.get(position).getTanggalTransaksi()));
    }

    @Override
    public int getItemCount() {
        return laporans.size();
    }

    class LaporanViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNama, tvRekening, tvTagihan, tvTanggal;

        public LaporanViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = (TextView) itemView.findViewById(R.id.tv_item_laporan_nama);
            tvRekening = (TextView) itemView.findViewById(R.id.tv_item_laporan_rekening);
            tvTagihan = (TextView) itemView.findViewById(R.id.tv_item_laporan_tagihan);
            tvTanggal = (TextView) itemView.findViewById(R.id.tv_item_laporan_tanggal);
        }
    }

}
