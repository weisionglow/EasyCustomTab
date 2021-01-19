package com.lwseasy.easycustomtab_sample

import android.graphics.Typeface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.lwseasy.easycustomtab.EasyCustomTabHelper
import com.lwseasy.easycustomtab.EasyCustomTabType
import com.lwseasy.easycustomtab.EasyTabModel
import com.lwseasy.easycustomtab_sample.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        // Step 1: Usual way to create ViewPager and TabLayout
        binding.mainViewPager.adapter = MainTabAdapter(supportFragmentManager)
        binding.mainTabLayout.setupWithViewPager(binding.mainViewPager)

        // Step 2: Put Context, TabLayout, ArrayList and EasyCustomTabType inside the EasyCustomTabHelper
        val customTabHelper = EasyCustomTabHelper(
            this,
            binding.mainTabLayout,
            arrayListOf(
                EasyTabModel(
                    "Home",
                    R.drawable.ic_home_inactive
                ),
                EasyTabModel(
                    "Video",
                    R.drawable.ic_video_inactive
                ),
                EasyTabModel(
                    "Alarm",
                    R.drawable.ic_notifications_inactive
                ),
                EasyTabModel(
                    "Profile",
                    R.drawable.ic_account_inactive
                )
            ),
            EasyCustomTabType.ShowBoth //Show image and title
        )

        // Step 3: Done

        // Optional: Set Custom Color
        customTabHelper.setColor(selectedColor = R.color.teal_700, unselectedColor = R.color.black)

        // Optional: Set Custom Font
        customTabHelper.setFont(
            Typeface.createFromAsset(
                assets,
                "font/nunito_regular.ttf"
            )
        )

        // Optional: If you want to do some extra function when selected tab
        customTabHelper.onTabSelected = {
//            Toast.makeText(this, "Selected Position: $it", Toast.LENGTH_SHORT).show()
        }
    }
}