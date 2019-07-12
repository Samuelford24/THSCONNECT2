package com.samuelford48gmail.thsconnect;

import android.app.Application;

import com.google.firebase.auth.FirebaseAuth;
import com.onesignal.OneSignal;

public class ONEsignal_notifications extends Application {
    public void onCreate() {
        super.onCreate();

        // OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
        OneSignal.setSubscription(true);

    }
}
