package com.rohit.ordercoffee;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quan=2;
    int price=5;
    String whipT,chocoT,customer,myText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        price=5;
        CheckBox whipCream = (CheckBox) findViewById(R.id.whip_cream_check);
        CheckBox choco = (CheckBox) findViewById(R.id.choco_check);
        EditText nameText= (EditText) findViewById(R.id.name);
        customer=  nameText.getText().toString();
        if (whipCream.isChecked()){
            whipT="Yes";
            price++;
        }
        else{
            whipT="No";
        }
        if (choco.isChecked()){
            chocoT="Yes";
            price=price+2;
        }
        else{
            chocoT="No";
        }
        displayPrice(quan*price);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        //intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee Order From APP");
        intent.putExtra(Intent.EXTRA_TEXT,myText);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        //displayPrice(quan*price);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
       //TextView priceTextView = (TextView) findViewById(R.id.orderSummary_text_view);
       myText="Name: "+customer+"\nAdd Whipped Cream: "+whipT+"\nAdd Choco Topping: "+chocoT+"\nQuantity: "+quan+"\nTotal= "+NumberFormat.getCurrencyInstance().format( number)+"\nThank You";
        //priceTextView.setText(myText);
    }



    public void inc(View view){
        quan++;
        if (quan>100){
            quan=100;
        }
        display(quan);
        //displayPrice(quan*5);
    }

    public void dec(View view){
        quan--;
        if (quan<1){
            quan=1;
        }
        display(quan);
        //displayPrice(quan*5);
    }
}
