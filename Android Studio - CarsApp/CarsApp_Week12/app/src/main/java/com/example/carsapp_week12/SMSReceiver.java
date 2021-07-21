package com.example.carsapp_week12;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;

public class SMSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        SmsMessage[] msg = Telephony.Sms.Intents.getMessagesFromIntent(intent);
        for (int i = 0; i < msg.length; i++) {
            SmsMessage currentmsg = msg[i];
            String message = currentmsg.getDisplayMessageBody();

            // each new messages send a broadcast contains new message to mainActivity
            Intent messageIntent = new Intent();
            messageIntent.setAction("SMS");
            messageIntent.putExtra("KEY_DETAILS", message);
            context.sendBroadcast(messageIntent);
        }
    }
}
