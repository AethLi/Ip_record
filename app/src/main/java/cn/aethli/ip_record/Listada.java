package cn.aethli.ip_record;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by 93162 on 2018/4/7.
 */

public class Listada extends ArrayAdapter {
    private int resId;

    public Listada(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource,  objects);
        resId=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Object o=getItem(position);
        String[] str=new String[2];
        System.arraycopy(o,0,str,0,2);
        String time=str[0];
        String ipstr=str[1];
        View view= LayoutInflater.from(getContext()).inflate(resId,parent,false);
        TextView time_text=view.findViewById(R.id.time_text);
        TextView ipstr_text=view.findViewById(R.id.ipstr_text);
        time_text.setText(time);
        ipstr_text.setText(ipstr);
        return view;
    }


}
