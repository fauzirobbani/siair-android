package com.example.siair.ui.admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.siair.R;
import com.example.siair.model.Pelanggan;
import com.example.siair.model.TagihanRequestBody;
import com.example.siair.viewmodel.TambahTagihanViewModel;

import java.util.ArrayList;

public class TambahTagihanActivity extends AppCompatActivity {

    TambahTagihanViewModel tambahTagihanViewModel;
    ProgressBar pbPelangganAll;
    Spinner spinnerPelanggan;
    EditText etMeteranbaru;
    Button btnTambahTagihan;
    AlertDialog.Builder dialog;
    View dialogView;
    LayoutInflater infater;
    TextView tvRekening, tvNama, tvAlamat, tvHp, tvMeteranLama, getTvMeteranBaru, tvVolume, tvHarga, tvBeban, tvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_tagihan);

        pbPelangganAll = (ProgressBar) findViewById(R.id.pb_tambah_tagihan);
        spinnerPelanggan = (Spinner) findViewById(R.id.sp_tambah_tagihan_pelanggan);
        etMeteranbaru = (EditText) findViewById(R.id.et_tambah_tagihan_meteran_baru);
        btnTambahTagihan = (Button) findViewById(R.id.btn_tambah_tagihan);
        tambahTagihanViewModel = ViewModelProviders.of(this).get(TambahTagihanViewModel.class);

        btnTambahTagihan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etMeteranbaru.getText().toString().matches("")) {
                    Toast.makeText(getApplicationContext(), "Silahkan Isi Meteran Baru" , Toast.LENGTH_SHORT).show();
                } else {
                    if (tambahTagihanViewModel.getPelangganValue() != null) {
                        showDialog();
                    } else {
                        Toast.makeText(getApplicationContext(), "Sedang memuat data" , Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        tambahTagihanViewModel.getPelangganAll().observe(this, new Observer<ArrayList<Pelanggan>>() {
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

        tambahTagihanViewModel.getIsSending().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    showLoading();
                } else {
                    hideLoading();
                }
            }
        });

        tambahTagihanViewModel.getIsSuccessSending().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Error sending data" , Toast.LENGTH_SHORT).show();
                }
            }
        });

        initView();
    }

    private void initView () {
        tambahTagihanViewModel.setPelangganAll();
    }

    private void showDialog () {
        Pelanggan pelanggan = tambahTagihanViewModel.getPelangganValue();
        int meteranBaru = Integer.parseInt(etMeteranbaru.getText().toString());
        int volume = meteranBaru - pelanggan.getMeteran();
        int harga = 3000;
        int beban = 4000;
        int total = (volume * harga) + beban;

        infater = getLayoutInflater();
        dialogView = infater.inflate(R.layout.dialog_tambah_tagihan, null);
        dialog = new AlertDialog.Builder(TambahTagihanActivity.this);
        dialog.setCancelable(true);
        dialog.setTitle("TAGIHAN BARU");
        dialog.setView(dialogView);

        tvNama = (TextView) dialogView.findViewById(R.id.dialog_tambah_tagihan_tv_nama);
        tvRekening = (TextView) dialogView.findViewById(R.id.dialog_tambah_tagihan_tv_rekening);
        tvAlamat = (TextView) dialogView.findViewById(R.id.dialog_tambah_tagihan_tv_alamat);
        tvHp = (TextView) dialogView.findViewById(R.id.dialog_tambah_tagihan_tv_hp);
        tvMeteranLama = (TextView) dialogView.findViewById(R.id.dialog_tambah_tagihan_tv_meteran_lama);
        getTvMeteranBaru = (TextView) dialogView.findViewById(R.id.dialog_tambah_tagihan_tv_meteran_baru);
        tvVolume = (TextView) dialogView.findViewById(R.id.dialog_tambah_tagihan_tv_volume);
        tvHarga = (TextView) dialogView.findViewById(R.id.dialog_tambah_tagihan_tv_harga);
        tvBeban = (TextView) dialogView.findViewById(R.id.dialog_tambah_tagihan_tv_beban);
        tvTotal = (TextView) dialogView.findViewById(R.id.dialog_tambah_tagihan_tv_total);

        tvNama.setText("Nama : " + pelanggan.getNama());
        tvRekening.setText("Nomor Rekening : " + pelanggan.getRekening());
        tvAlamat.setText("Alamat : " +pelanggan.getAlamat());
        tvHp.setText("Nomor Hp : " + pelanggan.getHp());
        tvMeteranLama.setText("Meteran Lama : " + pelanggan.getMeteran());
        getTvMeteranBaru.setText("Meteran baru : " + etMeteranbaru.getText().toString());
        tvVolume.setText("Volume : " + volume);
        tvHarga.setText("Harga : Rp. " + harga + " ,-");
        tvBeban.setText("Beban : Rp. " + beban + " ,-");
        tvTotal.setText("Total : Rp. " + total + " ,-");

        final TagihanRequestBody tagihanRequestBody = new TagihanRequestBody(
                pelanggan.getRekening(),
                meteranBaru,
                volume,
                total
        );

        dialog.setPositiveButton("TAMBAH TAGIHAN", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tambahTagihanViewModel.storeTagihan(tagihanRequestBody);
            }
        });

        dialog.setNegativeButton("BATAL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void getPelangganSuccess (final ArrayList<Pelanggan> pelanggans) {
        ArrayAdapter<Pelanggan> adapter = new ArrayAdapter<Pelanggan>(this, android.R.layout.simple_spinner_dropdown_item, pelanggans) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                TextView label = (TextView) super.getView(position, convertView, parent);
                label.setText(pelanggans.get(position).getNama() + " - " + pelanggans.get(position).getRekening() + " - " + pelanggans.get(position).getMeteran());
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
        spinnerPelanggan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tambahTagihanViewModel.setPelanggan(String.valueOf(pelanggans.get(position).getId()));
//                Toast.makeText(getApplicationContext(), "" + pelanggans.get(position).getRekening(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void getPelangganError () {
        // Notigy some error here
    }

    private void showLoading () {
        pbPelangganAll.setVisibility(View.VISIBLE);
    }

    private void hideLoading () {
        pbPelangganAll.setVisibility(View.GONE);
    }
}
