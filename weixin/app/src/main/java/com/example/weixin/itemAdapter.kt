package com.example.weixin
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class itemAdapter(val items:ArrayList<item>) : RecyclerView.Adapter<itemAdapter.itemViewHolder>() {
    inner class itemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.name)
        val desc = itemView.findViewById<TextView>(R.id.description)
        val img =  itemView.findViewById<ImageView>(R.id.avatar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_list,parent,false)
        val holder=itemViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: itemViewHolder, position: Int) {
        val item=items[position]
        holder.name.text=item.name
        holder.desc.text=item.description
        holder.img.setImageResource(item.avatar)

    }

    override fun getItemCount(): Int {
        return items.size
    }


}