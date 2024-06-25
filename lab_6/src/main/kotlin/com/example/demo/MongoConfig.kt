import com.mongodb.client.MongoClients
import org.bson.codecs.IdGenerator
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory
import org.springframework.data.mongodb.core.query.Criteria
import javax.management.Query

@Configuration
class MongoConfig {
    @Value("\${spring.data.mongodb.uri}")
    private lateinit var mongoUri: String

    @Value("\${spring.data.mongodb.database}")
    private lateinit var database: String

    @Bean
    fun mongoTemplate(): MongoTemplate {
        val mongoDbFactory = SimpleMongoClientDatabaseFactory(MongoClients.create(mongoUri), database)
        return MongoTemplate(mongoDbFactory)
    }

    /*@Bean
    fun idGenerator(mongoTemplate: MongoTemplate): IdGenerator {
        return object : IdGenerator {
            override fun generate(): Any {
                val query = org.springframework.data.mongodb.core.query.Query(Criteria.where("_id").exists(true))
                query.limit(1)
                val toDoItem = mongoTemplate.findOne(query, ToDoItem::class.java)
                val objectId = toDoItem?.id as ObjectId
                return objectId.toHexString()
            }
        }
    }*/
}