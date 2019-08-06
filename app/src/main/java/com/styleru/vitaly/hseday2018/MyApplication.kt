package com.styleru.vitaly.hseday2018

import android.app.Application
import com.yandex.metrica.YandexMetrica
import com.yandex.metrica.YandexMetricaConfig



class MyApplication: Application() {
//    var mFirebaseAnalytics: FirebaseAnalytics? = null
    override fun onCreate() {
        super.onCreate()
//        FirebaseApp.initializeApp(this);
//        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        val configBuilder = YandexMetricaConfig.newConfigBuilder("2ab4f90d-5000-4b37-bd99-a942ba7c945a")
        YandexMetrica.activate(applicationContext, configBuilder.build())

        YandexMetrica.enableActivityAutoTracking(this)
    }
}