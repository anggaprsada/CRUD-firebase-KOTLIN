package com.example.myapplication.activity

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.databinding.ActivityEditBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Edit : AppCompatActivity() {

    lateinit var binding : ActivityEditBinding
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var dbRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setValuesToViews()
        initView()

        binding.btnEdit.setOnClickListener{
            val intent = Intent(this,Update::class.java)
            intent.putExtra("nim",binding.tvNim.text)
            intent.putExtra("fullname",binding.tvFn.text)
            intent.putExtra("prodi",binding.tvProdi.text)
            intent.putExtra("phone",binding.tvPhone.text)
            intent.putExtra("email",binding.tvEmail.text)
            intent.putExtra("password",binding.tvPassword.text)
            startActivity(intent)
        }
        binding.btnDelete.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("Are You Sure?")
            dialog.setMessage("Deleting Data and Account will result in completely removing your account from the system and won't be able to access the app")
            dialog.setPositiveButton("Delete") { dialogInterface: DialogInterface, i: Int ->
                val nim = intent.getStringExtra("nim")
                deleteData(nim)
            }
            dialog.setNegativeButton("Dismiss",{ dialogInterface: DialogInterface, i: Int -> })
            dialog.show()

        }
    }

    private fun deleteData(nim: String?) {
        dbRef = FirebaseDatabase.getInstance("https://connectcrud-7a206-default-rtdb.asia-southeast1.firebasedatabase.app/")
            .getReference("Mahasiswa").child(nim!!)
        dbRef.removeValue()
            .addOnCompleteListener {
                if (it.isSuccessful){
                    firebaseAuth = FirebaseAuth.getInstance()
                    val user = firebaseAuth.currentUser!!
                    user.delete()
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                Intent(this, SignIn::class.java).also {
                                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    startActivity(it)
                                    Toast.makeText(applicationContext,"Successfuly Delete Data and Account", Toast.LENGTH_LONG).show()
                                }
                            } else {
                                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_LONG).show()
                            }
                        }
                }else{
                    Toast.makeText(this, it.exception.toString(), Toast.LENGTH_LONG).show()
                }
            }
            .addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }

    private fun initView(){

    }
    private fun setValuesToViews(){
        binding.tvNim.text = intent.getStringExtra("nim")
        binding.tvFn.text = intent.getStringExtra("fullname")
        binding.tvProdi.text = intent.getStringExtra("prodi")
        binding.tvPhone.text = intent.getStringExtra("phone")
        binding.tvEmail.text = intent.getStringExtra("email")
        binding.tvPassword.text = intent.getStringExtra("password")

    }
}