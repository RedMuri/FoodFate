package com.example.foodfate

import com.example.auth.AuthNavigation
import com.example.cart.CartNavigation
import com.example.favorite.FavoriteNavigation
import com.example.mainpage.MainPageNavigation
import com.example.meals.MealsNavigation
import com.example.profile.ProfileNavigation

interface Navigator : AuthNavigation, CartNavigation, MainPageNavigation, MealsNavigation,
    FavoriteNavigation, ProfileNavigation