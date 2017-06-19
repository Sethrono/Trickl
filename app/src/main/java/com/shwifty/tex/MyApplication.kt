package com.shwifty.tex

import android.app.Application
import android.util.Log
import com.facebook.stetho.Stetho
import com.shwifty.tex.chromecast.CastHandler
import com.uphyca.stetho_realm.RealmInspectorModulesProvider
import io.realm.Realm

/**
 * Created by arran on 29/04/2017.
 */
class MyApplication : Application() {

    companion object{
        var castHandler: CastHandler = CastHandler()
    }

    override fun onCreate() {
        //be aware of the order of initialisation
        Realm.init(this)
        TricklComponent.install()
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build())

        val arch = System.getProperty("os.arch")
        Log.v("architecture", arch)
    }


}