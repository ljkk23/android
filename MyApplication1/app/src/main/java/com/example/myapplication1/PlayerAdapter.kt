package com.example.myapplication1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PlayerAdapter(val players:ArrayList<player>) : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {
    inner class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.name)
        val desc = itemView.findViewById<TextView>(R.id.description)
        val img =  itemView.findViewById<ImageView>(R.id.avatar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_player,parent,false)
        val holder=PlayerViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player=players[position]
        holder.name.text=player.name
        holder.desc.text=player.description
        holder.img.setImageResource(player.avatar)

    }

    override fun getItemCount(): Int {
        return players.size
    }


}