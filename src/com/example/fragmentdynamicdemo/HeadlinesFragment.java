package com.example.fragmentdynamicdemo;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HeadlinesFragment extends ListFragment {
	OnHeadlineSelectedListener mCallback;

	public interface OnHeadlineSelectedListener {
		public void onArticleSelected(int position);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		int layout = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ? android.R.layout.simple_list_item_activated_1
				: android.R.layout.simple_list_item_1;
		setListAdapter(new ArrayAdapter<String>(getActivity(), layout,
				Ipsum.Headlines));

	}

	@Override
	public void onStart() {
		super.onStart();
		if (getFragmentManager().findFragmentById(R.id.fragment_container) != null) {
			getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mCallback = (OnHeadlineSelectedListener) activity;
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {

		mCallback.onArticleSelected(position);
		getListView().setItemChecked(position, true);
	}

}
