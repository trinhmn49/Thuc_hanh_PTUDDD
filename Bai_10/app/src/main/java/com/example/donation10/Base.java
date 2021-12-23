package com.example.donation10;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import models.Donation;

public class Base extends AppCompatActivity {
    public final int target = 10000;
    public int totalDonated = 0;
    public static List<Donation> donations = new ArrayList<Donation>();
    public DonationApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (DonationApp) getApplication();
        //
        app.dbManager.open();
        app.dbManager.setTotalDonated(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        app.dbManager.close();
    }

    public boolean newDonation(Donation donation)
    {
        boolean targetAchieved = totalDonated > target;
        if (!targetAchieved)
        {
            donations.add(donation);
            totalDonated += donation.amount;
        }
        else
        {
            //hiển thị report
            Toast toast = Toast.makeText(this, "Target Exceeded!", Toast.LENGTH_SHORT);
            toast.show();
        }
        return targetAchieved;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        //trong package menu
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu (Menu menu){
        super.onPrepareOptionsMenu(menu);
        MenuItem report = menu.findItem(R.id.menuReport);
        MenuItem donate = menu.findItem(R.id.menuDonate);

        if(donations.isEmpty())
            report.setEnabled(false);
        else
            report.setEnabled(true);

        //Donate is child of parent class
        if(this instanceof Donate){
            donate.setVisible(false);
            if(!donations.isEmpty())
                report.setVisible(true);
        }
        else {
            report.setVisible(false);
            donate.setVisible(true);
        }
        return true;
    }

    //hien thi khi click vao Item
    public void settings(MenuItem item)
    {
        Toast.makeText(this, "Settings Selected", Toast.LENGTH_SHORT).show();
    }

    public void report(MenuItem item)
    {
        startActivity (new Intent(this, Report.class));
    }

    public void donate(MenuItem item)
    {
        startActivity (new Intent(this, Donate.class));
    }

    public void reset(MenuItem item) {}
}