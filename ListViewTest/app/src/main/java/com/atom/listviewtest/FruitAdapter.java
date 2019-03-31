package com.atom.listviewtest;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class FruitAdapter extends ArrayAdapter<Fruit> {
    private int resourceId;

    public FruitAdapter(Context context, int textViewResourceId, List<Fruit> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    // 这个方法会在每个子项滚动到屏幕内时被触发
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Fruit fruit = getItem(position); // 获取当前项的Fruit实例

        // 性能改造方案
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.fruitImage = view.findViewById(R.id.fruit_image);
            viewHolder.fruitText = view.findViewById(R.id.fruit_name);
            view.setTag(viewHolder); // 将 ViewHolder 储存在 View 中
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag(); // 重新获取 ViewHolder
        }

        // 比较耗性能
        // View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        // ImageView fruitImage = view.findViewById(R.id.fruit_image);
        // TextView fruitText = view.findViewById(R.id.fruit_name);
        // fruitImage.setImageResource(fruit.getImageId());
        // fruitText.setText(fruit.getName());

        viewHolder.fruitImage.setImageResource(fruit.getImageId());
        viewHolder.fruitText.setText(fruit.getName());
        return view;
        // return super.getView(position, convertView, parent);
    }

    private class ViewHolder {
        ImageView fruitImage;
        TextView fruitText;
    }
}
