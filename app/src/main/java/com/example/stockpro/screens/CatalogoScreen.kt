package com.example.stockpro.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.stockpro.viewmodel.StockViewModel

@Composable
fun CatalogoScreen(
    nombreOperario: String,
    viewModel: StockViewModel,
    onEditarProducto: (Int) -> Unit,
    onVerReporte: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Operario: $nombreOperario"
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {

            items(viewModel.productos) { producto ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {

                        Text(producto.nombre)

                        Text(
                            "Stock: ${producto.stockActual}"
                        )

                        if (producto.stockActual < 5) {

                            Text(
                                "⚠ Stock Crítico"
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