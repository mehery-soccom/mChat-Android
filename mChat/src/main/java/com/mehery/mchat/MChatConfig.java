package com.mehery.mchat;

import android.graphics.Bitmap;

public class MChatConfig {

    String title;
    int headerColor;
    int resId;
    int titleColor;

    int sentMessageBubbleColor;
    int receivedMessageBubbleColor;
    int backgroundColor;

    int sentMessageTextColor;
    int receivedMessageTextColor;

    int userInputBackgroundColor;
    int userInputTextColor;



    public MChatConfig(String title, int headerColor, int resId,int titleColor) {
        this.title = title;
        this.headerColor = headerColor;
        this.resId = resId;
        this.titleColor = titleColor;
    }

    public void setSentMessageBubbleColor(int sentMessageBubbleColor) {
        this.sentMessageBubbleColor = sentMessageBubbleColor;
    }

    public void setReceivedMessageBubbleColor(int receivedMessageBubbleColor) {
        this.receivedMessageBubbleColor = receivedMessageBubbleColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setSentMessageTextColor(int sentMessageTextColor) {
        this.sentMessageTextColor = sentMessageTextColor;
    }

    public void setReceivedMessageTextColor(int receivedMessageTextColor) {
        this.receivedMessageTextColor = receivedMessageTextColor;
    }

    public void setUserInputBackgroundColor(int userInputBackgroundColor) {
        this.userInputBackgroundColor = userInputBackgroundColor;
    }

    public void setUserInputTextColor(int userInputTextColor) {
        this.userInputTextColor = userInputTextColor;
    }
}
