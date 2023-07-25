package com.example.loginandsignup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.core.graphics.toColorInt
import com.example.loginandsignup.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth


class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private var auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.dontsignup.setOnClickListener {
            val intent = Intent(this,SignUp::class.java)
            startActivity(intent)
        }

        binding.signinBtn.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.etEmail.error = "Please Enter Valid Email"
                binding.etEmail.requestFocus()
                Toast.makeText(this,"Please Enter Correct Email",Toast.LENGTH_SHORT).show()
            }
            else if (password.length<=6 && password.isEmpty()){
                binding.etPassword.requestFocus()
                binding.etPassword.error = "Please Enter Correct Password"
                Toast.makeText(this,"Please Enter Password",Toast.LENGTH_SHORT).show()
            }else{
                auth.signInWithEmailAndPassword(email,password)
                    .addOnFailureListener {
                        Toast.makeText(this,it.localizedMessage?.toString(),Toast.LENGTH_LONG).show()
                    }

                    .addOnCanceledListener {
                        Toast.makeText(this,"Login Failed Please Try Again..",Toast.LENGTH_LONG).show()
                    }

                    .addOnSuccessListener {
                        Toast.makeText(this,it.user?.email.toString(),Toast.LENGTH_LONG).show()
                        val intent = Intent(this,MainActivity::class.java)
                        startActivity(intent)
                        finishAffinity()
                    }
            }
        }



    }
}
