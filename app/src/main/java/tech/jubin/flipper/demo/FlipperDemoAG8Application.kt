package tech.jubin.flipper.demo

import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.navigation.NavigationFlipperPlugin
import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.flipper.plugins.sharedpreferences.SharedPreferencesFlipperPlugin
import com.facebook.soloader.SoLoader
import fr.afaucogney.mobile.flipper.BackStackFlipperPlugin
import tech.jubin.flipper.demo.network.NetworkUtils

class FlipperDemoAG8Application : Application() {


    override fun onCreate() {
        super.onCreate()
        SoLoader.init(this, false)

        val client = AndroidFlipperClient.getInstance(this)
        client.addPlugin(InspectorFlipperPlugin(this, DescriptorMapping.withDefaults()))
        client.addPlugin(DatabasesFlipperPlugin(this))
        client.addPlugin(NavigationFlipperPlugin.getInstance())
        NetworkFlipperPlugin().run {
            val flipperInterceptor = FlipperOkhttpInterceptor(this, true)
            NetworkUtils.addInterceptor(flipperInterceptor)
            client.addPlugin(this)
        }
        client.addPlugin(SharedPreferencesFlipperPlugin(this))
        client.addPlugin(BackStackFlipperPlugin(this))
        try {
            client.start()
        } catch (t: Throwable) {
            println(t.message)
        }

    }
}