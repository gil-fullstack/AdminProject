package com.tinellus.adminproject.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tinellus.adminproject.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    title: String,
    menuItems: List<String>,
    navController: NavController, // Optional navigation icon
    onNavigationIconClicked: () -> Unit = {} // Optional navigation icon click handler
) {
    var expanded by remember { mutableStateOf(false) }
    fun onMenuItemClicked(page: String) {
        navController.navigate(page)
    }
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                color = colorResource(id = R.color.white),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .heightIn(max = 24.dp)
            )
        },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(id = R.color.dark_blue)
        ),
        actions = {
            IconButton(onClick = { expanded = true }) {
                Icon(Icons.Filled.MoreVert, contentDescription = "More", tint = Color.White)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                        modifier = Modifier
                        .heightIn(max = 160.dp)
                    .widthIn(max = 250.dp)
            ) {
                menuItems.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(item, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.White) },
                        onClick = {
                            expanded = false
                            onMenuItemClicked(item)
                        },
                        modifier = Modifier.background(color = colorResource(id = R.color.dark_blue))
                    )
                    Canvas(modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)) {
                        drawLine(
                            color = Color.LightGray,
                            start = Offset(0f, 0f),
                            end = Offset(size.width, 0f),
                            strokeWidth = 1f // Adjust stroke width as needed
                        )
                    }
                }
            }
        }
    )
}