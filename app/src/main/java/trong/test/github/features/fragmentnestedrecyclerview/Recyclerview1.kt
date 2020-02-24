package trong.test.github.features.fragmentnestedrecyclerview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.MotionEvent

class Recyclerview1 : RecyclerView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int ) :  super(context, attrs, defStyle)


    var parent_requestDisallowInterceptTouchEvent = false

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        parent.requestDisallowInterceptTouchEvent(parent_requestDisallowInterceptTouchEvent)
        return super.onInterceptTouchEvent(ev)
    }

}