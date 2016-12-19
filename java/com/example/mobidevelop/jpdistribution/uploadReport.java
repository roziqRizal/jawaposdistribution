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
import android.os.Bundle;
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
import android.view.View;
import android.view.ViewGroup;
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
    public String[]  nama, address, phone, alasan, timer, idDB, reasontextcomment;
    String total;
    int customerFinished;
    TableLayout tableLayout;
    TextView addressTable, alamat, telepon, status, deliveryPoint, checkBox, durasi, durasifix,deliverypointfix, Point2,Point3, TV4, TV5, TV, TV23, TV14, TV24, TV3, TV25, TV26;
    String customer, ambilDurasi;
    LinearLayout row,  rowFix, center, left, right,  center1,center2,center3 ;
    View line;
    ImageView comment, report, point;
    FrameLayout  OK;
    FrameLayout save, cancel;
    FrameLayout scrollViewframe;
    RelativeLayout scrollViewframe2;
    EditText reason;
    AlertDialog ad, ad2, ad3, ad4;
    AlertDialog.Builder imageDialog, imageDialog2, imageDialog3, imageDialog4, imagedialog5;
    ScrollView scrollView;
    private static final String HASH_ALGORITHM = "HmacSHA256";
    SimpleDateFormat dateFormat;
    Drawable draw;
    ProgressDialog progressBar;
    DilatingDotsProgressBar mDilatingDotsProgressBar;
    DataRute dbRute;
    protected Cursor cursorData, cursorUpload;
    String[] doneCustomer, statusID;
    int loopingCursor=0;
    public LocationManager locmgr;
    int loopingcomment=0, yangbelum;
    //ImageView suksesbro;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_report);

        Typeface Semiboldtype = Typeface.createFromAsset(getAssets(),  "fonts/SourceSansPro-Semibold.ttf");
        Typeface SourceSansProRegular = Typeface.createFromAsset(getAssets(),  "fonts/SourceSansPro-Regular.ttf");

        TV24 = (TextView)findViewById(R.id.textView24);
        TV24.setTypeface(SourceSansProRegular);

        TV25 = (TextView)findViewById(R.id.textView25);
        TV26 = (TextView)findViewById(R.id.textView26);
        TV25.setTypeface(SourceSansProRegular);
        TV26.setTypeface(SourceSansProRegular);


        TV3 = (TextView)findViewById(R.id.textView3);
        TV3.setTypeface(SourceSansProRegular);

        //suksesbro = (ImageView)findViewById(R.id.imageView9);
        scrollViewframe = (FrameLayout) findViewById(R.id.scrollViewframe);
        scrollViewframe2 = (RelativeLayout) findViewById(R.id.scrollViewframe2);

        Context context = getApplicationContext();
        appPrefs appPrefs = new appPrefs(context);

        String m = appPrefs.getImage();
        byte[] avatar2 = Base64.decode(m, Base64.DEFAULT);
        Bitmap bmp = BitmapFactory.decodeByteArray(avatar2,0,avatar2.length);
        Bitmap resized = Bitmap.createScaledBitmap(bmp, 150, 150, true);
        Bitmap conv_bm = getRoundedRectBitmap(resized, 150);

        profile = (ImageView)findViewById(R.id.imageView);
        profile.setImageBitmap(conv_bm);

        imageDialog = new AlertDialog.Builder(this);
        imageDialog2 = new AlertDialog.Builder(this);
        imageDialog3 = new AlertDialog.Builder(this);
        imageDialog4 = new AlertDialog.Builder(this);
        imagedialog5 = new AlertDialog.Builder(this);

        dbRute = new DataRute(this);
        dbRute.connect();

        SQLiteDatabase read = dbRute.getReadableDatabase();
        SQLiteDatabase write = dbRute.getWritableDatabase();

        cursorUpload = write.rawQuery("SELECT * FROM UPLOAD",null);
        cursorData = read.rawQuery("SELECT * FROM DATA_PELANGGAN",null);

        Typeface custom_font_login = Typeface.createFromAsset(getAssets(),  "fonts/SourceSansPro-Light.otf");

        draw=getResources().getDrawable(R.drawable.customprogressbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        scrollView = (ScrollView)findViewById(R.id.scrollView);
        deliveryPoint = (TextView)findViewById(R.id.deliveryPoint);
        Point2 = (TextView)findViewById(R.id.tengah);
        Point3 = (TextView)findViewById(R.id.kanan);
        durasi = (TextView)findViewById(R.id.duration);
        deliverypointfix = (TextView)findViewById(R.id.textView10);
        durasifix = (TextView)findViewById(R.id.textView11);

        deliveryPoint.setTypeface(SourceSansProRegular);
        Point2.setTypeface(SourceSansProRegular);
        Point3.setTypeface(SourceSansProRegular);
        durasi.setTypeface(custom_font_login);
        deliverypointfix.setTypeface(custom_font_login);
        durasifix.setTypeface(custom_font_login);

        Intent myIntent = getIntent(); // gets the previously created intent

        locmgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        PublicToken = appPrefs.getToken();
        customerFinished = myIntent.getIntExtra("customerFinished",0);

        total = myIntent.getStringExtra("totalCustomer");
        reasontextcomment = myIntent.getStringArrayExtra("reasontextcomment");

        int totalInt = cursorData.getCount();


        customer = myIntent.getStringExtra("jsonArray");
        ambilDurasi = myIntent.getStringExtra("durasi");

        final String startID = myIntent.getStringExtra("StartID");


        progressBar = new ProgressDialog(uploadReport.this);
        progressBar.setMessage("logging out");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.setCancelable(false);

        String tmpStr10 = String.valueOf(customerFinished);
        final SpannableStringBuilder sb = new SpannableStringBuilder(tmpStr10);
        final ForegroundColorSpan fcs = new ForegroundColorSpan(getResources().getColor(R.color.basic));

        sb.setSpan(fcs, 0, 2, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        yangbelum = totalInt-customerFinished;

        System.out.println(" ini yang sudah "+customerFinished);
        System.out.println(" ini yang belum "+total);
        deliveryPoint.setText(sb+" ");
        Point3.setText(" "+cursorData.getCount());

        TV25.setText(yangbelum+" missing points?");

        upload = (ImageView) findViewById(R.id.button5);

        if (totalInt == customerFinished){
            TV25.setVisibility(View.INVISIBLE);
            TV26.setVisibility(View.INVISIBLE);
            upload.setVisibility(View.VISIBLE);
        }

        String[] separated = ambilDurasi.split(":");
        String fixdurasi = separated[0]+"h "+separated[1]+"m";
        durasi.setText(fixdurasi);
        durasi.setTextColor(getResources().getColor(R.color.basic));;
        getCustomer();
        init();



        System.out.println(Arrays.deepToString(doneCustomer));


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String timestamp = dateFormat.format(new Date());

                upload.setVisibility(View.INVISIBLE);

                System.out.println("ini ID"+ startID);






                //scrollView.setVisibility(View.INVISIBLE);
                //scrollViewframe2.setVisibility(View.VISIBLE);



                try {
                    new upload().execute(PublicToken, startID, hashMac(timestamp, "0fab227b319afe10a0566183e5c7317dd23127b3f79a964481c0e08640f21acc"), timestamp);

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

        nama = new String[cursorData.getCount()];
        address = new String[cursorData.getCount()];
        phone = new String[cursorData.getCount()];
        alasan = new String[cursorData.getCount()];
        timer = new String[cursorData.getCount()];
        idDB = new String[cursorData.getCount()];
        doneCustomer=new String[cursorUpload.getCount()];
        statusID=new String[cursorUpload.getCount()];
        //reasontextcomment = new String[cursorData.getCount()];

        //Intent myIntent = getIntent();
        //reasontextcomment = myIntent.getStringExtra("totalCustomer");

        for (int i=0;i<cursorData.getCount();i++){
            cursorData.moveToPosition(i);
            cursorUpload.moveToPosition(i);
            idDB [i] = cursorData.getString(1).toString();
            nama [i] = cursorData.getString(2).toString();
            address [i] = cursorData.getString(3).toString();
            phone [i] = cursorData.getString(4).toString();
            timer[i] = cursorUpload.getString(3).toString();

            if (cursorUpload.getString(4)!=null){
                doneCustomer[i] = cursorUpload.getString(4).toString();

            }

            System.out.println(doneCustomer[i]);



        }
    }

    public void init(){
        tableLayout = (TableLayout)findViewById(R.id.tableLayout);

        Typeface Semiboldtype = Typeface.createFromAsset(getAssets(),  "fonts/SourceSansPro-Semibold.ttf");
        final Typeface SourceSansProRegular = Typeface.createFromAsset(getAssets(),  "fonts/SourceSansPro-Regular.ttf");

        for (int i=0;i<cursorData.getCount();i++){

            loopingCursor = i;

            LinearLayout.LayoutParams namasetlayout = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            namasetlayout.setMargins(0,7,0,7);

            LinearLayout.LayoutParams leftParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            leftParams.setMargins(20,0,20,0);
            leftParams.gravity= Gravity.LEFT;

            LinearLayout.LayoutParams rightParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            rightParams.setMargins(60,0,40,0);
            rightParams.gravity= Gravity.RIGHT;

            final LinearLayout.LayoutParams image = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            image.setMargins(5,10,5,5);

            RelativeLayout.LayoutParams imagepoint = new RelativeLayout.LayoutParams(40, 40);
            imagepoint.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            imagepoint.setMargins(5,5,5,5);

            LinearLayout.LayoutParams image3 = new LinearLayout.LayoutParams(60, 60);
            image.setMargins(5,30,5,0);

            LinearLayout.LayoutParams image2 = new LinearLayout.LayoutParams(60, 60);
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



            comment = new ImageView(this);
            comment.setImageResource(R.drawable.commentimageresult);
            comment.setLayoutParams(image3);
            final int finalI = i;
            comment.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View arg0) {
                    LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                    View layout = layoutInflater.inflate(R.layout.reason, null);
                    imageDialog.setView(layout);

                    imageDialog.setCancelable(false);
                    ad = imageDialog.create();
                    ad.show();

                    TV4 = (TextView)layout.findViewById(R.id.textView4);
                    TV5 = (TextView)layout.findViewById(R.id.textView5);
                    TV = (TextView)layout.findViewById(R.id.textView);

                    TV4.setTypeface(SourceSansProRegular);
                    TV5.setTypeface(SourceSansProRegular);
                    TV.setTypeface(SourceSansProRegular);

                    System.out.println(loopingCursor);

                    reason = (EditText)layout.findViewById(R.id.reasonEdit);
                    reason.setTypeface(SourceSansProRegular);
                    System.out.println("roziq "+Arrays.deepToString(reasontextcomment));
                    System.out.println("roziq "+finalI);
                    reason.setText(reasontextcomment[finalI],TextView.BufferType.EDITABLE);

                    save = (FrameLayout) layout.findViewById(R.id.save);
                    save.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            imagedialog5.setMessage("data berhasil ditambahkan");
                            imagedialog5.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                            imagedialog5.create().show();

                            loopingcomment++;

                            cursorUpload.moveToPosition(loopingCursor);

                            SQLiteDatabase db = dbRute.getWritableDatabase();
                            ContentValues values = new ContentValues();
                            values.put("comment",reason.getText().toString());
                            //alasan[finalI] = reason.getText().toString();
                            db.insert("UPLOAD", null, values);

                            if (loopingcomment == yangbelum){
                                TV25.setVisibility(View.INVISIBLE);
                                TV26.setVisibility(View.INVISIBLE);
                                upload.setVisibility(View.VISIBLE);
                            }

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

            addressTable.setLayoutParams(namasetlayout);

            checkBox.setTextColor(Color.parseColor("#000000"));
            addressTable.setTextColor(Color.parseColor("#000000"));
            alamat.setTextColor(Color.parseColor("#000000"));
            telepon.setTextColor(Color.parseColor("#000000"));
            status.setTextColor(Color.parseColor("#000000"));

            checkBox.setText(nama[i]);
            status.setTextSize(12);
            addressTable.setText(address[i]);
            addressTable.setTextSize(13);
            telepon.setText(phone[i]);
            telepon.setTextSize(12);

            line = new View(this);
            LinearLayout.LayoutParams lain = (new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 1));
            lain.setMargins(15,28,15,28);
            line.setLayoutParams(lain);
            line.setBackgroundColor(Color.parseColor("#FFDFDFDF"));

            String statusString = doneCustomer[i];
            String sukses = "sukses";

            System.out.println(statusString);
            System.out.println(sukses);

            if (statusString==null){
                doneCustomer[i] = "gagal";
                status.setText(" belum dikirim");
                report.setImageResource(R.drawable.notcompleteimageresult);
            }

            /*if (phone[i]==null){
                    telepon.setText(" -");
                }*/

            else {
                status.setText("sudah dikirim");
                report.setImageResource(R.drawable.completeimageresult);
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
            center3.addView(telepon);

            right.addView(report);
            right.addView(comment);
            center.addView(center1);
            center.addView(center2);
            center.addView(center3);
            left.addView(point);
            row.addView(left);
            row.addView(center);
            row.addView(right);
            rowFix.addView(row);
            rowFix.addView(line);

            tableLayout.addView(rowFix,i);

        }
    }

    public class upload extends AsyncTask<String , String, String> {
        HttpURLConnection startTracking;
        URL url = null;


        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            //uploadProcess = new ProgressDialog(uploadReport.this);
            //uploadProcess.setMessage("Upload data . . . .");
            //uploadProcess.setIndeterminate(true);
            //uploadProcess.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            //uploadProcess.setCancelable(false);
            //uploadProcess.show();

            LayoutInflater layoutInflater3 = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
            View layout3 = layoutInflater3.inflate(R.layout.progressupload, null);

            imageDialog3.setView(layout3);
            imageDialog3.setCancelable(false);
            ad3 = imageDialog3.create();
            ad3.show();





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
                System.out.println("reason = "+alasanUpload);

                StringBuilder sb1 = new StringBuilder();
                for (int i = 0; i < timer.length; i++) {
                    sb1.append(timer[i]);
                    if (i != timer.length - 1) {
                        sb1.append(",");
                    }
                }
                String timerUpload = sb1.toString();
                System.out.println("timer = "+timerUpload);

                StringBuilder sb2 = new StringBuilder();
                for (int i = 0; i < idDB.length; i++) {
                    sb2.append(idDB[i]);
                    if (i != idDB.length - 1) {
                        sb2.append(",");
                    }
                }
                String idDBUpload = sb2.toString();
                System.out.println("ID = "+idDBUpload);

                StringBuilder sb3 = new StringBuilder();
                for (int i = 0; i < doneCustomer.length; i++) {
                    sb3.append(doneCustomer[i]);
                    if (i != doneCustomer.length - 1) {
                        sb3.append(",");
                    }
                }
                String statusIDUpload = sb3.toString();
                System.out.println("status = "+statusIDUpload);


                System.out.println("reason = "+Arrays.deepToString(alasan));
                System.out.println("timer = "+Arrays.deepToString(timer));
                System.out.println("ID = "+Arrays.deepToString(doneCustomer));
                System.out.println("status = "+Arrays.deepToString(statusID));


                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("token", params[0])
                        .appendQueryParameter("report_id",params[1])
                        .appendQueryParameter("cust_id", idDBUpload)
                        .appendQueryParameter("time", timerUpload)
                        .appendQueryParameter("reason", alasanUpload)
                        .appendQueryParameter("status", statusIDUpload)
                        .appendQueryParameter("sign", params[2])
                        .appendQueryParameter("timestamp",params[3]);
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

            System.out.println(result);
            //uploadProcess.dismiss();
            ad3.dismiss();
            //scrollView.setVisibility(View.INVISIBLE);
            //scrollViewframe2.setVisibility(View.VISIBLE);




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
                    TV3.setText("Report");
                    //suksesImage.setVisibility(View.VISIBLE);
                    scrollViewframe.setVisibility(View.INVISIBLE);



                }
            });




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

}
