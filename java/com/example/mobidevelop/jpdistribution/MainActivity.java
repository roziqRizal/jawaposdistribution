package com.example.mobidevelop.jpdistribution;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.GoogleDirection;
import com.akexorcist.googledirection.constant.AvoidType;
import com.akexorcist.googledirection.model.Direction;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.internal.MapLifecycleDelegate;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.plus.model.people.Person;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;


import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HttpsURLConnection;
import javax.xml.transform.Source;

import android.os.Handler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.http.HTTP;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {

    private GoogleMap nMap;
    public LocationManager locmgr;
    TextView username, mapSegmented, listSegmented, listNamaPelanggan, listAlamatPelanggan, listTelephonePelanggan, namaPelanggan, alamat, telepon, ID, timerCount, TV4, TV5, TV;
    ImageView pictureDisplay, imageButton, imageButton2, viewCustomer, point, comment, commentcustomer;
    Location location;
    SimpleDateFormat dateFormat;
    private static final String HASH_ALGORITHM = "HmacSHA256";
    Marker userMarker, userMarkerChange, usermarker1;
    ProgressDialog progressBar, mProgressDialog , mulaiTracking;
    String routeID, StartID, PublicToken, ambilDurasi, latlifetracking = "", longlifetracking = "";
    Button stop;
    FrameLayout start, skip, done;
    AlertDialog ad5, alertDialog, alertDialog1;
    AlertDialog.Builder alertdlg, finish, notFinish, nullRute, adb5;
    ImageView[] report;
    View layout, viewgradation1, viewgradation2, viewgradation, line;
    public int awal = 0, customerFinished = 0;
    public String[] timerFinished, nama, address, phone, photos, idDB, latitudes, longitudes, circlestring, circlestring2, reasontextcomment;
    public int[]  circlestringroute;
    Intent intent2;
    Circle circle;
    RelativeLayout contentPelanggan;
    FrameLayout tandaMap, tandaList, mapFrame, listFrame, save, cancel;
    LinearLayout segmentedButton, tandaPek, rowFix, rowContent, right, center, center1,center2,center3 , left, editprofile,changepassword, logout;
    EditText reason;
    TableLayout tableLayout;
    Handler handler = new Handler();
    long starttime = 0L, timeInMilliseconds = 0L, timeSwapBuff = 0L, updatedtime = 0L;
    int secs = 0, mins = 0, hour= 0, milliseconds = 0;
    public static PowerManager.WakeLock wakelock;
    PowerManager pm;
    DilatingDotsProgressBar mDilatingDotsProgressBar;
    AlertDialog ad3,ad2,ad4,ad;
    AlertDialog.Builder imageDialog3,imageDialog2,imageDialog4,imageDialogUpload,doneSkip, donerute, chooserute, imageDialog, imagedialog5, imagedialog6;
    AlertDialog.Builder alertdlg1;
    DataRute dbRute;
    protected Cursor cursor, cursorUpload;
    MarkerOptions MOUser,MOUserChange;
    TextView navigationaddress;
    int barisrute=0;
    TextView editprofiletext, changepasswordtext,logouttext, copyrighttext, starttext, donetext, skiptext, timertextview;
    public static final int CONNECTION_TIMEOUT = 100000;
    public static final int READ_TIMEOUT = 150000;
    private LifeTracking mTask;
    int endpointvalue=0, circleindex=0;
    String signHashCodeFirstname="", signHashCodeLastname="",signHashCodeEmail="", signHashCodeImage="", first_name, last_name, image;
    ActionBarDrawerToggle toggle;
    Drawable displayImageActionDrawer = null;
    Bitmap bmp;
    Typeface Boldtype, SemiBoldType, RegularType;
    LatLng latlngPelanggan=null;



    public void acquirewakeLock() {
        if(MainActivity.wakelock!=null){
            MainActivity.wakelock.release();
            MainActivity.wakelock=null;
        }
        pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakelock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                "TrackerService");
        wakelock.acquire();
        MainActivity.wakelock=this.wakelock;
    }
    public void releaseWakelock() {
        wakelock.release();
    }

    public Runnable updateTimer = new Runnable() {
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - starttime;
            updatedtime = timeSwapBuff + timeInMilliseconds;
            secs = (int) (updatedtime / 1000);
            mins = secs / 60;
            hour = mins / 60;
            secs = secs % 60;
            milliseconds = (int) (updatedtime % 1000);
            timerCount.setText(String.format("%02d", hour) + ":" + String.format("%02d", mins) );
            timerCount.setTextColor(Color.BLACK);

            handler.postDelayed(this, 0);

            ambilDurasi = (String) timerCount.getText();

        }};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        FontsOverride fontChanger = new FontsOverride(getAssets(), "SourceSansPro-Regular.ttf");
        fontChanger.replaceFonts((ViewGroup)this.findViewById(android.R.id.content));

        LinearLayout.LayoutParams togglebuttonphoto = new LinearLayout.LayoutParams(40, 40);

        viewgradation = (View)findViewById(R.id.viewgradationbot);
        viewgradation1 = (View)findViewById(R.id.viewgradation);
        viewgradation2 = (View)findViewById(R.id.viewgradation2);
        commentcustomer = (ImageView)findViewById(R.id.imageButton3);



        Context context = getApplicationContext();
        appPrefs appPrefs = new appPrefs(context);
        PublicToken = appPrefs.getToken();

        signHashCodeFirstname = appPrefs.getFirstname();
        signHashCodeLastname = appPrefs.getLastname();
        signHashCodeEmail = appPrefs.getEmail();
        signHashCodeImage = appPrefs.getImage();

        first_name = appPrefs.getFirstname();
        last_name = appPrefs.getLastname();

        pictureDisplay = (ImageView) findViewById(R.id.picture);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);



        image = appPrefs.getImage();






        adb5 = new AlertDialog.Builder(this);

        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerVisible(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });


        setDisplayPicture(image);

        editprofiletext = (TextView)findViewById(R.id.editprofil);
        changepasswordtext =(TextView)findViewById(R.id.changepassword);
        logouttext = (TextView) findViewById(R.id.logout);
        copyrighttext = (TextView) findViewById(R.id.textView19);
        starttext = (TextView) findViewById(R.id.textView20);
        donetext = (TextView) findViewById(R.id.textView21);
        skiptext = (TextView) findViewById(R.id.textView22);
        timertextview = (TextView)findViewById(R.id.timer);

        navigationaddress = (TextView) findViewById(R.id.posisiUser);

        Boldtype = Typeface.createFromAsset(getAssets(),  "fonts/SourceSansPro-Black.ttf");
        SemiBoldType = Typeface.createFromAsset(getAssets(),  "fonts/SourceSansPro-Semibold.ttf");
        RegularType = Typeface.createFromAsset(getAssets(),  "fonts/SourceSansPro-Regular.ttf");

        navigationaddress.setTypeface(RegularType);
        timertextview.setTypeface(RegularType);
        editprofiletext.setTypeface(RegularType);
        changepasswordtext.setTypeface(RegularType);
        logouttext.setTypeface(RegularType);
        copyrighttext.setTypeface(RegularType);
        starttext.setTypeface(SemiBoldType);
        donetext.setTypeface(SemiBoldType);
        skiptext.setTypeface(SemiBoldType);


        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



        acquirewakeLock();
        wakelock.acquire();

        dbRute = new DataRute(this);
        dbRute.connect();

        MOUser = new MarkerOptions();
        MOUserChange = new MarkerOptions();



        alertdlg1 = new AlertDialog.Builder(this);
        imageDialog = new AlertDialog.Builder(this);
        imageDialog3 = new AlertDialog.Builder(this);
        imageDialog2 = new AlertDialog.Builder(this);
        imageDialog4 = new AlertDialog.Builder(this);
        imagedialog5 = new AlertDialog.Builder(this);
        imagedialog6 = new AlertDialog.Builder(this);

        contentPelanggan = (RelativeLayout)findViewById(R.id.contentPelanggan);
        intent2 = new Intent(MainActivity.this, uploadReport.class);

        LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
//        View topbar = layoutInflater.inflate(R.layout.activity_main, null);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View hView =  navigationView.getHeaderView(0);
        //navigationView.addHeaderView(headerView);
        //View headerView = navigationView.inflateHeaderView(R.layout.nav_header_main);

        timerCount = (TextView)findViewById(R.id.timer);
        username = (TextView) findViewById(R.id.username);
        username.setTypeface(RegularType);
        username.setText(first_name+" "+last_name);



        imageDialogUpload = new AlertDialog.Builder(this);
        donerute = new AlertDialog.Builder(this);
        doneSkip = new AlertDialog.Builder(this);
        chooserute = new AlertDialog.Builder(this);

        timerCount.setTypeface(SemiBoldType);

        //String displayPicture = appPrefs.getImage();
        //pictureDisplay.setTag(displayPicture);
        //new DownloadProfile().execute(displayPicture);

        namaPelanggan = (TextView) findViewById(R.id.namaPelanggan);
        alamat = (TextView) findViewById(R.id.alamat);
        telepon = (TextView) findViewById(R.id.telepon);
        ID = (TextView) findViewById(R.id.ID);

        namaPelanggan.setTypeface(RegularType);
        alamat.setTypeface(RegularType);
        telepon.setTypeface(RegularType);
        ID.setTypeface(RegularType);

        layout = findViewById(R.id.tableLayout);
        locmgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


        segmentedButton = (LinearLayout)findViewById(R.id.segmentedButton);
        tandaPek = (LinearLayout)findViewById(R.id.tandaPek);
        editprofile = (LinearLayout)findViewById(R.id.editProfileLayout);
        changepassword = (LinearLayout)findViewById(R.id.changepasswordLayout);
        logout = (LinearLayout)findViewById(R.id.logoutLayout);



        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editProfile = new Intent(MainActivity.this, editProfil.class);
                endpointvalue = 3;
                startActivity(editProfile);
            }
        });

        changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent changePassword = new Intent(MainActivity.this, changePassword.class);
                startActivity(changePassword);
                endpointvalue = 4;
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String timestamp = dateFormat.format(new Date());

                try {
                    new Logout().execute(PublicToken, hashMac(timestamp, "0fab227b319afe10a0566183e5c7317dd23127b3f79a964481c0e08640f21acc"), timestamp);
                } catch (SignatureException e) {
                    e.printStackTrace();
                }
            }
        });



        mapSegmented = (TextView)findViewById(R.id.segmentedMap);
        listSegmented = (TextView)findViewById(R.id.segmentedList);
        listSegmented.setTextColor(Color.parseColor("#FFB3B3B3"));

        mapSegmented.setTypeface(RegularType);
        listSegmented.setTypeface(RegularType);

        tandaMap = (FrameLayout)findViewById(R.id.tandaMap);
        tandaList = (FrameLayout)findViewById(R.id.tandaList);
        mapFrame = (FrameLayout)findViewById(R.id.mapFrame);
        listFrame = (FrameLayout)findViewById(R.id.listFrame);
        mapSegmented.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tandaMap.setVisibility(View.VISIBLE);
                tandaList.setVisibility(View.INVISIBLE);
                listFrame.setVisibility(View.INVISIBLE);
                mapFrame.setVisibility(View.VISIBLE);
                viewgradation1.setVisibility(View.VISIBLE);
                viewgradation.setVisibility(View.VISIBLE);
                mapSegmented.setTextColor(Color.parseColor("#ffffff"));
                listSegmented.setTextColor(Color.parseColor("#FFB3B3B3"));

                int visibilityornot = contentPelanggan.getVisibility();
                System.out.println("visibility or not = "+ visibilityornot);

                if (visibilityornot==4){
                    RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT);
                    p.addRule(RelativeLayout.ABOVE, R.id.button4);
                    p.addRule(RelativeLayout.BELOW,R.id.viewgradationbot);
                    mapFrame.setLayoutParams(p);
                    listFrame.setLayoutParams(p);
                }

                else if (visibilityornot==0) {
                    RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT);
                    p.addRule(RelativeLayout.ABOVE, R.id.viewgradation);
                    p.addRule(RelativeLayout.BELOW, R.id.viewgradationbot);
                    mapFrame.setLayoutParams(p);
                    listFrame.setLayoutParams(p);
                }

            }
        });

        int visibilityornot = contentPelanggan.getVisibility();
        System.out.println("visibility or not = "+ visibilityornot);

        listSegmented.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tandaMap.setVisibility(View.INVISIBLE);
                tandaList.setVisibility(View.VISIBLE);
                mapFrame.setVisibility(View.INVISIBLE);
                listFrame.setVisibility(View.VISIBLE);
                viewgradation1.setVisibility(View.INVISIBLE);
                viewgradation.setVisibility(View.INVISIBLE);
                mapSegmented.setTextColor(Color.parseColor("#FFB3B3B3"));
                listSegmented.setTextColor(Color.parseColor("#ffffff"));

                int visibilityornot = contentPelanggan.getVisibility();
                System.out.println("visibility or not = "+ visibilityornot);

                if (visibilityornot==4){
                    RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT);
                    p.addRule(RelativeLayout.ABOVE, R.id.button4);
                    p.addRule(RelativeLayout.BELOW,R.id.tandaPek);
                    mapFrame.setLayoutParams(p);
                    listFrame.setLayoutParams(p);
                }

                else if (visibilityornot==0){
                    RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT);
                    p.addRule(RelativeLayout.ABOVE, R.id.contentPelanggan);
                    p.addRule(RelativeLayout.BELOW,R.id.tandaPek);
                    mapFrame.setLayoutParams(p);
                    listFrame.setLayoutParams(p);
                }


            }
        });

        alertdlg = new AlertDialog.Builder(this);
        nullRute = new AlertDialog.Builder(this);
        finish = new AlertDialog.Builder(this);
        notFinish = new AlertDialog.Builder(this);
        progressBar = new ProgressDialog(MainActivity.this);
        progressBar.setMessage("logging out");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.setCancelable(false);
        mProgressDialog = new ProgressDialog(MainActivity.this);
        mProgressDialog.setMessage("Downloading user data\nPlease wait");
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setCancelable(false);
        mulaiTracking = new ProgressDialog(MainActivity.this);
        mulaiTracking.setMessage("Start tracking record\n"+"Please wait");
        mulaiTracking.setIndeterminate(true);
        mulaiTracking.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mulaiTracking.setCancelable(false);
        viewCustomer = (ImageView) findViewById(R.id.imageView2);
        viewCustomer.setScaleType(ImageView.ScaleType.FIT_XY);
        done = (FrameLayout) findViewById(R.id.done);
        skip = (FrameLayout) findViewById(R.id.skip);
        imageButton2 = (ImageView) findViewById(R.id.imageButton2);
        Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.togglepicture);
        Bitmap bMapScaled = Bitmap.createScaledBitmap(bMap, 120,65, true);
        imageButton2.setImageBitmap(bMapScaled);
        imageButton2.setBackgroundColor(Color.TRANSPARENT);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageButton.setVisibility(View.VISIBLE);
                layout.setVisibility(View.INVISIBLE);
                viewCustomer.setVisibility(View.VISIBLE);
                imageButton2.setVisibility(View.INVISIBLE);
            }
        });
        imageButton = (ImageView) findViewById(R.id.imageButton);
        Bitmap bMap1 = BitmapFactory.decodeResource(getResources(), R.drawable.togglepicture3);
        Bitmap bMapScaled1 = Bitmap.createScaledBitmap(bMap1, 120,65, true);
        imageButton.setImageBitmap(bMapScaled1);
        imageButton.setBackgroundColor(Color.TRANSPARENT);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewCustomer.setVisibility(View.INVISIBLE);
                layout.setVisibility(View.VISIBLE);
                imageButton.setVisibility(View.INVISIBLE);
                imageButton2.setVisibility(View.VISIBLE);
            }
        });

        start = (FrameLayout) findViewById(R.id.button4);
        start.setVisibility(View.VISIBLE);


        stop = (Button) findViewById(R.id.button6);
        stop.setTypeface(RegularType);

        LayoutInflater layoutInflater3 = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout3 = layoutInflater3.inflate(R.layout.downloadprogress2, null);
        mDilatingDotsProgressBar = (DilatingDotsProgressBar) layout3.findViewById(R.id.progress);
        mDilatingDotsProgressBar.show();

        imageDialog3.setView(layout3);
        imageDialog3.setCancelable(false);
        ad3 = imageDialog3.create();
        ad3.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //ad3.show();
        //mProgressDialog.show();

        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timestamp = dateFormat.format(new Date());
        String id = "25";

        dataProcessing();

        getLocation();





        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println("longitude dan latitude = "+latlifetracking+" & "+longlifetracking);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        t.start();



    }

    public static Bitmap getRoundedRectBitmap(Bitmap bitmap, int pixels) {
        Bitmap result = null;
        try {
            result = Bitmap.createBitmap(150, 150, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(result);

            int color = 0xff424242;
            Paint paint = new Paint();
            Rect rect = new Rect(0, 0, 150, 150);

            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(color);
            canvas.drawCircle(75, 75, 75, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmap, rect, rect, paint);

        } catch (NullPointerException e) {
        } catch (OutOfMemoryError o) {
        }
        return result;
    }

    public void getLocation() {
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
                100,
                0, (android.location.LocationListener) loclistener);
        location = locmgr.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        latlifetracking = String.valueOf(location.getLatitude());
        longlifetracking = String.valueOf(location.getLongitude());


    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();

        if (id == R.id.user) {
            Intent editProfile = new Intent(MainActivity.this, editProfil.class);
            startActivity(editProfile);


        } else if (id == R.id.changepass) {
            Intent changePassword = new Intent(MainActivity.this, changePassword.class);
            startActivity(changePassword);

        } else if (id == R.id.logout) {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timestamp = dateFormat.format(new Date());

            try {
                new Logout().execute(PublicToken, hashMac(timestamp, "0fab227b319afe10a0566183e5c7317dd23127b3f79a964481c0e08640f21acc"), timestamp);
            } catch (SignatureException e) {
                e.printStackTrace();
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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

    android.location.LocationListener loclistener = new android.location.LocationListener() {
        public void onLocationChanged(Location location) {

            System.out.println("circle index"+circleindex);


            Context context = getApplicationContext();
            appPrefs appPrefs = new appPrefs(context);

            signHashCodeFirstname = appPrefs.getFirstname();
            signHashCodeLastname = appPrefs.getLastname();
            signHashCodeEmail = appPrefs.getEmail();
            signHashCodeImage = appPrefs.getImage();

            System.out.println(signHashCodeFirstname);
            System.out.println(first_name);

            if (signHashCodeFirstname!=first_name){
                first_name = signHashCodeFirstname;
                username.setText(first_name+" "+last_name);
            }

            if (signHashCodeLastname!=last_name){
                last_name = signHashCodeLastname;
                username.setText(first_name+" "+last_name);
            }

            if (signHashCodeImage!=image){
                image=signHashCodeImage;
                setDisplayPicture(image);
            }



            if (latitudes!=null){


                //if (circleindex!=0){
                //    latlngPelanggan = new LatLng(Double.parseDouble(latitudes[awal]), Double.parseDouble(longitudes[circleindex]));

                //}

                //else if (circleindex==0){
                System.out.println("asaasasasasa"+awal);

                if (awal<=cursor.getCount()-1) {
                    latlngPelanggan = null;
                    if (circleindex==0){
                        if (circle!=null){
                            circle.remove();
                        }
                        latlngPelanggan = new LatLng(Double.parseDouble(latitudes[awal]), Double.parseDouble(longitudes[awal]));

                        if (circle!=null){
                            circle.remove();
                            report[awal].setImageResource(R.drawable.notcompleteyetnew);

                        }

                        circle = nMap.addCircle(new CircleOptions().center(latlngPelanggan).radius(20).strokeColor(Color.RED).strokeWidth(1).fillColor(Color.RED));

                    }
                    else if (circleindex!=0){
                        latlngPelanggan = new LatLng(Double.parseDouble(latitudes[circleindex]), Double.parseDouble(longitudes[circleindex]));

                        if (circle!=null){
                            circle.remove();
                            report[awal].setImageResource(R.drawable.notcompleteyetnew);

                        }

                        circle = nMap.addCircle(new CircleOptions().center(latlngPelanggan).radius(20).strokeColor(Color.RED).strokeWidth(1).fillColor(Color.RED));


                    }




                }
                //}




                float[] distance = new float[2];

                Location.distanceBetween(location.getLatitude(), location.getLongitude(), circle.getCenter().latitude, circle.getCenter().longitude, distance);

                //System.out.println("jarak customer = "+ Arrays.deepToString(distance));


                if (distance[0] <= circle.getRadius()) {
                    done.performClick();

                    if (done.isPressed()) {
                        awal = awal + 1;
                    } else if (skip.isPressed()) {
                        awal = awal + 1;
                    }
                }
            }

            latlifetracking = String.valueOf(location.getLatitude());
            longlifetracking = String.valueOf(location.getLongitude());



            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timestamp = dateFormat.format(new Date());

            mTask = new LifeTracking();
            if (endpointvalue==0){
                /*try {
                    System.out.println("rute id 1"+mTask.getStatus());

                    mTask.execute(PublicToken, hashMac(timestamp, "0fab227b319afe10a0566183e5c7317dd23127b3f79a964481c0e08640f21acc"), String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude()), timestamp);

                    System.out.println("rute id 1"+mTask.getStatus());
                } catch (SignatureException e) {
                    e.printStackTrace();
                }*/
            }
            else {
                mTask.cancel(true);
            }

            if (circleindex==0){
                routingCustomer(awal);
            }
            else if (circleindex!=0){
                routingCustomer(circleindex);
            }

            /*try {
                mTask = new LifeTracking();

                mTask.execute(PublicToken, hashMac(timestamp, "0fab227b319afe10a0566183e5c7317dd23127b3f79a964481c0e08640f21acc"), String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude()), timestamp);
            } catch (SignatureException e) {
                e.printStackTrace();
            }*/
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        public void onProviderEnabled(String provider) {

        }

        public void onProviderDisabled(String provider) {

            alertdlg1.setTitle("GPS tidak tersedia");
            alertdlg1.setMessage("Aplikasi butuh akses GPS untuk lanjut.\n" + "aktifkan GPS sekarang?");
            alertdlg1.setPositiveButton("YA", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(
                        DialogInterface dialog, int which) {

                    //turnGPSOn();
                    Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(i);
                }
            });
            alertdlg1.setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(
                        DialogInterface dialog, int which) {
                    finish();
                }
            });
            alertdlg1.create().show();
        }
    };

    @Override
    public void onMapReady(GoogleMap googleMap) {

        nMap = googleMap;



        LatLng currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());
        nMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 19));



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
        nMap.setMyLocationEnabled(true);



    }

    public void datamarker(){

        for(int i=0; i < cursor.getCount(); i++) {
            final Double lat = Double.parseDouble(latitudes[i]);
            Double lng = Double.parseDouble(longitudes[i]);

            LatLng user = new LatLng(lat,lng);

            MOUser.title(nama[i]);
            MOUser.position(user);
            MOUser.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));


            userMarker = nMap.addMarker(MOUser);
            userMarker.isVisible();

            //nMap.addMarker(MOUser);


            nMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(final Marker usermarker) {

                    for (int j=0;j<nama.length;j++) {
                        if (nama[j].equals(usermarker.getTitle())) {
                            circleindex = j;
                            System.out.println("posisi  gggg "+j);
                            break;
                        }
                    }

                    System.out.println("array = "+Arrays.deepToString(circlestring));

                    if (circlestring2[circleindex]!="isdone"){

                        LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                        View layout = layoutInflater.inflate(R.layout.popupnotiftomboldua, null);

                        imageDialog2.setView(layout);
                        imageDialog2.setCancelable(false);
                        TextView TV28, TV29, TV30;
                        TV28 = (TextView)layout.findViewById(R.id.textView28);
                        TV29 = (TextView)layout.findViewById(R.id.textView29);
                        TV30 = (TextView) layout.findViewById(R.id.textView30);
                        TV30.setTypeface(SemiBoldType);
                        TV28.setTypeface(RegularType);
                        TV29.setTypeface(SemiBoldType);
                        TV30.setText("OK");
                        TV28.setText("apakah anda ingin memilih customer ini untuk rute selanjutnya?");
                        TV29.setText("TIDAK");
                        TV30.setTextSize(19);
                        TV28.setTextSize(18);
                        TV29.setTextSize(19);

                        final AlertDialog alertDialog;
                        alertDialog = imageDialog2.create();
                        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        alertDialog.show();

                        TV29.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                alertDialog.dismiss();
                            }
                        });

                        TV30.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                usermarker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

                                if (circle!=null){
                                    circle.remove();
                                }

                                circlestring[circleindex] = "isdone";
                                circlestring2[circleindex] = "isdone";

                                view(circleindex);
                                routingCustomer(circleindex);

                                MOUser.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

                                SQLiteDatabase db = dbRute.getReadableDatabase();
                                cursor = db.rawQuery("SELECT * FROM DATA_PELANGGAN",null);

                                cursor.moveToPosition(circleindex);

                                ContentValues values = new ContentValues();
                                values.put("id","sukses");
                                db.update("UPLOAD",values,null, null);

                                System.out.println("posisi marker "+cursor.getString(cursor.getColumnIndex("id")));
                            }
                        });

/*                        donerute.setMessage("apakah anda ingin memilih customer ini untuk rute selanjutnya ?");
                        donerute.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                usermarker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

                                if (circle!=null){
                                    circle.remove();
                                }

                                circlestring[circleindex] = "isdone";
                                circlestring2[circleindex] = "isdone";

                                view(circleindex);
                                routingCustomer(circleindex);

                                MOUser.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

                                SQLiteDatabase db = dbRute.getReadableDatabase();
                                cursor = db.rawQuery("SELECT * FROM DATA_PELANGGAN",null);

                                cursor.moveToPosition(circleindex);

                                ContentValues values = new ContentValues();
                                values.put("id","sukses");
                                db.update("UPLOAD",values,null, null);

                                System.out.println("posisi marker "+cursor.getString(cursor.getColumnIndex("id")));
                            }
                        });

                        donerute.setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                        donerute.create().show();
*/                    }

                    else if (circlestring2[circleindex]=="isdone"){
                        usermarker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

                    }


                    return false;
                }
            });


        }
    }

    public class Logout extends AsyncTask<String , String, String> {
        HttpURLConnection track;
        URL url = null;




        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progressBar.show();
            System.out.println("log out pre execute");
            endpointvalue = 1;
        }

        @Override
        protected String doInBackground(String... params){

            try{
                url = new URL("http://lab.hellomorra.com/jawapos/api/mobile-logout");
                System.setProperty("http.keepAlive", "false");
            }
            catch (MalformedURLException e){
                e.printStackTrace();
                return null;
            }
            try {
                System.out.println("do in background set up...");
                track = (HttpURLConnection) url.openConnection();
                track.setReadTimeout(READ_TIMEOUT);
                track.setConnectTimeout(CONNECTION_TIMEOUT);
                track.setRequestMethod("POST");
                track.setDoInput(true);
                track.setDoOutput(true);

                System.out.println("track ="+track.getResponseMessage());

                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("token", params[0])
                        .appendQueryParameter("sign", params[1])
                        .appendQueryParameter("timestamp",params[2]);
                String query = builder.build().getEncodedQuery();

                OutputStream os = track.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                //track.connect();
                System.out.println("track ="+track.getResponseMessage());

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return null;
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

                    return null;
                }

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } finally {

                //track.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            progressBar.dismiss();
            Intent intent = new Intent(MainActivity.this,Launch.class);
            startActivity(intent);
        }
    }

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

                System.out.println("rute id = "+ latlifetracking);


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

                    System.out.println("rute id = "+ result);

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
    }

    public class startTracking extends AsyncTask<String , String, String> {
        HttpURLConnection startTracking;
        URL url = null;


        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            endpointvalue = 2;

        }

        @Override
        protected String doInBackground(String... params){

            try{
                url = new URL("http://lab.hellomorra.com/jawapos/api/start-report");
            }
            catch (MalformedURLException e){
                e.printStackTrace();
                return "exception";
            }
            try {
                startTracking = (HttpURLConnection)url.openConnection();
                startTracking.setReadTimeout(READ_TIMEOUT);
                startTracking.setConnectTimeout(CONNECTION_TIMEOUT);
                startTracking.setRequestMethod("POST");
                startTracking.setDoInput(true);
                startTracking.setDoOutput(true);


                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("token", params[0])
                        .appendQueryParameter("sign", params[1])
                        .appendQueryParameter("timestamp",params[2])
                        .appendQueryParameter("rute_id",params[3]);
                String query = builder.build().getEncodedQuery();

                OutputStream os = startTracking.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));


                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                //startTracking.connect();

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
                //startTracking.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            try {

                endpointvalue = 0;
                System.out.println(result);
                ad2.dismiss();
                JSONObject jobjroute = new JSONObject(result);
                JSONObject dataRoute = jobjroute.getJSONObject("data");
                StartID = dataRoute.getString("id");


                datamarker();


            } catch (JSONException e) {
                Log.e("JSONException", "Error: " + e.toString());
            } // catch (JSONException e)


        } // protected void onPostExecute(Void v)
    }

    public void dataProcessing() {

        listdata();

        //cursor.moveToPosition(awal);
        //byte[] biteArray = cursor.getBlob(5);
        //System.out.println("isi blob "+awal+" = "+cursor.getBlob(5));
        double latPost = Double.parseDouble(latitudes[awal]);
        double longPost = Double.parseDouble(longitudes[awal]);
        final LatLng currentLatLng = new LatLng(latPost, longPost);


        if (photos[awal]!=null){
            String m = photos[awal];
            byte[] avatar2 = Base64.decode(m, Base64.DEFAULT);
            Bitmap bmp = BitmapFactory.decodeByteArray(avatar2,0,avatar2.length);
            Bitmap resized = Bitmap.createScaledBitmap(bmp, 400, 400, true);
            viewCustomer.setImageBitmap(resized);
        }

        else if (photos[awal]==null){
            try {
                InputStream ims = getAssets().open("house.png");
                Drawable d = Drawable.createFromStream(ims, null);
                viewCustomer.setImageDrawable(d);
            }
            catch(IOException ex) {
                return;
            }
        }

        //if (biteArray!=null){
        //    Bitmap bmp = BitmapFactory.decodeByteArray(biteArray,0,biteArray.length);
            //Bitmap resized = Bitmap.createScaledBitmap(bmp, 150, 150, true);
        //    viewCustomer.setImageBitmap(bmp);
        //}

        //else if (biteArray==null){
        //    try {
                // get input stream
        //        InputStream ims = getAssets().open("house.png");
                // load image as Drawable
        //        Drawable d = Drawable.createFromStream(ims, null);
                // set image to ImageView
        //        viewCustomer.setImageDrawable(d);
        //    }
        //    catch(IOException ex) {
        //        return;
        //    }
        //}

        for(int i=0; i < cursor.getCount(); i++) {
            namaPelanggan.setText("nama : "+nama[0]);
            alamat.setText("alamat : "+address[0]);
            telepon.setText("telepon : "+phone[0]);
            ID.setText("jarak dengan customer (m) : "+idDB[0]);

            String[] separated = address[0].split(",");
            navigationaddress.setText(separated[0]);



            start.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    SQLiteDatabase db = dbRute.getWritableDatabase();
                    db.delete("UPLOAD",null,null);

                    rowFix.setClickable(true);


                    RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT);
                    p.addRule(RelativeLayout.ABOVE, R.id.viewgradation);
                    p.addRule(RelativeLayout.BELOW,R.id.viewgradationbot);
                    mapFrame.setLayoutParams(p);
                    listFrame.setLayoutParams(p);

                    start.setVisibility(View.INVISIBLE);
                    stop.setVisibility(View.VISIBLE);
                    done.setVisibility(View.VISIBLE);
                    skip.setVisibility(View.VISIBLE);

                    imageButton.setVisibility(View.VISIBLE);
                    contentPelanggan.setVisibility(View.VISIBLE);
                    segmentedButton.setVisibility(View.VISIBLE);
                    tandaPek.setVisibility(View.VISIBLE);
                    timerCount.setVisibility(View.VISIBLE);

                    double latPost = Double.parseDouble(latitudes[awal]);
                    double longPost = Double.parseDouble(longitudes[awal]);
                    LatLng currentLatLng = new LatLng(latPost, longPost);
                    nMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 16));

                    LayoutInflater layoutInflater2 = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                    View layout2 = layoutInflater2.inflate(R.layout.downloadprogres, null);
                    mDilatingDotsProgressBar = (DilatingDotsProgressBar) layout2.findViewById(R.id.progress);
                    mDilatingDotsProgressBar.show();

                    imageDialog2.setView(layout2);
                    imageDialog2.setCancelable(false);
                    ad2 = imageDialog2.create();
                    ad2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    ad2.show();

                    Context context = getApplicationContext();
                    appPrefs appPrefs = new appPrefs(context);

                    Intent myIntent = getIntent();
                    routeID = appPrefs.getRouteID();

                    System.out.println("start id"+routeID);

                    dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String timestamp = dateFormat.format(new Date());


                    starttime = SystemClock.uptimeMillis();
                    handler.postDelayed(updateTimer, 0);

                    try {
                        new startTracking().execute(PublicToken, hashMac(timestamp,"0fab227b319afe10a0566183e5c7317dd23127b3f79a964481c0e08640f21acc"), timestamp, routeID);
                    } catch (SignatureException e) {
                        e.printStackTrace();
                    }

                }
            });


            stop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    /*int[] integercircle;
                    integercircle=new int[circlestring.length];

                    for (int i=0;i<circlestring.length;i++) {
                        if (circlestring[i].equals("isdone")) {
                            integercircle[i] = i;
                            break;
                        }
                    }*/

                    getLength(circlestring);



                    System.out.println();

                    if (cursorUpload==null){
                        LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                        View layout = layoutInflater.inflate(R.layout.popupnotif, null);

                        imageDialog2.setView(layout);
                        imageDialog2.setCancelable(false);
                        TextView TV28, TV29;
                        TV28 = (TextView)layout.findViewById(R.id.textView28);
                        TV29 = (TextView)layout.findViewById(R.id.textView29);
                        TV28.setTypeface(RegularType);
                        TV29.setTypeface(SemiBoldType);
                        TV28.setText("Anda belum menyelesaikan seluruh rute. Silahkan lanjutkan sampai rute terakhir");
                        TV29.setText("OK");
                        TV28.setTextSize(18);
                        TV29.setTextSize(19);

                        final AlertDialog alertDialog;
                        alertDialog = imageDialog2.create();
                        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        alertDialog.show();

                        TV29.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                alertDialog.dismiss();
                            }
                        });


                        /*imageDialogUpload.setTitle("Lanjutkan rute anda");
                        imageDialogUpload.setMessage("anda belum menyelesaikan seluruh rute. Silahkan lanjutkan sampai rute terakhir");
                        imageDialogUpload.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        imageDialogUpload.create().show();
                    */
                    }

                    else if (cursor.getCount()==cursorUpload.getCount()){
                        timeSwapBuff += timeInMilliseconds;
                        handler.removeCallbacks(updateTimer);



                        System.out.println("ini yang sudah "+ customerFinished);

                        intent2.putExtra("durasi",ambilDurasi);
                        intent2.putExtra("customerFinished",customerFinished);
                        intent2.putExtra("totalCustomer",cursor.getCount());
                        intent2.putExtra("timerFinished",timerFinished);
                        intent2.putExtra("StartID",StartID);
                        intent2.putExtra("reasontextcomment",reasontextcomment);

                        startActivity(intent2);
                    }

                    else {
                        LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                        View layout = layoutInflater.inflate(R.layout.popupnotif, null);

                        imageDialog2.setView(layout);
                        imageDialog2.setCancelable(false);
                        TextView TV28, TV29;
                        TV28 = (TextView)layout.findViewById(R.id.textView28);
                        TV29 = (TextView)layout.findViewById(R.id.textView29);
                        TV28.setTypeface(RegularType);
                        TV29.setTypeface(SemiBoldType);
                        TV28.setText("Anda belum menyelesaikan seluruh rute. Silahkan lanjutkan sampai rute terakhir");
                        TV29.setText("OK");
                        TV28.setTextSize(18);
                        TV29.setTextSize(19);

                        final AlertDialog alertDialog;
                        alertDialog = imageDialog2.create();
                        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        alertDialog.show();

                        TV29.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                alertDialog.dismiss();
                            }
                        });
                    }


                }
            });

        }

        Intent myIntent = getIntent(); // gets the previously created intent
        String firstname = myIntent.getStringExtra("first_name"); // will return "FirstKeyValue"
        String last_name = myIntent.getStringExtra("last_name");

    }

    public static <T> int getLength(T[] arr){
        int count = 0;

        System.out.println("integer count "+count);
        for(T el : arr)
            if (el != "isdone")
                ++count;
        return count;
    }

    public void routingCustomer(final int inputan) {

        final int total = cursor.getCount();

        System.out.println("sdaddad "+inputan);

        commentcustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comment(inputan);
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                circlestring2[awal]="isdone";

                if (circlestring[awal]!="isdone"){
                    done(inputan,inputan+1);

                }

                else {
                    donerute.setMessage("rute ini sudah selesai anda lewati, tekan tombol ok untuk rute selanjutnya");
                    donerute.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (circleindex==0){
                                awal++;

                            }
                            view(awal);
                        }
                    });
                    donerute.create().show();

                }

            }
        });


        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circlestring2[awal]="isdone";
                skip(inputan,inputan+1);

            }
        });


    }

    public void done(int inputan, int inputan2){

        SQLiteDatabase db = dbRute.getWritableDatabase();
        cursorUpload = db.rawQuery("SELECT * FROM UPLOAD",null);


        if (circle!=null){
            circle.remove();
        }

        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timestamp = dateFormat.format(new Date());

        ContentValues values = new ContentValues();
        values.put("id",idDB[inputan]);
        values.put("nama",nama[inputan]);
        values.put("timer", timestamp);
        if (inputan == cursor.getCount()-1){
            values.put("status", "sukses");
            customerFinished++;

            Double lat = Double.parseDouble(latitudes[inputan]);
            Double lng = Double.parseDouble(longitudes[inputan]);
            LatLng user = new LatLng(lat,lng);
            //MOUserChange.title(nama[inputan]);
            MOUserChange.position(user);
            MOUserChange.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
            //userMarker.remove();
            userMarkerChange = nMap.addMarker(MOUserChange);

            db.insert("UPLOAD", null, values);

            finish.setMessage("Your job is done");
            finish.setCancelable(false);
            finish.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    stop.performClick();
                }
            });
            finish.create().show();
        }

        else {
            report[inputan].setImageResource(R.drawable.completenew);


            if (circleindex==0){
                view(inputan2);
            }
            else if (circleindex!=0){
                view(awal);
            }


            values.put("status", "sukses");

            db.insert("UPLOAD", null, values);


            Double lat = Double.parseDouble(latitudes[inputan]);
            Double lng = Double.parseDouble(longitudes[inputan]);



            LatLng user = new LatLng(lat,lng);

            System.out.println("sdsaddddd"+inputan);

            MOUserChange.title(nama[inputan]);
            MOUserChange.position(user);
            MOUserChange.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
            userMarkerChange = nMap.addMarker(MOUserChange);


            System.out.println("posisi marker ");

            //nMap.addMarker(MOUserChange);

            customerFinished++;

        }
        if (circleindex==0){
            awal++;

        }
        circleindex = 0;
    }

    public void view(int inputan){
        namaPelanggan.setText(nama[inputan]);
        alamat.setText(address[inputan]);
        telepon.setText(phone[inputan]);
        ID.setText(idDB[inputan]);

        String[] separated = address[inputan].split(",");
        navigationaddress.setText(separated[0]);

        if (photos[inputan]!=null){
            String m = photos[inputan];
            byte[] avatar2 = Base64.decode(m, Base64.DEFAULT);
            Bitmap bmp = BitmapFactory.decodeByteArray(avatar2,0,avatar2.length);
            Bitmap resized = Bitmap.createScaledBitmap(bmp, 497, 380, true);
            viewCustomer.setImageBitmap(resized);
        }

        else if (photos[inputan]==null){
            try {
                InputStream ims = getAssets().open("house.png");
                Drawable d = Drawable.createFromStream(ims, null);
                viewCustomer.setImageDrawable(d);
            }
            catch(IOException ex) {
                return;
            }
        }

        double latPost = Double.parseDouble(latitudes[inputan]);
        double longPost = Double.parseDouble(longitudes[inputan]);
        final LatLng currentLatLng = new LatLng(latPost, longPost);


        nMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 16));

    }

    public void skip(int inputan, int inputan2){

        if (circle!=null){
            circle.remove();
        }
        SQLiteDatabase db = dbRute.getWritableDatabase();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timestamp = dateFormat.format(new Date());
        ContentValues values = new ContentValues();
        values.put("id",idDB[inputan]);
        values.put("nama",nama[inputan]);
        values.put("timer", timestamp);
        if (inputan == cursor.getCount()-1){
            db.insert("UPLOAD", null, values);
            finish.setMessage("Your job is done");
            finish.setCancelable(false);
            finish.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    stop.performClick();
                }
            });

            finish.create().show();

        }

        else {
            report[awal].setImageResource(R.drawable.notcompletenew);

            if (circleindex==0){
                view(inputan2);
            }
            else if (circleindex!=0){
                view(awal);
            }

            /*namaPelanggan.setText(nama[inputan2]);
            alamat.setText(address[inputan2]);
            telepon.setText(phone[inputan2]);
            ID.setText(idDB[inputan2]);
            String[] separated = address[inputan2].split(",");
            navigationaddress.setText(separated[0]);

            if (photos[inputan2]!=null){
                String m = photos[inputan2];
                byte[] avatar2 = Base64.decode(m, Base64.DEFAULT);
                Bitmap bmp = BitmapFactory.decodeByteArray(avatar2,0,avatar2.length);
                Bitmap resized = Bitmap.createScaledBitmap(bmp, 497, 380, true);
                viewCustomer.setImageBitmap(resized);
            }

            else if (photos[inputan2]==null){
                try {
                    InputStream ims = getAssets().open("house.png");
                    Drawable d = Drawable.createFromStream(ims, null);
                    viewCustomer.setImageDrawable(d);
                }
                catch(IOException ex) {
                    return;
                }
            }

            double latPost = Double.parseDouble(latitudes[inputan2]);
            double longPost = Double.parseDouble(longitudes[inputan2]);
            final LatLng currentLatLng = new LatLng(latPost, longPost);

            nMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 16));*/

            db.insert("UPLOAD", null, values);

            Double lat = Double.parseDouble(latitudes[inputan]);
            Double lng = Double.parseDouble(longitudes[inputan]);


            LatLng user = new LatLng(lat,lng);

            System.out.println("sdsaddddd"+inputan);
            MOUserChange.title(nama[inputan]);
            MOUserChange.position(user);
            MOUserChange.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
            userMarkerChange = nMap.addMarker(MOUserChange);
            nMap.addMarker(MOUserChange);

        }
        if (circleindex==0){
            awal++;

        }
        circleindex = 0;
    }

    public void listdata() {
        tableLayout = (TableLayout)findViewById(R.id.listLayout);

        SQLiteDatabase db = dbRute.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM DATA_PELANGGAN",null);

        nama = new String[cursor.getCount()];
        address = new String[cursor.getCount()];
        phone = new String[cursor.getCount()];
        report = new ImageView[cursor.getCount()];
        photos = new String[cursor.getCount()];
        idDB = new String[cursor.getCount()];
        latitudes = new String[cursor.getCount()];
        longitudes = new String[cursor.getCount()];
        circlestring = new String[cursor.getCount()];
        circlestring2 = new String[cursor.getCount()];
        circlestringroute = new int[cursor.getCount()];
        reasontextcomment = new String[cursor.getCount()];




        for(int i=0; i < cursor.getCount(); i++) {

            cursor.moveToPosition(i);
            if (cursor.getString(5)!=null){
                photos [i] = cursor.getString(5).toString();
            }

            idDB [i] = cursor.getString(1).toString();
            nama [i] = cursor.getString(2).toString();
            address [i] = cursor.getString(3).toString();
            phone [i] = cursor.getString(4).toString();
            longitudes [i] = cursor.getString(6).toString();
            latitudes [i] = cursor.getString(7).toString();


        }

        for (int baris=0;baris<cursor.getCount();baris++){
            barisrute=baris;

            Typeface Semiboldtype = Typeface.createFromAsset(getAssets(),  "fonts/SourceSansPro-Semibold.ttf");
            Typeface SourceSansProRegular = Typeface.createFromAsset(getAssets(),  "fonts/SourceSansPro-Regular.ttf");

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(20,0,20,0);
            lp.gravity= Gravity.LEFT;

            LinearLayout.LayoutParams rp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            rp.setMargins(60,0,40,0);
            rp.gravity= Gravity.RIGHT;

            LinearLayout.LayoutParams namasetlayout = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            namasetlayout.setMargins(0,7,0,7);

            RelativeLayout.LayoutParams image = new RelativeLayout.LayoutParams(40, 40);
            image.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            image.setMargins(5,5,5,5);

            LinearLayout.LayoutParams image2 = new LinearLayout.LayoutParams(55, 55);
            image.setMargins(5,0,5,30);

            LinearLayout.LayoutParams image3 = new LinearLayout.LayoutParams(60, 55);
            image.setMargins(5,30,5,0);

            rowFix = new LinearLayout(this);
            rowFix.setClickable(false);
            rowFix.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            rowFix.setOrientation(LinearLayout.VERTICAL);
            rowContent = new LinearLayout(this);
            rowContent.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            rowContent.setOrientation(LinearLayout.HORIZONTAL);
            right = new LinearLayout(this);
            right.setLayoutParams(rp);
            right.setOrientation(LinearLayout.VERTICAL);

            center = new LinearLayout(this);
            center.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,1));
            center.setOrientation(LinearLayout.VERTICAL);

            center1 = new LinearLayout(this);
            center1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            center1.setOrientation(LinearLayout.VERTICAL);

            center2 = new LinearLayout(this);
            center2.setLayoutParams(namasetlayout);
            center2.setOrientation(LinearLayout.VERTICAL);

            center3 = new LinearLayout(this);
            center3.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            center3.setOrientation(LinearLayout.VERTICAL);

            left = new LinearLayout(this);
            left.setLayoutParams(lp);
            left.setOrientation(LinearLayout.VERTICAL);

            //cursor.move(baris);

            point = new ImageView(this);
            point.setImageResource(R.drawable.pointbaru);
            point.setLayoutParams(image);


            report[baris] = new ImageView(this);
            report[baris].setImageResource(R.drawable.didnotcompleted);
            report[baris].setLayoutParams(image2);

            comment = new ImageView(this);
            comment.setImageResource(R.drawable.imagenew);
            comment.setLayoutParams(image3);

            final int j=baris;

            comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                    View layout = layoutInflater.inflate(R.layout.layout, null);
                    ImageView apalah = (ImageView) layout.findViewById(R.id.popupimage);
                    Button apalah2 = (Button) layout.findViewById(R.id.okpopup);
                    ImageView apalah3 = (ImageView) layout.findViewById(R.id.imageView8);

                    int x=j;
                    System.out.println(x);

                    if (photos[x]!=null){
                        String m = photos[x];
                        byte[] avatar2 = Base64.decode(m, Base64.DEFAULT);
                        Bitmap bmp = BitmapFactory.decodeByteArray(avatar2,0,avatar2.length);
                        apalah.setImageBitmap(bmp);
                    }

                    else if (photos[x]==null){
                        try {
                            InputStream ims = getAssets().open("house.png");
                            Drawable d = Drawable.createFromStream(ims, null);
                            apalah.setImageDrawable(d);
                        }
                        catch(IOException ex) {
                            return;
                        }
                    }


                    adb5.setView(layout);
                    adb5.setCancelable(true);
                    ad5 = adb5.create();
                    ad5.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    ad5.show();

                    apalah3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ad5.dismiss();
                        }
                    });


                }
            });


            listNamaPelanggan = new TextView(this);
            listAlamatPelanggan = new TextView(this);
            listTelephonePelanggan = new TextView(this);

            listNamaPelanggan.setTypeface(Semiboldtype);
            listAlamatPelanggan.setTypeface(SourceSansProRegular);
            listTelephonePelanggan.setTypeface(SourceSansProRegular);

            listNamaPelanggan.setTextColor(Color.parseColor("#000000"));
            listAlamatPelanggan.setTextColor(Color.parseColor("#000000"));
            listTelephonePelanggan.setTextColor(Color.parseColor("#000000"));

            listNamaPelanggan.setTextSize(13);
            listAlamatPelanggan.setTextSize(13);
            listTelephonePelanggan.setTextSize(13);

            //listNamaPelanggan.setLayoutParams(namasetlayout);
            //center2.setLayoutParams(namasetlayout);
            //listTelephonePelanggan.setLayoutParams(textviewlayout);

            listNamaPelanggan.setText(nama[baris]);
            listAlamatPelanggan.setText(address[baris]);
            listTelephonePelanggan.setText(phone[baris]);

            line = new View(this);
            LinearLayout.LayoutParams lain = (new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1));
            lain.setMargins(15,10,15,25);
            line.setLayoutParams(lain);
            line.setBackgroundColor(Color.parseColor("#FFDFDFDF"));

            right.addView(report[baris]);
            right.addView(comment);

            center1.addView(listNamaPelanggan);
            center2.addView(listAlamatPelanggan);
            center3.addView(listTelephonePelanggan);

            center.addView(center1);
            center.addView(center2);
            center.addView(center3);
            left.addView(point);

            rowContent.addView(left);
            rowContent.addView(center);
            rowContent.addView(right);

            rowFix.addView(rowContent);
            rowFix.addView(line);

            final int reportDoneImage = baris;


            report[baris].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    doneSkip.setMessage("pilihan untuk pelanggan ini");
                    doneSkip.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            report[reportDoneImage].setImageResource(R.drawable.completenew);
                        }
                    });
                    doneSkip.setNegativeButton("Skip", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    doneSkip.create().show();
                }
            });

            tableLayout.addView(rowFix,baris);
        }
    }

    public void comment(final int inputan){
        LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.reason, null);
        imageDialog.setView(layout);
        imageDialog.setCancelable(false);
        ad = imageDialog.create();
        ad.show();

        TV4 = (TextView)layout.findViewById(R.id.textView4);
        TV5 = (TextView)layout.findViewById(R.id.textView5);
        TV = (TextView)layout.findViewById(R.id.textView);

        final Typeface SourceSansProRegular = Typeface.createFromAsset(getAssets(),  "fonts/SourceSansPro-Regular.ttf");


        TV4.setTypeface(SourceSansProRegular);
        TV5.setTypeface(SourceSansProRegular);
        TV.setTypeface(SourceSansProRegular);

        System.out.println(inputan);

        reason = (EditText)layout.findViewById(R.id.reasonEdit);
        reason.setTypeface(SourceSansProRegular);

        save = (FrameLayout) layout.findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //loopingcomment++;

                ad.dismiss();

                reasontextcomment[inputan] = reason.getText().toString();

                //int l=Integer.parseInt(reason.getText().toString());

                LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View layout = layoutInflater.inflate(R.layout.popupnotif, null);


                //System.out.println("dasss"+l+"ssss");
                /*if (l==0){
                    System.out.println("dasssu"+reasontextcomment[inputan]);
                    imagedialog6.setMessage("Anda belum menuliskan data apapun. Apakah ingin lanjut?");
                    imagedialog6.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ad.dismiss();
                        }
                    });
                    imagedialog6.create().show();
                }

               else if(l!=0){*/

                    //imagedialog5.setMessage("Data berhasil ditambahkan");
                    //imagedialog5.setCancelable(false);
                    //imagedialog5.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    //    @Override
                    //    public void onClick(DialogInterface dialogInterface, int i) {

                    //    }
                    //});
                imagedialog5.setView(layout);
                imagedialog5.setCancelable(false);
                alertDialog = imagedialog5.create();
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.show();

                TextView tv28, tv29;
                tv28 = (TextView)layout.findViewById(R.id.textView28);
                tv29 = (TextView)layout.findViewById(R.id.textView29);

                tv28.setTextSize(18);
                tv29.setTextSize(19);


                tv28.setText("Data berhasil ditambahkan");
                tv29.setText("OK");

                tv28.setTypeface(RegularType);
                tv29.setTypeface(SemiBoldType);

                tv29.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });




                // cursorUpload.moveToPosition(inputan);
/*
                SQLiteDatabase db = dbRute.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("comment",reason.getText().toString());
                //alasan[finalI] = reason.getText().toString();
                db.insert("UPLOAD", null, values);

                /*
                if (loopingcomment == yangbelum){
                    TV25.setVisibility(View.INVISIBLE);
                    TV26.setVisibility(View.INVISIBLE);
                    upload.setVisibility(View.VISIBLE);
                }*/



                //}
            }
        });

        cancel = (FrameLayout) layout.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ad.dismiss();
            }
        });
    }

    public void setDisplayPicture (String inputan){
        byte[] avatar2 = Base64.decode(inputan, Base64.DEFAULT);
        bmp = BitmapFactory.decodeByteArray(avatar2,0,avatar2.length);

        Configuration config = getResources().getConfiguration();

        //iki if kanggo hpne mbak etric
        if (config.screenWidthDp <= 321){
            Bitmap resized1 = Bitmap.createScaledBitmap(bmp, 150, 150, true);
            Bitmap conv_bm1 = getRoundedRectBitmap(resized1, 150);
            displayImageActionDrawer = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(conv_bm1, 49, 49, true));
            System.out.println("roziqsatu");
        }

        //iki if kanggo hpku, hpne hendra, hpne edwin, hpne mbak devita, hpne mas nanda
        else if (config.screenWidthDp >= 321){
            Bitmap resized1 = Bitmap.createScaledBitmap(bmp, 150, 150, true);
            Bitmap conv_bm1 = getRoundedRectBitmap(resized1, 150);
            displayImageActionDrawer = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(conv_bm1, 54, 54, true));
            System.out.println("roziqdua");
        }

        Bitmap resized = Bitmap.createScaledBitmap(bmp, 150, 150, true);
        Bitmap conv_bm = getRoundedRectBitmap(resized, 147);

        toggle.setHomeAsUpIndicator(displayImageActionDrawer);


        pictureDisplay.setImageBitmap(conv_bm);
    }

}

