package com.pr.customview

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.pr.customview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        binding.bottomButtons.setListener { action ->

            when (action) {
                BottomButtonAction.POSITIVE -> {
                    binding.bottomButtons.setPositiveButtonText("Updated Ok")
                }
                BottomButtonAction.NEGATIVE -> {
                    binding.bottomButtons.setNegativeButtonText("Updated Cancel")
                }
            }
        }
    }

}