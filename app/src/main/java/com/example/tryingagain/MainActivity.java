package com.example.tryingagain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.annotation.Documented;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button fini;
    UserHelperClass userHelperClass;
    TextInputLayout favColor;
    RadioButton yes, no;
    CheckBox bAndB, bam, lilChick, tang;
    int i = 0;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userHelperClass = new UserHelperClass();

        //text input layout
        favColor = findViewById(R.id.til_favColor);

        //radio buttons
        yes = findViewById(R.id.rb_happyYes);
        no = findViewById(R.id.rb_happyNo);

        //check boxes
        bAndB = findViewById(R.id.cb_beatyAndBeast);
        bam = findViewById(R.id.cb_bambi);
        lilChick = findViewById(R.id.cb_chickenLil);
        tang = findViewById(R.id.cb_tangled);

        //end button
        fini = findViewById(R.id.bt_fini);

        String one = "Beauty And The Beast";
        String two = "Bambi";
        String three = "Chicken Little";
        String four = "Tangled";

        reference = rootNode.getInstance().getReference().child("something");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){
                    i = (int)snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        fini.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String y = yes.getText().toString();
                String n = no.getText().toString();

                userHelperClass.setColor(favColor.getEditText().getText().toString());
                reference.child(String.valueOf(i+1)).setValue(userHelperClass);

                //radio buttons
                if(yes.isChecked()){
                    userHelperClass.setHappiness(y);
                    reference.child(String.valueOf(i+1)).setValue(userHelperClass);
                } else{
                    userHelperClass.setHappiness(n);
                    reference.child(String.valueOf(i+1)).setValue(userHelperClass);
                }

                //checkboxes
                if(bAndB.isChecked()){
                    userHelperClass.setBeautyAndBeast(one);
                    reference.child(String.valueOf(i+1)).setValue(userHelperClass);
                } else{

                }
                if(bam.isChecked()){
                    userHelperClass.setBambi(two);
                    reference.child(String.valueOf(i+1)).setValue(userHelperClass);
                } else{

                }
                if(lilChick.isChecked()){
                    userHelperClass.setChickenLittle(three);
                    reference.child(String.valueOf(i+1)).setValue(userHelperClass);
                } else{

                }
                if(tang.isChecked()){
                    userHelperClass.setTangled(four);
                    reference.child(String.valueOf(i+1)).setValue(userHelperClass);
                } else{

                }

            }
        });
    }
}