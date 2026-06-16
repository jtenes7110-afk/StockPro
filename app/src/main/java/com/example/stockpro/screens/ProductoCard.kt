package com.example.stockpro.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.stockpro.model.Producto

@Composable
fun ProductoCard(
    producto: Producto,
    onEditar: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = producto.nombre,
                style = MaterialTheme.typography.titleMedium
            )

            Text("Descripción: ${producto.descripcion}")

            Text("Precio: $${producto.precio}")

            Text("Stock: ${producto.stockActual}")

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = onEditar
            ) {
                Text("Editar")
            }
        }
    }
}