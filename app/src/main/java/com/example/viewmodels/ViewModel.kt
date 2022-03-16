package com.example.viewmodels


import android.annotation.SuppressLint
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pojo.Pokemon
import com.example.pojo.PokemonResponse
import com.example.repository.Repository
import io.reactivex.Scheduler
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.functions.Function
import io.reactivex.rxjava3.schedulers.Schedulers

class ViewModel:ViewModel {

    lateinit var repository:Repository

    private val pokemonList:MutableLiveData<ArrayList<Pokemon>> = MutableLiveData()

    @ViewModelInject
    constructor(repository: Repository)
    {
        this.repository = repository
    }

    fun getPokemonList():MutableLiveData<ArrayList<Pokemon>>
    {
        return pokemonList
    }

    @SuppressLint("CheckResult")
    fun getPokemons()
    {
        repository.getPokemons()
            .subscribeOn(Schedulers.io())
            .map(object :Function<PokemonResponse ,ArrayList<Pokemon>>{
                override fun apply(t: PokemonResponse): ArrayList<Pokemon> {
                    val list = t.result
                    for (pokemon in list)
                    {
                        val url = pokemon.url
                        val pokemonIndex = url.split("/")
                        pokemon.url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemonIndex[pokemonIndex.size-1]}.png"
                    }
                    return list
                }

            }).observeOn(AndroidSchedulers.mainThread()).subscribe{result-> pokemonList.value = result}

    }
}