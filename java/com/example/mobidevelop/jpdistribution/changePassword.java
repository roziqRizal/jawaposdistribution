package com.example.mobidevelop.jpdistribution;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class changePassword extends AppCompatActivity {

    FrameLayout save,cancel;
    EditText old, newPass, newConfirm;
    LinearLayout back;
    ProgressDialog progressBar;
    private static final String HASH_ALGORITHM = "HmacSHA256";
    SimpleDateFormat dateFormat;
    DilatingDotsProgressBar mDilatingDotsProgressBar;
    AlertDialog ad3;
    AlertDialog.Builder imageDialog3, imagedialog, imagedialog2;
    String PublicToken;
    TextView TV7, TV8, TV9;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        Typeface Semiboldtype = Typeface.createFromAsset(getAssets(),  "fonts/SourceSansPro-Semibold.ttf");
        Typeface SourceSansProRegular = Typeface.createFromAsset(getAssets(),  "fonts/SourceSansPro-Regular.ttf");

        TV9 = (TextView)findViewById(R.id.textView9);
        TV8 = (TextView)findViewById(R.id.textView8);
        TV7 = (TextView)findViewById(R.id.textView7);
        TV9.setTypeface(SourceSansProRegular);
        TV8.setTypeface(SourceSansProRegular);
        TV7.setTypeface(SourceSansProRegular);

        progressBar = new ProgressDialog(this);
        progressBar.setCancelable(false);
        progressBar.setMessage("Waiting ...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        save = (FrameLayout) findViewById(R.id.button2);
        cancel = (FrameLayout) findViewById(R.id.button3);
        old = (EditText)findViewById(R.id.editText4);
        newPass = (EditText)findViewById(R.id.editText5);
        newConfirm = (EditText)findViewById(R.id.editText6);
        back = (LinearLayout) findViewById(R.id.topBar);

        old.setTypeface(SourceSansProRegular);
        newPass.setTypeface(SourceSansProRegular);
        newConfirm.setTypeface(SourceSansProRegular);

        Context context = getApplicationContext();
        appPrefs appPrefs = new appPrefs(context);
        PublicToken = appPrefs.getToken();

        imageDialog3 = new AlertDialog.Builder(this);
        imagedialog = new AlertDialog.Builder(this);
        imagedialog2 = new AlertDialog.Builder(this);

        Intent myIntent = getIntent(); // gets the previously created intent

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater layoutInflater3 = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View layout3 = layoutInflater3.inflate(R.layout.downloadprogres, null);
                mDilatingDotsProgressBar = (DilatingDotsProgressBar) layout3.findViewById(R.id.progress);
                mDilatingDotsProgressBar.show();

                imageDialog3.setView(layout3);
                imageDialog3.setCancelable(false);
                ad3 = imageDialog3.create();
                ad3.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                ad3.show();

                String oldPass = String.valueOf(old.getText());
                String newPas = String.valueOf(newPass.getText());
                String newconf = String.valueOf(newConfirm.getText());
                dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String timestamp = dateFormat.format(new Date());

                try {
                    new changePass().execute(oldPass,newPas,newconf,hashMac(timestamp,"0fab227b319afe10a0566183e5c7317dd23127b3f79a964481c0e08640f21acc"),timestamp,PublicToken);
                } catch (SignatureException e) {
                    e.printStackTrace();
                }

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    public static String toHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);

        Formatter formatter = new Formatter(sb);
        for (byte b : bytes) {
            formatter.format("%02x", b);
        }

        return sb.toString();
    }

    public String hashMac(String text, String secretKey) throws SignatureException {
        try {
            Key sk = new SecretKeySpec(secretKey.getBytes(), HASH_ALGORITHM);
            Mac mac = Mac.getInstance(sk.getAlgorithm());
            mac.init(sk);
            final byte[] hmac = mac.doFinal(text.getBytes());
            return toHexString(hmac);
        } catch (NoSuchAlgorithmException e1) {
            // throw an exception or pick a different encryption method
            throw new SignatureException(
                    "error building signature, no such algorithm in device "
                            + HASH_ALGORITHM);
        } catch (InvalidKeyException e) {
            throw new SignatureException(
                    "error building signature, invalid key " + HASH_ALGORITHM);
        }
    }

    public class changePass extends AsyncTask<String , String, String> {
        HttpURLConnection getRoute;
        URL url = null;


        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            System.out.println("fdsfdsfsd"+"ssssukses");

        }

        @Override
        protected String doInBackground(String... params){

            try{
                url = new URL("http://lab.hellomorra.com/jawapos/api/post-change-password");
            }
            catch (MalformedURLException e){
                e.printStackTrace();
                return "exception";
            }
            try {
                getRoute = (HttpURLConnection)url.openConnection();
                System.out.println("fdsfdsfsd"+getRoute);
                getRoute.setReadTimeout(10000);
                getRoute.setConnectTimeout(15000);
                getRoute.setRequestMethod("POST");
                getRoute.setDoInput(true);
                getRoute.setDoOutput(true);

                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("old_password", params[0])
                        .appendQueryParameter("new_password", params[1])
                        .appendQueryParameter("new_password_confirmation",params[2])
                        .appendQueryParameter("sign",params[3])
                        .appendQueryParameter("timestamp",params[4])
                        .appendQueryParameter("token",params[5]);

                String query = builder.build().getEncodedQuery();

                OutputStream os = getRoute.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                getRoute.connect();

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return "exception";
            }
            try {

                int response_code = getRoute.getResponseCode();
                System.out.println("fdsfdsfsd"+response_code);
                System.out.println("fdsfdsfsd"+HttpURLConnection.HTTP_OK);
                if (response_code == HttpURLConnection.HTTP_OK) {

                    InputStream input = getRoute.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    return(result.toString());


                }else{

                    return("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return "exception";
            } finally {
                getRoute.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject jobj = new JSONObject(result);
                System.out.println("fdsfdsfsd"+result);
                int status = (Integer) jobj.get("status");

                if (status==200){
                    imagedialog2.setMessage("Password berhasil diubah!");
                    imagedialog2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ad3.dismiss();
                            onBackPressed();
                        }
                    });
                    imagedialog2.create().show();

                    //ad3.dismiss();
                    //onBackPressed();
                }

                else if (status!=200){
                    ad3.dismiss();
                    imagedialog.setMessage("Anda belum memasukkan data / data yang anda masukkan salah");
                    imagedialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    imagedialog.create().show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
