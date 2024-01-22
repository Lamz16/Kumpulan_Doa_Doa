package com.lamz.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.lamz.core.R
import com.lamz.core.databinding.ListItemDoaBinding
import com.lamz.core.domain.model.KumpulanDoaDoa

@Suppress("unused")
class ListKumpulanDoaAdapter(private val onFavoriteClick : (KumpulanDoaDoa)->Unit) : RecyclerView.Adapter<ListKumpulanDoaAdapter.ListViewHolder>() {


    private var listData = ArrayList<KumpulanDoaDoa>()
    var onItemClick :((KumpulanDoaDoa) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<KumpulanDoaDoa>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val binding = ListItemDoaBinding.bind(itemView)

        fun bind(data : KumpulanDoaDoa){
            with(binding){
                tvJudul.text = data.doa
                tvDoa.text= data.ayat
            }
        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_doa, parent, false))

    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)

        val fab = holder.binding.fab
        if (data.favorite){
            fab.setImageDrawable(ContextCompat.getDrawable(fab.context, R.drawable.ic_bookmarked))
        }else{
            fab.setImageDrawable(ContextCompat.getDrawable(fab.context, R.drawable.ic_bookmark))
        }
        fab.setOnClickListener {
            notifyItemChanged(position)
            onFavoriteClick(data)
        }
    }

}