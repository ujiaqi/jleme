package cn.fishei.jleme.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import cn.fishei.jleme.logic.model.Goods
import cn.fishei.jleme.logic.repository.Repository

class MenuViewModel: ViewModel()  {


    private val menuLiveData = MutableLiveData<String>()

    private val searchLiveData = MutableLiveData<String>()

    val goodsList = ArrayList<Goods>()


    val menuData = Transformations.switchMap(menuLiveData){ id->
        Repository.getGoodsList(id)
    }

    fun resultMenu(id: String){
        menuLiveData.value = id
    }

    val searchGoods = Transformations.switchMap(searchLiveData){ key->
        Repository.searchGoods(key)
    }

    fun resultSearch(key: String){
        searchLiveData.value = key
    }

}