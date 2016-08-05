package com.example.android.sunshine.app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by Maxim Turaev on 04.08.2016.
 */
public class LocationEditTextPreference extends EditTextPreference {

    private static final int DEFAULT_MINIMUM_LOCATION_LENGTH = 2;
    private final int minLength;

    public LocationEditTextPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.LocationEditTextPreference, 0, 0);
        try {
            minLength = a.getInteger(R.styleable.LocationEditTextPreference_minLength, DEFAULT_MINIMUM_LOCATION_LENGTH);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void showDialog(Bundle state) {
        super.showDialog(state);
        if (getDialog() instanceof AlertDialog) {
            final AlertDialog dialog = (AlertDialog) getDialog();
            EditText editText = (EditText) dialog.findViewById(android.R.id.edit);
            if (editText.getText().length() == 0) {
                dialog.getButton(DialogInterface.BUTTON_POSITIVE).setEnabled(false);
            }
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (s.length() >= minLength) {
                        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setEnabled(true);
                    } else {
                        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setEnabled(false);
                    }
                }
            });
        }
    }
}
