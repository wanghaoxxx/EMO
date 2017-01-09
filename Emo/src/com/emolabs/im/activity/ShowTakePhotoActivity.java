package com.emolabs.im.activity;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.emolabs.im.R;
import com.emolabs.im.config.EmoApplication;
import com.emolabs.im.utils.ImageThumbUtils;

public class ShowTakePhotoActivity extends Activity implements OnClickListener{
	private static final int TAKE_PHOTO = 0;
	TextView tvTakePhotoAgain;
	TextView tvNext;
	SeekBar sbChange;
	ImageView ivShowPhoto;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_take_photo);
		tvTakePhotoAgain = (TextView) findViewById(R.id.tv_showphoto_rephotograph);
		tvNext = (TextView) findViewById(R.id.tv_showphoto_next);
		sbChange = (SeekBar) findViewById(R.id.sb_showphoto_change);
		ivShowPhoto = (ImageView) findViewById(R.id.iv_showphoto_photo);
		
		tvTakePhotoAgain.setOnClickListener(this);
		tvNext.setOnClickListener(this);
		
		
		handerTakePhoto();
	}

	private void handerTakePhoto() {
		Bitmap camorabitmap = BitmapFactory.decodeFile(EmoApplication.appDataDir + "/temp.jpg");
		if (null != camorabitmap) {
			// 下面这两句是对图片按照一定的比例缩放，这样就可以完美地显示出来。
			int scale = ImageThumbUtils.reckonThumbnail(camorabitmap.getWidth(), camorabitmap.getHeight(), 800, 600);
			Bitmap bitmap = ImageThumbUtils.PicZoom(camorabitmap, camorabitmap.getWidth() / scale,
					camorabitmap.getHeight() / scale);
			// 由于Bitmap内存占用较大，这里需要回收内存，否则会报out of memory异常
			camorabitmap.recycle();
			// 将处理过的图片显示在界面上，并保存到本地
			ivShowPhoto.setImageBitmap(bitmap);
			ImageThumbUtils.saveBitmapToSDCard(bitmap, EmoApplication.appDataDir + "/temp.jpg");
		}
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch(id){
		case R.id.tv_showphoto_rephotograph:
			Uri imageUri = Uri.fromFile(new File(EmoApplication.appDataDir,"temp.jpg"));
			Intent intentTakePhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			intentTakePhoto.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
			startActivityForResult(intentTakePhoto, TAKE_PHOTO);
			break;
		case R.id.tv_showphoto_next:
			Intent intent = new Intent();
			intent.putExtra("filename", EmoApplication.appDataDir + "/temp.jpg");
			intent.setClass(this, PublishActivity.class);
			startActivity(intent);
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == TAKE_PHOTO){
			handerTakePhoto();
		}
	}
	
}

















