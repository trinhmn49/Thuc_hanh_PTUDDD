package com.example.donation10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Report extends Base {
    ListView listView;

    static final String[] numbers = new String[] {
            "Amount, Pay method",
            "$10, Direct",
            "$100, PayPal",
            "$1000, Direct",
            "$10, PayPal",
            "$5000, PayPal"
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        DonationAdapter adapter = new DonationAdapter(this, donations);
        //list view
        listView = (ListView) findViewById(R.id.reportList);

        //hien thi danh sach
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menuReport:startActivity(new Intent(this, Report.class));
                break;

            case R.id.menuDonate: startActivity (new Intent(this, Donate.class));

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //tao menu trong activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        //return super.onCreateOptionsMenu(menu);
        return true;
    }

}