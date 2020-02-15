package com.model

/**
 * Photo album data class.
 *
 * @author Alexander Naumov.
 */
data class Album (
    var id: String = "",
    var name:String = "",
    var size:Int = 0,
    var photos:ArrayList<Photo> = arrayListOf()
)