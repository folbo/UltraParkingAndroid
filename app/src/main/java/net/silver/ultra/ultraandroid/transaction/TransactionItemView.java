package net.silver.ultra.ultraandroid.transaction;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.silver.ultra.ultraandroid.R;
import net.silver.ultra.ultraandroid.transaction.model.TransactionModel;

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

    public void bind(TransactionModel transaction) {
        parkingName.setText(transaction.getParkingName());
        dateStart.setText(transaction.getStartTime());
        dateEnd.setText(transaction.getEndTime());
        money.setText(Float.toString(transaction.getPrice()));
    }
}