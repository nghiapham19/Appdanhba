package com.nghiapham.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.nghiapham.appdanhba.R;
import com.nghiapham.appdanhba.SmsActivity;
import com.nghiapham.model.Contact;

import java.time.Instant;
import java.util.List;

/**
 * Created by Admin on 15-11-2017.
 */

public class ContactAdapter extends ArrayAdapter<Contact> {
    Activity context;
    int resource;
    List<Contact> objects;

    public ContactAdapter(@NonNull Activity context, int resource, @NonNull List<Contact> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource, null);

        EditText txtten = (EditText) row.findViewById(R.id.txtten);
        EditText txtphone = (EditText) row.findViewById(R.id.txtphone);
        ImageButton btncall = (ImageButton) row.findViewById(R.id.btncall);
        ImageButton btnsms = (ImageButton) row.findViewById(R.id.btnsms);
        ImageButton btndelete = (ImageButton) row.findViewById(R.id.btndelete);

        final Contact contact = this.objects.get(position);
        txtten.setText(contact.getName());
        txtphone.setText(contact.getPhone());

        btncall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyCall(contact);
            }
        });

        btnsms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLySms(contact);
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyDelete(contact);
            }
        });
        return row;
    }

    private void xuLyDelete(Contact contact) {
        this.remove(contact);
    }

    private void xuLySms(Contact contact) {
        Intent intent=new Intent(this.context, SmsActivity.class);
        intent.putExtra("CONTACT",contact);
        this.context.startActivity(intent);

    }

    private void xuLyCall(Contact contact) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri uri = Uri.parse("tel:" + contact.getPhone());
        intent.setData(uri);
        if (ActivityCompat.checkSelfPermission(this.context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        this.context.startActivity(intent);
    }
}
