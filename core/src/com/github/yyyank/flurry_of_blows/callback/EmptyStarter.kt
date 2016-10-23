package com.github.yyyank.flurry_of_blows.callback

/**
 * Created by yy_yank on 2016/10/23.
 */
class EmptyStarter(override var callback: Runnable?) : Starter {
    override fun start() {
    }

}