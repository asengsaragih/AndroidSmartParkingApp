package com.app.smartparking.base;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.app.smartparking.R;
import com.app.smartparking.helper.Session;
import com.app.smartparking.model.Slot;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BaseActivity extends AppCompatActivity {

    protected AppCompatActivity mActivity;
    protected Session mSession;
    //auth
    protected FirebaseAuth mAuth;

    //realtime database
    protected FirebaseDatabase mDatabase;

    private AlertDialog mLoadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;

        //disini ngoding seluruh fungsi yang dipanggil berulang ulang
        //contoh kaya toast dan lainnya
        //pemanggilan, tinggal ganti extend actvity nya ke baseActivity
        //contohnya ada di main activity

        //session check
        mSession = new Session(this);

        //inisialisasi loding bar
        loadingInitialize();

        //auth
        mAuth = FirebaseAuth.getInstance();

        //realtime
        mDatabase = FirebaseDatabase.getInstance();
    }

    private void loadingInitialize() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view= LayoutInflater.from(this).inflate(R.layout.progres_loading, null);
        builder.setView(view);
        builder.setCancelable(false);
        mLoadingDialog = builder.create();
    }

    protected void showLoading (boolean show){
        if (show)
            mLoadingDialog.show();
        else
            mLoadingDialog.dismiss();
    }

    protected boolean isEdittextEmpty(EditText editText){
        //fungsi untuk check value dari edittext
        return TextUtils.isEmpty(editText.getText().toString());
    }

    protected void toast(String message){
        //fungsi untuk menampilkan toast
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected void addSlotData(String name, String area) {
        Slot slot = new Slot(name, area, true);
        mDatabase.getReference(Constant.FIREBASE_TABLE_SLOT).push().setValue(slot);
    }

    protected String millisToStringDate(long millis) {
        //fungsi untuk merubah jam millis ke string date format
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constant.DATE_FORMAT, Locale.getDefault());
        return simpleDateFormat.format(calendar.getTime());
    }

    protected String millisToStringDateTime(long millis) {
        //fungsi untuk merubah jam millis ke string date format
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constant.DATETIME_FORMAT, Locale.getDefault());
        return simpleDateFormat.format(calendar.getTime());
    }
}
