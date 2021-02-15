package disiiy.khaper.newsapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import disiiy.khaper.newsapp.model.ArticlesItem
import disiiy.khaper.newsapp.model.ResponseNews
import kotlinx.android.synthetic.main.news_item.view.*

class NewsAdapter (var context : Context, var listNews : List<ArticlesItem?>?):
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    class ViewHolder (view : View) :RecyclerView.ViewHolder(view){
        fun bind (news: ArticlesItem){
            with(itemView){
                tv_title_item.text = news.title
                tv_date_item.text = news.publishedAt
                Glide.with(context).load(news.urlToImage).centerCrop().into(iv_item_news)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =  LayoutInflater.from(context).inflate(R.layout.news_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listNews!!.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(listNews?.get(position)!!)

}