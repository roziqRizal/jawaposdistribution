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
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.media.RemotePlaybackClient;
import android.text.InputType;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
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

    private Tracker mTracker;
    FrameLayout save,cancel;
    EditText old, newPass;
    LinearLayout back;
    ProgressDialog progressBar;
    private static final String HASH_ALGORITHM = "HmacSHA256";
    SimpleDateFormat dateFormat;
    DilatingDotsProgressBar mDilatingDotsProgressBar;
    AlertDialog ad3;
    AlertDialog.Builder imageDialog3, imagedialog, imagedialog2;
    String PublicToken;
    TextView TV7, TV8, TV9;
    Typeface Semiboldtype, Regulartype;
    ImageView mata1, mata2;
    boolean gone;
    String apkversion="/~9927";

    synchronized public Tracker getDefaultTracker(){
        if (mTracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
            mTracker = analytics.newTracker(R.xml.global_tracker);
        }
        return mTracker;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        overridePendingTransition(R.anim.changepasswordanim,R.anim.stopanim);

        Semiboldtype = Typeface.createFromAsset(getAssets(),  "fonts/SourceSansPro-Semibold.ttf");
        Regulartype = Typeface.createFromAsset(getAssets(),  "fonts/SourceSansPro-Regular.ttf");

        TV9 = (TextView)findViewById(R.id.textView9);
        TV8 = (TextView)findViewById(R.id.textView8);
        TV7 = (TextView)findViewById(R.id.textView7);
        TV9.setTypeface(Regulartype);
        TV8.setTypeface(Regulartype);
        TV7.setTypeface(Regulartype);

        mata1 = (ImageView)findViewById(R.id.imageView9);
        mata2 = (ImageView)findViewById(R.id.imageView10);

        progressBar = new ProgressDialog(this);
        progressBar.setCancelable(false);
        progressBar.setMessage("Waiting ...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        save = (FrameLayout) findViewById(R.id.button2);
        cancel = (FrameLayout) findViewById(R.id.button3);
        old = (EditText)findViewById(R.id.editText4);
        newPass = (EditText)findViewById(R.id.editText5);
        back = (LinearLayout) findViewById(R.id.topBar);


        old.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                mata1.setVisibility(View.VISIBLE);
                mata2.setVisibility(View.INVISIBLE);
            }
        });

        newPass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                mata1.setVisibility(View.INVISIBLE);
                mata2.setVisibility(View.VISIBLE);
            }
        });

        gone = false;
        System.out.println("Touch Down"+gone);

        old.setTypeface(Regulartype);
        newPass.setTypeface(Regulartype);

        mata1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch ( event.getAction() ) {
                    case MotionEvent.ACTION_DOWN:
                        old.setInputType(InputType.TYPE_CLASS_TEXT);
                        old.setTypeface(Regulartype);
                        newPass.setTypeface(Regulartype);
                        break;
                    case MotionEvent.ACTION_UP:
                        old.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        old.setTypeface(Regulartype);
                        newPass.setTypeface(Regulartype);
                        break;
                }
                return true;
            }
        });


        mata2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch ( event.getAction() ) {
                    case MotionEvent.ACTION_DOWN:
                        newPass.setInputType(InputType.TYPE_CLASS_TEXT);
                        old.setTypeface(Regulartype);
                        newPass.setTypeface(Regulartype);
                        break;
                    case MotionEvent.ACTION_UP:
                        newPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        old.setTypeface(Regulartype);
                        newPass.setTypeface(Regulartype);
                        break;
                }
                return true;
            }
        });




        Context context = getApplicationContext();
        appPrefs appPrefs = new appPrefs(context);
        PublicToken = appPrefs.getToken();
        String first_name = appPrefs.getFirstname();
        String last_name = appPrefs.getLastname();

        changePassword application = changePassword.this;
        mTracker = application.getDefaultTracker();

        Log.i("Change Password", "Setting screen name: " + first_name+" "+last_name);
        String uid = Build.MODEL;
        mTracker.setScreenName("ChangePassword~"+""+uid+"/"+ first_name+"_"+last_name+"_"+apkversion);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());


        imageDialog3 = new AlertDialog.Builder(this);
        imagedialog = new AlertDialog.Builder(this);
        imagedialog2 = new AlertDialog.Builder(this);

        Intent myIntent = getIntent(); // gets the previously created intent

        save.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch ( event.getAction() ) {
                    case MotionEvent.ACTION_DOWN:
                        save.setBackgroundColor(getResources().getColor(R.color.darkbutton));
                        break;
                    case MotionEvent.ACTION_UP:
                        save.setBackgroundColor(getResources().getColor(R.color.button));
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
                        dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                        String timestamp = dateFormat.format(new Date());

                        try {
                            new changePass().execute(oldPass,newPas,hashMac(timestamp,"0fab227b319afe10a0566183e5c7317dd23127b3f79a964481c0e08640f21acc"),timestamp,PublicToken);
                        } catch (SignatureException e) {
                            e.printStackTrace();
                        }
                        break;
                }
                return true;
            }
        });

        cancel.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch ( event.getAction() ) {
                    case MotionEvent.ACTION_DOWN:
                        cancel.setBackgroundColor(getResources().getColor(R.color.lightgrey));
                        break;
                    case MotionEvent.ACTION_UP:
                        cancel.setBackgroundColor(getResources().getColor(R.color.white));
                        onBackPressed();
                        break;
                }
                return true;
            }
        });

        back.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch ( event.getAction() ) {
                    case MotionEvent.ACTION_DOWN:
                        back.setBackgroundColor(getResources().getColor(R.color.basic));
                        break;
                    case MotionEvent.ACTION_UP:
                        back.setBackgroundColor(getResources().getColor(R.color.basic));
                        onBackPressed();
                        break;
                }
                return true;
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
                        .appendQueryParameter("sign",params[2])
                        .appendQueryParameter("timestamp",params[3])
                        .appendQueryParameter("token",params[4]);

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

                final AlertDialog alertDialog;

                if (status==200){

                    LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                    View layout = layoutInflater.inflate(R.layout.popupnotif, null);

                    imagedialog2.setView(layout);
                    imagedialog2.setCancelable(false);
                    alertDialog = imagedialog2.create();
                    alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    alertDialog.show();

                    TextView tv28, tv29, TV37;
                    TV37 = (TextView)layout.findViewById(R.id.textView36);
                    TV37.setText("Success!!!");
                    TV37.setTextSize(18);
                    TV37.setTypeface(Regulartype);
                    TV37.setTextColor(getResources().getColor(R.color.basic));
                    final FrameLayout frameLayout = (FrameLayout)layout.findViewById(R.id.frameLayout);
                    tv28 = (TextView)layout.findViewById(R.id.textView28);
                    tv29 = (TextView)layout.findViewById(R.id.textView29);

                    tv28.setTextSize(12);
                    tv29.setTextSize(12);


                    tv28.setText("Password was succesfully changed");
                    tv29.setText("OK");

                    tv28.setTypeface(Regulartype);
                    tv29.setTypeface(Semiboldtype);

                    frameLayout.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            switch ( event.getAction() ) {
                                case MotionEvent.ACTION_DOWN:
                                    frameLayout.setBackgroundColor(getResources().getColor(R.color.lightgrey));
                                    break;
                                case MotionEvent.ACTION_UP:
                                    frameLayout.setBackgroundColor(getResources().getColor(R.color.white));
                                    alertDialog.dismiss();
                                    onBackPressed();
                                    break;
                            }
                            return true;
                        }
                    });

                }

                else if (status!=200){
                    ad3.dismiss();

                    LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                    View layout = layoutInflater.inflate(R.layout.popupnotif, null);

                    imagedialog2.setView(layout);
                    imagedialog2.setCancelable(false);
                    alertDialog = imagedialog2.create();
                    alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    alertDialog.show();

                    TextView tv28, tv29, TV37;

                    TV37 = (TextView)layout.findViewById(R.id.textView36);
                    TV37.setText("Failed!!!");
                    TV37.setTextSize(18);
                    TV37.setTypeface(Regulartype);
                    TV37.setTextColor(getResources().getColor(R.color.button));

                    final FrameLayout frameLayout = (FrameLayout)layout.findViewById(R.id.frameLayout);
                    tv28 = (TextView)layout.findViewById(R.id.textView28);
                    tv29 = (TextView)layout.findViewById(R.id.textView29);

                    tv28.setTextSize(15);
                    tv29.setTextSize(12);


                    tv28.setText("You have not entered the data / the data is incorrect. Please enter again");
                    tv29.setText("OK");

                    tv28.setTypeface(Regulartype);
                    tv29.setTypeface(Semiboldtype);

                    frameLayout.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            switch ( event.getAction() ) {
                                case MotionEvent.ACTION_DOWN:
                                    frameLayout.setBackgroundColor(getResources().getColor(R.color.lightgrey));
                                    break;
                                case MotionEvent.ACTION_UP:
                                    frameLayout.setBackgroundColor(getResources().getColor(R.color.white));
                                    alertDialog.dismiss();
                                    break;
                            }
                            return true;
                        }
                    });
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
