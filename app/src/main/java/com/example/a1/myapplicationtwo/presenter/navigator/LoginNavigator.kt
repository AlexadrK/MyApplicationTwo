package com.example.a1.myapplicationtwo.presenter.navigator

import android.app.Activity
import com.example.a1.myapplicationtwo.NewsActivity
import com.example.a1.myapplicationtwo.RegisterActivity
import org.jetbrains.anko.startActivity

class LoginNavigator(
        private val activity: Activity) {

    fun toMain() {
        activity.startActivity<NewsActivity>()
    }

    fun toRegistration() {
        activity.startActivity<RegisterActivity>()
    }

}