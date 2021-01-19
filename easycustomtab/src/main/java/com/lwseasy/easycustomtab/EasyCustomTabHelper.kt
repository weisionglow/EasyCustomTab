package com.lwseasy.easycustomtab

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
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

    init {
        setupCustomTab()
    }

    fun setFont(typeface: Typeface) {
        customFont = typeface
        refreshTabView()
    }

    fun setColor(selectedColor: Int, unselectedColor: Int) {
        this.selectedColor = selectedColor
        this.unselectedColor = unselectedColor
        refreshTabView()
    }

    private fun setupCustomTab() {
        for (i: Int in 0 until (tabLayout?.tabCount ?: 0)) {

            var binding = DataBindingUtil.inflate<LayoutCustomTabBinding>(
                LayoutInflater.from(context),
                R.layout.layout_custom_tab,
                null,
                false
            )

            if (getTabTitle(i) != null) {
                binding?.layoutCustomTabTitle?.visibility = View.VISIBLE
                binding.layoutCustomTabTitle.text = getTabTitle(i)
            } else {
                binding?.layoutCustomTabTitle?.visibility = View.INVISIBLE
            }

            if (getTabIcon(i) != null) {
                binding?.layoutCustomTabIcon?.visibility = View.VISIBLE
                binding?.layoutCustomTabIcon?.setImageResource(getTabIcon(i)!!)
            } else {
                binding?.layoutCustomTabIcon?.visibility = View.INVISIBLE
            }

            when (easyCustomTabType) {
                EasyCustomTabType.ShowBoth -> {
                    binding.layoutCustomTabTitle.visibility = View.VISIBLE
                    binding.layoutCustomTabIcon.visibility = View.VISIBLE
                }
                EasyCustomTabType.ShowImageOnly -> {
                    binding.layoutCustomTabTitle.visibility = View.GONE
                    binding.layoutCustomTabIcon.visibility = View.VISIBLE
                }
                EasyCustomTabType.ShowTextOnly -> {
                    binding.layoutCustomTabTitle.visibility = View.VISIBLE
                    binding.layoutCustomTabIcon.visibility = View.GONE
                }
            }

            tabLayout?.getTabAt(i)?.customView = binding.root
            updateTabView(tabLayout?.getTabAt(i), i == 0)
        }

        addOnTabSelected()
    }

    private fun refreshTabView() {
        for (i: Int in 0 until (tabLayout?.tabCount ?: 0)) {
            updateTabView(tabLayout?.getTabAt(i), tabLayout?.selectedTabPosition == i)
        }
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
                    typeface = customFont

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
                    typeface = customFont

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