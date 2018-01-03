package fifteen

class DiskDefragmentationChallenge {
    fun usedSquares(salt: String): Int {
        val diskGrid = getDiskGrid(salt)
        return diskGrid.sumBy { it.count { it } }
    }

    private fun getDiskGrid(salt: String): List<List<Boolean>> {
        return (0..127).map {
            knotHash(salt + '-' + it.toString()).flatMap { toBitList(it) }
        }
    }

    private fun toBitList(n: Int): List<Boolean> =
            (7 downTo 0).map { (n shr it) % 2 == 1 }

    fun regionCount(salt: String): Int {
        val diskGrid = getDiskGrid(salt)
        val regionGrid = buildIntDiskGrid(128)

        var region = 0
        diskGrid.forEachIndexed { x, diskList ->
            diskList.forEachIndexed { y, _ ->
                if (isUnknownRegion(x, y, diskGrid, regionGrid)) {
                    region++
                    floodFill(diskGrid, regionGrid, x, y, region)
                }
            }
        }

        return region
    }

    private fun buildIntDiskGrid(size: Int): List<MutableList<Int>> =
            List(size, { MutableList(size, { 0 }) })

    private fun floodFill(diskGrid: List<List<Boolean>>, regionGrid: List<MutableList<Int>>, x: Int, y: Int, region: Int) {
        regionGrid[x][y] = region
        borders(x, y)
                .filter { isUnknownRegion(it, diskGrid, regionGrid) }
                .forEach { floodFill(diskGrid, regionGrid, it.first, it.second, region) }
    }

    private fun borders(x: Int, y: Int): List<Pair<Int, Int>> {
        return listOf(-1, 1)
                .flatMap { listOf(Pair(x + it, y), Pair(x, y + it)) }
                .filter { it.first in 0..127 && it.second in 0..127 }
    }

    private fun isUnknownRegion(p: Pair<Int, Int>, diskGrid: List<List<Boolean>>, regionGrid: List<List<Int>>): Boolean =
            isUnknownRegion(p.first, p.second, diskGrid, regionGrid)

    private fun isUnknownRegion(x: Int, y: Int, diskGrid: List<List<Boolean>>, regionGrid: List<List<Int>>): Boolean =
            diskGrid[x][y] && regionGrid[x][y] == 0


    /*
     * I totally stole this one from Ruud. Sorry.
     * I made my knot hash version in Python because at that point in time I didn't have access to the computer that I use for Kotlin.
     */
    private fun knotHash(input: String): List<Int> {
        val lengths = input.map { it.toInt() } + listOf(17, 31, 73, 47, 23)
        val rounds = (1..64).flatMap { lengths }

        val sparseHash = knot(rounds)
        return sparseHash.chunked(16) { it.reduce(Int::xor) }
    }

    private fun knot(lengths: List<Int>): List<Int> {
        val initial = (0..255).toList()
        val list = lengths.foldIndexed(initial) { skip, list, length ->
            val knot = list.drop(length) + list.take(length).reversed()
            knot.rotateLeft(skip)
        }

        // Undo all previous rotations
        val rotation = lengths.withIndex()
                .sumBy { (i, l) -> i + l } % list.size
        return list.rotateLeft(list.size - rotation)
    }

    private fun List<Int>.rotateLeft(n: Int) = drop(n % size) + take(n % size)
}