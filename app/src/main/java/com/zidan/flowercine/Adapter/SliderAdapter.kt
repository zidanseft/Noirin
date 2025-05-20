package com.zidan.flowercine.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.zidan.flowercine.Models.SliderItems
import com.zidan.flowercine.databinding.ViewholderSliderBinding

class SliderAdapter(private val sliderItems: MutableList<SliderItems>
,private val sliderPager2: ViewPager2
):RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {
    private var context: Context? = null
    private val runnable = Runnable {
        sliderItems.addAll(sliderItems)
        notifyDataSetChanged()
    }

    inner class SliderViewHolder(private val binding: ViewholderSliderBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(sliderItems: SliderItems){
                val requestOptions= RequestOptions().transform(CenterCrop(), RoundedCorners(60))
                context?.let{
                    Glide.with(it)
                        .load(sliderItems.image)
                        .apply {requestOptions}
                        .into(binding.imageSlide)
                }
            }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SliderAdapter.SliderViewHolder {
        context=parent.context
        val binding=ViewholderSliderBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SliderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SliderAdapter.SliderViewHolder, position: Int) {
        holder.bind(sliderItems[position])
        if(position==sliderItems.size-2){
            sliderPager2.post(runnable)
        }
    }

    override fun getItemCount(): Int =sliderItems.size
}