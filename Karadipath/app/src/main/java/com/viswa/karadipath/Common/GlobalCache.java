package com.viswa.karadipath.Common;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;


public class GlobalCache {


   private static GlobalCache instance = null;
    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawerLayout;

       public static GlobalCache getInstance() {
        if (instance == null) {
            instance = new GlobalCache();
        }
        return instance;
    }



    public void setDrawerToggle(ActionBarDrawerToggle drawerToggle) {
        this.drawerToggle = drawerToggle;
    }

    public void setDrawerLayout(DrawerLayout drawerLayout) {
        this.drawerLayout = drawerLayout;
    }


}

