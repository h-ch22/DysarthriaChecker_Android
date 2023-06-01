package com.cj.dysarthriachecker.frameworks.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.fragment.app.FragmentActivity
import com.cj.dysarthriachecker.ui.theme.DysarthriaCheckerTheme

class StartActivity : FragmentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DysarthriaCheckerTheme {
                SplashView()
            }
        }
    }
}