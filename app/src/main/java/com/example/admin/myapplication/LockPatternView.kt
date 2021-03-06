package com.example.admin.myapplication

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Point
import android.util.AttributeSet
import android.view.View

class LockPatternView : View {
    //是否初始化， 确保只初始化一次
    private var mIsInit = false
    // 二维数组初始化，int[3][3]
    private var mPoints: Array<Array<Point?>> = Array(3) { Array<Point?>(3, { null }) }
    // 外圆的半径
    private var mDotRadius: Int = 0
    // 画笔
    private lateinit var mLinePaint: Paint
    private lateinit var mPressedPaint: Paint
    private var mErrorPaint: Paint? = null
    private lateinit var mNormalPaint: Paint
    private var mArrowPaint: Paint? = null
    // 颜色
    private val mOuterPressedColor = 0xff8cbad8.toInt()
    private val mInnerPressedColor = 0xff0596f6.toInt()
    private val mOuterNormalColor = 0xffd9d9d9.toInt()
    private val mInnerNormalColor = 0xff929292.toInt()
    private val mOuterErrorColor = 0xff901032.toInt()
    private val mInnerErrorColor = 0xffea0945.toInt()

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet, defStyleAttrs: Int) : super(context, attributeSet, defStyleAttrs)

    override fun onDraw(canvas: Canvas) {
        //初始化九个宫格 onDraw()调用多次
        if (!mIsInit) {
            initDot()
            initPaint()
            mIsInit = true
        }
        //绘制九个宫格
        drawShow(canvas)

    }

    //绘制九宫格显示
    private fun drawShow(canvas: Canvas) {
        for(i in 0..2) {
            for (point in mPoints[i]) {

                //先绘制外圆
                mNormalPaint.color = mOuterNormalColor
                canvas.drawCircle(point!!.centerX.toFloat(), point.centerY.toFloat(),
                        mDotRadius.toFloat(), mNormalPaint)

                //后绘制内圆
                mNormalPaint.color = mInnerNormalColor
                canvas.drawCircle(point!!.centerX.toFloat(), point.centerY.toFloat(),
                        mDotRadius/6.toFloat(), mNormalPaint)
            }
        }
    }

    /**
     * 初始化点
     */
    private fun initDot() {
        //九个宫格，存到集合 Point[3][3]
        //不断绘制的时候这几个点都有状态，而且后面肯定要回调密码点都有下标  点肯定是一个对象
        var width = this.width
        var height = this.height

        var offsetX = 0
        var offsetY = 0
        if (height > width){
            offsetY = (height - width)/ 2
            height = width
        } else {
            offsetX = (width - height)/2
            width = height
        }
        var squareWidth = width / 3
        //外圆的大小，根据宽度来
        mDotRadius = width / 12
        //计算和指定点的中心位置
        mPoints[0][0] = Point(offsetX + squareWidth/2, offsetY + squareWidth/2, 0)
        mPoints[0][1] = Point(offsetX + squareWidth*3/2,offsetY + squareWidth/2,1 )
        mPoints[0][2] = Point(offsetX + squareWidth*5/2,offsetY + squareWidth/2,2 )
        mPoints[1][0] = Point(offsetX + squareWidth/2,offsetY + squareWidth*3/2,3 )
        mPoints[1][1] = Point(offsetX + squareWidth*3/2,offsetY + squareWidth*3/2,4 )
        mPoints[1][2] = Point(offsetX + squareWidth*5/2,offsetY + squareWidth*3/2,5 )
        mPoints[2][0] = Point(offsetX + squareWidth/2,offsetY + squareWidth*5/2,6 )
        mPoints[2][1] = Point(offsetX + squareWidth*3/2,offsetY + squareWidth*5/2,7 )
        mPoints[2][2] = Point(offsetX + squareWidth*5/2,offsetY + squareWidth*5/2,8 )

    }

    /**
     * 初始化画笔，三个点状态的画笔，线的画笔，箭头的画笔
     */
    private fun initPaint() {
        // 线的画笔
        mLinePaint = Paint()
        mLinePaint.color = mInnerPressedColor
        mLinePaint.style = Paint.Style.STROKE
        mLinePaint.isAntiAlias = true
        mLinePaint.strokeWidth = (mDotRadius / 9).toFloat()
        // 按下的画笔
        mPressedPaint = Paint()
        mPressedPaint.style = Paint.Style.STROKE
        mPressedPaint.isAntiAlias = true
        mPressedPaint.strokeWidth = (mDotRadius / 6).toFloat()
        // 错误的画笔
        mErrorPaint = Paint()
        mErrorPaint!!.style = Paint.Style.STROKE
        mErrorPaint!!.isAntiAlias = true
        mErrorPaint!!.strokeWidth = (mDotRadius / 6).toFloat()
        // 默认的画笔
        mNormalPaint = Paint()
        mNormalPaint!!.style = Paint.Style.STROKE
        mNormalPaint!!.isAntiAlias = true
        mNormalPaint!!.strokeWidth = (mDotRadius / 9).toFloat()
        // 箭头的画笔
        mArrowPaint = Paint()
        mArrowPaint!!.color = mInnerPressedColor
        mArrowPaint!!.style = Paint.Style.FILL
        mArrowPaint!!.isAntiAlias = true
    }

    class Point(var centerX: Int, var centerY:Int, var index:Int){
        private val STATUS_NORMAL = 1
        private val STATUS_PRESSED = 2
        private val STATUS_ERROR = 3
        //当前点的状态，有三种状态
        private var status = STATUS_NORMAL
    }
}