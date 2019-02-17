package nl.psdcompany.duonavigationdrawer.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;


public class Feedback extends Fragment {
RatingBar rtn;
Button buttonr;
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
        RatingBar ratingbar=(RatingBar)getActivity().findViewById(R.id.ratingBar);
        getActivity().setTitle("Feedback");


        rtn=(RatingBar)view.findViewById(R.id.ratingBar);

        buttonr=(Button)view.findViewById(R.id.buttonr);

        buttonr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String rating=String.valueOf(rtn.getRating());
                Toast.makeText(Feedback.super.getContext(),rating, Toast.LENGTH_LONG).show();
            }
        });


    }

    }
