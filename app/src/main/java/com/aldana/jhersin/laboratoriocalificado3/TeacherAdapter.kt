package com.aldana.jhersin.laboratoriocalificado3

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aldana.jhersin.laboratoriocalificado3.databinding.ItemTeacherBinding
import com.bumptech.glide.Glide

class TeacherAdapter(private val context: Context) : RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder>() {

    private var teacherList: List<Teacher> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHolder {
        val binding = ItemTeacherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeacherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeacherViewHolder, position: Int) {
        val teacher = teacherList[position]
        holder.bind(teacher)
    }

    override fun getItemCount(): Int {
        return teacherList.size
    }

    fun updateData(newTeacherList: List<Teacher>) {
        teacherList = newTeacherList
        notifyDataSetChanged()
    }

    inner class TeacherViewHolder(private val binding: ItemTeacherBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(teacher: Teacher) {
            binding.nameTextView.text = teacher.name
            binding.lastNameTextView.text = teacher.lastName
            Glide.with(context)
                .load(teacher.imageUrl)
                .centerCrop()
                .placeholder(R.drawable.placeholder_image)
                .into(binding.photoImageView)

            binding.root.setOnClickListener {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:${teacher.phoneNumber}")
                context.startActivity(intent)
            }

            binding.root.setOnLongClickListener {
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.data = Uri.parse("mailto:${teacher.email}")
                context.startActivity(intent)
                true
            }
        }
    }
}
