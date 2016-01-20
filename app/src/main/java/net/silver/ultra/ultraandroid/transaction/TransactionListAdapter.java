package net.silver.ultra.ultraandroid.transaction;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import net.silver.ultra.ultraandroid.MyApp;
import net.silver.ultra.ultraandroid.transaction.event.TransactionsDownloaded;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;

/**
 * Created by folbo on 2016-01-20.
 */

@EBean
public class TransactionListAdapter extends BaseAdapter {
    @App
    MyApp app;

    List<TransactionBean> transactions;

    @Bean
    TransactionLoader transactionLoader;

    @RootContext
    Context context;

    @Background
    public void fetchTransactions() {
        transactions = transactionLoader.getAllTransactions();
        app.getBus().post(new TransactionsDownloaded());

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TransactionItemView personItemView;
        if (convertView == null) {
            personItemView = TransactionItemView_.build(context);
        } else {
            personItemView = (TransactionItemView) convertView;
        }

        personItemView.bind(getItem(position));

        return personItemView;
    }

    @Override
    public int getCount() {
        return transactions.size();
    }

    @Override
    public TransactionBean getItem(int position) {
        return transactions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}

class TransactionBean {
    String dateStart;
    String dateEnd;
    String parkingName;
    float money;

    @Override
    public String toString() {
        return "";
    }

    public TransactionBean(String dateStart, String dateEnd, String parkingName, float money) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.parkingName = parkingName;
        this.money = money;
    }
}