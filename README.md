DemoTwitterImagePost
===================================

Demo which will show you how to set up  twitter app n post a tweet / upload image to twitter. 

ChangeLog
=======================================
version 1.0.1

__1]__ UI related modifications.


Usage
======================================
First and foremost you should have a valid twitter app configured correctly to post on twitter.

In case you don't have an app on twitter, follow the steps below to create a simple twitter app and how to configure it correctly.

Step 1.  Goto https://dev.twitter.com/apps Click on create new application button.

Then you will be presented the following page :

![Create App](https://dl.dropboxusercontent.com/u/61919232/learnNcode/DemoTwitterPost/1_twit_create_app.png "Create App")

step 1
Fill in the details of your app.

Make sure you don’t leave the CallBack URL blank. A callback url is the one where your app will be redirected to from twitter; this is a test app so for time being you can specify something like eg: http://www.google.com . If you leave this field blank then your app wont be authorized.

Step 2. Now its time to configure your app. Click on the settings tab. Scroll down to the

Application type section and change access to read and write.

![Settings](https://dl.dropboxusercontent.com/u/61919232/learnNcode/DemoTwitterPost/2_twit_settings_app.png "Settings")


Click on the update settings button at the bottom to reflect the changes. Ideally it takes 10 - 15 seconds for the settings to get updated so be patient.

Step 3. Download twitter4J library from the following url http://twitter4j.org/en/index.html and add it to your libs folder.

Note: The zip contains other additional jars. From these we need only twitter4j-core and

twitter4j-media support jars.

![Create App](https://dl.dropboxusercontent.com/u/61919232/learnNcode/DemoTwitterPost/3_twit_libs_eclipse.png)


Step 4. Now lets get back to some coding. First you need to add the twitter consumer key and secret key to the Strings xml:

Ex:

```java
<string name = "twitter_consumer_key">YOUR_CONSUMER_KEY</string>

<string name= "twitter_consumer_secret">YOUR_CONSUMER_SECRET</string>
```

![Create App](https://dl.dropboxusercontent.com/u/61919232/learnNcode/DemoTwitterPost/4_twit_key_secret_striked.jpg)

In our activity we have two buttons:

To post a tweet.

To post a tweet with image.

On button click we will check whether the app is already authorized if not we will authorize the app using a custom web view.

We will be using the custom webview to authorize the app so that once the app is authorized, you can finish the webview else you will be stuck with a common issue where the view remains opened even after the app is authorized.


![Create App](https://dl.dropboxusercontent.com/u/61919232/learnNcode/DemoTwitterPost/5_twit_calback_screen_cropped.jpg)

The authorization logic is written in LoginActivity class.

Step 5. How to post a tweet/image on twitter ? Is this that tricky ? Lets have a look at it.

a. Post:

```java
ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
configurationBuilder.setOAuthConsumerKey(context.getResources().getString(R.string.twitter_consumer_key));
configurationBuilder.setOAuthConsumerSecret(context.getResources().getString(R.string.twitter_consumer_secret));
configurationBuilder.setOAuthAccessToken(LoginActivity.getAccessToken((context)));
configurationBuilder.setOAuthAccessTokenSecret(LoginActivity.getAccessTokenSecret(context));
Configuration configuration = configurationBuilder.build();
Twitter twitter = new TwitterFactory(configuration).getInstance();
twitter.updateStatus(message);
```

b. Post with image:

```java
ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();

configurationBuilder.setOAuthConsumerKey(context.getResources().getString(R.string.twitter_consumer_key));

configurationBuilder.setOAuthConsumerSecret(context.getResources().getString(R.string.twitter_consumer_secret));

configurationBuilder.setOAuthAccessToken(LoginActivity.getAccessToken((context)));

configurationBuilder.setOAuthAccessTokenSecret(LoginActivity.getAccessTokenSecret(context));

Configuration configuration = configurationBuilder.build();

Twitter twitter = new TwitterFactory(configuration).getInstance();

StatusUpdate status = new StatusUpdate(message);

status.setMedia(file);  // set the image to be uploaded here.

twitter.updateStatus(status);
```

As you can see posting is as simple as it can be. Further you can download live and functioning app.

Happy Coding Happy Learning :)
