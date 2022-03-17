package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pojo.Pokemon

@Database(entities = [Pokemon::class] , version = 1 , exportSchema = false)
 abstract class PokemonDataBase:RoomDatabase() {
     abstract fun pokemonDao():PokemonDao
}