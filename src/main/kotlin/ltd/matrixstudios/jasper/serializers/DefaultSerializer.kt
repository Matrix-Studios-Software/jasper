package ltd.matrixstudios.jasper.serializers

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.LongSerializationPolicy

/**
 * Default serializer that Jasper
 * will use
 *
 * @using google/gson
 */
object DefaultSerializer {

    var GSON: Gson = GsonBuilder()
        .serializeNulls()
        .setPrettyPrinting()
        .setLongSerializationPolicy(LongSerializationPolicy.STRING)
        .create()

    /**
     * Sets the default Gson
     * to inputted value
     *
     * @param newGson
     * @return default gson
     */
    fun customizeGson(
        newGson: Gson
    ) : Gson {
        return newGson.also { this.GSON = it }
    }
}