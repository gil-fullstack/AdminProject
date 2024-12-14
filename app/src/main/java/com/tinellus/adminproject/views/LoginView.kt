package com.tinellus.adminproject.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun LoginView(navController: NavController){
     Column(modifier = Modifier.fillMaxSize(),
          verticalArrangement = Arrangement.Center,
          horizontalAlignment = CenterHorizontally) {
          Text(text = "Login")
          Button(onClick = { navController.navigate("home") }) {
               Text("Home", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.White)
          }
     }
}