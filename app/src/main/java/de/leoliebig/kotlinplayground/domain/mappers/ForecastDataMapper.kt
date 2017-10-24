package de.leoliebig.kotlinplayground.domain.mappers

import de.leoliebig.kotlinplayground.data.Forecast
import de.leoliebig.kotlinplayground.data.ForecastResult
import de.leoliebig.kotlinplayground.domain.models.ForecastList
import java.text.DateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import de.leoliebig.kotlinplayground.domain.models.Forecast as ModelForecast

/**
 * Created by Leo on 24.10.2017.
 */
class ForecastDataMapper {

    fun convertFromDataModel(forecast: ForecastResult): ForecastList =
            ForecastList(forecast.city.name, forecast.city.country, convertForecastList(forecast.list))

    private fun convertForecastList(listDataModel: List<Forecast>): List<ModelForecast> {
        return listDataModel.mapIndexed { i, forecast ->
            val dt = Calendar.getInstance().timeInMillis + TimeUnit.DAYS.toMillis(i.toLong())
            convertForecastItemToDomain(forecast.copy(dt = dt))
        }
    }

    private fun convertForecastItemToDomain(forecast: Forecast): ModelForecast {
        return ModelForecast(convertDate(forecast.dt), forecast.weather[0].description,
                forecast.temp.max.toInt(), forecast.temp.min.toInt())
    }

    private fun convertDate(date: Long): String {
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return df.format(date)
    }

}