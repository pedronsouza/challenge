package br.pedronsouza.desafio.bankfacil.domain.controllers;

import android.support.v7.app.AlertDialog;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;

import br.pedronsouza.desafio.bankfacil.domain.http.exceptions.HttpError;
import br.pedronsouza.desafio.bankfacil.ui.activities.BaseActivity;

@EBean
public abstract class BaseController {
    @RootContext
    BaseActivity context;

    private void showProgress() {
        if (context != null)
            context.showProgressIndicator();
    }

    private void hideProgress() {
        if (context != null)
            context.hideProgressIndicator();
    }

    protected void execute(NetworkCommand command) {
        showProgress();

        try {
            command.doWork();
        } catch (HttpError e) {
            showHttpErrorDialog(command);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        hideProgress();
    }

    @UiThread
    void showHttpErrorDialog(final NetworkCommand commandForRetry) {
        if (context != null)
            new AlertDialog.Builder(context)
                    .setTitle("Alerta")
                    .setMessage("Ocorreu um erro no tratamento da resposta")
                    .setPositiveButton("Tentar Novamente", (view, which) -> {
                        execute(commandForRetry);
                    }).show();
    }

    interface NetworkCommand {
        void doWork() throws Throwable;
    }
}
