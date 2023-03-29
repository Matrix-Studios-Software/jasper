package ltd.matrixstudios.jasper.connection

import com.mongodb.client.*

/**
 * All functions that are an extension
 * to the normal MongoClient
 */
class ExtendedMongoClient(
    private val client: MongoClient
) {

    /**
     * Default MongoDB database method
     *
     * @param name
     * @return called database
     */
    fun getDatabase(name: String) : MongoDatabase = client.getDatabase(name)

    /**
     * Calls for the raw MongoClient
     *
     * @return current client
     */
    fun returnRawClient() : MongoClient = client

    /**
     * Executes an action using
     * the raw MongoClient and also
     * returns the client itself.
     *
     * @return current client
     * @param action
     */
    fun executeAndReturnRawClient(
        action: (MongoClient) -> Unit
    ) : MongoClient {
        return client.also { action.invoke(it) }
    }

}