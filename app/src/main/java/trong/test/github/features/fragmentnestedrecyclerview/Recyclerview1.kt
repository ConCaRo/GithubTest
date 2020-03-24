package trong.test.github.features.fragmentnestedrecyclerview

import android.content.Context
import android.support.v4.view.MotionEventCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration


class Recyclerview1 : RecyclerView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int ) :  super(context, attrs, defStyle)


    var parent_requestDisallowInterceptTouchEvent = false

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        parent.requestDisallowInterceptTouchEvent(parent_requestDisallowInterceptTouchEvent)
        return super.onInterceptTouchEvent(ev)
    }

//    var mIsScrolling = false
//    var yPrec: Float = 0F
//    var mTouchSlop: Int = 0
//
//    private fun init() {
//        val vc = ViewConfiguration.get(context)
//        mTouchSlop = vc.scaledTouchSlop
//    }
//    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
//        //        parent.requestDisallowInterceptTouchEvent(parent_requestDisallowInterceptTouchEvent)
//        val action = MotionEventCompat.getActionMasked(event)
//        when (action) {
//            MotionEvent.ACTION_DOWN -> {
//                run { yPrec = event.y }
//                run {
//                    val dy: Float = event.y - yPrec
//                    if (dy > this.mTouchSlop) { // Start scrolling!
//                        mIsScrolling = true
//                    }
//                }
//            }
//            MotionEvent.ACTION_MOVE -> {
//                val dy: Float = event.y - yPrec
//                if (dy > mTouchSlop) {
//                    mIsScrolling = true
//                }
//            }
//        }
//        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
//            mIsScrolling = false
//        }
//        // Calculate the scrolldiff
//        val view: View = getChildAt(childCount - 1) as View
//        val diff: Int = view.getBottom() - (height + scrollY)
//        if ((!mIsScrolling || !listIsAtTop()) && diff == 0) {
//            if (event.action == MotionEvent.ACTION_MOVE) {
//                return false
//            }
//        }
//        return super.onInterceptTouchEvent(event)
//    }
//
//    override fun onTouchEvent(ev: MotionEvent?): Boolean {
//        val action = MotionEventCompat.getActionMasked(ev)
//        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
//            mIsScrolling = false
//        }
//        return super.onTouchEvent(ev)
//    }
//
//    fun listIsAtTop(): Boolean {
//        return if (childCount == 0) true else getChildAt(0).top == 0 && (this.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition() === 0
//    }
}