package com.example.android_app_remote_control_joystick.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.RequiresApi
import java.lang.Integer.min

// Interface for using strategy pattern.
interface OnJoystickChange {
    fun onChange(aileron: Float, elevator: Float)
}

class Joystick @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    // Initialize the paint.
    private val paint = Paint().apply {
        style = Paint.Style.FILL_AND_STROKE
        color = Color.parseColor("black")
        isAntiAlias = true
    }
    private var radius: Float = 0f // The joystick radius.
    private var center: PointF = PointF() // The joystick center point.
    var onChange: OnJoystickChange? = null


    // calculate the initial position and size of the joystick.
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onSizeChanged(width: Int, height: Int, oldw: Int, oldh: Int) {
        radius = 0.15f * min(width, height).toFloat()
        center = PointF(width / 2.0f, height / 2.0f)
    }

    // Draw the joystick with the required position and size.
    override fun onDraw(canvas: Canvas) {
        canvas.drawCircle(center.x, center.y, radius, paint)
    }

    @SuppressLint("ClickableViewAccessibility")

    // Touching screen event - set the airplane's values.
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null) {
            return true
        }
        when (event.action) {
            MotionEvent.ACTION_DOWN -> touchMove(event.x, event.y)
            MotionEvent.ACTION_MOVE -> touchMove(event.x, event.y)
            MotionEvent.ACTION_UP -> touchMove(event.x, event.y)
        }
        return true
    }

    // Calling the on change method.
    private fun touchMove(x: Float, y: Float) {
        var newX = x
        var newY = y
        if (x > 900) {
            newX = 900F
        } else {
            if (x < 0) {
                newX = 0F
            }
        }
        if (y > 900) {
            newY = 900F
        } else {
            if (y < 0) {
                newY = 0F
            }
        }
        center = PointF(newX, newY)
        onChange?.onChange(newX, newY)

        // Render the screen again.
        invalidate()
    }
}