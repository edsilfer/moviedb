package br.com.edsilfer.moviedb.model.enums

/**
 * Created by edgar on 17/02/2016.
 */
enum class EventCatalog private constructor(private val mValue: String?) {

    // Event 0000: List Movies
    e0000("e0000");

    override fun toString(): String {
        if (this.mValue == null) {
            return ""
        }
        return this.mValue
    }

    companion object {
        fun fromString(value: String?): EventCatalog? {
            if (value != null) {
                for (b in EventCatalog.values()) {
                    if (value.equals(b.mValue!!, ignoreCase = true)) {
                        return b
                    }
                }
            }
            return null
        }
    }
}
