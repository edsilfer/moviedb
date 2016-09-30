package br.com.edsilfer.moviedb.service

import br.com.edsilfer.moviedb.model.ResponseWrapper
import br.com.edsilfer.moviedb.model.TaskExecutor
import br.com.edsilfer.moviedb.model.enums.EventCatalog
import br.com.edsilfer.moviedb.model.enums.ResponseType
import br.com.edsilfer.moviedb.service.comm.NotificationCenter
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.runners.MockitoJUnitRunner

/**
 * Created by User on 30/09/2016.
 */
@RunWith(MockitoJUnitRunner::class)
class TestNotificationCenter {

    @Mock
    lateinit var mExecutor: TaskExecutor

    private val mPayload = "TEST PAYLOAD"

    @Test
    fun testExecuteOnSuccessTask() {
        val wrapper = ResponseWrapper(mPayload, ResponseType.SUCCESS)
        NotificationCenter.RegistrationCenter.registerForEvent(EventCatalog.e0000, mExecutor)
        NotificationCenter.notify(EventCatalog.e0000, wrapper)
        Thread.sleep(1000)
        Mockito.verify<TaskExecutor>(mExecutor, Mockito.times(1)).executeOnSuccessTask(mPayload)
    }


    @Test
    fun testExecuteOnErrorTask() {
        val wrapper = ResponseWrapper(mPayload, ResponseType.ERROR)
        NotificationCenter.RegistrationCenter.registerForEvent(EventCatalog.e0000, mExecutor)
        NotificationCenter.notify(EventCatalog.e0000, wrapper)
        Thread.sleep(1000)
        Mockito.verify<TaskExecutor>(mExecutor, Mockito.times(1)).executeOnErrorTask(mPayload)
    }
}
