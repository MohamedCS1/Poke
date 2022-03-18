package com.example.poke

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.Adapters.AdapterPokemons
import com.example.pojo.Pokemon
import com.example.viewmodels.PokemonsViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var adapter:AdapterPokemons

    lateinit var viewModel:PokemonsViewModel

    lateinit var rv:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv = findViewById(R.id.rv_pokyemon)

        adapter = AdapterPokemons(this)

        viewModel = ViewModelProvider(this)[PokemonsViewModel::class.java]

        rv.adapter = adapter

        setupSwipe()

        rv.layoutManager = LinearLayoutManager(this)

        viewModel.getPokemons()

        viewModel.getPokemonList().observe(this ,object :Observer<ArrayList<Pokemon>>{
            override fun onChanged(t: ArrayList<Pokemon>?) {
                adapter.setList(t!!)
            }
        })

        val bu_to_fav = findViewById<FloatingActionButton>(R.id.bu_to_fav).setOnClickListener {
            startActivity(Intent(this ,FavActivity::class.java))
        }
    }

    fun setupSwipe()
    {
        val callback:ItemTouchHelper.SimpleCallback = object :ItemTouchHelper.SimpleCallback(0 ,ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val swipedPokemon = adapter.getPokemonAt(position)
                viewModel.InsertPokemon(swipedPokemon)
                adapter.notifyDataSetChanged()
                Toast.makeText(this@MainActivity ,"Pokemon added to database" ,Toast.LENGTH_LONG).show()
            }
        }

        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rv)
    }
}