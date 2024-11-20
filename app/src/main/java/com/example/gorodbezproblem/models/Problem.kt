package com.example.gorodbezproblem.models

data class Problem(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val specificlocation: String = "",
    val category: String = "cleaning",
    val voteCount: Int = 1,
    val media: List<String> = emptyList(), // Храним ссылки в виде строк
    val status: String = "AWAITING",
    val lat: String = "55.765903",
    val long: String = "37.685013",
)

