import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView

/*
package edu.coe.richmond.finalindependentapp

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import java.util.*

class GameView : View, OnTouchListener {
    private var pad: Paddle? = null
    private var finishX: Float = 0.0f
    private var topY: Float = 0.0f
    private var bottomY: Float = 0.0f
    private var startX: Float = 0.0f


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec)
        topY = (heightMeasureSpec * 0.84).toFloat()
        bottomY = (heightMeasureSpec * 0.9).toFloat()
        startX = (widthMeasureSpec * 0.4).toFloat()
        finishX = (widthMeasureSpec * 0.6).toFloat()
    }

    constructor(context: Context?) : super(context) {
        initView()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView()
    }

    private fun initView() {

    }

    fun setPaddle(pad: Paddle?) {
        this.pad = pad
    }






    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        if (event!!.action == MotionEvent.ACTION_MOVE) {
            startX = event.getRawX()
        }

        //val touchX = event!!.x.toInt()
        //val touchY = event!!.y.toInt()
        //when (event.action) {
        //    MotionEvent. -> pad!!.changeDirection(touchX)
        //}
        return false
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawColor(Color.BLACK)
        val p = Paint()
        p.color = Color.RED
        canvas.drawRect(startX, finishX, topY, bottomY, p)
        //val body: ArrayList<> = pad!!.history
        p.color = Color.BLUE
        //for (b in body) {
        //    canvas.drawRect
        //}
    }
}*/

class GameView : Activity() {
    // gameView will be the view of the game
    // It will also hold the logic of the game
    // and respond to screen touches as well
    var breakoutView: BreakoutView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize gameView and set it as the view
        breakoutView = BreakoutView(this)
        setContentView(breakoutView)
    }

    // Here is our implementation of GameView
    // It is an inner class.
    // Note how the final closing curly brace }
    // is inside SimpleGameEngine
    // Notice we implement runnable so we have
    // A thread and can override the run method.
    inner class BreakoutView(context: Context?) :
        SurfaceView(context), Runnable {
        // This is our thread
        var gameThread: Thread? = null

        // This is new. We need a SurfaceHolder
        // When we use Paint and Canvas in a thread
        // We will see it in action in the draw method soon.
        var ourHolder: SurfaceHolder = TODO()

        // A boolean which we will set and unset
        // when the game is running- or not.
        @Volatile
        var playing = false

        // Game is paused at the start
        var paused = true

        // A Canvas and a Paint object
        var canvas: Canvas = TODO()
        var paint: Paint

        // This variable tracks the game frame rate
        var fps: Long = 0

        // This is used to help calculate the fps
        private var timeThisFrame: Long = 0
        override fun run() {
            while (playing) {

                // Capture the current time in milliseconds in startFrameTime
                val startFrameTime = System.currentTimeMillis()

                // Update the frame
                // Update the frame
                if (!paused) {
                    update()
                }

                // Draw the frame
                draw()

                // Calculate the fps this frame
                // We can then use the result to
                // time animations and more.
                timeThisFrame = System.currentTimeMillis() - startFrameTime
                if (timeThisFrame >= 1) {
                    fps = 1000 / timeThisFrame
                }
            }
        }

        // Everything that needs to be updated goes in here
        // Movement, collision detection etc.
        fun update() {}

        // Draw the newly updated scene
        fun draw() {

            // Make sure our drawing surface is valid or we crash
            if (ourHolder.surface.isValid) {
                // Lock the canvas ready to draw
                canvas = ourHolder.lockCanvas()

                // Draw the background color
                canvas.drawColor(Color.argb(255, 26, 128, 182))

                // Choose the brush color for drawing
                paint.color = Color.argb(255, 255, 255, 255)

                // Draw the paddle

                // Draw the ball

                // Draw the bricks

                // Draw the HUD

                // Draw everything to the screen
                ourHolder.unlockCanvasAndPost(canvas)
            }
        }

        // If SimpleGameEngine Activity is paused/stopped
        // shutdown our thread.
        fun pause() {
            playing = false
            try {
                gameThread!!.join()
            } catch (e: InterruptedException) {
                Log.e("Error:", "joining thread")
            }
        }

        // If SimpleGameEngine Activity is started theb
        // start our thread.
        fun resume() {
            playing = true
            gameThread = Thread(this)
            gameThread!!.start()
        }

        // The SurfaceView class implements onTouchListener
        // So we can override this method and detect screen touches.
        override fun onTouchEvent(motionEvent: MotionEvent): Boolean {
            when (motionEvent.action and MotionEvent.ACTION_MASK) {
                MotionEvent.ACTION_DOWN -> {
                }
                MotionEvent.ACTION_UP -> {
                }
            }
            return true
        }

        // When the we initialize (call new()) on gameView
        // This special constructor method runs
        init {
            // The next line of code asks the
            // SurfaceView class to set up our object.
            // How kind.

            // Initialize ourHolder and paint objects
            ourHolder = holder
            paint = Paint()
        }
    }

    // This is the end of our BreakoutView inner class
    // This method executes when the player starts the game
    override fun onResume() {
        super.onResume()

        // Tell the gameView resume method to execute
        breakoutView!!.resume()
    }

    // This method executes when the player quits the game
    override fun onPause() {
        super.onPause()

        // Tell the gameView pause method to execute
        breakoutView!!.pause()
    }
}
