package ltd.matrixstudios.jasper

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoDatabase
import ltd.matrixstudios.jasper.connection.ExtendedMongoClient

object Jasper
{

    lateinit var globalClient: ExtendedMongoClient
    lateinit var globalDatabase: MongoDatabase

    /**
     * Creates a new ExtendedMongoClient
     * and sets the values that Jasper
     * will use to store objects
     *
     * @param uri
     * @param database
     * @return new client
     */
    fun createClient(uri: String, database: String) : ExtendedMongoClient
    {
       return ExtendedMongoClient(MongoClients.create(uri)).also {
           this.globalClient = it
           this.globalDatabase = it.getDatabase(database)
        }
    }
}