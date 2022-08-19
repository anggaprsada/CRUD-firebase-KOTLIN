package com.example.myapplication.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.Mahasiswa
import com.example.myapplication.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Update : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding
    private lateinit var dbRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setValue()

        binding.btnBack.setOnClickListener {
            finish()
        }
        binding.btnUpdate.setOnClickListener{
            updateMhsData()
        }
    }

    private fun setValue() {
        binding.etNim.setText(intent.getStringExtra("nim"))
        binding.etFn.setText(intent.getStringExtra("fullname"))
        binding.etProdi.setText(intent.getStringExtra("prodi"))
        binding.etPhone.setText(intent.getStringExtra("phone"))
        binding.etEmail.setText(intent.getStringExtra("email"))
        binding.etPassword.setText(intent.getStringExtra("password"))
    }


    private fun updateMhsData() {
        val nim : String = binding.etNim.text.toString().trim()
        val fullname : String = binding.etFn.text.toString().trim()
        val prodi : String = binding.etProdi.text.toString().trim()
        val phone : String = binding.etPhone.text.toString().trim()
        val email : String = binding.etEmail.text.toString().trim()
        val pass : String = binding.etPassword.text.toString().trim()

        if (nim.isEmpty()) {
            binding.etNim.error = "Please enter nim"
            if (nim < 10.toString()){
                binding.etNim.error = "Nim wrong! "
                return
            }
            return
        }
        if (fullname.isEmpty()) {
            binding.etFn.error = "Please enter fullname"
            if (prodi.isEmpty()) {
                binding.etProdi.error = "Please enter prodi"
                return
            }
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

        dbRef = FirebaseDatabase.getInstance("https://connectcrud-7a206-default-rtdb.asia-southeast1.firebasedatabase.app/")
            .getReference("Mahasiswa")

        val mhs = Mahasiswa(nim, fullname, prodi, phone, email, pass)

        dbRef.child(nim).setValue(mhs)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    val intent = Intent(this, MainMenu::class.java)
                    startActivity(intent)
                    finish()
                    Toast.makeText(applicationContext,"Successfuly Update", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this, it.exception.toString(), Toast.LENGTH_LONG).show()
                }
            }
            .addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }
}