package com.example.mobidevelop.jpdistribution;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;

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
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class ServiceGPS extends Service {

    public static final int CONNECTION_TIMEOUT = 100000;
    public static final int READ_TIMEOUT = 150000;

    SimpleDateFormat dateFormat;
    String PublicToken;
    Intent myIntent;
    Location location;
    //Context context;
    public LocationManager locmgr;
    private static final String HASH_ALGORITHM = "HmacSHA256";

    /*public class IabHelper {
        public IabHelper(Context ctx) {
            context = ctx.getApplicationContext();
        }
    }

    IabHelper helper;

    public ServiceGPS() {
        //appPrefs appPrefs = new appPrefs(context);
        //PublicToken = appPrefs.getToken();

    }*/


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

    class Logic1 extends Thread {

        public void run() {
            for (int i = 0; i < 10; i++) {
                Thread s = Thread.currentThread();



                try {
                    s.sleep(1000);              // these are the three types of way i called sleep method
                    Thread.sleep(1000);         //
                    this.sleep(1000);           //
                } catch (Exception e) {

                }
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        /*Thread t = new Thread(new Logic1());
        t.start();
        locmgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

        }
        locmgr.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                0,
                0, (android.location.LocationListener) loclistener);
        location = locmgr.getLastKnownLocation(LocationManager.GPS_PROVIDER);
*/
        return super.onStartCommand(intent, flags, startId);
    }

    android.location.LocationListener loclistener = new android.location.LocationListener() {
        public void onLocationChanged(Location location) {
            System.out.println("Latitude value : "+location.getLatitude());
            System.out.println("Longitude value : "+location.getLongitude());

            try {
                dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String timestamp = dateFormat.format(new Date());

                new LifeTracking().execute(PublicToken, hashMac(timestamp, "0fab227b319afe10a0566183e5c7317dd23127b3f79a964481c0e08640f21acc"), String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude()), timestamp);

            } catch (SignatureException e) {
                e.printStackTrace();
            }

        }

        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        public void onProviderEnabled(String provider) {

        }

        public void onProviderDisabled(String provider) {


        }
    };


    public class LifeTracking extends AsyncTask<String , String, String> {
        HttpURLConnection track;
        URL url = null;


        @Override
        protected void onPreExecute(){
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params){

            try{
                url = new URL("http://lab.hellomorra.com/jawapos/api/set-my-position");
            }
            catch (MalformedURLException e){
                e.printStackTrace();
                return "exception";
            }
            try {
                track = (HttpURLConnection)url.openConnection();
                track.setReadTimeout(READ_TIMEOUT);
                track.setConnectTimeout(CONNECTION_TIMEOUT);
                track.setRequestMethod("POST");
                track.setDoInput(true);
                track.setDoOutput(true);



                dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String timestamp = dateFormat.format(new Date());

                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("token", params[0])
                        .appendQueryParameter("sign", params[1])
                        .appendQueryParameter("lat",params[2])
                        .appendQueryParameter("lng",params[3])
                        .appendQueryParameter("timestamp",params[4]);
                String query = builder.build().getEncodedQuery();

                OutputStream os = track.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                track.connect();


            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return "exception";
            }
            try {

                int response_code = track.getResponseCode();



                if (response_code == HttpURLConnection.HTTP_OK) {

                    InputStream input = track.getInputStream();
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

                track.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            System.out.println("hasil "+result);

        }
    }


}
