package supinfo.com.supfitness;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar widgetToolbar = (Toolbar)  findViewById(R.id.widgetToolbar);
        setSupportActionBar(widgetToolbar);
        //widgetToolbar.setNavigationIcon(R.drawable.ic_launcher);

        TabLayout viewTabLayout = (TabLayout) findViewById(R.id.widgetTabLayout);
        assert viewTabLayout != null;
        viewTabLayout.addTab(viewTabLayout.newTab().setText("Weight"));
        viewTabLayout.addTab(viewTabLayout.newTab().setText("Weight Curve"));
        viewTabLayout.addTab(viewTabLayout.newTab().setText("Foot Races"));
        viewTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        final TabAdapter adapter = new TabAdapter(getSupportFragmentManager(), viewTabLayout.getTabCount());
        assert viewPager != null;
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(viewTabLayout));
        viewTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return id == R.id.widgetToolbar || super.onOptionsItemSelected(item);
    }
}
