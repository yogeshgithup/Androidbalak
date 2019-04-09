package nl.psdcompany.duonavigationdrawer.example;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
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
import java.util.ArrayList;

public class Fee_status extends  Fragment {

    private ArrayList<pojo_fees> datalist;
    String url="http://192.168.1.27:8080/GETSWEB/SerFeesStatusAndroid";
    TableLayout tableLayout;

    String n;
    Intent intent;

    JSONArray ab = new JSONArray();
    JSONObject obj = new JSONObject();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
         return inflater.inflate(R.layout.fee_status, container, false);

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Fees Status");
        tableLayout=(TableLayout)view.findViewById(R.id.tablelayout);


        intent = getActivity().getIntent();

        n = intent.getStringExtra("Username");


        try
        {
            obj.put("Username",n);
            ab.put(obj);
            Log.d("135", ab.toString());
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);

        if(savedInstanceState==null) {
            MyTask1 mt1 = new MyTask1();
            mt1.execute(url,ab.toString());
        }
    }

    @Override
    public void onResume() {

        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {

                    // handle back button

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setCancelable(false);
                    builder.setTitle("Exit");
                    builder.setMessage("Do you want to Log Out?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent=new Intent(getActivity(),HomePage.class);
                            startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    return true;
                }

                return false;
            }
        });


//            MyTask1 mt1 = new MyTask1();
//            mt1.execute(url);


    }


    class MyTask1 extends AsyncTask<String, String, String> {


        public String doInBackground(String... params) {
            String resp = null;

            String weburl=params[0];

            StringBuffer output=new StringBuffer();
            try {
                InputStream stream = null;
                // start of code of connnetion
                weburl = weburl + "?data=" + params[1];
                Log.d("url123",weburl);
                URL url = new URL(weburl);
                URLConnection connection = url.openConnection();

                String sd = "abcbjj";
                Log.d("error128",sd);
                HttpURLConnection httpConnection = (HttpURLConnection) connection;
                String sm = "bnhjbc";
                Log.d("error131",sm);
                httpConnection.setRequestMethod("GET");
                String sg = "abcggh";
                Log.d("error134",sg);
                httpConnection.connect();
                String sk = "abcmj";
                Log.d("error137",sk);
                stream = httpConnection.getInputStream();
                String sl = "abckkii";
                Log.d("error140",sl);
                BufferedReader buffer = new BufferedReader(new InputStreamReader(stream));
                String s = "abc";
                Log.d("error143",s);
                while ((s = buffer.readLine()) != null)
                    output.append(s);
            } catch (Exception e) {
                Log.e("error147",e.getMessage());

            }
            return output.toString();


        }


        protected void onPreExecute(){

        }

        protected void onPostExecute(String s) {
            Log.d("158line",s);
            JSONTokener jt=new JSONTokener(s);

            try {
                JSONArray ja=new JSONArray(s);
                for(int i=0;i<ja.length();i++) {

                    JSONObject obj = (JSONObject) ja.getJSONObject(i);

                    String course = obj.getString("c_name");
                    Integer fees=Integer.parseInt(obj.getString("c_fees"));
                    Integer fees_paid= Integer.parseInt(obj.getString("fees_paid"));

                    Log.d("171line",LayoutInflater.from(getContext())+"");
                    View tableRow =LayoutInflater.from(getContext()).inflate(R.layout.fees_item,null,false);
                    TextView coursee  = (TextView)tableRow.findViewById(R.id.course);
                    TextView feess= (TextView)tableRow.findViewById(R.id.fees);
                    TextView fpaid= (TextView)tableRow.findViewById(R.id.fpaid);
                    TextView fremain=(TextView)tableRow.findViewById(R.id.fremain);


                    Log.d("178line",jt.toString());

                    coursee.setText(course);
                    feess.setText(String.format("%d",fees));
                    fpaid.setText(String.format("%d",fees_paid));

                    Integer remain= Integer.parseInt(obj.getString("c_fees")) - Integer.parseInt(obj.getString("fees_paid"));
                    if(remain > 0)
                    {
                        fremain.setText(String.format("%d",remain));
                    }
                    else
                    {
                        fremain.setText("fees paid");
                    }


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