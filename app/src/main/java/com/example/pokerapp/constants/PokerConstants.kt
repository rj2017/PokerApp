package com.example.pokerapp.constants

class PokeConstants private constructor(){
    object SHARED{
        const val name = "nome"
    }

    object HEADER{
        const val PERSON_KEY = "personkey"
        const val TOKEN_KEY = "token"
    }

    object HTTP{
        const val SUCCESS = 200
    }

    object PUTEXTRAS{
        const val ID_POKEMON = "id_pokemon"
        const val IMAGE = "img_pokemon"
        const val NAME = "name_pokemon"
        const val TYPE = "tipo"
        const val STATS = "stats"
        const val ABILITIES = "abilities"
    }
}