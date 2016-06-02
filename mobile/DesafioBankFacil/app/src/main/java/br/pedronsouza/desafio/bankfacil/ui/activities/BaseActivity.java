package br.pedronsouza.desafio.bankfacil.ui.activities;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.greenrobot.eventbus.EventBus;

import br.pedronsouza.desafio.bankfacil.ui.fragments.ProgressIndicator;
import br.pedronsouza.desafio.bankfacil.ui.fragments.ProgressIndicator_;

@EActivity
public abstract class BaseActivity extends AppCompatActivity {
    private Handler handler;

    @Override
    protected void onResume() {
        super.onResume();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
    }

    @UiThread
    public void showProgressIndicator() {
        if (handler == null)
            handler = new Handler();

        handler.postDelayed(() -> {
            Fragment fragment = getSupportFragmentManager().findFragmentByTag(ProgressIndicator.class.getCanonicalName());
            boolean isVisible = true;

            if (fragment == null) {
                fragment = ProgressIndicator_.builder().build();
                isVisible = fragment.isVisible();
            }

            if (!isVisible) {
                getSupportFragmentManager().beginTransaction()
                        .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .replace(android.R.id.content, fragment, ProgressIndicator.class.getCanonicalName())
                        .commit();
            }
        }, 120);
    }

    @UiThread
    public void hideProgressIndicator() {
        if (handler == null)
            handler = new Handler();

        handler.postDelayed(() -> {
            Fragment fragment = getSupportFragmentManager().findFragmentByTag(ProgressIndicator.class.getCanonicalName());

            if (fragment != null)
                getSupportFragmentManager().beginTransaction()
                        .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .remove(fragment)
                        .commit();
        }, 120);


    }
}
