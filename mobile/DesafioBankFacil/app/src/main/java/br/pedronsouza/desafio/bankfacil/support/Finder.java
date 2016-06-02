package br.pedronsouza.desafio.bankfacil.support;

import android.app.Activity;
import android.view.View;

public class Finder  {

    public static <T> T findViewById(Activity context, int id) {
        return (T)context.findViewById(id);
    }

    public static <T> T findViewById(View view, int id) {
        return (T)view.findViewById(id);
    }

}
