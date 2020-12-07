package com.supinternet.aqi.ui.screens.main.tabs.travel

import android.provider.Settings.Global.getString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.supinternet.aqi.R
import com.supinternet.aqi.data.network.model.ranking.RankingsCountry

class CountriesAdapter(private val dataSet: List<RankingsCountry>) : RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val numberView: TextView
        val cityView: TextView
        val countryView: TextView
        val score: TextView
        val img: ImageView

        init {
            // Define click listener for the ViewHolder's View.
            numberView = view.findViewById(R.id.number)
            cityView = view.findViewById(R.id.city)
            countryView = view.findViewById(R.id.country)
            score = view.findViewById(R.id.score)
            img = view.findViewById(R.id.img)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.country_row_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        val country = dataSet[position]

        val context = viewHolder.itemView.context

        viewHolder.numberView.text = position.toString()
        viewHolder.cityView.text = country.city
        viewHolder.countryView.text = context.getString(
            context.resources.getIdentifier(
                "country_${country.countryCode}_name",
                "string",
                context.packageName)
        )

        viewHolder.img.setImageResource(context.resources.getIdentifier(
                "ic_flag_country_${country.countryCode.toLowerCase()}",
                "drawable",
                context.packageName)
        )

        viewHolder.score.text = country.aqi.toString()

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}