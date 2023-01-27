package com.example.citylist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.citylist.databinding.CityItemBinding
import com.example.database.model.CityEntity
import com.example.model.domain.city.CoordModel

class CityAdapter(private val onClick: (CoordModel?) -> Unit) : PagingDataAdapter<CityEntity,
    CityAdapter.MainViewHolder>(
    DIFF_CALLBACK
) {
    private val options: RequestOptions = RequestOptions()
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)

    inner class MainViewHolder(val binding: CityItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            CityItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = getItem(position)

        holder.binding.apply {
            Glide.with(this.root.context)
                .load(item?.imageUrl ?: "")
                .apply(options)
                .into(image)
            name.text = item?.name ?: ""

            root.setOnClickListener {
                onClick(item?.coord)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CityEntity>() {
            override fun areItemsTheSame(oldItem: CityEntity, newItem: CityEntity): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: CityEntity, newItem: CityEntity): Boolean =
                oldItem == newItem
        }
    }
}
