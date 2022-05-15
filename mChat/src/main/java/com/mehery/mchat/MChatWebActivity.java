package com.mehery.mchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.webkit.JsReplyProxy;
import androidx.webkit.WebMessageCompat;
import androidx.webkit.WebViewCompat;
import androidx.webkit.WebViewFeature;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.agrawalsuneet.dotsloader.loaders.LazyLoader;
import com.github.loadingview.LoadingDialog;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_chat_web);
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
        mywebview.loadUrl("https://"+domain+"/postman/ext/plugin/customer/app/chat/");
        setPostMessgeListener(mywebview);
//        String yourhtmlpage = "<!DOCTYPE html>\n" +
//                "<html>\n" +
//                "  <head>\n" +
//                "    <title>Hello World!</title>\n" +
//                "    <link rel=\"stylesheet\" href=\"styles.css\" />\n" +
//                "  </head>\n" +
//                "  <body>\n" +
//                "      <p id=\"p1\">Hello World!</p>\n" +
//                "      <button onclick=\"myFunction()\">Click me</button>\n" +
//                "      <p id=\"currentTime\"></p>\n" +
//                "      <script >\n" +
//                "       \n" +
//                "        \n" +
//                "        function callMobileEventListener(option){\n" +
//                "          alert(\"Test\")\n" +
//                "          document.getElementById(\"p1\").innerHTML = option.options.domain;\n" +
//                "        }\n" +
//                "        \n" +
//                "        function myFunction(){\n" +
//                "           window.parent.postMessage(\"ON_CHAT_LOAD\", '*');\n" +
//                "        }\n" +
//                "      </script>\n" +
//                "  </body>\n" +
//                "</html>";
//        mywebview.loadDataWithBaseURL(null, yourhtmlpage, "text/html", "UTF-8", null);

//        mywebview.loadUrl("https://pranjal.mehery.io");
//        mywebview.loadUrl("https://www.google.com");
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
        getSupportActionBar().hide();
    }

    @Override
    protected void onStop() {
        super.onStop();
        getSupportActionBar().show();
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

    @Override
    public void postMessageReceived(String data) {
        Log.i("JsObject", "postMessage data="+data);
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