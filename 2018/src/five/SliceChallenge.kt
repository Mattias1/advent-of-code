package five

import gridList.GridList
import parse

// Day 3
class SliceChallenge {
    fun overlappingInches(input: List<String>): Int {
        val claims = input.map { SliceClaim(it) }
        val grid = buildCountGrid(claims)

        return grid.values.count { it > 1 }
    }

    private fun buildCountGrid(claims: List<SliceClaim>): GridList<Int> {
        val maxX: Int = claims.map { it.x + it.w }.max()!!
        val maxY: Int = claims.map { it.y + it.h }.max()!!

        var grid = GridList(maxX, maxY) { _, _ -> 0 }
        for (claim in claims) {
            grid = grid.mapIndexed { x, y, v -> if (claim.inRange(x, y)) v + 1 else v }
        }

        return grid
    }

    fun intactGrid(input: List<String>): Int {
        val claims = input.map { SliceClaim(it) }
        val grid = buildCountGrid(claims)

        return claims.single { gridIsIntact(it, grid) }.id
    }

    private fun gridIsIntact(claim: SliceClaim, grid: GridList<Int>): Boolean {
        return grid.allIndexed { x, y, v -> !claim.inRange(x, y) || v == 1 }
    }
}

class SliceClaim {
    val id: Int
    val x: Int
    val y: Int
    val w: Int
    val h: Int

    constructor(input: String) {
        val parsed = parse(input, "#{} @ {},{}: {}x{}")

        id = parsed[0].toInt()
        x = parsed[1].toInt()
        y = parsed[2].toInt()
        w = parsed[3].toInt()
        h = parsed[4].toInt()
    }

    fun inRange(x: Int, y: Int): Boolean = x in this.x until this.x + w && y in this.y until this.y + h
}