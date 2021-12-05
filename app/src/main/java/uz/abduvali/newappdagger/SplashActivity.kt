package uz.abduvali.newappdagger

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import uz.abduvali.newappdagger.database.dao.TopicDao
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var topicDao: TopicDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        App.appComponent.inject(this)
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = if (topicDao.getTopics().isEmpty()) {
                Intent(this, WelcomeActivity::class.java)
            } else {
                Intent(this, MainActivity::class.java)
            }
            startActivity(intent)
            finish()
        }, 500)
    }
}