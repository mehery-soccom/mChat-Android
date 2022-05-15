package com.mehery.mchat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class MChat {

    SharedPreferences mPrefs;
    Context mContext;

    public MChat(Context context) {
        mContext = context;
    }

//    public  void init(String domain){
//        mPrefs = mContext.getSharedPreferences("MCHATPREF",Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = mPrefs.edit();
//        editor.putString("domain",domain);
//        editor.apply();
//    }


    public void start(String domain, String key, String id,MChatConfig mChatConfig){
        if(mContext != null){
            Intent intent = new Intent(mContext,MChatWebActivity.class);
            intent.putExtra("domain",domain);
            intent.putExtra("key",key);
            intent.putExtra("id",id);
            intent.putExtra("title",mChatConfig.title);
            intent.putExtra("headerColor",mChatConfig.headerColor);
            intent.putExtra("resId",mChatConfig.resId);
            intent.putExtra("titleColor",mChatConfig.titleColor);
            if(mChatConfig.backgroundColor != 0) {
                intent.putExtra("backgroundColor", mChatConfig.backgroundColor);
            }
            if(mChatConfig.sentMessageBubbleColor != 0) {
                intent.putExtra("sentMessageBubbleColor", mChatConfig.sentMessageBubbleColor);
            }
            if(mChatConfig.receivedMessageBubbleColor != 0) {
                intent.putExtra("receivedMessageBubbleColor", mChatConfig.receivedMessageBubbleColor);
            }
            if(mChatConfig.sentMessageTextColor != 0) {
                intent.putExtra("sentMessageTextColor", mChatConfig.sentMessageTextColor);
            }
            if(mChatConfig.receivedMessageTextColor != 0) {
                intent.putExtra("receivedMessageTextColor", mChatConfig.receivedMessageTextColor);
            }

            if(mChatConfig.userInputBackgroundColor != 0) {
                intent.putExtra("userInputBackgroundColor", mChatConfig.userInputBackgroundColor);
            }
            if(mChatConfig.userInputTextColor != 0) {
                intent.putExtra("userInputTextColor", mChatConfig.userInputTextColor);
            }

            mContext.startActivity(intent);

        }
    }

}
