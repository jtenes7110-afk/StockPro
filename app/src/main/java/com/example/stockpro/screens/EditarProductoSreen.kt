package com.example.stockpro.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.stockpro.viewmodel.StockViewModel

@Composable
fun EditarProductoScreen(
    productoId: Int,
    viewModel: StockViewModel,
    onGuardar: () -> Unit
) {

    val producto = viewModel.obtenerProducto(productoId)

    var nuevoStock by remember {
        mutableStateOf(
            producto?.stockActual?.toString() ?: ""
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Editar Producto",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Producto: ${producto?.nombre}"
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = nuevoStock,
            onValueChange = {
                nuevoStock = it
            },
            label = {
                Text("Nuevo Stock")
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {

                nuevoStock.toIntOrNull()?.let { cantidad ->

                    viewModel.actualizarStock(
                        productoId,
                        cantidad
                    )

                    onGuardar()
                }
            }
        ) {
            Text("Guardar")
        }
    }
}