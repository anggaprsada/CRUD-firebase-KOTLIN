# Create
Dalam proses ini berkaitan dengan proses [Sign Up](https://github.com/anggaprsada/crud-firebase-kotlin/blob/main/Tutorial/Authentication.md) dimana ketika meng-inputkan data yang nantinya akan dituliskan kedalam <code translate="no" dir="ltr">Realtime Database Firebase</code><br/>
proses ini berada pada <code translate="no" dir="ltr">SignUp.kt</code> 
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
            //...
     }
}
```
Karena kita menggunakan database <code translate="no" dir="ltr">Singapore<code> pada get Instance tersebut diinputkan link yang berada Database yang berada di Firebase Konsol.<br/>
  

## Read

## Update

## Delete


<code translate="no" dir="ltr">SignUp.kt</code>
