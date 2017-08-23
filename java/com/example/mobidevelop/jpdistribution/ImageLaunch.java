package com.example.mobidevelop.jpdistribution;

import android.*;
import java.util.Calendar;
import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.support.v4.content.ContextCompat;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import org.json.JSONArray;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class ImageLaunch extends AppCompatActivity {

    AlertDialog.Builder alertdlg;

    String PublicToken;
    private static final String HASH_ALGORITHM = "HmacSHA256";
    SimpleDateFormat dateFormat;
    Location location;
    public LocationManager locmgr;
    DataRute dbrute;

    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;

    boolean doubleBackToExitPressedOnce = false;
    RelativeLayout pressagain;
    Typeface Semibold, Regular;
    DilatingDotsProgressBar mDilatingDotsProgressBar;
    TextView TV34;
    Context context;
    AlertDialog.Builder imageDialog3;
    AlertDialog ad3;
    ImageView locationimage;
    String uModel,uBrand, apkversion="/~9927",uID,uDevice,uBootloader,uDisplay, uSerial;

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
        setContentView(R.layout.activity_image_launch);



        overridePendingTransition(R.anim.startanim,R.anim.stopanim);

        context = getApplicationContext();

        Semibold = Typeface.createFromAsset(getAssets(), "fonts/SourceSansPro-Semibold.ttf");
        Regular = Typeface.createFromAsset(getAssets(), "fonts/SourceSansPro-Regular.ttf");

        pressagain = (RelativeLayout) findViewById(R.id.pressagain);

        int currentapiVersion = android.os.Build.VERSION.SDK_INT;


        TV34 = (TextView)findViewById(R.id.textView34);

        imageDialog3 = new AlertDialog.Builder(this);

        Toast.makeText(context, "find user location...", Toast.LENGTH_LONG).show();

        //mDilatingDotsProgressBar = (DilatingDotsProgressBar)findViewById(R.id.progress);
        //mDilatingDotsProgressBar.show();


        LayoutInflater layoutInflater3 = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout3 = layoutInflater3.inflate(R.layout.gpssetting, null);
        mDilatingDotsProgressBar = (DilatingDotsProgressBar)layout3.findViewById(R.id.progress);
        mDilatingDotsProgressBar.show();

        TextView textView38=(TextView)layout3.findViewById(R.id.textView38);
        textView38.setTextSize(12);
        textView38.setTypeface(Regular);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        imageDialog3.setView(layout3);
        imageDialog3.setCancelable(false);
        locationimage = (ImageView)layout3.findViewById(R.id.imageView11);
        locationimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }
        });
        ad3 = imageDialog3.create();



        ad3.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ad3.setCancelable(false);

        dbrute = new DataRute(this);
        alertdlg = new AlertDialog.Builder(this);

        appPrefs appPrefs = new appPrefs(context);
        locmgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        PublicToken = appPrefs.getToken();


        String first_name = appPrefs.getFirstname();
        String last_name = appPrefs.getLastname();

        ImageLaunch application = ImageLaunch.this;
        mTracker = application.getDefaultTracker();

        Log.i("Splash Screen", "Setting screen name: " + first_name+" "+last_name);
        uModel = Build.MODEL;
        uBrand = Build.BRAND;
        uID = Build.ID;
        uDevice = Build.DEVICE;
        uBootloader = Build.BOOTLOADER;
        uDisplay = Build.DISPLAY;
        uSerial = Build.SERIAL;


        mTracker.setScreenName("SplashScreen~"+""+uBrand+"/"+uModel+"_"+apkversion);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

        mTracker.send(new HitBuilders.EventBuilder().setCategory("APKVersion"+apkversion)
                .setAction(uBrand+"/"+uModel+"/"+uSerial)
                .setLabel(uDevice+"/"+uID+"/"+uBootloader+"/"+uDisplay)
                .build());


        String membingungkan = "qurtubi";

        dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        final String timestamp = dateFormat.format(new Date());



        TV34.setTextSize(12);
        TV34.setTypeface(Regular);
        //TV34.setText("mencari lokasi user..");

        getLocation();

        Toast.makeText(context, "find user location...", Toast.LENGTH_LONG).show();



        if (currentapiVersion >= 23) {
            insertDummyContactWrapper();
        }

        pressagain.setClickable(false);

        pressagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    new GetRoute().execute(PublicToken, hashMac(timestamp, "0fab227b319afe10a0566183e5c7317dd23127b3f79a964481c0e08640f21acc"), timestamp);
                    pressagain.setClickable(false);
                } catch (SignatureException e) {
                    e.printStackTrace();
                }
            }
        });


        try {
            new GetRoute().execute(PublicToken, hashMac(timestamp, "0fab227b319afe10a0566183e5c7317dd23127b3f79a964481c0e08640f21acc"), timestamp);
        } catch (SignatureException e) {
            e.printStackTrace();
        }
    }

    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

    @TargetApi(Build.VERSION_CODES.M)
    private void insertDummyContactWrapper() {
        int hasWriteContactsPermission = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            hasWriteContactsPermission = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_CODE_ASK_PERMISSIONS);
            return;
        }
        //insertDummyContact();
    }


    public void getLocation() {
        boolean gps_active = locmgr.isProviderEnabled(LocationManager.GPS_PROVIDER);

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
                10000,
                10000, (android.location.LocationListener) loclistener);
        location = locmgr.getLastKnownLocation(LocationManager.GPS_PROVIDER);


    }

    android.location.LocationListener loclistener = new android.location.LocationListener() {
        public void onLocationChanged(Location location) {
            ad3.dismiss();
            pressagain.setClickable(true);
            mDilatingDotsProgressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(context, "user location founded, press screen..", Toast.LENGTH_SHORT).show();
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        public void onProviderEnabled(String provider) {
            Toast.makeText(context, "GPS on", Toast.LENGTH_SHORT).show();
        }

        public void onProviderDisabled(String provider) {
            if (ad3.isShowing()){
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }
        }
    };

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

    @Override
    public void onBackPressed() {
        //Intent setIntent = new Intent(ImageLaunch.this,ImageLaunch.class);
        //startActivity(setIntent);

        if (doubleBackToExitPressedOnce) {
            this.finishAffinity();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "tekan sekali lagi untuk keluar ", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);

    }

    public class GetRoute extends AsyncTask<String , String, String> {
        HttpURLConnection getRoute;
        HttpURLConnection jawapos;
        URL url = null;
        URL url2 = null;


        @Override
        protected void onPreExecute(){
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params){

            try{
                url = new URL("http://lab.hellomorra.com/jawapos/api/get-my-route");
                url2 = new URL("http://lab.hellomorra.com");
            }
            catch (MalformedURLException e){
                e.printStackTrace();
                return "exception";
            }
            try {
                jawapos = (HttpURLConnection)url2.openConnection();
                jawapos.connect();

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
                return null;
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

                    return null;
                }

            } catch (IOException e) {
                Intent imageLaunch = new Intent(ImageLaunch.this,ImageLaunch.class);
                startActivity(imageLaunch);
                e.printStackTrace();
                return null;
            } finally {
                //getRoute.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            try {

                JSONObject jobjroute = new JSONObject(result);
                String status = jobjroute.getString("status");

                getLocation();

                LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View layout = layoutInflater.inflate(R.layout.popupnotif, null);

                if (location==null){
                    alertdlg.setView(layout);
                    alertdlg.setCancelable(false);
                    TextView TV28, TV29, TV37;
                    TV37 = (TextView)layout.findViewById(R.id.textView36);
                    TV37.setText("GPS Signal Required");
                    TV37.setTextSize(18);
                    TV37.setTypeface(Regular);
                    TV37.setTextColor(getResources().getColor(R.color.basic));
                    final FrameLayout frameLayout = (FrameLayout)layout.findViewById(R.id.frameLayout);
                    TV28 = (TextView)layout.findViewById(R.id.textView28);
                    TV29 = (TextView)layout.findViewById(R.id.textView29);
                    TV28.setTypeface(Regular);
                    TV29.setTypeface(Semibold);
                    TV28.setText("Please turn on the GPS and make the permission setting is on. Please see documentation for details");
                    TV29.setText("Continue");
                    TV28.setTextSize(12);
                    TV29.setTextSize(12);

                    final AlertDialog alertDialog;
                    alertDialog = alertdlg.create();
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
                                    pressagain.setClickable(true);

                                    ad3.show();
                                    Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                    //int flags = i.getFlags();
                                    startActivity(i);
                                    break;
                            }
                            return true;
                        }
                    });

                }

                else {
                    if (status.contains("401")){



                        dbrute.connect();
                        SQLiteDatabase db = dbrute.getWritableDatabase();
                        db.delete("DATA_PELANGGAN",null,null);
                        db.close();
                        Intent backToLogin = new Intent(ImageLaunch.this,Launch.class);
                        startActivity(backToLogin);

                    }

                    else {


                        new Handler().postDelayed(new Runnable() {

                            @Override
                            public void run() {

                                Toast.makeText(context, "logging-in...", Toast.LENGTH_SHORT).show();
                                Intent toMain = new Intent(ImageLaunch.this,MainActivity.class);
                                startActivity(toMain);
                            }
                        }, 5000);

                    }
                }



            } catch (JSONException e) {
                Log.e("JSONException", "Error: " + e.toString());
            } // catch (JSONException e)


        } // protected void onPostExecute(Void v)
    }

    public void waiting(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);

            }
        }, 2000);
    }


}
