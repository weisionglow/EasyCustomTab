# EasyCustomTab
![ezgif com-gif-maker](https://user-images.githubusercontent.com/77658913/105059112-e9040c80-5ab1-11eb-8e0c-745639bd0ccc.gif)

## Step 1: Add the JitPack repository to your build file
```
allprojects {
  repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

## Step 2: Add the dependency
```
dependencies {
  implementation 'com.github.weisionglow:EasyCustomTab:1.0'
 }
```

## Step 3: Usual way to create ViewPager and TabLayout
``` kotlin
binding.mainViewPager.adapter = MainTabAdapter(supportFragmentManager)
binding.mainTabLayout.setupWithViewPager(binding.mainViewPager)
```

## Step 4: Put Context, TabLayout, ArrayList and EasyCustomTabType inside the EasyCustomTabHelper
``` kotlin
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
```

## Step 5: Done
Easy!!!

## You can change your show tab view:
``` kotlin
EasyCustomTabType.ShowTextOnly //Show text only
```
![ezgif com-gif-maker (1)](https://user-images.githubusercontent.com/77658913/105061053-033eea00-5ab4-11eb-8deb-636610d43d06.gif)

...
...

Option: If you want custom your color, font or function
For example:
``` kotlin
// Set Custom Color
customTabHelper.setColor(selectedColor = R.color.teal_700, unselectedColor = R.color.black)


// Set Custom Font
customTabHelper.setFont(
    Typeface.createFromAsset(
        assets,
        "font/nunito_regular.ttf"
    )
)

// If you want to do some extra function when selected tab
customTabHelper.onTabSelected = {
    Toast.makeText(this, "Selected Position: $it", Toast.LENGTH_SHORT).show()
}
```
