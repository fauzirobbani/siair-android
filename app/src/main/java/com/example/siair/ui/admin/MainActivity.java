package com.example.siair.ui.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

        String dataLogin = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE).getString("DATA_LOGIN", null);

        if (dataLogin == null) {
            Intent toLogin = new Intent(this, AuthActivity.class);
            startActivity(toLogin);
            finish();
        }

        navigation = findViewById(R.id.bn_admin);
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        loadFragment(new TagihanFragment());
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
                case R.id.navigation_logout:
                    logout();
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
