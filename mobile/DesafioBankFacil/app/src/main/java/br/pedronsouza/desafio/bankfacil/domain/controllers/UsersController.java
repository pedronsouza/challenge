package br.pedronsouza.desafio.bankfacil.domain.controllers;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.greenrobot.eventbus.EventBus;

import br.pedronsouza.desafio.bankfacil.domain.models.UserList;

@EBean
public class UsersController extends BaseController {
    private static final int USERLIST_LIMIT = 70;
    /**
     * Retrieve users from server and emmit an {@link br.pedronsouza.desafio.bankfacil.domain.models.UserList}
     * broadcast
     */
    @Background
    public void getUserList() {
        execute(() -> {
            UserList results = proxy.getUserList(USERLIST_LIMIT);
            EventBus.getDefault().post(results);
        });
    }
}
