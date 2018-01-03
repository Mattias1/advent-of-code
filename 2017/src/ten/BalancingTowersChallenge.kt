package ten

class BalancingTowersChallenge {
    fun getBase(input: List<String>): BalancingTower {
        val towers = buildTowerMap(input)
        return getTowerBase(towers.values)
    }

    private fun buildTowerMap(input: List<String>): Map<String, BalancingTower> {
        return input.map { BalancingTower(it) }.associateBy { it.Name }
    }

    private fun getTowerBase(towers: Collection<BalancingTower>): BalancingTower {
        return towers.first { t ->
            towers.none { it.OtherNames.contains(t.Name) }
        }
    }

    fun getImbalancedFixWeight(input: List<String>): Int {
        val towers = buildTowerMap(input)
        val root = getTowerBase(towers.values)
        return buildTowerTree(towers, root)
    }

    private fun buildTowerTree(towers: Map<String, BalancingTower>, currentRoot: BalancingTower): Int {
        if (currentRoot.OtherNames.isEmpty()) {
            currentRoot.TotalWeight = currentRoot.Weight
            return 0
        }

        currentRoot.OtherTowers = currentRoot.OtherNames.map {
            val other = towers[it]!!
            val fixWeight = buildTowerTree(towers, other)
            if (fixWeight > 0) {
                return@buildTowerTree fixWeight
            }
            other
        }
        currentRoot.TotalWeight = currentRoot.Weight + currentRoot.totalSubWeights()

        if (currentRoot.OtherTowers.any { it.TotalWeight != currentRoot.OtherTowers.first().TotalWeight }) {
            return getFixWeight(currentRoot.OtherTowers)
        }

        return 0
    }

    private fun getFixWeight(towers: List<BalancingTower>): Int {
        val a = towers[0]
        val b = towers[1]
        val c = towers[2]

        return when {
            a.TotalWeight == b.TotalWeight -> a.TotalWeight - c.totalSubWeights()
            a.TotalWeight == c.TotalWeight -> a.TotalWeight - b.totalSubWeights()
            else -> b.TotalWeight - a.totalSubWeights()
        }
    }
}

class BalancingTower {
    val Name: String
    val Weight: Int
    val OtherNames: List<String>

    var OtherTowers: List<BalancingTower> = listOf()
    var TotalWeight: Int = 0

    constructor(input: String) {
        val inputs = input.split(' ').filter { it.isNotBlank() }

        Name = inputs[0]
        Weight = inputs[1].trim('(', ')').toInt()
        OtherNames = if (inputs.count() > 2) inputs.drop(3).map { it.trim(',') } else listOf()
    }

    fun totalSubWeights(): Int {
        return OtherTowers.sumBy { it.TotalWeight }
    }
}
