package com.example.loginandsignup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.google.firebase.auth.FirebaseAuth

class Splash : AppCompatActivity() {
   private var auth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()

        Handler(Looper.getMainLooper()).postDelayed({

            if (auth.currentUser!=null){
                val intent = Intent(this,MainActivity::class.java)
                finish()
                startActivity(intent)
            }else{
                val intent = Intent(this,SignIn::class.java)
                finish()
                startActivity(intent)
            }


        },2500)

    }
}