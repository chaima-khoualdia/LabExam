package com.example.chaimakhoualdia.data.repository

import com.example.chaimakhoualdia.R
import com.example.chaimakhoualdia.data.local.entity.Question

/**
 * Seed data for the heritage quiz.
 * Only Roman Heritage is preloaded, exactly as required.
 */
object SeedData {
    const val ROMAN_HERITAGE = "Roman Heritage"
    const val ISLAMIC_HERITAGE = "Islamic Heritage"
    const val PUNIC_PRE_ROMAN = "Punic & Pre-Roman"
    const val MODERN_HERITAGE = "Modern Heritage"
    const val NATURAL_MIXED_SITES = "Natural & Mixed Sites"
    const val CITIES = "Cities"

    val categoryCounts = linkedMapOf(
        ROMAN_HERITAGE to 12,
        ISLAMIC_HERITAGE to 10,
        PUNIC_PRE_ROMAN to 6,
        MODERN_HERITAGE to 8,
        NATURAL_MIXED_SITES to 5,
        CITIES to 10
    )

    val romanHeritageQuestions: List<Question> = listOf(
        Question(
            id = 1,
            category = ROMAN_HERITAGE,
            monument = "El Jem Amphitheater",
            location = "El Jem",
            fact = "What is the name of this amphitheater, the 3rd largest in the world?",
            imageRes = R.drawable.el_jem,
            options = listOf("El Jem", "Carthage", "Dougga", "Bulla Regia"),
            correctAnswer = "El Jem",
            difficulty = "Easy",
            hint = "Located in central Tunisia, often compared to Rome's Colosseum."
        ),
        Question(
            id = 2,
            category = ROMAN_HERITAGE,
            monument = "Dougga Capitol",
            location = "Dougga",
            fact = "Which Roman site is famous for its well-preserved Capitol temple?",
            imageRes = R.drawable.dougga,
            options = listOf("Dougga", "Carthage", "Thuburbo Majus", "Oudna"),
            correctAnswer = "Dougga",
            difficulty = "Easy",
            hint = "UNESCO site known as the best preserved Roman small town in North Africa."
        ),
        Question(
            id = 3,
            category = ROMAN_HERITAGE,
            monument = "Zaghouan Aqueduct",
            location = "Zaghouan",
            fact = "This aqueduct carried water to which city?",
            imageRes = R.drawable.zaghouan,
            options = listOf("Carthage", "Tunis", "Kairouan", "Sousse"),
            correctAnswer = "Carthage",
            difficulty = "Easy",
            hint = "Built under Emperor Hadrian to supply the capital of Roman Africa."
        ),
        Question(
            id = 4,
            category = ROMAN_HERITAGE,
            monument = "Oudna Amphitheater",
            location = "Oudna",
            fact = "Which Roman site is located near modern Tunis?",
            imageRes = R.drawable.oudna,
            options = listOf("Oudna", "El Jem", "Dougga", "Bulla Regia"),
            correctAnswer = "Oudna",
            difficulty = "Easy",
            hint = "Also called Uthina, only 30 km from Tunis."
        ),
        Question(
            id = 5,
            category = ROMAN_HERITAGE,
            monument = "Bacchus Mosaic",
            location = "(various)",
            fact = "This mosaic depicts which Roman god?",
            imageRes = R.drawable.bacchus,
            options = listOf("Bacchus", "Neptune", "Apollo", "Mars"),
            correctAnswer = "Bacchus",
            difficulty = "Easy",
            hint = "God of wine, often shown riding animals like tigers."
        ),
        Question(
            id = 6,
            category = ROMAN_HERITAGE,
            monument = "Dougga Capitol dedication",
            location = "Dougga",
            fact = "The Capitol temple at Dougga was dedicated to which gods?",
            imageRes = R.drawable.dougga,
            options = listOf("Jupiter, Juno, Minerva", "Venus", "Mars", "Neptune"),
            correctAnswer = "Jupiter, Juno, Minerva",
            difficulty = "Medium",
            hint = "Known as the Capitoline Triad in Roman religion."
        ),
        Question(
            id = 7,
            category = ROMAN_HERITAGE,
            monument = "Carthage Antonine Baths",
            location = "Carthage",
            fact = "The Antonine Baths at Carthage were built during which century?",
            imageRes = R.drawable.carthage_baths,
            options = listOf("2nd", "3rd", "4th", "5th"),
            correctAnswer = "2nd",
            difficulty = "Medium",
            hint = "Constructed during the reign of Antoninus Pius."
        ),
        Question(
            id = 8,
            category = ROMAN_HERITAGE,
            monument = "Zaghouan Aqueduct purpose",
            location = "Zaghouan",
            fact = "What was the main purpose of the Zaghouan Aqueduct?",
            imageRes = R.drawable.zaghouan,
            options = listOf("Transport water", "Defense", "Road system", "Storage"),
            correctAnswer = "Transport water",
            difficulty = "Medium",
            hint = "Originated from a mountain spring."
        ),
        Question(
            id = 9,
            category = ROMAN_HERITAGE,
            monument = "Bulla Regia underground houses",
            location = "Bulla Regia",
            fact = "Which Roman site is known for underground houses?",
            imageRes = R.drawable.bulla_regia,
            options = listOf("Bulla Regia", "Dougga", "El Jem", "Carthage"),
            correctAnswer = "Bulla Regia",
            difficulty = "Medium",
            hint = "Built partly below ground to escape summer heat."
        ),
        Question(
            id = 10,
            category = ROMAN_HERITAGE,
            monument = "Oudna Amphitheater capacity",
            location = "Oudna",
            fact = "The amphitheater at Oudna could seat approximately how many spectators?",
            imageRes = R.drawable.oudna,
            options = listOf("10,000", "15,000", "20,000", "30,000"),
            correctAnswer = "15,000",
            difficulty = "Medium",
            hint = "Smaller than El Jem but still impressive."
        ),
        Question(
            id = 11,
            category = ROMAN_HERITAGE,
            monument = "Bulla Regia houses",
            location = "Bulla Regia",
            fact = "What unique feature makes Bulla Regia's houses different?",
            imageRes = R.drawable.bulla_regia,
            options = listOf("Built underground", "Made of marble", "Converted to mosques", "Used as reservoirs"),
            correctAnswer = "Built underground",
            difficulty = "Hard",
            hint = null
        ),
        Question(
            id = 12,
            category = ROMAN_HERITAGE,
            monument = "El Jem Amphitheater construction",
            location = "El Jem",
            fact = "The El Jem Amphitheater was constructed around which year?",
            imageRes = R.drawable.el_jem,
            options = listOf("238 AD", "150 AD", "300 AD", "400 AD"),
            correctAnswer = "238 AD",
            difficulty = "Hard",
            hint = null
        )
    )
}

