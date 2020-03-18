package com.example.fcm;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;

public class MyFirebaseInstanceIDService extends FirebaseMessagingService {

    private static final String TAG = "mFirebaseIIDService";
    private static final String SUBSCRIBE_TO = "userABC";

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);

         /*
          This method is invoked whenever the token refreshes
          OPTIONAL: If you want to send messages to this application instance
          or manage this apps subscriptions on the server side,
          you can send this token to your server.
        */
        // String token = FirebaseInstanceId.getInstance().getToken();

        // Once the token is generated, subscribe to topic with the userId

        //FirebaseMessaging.getInstance().subscribeToTopic(SUBSCRIBE_TO);
        //Log.i(TAG, "onTokenRefresh completed with token: " + token);

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }
                        else {

                            // Get new Instance ID token
                            String token = task.getResult().getToken();
                            FirebaseMessaging.getInstance().subscribeToTopic(SUBSCRIBE_TO);
                            Log.i(TAG, "onTokenRefresh completed with token: " + token);
                        }

                        // Log and toast
                        //String msg = getString(, token);
                       //Log.d(TAG, msg);
                        //Toast.makeText(MyFirebaseInstanceIDService.this , msg , Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
