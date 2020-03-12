package com.casumo.punters.services

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig

class DynamoDBAdapter private constructor() {
    private val dbClient: AmazonDynamoDB? = AmazonDynamoDBClientBuilder.standard()
            .withRegion(System.getenv("REGION"))
            .build()
    private var mapper: DynamoDBMapper? = null

    fun createDbMapper(mapperConfig: DynamoDBMapperConfig): DynamoDBMapper? {
        if (this.dbClient != null)
            mapper = DynamoDBMapper(this.dbClient, mapperConfig)

        return this.mapper
    }

    companion object {
        private var dbAdapter: DynamoDBAdapter? = null

        val instance: DynamoDBAdapter?
            get() {
                if (dbAdapter == null)
                    dbAdapter = DynamoDBAdapter()

                return dbAdapter
            }
    }
}
