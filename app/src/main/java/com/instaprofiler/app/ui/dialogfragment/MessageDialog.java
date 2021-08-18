package com.instaprofiler.app.ui.dialogfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.instaprofiler.app.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class MessageDialog extends BottomSheetDialogFragment {

    public String msg;
    public MessageDialog(String msg)
    {
        this.msg=msg;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.TransparentDialogTheme);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.dialog_message,container,false);
         TextView text=v.findViewById(R.id.dialog_text_message);
         text.setText(msg);
        return  v;
    }
}
