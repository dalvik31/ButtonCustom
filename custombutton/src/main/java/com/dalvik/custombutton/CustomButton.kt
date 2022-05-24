package com.dalvik.custombutton

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.card.MaterialCardView


class CustomButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    //Views
    private var root: View
    private var button: TextView
    private var btnBackground: MaterialCardView
    private var btnIcon: AppCompatImageView
    private var progressAnimation: LottieAnimationView


    //variables
    private var btnText: String = android.R.string.ok.toString()
    private var btnIconColor: Int = android.R.color.white
    private var btnBackgroundColor: Int = android.R.color.darker_gray
    private var btnTextColor: Int = android.R.color.white
    private var btnType: TypesButtons = TypesButtons.NO_SELECTION
    private var btnIsProgress: Boolean = false
    private var btnIconImage: Int = 0
    private var showIcon: Boolean = false
    private var btnCornerRadius: Float = 0f
    private var btnLottieFile: Int = 0


    //Init views
    init {
        root = LayoutInflater.from(context).inflate(R.layout.custom_button_view, this, true)
        button = root.findViewById(R.id.textview_title)
        btnIcon = root.findViewById(R.id.btnIcon)
        progressAnimation = root.findViewById(R.id.progress_animation)
        btnBackground = root.findViewById(R.id.cardContainer)
        loadAttr(attrs, defStyleAttr)
    }

    private fun loadAttr(attrs: AttributeSet?, defStyleAttr: Int) {
        val styledAttrs = context.theme
            .obtainStyledAttributes(attrs, R.styleable.CustomButton, defStyleAttr, 0)
        btnText = styledAttrs.getString(R.styleable.CustomButton_btnText).toString()
        btnBackgroundColor = styledAttrs.getResourceId(
            R.styleable.CustomButton_btnBackgroundColor,
            android.R.color.darker_gray
        )


        btnIconColor =
            styledAttrs.getResourceId(R.styleable.CustomButton_btnIconColor, android.R.color.white)
        btnTextColor =
            styledAttrs.getResourceId(R.styleable.CustomButton_btnTextColor, android.R.color.white)

        btnType = TypesButtons.values()[styledAttrs.getInt(
            R.styleable.CustomButton_btnType,
            TypesButtons.NO_SELECTION.btnType
        )]

        showIcon = styledAttrs.getBoolean(R.styleable.CustomButton_showIcon, false)
        btnIsProgress = styledAttrs.getBoolean(R.styleable.CustomButton_btnIsProgress, false)
        btnIconImage = styledAttrs.getResourceId(R.styleable.CustomButton_btnIcon, 0)
        btnCornerRadius = styledAttrs.getFloat(R.styleable.CustomButton_btnCornerRadius, 0f)


        btnLottieFile = styledAttrs.getResourceId(R.styleable.CustomButton_btnLottieFile, 0)

        styledAttrs.recycle()

        initView()
    }


    private fun initView() {
        if (btnType == TypesButtons.NO_SELECTION) {
            buttonDefault()
        } else {
            buttonGeneral(btnType.btnColor)
        }
        if (btnCornerRadius > 0) {
            btnBackground.radius = btnCornerRadius
            btnBackground.elevation = 0f
        }

        if (btnLottieFile != 0) {
            progressAnimation.setAnimation(btnLottieFile)
        }
        if (!btnIsProgress)
            setImageResource()
    }

    private fun setImageResource() {
        if (showIcon) {
            btnIcon.visibility = View.VISIBLE
            if (btnType.btnType != 0 && btnType.btnType != 1) {
                btnIcon.setImageResource(btnType.btnIcon)
            } else if (btnIconImage != 0) {
                btnIcon.setImageResource(btnIconImage)
            } else {
                btnIcon.visibility = View.GONE
            }

        } else {
            btnIcon.visibility = View.GONE
        }
    }

    private fun buttonDefault() {
        button.text = btnText

        btnBackground.setCardBackgroundColor(
            ContextCompat.getColor(
                context,
                btnBackgroundColor
            )
        )

        btnIcon.setColorFilter(
            ContextCompat.getColor(
                context,
                btnIconColor
            )
        )
        button.setTextColor(
            ContextCompat.getColor(
                context,
                btnTextColor
            )
        )

    }


    override fun setOnClickListener(l: OnClickListener?) {
        btnBackground.setOnClickListener(l)
        super.setOnClickListener(l)
    }


    private fun buttonGeneral(colorBackground: Int) {
        button.text = btnText
        btnBackground.setCardBackgroundColor(
            ContextCompat.getColor(
                context,
                colorBackground
            )
        )
        btnIcon.setColorFilter(
            ContextCompat.getColor(
                context,
                android.R.color.white
            )
        )
        button.setTextColor(
            ContextCompat.getColor(
                context,
                android.R.color.white
            )
        )
    }

    fun setLoading(loading: Boolean) {
        if (loading) {
            button.visibility = View.INVISIBLE
            progressAnimation.visibility = View.VISIBLE
            btnBackground.isEnabled = false
        } else {
            btnBackground.isEnabled = true
            button.visibility = View.VISIBLE
            progressAnimation.visibility = View.INVISIBLE
        }
    }

    fun setText(@StringRes textResource: Int) {
        button.text = context.getString(textResource)
    }


}

enum class TypesButtons(val btnType: Int, val btnColor: Int, val btnIcon: Int) {
    NO_SELECTION(0, 0, 0),
    DEFAULT(1, android.R.color.darker_gray, 0),
    SUCCESS(2, android.R.color.holo_green_light, R.drawable.ic_success),
    WARNING(3, android.R.color.holo_orange_light, R.drawable.ic_warning),
    ERROR(4, android.R.color.holo_red_light, R.drawable.ic_error)
}
