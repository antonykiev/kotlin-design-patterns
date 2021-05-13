package behavioural

import com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER
import org.junit.jupiter.api.Test


enum class WeatherType {
    SUNNY, RAINY, WINDY, COLD;

    override fun toString(): String {
        return name.toLowerCase()
    }
}

interface WeatherObserver {
    fun update(currentWeather: WeatherType?)
}

class Weather {
    private var currentWeather: WeatherType
    private val observers: MutableList<WeatherObserver>

    fun addObserver(obs: WeatherObserver) {
        observers.add(obs)
    }

    fun removeObserver(obs: WeatherObserver?) {
        observers.remove(obs)
    }

    fun timePasses() {
        val enumValues = WeatherType.values()
        currentWeather = enumValues[(currentWeather.ordinal + 1) % enumValues.size]
        println("The weather changed to {}. $currentWeather")
        notifyObservers()
    }

    private fun notifyObservers() {
        observers.forEach { obs -> obs.update(currentWeather) }
    }

    init {
        observers = ArrayList()
        currentWeather = WeatherType.SUNNY
    }
}

class Orcs : WeatherObserver {
    override fun update(currentWeather: WeatherType?) {
        when (currentWeather) {
            WeatherType.COLD -> println("The orcs are freezing cold.")
            WeatherType.RAINY -> println("The orcs are dripping wet.")
            WeatherType.SUNNY -> println("The sun hurts the orcs' eyes.")
            WeatherType.WINDY -> println("The orc smell almost vanishes in the wind.")
            else -> {
            }
        }
    }
}

class Hobbits : WeatherObserver {
    override fun update(currentWeather: WeatherType?) {
        when (currentWeather) {
            WeatherType.COLD -> println("The hobbits are shivering in the cold weather.")
            WeatherType.RAINY -> println("The hobbits look for cover from the rain.")
            WeatherType.SUNNY -> println("The happy hobbits bade in the warm sun.")
            WeatherType.WINDY -> println("The hobbits hold their hats tightly in the windy weather.")
            else -> {
            }
        }
    }
}

class ObserverTest {

    @Test
    fun Observer() {

        val weather = Weather()
        weather.addObserver(Orcs())
        weather.addObserver(Hobbits())

        weather.timePasses()
        weather.timePasses()
        weather.timePasses()
        weather.timePasses()

        println("--Running generic version--")
        val gWeather = Weather()
        gWeather.addObserver(Orcs())
        gWeather.addObserver(Hobbits())

        gWeather.timePasses()
        gWeather.timePasses()
        gWeather.timePasses()
        gWeather.timePasses()
    }
}