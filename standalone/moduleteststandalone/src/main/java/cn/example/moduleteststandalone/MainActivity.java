package cn.example.moduleteststandalone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import cn.example.module_annotation.BindView;
import cn.example.module_core.ButterKnifeUtils;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.mainTest)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ButterKnifeUtils.bind(this);
        new MainActivity_ViewBinding(this, this.getWindow().getDecorView());
        textView.setText("bind success");

    }
}
