# Create
Dalam proses ini berkaitan dengan proses [Sign Up](https://github.com/anggaprsada/crud-firebase-kotlin/blob/main/Tutorial/Authentication.md) dimana ketika meng-inputkan data yang nantinya akan dituliskan kedalam <code translate="no" dir="ltr">Realtime Database Firebase</code><br/>
Membuat data class pada Android Studio untuk data yang akan diinputkan, dengan cara membuat file kotlin baru diberi nama <code translate="no" dir="ltr">Mahasiswa</code>
```kotlin
package com.example.myapplication

data class Mahasiswa(
    val nim : String?=null,
    val fullname : String?=null,
    val prodi : String?=null,
    val phone : String?=null,
    val email : String?=null,
    val password : String?=null
)
```
Inisiaskan terlebih Dahulu didalam <code translate="no" dir="ltr">Realtime Database Firebase</code> untuk Database nya
```kotlin
class SignUp : AppCompatActivity() {
    //...
    private lateinit var dbRef : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        //...
        dbRef = FirebaseDatabase
            .getInstance("https://connectcrud-7a206-default-rtdb.asia-southeast1.firebasedatabase.app/")
            .getReference("Mahasiswa")
        binding.btnSU.setOnClickListener{
            saveData()
        }
        //...
     }
     private fun saveData() {
        val nim = binding.etNim.text.toString()
        val fullname = binding.etFn.text.toString()
        val prodi = binding.etProdi.text.toString()
        val phone = binding.etPhone.text.toString()
        val email = binding.etEmail.text.toString()
        val pass = binding.etPassword.text.toString()
        //...
      }
}
```
Karena kita menggunakan database <code translate="no" dir="ltr">Singapore</code> pada get Instance tersebut diinputkan link yang berada Database yang berada di Firebase Konsol.<br/>

Kemudian untuk menginputkan data ke database dengan cara berikut pada <code translate="no" dir="ltr">SignUp.kt</code>
 ```kotlin
 class SignUp : AppCompatActivity() {
        //...
        override fun onCreate(savedInstanceState: Bundle?) {
        //...
        }
        private fun saveData() {
                //...
                val mhs = Mahasiswa(nim,fullname,prodi,phone,email,pass)
                dbRef.child(nim).setValue(mhs)
                    .addOnCompleteListener {
                            //...
                    }
                    .addOnFailureListener { err ->
                            Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
                    }
    }
}
 ```
