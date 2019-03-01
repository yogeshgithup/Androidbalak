package nl.psdcompany.duonavigationdrawer.example;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;
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

public class Course extends AppCompatActivity {
    private ArrayList<pojo_course> datalist;
    String url="http://192.168.1.72:8080/GETSWEB/SerCourseAndroid";
    TableLayout tableLayout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course);
        tableLayout=(TableLayout)findViewById(R.id.tablelayout);


        MyTask1 mt1=new MyTask1();
        mt1.execute(url);

    }
    class MyTask1 extends AsyncTask<String, String, String> {


        public String doInBackground(String... params) {
            String resp = null;

            String weburl=params[0];

            StringBuffer output=new StringBuffer();
            try {
                InputStream stream = null;
                // start of code of connnetion

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


        protected void onPreExecute(){

        }

        protected void onPostExecute(String s) {
            Log.d("111line",s);
            JSONTokener jt=new JSONTokener(s);

            try {
                JSONArray ja=new JSONArray(s);
                for(int i=0;i<ja.length();i++) {

                    JSONObject obj = (JSONObject) ja.getJSONObject(i);

                    String course = obj.getString("c_name");
                    String subject=obj.getString("sub_name");
                    String section = obj.getString("sec_name");
                    Integer fees=Integer.parseInt(obj.getString("c_fees"));
                    Integer duration= Integer.parseInt(obj.getString("duration"));
//
//                    Log.d("edfr",course);
//                    Log.d("asd",subject);
//                    Log.d("edfrwww",section);
//                  Log.d("asdfvf",fees);
//                   Log.d("edfrjkl",duration);
                    View tableRow = LayoutInflater.from(Course.this).inflate(R.layout.course_item,null,false);
                    TextView coursee  = (TextView)tableRow.findViewById(R.id.course);
                    TextView subjectt= (TextView)tableRow.findViewById(R.id.subject);
                    TextView sectionn= (TextView)tableRow.findViewById(R.id.section);
                    TextView feess= (TextView)tableRow.findViewById(R.id.fees);
                    TextView durationn= (TextView)tableRow.findViewById(R.id.duration);
                    Log.d("115",jt.toString());

                    coursee.setText(course);
                    subjectt.setText(subject);
                    sectionn.setText(section);
                    feess.setText(String.format("%d",fees));
                    durationn.setText(String.format("%d",duration));
                    tableLayout.addView(tableRow);

                }

            } catch (JSONException e) {
                e.printStackTrace();
                String we="qwe";
                Log.d("qwe",e.getMessage());
            }
            Toast.makeText(Course.this, s, Toast.LENGTH_SHORT).show();
            String n="get is called";
            Log.d("149",n);
        }


    }

}
