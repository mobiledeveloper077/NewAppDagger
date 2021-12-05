package uz.abduvali.newappdagger.di.component

import dagger.Component
import uz.abduvali.newappdagger.SplashActivity
import uz.abduvali.newappdagger.di.module.DatabaseModule
import uz.abduvali.newappdagger.di.module.NetworkModule
import uz.abduvali.newappdagger.fragments.*
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DatabaseModule::class])
interface AppComponent {

    fun inject(categoryPagerFragment: CategoryPagerFragment)

    fun inject(topicsFragment: TopicsFragment)

    fun inject(splashActivity: SplashActivity)

    fun inject(categoriesFragment: CategoriesFragment)

    fun inject(homeFragment: HomeFragment)

    fun inject(bookmarksFragment: BookmarksFragment)
}