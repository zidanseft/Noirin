package com.zidan.flowercine.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.zidan.flowercine.Models.Film
import com.zidan.flowercine.databinding.ViewholderFilmBinding


class FilmListAdapter(private val items: ArrayList<Film>): RecyclerView.Adapter<FilmListAdapter.ViewHolder>() {
    private val context: Context? = null
    inner class ViewHolder(private val binding: ViewholderFilmBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(film: Film){
            binding.nameTxt.text=film.Title
            val requestOptions = RequestOptions().transform(CenterCrop(), RoundedCorners(30))

            Glide.with(binding.root.context)
                .load(film.Poster)
                .apply(requestOptions)
                .into(binding.pic)

            binding.root.setOnClickListener {
//                val intent = Intent(context, DetailActivity::class.java)
//                intent.putExtra("film", film)
//                context.startActivity(intent)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmListAdapter.ViewHolder{

        val binding = ViewholderFilmBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmListAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int =items.size
}