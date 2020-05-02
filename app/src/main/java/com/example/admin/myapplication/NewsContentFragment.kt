package com.example.admin.myapplication

import android.opengl.Visibility
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.news_content_frag.*

class NewsContentFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.news_content_frag, container, false)
    }

    fun refresh(title: String, content: String){
        contentLayout.visibility = View.VISIBLE
        newsTitle.text = title
        newContent.text = content
    }

}