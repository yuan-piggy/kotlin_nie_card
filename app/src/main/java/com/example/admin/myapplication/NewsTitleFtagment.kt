package com.example.admin.myapplication

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class NewsTitleFtagment: Fragment() {
    private var isTwoPane = false
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.news_title_frag, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isTwoPane = activity?.findViewById<View>(R.id.newsContentLayout) != null
    }

//    inner class NewsAdapter(val newsList:List<News>):RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
//        inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
//            val newsTitle: TextView = view.findViewById(R.id.newsTitle)
//        }
//
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//        }
//
//        override fun getItemCount(): Int {
//            return newsList.size
//        }
//
//        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//           val news = newsList[position]
//            holder.newsTitle.text = news.title
//        }
//
//    }
}