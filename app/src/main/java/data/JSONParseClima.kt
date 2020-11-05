package data

import model.Clima
import model.Lugar
import org.json.JSONException
import org.json.JSONObject
import util.Utils

object JSONParseClima {

    fun getWeather(data: String): Clima {
        val clima = Clima()

        try {
            val jsonObject = JSONObject(data)
            val lugar = Lugar()
            val coordObj = Utils.getObject("coord", jsonObject)
            lugar.lat = Utils.getFloat("lat", coordObj)
            lugar.lon = Utils.getFloat("lon", coordObj)

            val sysObj = Utils.getObject("sys", jsonObject)
            lugar.pais = Utils.getString("country", sysObj)
            lugar.amanecer = Utils.getLong("sunrise", sysObj)
            lugar.puestaSol = Utils.getLong("sunset", sysObj)

            lugar.ciudad = Utils.getString("name", jsonObject)
            lugar.ultimaActualizacion = Utils.getLong("dt", jsonObject)
            clima.lugar = lugar

            val mainObject = Utils.getObject("main", jsonObject)
            clima.condicionActual.humedad = Utils.getFloat("humidity", mainObject)
            clima.condicionActual.temperatura = Utils.getDouble("temp", mainObject)
            clima.condicionActual.presion = Utils.getFloat("pressure", mainObject)
            clima.condicionActual.maxTemp = Utils.getFloat("temp_max", mainObject)
            clima.condicionActual.minTemp = Utils.getFloat("temp_min", mainObject)
            clima.condicionActual.minTemp = Utils.getFloat("temp_min", mainObject)

            // de la sgte forma traemos un array
            val jsonArray = jsonObject.getJSONArray("weather")
            val jsonWeather = jsonArray.getJSONObject(0)
            clima.condicionActual.weatherId = Utils.getInt("id", jsonWeather)
            clima.condicionActual.descripcion = Utils.getString("description", jsonWeather)
            clima.condicionActual.icono = Utils.getString("icon", jsonWeather)
            clima.condicionActual.condicion = Utils.getString("main", jsonWeather)

            val vientoObj = Utils.getObject("wind", jsonObject)
            clima.viento.velocidad = Utils.getFloat("speed", vientoObj)
            clima.viento.cent = Utils.getFloat("deg", vientoObj)

            val nubeObj = Utils.getObject("clouds", jsonObject)
            clima.nubes.precipitacion = Utils.getInt("all", nubeObj)



        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return clima
    }
}