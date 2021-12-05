package uz.abduvali.newappdagger

import android.app.Application
import com.yariksoffice.lingver.Lingver
import uz.abduvali.newappdagger.di.component.AppComponent
import uz.abduvali.newappdagger.di.component.DaggerAppComponent
import uz.abduvali.newappdagger.di.module.DatabaseModule
import uz.abduvali.newappdagger.fragments.HomeFragment
import uz.abduvali.newappdagger.utils.MySharedPreference
import uz.abduvali.newappdagger.utils.ThemeHelper

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .databaseModule(DatabaseModule(applicationContext))
            .build()
        appComponent.inject(HomeFragment())
        Lingver.init(this, "en")
        ThemeHelper.applyTheme(ThemeHelper.lightMode)
        MySharedPreference(applicationContext).setPreferences("isDark", "0")
    }
}