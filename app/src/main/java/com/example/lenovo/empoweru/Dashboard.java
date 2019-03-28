package com.example.lenovo.empoweru;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import technolifestyle.com.imageslider.FlipperLayout;
import technolifestyle.com.imageslider.FlipperView;

public class Dashboard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ImageButton profilel,imgbtngr,imgbtngrl;
    TextView usernamet,levelt,textView,textView2;String name,level;
    private LinearLayout empoweruweb;FrameLayout flgr,flgrl;
    AlertDialog.Builder builder1,builder2;
    FlipperLayout flipperLayout;
    private TextView tvgr,tvgrl;
    private ImageView profilet;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
      //  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //        WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_dashboard);

        flipperLayout = (FlipperLayout) findViewById(R.id.vvpflipper_layout);
        builder1 = new AlertDialog.Builder(this);
        builder1.setCancelable(false);
        builder2 = new AlertDialog.Builder(this);
        builder2.setCancelable(false);
        NavigationView navigationView2 = (NavigationView) findViewById(R.id.nav_view);

        View header=navigationView2.getHeaderView(0);
        /*View        view=navigationView.inflateHeaderView(R.layout.nav_header_main);*/
        textView=(TextView)header.findViewById(R.id.textView);
        textView2=(TextView)header.findViewById(R.id.textView2);

        setLayout();
        flgr=(FrameLayout)findViewById(R.id.flgr);
        tvgr=(TextView)findViewById(R.id.attagr);
        imgbtngr=(ImageButton)findViewById(R.id.grievance);
        tvgr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this,HMGrievance.class));
            }
        });
        imgbtngr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this,HMGrievance.class));
            }
        });
        flgr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this,HMGrievance.class));
            }
        });
        flgrl=(FrameLayout)findViewById(R.id.fmgrl);
        tvgrl=(TextView)findViewById(R.id.attaleave);
        imgbtngrl=(ImageButton)findViewById(R.id.leave);
        tvgrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this,HMleave.class));
            }
        });
        imgbtngrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this,HMleave.class));
            }
        });
        flgrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this,HMleave.class));
            }
        });
        empoweruweb = (LinearLayout) findViewById(R.id.empoweruweb);
        empoweruweb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://www.empoweru.in/"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        usernamet=(TextView) findViewById(R.id.username);
        levelt=(TextView)findViewById(R.id.level);

        SharedPreferences prefs = getSharedPreferences("userInfo", MODE_PRIVATE);
        // String restoredText = prefs.getString("text", null);
        //   if (restoredText != null) {
        name = prefs.getString("name", "No name defined");//"No name defined" is the default value.
        level = prefs.getString("level", ""); //0 is the default value.
        //  }
        textView.setText(name);
        textView2.setText(level);
        usernamet.setText(name);
        levelt.setText(level);
        profilet=header.findViewById(R.id.imageViewp);
        profilet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this,Profiledetails.class));
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

       //setSupportActionBar(toolbar);
        Intent intent = getIntent();


        String id = intent.getStringExtra("userid");
        String paswrd = intent.getStringExtra("passwrd");
        profilel= (ImageButton)findViewById(R.id.att);
        profilel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this,Tabbedteacher.class));
            }
        });



   /*     FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
       /* profilel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this,Profiledetails.class);
                startActivity(intent);
                // startActivity(new Intent(getApplicationContext(),Profiledetails.class));
            }
        });
*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    private void setLayout() {
        String url[] = new String[]{
                "http://blog.eap.ucop.edu/wp-content/uploads/2016/01/Julie-Huang-27.jpg",
                "https://i.pinimg.com/originals/d3/84/d1/d384d1c565dc6b501a61286bf0879481.jpg",
                "https://i.pinimg.com/originals/18/40/72/184072abb72399c23ab635faaa0a94ad.jpg"
        };
        Resources res = getResources();
        Drawable drawable1 = res.getDrawable(R.drawable.hell);
        // int drwint=Integer.parseInt(drawable1.toString());
        Drawable drawable2 = res.getDrawable(R.drawable.met);
        //  int drwint2=Integer.parseInt(drawable2.toString());
        Drawable drawable3 = res.getDrawable(R.drawable.moto);
        //   int drwint3=Integer.parseInt(drawable3.toString());
        int drw[]={R.drawable.hell,R.drawable.met,R.drawable.moto};
        for (int i = 0; i < 3; i++) {
            FlipperView view = new FlipperView(getBaseContext());
            view.setImageDrawable(drw[i])
                    .setDescription("");
            flipperLayout.addFlipperView(view);
            view.setOnFlipperClickListener(new FlipperView.OnFlipperClickListener() {
                @Override
                public void onFlipperClick(FlipperView flipperView) {
                    //Toast.makeText(MainActivity.this
                         //   , "Here " + (flipperLayout.getCurrentPagePosition() + 1)
                         //   , Toast.LENGTH_SHORT).show();
                }
            });
        }
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
        getMenuInflater().inflate(R.menu.dashboard, menu);
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
    private void deleteAppData() {
        try {
            // clearing app data
            String packageName = getApplicationContext().getPackageName();
            Runtime runtime = Runtime.getRuntime();
            runtime.exec("pm clear "+packageName);

        } catch (Exception e) {
            e.printStackTrace();
        } }

    public void deleteDatabase(Context context) {
        try {
            String destPath = getFilesDir().getPath();
            destPath = destPath.substring(0, destPath.lastIndexOf("/")) + "/databases";
            File dir = context.getDatabasePath(destPath);
            deleteDir(dir);
        } catch (Exception e) {}
    }

    public void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) {}
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if(dir!= null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            startActivity(new Intent(this,Profiledetails.class));
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            startActivity(new Intent(this,Tabbedteacher.class));
        } else if (id == R.id.nav_slideshow) {
           // deleteAppData();
            Boolean isconnected = ConnectivityReceiver.isConnected();
            if(isconnected) {
                builder1.setTitle("Warning!");
                builder1.setMessage("You will lose your previous activities data. Do you still want to proceed?");

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // startActivity(new Intent(getContext(),BlockTabbedteacher.class));
                                dialog.cancel();

                                deleteCache(getApplicationContext());
                                deleteDatabase(getApplicationContext());
                                Splash_Screen.savePreferences("userlogin","6");
                                deleteCache(getApplicationContext());
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                File sharedPreferenceFile = new File("/data/data/"+ getPackageName()+ "/shared_prefs/");
                                File[] listFiles = sharedPreferenceFile.listFiles();
                                for (File file : listFiles) {
                                    file.delete();
                                }
                                startActivity(intent);
                            }

                        });

                builder1.setNegativeButton(
                        "Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
            else{
                builder2.setTitle("No Internet Connection");
            //    builder1.setMessage("");

                builder2.setPositiveButton(
                        "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // startActivity(new Intent(getContext(),BlockTabbedteacher.class));
                                dialog.cancel();


                            }

                        });
                AlertDialog alert12 = builder2.create();
                alert12.show();

            }
      //  AlertDialog alert11 = builder1.create();
       // alert11.show();

         //   getApplicationContext().getSharedPreferences("user_info", 0).edit().clear().commit();
           // getApplicationContext().getSharedPreferences("userlogin", 0).edit().clear().commit();
           // SharedPreferences settings = getSharedPreferences("userlogin", Context.MODE_PRIVATE);
            //settings.edit().clear().commit();

           /* Intent newIntent = new Intent(this,Splash_Screen.class);
            newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(newIntent);*/
          /*  Intent i = getBaseContext().getPackageManager()
                    .getLaunchIntentForPackage( getBaseContext().getPackageName() );
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);*/

         /*  Intent mStartActivity = new Intent(this, LoginActivity.class);
            int mPendingIntentId = 123456;

            PendingIntent mPendingIntent = PendingIntent.getActivity(this, mPendingIntentId, mStartActivity,
                    PendingIntent.FLAG_CANCEL_CURRENT);
            AlarmManager mgr = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 10, mPendingIntent);
            System.exit(0);*/
          //  deleteAppData();
          /*  if (Build.VERSION_CODES.KITKAT <= Build.VERSION.SDK_INT) {
                ((ActivityManager)this.getSystemService(ACTIVITY_SERVICE))
                        .clearApplicationUserData();
            }*/
           // startActivity(new Intent(this,LoginActivity.class));
          /*  Intent intent = new Intent(getApplicationContext(), Splash_Screen.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (Build.VERSION_CODES.KITKAT <= Build.VERSION.SDK_INT) {
                ((ActivityManager)this.getSystemService(ACTIVITY_SERVICE))
                        .clearApplicationUserData();
            }
            getApplicationContext().startActivity(intent);*/
        } else if (id == R.id.nav_manage) {
            startActivity(new Intent(this,Helpline.class));
        } else if (id == R.id.nav_share) {
           // startActivity(new Intent(this,MainActivity.class));
        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
