package io.thelimitbreaker.multithemedemo.users.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.thelimitbreaker.multithemedemo.R
import io.thelimitbreaker.multithemedemo.rest.model.UserEntity
import io.thelimitbreaker.multithemedemo.utils.DrawableHelper
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.adapter_item.view.*

class UsersAdapter :
    ListAdapter<UserEntity, UsersAdapter.UserViewHolder>(UserDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return UserViewHolder(inflater.inflate(R.layout.adapter_item, parent, false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class UserViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(userEntity: UserEntity) {
            with(containerView) {
                text_main.text = userEntity.username
                text_initial.text = userEntity.name[0].toString()
                text_initial.background = DrawableHelper.getRandCircleBG(context)
                text_sub.text = userEntity.email
            }
        }
    }

    class UserDiffCallback : DiffUtil.ItemCallback<UserEntity>() {
        override fun areItemsTheSame(oldItem: UserEntity, newItem: UserEntity) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: UserEntity, newItem: UserEntity) = oldItem == newItem

    }
}
