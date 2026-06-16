package com.example.stockpro.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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

    var cantidad by remember {
        mutableStateOf(
            producto?.stockActual ?: 0
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Editar Producto",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = producto?.nombre ?: ""
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Button(
                onClick = {
                    if (cantidad > 0) {
                        cantidad--
                    }
                }
            ) {
                Text("-")
            }

            Text(
                text = cantidad.toString(),
                style = MaterialTheme.typography.headlineMedium
            )

            Button(
                onClick = {
                    cantidad++
                }
            ) {
                Text("+")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {

                viewModel.actualizarStock(
                    productoId,
                    cantidad
                )

                onGuardar()
            }
        ) {
            Text("Guardar")
        }
    }
}