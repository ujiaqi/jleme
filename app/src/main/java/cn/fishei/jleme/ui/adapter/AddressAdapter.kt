package cn.fishei.jleme.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.fishei.jleme.R
import cn.fishei.jleme.logic.model.AddressList

class AddressAdapter(private val addressList: List<AddressList>):RecyclerView.Adapter<AddressAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val notion :TextView = view.findViewById(R.id.address_one)
        val name :TextView = view.findViewById(R.id.name_one)
        val phone :TextView = view.findViewById(R.id.phone_one)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.address_recycler, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val address = addressList[position]
        holder.notion.text = address.address
        holder.name.text = address.name
        holder.phone.text = address.phone
    }

    override fun getItemCount(): Int {
        return addressList.size
    }
}