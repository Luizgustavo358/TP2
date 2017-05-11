package br.com.gustavo.luiz.trabalhopratico2_lddm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class BancoDeDados extends AppCompatActivity
{
    public ArrayList<Coordenadas> imageArray = new ArrayList<>();
    public ListView dataList;
    CoordImageAdapter imageAdapter;
    public DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banco_de_dados);

        dataList = (ListView) findViewById(R.id.listView);

        db = new DbHelper(this);

        List<Coordenadas> coordenadas = db.getAllCoordenadas();

        for(Coordenadas c : coordenadas)
        {
            imageArray.add(c);
        }// end for

        imageAdapter = new CoordImageAdapter(this, R.layout.content_db, imageArray);

        dataList.setAdapter(imageAdapter);
    }
}
