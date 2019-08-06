package com.styleru.vitaly.hseday2018

import android.support.annotation.ColorInt
import android.animation.ValueAnimator
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import com.kekstudio.dachshundtablayout.indicators.AnimatedIndicatorInterface.DEFAULT_DURATION
import android.view.animation.LinearInterpolator
import com.kekstudio.dachshundtablayout.DachshundTabLayout
import com.kekstudio.dachshundtablayout.indicators.AnimatedIndicatorInterface

// Это кастомный индикатор(та зеленая полоска) для вкладок на экране "Про вышку"
class CustomIndicator(private val dachshundTabLayout: DachshundTabLayout) : AnimatedIndicatorInterface, ValueAnimator.AnimatorUpdateListener {

    private val paint: Paint
    private val rectF: RectF
    private val rect: Rect

    private var height: Int = 0
    private var edgeRadius: Int = 0
    private var leftX: Int = 0
    private var rightX: Int = 0

    private val valueAnimatorLeft: ValueAnimator
    private val valueAnimatorRight: ValueAnimator

    private val linearInterpolator: LinearInterpolator

    init {
        linearInterpolator = LinearInterpolator()

        valueAnimatorLeft = ValueAnimator()
        valueAnimatorLeft.duration = DEFAULT_DURATION
        valueAnimatorLeft.addUpdateListener(this)
        valueAnimatorLeft.interpolator = linearInterpolator

        valueAnimatorRight = ValueAnimator()
        valueAnimatorRight.duration = DEFAULT_DURATION
        valueAnimatorRight.addUpdateListener(this)
        valueAnimatorRight.interpolator = linearInterpolator

        rectF = RectF()
        rect = Rect()

        paint = Paint()
        paint.setAntiAlias(true)
        paint.setStyle(Paint.Style.FILL)

        leftX = dachshundTabLayout.getChildXLeft(dachshundTabLayout.currentPosition).toInt()
        rightX = dachshundTabLayout.getChildXRight(dachshundTabLayout.currentPosition).toInt()

        edgeRadius = -1
    }


    override fun onAnimationUpdate(valueAnimator: ValueAnimator) {
        leftX = valueAnimatorLeft.animatedValue as Int
        rightX = valueAnimatorRight.animatedValue as Int

        rect.top = dachshundTabLayout.height - height
        rect.left = leftX + height / 2
        rect.right = rightX - height / 2
        rect.bottom = dachshundTabLayout.height

        dachshundTabLayout.invalidate(rect)
    }

    override fun setSelectedTabIndicatorColor(@ColorInt color: Int) {
        paint.setColor(color)
    }

    override fun setSelectedTabIndicatorHeight(height: Int) {
        this.height = height

        if (edgeRadius == -1)
            edgeRadius = height
    }

    override fun setIntValues(startXLeft: Int, endXLeft: Int,
                              startXCenter: Int, endXCenter: Int,
                              startXRight: Int, endXRight: Int) {
        valueAnimatorLeft.setIntValues(startXLeft, endXLeft)
        valueAnimatorRight.setIntValues(startXRight, endXRight)
    }

    override fun setCurrentPlayTime(currentPlayTime: Long) {
        valueAnimatorLeft.currentPlayTime = currentPlayTime
        valueAnimatorRight.currentPlayTime = currentPlayTime
    }

    override fun draw(canvas: Canvas) {
        rectF.top = (dachshundTabLayout.height - height).toFloat() + 2
        rectF.left = (leftX + height / 2).toFloat() + 45
        rectF.right = (rightX - height / 2).toFloat() - 45
        rectF.bottom = dachshundTabLayout.height.toFloat() - 12

        canvas.drawRoundRect(rectF, edgeRadius.toFloat(), edgeRadius.toFloat(), paint)
    }

    override fun getDuration(): Long {
        return valueAnimatorLeft.duration
    }
}