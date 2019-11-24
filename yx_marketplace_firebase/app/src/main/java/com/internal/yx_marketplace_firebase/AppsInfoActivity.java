package com.internal.yx_marketplace_firebase;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AppsInfoActivity extends AppCompatActivity {

    TextView mTitleTv,mcategoryTv,mAgeRatingTv,mCompanyName,mDescriptionTv;
    ImageView mImageTv;
    int [] Images = {R.drawable.download,R.drawable.delete};
    private int current_image;
    private static ImageView buttonimage;
   /* private DatabaseReference mDatabase;
    private String key;
    private String email;
    private Firebase mgetdata;
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps_info);
        //Action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("App Detail");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        mAgeRatingTv = findViewById(R.id.ageRatingText);
        mCompanyName = findViewById(R.id.companyName);
        mDescriptionTv = findViewById(R.id.desc);
        mTitleTv = findViewById(R.id.title);
        //mcategoryTv = findViewById(R.id.post_category);
        mImageTv = findViewById(R.id.image);

        buttonimage = (ImageView) findViewById(R.id.downloadimage);

        byte[] bytes = getIntent().getByteArrayExtra("image");
        String title = getIntent().getStringExtra("title");
        String comepanyname = getIntent().getStringExtra("companyname");
        String agerating = getIntent().getStringExtra("agerating");
       //  email = getIntent().getStringExtra("emial");
        String category = getIntent().getStringExtra("category");
        String description = getIntent().getStringExtra("description");
      //  key = getIntent().getStringExtra("key");
        Bitmap bmp = BitmapFactory.decodeByteArray(bytes,0,bytes.length);


        //mgetdata = new Firebase("https://marketplace-d645d.firebaseio.com/"+category+"/"+key+"/email");
/*
        mgetdata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String Value = dataSnapshot.getValue(String.class);
                    if(Value == "downloaded"){
                        buttonimage.setImageResource(Images[0]);
                    }
                    else if(Value =="deleted"){
                        buttonimage.setImageResource(Images[1]);
                    }
                    else{
                        buttonimage.setImageResource(Images[0]);
                    }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });*/

        //mcategoryTv.setText(category);
        mTitleTv.setText(title);
        mAgeRatingTv.setText("agerating:  "+agerating);
        mCompanyName.setText("company:  "+comepanyname);
        mDescriptionTv.setText(description);
        mImageTv.setImageBitmap(bmp);
        buttonclick();


    }
    public void buttonclick(){
        buttonimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 0 download  1 deleted
                current_image++;
                current_image = current_image%Images.length;
               buttonimage.setImageResource(Images[current_image]);
            }
        });
    }

    //handle onBackPressed(go to previous activity
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
