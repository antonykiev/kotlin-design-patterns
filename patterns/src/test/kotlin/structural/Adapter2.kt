package structural

import org.junit.jupiter.api.Test
import kotlin.math.pow
import kotlin.math.sqrt

class RoundHole(private val radius: Double) {

    fun fits(peg: RoundPeg): Boolean {
        return radius >= peg.radius
    }

}

open class RoundPeg {
    open var radius = 0.0

    constructor()
    constructor(radius: Double) {
        this.radius = radius
    }
}

class SquarePeg(val width: Double) {

    val square: Double
        get() {
            return width.pow(2.0)
        }
}

class SquarePegAdapter(private val peg: SquarePeg) : RoundPeg() {

    override var radius: Double
        get() {
            return sqrt((peg.width / 2).pow(2.0) * 2)
        }
        set(radius) {
            super.radius = radius
        }
}

class Adapter2Test {

    @Test
    fun Adapter() {
        println("----Adapter2Test STARTED----")

        val hole = RoundHole(5.0)
        val rpeg = RoundPeg(5.0)
        if (hole.fits(rpeg)) {
            println("Round peg r5 fits round hole r5.")
        }

        val smallSqPeg = SquarePeg(2.0)
        val largeSqPeg = SquarePeg(20.0)

        val smallSqPegAdapter = SquarePegAdapter(smallSqPeg)
        val largeSqPegAdapter = SquarePegAdapter(largeSqPeg)
        if (hole.fits(smallSqPegAdapter)) {
            println("Square peg w2 fits round hole r5.")
        }
        if (!hole.fits(largeSqPegAdapter)) {
            println("Square peg w20 does not fit into round hole r5.")
        }

        println("----Adapter2Test FINISHED----")

    }
}