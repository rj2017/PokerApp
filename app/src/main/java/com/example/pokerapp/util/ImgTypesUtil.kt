package com.example.pokerapp.util


import com.example.pokerapp.R

object ImgTypesUtil{

    fun getIdImageForType(type : String) : Int{
        val returnId : Int
        when(type){
            "bug" -> returnId = R.drawable.ic_bug
            "dark" -> returnId = R.drawable.ic_dark
            "dragon" -> returnId = R.drawable.ic_dragon
            "electric" -> returnId = R.drawable.ic_electric
            "fairy" -> returnId = R.drawable.ic_fairy
            "fighting" -> returnId = R.drawable.ic_fighting
            "fire" -> returnId = R.drawable.ic_fire
            "flying" -> returnId = R.drawable.ic_flying
            "ghost" -> returnId = R.drawable.ic_ghost
            "grass" -> returnId = R.drawable.ic_grass
            "ground" -> returnId = R.drawable.ic_ground
            "ice" -> returnId = R.drawable.ic_ice
            "iron" -> returnId = R.drawable.ic_iron
            "steel" -> returnId = R.drawable.ic_iron
            "normal" -> returnId = R.drawable.ic_normal
            "poison" -> returnId = R.drawable.ic_poison
            "psychic" -> returnId = R.drawable.ic_psychic
            "rock" -> returnId = R.drawable.ic_rock
            "water" -> returnId = R.drawable.ic_water
            else -> returnId = R.drawable.ic_normal
        }
        return returnId
    }

    fun getColorForType(type: String) : Int{
        val returnId : Int

        when(type){
            "bug" -> returnId = R.color.bug
            "dark" -> returnId = R.color.dark
            "dragon" -> returnId = R.color.dragon
            "electric" -> returnId = R.color.electric
            "fairy" -> returnId = R.color.fairy
            "fighting" -> returnId = R.color.fighting
            "fire" -> returnId = R.color.fire
            "steer" -> returnId = R.color.iron
            "flying" -> returnId = R.color.flying
            "ghost" -> returnId = R.color.ghost
            "grass" -> returnId = R.color.grass
            "ground" -> returnId = R.color.ground
            "ice" -> returnId = R.color.ice
            "iron" -> returnId = R.color.iron
            "normal" -> returnId = R.color.normal
            "poison" -> returnId = R.color.poison
            "psychic" -> returnId = R.color.psychic
            "rock" -> returnId = R.color.rock
            "water" -> returnId = R.color.water
            else -> returnId = R.color.normal
        }

        return returnId
    }

    fun getThemeForType(type: String) : Int{
        val returnTheme : Int

        when(type){
            "bug" -> returnTheme = R.style.bug
            "dark" -> returnTheme = R.style.dark
            "dragon" -> returnTheme = R.style.dragon
            "electric" -> returnTheme = R.style.electric
            "fairy" -> returnTheme = R.style.fairy
            "fighting" -> returnTheme = R.style.fighting
            "fire" -> returnTheme = R.style.fire
            "steer" -> returnTheme = R.style.iron
            "flying" -> returnTheme = R.style.flying
            "ghost" -> returnTheme = R.style.ghost
            "grass" -> returnTheme = R.style.grass
            "ground" -> returnTheme = R.style.ground
            "ice" -> returnTheme = R.style.ice
            "iron" -> returnTheme = R.style.iron
            "normal" -> returnTheme = R.style.normal
            "poison" -> returnTheme = R.style.poison
            "psychic" -> returnTheme = R.style.psychic
            "rock" -> returnTheme = R.style.rock
            "water" -> returnTheme = R.style.water
            else -> returnTheme = R.style.normal
        }

        return returnTheme
    }

}