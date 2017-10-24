package de.leoliebig.kotlinplayground.domain.commands

import de.leoliebig.kotlinplayground.data.ForecastRequest
import de.leoliebig.kotlinplayground.domain.mappers.ForecastDataMapper
import de.leoliebig.kotlinplayground.domain.models.ForecastList

/**
 * Created by Leo on 24.10.2017.
 */
class RequestForecastCommand(val zipCode: String) : Command<ForecastList> {

    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(forecastRequest.execute())
    }

}