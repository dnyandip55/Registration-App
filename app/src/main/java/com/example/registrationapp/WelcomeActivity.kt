package com.example.registrationapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val userName=intent.getStringExtra(SignIn.KEY3)
        val name=intent.getStringExtra(SignIn.KEY2)
        val email=intent.getStringExtra(SignIn.KEY1)


        val welcomeText=findViewById<TextView>(R.id.tvWelcome)
        val mailText=findViewById<TextView>(R.id.tvEmail)
        val idText=findViewById<TextView>(R.id.tvUserName)

        welcomeText.text="Welcome $name"
        mailText.text="Email: $email"
        idText.text="User Id: $userName"

    }
}