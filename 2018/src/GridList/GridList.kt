package gridList

open class GridList<T> {
    private var _width: Int
    private var _height: Int
    protected val mutableList: MutableList<T>

    constructor(width: Int, height: Int, init: (Int, Int) -> T) : this(
        width,
        height,
        { p: Pair<Int, Int> -> init(p.first, p.second) })

    constructor(width: Int, height: Int, init: (Pair<Int, Int>) -> T) {
        _width = width
        _height = height
        mutableList = MutableList(width * height) { i -> init(getInverseIndex(i)) }
    }

    constructor(rows: List<List<T>>, default: T) {
        _width = rows.map { it.count() }.max() ?: 0
        _height = rows.count()
        mutableList = MutableList(_width * _height) { i ->
            val p = getInverseIndex(i)
            rows[p.second].getOrElse(p.first) {
                default
            }
        }
    }

    constructor(rows: List<List<T>>) {
        _width = rows.map { it.count() }.max() ?: 0
        _height = rows.count()
        mutableList = MutableList(_width * _height) { i ->
            val p = getInverseIndex(i)
            rows[p.second][p.first]
        }
    }

    val width: Int get() = _width
    val height: Int get() = _height
    fun count(): Int = mutableList.count()

    val indices: List<Pair<Int, Int>>
        get() = (0 until _width).flatMap { x -> (0 until _height).map { y -> Pair(x, y) } }

    val values: List<T> get() = mutableList

    fun isInRange(p: Pair<Int, Int>) = isInRange(p.first, p.second)
    fun isInRange(x: Int, y: Int): Boolean =
        x in 0 until _width && y in 0 until _height

    fun getRow(y: Int): List<T> =
        (0 until _width).map { this[it, y] }

    fun getColumn(x: Int): List<T> =
        (0 until _height).map { this[x, it] }

    val rows: List<List<T>> get() = (0 until _height).map { getRow(it) }

    val columns: List<List<T>> get() = (0 until _width).map { getColumn(it) }

    inline fun <TResult> map(crossinline transform: (T) -> TResult): GridList<TResult> =
        GridList(width, height) { x, y -> transform(this[x, y]) }

    inline fun <TResult> mapIndexed(crossinline transform: (Int, Int, T) -> TResult): GridList<TResult> =
        GridList(width, height) { x, y -> transform(x, y, this[x, y]) }

    inline fun <TResult> mapIndexed(crossinline transform: (Pair<Int, Int>, T) -> TResult): GridList<TResult> =
        GridList(width, height) { p -> transform(p, this[p]) }

    inline fun allIndexed(crossinline predicate: (Int, Int, T) -> Boolean): Boolean {
        for (y in 0 until height) {
            for (x in 0 until width) {
                if (!predicate(x, y, this[x, y])) {
                    return false
                }
            }
        }
        return true
    }

    inline fun allIndexed(crossinline predicate: (Pair<Int, Int>, T) -> Boolean): Boolean =
        allIndexed { x, y, v -> predicate(Pair(x, y), v) }

    inline fun anyIndexed(crossinline predicate: (Int, Int, T) -> Boolean): Boolean {
        for (y in 0 until height) {
            for (x in 0 until width) {
                if (predicate(x, y, this[x, y])) {
                    return true
                }
            }
        }
        return false
    }

    inline fun anyIndexed(crossinline predicate: (Pair<Int, Int>, T) -> Boolean): Boolean =
        anyIndexed { x, y, v -> predicate(Pair(x, y), v) }

    // Internal helpers
    protected fun getIndex(p: Pair<Int, Int>): Int = getIndex(p.first, p.second)

    protected fun getIndex(x: Int, y: Int): Int =
        x + y * _width

    private fun getInverseIndex(i: Int): Pair<Int, Int> =
        Pair(i % _width, i / _width)

    // List operator overloads
    operator fun get(p: Pair<Int, Int>) = get(p.first, p.second)

    operator fun get(x: Int, y: Int): T =
        mutableList[getIndex(x, y)]

    fun getOrNull(p: Pair<Int, Int>) = getOrNull(p.first, p.second)
    fun getOrNull(x: Int, y: Int): T? =
        mutableList.getOrNull(getIndex(x, y))

    fun getOrElse(x: Int, y: Int, defaultValue: (Int, Int) -> T): T =
        getOrElse(Pair(x, y)) { p -> defaultValue(p.first, p.second) }

    fun getOrElse(p: Pair<Int, Int>, defaultValue: (Pair<Int, Int>) -> T) =
        mutableList.getOrElse(getIndex(p)) { defaultValue(getInverseIndex(it)) }

    operator fun contains(value: T): Boolean = value in mutableList
}

class MutableGridList<T> : GridList<T> {
    constructor(width: Int, height: Int, init: (Int, Int) -> T) : super(width, height, init)
    constructor(width: Int, height: Int, init: (Pair<Int, Int>) -> T) : super(width, height, init)

    constructor(rows: List<List<T>>, default: T) : super(rows, default)
    constructor(rows: List<List<T>>) : super(rows)

    // Operator overloads
    operator fun set(p: Pair<Int, Int>, value: T) = set(p.first, p.second, value)

    operator fun set(x: Int, y: Int, value: T) {
        mutableList[getIndex(x, y)] = value
    }
}