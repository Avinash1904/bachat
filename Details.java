package com.example.avinash.bachat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Details extends AppCompatActivity {
    private int i=0;
    FirebaseDatabase database;
    File localFile = null;
    ListView list;
    ImageView imageView;
    CustomListAdapter adapte;
    private StorageReference riversRef,storageRef;
    ArrayList<String> imageKey=new ArrayList<>();
    String[] itemname ={
            "Safari",
            "Apple"

    };
    ArrayList<String> Userlist;

    Bitmap imgid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        String month=intent.getStringExtra("month");
        setTitle(month);
        setContentView(R.layout.activity_details);
        database = FirebaseDatabase.getInstance();

        firebaseImage();

        //CustomListAdapter adapter=new CustomListAdapter(this, itemname, imgid);
        list=(ListView)findViewById(R.id.list);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem= itemname[+position];
                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void onAdd(View view){

        Intent intent=new Intent(this,Add_Data.class);
        startActivity(intent);
    }

    public void firebaseImage(){
       // Userlist = new ArrayList<String>();

        imageView= (ImageView) findViewById(R.id.img);

        ///////////////////to get all images store the url in the database and then

                        ///String url=dataSnapshot.child("url").value();
        ////               now in reversref=FirebaseStorage.getInstance().getReferenceFromUrl(url);

        ////////////////here

        DatabaseReference reference=database.getReference("URL");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                String url = (String) dataSnapshot.child("url1").getValue();





                /////ArrayList implementation
                Map<String, String> map = new HashMap<>();
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    String key=dsp.getKey().toString();
                    imageKey.add(key);

                    riversRef = FirebaseStorage.getInstance().getReferenceFromUrl(dsp.getValue().toString());


                    try {
                        localFile = File.createTempFile("images", "jpg");
                        Log.e("try", "yo");


                        riversRef.getFile(localFile)
                                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                        // Successfully downloaded data to local file
                                        Log.e("success", "yo");
                                        // ...
                                        imgid = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                        //imageView.setImageBitmap(imgid);
                                        CustomListAdapter adapter = new CustomListAdapter(Details.this, imageKey, imgid);
                                        list.setAdapter(adapter);


                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                // Handle failed download
                                // ...
                                Log.e("error", "download fail");
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    //map.put(String.valueOf(dsp.getKey()), String.valueOf(dsp.getValue()));

                    // Userlist.add(String.valueOf(dsp.getValue()));
                }
               /* for (Map.Entry m : map.entrySet()) {
                    Log.e("map url" + m.getKey().toString(), m.getValue().toString());
                 String key=m.getKey().toString();
                    imageKey.add(key);

                /*Iterator itr=Userlist.iterator();
                while(itr.hasNext()){
                    Log.e("url:",itr.next().toString());
                }

                riversRef = FirebaseStorage.getInstance().getReferenceFromUrl(m.getValue().toString());


                try {
                    localFile = File.createTempFile("images", "jpg");
                    Log.e("try", "yo");


                    riversRef.getFile(localFile)
                            .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                    // Successfully downloaded data to local file
                                    Log.e("success", "yo");
                                    // ...
                                    imgid = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                    //imageView.setImageBitmap(imgid);
                                    CustomListAdapter adapter = new CustomListAdapter(Details.this, imageKey, imgid);
                                    list.setAdapter(adapter);


                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle failed download
                            // ...
                            Log.e("error", "download fail");
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }*/
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        //////////////end



    }


}
