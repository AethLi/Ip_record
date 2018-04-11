package cn.aethli.ip_record;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.nightonke.jellytogglebutton.JellyToggleButton;
import com.nightonke.jellytogglebutton.State;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.os.SystemClock.sleep;

public class MainActivity extends AppCompatActivity {

    private Dbtools dt=null;
    private SQLiteDatabase sdb=null;
    private int flag=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConstraintLayout c=findViewById(R.id.main_constra);
        c.setBackgroundColor(ContextCompat.getColor(this,R.color.backgroundPrimary));
        JellyToggleButton jtb=findViewById(R.id.main_jtb);
        jtb.setOnStateChangeListener(new JellyToggleButton.OnStateChangeListener() {
            @Override
            public void onStateChange(float process, State state, JellyToggleButton jtb) {
                ConstraintLayout c=findViewById(R.id.main_constra);
                android.support.v7.widget.Toolbar tb=findViewById(R.id.main_tb);
                if (state.equals(State.LEFT)) {
                    tb.setBackground(getDrawable(R.drawable.toolbarcolor1));
                    c.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.backgroundPrimary));
                    END();
                }
                if (state.equals(State.RIGHT)) {
                    tb.setBackground(getDrawable(R.drawable.toolbarcolor2));
                    c.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.backgroundActive));
                    START();
                }
            }
        });
        dt=new Dbtools(this,"Ip.db",null,1);
        sdb=dt.getWritableDatabase();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case (R.id.START):
                START();
                break;
            case (R.id.END):
                END();
                break;
            case (R.id.SAVE):
                SAVE();
                break;
            case (R.id.CHECK):
                CHECK();
                break;
            case (R.id.EMPTY):
                EMPTY();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void START() {
        Toast.makeText(this,"start",Toast.LENGTH_SHORT).show();
        flag=0;

        ipGet();
    }
    private void END(){
        Toast.makeText(this,"end",Toast.LENGTH_SHORT).show();
        flag=1;
    }
    private void SAVE(){
        Toast.makeText(MainActivity.this,"HAS NOT REALISTIC",Toast.LENGTH_LONG).show();
    }
    private void CHECK(){
        Intent intent=new Intent(MainActivity.this,ShowActivity.class);
        startActivity(intent);
    }
    private void EMPTY(){
        Toast.makeText(this,"empty",Toast.LENGTH_SHORT).show();
        sdb.delete("IP",null,null);
    }

    private void ipGet(){
       new Thread(new Runnable() {
           @Override
           public void run() {
               do{
                   String ipaddr=Ipaddr.getIp();
                   ContentValues values=new ContentValues();
                   values.put("ipstr",ipaddr);
                   values.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString());
                   sdb.insert("IP",null,values);
                   sleep(5000);
               }while (flag==0);
           }
       }).start();
       {
       }
    }


}
