package de.leoliebig.kotlinplayground

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import de.leoliebig.kotlinplayground.flow.FlowRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "KotlinPlayground"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //printRandomNumbers()
        printRandomStates()
    }

    private fun printRandomNumbers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                FlowRepository.randomNumbers(4).collect {
                    Log.d(TAG, "Collected random number: $it")
                }
            }
        }
    }

    private fun printRandomStates() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                FlowRepository.randomStates(4).collect {
                    Log.d(TAG, "Collected random state: $it")
                }
            }
        }
    }

}
