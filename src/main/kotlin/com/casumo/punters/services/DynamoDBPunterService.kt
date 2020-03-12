package com.casumo.punters.services

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression
import com.amazonaws.services.dynamodbv2.model.AttributeValue
import com.casumo.punters.model.Punter
import java.io.IOException

class DynamoDBPunterService : PunterService {

    private var dbMapper: DynamoDBMapper
    private var mapperConfig = DynamoDBMapperConfig.builder()
            .withTableNameOverride(DynamoDBMapperConfig.TableNameOverride(Punter.PUNTERS_TABLE_NAME))
            .build()

    init {
        dbMapper = DynamoDBAdapter.instance!!.createDbMapper(mapperConfig)!!
    }

    @Throws(IOException::class)
    override fun getByReference(playerReference: String): Punter? {
        var punter: Punter? = null
        val params = HashMap<String, AttributeValue>()
        params[":v1"] = AttributeValue().withS(playerReference)

        val queryExp = DynamoDBQueryExpression<Punter>()
                .withKeyConditionExpression("player_ref = :v1")
                .withExpressionAttributeValues(params)

        val result = dbMapper.query(Punter::class.java, queryExp)

        if (result.size > 0) {
            punter = result[0]
        }
        return punter
    }
}
