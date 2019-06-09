/*
 * MIT License

 * Copyright (c) 2019 Sumit Kombe

 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:

 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.example.CoffeeOrderingApp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * This method increases the number of coffees ordered by 1.
     */
    public void increment(View view) {
        if (quantity < 100) {
            quantity += 1;
            displayQuantity(quantity);
        } else {
            Toast.makeText(this, "You cannot order more than 100 cups of Coffee.", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    /**
     * This method decreases the number of coffees ordered by 1.
     */
    public void decrement(View view) {
        if (quantity > 1) {
            quantity -= 1;
            displayQuantity(quantity);
        } else {
            Toast.makeText(this, "You cannot order less than 1 cup of Coffee.", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        CheckBox check = (CheckBox) findViewById(R.id.check_cream);
        boolean isTrue = check.isChecked();

        CheckBox check1 = (CheckBox) findViewById(R.id.check_chocolate);
        boolean isTrue1 = check1.isChecked();

        EditText name = (EditText) findViewById(R.id.input_view);
        String name1 = name.getText().toString();

        createOrderSummary(isTrue, isTrue1, name1);
    }

    /**
     * This method calculates the total price.
     */
    private int calculatePrice(boolean tru, boolean tru1) {
        int bestPrice = 5;

        if (tru) {
            bestPrice += 1;
        }

        if (tru1) {
            bestPrice += 2;
        }
        return quantity * bestPrice;
    }

    /**
     * This method generates the Order Summary in Email.
     */
    private void createOrderSummary(boolean tr, boolean tr1, String name1) {
        String summary = "Name : " + name1 +
                "\nAdd whipped cream?" + tr +
                "\nAdd Chocolate?" + tr1 +
                "\nQuantity : " + quantity +
                "\nTotal : $" + calculatePrice(tr, tr1) +
                "\nThank You!";

        /**
         * This will transfer our order summary into an E-mail!
         */
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Order for " + name1);
        intent.putExtra(Intent.EXTRA_TEXT, summary);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

}