package com.actosoftcommunity.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by martinmelo on 5/11/17.
 */

public class LoginDialogFragment extends DialogFragment {

    public interface LoginDialogListener
    {
        public void check(String correo, String pass);
    }

    LoginDialogListener mListener;

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        try
        {
            mListener = (LoginDialogListener) context;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(context.toString() + "must implement ksfldkfdlsfñsd");
        }

    }
    EditText ETEmail, ETPass;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.login_dialog, null);

        builder.setView(dialogView);
        ETEmail = (EditText) dialogView.findViewById(R.id.ETEmail);
        ETPass = (EditText) dialogView.findViewById(R.id.ETPass);
                builder.setPositiveButton(R.string.Positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.i("TG","que pex");
                        String correo = ETEmail.getText().toString();
                        String pass = ETPass.getText().toString();
                        Log.i("TG",correo);
                        buttonClicked(correo, pass);
                    }
                })
                .setNegativeButton(R.string.Negative, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        LoginDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }

    public void buttonClicked(String correo, String contra)
    {
        mListener.check(correo, contra);
    }
}
