package com.shodiq.rockpaperscissors_challenge4

class Match(private val playerChoice: Int, private val comChoice: Int) {
    val action = arrayOf("Rock", "Paper", "Scissors")

    fun result(): String {

        return when {
            (playerChoice + 1) % 3 == comChoice -> {
                "Computer Win"
            }
            playerChoice == comChoice -> {
                "Match Draw"
            }
            else -> {
                "Player Win"
            }
        }

    }
}