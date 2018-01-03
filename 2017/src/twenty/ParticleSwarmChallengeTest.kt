package twenty

import junit.framework.TestCase
import org.junit.Assert

class ParticleSwarmChallengeTest : TestCase() {
    private val challenge = ParticleSwarmChallenge()

    fun testClosestParticleToOrigin_simple() {
        val input = listOf(
                "p=< 3,0,0>, v=< 2,0,0>, a=<-1,0,0>",
                "p=< 4,0,0>, v=< 0,0,0>, a=<-2,0,0>"
        )
        val result = challenge.closestParticleToOrigin(input)
        Assert.assertEquals(0, result)
    }

    fun testClosestParticleToOrigin_withTies() {
        val input = listOf(
                "p=< 3,0,-2>, v=< 2,0,0>, a=<-1,0,0>",
                "p=< 4,0,0>, v=< 0,0,0>, a=<-2,0,0>",
                "p=< 3,-1,0>, v=< 2,0,0>, a=<-1,0,0>"
        )
        val result = challenge.closestParticleToOrigin(input)
        Assert.assertEquals(2, result)
    }

    fun testParticlesThatDontCollide_removeFirstStep_butNotSecond() {
        val input = listOf(
                "p=<3,0,-2>, v=<-1,0,0>, a=<-1,0,0>", // These two will collide in the first step
                "p=<4,0,-2>, v=<0,0,0>, a=<-2,0,0>",  // These two will collide in the first step
                "p=<3,-1,0>, v=<2,0,0>, a=<-1,0,0>",
                "p=<5,0,-2>, v=<-1,0,0>, a=<-2,0,0>"  // This one would collide in the second step - except that the first one is removed by then
        )
        val result = challenge.particlesThatDontCollide(input)
        Assert.assertEquals(2, result)
    }
}