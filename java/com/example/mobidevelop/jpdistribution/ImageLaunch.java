package com.example.mobidevelop.jpdistribution;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_launch);

        dbrute = new DataRute(this);
        alertdlg = new AlertDialog.Builder(this);
        Context context = getApplicationContext();
        appPrefs appPrefs = new appPrefs(context);
        locmgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        PublicToken = appPrefs.getToken();
        String membingungkan = "qurtubi";

        dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String timestamp = dateFormat.format(new Date());

        getLocation();

        System.out.println(PublicToken);


        try {
            new GetRoute().execute(PublicToken, hashMac(timestamp, "0fab227b319afe10a0566183e5c7317dd23127b3f79a964481c0e08640f21acc"), timestamp);
        } catch (SignatureException e) {
            e.printStackTrace();
        }
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

    public class GetRoute extends AsyncTask<String , String, String> {
        HttpURLConnection getRoute;
        HttpURLConnection jawapos;
        URL url = null;
        URL url2 = null;


        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            System.out.println("1");
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

                System.out.println("jawapos = "+jawapos.getResponseMessage() );

                getRoute = (HttpURLConnection)url.openConnection();
                getRoute.setReadTimeout(READ_TIMEOUT);
                getRoute.setConnectTimeout(CONNECTION_TIMEOUT);
                getRoute.setRequestMethod("POST");
                getRoute.setDoInput(true);
                getRoute.setDoOutput(true);

                System.out.println("2");

                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("token", params[0])
                        .appendQueryParameter("sign", params[1])
                        .appendQueryParameter("timestamp",params[2]);
                String query = builder.build().getEncodedQuery();
                System.out.println(builder);

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

                System.out.println(response_code);
                System.out.println(HttpURLConnection.HTTP_OK);

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

                System.out.println(result);
                JSONObject jobjroute = new JSONObject(result);
                String status = jobjroute.getString("status");
                System.out.println(status);

                if (status.contains("401")){


                    dbrute.connect();
                    SQLiteDatabase db = dbrute.getWritableDatabase();
                    db.delete("DATA_PELANGGAN",null,null);
                    db.close();
                    System.out.println("sukses");
                    Intent backToLogin = new Intent(ImageLaunch.this,Launch.class);
                    startActivity(backToLogin);


                }

                else {
                    Intent toMain = new Intent(ImageLaunch.this,MainActivity.class);
                    startActivity(toMain);
                }
            } catch (JSONException e) {
                Log.e("JSONException", "Error: " + e.toString());
            } // catch (JSONException e)


        } // protected void onPostExecute(Void v)
    }


}
