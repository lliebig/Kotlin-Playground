package de.leoliebig.kotlinplayground.domain.commands

/**
 * Created by Leo on 24.10.2017.
 */
interface Command<out T> {
    fun execute(): T
}