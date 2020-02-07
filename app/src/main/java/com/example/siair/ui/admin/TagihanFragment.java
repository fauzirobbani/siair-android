package com.example.siair.ui.admin;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.siair.R;
import com.example.siair.adapter.TagihanAdapter;
import com.example.siair.model.Tagihan;
import com.example.siair.viewmodel.TagihanViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TagihanFragment extends Fragment {

    TagihanViewModel tagihanViewModel;
    RecyclerView rvTagihan;
    TagihanAdapter tagihanAdapter;
    ProgressBar pbTagihanAll;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_admin_tagihan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvTagihan = (RecyclerView) view.findViewById(R.id.rv_tagihan);
        pbTagihanAll = (ProgressBar) view.findViewById(R.id.pb_tagihan_all);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_tagihan);

        tagihanViewModel = ViewModelProviders.of(this).get(TagihanViewModel.class);

        tagihanViewModel.getIsLoading().observe(this, new Observer<Boolean>(){
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    showLoading();
                } else {
                    hideLoading();
                }
            }
        });

        tagihanViewModel.getTagihanAll().observe(this, new Observer<ArrayList<Tagihan>>() {
            @Override
            public void onChanged(ArrayList<Tagihan> tagihans) {
                if (tagihans != null) {
                    getTagihanSuccess(tagihans);
                } else {
                    getTagihanError();
                }
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getTagihan();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        rvTagihan.setLayoutManager(layoutManager);
        tagihanAdapter = new TagihanAdapter(getActivity().getApplicationContext(), false);

        getTagihan();
    }

    private void getTagihan () {
        tagihanViewModel.setTagihanAll();
    }

    private void getTagihanSuccess (ArrayList<Tagihan> tagihans) {
        tagihanAdapter.setTagihan(tagihans);
        rvTagihan.setAdapter(tagihanAdapter);
    }

    private void getTagihanError () {
        Toast.makeText(getActivity().getApplicationContext(), "Tagihan Error", Toast.LENGTH_SHORT).show();
    }

    private void showLoading () {
        rvTagihan.setVisibility(View.GONE);
        pbTagihanAll.setVisibility(View.VISIBLE);
    }

    private void hideLoading () {
        pbTagihanAll.setVisibility(View.GONE);
        rvTagihan.setVisibility(View.VISIBLE);
    }
}
