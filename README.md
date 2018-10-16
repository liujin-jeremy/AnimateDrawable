
## 该库用于实现 Animate Drawable

实现动画Drawable 需要继承 Drawable 并且实现 Animatable 接口

## 引入

To get a Git project into your build:

**Step 1.** Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

**Step 2.** Add the dependency

```
	dependencies {
	        implementation 'com.github.threekilogram:animateDrawable:1.2.0'
	}
```

