package serj.android.workshop2.com.testttt;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    public static final String ORDER_KEY = "ORDER_KEY";
    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private Button orderButton;
    private TextView textView;
    private ImageView pizzaImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        checkBox1 = (CheckBox) findViewById(R.id.checkBox);

        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(MainActivity.this, "isChecked:" + isChecked, Toast.LENGTH_SHORT).show();
            }
        });


        pizzaImageView = (ImageView)findViewById(R.id.imageView);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        orderButton = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView2);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pizza);


        pizzaImageView.setImageBitmap(bitmap);


        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String orderString = "";

                if (checkBox1.isChecked()) {
                    orderString += checkBox1.getText().toString();
                }

                if (checkBox2.isChecked()) {
                    orderString += checkBox2.getText().toString();
                }

                textView.setText(orderString);

                Intent intent = new Intent(MainActivity.this, PizzaOrderActivity.class);
                intent.putExtra(ORDER_KEY, orderString);
                startActivity(intent);
            }
        });
    }


}
