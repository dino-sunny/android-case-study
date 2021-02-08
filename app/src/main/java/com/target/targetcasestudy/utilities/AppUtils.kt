package com.target.targetcasestudy.utilities

import android.graphics.Paint
import android.widget.TextView

class AppUtils {
    companion object {
        fun TextView.showStrikeThrough(show: Boolean) {
            paintFlags =
                if (show) paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                else paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
}