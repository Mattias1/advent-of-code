package gridList

fun <T> GridList<T>.reflectHorizontal(): GridList<T> {
    return GridList(this.width, this.height, { x, y -> this[x, this.height - y - 1] })
}

fun <T> GridList<T>.reflectVertical(): GridList<T> {
    return GridList(this.width, this.height, { x, y -> this[this.width - x - 1, y] })
}

fun <T> GridList<T>.rotateRight(): GridList<T> {
    return GridList(this.height, this.width, { x, y -> this[y, this.width - x - 1] })
}

fun <T> GridList<T>.rotateLeft(): GridList<T> {
    return GridList(this.height, this.width, { x, y -> this[this.height - y - 1, x] })
}
