package com.example.shoppingapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shoppingapp.model.data.MyData
import com.example.shoppingapp.ui.theme.ShoppingAppTheme
import com.example.shoppingapp.view.detailView
import com.example.shoppingapp.view.homePage
import com.example.shoppingapp.viewModel.DetailViewModel
import com.example.shoppingapp.viewModel.HomeViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val homeViewModel : HomeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        val detailViewModel : DetailViewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        setContent {
            ShoppingAppTheme {
                AppNavHost(navController = rememberNavController(),
                    homeVM = homeViewModel,
                    detailVM = detailViewModel)
            }
        }
    }
}

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    homeVM : HomeViewModel,
    detailVM : DetailViewModel,
    startDestination: String = NavigateItem.Home.route
){
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ){
        composable(NavigateItem.Home.route) {
            homePage(homeVM,detailVM,navController)
        }
        composable(NavigateItem.Details.route) {
            detailView(detailVM,navController)
        }

    }
}