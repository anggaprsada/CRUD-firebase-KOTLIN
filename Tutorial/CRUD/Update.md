## Update

Kode Untuk meng Update
```kotlin
val nim : String = binding.etNim.text.toString().trim()
        val fullname : String = binding.etFn.text.toString().trim()
        val prodi : String = binding.etProdi.text.toString().trim()
        val phone : String = binding.etPhone.text.toString().trim()
        val email : String = binding.etEmail.text.toString().trim()
        val pass : String = binding.etPassword.text.toString().trim()
dbRef = FirebaseDatabase.getInstance("https://connectcrud-7a206-default-rtdb.asia-southeast1.firebasedatabase.app/")
            .getReference("Mahasiswa")

        val mhs = Mahasiswa(nim, fullname, prodi, phone, email, pass)

        dbRef.child(nim).setValue(mhs)
            .addOnCompleteListener {
                
            }
            .addOnFailureListener {
            }
```
Hal pertama yang harus dilakukan adalah membuat :
- Activity <code translate="no" dir="ltr">Edit.kt</code> untuk melihat data profile secara menyeluruh., yang sudah terbuat di [READ](https://github.com/anggaprsada/crud-firebase-kotlin/edit/main/Tutorial/CRUD/Read.md).
- Activity <code translate="no" dir="ltr">Update.kt</code> untuk menginputkan data yang diubah. 

Kode :
- Layout <code translate="no" dir="ltr">activity_update.xml</code>
```
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".activity.SignUp">
    <ScrollView
        android:layout_width="match_parent"
        android:layout_height="match_parent">
        <RelativeLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent">
            <View
                android:layout_width="match_parent"
                android:layout_height="210dp"
                android:layout_alignParentStart="true"
                android:layout_alignParentTop="true"
                android:layout_alignParentEnd="true"
                android:background="@drawable/gradient" />

            <TextView
                android:id="@+id/btnBack"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_alignParentStart="true"
                android:layout_alignParentTop="true"
                android:layout_marginStart="25dp"
                android:layout_marginTop="25dp"
                android:backgroundTint="@color/white"
                android:clickable="true"
                android:drawableStart="@drawable/ic_baseline_arrow_back_ios_new_24"
                android:gravity="center"
                android:minHeight="48dp"
                android:text=" Back"
                android:textColor="@color/white"
                android:textSize="20sp"
                android:textStyle="bold" />

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Update Account"
                android:textColor="@color/white"
                android:textSize="25sp"
                android:layout_marginStart="30dp"
                android:layout_marginTop="150dp"
                android:layout_alignParentStart="true"/>

            <ImageView
                android:id="@+id/imageView4"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_alignParentTop="true"
                android:layout_alignParentEnd="true"
                app:srcCompat="@drawable/sign_up_pola" />

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_below="@+id/imageView4"
                android:orientation="vertical"
                android:paddingBottom="30dp">

                <com.google.android.material.textfield.TextInputLayout
                    style="@style/Widget.MaterialComponents.TextInputLayout.OutlinedBox"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:layout_marginHorizontal="20dp"
                    android:layout_marginTop="30dp"
                    app:endIconMode="clear_text"
                    app:startIconDrawable="@drawable/ic_baseline_vpn_key_24">

                    <com.google.android.material.textfield.TextInputEditText
                        android:id="@+id/etNim"
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        android:hint="NIM"
                        android:maxLength="10"
                        android:inputType="number"/>
                </com.google.android.material.textfield.TextInputLayout>

                <com.google.android.material.textfield.TextInputLayout
                    style="@style/Widget.MaterialComponents.TextInputLayout.OutlinedBox"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:layout_marginHorizontal="20dp"
                    android:layout_marginTop="20dp"
                    android:hint="Fullname"
                    app:endIconMode="clear_text"
                    app:startIconDrawable="@drawable/icon_feather_user">

                    <com.google.android.material.textfield.TextInputEditText
                        android:id="@+id/etFn"
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        android:maxLength="30"
                        android:inputType="textPersonName"/>
                </com.google.android.material.textfield.TextInputLayout>

                <com.google.android.material.textfield.TextInputLayout
                    style="@style/Widget.MaterialComponents.TextInputLayout.OutlinedBox"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:layout_marginHorizontal="20dp"
                    android:layout_marginTop="20dp"
                    android:hint="Prodi"
                    app:endIconMode="clear_text"
                    app:startIconDrawable="@drawable/ic_key">

                    <com.google.android.material.textfield.TextInputEditText
                        android:id="@+id/etProdi"
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content" />
                </com.google.android.material.textfield.TextInputLayout>

                <com.google.android.material.textfield.TextInputLayout
                    style="@style/Widget.MaterialComponents.TextInputLayout.OutlinedBox"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:layout_marginHorizontal="20dp"
                    android:layout_marginTop="20dp"
                    android:hint="Phone Number"
                    app:endIconMode="clear_text"
                    app:startIconDrawable="@drawable/ic_phone">

                    <com.google.android.material.textfield.TextInputEditText
                        android:id="@+id/etPhone"
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        android:maxLength="13"
                        android:inputType="number"/>
                </com.google.android.material.textfield.TextInputLayout>

                <com.google.android.material.textfield.TextInputLayout
                    style="@style/Widget.MaterialComponents.TextInputLayout.OutlinedBox"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:layout_marginHorizontal="20dp"
                    android:layout_marginTop="20dp"
                    app:endIconMode="clear_text"
                    app:startIconDrawable="@drawable/icon_feather_mail">

                    <com.google.android.material.textfield.TextInputEditText
                        android:id="@+id/etEmail"
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        android:text="Email"
                        android:focusable="false"
                        android:inputType="textEmailAddress" />
                </com.google.android.material.textfield.TextInputLayout>

                <com.google.android.material.textfield.TextInputLayout
                    style="@style/Widget.MaterialComponents.TextInputLayout.OutlinedBox"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:layout_marginHorizontal="20dp"
                    android:layout_marginTop="20dp"
                    app:endIconMode="password_toggle"
                    app:startIconDrawable="@drawable/icon_feather_lock">

                    <com.google.android.material.textfield.TextInputEditText
                        android:id="@+id/etPassword"
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        android:text="Password"
                        android:focusable="false"
                        android:inputType="textPassword" />
                </com.google.android.material.textfield.TextInputLayout>

                <Button
                    android:id="@+id/btnUpdate"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:layout_marginHorizontal="20dp"
                    android:layout_marginTop="20dp"
                    android:backgroundTint="@color/teal_700"
                    android:text="UPDATE"
                    tools:ignore="TouchTargetSizeCheck" />

            </LinearLayout>
        </RelativeLayout>
    </ScrollView>
</RelativeLayout>
```
- Activity <code translate="no" dir="ltr">Update.kt</code>
```kotlin
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
``` 
</br>
Setting data  di Edit Text agar terisi Otomatis
```kotlin
private fun setValue() {
       binding.etNim.setText(intent.getStringExtra("nim"))
       binding.etFn.setText(intent.getStringExtra("fullname"))
       binding.etProdi.setText(intent.getStringExtra("prodi"))
       binding.etPhone.setText(intent.getStringExtra("phone"))
       binding.etEmail.setText(intent.getStringExtra("email"))
       binding.etPassword.setText(intent.getStringExtra("password"))
}
```

