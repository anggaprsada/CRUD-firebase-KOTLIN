# Authentication
Membuat sistem Sign in, Sign Up dan Sign Out

## Sign Up
Anda membuat pengguna baru di project Firebase dengan memanggil metode <code translate="no" dir="ltr">createUserWithEmailAndPassword</code>. membuat pengguna baru yang diautentikasi dengan sandi dari bagian Authentication pada [Firebase console](https://console.firebase.google.com/u/0/) di halaman Users.<br/>

kode ini berada di SignUp.kt
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


