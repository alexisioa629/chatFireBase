package com.example.chatfirebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ImageView imagenPerfilJ;
    private TextView textViewNombreJ;
    private RecyclerView contenedorMensajeJ;
    private EditText editTextMensajeJ;
    private Button buttonEnviarJ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void conexionElementosVista(){
        imagenPerfilJ=(ImageView) findViewById(R.id.imageViewFotoPerfil);
        textViewNombreJ= (TextView) findViewById(R.id.textViewNombreUsuario);
        contenedorMensajeJ= (RecyclerView) findViewById(R.id.contenedorMensaje);
        editTextMensajeJ= (EditText) findViewById(R.id.txtMensaje);
        buttonEnviarJ= (Button) findViewById(R.id.btnEnviar);
    }
}
