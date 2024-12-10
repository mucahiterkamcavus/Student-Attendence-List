package com.recylerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.Lessons
import com.example.studentattendencelist.R

data class TeacherLessonDataAdapter(val data : ArrayList<Lessons>) : RecyclerView.Adapter<TeacherLessonViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherLessonViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(
            R.layout.teacher_lessons_item,
            parent,false)
        val viewHolder = TeacherLessonViewHolder(layout)
        return viewHolder
    }

    override fun onBindViewHolder(holder: TeacherLessonViewHolder, position: Int) {
        holder.bindData(data[position])
    }

    override fun getItemCount(): Int {
        return data.size

    }
}

