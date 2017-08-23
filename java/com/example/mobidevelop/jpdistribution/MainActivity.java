package com.example.mobidevelop.jpdistribution;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
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
import android.os.Environment;
import android.os.Looper;
import android.os.PowerManager;
import android.os.Handler;
import android.os.StatFs;
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
import android.view.MotionEvent;
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
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
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
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.GoogleDirection;
import com.akexorcist.googledirection.constant.AvoidType;
import com.akexorcist.googledirection.model.Direction;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.internal.MapLifecycleDelegate;
import com.google.android.gms.maps.model.BitmapDescriptor;
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
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.Text;
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
import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import static com.example.mobidevelop.jpdistribution.R.id.name;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {

    Bitmap resizedual;
    private Animator mCurrentAnimator;
    private Tracker mTracker;
    float[][] distance;
    private GoogleMap nMap;
    public View.OnClickListener myOnClickListener;
    public LocationManager locmgr;
    TextView username, mapSegmented, listSegmented, listNamaPelanggan, listAlamatPelanggan, listTelephonePelanggan, namaPelanggan, alamat, telepon, ID, timerCount, TV4, TV5, TV, namaPelangganDetailRN, navigationaddress, editprofiletext, changepasswordtext,logouttext, copyrighttext, starttext, donetext, skiptext, timertextview, commenttext, TV35;
    ImageView pictureDisplay, imageButton, imageButton2, viewCustomer, point,runred,runblack, rungreen, imageview14;
    ImageView[] comment, report;
    FrameLayout commentcustomer, stop, start, skip, done, tandaMap, tandaList, mapFrame, listFrame, save, cancel;
    SQLiteDatabase db;
    Boolean mm =false, doubleBackToExitPressedOnce = false;
    LatLng currentposition;
    Bitmap biruitem, biruputih, ijoitem,ijoputih,merahitem,merahputih,bmp;
    Location location;
    SimpleDateFormat dateFormat;
    private static final String HASH_ALGORITHM = "HmacSHA256";
    Marker userMarker, usermarkerselect;
    MarkerOptions MOUser,MOUserChange,MOUserregnon,MOUserselect;
    ProgressDialog progressBar, mProgressDialog , mulaiTracking;
    String routeID, StartID, PublicToken, ambilDurasi, latlifetracking = "", longlifetracking = "", presstype="Manual", signHashCodeFirstname="", signHashCodeLastname="",signHashCodeEmail="", signHashCodeImage="", first_name, last_name, image;
    RelativeLayout viewcustomertemplate, contentPelanggan, gradationbackground, chooseOptionForCustomer;
    AlertDialog alertDialog, ad3,ad2,ad,circleprocess,circleprocesspage;
    AlertDialog.Builder alertdlg, finish, notFinish, nullRute, adb5, imageDialog3,imageDialog2,imageDialog4,imageDialogUpload,doneSkip, donerute, chooserute, imageDialog, imagedialog5, imagedialog6, circleprogress, circleprogresspage, alertdlg1, alertdialogbuilder;
    View layout, viewgradation1, viewgradation, line;
    public int awal = 0, customerFinished = 0;
    public String[] timerFinished, nama, address, phone, photos, idDB, latitudes, longitudes, circlestring, circlestring2, reasontextcomment, jenis_pelanggan, program_pelanggan, circlestring2forredmarker, donenotification, databaseIdSaving, databaseIdSavingSkip, databaseContentComment;
    public int[]  circlestringroute,databaseIdComment;
    Intent intent2, serviceIntent;
    Circle[] circle;
    LinearLayout segmentedButton, tandaPek, rowFix, rowContent, right, center, center1,center2,center3 , left, editprofile,changepassword, logout, tableLayout, optiondoneskipcommentstop;
    EditText reason;
    Handler handler = new Handler();
    long starttime = 10L, timeInMilliseconds = 0L, timeSwapBuff = 0L, updatedtime = 0L;
    public static PowerManager.WakeLock wakelock;
    PowerManager pm;
    DilatingDotsProgressBar mDilatingDotsProgressBar, mDilatingDotsProgressBar1;
    DataRute dbRute;
    protected Cursor cursor, cursorUpload, cursorreason;
    public static final int CONNECTION_TIMEOUT = 100000;
    public static final int READ_TIMEOUT = 150000;
    private LifeTracking mTask;
    ActionBarDrawerToggle toggle;
    Drawable displayImageActionDrawer = null;
    Typeface Boldtype, SemiBoldType, RegularType;
    LatLng[] latlngPelanggan=null;
    Context context;
    int integerrouting = 2, starttrackingstatus=0, starttrackingbutton=0, heighttemplate, heightimage, widthimage, heightmarker=80, widthmarker=80, timeinterval = 1000, geofencing=0, customerrightnow, timingclear = 0, restrictedawal = 0, bitmapWidth, bitmapHeight, pointHeight, pointWidth, segmentedWidth, segmentedHeigth, pointFiveteen, endpointvalue=0, circleindex = 0, listindex = 0, circleadding = 0, barisrute=0, secs = 0, mins = 0, hour= 0, milliseconds = 0, continuedatabase=0, awaldatabase, minscontinue,hourscontinue, minnotif=0;
    private int mShortAnimationDuration;
    String commentdoneskip = "tidak", uModel, uId, apkversion="/~9927";
    ContentValues values;


    synchronized public Tracker getDefaultTracker(){
        if (mTracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
            mTracker = analytics.newTracker(R.xml.global_tracker);
        }
        return mTracker;
    }


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

            appPrefs appPrefs = new appPrefs(context);

            if (minnotif==1){
                timeInMilliseconds = SystemClock.uptimeMillis() - starttime;
                updatedtime = timeSwapBuff + timeInMilliseconds;
                secs = (int) (updatedtime / 1000);
                mins = minscontinue+secs / 60;
                hour = hourscontinue+mins / 60;
                secs = secs % 60;
                mins = mins % 60;
                milliseconds = (int) (updatedtime % 1000);
                timerCount.setText(String.format("%02d", hour) + ":" + String.format("%02d", mins) + ":" +String.format("%02d", secs));
            }

            else {
                timeInMilliseconds = SystemClock.uptimeMillis() - starttime;
                updatedtime = timeSwapBuff + timeInMilliseconds;
                secs = (int) (updatedtime / 1000);
                mins = secs / 60;
                hour = mins / 60;
                secs = secs % 60;
                mins = mins % 60;
                milliseconds = (int) (updatedtime % 1000);
                timerCount.setText(String.format("%02d", hour) + ":" + String.format("%02d", mins) + ":" +String.format("%02d", secs));
            }

            appPrefs.setDurasihour(String.format("%02d", hour));
            appPrefs.setDurasimins(String.format("%02d", mins));

            handler.postDelayed(this, 0);

            ambilDurasi = (String) timerCount.getText();

        }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(MainActivity.this));

        serviceIntent = new Intent(MainActivity.this, ServiceGPS.class);
        startService(new Intent(this, ServiceGPS.class));


        LayoutInflater donebutton = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View donebuttonlayout = donebutton.inflate(R.layout.downloadprogres, null);
        mDilatingDotsProgressBar = (DilatingDotsProgressBar) donebuttonlayout.findViewById(R.id.progress);
        mDilatingDotsProgressBar.show();

        LayoutInflater donebutton1 = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View donebuttonlayout1 = donebutton1.inflate(R.layout.downloadprogres, null);
        mDilatingDotsProgressBar1 = (DilatingDotsProgressBar) donebuttonlayout1.findViewById(R.id.progress);
        mDilatingDotsProgressBar1.show();

        circleprogress = new AlertDialog.Builder(this);
        circleprogresspage = new AlertDialog.Builder(this);

        circleprogresspage.setView(donebuttonlayout1);
        circleprogresspage.setCancelable(false);
        circleprogress.setView(donebuttonlayout);
        circleprogress.setCancelable(false);
        circleprocess = circleprogress.create();
        circleprocess.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        circleprocesspage = circleprogresspage.create();
        circleprocesspage.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

     //   circleprocesspage.show();

        overridePendingTransition(R.anim.startanim,R.anim.stopanim);

        pinpoint();

        context = getApplicationContext();
        appPrefs appPrefs = new appPrefs(context);
        PublicToken = appPrefs.getToken();

        signHashCodeFirstname = appPrefs.getFirstname();
        signHashCodeLastname = appPrefs.getLastname();
        signHashCodeEmail = appPrefs.getEmail();
        signHashCodeImage = appPrefs.getImage();

        final MainActivity application = MainActivity.this;
        mTracker = application.getDefaultTracker();

        Log.i("MainActivity", "Setting screen name: " + signHashCodeFirstname+" "+signHashCodeLastname);
        uModel = Build.MODEL;
        uId = Build.BRAND;
        mTracker.setScreenName("MainActivity~"+""+uId+"/"+uModel+"/"+ signHashCodeFirstname+"_"+signHashCodeLastname+"_"+apkversion);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());



        FontsOverride fontChanger = new FontsOverride(getAssets(), "SourceSansPro-Regular.ttf");
        fontChanger.replaceFonts((ViewGroup)this.findViewById(android.R.id.content));

        bitmapHeight = (int) getResources().getDimension(R.dimen.bitmapHeight);
        bitmapWidth = (int) getResources().getDimension(R.dimen.bitmapHeight);
        pointWidth = (int) getResources().getDimension(R.dimen.pointWidth);
        pointFiveteen = (int) getResources().getDimension(R.dimen.pointfiveteen);
        pointHeight = (int) getResources().getDimension(R.dimen.pointHeight);
        segmentedWidth = (int) getResources().getDimension(R.dimen.segmentedWidth);
        segmentedHeigth = (int) getResources().getDimension(R.dimen.segmentedHeigth);

        LinearLayout.LayoutParams togglebuttonphoto = new LinearLayout.LayoutParams(40, 40);

        viewgradation = (View)findViewById(R.id.viewgradationbot);
        viewgradation1 = (View)findViewById(R.id.viewgradation);
        commentcustomer = (FrameLayout)findViewById(R.id.imageButton3);
        viewcustomertemplate = (RelativeLayout) findViewById(R.id.imageViewtemplate);


        //  heighttemplate = viewcustomertemplate.getHeight();

        alertdialogbuilder = new AlertDialog.Builder(this);





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
                mTracker.send(new HitBuilders.EventBuilder().setCategory("MainActivity~/sideBarMenu/~923"+apkversion)
                        .setAction(signHashCodeEmail)
                        .setLabel(uModel+"/"+uId)
                        .build());
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
        commenttext = (TextView) findViewById(R.id.imageButtontextview);
        TV35 = (TextView)findViewById(R.id.textView35);
        timertextview = (TextView)findViewById(R.id.timer);

        navigationaddress = (TextView) findViewById(R.id.posisiUser);


        Boldtype = Typeface.createFromAsset(getAssets(),  "fonts/SourceSansPro-Black.ttf");
        SemiBoldType = Typeface.createFromAsset(getAssets(),  "fonts/SourceSansPro-Semibold.ttf");
        RegularType = Typeface.createFromAsset(getAssets(),  "fonts/SourceSansPro-Regular.ttf");

        navigationaddress.setTypeface(RegularType);

        timertextview.setTypeface(RegularType);
        timertextview.setTextColor(Color.parseColor("#FFFFFF"));
        editprofiletext.setTypeface(RegularType);
        changepasswordtext.setTypeface(RegularType);
        logouttext.setTypeface(RegularType);
        copyrighttext.setTypeface(RegularType);
        starttext.setTypeface(SemiBoldType);
        donetext.setTypeface(SemiBoldType);
        skiptext.setTypeface(SemiBoldType);
        commenttext.setTypeface(SemiBoldType);
        TV35.setTypeface(SemiBoldType);

        donetext.setText("DONE");
        skiptext.setText("SKIP");
        commenttext.setText("COMMENT");
        TV35.setText("STOP");

        donetext.setTextSize(12);
        skiptext.setTextSize(12);
        commenttext.setTextSize(12);
        TV35.setTextSize(12);



        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



        acquirewakeLock();
        wakelock.acquire();

        dbRute = new DataRute(this);
        dbRute.connect();

        MOUser = new MarkerOptions();
        MOUserChange = new MarkerOptions();
        MOUserselect = new MarkerOptions();
        MOUserregnon = new MarkerOptions();



        alertdlg1 = new AlertDialog.Builder(this);
        imageDialog = new AlertDialog.Builder(this);
        imageDialog3 = new AlertDialog.Builder(this);
        imageDialog2 = new AlertDialog.Builder(this);
        imageDialog4 = new AlertDialog.Builder(this);
        imagedialog5 = new AlertDialog.Builder(this);
        imagedialog6 = new AlertDialog.Builder(this);

        contentPelanggan = (RelativeLayout)findViewById(R.id.contentPelanggan);
        gradationbackground = (RelativeLayout)findViewById(R.id.gradationbackground);
        intent2 = new Intent(MainActivity.this, uploadReport.class);

        LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        //View topbar = layoutInflater.inflate(R.layout.activity_main, null);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View hView =  navigationView.getHeaderView(0);
        //navigationView.addHeaderView(headerView);
        //View headerView = navigationView.inflateHeaderView(R.layout.nav_header_main);

        timerCount = (TextView)findViewById(R.id.timer);
        username = (TextView) findViewById(R.id.username);
        username.setTypeface(RegularType);
        username.setText(first_name+" "+last_name);

        optiondoneskipcommentstop = (LinearLayout)findViewById(R.id.optiondoneskipcommentstop);

        imageDialogUpload = new AlertDialog.Builder(this);
        donerute = new AlertDialog.Builder(this);
        doneSkip = new AlertDialog.Builder(this);
        chooserute = new AlertDialog.Builder(this);

        timerCount.setTypeface(SemiBoldType);
        timerCount.setTextColor(Color.WHITE);

        //String displayPicture = appPrefs.getImage();
        //pictureDisplay.setTag(displayPicture);
        //new DownloadProfile().execute(displayPicture);

        namaPelanggan = (TextView) findViewById(R.id.namaPelanggan);
        namaPelangganDetailRN = (TextView) findViewById(R.id.namaPelangganDetailRN);
        alamat = (TextView) findViewById(R.id.alamat);
        telepon = (TextView) findViewById(R.id.telepon);
        ID = (TextView) findViewById(R.id.ID);

        namaPelanggan.setTypeface(RegularType);
        namaPelangganDetailRN.setTypeface(RegularType);
        alamat.setTypeface(RegularType);
        telepon.setTypeface(RegularType);
        ID.setTypeface(RegularType);

        namaPelanggan.setTextSize(12);
        namaPelangganDetailRN.setTextSize(12);
        alamat.setTextSize(12);
        telepon.setTextSize(12);
        ID.setTextSize(12);

        namaPelanggan.setTextColor(Color.BLACK);
        namaPelangganDetailRN.setTextColor(Color.BLACK);
        alamat.setTextColor(Color.GRAY);
        telepon.setTextColor(Color.GRAY);


        layout = findViewById(R.id.tableLayout);
        locmgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


        segmentedButton = (LinearLayout)findViewById(R.id.segmentedButton);
        chooseOptionForCustomer = (RelativeLayout) findViewById(R.id.chooseOptionForCustomer);
        tandaPek = (LinearLayout)findViewById(R.id.tandaPek);
        editprofile = (LinearLayout)findViewById(R.id.editProfileLayout);
        changepassword = (LinearLayout)findViewById(R.id.changepasswordLayout);
        logout = (LinearLayout)findViewById(R.id.logoutLayout);


        editprofile.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch ( event.getAction() ) {
                    case MotionEvent.ACTION_DOWN:
                        editprofile.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                        break;
                    case MotionEvent.ACTION_UP:
                        editprofile.setBackgroundColor(getResources().getColor(R.color.basic));
                        mTracker.send(new HitBuilders.EventBuilder().setCategory("MainActivity~/editProfileButton/~923"+apkversion)
                                .setAction(signHashCodeEmail)
                                .setLabel(uModel+"/"+uId)
                                .build());
                        Intent editProfile = new Intent(MainActivity.this, editProfil.class);
                        endpointvalue = 3;
                        startActivity(editProfile);
                        break;
                }
                return true;
            }
        });

        changepassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch ( event.getAction() ) {
                    case MotionEvent.ACTION_DOWN:
                        changepassword.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                        break;
                    case MotionEvent.ACTION_UP:
                        changepassword.setBackgroundColor(getResources().getColor(R.color.basic));
                        mTracker.send(new HitBuilders.EventBuilder().setCategory("MainActivity~/changePasswordButton/~923"+apkversion)
                                .setAction(signHashCodeEmail)
                                .setLabel(uModel+"/"+uId)
                                .build());
                        Intent changePassword = new Intent(MainActivity.this, changePassword.class);
                        startActivity(changePassword);
                        endpointvalue = 4;
                        break;
                }
                return true;
            }
        });

        logout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch ( event.getAction() ) {
                    case MotionEvent.ACTION_DOWN:
                        logout.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                        break;
                    case MotionEvent.ACTION_UP:
                        logout.setBackgroundColor(getResources().getColor(R.color.basic));
                        mTracker.send(new HitBuilders.EventBuilder().setCategory("MainActivity~/logoutButton/~923"+apkversion)
                                .setAction(signHashCodeEmail)
                                .setLabel(uModel+"/"+uId)
                                .build());
                        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String timestamp = dateFormat.format(new Date());

                        try {
                            new Logout().execute(PublicToken, hashMac(timestamp, "0fab227b319afe10a0566183e5c7317dd23127b3f79a964481c0e08640f21acc"), timestamp);
                        } catch (SignatureException e) {
                            e.printStackTrace();
                        }
                        break;
                }
                return true;
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

        mapSegmented.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch ( event.getAction() ) {
                    case MotionEvent.ACTION_DOWN:
                        mapSegmented.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                        break;
                    case MotionEvent.ACTION_UP:
                        mapSegmented.setBackgroundColor(getResources().getColor(R.color.basic));
                        mapSegmented.performClick();
                        break;
                }
                return true;
            }
        });

        mapSegmented.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tandaMap.setVisibility(View.VISIBLE);
                tandaList.setVisibility(View.INVISIBLE);
                listFrame.setVisibility(View.INVISIBLE);
                mapFrame.setVisibility(View.VISIBLE);
                viewgradation1.setVisibility(View.VISIBLE);
                viewgradation.setVisibility(View.VISIBLE);
                mapSegmented.setTextColor(Color.parseColor("#ffffff"));
                listSegmented.setTextColor(Color.parseColor("#FFB3B3B3"));

                mTracker.send(new HitBuilders.EventBuilder().setCategory("MainActivity~/mapSegmentButton/~923"+apkversion)
                        .setAction(signHashCodeEmail)
                        .setLabel(uModel+"/"+uId)
                        .build());


                int visibilityornot = contentPelanggan.getVisibility();

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

        listSegmented.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch ( event.getAction() ) {
                    case MotionEvent.ACTION_DOWN:
                        listSegmented.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                        break;
                    case MotionEvent.ACTION_UP:
                        listSegmented.setBackgroundColor(getResources().getColor(R.color.basic));

                        listSegmented.performClick();
                        break;
                }
                return true;
            }
        });

        listSegmented.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tandaMap.setVisibility(View.INVISIBLE);
                tandaList.setVisibility(View.VISIBLE);
                mapFrame.setVisibility(View.INVISIBLE);
                listFrame.setVisibility(View.VISIBLE);
                viewgradation1.setVisibility(View.INVISIBLE);
                viewgradation.setVisibility(View.INVISIBLE);
                mapSegmented.setTextColor(Color.parseColor("#FFB3B3B3"));
                listSegmented.setTextColor(Color.parseColor("#ffffff"));

                mTracker.send(new HitBuilders.EventBuilder().setCategory("MainActivity~/listSegmentButton/~923"+apkversion)
                        .setAction(signHashCodeEmail)
                        .setLabel(uModel+"/"+uId)
                        .build());

                int visibilityornot = contentPelanggan.getVisibility();

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
        viewCustomer.setScaleType(ImageView.ScaleType.FIT_START);
        done = (FrameLayout) findViewById(R.id.done);
        skip = (FrameLayout) findViewById(R.id.skip);
        imageButton2 = (ImageView) findViewById(R.id.imageButton2);
        Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.togglebuttonreverse);
        Bitmap bMapScaled = Bitmap.createScaledBitmap(bMap, segmentedHeigth,segmentedHeigth, true);
        imageButton2.setImageBitmap(bMapScaled);
        imageButton2.setBackgroundColor(Color.TRANSPARENT);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageButton.setVisibility(View.VISIBLE);
                layout.setVisibility(View.INVISIBLE);
                viewcustomertemplate.setVisibility(View.VISIBLE);
                imageButton2.setVisibility(View.INVISIBLE);
                timerCount.setTextColor(Color.WHITE);
                gradationbackground.setBackgroundResource(R.drawable.gradation);
            }
        });
        imageButton = (ImageView) findViewById(R.id.imageButton);
        Bitmap bMap1 = BitmapFactory.decodeResource(getResources(), R.drawable.togglebuttonquestion);
        Bitmap bMapScaled1 = Bitmap.createScaledBitmap(bMap1, segmentedHeigth,segmentedHeigth, true);
        imageButton.setImageBitmap(bMapScaled1);
        imageButton.setBackgroundColor(Color.TRANSPARENT);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewcustomertemplate.setVisibility(View.INVISIBLE);
                layout.setVisibility(View.VISIBLE);
                imageButton.setVisibility(View.INVISIBLE);
                imageButton2.setVisibility(View.VISIBLE);
                timerCount.setTextColor(Color.BLACK);
                gradationbackground.setBackgroundResource(R.drawable.gradationopacity);
            }
        });

        imageview14 = (ImageView)findViewById(R.id.imageView14);
        runblack = (ImageView)findViewById(R.id.imageView12);
        runred = (ImageView)findViewById(R.id.imageView13);
        rungreen = (ImageView)findViewById(R.id.greenmanwalk);

        BitmapDrawable runblackimage =(BitmapDrawable)getResources().getDrawable(R.drawable.manwalkingblack);
        BitmapDrawable runredimage = (BitmapDrawable)getResources().getDrawable(R.drawable.manwalkingred);
        final BitmapDrawable rungreenimage = (BitmapDrawable)getResources().getDrawable(R.drawable.manwalkinggreen);

        int heightrun = 60;
        int widthrun = 60;
        Bitmap bitmapgetred=runredimage.getBitmap();
        Bitmap bitmapgetblack = runblackimage.getBitmap();
        Bitmap bitmapgetgreen = rungreenimage.getBitmap();
        Bitmap bitmapgreen = Bitmap.createScaledBitmap(bitmapgetgreen, widthrun, heightrun, false);
        Bitmap bitmapred = Bitmap.createScaledBitmap(bitmapgetred, widthrun, heightrun, false);
        Bitmap bitmapblack = Bitmap.createScaledBitmap(bitmapgetblack, widthrun, heightrun, false);

        runblack.setImageBitmap(bitmapblack);
        runred.setImageBitmap(bitmapred);
        rungreen.setImageBitmap(bitmapgreen);

        runblack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTracker.send(new HitBuilders.EventBuilder().setCategory("MainActivity~/runningRedLogo/~923"+apkversion)
                        .setAction(signHashCodeEmail)
                        .setLabel(uModel+"/"+uId)
                        .build());
                runblack.setVisibility(View.INVISIBLE);
                runred.setVisibility(View.VISIBLE);
                rungreen.setVisibility(View.INVISIBLE);
                integerrouting = 0;
            }
        });

        runred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTracker.send(new HitBuilders.EventBuilder().setCategory("MainActivity~/runningGreenLogo/~923"+apkversion)
                        .setAction(signHashCodeEmail)
                        .setLabel(uModel+"/"+uId)
                        .build());
                runred.setVisibility(View.INVISIBLE);
                runblack.setVisibility(View.INVISIBLE);
                rungreen.setVisibility(View.VISIBLE);
                integerrouting = 1;

            }
        });

        rungreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTracker.send(new HitBuilders.EventBuilder().setCategory("MainActivity~/runningBlackLogo/~923"+apkversion)
                        .setAction(signHashCodeEmail)
                        .setLabel(uModel+"/"+uId)
                        .build());
                runblack.setVisibility(View.VISIBLE);
                runred.setVisibility(View.INVISIBLE);
                rungreen.setVisibility(View.INVISIBLE);
                integerrouting = 2;
            }
        });

        start = (FrameLayout) findViewById(R.id.button4);
        start.setVisibility(View.VISIBLE);


        stop = (FrameLayout) findViewById(R.id.button6);


        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timestamp = dateFormat.format(new Date());
        String id = "25";

        dataProcessing();

        getLocation();

        mShortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);




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
                0,
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
            TV37.setTypeface(RegularType);
            TV37.setTextColor(getResources().getColor(R.color.basic));
            final FrameLayout frameLayout, framelayout2;
            frameLayout = (FrameLayout)layout.findViewById(R.id.frameLayout);
            framelayout2 = (FrameLayout)layout.findViewById(R.id.frameLayout2);
            TV28 = (TextView)layout.findViewById(R.id.textView28);
            TV29 = (TextView)layout.findViewById(R.id.textView29);
            TV30 = (TextView)layout.findViewById(R.id.textView30);

            TV28.setTypeface(RegularType);
            TV29.setTypeface(SemiBoldType);
            TV30.setTypeface(SemiBoldType);

            TV28.setTextSize(12);
            TV29.setTextSize(12);
            TV30.setTextSize(12);

            TV28.setText("Are you sure to quit ?");
            TV29.setText("YES");
            TV30.setText("NO");

            backonprevious = backprevious.create();
            backonprevious.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            backonprevious.show();

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

            if (minnotif!=0){
                minnotif=0;
                pressstartbutton();
            }

            if (MOUser.getIcon()!=null){
                circleprocesspage.dismiss();
            }

            for (int h=0;h<=cursor.getCount()-1;h++){
                if (namaPelanggan.getText().equals(nama[h])){
                    customerrightnow = h;
                }
            }

            if (latlngPelanggan!=null){
                if (starttrackingstatus==0){
                    dummy();
                }
            }


            Calendar c = Calendar.getInstance();
            int seconds = c.get(Calendar.SECOND);

            appPrefs appPrefs = new appPrefs(context);

            signHashCodeFirstname = appPrefs.getFirstname();
            signHashCodeLastname = appPrefs.getLastname();
            signHashCodeEmail = appPrefs.getEmail();
            signHashCodeImage = appPrefs.getImage();

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

            stop.setClickable(false);
            done.setClickable(false);
            skip.setClickable(false);
            commentcustomer.setClickable(false);

            if (userMarker!=null){
                //usermarker.
            }

            if (continuedatabase==1){
                if (MOUser!=null){
                    for (int i=0;i<donenotification.length;i++){
                        if (donenotification[i]==""){
                            awaldatabase = i;
                            break;
                        }
                    }
                    routingCustomer(awaldatabase);
                }
            }


            if (circleadding!=0){
                circleprocess.dismiss();
                routingCustomer(circleindex);
            }
            else if (listindex!=0){
                circleprocess.dismiss();
                timingclear=0;
                routingCustomer(listindex-1);
            }
            else {
                if (donenotification[awal].equals("done")||donenotification[awal].equals("skip")){

                    if (geofencing==0 && circleadding == 0 && listindex==0){
                        if (customerFinished<cursor.getCount()) {
                            awal++;
                        }
                    }

                }
                else {
                    circleprocess.dismiss();
                    routingCustomer(awal);
                }
            }


            if (donenotification!=null){

                int inputanclickdone = 0;

                if (circle[awal]!=null){
                    circleprocess.dismiss();

                    for (int i=awal;i<=cursor.getCount()-1;i++) {
                        if (circle[i]!=null){
                            Location.distanceBetween(location.getLatitude(), location.getLongitude(), circle[i].getCenter().latitude, circle[i].getCenter().longitude, distance[i]);
                            if (distance[i][0] <= 20){
                                if (donenotification[i].equals("")){
                                    geofencing = 1;
                                    mTracker.send(new HitBuilders.EventBuilder().setCategory("MainActivity~/doneGeofencing/~923"+apkversion)
                                            .setAction(signHashCodeEmail)
                                            .setLabel(uModel+"/"+uId)
                                            .build());
                                    done(i);
                                }
                            }
                        }
                    }
                }
            }

            currentposition = new LatLng(location.getLatitude(),location.getLongitude());

            latlifetracking = String.valueOf(location.getLatitude());
            longlifetracking = String.valueOf(location.getLongitude());
            //if (location!=null){
            LatLng currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());
            if (integerrouting==1){
                nMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 16));
            }

            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timestamp = dateFormat.format(new Date());

            mTask = new LifeTracking();
            if (endpointvalue==0){
                if (seconds == 0 || seconds == 10 || seconds == 20 || seconds == 30 || seconds == 40 || seconds == 50){

                    //serviceIntent.putExtra("latitude",location.getLatitude());
                    //serviceIntent.putExtra("longitude",location.getLongitude());
                    try {

                        new LifeTracking().execute(PublicToken, hashMac(timestamp, "0fab227b319afe10a0566183e5c7317dd23127b3f79a964481c0e08640f21acc"), String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude()), timestamp);

                    } catch (SignatureException e) {
                        e.printStackTrace();
                    }
                }


            }
            else {
                mTask.cancel(true);
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

            LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
            View layout = layoutInflater.inflate(R.layout.popupnotiftomboldua, null);

            imageDialog2.setView(layout);
            imageDialog2.setCancelable(false);
            TextView TV28, TV29, TV30, TV37;
            final FrameLayout frameLayout, frameLayout2;
            frameLayout = (FrameLayout)layout.findViewById(R.id.frameLayout);
            frameLayout2 = (FrameLayout)layout.findViewById(R.id.frameLayout2);
            TV37 = (TextView)layout.findViewById(R.id.textView37);
            TV37.setText("GPS Signal Required");
            TV37.setTextSize(18);
            TV37.setTypeface(RegularType);
            TV37.setTextColor(getResources().getColor(R.color.basic));
            TV28 = (TextView)layout.findViewById(R.id.textView28);
            TV29 = (TextView)layout.findViewById(R.id.textView29);
            TV30 = (TextView)layout.findViewById(R.id.textView30);
            TV28.setTypeface(RegularType);
            TV29.setTypeface(SemiBoldType);
            TV30.setTypeface(SemiBoldType);
            TV28.setText("Require GPS access to continue. Are you want to turn on GPS?");
            TV29.setText("Continue");
            TV30.setText("NO");
            TV28.setTextSize(12);
            TV29.setTextSize(12);
            TV30.setTextSize(12);

            final AlertDialog alertDialog;
            alertDialog = imageDialog2.create();
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            alertDialog.show();

            frameLayout2.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch ( event.getAction() ) {
                        case MotionEvent.ACTION_DOWN:
                            frameLayout2.setBackgroundColor(getResources().getColor(R.color.lightgrey));
                            break;
                        case MotionEvent.ACTION_UP:
                            frameLayout2.setBackgroundColor(getResources().getColor(R.color.white));
                            finish();
                            break;
                    }
                    return true;
                }
            });

            frameLayout.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch ( event.getAction() ) {
                        case MotionEvent.ACTION_DOWN:
                            frameLayout.setBackgroundColor(getResources().getColor(R.color.lightgrey));
                            break;
                        case MotionEvent.ACTION_UP:
                            frameLayout.setBackgroundColor(getResources().getColor(R.color.white));
                            Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivity(i);
                            break;
                    }
                    return true;
                }
            });

        }
    };



    @Override
    public void onMapReady(GoogleMap googleMap) {

        nMap = googleMap;



        LatLng currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());
        nMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 16));



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

    public void datamarker(int inputan){

        if (starttrackingbutton==1){
            for (int jumlahcircle=0;jumlahcircle<=cursor.getCount()-1;jumlahcircle++)
            {
                if (donenotification[jumlahcircle].equals("done")){
                    System.out.println("muncul ");
                }
                else if (donenotification[jumlahcircle].equals("skip")){
                    System.out.println("muncul ");
                }
                else {
                    System.out.println("muncul "+jumlahcircle);
                    circle[jumlahcircle] = nMap.addCircle(new CircleOptions().center(latlngPelanggan[jumlahcircle]).radius(15).strokeColor(getResources().getColor(R.color.circlecolor)).strokeWidth(1).fillColor(getResources().getColor(R.color.circlecolor)));
                }

            }
        }

        for(int i=0; i < cursor.getCount(); i++) {
            final Double lat = Double.parseDouble(latitudes[i]);
            Double lng = Double.parseDouble(longitudes[i]);
            LatLng user = new LatLng(lat,lng);

            MOUser.position(user);

            if (donenotification[i].equals("done")){
                MOUser.icon(BitmapDescriptorFactory.fromBitmap(ijoitem));
            }
            else if (donenotification[i].equals("skip")){
                MOUser.icon(BitmapDescriptorFactory.fromBitmap(merahitem));

            }
            else {
                MOUser.icon(BitmapDescriptorFactory.fromBitmap(biruitem));
            }

            userMarker = nMap.addMarker(MOUser);

            if (circleadding==0){
                nMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(final Marker usermarker) {

                        mTracker.send(new HitBuilders.EventBuilder().setCategory("MainActivity~/pinPointSelect/~923"+apkversion)
                                .setAction(signHashCodeEmail)
                                .setLabel(uModel+"/"+uId)
                                .build());
                        listindex=0;

                        LatLng equalizer = usermarker.getPosition();
                        String stringequalizer = Double.toString(equalizer.latitude);

                        for (int j=0;j<nama.length;j++) {
                            if (equalizer.latitude==Double.parseDouble(latitudes[j])){
                                circleindex = j;
                            }
                        }

                        nMap.clear();
                        datamarker(circleindex);

                        circleadding =1;
                        chooseOptionForCustomer.setVisibility(View.VISIBLE);

                        return mm;
                    }
                });
            }
        }

        Double latselect = Double.parseDouble(latitudes[inputan]);
        Double longselect = Double.parseDouble(longitudes[inputan]);
        LatLng userselect = new LatLng(latselect,longselect);
        MOUserselect.position(userselect);
        if (donenotification[inputan].equals("done")){
            MOUserselect.icon(BitmapDescriptorFactory.fromBitmap(ijoputih));
        }
        else if (donenotification[inputan].equals("skip")){
            MOUserselect.icon(BitmapDescriptorFactory.fromBitmap(merahputih));
        }
        else {
            MOUserselect.icon(BitmapDescriptorFactory.fromBitmap(biruputih));
        }
        usermarkerselect = nMap.addMarker(MOUserselect);


        for (int j=0; j<cursor.getCount();j++){
            Double latregnon = Double.parseDouble(latitudes[j]);
            Double longregnon = Double.parseDouble(longitudes[j]);
            LatLng userregnon = new LatLng(latregnon,longregnon);
            int height = 30;
            int width = 30;
            BitmapDrawable bitmapdraw = null;
            if (jenis_pelanggan[j].equals("NON REGULER")){
                bitmapdraw =(BitmapDrawable)getResources().getDrawable(R.drawable.jawaposnnew);
            }
            else {
                bitmapdraw =(BitmapDrawable)getResources().getDrawable(R.drawable.jawaposrnew);
            }
            Bitmap b=bitmapdraw.getBitmap();
            Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
            MOUserregnon.position(userregnon);
            MOUserregnon.icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
            nMap.addMarker(MOUserregnon);
        }

    }

    public class Logout extends AsyncTask<String , String, String> {
        HttpURLConnection track;
        URL url = null;
        AlertDialog alertDialog;




        @Override
        protected void onPreExecute(){
            super.onPreExecute();

            LayoutInflater layoutInflater2 = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
            View layout2 = layoutInflater2.inflate(R.layout.downloadprogres, null);
            mDilatingDotsProgressBar = (DilatingDotsProgressBar) layout2.findViewById(R.id.progress);
            mDilatingDotsProgressBar.show();

            imageDialog2.setView(layout2);
            imageDialog2.setCancelable(false);
            ad2 = imageDialog2.create();
            ad2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            ad2.show();

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
                track = (HttpURLConnection) url.openConnection();
                track.setReadTimeout(READ_TIMEOUT);
                track.setConnectTimeout(CONNECTION_TIMEOUT);
                track.setRequestMethod("POST");
                track.setDoInput(true);
                track.setDoOutput(true);

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
            ad2.dismiss();
            db = dbRute.getWritableDatabase();
            db.delete("UPLOAD",null,null);
            db.delete("reasontext",null,null);

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
                ad2.dismiss();
                JSONObject jobjroute = new JSONObject(result);
                JSONObject dataRoute = jobjroute.getJSONObject("data");
                StartID = dataRoute.getString("id");


            } catch (JSONException e) {
                Log.e("JSONException", "Error: " + e.toString());
            } // catch (JSONException e)


        } // protected void onPostExecute(Void v)
    }

    public void dataProcessing() {


        listdata();

        //cursor.moveToPosition(awal);
        //byte[] biteArray = cursor.getBlob(5);
        double latPost = Double.parseDouble(latitudes[awal]);
        double longPost = Double.parseDouble(longitudes[awal]);
        final LatLng currentLatLng = new LatLng(latPost, longPost);


        for(int i=0; i < cursor.getCount(); i++) {
            namaPelanggan.setText(nama[0]);
            BitmapDrawable bitmapdraw = null;
            if (jenis_pelanggan[0].equals("NON REGULER")){
                bitmapdraw =(BitmapDrawable)getResources().getDrawable(R.drawable.jawaposnnew);
            }
            else {
                bitmapdraw =(BitmapDrawable)getResources().getDrawable(R.drawable.jawaposrnew);
            }

            Bitmap b=bitmapdraw.getBitmap();
            Bitmap smallMarker = Bitmap.createScaledBitmap(b, 30, 30, false);

            imageview14.setImageBitmap(smallMarker);
            namaPelangganDetailRN.setText(program_pelanggan[0]);
            alamat.setText(address[0]);
            telepon.setText(phone[0]);
            //ID.setText(idDB[0]);

            String[] separated = address[0].split(",");
            navigationaddress.setText(separated[0]);

            start.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch ( event.getAction() ) {
                        case MotionEvent.ACTION_DOWN:
                            start.setBackgroundColor(getResources().getColor(R.color.darkbutton));
                            break;
                        case MotionEvent.ACTION_UP:
                            start.setBackgroundColor(getResources().getColor(R.color.button));
                            pressstartbutton();
                            break;
                    }
                    return true;
                }
            });


            stop.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch ( event.getAction() ) {
                        case MotionEvent.ACTION_DOWN:
                            stop.setBackgroundColor(getResources().getColor(R.color.darkbutton));
                            break;
                        case MotionEvent.ACTION_UP:
                            stop.setBackgroundColor(getResources().getColor(R.color.button));
                            stop.performClick();
                            break;
                    }
                    return true;
                }
            });

            stop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getLength(circlestring);

                    mTracker.send(new HitBuilders.EventBuilder().setCategory("MainActivity~/stopButton/~923"+apkversion)
                            .setAction(signHashCodeEmail)
                            .setLabel(uModel+"/"+uId)
                            .build());

                    if (cursorUpload==null){
                        LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                        View layout = layoutInflater.inflate(R.layout.popupnotiftomboldua, null);

                        imageDialog2.setView(layout);
                        imageDialog2.setCancelable(false);
                        TextView TV28, TV29, TV30, TV37;
                        final FrameLayout frameLayout, frameLayout2;
                        frameLayout = (FrameLayout)layout.findViewById(R.id.frameLayout);
                        frameLayout2 = (FrameLayout)layout.findViewById(R.id.frameLayout2);
                        TV37 = (TextView)layout.findViewById(R.id.textView37);
                        TV37.setText("Go to upload");
                        TV37.setTextSize(18);
                        TV37.setTypeface(RegularType);
                        TV37.setTextColor(getResources().getColor(R.color.basic));
                        TV28 = (TextView)layout.findViewById(R.id.textView28);
                        TV29 = (TextView)layout.findViewById(R.id.textView29);
                        TV30 = (TextView)layout.findViewById(R.id.textView30);
                        TV28.setTypeface(RegularType);
                        TV29.setTypeface(SemiBoldType);
                        TV30.setTypeface(SemiBoldType);
                        TV28.setText("Incomplete delivery. Are you sure you want to stop?");
                        TV29.setText("CONTINUE");
                        TV30.setText("NO");
                        TV28.setTextSize(12);
                        TV29.setTextSize(12);
                        TV30.setTextSize(12);

                        final AlertDialog alertDialog;
                        alertDialog = imageDialog2.create();
                        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        alertDialog.show();

                        frameLayout2.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                switch ( event.getAction() ) {
                                    case MotionEvent.ACTION_DOWN:
                                        frameLayout2.setBackgroundColor(getResources().getColor(R.color.lightgrey));
                                        break;
                                    case MotionEvent.ACTION_UP:
                                        frameLayout2.setBackgroundColor(getResources().getColor(R.color.white));
                                        alertDialog.dismiss();
                                        break;
                                }
                                return true;
                            }
                        });

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

                                        intent2.putExtra("durasi",ambilDurasi);
                                        intent2.putExtra("customerFinished",customerFinished);
                                        intent2.putExtra("totalCustomer",cursor.getCount());
                                        intent2.putExtra("timerFinished",timerFinished);
                                        intent2.putExtra("StartID",StartID);
                                        intent2.putExtra("reasontextcomment",reasontextcomment);

                                        startActivity(intent2);
                                        break;
                                }
                                return true;
                            }
                        });

                    }

                    else if (cursor.getCount()==cursorUpload.getCount()){

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
                        View layout = layoutInflater.inflate(R.layout.popupnotiftomboldua, null);

                        imageDialog2.setView(layout);
                        imageDialog2.setCancelable(false);
                        TextView TV28, TV29, TV30, TV37;
                        final FrameLayout frameLayout, frameLayout2;
                        frameLayout = (FrameLayout)layout.findViewById(R.id.frameLayout);
                        frameLayout2 = (FrameLayout)layout.findViewById(R.id.frameLayout2);
                        TV37 = (TextView)layout.findViewById(R.id.textView37);
                        TV37.setText("Go to upload");
                        TV37.setTextSize(18);
                        TV37.setTypeface(RegularType);
                        TV37.setTextColor(getResources().getColor(R.color.basic));
                        TV28 = (TextView)layout.findViewById(R.id.textView28);
                        TV29 = (TextView)layout.findViewById(R.id.textView29);
                        TV30 = (TextView)layout.findViewById(R.id.textView30);
                        TV28.setTypeface(RegularType);
                        TV29.setTypeface(SemiBoldType);
                        TV30.setTypeface(SemiBoldType);
                        TV28.setText("Incomplete delivery. Are you sure you want to stop?");
                        TV29.setText("CONTINUE");
                        TV30.setText("NO");
                        TV28.setTextSize(12);
                        TV29.setTextSize(12);
                        TV30.setTextSize(12);

                        final AlertDialog alertDialog;
                        alertDialog = imageDialog2.create();
                        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        alertDialog.show();

                        frameLayout2.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                switch ( event.getAction() ) {
                                    case MotionEvent.ACTION_DOWN:
                                        frameLayout2.setBackgroundColor(getResources().getColor(R.color.lightgrey));
                                        break;
                                    case MotionEvent.ACTION_UP:
                                        frameLayout2.setBackgroundColor(getResources().getColor(R.color.white));
                                        alertDialog.dismiss();
                                        break;
                                }
                                return true;
                            }
                        });

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

                                        intent2.putExtra("durasi",ambilDurasi);
                                        intent2.putExtra("customerFinished",customerFinished);
                                        intent2.putExtra("totalCustomer",cursor.getCount());
                                        intent2.putExtra("timerFinished",timerFinished);
                                        intent2.putExtra("StartID",StartID);
                                        intent2.putExtra("reasontextcomment",reasontextcomment);

                                        startActivity(intent2);
                                        break;
                                }
                                return true;
                            }
                        });

                    }
                }
            });

        }

        Intent myIntent = getIntent(); // gets the previously created intent
        String firstname = myIntent.getStringExtra("first_name"); // will return "FirstKeyValue"
        String last_name = myIntent.getStringExtra("last_name");
        //circleprocesspage.dismiss();

    }

    public void pressstartbutton(){
        mTracker.send(new HitBuilders.EventBuilder().setCategory("MainActivity~/startButton/~923"+apkversion)
                .setAction(signHashCodeEmail)
                .setLabel(uModel+"/"+uId)
                .build());
        starttrackingbutton=1;
        continuedatabase = 0;

        start.setVisibility(View.INVISIBLE);
        optiondoneskipcommentstop.setVisibility(View.VISIBLE);
        stop.setVisibility(View.VISIBLE);
        done.setVisibility(View.VISIBLE);
        skip.setVisibility(View.VISIBLE);

        LayoutInflater layoutInflater2 = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout2 = layoutInflater2.inflate(R.layout.downloadprogres, null);
        mDilatingDotsProgressBar = (DilatingDotsProgressBar) layout2.findViewById(R.id.progress);
        mDilatingDotsProgressBar.show();

        imageDialog2.setView(layout2);
        imageDialog2.setCancelable(false);
        ad2 = imageDialog2.create();
        ad2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ad2.show();

        appPrefs appPrefs = new appPrefs(context);

        Intent myIntent = getIntent();
        routeID = appPrefs.getRouteID();

        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timestamp = dateFormat.format(new Date());


        starttime = SystemClock.uptimeMillis();
        handler.postDelayed(updateTimer, 60);

        try {
            new startTracking().execute(PublicToken, hashMac(timestamp,"0fab227b319afe10a0566183e5c7317dd23127b3f79a964481c0e08640f21acc"), timestamp, routeID);
        } catch (SignatureException e) {
            e.printStackTrace();
        }

        nMap.clear();
        listindex=0;
        circleadding=0;
        if (cursorUpload.getCount()==0){
            datamarker(awal);
        }
        else {
            datamarker(awaldatabase);
        }
    }

    public static <T> int getLength(T[] arr){
        int count = 0;

        for(T el : arr)
            if (el != "isdone")
                ++count;
        return count;
    }

    public void routingCustomer(final int inputan) {


        restrictedawal = 1;
        Double lat = Double.parseDouble(latitudes[inputan]);
        Double lng = Double.parseDouble(longitudes[inputan]);

        LatLng equalizer = new LatLng(lat,lng);


        if (starttrackingstatus==1){
            if (timingclear==0){
                timingclear=1;
                nMap.clear();
                datamarker(inputan);
            }
        }



        if (integerrouting==2){
            nMap.animateCamera(CameraUpdateFactory.zoomIn());
            nMap.animateCamera(CameraUpdateFactory.newLatLngZoom(equalizer, 16));
        }

        if (starttrackingstatus==1){
                viewcontent(inputan);
        }

        final int total = cursor.getCount();

        commentcustomer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch ( event.getAction() ) {
                    case MotionEvent.ACTION_DOWN:
                        commentcustomer.setBackgroundColor(getResources().getColor(R.color.lightgrey));
                        break;
                    case MotionEvent.ACTION_UP:
                        commentcustomer.setBackgroundColor(getResources().getColor(R.color.white));
                        mTracker.send(new HitBuilders.EventBuilder().setCategory("MainActivity~/commentButton/~923"+apkversion)
                                .setAction(signHashCodeEmail)
                                .setLabel(uModel+"/"+uId)
                                .build());
                        comment(inputan);
                        break;
                }
                return true;
            }
        });

        done.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch ( event.getAction() ) {
                    case MotionEvent.ACTION_DOWN:
                        done.setBackgroundColor(getResources().getColor(R.color.darkdonecolor));
                        break;
                    case MotionEvent.ACTION_UP:
                        done.setBackgroundColor(getResources().getColor(R.color.donecolor));
                        mTracker.send(new HitBuilders.EventBuilder().setCategory("MainActivity~/doneButton/~923"+apkversion)
                                .setAction(signHashCodeEmail)
                                .setLabel(uModel+"/"+uId)
                                .build());
                        done.performClick();
                        break;
                }
                return true;
            }
        });

        myOnClickListener = new View.OnClickListener() {
            public void onClick(View v) {
                if (customerFinished<cursor.getCount()){
                    if (donenotification[inputan].equals("done")||donenotification[inputan].equals("skip")){
                        LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                        View layout = layoutInflater.inflate(R.layout.popupnotif, null);

                        donerute.setView(layout);
                        donerute.setCancelable(false);
                        alertDialog = donerute.create();
                        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        alertDialog.show();

                        TextView tv28, tv29, TV37;
                        TV37 = (TextView)layout.findViewById(R.id.textView36);
                        TV37.setText("Done");
                        TV37.setTextSize(18);
                        TV37.setTypeface(RegularType);
                        TV37.setTextColor(getResources().getColor(R.color.basic));
                        final FrameLayout frameLayout = (FrameLayout)layout.findViewById(R.id.frameLayout);
                        tv28 = (TextView)layout.findViewById(R.id.textView28);
                        tv29 = (TextView)layout.findViewById(R.id.textView29);

                        tv28.setTextSize(12);
                        tv29.setTextSize(12);

                        tv28.setText("This customer has been added to your delivery. please press 'OK'");
                        tv29.setText("OK");

                        tv28.setTypeface(RegularType);
                        tv29.setTypeface(SemiBoldType);

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
                                        circleadding = 0;
                                        listindex = 0;
                                        nMap.clear();
                                        datamarker(awal);
                                        break;
                                }
                                return true;
                            }
                        });
                    }
                    else {
                        done(inputan);
                    }

                }
                else {
                    finalalert(inputan);
                }
            }
        };

            done.setOnClickListener(myOnClickListener);

            skip.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch ( event.getAction() ) {
                        case MotionEvent.ACTION_DOWN:
                            skip.setBackgroundColor(getResources().getColor(R.color.lightgrey));
                            break;
                        case MotionEvent.ACTION_UP:
                            skip.setBackgroundColor(getResources().getColor(R.color.white));
                            mTracker.send(new HitBuilders.EventBuilder().setCategory("MainActivity~/skipButton/~923"+apkversion)
                                    .setAction(signHashCodeEmail)
                                    .setLabel(uModel+"/"+uId)
                                    .build());
                            skip.performClick();
                            break;
                    }
                    return true;
                }
            });

            skip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (customerFinished<cursor.getCount()) {
                        if (donenotification[inputan].equals("done")||donenotification[inputan].equals("skip")){
                            LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                            View layout = layoutInflater.inflate(R.layout.popupnotif, null);

                            donerute.setView(layout);
                            donerute.setCancelable(false);
                            alertDialog = donerute.create();
                            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            alertDialog.show();

                            TextView tv28, tv29, TV37;
                            TV37 = (TextView)layout.findViewById(R.id.textView36);
                            TV37.setText("Done");
                            TV37.setTextSize(18);
                            TV37.setTypeface(RegularType);
                            TV37.setTextColor(getResources().getColor(R.color.basic));
                            final FrameLayout frameLayout = (FrameLayout)layout.findViewById(R.id.frameLayout);
                            tv28 = (TextView)layout.findViewById(R.id.textView28);
                            tv29 = (TextView)layout.findViewById(R.id.textView29);

                            tv28.setTextSize(12);
                            tv29.setTextSize(12);

                            tv28.setText("This customer has been added to your delivery. please press 'OK'");
                            tv29.setText("OK");

                            tv28.setTypeface(RegularType);
                            tv29.setTypeface(SemiBoldType);

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
                                            circleadding = 0;
                                            listindex = 0;
                                            nMap.clear();
                                            datamarker(awal);
                                            break;
                                    }
                                    return true;
                                }
                            });
                        }
                        else {
                            donenotification[inputan] = "skip";
                            skip(inputan);
                        }

                    }
                    else {
                        finalalert(inputan);
                    }
                }
            });





    }




    public void done(int inputan){

        circleprocess.show();

        if (inputan==cursor.getCount()){
            stop.performClick();
        }

        else {

            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timestamp = dateFormat.format(new Date());

            Double lat = Double.parseDouble(latitudes[inputan]);
            Double lng = Double.parseDouble(longitudes[inputan]);

            /*try {
                mTask = new LifeTracking();

                mTask.execute(PublicToken, hashMac(timestamp, "0fab227b319afe10a0566183e5c7317dd23127b3f79a964481c0e08640f21acc"), String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude()), timestamp);
            } catch (SignatureException e) {
                e.printStackTrace();
            }*/

            db = dbRute.getWritableDatabase();
            cursorUpload = db.rawQuery("SELECT * FROM UPLOAD",null);

            String commentdataupload = null;
            if (reasontextcomment[inputan]!=null){
                commentdataupload = reasontextcomment[inputan];
            }

            if (geofencing == 1) {
                presstype="Automatic";
            }
            else{
                presstype="Manual";
            }

            values = new ContentValues();
            values.put("id",idDB[inputan]);
            values.put("nama",nama[inputan]);
            values.put("timer", timestamp);
            values.put("alamat", address[inputan]);
            values.put("comment",commentdataupload);

            values.put("doneorskip","done");
            values.put("notifcomment",commentdoneskip);
            values.put("latitude",currentposition.latitude);
            values.put("longitude",currentposition.longitude);
            values.put("presstype",presstype);
            values.put("spare1",inputan);
            values.put("spare3",inputan);

            report[inputan].setImageResource(R.drawable.pointdone);

            values.put("distance", distance[inputan][0]);
            values.put("status", "sukses");


            db.insert("UPLOAD", null, values);


            LatLng user = new LatLng(lat,lng);

            int height = 30;
            int width = 30;
            BitmapDrawable bitmapdraw = null;
            if (jenis_pelanggan[inputan].equals("NON REGULER")){
                bitmapdraw =(BitmapDrawable)getResources().getDrawable(R.drawable.jawaposnnew);
            }
            else {
                bitmapdraw =(BitmapDrawable)getResources().getDrawable(R.drawable.jawaposrnew);
            }
            Bitmap b=bitmapdraw.getBitmap();
            Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

            circle[inputan].remove();

            if (geofencing==0 && circleadding==0 && listindex==0){
                if (awal<cursor.getCount()-1){
                    awal++;
                }
            }

            customerFinished++;
            donenotification[inputan] = "done";
            geofencing = 0;
            circleindex = 0;
            listindex = 0;
            circleadding = 0;
            commentdoneskip = "tidak";
            timeinterval = 1000;
            timingclear=0;
            restrictedawal=0;

            if (customerFinished==cursor.getCount()){
                finalalert(inputan);
            }

        }

    }

    public void skip(int inputan){

        circleprocess.show();

        if (inputan==cursor.getCount()){
            stop.performClick();
        }

        else {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timestamp = dateFormat.format(new Date());

            circle[inputan].remove();

            /*try {
                mTask = new LifeTracking();
                mTask.execute(PublicToken, hashMac(timestamp, "0fab227b319afe10a0566183e5c7317dd23127b3f79a964481c0e08640f21acc"), String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude()), timestamp);
            } catch (SignatureException e) {
                e.printStackTrace();
            }*/

            String commentdataupload = null;
            if (reasontextcomment[inputan]!=null){
                commentdataupload = reasontextcomment[inputan];
            }

            if (geofencing == 1) {
                presstype="Automatic";
            }
            else{
                presstype="Manual";
            }

            db = dbRute.getWritableDatabase();
            values = new ContentValues();
            values.put("id",idDB[inputan]);
            values.put("nama",nama[inputan]);
            values.put("timer", timestamp);
            values.put("alamat", address[inputan]);
            values.put("comment",commentdataupload);
            values.put("doneorskip","skip");
            values.put("notifcomment",commentdoneskip);
            values.put("latitude",location.getLatitude());
            values.put("longitude",location.getLongitude());
            values.put("presstype",presstype);
            values.put("spare2",inputan);
            values.put("spare3",inputan);

            report[awal].setImageResource(R.drawable.pointnotdone);
            values.put("distance", distance[inputan][0]);

            db.insert("UPLOAD", null, values);

            Double lat = Double.parseDouble(latitudes[inputan]);
            Double lng = Double.parseDouble(longitudes[inputan]);


            LatLng user = new LatLng(lat,lng);

            int height = 30;
            int width = 30;
            BitmapDrawable bitmapdraw = null;
            if (jenis_pelanggan[inputan].equals("NON REGULER")){
                bitmapdraw =(BitmapDrawable)getResources().getDrawable(R.drawable.jawaposnnew);
            }
            else {
                bitmapdraw =(BitmapDrawable)getResources().getDrawable(R.drawable.jawaposrnew);
            }
            Bitmap b=bitmapdraw.getBitmap();
            Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);


            if (geofencing==0 && circleadding==0 && listindex==0){
                if (awal<cursor.getCount()-1){
                    awal++;
                }
            }
            customerFinished++;
            geofencing = 0;
            circleadding = 0;
            circleindex = 0;
            listindex = 0;
            commentdoneskip = "tidak";
            timeinterval = 1000;
            timingclear=0;
            restrictedawal=0;

            if (customerFinished==cursor.getCount()){
                finalalert(inputan);
            }
        }
    }

    public void viewcontent(final int inputan){

        namaPelanggan.setText(nama[inputan]);
        BitmapDrawable bitmapdraw = null;
        if (jenis_pelanggan[inputan].equals("NON REGULER")){
            bitmapdraw =(BitmapDrawable)getResources().getDrawable(R.drawable.jawaposnnew);
        }
        else {
            bitmapdraw =(BitmapDrawable)getResources().getDrawable(R.drawable.jawaposrnew);
        }

        Bitmap b=bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, 30, 30, false);

        imageview14.setImageBitmap(smallMarker);

        namaPelangganDetailRN.setText(program_pelanggan[inputan]);
        alamat.setText(address[inputan]);
        telepon.setText(phone[inputan]);
        //ID.setText(idDB[inputan]);

        String[] separated = address[inputan].split(",");
        navigationaddress.setText(separated[0]);


        if (photos[inputan]!=null){
            viewCustomer.setVisibility(View.VISIBLE);
            String m = photos[inputan];
            byte[] avatar2 = Base64.decode(m, Base64.DEFAULT);
            Bitmap bmp = BitmapFactory.decodeByteArray(avatar2,0,avatar2.length);
            double xxx1 = bmp.getWidth();
            double xxx2 = bmp.getHeight();
            double xxxx = xxx1/xxx2;
            double xxxxx =  ((double)14/(double) 49);
            heightimage = heighttemplate;
            double xxxxxx = xxxx*heightimage;
            if (xxxx>1){
                widthimage = (int) xxxx * heightimage;
            }
            else {
                widthimage = heightimage;
            }
            resizedual = Bitmap.createScaledBitmap(bmp, (int)xxxxxx, widthimage, true);
            viewCustomer.setImageBitmap(resizedual);
        }

        else if (photos[inputan]==null){
            viewCustomer.setVisibility(View.INVISIBLE);

        }

        final  View thumbview = viewCustomer;

        viewCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //zoomImageFromThumb(viewCustomer,resizedual);
                Intent viewimagecustomer = new Intent(MainActivity.this, FullscreenActivity.class);
                viewimagecustomer.putExtra("stringimage",photos[inputan]);
                mTracker.send(new HitBuilders.EventBuilder().setCategory("MainActivity~/viewCustomerImage/~923"+apkversion)
                        .setAction(signHashCodeEmail)
                        .setLabel(uModel+"/"+uId)
                        .build());
                startActivity(viewimagecustomer);
            }
        });

    }

    double roundTwoDecimals(double d)
    {
        DecimalFormat twoDForm = new DecimalFormat("#.####");
        return Double.valueOf(twoDForm.format(d));
    }



    public void listdata() {

        tableLayout = (TableLayout)findViewById(R.id.listLayout);

        db = dbRute.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM DATA_PELANGGAN",null);
        cursorUpload = db.rawQuery("SELECT * FROM UPLOAD",null);
        cursorreason = db.rawQuery("SELECT * FROM reasontext",null);


        nama = new String[cursor.getCount()];
        jenis_pelanggan = new String[cursor.getCount()];
        program_pelanggan = new String[cursor.getCount()];
        address = new String[cursor.getCount()];
        phone = new String[cursor.getCount()];
        report = new ImageView[cursor.getCount()];
        comment = new ImageView[cursor.getCount()];
        photos = new String[cursor.getCount()];
        idDB = new String[cursor.getCount()];
        latitudes = new String[cursor.getCount()];
        longitudes = new String[cursor.getCount()];
        circlestring = new String[cursor.getCount()];
        circlestring2 = new String[cursor.getCount()];
        circlestring2forredmarker = new String[cursor.getCount()];
        circlestringroute = new int[cursor.getCount()];
        reasontextcomment = new String[cursor.getCount()];
        latlngPelanggan = new LatLng[cursor.getCount()];
        circle = new Circle[cursor.getCount()];
        distance = new float[cursor.getCount()][2];
        donenotification = new String[cursor.getCount()];


        for(int i=0; i < cursor.getCount(); i++) {

            cursor.moveToPosition(i);
            if (cursor.getString(5)!=null){
                photos [i] = cursor.getString(5).toString();
            }

            reasontextcomment[i] = null;
            donenotification[i] = "";
            idDB [i] = cursor.getString(1).toString();
            nama [i] = cursor.getString(2).toString();
            address [i] = cursor.getString(3).toString();
            phone [i] = cursor.getString(4).toString();
            longitudes [i] = cursor.getString(6).toString();
            latitudes [i] = cursor.getString(7).toString();
            jenis_pelanggan [i] = cursor.getString(8).toString();
            program_pelanggan [i] = cursor.getString(9).toString();
            latlngPelanggan[i] = new LatLng(Double.parseDouble(latitudes[i]), Double.parseDouble(longitudes[i]));

        }


        for (int baris=0;baris<cursor.getCount();baris++){
            barisrute=baris;


            final Typeface Semiboldtype = Typeface.createFromAsset(getAssets(),  "fonts/SourceSansPro-Semibold.ttf");
            Typeface SourceSansProRegular = Typeface.createFromAsset(getAssets(),  "fonts/SourceSansPro-Regular.ttf");

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(20,0,20,0);
            lp.gravity= Gravity.LEFT;

            LinearLayout.LayoutParams rp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            rp.setMargins(60,0,40,0);
            rp.gravity= Gravity.RIGHT;

            LinearLayout.LayoutParams namasetlayout = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            namasetlayout.setMargins(0,7,0,7);

            RelativeLayout.LayoutParams image = new RelativeLayout.LayoutParams(pointHeight, pointHeight);
            image.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            image.setMargins(5,5,5,5);

            LinearLayout.LayoutParams image2 = new LinearLayout.LayoutParams(pointFiveteen, pointFiveteen);
            image.setMargins(5,0,5,30);

            LinearLayout.LayoutParams image3 = new LinearLayout.LayoutParams(pointHeight, pointHeight);
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

            point = new ImageView(this);
            point.setImageResource(R.drawable.pointbaru);
            point.setLayoutParams(image);


            report[baris] = new ImageView(this);
            report[baris].setImageResource(R.drawable.pointnotdone);
            report[baris].setLayoutParams(image2);

            comment[baris] = new ImageView(this);
            comment[baris].setLayoutParams(image3);

            final int j =baris;

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

            listNamaPelanggan.setTextColor(Color.BLACK);
            listAlamatPelanggan.setTextColor(Color.GRAY);

            listNamaPelanggan.setText(nama[baris]);
            listAlamatPelanggan.setText(address[baris]);
            listTelephonePelanggan.setText(phone[baris]);

            line = new View(this);
            LinearLayout.LayoutParams lain = (new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1));
            lain.setMargins(15,10,15,25);
            line.setLayoutParams(lain);
            line.setBackgroundColor(Color.parseColor("#FFDFDFDF"));

            //right.addView(report[baris]);
            right.addView(comment[baris]);

            center1.addView(listNamaPelanggan);
            center2.addView(listAlamatPelanggan);
            //center3.addView(listTelephonePelanggan);

            center.addView(center1);
            center.addView(center2);
            center.addView(center3);
            left.addView(report[baris]);

            rowContent.addView(left);
            rowContent.addView(center);
            rowContent.addView(right);

            rowFix.addView(rowContent);
            rowFix.addView(line);


            rowFix.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {

                    switch ( motionEvent.getAction() ) {
                        case MotionEvent.ACTION_DOWN:
                            rowFix.setBackgroundColor(getResources().getColor(R.color.lightgrey));
                            break;
                        case MotionEvent.ACTION_UP:
                            rowFix.setBackgroundColor(getResources().getColor(R.color.white));
                            mTracker.send(new HitBuilders.EventBuilder().setCategory("MainActivity~/listCustomerSelect/~923"+apkversion)
                                    .setAction(signHashCodeEmail)
                                    .setLabel(uModel+"/"+uId)
                                    .build());
                            rowFix.performClick();
                            break;
                    }

                    return false;
                }
            });

            rowFix.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    circleadding = 0;
                    listindex = j+1;
                    mapSegmented.performClick();
                }
            });

            //selectrow();




            tableLayout.addView(rowFix,baris);
        }

        if (cursorreason.getCount()!=0){
            databaseContentComment = new String[cursorreason.getCount()];
            databaseIdComment  = new int[cursorreason.getCount()];
            for (int i=0; i<cursorreason.getCount(); i++){
                cursorreason.moveToPosition(i);
                if (cursorreason.getString(0)!=null){
                    databaseContentComment[i] = cursorreason.getString(0).toString();
                    databaseIdComment[i] = Integer.parseInt(cursorreason.getString(1).toString());
                }

            }

            for (int i=0; i<databaseContentComment.length;i++) {
                if (databaseContentComment[i] != null) {
                    reasontextcomment[databaseIdComment[i]] = databaseContentComment[i];
                }
            }

        }

        if (cursorUpload.getCount()!=0){

            appPrefs appPrefs = new appPrefs(context);
            continuedatabase = 1;
            minnotif = 1;
            minscontinue = Integer.parseInt(appPrefs.getDurasimins());
            hourscontinue = Integer.parseInt(appPrefs.getDurasihour());

            databaseIdSaving = new String[cursorUpload.getCount()];
            databaseIdSavingSkip = new String [cursorUpload.getCount()];


            customerFinished = cursorUpload.getCount();

            for (int i=0; i < cursorUpload.getCount(); i++){
                cursorUpload.moveToPosition(i);
                if (cursorUpload.getString(13)!=null){
                    databaseIdSaving[i] = cursorUpload.getString(13).toString();
                }
                if (cursorUpload.getString(14)!=null){
                    databaseIdSavingSkip[i] = cursorUpload.getString(14).toString();
                }
            }

            int b = 0;


            for (int i=0; i<databaseIdSaving.length;i++){
                if (databaseIdSaving[i]!=null){
                    b = Integer.parseInt(databaseIdSaving[i]);
                    donenotification[b] = "done";
                    report[b].setImageResource(R.drawable.pointdone);
                }
                else if (databaseIdSavingSkip[i]!=null){
                    b = Integer.parseInt(databaseIdSavingSkip[i]);
                    donenotification[b] = "skip";
                    report[b].setImageResource(R.drawable.pointnotdone);
                }
            }

        }

        //circleprocess1.dismiss();

    }

    public void selectrow(){



    }

    public void dummy(){

        rowFix.setClickable(true);

        starttrackingstatus=1;
        heighttemplate = viewcustomertemplate.getHeight();

        if (photos[awal]!=null){
            viewCustomer.setVisibility(View.VISIBLE);
            String m = photos[awal];
            byte[] avatar2 = Base64.decode(m, Base64.DEFAULT);
            Bitmap bmp = BitmapFactory.decodeByteArray(avatar2,0,avatar2.length);
            heightimage = heighttemplate;
            widthimage = bmp.getWidth()/bmp.getWidth()*heightimage;
            Bitmap resized = Bitmap.createScaledBitmap(bmp, heightimage, widthimage, true);
            viewCustomer.setImageBitmap(bmp);
        }

        else if (photos[awal]==null){
            viewCustomer.setVisibility(View.INVISIBLE);

        }



        RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        p.addRule(RelativeLayout.ABOVE, R.id.viewgradation);
        p.addRule(RelativeLayout.BELOW,R.id.viewgradationbot);
        mapFrame.setLayoutParams(p);
        listFrame.setLayoutParams(p);


        imageButton.setVisibility(View.VISIBLE);
        //contentPelanggan.setVisibility(View.VISIBLE);
        segmentedButton.setVisibility(View.VISIBLE);
        tandaPek.setVisibility(View.VISIBLE);
        timerCount.setVisibility(View.VISIBLE);

        double latPost = Double.parseDouble(latitudes[awal]);
        double longPost = Double.parseDouble(longitudes[awal]);
        LatLng currentLatLng = new LatLng(latPost, longPost);
        nMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15));
    }

    public void comment(final int inputan){
        LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.reason, null);
        imageDialog.setView(layout);
        imageDialog.setCancelable(false);
        ad = imageDialog.create();
        ad.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ad.show();

        TV4 = (TextView)layout.findViewById(R.id.textView4);
        TV5 = (TextView)layout.findViewById(R.id.textView5);
        TV = (TextView)layout.findViewById(R.id.textView);

        TV4.setTextSize(12);
        TV5.setTextSize(12);

        final Typeface SourceSansProRegular = Typeface.createFromAsset(getAssets(),  "fonts/SourceSansPro-Regular.ttf");


        TV4.setTypeface(SourceSansProRegular);
        TV5.setTypeface(SourceSansProRegular);
        TV.setTypeface(SourceSansProRegular);

        reason = (EditText)layout.findViewById(R.id.reasonEdit);
        reason.setTypeface(SourceSansProRegular);

        reason.setText(reasontextcomment[inputan]);

        save = (FrameLayout) layout.findViewById(R.id.save);

        save.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch ( event.getAction() ) {
                    case MotionEvent.ACTION_DOWN:
                        save.setBackgroundColor(getResources().getColor(R.color.lightgrey));
                        break;
                    case MotionEvent.ACTION_UP:
                        save.setBackgroundColor(getResources().getColor(R.color.white));

                        //loopingcomment++;
                        commentdoneskip = "ada";
                        ad.dismiss();

                        reasontextcomment[inputan] = reason.getText().toString();

                        comment[inputan].setImageResource(R.drawable.commentnewbgt);

                        values = new ContentValues();
                        values.put("reasonsatu",reason.getText().toString());
                        values.put("reasonindex",inputan);
                        values.put("spare1",inputan);

                        db.insert("reasontext",null,values);

                        break;
                }
                return true;
            }
        });

        cancel = (FrameLayout) layout.findViewById(R.id.cancel);
        cancel.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch ( event.getAction() ) {
                    case MotionEvent.ACTION_DOWN:
                        cancel.setBackgroundColor(getResources().getColor(R.color.lightgrey));
                        break;
                    case MotionEvent.ACTION_UP:
                        cancel.setBackgroundColor(getResources().getColor(R.color.white));
                        ad.dismiss();
                        break;
                }
                return true;
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
        }

        //iki if kanggo hpku, hpne hendra, hpne edwin, hpne mbak devita, hpne mas nanda
        else if (config.screenWidthDp >= 321){
            Bitmap resized1 = Bitmap.createScaledBitmap(bmp, 150, 150, true);
            Bitmap conv_bm1 = getRoundedRectBitmap(resized1, 150);
            displayImageActionDrawer = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(conv_bm1, bitmapWidth, bitmapHeight, true));
        }

        Bitmap resized = Bitmap.createScaledBitmap(bmp, 150, 150, true);
        Bitmap conv_bm = getRoundedRectBitmap(resized, 150);

        toggle.setHomeAsUpIndicator(displayImageActionDrawer);


        pictureDisplay.setImageBitmap(conv_bm);
    }

    public void finalalert(final int inputan){
        LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.popupnotif, null);

        donerute.setView(layout);
        donerute.setCancelable(false);
        alertDialog = donerute.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();

        TextView tv28, tv29, TV37;
        TV37 = (TextView)layout.findViewById(R.id.textView36);
        TV37.setText("Done");
        TV37.setTextSize(18);
        TV37.setTypeface(RegularType);
        TV37.setTextColor(getResources().getColor(R.color.basic));
        final FrameLayout frameLayout = (FrameLayout)layout.findViewById(R.id.frameLayout);
        tv28 = (TextView)layout.findViewById(R.id.textView28);
        tv29 = (TextView)layout.findViewById(R.id.textView29);

        tv28.setTextSize(12);
        tv29.setTextSize(12);

        tv28.setText("Paper delivery completed, press stop to upload report");
        tv29.setText("OK");

        tv28.setTypeface(RegularType);
        tv29.setTypeface(SemiBoldType);

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
                        circleprocess.dismiss();
                        datamarker(inputan);
                        break;
                }
                return true;
            }
        });
    }

    public void pinpoint(){
        BitmapDrawable biruitemsatu =(BitmapDrawable)getResources().getDrawable(R.drawable.biruitem);
        Bitmap biruitemdua=biruitemsatu.getBitmap();
        biruitem = Bitmap.createScaledBitmap(biruitemdua, heightmarker, widthmarker, false);

        BitmapDrawable biruputihsatu = (BitmapDrawable)getResources().getDrawable(R.drawable.biruputih);
        Bitmap biruputihdua = biruputihsatu.getBitmap();
        biruputih = Bitmap.createScaledBitmap(biruputihdua, heightmarker, widthmarker, false);



        BitmapDrawable ijohitamsatu = (BitmapDrawable)getResources().getDrawable(R.drawable.ijoitem);
        Bitmap ijohitamdua = ijohitamsatu.getBitmap();
        ijoitem = Bitmap.createScaledBitmap(ijohitamdua, heightmarker, widthmarker, false);

        BitmapDrawable merahitemsatu = (BitmapDrawable)getResources().getDrawable(R.drawable.merahitem);
        Bitmap merahitemdua = merahitemsatu.getBitmap();
        merahitem = Bitmap.createScaledBitmap(merahitemdua, heightmarker, widthmarker, false);

        BitmapDrawable ijoputihsatu = (BitmapDrawable)getResources().getDrawable(R.drawable.ijoputih);
        Bitmap ijoputihdua = ijoputihsatu.getBitmap();
        ijoputih = Bitmap.createScaledBitmap(ijoputihdua, heightmarker, widthmarker, false);

        BitmapDrawable merahputihsatu = (BitmapDrawable)getResources().getDrawable(R.drawable.merahputih);
        Bitmap merahputihdua = merahputihsatu.getBitmap();
        merahputih = Bitmap.createScaledBitmap(merahputihdua, heightmarker, widthmarker, false);
    }

    private void zoomImageFromThumb(final View thumbView, Bitmap imageResId) {
        // If there's an animation in progress, cancel it immediately and proceed with this one.
        if (mCurrentAnimator != null) {
            mCurrentAnimator.cancel();
        }

        // Load the high-resolution "zoomed-in" image.
        viewCustomer.setImageBitmap(imageResId);

        // Calculate the starting and ending bounds for the zoomed-in image. This step
        // involves lots of math. Yay, math.
        final Rect startBounds = new Rect();
        final Rect finalBounds = new Rect();
        final Point globalOffset = new Point();

        // The start bounds are the global visible rectangle of the thumbnail, and the
        // final bounds are the global visible rectangle of the container view. Also
        // set the container view's offset as the origin for the bounds, since that's
        // the origin for the positioning animation properties (X, Y).
        thumbView.getGlobalVisibleRect(startBounds);
        //findViewById(R.id.container).getGlobalVisibleRect(finalBounds, globalOffset);
        startBounds.offset(-globalOffset.x, -globalOffset.y);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);

        // Adjust the start bounds to be the same aspect ratio as the final bounds using the
        // "center crop" technique. This prevents undesirable stretching during the animation.
        // Also calculate the start scaling factor (the end scaling factor is always 1.0).
        float startScale;
        if ((float) finalBounds.width() / finalBounds.height()
                > (float) startBounds.width() / startBounds.height()) {
            // Extend start bounds horizontally
            startScale = (float) startBounds.height() / finalBounds.height();
            float startWidth = startScale * finalBounds.width();
            float deltaWidth = (startWidth - startBounds.width()) / 2;
            startBounds.left -= deltaWidth;
            startBounds.right += deltaWidth;
        } else {
            // Extend start bounds vertically
            startScale = (float) startBounds.width() / finalBounds.width();
            float startHeight = startScale * finalBounds.height();
            float deltaHeight = (startHeight - startBounds.height()) / 2;
            startBounds.top -= deltaHeight;
            startBounds.bottom += deltaHeight;
        }

        // Hide the thumbnail and show the zoomed-in view. When the animation begins,
        // it will position the zoomed-in view in the place of the thumbnail.
        thumbView.setAlpha(0f);
        viewCustomer.setVisibility(View.VISIBLE);

        // Set the pivot point for SCALE_X and SCALE_Y transformations to the top-left corner of
        // the zoomed-in view (the default is the center of the view).
        viewCustomer.setPivotX(0f);
        viewCustomer.setPivotY(0f);

        // Construct and run the parallel animation of the four translation and scale properties
        // (X, Y, SCALE_X, and SCALE_Y).
        AnimatorSet set = new AnimatorSet();
        set
                .play(ObjectAnimator.ofFloat(viewCustomer, View.X, startBounds.left,
                        finalBounds.left))
                .with(ObjectAnimator.ofFloat(viewCustomer, View.Y, startBounds.top,
                        finalBounds.top))
                .with(ObjectAnimator.ofFloat(viewCustomer, View.SCALE_X, startScale, 1f))
                .with(ObjectAnimator.ofFloat(viewCustomer, View.SCALE_Y, startScale, 1f));
        set.setDuration(mShortAnimationDuration);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mCurrentAnimator = null;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                mCurrentAnimator = null;
            }
        });
        set.start();
        mCurrentAnimator = set;

        // Upon clicking the zoomed-in image, it should zoom back down to the original bounds
        // and show the thumbnail instead of the expanded image.
        final float startScaleFinal = startScale;
        viewCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrentAnimator != null) {
                    mCurrentAnimator.cancel();
                }

                // Animate the four positioning/sizing properties in parallel, back to their
                // original values.
                AnimatorSet set = new AnimatorSet();
                set
                        .play(ObjectAnimator.ofFloat(viewCustomer, View.X, startBounds.left))
                        .with(ObjectAnimator.ofFloat(viewCustomer, View.Y, startBounds.top))
                        .with(ObjectAnimator
                                .ofFloat(viewCustomer, View.SCALE_X, startScaleFinal))
                        .with(ObjectAnimator
                                .ofFloat(viewCustomer, View.SCALE_Y, startScaleFinal));
                set.setDuration(mShortAnimationDuration);
                set.setInterpolator(new DecelerateInterpolator());
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        thumbView.setAlpha(1f);
                        viewCustomer.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        thumbView.setAlpha(1f);
                        viewCustomer.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                    }
                });
                set.start();
                mCurrentAnimator = set;
            }
        });
    }

}

