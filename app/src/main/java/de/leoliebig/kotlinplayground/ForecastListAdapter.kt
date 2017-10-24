package de.leoliebig.kotlinplayground

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import de.leoliebig.kotlinplayground.domain.models.ForecastList

/**
 * Created by Leo on 24.10.2017.
 */
class ForecastListAdapter(val forecast: ForecastList) : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TextView(parent.context));
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(forecast.dailyForecast[position]) {
            holder.textView.text = "$date - $description - $high/$low"
        }

    }

    override fun getItemCount(): Int = forecast.dailyForecast.size

    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

}