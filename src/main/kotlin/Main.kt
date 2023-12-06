import game.tileMap.RandomTileMapGenerator
import game.tileMap.TileMapFromResource
import math.Vec2

fun main() {

    /* TODO:
        Добавить поддержку большой карты, чтобы рисовать только ограниченную область на этой карте
        Добавить команду для зума
        Добавить врагов
     */

    val map = RandomTileMapGenerator(24, 8).getTileMap()
//    val map = TileMapFromResource("/map").getTileMap()

    val charPos = map.getCenter()

    var lastDelta = Vec2(0, 0)

    while (true) {
        map.draw(charPos)

        if (map.isWin(charPos)) {
            println("  _______ ")
            println("{ YOU WIN }")
            println("  ------- ")
            break
        }

        print("Write command: ")

        val desired = charPos.copy()

        when (val command = readln()) {
            "w" -> desired.y--
            "s" -> desired.y++
            "a" -> desired.x--
            "d" -> desired.x++
            "r" -> {
                val dest = charPos + lastDelta
                if (map.isWall(dest)) {
                    map[dest] = ' '
                }
            }
            "exit" -> break
            else -> println("there is no command $command")
        }

        lastDelta = desired - charPos

        if (map.isWalkable(desired)) {
            charPos.set(desired)
        }
    }
}