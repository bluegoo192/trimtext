package com.cssquids.trimtext.Statex

/**
 * Created by Arthur on 2/16/2017.
 *
 * Singleton containing the current application state
 *
 */
object State {

    /**
     * Why x as the name for our state?
     * First, basically every reference to CurrentState will refer to the state object
     * So we'll be writing CurrentState.x... a lot.  x is very concise and easy to type
     * (Considering that this object is called CurrentState, I don't think much clarity is lost to the name 'x')
     * Second, x is a nice homage to the Flux/Redux principles that inspired this class
     *
     * By the way, @JvmField annotation simply tells Kotlin to expose x as a field when accessing this obj from Java
     */
    @JvmField
    var x = Store()

}