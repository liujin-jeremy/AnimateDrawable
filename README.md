
## 该库用于实现 Animate Drawable

实现动画Drawable 需要继承 Drawable 并且实现 Animatable 接口

### BiliBiliLoadingDrawable

创建Drawable

```
BiliBiliLoadingDrawable biliLoadingDrawable = new BiliBiliLoadingDrawable(300);
biliLoadingDrawable.setStrokeWidth(10);
biliLoadingDrawable.setRepeat(3);
biliLoadingDrawable.setColor(getResources().getColor(R.color.colorAccent));
```

设置给ImageView

```
mAnimate.setImageDrawable(biliLoadingDrawable);
```

开始

```
biliLoadingDrawable.start();
```

![](img/pic01.gif)

### CircleLoadingDrawable

创建

```
CircleLoadingDrawable circleLoadingDrawable = new CircleLoadingDrawable(300);
mImageView.setImageDrawable(circleLoadingDrawable);
```

开始

```
circleLoadingDrawable.start();
```

结束

```
circleLoadingDrawable.stop();
```

![](img/pic02.gif)