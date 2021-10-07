package com.wontak.braze.iam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.braze.ui.inappmessage.BrazeInAppMessageManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            val intent = Intent(this, DeepLinkActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "Bula bula bula", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        // Registers the BrazeInAppMessageManager for the current Activity. This Activity will now listen for
        // in-app messages from Braze.
        BrazeInAppMessageManager.getInstance().registerInAppMessageManager(this)
    }

    override fun onPause() {
        super.onPause()
        // Unregisters the BrazeInAppMessageManager.
        BrazeInAppMessageManager.getInstance().unregisterInAppMessageManager(this)
    }
}