package com.android_lesson

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class Q_Film_Adapter(private val mContext: Context, private val films: List<Q_Film>) :
    RecyclerView.Adapter<Q_Film_Adapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var filmImage: ImageView
        var filmName: TextView
        var filmPrice: TextView
        var filmBtnAddToCart: Button

        init {
            filmImage = view.findViewById(R.id.imageView_film_img)
            filmName = view.findViewById(R.id.textView_film_name)
            filmPrice = view.findViewById(R.id.textView_film_price)
            filmBtnAddToCart = view.findViewById(R.id.button_film_addtocart)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.card_design_film, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return films.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val film = films[position]
        holder.filmName.text = film.film_name
        holder.filmPrice.text = "€${film.film_price}"

//        holder.filmImage.setImageResource(R.drawable.film_django) // Static image, not dynamic
        // Dynamically set image resource by name from drawable folder
        holder.filmImage
            .setImageResource(mContext.resources.getIdentifier("film_${film.film_image}", "drawable", mContext.packageName))

        holder.filmBtnAddToCart.setOnClickListener {
            Toast.makeText(mContext, "Added to cart: ${film.film_name}", Toast.LENGTH_SHORT).show()
        }
    }
}