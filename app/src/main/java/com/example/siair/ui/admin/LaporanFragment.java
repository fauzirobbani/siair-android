package com.example.siair.ui.admin;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.siair.R;
import com.example.siair.adapter.LaporanAdapter;
import com.example.siair.model.Laporan;
import com.example.siair.viewmodel.LaporanViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class LaporanFragment extends Fragment {

    LaporanViewModel laporanViewModel;
    RecyclerView rvLaporan;
    LaporanAdapter laporanAdapter;
    ProgressBar pbLaporanAll;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_admin_laporan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvLaporan = (RecyclerView) view.findViewById(R.id.rv_laporan);
        pbLaporanAll = (ProgressBar) view.findViewById(R.id.pb_laporan_all);

        laporanViewModel = ViewModelProviders.of(this).get(LaporanViewModel.class);

        laporanViewModel.getIsLoading().observe(this, new Observer<Boolean>(){
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    showLoading();
                } else {
                    hideLoading();
                }
            }
        });

        laporanViewModel.getLaporanAll().observe(this, new Observer<ArrayList<Laporan>>() {
            @Override
            public void onChanged(ArrayList<Laporan> laporans) {
                if (laporans != null) {
                    getLaporanSuccess(laporans);
                } else {
                    getLaporanError();
                }
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        rvLaporan.setLayoutManager(layoutManager);
        laporanAdapter = new LaporanAdapter();

        initView();
    }

    private void initView () {
        laporanViewModel.setLaporanAll();
    }

    private void getLaporanSuccess (ArrayList<Laporan> laporans) {
        laporanAdapter.setLaporans(laporans);
        rvLaporan.setAdapter(laporanAdapter);
    }

    private void getLaporanError () {
        Toast.makeText(getActivity().getApplicationContext(), "Laporan Error", Toast.LENGTH_SHORT).show();
    }

    private void showLoading () {
        pbLaporanAll.setVisibility(View.VISIBLE);
    }

    private void hideLoading () {
        pbLaporanAll.setVisibility(View.GONE);
    }
}
