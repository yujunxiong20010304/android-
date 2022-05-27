package com.example.androidend;
import android.graphics.Typeface;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    Button[] btn = new Button[10];
    Button[] charbtn = new Button[5];   //运算符
    Button point;         // 点
    TextView window;     // 窗口按钮
    Button equality;      // 等于按钮
    Button disappear;
    TextView window_two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ----------------------------------- 删除按钮----------------------------------------------
        Typeface font = Typeface.createFromAsset(getAssets(), "iconfont.ttf");
        Button delete = (Button) findViewById(R.id.shan);     // 删除按钮
        delete.setTypeface(font);
        // ----------------------------- 以上为了实现阿里巴巴字体图标----------------------------------

        btn[0] = findViewById(R.id.zero);    // 0
        btn[1] = findViewById(R.id.one);     // 1
        btn[2] = findViewById(R.id.two);     // 2
        btn[3] = findViewById(R.id.three);   // 3
        btn[4] = findViewById(R.id.four);    // 4
        btn[5] = findViewById(R.id.five);    // 5
        btn[6] = findViewById(R.id.six);     // 6
        btn[7] = findViewById(R.id.seven);   // 7
        btn[8] = findViewById(R.id.eight);   // 8
        btn[9] = findViewById(R.id.nine);    // 9
        charbtn[0] = findViewById(R.id.chu);      // 除
        charbtn[1] = findViewById(R.id.cheng);    // 乘
        charbtn[2] = findViewById(R.id.jian);     // 减
        charbtn[3] = findViewById(R.id.jia);      // 加
        point = findViewById(R.id.point);         // 点
        charbtn[4] = findViewById(R.id.yu);       // 求于

        window = findViewById(R.id.window);    // 窗口
        equality = findViewById(R.id.eval);    // 等于按钮
        disappear = findViewById(R.id.clear);
        window_two = findViewById(R.id.window_two);

        //------------------------------------------------------------------------------------------
        disappear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                window.setText("");                               // 把窗口内容滞空
            }
        });

        BtnClickListenter btnClickListenter = new BtnClickListenter();
        /*for循环设置点击监听器*/
        for(Button button:btn){ /*button是循环的变量*/
            button.setOnClickListener(btnClickListenter);
        }


        CharBtnClickListenter charbtnClickListenter = new CharBtnClickListenter();
        /*for循环设置点击监听器*/
        for(Button button:charbtn){ /*button是循环的变量*/
            button.setOnClickListener(charbtnClickListenter);
        }

        //------------------------------------------------------------------------------------------


        point.setOnClickListener(new View.OnClickListener() {// 点
            @Override
            public void onClick(View view) {
                Button btn = (Button) view;
                String value = btn.getText().toString();
                String values = window.getText().toString();
                String[] numbers = values.split("[\\+\\-\\*\\/\\%]");
                char char_last = values.charAt(values.length()-1);
                if(numbers[numbers.length-1].contains(".")){
                    return;
                }
                if (char_last=='+'||char_last=='-'||char_last=='*'||char_last=='/'||char_last=='%'){
                    return;
                }else {
                    window.setText(values+value+"");
                    window_two.setText(values+value+"");
                }

            }
        });


        equality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {                             // 等于
                Button btn = (Button) view;
                String value = btn.getText().toString();
                String values = window.getText().toString();
                String values_two = window_two.getText().toString();
                window.setText(values_two);
                double key = 0;
                String[] numbers = values.split("[\\+\\-\\*\\/\\%]");
                if(values.contains("+")){
                    key = Double.valueOf(numbers[0])+Double.valueOf(numbers[1]);
                    window.setText(values_two+"="+key+"");
                }
                if(values.contains("-")){
                    key = Double.valueOf(numbers[0])-Double.valueOf(numbers[1]);
                    window.setText(values_two+"="+key+"");
                }
                if(values.contains("*")){
                    key = Double.valueOf(numbers[0])*Double.valueOf(numbers[1]);
                    window.setText(values_two+"="+key+"");
                }
                if(values.contains("/")){
                    key = Double.valueOf(numbers[0])/Double.valueOf(numbers[1]);
                    window.setText(values_two+"="+key+"");
                }
                if(values.contains("%")){
                    key = Double.valueOf(numbers[0])%Double.valueOf(numbers[1]);
                    window.setText(values_two+"="+key+"");
                }


            }
        });

        delete.setOnClickListener(new View.OnClickListener() {            // 删除监控
            @Override
            public void onClick(View view) {
                Button btn = (Button) view;
                String value = btn.getText().toString();
                String values = window.getText().toString();
                String v = values.substring(0,values.length()-1);
                window.setText(v);
                window_two.setText(v);

            }

        });


    }

    class CharBtnClickListenter implements View.OnClickListener{
        @Override
        public void onClick(View view){
            Button btn = (Button) view;
            String value = btn.getText().toString();
            String values = window.getText().toString();
            double key = 0;
            String[] numbers = values.split("[\\+\\-\\*\\/\\%]");
            if (values.equals("")){
                return;
            }
            if (values.contains("=")){
                return;
            }
            if (numbers.length==2){
                if(values.contains("+")){
                    key = Double.valueOf(numbers[0])+Double.valueOf(numbers[1]);
                    window.setText(key+"");
                }
                if(values.contains("*")){
                    key = Double.valueOf(numbers[0])*Double.valueOf(numbers[1]);
                    window.setText(key+"");
                }
                if(values.contains("/")){
                    key = Double.valueOf(numbers[0])/Double.valueOf(numbers[1]);
                    window.setText(key+"");
                }
                if(values.contains("-")){
                    key = Double.valueOf(numbers[0])-Double.valueOf(numbers[1]);
                    window.setText(key+"");
                }
                if(values.contains("%")){
                    key = Double.valueOf(numbers[0])%Double.valueOf(numbers[1]);
                    window.setText(key+"");
                }
            }else {
                window.setText(values+value);
            }
            window_two.setText(values+value+"");

        }
    }

    class BtnClickListenter implements View.OnClickListener{
        @Override
        public void onClick(View view){
            Button btn = (Button) view;
            String value = btn.getText().toString();
            String values = window.getText().toString();
            window.setText(values+value);
            window_two.setText(values+value+"");

        }


    }



}