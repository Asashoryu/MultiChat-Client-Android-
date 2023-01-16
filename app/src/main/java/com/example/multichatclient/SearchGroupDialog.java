package com.example.multichatclient;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;


public class SearchGroupDialog extends AppCompatDialogFragment {

    EditText ed_name;
    NewGroupDialog.DialogInterface dialogInterface;

    @NonNull
    @Override
    public Dialog onCreateDialog (@Nullable Bundle savedIstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.group_dialog,null);
        builder.setView(view)
                .setTitle("Cerca Gruppo")
                .setNegativeButton("Cancel", new android.content.DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(android.content.DialogInterface dialog, int i) {

                    }
                })
                .setPositiveButton("Ok", new android.content.DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(android.content.DialogInterface dialog, int i) {
                        String name = ed_name.getText().toString();
                        dialogInterface.searchGroup(name);
                    }
                });
        ed_name = view.findViewById(R.id.group_name);
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        dialogInterface = (NewGroupDialog.DialogInterface) context;
    }
}

