package nl.psdcompany.duonavigationdrawer.example;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class Course_details extends Fragment {

    private ArrayList<person> datalist;
    String url="http://192.168.1.95:8080/GETSWEB/SerCourseAndroid";
    TableLayout tableLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
       return inflater.inflate(R.layout.course_table, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Course Details");
        tableLayout=(TableLayout)view.findViewById(R.id.tablelayout);


    }

    @Override
    public void onResume() {
        super.onResume();

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
                    Integer fees=Integer.parseInt(obj.getString("c_fees"));
                    Integer duration= Integer.parseInt(obj.getString("duration"));
                    String subject=obj.getString("sub_name");
                    String section = obj.getString("sec_name");
                    Log.d("118line",LayoutInflater.from(getContext())+"");
                    View tableRow =LayoutInflater.from(getContext()).inflate(R.layout.course_item,null,false);
                    TextView coursee  = (TextView)tableRow.findViewById(R.id.course);
                    TextView feess= (TextView)tableRow.findViewById(R.id.fees);
                    TextView durationn= (TextView)tableRow.findViewById(R.id.duration);
                    TextView subjectt= (TextView)tableRow.findViewById(R.id.subject);
                    TextView sectionn= (TextView)tableRow.findViewById(R.id.section);

                    Log.d("115",jt.toString());

                    coursee.setText(course);
                    feess.setText(String.format("%d",fees));
                    durationn.setText(String.format("%d",duration));
                    subjectt.setText(subject);
                    sectionn.setText(section);

                    tableLayout.addView(tableRow);

                }

            } catch (JSONException e) {
                e.printStackTrace();
                String we="qwe";
                Log.d("qwe",e.getMessage());
            }
        }


    }
}
