package com.example.cx.beiwanglu;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import org.w3c.dom.Text;

import static com.example.cx.beiwanglu.DBService.queryAll;

public class MainActivity extends AppCompatActivity {
    private Cursor listItemCursor = null;
    private EditText sv1;
    private ListView lv1;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 设置添加备忘录按钮事件，切换activity
        sv1 = (EditText) findViewById(R.id.sv);
        lv1 = (ListView) findViewById(R.id.listNote);
        button = (Button) findViewById(R.id.S);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Search();
            }
        });

        this.findViewById(R.id.addNote).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {
                        Intent in = new Intent();
                        in.setClassName(getApplicationContext(), "com.example.cx.beiwanglu.EditActivity");
                        startActivity(in);
                    }
                });

        // 查询所有备忘录，并将备忘录展示出来
        listItemCursor = queryAll();
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(MainActivity.this, R.layout.item_1, listItemCursor, new String[] { "_id",
                "title", "createTime" }, new int[] { R.id.noteId,
                R.id.noteTitle, R.id.noteCreateTime },
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        ((ListView) this.findViewById(R.id.listNote)).setAdapter(adapter);
        initListNoteListener();
    }


    /**
     * 初始化备忘录列表的长按和点击事件
     */
    private void initListNoteListener() {
        // 长按删除
        ((ListView) this.findViewById(R.id.listNote)).setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {//长按按钮

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, final long id) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("提示框")
                        .setMessage("确认删除该备忘录？？")
                        .setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0,int arg1) {
                                        DBService.deleteNoteById((int) id);
                                        //删除后刷新列表
                                        MainActivity.this.onResume();
                                        Toast.makeText(MainActivity.this, "删除成功！！", Toast.LENGTH_LONG).show();
                                    }
                                }).setNegativeButton("取消", null).show();
                return true;
            }
        });

        //点击进行修改操作
        ((ListView) this.findViewById(R.id.listNote)).setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in = new Intent();
                in.setClassName(view.getContext(), "com.example.cx.beiwanglu.EditActivity");
                // 将id数据放置到Intent，切换视图后可以将数据传递过去
                in.putExtra("id", id);
                startActivity(in);
            }
        });

    }
    public void Search(){

        Cursor cursor = DBService.queryNoteByTitle(sv1.getText().toString());
        int id;
        if(cursor.moveToFirst()){
                 id = cursor.getInt(cursor.getColumnIndex("_id"));
            Bundle bundle=new Bundle();
            bundle.putInt("id",id);
            Intent intent=new Intent(this,SearchActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);

        }

        onResume();
        lv1.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1));
        //设置ListView启动过滤
        lv1.setTextFilterEnabled(true);

        sv1 = (EditText) findViewById(R.id.sv);
        //设置sv默认是否自动缩小为图标
        //sv1.setIconifiedByDefault(false);
        //设置显示收索按钮
       // sv1.setSubmitButtonEnabled(true);

        //设置sv默认显示的提示文本
//        sv1.setQueryHint("查找");
//
//        //为该sv设置监听
//        sv1.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            //用户点击输入时触发
//            @Override
//
//            public boolean onQueryTextSubmit(String query) {
//
//                System.out.println("选择的是---" + query);
//// 一般实际应用中可在这里做逻辑处理
//
//                return false;
//            }
//
//            // 用户输入时触发
//            @Override
//            public boolean onQueryTextChange(String newText) {
//
//                //如果newText不为0
//                if (newText.isEmpty()) {
//                    //
//                    lv1.clearTextFilter();
//                } else {
//                    //使用用户输入内容对lv进行过滤
//                    lv1.setFilterText(newText);
//                }
//                return false;
//            }
//        });
    }
    /*private void findViews() {
        sv1 = (SearchView) findViewById(R.id.sv);
        lv1 = (ListView) findViewById(R.id.listNote);
        lv1.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mStrings));
        lv1.setTextFilterEnabled(true);
        lv1.setOnQueryTextListener(new SearchView.OnQueryTextListener()// 设置搜索文本监听{
        @Override
        public boolean onQueryTextSubmit(String query) {//搜索时触发事件
            return false;
        }
        @Override
        public boolean onQueryTextChange(String new Text) {//搜索时根据文本框动态改变搜索内容
            if (!TextUtils.isEmpty(new Text)){
                lv1.setFilterText(new Text);
            }   else {
                lv1.clearChoices();
            }
            return false;
        }
    });
}/*


    /**
     * 当从另一个视图进入该视图会调用该方法
     */
    @Override
    protected void onResume() {
        super.onResume();
        // 要求刷新主页列表
        if (listItemCursor != null) {
            listItemCursor.requery();
        }
    }
}
