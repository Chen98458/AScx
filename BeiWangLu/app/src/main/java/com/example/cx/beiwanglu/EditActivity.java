package com.example.cx.beiwanglu;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    private EditText titleEditText = null;
    private EditText contentEditText = null;
    private String noteId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edti_1);

        titleEditText = (EditText) EditActivity.this.findViewById(R.id.title);
        contentEditText = (EditText) EditActivity.this.findViewById(R.id.content);

        initNoteEditValue();

        //取消按钮监听
        this.findViewById(R.id.cancel).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {
                        EditActivity.this.finish();
                    }
                });

        this.findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                final String title = titleEditText.getText().toString();
                final String content = contentEditText.getText().toString();

                //判断标题和内容是否为空，不为空才能保存
                if ("".equals(title) || "".equals(content)) {
                    Toast.makeText(EditActivity.this, "标题或者内容不能为空",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                //提示保存
                new AlertDialog.Builder(EditActivity.this)
                        .setTitle("提示框")
                        .setMessage("确定保存备忘录吗？？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                ContentValues values = new ContentValues();
                                values.put("title", title);
                                values.put("content", content);

                                //如果noteId不为空那么就是更新操作，为空就是添加操作
                                if (null == noteId || "".equals(noteId))
                                    DBService.addNote(values);
                                else
                                    DBService.updateNoteById(Integer.valueOf(noteId), values);
                                //结束当前activity
                                EditActivity.this.finish();
                                Toast.makeText(EditActivity.this, "保存成功！！", Toast.LENGTH_LONG).show();
                            }
                        }).setNegativeButton("取消", null).show();

            }
        });
    }

    private void initNoteEditValue() {
        // 从Intent中获取id的值
        long id = this.getIntent().getLongExtra("id", -1L);
        // 如果有传入id那么id！=-1
        if (id != -1L) {
            // 使用noteId保存id
            noteId = String.valueOf(id);
            // 查询该id的备忘录
            Cursor cursor = DBService.queryNoteById((int) id);
            if (cursor.moveToFirst()) {
                // 将内容提取出来
                titleEditText.setText(cursor.getString(1));
                contentEditText.setText(cursor.getString(2));
            }
        }
    }

}