package com.example.shoppingapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.model.NetworkResponse
import com.example.shoppingapp.model.RetrofitInstance
import com.example.shoppingapp.model.data.Product
import kotlinx.coroutines.launch

class DetailViewModel() :ViewModel() {
    private var _product = MutableLiveData<NetworkResponse<Product?>>()
    val product: LiveData<NetworkResponse<Product?>> = _product
    private val productApi = RetrofitInstance.productApi

    fun getProductById(id : Int){
        viewModelScope.launch {
            try {
                val apiResponse = productApi.getData()
                if (apiResponse.isSuccessful){
                    apiResponse.body()?.products.let {
                        _product.value = NetworkResponse.Success(it?.get(id-1))
                    }
                }else{
                    _product.value = NetworkResponse.Error("Fail to load")
                }
            }catch (e : Exception){
                _product.value = NetworkResponse.Error("Fail to load")
            }


        }
    }
}