package com.casumo.punters.responses

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent
import com.casumo.punters.model.Punter
import org.apache.http.HttpStatus

data class ErrorResponse(val status: Int, val error: String) : GenericResponse()
data class PunterResponse(val punter: Punter?) : GenericResponse()

fun generateErrorResponse(errorCode: Int, message: String) = APIGatewayProxyResponseEvent()
        .withStatusCode(errorCode)
        .withBody(ErrorResponse(errorCode, message).toJSON())

fun generateOKResponse(entity: GenericResponse? = null) = APIGatewayProxyResponseEvent()
        .withStatusCode(HttpStatus.SC_OK)
        .withBody(entity?.toJSON())
