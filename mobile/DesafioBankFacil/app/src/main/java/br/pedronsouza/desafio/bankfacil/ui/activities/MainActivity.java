package br.pedronsouza.desafio.bankfacil.ui.activities;

import android.content.res.Configuration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import br.pedronsouza.desafio.bankfacil.R;
import br.pedronsouza.desafio.bankfacil.domain.controllers.UsersController;
import br.pedronsouza.desafio.bankfacil.domain.models.UserList;
import br.pedronsouza.desafio.bankfacil.ui.UserListAdapter;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @Bean
    UsersController controller;

    @ViewById(R.id.recycler)
    RecyclerView recyclerView;
    UserListAdapter adapter;

    @AfterViews void init() {
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }
        else{
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        }

        controller.getUserList();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUserListRetrieved(UserList results) {
        adapter = new UserListAdapter(results);
        recyclerView.setAdapter(adapter);
    }
}
