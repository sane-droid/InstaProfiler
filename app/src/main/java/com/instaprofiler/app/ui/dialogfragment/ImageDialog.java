package com.instaprofiler.app.ui.dialogfragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;

import com.bumptech.glide.Glide;
import com.instaprofiler.app.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageDialog extends DialogFragment {
    String profilePic;
    RelativeLayout relativeLayout;

    public ImageDialog(String profilePic) {
        this.profilePic = profilePic;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.image_dialog_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView imageView = view.findViewById(R.id.imageView2);
        Glide.with(this).load(profilePic).into(imageView);
        Button share = view.findViewById(R.id.share);
        Button download = view.findViewById(R.id.download);
        relativeLayout = view.findViewById(R.id.imageFrame);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shareImg();
            }
        });

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    savelogo("png");
            }
        });
    }

    public void shareImg() {
        Bitmap bitmap;
        bitmap = Bitmap.createBitmap(relativeLayout.getWidth(), relativeLayout.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        relativeLayout.draw(canvas);
        File path = new File(Environment.getExternalStorageDirectory().toString() + "/InstaProfiler/MyPhotos/Shared");
        if (!path.exists()) {
            path.mkdirs();
        }
        File file = new File(path, +System.currentTimeMillis() + ".png");
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            Intent share = new Intent();
            share.setAction(Intent.ACTION_SEND);
            share.setType("image/png");
            share.putExtra(Intent.EXTRA_STREAM, Uri.parse(file.toString()));
            startActivity(Intent.createChooser(share, "Send Profile Pic"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void savelogo(String extension) {
        Bitmap bitmap = Bitmap.createBitmap(relativeLayout.getWidth(), relativeLayout.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas mCanvas = new Canvas(bitmap);
        relativeLayout.draw(mCanvas);
        File path = new File(Environment.getExternalStorageDirectory().toString() + "/LogoMaker/MyLogos/");
        if (!path.exists()) {

            path.mkdirs();
        }
        File mfile = new File(path, +System.currentTimeMillis() + extension);
        try {
            mfile.createNewFile();
            FileOutputStream outputstream = new FileOutputStream(mfile);
            if (extension.equals(new String(".png"))) {

                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputstream);
                Toast.makeText(getContext(), "Successfully Save in " + path.toString(), Toast.LENGTH_SHORT).show();
            } else {

                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputstream);
                Toast.makeText(getContext(), "Successfully Save in " + path.toString(), Toast.LENGTH_SHORT).show();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



//    public  void shareimg(){
//        Bitmap bitmap= null;
//
//        bitmap = Bitmap.createBitmap(relative.getWidth(),relative.getHeight(),Bitmap.Config.ARGB_8888);
//
//
//        Canvas mCanvas=new Canvas(bitmap);
//        relative.draw(mCanvas);
//
//        File path=new File(Environment.getExternalStorageDirectory().toString()+"/LogoMaker/MyLogos/Shared/");
//        if (!path.exists())
//        {
//
//            path.mkdirs();
//        }
//        File mfile=new File(path,+System.currentTimeMillis()+".png");
//        FileOutputStream outputstream= null;
//        try {
//            outputstream = new FileOutputStream(mfile);
//            bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputstream);
//            Intent share=new Intent();
//            share.setAction(Intent.ACTION_SEND);
//            share.setType(getResources().getString(R.string.type_intent_image));
//            share.putExtra(Intent.EXTRA_STREAM,Uri.parse(mfile.toString()));
//            share.putExtra(Intent.EXTRA_TEXT,"Created From LogoMaker");
//            startActivity(Intent.createChooser(share,"Send Logo"));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//
//    }
//
//
//    public void savelogo(String extension){
//        Bitmap bitmap=Bitmap.createBitmap(relative.getWidth(),relative.getHeight(),Bitmap.Config.ARGB_8888);
//
//        Canvas mCanvas=new Canvas(bitmap);
//        relative.draw(mCanvas);
//        File path=new File(Environment.getExternalStorageDirectory().toString()+"/LogoMaker/MyLogos/");
//        if (!path.exists())
//        {
//
//            path.mkdirs();
//        }
//        File mfile=new File(path,+System.currentTimeMillis()+extension);
//        try {
//            mfile.createNewFile();
//            FileOutputStream outputstream=new FileOutputStream(mfile);
//            if(extension.equals(new String(".png"))) {
//
//                bitmap.compress(Bitmap.CompressFormat.PNG,100,outputstream);
//                Toast.makeText(this, "Successfully Save in "+path.toString(), Toast.LENGTH_SHORT).show();
//            }
//            else{
//
//                bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputstream);
//                Toast.makeText(this, "Successfully Save in "+path.toString(), Toast.LENGTH_SHORT).show();
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//    }
