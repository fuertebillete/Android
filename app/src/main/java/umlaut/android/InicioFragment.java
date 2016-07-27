package umlaut.android;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Gabriel Rojas on 20/7/2016.
 */
public class InicioFragment extends Fragment  {

    private TextView tv_name,tv_email,tv_tipo_usuario;
    private SharedPreferences pref;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Inicio");
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        tv_tipo_usuario= (TextView)getActivity().findViewById(R.id.tv_tipo_usuario_header);
        tv_name = (TextView)getActivity().findViewById(R.id.tv_nombre_usuario_header);
        tv_email = (TextView)getActivity().findViewById(R.id.tv_email_header);


        Button buttonChangeText = (Button) view.findViewById(R.id.buttonFragmentInbox);

        final TextView textViewInboxFragment = (TextView) view.findViewById(R.id.textViewInboxFragment);

        buttonChangeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textViewInboxFragment.setText("Este es el fragment Inicio");
                textViewInboxFragment.setTextColor(getResources().getColor(R.color.md_yellow_800));

            }
        });

        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {

        pref = getActivity().getPreferences(0);
        String nombre = pref.getString(Constantes.Nombre, "empty");
        String mail = pref.getString(Constantes.Mail, "empty");
        Integer tipo_usuario = pref.getInt(Constantes.Tipo_Usuario, 0);


        tv_name.setText(nombre);
        tv_email.setText(mail);

        if(tipo_usuario==1) {
            tv_tipo_usuario.setText("Alumno");
        }else if(tipo_usuario==2) {
            tv_tipo_usuario.setText("Profesor");
        }else if(tipo_usuario==3) {
            tv_tipo_usuario.setText("Administrador");
        }else if(tipo_usuario==4) {
            tv_tipo_usuario.setText("Coordinador");
        }
    }






}
