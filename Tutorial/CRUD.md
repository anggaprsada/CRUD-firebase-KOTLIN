# CRUD
CRUD itu sendiri adalah Create, Read, Update, dan Delete. hal ini merujuk dalam pengolahan dari database tersebut.<br/>

Terlebih dahulu kalian pastikan database sudah terdaftar pada firebase:
- Buka [Konsol Firebase](https://console.firebase.google.com/u/0/), pilih project yang sudah terdaftar dengan aplikasi. Pilih Builid  > Realtime Database > Create Database

- Pada Database options pilih <code translate="no" dir="ltr">Singapore(asia-southeast1)</code> > Next

- Pada Security Rules pilih <code translate="no" dir="ltr">Start in locked mode</code> > Enable

- Pilih Rules, Edi rules menjadi :
```
{
  "rules": {
    ".read":true,
    ".write": true
  }
}
```
- Kemudian Publish

## Create
Dalam proses ini berkaitan dengan proses [Sign Up](https://github.com/anggaprsada/crud-firebase-kotlin/blob/main/Tutorial/Authentication.md) dimana ketika meng-inputkan data yang nantinya akan dituliskan kedalam <code translate="no" dir="ltr">Realtime Database Firebase</code><br/>
proses ini berada pada <code translate="no" dir="ltr">SignUp.kt</code> 
Inisiaskan terlebih Dahulu didalam <code translate="no" dir="ltr">Realtime Database Firebase</code> untuk Database nya
```kotlin
class SignUp : AppCompatActivity() {
    //...
    private lateinit var dbRef : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dbRef = FirebaseDatabase
            .getInstance("https://connectcrud-7a206-default-rtdb.asia-southeast1.firebasedatabase.app/")
            .getReference("Mahasiswa")
            //...
     }
}
```
            
## Read

## Update

## Delete


<code translate="no" dir="ltr">SignUp.kt</code>
