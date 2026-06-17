package com.pedro.pideyaapp.data.mapper

import com.pedro.pideyaapp.data.datasource.EstablishmentDataModel
import com.pedro.pideyaapp.domain.model.Establishment

fun EstablishmentDataModel.toDomain(): Establishment {
    return Establishment(
        id = id,
        eventId = eventId,
        name = name,
        description = description,
        typeLabel = typeLabel
    )
}
