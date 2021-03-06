package de.leoliebig.kotlinplayground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.leoliebig.kotlinplayground.domain.commands.RequestForecastCommand
import org.jetbrains.anko.doAsyncResult
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    var name: String = ""
        get() = field.capitalize()
        set(value) {
            field = value.capitalize()
        }

    lateinit var forecastList: RecyclerView

    private val items = listOf(
            "Today - Bad weather",
            "Tomorrow - Not so bad weather",
            "Day after tomorrow - Sunny"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initLayout()
        requestForecast("10559")

        //toast(TAG)
    }

    private fun initLayout() {
        forecastList = find<RecyclerView>(R.id.forecast_list)
        forecastList.layoutManager = LinearLayoutManager(this)
    }


    private fun requestForecast(zipCode: String) {
        doAsyncResult {
            val result = RequestForecastCommand(zipCode).execute()
            uiThread { forecastList.adapter = ForecastListAdapter(result) }
        }
    }

    fun getNameSpecial(): String {
        return "$name is special";
    }

    //extension property for using the class name as tag
    val AppCompatActivity.TAG: String
        get() = javaClass.simpleName

}
 