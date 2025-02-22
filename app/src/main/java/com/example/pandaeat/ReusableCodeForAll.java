package com.example.pandaeat;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class ReusableCodeForAll {
    public static void ShowAlert(Context context, String title, String messeage)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).setTitle(title).setMessage(messeage).show();
    }
}
