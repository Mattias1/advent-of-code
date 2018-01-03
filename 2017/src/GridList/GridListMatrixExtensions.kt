package gridList

operator fun GridList<Int>.plus(other: GridList<Int>): GridList<Int> {
    if (width != other.width || height != other.height) {
        throw IllegalArgumentException("GridList sizes are not equal")
    }
    return GridList(width, height, { x, y -> this[x, y] + other[x, y] })
}

operator fun GridList<Int>.minus(other: GridList<Int>): GridList<Int> {
    return GridList(width, height, { x, y -> this[x, y] - other[x, y] })
}

operator fun GridList<Int>.times(other: GridList<Int>): GridList<Int> {
    if (width != other.width || height != other.height) {
        throw IllegalArgumentException("GridList sizes are not equal")
    }
    return GridList(width, height, { x, y ->
        dotProduct(this.getRow(y), other.getColumn(x))
    })
}

fun dotProduct(a: List<Int>, b: List<Int>): Int {
    if (a.count() != b.count()) {
        throw IllegalArgumentException("Vector counts are not equal")
    }
    return a.zip(b)
            .map { p -> p.first * p.second }
            .sum()
}
