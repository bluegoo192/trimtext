package com.cssquids.trimtext.Statex

/**
 * Created by Arthur on 2/16/2017.
 */
class SceneSettings {

    var sceneWidth: Int = 800
    var sceneHeight: Int = 600
    var verticalSpacing: Double = 0.0

    fun getWidth(): Int { return sceneWidth }
    fun getHeight(): Int { return sceneHeight }
    fun getVSpacing(): Double { return verticalSpacing }
}