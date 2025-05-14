package com.android_lesson

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class P_RVAdapter(private val mContext: Context, private val countries: List<P_Country>) :
    RecyclerView.Adapter<P_RVAdapter.CardViewHolder>() {

    inner class CardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var rowCardView: CardView = view.findViewById(R.id.row_cardView)
        var rowTextView: TextView = view.findViewById(R.id.textView_cardDesign)
        var moreImg: ImageView = view.findViewById(R.id.img_cardView_more)

        /* Alternative
        var rowCardView: CardView
        var rowTextView: TextView
        var moreImg: ImageView

        init {
            rowCardView = view.findViewById(R.id.row_cardView)
            rowTextView = view.findViewById(R.id.textView_cardDesign)
            moreImg = view.findViewById(R.id.img_cardView_more)
        }
         */
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.card_design, parent, false)
        return CardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val country = countries[position]
        holder.rowTextView.text = country.name
        holder.rowCardView.setOnClickListener {
            Toast.makeText(mContext, "Selected: ${country.name}", Toast.LENGTH_SHORT).show()
        }
    }
}