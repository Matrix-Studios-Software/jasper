package ltd.matrixstudios.jasper.container

import com.mongodb.client.result.DeleteResult
import com.mongodb.client.result.UpdateResult
import ltd.matrixstudios.jasper.Jasper
import ltd.matrixstudios.jasper.annotation.DataObject
import ltd.matrixstudios.jasper.serializers.DefaultSerializer
import org.bson.Document

/**
 * Contains all functions that will
 * directly call to the MongoCollection
 */
abstract class JasperContainer<K, T>(
    private val serializableClass: Class<T>
) {
    private val collection =
        Jasper.globalDatabase.getCollection(serializableClass.getAnnotation(DataObject::class.java).collection)

    /**
     * Updates a value in mongo
     * based on the key provided
     *
     * @param key
     * @param item
     * @return respective update result
     */
    fun update(key: K, item: T): UpdateResult {
        val gsonObject = DefaultSerializer.GSON.toJson(item)
        val gsonToBson = Document.parse(gsonObject)
        val filter = Document("_id", key.toString())

        return collection.updateOne(filter, gsonToBson)
    }

    /**
     * Deletes a value in mongo
     * based on the key provided
     *
     * @param key
     * @return respective delete result
     */
    fun delete(key: K): DeleteResult = collection.deleteOne(Document("_id", key.toString()))

    /**
     * Returns a list of every mongo element
     *
     * @return result of query
     */
    fun getAllItems(): List<T> = BundledQuery<T>(collection, serializableClass).fetchAndSerialize()

    /**
     * Returns a list of every mongo element
     *
     * @return result of query
     */
    fun getByKey(key: K): List<T> =
        BundledQuery<T>(collection, serializableClass).fetchAndSerializeWithRestriction(Document("_id", key.toString()))
}
