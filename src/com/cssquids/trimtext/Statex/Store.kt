package com.cssquids.trimtext.Statex

import com.cssquids.trimtext.Main
import com.cssquids.trimtext.UI.Editor
import javafx.application.Application
import javafx.scene.Parent
import javafx.scene.layout.VBox
import java.util.*

/**
 * Created by Arthur on 2/16/2017.
 *
 * This class provides a generic Flux-like container for application state
 * You can have as many State objects as you like, since only the one held by CurrentState affects anything
 *
 * This class is for data ONLY -- think of it a fancy data structure
 * DO NOT PUT ANY LOGIC, EVEN OBJECT SETUP, IN THIS CLASS
 * EVERYTHING MUST BE THREAD SAFE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 *
 */
class Store {

    var threadPoolSize: Int = 5

    var ignoreNextPress: Boolean = false

    var editors: Vector<Editor> = Vector()
    var currentEditor: Editor? = null

    var settings = SceneSettings()
    var verticalLayout = VBox(settings.getVSpacing())

    var sceneLayout: Parent = verticalLayout //in the future, we could change sceneLayout to something else
    var app: Main? = null //cheap trick to expose app globally.  temporary fix
    //TODO: remove this once Main functionality has been refactored to other classes
}