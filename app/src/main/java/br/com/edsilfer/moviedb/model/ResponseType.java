package br.com.edsilfer.moviedb.model;

/**
 * Created by edgar on 17/02/2016.
 */
public enum ResponseType {

    SUCCESS("SUCCESS"), ERROR("ERROR");

    private String mValue;

    ResponseType(String value) {
        this.mValue = value;
    }

    @Override
    public String toString() {
        if (this.mValue == null) {
            return "";
        }
        return this.mValue;
    }
}
