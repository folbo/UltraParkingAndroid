package net.silver.ultra.ultraandroid.transaction;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.silver.ultra.ultraandroid.R;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by folbo on 2016-01-20.
 */

@EViewGroup(R.layout.transaction_list_item)
public class TransactionItemView extends LinearLayout {

    @ViewById(R.id.transaction_parking_name) TextView parkingName;
    @ViewById(R.id.transaction_date_start) TextView dateStart;
    @ViewById(R.id.transaction_date_end) TextView dateEnd;
    @ViewById(R.id.transaction_money) TextView money;

    public TransactionItemView(Context context) {
        super(context);
    }

    public void bind(TransactionBean transaction) {
        parkingName.setText(transaction.parkingName);
        dateStart.setText(transaction.dateStart);
        dateEnd.setText(transaction.dateEnd);
        money.setText(Float.toString(transaction.money));
    }
}