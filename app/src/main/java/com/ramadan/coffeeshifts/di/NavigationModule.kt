package com.ramadan.coffeeshifts.di

import com.ramadan.coffeeshifts.navigation.AppNavigator
import com.ramadan.coffeeshifts.navigation.Navigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
abstract class NavigationModule {
    @Binds
    abstract fun bindsAppNavigator(appNavigator: AppNavigator) : Navigator
}