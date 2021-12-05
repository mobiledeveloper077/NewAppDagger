package uz.abduvali.newappdagger.models

data class NewData(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)