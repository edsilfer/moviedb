package br.com.edsilfer.moviedb.model

/**
 * Sets the contract for network calls handlers
 */
interface TaskExecutor {
    fun executeOnSuccessTask(payload: Any)

    fun executeOnErrorTask(payload: Any)
}
