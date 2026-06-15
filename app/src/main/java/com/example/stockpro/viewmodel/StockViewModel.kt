package com.example.stockpro.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.stockpro.model.Producto

class StockViewModel : ViewModel() {

    val productos = mutableStateListOf(
        Producto(1, "Laptop", "Laptop HP ProBook", 850.0, 10),
        Producto(2, "Mouse", "Mouse Logitech", 20.0, 4),
        Producto(3, "Teclado", "Teclado Mecánico", 45.0, 2),
        Producto(4, "Monitor", "Monitor Samsung 24", 180.0, 8),
        Producto(5, "Impresora", "Impresora Epson", 250.0, 0),
        Producto(6, "Disco SSD", "SSD Kingston 1TB", 90.0, 3)
    )

    fun obtenerProducto(id: Int): Producto? {
        return productos.find { it.id == id }
    }

    fun actualizarStock(id: Int, nuevaCantidad: Int) {

        val index = productos.indexOfFirst { it.id == id }

        if (index != -1) {

            val producto = productos[index]

            productos[index] = producto.copy(
                stockActual = nuevaCantidad
            )
        }
    }

    fun calcularValorTotalInventario(): Double {

        return productos.sumOf {
            it.precio * it.stockActual
        }
    }

    fun obtenerProductosEnRiesgo(): List<Producto> {

        return productos.filter {
            it.stockActual < 5
        }
    }

    fun contarProductosSinStock(): Int {

        return productos.count {
            it.stockActual == 0
        }
    }
}