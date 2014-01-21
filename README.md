DemoTwitterImagePost
===================================

Demo which will show you how to set up  twitter app n post a tweet / upload image to twitter. 


This is a simple tutorial on how to use tabs with fragment in android application.

Final output
<br>
![Final Outout](https://dl.dropboxusercontent.com/u/61919232/learnNcode/tabSample/tabSample.png "Final output")

<br>

In this application we are using fragment from android-support-v4.jar,  you are required to add android-support-v4.jar into your app.

1] Create a class lets say "BaseActivity" as we are using fragments to show tabs. BaseActivity class will extends "android.support.v4.app.FragmentActivity" class.

Here is xml file for BaseActivity class

```xml
<android.support.v4.app.FragmentTabHost
       android:id="@android:id/tabhost"
       android:layout_width="match_parent"
       android:layout_height="match_parent" >
       <LinearLayout
           android:layout_width="match_parent"
           android:layout_height="match_parent"
           android:orientation="vertical" >
           <TabWidget
               android:id="@android:id/tabs"
               android:layout_width="match_parent"
               android:layout_height="wrap_content"
               android:orientation="horizontal" />
           <FrameLayout
               android:id="@android:id/tabcontent"
               android:layout_width="match_parent"
               android:layout_height="match_parent" />
       </LinearLayout>
   </android.support.v4.app.FragmentTabHost>
```

2] BaseActivity class

In `onCreate()` method first setup tabhost instance and then add tabs

```java
mTabHost  = (FragmentTabHost) findViewById(android.R.id.tabhost);
mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
mTabHost.addTab(mTabHost.newTabSpec("tab1").
setIndicator(getResources().getString(R.string.tab_one)),
TabOneFrgment.class, null);
mTabHost.addTab(mTabHost.newTabSpec("tab2").
setIndicator(getResources().getString(R.string.tab_two)),
TabTwoFrgment.class, null);
```
Here we are adding tabs in tabhost using addTab method

addTab method takes 3 parameter:

a] TabSpec instance :  you can define tab indicator, can assign a tag for this tab etc.

b] activity which you want to show when user clicks on this tab.

c] bundle instance

`setIndicator()`: here you can set text and or icon which will displayed on tab.

You can also add icon with tabs

```java          
              Ex : mTabHost.addTab(mTabHost.newTabSpec("tab2").
                     setIndicator(getResources().getString(R.string.tab_two),                    
                     getResources().getDrawable(R.drawable.ic_launcher)),
                    TabTwoFrgment.class, null);
```                    
                    
Tag a tab

By default tabs are shown in the same order as they were added in tabhost.

You can set a tab as current tab  by using setCurrentTabByTag method.

```java
Ex : mTabHost.setCurrentTabByTag("tag2");
````

You can also show a tab by using tags :

```java
Ex : TabOneFrgment tabOneFrgment = (TabOneFrgment)  fragmentManager.findFragmentByTag("tab1");
fragmentTransaction.show(tabOneFrgment);
fragmentTransaction.commit();
```

How to navigate tabs???

Tab navigation has 2 aspects:

a] create a new fragment (add a fragment)

b] show already added fragment.

It is easy to create a new fragment on tab click but the trickiest part is to retain fragment state while changing tabs.

So in the sample app we are retaining fragment state whenever user changes a tab.

For this you need to add `setOnTabChangedListener` to the tab host.

Ex : `mTabHost.setOnTabChangedListener(new OnTabChangeListener() {.....});`

Inside setOnTabChangedListener we need check whether the intended fragment is already created (please refer fragment life cycle).

If fragment is already created  we just need to show that fragment else we will have to create a new fragment and hide current fragment.

```java

mTabHost.setOnTabChangedListener(new OnTabChangeListener() {
@Override
public void onTabChanged(String tabId) {
android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
TabOneFrgment tabOneFrgment = (TabOneFrgment) fragmentManager.findFragmentByTag("tab1");
TabTwoFrgment tabTwoFrgment = (TabTwoFrgment) fragmentManager.findFragmentByTag("tab2");
android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
if(tabId.equalsIgnoreCase("tab1")){
if(tabOneFrgment != null){
if(tabTwoFrgment != null){
fragmentTransaction.hide(tabTwoFrgment);
}
fragmentTransaction.show(tabOneFrgment);
}
}else{
if(tabTwoFrgment != null){
if(tabOneFrgment != null){
fragmentTransaction.hide(tabOneFrgment);
       }
fragmentTransaction.show(tabTwoFrgment);
}
}
fragmentTransaction.commit();
}
});
``` 

Happy Coding Happy Learning :)
