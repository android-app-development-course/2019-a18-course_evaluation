package com.scottmangiapane.courseevaluation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SearchActivity extends AppCompatActivity {

    private Spinner spinner;
    private SearchView searchView;
    private AppCompatButton courseDetailBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);

        spinner=findViewById(R.id.spinner);

        searchView=findViewById(R.id.searchView);
        // 设置搜索文本监听
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(SearchActivity.this,spinner.getSelectedItem().toString()+newText,Toast.LENGTH_LONG).show();
                return false;
            }
        });


        courseDetailBtn=findViewById(R.id.courseBtn1);
        courseDetailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(SearchActivity.this, CourseDetailActivity.class);
                SearchActivity.this.startActivity(intent);
            }
        });

    }

}
