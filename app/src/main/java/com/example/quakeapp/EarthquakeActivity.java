package com.example.quakeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.loader.content.Loader;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {

    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=5&limit=10";

    private CustomAdapter mAdapter;

    private MutableLiveData<List<EarthQuake>> mutableLiveData = new MutableLiveData<>();

    private static final String LOG_TAG = EarthquakeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);



        if (mutableLiveData.getValue() == null) {
            EarthquakeAsyncTask task = new EarthquakeAsyncTask();
            task.execute(USGS_REQUEST_URL);
        }
        else {
            mutableLiveData.observe(this, new Observer<List<EarthQuake>>() {
                @Override
                public void onChanged(List<EarthQuake> earthQuakes) {
                    Log.e(LOG_TAG , "I dont use AsyncTask anymore");
                    mAdapter.addAll(earthQuakes);
                }
            });
        }








        ListView earthquakeListView = (ListView) findViewById(R.id.list);



        mAdapter = new CustomAdapter(
                this, new ArrayList<EarthQuake>());

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EarthQuake currentEarthQuake = mAdapter.getItem(position);
                Uri earthQuakeUri = Uri.parse(currentEarthQuake.getUrl());
                Intent intent = new Intent(Intent.ACTION_VIEW, earthQuakeUri);

                startActivity(intent);
            }
        });

        earthquakeListView.setAdapter(mAdapter);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putParcelable("KEY",mAdapter);
    }

    private class EarthquakeAsyncTask extends AsyncTask<String, Void, List<EarthQuake>> {


        @Override
        protected List<EarthQuake> doInBackground(String... urls) {

            if (urls.length > 1 || urls[0] == null) {
                return null;
            }
            List<EarthQuake> earthQuake = QueryUtils.fetchEarthQuakeData(urls[0]);
            Log.e(LOG_TAG , "Im using background network thing, im bad boy");




            return earthQuake;
        }

        @Override
        protected void onPostExecute(List<EarthQuake> data) {
            mAdapter.clear();
            mutableLiveData.setValue(data);
            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);
            }

        }


    }

    private class newViewModel extends ViewModel {

    }
}