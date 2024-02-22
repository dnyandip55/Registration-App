package com.example.registrationapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignIn : AppCompatActivity() {
    lateinit var databaseReference: DatabaseReference

    companion object {
         val KEY1="com.example.registrationapp.KEY1"
        val KEY2="com.example.registrationapp.KEY2"
        val KEY3="com.example.registrationapp.KEY3"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        val signInBtn=findViewById<Button>(R.id.signInBtn)
        val userName=findViewById<TextInputEditText>(R.id.userName)
        val password=findViewById<TextInputEditText>(R.id.password)

        signInBtn.setOnClickListener(){
            val uniqueId=userName.text.toString()
            val password=password.text.toString()

            if(uniqueId.isNotEmpty() && password.isNotEmpty()){
                readData(uniqueId,password)
            }else{
                Toast.makeText(this,"Please enter user ID and password",Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun readData(uniqueId: String,password:String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("User")
        databaseReference.child(uniqueId).get().addOnSuccessListener { dataSnapshot ->
            if (dataSnapshot.exists()) {
                val email = dataSnapshot.child("email").value?.toString()
                val name = dataSnapshot.child("name").value?.toString()
                val userId = dataSnapshot.child("uniqueId").value?.toString()
                val correctPassword=dataSnapshot.child("password").value?.toString()

                if (password == correctPassword) {
                    val welcomeIntent = Intent(this, WelcomeActivity::class.java)
                    welcomeIntent.putExtra(KEY1, email)
                    welcomeIntent.putExtra(KEY2, name)
                    welcomeIntent.putExtra(KEY3, userId)
                    startActivity(welcomeIntent)
            } else {
                Toast.makeText(this, "Incorrect password", Toast.LENGTH_SHORT).show()
            }
            } else {
                Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Failed to retrieve data", Toast.LENGTH_SHORT).show()
        }
    }

}