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
