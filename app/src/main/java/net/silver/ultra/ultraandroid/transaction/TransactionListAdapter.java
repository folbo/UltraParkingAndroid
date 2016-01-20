package net.silver.ultra.ultraandroid.transaction;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import net.silver.ultra.ultraandroid.MyApp;
import net.silver.ultra.ultraandroid.transaction.event.TransactionsDownloaded;
import net.silver.ultra.ultraandroid.transaction.model.TransactionModel;

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

    @Bean
    TransactionLoader transactionLoader;

    @RootContext
    Context context;

    private List<TransactionModel> transactions;

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
    public TransactionModel getItem(int position) {
        return transactions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}

