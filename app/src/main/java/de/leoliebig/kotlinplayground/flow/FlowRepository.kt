package de.leoliebig.kotlinplayground.flow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlin.random.Random

object FlowRepository {

    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    fun randomNumbers(amount: Int) : Flow<Int> {
        val random = Random.Default
        return flow {
            for(i in 1..amount) {
                emit(random.nextInt())
            }
        }
    }

    fun randomStates(amount: Int, delayInMillis: Long = 300) : StateFlow<String> {
        val states = listOf("Hot", "Cold", "Normal")
        val mutableState = MutableStateFlow(states.random())

        coroutineScope.launch {
            for(i in 1..amount) {
                mutableState.value = states.random()
                delay(delayInMillis)
            }
        }

        return mutableState
    }

    //TODO SharedFlow

}