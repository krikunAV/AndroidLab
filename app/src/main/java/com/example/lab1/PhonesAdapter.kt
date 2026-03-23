package com.example.lab1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PhonesAdapter : RecyclerView.Adapter<PhonesAdapter.PhonesViewHolder>() {

    private val phonesList = ArrayList<PhoneModel>()

    fun setupPhones(list: ArrayList<PhoneModel>) {
        phonesList.clear()
        phonesList.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = phonesList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhonesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item, parent, false)
        return PhonesViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhonesViewHolder, position: Int) {
        holder.bind(phonesList[position])
    }

    class PhonesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName: TextView = itemView.findViewById(R.id.tv_phone_name)
        private val tvPrice: TextView = itemView.findViewById(R.id.tv_price)
        private val tvDate: TextView = itemView.findViewById(R.id.tv_date)
        private val tvScore: TextView = itemView.findViewById(R.id.tv_score)

        fun bind(phone: PhoneModel) {
            tvName.text = phone.name
            tvPrice.text = phone.price
            tvDate.text = phone.date
            tvScore.text = phone.score
        }
    }
}