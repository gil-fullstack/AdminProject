package com.tinellus.adminproject.views

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.tinellus.adminproject.RetrofitInstance
import com.tinellus.adminproject.components.MyTopAppBar
import com.tinellus.adminproject.data.Api
import com.tinellus.adminproject.data.ProductsRepositoryImpl
import com.tinellus.adminproject.data.model.Product
import kotlinx.coroutines.runBlocking


@Composable
fun ProductRegistrationScreen(
    navController: NavController,
    //onProductRegistered: () -> Unit // Callback when product is registered
) {
    
    val viewModel = viewModel<ProductsViewModel>(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ProductsViewModel(ProductsRepositoryImpl(RetrofitInstance.api))
                        as T
            }
        }
    )
    

    var productName by remember { mutableStateOf("") }
    var productStock by remember { mutableStateOf("") }
    var productPrice by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            MyTopAppBar(
                title = "Cadastro de Products",
                menuItems = listOf("Home", "Products", "Login"),
                navController
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize().padding(innerPadding)
                .background(Color(0x3B9E72FF)),
        ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

                OutlinedTextField(
                    value = productName,
                    onValueChange = { productName = it },
                    label = { Text("Product Name") },
                    modifier = Modifier.padding(bottom = 16.dp).background(color = Color.White)
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = productStock,
                    onValueChange = { productStock = it },
                    label = { Text("Stock") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.padding(bottom = 16.dp).background(color = Color.White)
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = productPrice,
                    onValueChange = { productPrice = it },
                    label = { Text("Price") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.padding(bottom = 16.dp).background(color = Color.White)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {

                        val productRequest = productsRequest(
                            name = productName,
                            stock = productStock.toInt(),
                            price = productPrice.toDouble()
                        )
                        viewModel.registerProduct(productRequest )

                        navController.navigate("products")
                    }

                ) {
                    Text("SALVAR")
                }
            }

        }

    }
}
fun postData(productName:String, productStock:Int, productPrice:Double) {
    val retrofit = createRetrofitInstance()

    val apiService = retrofit.create(Api::class.java)

    val productRequest = productsRequest(
        name = productName,
        stock = productStock.toInt(),
        price = productPrice.toDouble()
    )

    try {
        val response = runBlocking { // Use runBlocking for suspending functions
            apiService.createProduct(productRequest)            // Pass the LoginRequest object
        }
        Log.d("Response", response.toString())
    }catch (e: Exception){
        Log.d("Error", e.toString())
    }

}


data class productsRequest(
    val name: String,
    val stock: Int,
    val price: Double
)