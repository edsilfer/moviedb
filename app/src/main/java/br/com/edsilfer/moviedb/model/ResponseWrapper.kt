package br.com.edsilfer.moviedb.model

import java.io.Serializable

/**
 * Created by edgar on 17/02/2016.
 */
class ResponseWrapper : Serializable {

    var type: ResponseType? = null
    var payload: Any? = null

    constructor() {
    }

    constructor(payload: Any?, type: ResponseType?) {
        this.payload = payload
        this.type = type
    }
}
