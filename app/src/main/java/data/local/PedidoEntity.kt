package com.pedro.pideyaapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pedidos")
data class PedidoEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val nombreProducto: String,
    val cantidad: Int,
    val precioTotal: Double,
    val fecha: Long
)