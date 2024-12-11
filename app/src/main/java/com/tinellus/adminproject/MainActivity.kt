package com.tinellus.adminproject

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AssistChipDefaults.shape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
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
import com.tinellus.adminproject.data.ProductsRepositoryImpl
import com.tinellus.adminproject.data.model.Product
import com.tinellus.adminproject.ui.theme.AdminProjectTheme
import com.tinellus.adminproject.views.ProductsViewModel
import kotlinx.coroutines.flow.collectLatest

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<ProductsViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return ProductsViewModel(ProductsRepositoryImpl(RetrofitInstance.api))
                            as T
                }
            }
        })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdminProjectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val context = LocalContext.current
                    val productList = viewModel.products.collectAsState().value
                    LaunchedEffect(key1 = viewModel.showErrorToastChannel) {
                        viewModel.showErrorToastChannel.collectLatest { show ->
                            if (show) {
                                Toast.makeText(
                                    this@MainActivity,
                                    "Error loading products",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }

                    Log.d("MainActivity1", "productList: $productList")
                    if (productList.isEmpty()) {

                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize().padding(innerPadding),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            contentPadding = PaddingValues(16.dp)
                        ) {
                            items(productList.size) { index ->
                                Product(productList[index])
                                Spacer(modifier = Modifier.height(16.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}



@Composable
fun Product(product: Product) {
    Spacer(modifier = Modifier.height(10.dp))
    Column(modifier = Modifier.padding(2.dp).fillMaxSize()) {
        Row(modifier = Modifier.fillMaxSize().padding(4.dp).border(1.dp, Color.Black, shape = shape),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                CustomText(text = product.name, fontSize = 24, fontWeight = FontWeight.Bold, Color.Blue)
            }
            CustomText(text = product.price.toString(), fontSize = 20, fontWeight = FontWeight.Bold, Color.Blue)
            CustomText(text = product.stock.toString(), fontSize = 20, fontWeight = FontWeight.Bold, Color.Blue)
        }
    }
}

@Composable
fun CustomText(text: String, fontSize: Int, fontWeight: FontWeight, myColor: Color) {
    Text(text = text, fontWeight = fontWeight, fontSize = fontSize.sp, color = myColor)
}