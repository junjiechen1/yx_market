package com.internal.yx_marketplace_firebase;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ImageView gameImage = (ImageView) findViewById(R.id.gameimage);
        ImageView socialImage = (ImageView)findViewById(R.id.SocialImage);
        ImageView fitenssImage = (ImageView)findViewById(R.id.FitnessImage);
        ImageView educationImage = (ImageView)findViewById(R.id.EducationImage);
        ImageView kidsImage = (ImageView)findViewById(R.id.KidImage);
        ImageView financeImage =(ImageView)findViewById(R.id.FinanceImage);
        ActionBar actionBar = getSupportActionBar();
        //set title
        actionBar.setTitle("Category");
        gameImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, GameListActivity.class));
            }
        });
        socialImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, SocialListActivity.class));
            }
        });
        educationImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,EducationListAcitivity.class));
            }
        });
        fitenssImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,FitnessListActivity.class));
            }
        });

        kidsImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,KidsListActivity.class ));
            }
        });
        financeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,FinanceListActivity.class));
            }
        });
    }
}
