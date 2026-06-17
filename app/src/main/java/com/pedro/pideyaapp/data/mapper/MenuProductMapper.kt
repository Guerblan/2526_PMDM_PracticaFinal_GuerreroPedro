package com.pedro.pideyaapp.data.mapper

import com.pedro.pideyaapp.data.datasource.MenuProductDataModel
import com.pedro.pideyaapp.domain.model.MenuProduct

fun MenuProductDataModel.toDomain(): MenuProduct {
    return MenuProduct(
        id = id,
        establishmentId = establishmentId,
        establishmentName = establishmentName,
        name = name,
        description = description,
        price = price
    )
}
