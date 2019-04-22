package nl.psdcompany.duonavigationdrawer.example;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;

import nl.psdcompany.duonavigationdrawer.views.DuoDrawerLayout;
import nl.psdcompany.duonavigationdrawer.views.DuoMenuView;
import nl.psdcompany.duonavigationdrawer.widgets.DuoDrawerToggle;

import static nl.psdcompany.duonavigationdrawer.example.R.array.menuOptions;

public class MainActivity extends AppCompatActivity implements DuoMenuView.OnMenuClickListener {
    private MenuAdapter mMenuAdapter;
    private ViewHolder mViewHolder;

    private ArrayList<String> mTitles = new ArrayList<>();

   private static final int MEGABYTE= 1024 * 1024;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTitles = new ArrayList<>(Arrays.asList(getResources().getStringArray(menuOptions)));

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

        startActivity(new Intent(this, HomePage.class));
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
        getSupportFragmentManager().beginTransaction().replace(R.id.container, (android.support.v4.app.Fragment) fragment).commit();

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
                goToFragment(new Course_details(), false);
                break;
            case 1:
                goToFragment(new Fee_status(), false);
                break;
            case 2:
                goToFragment(new Student_material(), false);
                break;
            case 3:
                goToFragment(new Timetable(), false);
                break;
            case 4:
                goToFragment(new Report(), false);
                break;
            case 5:
                goToFragment(new Feedback(), false);
                break;
            default:
                goToFragment(new Course_details(), false);
                break;


        }
        mViewHolder.mDuoDrawerLayout.closeDrawer();
    }

    @Override
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

            Intent iii = getIntent();
            String u = iii.getStringExtra("Username");

            iii = new Intent(MainActivity.this, StaffEmail.class);
            iii.putExtra("Username", u);
            startActivity(iii);
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


    public void MyDownload(View imageButton) {

        ImageButton imgbtn = (ImageButton) imageButton;

        TableLayout tl = this.findViewById(R.id.tablelayout);
        Log.d("count", "" + tl.getChildCount());
        String type = "", title = "";
        String course = "";
        String subject = "",filepath="";
        for (int i = 1; i < tl.getChildCount(); i++) {
            TableRow tb = (TableRow) tl.getChildAt(i);
            ImageButton ib = (ImageButton) tb.getChildAt(tb.getChildCount() - 1);
            if (imgbtn == ib) {
                TextView filepathh=(TextView)tb.getChildAt(4);
                TextView ettype = (TextView) tb.getChildAt(3);
                TextView ettitle = (TextView) tb.getChildAt(2);
                TextView tvcourse = (TextView) tb.getChildAt(0);
                TextView tvsubject = (TextView) tb.getChildAt(1);


                filepath=filepathh.getText().toString();
                type = ettype.getText().toString();
                title = ettitle.getText().toString();
                course = tvcourse.getText().toString();
                subject = tvsubject.getText().toString();


            }
        }
        Log.d("title", title);
        Log.d("type", type);
        Log.d("c_name", course);
        Log.d("sub_name", subject);
        Log.d("filepath",filepath);



        MyTask1 mt1 = new MyTask1();
        mt1.execute( title,filepath);

    }


    private class MyTask1 extends AsyncTask<String, String, String> {


        public String doInBackground(String... params) {

            String title = params[0];
            String filepath=params[1];

            Log.d("titleeee", title);
            Log.d("pathh",filepath);

            try {
                File folder = new File(Environment.getExternalStorageDirectory() + "/myFolder");
                boolean success = true;
                if (!folder.exists()) {
                    success = folder.mkdir();
                }
                if (success) {
                    // Do something on success
                } else {
                    // Do something else on failure
                }
                Log.d("folder", folder.getAbsolutePath());



                int index=filepath.indexOf("&");

                String  file=filepath.substring(0,index);
                 String extension=filepath.substring(index+1);

                 Log.d("244sdf",""+extension);
                 Log.d("249ssdf",""+file);




                URL url = new URL(file);
                Log.d("urlwer", "" + url);

                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.connect();

                // this will be useful so that you can show a tipical 0-100% progress bar

                int lenghtOfFile = connection.getContentLength();
                Log.d("length", "" + lenghtOfFile);

                // download the file
                InputStream input = connection.getInputStream();

                String fileName = title;


                String path = folder.getAbsolutePath() + "/" + fileName+extension;
                Log.i("ExternalStorageDemo", "Save to: " + path);

                File myFile = new File(path);
                myFile.createNewFile();

                FileOutputStream fOut = new FileOutputStream(path);


                byte[] buffer = new byte[MEGABYTE];
                int bufferlength=0;
                while ((bufferlength = input.read(buffer))>0)
                    fOut.write(buffer,0,bufferlength);

                // flushing output
                fOut.flush();
                fOut.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return null;
        }


        protected void onPostExecute(String s) {
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), " saved", Toast.LENGTH_LONG).show();
            String n = "get is called";
            Log.d("149line", n);

        }
    }
}
