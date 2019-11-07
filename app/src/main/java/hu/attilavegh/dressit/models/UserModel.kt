package hu.attilavegh.dressit.models

import java.io.Serializable


data class UserModel(
    val id: String = "",
    val name: String = "",
    val gender: String = "",
    val size: String = "",
    val height: Number = 0,
    val weight: Number = 0,
    val chest: Number = 0,
    val waist: Number = 0,
    val hips: Number = 0,
    val sleeves: Number = 0
) {
    fun getUserHashMap(): HashMap<String, Serializable> {
        return hashMapOf(
            "id" to id,
            "name" to name,
            "gender" to gender,
            "size" to size,
            "height" to height,
            "weight" to weight,
            "chest" to chest,
            "waist" to waist,
            "hips" to hips,
            "sleeves" to sleeves
        )
    }
}