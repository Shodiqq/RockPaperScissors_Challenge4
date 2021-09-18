package com.shodiq.rockpaperscissors_challenge4

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.shodiq.rockpaperscissors_challenge4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var playerAttributes: Array<android.widget.FrameLayout>
    private lateinit var comAttributes: Array<android.widget.FrameLayout>
    private var playerChoose: Int = 0
    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        start()
    }

    private fun start() {
        playerAttributes =
            arrayOf(binding.flPlayerRock, binding.flPlayerPaper, binding.flPlayerScissors)
        comAttributes =
            arrayOf(binding.flComRock, binding.flComPaper, binding.flComScissors)

        playerAttributes.forEachIndexed { index, frameLayout ->
            frameLayout.setOnClickListener { atrbt ->
                setBackground(atrbt)
                playerChoose = index
                playerAttributes.forEach {
                    if (atrbt != it) {
                        setToDefaultBackground(it)
                    }
                }
                process(randomComChoose())
            }
        }
        binding.ivReset.setOnClickListener() {
            resetGame()
        }
    }

    private fun randomComChoose(): Int {
        return (0..3).random() % 3
    }

    private fun resetGame() {
        playerChoose
        binding.tvResult.text = ""
        playerAttributes.forEach {
            setToDefaultBackground(it)
            it.isClickable = true
        }
        comAttributes.forEach {
            setToDefaultBackground(it)
        }
    }

    private fun process(comChoose: Int) {
        setBackground(comAttributes[comChoose])
        val match = Match(playerChoose, comChoose)
        val result = match.result()

        binding.tvResult.text = result

        Log.d(TAG, "PLayer Choice = ${match.action[playerChoose]}")
        Log.d(TAG,"Computer Choose= ${match.action[comChoose]}")
        Log.d(TAG, "Result = $result")
        disableClick()
    }

    private fun disableClick() {
        playerAttributes.forEachIndexed { index, frameLayout ->
            frameLayout.isClickable = false
        }
    }

    private fun setToDefaultBackground(frameLayout: View) {
        frameLayout.setBackgroundResource(R.drawable.bg_transparent)
    }

    private fun setBackground(frameLayout: View) {
        frameLayout.setBackgroundResource(R.drawable.bg_color)
    }
}