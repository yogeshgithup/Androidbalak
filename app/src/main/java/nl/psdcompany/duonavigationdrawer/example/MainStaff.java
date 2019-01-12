package nl.psdcompany.duonavigationdrawer.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import nl.psdcompany.duonavigationdrawer.views.DuoDrawerLayout;
import nl.psdcompany.duonavigationdrawer.views.DuoMenuView;
import nl.psdcompany.duonavigationdrawer.widgets.DuoDrawerToggle;

import static nl.psdcompany.duonavigationdrawer.example.R.array.menuOptions2;

public class MainStaff extends AppCompatActivity implements DuoMenuView.OnMenuClickListener {
    private MenuAdapter mMenuAdapter;
    private ViewHolder mViewHolder;

    private ArrayList<String> mTitles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);
        mTitles = new ArrayList<>(Arrays.asList(getResources().getStringArray(menuOptions2)));

        // Initialize the views
        mViewHolder = new ViewHolder();

        // Handle toolbar actions
        handleToolbar();

        // Handle menu actions
        handleMenu();

        // Handle drawer actions
        handleDrawer();

        // Show main fragment in container
        goToFragment(new Course_details(), false);
        mMenuAdapter.setViewSelected(0, true);
        setTitle(mTitles.get(0));

    }

    private void handleToolbar() {
        setSupportActionBar(mViewHolder.mToolbar);
    }

    private void handleDrawer() {
        DuoDrawerToggle duoDrawerToggle = new DuoDrawerToggle(this,
                mViewHolder.mDuoDrawerLayout,
                mViewHolder.mToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        mViewHolder.mDuoDrawerLayout.setDrawerListener(duoDrawerToggle);
        duoDrawerToggle.syncState();

    }

    private void handleMenu() {
        mMenuAdapter = new MenuAdapter(mTitles);

        mViewHolder.mDuoMenuView.setOnMenuClickListener(this);
        mViewHolder.mDuoMenuView.setAdapter(mMenuAdapter);
    }

    @Override
    public boolean onFooterClicked() {

        startActivity(new Intent(this, Homepage.class));
        return true;
        // Toast.makeText(this, "onFooterClicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onHeaderClicked() {

        Toast.makeText(this, "onHeaderClicked", Toast.LENGTH_SHORT).show();
        return false;
    }

    private void goToFragment(Fragment fragment, boolean addToBackStack) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.container,(android.support.v4.app.Fragment) fragment).commit();

    }

    @Override
    public void onOptionClicked(int position, Object objectClicked) {
        // Set the toolbar title
        setTitle(mTitles.get(position));

        // Set the right options selected
        mMenuAdapter.setViewSelected(position, true);
        // Navigate to the right fragment


        switch (position) {
            case 0:
                goToFragment(new Material_upload(),false);
                break;
            case 1:
                goToFragment(new Working_Schedule(),false);
                break;
            case 2:
                goToFragment(new Feedback(),false);
                break;
            default:
                goToFragment(new Material_upload(), false);
                break;


        }
        mViewHolder.mDuoDrawerLayout.closeDrawer();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menus, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_email) {
            startActivity(new Intent(this, StaffEmail.class));
            return true;
        }
        if (id == R.id.action_changepswd) {
            startActivity(new Intent(this, ChangePswd.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private class ViewHolder {
        private DuoDrawerLayout mDuoDrawerLayout;
        private DuoMenuView mDuoMenuView;
        private Toolbar mToolbar;

        ViewHolder() {
            mDuoDrawerLayout = (DuoDrawerLayout) findViewById(R.id.drawer);
            mDuoMenuView = (DuoMenuView) mDuoDrawerLayout.getMenuView();
            mToolbar = (Toolbar) findViewById(R.id.toolbar);
        }
    }
}

