package game.tileMap

import extensions.nextVec2
import math.Vec2
import kotlin.random.Random

abstract class AbstractTileMapGenerator {
    abstract fun getTileMap(): TileMap
}

class RandomTileMapGenerator(val width: Int, val height: Int, val seed: Long = System.currentTimeMillis()) : AbstractTileMapGenerator() {
    override fun getTileMap(): TileMap {
        println("Tile map generated with seed: $seed")

        val map = TileMap(width, height)

        val random = Random(seed)
        map.forEach { v: Vec2 ->
            val r = random.nextFloat()
            map[v] = if (r < 0.2f) '#' else ' '
        }
        map[random.nextVec2(width, height)] = 'X'

        return map
    }
}

class TileMapFromResource(val resourceName: String) : AbstractTileMapGenerator() {
    override fun getTileMap(): TileMap {
        // TODO: catch exceptions
        val resource = javaClass.getResourceAsStream(resourceName)
        val reader = resource!!.bufferedReader()

        val width = reader.readLine().toInt()
        val height = reader.readLine().toInt()

        val map = TileMap(width, height)

        for (y in 0..<height) {
            val line = reader.readLine().padEnd(width, ' ')
            for (x in line.indices) {
                map[Vec2(x, y)] = line[x]
            }
        }

        return map
    }
}