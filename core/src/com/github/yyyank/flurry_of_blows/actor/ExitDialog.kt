package com.github.yyyank.flurry_of_blows.actor

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.ui.Button
import com.badlogic.gdx.scenes.scene2d.ui.Dialog
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Skin

/**
 * Created by yy_yank on 2016/11/04.
 */
class ExitDialog internal constructor(styleName: String, skin: Skin) : Dialog("", skin, styleName) {

    init {
        contentTable.add(Image(skin, "exitMessage"))
        button(Button(skin, styleName + "Yes"), true)
        button(Button(skin, styleName + "No"), false)
    }

    override protected fun result(obj: Any) {
        if (obj as Boolean) {
            Gdx.app.exit()
        }
    }
}

fun forTitle(skin: Skin): Dialog = ExitDialog("exit", skin)
fun forResult(skin: Skin): Dialog = ExitDialog("exit2", skin)