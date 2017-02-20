package com.cssquids.trimtext.Statex.Internal

import javafx.collections.ObservableList
import javafx.scene.control.SingleSelectionModel
import javafx.scene.control.Tab
import javafx.scene.control.TabPane

/**
 * Created by Arthur on 2/19/2017.
 */
class Tabs {

    var tabPane: TabPane? = null


    //Shortcut functions to make code in other classes more concise

    fun getTabs(): ObservableList<Tab>? { return tabPane?.getTabs(); }
    fun add(t: Tab): Unit { tabPane?.getTabs()?.add(t) }
    fun getSelectModel(): SingleSelectionModel<Tab>? { return tabPane?.getSelectionModel() }
    fun getCurrentTab(): Tab? {
        return getSelectModel()?.getSelectedItem()
    }
}