package br.pedronsouza.desafio.bankfacil.domain.http;

import br.pedronsouza.desafio.bankfacil.domain.http.exceptions.HttpError;
import br.pedronsouza.desafio.bankfacil.domain.models.UserList;

public interface IProxy {
    public UserList getUserList(final int limit) throws HttpError;
}
