package com.house.desafio_firebase.views.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.house.desafio_firebase.R
import com.house.desafio_firebase.entities.Game
import com.house.desafio_firebase.views.ui.CadastroGameActivity
import com.house.desafio_firebase.views.ui.EdicaoGameActivity
import com.squareup.picasso.Picasso

class GameAdapter(var context: Context, var listGames : ArrayList<Game>) : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GameViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        return GameViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val currentGame = listGames[position]

        holder.name.text = currentGame.name
        holder.releaseDate.text = currentGame.releaseDate
        Picasso.get().load(currentGame.imageUrl)
            .into(holder.imageUrl)

        holder.imageUrl.setOnClickListener {
            val intent = Intent(it.context, EdicaoGameActivity::class.java)
            intent.putExtra("game", currentGame)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listGames.size
    }

    class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.textView_name)
        var releaseDate: TextView = itemView.findViewById(R.id.textView_releaseDate)
        var imageUrl: ImageView = itemView.findViewById(R.id.imageView)
    }

    fun add(game: ArrayList<Game>) {
        val positionStart = this.listGames.size
        listGames.addAll(game)
        val itemCount = this.listGames.size
        notifyItemRangeChanged(positionStart, itemCount)
    }
}
