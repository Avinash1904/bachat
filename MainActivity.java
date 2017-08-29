package com.example.avinash.bachat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import static java.util.Calendar.FEBRUARY;
import static java.util.Calendar.JANUARY;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    String[] month;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView= (ListView) findViewById(R.id.listView1);
        month= new String[]{"JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,month);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item=((TextView)view).getText().toString();
                Intent intent=new Intent(MainActivity.this,Details.class);
                intent.putExtra("month",item);
                startActivity(intent);
                //Toast.makeText(MainActivity.this,item,Toast.LENGTH_LONG).show();
            }
        });
    }
}
