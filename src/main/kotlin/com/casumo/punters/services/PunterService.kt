package com.casumo.punters.services

import com.casumo.punters.model.Punter

interface PunterService {

    fun getByReference(playerReference: String): Punter?
}
