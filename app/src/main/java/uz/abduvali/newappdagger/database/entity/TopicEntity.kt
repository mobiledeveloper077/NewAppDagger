package uz.abduvali.newappdagger.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TopicEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    var isSelected: Boolean = false
)