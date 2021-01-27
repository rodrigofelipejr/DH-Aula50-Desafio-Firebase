package com.house.digital.views.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import  com.house.digital.R
import com.house.digital.entities.Game
import com.house.digital.views.ui.CadastroGameActivity
import com.squareup.picasso.Picasso

class GameAdapter(val listGames: ArrayList<Game>, val context: Context) :
    RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GameViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        return GameViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val currentItem = listGames[position]
        holder.title.text = currentItem.title
        holder.createdAt.text = currentItem.createdAt

        Picasso.get().load(currentItem.urlCover)
            .into(holder.urlCover)

        holder.urlCover.setOnClickListener {
            context.startActivity(
                Intent(it.context, CadastroGameActivity::class.java)
                    .putExtra("title", currentItem.title)
                    .putExtra("createdAt", currentItem.createdAt)
                    .putExtra("description", currentItem.description)
                    .putExtra("urlCover", currentItem.urlCover)
            )
        }
    }

    override fun getItemCount(): Int {
        return listGames.size
    }

    class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.textView_title)
        var createdAt: TextView = itemView.findViewById(R.id.textView_createdAt)
        var urlCover: ImageView = itemView.findViewById(R.id.imageView)
    }
}

