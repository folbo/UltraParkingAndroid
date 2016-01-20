package net.silver.ultra.ultraandroid.transaction;

import android.os.SystemClock;

import net.silver.ultra.ultraandroid.transaction.model.TransactionModel;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.SupposeBackground;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by folbo on 2016-01-20.
 */

@EBean
public class TransactionLoader {

    @SupposeBackground
    public List<TransactionModel> getAllTransactions(){
        List<TransactionModel> objects = new ArrayList<>();
        objects.add(new TransactionModel("parking", "26.2.5555", "27.3.5555", 5.06f));
        objects.add(new TransactionModel("parking", "26.2.5555", "27.3.5555", 5.06f));
        objects.add(new TransactionModel("parking", "26.2.5555", "27.3.5555", 5.06f));
        objects.add(new TransactionModel("parking", "26.2.5555", "27.3.5555", 5.06f));
        objects.add(new TransactionModel("parking", "26.2.5555", "27.3.5555", 5.06f));
        objects.add(new TransactionModel("parking", "26.2.5555", "27.3.5555", 5.06f));
        objects.add(new TransactionModel("parking", "26.2.5555", "27.3.5555", 5.06f));

        SystemClock.sleep(2000);

        return objects;
    }
}
