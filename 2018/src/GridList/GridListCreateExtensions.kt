package gridList

import kotlin.math.sqrt

fun <T> GridList<T>.splitToSubGrids(width: Int, height: Int): MutableGridList<MutableGridList<T>> =
    MutableGridList(this.width / width, this.height / height) { x, y -> subGrid(x * width, y * height, width, height) }

fun <T> GridList<T>.subGrid(startX: Int, startY: Int, width: Int, height: Int): MutableGridList<T> =
    MutableGridList(width, height) { x, y -> this[startX + x, startY + y] }

fun <T> gridOf(vararg args: T): MutableGridList<T> {
    val size = sqrt(args.count().toDouble()).toInt()
    return MutableGridList(size, size) { x, y -> args[x + size * y] }
}

fun <T> joinGrids(subGrids: GridList<out GridList<T>>): MutableGridList<T> {
    if (subGrids.count() == 0) {
        return MutableGridList(listOf())
    }
    val sub = subGrids[0, 0]
    val width = subGrids.width * sub.width
    val height = subGrids.height * sub.height
    return MutableGridList(
        width,
        height
    ) { x, y -> subGrids[x / sub.width, y / sub.height][x % sub.width, y % sub.height] }
}
