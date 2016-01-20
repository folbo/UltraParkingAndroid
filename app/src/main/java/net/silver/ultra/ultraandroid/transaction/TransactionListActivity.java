package net.silver.ultra.ultraandroid.transaction;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.squareup.otto.Subscribe;

import net.silver.ultra.ultraandroid.BaseActivity;
import net.silver.ultra.ultraandroid.R;
import net.silver.ultra.ultraandroid.transaction.event.TransactionsDownloaded;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by folbo on 2016-01-20.
 */

@EActivity(R.layout.activity_transaction_list)
public class TransactionListActivity extends BaseActivity {

    @ViewById(R.id.transaction_list_view)  ListView transactionListView;
    @ViewById(R.id.load_transactions_progress)  View progressView;

    @Bean
    TransactionListAdapter adapter;

    @AfterViews
    void initViews() {
        showProgress(true);

        adapter.fetchTransactions();
    }

    @Override
    protected boolean useDrawerToggle()
    {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            onBackPressed();

        return super.onOptionsItemSelected(item);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            transactionListView.setVisibility(show ? View.GONE : View.VISIBLE);
            transactionListView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    transactionListView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            progressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    progressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            transactionListView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Subscribe
    public void onTransactionsFetchedEvent(TransactionsDownloaded event) {
        showProgress(false);
        transactionListView.setVisibility(View.VISIBLE);
        transactionListView.setAdapter(adapter);
    }
}


