package edu.coe.richmond.finalindependentapp

import android.graphics.RectF


class Paddle {
    // RectF is an object that holds four coordinates - just what we need
    private var rect: RectF? = null

    // How long and high our paddle will be
    private var length = 0f
    private var height = 0f

    // X is the far left of the rectangle which forms our paddle
    private var x = 0f

    // Y is the top coordinate
    private var y = 0f

    // This will hold the pixels per second speed that the paddle will move
    private var paddleSpeed = 0f

    // Which ways can the paddle move
    val STOPPED = 0
    val LEFT = 1
    val RIGHT = 2

    // Is the paddle moving and in which direction
    private var paddleMoving = STOPPED

    // This the the constructor method
    // When we create an object from this class we will pass
    // in the screen width and height
    fun Paddle(screenX: Int, screenY: Int) {
        // 130 pixels wide and 20 pixels high
        length = 130f
        height = 20f

        // Start paddle in roughly the sceen centre
        x = screenX / 2.toFloat()
        y = screenY - 20.toFloat()
        rect = RectF(x, y, x + length, y + height)

        // How fast is the paddle in pixels per second
        paddleSpeed = 350f
    }

    // This is a getter method to make the rectangle that
    // defines our paddle available in BreakoutView class
    fun getRect(): RectF? {
        return rect
    }

    // This method will be used to change/set if the paddle is going left, right or nowhere
    fun setMovementState(state: Int) {
        paddleMoving = state
    }

    // This update method will be called from update in BreakoutView
    // It determines if the paddle needs to move and changes the coordinates
    // contained in rect if necessary
    fun update(fps: Long) {
        if (paddleMoving == LEFT) {
            x = x - paddleSpeed / fps
        }
        if (paddleMoving == RIGHT) {
            x = x + paddleSpeed / fps
        }
        rect!!.left = x
        rect!!.right = x + length
    }

    /*var x: Int
        private set
    var maxX: Int
        private set
    //var maxY: Int
       // private set
    var history: ArrayList<Rect>
    var direction: Int
        private set
    private var size: Int
    private var moveX = 0
    //private var moveY = 0
    private var speed: Int

    constructor() {
        maxX = 0
        //maxY = 0
        x = 0
        //y = 0
        history = ArrayList()
        size = 15
        direction = UP
        speed = 25
    }

    //fun changeDirection(touchX: Int) {
    //    when (direction)
    //}








    companion object {
        const val UP = 1
        const val DOWN = 2
        const val LEFT = 3
        const val RIGHT = 4
    }*/
}