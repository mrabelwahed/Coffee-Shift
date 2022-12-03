package com.ramadan.coffeeshifts.di

import android.content.Context
import com.ramadan.coffeeshifts.data.ShiftsRepositoryImpl
import com.ramadan.coffeeshifts.domain.contract.ShiftsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindShiftsRepository(
        characterRepositoryImpl: ShiftsRepositoryImpl
    ): ShiftsRepository
}

@InstallIn(SingletonComponent::class)
@Module
 object RepositoryImplModule {
    @Provides
    fun providesRepositoryImpl(@ApplicationContext appContext: Context) =
        ShiftsRepositoryImpl(appContext)
}