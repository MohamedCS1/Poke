package com.example.poke

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.Adapters.AdapterPokemons
import com.example.pojo.Pokemon
import com.example.viewmodels.PokemonsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rv = findViewById<RecyclerView>(R.id.rv_pokyemon)

        val adapter = AdapterPokemons(this)

        val viewModel = ViewModelProvider(this)[PokemonsViewModel::class.java]

        rv.adapter = adapter

        rv.layoutManager = LinearLayoutManager(this)

        viewModel.getPokemons()

        viewModel.getPokemonList().observe(this ,object :Observer<ArrayList<Pokemon>>{
            override fun onChanged(t: ArrayList<Pokemon>?) {
                adapter.setList(t!!)
            }
        })
    }
}