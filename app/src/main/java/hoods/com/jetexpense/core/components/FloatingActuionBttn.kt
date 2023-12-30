package hoods.com.jetexpense.core.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable


@Composable
fun FloatingActionBttn(
    onClick: () -> Unit,
    ) {

    FloatingActionButton(onClick = onClick){
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = null,
        )
    }
}