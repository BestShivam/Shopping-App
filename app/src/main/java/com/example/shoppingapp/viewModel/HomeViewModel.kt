package com.example.shoppingapp.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.model.NetworkResponse
import com.example.shoppingapp.model.RetrofitInstance
import com.example.shoppingapp.model.data.Product
import kotlinx.coroutines.launch

class HomeViewModel() :ViewModel() {
    private var _products = MutableLiveData<NetworkResponse<List<Product>?>>()
    val products: LiveData<NetworkResponse<List<Product>?>> = _products
    private val productApi = RetrofitInstance.productApi

    fun getProductData(){
        viewModelScope.launch {
            try {
                _products.value = NetworkResponse.Loading
                val apiResponse = productApi.getData()
                if (apiResponse.isSuccessful){
                    apiResponse.body()?.products.let {
                        _products.value = NetworkResponse.Success(it)
                    }
                }else{
                    _products.value = NetworkResponse.Error("failed to load")
                }
            }catch (e: Exception){
                _products.value = NetworkResponse.Error("failed to load")
            }

        }
    }


}