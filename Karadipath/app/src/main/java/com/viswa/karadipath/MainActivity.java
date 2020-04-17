package com.viswa.karadipath;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.viswa.karadipath.Common.Constants;
import com.viswa.karadipath.Common.GlobalCache;
import com.viswa.karadipath.Fragments.ProductFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, FragmentManager.OnBackStackChangedListener {//, View.OnClickListener

    Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    GlobalCache gCache;

    private NotificationManager notificationManager;
    private NotificationCompat.Builder notificationBuilder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            //getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_white);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            gCache = GlobalCache.getInstance();
        notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

            toggle = new ActionBarDrawerToggle(
                    this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
                @Override
                public void onDrawerClosed(View drawerView) {
                    super.onDrawerClosed(drawerView);
                    supportInvalidateOptionsMenu(); // calls onPrepareOptionsMenu()
                }

                @Override
                public void onDrawerOpened(View drawerView) {
                    super.onDrawerOpened(drawerView);
                    supportInvalidateOptionsMenu(); // calls onPrepareOptionsMenu()
                }
            };
            toggle.syncState();
            drawer.setDrawerListener(toggle);


            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
            navigationView.setCheckedItem(R.id.nav_product);


            View navHeader = navigationView.getHeaderView(0);


            GlobalCache.getInstance().setDrawerLayout(drawer);
            GlobalCache.getInstance().setDrawerToggle(toggle);

            try {
                toolbar.setOnTouchListener(new View.OnTouchListener() {
                    public boolean onTouch(View v, MotionEvent event) {
                        return false;
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }


        ProductFragment productFragment = new ProductFragment();
        showFragmentWithoutStack(productFragment, Constants.PRODUCT_FRAGMENT_TITLE);



    }





    @Override
    public void onBackPressed() {
        try {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);

            } else {

                if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                    finish();
                } else {
                    getSupportFragmentManager().popBackStackImmediate();
                }
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            if (toggle.onOptionsItemSelected(item)) {
                return true;
            } else {
                onBackPressed();
            }
        }
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        try {
            if (id == R.id.nav_notification) {
                buildLocalNotification();
            } else if (id == R.id.nav_product) {
                ProductFragment productFragment = new ProductFragment();
                showFragmentWithoutStack(productFragment, Constants.PRODUCT_FRAGMENT_TITLE);
            }

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private void buildLocalNotification() {

        String id = "id_product";
        // The user-visible name of the channel.
        CharSequence name = "Product";
        // The user-visible description of the channel.
        String description = "Notifications regarding our products";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {


            int importance = NotificationManager.IMPORTANCE_MAX;
            NotificationChannel mChannel = new NotificationChannel(id, name, importance);
            // Configure the notification channel.
            mChannel.setDescription(description);
            mChannel.enableLights(true);
            // Sets the notification light color for notifications posted to this
            // channel, if the device supports this feature.
            mChannel.setLightColor(Color.RED);
            notificationManager.createNotificationChannel(mChannel);
        }

        Log.i("Test ==>", "notify");
        Bitmap icon = BitmapFactory.decodeResource(this.getResources(),
                R.mipmap.ic_launcher);

        notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(icon)
                .setChannelId(id)
                .setPriority(Notification.PRIORITY_MAX)
                .setContentTitle(Constants.notification_title)
                .setContentText(Constants.notification_content);
        sendNotification();
    }

    private void sendNotification() {
        Log.i("Test ==>", "send");
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        notificationBuilder.setContentIntent(contentIntent);
        Notification notification = notificationBuilder.build();

        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.defaults |= Notification.DEFAULT_SOUND;
        notificationManager.notify(1, notification);
    }


    @Override
    public void onBackStackChanged() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container);
        if (fragment != null) {

        }
    }


    public void showFragmentWithoutStack(Fragment fragment, String back_stack_name) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.container, fragment, back_stack_name);
        transaction.commit();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
