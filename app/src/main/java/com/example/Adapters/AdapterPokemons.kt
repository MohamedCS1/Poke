package com.example.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pojo.Pokemon
import com.example.poke.R

class AdapterPokemons:RecyclerView.Adapter<AdapterPokemons.PokemonViewHolder>() {

    var arrayofPokemons = arrayListOf<Pokemon>()
    lateinit var context:Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        context = parent.context
        return PokemonViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_pokemon,null ,false))
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.name.text = arrayofPokemons[position].name
        Glide.with(context).load(arrayofPokemons[position].url).into(holder.image)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    class PokemonViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)
    {
        val image = itemView.findViewById<ImageView>(R.id.imgeview_pokemon)
        val name = itemView.findViewById<TextView>(R.id.tv_name_pokemon)
    }

    fun setList(list:ArrayList<Pokemon>)
    {
        this.arrayofPokemons = list
    }
}