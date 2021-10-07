package com.wontak.braze.iam

import android.app.Application
import com.braze.Braze
import com.braze.BrazeActivityLifecycleCallbackListener
import com.braze.ui.inappmessage.BrazeInAppMessageManager
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AndroidApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initBrazeSDK()
    }

    private fun initBrazeSDK() {
        BrazeInAppMessageManager.getInstance().ensureSubscribedToInAppMessageEvents(this)
        registerActivityLifecycleCallbacks(BrazeActivityLifecycleCallbackListener(true, true))
        FirebaseMessaging.getInstance().token.addOnSuccessListener {
            Braze.getInstance(this).registerPushToken(it)
        }
        CoroutineScope(Dispatchers.IO).launch {
            try {
                @Suppress("BlockingMethodInNonBlockingContext")
                val adInfo = AdvertisingIdClient.getAdvertisingIdInfo(baseContext)
                val id = adInfo.id ?: throw Exception("Could not find any google advertising id")
                Braze.getInstance(baseContext).changeUser(id)
                Braze.getInstance(baseContext).setGoogleAdvertisingId(id, adInfo.isLimitAdTrackingEnabled)
                toast(baseContext, id)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}