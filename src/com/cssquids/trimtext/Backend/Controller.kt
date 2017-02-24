package com.cssquids.trimtext.Backend

import com.cssquids.trimtext.Statex.State
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

/**
 * Created by Arthur on 2/16/2017.
 *
 * This singleton object handles backend  functionality
 * In the future, it should intelligently allocate resources/threads to maximize performance
 * ...While abstracting away that complex logic
 * For now, though, basically a stub
 *
 */
object Controller {

    var executor: ExecutorService = Executors.newFixedThreadPool(State.x.threadPoolSize);

    fun <V> run(f:() -> V): Future<V> {
        return executor.submit(f);
    }

    fun <V> run(task:() -> V, callback:(V) -> Unit) {
        executor.submit{ callback(task()) }
    }

    fun <V> run(task:() -> V, placeholder: V, callback:(V) -> Unit) {
        executor.submit{ callback(task()) }
        callback(placeholder)
    }

    fun java_run(f:() -> Unit) {
        executor.submit(f);
    }

    fun stop() {
        executor.shutdown();
    }

}