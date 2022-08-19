## Read
Hal pertama yang harus dilakukan adalah membuat Activity <code translate="no" dir="ltr">MainMenu.kt</code> untuk menampilkan Data melalui Recycle View,<code translate="no" dir="ltr">Adapter</code> Untuk koneksikan database dengan aplikasi, <code translate="no" dir="ltr">Layout List</code> untuk data yang ditampilkan didalam Recycle View.</br>

- <code translate="no" dir="ltr">Adapter</code>
```kotlin
class mhsAdapter(private val mhsList: ArrayList<Mahasiswa>):
    RecyclerView.Adapter<mhsAdapter.ViewHolder>(){

    private  lateinit var mListener : onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.mhs_list,parent,false)
        return ViewHolder(inflater, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentMhs = mhsList[position]
        holder.nim.text = currentMhs.nim
        holder.fullname.text = currentMhs.fullname
        holder.prodi.text = currentMhs.prodi
        holder.email.text = currentMhs.email
    }

    override fun getItemCount(): Int {
        return mhsList.size
    }

    class ViewHolder(inflater: View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(inflater) {
        val nim : TextView = itemView.findViewById(R.id.tvNim)
        val fullname : TextView = itemView.findViewById(R.id.tvFn)
        val prodi : TextView = itemView.findViewById(R.id.tvProdi)
        val email : TextView = itemView.findViewById(R.id.tvEmail)

        init {
            inflater.setOnClickListener{
                clickListener.onItemClick(bindingAdapterPosition)
            }
        }
    }

}
```
- <code translate="no" dir="ltr">Layout List</code>
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_margin="10dp"
    android:orientation="vertical">
    <androidx.cardview.widget.CardView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:cardCornerRadius="20dp"
        android:layout_margin="10dp"
        android:backgroundTint="@color/teal_700">
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="vertical"
            android:layout_margin="10dp">
            <TextView
                android:id="@+id/tvNim"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="NIM"
                android:textStyle="bold"
                android:textColor="@color/white"
                android:textSize="20dp"
                android:layout_marginHorizontal="10dp"/>

            <TextView
                android:id="@+id/tvFn"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Nama"
                android:textColor="@color/white"
                android:textSize="20dp"
                android:layout_marginHorizontal="10dp"/>

            <TextView
                android:id="@+id/tvProdi"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Prodi"
                android:textColor="@color/white"
                android:textSize="20dp"
                android:layout_marginHorizontal="10dp"/>

            <TextView
                android:id="@+id/tvEmail"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Email"
                android:textSize="20dp"
                android:textColor="@color/white"
                android:textStyle="italic"
                android:layout_marginHorizontal="10dp"/>


        </LinearLayout>

    </androidx.cardview.widget.CardView>


</LinearLayout>
```
- <code translate="no" dir="ltr">MainMenu.kt</code>
```kotlin
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
        //...
    }
}
```
