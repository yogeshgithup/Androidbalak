package nl.psdcompany.duonavigationdrawer.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Homepage extends AppCompatActivity {
    Button Login;
    EditText uname, pswd;
    TextView fpwd, sign;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.homepage);

        sign = (TextView) findViewById(R.id.sign);
        Login = (Button) findViewById(R.id.b1);
        uname = (EditText) findViewById(R.id.uid);
        pswd = (EditText) findViewById(R.id.pwd);
        fpwd=(TextView)findViewById(R.id.fpwd);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uid = uname.getText().toString();
                String pwd = pswd.getText().toString();


                if (uid.equals("student") && pwd.equals("student")) {
                    Toast.makeText(Homepage.this, "LogIn Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Homepage.this, MainActivity.class);
                    startActivity(intent);
                }
                if(uid.equals("admin") && pwd.equals("admin")){
                    Toast.makeText(Homepage.this,"Login Successfully",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Homepage.this,MainAdmin.class);
                    startActivity(intent);
                }
                if (uid.equals("staff") && pwd.equals("staff")){
                    Toast.makeText(Homepage.this,"Login Successfully",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Homepage.this,MainStaff.class);
                    startActivity(intent);
                }

            }
        });
        fpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Homepage.this,Forgotpswd.class);
                startActivity(intent);
            }
        });

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Homepage.this,Registration.class);
                startActivity(intent);
            }
        });


    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_gallery) {
            startActivity(new Intent(this, Gallery.class));
            return true;
        }
        if (id == R.id.action_course) {
            startActivity(new Intent(this, Course.class));
            return true;
        }
        if (id == R.id.action_aboutus) {
            startActivity(new Intent(this, AboutUs.class));
            return true;
        }
        if (id == R.id.action_contactus) {
            startActivity(new Intent(this, ContactUs.class));
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}










//package com.example.divyesh.project2;
//
//
//        import android.content.Intent;
//        import android.os.AsyncTask;
//        import android.os.Bundle;
//        import android.support.v7.app.AppCompatActivity;
//        import android.util.Log;
//        import android.view.Menu;
//        import android.view.MenuInflater;
//        import android.view.MenuItem;
//        import android.view.View;
//        import android.widget.Button;
//        import android.widget.EditText;
//        import android.widget.TextView;
//        import android.widget.Toast;
//
//        import org.json.JSONArray;
//        import org.json.JSONException;
//        import org.json.JSONObject;
//
//        import java.io.BufferedReader;
//        import java.io.InputStream;
//        import java.io.InputStreamReader;
//        import java.net.HttpURLConnection;
//        import java.net.URL;
//        import java.net.URLConnection;
//
//public class HomePage extends AppCompatActivity {
//    Button Signup;
//    Button Login;
//    EditText uname, pswd;
//    TextView fpwd;
//
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.homepage);
//
//        Signup = (Button) findViewById(R.id.button5);
//        Login = (Button) findViewById(R.id.b1);
//        uname = (EditText) findViewById(R.id.uid);
//        pswd = (EditText) findViewById(R.id.pwd);
//        fpwd=(TextView)findViewById(R.id.fpwd);
//
//
//        Login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                JSONArray ab = new JSONArray();
//                JSONObject obj = new JSONObject();
//
//                try {
//                    obj.put("Username",uname.getText().toString());
//                    obj.put("Password",pswd.getText().toString());
//
//                    ab.put(obj);
//                    Log.d("135", ab.toString());
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//                MyTask1 mt1=new MyTask1();
//                mt1.execute("http://192.168.1.79:8080/GETSWEB/SerLogin",ab.toString());
//
//
//            }
//        });
//
//
//        fpwd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(HomePage.this,forgotpswd.class);
//                startActivity(intent);
//            }
//        });
//
//        Signup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(HomePage.this, Registration.class);
//                startActivity(intent);
//            }
//        });
//
//    }
//    private class MyTask1 extends AsyncTask<String, String, String> {
//
//
//        public String doInBackground(String... params) {
//            String resp = null;
//
//            String weburl=params[0];
//
//            StringBuffer output=new StringBuffer();
//            try {
//                InputStream stream = null;
//                // start of code of connnetion
//                weburl=weburl+"?data="+params[1];
//                Log.d("url",weburl);
//                URL url = new URL(weburl);
//                URLConnection connection = url.openConnection();
//
//                String sd = "abcbjj";
//                Log.d("error41",sd);
//                HttpURLConnection httpConnection = (HttpURLConnection) connection;
//                String sm = "bnhjbc";
//                Log.d("error42",sm);
//                httpConnection.setRequestMethod("GET");
//                String sg = "abcggh";
//                Log.d("error43",sg);
//                httpConnection.connect();
//                String sk = "abcmj";
//                Log.d("error44",sk);
//                stream = httpConnection.getInputStream();
//                String sl = "abckkii";
//                Log.d("error46",sl);
//                BufferedReader buffer = new BufferedReader(new InputStreamReader(stream));
//                String s = "abc";
//                Log.d("error45",s);
//                while ((s = buffer.readLine()) != null)
//                    output.append(s);
//            }
//            catch (Exception e) {
//                Log.e("error62",e.getMessage());
//
//            }
//            return output.toString();
//
//
//        }
//
//
//
//        protected void onPostExecute(String output) {
//            Toast.makeText(HomePage.this, output, Toast.LENGTH_SHORT).show();
//            String n = "get is called";
//            Log.d("149", n);
//
//            Log.d("126", output);
//
//            if (output.equals("A1")) {
//                Toast.makeText(HomePage.this, "LogIn Successfully", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(HomePage.this, Main2Activity.class);
//                startActivity(intent);
//
//
//            } else if (output.equals("F")) {
//                Toast.makeText(HomePage.this, "LogIn Successfully", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(HomePage.this, Main3Activity.class);
//                startActivity(intent);
//
//            } else if (output.equals("S")) {
//                Toast.makeText(HomePage.this, "LogIn Successfully", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(HomePage.this, MainActivity.class);
//                startActivity(intent);
//
//            } else {
//                Toast.makeText(HomePage.this, "LogIn Failed", Toast.LENGTH_SHORT).show();
//            }
//
//
//        }
//
//
//    }
//
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.mainmenu, menu);
//        return true;
//    }
//
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        switch (id) {
//            case R.id.item1:
//                startActivity(new Intent(this, About.class));
//                return true;
//
//            case R.id.item2:
//                startActivity(new Intent(this, Contact.class));
//                return true;
//
//            case R.id.item3:
//                startActivity(new Intent(this, Gallery.class));
//                return true;
//
//
//            case R.id.item4:
//                startActivity(new Intent(this,Course_Details.class));
//                return true;
//
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
//
//
//}
//
//
//  protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.homepage);
//
//        Signup = (Button) findViewById(R.id.button5);
//        Login = (Button) findViewById(R.id.b1);
//        uname = (EditText) findViewById(R.id.uid);
//        pswd = (EditText) findViewById(R.id.pwd);
//
//
//        Login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String uid = uname.getText().toString();
//                String pwd = pswd.getText().toString();
//                String url = "http:\\192.168.1.79:8080/GETSWEB/SerLogin?name="+uid+"&password="+pwd;
//
//                LoginServletTask st = new LoginServletTask();
//                st.execute(url);
//
//
//            }
//        });
//
//
//        Signup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(HomePage.this,Registration.class);
//                startActivity(intent);
//            }
//        });
//
//    }
//
//
//    private class LoginServletTask extends AsyncTask<String, String, String> {
//
//
//        @Override
//        protected void onPreExecute() {
//        }
//
//        protected String doInBackground(String... urls) {
//            URL url;
//            String r = null;
//            String resp = null;
//
//            String weburl=urls[0];
//
//            StringBuffer output=new StringBuffer();
//            try {
//                InputStream stream = null;
//                // start of code of connnetion
//                weburl=weburl+"?data="+urls[1];
//                Log.d("url",weburl);
//                URL urll = new URL(weburl);
//                URLConnection connection = urll.openConnection();
//
//                String sd = "abcbjj";
//                Log.d("error41",sd);
//                HttpURLConnection httpConnection = (HttpURLConnection) connection;
//                String sm = "bnhjbc";
//                Log.d("error42",sm);
//                httpConnection.setRequestMethod("GET");
//                String sg = "abcggh";
//                Log.d("error43",sg);
//                httpConnection.connect();
//                String sk = "abcmj";
//                Log.d("error44",sk);
//                stream = httpConnection.getInputStream();
//                String sl = "abckkii";
//                Log.d("error46",sl);
//                BufferedReader buffer = new BufferedReader(new InputStreamReader(stream));
//                String s = "abc";
//                Log.d("error45",s);
//                while ((s = buffer.readLine()) != null)
//                    output.append(s);
//            } catch (Exception e) {
//                Log.e("error62",e.getMessage());
//
//            }
//            return output.toString();
//
//
//        }
//
//            /*try
//
//            {
//                Log.e("476", "inbackgoud" +urls[0]);
//                url = new URL(urls[0]);
//                HttpURLConnection con = (HttpURLConnection) url.openConnection();
//                con.setRequestMethod("GET");
//                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//                String inputLine;
//                StringBuffer response = new StringBuffer();
//                while ((inputLine = in.readLine()) != null) {
//                    response.append(inputLine);
//
//                }
//                in.close();
//                r = response.toString();
//
//            } catch (Exception e) {
//                Log.e("492", e.getMessage());
//                e.printStackTrace();
//                r = e.getMessage();
//
//            }
//            return r;
//        }*/
//
//        @Override
//        protected void onPostExecute(String s) {
//            Log.d("126",s);
//
//            if (s.equals("Admin")) {
//                Toast.makeText(HomePage.this, "LogIn Successfully", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(HomePage.this, Main2Activity.class);
//                startActivity(intent);
//
//
//            } else if (s.equals("Staff")) {
//                Toast.makeText(HomePage.this, "LogIn Successfully", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(HomePage.this, Main3Activity.class);
//                startActivity(intent);
//
//            } else if (s.equals("Student")) {
//                Toast.makeText(HomePage.this, "LogIn Successfully", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(HomePage.this, MainActivity.class);
//                startActivity(intent);
//
//            } else {
//                Toast.makeText(HomePage.this, "LogIn Failed", Toast.LENGTH_SHORT).show();
//            }
//
//        }
//
//    }
//}*/
//
//
//
//
//
