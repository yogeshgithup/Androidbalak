package nl.psdcompany.duonavigationdrawer.example;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nl.psdcompany.duonavigationdrawer.views.DuoDrawerLayout;
import nl.psdcompany.duonavigationdrawer.views.DuoMenuView;
import nl.psdcompany.duonavigationdrawer.widgets.DuoDrawerToggle;

import static nl.psdcompany.duonavigationdrawer.example.R.array.menuOptions1;

public class MainAdmin extends AppCompatActivity implements DuoMenuView.OnMenuClickListener {
    private static final int REQUEST_CODE_DOC = 1;
    private MenuAdapter mMenuAdapter;
    private ViewHolder mViewHolder;

    private ArrayList<String> mTitles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        mTitles = new ArrayList<>(Arrays.asList(getResources().getStringArray(menuOptions1)));

        // Initialize the views
        mViewHolder = new ViewHolder();

        // Handle toolbar actions
        handleToolbar();

        // Handle menu actions
        handleMenu();

        // Handle drawer actions
        handleDrawer();

        // Show main fragment in container
        goToFragment(new Material_upload(), false);
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
                goToFragment(new Material_upload(), false);

                break;
            case 1:
                goToFragment(new Send_notification(), false);
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
            Intent i = getIntent();
            String u = i.getStringExtra("Username");

            i = new Intent(MainAdmin.this, AdminEmail.class);
            i.putExtra("Username", u);
            startActivity(i);

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


    public void MyClick (View imageButton) {

        Toast.makeText(MainAdmin.this,"clicked",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent, 7);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub

        switch (requestCode) {
            case 7:
                if (resultCode == RESULT_OK) {
                    String path = data.getData().getPath();


                    File uploadFile1= new File(this.getFilesDir()+path);
                 //   Log.d("198line",path+"  "+uploadFile1.exists()+"");

                       Toast.makeText(MainAdmin.this, path, Toast.LENGTH_LONG).show();

                    MyTask1 mt1 = new MyTask1();
                    mt1.execute("http://192.168.1.27:8080/GETSWEB/AndroidDownload",path);
                }
                break;
        }
    }

    private class MyTask1 extends AsyncTask<String, String, String> {



        public String doInBackground(String... params) {
            String resp = "";

            String weburl=params[0];
            String path = params[1];
            String charset = "UTF-8";

//                File uploadFile1= new File(path);

                String filename= "123file";
                File extStore = Environment.getExternalStorageDirectory();
                // ==> /storage/emulated/0/note.txt
                String filepath = extStore.getAbsolutePath() + "/" + filename;
                Log.i("ExternalStorageDemo", "Save to: " +path);



                try {
                    int index=path.indexOf(":");
                    path=path.substring(index+1);
                    Log.d("249",""+path);
                    File uploadFile1 = new File(Environment.getExternalStorageDirectory()+"/"+path);

                   /* Log.d("exist",""+uploadFile1.getName());
                    FileInputStream fin = new FileInputStream(Environment.getExternalStorageDirectory()+"/vidhi.txt");
                    int l=fin.available();
                    byte[]filedata=new byte[l];
                    fin.read(filedata);
                    String d=new String(filedata);
                    Log.d("data123",d);

*/



                    Log.d("218line",uploadFile1.exists()+" "+path);
                       // Log.d("218line",uploadFile1.exists()+""+);
               MultipartUtility multipart = new MultipartUtility(weburl, charset);
               // multipart.addFormField("description", "documents");
                multipart.addFormField("data", "vidhi");

                multipart.addFilePart("file", uploadFile1);
                List<String> response = multipart.finish();

               // System.out.println("SERVER REPLIED:"+response.size());
                 Log.d("270 resp size",response.size()+"");

                for (String line : response) {
                    resp=resp+response;
                }
                } catch (Exception e) {
                    Log.d("error1",""+e.getMessage());
                }
            return resp;


        }


        protected void onPostExecute(String output) {
            Toast.makeText(MainAdmin.this, output, Toast.LENGTH_SHORT).show();
            String n = "get is called";
            Log.d("149line", n);
            Log.d("288line", output);


        }

    }
}

