package creational

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

interface Plant

class OrangePlant : Plant {
    override fun toString() = "OrangePlant"
}

class ApplePlant : Plant {
    override fun toString() = "ApplePlant"
}

abstract class PlantFactory {

    abstract fun makePlant(): Plant

    companion object {
        inline fun <reified T : Plant> createFactory(): PlantFactory =
            when (T::class) {
                OrangePlant::class -> OrangeFactory()
                ApplePlant::class -> AppleFactory()
                else -> throw IllegalArgumentException()
            }
    }
}

class AppleFactory : PlantFactory() {
    override fun makePlant(): Plant = ApplePlant()
}

class OrangeFactory : PlantFactory() {
    override fun makePlant(): Plant = OrangePlant()
}

class AbstractFactoryTest {

    @Test
    fun `Abstract Factory`() {
        println("----AbstractFactoryTest STARTED----")

        val plantFactory = PlantFactory.createFactory<OrangePlant>()
        val plant = plantFactory.makePlant()
        println("----Created plant: $plant----")

        assertThat(plant).isInstanceOf(OrangePlant::class.java)

        println("----AbstractFactoryTest FINISHED----")
    }

}