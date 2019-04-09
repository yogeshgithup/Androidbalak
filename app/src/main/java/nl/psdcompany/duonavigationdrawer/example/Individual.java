package nl.psdcompany.duonavigationdrawer.example;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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
import java.util.ArrayList;

public class Individual extends AppCompatActivity {
    ArrayList<String> data;
    EditText edtn1;
    Button sendn1;
    Spinner sp1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.individual);

        edtn1 = (EditText) findViewById(R.id.edtn1);
        sendn1 = (Button) findViewById(R.id.sendn1);
        sp1 = (Spinner) findViewById(R.id.sp1);


        MyTask1 mt1 = new MyTask1();

        mt1.execute("http://192.168.1.27:8080/GETSWEB/SerSpinnerAndroid");



        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                // Notify the selected item text
                Toast.makeText
                        (getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        sendn1.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                JSONArray ab = new JSONArray();
                JSONObject obj = new JSONObject();

                try {
                    obj.put("Message", edtn1.getText().toString());
                    obj.put("enroll_no", sp1.getSelectedItem());
                    ab.put(obj);
                    Log.d("135", ab.toString());


                } catch (JSONException e) {
                    e.printStackTrace();
                }


                MyTask2 mt2 = new MyTask2();
                mt2.execute("http://192.168.1.27:8080/GETSWEB/SerIndividualAndroid", ab.toString());

            }


        });


    }

    class MyTask2 extends AsyncTask<String, String, String> {


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
            return output.toString();


        }

     protected void onPostExecute(String output) {
         Toast.makeText(Individual.this, output, Toast.LENGTH_SHORT).show();
     }
    }

    class MyTask1 extends AsyncTask<String, String, String> {


        public String doInBackground(String... params) {
            String resp = null;

            String weburl = params[0];

            StringBuffer output = new StringBuffer();
            try {
                InputStream stream = null;
                // start of code of connnetion
                // weburl=weburl+"?data="+params[1];
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
            return output.toString();


        }


        protected void onPostExecute(String output) {
            Toast.makeText(Individual.this, output, Toast.LENGTH_SHORT).show();
            String n = "get is called";
            Log.d("149", output);


            // JSONTokener js=new JSONTokener(output);
            String sg = "abcggh";
            Log.d("error51", sg);


            try {
                ArrayList<String> list = new ArrayList<>();

                JSONTokener js = new JSONTokener(output);


                JSONArray jb = (JSONArray) js.nextValue();

                Log.d("140abc", jb.length() + "");


                JSONObject ob = null;


                for (int i = 0; i < jb.length(); i++) {

                    ob = jb.getJSONObject(i);

                    String p_id = ob.getString("p_id");
                    String p_fname = ob.getString("p_fname");
                    String p_lname = ob.getString("p_lname");
                    Log.d("142", p_id + " " + p_fname + " " + p_lname);
                    list.add(p_id + " " + p_fname + " " + p_lname);

                }


                Log.d("148", list.toString());

                Log.d("189", list.size() + "");

                ArrayAdapter<String> adapter = new ArrayAdapter(Individual.this, android.R.layout.simple_spinner_item, list);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp1.setAdapter(adapter);


            } catch (JSONException e) {
                Log.d("173ac", "" + e.getMessage());
                e.printStackTrace();
            }

        }
    }

}


