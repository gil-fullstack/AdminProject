package com.tinellus.adminproject.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tinellus.adminproject.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavController) {
    var expanded by remember { mutableStateOf(false) }
    fun onBackNavClicked() {
        navController.navigate("login")
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Home",
                        color = colorResource(id = R.color.white),
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .heightIn(max = 24.dp)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.dark_blue)
                ),
                actions = {

                   IconButton(onClick = { expanded = true }) {
                       Icon(
                           Icons.Filled.Menu,
                           contentDescription = "More",
                           tint = Color.White)
                   }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier.heightIn(max = 160.dp)
                    ) {
                        DropdownMenuItem(
                            text = { Text("Home") },
                            onClick = {
                                expanded = false
                                onBackNavClicked()
                            },
                        )
                        DropdownMenuItem(
                            text = { Text("Products") },
                            onClick = {
                                expanded = false
                                onBackNavClicked()
                            },
                        )
                        DropdownMenuItem(
                            text = { Text("Login") },
                            onClick = {
                                expanded = false
                                onBackNavClicked()
                            },
                        )
                    }
                }

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