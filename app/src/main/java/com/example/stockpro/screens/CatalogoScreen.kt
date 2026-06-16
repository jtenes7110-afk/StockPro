package com.example.stockpro.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.stockpro.model.Producto
import com.example.stockpro.viewmodel.StockViewModel

@Composable
fun CatalogoScreen(
    nombreOperario: String,
    viewModel: StockViewModel,
    onEditarProducto: (Int) -> Unit,
    onVerReporte: () -> Unit
) {

    var mostrarCriticos by remember {
        mutableStateOf(false)
    }

    val productosMostrar: List<Producto> =
        if (mostrarCriticos) {
            viewModel.obtenerProductosEnRiesgo()
        } else {
            viewModel.productos
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Operario: $nombreOperario"
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Button(
                onClick = {
                    mostrarCriticos = false
                }
            ) {
                Text("Ver Todo")
            }

            Button(
                onClick = {
                    mostrarCriticos = true
                }
            ) {
                Text("Stock Crítico")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {

            items(productosMostrar) { producto ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {

                        Text(
                            text = producto.nombre
                        )

                        Text(
                            text = "Precio: $${producto.precio}"
                        )

                        if (producto.stockActual < 5) {

                            Text(
                                text = "Stock: ${producto.stockActual}",
                                color = Color.Red
                            )

                        } else {

                            Text(
                                text = "Stock: ${producto.stockActual}"
                            )
                        }

                        Button(
                            onClick = {
                                onEditarProducto(producto.id)
                            }
                        ) {
                            Text("Editar")
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onVerReporte
        ) {
            Text("Ver Reporte")
        }
    }
}