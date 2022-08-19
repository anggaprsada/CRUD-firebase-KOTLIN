package com.example.myapplication.activity

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class SignIn : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnSI.setOnClickListener{
            val email = binding.etEmail.text.toString()
            val pass = binding.etPassword.text.toString()
            loginUser(email,pass)
        }
        binding.btnSU.setOnClickListener{
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
    }

    private fun loginUser(email: String, pass: String) {
        if(email.isNotEmpty()&& pass.isNotEmpty()){
            firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener{
                if (it.isSuccessful){
                    Intent(this, MainMenu::class.java).also {
                        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(it)
                    }
                }else{
                    Toast.makeText(this,it.exception.toString(),Toast.LENGTH_LONG).show()
                }
            }
        }else{
            Toast.makeText(this,"Empty Fields Are not Allowed !!",Toast.LENGTH_LONG).show()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Are You Sure?")
        dialog.setMessage("Exit this app")
        dialog.setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int ->
            finish()
        }
        dialog.setNegativeButton("No",{ dialogInterface: DialogInterface, i: Int -> })
        dialog.show()
    }
    override fun onStart() {
        super.onStart()
        if (firebaseAuth.currentUser != null) {
            Intent(this, MainMenu::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }
    }
}