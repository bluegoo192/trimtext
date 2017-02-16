package com.cssquids.trimtext.Statex

import javafx.scene.Parent
import javafx.scene.layout.VBox

/**
 * Created by Arthur on 2/16/2017.
 *
 * This class provides a Flux-like container for application state
 */
class State {

    var settings = SceneSettings()
    var verticalLayout = VBox(settings.getVSpacing())

    var sceneLayout: Parent = verticalLayout //in the future, we could change sceneLayout to something else
}