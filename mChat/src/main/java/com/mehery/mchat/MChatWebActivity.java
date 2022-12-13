 package com.mehery.mchat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.CookieManager;
import android.webkit.JavascriptInterface;
import android.webkit.MimeTypeMap;
import android.webkit.PermissionRequest;
import android.webkit.URLUtil;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.LazyLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.StringWriter;
import java.util.HashMap;

 public class MChatWebActivity extends AppCompatActivity implements PostMessageInterface {

    WebView mywebview;
    String domain;
    String key;
    String id;
    int headerColor;
    String title;
    int resId;
    int titleColor;
    LazyLoader lazyLoader;

    int backgroundColor;
    int sentMessageBubbleColor;
    int receivedMessageBubbleColor;
    int sentMessageTextColor;
    int receivedMessageTextColor;

    int userInputBackgroundColor;
    int userInputTextColor;

     public ValueCallback<Uri[]> uploadMessage;
     private ValueCallback<Uri> mUploadMessage;
     public static final int REQUEST_SELECT_FILE = 100;
     private final static int FILECHOOSER_RESULTCODE = 1;
     OnComplete onComplete ;
     private final int MY_PERMISSIONS_RECORD_AUDIO = 1;
     private final int MY_PERMISSIONS_READ_EXTERNAL_STORAGE = 2;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_chat_web);
         if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
             ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, MY_PERMISSIONS_RECORD_AUDIO);
         }

         if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
             ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_READ_EXTERNAL_STORAGE);
         }
         onComplete = new OnComplete();
        lazyLoader = (LazyLoader) findViewById(R.id.loaderView);
        lazyLoader.setVisibility(View.VISIBLE);

        mywebview = (WebView) findViewById(R.id.webView);
        RelativeLayout headerLayout = (RelativeLayout) findViewById(R.id.headerLayout);
        ImageView chatImage = (ImageView) findViewById(R.id.chatImage);
        TextView headerTitle = (TextView) findViewById(R.id.headerTitle);


        headerColor = getIntent().getExtras().getInt("headerColor");
        title = getIntent().getExtras().getString("title");
        resId = getIntent().getExtras().getInt("resId");
        titleColor = getIntent().getExtras().getInt("titleColor");

        if(getIntent().hasExtra("backgroundColor")){
            backgroundColor = getIntent().getExtras().getInt("backgroundColor");
        }

        if(getIntent().hasExtra("sentMessageBubbleColor")){
            sentMessageBubbleColor = getIntent().getExtras().getInt("sentMessageBubbleColor");
        }

        if(getIntent().hasExtra("receivedMessageBubbleColor")){
            receivedMessageBubbleColor = getIntent().getExtras().getInt("receivedMessageBubbleColor");
        }

        if(getIntent().hasExtra("sentMessageTextColor")){
            sentMessageTextColor = getIntent().getExtras().getInt("sentMessageTextColor");
        }

        if(getIntent().hasExtra("receivedMessageTextColor")){
            receivedMessageTextColor = getIntent().getExtras().getInt("receivedMessageTextColor");
        }

        if(getIntent().hasExtra("userInputBackgroundColor")){
            userInputBackgroundColor = getIntent().getExtras().getInt("userInputBackgroundColor");
        }

        if(getIntent().hasExtra("userInputTextColor")){
            userInputTextColor = getIntent().getExtras().getInt("userInputTextColor");
        }



        Log.e("HeaderColor",String.valueOf(headerColor));

        headerLayout.setBackgroundColor(headerColor);
        chatImage.setImageDrawable(getResources(). getDrawable(resId));
        headerTitle.setText(title);
        headerTitle.setTextColor(titleColor);

        domain = getIntent().getExtras().getString("domain");
        key = getIntent().getExtras().getString("key");
        id = getIntent().getExtras().getString("id");

        mywebview.setWebChromeClient(new WebChromeClient());
        mywebview.clearCache(true);
        mywebview.clearHistory();
        mywebview.getSettings().setJavaScriptEnabled(true);
        mywebview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mywebview.getSettings().setDomStorageEnabled(true);


        mywebview.getSettings().setLoadsImagesAutomatically(true);
        mywebview.getSettings().setJavaScriptEnabled(true);
        mywebview.getSettings().setDomStorageEnabled(true);
        mywebview.getSettings().setAppCacheEnabled(true);
        mywebview.getSettings().setDomStorageEnabled(true);
        mywebview.getSettings().setAllowContentAccess(true);
        mywebview.getSettings().setAllowFileAccess(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mywebview.getSettings().setSafeBrowsingEnabled(false);
            mywebview.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        mywebview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(mywebview, true);
        }
        mywebview.getSettings().setAllowContentAccess(true);
        mywebview.getSettings().setAllowFileAccess(true);
        mywebview.getSettings().setBlockNetworkImage(false);
        mywebview.getSettings().setLoadWithOverviewMode(true);
        mywebview.getSettings().setUseWideViewPort(true);
        mywebview.getSettings().setUseWideViewPort(true);
        mywebview.getSettings().setAllowUniversalAccessFromFileURLs(true);
        mywebview.getSettings().setAllowFileAccessFromFileURLs(true);
        mywebview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        HashMap<String,String> headerMap = new HashMap<>();
        String ua=new WebView(this).getSettings().getUserAgentString();
        mywebview.getSettings().setUserAgentString(ua + "WebViewApp");
        mywebview.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onPermissionRequest(PermissionRequest request) {
                runOnUiThread(() -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        request.grant(request.getResources());
                    }
                });
            }

            @Override
            public void onPermissionRequestCanceled(PermissionRequest request) {
                super.onPermissionRequestCanceled(request);
            }

                                         protected void openFileChooser(ValueCallback uploadMsg, String acceptType) {
                                             mUploadMessage = uploadMsg;
                                             Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                                             i.addCategory(Intent.CATEGORY_OPENABLE);
                                             i.setType("image/*");
                                             startActivityForResult(Intent.createChooser(i, "File Chooser"), FILECHOOSER_RESULTCODE);
                                         }


                                         // For Lollipop 5.0+ Devices
                                         @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                                         public boolean onShowFileChooser(WebView mWebView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
                                             if (uploadMessage != null) {
                                                 uploadMessage.onReceiveValue(null);
                                                 uploadMessage = null;
                                             }

                                             uploadMessage = filePathCallback;

                                             Intent intent = fileChooserParams.createIntent();
                                             try {
                                                 startActivityForResult(intent, REQUEST_SELECT_FILE);
                                             } catch (ActivityNotFoundException e) {
                                                 uploadMessage = null;
                                                 Toast.makeText(MChatWebActivity.this, "Cannot Open File Chooser", Toast.LENGTH_LONG).show();
                                                 return false;
                                             }
                                             return true;
                                         }

                                         //For Android 4.1 only
                                         protected void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                                             mUploadMessage = uploadMsg;
                                             Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                                             intent.addCategory(Intent.CATEGORY_OPENABLE);
                                             intent.setType("image/*");
                                             startActivityForResult(Intent.createChooser(intent, "File Chooser"), FILECHOOSER_RESULTCODE);
                                         }

                                         protected void openFileChooser(ValueCallback<Uri> uploadMsg) {
                                             mUploadMessage = uploadMsg;
                                             Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                                             i.addCategory(Intent.CATEGORY_OPENABLE);
                                             i.setType("image/*");
                                             startActivityForResult(Intent.createChooser(i, "File Chooser"), FILECHOOSER_RESULTCODE);
                                         }



            // The webPage has 2 filechoosers and will send a
            // console message informing what action to perform,
            // taking a photo or updating the file

            public boolean onConsoleMessage(ConsoleMessage cm) {

                onConsoleMessage(cm.message(), cm.lineNumber(), cm.sourceId());
                return true;
            }

            public void onConsoleMessage(String message, int lineNumber, String sourceID) {
                //Log.d("androidruntime", "Show console messages, Used for debugging: " + message);

            }
        });
         mywebview.getSettings().setMediaPlaybackRequiresUserGesture(false);
         mywebview.getSettings().setMixedContentMode( WebSettings.MIXED_CONTENT_ALWAYS_ALLOW );
        mywebview.loadUrl("https://"+domain+"/postman/ext/plugin/customer/app/chat/");

        setPostMessgeListener(mywebview);
    }

     @Override
     protected void onPause() {
         super.onPause();
         // to stop handling download complete when exit app
         unregisterReceiver(onComplete);
     }




     @Override
     public void onActivityResult(int requestCode, int resultCode, Intent intent) {
         super.onActivityResult(requestCode, resultCode, intent);
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
             if (requestCode == REQUEST_SELECT_FILE) {
                 if (uploadMessage == null)
                     return;
                 uploadMessage.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode, intent));
                 uploadMessage = null;
             }
         } else if (requestCode == FILECHOOSER_RESULTCODE) {
             if (null == mUploadMessage)
                 return;
             // Use MainActivity.RESULT_OK if you're implementing WebView inside Fragment
             // Use RESULT_OK only if you're implementing WebView inside an Activity
             Uri result = intent == null || resultCode != MChatWebActivity.RESULT_OK ? null : intent.getData();
             mUploadMessage.onReceiveValue(result);
             mUploadMessage = null;
         } else
             Toast.makeText(MChatWebActivity.this, "Failed to Upload Image", Toast.LENGTH_LONG).show();
     }

     void setPostMessgeListener(WebView webView){
        webView.addJavascriptInterface(new JsObject(this::postMessageReceived), "Android");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                webView.loadUrl("javascript:(function() {" +
                        "function receiveMessage(event) {\n" +
                        "Android.receiveMessage(JSON.stringify(event.data));\n" +
                        "}" +
                        "window.addEventListener(\"message\", receiveMessage, false);"+
                        "})()"
                );
                Log.i("TAG", "onPageStarted "+url);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
//        getActionBar().hide();
//        getSupportActionBar().hide();
    }

    @Override
    protected void onStop() {
        super.onStop();
//        getActionBar().show();
//        getSupportActionBar().show();
    }

    void evaluateScript(WebView webView, String script){
        webView.post(new Runnable() {
            @Override
            public void run() {
                webView.evaluateJavascript(script, new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String s) {
                        Log.d("CALLBACK",s);
                    }
                });
            }
        });

    }

     public static String escape(final CharSequence text) {

         if (text == null) {
             return "";
         }
         final StringWriter writer = new StringWriter();
         for (int i = 0, length = text.length(); i < length; i++) {
             final char c = text.charAt(i);
             switch (c) {
                 case '"':
                     writer.write("\\\"");
                     break;
                 case '\\':
                     writer.write("\\\\");
                     break;
                 default:
                     if (c > 0x1f) {
                         writer.write(c);
                     } else {
                         writer.write("\\u");
                         final String hex = "000" + Integer.toHexString(c);
                         writer.write(hex.substring(hex.length() - 4));
                     }
             }
         }
         return writer.toString();
     }

    @Override
    public void postMessageReceived(String data) {
        Log.i("JsObject", "postMessage data="+data);
        if(data.contains("DOWNLOAD_FILE")){
            try {
                data = data.replace("\\\"", "\"");
                data = data.substring(data.indexOf("{"), data.lastIndexOf("}") + 1);
                JSONObject obj = new JSONObject(data);
                JSONObject myChatEventObject = obj.optJSONObject("myChatEvent");
                if(myChatEventObject != null){
                    String url = myChatEventObject.optString("url");
                    if(url != null){
                        Log.e("URL",url);
//                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//                        startActivity(intent);

                        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));

                        request.setTitle("Downloading...");  //set title for notification in status_bar
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);  //flag for if you want to show notification in status or not

                        //String nameOfFile = "YourFileName.pdf";    //if you want to give file_name manually

                        String nameOfFile = URLUtil.guessFileName(url, null, MimeTypeMap.getFileExtensionFromUrl(url)); //fetching name of file and type from server

                        File f = new File(Environment.DIRECTORY_PICTURES);       // location, where to download file in external directory
                        if (!f.exists()) {
                            f.mkdirs();
                        }
                        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, nameOfFile);
                        DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                        downloadManager.enqueue(request);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if(data.contains("ON_CHAT_LOAD")){

            Log.e("ADDED ONCHAT","ONCHAT");
            String config = "{";
            config= config+"\"header.disabled\" : true";
            config= config+",\"launcher.open\" : true";
            if(backgroundColor != 0){
                config= config+",\"messageList.bg.color\" : \""+String.format("#%06X", (0xFFFFFF & backgroundColor))+"\"";
            }

            if(sentMessageBubbleColor != 0){
                config= config+",\"sentMessage.bg.color\" : \""+String.format("#%06X", (0xFFFFFF & sentMessageBubbleColor))+"\"";
            }

            if(sentMessageTextColor != 0){
                config= config+",\"sentMessage.text.color\" : \""+String.format("#%06X", (0xFFFFFF & sentMessageTextColor))+"\"";
            }

            if(receivedMessageBubbleColor != 0){
                config= config+",\"receivedMessage.bg.color\" : \""+String.format("#%06X", (0xFFFFFF & receivedMessageBubbleColor))+"\"";
            }

            if(receivedMessageTextColor != 0){
                config= config+",\"receivedMessage.text.color\" : \""+String.format("#%06X", (0xFFFFFF & receivedMessageTextColor))+"\"";
            }

            if(userInputBackgroundColor != 0){
                config= config+",\"userInput.bg.color\" : \""+String.format("#%06X", (0xFFFFFF & userInputBackgroundColor))+"\"";
            }

            if(userInputTextColor != 0){
                config= config+",\"userInput.text.color\" : \""+String.format("#%06X", (0xFFFFFF & userInputTextColor))+"\"";
            }
            config= config+"}";
            Log.e("Config",config);
            String options = "{" +
                    "event : \"SET_OPTIONS\" , " +
                    "options : {" +
                                "\"domain\" : \""+domain+"\", " +
                                "\"channelId\" : \""+id+"\", " +
                                "\"channelKey\" : \""+key+"\" , " +
                                "\"config\" : " +config+
                                "}" +
                            "}";
            Log.e("OPTIONS",options);
            evaluateScript(mywebview,"javascript: callMobileEventListener("+options+");");
        }else if(data.contains("ON_CHAT_TOGGLE")){
            lazyLoader.setVisibility(View.GONE);
        }


    }
}

class JsObject {
    PostMessageInterface postMessageInterface;

    JsObject(PostMessageInterface postMessageInterface){
        this.postMessageInterface = postMessageInterface;
    }
    @JavascriptInterface
    public void receiveMessage(String data) {
        if(postMessageInterface != null){
            postMessageInterface.postMessageReceived(data);
        }
        //handle data here
    }
}

interface PostMessageInterface{
    void postMessageReceived(String data);
}