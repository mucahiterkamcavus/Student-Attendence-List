package com.recylerview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.Lessons
import com.example.studentattendencelist.R

class TeacherLessonViewHolder(itemView: View) : ViewHolder(itemView) {

        fun bindData(lessons: Lessons){
            val lessonname = itemView.findViewById<TextView>(R.id.lessonName)
            val lessonid = itemView.findViewById<TextView>(R.id.tvLessonid)

            lessonname.text= lessons.name
            lessonid.text=lessons.id
        }
    }

