package com.casumo.punters.model

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable

@DynamoDBTable(tableName = "player-categories-dev")
class Punter(
        @DynamoDBHashKey(attributeName = PLAYER_REF_FIELD)
        var playerRef: String,
        @DynamoDBAttribute(attributeName = "punter_category")
        var punterCategory: String?
) {

    constructor() : this("", "")

    companion object {
        val PUNTERS_TABLE_NAME = System.getenv("DYNAMODB_TABLE")
        const val PLAYER_REF_FIELD = "player_ref"
    }
}
