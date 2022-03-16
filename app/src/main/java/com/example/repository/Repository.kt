package com.example.repository

import com.example.network.PokemonApiService
import com.example.pojo.PokemonResponse
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class Repository {
     var pokemonApiService:PokemonApiService
     constructor(pokemonApiService: PokemonApiService)
     {
         this.pokemonApiService = pokemonApiService
     }

    @Inject
    fun getPokemons():Observable<PokemonResponse>
    {
        return pokemonApiService.getPokemons()
    }
}