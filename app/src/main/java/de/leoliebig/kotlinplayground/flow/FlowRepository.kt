package de.leoliebig.kotlinplayground.flow

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.random.Random

object FlowRepository {

    private const val TAG = "FlowRepository"

    private val coroutineScope = CoroutineScope(Dispatchers.Default)
    private val states = listOf("Hot", "Cold", "Normal")
    private val mutableState = MutableStateFlow(states.random())
    private val state = mutableState.asStateFlow()


    fun randomNumbers(amount: Int) : Flow<Int> {
        //cold flow, starts execution if collected, single consumer

        val random = Random.Default
        return flow {
            for(i in 1..amount) {
                emit(random.nextInt())
            }
        }
    }

    fun randomStates(amountOfNewStatesToProduce: Int = 0, delayInMillis: Long = 300) : StateFlow<String> {
        //hot flow, always active and in memory
        //has a producer and one or multiple consumers

        //send some states for demonstration purposes
        coroutineScope.launch {
            for(i in 1..amountOfNewStatesToProduce) {
                val state = states.random()
                Log.d(TAG, "Updating state to $state")
                delay(delayInMillis)
                mutableState.value = state
            }
        }

        return state
    }

    //TODO SharedFlow

}