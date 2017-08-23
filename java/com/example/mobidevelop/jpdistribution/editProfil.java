package com.example.mobidevelop.jpdistribution;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
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

public class editProfil extends AppCompatActivity {

    private Tracker mTracker;
    TextView change, nama, emailaccount, TV9, TV7, TV8;
    FrameLayout save, cancel;
    EditText firstName, lastName, email;
    private static final String HASH_ALGORITHM = "HmacSHA256";
    SimpleDateFormat dateFormat;
    ProgressDialog progressBar;
    LinearLayout topBar;
    DilatingDotsProgressBar mDilatingDotsProgressBar;
    AlertDialog ad3, alertDialog;
    AlertDialog.Builder imageDialog3, imagedialog, donerute;
    String firstnameuser, lastnameuser, emailuser;

    ImageView profileuser;
    ImageButton editprofileuser;

    private static final int SELECT_PICTURE = 100;
    private static int RESULT_LOAD_IMAGE = 1;

    View satu,dua,tiga,empat;
    String uploadprofile;
    Typeface Semiboldtype, Regulartype;
    String uId, uModel, apkversion = "/~9927";

    static int we = 200;
    static int he = 200;

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
        setContentView(R.layout.activity_edit_profil);

        overridePendingTransition(R.anim.editprofileanim,R.anim.stopanim);

        Semiboldtype = Typeface.createFromAsset(getAssets(),  "fonts/SourceSansPro-Semibold.ttf");
        Regulartype = Typeface.createFromAsset(getAssets(),  "fonts/SourceSansPro-Regular.ttf");

        satu = (View)findViewById(R.id.viewsatu);
        dua = (View)findViewById(R.id.viewdua);
        tiga = (View)findViewById(R.id.viewtiga);
        empat = (View)findViewById(R.id.viewempat);
        TV9 = (TextView)findViewById(R.id.textView9);
        TV8 = (TextView)findViewById(R.id.textView8);
        TV7 = (TextView)findViewById(R.id.textView7);
        TV9.setTypeface(Regulartype);
        TV8.setTypeface(Regulartype);
        TV7.setTypeface(Regulartype);

        donerute = new AlertDialog.Builder(this);



        Context context = getApplicationContext();
        appPrefs appPrefs = new appPrefs(context);

        firstnameuser = appPrefs.getFirstname();
        lastnameuser = appPrefs.getLastname();
        emailuser = appPrefs.getEmail();

        editProfil application = editProfil.this;
        mTracker = application.getDefaultTracker();


        Log.i("Edit Password", "Setting screen name: " + firstnameuser+" "+lastnameuser);
        uId = Build.BRAND;
        uModel = Build.MODEL;
        mTracker.setScreenName("MainActivity~"+""+uId+"/"+uModel+"/"+ firstnameuser+"_"+lastnameuser+"_"+apkversion);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

        BitmapFactory.Options opts = new BitmapFactory.Options();


        //Drawable drawable = getResources().getDrawable( R.drawable.editimageprofilebaru );
        Bitmap icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.editimageprofilebaru);
        Bitmap resized1 = Bitmap.createScaledBitmap(icon, 150, 150, true);
        Bitmap conv_bm2 = getRoundedRectBitmap(resized1, 150);
        Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(conv_bm2, 80, 85, true));
        //Bitmap bmp = BitmapFactory.decodeByteArray(icon,0,icon.length);


        String m = appPrefs.getImage();
        byte[] avatar2 = Base64.decode(m, Base64.DEFAULT);
        Bitmap bmp = BitmapFactory.decodeByteArray(avatar2,0,avatar2.length);
        Bitmap resized = Bitmap.createScaledBitmap(bmp, 150, 150, true);
        Bitmap conv_bm = getRoundedRectBitmap(resized, 150);


        Drawable myIcon = getResources().getDrawable( R.drawable.editimage );
        Bitmap bitmap = ((BitmapDrawable) myIcon).getBitmap();
        Bitmap conv_bm1 = getRoundedRectBitmap(bitmap, 50);

        nama = (TextView)findViewById(R.id.name);
        emailaccount = (TextView)findViewById(R.id.textView15);
        profileuser = (ImageView)findViewById(R.id.imageView3);
        editprofileuser = (ImageButton)findViewById(R.id.imageButton3);

        editprofileuser.setImageDrawable(d);

        nama.setTypeface(Semiboldtype);
        emailaccount.setTypeface(Semiboldtype);

        editprofileuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mTracker.send(new HitBuilders.EventBuilder().setCategory("EditProfile~/changeProfileImageButton/~923"+apkversion)
                        .setAction(emailuser)
                        .setLabel(uModel+"/"+uId)
                        .build());

                LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View layout = layoutInflater.inflate(R.layout.popupnotif, null);

                donerute.setView(layout);
                donerute.setCancelable(false);
                alertDialog = donerute.create();
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.show();

                TextView tv28, tv29, TV37;
                TV37 = (TextView)layout.findViewById(R.id.textView36);
                TV37.setText("Attention");
                TV37.setTextSize(18);
                TV37.setTypeface(Regulartype);
                TV37.setTextColor(getResources().getColor(R.color.black));
                final FrameLayout frameLayout = (FrameLayout)layout.findViewById(R.id.frameLayout);
                tv28 = (TextView)layout.findViewById(R.id.textView28);
                tv29 = (TextView)layout.findViewById(R.id.textView29);

                tv28.setTextSize(12);
                tv29.setTextSize(12);

                tv28.setText("Image size max 2MB !");
                tv29.setText("OK");

                tv28.setTextColor(getResources().getColor(R.color.red));
                tv29.setTextColor(getResources().getColor(R.color.black));

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
                                openImageChooser();
                                break;
                        }
                        return true;
                    }
                });


            }
        });

        profileuser.setBackgroundColor(getResources().getColor(R.color.white));
        //profileuser.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        profileuser.setImageBitmap(conv_bm);
        //editprofileuser.setImageBitmap(conv_bm1);


        progressBar = new ProgressDialog(this);
        progressBar.setCancelable(false);
        progressBar.setMessage("Waiting ...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        firstName = (EditText)findViewById(R.id.editText7);
        lastName = (EditText)findViewById(R.id.editText8);
        email = (EditText)findViewById(R.id.email);

        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                mTracker.send(new HitBuilders.EventBuilder().setCategory("EditProfile~/emailColumn/~923"+apkversion)
                        .setAction(emailuser)
                        .setLabel(uModel+"/"+uId)
                        .build());
            }
        });

        firstName.setText(firstnameuser);
        lastName.setText(lastnameuser);
        email.setText(emailuser);

        firstName.setTypeface(Regulartype);
        lastName.setTypeface(Regulartype);
        email.setTypeface(Regulartype);

        firstName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                mTracker.send(new HitBuilders.EventBuilder().setCategory("EditProfile~/firstNameColumn/~923"+apkversion)
                        .setAction(emailuser)
                        .setLabel(uModel+"/"+uId)
                        .build());
                satu.setVisibility(View.VISIBLE);
                dua.setVisibility(View.INVISIBLE);
                tiga.setVisibility(View.INVISIBLE);
                empat.setVisibility(View.VISIBLE);
            }
        });

        lastName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                mTracker.send(new HitBuilders.EventBuilder().setCategory("EditProfile~/lastNameColumn/~923"+apkversion)
                        .setAction(emailuser)
                        .setLabel(uModel+"/"+uId)
                        .build());
                satu.setVisibility(View.INVISIBLE);
                dua.setVisibility(View.VISIBLE);
                tiga.setVisibility(View.VISIBLE);
                empat.setVisibility(View.INVISIBLE);
            }
        });

        imageDialog3 = new AlertDialog.Builder(this);
        imagedialog = new AlertDialog.Builder(this);

        topBar = (LinearLayout) findViewById(R.id.topBar);
        topBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        final String PublicToken = appPrefs.getToken();

        save = (FrameLayout) findViewById(R.id.button2);
        cancel = (FrameLayout) findViewById(R.id.button3);
        save.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch ( event.getAction() ) {
                    case MotionEvent.ACTION_DOWN:
                        save.setBackgroundColor(getResources().getColor(R.color.darkbutton));
                        break;
                    case MotionEvent.ACTION_UP:
                        save.setBackgroundColor(getResources().getColor(R.color.button));
                        mTracker.send(new HitBuilders.EventBuilder().setCategory("EditProfile~/saveButton/~923"+apkversion)
                                .setAction(emailuser)
                                .setLabel(uModel+"/"+uId)
                                .build());
                        LayoutInflater layoutInflater3 = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                        View layout3 = layoutInflater3.inflate(R.layout.downloadprogres, null);
                        mDilatingDotsProgressBar = (DilatingDotsProgressBar) layout3.findViewById(R.id.progress);
                        mDilatingDotsProgressBar.show();

                        imageDialog3.setView(layout3);
                        imageDialog3.setCancelable(false);
                        ad3 = imageDialog3.create();
                        ad3.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        ad3.show();

                        String firstN = String.valueOf(firstName.getText());
                        String lastN = String.valueOf(lastName.getText());
                        String mail = String.valueOf(email.getText());
                        String address = "sby";
                        String avatar = null;
                        dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                        String timestamp = dateFormat.format(new Date());
                        try {
                            new editProfile().execute(firstN,lastN,address,avatar,mail,hashMac(timestamp,"0fab227b319afe10a0566183e5c7317dd23127b3f79a964481c0e08640f21acc"),timestamp, PublicToken);
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
                        mTracker.send(new HitBuilders.EventBuilder().setCategory("EditProfile~/cancelButton/~923"+apkversion)
                                .setAction(emailuser)
                                .setLabel(uModel+"/"+uId)
                                .build());
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

    public class editProfile extends AsyncTask<String , String, String> {
        HttpURLConnection getRoute;
        URL url = null;


        @Override
        protected void onPreExecute(){
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params){

            try{
                url = new URL("http://lab.hellomorra.com/jawapos/api/post-edit-profile");
            }
            catch (MalformedURLException e){
                e.printStackTrace();
                return "exception";
            }
            try {
                getRoute = (HttpURLConnection)url.openConnection();
                getRoute.setReadTimeout(10000);
                getRoute.setConnectTimeout(15000);
                getRoute.setRequestMethod("POST");
                getRoute.setDoInput(true);
                getRoute.setDoOutput(true);



                String a = "";
                if (uploadprofile!=null){
                    a=uploadprofile;
                }


                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("first_name", params[0])
                        .appendQueryParameter("last_name", params[1])
                        .appendQueryParameter("address",params[2])
                        .appendQueryParameter("avatar", a)
                        .appendQueryParameter("email",params[4])
                        .appendQueryParameter("sign",params[5])
                        .appendQueryParameter("timestamp",params[6])
                        .appendQueryParameter("token",params[7]);

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

                JSONObject data = jobj.getJSONObject("data");
                JSONObject data2 = data.getJSONObject("data");


                String firstnamestring = (String) data2.get("first_name");
                String lastnamestring = (String) data2.get("last_name");
                String emailstring = (String) data2.get("email");
                String avatarstring = (String) data2.get("avatar");


                String avatarstring2 = "http://lab.hellomorra.com/jawapos/public/"+avatarstring;


                Context context = getApplicationContext();
                appPrefs appPrefs = new appPrefs(context);

                appPrefs.setEmail(emailstring);
                appPrefs.setFirstname(firstnamestring);
                appPrefs.setLastname(lastnamestring);

                new DownloadImageTask().execute(avatarstring);





            } catch (JSONException e) {
                e.printStackTrace();
            }





        } // protected void onPostExecute(Void v)
    }

    void openImageChooser() {
        /*Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);*/
        Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            // Get the url from data
            Uri selectedImageUri = data.getData();
            if (null != selectedImageUri) {
                // Get the path from the Uri
                String path = getPathFromURI(selectedImageUri);

                final InputStream imageStream;
                try {
                    imageStream = getContentResolver().openInputStream(selectedImageUri);
                    Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

                    float factorH = he / (float)selectedImage.getHeight();
                    float factorW = we / (float)selectedImage.getWidth();
                    float factorToUse = (factorH > factorW) ? factorW : factorH;

                    Bitmap resized = Bitmap.createScaledBitmap(selectedImage,
                            (int) (selectedImage.getWidth() * factorToUse),
                            (int) (selectedImage.getHeight() * factorToUse),
                            true);

                    Bitmap conv_bm = getRounded(resized, 1000);


                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    conv_bm.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                    byte[] img = bos.toByteArray();
                    uploadprofile = Base64.encodeToString(img, Base64.DEFAULT);

                    //profileuser.setBackgroundColor(getResources().getColor(R.color.black));
                    profileuser.setImageBitmap(conv_bm);


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }


                //Log.i(TAG, "Image Path : " + path);
                // Set the image in ImageView

            }
            }

    }

    public static Bitmap getRounded(Bitmap bitmap, int pixels) {
        Bitmap result = null;
        try {
            result = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(result);



            int color = 0xff000000;
            Paint paint = new Paint();
            Rect rect = new Rect(0, 0, 200, 200);


            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(color);
            canvas.drawCircle(100, 100, 100, paint);

            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmap, (canvas.getWidth()-bitmap.getWidth())/2, (canvas.getHeight()-bitmap.getHeight())/2, paint);

        } catch (NullPointerException e) {
        } catch (OutOfMemoryError o) {
        }
        return result;
    }

    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Bitmap doInBackground(String... URL) {

            String imageURL = URL[0];

            Bitmap bitmap = null;
            try {

                byte[] img=null;
                if (URL[0]!=null){
                    InputStream input = new java.net.URL(URL[0]).openStream();
                    // Decode Bitmap
                    bitmap = BitmapFactory.decodeStream(input);
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                    img = bos.toByteArray();
                    Bitmap bmp1 = BitmapFactory.decodeByteArray(img,0,img.length);

                    Context context = getApplicationContext();
                    appPrefs appPrefs = new appPrefs(context);

                    String s = Base64.encodeToString(img, Base64.DEFAULT);
                    appPrefs.setImage(s);


                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {

            LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
            View layout = layoutInflater.inflate(R.layout.popupnotif, null);

            final AlertDialog alertDialog;
            imagedialog.setView(layout);
            imagedialog.setCancelable(false);
            alertDialog = imagedialog.create();
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            alertDialog.show();

            TextView tv28, tv29, TV37;
            final FrameLayout frameLayout = (FrameLayout)layout.findViewById(R.id.frameLayout);
            tv28 = (TextView)layout.findViewById(R.id.textView28);
            tv29 = (TextView)layout.findViewById(R.id.textView29);

            TV37 = (TextView)layout.findViewById(R.id.textView36);
            TV37.setText("Success!!!");
            TV37.setTextSize(18);
            TV37.setTypeface(Regulartype);
            TV37.setTextColor(getResources().getColor(R.color.basic));
            tv28.setTextSize(12);
            tv29.setTextSize(12);


            tv28.setText("Your profile has succesfully changed");
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
    }
}
