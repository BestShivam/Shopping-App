package com.example.shoppingapp.view

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.shoppingapp.model.NetworkResponse
import com.example.shoppingapp.model.data.Product
import com.example.shoppingapp.viewModel.DetailViewModel

@Composable
fun detailView(viewModel: DetailViewModel, navController: NavController){

    val product = viewModel.product.observeAsState()

    when(val result = product.value){
        is NetworkResponse.Error -> {
            Text(result.message)
        }
        NetworkResponse.Loading -> {
            CircularProgressIndicator()
        }
        is NetworkResponse.Success -> {
            result.data?.let { cardView2(it) { } }
        }
        null -> {}
    }
}

@Composable
fun cardView2(data : Product, onClick : ()->Unit){
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        item{
            Card(modifier = Modifier.fillMaxWidth()
                .padding(12.dp),
                onClick = onClick
            ) {

                AsyncImage(model = data.thumbnail,
                    contentDescription = data.title,
                    modifier = Modifier.size(300.dp).fillMaxWidth())

                Column (modifier = Modifier.padding(8.dp)){
                    Text(text = data.title, fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        modifier = Modifier.padding(4.dp))
                    Text("₹"+data.discountPercentage.toString(),modifier = Modifier.padding(8.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 35.sp)
                }
            }
            Text(data.description,modifier = Modifier.padding(8.dp),
                fontSize = 16.sp)
            Card(modifier = Modifier.fillMaxWidth()) {
                Text("warranty : ${data.warrantyInformation} ", fontSize = 20.sp,
                    modifier = Modifier.padding(8.dp))
                Text("Delivery : ${data.shippingInformation}", fontSize = 20.sp,
                    modifier = Modifier.padding(8.dp))
            }

            Text("Review", fontSize = 25.sp, fontWeight = FontWeight.Medium
                , modifier = Modifier.padding(8.dp))

            for(reviewer in data.reviews ){

                Card (modifier = Modifier.padding(4.dp)){
                    Spacer(modifier = Modifier.fillMaxWidth().height(8.dp))
                    Text(reviewer.reviewerName + " : "+ reviewer.comment, modifier = Modifier.padding(horizontal = 8.dp),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium)
                    Text( reviewer.date.split("T")[0],
                        modifier = Modifier.padding(horizontal = 8.dp))
                    Spacer(modifier = Modifier.fillMaxWidth().height(8.dp))

                }

            }
        }
    }


}