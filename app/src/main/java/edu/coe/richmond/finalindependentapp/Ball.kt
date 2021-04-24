package edu.coe.richmond.finalindependentapp

import kotlin.math.abs

class Ball {
    var x: Int
        private set
    var y: Int
        private set
    var maxX: Int
        private set
    var maxY: Int
        private set

    var direction: Int
        private set
    private var size: Int
    private var moveX = 0
    private var moveY = 0
    private var speed: Int

    constructor() {
        maxX = 0
        maxY = 0
        x = 0
        y = 0
        size = 15
        direction = UP
        speed = 25
    }

    constructor(maxX: Int, maxY: Int) {
        this.maxX = maxX
        this.maxY = maxY
        x = this.maxX / 2
        y = this.maxY / 2
        size = 25
        direction = UP
        speed = 5
    }

    fun setMomentum(xMomentum: Float, yMomentum:Float){
        moveX = if (abs(xMomentum) < 3)  0 else xMomentum.toInt()
        moveY = if (abs(yMomentum) < 3)  0 else yMomentum.toInt()
    }

    fun ChangeDirection(touchX: Int, touchY: Int) {
        when (direction) {
            DOWN, UP -> {
                moveY = 0
                if (touchX < x) {
                    direction = LEFT
                    moveX = -speed
                } else {
                    direction = RIGHT
                    moveX = speed
                }
            }
            LEFT, RIGHT -> {
                moveX = 0
                if (touchY < y) {
                    direction = UP
                    moveY = -speed
                } else {
                    direction = DOWN
                    moveY = speed
                }
            }
        }
    }

    fun move() {
        val newX = x + moveX
        val newY = y + moveY
        if (newX > 0 && newX < maxX && newY > 0 && newY < maxY) {
            x += moveX
            y += moveY
        }
    }

    companion object {
        const val UP = 1
        const val DOWN = 2
        const val LEFT = 3
        const val RIGHT = 4
    }
}