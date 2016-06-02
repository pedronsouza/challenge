package br.pedronsouza.desafio.bankfacil.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import br.pedronsouza.desafio.bankfacil.R;
import br.pedronsouza.desafio.bankfacil.domain.models.User;
import br.pedronsouza.desafio.bankfacil.domain.models.UserList;
import br.pedronsouza.desafio.bankfacil.support.Finder;
import br.pedronsouza.desafio.bankfacil.support.RoundedTransformation;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder>{
    UserList itens;

    public UserListAdapter(UserList itens) {
        this.itens = itens;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item, parent, false);
        return new UserViewHolder(v);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.updateInfo(itens.getUsers().get(position));
    }

    @Override
    public int getItemCount() {
        return (itens != null && itens.getUsers().size() > 0 ) ? itens.getUsers().size() : 0;
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView username;
        private final View loading;

        public UserViewHolder(View itemView) {
            super(itemView);
            imageView = Finder.findViewById(itemView, R.id.user_image);
            username = Finder.findViewById(itemView, R.id.username);
            loading = Finder.findViewById(itemView, R.id.loading);
        }

        public void updateInfo(User user) {
            loading.setVisibility(View.VISIBLE);
            Picasso.with(itemView.getContext())
                    .load(user.getThumbImgUrl())
                    .transform(new RoundedTransformation(6, 0))
                    .into(imageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            loading.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError() {

                        }
                    });

            username.setText(user.getName());
        }
    }
}
