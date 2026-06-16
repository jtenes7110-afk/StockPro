package com.example.stockpro.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.stockpro.viewmodel.StockViewModel

@Composable
fun ReporteScreen(
    viewModel: StockViewModel,
    onVolver: () -> Unit
) {

    val capitalTotal =
        viewModel.calcularValorTotalInventario()

    val productosSinStock =
        viewModel.contarProductosSinStock()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),

        verticalArrangement = Arrangement.Center,

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Reporte Financiero",
            style = MaterialTheme.typography.headlineMedium,
            color = Color(0xFF1565C0)
        )

        Text(
            text = "Capital Invertido Total",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Card {

            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                Text(
                    text = "$$capitalTotal",
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Text(
            text = "Productos sin stock: $productosSinStock"
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Button(
            onClick = onVolver
        ) {

            Text(
                text = "Volver al Catálogo"
            )
        }
    }
}