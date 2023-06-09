package com.example.foodfate

import com.example.auth.AuthNavigation
import com.example.cart.CartNavigation
import com.example.mainpage.MainPageNavigation
import com.example.meals.MealsNavigation

interface Navigator: AuthNavigation, CartNavigation, MainPageNavigation, MealsNavigation