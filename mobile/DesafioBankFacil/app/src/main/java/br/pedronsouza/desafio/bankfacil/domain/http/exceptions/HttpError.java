package br.pedronsouza.desafio.bankfacil.domain.http.exceptions;

/**
 * Created by pedrosouza on 6/2/16.
 */
public class HttpError extends Throwable {
    private final int statusCode;
    private final String errorBody;

    public HttpError(int statusCode, String errorBody) {
        this.statusCode = statusCode;
        this.errorBody = errorBody;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getErrorBody() {
        return errorBody;
    }


}
