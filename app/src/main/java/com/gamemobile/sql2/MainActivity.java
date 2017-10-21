package com.gamemobile.sql2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.gamemobile.sql2.Database.Database;
import com.gamemobile.sql2.adapter.CustomAdapter;
import com.gamemobile.sql2.model.Them;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText edtTen;
    private EditText edtNguyenlieu;
    private EditText edtCachlam;
    private EditText edtId;
    private Button btnSave;
    private Button btnUpdate;
    private ListView lvThem;
    private Database database;
    private CustomAdapter customAdapter;
    private List<Them> listThem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = new Database(this);
        initWidget();
        listThem = database.getAllthem();
        setAdapter();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Them them = createThem();
                if (them != null) {
                    database.addMon(them);
                }
                updateListthem();
                setAdapter();
            }
        });
        lvThem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Them them = listThem.get(position);
                edtId.setText(String.valueOf(them.getmID()));
                edtTen.setText(them.getmTen());
                edtNguyenlieu.setText(them.getmNguyenlieu());
                edtCachlam.setText(them.getmCachlam());
                btnSave.setEnabled(false);
                btnUpdate.setEnabled(true);
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Them them = new Them();
                them.setmID(Integer.parseInt(String.valueOf(edtId.getText())));
                them.setmTen(edtTen.getText()+"");
                them.setmNguyenlieu(edtNguyenlieu.getText()+"");
                them.setmCachlam(edtCachlam.getText()+"");
                int result = database.updateThem(them);
                if(result>0){
                    updateListthem();
                }
                btnSave.setEnabled(true);
                btnUpdate.setEnabled(false);
            }
        });
        lvThem.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                Them them = listThem.get(position);
                int result = database.deleteThem(them.getmID());
                if(result>0){
                    Toast.makeText(MainActivity.this, "Delete successfuly", Toast.LENGTH_SHORT).show();
                    updateListthem();
                }else{
                    Toast.makeText(MainActivity.this, "Delete fail", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }
    private Them createThem() {
        String Ten = edtTen.getText().toString();
        String Nguyenlieu = edtNguyenlieu.getText().toString();
        String Cachlam = edtCachlam.getText().toString();

        Them them = new Them(Ten, Nguyenlieu, Cachlam);
        return them;
    }
    private void initWidget() {
        edtTen = (EditText) findViewById(R.id.edt_tenmon);
        edtNguyenlieu = (EditText) findViewById(R.id.edt_nguyenlieu);
        edtCachlam = (EditText) findViewById(R.id.edt_cachlam);
        btnSave = (Button) findViewById(R.id.btn_save);
        lvThem = (ListView) findViewById(R.id.lv_themmon);
        edtId = (EditText) findViewById(R.id.edt_id);
        btnUpdate = (Button) findViewById(R.id.btn_update);
    }
    private void setAdapter() {
        if (customAdapter == null) {
            customAdapter = new CustomAdapter(this, R.layout.items_list_them, listThem);
            lvThem.setAdapter(customAdapter);
        }else{
            customAdapter.notifyDataSetChanged();
            lvThem.setSelection(customAdapter.getCount()-1);
        }
    }
    public void updateListthem(){
        listThem.clear();
        listThem.addAll(database.getAllthem());
        if(customAdapter!= null){
            customAdapter.notifyDataSetChanged();
        }
    }
}
