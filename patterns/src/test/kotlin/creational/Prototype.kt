package creational

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test


abstract class Prototype : Cloneable {
    @Throws(CloneNotSupportedException::class)
    abstract fun copy(): Any
}

abstract class Mage : Prototype() {
    @Throws(CloneNotSupportedException::class)
    abstract override fun copy(): Mage
}

abstract class Warlord : Prototype() {
    @Throws(CloneNotSupportedException::class)
    abstract override fun copy(): Warlord
}

abstract class Beast : Prototype() {
    @Throws(CloneNotSupportedException::class)
    abstract override fun copy(): Beast
}

interface HeroFactory {
    fun createMage(): Mage?
    fun createWarlord(): Warlord?
    fun createBeast(): Beast?
}

class ElfBeast : Beast {
    private var helpType: String

    constructor(helpType: String) {
        this.helpType = helpType
    }

    constructor(elfBeast: ElfBeast) {
        helpType = elfBeast.helpType
    }

    @Throws(CloneNotSupportedException::class)
    override fun copy(): Beast {
        return ElfBeast(this)
    }

    override fun toString(): String {
        return "Elven eagle helps in $helpType"
    }
}

class ElfMage : Mage {
    private var helpType: String

    constructor(helpType: String) {
        this.helpType = helpType
    }

    constructor(elfMage: ElfMage) {
        helpType = elfMage.helpType
    }

    @Throws(CloneNotSupportedException::class)
    override fun copy(): ElfMage {
        return ElfMage(this)
    }

    override fun toString(): String {
        return "Elven mage helps in $helpType"
    }
}

class ElfWarlord : Warlord {
    private var helpType: String

    constructor(helpType: String) {
        this.helpType = helpType
    }

    constructor(elfWarlord: ElfWarlord) {
        helpType = elfWarlord.helpType
    }

    @Throws(CloneNotSupportedException::class)
    override fun copy(): ElfWarlord {
        return ElfWarlord(this)
    }

    override fun toString(): String {
        return "Elven warlord helps in $helpType"
    }
}

class OrcBeast : Beast {
    private var weapon: String

    constructor(weapon: String) {
        this.weapon = weapon
    }

    constructor(orcBeast: OrcBeast) {
        weapon = orcBeast.weapon
    }

    @Throws(CloneNotSupportedException::class)
    override fun copy(): Beast {
        return OrcBeast(this)
    }

    override fun toString(): String {
        return "Orcish wolf attacks with $weapon"
    }
}

class OrcMage : Mage {
    private var weapon: String

    constructor(weapon: String) {
        this.weapon = weapon
    }

    constructor(orcMage: OrcMage) {
        weapon = orcMage.weapon
    }

    @Throws(CloneNotSupportedException::class)
    override fun copy(): OrcMage {
        return OrcMage(this)
    }

    override fun toString(): String {
        return "Orcish mage attacks with $weapon"
    }
}

class OrcWarlord : Warlord {
    private var weapon: String

    constructor(weapon: String) {
        this.weapon = weapon
    }

    constructor(orcWarlord: OrcWarlord) {
        weapon = orcWarlord.weapon
    }

    @Throws(CloneNotSupportedException::class)
    override fun copy(): OrcWarlord {
        return OrcWarlord(this)
    }

    override fun toString(): String {
        return "Orcish warlord attacks with $weapon"
    }
}

class HeroFactoryImpl(mage: Mage, warlord: Warlord, beast: Beast) : HeroFactory {
    private val mage: Mage = mage
    private val warlord: Warlord = warlord
    private val beast: Beast = beast

    override fun createMage(): Mage? {
        return try {
            mage.copy()
        } catch (e: CloneNotSupportedException) {
            null
        }
    }

    override fun createWarlord(): Warlord? {
        return try {
            warlord.copy()
        } catch (e: CloneNotSupportedException) {
            null
        }
    }

    override fun createBeast(): Beast? {
        return try {
            beast.copy()
        } catch (e: CloneNotSupportedException) {
            null
        }
    }
}


class PrototypeTest {

    @Test
    fun Prototype() {
        var factory: HeroFactory

        factory = HeroFactoryImpl(ElfMage("cooking"), ElfWarlord("cleaning"), ElfBeast("protecting"))
        var mage: Mage? = factory.createMage()
        var warlord: Warlord? = factory.createWarlord()
        var beast: Beast? = factory.createBeast()
        println(mage.toString())
        println(warlord.toString())
        println(beast.toString())

        factory = HeroFactoryImpl(OrcMage("axe"), OrcWarlord("sword"), OrcBeast("laser"))
        mage = factory.createMage()
        warlord = factory.createWarlord()
        beast = factory.createBeast()
        println(mage.toString())
        println(warlord.toString())
        println(beast.toString())

        Assertions.assertThat(mage.toString()).contains("axe")
        Assertions.assertThat(warlord.toString()).contains("sword")
        Assertions.assertThat(beast.toString()).contains("laser")
    }
}