package com.example.user.gridview;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    GridView myGrid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myGrid = (GridView) findViewById(R.id.gridView);
        myGrid.setAdapter(new GridAdapter(this));
        myGrid.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this,MyDialog.class);
        ViewHolder holder = (ViewHolder) view.getTag();
        Country temp = (Country) holder.myCountry.getTag();
        intent.putExtra("countryImage",temp.imageId);
        intent.putExtra("countryName",temp.countryName);
        startActivity(intent);
    }
}

class Country
{
    int imageId;
    String countryName;
    Country(int imageId, String countryName)
    {
        this.imageId = imageId;
        this.countryName = countryName;
    }
}
class GridAdapter extends BaseAdapter
{
    ArrayList<Country> list;

    Context context;
    GridAdapter(Context context)
    {
        this.context = context;
        list = new ArrayList<>();
        Resources res = context.getResources();
        String[] tempCountryNames = res.getStringArray(R.array.country_names);
        int[] countryImages = {R.drawable.india,R.drawable.germany,R.drawable.itali,R.drawable.norway,R.drawable.pakistan,R.drawable.pillifines,R.drawable.saudi,R.drawable.spain,R.drawable.turkey,R.drawable.us};
        for( int i=0;i<10;i++)
        {
            Country tempCountry = new Country(countryImages[i],tempCountryNames[i]);
            list.add(tempCountry);
        }

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;
        if( row == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.single_item,parent,false);
            holder= new ViewHolder(row);
            row.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) row.getTag();
        }

        Country temp = list.get(position);
        holder.myCountry.setImageResource(temp.imageId);
        holder.myCountry.setTag(temp);
        return row;
    }
}

class ViewHolder
{
    ImageView myCountry;
    ViewHolder(View v)
    {
        myCountry = (ImageView) v.findViewById(R.id.imageView);
    }
}