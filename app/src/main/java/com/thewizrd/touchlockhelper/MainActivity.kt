package com.thewizrd.touchlockhelper

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.thewizrd.touchlockhelper.databinding.ActivityMainBinding

class MainActivity : Activity() {
    companion object {
        private const val ACTION_ENABLE_WET_MODE =
            "com.google.android.wearable.action.ENABLE_WET_MODE"
    }

    private lateinit var binding: ActivityMainBinding
    private var isAutoLaunched = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViews()
        setupListeners()
    }

    private fun setupViews() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.touchlockPrefSwitch.isChecked = Settings.isAutoLaunchTouchLockEnabled(this)
    }

    private fun setupListeners() {
        binding.launchTouchlockPref.setOnClickListener {
            startWetMode()
        }

        binding.touchlockPrefSwitch.setOnCheckedChangeListener { _, isChecked ->
            Settings.setAutoLaunchTouchLockEnabled(this, isChecked)
        }
    }

    override fun onResume() {
        super.onResume()
        if (Settings.isAutoLaunchTouchLockEnabled(this) && !isAutoLaunched) {
            startWetMode()
            isAutoLaunched = true
        }
    }

    private fun startWetMode() {
        sendBroadcast(Intent(ACTION_ENABLE_WET_MODE))
    }
}
