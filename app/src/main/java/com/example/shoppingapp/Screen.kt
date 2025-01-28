package com.example.shoppingapp

enum class Screen {
    HOME,
    DETAILS
}
sealed class NavigateItem(val route: String){
    data object Home : NavigateItem(Screen.HOME.name)
    data object Details : NavigateItem(Screen.DETAILS.name)
}