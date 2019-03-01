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
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
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


public class Feedback extends Fragment {
    RatingBar rtn;
    Button buttonr;
    EditText edtf1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.feedback, container, false);

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        RatingBar ratingbar = (RatingBar) getActivity().findViewById(R.id.ratingBar);
        getActivity().setTitle("Feedback");


        rtn = (RatingBar) view.findViewById(R.id.ratingBar);

        buttonr = (Button) view.findViewById(R.id.buttonr);
        edtf1 = (EditText) view.findViewById(R.id.edtf1);

        buttonr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JSONArray ab = new JSONArray();
                JSONObject obj = new JSONObject();

                String rating = String.valueOf(rtn.getRating());
              Toast.makeText(Feedback.super.getContext(), rating, Toast.LENGTH_LONG).show();

                try {
                    obj.put("Comment", edtf1.getText().toString());
                    obj.put("Rating",String.valueOf(rtn.getRating()));
                    ab.put(obj);
                    Log.d("135", ab.toString());


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                MyTask1 mt1 = new MyTask1();
                mt1.execute("http://192.168.1.72:8080/GETSWEB/SerAllBatchAndroid", ab.toString());

            }
        });


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
//            Toast.makeText(Feedback.this, output, Toast.LENGTH_SHORT).show();
            String n = "get is called";
            Log.d("149", n);

        }

    }
}