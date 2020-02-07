package com.example.siair.ui.pelanggan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.siair.R;
import com.example.siair.adapter.TagihanAdapter;
import com.example.siair.model.Tagihan;
import com.example.siair.model.User;
import com.example.siair.ui.AuthActivity;
import com.example.siair.viewmodel.PelangganViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String rekening;
    TextView tvTagihan;
    RecyclerView rvLaporanPelanggan;
    ProgressBar pbTagihan, pbLaporan;
    PelangganViewModel pelangganViewModel;
    TagihanAdapter tagihanAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pelanggan);

        String dataLogin = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE).getString("DATA_LOGIN", null);
        User user = new Gson().fromJson(dataLogin, User.class);

        if (dataLogin == null) {
            Intent toLogin = new Intent(this, AuthActivity.class);
            startActivity(toLogin);
            finish();
        } else {
            if (user.getStatus().equals("1")) {
                Intent toAdmin = new Intent(this, com.example.siair.ui.admin.MainActivity.class);
                startActivity(toAdmin);
                finish();
            } else {
                rekening = user.getUsername();

                tvTagihan = (TextView) findViewById(R.id.tv_tagihan_pelanggan);
                rvLaporanPelanggan = (RecyclerView) findViewById(R.id.rv_laporan_pelanggan);
                pbTagihan = (ProgressBar) findViewById(R.id.pb_tagihan_pelanggan);
                pbLaporan = (ProgressBar) findViewById(R.id.pb_laporan_pelanggan);

                ActionBar actionBar = getSupportActionBar();
                actionBar.setTitle(user.getName() + " - " + user.getUsername());

                pelangganViewModel = ViewModelProviders.of(this).get(PelangganViewModel.class);

                pelangganViewModel.getIsTagihanLoading().observe(this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        if (aBoolean) {
                            tagihanLoadingShow();
                        } else {
                            tagihanLoadingHide();
                        }
                    }
                });

                pelangganViewModel.getIsLaporanLoading().observe(this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        if (aBoolean) {
                            laporanLoadingShow();
                        } else {
                            laporanLoadingHide();
                        }
                    }
                });

                pelangganViewModel.getTagihane().observe(this, new Observer<Tagihan>() {
                    @Override
                    public void onChanged(Tagihan tagihan) {
                        if (tagihan != null) {
                            tagihanSuccess("Volume " + tagihan.getVolume() + ", Meteran tertulis " + tagihan.getMeteranBaru() + ", Yang harus dibayarkan Rp. " + tagihan.getTagihan() + " ,- " );
                        } else {
                            tagihanError();
                        }
                    }
                });

                pelangganViewModel.getLaporan().observe(this, new Observer<ArrayList<Tagihan>>() {
                    @Override
                    public void onChanged(ArrayList<Tagihan> tagihans) {
                        if (tagihans != null) {
                            laporanSuccess(tagihans);
                        } else {
                            laporanError();
                        }
                    }
                });

                pelangganViewModel.getIdPelanggan().observe(this, new Observer<Integer>() {
                    @Override
                    public void onChanged(Integer integer) {
                        if (integer != null) {
                            initOther();
                        }
                    }
                });

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                rvLaporanPelanggan.setLayoutManager(layoutManager);
                tagihanAdapter = new TagihanAdapter(getApplicationContext(), true);

                initView();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu_pelanggan, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_pelanggan_logout:
                logout();
                return true;
        }

        return false;
    }

    private void initView () {
        pelangganViewModel.setIdPelanggan(rekening);
    }

    private void initOther () {
        String id = String.valueOf(pelangganViewModel.getIdPelangganValue());
        pelangganViewModel.setTagihanPelanggan(id);
        pelangganViewModel.setLaporanPelanggan(id);
    }

    private void tagihanLoadingShow () {
        pbTagihan.setVisibility(View.VISIBLE);
    }

    private void tagihanLoadingHide () {
        pbTagihan.setVisibility(View.GONE);
    }

    private void laporanLoadingShow () {
        pbLaporan.setVisibility(View.VISIBLE);
    }

    private void laporanLoadingHide () {
        pbLaporan.setVisibility(View.GONE);
    }

    private void tagihanSuccess (String tagihan) {
        tvTagihan.setText(tagihan);
    }

    private void tagihanError () {
//        Toast.makeText(this, "Tagihan Error", Toast.LENGTH_SHORT).show();
    }

    private void laporanSuccess (ArrayList<Tagihan> tagihans) {
        tagihanAdapter.setTagihan(tagihans);
        rvLaporanPelanggan.setAdapter(tagihanAdapter);
    }

    private void laporanError () {
        Toast.makeText(this, "Laporan Error", Toast.LENGTH_SHORT).show();
    }

    private void logout () {
        SharedPreferences.Editor sharedEditor = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE).edit();
        sharedEditor.remove("DATA_LOGIN");
        sharedEditor.apply();
        Intent toLogin = new Intent(this, AuthActivity.class);
        startActivity(toLogin);
        finish();
    }

}
