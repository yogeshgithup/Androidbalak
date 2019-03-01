package nl.psdcompany.duonavigationdrawer.example;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import android.content.Intent;

public class AllBatch extends AppCompatActivity {

    Button sendd;
    TextView tvb1;
    EditText edtb1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allbatch);
        tvb1 = (TextView) findViewById(R.id.tvb1);
        edtb1 = (EditText) findViewById(R.id.edtb1);
        sendd = (Button) findViewById(R.id.sendd);



        sendd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JSONArray ab = new JSONArray();
                JSONObject obj = new JSONObject();

                try {
                    obj.put("Message",edtb1.getText().toString());

                    ab.put(obj);
                    Log.d("135", ab.toString());


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                MyTask1 mt1=new MyTask1();

                mt1.execute("http://192.168.1.72:8080/GETSWEB/SerAllBatchAndroid",ab.toString());

                

            }
        });
    }

    class MyTask1 extends AsyncTask<String, String, String> {


        public String doInBackground(String... params) {
            String resp = null;

            String weburl=params[0];

            StringBuffer output=new StringBuffer();
            try {
                InputStream stream = null;
                // start of code of connnetion
                weburl=weburl+"?data="+params[1];
                Log.d("url",weburl);
                URL url = new URL(weburl);
                URLConnection connection = url.openConnection();

                String sd = "abcbjj";
                Log.d("error41",sd);
                HttpURLConnection httpConnection = (HttpURLConnection) connection;
                String sm = "bnhjbc";
                Log.d("error42",sm);

                httpConnection.setRequestMethod("GET");
                String sg = "abcggh";
                Log.d("error43",sg);
                httpConnection.connect();
                String sk = "abcmj";
                Log.d("error44",sk);
                stream = httpConnection.getInputStream();
                String sl = "abckkii";
                Log.d("error46",sl);
                BufferedReader buffer = new BufferedReader(new InputStreamReader(stream));
                String s = "abc";
                Log.d("error45",s);
                while ((s = buffer.readLine()) != null)
                    output.append(s);
            } catch (Exception e) {
                Log.e("error62",e.getMessage());

            }
            return output.toString();


        }



        protected void onPostExecute(String output) {
            Toast.makeText(AllBatch.this, output, Toast.LENGTH_SHORT).show();
            String n="get is called";
            Log.d("149",n);

        }


    }
}

