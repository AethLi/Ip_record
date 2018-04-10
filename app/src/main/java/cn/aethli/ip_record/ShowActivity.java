package cn.aethli.ip_record;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
//        android.app.ActionBar actionBar=getActionBar();
//        actionBar.hide();
        ListView listView=findViewById(R.id.ip_list);
        Listada la=new Listada(ShowActivity.this,R.layout._ip_time,initList());
        listView.setAdapter(la);
    }

    private List initList(){
        List l=new ArrayList();
        String time=null;
        String ipstr=null;
        SQLiteDatabase sdb=SQLiteDatabase.openDatabase("/data/data/cn.aethli.ip_record/databases/Ip.db",null,SQLiteDatabase.OPEN_READONLY);
        Cursor cursor=sdb.query("IP",null,null,null,null,null,null);
        if(cursor!=null&&cursor.moveToFirst()){
            do{
                time=cursor.getString(cursor.getColumnIndex("time"));
                ipstr=cursor.getString(cursor.getColumnIndex("ipstr"));
                String[] str=new String[]{time,ipstr};
                l.add(str);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return  l;
    }
}
