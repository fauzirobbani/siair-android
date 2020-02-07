package com.example.siair.ui.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.siair.R;
import com.example.siair.ui.AuthActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView navigation;
    Fragment tagihanFragment = new TagihanFragment();
    Fragment laporanFragment = new LaporanFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);

        navigation = findViewById(R.id.bn_admin);
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        loadFragment(tagihanFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.option_menu_tambah_tagihan:
                Intent toTambahTagihan = new Intent(this, TambahTagihanActivity.class);
                startActivity(toTambahTagihan);
                return true;
            case R.id.option_menu_logout:
                logout();
                return true;
        }

        return false;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.navigation_tagihan:
                    loadFragment(tagihanFragment);
                    return true;
                case R.id.navigation_laporan:
                    loadFragment(laporanFragment);
                    return true;
            }

            return false;
        }
    };

    private void loadFragment (Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_admin_container, fragment);
        transaction.commit();
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
