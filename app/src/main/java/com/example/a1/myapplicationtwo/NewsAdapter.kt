package com.example.a1.myapplicationtwo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_new.view.*

/**
 * Created by 1 on 03.07.2017.
 */
class NewsAdapter : RecyclerView.Adapter<NewsAdapter.Holder>() {
    var mData: List<NewsActivity.New> = emptyList()  //список новостей
    fun setData(data: List<NewsActivity.New>){
    mData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {//создаёт ячейку
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_new, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {  //заполняет содержимым
    val new = mData[position]
        holder.title.text = new.title
        holder.text.text = new.text


        Glide.with(holder.itemView.context)
                .load(new.image)
                .into(holder.image)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {

    val title: TextView = view.title
    val text: TextView = view.text
        val image:ImageView = view.image
    }
}

