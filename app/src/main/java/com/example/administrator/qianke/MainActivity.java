package com.example.administrator.qianke;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private Check_in_Fragment check_in_fragment;
    private Statistics_Fragment statistics_fragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.check_in:
                    if (check_in_fragment != null) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, check_in_fragment).commitAllowingStateLoss();
                    } else {
                        check_in_fragment = new Check_in_Fragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, check_in_fragment).commitAllowingStateLoss();
                    }
                    return true;
                case R.id.record:
                    if (statistics_fragment != null){
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,statistics_fragment).commitAllowingStateLoss();
                    }else {
                        statistics_fragment = new Statistics_Fragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,statistics_fragment).commitAllowingStateLoss();
                    }
                    return true;
                case R.id.setting:

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(MainActivity.this,Login_Register.class);
        startActivity(intent);

        check_in_fragment = new Check_in_Fragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, check_in_fragment).commitAllowingStateLoss();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
