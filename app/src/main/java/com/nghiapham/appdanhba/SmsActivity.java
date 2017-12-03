package com.nghiapham.appdanhba;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.nghiapham.model.Contact;

public class SmsActivity extends AppCompatActivity {
    TextView txtnguoinhan;
    EditText txtnoidung;
    ImageButton btnguitn;

    Contact selectedContact = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnguitn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLysms();
            }
        });

    }

    private void xuLysms() {
        final SmsManager sms = SmsManager.getDefault();
        Intent msgSent = new Intent("ACTION_MSG_SENT");
        final PendingIntent pendingMssent =
                PendingIntent.getBroadcast(this, 0, msgSent, 0);
        registerReceiver(new BroadcastReceiver(){
            @Override
            public void onReceive(Context context, Intent intent) {
               int result=getResultCode();
               String msg="Đã gửi tin nhấn thành công";
               if(result != Activity.RESULT_OK){
                   msg="Gửi tin nhấ thất bại";
               }
                Toast.makeText(SmsActivity.this,msg,Toast.LENGTH_LONG).show();
            }
        },new IntentFilter("ACTION_MSG_SENT"));
        sms.sendTextMessage(selectedContact.getPhone(),null,txtnoidung.getText()+"",pendingMssent,null);
    }


    private void addControls() {
        txtnguoinhan=(TextView)findViewById(R.id.txtnguoinhan);
        txtnoidung=(EditText)findViewById(R.id.txtnoidung);
        btnguitn=(ImageButton)findViewById(R.id.btnguitn);

        Intent intent=getIntent();
        selectedContact=(Contact) intent.getSerializableExtra("CONTACT");
        txtnguoinhan.setText(selectedContact.getName()+"-"+selectedContact.getPhone());

    }
}
