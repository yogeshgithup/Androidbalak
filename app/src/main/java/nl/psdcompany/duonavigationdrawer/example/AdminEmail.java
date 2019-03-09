package nl.psdcompany.duonavigationdrawer.example;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class AdminEmail extends AppCompatActivity {

    TextView tvd1,tvd2,tvd3,tvd4;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminemail);
        tvd1 = (TextView) findViewById(R.id.tvd1);
        tvd2 = (TextView) findViewById(R.id.tvd2);
        tvd3 = (TextView) findViewById(R.id.tvd3);
        tvd4 = (TextView) findViewById(R.id.tvd4);
    //    String username;
        String n;
        Intent intent;
        intent=getIntent();
        n=intent.getStringExtra("Username");


        JSONArray ab = new JSONArray();
        JSONObject obj = new JSONObject();
        try
        {
            obj.put("Username",n);
            ab.put(obj);
            Log.d("135", ab.toString());
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        MyTask1 mt1 = new MyTask1();
        mt1.execute("http://192.168.1.42:8080/GETSWEB/SerDetailsAndroid",ab.toString());

    }

    private class MyTask1 extends AsyncTask<String, String, String> {


        ProgressDialog pd=null;
        public String doInBackground(String... params) {
            String resp = null;

            String weburl = params[0];

            StringBuffer output = new StringBuffer();
            try {
                InputStream stream = null;
                // start of code of connnetion
             weburl = weburl + "?data=" + params[1];
                Log.d("url", weburl);
                URL url = new URL(weburl);
                URLConnection connection = url.openConnection();

                String sd = "abcbjj";
                Log.d("error41", sd);
                HttpURLConnection httpConnection = (HttpURLConnection) connection;
                String sm = "bnhjbc";
                Log.d("error42", sm);
                httpConnection.setRequestMethod("GET");
                String sg = "abcggh";
                Log.d("error43", sg);
                httpConnection.connect();
                String sk = "abcmj";
                Log.d("error44", sk);
                stream = httpConnection.getInputStream();
                String sl = "abckkii";
                Log.d("error46", sl);
                BufferedReader buffer = new BufferedReader(new InputStreamReader(stream));
                String s = "abc";
                Log.d("error45", s);
                while ((s = buffer.readLine()) != null)
                    output.append(s);
            } catch (Exception e) {
                Log.e("error62", e.getMessage());

            }
            String sl = "abckkii";
            Log.d("error46", sl);
            return output.toString();

        }
        protected void onPostExecute(String output) {

            String slo = "abckkii";
            Log.d("error46", slo);
            //Log.d("error113",n)
            JSONTokener js=new JSONTokener(output);
            String slv = "abckkiihjj";
            Log.d("error46", slv);
            try {
                JSONArray ab = new JSONArray(output);

                JSONObject obj = ab.getJSONObject(0);

                tvd1.setText(obj.getString("f_name"));
                tvd2.setText(obj.getString("contact_no"));
               tvd3.setText(obj.getString("email"));
                tvd4.setText(obj.getString("address"));
                Log.d("115",js.toString());



            } catch (JSONException e) {
                e.printStackTrace();
            }


            pd.dismiss();
        }

        @Override
        protected void onPreExecute() {
            pd=new ProgressDialog(AdminEmail.this);
            pd.setIndeterminate(true);
            pd.setMessage("Loading...");
            pd.setCancelable(false);
            pd.setTitle("Please Wait");
            pd.show();

            super.onPreExecute();
        }
    }
}
