package com.internal.yx_marketplace_firebase;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;

public class FitnessListActivity extends AppCompatActivity {
    private RecyclerView mBlogList;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness_list);
        //actionbar
        ActionBar actionBar = getSupportActionBar();
        //set title
        actionBar.setTitle("fitness List");
        mDatabase = FirebaseDatabase.getInstance().getReference().child("fitness");
        mDatabase.keepSynced(true);

        mBlogList = (RecyclerView)findViewById(R.id.recycleView);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));



    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Model, GameListActivity.ModelViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Model, GameListActivity.ModelViewHolder>
                (Model.class,R.layout.row, GameListActivity.ModelViewHolder.class,mDatabase) {
            @Override
            protected void populateViewHolder(GameListActivity.ModelViewHolder modelViewHolder, Model model, int i) {
                //  modelViewHolder.setCategory(model.getCategory());
                modelViewHolder.setCategory(model.getCategory());
                modelViewHolder.setappName(model.getAppName());
                modelViewHolder.setImage(getApplicationContext(),model.getImage());
                modelViewHolder.setCompanyName(model.getCompanyName());
                modelViewHolder.setAgerating(model.getAgerating());
                modelViewHolder.setEmail(model.getEmail());
                modelViewHolder.setDescription(model.getDescription());
            }

            @Override
            public GameListActivity.ModelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                GameListActivity.ModelViewHolder viewHolder = super.onCreateViewHolder(parent,viewType);
                viewHolder.setOnClickListener(new GameListActivity.ModelViewHolder.ClickListener() {
                    @Override
                    public void OnItemClick(View view, int position) {
                        TextView mTitleTv = view.findViewById(R.id.post_title);
                        TextView mAgeRatingTv = view.findViewById(R.id.post_agerating);
                        TextView mdescriptionTv = view.findViewById(R.id.post_descrption);
                        TextView memailtv = view.findViewById(R.id.post_email);
                        TextView mCompanyNametv = view.findViewById(R.id.post_companyname);
                        TextView mcategoryTv = view.findViewById(R.id.post_category);
                        ImageView mImageTv = view.findViewById(R.id.post_image);
                        //get data from views
                        String mTitle = mTitleTv.getText().toString();
                        String mAgeRating =mAgeRatingTv.getText().toString();
                        String memail = memailtv.getText().toString();
                        String mcompanyname =mCompanyNametv.getText().toString();
                        String mdescriptiont = mdescriptionTv.getText().toString();
                        String mcategoryc = mcategoryTv.getText().toString();
                        Drawable mDrawable = mImageTv.getDrawable();
                        Bitmap mBitmap=((BitmapDrawable)mDrawable).getBitmap();
                        //pass this data to new activity
                        Intent intent = new Intent(view.getContext(),AppsInfoActivity.class);
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        mBitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
                        byte[] bytes = stream.toByteArray();

                        intent.putExtra("image",bytes);
                        intent.putExtra("agerating",mAgeRating);
                        intent.putExtra("emial",memail);
                        intent.putExtra("companyname",mcompanyname);
                        intent.putExtra("description",mdescriptiont);
                        intent.putExtra("title",mTitle);
                        intent.putExtra("category",mcategoryc);

                        intent.putExtra("key",mDatabase.push().getKey());
                        startActivity(intent);

                    }

                    @Override
                    public void OnItemlongClick(View view, int position) {


                    }

                });
                return viewHolder;
            }
        };
        mBlogList.setAdapter(firebaseRecyclerAdapter);
    }

    public static class ModelViewHolder extends  RecyclerView.ViewHolder{
        View mView;
        TextView post_title;
        TextView post_desc;
        TextView post_agerating;
        TextView post_companyname;
        TextView post_description;
        ImageView post_Image;
        TextView post_email;
        public  ModelViewHolder(View itemView){
            super(itemView);
            mView = itemView;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickListener.OnItemClick(v,getAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mClickListener.OnItemlongClick(v,getAdapterPosition());
                    return false;
                }
            });
            post_desc = (TextView)mView.findViewById(R.id.post_category);
            post_title = (TextView)mView.findViewById(R.id.post_title);
            post_Image = (ImageView)mView.findViewById(R.id.post_image);
            post_agerating = (TextView)mView.findViewById(R.id.post_agerating);
            post_companyname = (TextView)mView.findViewById(R.id.post_companyname);
            post_description = (TextView)mView.findViewById(R.id.post_descrption);
            post_email = (TextView)mView.findViewById(R.id.post_email);
        }
        public void setCompanyName(String companyName){
            post_companyname.setText(companyName);
        }
        public void  setAgerating(String agerating){
            post_agerating.setText(agerating);
        }
        public void setEmail(String email){
            post_email.setText(email);
        }
        public  void setCategory(String category){

            post_desc.setText(category);
        }
        public void setDescription(String description){
            post_description.setText(description);
        }

        public void setappName(String appName){
            post_title.setText(appName);
        }

        public  void  setImage (Context ctx , String image){

            Picasso.with(ctx).load(image).into(post_Image);
        }
        private  GameListActivity.ModelViewHolder.ClickListener mClickListener;

        public interface ClickListener{
            void OnItemClick(View view,int position);
            void OnItemlongClick(View view ,int position);

        }
        public void setOnClickListener(GameListActivity.ModelViewHolder.ClickListener clickListener){
            mClickListener = clickListener;
        }

    }
}
