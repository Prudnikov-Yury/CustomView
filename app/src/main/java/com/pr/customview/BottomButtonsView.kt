package com.pr.customview

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.pr.customview.databinding.PartButtonsBinding

class BottomButtonsView(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int,
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val binding: PartButtonsBinding

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(
        context,
        attrs,
        defStyleAttr,
        0
    )

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.part_buttons, this, true)
        binding = PartButtonsBinding.bind(this)
        initializeAttributes(attrs, defStyleAttr, defStyleRes)
    }

    private fun initializeAttributes(attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.BottomButtonsView,
            defStyleAttr,
            defStyleRes
        )

        with(binding) {
            val positiveButtonText = typedArray.getString(R.styleable.BottomButtonsView_bottomPositiveButtonText)
            binding.positiveButton.text = positiveButtonText ?: "Ok"

            val negativeButtonText = typedArray.getString(R.styleable.BottomButtonsView_bottomNegativeButtonText)
            binding.negativeButton.text = negativeButtonText ?: "Cancel"

            val bottomPositiveBackgroundColor = typedArray.getColor(R.styleable.BottomButtonsView_bottomPositiveBackgroundColor, Color.BLACK)
            binding.positiveButton.backgroundTintList = ColorStateList.valueOf(bottomPositiveBackgroundColor)

            val bottomNegativeBackgroundColor = typedArray.getColor(
                R.styleable.BottomButtonsView_bottomNegativeBackgroundColor,
                Color.WHITE
            )
            binding.negativeButton.backgroundTintList = ColorStateList.valueOf(bottomNegativeBackgroundColor)

            val isProgressMode = typedArray.getBoolean(R.styleable.BottomButtonsView_bottomProgressMode, false)

            if (isProgressMode) {
                positiveButton.isVisible = false
                negativeButton.isVisible = false
                progress.isVisible = true
            } else {
                positiveButton.isVisible = true
                negativeButton.isVisible = true
                progress.isVisible = false
            }
        }

        typedArray.recycle()
    }

}