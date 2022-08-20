## Delete
Pada tahap delete ini juga akan menghapus akun dari [Authentication](https://github.com/anggaprsada/crud-firebase-kotlin/blob/main/Tutorial/Authentication.md)</br>
Kode untuk Delete
```kotlin
dbRef = FirebaseDatabase.getInstance("https://connectcrud-7a206-default-rtdb.asia-southeast1.firebasedatabase.app/")
            .getReference("Mahasiswa").child(nim!!)
dbRef.removeValue()
            .addOnCompleteListener {
            //...
            }
            .addOnFailureListener {
            //...
            }
```

Hal pertama yang harus dilakukan adalah membuat Activity <code translate="no" dir="ltr">Edit.kt.kt</code>  untuk melihat data profile secara menyeluruh, yang sudah dibuat di [Edit](https://github.com/anggaprsada/crud-firebase-kotlin/blob/main/Tutorial/CRUD/Read.md).

Inisiasikan terlebih dahulu 
```kotlin
class Edit : AppCompatActivity() {

    lateinit var binding : ActivityEditBinding
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var dbRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        //...
    }
}
```
Membuat Alert Dialog agar ketika button diklik tidak langsung terhapus tetapi memberi kepastian akun dan data tersebut dihapus atau tidak
```kotlin
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
```
Ketika Setuju akun tersebut dihapus maka akan diarahkan ke private fun deleteData(nim)
```kotlin
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
            } else{
                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_LONG).show()
            }
        }   
        .addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
        }
}
```
