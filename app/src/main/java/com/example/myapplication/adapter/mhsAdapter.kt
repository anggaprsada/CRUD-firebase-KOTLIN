package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Mahasiswa
import com.example.myapplication.R

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