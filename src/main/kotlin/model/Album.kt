package model

/**
 * Photo album data class.
 *
 * @author Alexander Naumov.
 */
data class Album (
    var name:String = "",
    var size:Int = 0,
    val photos:List<Photo> = listOf()
)