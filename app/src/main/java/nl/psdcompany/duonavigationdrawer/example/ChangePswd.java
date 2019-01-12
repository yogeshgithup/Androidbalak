package nl.psdcompany.duonavigationdrawer.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

            }
        });
    }
}

