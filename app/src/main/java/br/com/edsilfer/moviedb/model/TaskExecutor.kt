package br.com.edsilfer.moviedb.model

/**
 * Created by edgar on 17/02/2016.
 */
interface TaskExecutor {
    fun executeOnSuccessTask(payload: Any)

    fun executeOnErrorTask(payload: Any)
}
