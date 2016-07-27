package umlaut.android;

/**
 * Created by Gabriel Rojas on 19/7/2016.
 */
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginFragment extends Fragment implements View.OnClickListener{

    private AppCompatButton btn_login;
    private EditText et_email,et_password;

    private ProgressBar progress;
    private SharedPreferences pref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login,container,false);

        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        initViews(view);
        return view;
    }

    private void initViews(View view){

        pref = getActivity().getPreferences(0);

        btn_login = (AppCompatButton)view.findViewById(R.id.btn_login);

        et_email = (EditText)view.findViewById(R.id.et_email);
        et_password = (EditText)view.findViewById(R.id.et_clave);

        progress = (ProgressBar)view.findViewById(R.id.progress);

        btn_login.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){



            case R.id.btn_login:

                String mail = et_email.getText().toString();
                String password = et_password.getText().toString();

                if(!mail.isEmpty() && !password.isEmpty()) {

                    progress.setVisibility(View.VISIBLE);
                    loginProcess(mail,password);


                } else {

                    Snackbar.make(getView(), "Los campos estan vacios !", Snackbar.LENGTH_LONG).show();
                }
                break;

        }
    }

    private void loginProcess(String mail,String password){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        Usuario user = new Usuario();
        user.setMail(mail);
        user.setPassword(password);
        ServerRequest request = new ServerRequest();
        request.setOperation(Constantes.Login_Operacion);
        request.setUser(user);
        Call<ServerResponse> response = requestInterface.operation(request);

        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                ServerResponse resp = response.body();
                Snackbar.make(getView(), resp.getMessage(), Snackbar.LENGTH_LONG).show();

                if(resp.getResultado().equals(Constantes.Exito)){
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putBoolean(Constantes.IS_LOGGED_IN,true);
                    editor.putString(Constantes.Mail,resp.getUser().getMail());
                    editor.putString(Constantes.Nombre,resp.getUser().getNombre());
                    editor.putInt(Constantes.ID,(resp.getUser().getId_dni()));
                    editor.putInt(Constantes.Tipo_Usuario,(resp.getUser().getTipo_usuario()));
                    editor.apply();
                    goToInicio();

                }
                progress.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

                progress.setVisibility(View.INVISIBLE);
                Log.d(Constantes.TAG,"failed");
                Snackbar.make(getView(), t.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();

            }
        });
    }

    private void goToInicio(){

        Fragment inicio = new InicioFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame,inicio);
        ft.commit();
    }



}