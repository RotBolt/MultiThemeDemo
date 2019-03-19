package io.thelimitbreaker.multithemedemo.users.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import io.thelimitbreaker.multithemedemo.rest.RestClient
import io.thelimitbreaker.multithemedemo.rest.model.UserEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UsersViewModel : ViewModel() {
    private val userList = MutableLiveData<Array<UserEntity>>()

    fun getUsers(): LiveData<Array<UserEntity>> = userList

    fun loadUsers() {
        CoroutineScope(Dispatchers.Main).launch {
            val result = RestClient.getUsersAsync().await()
            userList.value = result
        }
    }
}
