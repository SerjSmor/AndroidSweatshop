package serj.android.workshop2.com.testttt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class PizzaOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_order);

        Intent intent = getIntent();
        String order = intent.getStringExtra(MainActivity.ORDER_KEY);
        Toast.makeText(PizzaOrderActivity.this, order, Toast.LENGTH_SHORT).show();

    }
}
