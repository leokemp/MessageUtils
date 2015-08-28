package com.kemp.Adapter;

import java.util.List;

import com.kemp.entity.DeviceData;
import com.kemp.activity.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DeviceAdapter extends BaseAdapter {

	List<DeviceData> datas = null;

	protected LayoutInflater layoutInflater = null;

	public DeviceAdapter(Context c, List<DeviceData> datas) {
		// TODO Auto-generated constructor stub
		this.datas = datas;
		layoutInflater = LayoutInflater.from(c);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return datas.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		EntityHolder entityHolder = null;
		if (convertView == null) {
			entityHolder = new EntityHolder();

			convertView = layoutInflater.inflate(R.layout.device_item_sytle,
					null);
			// ����Ϊ������һ�������ݣ����´λص���һ����ʱ��ֱ��refresh�������ض������ļ�
//			entityHolder.image = (ImageView) convertView
//					.findViewById(R.id.Device_item_iv);
			entityHolder.name = (TextView) convertView
					.findViewById(R.id.Device_item_tv);
			;
			convertView.setTag(entityHolder);
		} else {
			entityHolder = (EntityHolder) convertView.getTag();
		}

		// entityHolder.image.setImageDrawable(datas.get(position).getImage());
		entityHolder.name.setText(datas.get(position).getAdress());
		//entityHolder.image.setImageDrawable(datas.get(position).getIcon());

		return convertView;
	}

	private class EntityHolder {
		// ���ӵ�ַ
	//	public ImageView image;
		// ���
		public TextView name;
	}

}
