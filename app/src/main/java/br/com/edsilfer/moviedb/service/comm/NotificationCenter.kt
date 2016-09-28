package br.com.edsilfer.moviedb.service.comm

import br.com.edsilfer.moviedb.model.ResponseType
import br.com.edsilfer.moviedb.model.ResponseWrapper
import br.com.edsilfer.moviedb.model.TaskExecutor
import br.com.edsilfer.moviedb.model.enums.EventCatalog
import java.util.*


/**
 * Created by edgar on 17/02/2016.
 */
object NotificationCenter {

    // =============================================================================================
    // EVENT HOLDERS
    // =============================================================================================
    // Event 0000: List Movies
    private val mSubEvent0000 = HashSet<TaskExecutor>()
    // Event 0001: Search Movie
    private val mSubEvent0001 = HashSet<TaskExecutor>()

    // =============================================================================================
    // PUBLIC INTERFACE
    // =============================================================================================
    fun notify(event: EventCatalog, response: ResponseWrapper) {
        val r = Runnable {
            for (executor in getSubscriberList(event)!!) {
                when (response.type) {
                    ResponseType.ERROR -> if (null != response.payload) executor.executeOnErrorTask(response.payload!!)
                    ResponseType.SUCCESS -> if (null != response.payload) executor.executeOnSuccessTask(response.payload!!)
                }
            }
        }
        Thread(r).start()
    }

    private fun getSubscriberList(event: EventCatalog): MutableSet<TaskExecutor>? {
        when (event) {
            EventCatalog.e0000 -> return mSubEvent0000
            EventCatalog.e0001 -> return mSubEvent0001
        }
    }

    // =============================================================================================
    // REGISTRATION INTERFACE
    // =============================================================================================
    object RegistrationCenter {
        fun registerForEvent(event: EventCatalog, subscriber: TaskExecutor) {
            getSubscriberList(event)!!.add(subscriber)
        }

        fun unregisterForEvent(event: EventCatalog, subscriber: TaskExecutor) {
            getSubscriberList(event)!!.remove(subscriber)
        }
    }
}
