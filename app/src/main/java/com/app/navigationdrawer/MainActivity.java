package com.app.navigationdrawer;

import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.balysv.materialmenu.MaterialMenuView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static String[] titles = null;
    private static int[] icons;
    public static View menuBarIconLayout;
    private MaterialMenuView material_menu_button;
    public static TextView textTopTitle;
    private DrawerLayout drawerLayout;
    private RecyclerView recyclerView;
    private NavigationView navigationView;
    private NavigationDrawerAdapter navAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initMenuIcon();
        setupNav_View_Adapter();
    }
    private void initMenuIcon(){
        menuBarIconLayout = findViewById(R.id.menuBarIconLayout);
        material_menu_button = (MaterialMenuView) findViewById(R.id.material_menu_button);
        menuBarIconLayout.setOnClickListener(onClickListener);
        material_menu_button.setOnClickListener(onClickListener);
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if(drawerLayout.isDrawerOpen(GravityCompat.START)){
                    material_menu_button.animateIconState(MaterialMenuDrawable.IconState.BURGER);
                    material_menu_button.animate();
                    drawerLayout.closeDrawers();
                }else {
                    material_menu_button.animateIconState(MaterialMenuDrawable.IconState.X);
                    material_menu_button.animate();
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        });
    }
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.material_menu_button:
                    if(drawerLayout.isDrawerOpen(GravityCompat.START)){
                        material_menu_button.animateIconState(MaterialMenuDrawable.IconState.BURGER);
                        material_menu_button.animate();
                        drawerLayout.closeDrawers();
                    }else {
                        material_menu_button.animateIconState(MaterialMenuDrawable.IconState.X);
                        material_menu_button.animate();
                        drawerLayout.openDrawer(GravityCompat.START);
                    }
            }
        }
    };
    private void setupNav_View_Adapter(){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                item.setChecked(true);
                drawerLayout.closeDrawers();
                return false;
            }
        });
        navAdapter = new NavigationDrawerAdapter(getApplicationContext(), getData());
        navAdapter.setOnTapListener(new OnTapListener() {
            @Override
            public void OnTapView(final int position) {
                drawerLayout.closeDrawers();
                NavigationDrawerAdapter.selected_item = position;
                navAdapter.notifyDataSetChanged();
                Handler handler = new Handler();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
//                        displayView(position);
                    }
                };

                handler.postDelayed(runnable, 500); // here 500 is the delay
            }
        });
        recyclerView.setAdapter(navAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    public void initView(){
        recyclerView = (RecyclerView) findViewById(R.id.drawer_slidermenu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView= (NavigationView) findViewById(R.id.navigation_view);
        textTopTitle = (TextView) findViewById(R.id.textTitle);
        titles = getResources().getStringArray(R.array.nav_drawer_labels);
        icons = new int[]{
                R.drawable.home,
                R.drawable.home,
                R.drawable.manage,
                R.drawable.settings,
                R.drawable.settings,
                R.drawable.settings,
                R.drawable.settings
        };
    }

    public static List<NavDrawerItem> getData() {
        List<NavDrawerItem> data = new ArrayList<>();
        // preparing navigation drawer items

        NavDrawerItem navItem = new NavDrawerItem();
        navItem.setTitle(titles[0]);
        navItem.setIcon(icons[0]);
        data.add(navItem);

        navItem = new NavDrawerItem();
        navItem.setTitle(titles[1]);
        navItem.setIcon(icons[1]);
        data.add(navItem);

        navItem = new NavDrawerItem();
        navItem.setTitle(titles[2]);
        navItem.setIcon(icons[2]);
        data.add(navItem);

        navItem = new NavDrawerItem();
        navItem.setTitle(titles[3]);
        navItem.setIcon(icons[3]);
        data.add(navItem);

        navItem = new NavDrawerItem();
        navItem.setTitle(titles[4]);
        navItem.setIcon(icons[4]);
        data.add(navItem);

        navItem = new NavDrawerItem();
        navItem.setTitle(titles[5]);
        navItem.setIcon(icons[5]);
        data.add(navItem);

        navItem = new NavDrawerItem();
        navItem.setTitle(titles[6]);
        navItem.setIcon(icons[6]);
        data.add(navItem);

        return data;
    }
}
