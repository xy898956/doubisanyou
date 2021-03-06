package com.doubisanyou.appcenter.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.doubisanyou.appcenter.R;
import com.doubisanyou.appcenter.bean.AddressGroupEntity;
import com.doubisanyou.appcenter.bean.ContactEntity;

public class TeaAddressListViewAdapter extends BaseExpandableListAdapter {
	// private String[] items;
	// private String[][] subItems;
	private List<AddressGroupEntity> addressGroup;
	private List<ArrayList<ContactEntity>> contacts;
	private int[] avators = { R.drawable.abaose };
	private LayoutInflater inflater;

	public TeaAddressListViewAdapter(Context context,
			List<AddressGroupEntity> addressGroup,
			List<ArrayList<ContactEntity>> contacts) {
		inflater = LayoutInflater.from(context);
		this.addressGroup = addressGroup;
		this.contacts = contacts;
	}

	@Override
	public Object getChild(int groupPos, int childPos) {
		return contacts.get(groupPos).get(childPos);
	}

	@Override
	public long getChildId(int groupPos, int childPos) {
		return childPos;
	}

	@Override
	public View getChildView(int groupPos, int childPos, boolean isLastChild,
			View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.address_list_subitem, null);
		}
		/*
		 * convertView.setTag(1, groupPos); convertView.setTag(2, childPos);
		 */
		PosBean pb = new PosBean();
		pb.groupPos = groupPos;
		pb.childPos = childPos;
		convertView.setTag(pb);
		TextView tv = (TextView) convertView
				.findViewById(R.id.contact_nickname_tv);
		
		ContactEntity ce = (ContactEntity) getChild(groupPos, childPos);
		byte[] avatar = ce.getAvatar();
		if (avatar != null) {
			ImageView iv = (ImageView) convertView
					.findViewById(R.id.contact_avatar_iv);
			Bitmap bm = BitmapFactory.decodeByteArray(ce.getAvatar(), 0,
					ce.getAvatar().length);
			iv.setImageBitmap(bm);
		}
		tv.setText(ce.getName());
		if (ce.isOnline()) {
			tv.setTextColor(Color.BLUE);
		} else {
			tv.setTextColor(Color.GRAY);
		}
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPos) {
		return contacts.get(groupPos).size();
	}

	@Override
	public Object getGroup(int groupPos) {
		return addressGroup.get(groupPos);
	}

	@Override
	public int getGroupCount() {
		return addressGroup.size();
	}

	@Override
	public long getGroupId(int groupPos) {
		return groupPos;
	}

	@Override
	public View getGroupView(int groupPos, boolean isExpanded,
			View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.address_list_item, null);
		}
		AddressGroupEntity age = (AddressGroupEntity) getGroup(groupPos);
		TextView groupNameTv = (TextView) convertView
				.findViewById(R.id.contact_group_name_tv);
		groupNameTv.setText(age.getGroupName());
		TextView countTv = (TextView) convertView
				.findViewById(R.id.contact_group_count_tv);
		countTv.setText(age.getOnline() + "/" + age.getGroupCount());
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return true;
	}

	public class PosBean {
		public int groupPos;
		public int childPos;
	}
}
