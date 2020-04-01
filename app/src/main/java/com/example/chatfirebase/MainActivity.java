package com.example.chatfirebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ImageView imagenPerfilJ;
    private TextView textViewNombreJ;
    private RecyclerView contenedorMensajeJ;
    private EditText editTextMensajeJ;
    private Button buttonEnviarJ;

    //herramientas para la base de datos.
   // private FireBaseDataBase dataBase;

    //instanciar la clase adptador
    private Adaptador adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        conexionElementosVista();
        //instanciar la clase adptador
        adaptador= new Adaptador(this);
        LinearLayoutManager layout= new LinearLayoutManager(this);
        //setear el linearlayaut al recycler view
        contenedorMensajeJ.setLayoutManager(layout);
        //agregar el adaptador
        contenedorMensajeJ.setAdapter(adaptador);

        //darle accion al boton
        buttonEnviarJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adaptador.adaptarMensajes(new ModeloMensaje(editTextMensajeJ.getText().toString(),textViewNombreJ.getText().toString(),"00:00","1"));
                editTextMensajeJ.setText("");
            }
        });
    //importar metodo para que vaya bajando los mensajes en la pantalla
        adaptador.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                //lamar el metodo del scroll
                scrollBar();
            }
        });


    }
    //metodo para bajar la pantall
    public void scrollBar(){
        contenedorMensajeJ.scrollToPosition(adaptador.getItemCount()-1);
    }

    public void conexionElementosVista(){
        imagenPerfilJ=(ImageView) findViewById(R.id.imageViewFotoPerfil);
        textViewNombreJ= (TextView) findViewById(R.id.textViewNombreUsuario);
        contenedorMensajeJ= (RecyclerView) findViewById(R.id.contenedorMensaje);
        editTextMensajeJ= (EditText) findViewById(R.id.txtMensaje);
        buttonEnviarJ= (Button) findViewById(R.id.btnEnviar);
    }
}
