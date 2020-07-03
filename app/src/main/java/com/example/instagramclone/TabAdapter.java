package com.example.instagramclone;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

// Its an abstract class
public class TabAdapter extends FragmentStatePagerAdapter {
    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
        switch(position)
        {
            case 0:
                ProfileTab profileTab=new ProfileTab();
                return  profileTab;
            case 1:
                UsersTab usersTab=new UsersTab();
                return usersTab;
            case 2:
                //alternative direct way
                return new SharePictureTab();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 3;

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position)
    {
        switch (position)
        {
            case 0:
                return "Profile";
            case 1:
                return "Users";
            case 2:
                return "Share Pictures";
            default:
                return null;
        }

    }
}
