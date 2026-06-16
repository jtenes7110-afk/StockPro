package com.example.stockpro.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
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

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Text(
            text = "Sistema de Inventario",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = nombreOperario,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = Color(0xFF1565C0)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Button(
                onClick = {
                    mostrarCriticos = false
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2E7D32)
                )
            ) {
                Text("Ver Todo")
            }

            Button(
                onClick = {
                    mostrarCriticos = true
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF388E3C)
                )
            ) {
                Text("Stock Crítico")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.weight(0.85f)
        ) {

            items(productosMostrar) { producto ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF1E293B)
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {

                        Text(
                            text = producto.nombre,
                            color = Color.White,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text(
                            text = "Precio: $${producto.precio}",
                            color = Color(0xFFE2E8F0)
                        )

                        if (producto.stockActual < 5) {

                            Text(
                                text = "Stock: ${producto.stockActual}",
                                color = Color(0xFFFF5252),
                                style = MaterialTheme.typography.titleMedium
                            )

                        } else {

                            Text(
                                text = "Stock: ${producto.stockActual}"
                            )
                        }

                        Button(
                            onClick = {
                                onEditarProducto(producto.id)
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF2563EB)
                            )
                        ) {
                            Text("Editar")
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onVerReporte,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF00897B)
            )
        ) {
            Text("Ver Reporte")
        }
    }
}