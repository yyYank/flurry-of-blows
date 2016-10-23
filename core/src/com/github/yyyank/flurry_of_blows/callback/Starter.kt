package com.github.yyyank.flurry_of_blows.callback

/**
 * Created by yy_yank on 2016/10/23.
 */
interface Starter {
    var callback : Runnable?
    fun start() : Unit
}

data class hoge(val a : String){

}