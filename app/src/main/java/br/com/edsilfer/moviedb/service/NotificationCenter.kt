package br.com.edsilfer.moviedb.service

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
    // Event 0000: Get GCM Token
    private val mSubEvent0000 = HashSet<TaskExecutor>()


    // =============================================================================================
    // PUBLIC INTERFACE
    // =============================================================================================
    fun notify(event: EventCatalog, response: ResponseWrapper) {
        val r = Runnable {
            for (executor in NotificationCenter.getSubscriberList(event)!!) {
                when (response.type) {
                    ResponseType.ERROR -> executor.executeOnErrorTask(response.payload!!)
                    ResponseType.SUCCESS -> executor.executeOnSuccessTask(response.payload!!)
                }
            }
        }
        Thread(r).start()
    }

    private fun getSubscriberList(event: EventCatalog): MutableSet<TaskExecutor>? {
        when (event) {
            EventCatalog.e0000 -> return NotificationCenter.mSubEvent0000
        }
        return null
    }

    // =============================================================================================
    // REGISTRATION INTERFACE
    // =============================================================================================
    object RegistrationCenter {
        fun registerForEvent(event: EventCatalog, subscriber: TaskExecutor) {
            NotificationCenter.getSubscriberList(event)!!.add(subscriber)
        }

        fun unregisterForEvent(event: EventCatalog, subscriber: TaskExecutor) {
            NotificationCenter.getSubscriberList(event)!!.remove(subscriber)
        }
    }
}
