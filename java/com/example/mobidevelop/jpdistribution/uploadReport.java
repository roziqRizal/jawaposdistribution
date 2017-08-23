package com.example.mobidevelop.jpdistribution;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import android.view.ViewGroup.LayoutParams;
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
import java.util.Arrays;
import java.util.Date;
import java.util.Formatter;
import java.util.logging.Handler;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import retrofit2.http.HTTP;

public class uploadReport extends AppCompatActivity {

    String PublicToken= "";
    ImageView upload,profile;
    public String[]  nama, address, phone, alasan, timer, idDB, reasontextcomment, reasontextcomment2, distances, longitudesposition,latitudeposition,presstype,doneskipcomment,doneskipnotif;
    int[] coba;
    String total;
    int customerFinished = 0, failedconn=0;
    TableLayout tableLayout;
    TextView addressTable, alamat, telepon, status, deliveryPoint, checkBox, durasi, durasifix,deliverypointfix, Point2,Point3, TV4, TV5, TV, TV23, TV14, TV24, TV3, TV25, TV26;
    String customer, ambilDurasi;
    LinearLayout row,  rowFix, center, left, right,  center1,center2,center3 ;
    View line;
    ImageView comment, report, point;
    ImageView commentdua[];
    FrameLayout  OK;
    FrameLayout save, cancel;
    FrameLayout scrollViewframe;
    RelativeLayout scrollViewframe2;
    EditText reason;
    AlertDialog ad, ad2, ad3, ad4, alertDialog;
    AlertDialog.Builder imageDialog, imageDialog2, imageDialog3, imageDialog4, imagedialog5, donerute;
    ScrollView scrollView;
    private static final String HASH_ALGORITHM = "HmacSHA256";
    SimpleDateFormat dateFormat;
    Drawable draw;
    ProgressDialog progressBar;
    DilatingDotsProgressBar mDilatingDotsProgressBar;
    DataRute dbRute;
    protected Cursor cursorData, cursorUpload, cursorreason;
    String[] doneCustomer, statusID;
    int loopingCursor=0;
    public LocationManager locmgr;
    int loopingcomment=0, yangbelum, yangbelum2, yangbelum3;
    int bitmapWidth, bitmapHeight, pointHeight, pointWidth, segmentedWidth, segmentedHeigth, pointFiveteen;
    boolean doubleBackToExitPressedOnce = false;
    Typeface Semiboldtype, RegularType;
    ActionBarDrawerToggle toggle;
    FrameLayout topBarEdit;
    ImageView pictureDisplay;
    String signHashCodeFirstname, signHashCodeLastname, signHashCodeEmail;
    TextView username, editprofiletext, changepasswordtext,logouttext;
    LinearLayout editprofile,changepassword, logout;
    int endpointvalue=0;
    //ImageView suksesbro;
    String uModel,uId, apkversion = "/~9927";

    private Tracker mTracker;
    SQLiteDatabase read, write;
    Context context;


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
        setContentView(R.layout.activity_upload_report);

        overridePendingTransition(R.anim.startanim,R.anim.stopanim);

        topBarEdit = (FrameLayout)findViewById(R.id.topBarEdit);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        topBarEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawer.isDrawerVisible(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });

        donerute = new AlertDialog.Builder(this);

        pictureDisplay = (ImageView) findViewById(R.id.picture);

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

        Semiboldtype = Typeface.createFromAsset(getAssets(),  "fonts/SourceSansPro-Semibold.ttf");
        RegularType = Typeface.createFromAsset(getAssets(),  "fonts/SourceSansPro-Regular.ttf");

        TV24 = (TextView)findViewById(R.id.textView24);
        TV24.setTypeface(RegularType);

        TV25 = (TextView)findViewById(R.id.textView25);
        TV26 = (TextView)findViewById(R.id.textView26);
        TV25.setTypeface(RegularType);
        TV26.setTypeface(RegularType);

        TV25.setTextSize(12);
        TV26.setTextSize(12);

        bitmapHeight = (int) getResources().getDimension(R.dimen.bitmapHeight);
        bitmapWidth = (int) getResources().getDimension(R.dimen.bitmapHeight);
        pointWidth = (int) getResources().getDimension(R.dimen.pointWidth);
        pointFiveteen = (int) getResources().getDimension(R.dimen.pointfiveteen);
        pointHeight = (int) getResources().getDimension(R.dimen.pointHeight);
        segmentedWidth = (int) getResources().getDimension(R.dimen.segmentedWidth);
        segmentedHeigth = (int) getResources().getDimension(R.dimen.segmentedHeigth);


        TV3 = (TextView)findViewById(R.id.textView3);
        TV3.setTypeface(RegularType);

        //suksesbro = (ImageView)findViewById(R.id.imageView9);
        scrollViewframe = (FrameLayout) findViewById(R.id.scrollViewframe);
        scrollViewframe2 = (RelativeLayout) findViewById(R.id.scrollViewframe2);

        context = getApplicationContext();
        appPrefs appPrefs = new appPrefs(context);

        signHashCodeFirstname = appPrefs.getFirstname();
        signHashCodeLastname = appPrefs.getLastname();
        signHashCodeEmail = appPrefs.getEmail();

        uploadReport application = uploadReport.this;
        mTracker = application.getDefaultTracker();

        Log.i("Upload Report", "Setting screen name: " + signHashCodeFirstname+" "+signHashCodeLastname);
        uModel = Build.MODEL;
        uId = Build.BRAND;
        mTracker.setScreenName("UploadReport~"+""+uId+"/"+uModel+"/"+ signHashCodeFirstname+"_"+signHashCodeLastname+"_"+apkversion);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

        String m = appPrefs.getImage();
        byte[] avatar2 = Base64.decode(m, Base64.DEFAULT);
        Bitmap bmp = BitmapFactory.decodeByteArray(avatar2,0,avatar2.length);
        Bitmap resized = Bitmap.createScaledBitmap(bmp, 150, 150, true);
        Bitmap conv_bm = getRoundedRectBitmap(resized, 150);

        profile = (ImageView)findViewById(R.id.imageView);
        profile.setImageBitmap(conv_bm);
        pictureDisplay.setImageBitmap(conv_bm);

        username = (TextView) findViewById(R.id.username);
        username.setTypeface(RegularType);
        username.setText(signHashCodeFirstname+" "+signHashCodeLastname);

        imageDialog = new AlertDialog.Builder(this);
        imageDialog2 = new AlertDialog.Builder(this);
        imageDialog3 = new AlertDialog.Builder(this);
        imageDialog4 = new AlertDialog.Builder(this);
        imagedialog5 = new AlertDialog.Builder(this);

        dbRute = new DataRute(this);
        dbRute.connect();

        read = dbRute.getReadableDatabase();
        write = dbRute.getWritableDatabase();

        cursorUpload = write.rawQuery("SELECT * FROM UPLOAD",null);
        cursorData = read.rawQuery("SELECT * FROM DATA_PELANGGAN",null);
        cursorreason = read.rawQuery("SELECT * FROM reasontext",null);




        Typeface custom_font_login = Typeface.createFromAsset(getAssets(),  "fonts/SourceSansPro-Light.otf");

        draw=getResources().getDrawable(R.drawable.customprogressbar);
        setSupportActionBar(toolbar);
        scrollView = (ScrollView)findViewById(R.id.scrollView);
        deliveryPoint = (TextView)findViewById(R.id.deliveryPoint);
        Point2 = (TextView)findViewById(R.id.tengah);
        Point3 = (TextView)findViewById(R.id.kanan);
        durasi = (TextView)findViewById(R.id.duration);
        deliverypointfix = (TextView)findViewById(R.id.textView10);
        durasifix = (TextView)findViewById(R.id.textView11);

        deliveryPoint.setTypeface(RegularType);
        Point2.setTypeface(RegularType);
        Point3.setTypeface(RegularType);
        durasi.setTypeface(RegularType);
        deliverypointfix.setTypeface(RegularType);
        durasifix.setTypeface(RegularType);
        durasi.setTextSize(18);

        Intent myIntent = getIntent(); // gets the previously created intent

        locmgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        PublicToken = appPrefs.getToken();
        //customerFinished = myIntent.getIntExtra("customerFinished",0);

        total = myIntent.getStringExtra("totalCustomer");
        //reasontextcomment = myIntent.getStringArrayExtra("reasontextcomment");



        int totalInt = cursorData.getCount();


        customer = myIntent.getStringExtra("jsonArray");
        ambilDurasi = myIntent.getStringExtra("durasi");

        final String startID = myIntent.getStringExtra("StartID");


        progressBar = new ProgressDialog(uploadReport.this);
        progressBar.setMessage("logging out");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.setCancelable(false);

        //String tmpStr10 = String.valueOf(customerFinished);
        //final SpannableStringBuilder sb = new SpannableStringBuilder(tmpStr10);
        //final ForegroundColorSpan fcs = new ForegroundColorSpan(getResources().getColor(R.color.basic));

        //sb.setSpan(fcs, 0, customerFinished, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        String[] separated = ambilDurasi.split(":");
        String fixdurasi = separated[0]+"h "+separated[1]+"m";
        durasi.setText(ambilDurasi);
        durasi.setTextColor(getResources().getColor(R.color.black));
        getCustomer();
        initdua();
        //init();


        if (cursorUpload.getCount()<cursorData.getCount()){
            yangbelum = cursorData.getCount()-cursorUpload.getCount()+yangbelum2;
        }
        else {
            yangbelum = yangbelum2;
        }

        deliveryPoint.setTextColor(getResources().getColor(R.color.black));

        deliveryPoint.setText(totalInt-yangbelum+" ");

        Point3.setText(" "+cursorData.getCount());

        TV25.setText(yangbelum+" missing deliveries?");

        upload = (ImageView) findViewById(R.id.button5);

        if (customerFinished==cursorData.getCount()){
            TV25.setVisibility(View.INVISIBLE);
            TV26.setVisibility(View.INVISIBLE);
            upload.setVisibility(View.VISIBLE);
        }
        else if (yangbelum==0){
            TV25.setVisibility(View.INVISIBLE);
            TV26.setVisibility(View.INVISIBLE);
            upload.setVisibility(View.VISIBLE);
        }
        else {
            TV25.setVisibility(View.VISIBLE);
            TV26.setVisibility(View.VISIBLE);
            upload.setVisibility(View.INVISIBLE);
        }



        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mTracker.send(new HitBuilders.EventBuilder().setCategory("UploadActivity~/uploadButton/~923"+apkversion)
                        .setAction(signHashCodeEmail)
                        .setLabel(uModel+"/"+uId)
                        .build());

                dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String timestamp = dateFormat.format(new Date());

                upload.setVisibility(View.INVISIBLE);

                appPrefs appPrefs = new appPrefs(context);
                appPrefs.setDurasihour("");
                appPrefs.setDurasimins("");


                //scrollView.setVisibility(View.INVISIBLE);
                //scrollViewframe2.setVisibility(View.VISIBLE);
                try {
                    new upload().execute(PublicToken, startID, hashMac(timestamp, "0fab227b319afe10a0566183e5c7317dd23127b3f79a964481c0e08640f21acc"), timestamp);

                } catch (SignatureException e) {
                    e.printStackTrace();
                }

            }
        });

        editprofiletext = (TextView)findViewById(R.id.editprofil);
        changepasswordtext =(TextView)findViewById(R.id.changepassword);
        logouttext = (TextView) findViewById(R.id.logout);

        editprofiletext.setTypeface(RegularType);
        changepasswordtext.setTypeface(RegularType);
        logouttext.setTypeface(RegularType);

        editprofile = (LinearLayout)findViewById(R.id.editProfileLayout);
        changepassword = (LinearLayout)findViewById(R.id.changepasswordLayout);
        logout = (LinearLayout)findViewById(R.id.logoutLayout);

        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editProfile = new Intent(uploadReport.this, editProfil.class);
                endpointvalue = 3;
                startActivity(editProfile);
            }
        });

        changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent changePassword = new Intent(uploadReport.this, changePassword.class);
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

    public void getCustomer(){

        nama = new String[cursorUpload.getCount()];
        address = new String[cursorUpload.getCount()];
        phone = new String[cursorUpload.getCount()];
        alasan = new String[cursorUpload.getCount()];
        timer = new String[cursorUpload.getCount()];
        idDB = new String[cursorUpload.getCount()];
        commentdua = new ImageView[cursorUpload.getCount()];
        distances = new String[cursorUpload.getCount()];
        latitudeposition = new String[cursorUpload.getCount()];
        longitudesposition = new String[cursorUpload.getCount()];
        presstype = new String[cursorUpload.getCount()];
        reasontextcomment2 = new String[cursorUpload.getCount()];
        reasontextcomment = new String[cursorUpload.getCount()];

        ImageView hhh = new ImageView(this);
        hhh.setImageResource(R.drawable.commentnewbgt);

        doneCustomer=new String[cursorUpload.getCount()];
        statusID=new String[cursorUpload.getCount()];
        doneskipcomment = new String[cursorUpload.getCount()];
        doneskipnotif = new String[cursorUpload.getCount()];

        //Intent myIntent = getIntent();
        //reasontextcomment = myIntent.getStringExtra("totalCustomer");

        for (int i=0;i<cursorUpload.getCount();i++){
            cursorUpload.moveToPosition(i);
            idDB [i] = cursorUpload.getString(1).toString();
            nama [i] = cursorUpload.getString(2).toString();
            address [i] = cursorUpload.getString(6).toString();
            doneskipcomment[i] = cursorUpload.getString(11).toString();
            doneskipnotif[i] = cursorUpload.getString(12).toString();
            timer[i] = cursorUpload.getString(3).toString();
            distances[i] = cursorUpload.getString(7).toString();
            latitudeposition[i] = cursorUpload.getString(9).toString();
            longitudesposition[i] = cursorUpload.getString(8).toString();
            presstype[i] = cursorUpload.getString(10).toString();


            if (cursorUpload.getString(15)!=null){
                reasontextcomment2[i] = cursorUpload.getString(15).toString();
            }

            if (doneskipnotif[i].equals("done")){
                customerFinished++;
            }

            commentdua[i] = hhh;

            if (cursorUpload.getString(4)!=null){
                doneCustomer[i] = cursorUpload.getString(4).toString();
            }

        }

       coba = new int[cursorreason.getCount()];

        if (cursorreason.getCount()!=0){
            for (int j=0;j<cursorreason.getCount();j++){
                cursorreason.moveToPosition(j);
                if (cursorreason.getString(2)!=null){
                    coba[j] = Integer.parseInt(cursorreason.getString(2).toString());
                }
            }
        }



        for (int i=0;i<reasontextcomment2.length;i++){
            for (int j=0;j<coba.length;j++){
                cursorreason.moveToPosition(j);
                if (coba[j]==Integer.parseInt(reasontextcomment2[i])){
                   reasontextcomment[i]=cursorreason.getString(0).toString();
                }
            }
        }



    }

    public void initdua(){
        tableLayout = (TableLayout)findViewById(R.id.tableLayout);
        LayoutInflater inflater = getLayoutInflater();

        final Typeface Semiboldtype = Typeface.createFromAsset(getAssets(),  "fonts/SourceSansPro-Semibold.ttf");
        final Typeface SourceSansProRegular = Typeface.createFromAsset(getAssets(),  "fonts/SourceSansPro-Regular.ttf");

        for (int i=0; i<cursorUpload.getCount();i++){
            LinearLayout rowcontent = (LinearLayout) inflater.inflate(R.layout.tablerow,tableLayout,false);
            TextView textView39 = (TextView)rowcontent.findViewById(R.id.textView39);
            TextView textView40 = (TextView)rowcontent.findViewById(R.id.textView40);
            LinearLayout realtablerow = (LinearLayout)rowcontent.findViewById(R.id.realtabelrow);
            ImageView pointimage = (ImageView)rowcontent.findViewById(R.id.imageView17);
            final ImageView commentimage = (ImageView)rowcontent.findViewById(R.id.imageView18);


            if (doneskipnotif[i].equals("done")){
                pointimage.setImageResource(R.drawable.pointdone);
            }

            else if (doneskipnotif[i].equals("skip")){
                pointimage.setImageResource(R.drawable.pointnotdone);

            }


            /*if (doneskipcomment[i].equals("ada")){
                commentimage.setImageResource(R.drawable.commentnewbgt);
            }*/


            if (reasontextcomment[i]!=null){
                commentimage.setImageResource(R.drawable.commentnewbgt);
            }

            else if (reasontextcomment[i]==null && doneskipnotif[i].equals("skip")){
                commentimage.setImageResource(R.drawable.commentnewbgtcopy);
                yangbelum2++;
            }


            final int finalI = i;
            commentimage.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View arg0) {
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

                    TV4.setTypeface(SourceSansProRegular);
                    TV5.setTypeface(SourceSansProRegular);
                    TV.setTypeface(SourceSansProRegular);

                    TV4.setTextSize(12);
                    TV5.setTextSize(12);


                    reason = (EditText)layout.findViewById(R.id.reasonEdit);
                    reason.setTypeface(SourceSansProRegular);
                    reason.setText(reasontextcomment[finalI],TextView.BufferType.EDITABLE);

                    save = (FrameLayout) layout.findViewById(R.id.save);
                    save.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

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

                            tv28.setText("Comment has been added");
                            tv29.setText("OK");

                            tv28.setTypeface(RegularType);
                            tv29.setTypeface(Semiboldtype);

                            final String katalistor = reasontextcomment[finalI];

                            frameLayout.setOnTouchListener(new View.OnTouchListener() {
                                @Override
                                public boolean onTouch(View v, MotionEvent event) {
                                    switch ( event.getAction() ) {
                                        case MotionEvent.ACTION_DOWN:
                                            frameLayout.setBackgroundColor(getResources().getColor(R.color.lightgrey));
                                            break;
                                        case MotionEvent.ACTION_UP:
                                            frameLayout.setBackgroundColor(getResources().getColor(R.color.white));
                                            String a = reason.getEditableText().toString();
                                            int as = reason.length();

                                            if (as==0){
                                                commentimage.setImageResource(R.drawable.commentnewbgtcopy);
                                            }
                                            else {
                                                commentimage.setImageResource(R.drawable.commentnewbgt);

                                            }

                                            reasontextcomment[finalI] = reason.getText().toString();

                                            if (katalistor==null){
                                                yangbelum = yangbelum-1;
                                                TV25.setText(yangbelum+" missing deliveries?");

                                                if (yangbelum==0){
                                                    TV25.setVisibility(View.INVISIBLE);
                                                    TV26.setVisibility(View.INVISIBLE);
                                                    upload.setVisibility(View.VISIBLE);
                                                }
                                            }

                                            alertDialog.dismiss();
                                            break;
                                    }
                                    return true;
                                }
                            });


                            loopingcomment++;

                            cursorUpload.moveToPosition(loopingCursor);

                            /*SQLiteDatabase db = dbRute.getWritableDatabase();
                            ContentValues values = new ContentValues();
                            values.put("comment",reason.getText().toString());
                            //alasan[finalI] = reason.getText().toString();
                            db.insert("UPLOAD", null, values);*/


                            ad.dismiss();

                        }
                    });

                    cancel = (FrameLayout) layout.findViewById(R.id.cancel);
                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ad.dismiss();
                        }
                    });
                }});


            //commentimage = commentdua[i];

            textView39.setTypeface(Semiboldtype);
            textView40.setTypeface(SourceSansProRegular);

            textView39.setTextSize(12);
            textView40.setTextSize(12);

            textView39.setText(nama[i]);
            textView40.setText(address[i]);

            textView39.setTextColor(Color.parseColor("#000000"));
            textView40.setTextColor(Color.parseColor("#000000"));


            tableLayout.addView(rowcontent);
        }


    }

    public void init(){
        tableLayout = (TableLayout)findViewById(R.id.tableLayout);

        final Typeface Semiboldtype = Typeface.createFromAsset(getAssets(),  "fonts/SourceSansPro-Semibold.ttf");
        final Typeface SourceSansProRegular = Typeface.createFromAsset(getAssets(),  "fonts/SourceSansPro-Regular.ttf");

        for (int i=0;i<cursorUpload.getCount();i++){

            loopingCursor = i;

            LinearLayout.LayoutParams namasetlayout = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            namasetlayout.setMargins(0,7,0,7);

            LinearLayout.LayoutParams leftParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            leftParams.setMargins(20,0,20,0);
            leftParams.gravity= Gravity.LEFT;

            LinearLayout.LayoutParams rightParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            rightParams.setMargins(60,0,40,0);


            final LinearLayout.LayoutParams image = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            image.setMargins(5,10,5,5);

            RelativeLayout.LayoutParams imagepoint = new RelativeLayout.LayoutParams(pointHeight, pointHeight);
            imagepoint.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            imagepoint.setMargins(5,5,5,5);

            LinearLayout.LayoutParams image3 = new LinearLayout.LayoutParams(pointHeight, pointHeight);
            image.setMargins(5,30,5,0);

            LinearLayout.LayoutParams image2 = new LinearLayout.LayoutParams(pointFiveteen, pointFiveteen);
            image.setMargins(5,0,5,30);

            right = new LinearLayout(this);
            right.setLayoutParams(rightParams);
            right.setOrientation(LinearLayout.VERTICAL);
            center = new LinearLayout(this);
            center.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1));
            center.setOrientation(LinearLayout.VERTICAL);
            left = new LinearLayout(this);
            left.setLayoutParams(leftParams);
            left.setOrientation(LinearLayout.VERTICAL);
            row = new LinearLayout(this);
            row.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            row.setOrientation(LinearLayout.HORIZONTAL);
            rowFix = new LinearLayout(this);
            rowFix.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            rowFix.setOrientation(LinearLayout.VERTICAL);

            point = new ImageView(this);
            point.setImageResource(R.drawable.pointbaru);
            point.setLayoutParams(imagepoint);

            report = new ImageView(this);
            report.setLayoutParams(image2);


            commentdua[i].setImageResource(R.drawable.commentnewbgt);

            comment = new ImageView(this);
            LinearLayout.LayoutParams image3center = new LinearLayout.LayoutParams(pointHeight,pointHeight);
            image3center.gravity = Gravity.CENTER_VERTICAL;
            comment.setLayoutParams(image3center);


            if (reasontextcomment[i]!=null){
                comment.setImageResource(R.drawable.commentnewbgt);
            }

            final int finalI = i;
            comment.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View arg0) {
                    LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                    View layout = layoutInflater.inflate(R.layout.reason, null);
                    imageDialog.setView(layout);

                    mTracker.send(new HitBuilders.EventBuilder().setCategory("UploadActivity~/commentButton/~923"+apkversion)
                            .setAction(signHashCodeEmail)
                            .setLabel(uModel+"/"+uId)
                            .build());

                    imageDialog.setCancelable(false);
                    ad = imageDialog.create();
                    ad.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    ad.show();

                    TV4 = (TextView)layout.findViewById(R.id.textView4);
                    TV5 = (TextView)layout.findViewById(R.id.textView5);
                    TV = (TextView)layout.findViewById(R.id.textView);

                    TV4.setTypeface(SourceSansProRegular);
                    TV5.setTypeface(SourceSansProRegular);
                    TV.setTypeface(SourceSansProRegular);

                    TV4.setTextSize(12);
                    TV5.setTextSize(12);


                    reason = (EditText)layout.findViewById(R.id.reasonEdit);
                    reason.setTypeface(SourceSansProRegular);
                    reason.setText(reasontextcomment[finalI],TextView.BufferType.EDITABLE);

                    save = (FrameLayout) layout.findViewById(R.id.save);
                    save.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

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

                            reasontextcomment[finalI] = reason.getText().toString();

                            loopingcomment++;

                            cursorUpload.moveToPosition(loopingCursor);

                            /*SQLiteDatabase db = dbRute.getWritableDatabase();
                            ContentValues values = new ContentValues();
                            values.put("comment",reason.getText().toString());
                            //alasan[finalI] = reason.getText().toString();
                            db.insert("UPLOAD", null, values);*/


                            ad.dismiss();

                        }
                    });

                    cancel = (FrameLayout) layout.findViewById(R.id.cancel);
                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ad.dismiss();
                        }
                    });
                }});


            checkBox = new TextView(this);
            addressTable = new TextView(this);
            alamat = new TextView(this);
            telepon = new TextView(this);
            status = new TextView(this);

            checkBox.setTypeface(Semiboldtype);
            addressTable.setTypeface(SourceSansProRegular);
            telepon.setTypeface(SourceSansProRegular);
            addressTable.setTextColor(Color.GRAY);

            addressTable.setLayoutParams(namasetlayout);

            checkBox.setTextColor(Color.parseColor("#000000"));
            addressTable.setTextColor(Color.parseColor("#000000"));
            alamat.setTextColor(Color.parseColor("#000000"));
            telepon.setTextColor(Color.parseColor("#000000"));
            status.setTextColor(Color.parseColor("#000000"));

            checkBox.setText(nama[i]);
            status.setTextSize(12);
            addressTable.setText(address[i]);
            addressTable.setTextSize(12);
            telepon.setText(phone[i]);
            telepon.setTextSize(12);

            line = new View(this);
            LinearLayout.LayoutParams lain = (new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 1));
            lain.setMargins(15,28,15,28);
            line.setLayoutParams(lain);
            line.setBackgroundColor(Color.parseColor("#FFDFDFDF"));

            String statusString = doneCustomer[i];
            String sukses = "sukses";


            if (statusString==null){
                doneCustomer[i] = "gagal";
                status.setText(" belum dikirim");
                report.setImageResource(R.drawable.pointnotdone);
                comment.setImageResource(R.drawable.commentnewbgt);
            }

            /*if (phone[i]==null){
                    telepon.setText(" -");
                }*/

            else {
                status.setText("sudah dikirim");
                report.setImageResource(R.drawable.pointdone);
            }

            /*if (statusString==sukses){
                status.setText(" sudah dikirim");
                report.setImageResource(R.drawable.complete);


            }*/

            center1 = new LinearLayout(this);
            center1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            center1.setOrientation(LinearLayout.VERTICAL);

            center2 = new LinearLayout(this);
            center2.setLayoutParams(namasetlayout);
            center2.setOrientation(LinearLayout.VERTICAL);

            center3 = new LinearLayout(this);
            center3.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            center3.setOrientation(LinearLayout.VERTICAL);

            center1.addView(checkBox);
            center2.addView(addressTable);


            right.addView(comment);
            center.addView(center1);
            center.addView(center2);
            center.addView(center3);
            left.addView(report);

            row.addView(left);
            row.addView(center);
            row.addView(right);

            rowFix.addView(row);
            rowFix.addView(line);

            tableLayout.addView(rowFix,i);

        }
    }

    public void ubah(int inputan){
        comment.setImageResource(R.drawable.commentnewbgt);
    }

    public class upload extends AsyncTask<String , String, String> {
        HttpURLConnection startTracking;
        URL url = null;


        @Override
        protected void onPreExecute(){
            super.onPreExecute();

            LayoutInflater layoutInflater4 = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
            View layout4 = layoutInflater4.inflate(R.layout.downloadprogres, null);
            mDilatingDotsProgressBar = (DilatingDotsProgressBar) layout4.findViewById(R.id.progress);
            mDilatingDotsProgressBar.show();

            imageDialog4.setView(layout4);
            imageDialog4.setCancelable(false);
            ad4 = imageDialog4.create();
            ad4.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            ad4.show();
            //uploadProcess = new ProgressDialog(uploadReport.this);
            //uploadProcess.setMessage("Upload data . . . .");
            //uploadProcess.setIndeterminate(true);
            //uploadProcess.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            //uploadProcess.setCancelable(false);
            //uploadProcess.show();

            /*LayoutInflater layoutInflater3 = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
            View layout3 = layoutInflater3.inflate(R.layout.progressupload, null);

            imageDialog3.setView(layout3);
            imageDialog3.setCancelable(false);
            ad3 = imageDialog3.create();
            ad3.show();
*/




        }

        @Override
        protected String doInBackground(String... params){

            try{
                url = new URL("http://lab.hellomorra.com/jawapos/api/post-report");
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


                /*for (int i=0;i<cursorUpload.getCount();i++){
                    cursorUpload.moveToPosition(i);
                    if (cursorUpload.getString(5)!=null){
                        alasan[i] = cursorUpload.getString(5).toString();
                    }
                }*/

                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < reasontextcomment.length; i++) {
                    sb.append(reasontextcomment[i]);
                    if (i != reasontextcomment.length - 1) {
                        sb.append(",");
                    }
                }
                String alasanUpload = sb.toString();

                StringBuilder sb1 = new StringBuilder();
                for (int i = 0; i < timer.length; i++) {
                    sb1.append(timer[i]);
                    if (i != timer.length - 1) {
                        sb1.append(",");
                    }
                }
                String timerUpload = sb1.toString();

                StringBuilder sb2 = new StringBuilder();
                for (int i = 0; i < idDB.length; i++) {
                    sb2.append(idDB[i]);
                    if (i != idDB.length - 1) {
                        sb2.append(",");
                    }
                }
                String idDBUpload = sb2.toString();

                StringBuilder sb3 = new StringBuilder();
                for (int i = 0; i < doneCustomer.length; i++) {
                    sb3.append(doneCustomer[i]);
                    if (i != doneCustomer.length - 1) {
                        sb3.append(",");
                    }
                }
                String statusIDUpload = sb3.toString();

                StringBuilder sb4 = new StringBuilder();
                for (int i = 0; i < distances.length; i++) {
                    sb4.append(distances[i]);
                    if (i != distances.length - 1) {
                        sb4.append(",");
                    }
                }
                String distanceupload = sb4.toString();

                StringBuilder sb5 = new StringBuilder();
                for (int i = 0; i < latitudeposition.length; i++){
                    sb5.append(latitudeposition[i]);
                    if (i != latitudeposition.length - 1) {
                        sb5.append(",");
                    }
                }
                String latitudepositionupload = sb5.toString();

                StringBuilder sb6 = new StringBuilder();
                for (int i = 0; i < longitudesposition.length; i++){
                    sb6.append(longitudesposition[i]);
                    if (i != longitudesposition.length - 1) {
                        sb6.append(",");
                    }
                }
                String longitudepositionupload = sb6.toString();

                StringBuilder sb7 = new StringBuilder();
                for (int i = 0; i < presstype.length; i++){
                    sb7.append(presstype[i]);
                    if (i != presstype.length - 1) {
                        sb7.append(",");
                    }
                }
                String presstypeupload = sb7.toString();



                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("token", params[0])
                        .appendQueryParameter("report_id",params[1])
                        .appendQueryParameter("cust_id", idDBUpload)
                        .appendQueryParameter("time", timerUpload)
                        .appendQueryParameter("reason", alasanUpload)
                        .appendQueryParameter("status", statusIDUpload)
                        .appendQueryParameter("sign", params[2])
                        .appendQueryParameter("timestamp",params[3])
                        .appendQueryParameter("distance",distanceupload)
                        .appendQueryParameter("longitude",longitudepositionupload)
                        .appendQueryParameter("latitude",latitudepositionupload)
                        .appendQueryParameter("type",presstypeupload);
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
                System.out.println("gagal");
                failedconn = 1;
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
                failedconn = 1;
                e.printStackTrace();
                return "exception";
            } finally {
                failedconn = 0;
                startTracking.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String result) {

            //uploadProcess.dismiss();
            ad4.dismiss();
            //scrollView.setVisibility(View.INVISIBLE);
            //scrollViewframe2.setVisibility(View.VISIBLE);

            if (failedconn==0){
                LayoutInflater layoutInflater2 = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View layout2 = layoutInflater2.inflate(R.layout.report_uploaded, null);

                imageDialog2.setView(layout2);
                imageDialog2.setCancelable(false);
                ad2 = imageDialog2.create();
                ad2.show();

                OK = (FrameLayout)layout2.findViewById(R.id.OK);

                Typeface Semiboldtype = Typeface.createFromAsset(getAssets(),  "fonts/SourceSansPro-Semibold.ttf");
                final Typeface SourceSansProRegular = Typeface.createFromAsset(getAssets(),  "fonts/SourceSansPro-Regular.ttf");


                TV14 = (TextView)layout2.findViewById(R.id.textView14);
                TV23 = (TextView)layout2.findViewById(R.id.textView23);
                TV14.setTypeface(SourceSansProRegular);
                TV23.setTypeface(SourceSansProRegular);

                OK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ad2.dismiss();

                        mTracker.send(new HitBuilders.EventBuilder().setCategory("UploadActivity~/uploadSuccess/~923"+apkversion)
                                .setAction(signHashCodeEmail)
                                .setLabel(uModel+"/"+uId)
                                .build());
                        TV3.setText("Report");
                        //suksesImage.setVisibility(View.VISIBLE);
                        scrollViewframe.setVisibility(View.INVISIBLE);

                        read.delete("UPLOAD",null,null);


                        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String timestamp = dateFormat.format(new Date());
                        try {
                            new Logout().execute(PublicToken, hashMac(timestamp, "0fab227b319afe10a0566183e5c7317dd23127b3f79a964481c0e08640f21acc"), timestamp);
                        } catch (SignatureException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }

            else {
                //Toast.makeText(this, "tekan sekali lagi untuk keluar " , Toast.LENGTH_SHORT).show();
                LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View layout = layoutInflater.inflate(R.layout.popupnotif, null);

                upload.setVisibility(View.VISIBLE);

                donerute.setView(layout);
                donerute.setCancelable(false);
                alertDialog = donerute.create();
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.show();

                TextView tv28, tv29, TV37;
                TV37 = (TextView)layout.findViewById(R.id.textView36);
                TV37.setText("Missing Connection");
                TV37.setTextSize(18);
                TV37.setTypeface(RegularType);
                TV37.setTextColor(getResources().getColor(R.color.red));
                final FrameLayout frameLayout = (FrameLayout)layout.findViewById(R.id.frameLayout);
                tv28 = (TextView)layout.findViewById(R.id.textView28);
                tv29 = (TextView)layout.findViewById(R.id.textView29);

                tv28.setTextSize(12);
                tv29.setTextSize(12);

                tv28.setText("connection failed!! please check your internet connection and try again");
                tv29.setText("OK");

                tv28.setTypeface(RegularType);
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




        } // protected void onPostExecute(Void v)
    }

    public class Logout extends AsyncTask<String , String, String> {
        HttpURLConnection track;
        URL url = null;


        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            LayoutInflater layoutInflater4 = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
            View layout4 = layoutInflater4.inflate(R.layout.downloadprogres, null);
            mDilatingDotsProgressBar = (DilatingDotsProgressBar) layout4.findViewById(R.id.progress);
            mDilatingDotsProgressBar.show();

            imageDialog4.setView(layout4);
            imageDialog4.setCancelable(false);
            ad4 = imageDialog4.create();
            ad4.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            ad4.show();
        }

        @Override
        protected String doInBackground(String... params){

            try{
                url = new URL("http://lab.hellomorra.com/jawapos/api/mobile-logout");
            }
            catch (MalformedURLException e){
                e.printStackTrace();
                return "exception";
            }
            try {
                track = (HttpURLConnection)url.openConnection();
                track.setReadTimeout(10000);
                track.setConnectTimeout(15000);
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

            ad4.dismiss();
            Intent intent = new Intent(uploadReport.this,Launch.class);
            startActivity(intent);
        }
    }

    AlertDialog backonprevious;
    @Override
    public void onBackPressed() {

        LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.popupnotiftomboldua, null);

       // if (doubleBackToExitPressedOnce) {

            AlertDialog.Builder backprevious = new AlertDialog.Builder(this);
            backprevious.setView(layout);
            TextView TV28, TV29, TV30, TV37;
        final FrameLayout frameLayout, frameLayout2;
        frameLayout = (FrameLayout)layout.findViewById(R.id.frameLayout);
        frameLayout2 = (FrameLayout)layout.findViewById(R.id.frameLayout2);
            TV37 = (TextView)layout.findViewById(R.id.textView37);
            TV28 = (TextView)layout.findViewById(R.id.textView28);
            TV29 = (TextView)layout.findViewById(R.id.textView29);
            TV30 = (TextView)layout.findViewById(R.id.textView30);

            TV37.setTypeface(RegularType);
            TV28.setTypeface(RegularType);
            TV29.setTypeface(Semiboldtype);
            TV30.setTypeface(Semiboldtype);

            TV37.setTextSize(18);
            TV28.setTextSize(12);
            TV29.setTextSize(12);
            TV30.setTextSize(12);

            TV37.setText("Exit page");
            TV28.setText("Are you sure to quit this page?");
            TV29.setText("YES");
            TV30.setText("NO");

            backonprevious = backprevious.create();
            backonprevious.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            backonprevious.show();

        frameLayout2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch ( event.getAction() ) {
                    case MotionEvent.ACTION_DOWN:
                        frameLayout2.setBackgroundColor(getResources().getColor(R.color.lightgrey));
                        break;
                    case MotionEvent.ACTION_UP:
                        frameLayout2.setBackgroundColor(getResources().getColor(R.color.white));
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
                        finish();
                        break;
                }
                return true;
            }
        });




        //    return;
        //}

        /*this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "tekan sekali lagi untuk keluar " , Toast.LENGTH_SHORT).show();

        new android.os.Handler().postDelayed(new Runnable() {

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

}
