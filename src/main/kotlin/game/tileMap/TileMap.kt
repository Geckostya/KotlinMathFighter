package game.tileMap

import math.Vec2

class TileMap(val width: Int, val height: Int) {
    private val tiles = CharArray(width * height) { ' ' }
    private val screenDelimiter = "=".repeat(width)

    fun getCenter() = Vec2(width / 2, height / 2)

    fun isValid(v: Vec2): Boolean {
        return v.x in 0..<width && v.y in 0..<height
    }

    fun getTile(v: Vec2): Char {
        return tiles[getIndex(v)]
    }

    fun setTile(v: Vec2, c: Char) {
        tiles[getIndex(v)] = c
    }

    fun forEach(function: (Vec2) -> Unit) {
        val v = Vec2(0, 0)
        for (y in 0..<height) {
            for (x in 0..<width) {
                v.set(x, y)
                function(v)
            }
        }
    }

    operator fun get(v: Vec2) = getTile(v)
    operator fun set(v: Vec2, c: Char) = setTile(v, c)

    fun isWalkable(v: Vec2) = isValid(v) && getTile(v) != '#'
    fun isWall(v: Vec2) = isValid(v) && getTile(v) == '#'
    fun isWin(v: Vec2) = isValid(v) && getTile(v) == 'X'

    fun draw(charPos: Vec2) {
        println(screenDelimiter)
        val p = Vec2(0, 0)
        for (y in 0..<height) {
            for (x in 0..<width) {
                p.set(x, y)
                val isChar = charPos == p
                print(if (isChar) '@' else getTile(p))
            }
            println()
        }
        println(screenDelimiter)
    }

    private fun getIndex(v: Vec2) = v.y * width + v.x
}