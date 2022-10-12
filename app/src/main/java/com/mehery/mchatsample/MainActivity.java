package com.mehery.mchatsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.mehery.mchat.MChat;
import com.mehery.mchat.MChatConfig;

import java.util.Locale;

import top.defaults.colorpicker.ColorPickerPopup;



public class MainActivity extends AppCompatActivity {

    int headerColor = 0xfff;
    int headerTitleColor = 0xFF000000;
    int chatBackgroundColor;
    int sentMessageBubbleColor;
    int receivedMessageBubbleColor;
    int sentMessageTextColor;
    int receivedMessageTextColor;
    int userInputBackgroundColor;
    int userInputTextColor;
    private final int MY_PERMISSIONS_RECORD_AUDIO = 1;
    private final int MY_PERMISSIONS_READ_EXTERNAL_STORAGE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, MY_PERMISSIONS_RECORD_AUDIO);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_READ_EXTERNAL_STORAGE);
        }




        TextInputEditText domainEditText = (TextInputEditText) findViewById(R.id.domainET);
        domainEditText.setText("almullaexchange.mehery.com");

        TextInputEditText channelKeyEditText = (TextInputEditText) findViewById(R.id.channelKeyET);
        channelKeyEditText.setText("1gzrmyly9nsafL8SA7KA0VI");

        TextInputEditText channelIdEditText = (TextInputEditText) findViewById(R.id.channelIdET);
        channelIdEditText.setText("web:onlineapp");

        TextInputEditText headerTitleET = (TextInputEditText) findViewById(R.id.headerTitleET);

        Button headerColorButton = (Button) findViewById(R.id.headerColorButton);
        headerColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ColorPickerPopup.Builder(MainActivity.this)
                        .initialColor(Color.RED) // Set initial color
                        .enableBrightness(true) // Enable brightness slider or not
                        .enableAlpha(true) // Enable alpha slider or not
                        .okTitle("Choose")
                        .cancelTitle("Cancel")
                        .showIndicator(true)
                        .showValue(true)
                        .build()
                        .show(headerColorButton, new ColorPickerPopup.ColorPickerObserver() {
                            @Override
                            public void onColorPicked(int color) {
                                headerColorButton.setBackgroundColor(color);
                                headerColor = color;
                            }
                        });
            }
        });

        Button headerTitleColorButton = (Button) findViewById(R.id.headerTitleColorButton);
        headerTitleColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ColorPickerPopup.Builder(MainActivity.this)
                        .initialColor(Color.RED) // Set initial color
                        .enableBrightness(true) // Enable brightness slider or not
                        .enableAlpha(true) // Enable alpha slider or not
                        .okTitle("Choose")
                        .cancelTitle("Cancel")
                        .showIndicator(true)
                        .showValue(true)
                        .build()
                        .show(headerTitleColorButton, new ColorPickerPopup.ColorPickerObserver() {
                            @Override
                            public void onColorPicked(int color) {
                                headerTitleColorButton.setBackgroundColor(color);
                                headerTitleColor = color;
                            }
                        });
            }
        });

        Button chatBackgroundColorButton = (Button) findViewById(R.id.chatBackgroundColor);
        chatBackgroundColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ColorPickerPopup.Builder(MainActivity.this)
                        .initialColor(Color.RED) // Set initial color
                        .enableBrightness(true) // Enable brightness slider or not
                        .enableAlpha(true) // Enable alpha slider or not
                        .okTitle("Choose")
                        .cancelTitle("Cancel")
                        .showIndicator(true)
                        .showValue(true)
                        .build()
                        .show(chatBackgroundColorButton, new ColorPickerPopup.ColorPickerObserver() {
                            @Override
                            public void onColorPicked(int color) {
                                chatBackgroundColorButton.setBackgroundColor(color);
                                chatBackgroundColor = color;
                            }
                        });
            }
        });

        Button sentMessageBubbleColorButton = (Button) findViewById(R.id.sentMessageBubbleColor);
        sentMessageBubbleColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ColorPickerPopup.Builder(MainActivity.this)
                        .initialColor(Color.RED) // Set initial color
                        .enableBrightness(true) // Enable brightness slider or not
                        .enableAlpha(true) // Enable alpha slider or not
                        .okTitle("Choose")
                        .cancelTitle("Cancel")
                        .showIndicator(true)
                        .showValue(true)
                        .build()
                        .show(sentMessageBubbleColorButton, new ColorPickerPopup.ColorPickerObserver() {
                            @Override
                            public void onColorPicked(int color) {
                                sentMessageBubbleColorButton.setBackgroundColor(color);
                                sentMessageBubbleColor = color;
                            }
                        });
            }
        });


        Button sentMessageTextColorButton = (Button) findViewById(R.id.sentMessageTextColor);
        sentMessageTextColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ColorPickerPopup.Builder(MainActivity.this)
                        .initialColor(Color.RED) // Set initial color
                        .enableBrightness(true) // Enable brightness slider or not
                        .enableAlpha(true) // Enable alpha slider or not
                        .okTitle("Choose")
                        .cancelTitle("Cancel")
                        .showIndicator(true)
                        .showValue(true)
                        .build()
                        .show(sentMessageTextColorButton, new ColorPickerPopup.ColorPickerObserver() {
                            @Override
                            public void onColorPicked(int color) {
                                sentMessageTextColorButton.setBackgroundColor(color);
                                sentMessageTextColor = color;
                            }
                        });
            }
        });

        Button receivedMessageBubbleColorButton = (Button) findViewById(R.id.receivedMessageBubbleColor);
        receivedMessageBubbleColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ColorPickerPopup.Builder(MainActivity.this)
                        .initialColor(Color.RED) // Set initial color
                        .enableBrightness(true) // Enable brightness slider or not
                        .enableAlpha(true) // Enable alpha slider or not
                        .okTitle("Choose")
                        .cancelTitle("Cancel")
                        .showIndicator(true)
                        .showValue(true)
                        .build()
                        .show(receivedMessageBubbleColorButton, new ColorPickerPopup.ColorPickerObserver() {
                            @Override
                            public void onColorPicked(int color) {
                                receivedMessageBubbleColorButton.setBackgroundColor(color);
                                receivedMessageBubbleColor = color;
                            }
                        });
            }
        });


        Button receivedMessageTextColorButton = (Button) findViewById(R.id.receivedMessageTextColor);
        receivedMessageTextColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ColorPickerPopup.Builder(MainActivity.this)
                        .initialColor(Color.RED) // Set initial color
                        .enableBrightness(true) // Enable brightness slider or not
                        .enableAlpha(true) // Enable alpha slider or not
                        .okTitle("Choose")
                        .cancelTitle("Cancel")
                        .showIndicator(true)
                        .showValue(true)
                        .build()
                        .show(receivedMessageTextColorButton, new ColorPickerPopup.ColorPickerObserver() {
                            @Override
                            public void onColorPicked(int color) {
                                receivedMessageTextColorButton.setBackgroundColor(color);
                                receivedMessageTextColor = color;
                            }
                        });
            }
        });

        Button userInputBackgroundColorButton = (Button) findViewById(R.id.userInputBackgroundColor);
        userInputBackgroundColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ColorPickerPopup.Builder(MainActivity.this)
                        .initialColor(Color.RED) // Set initial color
                        .enableBrightness(true) // Enable brightness slider or not
                        .enableAlpha(true) // Enable alpha slider or not
                        .okTitle("Choose")
                        .cancelTitle("Cancel")
                        .showIndicator(true)
                        .showValue(true)
                        .build()
                        .show(userInputBackgroundColorButton, new ColorPickerPopup.ColorPickerObserver() {
                            @Override
                            public void onColorPicked(int color) {
                                userInputBackgroundColorButton.setBackgroundColor(color);
                                userInputBackgroundColor = color;
                            }
                        });
            }
        });

        Button userInputTextColorButton = (Button) findViewById(R.id.userInputTextColor);
        userInputTextColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ColorPickerPopup.Builder(MainActivity.this)
                        .initialColor(Color.RED) // Set initial color
                        .enableBrightness(true) // Enable brightness slider or not
                        .enableAlpha(true) // Enable alpha slider or not
                        .okTitle("Choose")
                        .cancelTitle("Cancel")
                        .showIndicator(true)
                        .showValue(true)
                        .build()
                        .show(userInputTextColorButton, new ColorPickerPopup.ColorPickerObserver() {
                            @Override
                            public void onColorPicked(int color) {
                                userInputTextColorButton.setBackgroundColor(color);
                                userInputTextColor = color;
                            }
                        });
            }
        });






        Button submitButton = (Button) findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!domainEditText.getText().toString().isEmpty() && !channelKeyEditText.getText().toString().isEmpty() && !channelIdEditText.getText().toString().isEmpty()){
                    MChat mChat = new MChat(MainActivity.this);
                    MChatConfig config = new MChatConfig(headerTitleET.getText().toString(),headerColor,R.drawable.robot,headerTitleColor);
                    if(chatBackgroundColor != 0){
                        config.setBackgroundColor(chatBackgroundColor);
                    }
                    if(sentMessageBubbleColor != 0){
                        config.setSentMessageBubbleColor(sentMessageBubbleColor);
                    }
                    if(sentMessageTextColor != 0){
                        config.setSentMessageTextColor(sentMessageTextColor);
                    }
                    if(receivedMessageBubbleColor != 0){
                        config.setReceivedMessageBubbleColor(receivedMessageBubbleColor);
                    }
                    if(receivedMessageTextColor != 0){
                        config.setReceivedMessageTextColor(receivedMessageTextColor);
                    }

                    if(userInputBackgroundColor != 0){
                        config.setUserInputBackgroundColor(userInputBackgroundColor);
                    }

                    if(userInputTextColor != 0){
                        config.setUserInputTextColor(userInputTextColor);
                    }
                    mChat.start(domainEditText.getText().toString(),channelKeyEditText.getText().toString(),channelIdEditText.getText().toString(),config);
                }else{
                    hideKeyboard();
                    Snackbar.make(view, "Please enter all the input fields to proceed", Snackbar.LENGTH_SHORT).show();
                }

            }
        });


    }

    private String colorHex(int color) {
        int a = Color.alpha(color);
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);
        return String.format(Locale.getDefault(), "0x%02X%02X%02X%02X", a, r, g, b);
    }

    public void hideKeyboard() {
        // Check if no view has focus:
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}