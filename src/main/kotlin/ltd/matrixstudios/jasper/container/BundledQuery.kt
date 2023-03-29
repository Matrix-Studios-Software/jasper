package ltd.matrixstudios.jasper.container

import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoIterable
import ltd.matrixstudios.jasper.serializers.DefaultSerializer
import org.bson.Document
import org.bson.conversions.Bson
import java.lang.reflect.Type

class BundledQuery<T>(
    private val collection: MongoCollection<Document>,
    val serializerType: Type
) {

    /**
     * Pulls every mongo value
     * and returns them to object
     * form
     *
     * @return list of all items
     */
    fun fetchAndSerialize() : MutableList<T>
    {
        val iterable = collection.find()
        val cursor = iterable.cursor()
        val documents = mutableListOf<Document>()

        /**
         * Cursor over every element in mongo
         * @complexity O(n)
         */
        while (cursor.hasNext()) {
            documents.add(cursor.next())
        }

        val finalElements = mutableListOf<T>()

        /**
         * Iterate every element and serialize
         * @complexity O(n)
         */
        for (json in documents)
        {
            val parsed = DefaultSerializer.GSON.fromJson<T>(json.toJson(), serializerType)
            finalElements.add(parsed)
        }

        return finalElements
    }

    /**
     * Pulls every mongo value
     * within restriction
     * and returns them to object
     * form
     *
     * @return list of all items
     * @param bson restriction
     */
    fun fetchAndSerializeWithRestriction(restriction: Bson) : MutableList<T>
    {
        val iterable = collection.find(restriction)
        val cursor = iterable.cursor()
        val documents = mutableListOf<Document>()

        /**
         * Cursor over every element in mongo
         * @complexity O(n)
         */
        while (cursor.hasNext()) {
            documents.add(cursor.next())
        }

        val finalElements = mutableListOf<T>()

        /**
         * Iterate every element and serialize
         * @complexity O(n)
         */
        for (json in documents)
        {
            val parsed = DefaultSerializer.GSON.fromJson<T>(json.toJson(), serializerType)
            finalElements.add(parsed)
        }

        return finalElements
    }

}