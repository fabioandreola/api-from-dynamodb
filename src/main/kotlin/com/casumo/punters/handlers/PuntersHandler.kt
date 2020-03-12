package com.casumo.punters.handlers

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent

interface PuntersHandler {

    fun handle(request: APIGatewayProxyRequestEvent): APIGatewayProxyResponseEvent

}
