package com.example.cx.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
public class MainActivity extends Activity implements OnClickListener {
    /*EditText editText =(EditText) findViewById(R.id.edit_show);
     button4.setOnClickListener(this) ;
     button4.setText("/");
    Button button5=(Button) findViewById(R.id.but5);//传入按钮的实例
        button5.setOnClickListener(this) ;
        button5.setText("7");
    Button button6=(Button) findViewById(R.id.but6);//传入按钮的实例
        button6.setOnClickListener(this) ;
        button6.setText("8");*/

    private Button button2,button3,button4,button5, button6, button7,button8,button9,button10,button11,button12,button13,button14,button15,button16,button18,button19,button20;
    public int a = 0;
    public int i = 0;
    private String text1 = "0";
    private String text2 = "0";
    private EditText editText;
    private EditText editText1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.first_layout);
        editText = (EditText) findViewById(R.id.edit_show);
        editText1 = (EditText) findViewById(R.id.edit_show1);
        button3 = (Button) findViewById(R.id.but3);//传入按钮的实例
        button3.setOnClickListener(this);
        button4 = (Button) findViewById(R.id.but4);//传入按钮的实例
        button4.setOnClickListener(this);
        button2 = (Button) findViewById(R.id.but2);//传入按钮的实例
        button2.setOnClickListener(this);
        button5 = (Button) findViewById(R.id.but5);//传入按钮的实例
        button5.setOnClickListener(this);
        button6 = (Button) findViewById(R.id.but6);//传入按钮的实例
        button6.setOnClickListener(this);
         button7=(Button) findViewById(R.id.but7);//传入按钮的实例
        button7.setOnClickListener(this) ;
         button8=(Button) findViewById(R.id.but8);//传入按钮的实例
        button8.setOnClickListener(this) ;
         button9=(Button) findViewById(R.id.but9);//传入按钮的实例
        button9.setOnClickListener(this) ;
         button10=(Button) findViewById(R.id.but10);//传入按钮的实例
        button10.setOnClickListener(this) ;
         button11=(Button) findViewById(R.id.but11);//传入按钮的实例
        button11.setOnClickListener(this) ;
         button12=(Button) findViewById(R.id.but12);//传入按钮的实例
        button12.setOnClickListener(this) ;
         button13=(Button) findViewById(R.id.but13);//传入按钮的实例
        button13.setOnClickListener(this) ;
         button14=(Button) findViewById(R.id.but14);//传入按钮的实例
        button14.setOnClickListener(this) ;
         button15=(Button) findViewById(R.id.but15);//传入按钮的实例
        button15.setOnClickListener(this) ;
         button16=(Button) findViewById(R.id.but16);//传入按钮的实例
        button16.setOnClickListener(this) ;
         button18=(Button) findViewById(R.id.but18);//传入按钮的实例
        button18.setOnClickListener(this) ;
         button19=(Button) findViewById(R.id.but19);//传入按钮的实例
        button19.setOnClickListener(this) ;
        button20 = (Button) findViewById(R.id.but20);//传入按钮的实例
        button20.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String cal =editText.getText().toString();
        String cal1 =editText1.getText().toString();
        switch (v.getId()) {
            case R.id.but5:
                editText.append("7");
                editText1.append("7");
                break;
            case R.id.but6:
                editText.append("8");
                editText1.append("8");
                break;
            case R.id.but7:
                editText.append("9");
                editText1.append("9");
                break;
            case R.id.but9:
                editText.append("4");
                editText1.append("4");
                break;
            case R.id.but10:
                editText.append("5");
                editText1.append("5");
                break;
            case R.id.but11:
                editText.append("6");
                editText1.append("6");
                break;
            case R.id.but13:
                editText.append("1");
                editText1.append("1");
                break;
            case R.id.but14:
                editText.append("2");
                editText1.append("2");
                break;
            case R.id.but15:
                editText.append("3");
                editText1.append("3");
                break;
            case R.id.but19:
                editText.append(".");
                editText1.append(".");
                break;
            case R.id.but18:
                editText.append("0");
                editText1.append("0");
                break;
            case R.id.but4:
                a = 4;
                text1 = editText.getText().toString();
                editText.setText("");
                editText1.append("/");
                break;
            case R.id.but8:
                a = 3;
                text1 = editText.getText().toString();
                editText.setText("");
                editText1.append("*");
                break;
            case R.id.but12:
                a = 2;
                text1 = editText.getText().toString();
                editText.setText("");
                editText1.append("-");
                break;
            case R.id.but16:
                a = 1;
                text1 = editText.getText().toString();
                editText.setText("");
                editText1.append("+");
                break;
            case R.id.but20:
                switch (a) {
                    case 1:
                        text2 = editText.getText().toString();
                        Double res = Double.parseDouble(text1)
                                +Double.parseDouble(text2);
                        editText.setText(res+"");
                        editText1.setText(res+"");

                        break;
                    case 2:
                        text2 = editText.getText().toString();
                        Double res2 = Double.parseDouble(text1)
                                -Double.parseDouble(text2);
                        editText.setText(res2 + "");
                        editText1.setText(res2+"");
                        break;
                    case 3:
                        text2 = editText.getText().toString();
                        Double res3 = Double.parseDouble(text1)
                                * Double.parseDouble(text2);
                        editText.setText(res3 + "");
                        editText1.setText(res3+"");
                        break;
                    case 4:
                        text2 = editText.getText().toString();
                        if(Double.parseDouble(text2)==0){
                            editText.setText("除数不能为零");
                        }
                        Double res4 = Double.parseDouble(text1)
                                / Double.parseDouble(text2);
                        editText.setText(res4 + "");
                        editText1.setText(res4+"");
                        break;
                    default:
                        break;
                }
                break;
            case R.id.but3:
                if(cal!=null){
                    editText.setText(cal.substring(0,cal.length()-1));
                    editText1.setText(cal1.substring(0,cal1.length()-1));
                }
                break;
            case R.id.but2:
                a = 0;
                text1 = "0";
                text2 = "0";
                editText.setText("");
                editText1.setText("");
                break;
            default:
                break;
        }

    }
}










