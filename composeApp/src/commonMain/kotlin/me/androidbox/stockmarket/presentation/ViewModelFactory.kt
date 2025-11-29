package me.androidbox.stockmarket.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import me.androidbox.stockmarket.data.repository.StockRepositoryImp
import kotlin.reflect.KClass

class ViewModelFactory(
    private val stockRepositoryImp: StockRepositoryImp
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
        return StockViewModel(stockRepositoryImp) as T
    }
}