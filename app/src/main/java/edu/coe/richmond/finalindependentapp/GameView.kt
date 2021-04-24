package edu.coe.richmond.finalindependentapp

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener

class GameView : View, OnTouchListener {
    private var pad: Paddle? = null


    constructor(context: Context?) : super(context) {
        initView()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView()
    }

    private fun initView() {
        setOnTouchListener(this)
    }

    fun setPaddle(pad: Paddle?) {
        this.pad = pad
    }






    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        TODO("Not yet implemented")
    }
}