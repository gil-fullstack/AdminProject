package com.tinellus.adminproject.views

import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.tinellus.adminproject.RetrofitInstance
import com.tinellus.adminproject.components.MyTopAppBar
import com.tinellus.adminproject.data.Api
import com.tinellus.adminproject.data.ProductsRepositoryImpl
import com.tinellus.adminproject.data.model.Product
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking
import java.text.DecimalFormat

@Composable
fun ProductsView(navController: NavController) {
    val viewModel = viewModel<ProductsViewModel>(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ProductsViewModel(ProductsRepositoryImpl(RetrofitInstance.api))
                        as T
            }
        }
    )
    Scaffold(
//        modifier = Modifier.fillMaxSize(),
        topBar = {
            MyTopAppBar(
                title = "Products",
                menuItems = listOf("Home", "Products", "Login"),
                navController
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .background(color = Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        )

        {
            Button(onClick = { navController.navigate("products_register") }) {
                Text(text = "Novo Produto", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            val productList = viewModel.products.collectAsState().value
            val context = LocalContext.current
            LaunchedEffect(key1 = viewModel.showErrorToastChannel) {
                viewModel.showErrorToastChannel.collectLatest { show ->
                    if (show) {
                        Toast.makeText(
                            context,
                            "Error loading products",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
            if (productList.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {

                LazyColumn(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(productList.size) { index ->
                        ProductCard(productList[index])
                        Spacer(modifier = Modifier.height(1.dp))
                        Log.d("Product ID", productList[index].id.toString())
                    }

                }

            }

        }


    }


}

@Composable
fun CustomText(text: String, fontSize: Int, fontWeight: FontWeight, myColor: Color) {
    Text(text = text, fontWeight = fontWeight, fontSize = fontSize.sp, color = myColor)
}

@Composable
fun ProductCard(product: Product) {
    val decimalFormat = DecimalFormat("#,##0.00") // Define the format
    val formattedPrice = decimalFormat.format(product.price)
    val viewModel = viewModel<ProductsViewModel>(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ProductsViewModel(ProductsRepositoryImpl(RetrofitInstance.api))
                        as T
            }
        }
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                CustomText(
                    text = product.name,
                    fontSize = 16,
                    fontWeight = FontWeight.Bold,
                    myColor = Color.Blue
                )
                Spacer(modifier = Modifier.height(4.dp))
                CustomText(
                    text = "Stock: ${product.stock}",
                    fontSize = 16,
                    fontWeight = FontWeight.Normal,
                    myColor = Color.Black
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "R$ $formattedPrice",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.DarkGray // Or your desired color
            )
            IconButton(onClick = {
                viewModel.deleteProduct(product.id!!)
            }){
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete", tint = Color.Red)
            }
        }
    }
}

