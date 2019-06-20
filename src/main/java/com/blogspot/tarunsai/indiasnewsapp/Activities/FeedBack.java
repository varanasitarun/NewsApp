package com.blogspot.tarunsai.indiasnewsapp.Activities;

import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.blogspot.tarunsai.indiasnewsapp.R;
import com.firebase.client.Firebase;


public class FeedBack extends AppCompatActivity {
    EditText n,e,m;
    Button s,submis;
    Firebase firebase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back1);
        n=findViewById(R.id.username);
        e=findViewById(R.id.email);
        m=findViewById(R.id.feedback);
        s=findViewById(R.id.send);
        submis=findViewById(R.id.submissiondetails);
        firebase.setAndroidContext(this);
        String uniqueId= Settings.Secure.getString(getApplicationContext().getContentResolver(),Settings.Secure.ANDROID_ID);
        firebase=new Firebase("https://holidayviewer.firebaseio.com/Users"+uniqueId);
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submis.setEnabled(true);
                final String namedata=n.getText().toString();
                final String Email=e.getText().toString();
                final String Message=m.getText().toString();

                Firebase child_name=firebase.child("Name");
                child_name.setValue(namedata);
                if(namedata.isEmpty()){
                    n.setError("This is required field");
                }
                else{
                    n.setError(null);
                    s.setEnabled(true);
                }

                Firebase child_email=firebase.child("Email");
                child_email.setValue(Email);
                if(Email.isEmpty()){
                    e.setError("This is required field");
                }
                else{
                    e.setError(null);
                }

                Firebase child_message=firebase.child("Message");
                child_message.setValue(Message);
                if(Message.isEmpty()){
                    m.setError("This is required field");
                }
                else{
                    m.setError(null);
                }
                if(!(namedata.isEmpty()&&Email.isEmpty()&&Message.isEmpty())){
                    Toast.makeText(FeedBack.this, R.string.display, Toast.LENGTH_SHORT).show();
                    n.setText("");
                    e.setText("");
                    m.setText("");
                }
                submis.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new AlertDialog.Builder(FeedBack.this).setTitle("Sended Details").
                                setMessage("Name :"+namedata+"\n\nEmail :"+Email+"\n\nMessage :"+Message).show();
                    }
                });

            }

        });

    }
}
