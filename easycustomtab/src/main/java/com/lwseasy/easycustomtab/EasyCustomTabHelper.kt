package com.lwseasy.easycustomtab

import android.content.Context
import android.graphics.Typeface
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayout
import com.lwseasy.easycustomtab.databinding.LayoutCustomTabBinding
import java.lang.Exception

/**
 * Created by Low Wei Siong on 19/01/2021
 **/

class EasyCustomTabHelper(
    private val context: Context,
    private val tabLayout: TabLayout?,
    private val easyTabList: ArrayList<EasyTabModel>,
    private val easyCustomTabType: EasyCustomTabType = EasyCustomTabType.ShowBoth
) {

    var onTabSelected: ((Int) -> Unit)? = null

    private var selectedColor = R.color.peach_F18B88
    private var unselectedColor = R.color.black
    private var currentPosition = 0
    private var customFont: Typeface? = null
    private var customFontSize = 14.0f


    fun setFont(typeface: Typeface) {
        this.customFont = typeface
    }

    fun setFontSize(fontSize: Float) {
        this.customFontSize = fontSize
    }

    fun setColor(selectedColor: Int, unselectedColor: Int) {
        this.selectedColor = selectedColor
        this.unselectedColor = unselectedColor
    }

    fun build() {
        for (i: Int in 0 until (tabLayout?.tabCount ?: 0)) {

            var binding = DataBindingUtil.inflate<LayoutCustomTabBinding>(
                LayoutInflater.from(context),
                R.layout.layout_custom_tab,
                null,
                false
            )

            binding?.layoutCustomTabTitle?.typeface = customFont
            binding?.layoutCustomTabTitle?.textSize = customFontSize

            when (easyCustomTabType) {
                EasyCustomTabType.ShowBoth -> {
                    binding.layoutCustomTabTitle.visibility = View.VISIBLE
                    binding.layoutCustomTabIcon.visibility = View.VISIBLE

                    setTextTitle(binding.layoutCustomTabTitle, getTabTitle(i))
                    setImageIcon(binding.layoutCustomTabIcon, getTabIcon(i))
                }
                EasyCustomTabType.ShowImageOnly -> {
                    binding.layoutCustomTabTitle.visibility = View.GONE
                    binding.layoutCustomTabIcon.visibility = View.VISIBLE

                    setImageIcon(binding.layoutCustomTabIcon, getTabIcon(i))
                }
                EasyCustomTabType.ShowTextOnly -> {
                    binding.layoutCustomTabTitle.visibility = View.VISIBLE
                    binding.layoutCustomTabIcon.visibility = View.GONE

                    setTextTitle(binding.layoutCustomTabTitle, getTabTitle(i))
                }
            }

            tabLayout?.getTabAt(i)?.customView = binding.root
            updateTabView(tabLayout?.getTabAt(i), i == 0)
        }

        addOnTabSelected()
    }

    private fun addOnTabSelected() {
        tabLayout?.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                currentPosition = tab.position
                updateTabView(tab, true)

                onTabSelected?.invoke(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                updateTabView(tab, false)
            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }

    private fun updateTabView(tab: TabLayout.Tab?, isActive: Boolean) {
        if (tab?.customView != null) {
            val binding =
                DataBindingUtil.findBinding<LayoutCustomTabBinding>(tab.customView!!)

            if (isActive) {
                binding?.layoutCustomTabTitle?.apply {
                    setTextColor(
                        ContextCompat.getColor(
                            context,
                            selectedColor
                        )
                    )
                }

                binding?.layoutCustomTabIcon?.setColorFilter(
                    ContextCompat.getColor(
                        context,
                        selectedColor
                    ), android.graphics.PorterDuff.Mode.MULTIPLY
                )
            } else {
                binding?.layoutCustomTabTitle?.apply {
                    setTextColor(
                        ContextCompat.getColor(
                            context,
                            unselectedColor
                        )
                    )
                }

                binding?.layoutCustomTabIcon?.setColorFilter(
                    ContextCompat.getColor(
                        context,
                        unselectedColor
                    ), android.graphics.PorterDuff.Mode.MULTIPLY
                )
            }
        }
    }

    private fun setTextTitle(textView: TextView, title: String?) {
        textView.text = title
    }

    private fun setImageIcon(imageView: ImageView, imageResource: Int?) {
        if (imageResource != null)
            imageView.setImageResource(imageResource)
    }


    private fun getTabIcon(position: Int): Int? {
        return try {
            easyTabList[position].image
        } catch (e: Exception) {
            null
        }
    }

    private fun getTabTitle(position: Int): String? {
        return try {
            return easyTabList[position].title
        } catch (e: Exception) {
            null
        }
    }
}