package com.example.myapplication.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Mahasiswa
import com.example.myapplication.R
import com.example.myapplication.adapter.mhsAdapter
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.ActivityMainMenuBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class MainMenu : AppCompatActivity() {

    lateinit var mhsRView : RecyclerView
    lateinit var mhslist :ArrayList<Mahasiswa>
    lateinit var dbRef : DatabaseReference
    lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ActivityMainMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mhsRView =findViewById(R.id.mhsRV)
        mhsRView.layoutManager =LinearLayoutManager(this)

        mhslist = arrayListOf<Mahasiswa>()
        firebaseAuth = FirebaseAuth.getInstance()
        val user =firebaseAuth.currentUser
        if (user != null){
            getMahasiswaData()
        }else {
            finish()
        }

        binding.btnSO.setOnClickListener {
            signOut()
        }

    }

    private fun getMahasiswaData() {
        firebaseAuth = FirebaseAuth.getInstance()
        val user =firebaseAuth.currentUser
        val emaill= user?.email
        mhsRView.visibility=View.GONE
        dbRef = FirebaseDatabase.getInstance("https://connectcrud-7a206-default-rtdb.asia-southeast1.firebasedatabase.app/")
            .getReference("Mahasiswa")
        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                mhslist.clear()
                if (snapshot.exists()){
                    for (mhsSnap in snapshot.children){
                        val mhsData = mhsSnap.getValue(Mahasiswa::class.java)
                        mhslist.add(mhsData!!)
                    }
                    val mAdapter = mhsAdapter(mhslist)
                    mhsRView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : mhsAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            if(mhslist[position].email == emaill){
                                val intent = Intent(this@MainMenu,Edit::class.java)

                                //put extras
                                intent.putExtra("nim",mhslist[position].nim)
                                intent.putExtra("fullname",mhslist[position].fullname)
                                intent.putExtra("prodi",mhslist[position].prodi)
                                intent.putExtra("phone",mhslist[position].phone)
                                intent.putExtra("email",mhslist[position].email)
                                intent.putExtra("password",mhslist[position].password)
                                startActivity(intent)
                            }else {
                                Toast.makeText(this@MainMenu, "Data Privacy",Toast.LENGTH_LONG).show()
                            }

                        }

                    })

                    mhsRView.visibility = View.VISIBLE

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
    private fun signOut() {
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signOut()
        Intent(this, SignIn::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(it)
        }
    }
}