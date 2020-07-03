package com.example.instagramclone;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class ProfileTab extends Fragment
{
    private EditText edtProfileName,edtProfileBio,edtProfileProfession,edtprofileHobbies,edtprofilefavSports;
    private Button btnUpdateInfo;


    public ProfileTab() {
        }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //si,ilar to setcontent view
        View view= inflater.inflate(R.layout.fragment_profile_tab, container, false);
        edtProfileName=view.findViewById(R.id.edtProfileNmae);//view contains our iu componenets
        edtProfileBio=view.findViewById(R.id.edtProfileBio);
        edtProfileProfession=view.findViewById(R.id.edtProfileProfession);
        edtprofileHobbies=view.findViewById(R.id.edtProfilehobbies);
        edtprofilefavSports=view.findViewById(R.id.edtprofilefavouriteSport);

        btnUpdateInfo=view.findViewById(R.id.btnupdateProfileInfo);

        //To save data to the server

        final ParseUser parseUser=ParseUser.getCurrentUser();

        if(parseUser.get("profileName")==null)
            edtProfileName.setText("");
        else
            edtProfileName.setText(parseUser.get("profileName")+"");

        if(parseUser.get("profileBio")==null)
            edtProfileBio.setText("");
        else
        edtProfileBio.setText(parseUser.get("profileBio")+"");

        if(parseUser.get("profileProfession")==null)
            edtProfileProfession.setText("");
        else
            edtProfileProfession.setText(parseUser.get("profileProfession")+"");

        if(parseUser.get("profileHobbies")==null)
            edtprofileHobbies.setText("");
        else
            edtprofileHobbies.setText(parseUser.get("profileHobbies")+"");

        if(parseUser.get("profileFavSports")==null)
            edtprofilefavSports.setText("");
        else
            edtprofilefavSports.setText(parseUser.get("profileFavSports")+"");



        btnUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                parseUser.put("profileName",edtProfileName.getText().toString());
                parseUser.put("profileBio",edtProfileBio.getText().toString());
                parseUser.put("profileProfession",edtProfileProfession.getText().toString());
                parseUser.put("profileHobbies",edtprofileHobbies.getText().toString());
                parseUser.put("profileFavSports",edtprofilefavSports.getText().toString());

                parseUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e)
                    {
                        if(e==null)
                        {
                            FancyToast.makeText(getContext(), "Info Updated", Toast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();
                        }
                        else
                            {
                                FancyToast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT, FancyToast.ERROR, false).show();
                        }


                    }
                });

            }
        });

        return view;
    }
}