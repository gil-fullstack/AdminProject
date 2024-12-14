package com.tinellus.adminproject.views


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tinellus.adminproject.components.MyTopAppBar

@Composable
fun Home(navController: NavController) {
    Scaffold(
        topBar = {
            MyTopAppBar(
                title = "Home",
                menuItems = listOf("Home", "Products", "Login"),
                navController
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
                verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
            ) {
                Text(text = "Home")
                Button(onClick = { navController.navigate("products") }) {
                    Text(text = "Produtos", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
            }
        }
    }

}