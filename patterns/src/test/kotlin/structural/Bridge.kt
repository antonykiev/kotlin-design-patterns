package structural

import com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER
import org.junit.jupiter.api.Test


interface Enchantment {
    fun onActivate()
    fun apply()
    fun onDeactivate()
}

interface Weapon {
    fun wield()
    fun swing()
    fun unwield()
    val enchantment: Enchantment?
}

class FlyingEnchantment : Enchantment {
    override fun onActivate() {
        println("----The item begins to glow faintly.----")
    }

    override fun apply() {
        println("----The item flies and strikes the enemies finally returning to owner's hand.----")
    }

    override fun onDeactivate() {
        println("----The item's glow fades.----")
    }
}

class SoulEatingEnchantment : Enchantment {
    override fun onActivate() {
        println("----The item spreads bloodlust.----")
    }

    override fun apply() {
        println("----The item eats the soul of enemies.----")
    }

    override fun onDeactivate() {
        println("----Bloodlust slowly disappears.----")
    }

}

class Hammer(override val enchantment: Enchantment) : Weapon {

    override fun wield() {
        println("----The hammer is wielded.----")
        enchantment.onActivate()
    }

    override fun swing() {
        println("----The hammer is swinged.----")
        enchantment.apply()
    }

    override fun unwield() {
        println("----The hammer is unwielded.----")
        enchantment.onDeactivate()
    }
}

class Sword(override val enchantment: Enchantment) : Weapon {

    override fun wield() {
        println("----The sword is wielded.----")
        enchantment.onActivate()
    }

    override fun swing() {
        println("----The sword is swinged.----")
        enchantment.apply()
    }

    override fun unwield() {
        println("----The sword is unwielded.----")
        enchantment.onDeactivate()
    }
}

class BridgeTest {

    @Test
    fun Bridge() {
        println("----The knight receives an enchanted sword.----")
        val enchantedSword = Sword(SoulEatingEnchantment())
        enchantedSword.wield()
        enchantedSword.swing()
        enchantedSword.unwield()

        println("----The valkyrie receives an enchanted hammer.----")
        val hammer = Hammer(FlyingEnchantment())
        hammer.wield()
        hammer.swing()
        hammer.unwield()
    }
}