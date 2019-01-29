package men.ngopi.sans.myinstaapp;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import men.ngopi.sans.myinstaapp.fragments.FragmentHome;

import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private int selectedFragmentId = R.id.navigation_home;
    private Fragment selectedFragment = null;
    private BottomNavigationView bottomNavigationView;
    private static MainActivity instance;

    public static MainActivity getInstance() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instance = this;

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setItemIconSize(100);
        bottomNavigationView.setOnNavigationItemSelectedListener((item) -> {
            Log.d("ke sini kan", "" + item.getItemId());
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    this.selectedFragment = FragmentHome.newInstance();
                    this.selectedFragmentId = R.id.navigation_home;
                case R.id.navigation_add:
                    return false;
                case R.id.navigation_account:
//                    mTextMessage.setText(R.string.title_notifications);
            }

            Log.d("selected Fragment id", "" + selectedFragmentId);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.screen_display, selectedFragment);
            transaction.commit();

            return true;
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        switch (selectedFragmentId) {
            case R.id.navigation_home: {
                bottomNavigationView.setSelectedItemId(R.id.navigation_home);
                break;
            }
        }
    }

}
