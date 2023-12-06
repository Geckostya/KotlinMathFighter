package extensions

import math.Vec2
import kotlin.random.Random

fun Random.nextVec2(untilX: Int, untilY: Int) = Vec2(nextInt(untilX), nextInt(untilY))