package project.todyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import project.todyapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        supportFragmentManager.beginTransaction().add(R.id.main_activity,SplashFragment()).commit()
        setContentView(R.layout.activity_main)
    }
}