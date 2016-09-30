package br.com.edsilfer.moviedb.model

import br.com.edsilfer.moviedb.model.enums.ResponseType
import java.io.Serializable

/**
 * Created by edgar on 17/02/2016.
 */
class ResponseWrapper : Serializable {

    var type: ResponseType? = null
    var payload: Any? = null

    constructor(payload: Any?, type: ResponseType?) {
        this.payload = payload
        this.type = type
    }
}
