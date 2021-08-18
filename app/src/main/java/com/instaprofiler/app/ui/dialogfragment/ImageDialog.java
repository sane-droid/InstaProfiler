package com.instaprofiler.app.ui.dialogfragment;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.instaprofiler.app.R;
import com.instaprofiler.app.data.model.User;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class ImageDialog extends BottomSheetDialogFragment {
    User user;
    RelativeLayout container;

    public static final String LOCATION = "/InstaProfiler/Photos";

    public ImageDialog(User user) {
        this.user = user;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL,R.style.TransparentDialogTheme);
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
        Glide.with(this).load(user.getProfilePicUrlHd()).into(imageView);
        FloatingActionButton share = view.findViewById(R.id.share);
        container= view.findViewById(R.id.imageFrame);
        share.setOnClickListener(v -> shareImg());

        AdView mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        TextView following=view.findViewById(R.id.p_following);
        TextView followers=view.findViewById(R.id.p_follower);
        TextView name=view.findViewById(R.id.p_name);
        followers.setText(getInMK(user.getEdgeFollowedBy().getCount()));
        following.setText(getInMK(user.getEdgeFollow().getCount()));
        name.setText(user.getUsername());

    }

    public  static  String getInMK(Integer count)
    {
        if(count<1000)
            return ""+count;
        else if(count>=1000 && count<1000*1000)
            return String.format("%.2f K",(float)(count/1000));
        else
            return String.format("%.2f M",(float)count/(1000*1000));
    }


    private Uri saveImage(String extension) {
        Uri uri = null;
        Bitmap bitmap;
        bitmap = Bitmap.createBitmap(container.getWidth(), container.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        container.draw(canvas);
        boolean success;

        File path = new File(Environment.getExternalStorageDirectory().toString() + LOCATION);
        if (!path.exists()) {
            success = path.mkdirs();
            if (!success) {
                path = new File(
                        requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                                .toString() + LOCATION
                );
                path.mkdirs();
            }
        }

        File file = new File(path, +System.currentTimeMillis() + extension);
        FileOutputStream fileOutputStream;

        try {
            fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            uri = Uri.fromFile(file);
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
            values.put(MediaStore.MediaColumns.DATA, file.getAbsolutePath());
            requireActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            Toast.makeText(requireContext(),"Successfully Saved in gallery",Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            Toast.makeText(requireContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
        }
        return uri;
    }


    public void shareImg() {

        Bitmap bitmap=Bitmap.createBitmap(container.getWidth(), container.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        container.draw(canvas);

        String pathBitmap = MediaStore.Images.Media.insertImage(requireContext().getContentResolver(), bitmap, System.currentTimeMillis()+"", null);
        Intent share = new Intent();
        share.setAction(Intent.ACTION_SEND);
        share.setType("image/png");
        share.putExtra(Intent.EXTRA_STREAM, Uri.parse(pathBitmap));
        share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(Intent.createChooser(share, "Send Profile Pic"));
    }
}



