package nl.psdcompany.duonavigationdrawer.example;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class Timetable extends Fragment {


    private ArrayList<pojo_timetable> datalist;
    String url="http://192.168.1.27:8080/GETSWEB/SerTimeTable";

    TableLayout tableLayout;

    TextView day,shift,slot,batch;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View v = inflater.inflate(R.layout.timetable, container, false);

        day =(TextView)v.findViewById(R.id.day);
        shift = (TextView) v.findViewById(R.id.shift);
        slot=(TextView)v.findViewById(R.id.slot);
        batch=(TextView)v.findViewById(R.id.batch);


        return v;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Timetable");
        tableLayout=(TableLayout)view.findViewById(R.id.tablelayout);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);

        if(savedInstanceState==null) {
            MyTask1 mt1 = new MyTask1();
            mt1.execute(url);
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
            Log.d("153line",s);

            Toast.makeText(getActivity(),"data:"+s,Toast.LENGTH_LONG).show();
            JSONTokener jt=new JSONTokener(s);

            try {
                JSONArray ja=new JSONArray(s);
                String day="",shift="",slot="",batchname="";
                for(int i=0;i<ja.length();i++) {
                    day="";
                    shift="";
                    slot="";
                    batchname="";
                    JSONObject obj = (JSONObject) ja.getJSONObject(i);

                     day = obj.getString("day");


                    JSONArray daydetail=null;
                    try {
                        daydetail = obj.getJSONArray("daydetail");
                    }
                    catch(Exception ex)
                    {
                        daydetail=null;
                    }

                  if(daydetail!=null) {
                      Log.d("obj", day + "----" + daydetail.toString());

                      for (int j = 0; j < daydetail.length(); j++) {

                          JSONObject shiftobj = daydetail.getJSONObject(j);
                          shift = shiftobj.getString("shift");
                          // JSONArray shiftdetail=shiftobj.getJSONArray("shiftdetail");

                          JSONArray shiftdetail = null;
                          try {
                              shiftdetail = shiftobj.getJSONArray("shiftdetail");
                          } catch (Exception ex) {
                              shiftdetail = null;
                              Log.d("shifttt",""+ex.getMessage());
                          }

                          if (shiftdetail != null) {
                              for (int k = 0; k < shiftdetail.length(); k++) {

                                  JSONObject batch = shiftdetail.getJSONObject(k);
                                  batchname = batchname + "\n" + batch.getString("batchname");
                                  slot = slot + "\n" + batch.getString("slot");
//
//                                  batchname = batch.getString("batchname");
//                                  slot = batch.getString("slot");
//
//                                  View row = LayoutInflater.from(getContext()).inflate(R.layout.timetable_textview, null, false);
//
//                                  TextView batchh= (TextView)row.findViewById(R.id.batch);
//                                  TextView slott= (TextView)row.findViewById(R.id.slot);
//                                  batchh.setText(batchname);
//                                  slott.setText(slot);
//                                  row1.addView(row);

                              }
                          }
                      }
                  }
                    View tableRow = LayoutInflater.from(getContext()).inflate(R.layout.timetable_item, null, false);

                    TextView dayy= (TextView)tableRow.findViewById(R.id.day);

                    TextView shiftt= (TextView)tableRow.findViewById(R.id.shift);

                    TextView slott=(TextView)tableRow.findViewById(R.id.slot);

                    TextView batchh =(TextView)tableRow.findViewById(R.id.batch);

                    Log.d("115",jt.toString());
                    dayy.setText(day);
                    shiftt.setText(shift);
                    slott.setText(slot);
                    batchh.setText(batchname);



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
