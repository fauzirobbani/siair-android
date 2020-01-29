package com.example.siair.ui.admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.siair.R;
import com.example.siair.model.Pelanggan;
import com.example.siair.viewmodel.TambahTagihanViewModel;

import java.util.ArrayList;

public class TambahTagihanActivity extends AppCompatActivity {

    ProgressBar pbPelangganAll;
    TambahTagihanViewModel tambahTagihanViewModel;
    Spinner spinnerPelanggan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_tagihan);

        pbPelangganAll = (ProgressBar) findViewById(R.id.pb_tambah_tagihan);
        spinnerPelanggan = (Spinner) findViewById(R.id.sp_tambah_tagihan_pelanggan);
        tambahTagihanViewModel = ViewModelProviders.of(this).get(TambahTagihanViewModel.class);

        tambahTagihanViewModel.getLaporanAll().observe(this, new Observer<ArrayList<Pelanggan>>() {
            @Override
            public void onChanged(ArrayList<Pelanggan> pelanggans) {
                if (pelanggans != null) {
                    getPelangganSuccess(pelanggans);
                } else {
                    getPelangganError();
                }
            }
        });

        tambahTagihanViewModel.getIsLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    showLoading();
                } else {
                    hideLoading();
                }
            }
        });

        initView();
    }

    private void initView () {
        tambahTagihanViewModel.setPelangganAll();
    }

    private void getPelangganSuccess (final ArrayList<Pelanggan> pelanggans) {
        ArrayAdapter<Pelanggan> adapter = new ArrayAdapter<Pelanggan>(this, android.R.layout.simple_spinner_dropdown_item, pelanggans) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                TextView label = (TextView) super.getView(position, convertView, parent);
                label.setText(pelanggans.get(position).getNama() + " - " + pelanggans.get(position).getRekening());
                return label;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                TextView label = (TextView) super.getDropDownView(position, convertView, parent);
                label.setText(pelanggans.get(position).getNama() + " - " + pelanggans.get(position).getRekening());
                return label;
            }
        };
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPelanggan.setAdapter(adapter);
    }

    private void getPelangganError () {
        Toast.makeText(this, "Pelanggan Error", Toast.LENGTH_SHORT).show();
    }

    private void showLoading () {
        pbPelangganAll.setVisibility(View.VISIBLE);
    }

    private void hideLoading () {
        pbPelangganAll.setVisibility(View.GONE);
    }
}
