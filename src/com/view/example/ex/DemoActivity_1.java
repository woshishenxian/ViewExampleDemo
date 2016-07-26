package com.view.example.ex;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.view.example.CommonUtils;
import com.view.example.R;

public class DemoActivity_1 extends AppCompatActivity {
	
	private static final String TAG = "DemoActivity_1";
	private HorizontalScrollViewEx mListContainer;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_demo_1);
		Log.d(TAG	, "onCreate");
		initView();
	}
	
	private void initView(){
		LayoutInflater inflater = getLayoutInflater();
		mListContainer = (HorizontalScrollViewEx) findViewById(R.id.container);
		
		final int screenWidth = CommonUtils.getScreenWidth(this);
		final int screenHeight = CommonUtils.getScreenHeight(this);
		
		for (int i = 0; i <3; i++) {
			ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.content_layout, mListContainer,false);
			layout.getLayoutParams().width = screenWidth;
			layout.getLayoutParams().height = screenHeight;
			TextView textView = (TextView) layout.findViewById(R.id.title);
			textView.setText("PAGE "+ (i+1));
			createList(layout);
			mListContainer.addView(layout);
		}
	}
	
	private void createList(ViewGroup layout){
		ListView listView = (ListView) layout.findViewById(R.id.list);
		ArrayList<String> datas = new ArrayList<>();
		for (int i = 0; i < 50; i++) {
			datas.add("name :"+i);
			ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.content_list_item, R.id.name, datas);
			listView.setAdapter(adapter);
		}
	}

}
