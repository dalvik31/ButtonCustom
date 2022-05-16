# ButtonCustom

> Step 1. Add the JitPack repository to your build file

```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
```

> Step 2. Add the dependency


```
	dependencies {
	        implementation 'com.github.dalvik31:ButtonCustom:Tag'
	}
  
```

< Step 3 Use
```
  private var buttonCustom: CustomButton? = null
  
   buttonCustom = findViewById(R.id.customButton)

        buttonCustom!!.setOnClickListener {
            Toast.makeText(this, "button clicked", Toast.LENGTH_LONG).show()
        }
  
```


< Step 4 Config for btnType property

```
enum class TypesButtons(val btnType: Int, val btnColor: Int, val btnIcon: Int) {
    NO_SELECTION(0, 0, 0),
    DEFAULT(1, android.R.color.darker_gray, 0),
    SUCCESS(2, android.R.color.holo_green_light, R.drawable.ic_success),
    WARNING(3, android.R.color.holo_orange_light, R.drawable.ic_warning),
    ERROR(4, android.R.color.holo_red_light, R.drawable.ic_error)
}

```


< Step 5 Config layout

```
    <com.dalvik.custombutton.CustomButton
        android:id="@+id/customButton"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:showIcon="true"
        app:btnBackgroundColor="@color/purple_200"
        app:btnIcon="@drawable/ic_success"
        app:btnCornerRadius="30"
        app:btnText="@string/app_name"
        app:btnTextColor="@color/white"
        app:btnIconColor="@color/white"
        app:btnType="1"/>

```

