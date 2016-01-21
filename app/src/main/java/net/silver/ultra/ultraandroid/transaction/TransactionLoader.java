package net.silver.ultra.ultraandroid.transaction;

import android.os.SystemClock;

import net.silver.ultra.ultraandroid.transaction.model.TransactionModel;
import net.silver.ultra.ultraandroid.util.RestManager;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.SupposeBackground;
import org.androidannotations.annotations.rest.RestService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by folbo on 2016-01-20.
 */

@EBean
public class TransactionLoader {
    @Bean
    RestManager restManager;

    @SupposeBackground
    public List<TransactionModel> getAllTransactions(){
        List<TransactionModel> objects = new ArrayList<>();
        TransactionModel[] model = restManager.getTransactionRestService().getAll();

        for(int i = 0; i < model.length; i++){
            objects.add(model[i]);
        }

        return objects;
    }
}
