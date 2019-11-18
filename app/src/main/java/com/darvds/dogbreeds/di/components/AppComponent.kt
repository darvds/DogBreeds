package com.darvds.dogbreeds.di.components

import com.darvds.dogbreeds.di.modules.NetworkingModule
import com.darvds.dogbreeds.ui.breeddetails.BreedDetailsActivity
import com.darvds.dogbreeds.ui.breedlist.BreedListActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkingModule::class
    ])
interface AppComponent {
    fun inject(breedListActivity: BreedListActivity)
    fun inject(breedListActivity: BreedDetailsActivity)

}