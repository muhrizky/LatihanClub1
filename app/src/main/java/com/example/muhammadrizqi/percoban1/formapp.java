package com.example.muhammadrizqi.percoban1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class formapp extends AppCompatActivity
    implements View.OnClickListener { //1

    Button kirim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formapp);
        kirim = (Button) findViewById(R.id.btn_kirim); //2

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void submitasmara(View view){
        final EditText nameEditText = (EditText) findViewById(R.id.nama);
        final EditText alamatEditText = (EditText) findViewById(R.id.alamat);
        final EditText nohpEditText = (EditText) findViewById(R.id.nohp);
        final EditText nikEditText = (EditText) findViewById(R.id.nik);
        final EditText aspirasiEditText = (EditText) findViewById(R.id.aspirasi);
        Editable aspirasi = aspirasiEditText.getText();
        Editable nik = nikEditText.getText();
        Editable nama = nameEditText.getText();
        Editable alamat = alamatEditText.getText();
        Editable nohp = nohpEditText.getText();

        if(nameEditText.getText().toString().equals("") || alamatEditText.getText().toString().equals("") || nohpEditText.getText().toString().equals("")
                || nikEditText.getText().toString().equals("")|| aspirasiEditText.getText().toString().equals("")){
            Toast.makeText(formapp.this,"Harap Lengkapi Penuh Isian Anda !", Toast.LENGTH_SHORT).show();
            return;
        }
        String orderSummary = createOrderSummary(nama, alamat, nohp, nik, aspirasi);
        Log.i("MainActivity.java", orderSummary);
        displayMessage(orderSummary);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // hanya bisa dihandle aplikasi email
        intent.putExtra(Intent.EXTRA_SUBJECT, "ASMARA Dari " + nik);
        intent.putExtra(Intent.EXTRA_TEXT, orderSummary);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "aspirasi@jatengprov.go.id" });


        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        //order.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //   public void onClick(View v) {


        //   }
        //});
    }


    /**
     * This method is used to create the order summary.
     * @return order summary
     */
    public String createOrderSummary(Editable nama, Editable alamat, Editable nohp, Editable nik, Editable aspirasi ){
        String orderSummary = "Nama Anda : " + nama;
        orderSummary = orderSummary + "\nNIK : " + nik;
        orderSummary = orderSummary + "\nAlamat : " + alamat;
        orderSummary = orderSummary + "\nNo.HP : "  + nohp;
        orderSummary = orderSummary + "\nBerikut Aspirasi Anda : " + aspirasi;
        orderSummary = orderSummary + "\nTerima Kasih :) !";
        return orderSummary;
    }

    private void displayMessage(String a) {

        TextView orderSummaryTextView = (TextView) findViewById(R.id.ringkasan);
        //priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
        orderSummaryTextView.setText(a);
    }


    @Override
    public void onClick(View v) { //1

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            // finish the activity
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

