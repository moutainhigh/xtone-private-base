package com.wns.push.util;

import org.springframework.data.mongodb.core.MongoTemplate;

public class WnsBaseMongoDao
{
    private MongoTemplate mongoTemplate;

    public void createCollection(Class<Object> entityClass)
    {
        if (!mongoTemplate.collectionExists(entityClass))
        {
            mongoTemplate.createCollection(entityClass);
        }
    }

    public void dropCollection(Class<Object> entityClass)
    {
        if (mongoTemplate.collectionExists(entityClass))
        {
            mongoTemplate.dropCollection(entityClass);
        }
    }

    public MongoTemplate getMongoTemplate()
    {
        return mongoTemplate;
    }

    public void setMongoTemplate(MongoTemplate mongoTemplate)
    {
        this.mongoTemplate = mongoTemplate;
    }
}
