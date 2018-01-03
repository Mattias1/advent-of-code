package twenty

import kotlin.math.absoluteValue

class ParticleSwarmChallenge {
    fun closestParticleToOrigin(input: List<String>): Int {
        val particles = parseParticles(input)
        return particles.sorted().first().index
    }

    private fun parseParticles(input: List<String>): List<Particle> =
            input.mapIndexed { i, v -> buildParticle(i, v) }

    fun particlesThatDontCollide(input: List<String>): Int {
        val particles = parseParticles(input)
        return (0..10_000).fold(particles) { ps, _ -> simulateRound(ps) }.count()
    }

    private fun simulateRound(particles: List<Particle>): List<Particle> {
        val collidingIds: List<Int> = particles.flatMap { it.collidingParticleIds(particles) }.distinct()
        return particles
                .filter { it.index !in collidingIds }
                .map { it.step() }
    }

    private fun buildParticle(index: Int, input: String): Particle {
        val values = input.split(", ")
        return Particle(index, buildVec3(values[0]), buildVec3(values[1]), buildVec3(values[2]))
    }

    private fun buildVec3(input: String): Vec3 {
        val values = input.filter { it in ",-0123456789" }.split(',').map { it.toInt() }
        return Vec3(values[0], values[1], values[2])
    }
}


class Particle(val index: Int, val p: Vec3, val v: Vec3, val a: Vec3) : Comparable<Particle> {
    fun step(): Particle =
            Particle(index, p + v + a, v + a, a)

    fun collidingParticleIds(particles: List<Particle>): List<Int> = particles
            .filter { it.index != this.index && it.p.x == this.p.x && it.p.y == this.p.y && it.p.z == this.p.z }
            .map { it.index }

    override fun compareTo(other: Particle): Int
            = compareValuesBy(this, other, { it.a.manhattanMagnitude }, { it.v.manhattanMagnitude }, { it.p.manhattanMagnitude })
}


class Vec3(val x: Int, val y: Int, val z: Int) {
    val manhattanMagnitude: Int get() = x.absoluteValue + y.absoluteValue + z.absoluteValue

    operator fun plus(increment: Vec3): Vec3 = Vec3(x + increment.x, y + increment.y, z + increment.z)
}
