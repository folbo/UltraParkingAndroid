package net.silver.ultra.ultraandroid.transaction;

import android.os.SystemClock;

import net.silver.ultra.ultraandroid.MyApp;
import net.silver.ultra.ultraandroid.transaction.event.TransactionsDownloaded;

import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Background;
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
    public List<TransactionBean> getAllTransactions(){
        List<TransactionBean> objects = new ArrayList<>();
        objects.add(new TransactionBean("parking", "26.2.5555", "27.3.5555", 5.06f));
        objects.add(new TransactionBean("parking", "26.2.5555", "27.3.5555", 5.06f));
        objects.add(new TransactionBean("parking", "26.2.5555", "27.3.5555", 5.06f));
        objects.add(new TransactionBean("parking", "26.2.5555", "27.3.5555", 5.06f));
        objects.add(new TransactionBean("parking", "26.2.5555", "27.3.5555", 5.06f));
        objects.add(new TransactionBean("parking", "26.2.5555", "27.3.5555", 5.06f));
        objects.add(new TransactionBean("parking", "26.2.5555", "27.3.5555", 5.06f));


        SystemClock.sleep(2000);


        return objects;
    }
}
