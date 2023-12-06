package math

class Vec2(var x: Int, var y: Int) {
    operator fun plus(other: Vec2) = Vec2(x + other.x, y + other.y)
    operator fun minus(other: Vec2) = Vec2(x - other.x, y - other.y)
    operator fun times(other: Int) = Vec2(x * other, y * other)
    operator fun plusAssign(other: Vec2) {
        x += other.x
        y += other.y
    }
    operator fun minusAssign(other: Vec2) {
        x -= other.x
        y -= other.y
    }
    operator fun timesAssign(other: Int) {
        x *= other
        y *= other
    }

    override fun equals(other: Any?): Boolean {
        if (other is Vec2) {
            return x == other.x && y == other.y
        }
        return false
    }

    fun set(x: Int, y: Int) {
        this.x = x
        this.y = y
    }

    fun set(v: Vec2) = set(v.x, v.y)

    fun copy(): Vec2 = Vec2(x, y)
}