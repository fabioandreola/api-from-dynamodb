package com.casumo.punters.handlers

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent
import com.casumo.punters.model.Punter
import com.casumo.punters.responses.PunterResponse
import com.casumo.punters.responses.generateErrorResponse
import com.casumo.punters.responses.generateOKResponse
import com.casumo.punters.services.DynamoDBPunterService
import org.apache.http.HttpStatus

class GetPuntersHandler : PuntersHandler {

    private val punterService = DynamoDBPunterService()

    override fun handle(request: APIGatewayProxyRequestEvent): APIGatewayProxyResponseEvent {
        return try {
            val playerRef = request.pathParameters?.get(Punter.PLAYER_REF_FIELD)
            if (playerRef.isNullOrEmpty()) {
                return generateErrorResponse(errorCode = HttpStatus.SC_BAD_REQUEST,
                        message = "Error processing sku or sku not provided")
            }

            val punter = punterService.getByReference(playerRef)
            punter?.let {
                return generateOKResponse(PunterResponse(it))
            }

            return generateErrorResponse(HttpStatus.SC_NOT_FOUND,
                    "Punter not found")

        } catch (e: Exception) {
            generateErrorResponse(HttpStatus.SC_INTERNAL_SERVER_ERROR,
                    e.localizedMessage)
        }
    }
}
