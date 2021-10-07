package com.wontak.braze.iam

import android.content.Context
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun toast(context: Context, text: String) = CoroutineScope(Dispatchers.Main).launch {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}