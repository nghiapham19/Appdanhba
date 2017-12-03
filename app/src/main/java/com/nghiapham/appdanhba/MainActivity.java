package com.nghiapham.appdanhba;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.nghiapham.adapter.ContactAdapter;
import com.nghiapham.model.Contact;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
     EditText txtten,txtphone;
     Button bntluu;
     ListView lvDanhBa;
     ArrayList<Contact>dsDanhBa;
     ContactAdapter adapterContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        bntluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyLuu();
            }
        });
    }

    private void xuLyLuu() {
        Contact contact=new Contact(txtten.getText().toString(),
                            txtphone.getText().toString());
        dsDanhBa.add(contact);
        adapterContact.notifyDataSetChanged();
    }

    private void addControls() {
    txtten=(EditText)findViewById(R.id.txtten);
    txtphone=(EditText)findViewById(R.id.txtphone);
    bntluu=(Button)findViewById(R.id.bntluu);

    lvDanhBa=(ListView)findViewById(R.id.lvDanhBa);
    dsDanhBa =new ArrayList<>();
    adapterContact=new ContactAdapter(MainActivity.this,R.layout.item,dsDanhBa);
    lvDanhBa.setAdapter(adapterContact);

    }
}
