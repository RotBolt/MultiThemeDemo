package io.thelimitbreaker.multithemedemo.users.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.thelimitbreaker.multithemedemo.R
import io.thelimitbreaker.multithemedemo.users.ui.adapter.UsersAdapter
import io.thelimitbreaker.multithemedemo.users.viewModel.UsersViewModel
import kotlinx.android.synthetic.main.users_fragment.*

internal const val TAG_USER_FRAGMENT = "user_frag"

class UsersFragment : Fragment() {

    companion object {
        fun newInstance() = UsersFragment()
    }

    private lateinit var viewModel: UsersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.users_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UsersViewModel::class.java)
        val adapter = UsersAdapter()
        setupRecyclerView(adapter)
        viewModel.getUsers().observe(this, Observer { userArray ->
            Log.i("PUI", "array ${userArray.asList().size}")
            showLoadingScreen(false)
            adapter.submitList(userArray.asList())
        })

        showLoadingScreen(true)
        viewModel.loadUsers()
    }

    private fun setupRecyclerView(adapter: UsersAdapter) {
        rv_users.adapter = adapter
        rv_users.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }

    private fun showLoadingScreen(status: Boolean) {
        if (status) {
            loader_user.isVisible = true
            rv_users.isVisible = false
        } else {
            loader_user.isVisible = false
            rv_users.isVisible = true
        }
    }

}
