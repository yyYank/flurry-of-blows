package com.github.yyyank.flurry_of_blows.callback

import java.util.*

/**
 * Created by yy_yank on 2016/10/23.
 */
object CallbackRouter {
    private val map : LinkedHashMap<Starter, Array<out Starter>> = LinkedHashMap()

    fun defineRoot(trigger : Starter, vararg starters : Starter) {
        map.put(trigger, starters)
    }

    fun initialize(){
        map.clear()
    }

    fun start() {
        map.entries.firstOrNull()?.key?.start()
    }

    fun next(starter : Starter) : Array<out Starter> = map.get(starter) ?: emptyArray()
}
