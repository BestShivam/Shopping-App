package com.example.shoppingapp.view

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.shoppingapp.NavigateItem
import com.example.shoppingapp.model.Constants
import com.example.shoppingapp.model.NetworkResponse
import com.example.shoppingapp.model.data.Product
import com.example.shoppingapp.viewModel.DetailViewModel
import com.example.shoppingapp.viewModel.HomeViewModel
import com.valentinilk.shimmer.shimmer


@Composable
fun homePage(homeVM: HomeViewModel,detailVM: DetailViewModel,navController: NavController) {
    var productResult = homeVM.products.observeAsState()
    var selectedTabIndex by remember { mutableStateOf(0) }
    LaunchedEffect (Unit){
        homeVM.getProductData()
    }
    Column (modifier = Modifier.fillMaxSize()
        .padding(top = 32.dp, start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top)
    {
        // lifestyle -> "beauty" and "fragrances"
        // home essential -> "furniture" and "groceries"
        TabRow(selectedTabIndex = selectedTabIndex) {
            listOf("Lifestyle","Home essentials").forEachIndexed { index, title ->
                Tab(selected = selectedTabIndex == index,
                    onClick = {selectedTabIndex = index},
                    text = {Text("$title")},
                )
            }
        }
        when(val result = productResult.value){
            is NetworkResponse.Error -> {
                Text(result.message)
            }
            NetworkResponse.Loading -> {
                LazyColumn {
                    items(10){
                        ShimmerEffect()
                    }
                }
            }
            is NetworkResponse.Success -> {
                result.data.let {
                    if (it != null) {
                        LazyColumn {
                            items(it){product->
                                when(selectedTabIndex){
                                    0 -> {
                                        if (product.category == Constants.BEAUTY ||product.category == Constants.FRAGRANCE){
                                            cardView(product) {
                                                detailVM.getProductById(product.id)
                                                navController.navigate(NavigateItem.Details.route)
                                            }
                                        }
                                    }
                                    1 -> {
                                        if (product.category == Constants.FURNITURE ||product.category == Constants.GROCERIES){
                                            cardView(product) {
                                                detailVM.getProductById(product.id)
                                                navController.navigate(NavigateItem.Details.route)
                                            }
                                        }
                                    }

                                }


                            }
                        }
                    }

                }
            }
            null -> {
            }
        }


    }
}

@Composable
fun cardView(data : Product,onClick : ()->Unit){
    Card(modifier = Modifier.fillMaxWidth()
        .padding(12.dp),
        onClick = onClick
    ) {
        Row (verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center){
            AsyncImage(model = data.thumbnail,
                contentDescription = data.title)
            Column (modifier = Modifier.padding(8.dp)){
                Text(text = data.title, fontWeight = FontWeight.Bold,
                    fontSize = 20.sp)
                Text(text = "Rating : "+data.rating.toString())
                Text(text = "category : "+data.category)
            }
        }

    }
}

@Composable
fun ShimmerEffect(){
    Column (verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
            .padding(16.dp)){
        Card (modifier = Modifier.fillMaxWidth()
        ){
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Box(modifier = Modifier.size(100.dp)
                    .clip(RoundedCornerShape(40.dp))
                    .shimmer()
                    .padding(10.dp)
                    .background(Color.Gray)
                ){
                }
                Column (modifier = Modifier.padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                ){
                    Box(modifier = Modifier.height(20.dp).width(200.dp).shimmer()
                        .clip(RoundedCornerShape(5.dp))
                        .background(Color.Gray))
                    Spacer(Modifier.fillMaxWidth().height(10.dp))
                    Box(modifier = Modifier.height(20.dp)
                        .width(100.dp).shimmer().clip(RoundedCornerShape(5.dp))
                        .background(Color.Gray))
                    Spacer(Modifier.fillMaxWidth().height(5.dp))
                    Box(modifier = Modifier.height(20.dp).width(80.dp).shimmer()
                        .clip(RoundedCornerShape(5.dp))
                        .background(Color.Gray))
                }
            }

        }
    }
}



