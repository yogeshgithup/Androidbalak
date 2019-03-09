package nl.psdcompany.duonavigationdrawer.example;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class ChangePswd extends AppCompatActivity {

    EditText cp,cp1,cp2,cp3;
    Button cpbtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changepassword);

        cp=(EditText)findViewById(R.id.cp);
        cp1=(EditText)findViewById(R.id.cp1);
        cp2=(EditText)findViewById(R.id.cp2);
        cp3=(EditText)findViewById(R.id.cp3);
        cpbtn=(Button)findViewById(R.id.cpbtn);

        cpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JSONArray ab = new JSONArray();
                JSONObject obj = new JSONObject();

                try{
                    obj.put("email",cp.getText().toString());
                    obj.put("password",cp1.getText().toString());
                    obj.put("newpassword",cp2.getText().toString());
                    obj.put("reenterpswd",cp3.getText().toString());

                    ab.put(obj);
                    Log.d("135", ab.toString());

                    MyTask1 mt1=new MyTask1();
                    mt1.execute("http://192.168.1.42:8080/GETSWEB/SerChangePassword",ab.toString());



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private class MyTask1 extends AsyncTask<String, String, String> {


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
            Toast.makeText(ChangePswd.this, output, Toast.LENGTH_SHORT).show();
            String n="get is called";
            Log.d("error113",n);

        }


    }


}

