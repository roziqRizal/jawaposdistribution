package com.example.mobidevelop.jpdistribution;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class resetPassword extends AppCompatActivity {

    FrameLayout cancel, save;
    ProgressDialog progressBar;
    EditText emailText;
    SimpleDateFormat dateFormat;
    private static final String HASH_ALGORITHM = "HmacSHA256";
    String email;
    LinearLayout topBarReset;
    DilatingDotsProgressBar mDilatingDotsProgressBar;
    AlertDialog ad3;
    AlertDialog.Builder imageDialog3, imagedialog2, imagedialog, imagedialog4;
    TextView canceltext, resettext, topbartitle, resettitle, info1, info2;
    Typeface Semibold, Regular;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password2);

        resettext = (TextView) findViewById(R.id.textView7);
        canceltext = (TextView) findViewById(R.id.textView8);
        topbartitle = (TextView) findViewById(R.id.textView9);
        resettitle = (TextView) findViewById(R.id.textView12);
        info1 = (TextView) findViewById(R.id.textView13);
        info2 = (TextView) findViewById(R.id.textView14);
        imageDialog3 = new AlertDialog.Builder(this);
        imagedialog2 = new AlertDialog.Builder(this);
        imagedialog = new AlertDialog.Builder(this);
        imagedialog4 = new AlertDialog.Builder(this);

        Semibold = Typeface.createFromAsset(getAssets(),  "fonts/SourceSansPro-Semibold.ttf");
        Regular = Typeface.createFromAsset(getAssets(),  "fonts/SourceSansPro-Regular.ttf");

        emailText = (EditText)findViewById(R.id.emailReset);
        emailText.setTypeface(Regular);
        progressBar = new ProgressDialog(this);
        progressBar.setCancelable(false);
        progressBar.setMessage("Waiting ...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        topBarReset = (LinearLayout) findViewById(R.id.topBar);
        topBarReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        resettext.setTypeface(Semibold);
        canceltext.setTypeface(Semibold);
        topbartitle.setTypeface(Regular);
        resettitle.setTypeface(Regular);
        info1.setTypeface(Regular);
        info2.setTypeface(Regular);


        dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        final String timestamp = dateFormat.format(new Date());

        cancel = (FrameLayout) findViewById(R.id.button3);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        save = (FrameLayout) findViewById(R.id.button2);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    LayoutInflater layoutInflater3 = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                    View layout3 = layoutInflater3.inflate(R.layout.downloadprogres, null);
                    mDilatingDotsProgressBar = (DilatingDotsProgressBar) layout3.findViewById(R.id.progress);
                    mDilatingDotsProgressBar.show();

                    imageDialog3.setView(layout3);
                    imageDialog3.setCancelable(false);
                    ad3 = imageDialog3.create();
                    ad3.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    ad3.show();

                    email = emailText.getText().toString();
                    String fakeemail = "roziqrizal881992@gmail.com";

                    new resetAsyntask().execute(email,hashMac(timestamp,"0fab227b319afe10a0566183e5c7317dd23127b3f79a964481c0e08640f21acc"),timestamp);

                } catch (SignatureException e) {
                    e.printStackTrace();
                }
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

    public String hashMac(String text, String secretKey) throws SignatureException{
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


    public class resetAsyntask extends AsyncTask<String , String, String> {
        HttpURLConnection startTracking;
        URL url = null;


        @Override
        protected void onPreExecute(){
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params){

            try{
                url = new URL("http://lab.hellomorra.com/jawapos/api/post-forget-password");
            }
            catch (MalformedURLException e){
                e.printStackTrace();
                return "exception";
            }
            try {
                startTracking = (HttpURLConnection)url.openConnection();
                startTracking.setReadTimeout(10000);
                startTracking.setConnectTimeout(15000);
                startTracking.setRequestMethod("POST");
                startTracking.setDoInput(true);
                startTracking.setDoOutput(true);


                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("email", params[0])
                        .appendQueryParameter("sign", params[1])
                        .appendQueryParameter("timestamp",params[2]);
                String query = builder.build().getEncodedQuery();

                OutputStream os = startTracking.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));


                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                startTracking.connect();

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return "exception";
            }
            try {

                int response_code = startTracking.getResponseCode();

                if (response_code == HttpURLConnection.HTTP_OK) {


                    InputStream input = startTracking.getInputStream();
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
                startTracking.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject jobj = new JSONObject(result);

                int duaratus = (Integer) jobj.get("status");

                if (duaratus==200){
                    JSONObject data = jobj.getJSONObject("data");
                    boolean success = (boolean) data.getBoolean("success");

                    if (success==true){
                        LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                        View layout = layoutInflater.inflate(R.layout.popupnotif, null);

                        imagedialog.setView(layout);
                        imagedialog.setCancelable(false);
                        TextView TV28, TV29;
                        TV28 = (TextView)layout.findViewById(R.id.textView28);
                        TV29 = (TextView)layout.findViewById(R.id.textView29);
                        TV28.setTypeface(Regular);
                        TV29.setTypeface(Semibold);
                        TV28.setText("Password anda sudah berganti. silahkan cek email anda!");
                        TV29.setText("OK");
                        TV28.setTextSize(18);
                        TV29.setTextSize(19);

                        final AlertDialog alertDialog;
                        alertDialog = imagedialog.create();
                        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        alertDialog.show();

                        TV29.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ad3.dismiss();
                                alertDialog.dismiss();
                                onBackPressed();
                            }
                        });


/*                        imagedialog.setMessage("Password anda sudah berganti. silahkan cek email anda!");
                        imagedialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ad3.dismiss();
                                onBackPressed();
                            }
                        });
                        imagedialog.create().show();
*/                    }

                    else if (success==false){

                        LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                        View layout = layoutInflater.inflate(R.layout.popupnotif, null);

                        imagedialog.setView(layout);
                        imagedialog.setCancelable(false);
                        TextView TV28, TV29;
                        TV28 = (TextView)layout.findViewById(R.id.textView28);
                        TV29 = (TextView)layout.findViewById(R.id.textView29);
                        TV28.setTypeface(Regular);
                        TV29.setTypeface(Semibold);
                        TV28.setText("Alamat email yang anda masukkan salah / belum terdaftar");
                        TV29.setText("OK");
                        TV28.setTextSize(18);
                        TV29.setTextSize(19);

                        final AlertDialog alertDialog;
                        alertDialog = imagedialog.create();
                        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        alertDialog.show();

                        TV29.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ad3.dismiss();
                                alertDialog.dismiss();
                            }
                        });

/*                        ad3.dismiss();
                        imagedialog4.setMessage("Alamat email yang anda masukkan salah / belum terdaftar");
                        imagedialog4.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        imagedialog4.create().show();
*/                    }

                }

                else if (duaratus!=200){

                    LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                    View layout = layoutInflater.inflate(R.layout.popupnotif, null);

                    imagedialog.setView(layout);
                    imagedialog.setCancelable(false);
                    TextView TV28, TV29;
                    TV28 = (TextView)layout.findViewById(R.id.textView28);
                    TV29 = (TextView)layout.findViewById(R.id.textView29);
                    TV28.setTypeface(Regular);
                    TV29.setTypeface(Semibold);
                    TV28.setText("Field email belum diisi");
                    TV29.setText("OK");
                    TV28.setTextSize(18);
                    TV29.setTextSize(19);

                    final AlertDialog alertDialog;
                    alertDialog = imagedialog.create();
                    alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    alertDialog.show();

                    TV29.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ad3.dismiss();
                            alertDialog.dismiss();
                        }
                    });
/*                    ad3.dismiss();
                    imagedialog2.setMessage("Field email belum diisi");
                    imagedialog2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    imagedialog2.create().show();
*/                }



            } catch (JSONException e) {
                e.printStackTrace();
            }



        } // protected void onPostExecute(Void v)

        private boolean convertIntToBool(int value) {
            if(value==0) {
                return false;
            } else {
                return true;
            }
        }
    }


}
