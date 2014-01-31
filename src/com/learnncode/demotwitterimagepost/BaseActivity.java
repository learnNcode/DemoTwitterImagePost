/*
 * Copyright 2013 - learnNcode (learnncode@gmail.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.learnncode.demotwitterimagepost;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.learnncode.demotwitterimagepost.HelperMethods.TwitterCallback;

public class BaseActivity extends Activity {
	private Context context;
	private static final String TAG = "BaseActivity";
	private AlertDialog mAlertBuilder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_demo_twitter_base);

		context = BaseActivity.this;

		findViewById(R.id.postImageButton).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (LoginActivity.isActive(context)) {
					try {

						mAlertBuilder = new AlertDialog.Builder(context).create();
						mAlertBuilder.setCancelable(false);
						mAlertBuilder.setTitle(R.string.please_wait_title);
						View view = getLayoutInflater().inflate(R.layout.view_loading, null);
						((TextView) view.findViewById(R.id.messageTextViewFromLoading)).setText(getString(R.string.posting_image_message));
						mAlertBuilder.setView(view);
						mAlertBuilder.show();

						InputStream inputStream  = v.getContext().getAssets().open("1.png");
						Bitmap bmp = BitmapFactory.decodeStream(inputStream);
						String filename = Environment.getExternalStorageDirectory().toString() + File.separator + "1.png";
						Log.d("BITMAP", filename);
						FileOutputStream out = new FileOutputStream(filename);
						bmp.compress(Bitmap.CompressFormat.PNG, 90, out);

						HelperMethods.postToTwitterWithImage(context, ((Activity)context), filename, getString(R.string.tweet_with_image_text), new TwitterCallback() {

							@Override
							public void onFinsihed(Boolean response) {
								mAlertBuilder.dismiss();
								Log.d(TAG, "----------------response----------------" + response);
								Toast.makeText(context, getString(R.string.image_posted_on_twitter), Toast.LENGTH_SHORT).show();
							}
						});

					} catch (Exception ex) {
						Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show();
					}
				}else{
					startActivity(new Intent(context, LoginActivity.class));
				}				
			}
		});

		findViewById(R.id.postTweetButton).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {


				if (LoginActivity.isActive(context)) {
					try {

						mAlertBuilder = new AlertDialog.Builder(context).create();
						mAlertBuilder.setCancelable(false);
						mAlertBuilder.setTitle(R.string.please_wait_title);
						View view = getLayoutInflater().inflate(R.layout.view_loading, null);
						((TextView) view.findViewById(R.id.messageTextViewFromLoading)).setText(getString(R.string.posting_tweet_message));
						mAlertBuilder.setView(view);
						mAlertBuilder.show();

						HelperMethods.postToTwitter(context, ((Activity)context), getString(R.string.tweet_text), new TwitterCallback() {
							@Override
							public void onFinsihed(Boolean response) {
								Log.d(TAG, "----------------response----------------" + response);
								mAlertBuilder.dismiss();
								Toast.makeText(context, getString(R.string.tweet_posted_on_twitter), Toast.LENGTH_SHORT).show();

							}
						});

					} catch (Exception ex) {
						ex.printStackTrace();
						Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show();
					}
				}else{
					startActivity(new Intent(context, LoginActivity.class));
				}				
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (LoginActivity.isActive(context)) {
			Toast.makeText(BaseActivity.this, getString(R.string.authentication_done_now_post_tweet_or_image_text), Toast.LENGTH_LONG).show();
		}
	}
}
