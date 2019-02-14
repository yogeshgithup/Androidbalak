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

public class Registration extends AppCompatActivity {
    Button Submit,pro;
    TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9, tv10, tv11, tv12, tv13, tv14;
    EditText et1, et2, et3, et4, et5, et6, et7, et8, et9, et10, et11, et12, et13, et14, et15, et16, et17, et18, et19, et20, et21;

    RadioButton rb;
    Spinner sp1;
    RadioGroup rg;
    // Button b1,b2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);
        tv5 = (TextView) findViewById(R.id.tv5);
        tv6 = (TextView) findViewById(R.id.tv6);
        tv7 = (TextView) findViewById(R.id.tv7);
        tv8 = (TextView) findViewById(R.id.tv8);
        tv9 = (TextView) findViewById(R.id.tv9);
        tv10 = (TextView) findViewById(R.id.tv10);
        tv11 = (TextView) findViewById(R.id.tv11);
        tv12 = (TextView) findViewById(R.id.tv12);
        tv13 = (TextView) findViewById(R.id.tv13);
        tv14 = (TextView) findViewById(R.id.tv14);


        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        et3 = (EditText) findViewById(R.id.et3);
        et4 = (EditText) findViewById(R.id.et4);
        et5 = (EditText) findViewById(R.id.et5);
        et6 = (EditText) findViewById(R.id.et6);
        et7 = (EditText) findViewById(R.id.et7);
        et8 = (EditText) findViewById(R.id.et8);
        et9 = (EditText) findViewById(R.id.et9);
        et10 = (EditText) findViewById(R.id.et10);
        et11 = (EditText) findViewById(R.id.et11);
        et12 = (EditText) findViewById(R.id.et12);
        et13 = (EditText) findViewById(R.id.et13);
        et14 = (EditText) findViewById(R.id.et14);
        et15 = (EditText) findViewById(R.id.et15);
        et16 = (EditText) findViewById(R.id.et16);

        et17 = (EditText) findViewById(R.id.et17);
        et18 = (EditText) findViewById(R.id.et18);
        et19 = (EditText) findViewById(R.id.et19);
        et20 = (EditText) findViewById(R.id.et20);
        et21 = (EditText) findViewById(R.id.et21);

        rg=(RadioGroup)findViewById(R.id.rg);
        // rb = (RadioButton) findViewById(R.id.rb1);
        //rb2 = (RadioButton) findViewById(R.id.rb2);

        sp1 = (Spinner) findViewById(R.id.sp1);
        // b1=(Button)findViewById(R.id.b1);
        Submit = (Button) findViewById(R.id.b2);
        pro=(Button)findViewById(R.id.pro);

        pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registration.this, Main2Activity.class);
                startActivity(intent);

            }
        });

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JSONArray ab = new JSONArray();
                JSONObject obj = new JSONObject();

                try {
                    obj.put("f_name",et1.getText().toString());
                  obj.put("m_name",et2.getText().toString());
                 obj.put("l_name",et3.getText().toString());

                  int selectedId =rg.getCheckedRadioButtonId();

                    rb = (RadioButton) findViewById(selectedId);
                    obj.put((String)tv2.getText(),rb.getText());


//                   /* if (rb1.isChecked()) {
//                      //  rb2.setChecked(false);
//                        obj.put((String) tv2.getText(), rb1.getText());
//
//                    } else if(rb2.isChecked()) {
//                       // rb1.setChecked(false);
//                        obj.put((String) tv2.getText(), rb2.getText());
//
//                    }*/
//
          obj.put("dob", et4.getText());
                    obj.put("contact_no", et5.getText());
                 obj.put("email", et6.getText());
                 obj.put("ReEnterEmail", et7.getText());
                    obj.put("home_no",et8.getText().toString());
                  obj.put("street_no",et9.getText().toString());
                   obj.put("street_name",et10.getText().toString());
                    obj.put("area",et11.getText().toString());
                    obj.put("city",et12.getText().toString());
                    obj.put("pincode",et13.getText().toString());


                    JSONArray qua = new JSONArray();
                    String q1=et14.getText().toString().trim();
                    String q2=et15.getText().toString().trim();
                    String q3=et16.getText().toString().trim();
                    String q4=et17.getText().toString().trim();
                    if(q1.length()!=0) {
                        qua.put(q1);
                    }
                    if(q2.length()!=0) {
                        qua.put(q2);
                    }
                    if(q3.length()!=0) {
                        qua.put(q3);
                    }
                    if(q4.length()!=0) {
                        qua.put(q4);
                    }
                    obj.put("qualification:",qua);

                   obj.put("parent_name", et18.getText());
                    obj.put("parent_contact_no", et19.getText());
                    obj.put("guardian_name", et20.getText());
                    obj.put("guardian_contact_no",et21.getText());
                    obj.put("course",sp1.getSelectedItem());


                    ab.put(obj);
                    Log.d("135", ab.toString());


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                MyTask1 mt1=new MyTask1();

                mt1.execute("http://192.168.1.53:8080/GETSWEB/SerPersonAndroid",ab.toString());

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
            Toast.makeText(Registration.this, output, Toast.LENGTH_SHORT).show();
            String n="get is called";
            Log.d("149",n);

        }


    }
}
