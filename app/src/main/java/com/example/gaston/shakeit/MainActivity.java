package com.example.gaston.shakeit;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private EditText itemIngresado;
    private ListView listaDeItems;
    private ArrayAdapter<String> listAdapter;
    private List<String> listArray;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setItemIngresado((EditText) findViewById(R.id.et_item));
        setListaDeItems((ListView) findViewById(R.id.list_items));
        setItemClickEvent();
        setListArray(new ArrayList<String>());
        setListAdapter(new ArrayAdapter<String>(this, R.layout.simple_list_item_1, getListArray()));
        getListaDeItems().setAdapter(getListAdapter());
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {
            @Override
            public void onShake(int count) {
                handleShakeEvent(count);
            }
        });

        runDefaultItemList();
    }

    private void runDefaultItemList() {
        getListAdapter().add("Naranjas");
        getListAdapter().add("Peras");
        getListAdapter().add("Manzanas");
        getListAdapter().add("Bananas");
        getListAdapter().add("Mandarinas");
        getListAdapter().add("Frutillas");
        getListAdapter().add("Kiwis");
        getListAdapter().notifyDataSetChanged();
    }

    private void handleShakeEvent(int count) {
        if(!getListAdapter().isEmpty()) {
            Intent i = new Intent(MainActivity.this, ShakeActivity.class);
            i.putExtra("RESULTADO", getRandomItemList());
            startActivity(i);
        }else{
            Toast.makeText(getApplicationContext(), "No hay items amego!", Toast.LENGTH_SHORT).show();
        }
    }

    public String getRandomItemList() {
        String item = "";
        int numeroAleatorio = (int)(Math.random()*getListAdapter().getCount());
        item = getListAdapter().getItem(numeroAleatorio);
        return item;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void agregarItem(View view){
        getListAdapter().add(getItemIngresado().getText().toString());
        getListAdapter().notifyDataSetChanged();

    }

    public void setItemClickEvent(){
        getListaDeItems().setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getListAdapter().remove(getListAdapter().getItem(position));
                getListAdapter().notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "Item borrado!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void borrarTodosLosItems(View view){
        getListAdapter().clear();
        getListAdapter().notifyDataSetChanged();
        Toast.makeText(getApplicationContext(), "Todo borrado ameo!", Toast.LENGTH_SHORT).show();
    }


    public ArrayAdapter<String> getListAdapter() {
        return listAdapter;
    }

    public void setListAdapter(ArrayAdapter<String> listAdapter) {
        this.listAdapter = listAdapter;
    }

    public ListView getListaDeItems() {
        return listaDeItems;
    }

    public void setListaDeItems(ListView listaDeItems) {
        this.listaDeItems = listaDeItems;
    }


    public EditText getItemIngresado() {
        return itemIngresado;
    }

    public void setItemIngresado(EditText itemIngresado) {
        this.itemIngresado = itemIngresado;
    }


    public List<String> getListArray() {
        return listArray;
    }

    public void setListArray(List<String> listArray) {
        this.listArray = listArray;
    }


}
