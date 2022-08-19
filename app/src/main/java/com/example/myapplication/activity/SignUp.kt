package com.example.myapplication.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.Mahasiswa
import com.example.myapplication.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var dbRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        dbRef = FirebaseDatabase
            .getInstance("https://connectcrud-7a206-default-rtdb.asia-southeast1.firebasedatabase.app/")
            .getReference("Mahasiswa")
        binding.btnSU.setOnClickListener{
            saveData()
        }

        binding.btnBack.setOnClickListener{
            finish()
        }
        binding.tvT.setOnClickListener{
            Toast.makeText(this,"event Terms", Toast.LENGTH_LONG).show()
        }
    }

    private fun saveData() {
        val nim = binding.etNim.text.toString()
        val fullname = binding.etFn.text.toString()
        val prodi = binding.etProdi.text.toString()
        val phone = binding.etPhone.text.toString()
        val email = binding.etEmail.text.toString()
        val pass = binding.etPassword.text.toString()
        val cpass = binding.etCPassword.text.toString()

        if (nim.isEmpty()) {
            binding.etNim.error = "Please enter nim"
            if (nim < 10.toString()){
                    binding.etNim.error = "Nim "
                return
            }
            return
        }
        if (fullname.isEmpty()) {
            binding.etFn.error = "Please enter fullname"
            return
        }
        if (prodi.isEmpty()) {
            binding.etProdi.error = "Please enter prodi"
            return
        }
        if (phone.isEmpty()) {
            binding.etPhone.error = "Please enter phone"
            if (phone < 10.toString()){
                binding.etPhone.error = "Phone number wrong!"
                return
            }
            return
        }
        if (email.isEmpty()) {
            binding.etEmail.error = "Please enter email"
            return
        }
        if (pass.isEmpty()){
            binding.etPassword.error = "Password not Empty"
            return
        }
        if (cpass.isEmpty()){
            binding.etPassword.error = "Confirm Password not Empty"
            if(pass != cpass) {
                binding.etCPassword.error = "Password not Equals"
                return
            }
            return
        }

        val mhs = Mahasiswa(nim,fullname,prodi,phone,email,pass)
        dbRef.child(nim).setValue(mhs)
            .addOnCompleteListener {
                firebaseAuth.createUserWithEmailAndPassword(email,pass)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            Intent(this, SignIn::class.java).also {
                                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                startActivity(it)
                                Toast.makeText(this, "Success Sign Up", Toast.LENGTH_LONG).show()
                            }
                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_LONG).show()
                        }
                    }
            }
            .addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }
}
