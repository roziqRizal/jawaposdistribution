package com.example.mobidevelop.jpdistribution;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.IntegerRes;
import android.support.multidex.MultiDex;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.InputType;
import android.util.Base64;
import android.util.Log;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.vision.text.Text;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
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
import java.util.Arrays;
import java.util.Date;
import java.util.Formatter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import retrofit2.http.HTTP;

public class Launch extends AppCompatActivity {

    public static final int CONNECTION_TIMEOUT = 100000;
    public static final int READ_TIMEOUT = 150000;
    String results = "";
    String PublicToken = "";
    Location location;

    SQLiteDatabase db;

    private static final String HASH_ALGORITHM = "HmacSHA256";
    private EditText mEmailView;
    private EditText mPasswordView;
    SimpleDateFormat dateFormat;
    ProgressDialog progressBar;
    DilatingDotsProgressBar mDilatingDotsProgressBar;
    private int progressBarStatus = 0;
    private Handler progressBarHandler = new Handler();
    private long fileSize = 0;
    public LocationManager locmgr;
    TextView resetPassword;
    RelativeLayout blur;
    AlertDialog ad3;
    AlertDialog.Builder imageDialog3, imageDialog2;
    String routeID, StartID;
    JSONObject jobjroute;
    DataRute dbrute;
    AlertDialog.Builder alertdlgdownload, finish, notFinish, nullRute;
    JSONArray customer = null, customers = null, akhirFotoArray = null;
    public String[] contactNames;
    public String[] customerNotFinished, awalfoto;
    public String[][] path;
    String first_name, last_name, emailuser;
    String nama_depan, alamatStr, teleponStr, IDStr, pelangganIDStr, nama_belakang, latitudes, longitudes, photo, jenis_pelanggan, program_pelanggan;
    Bitmap save;
    private ProgressDialog progressDownloadImage;
    int jumpTime = 0;
    int totalProgressTime = 0;
    EditText satu,dua;
    String avatar="";
    ImageView remove1, remove2;
    View wahid, isnain, salasah, arbaah;
    TextView copyright, textView27;
    Typeface Regular, Semibold;
    boolean doubleBackToExitPressedOnce = false;
    FrameLayout buttontouch;
    String first_name2, last_name2, uId, uModel, apkversion="/~9927";

    private Tracker mTracker;


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
        setContentView(R.layout.activity_launch);

        overridePendingTransition(R.anim.startanim,R.anim.stopanim);
        copyright = (TextView)findViewById(R.id.textView6);
        textView27 = (TextView)findViewById(R.id.textView27);
        mEmailView = (EditText) findViewById(R.id.editText2);
        mPasswordView = (EditText) findViewById(R.id.editText);
        wahid = (View)findViewById(R.id.viewgradation);
        isnain = (View) findViewById(R.id.viewgradation2);
        salasah = (View) findViewById(R.id.viewgradation3);
        arbaah = (View)findViewById(R.id.viewgradation4);
        progressBar = new ProgressDialog(this);
        progressBar.setCancelable(false);
        progressBar.setMessage("Waiting ...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBarStatus = 0;
        fileSize = 0;
        locmgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //getLocation();
        imageDialog3 = new AlertDialog.Builder(this);
        imageDialog2 = new AlertDialog.Builder(this);
        nullRute = new AlertDialog.Builder(this);
        alertdlgdownload = new AlertDialog.Builder(this);

        buttontouch = (FrameLayout)findViewById(R.id.button);

        buttontouch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch ( event.getAction() ) {
                    case MotionEvent.ACTION_DOWN:
                        buttontouch.setBackground(getResources().getDrawable(R.drawable.customrectanglebuttondark));

                        break;
                    case MotionEvent.ACTION_UP:
                        buttontouch.setBackground(getResources().getDrawable(R.drawable.customrectanglebutton));
                        mTracker.send(new HitBuilders.EventBuilder().setCategory("LoginPage~/loginButton/~923"+apkversion)
                                .setAction(uModel+"/"+uId)
                                .build());
                        checkLogin();
                        break;
                }
                return true;
            }
        });

        remove1 = (ImageView)findViewById(R.id.imageView4);
        remove2 = (ImageView)findViewById(R.id.imageView5);

        progressDownloadImage=new ProgressDialog(this);
        //progressDownloadImage.setMessage("Download rute");
        progressDownloadImage.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDownloadImage.setProgress(0);

        dbrute = new DataRute(this);
        dbrute.connect();
        db = dbrute.getWritableDatabase();
        db.delete("DATA_PELANGGAN",null,null);
        db.delete("UPLOAD",null,null);
        db.delete("reasontext",null,null);
        //db.close();

        Context context = getApplicationContext();
        appPrefs appPrefs = new appPrefs(context);

        appPrefs.setgetRouteID("");
        appPrefs.setToken("");
        appPrefs.setFirstname("");
        appPrefs.setLastname("");
        appPrefs.setDurasihour("");
        appPrefs.setDurasimins("");

        first_name2 = appPrefs.getFirstname();
        last_name2 = appPrefs.getLastname();

        uId = Build.BRAND;
        uModel = Build.MODEL;

        Launch application = Launch.this;
        mTracker = application.getDefaultTracker();

        Log.i("Login Page", "Setting screen name: " + uId);
        mTracker.setScreenName("LoginPage~"+""+uId+"/"+uModel+"_"+apkversion);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());




        blur = (RelativeLayout)findViewById(R.id.blur);

        satu = (EditText)findViewById(R.id.editText);
        dua = (EditText)findViewById(R.id.editText2);

        remove1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dua.setText("");
            }
        });

        remove2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                satu.setText("");
            }
        });



        satu.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                mTracker.send(new HitBuilders.EventBuilder().setCategory("LoginPage~/passwordColumn/~923"+apkversion)
                        .setAction(uModel+"/"+uId)
                        .build());
                remove1.setVisibility(View.INVISIBLE);
                remove2.setVisibility(View.VISIBLE);
                wahid.setVisibility(View.INVISIBLE);
                salasah.setVisibility(View.VISIBLE);
                isnain.setVisibility(View.VISIBLE);
                arbaah.setVisibility(View.INVISIBLE);
            }
        });

        dua.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                mTracker.send(new HitBuilders.EventBuilder().setCategory("LoginPage~/emailColumn/~923"+apkversion)
                        .setAction(uModel+"/"+uId)
                        .build());
                remove1.setVisibility(View.VISIBLE);
                remove2.setVisibility(View.INVISIBLE);
                wahid.setVisibility(View.VISIBLE);
                salasah.setVisibility(View.INVISIBLE);
                isnain.setVisibility(View.INVISIBLE);
                arbaah.setVisibility(View.VISIBLE);
            }
        });

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Utopia-Bold.ttf");



        TextView loginfont = (TextView)findViewById(R.id.textView2);
        Typeface custom_font_login = Typeface.createFromAsset(getAssets(),  "fonts/SourceSansPro-Light.otf");

        Semibold = Typeface.createFromAsset(getAssets(),  "fonts/SourceSansPro-Semibold.ttf");
        Regular = Typeface.createFromAsset(getAssets(),  "fonts/SourceSansPro-Regular.ttf");

        textView27.setTypeface(Regular);
        loginfont.setTypeface(Regular);
        satu.setTypeface(Regular);
        dua.setTypeface(Regular);
        copyright.setTypeface(Regular);

        resetPassword = (TextView)findViewById(R.id.resetPassword);
        resetPassword.setTypeface(Regular);
        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Launch.this, resetPassword.class);
                mTracker.send(new HitBuilders.EventBuilder().setCategory("LoginPage~/resetPasswordButton/~923"+apkversion)
                        .setAction(uModel+"/"+uId)
                        .build());
                startActivity(intent);
            }
        });

    }

    public void getLocation(){
        boolean gps_active = locmgr.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gps_active) {
            AlertDialog.Builder alertdlg = new AlertDialog.Builder(this);
            alertdlg.setTitle("GPS tidak tersedia");
            alertdlg.setMessage("Aplikasi butuh akses GPS untuk lanjut.\n" + "aktifkan GPS sekarang?");
            alertdlg.setPositiveButton("YA", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(
                        DialogInterface dialog, int which) {
                    //turnGPSOn();
                    Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(i);
                }
            });
            alertdlg.setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(
                        DialogInterface dialog, int which) {
                    finish();
                }
            });
            alertdlg.create().show();
        }
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        locmgr.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                1000,
                1000, (android.location.LocationListener) loclistener);

        location = locmgr.getLastKnownLocation(LocationManager.GPS_PROVIDER);


    }

    android.location.LocationListener loclistener = new android.location.LocationListener() {
        public void onLocationChanged(Location location) {

        }

        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        public void onProviderEnabled(String provider) {

        }

        public void onProviderDisabled(String provider) {

        }
    };

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }

    public void checkLogin() {
        final String email = mEmailView.getText().toString();
        final String password = mPasswordView.getText().toString();

        final String fakeEmail = "paperboy@account.hellomorra.com";
        final String fakePassword="12345";

        final String fakeemail2 = "roziqrizal881992@gmail.com";
        final String fakepassword2 = "asd";
        dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String timestamp = dateFormat.format(new Date());

        try {
            new AsyncLogin().execute(email,password,hashMac(timestamp,"0fab227b319afe10a0566183e5c7317dd23127b3f79a964481c0e08640f21acc"),timestamp);
        } catch (SignatureException e) {
            e.printStackTrace();
        }
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

    private class AsyncLogin extends AsyncTask<String, String, String> {
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            blur.setAlpha((float) 1);

            LayoutInflater layoutInflater3 = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
            View layout3 = layoutInflater3.inflate(R.layout.downloadprogres, null);
            mDilatingDotsProgressBar = (DilatingDotsProgressBar) layout3.findViewById(R.id.progress);
            mDilatingDotsProgressBar.show();

            imageDialog3.setView(layout3);
            imageDialog3.setCancelable(false);
            ad3 = imageDialog3.create();
            ad3.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            ad3.show();

        }

        @Override
        protected String doInBackground (String... params){
            try{
                url = new URL("http://lab.hellomorra.com/jawapos/api/auth");
            }
            catch (MalformedURLException e){
                // TODO Auto-generated catch block
                e.printStackTrace();
                return null;
            }
            try{
                conn = (HttpURLConnection)url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");

                // setDoInput and setDoOutput method depict handling of both send and receive
                conn.setDoInput(true);
                conn.setDoOutput(true);

                // Append parameters to URL
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("email", params[0])
                        .appendQueryParameter("password", params[1])
                        .appendQueryParameter("sign",params[2])
                        .appendQueryParameter("timestamp",params[3]);
                String query = builder.build().getEncodedQuery();

                // Open connection for sending data
                OutputStream os = conn.getOutputStream();

                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                conn.connect();
            }
            catch (IOException e1){
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return null;
            }
            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line = null;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    results = result.toString();

                    // Pass data to onPostExecute method
                    return(result.toString());
                    //onPostExecute();

                }else{
                    Intent intent = new Intent(Launch.this,Launch.class);
                    startActivity(intent);
                    return null;
                }

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

            finally {
                conn.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String result) {

            try {
                JSONObject jobj = new JSONObject(results);
                String status = jobj.getString("status");



                int statusINT = Integer.parseInt(status);
                if (statusINT == 200){
                    JSONObject data = jobj.getJSONObject("data");



                    if (data.length()==3){
                        String token = (String) data.get("token");
                        JSONObject user = data.getJSONObject("user");
                        JSONObject data2 = user.getJSONObject("data");
                        first_name = (String) data2.get("first_name");
                        last_name = (String) data2.get("last_name");
                        avatar = (String) data2.get("avatar");
                        emailuser = (String) data2.get("email");

                        Context context = getApplicationContext();
                        appPrefs appPrefs = new appPrefs(context);
                        appPrefs.setToken(token);
                        appPrefs.setFirstname(first_name);
                        appPrefs.setLastname(last_name);
                        appPrefs.setEmail(emailuser);
                        String[] separated = avatar.split("_");

                        String urlimageprofile = separated[0]+"_default.jpg";

                        System.out.println("status load "+ jobj);

                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                        String timestamp1  = dateFormat.format(new Date());

                        PublicToken = token;



                        try {
                            dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                            String timestamp = dateFormat.format(new Date());
                            System.out.println("kksaskaks44444444444444"+avatar);

                            System.out.println("status load 2"+ jobj);

                            new DownloadImageTask().execute(avatar);
                            new GetRoute().execute(PublicToken, hashMac(timestamp, "0fab227b319afe10a0566183e5c7317dd23127b3f79a964481c0e08640f21acc"), timestamp);

                        } catch (SignatureException e) {
                            e.printStackTrace();
                        }
                    }

                    else {
                        LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                        View layout = layoutInflater.inflate(R.layout.popupnotif, null);

                        imageDialog2.setView(layout);
                        imageDialog2.setCancelable(false);
                        TextView TV28, TV29, TV37;
                        TV37 = (TextView)layout.findViewById(R.id.textView36);
                        TV37.setText("Failed!!!");
                        TV37.setTextSize(18);
                        TV37.setTypeface(Regular);
                        TV37.setTextColor(getResources().getColor(R.color.button));
                        final FrameLayout frameLayout = (FrameLayout)layout.findViewById(R.id.frameLayout);
                        TV28 = (TextView)layout.findViewById(R.id.textView28);
                        TV29 = (TextView)layout.findViewById(R.id.textView29);
                        TV28.setTypeface(Regular);
                        TV29.setTypeface(Semibold);
                        TV28.setText("Incorrect email / password");
                        TV29.setText("OK");
                        TV28.setTextSize(12);
                        TV29.setTextSize(12);

                        final AlertDialog alertDialog;
                        alertDialog = imageDialog2.create();
                        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        alertDialog.show();

                        frameLayout.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                switch ( event.getAction() ) {
                                    case MotionEvent.ACTION_DOWN:
                                        frameLayout.setBackgroundColor(getResources().getColor(R.color.lightgrey));
                                        break;
                                    case MotionEvent.ACTION_UP:
                                        frameLayout.setBackgroundColor(getResources().getColor(R.color.white));
                                        mTracker.send(new HitBuilders.EventBuilder().setCategory("LoginPage~/wrongEmail/~923"+apkversion)
                                                .setAction(uModel+"/"+uId)
                                                .setLabel("incorrect/unregistered~email~address")
                                                .build());
                                        alertDialog.dismiss();
                                        ad3.dismiss();
                                        break;
                                }
                                return true;
                            }
                        });

                    }


                }

                else {
                    LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                    View layout = layoutInflater.inflate(R.layout.popupnotif, null);

                    imageDialog2.setView(layout);
                    imageDialog2.setCancelable(false);
                    TextView TV28, TV29, TV37;

                    TV37 = (TextView)layout.findViewById(R.id.textView36);
                    TV37.setText("Failed!!!");
                    TV37.setTextSize(18);
                    TV37.setTypeface(Regular);
                    TV37.setTextColor(getResources().getColor(R.color.basic));
                    final FrameLayout frameLayout=(FrameLayout)layout.findViewById(R.id.frameLayout);
                    TV28 = (TextView)layout.findViewById(R.id.textView28);
                    TV29 = (TextView)layout.findViewById(R.id.textView29);
                    TV28.setTypeface(Regular);
                    TV29.setTypeface(Semibold);
                    TV28.setText("Email / password yang anda masukkan salah");
                    TV29.setText("OK");
                    TV28.setTextSize(12);
                    TV29.setTextSize(12);

                    final AlertDialog alertDialog;
                    alertDialog = imageDialog2.create();
                    alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    alertDialog.show();

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
                                    mTracker.send(new HitBuilders.EventBuilder().setCategory("LoginPage~/wrongEmail/~923"+apkversion)
                                            .setAction(uModel+"/"+uId)
                                            .setLabel("incorrect/unregistered~email~address")
                                            .build());
                                    ad3.dismiss();
                                    break;
                            }
                            return true;
                        }
                    });

                }



            } catch (JSONException e) {
                e.printStackTrace();
            }

        } // protected void onPostExecute(Void v)


    }

    public class GetRoute extends AsyncTask<String , String, String> {
        HttpURLConnection getRoute;
        URL url = null;


        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params){

            try{

                url = new URL("http://lab.hellomorra.com/jawapos/api/get-my-route");
            }
            catch (MalformedURLException e){
                e.printStackTrace();
                return "exception";
            }
            try {

                getRoute = (HttpURLConnection)url.openConnection();
                getRoute.setReadTimeout(READ_TIMEOUT);
                getRoute.setConnectTimeout(CONNECTION_TIMEOUT);
                getRoute.setRequestMethod("POST");
                getRoute.setDoInput(true);
                getRoute.setDoOutput(true);

                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("token", params[0])
                        .appendQueryParameter("sign", params[1])
                        .appendQueryParameter("timestamp",params[2]);
                String query = builder.build().getEncodedQuery();

                OutputStream os = getRoute.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                //getRoute.connect();

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return "exception";
            }
            try {

                int response_code = getRoute.getResponseCode();
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
                    Intent intent = new Intent(Launch.this,Launch.class);
                    startActivity(intent);
                    return("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return "exception";
            } finally {
                //getRoute.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            try {

                JSONObject jobjroute = new JSONObject(result);
                String status = jobjroute.getString("status");

                System.out.println("status load 3"+ jobjroute);

                JSONObject dataRoute = jobjroute.getJSONObject("data");
                JSONArray rute = dataRoute.getJSONArray("rute");

                if (rute.length() == 0){

                    LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                    View layout = layoutInflater.inflate(R.layout.popupnotif, null);

                    final AlertDialog alertDialog;
                    nullRute.setView(layout);
                    nullRute.setCancelable(false);
                    alertDialog = nullRute.create();
                    alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    alertDialog.show();

                    TextView tv28, tv29, TV37;
                    TV37 = (TextView)layout.findViewById(R.id.textView36);
                    TV37.setText("Failed!!!");
                    TV37.setTextSize(18);
                    TV37.setTypeface(Regular);
                    TV37.setTextColor(getResources().getColor(R.color.basic));
                    final FrameLayout frameLayout = (FrameLayout)layout.findViewById(R.id.frameLayout);
                    tv28 = (TextView)layout.findViewById(R.id.textView28);
                    tv29 = (TextView)layout.findViewById(R.id.textView29);

                    tv28.setTextSize(12);
                    tv29.setTextSize(12);


                    tv28.setText("You have no route");
                    tv29.setText("OK");

                    tv28.setTypeface(Regular);
                    tv29.setTypeface(Semibold);


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
                                    ad3.dismiss();
                                    break;
                            }
                            return true;
                        }
                    });

                }

                else {


                    JSONObject route = rute.getJSONObject(0);


                    routeID = route.getString("id");
                    dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    String timestamp = dateFormat.format(new Date());

                    System.out.println("status load 4"+ routeID);

                    Context context = getApplicationContext();
                    appPrefs appPrefs = new appPrefs(context);
                    appPrefs.setgetRouteID(routeID);

                    try {
                        new GetUserData().execute(PublicToken, routeID, hashMac(timestamp, "0fab227b319afe10a0566183e5c7317dd23127b3f79a964481c0e08640f21acc"), timestamp);
                    } catch (SignatureException e) {
                        e.printStackTrace();
                    }

                }

            } catch (JSONException e) {
                Log.e("JSONException", "Error: " + e.toString());
            } // catch (JSONException e)


        } // protected void onPostExecute(Void v)
    }

    public class GetUserData extends AsyncTask<String , Integer, String> {
        HttpURLConnection getRoute;
        URL url = null;



        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            System.out.println("status load 5 ");
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            // if we get here, length is known, now set indeterminate to false


        }



        @Override
        protected String doInBackground(String... params){

            try{
                url = new URL("http://lab.hellomorra.com/jawapos/api/get-customers-by-route");
            }
            catch (MalformedURLException e){
                e.printStackTrace();
                return "exception";
            }
            try {
                getRoute = (HttpURLConnection)url.openConnection();
                getRoute.setReadTimeout(READ_TIMEOUT);
                getRoute.setConnectTimeout(CONNECTION_TIMEOUT);
                getRoute.setRequestMethod("POST");
                getRoute.setDoInput(true);
                getRoute.setDoOutput(true);

                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("token", params[0])
                        .appendQueryParameter("rute_id", params[1])
                        .appendQueryParameter("sign", params[2])
                        .appendQueryParameter("timestamp",params[3]);
                String query = builder.build().getEncodedQuery();

                OutputStream os = getRoute.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                //getRoute.connect();

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return "exception";
            }
            try {

                int response_code = getRoute.getResponseCode();

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
                    Intent intent = new Intent(Launch.this,Launch.class);
                    startActivity(intent);
                    return("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return "exception";
            } finally {
                //getRoute.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String result) {


            try {
                jobjroute = new JSONObject(result);



                JSONObject dataUser = jobjroute.getJSONObject("data");
                JSONObject dataRute = dataUser.getJSONObject("rute");



                customers = dataUser.getJSONArray("customers");

                customer = dataRute.getJSONArray("customer");
                contactNames = new String[customers.length()];




                //System.out.println("testing "+customers.get(12));


                awalfoto = new String[customers.length()];
                path = new String[customers.length()][10];

                for (int i =0; i<customers.length();i++){
                    JSONObject loopCustomers = customers.getJSONObject(i);

                    System.out.println("status load 6 "+loopCustomers.getString("nama_depan"));

                    nama_depan = loopCustomers.getString("nama_depan");
                    nama_belakang = loopCustomers.getString("nama_belakang");
                    latitudes = loopCustomers.getString("lat");
                    longitudes = loopCustomers.getString("lng");
                    photo = loopCustomers.getString("photo");
                    alamatStr = loopCustomers.getString("alamat_1");
                    teleponStr = loopCustomers.getString("telepon_hp");
                    IDStr = loopCustomers.getString("id");
                    pelangganIDStr = loopCustomers.getString("pelanggan_id");





                }


                final JSONObject move = customer.getJSONObject(0);
                String latmove = move.getString("lat");
                String lngmove = move.getString("lng");
                LatLng latlngmove = new LatLng(Double.parseDouble(latmove),Double.parseDouble(lngmove));

                storeImage();

            } catch (JSONException e) {
                e.printStackTrace();
            }

        } // protected void onPostExecute(Void v)
    }

    public void storeImage(){
        try {
            String[] urlphotos = new String[customer.length()];
            System.out.println("status load 7 "+customer.length());
            for (int j=0; j<customer.length();j++){
                JSONObject loopCustomers = customers.getJSONObject(j);

                JSONArray photos = loopCustomers.getJSONArray("photos");
                awalfoto[j] = loopCustomers.getString("photo");

                for (int k=0;k<photos.length();k++){
                    JSONObject loopPhotos = photos.optJSONObject(k);
                    path[j][k] = loopPhotos.getString("path");

                }

                String urlPhoto = awalfoto[j]+"/"+path[j][0];

                if (path[j][0]==null){
                    urlphotos[j]=null;
                }
                else {
                    urlphotos[j] = urlPhoto;

                }

            }

            new DownloadImage().execute(urlphotos);




        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            System.out.println("kksaskaks12112");

        }

        @Override
        protected Bitmap doInBackground(String... URL) {

            String imageURL = URL[0];

            Bitmap bitmap1 = null;

            System.out.println("kksaskaksroziq "+URL[0]);
            byte[] img=null;
            //if (URL[0]!=null){
            InputStream input = null;
            //try {
            try {
                input = new URL(imageURL).openStream();
                System.out.println("kksaskaksroziq "+input);

                bitmap1 = BitmapFactory.decodeStream(input);

                System.out.println("kksaskaks3333"+bitmap1);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                bitmap1.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                img = bos.toByteArray();
                Bitmap bmp1 = BitmapFactory.decodeByteArray(img,0,img.length);
                Context context = getApplicationContext();
                appPrefs appPrefs = new appPrefs(context);
                String s = Base64.encodeToString(img, Base64.DEFAULT);
                appPrefs.setImage(s);


            } catch (IOException e) {
                e.printStackTrace();
            }


                // Decode Bitmap


           // } catch (IOException e) {
            //    e.printStackTrace();
            //}
            return bitmap1;
        }

        @Override
        protected void onPostExecute(Bitmap result) {


        }
    }

    private class DownloadImage extends AsyncTask<String, Void, Bitmap> {

        LayoutInflater layoutInflater2 = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout2 = layoutInflater2.inflate(R.layout.popupnotifdownload, null);
        ProgressBar progressBar = (ProgressBar)layout2.findViewById(R.id.progressBar);
        AlertDialog alertDialogdownload;
        TextView TV31 = (TextView)layout2.findViewById(R.id.textView31);
        TextView TV32 = (TextView)layout2.findViewById(R.id.textView32);
        TextView TV33 = (TextView)layout2.findViewById(R.id.textView33);
        TextView TV34 = (TextView)layout2.findViewById(R.id.textView34);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ad3.dismiss();

            TV34.setText("Downloading data");
            TV32.setText("Please wait, downloading process is running");

            TV31.setTypeface(Regular);
            TV32.setTypeface(Regular);
            TV33.setTypeface(Regular);
            TV34.setTypeface(Regular);

            TV34.setTextSize(18);
            TV31.setTextSize(12);
            TV32.setTextSize(12);
            TV33.setTextSize(12);

            alertdlgdownload.setView(layout2);
            alertdlgdownload.setCancelable(false);
            alertDialogdownload = alertdlgdownload.create();
            progressBar.setMax(customer.length());
            alertDialogdownload.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            alertDialogdownload.show();


        }

        @Override
        protected Bitmap doInBackground(String... URL) {
            Bitmap bitmap = null;
            int persen = 0;

            for (int i=0;i<URL.length;i++){

                try {
                    JSONObject loopCustomers = customers.getJSONObject(i);

                    nama_depan = loopCustomers.getString("nama_depan");
                    nama_belakang = loopCustomers.getString("nama_belakang");
                    latitudes = loopCustomers.getString("lat");
                    longitudes = loopCustomers.getString("lng");
                    photo = loopCustomers.getString("photo");
                    alamatStr = loopCustomers.getString("alamat_1");
                    teleponStr = loopCustomers.getString("telepon_hp");
                    IDStr = loopCustomers.getString("id");
                    pelangganIDStr = loopCustomers.getString("pelanggan_id");
                    jenis_pelanggan = loopCustomers.getString("jenis_pelanggan");
                    program_pelanggan = loopCustomers.getString("program");

                    if (program_pelanggan == null){
                        program_pelanggan = "belum ada isinya";
                    }

                    String nama_depan1 = nama_depan.toLowerCase();
                    String nama_belakang1 = nama_belakang.toLowerCase();
                    String alamatStr1 = alamatStr.toLowerCase();


                    String cap = nama_depan1.substring(0, 1).toUpperCase() + nama_depan1.substring(1);
                    String cap1 = alamatStr1.substring(0, 1).toUpperCase() + alamatStr1.substring(1);

                    try {
                        // Download Image from URL
                        byte[] img=null;
                        String s=null;
                        if (URL[i]!=null){
                            InputStream input = new java.net.URL(URL[i]).openStream();
                            // Decode Bitmap
                            bitmap = BitmapFactory.decodeStream(input);
                            ByteArrayOutputStream bos = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                            img = bos.toByteArray();
                            s = Base64.encodeToString(img, Base64.DEFAULT);

                        }

                        dbrute.connect();

                        SQLiteDatabase db = dbrute.getWritableDatabase();

                        System.out.println("testing "+i+" "+cap);

                        ContentValues values = new ContentValues();
                        values.put("foto",s);
                        values.put("id",IDStr);
                        values.put("nama",cap+" "+nama_belakang1);
                        values.put("alamat", cap1); // Shop Name
                        values.put("telepon", teleponStr); // Shop Phone Number
                        values.put("lat", latitudes);
                        values.put("long",longitudes);
                        values.put("spare1",jenis_pelanggan);
                        values.put("spare2",program_pelanggan);

                        db.insert("DATA_PELANGGAN", null, values);
                        db.close();

                        jumpTime++;



                        downloadProgressPopUp(i);


                        final int abe=i;
                        final int abe2=persen;
                        TV31.post(new Runnable() {
                            public void run() {
                                //TV31.setText(""+abe+" / "+customer.length());
                            }
                        });

                        TV33.post(new Runnable() {
                            @Override
                            public void run() {
                                TV33.setText(""+abe+" / "+customer.length());
                            }
                        });

                        progressBar.post(new Runnable() {
                            public void run() {
                                progressBar.setProgress(abe);
                            }
                        });
                        //progressBar.setProgress(i);
                        //TV31.setText(""+i);
                        //progressBar.setProgress(i);

                } catch (Exception e) {
                    e.printStackTrace();
                }} catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            String imageURL = URL[0];



            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            alertDialogdownload.dismiss();



            Intent intent = new Intent(Launch.this, MainActivity.class);
            intent.putExtra("routeID",routeID);
            startActivity(intent);

        }
    }

    public void downloadProgressPopUp(int inputan){
        LayoutInflater layoutInflater2 = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout2 = layoutInflater2.inflate(R.layout.popupnotifdownload, null);
        TextView TV31 = (TextView)layout2.findViewById(R.id.textView31);
        TextView TV32 = (TextView)layout2.findViewById(R.id.textView32);
        TextView TV33 = (TextView)layout2.findViewById(R.id.textView33);

        double persen = inputan/customer.length();

        System.out.println("persen "+persen/customer.length());
    }

    AlertDialog backonprevious;
    @Override
    public void onBackPressed() {

        LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.popupnotiftomboldua, null);

        if (doubleBackToExitPressedOnce) {

            AlertDialog.Builder backprevious = new AlertDialog.Builder(this);
            backprevious.setView(layout);
            TextView TV28, TV29, TV30, TV37;
            TV37 = (TextView)layout.findViewById(R.id.textView37);
            TV37.setText("Exit application");
            TV37.setTextSize(18);
            TV37.setTypeface(Regular);
            TV37.setTextColor(getResources().getColor(R.color.basic));
            final FrameLayout frameLayout, framelayout2;
            frameLayout = (FrameLayout)layout.findViewById(R.id.frameLayout);
            framelayout2 = (FrameLayout)layout.findViewById(R.id.frameLayout2);
            TV28 = (TextView)layout.findViewById(R.id.textView28);
            TV29 = (TextView)layout.findViewById(R.id.textView29);
            TV30 = (TextView)layout.findViewById(R.id.textView30);

            TV28.setTypeface(Regular);
            TV29.setTypeface(Semibold);
            TV30.setTypeface(Semibold);

            TV28.setTextSize(12);
            TV29.setTextSize(12);
            TV30.setTextSize(12);

            TV28.setText("Are you sure to quit ?");
            TV29.setText("YES");
            TV30.setText("NO");

            backonprevious = backprevious.create();
            backonprevious.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            backonprevious.show();

            frameLayout.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch ( event.getAction() ) {
                        case MotionEvent.ACTION_DOWN:
                            frameLayout.setBackgroundColor(getResources().getColor(R.color.lightgrey));
                            break;
                        case MotionEvent.ACTION_UP:
                            frameLayout.setBackgroundColor(getResources().getColor(R.color.white));
                            finishActivity();
                            break;
                    }
                    return true;
                }
            });

            framelayout2.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch ( event.getAction() ) {
                        case MotionEvent.ACTION_DOWN:
                            framelayout2.setBackgroundColor(getResources().getColor(R.color.lightgrey));
                            break;
                        case MotionEvent.ACTION_UP:
                            framelayout2.setBackgroundColor(getResources().getColor(R.color.white));
                            backonprevious.dismiss();
                            break;
                    }
                    return true;
                }
            });




            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "tab back again to quit" , Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/
    }

    public void finishActivity(){
        this.finishAffinity();
    }


}
