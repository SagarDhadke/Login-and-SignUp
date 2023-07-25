package com.example.loginandsignup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.loginandsignup.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth


class SignUp : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    private var auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)


      binding.signupBtn.setOnClickListener {
          val email = binding.signupEmail.text.toString()
          val pass = binding.signupPass.text.toString()
          val confirmPass = binding.signupConfirmpass.text.toString()

       if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
           binding.signupEmail.error = "Enter Valid Email Address"
           binding.signupEmail.requestFocus()
           Toast.makeText(this,"Enter Your Details...",Toast.LENGTH_SHORT).show()
       }
       else if (pass.isEmpty()){
           binding.signupPass.error = "Enter Valid Password"
           binding.signupPass.requestFocus()
           binding.signupPass.length()>=8
           Toast.makeText(this,"Enter Your Details...",Toast.LENGTH_SHORT).show()
       }
       else if (confirmPass === pass){
           binding.signupConfirmpass.error = "Password Don't Match"
           Toast.makeText(this,"Enter Your Details...",Toast.LENGTH_SHORT).show()
       }
          else{

              auth.createUserWithEmailAndPassword(email,pass)
                  .addOnFailureListener {
                      Toast.makeText(this,it.localizedMessage?.toString(),Toast.LENGTH_SHORT).show()
                  }
                  .addOnSuccessListener {
                      Toast.makeText(this,it.user?.email.toString(),Toast.LENGTH_LONG).show()
                      val intent = Intent(this,MainActivity::class.java)
                      startActivity(intent)
                      finishAffinity()
                  }
                  .addOnCanceledListener {
                      Toast.makeText(this,"SignUp Failed Please Try Again..",Toast.LENGTH_LONG).show()
                  }
       }

      }

        binding.alreadyHaveA.setOnClickListener {
            val intent = Intent(this,Login::class.java)
            startActivity(intent)
            finish()
        }



    }
}