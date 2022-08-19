# Authentication
Membuat sistem Sign in, Sign Up dan Sign Out

## Sign Up
Membuat pengguna baru di project Firebase dengan memanggil metode <code translate="no" dir="ltr">createUserWithEmailAndPassword</code>. membuat pengguna baru yang diautentikasi dengan sandi dari bagian Authentication pada [Firebase console](https://console.firebase.google.com/u/0/) di halaman Users.<br/>
kode ini berada di  <code translate="no" dir="ltr">SignUp.kt</code> <br/>
inisiasikan terlebih dahulu
```kotlin
class SignUp : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    //...

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val email = binding.etEmail.text.toString()
        val pass = binding.etPassword.text.toString()
        //...
    }
}
```
kode ini berada di  <code translate="no" dir="ltr">SignUp.kt</code> di dalam button  <code translate="no" dir="ltr">setClickListener</code>
```kotlin

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
```

Intent tersebut ketika terdaftar akan merujuk langsung ke Main menu
```kotlin
Intent(this, SignIn::class.java).also {
  it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
  startActivity(it)
  Toast.makeText(this, "Success Sign Up", Toast.LENGTH_LONG).show()
}
```

## Sign In

## Sign Out

