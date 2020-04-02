package com.example.chatfirebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private ImageView imagenPerfilJ;
    private EditText editTextNombreJ;
    private RecyclerView contenedorMensajeJ;
    private EditText editTextMensajeJ;
    private Button buttonEnviarJ;
    private ImageButton imageButtonGaleriaJ;

    //herramientas para la base de datos.
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;



    //instanciar la clase adptador
    private Adaptador adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        conexionElementosVista();
    //inicializar objetos de data base
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("chat");//sala de chat


        //instanciar la clase adptador
        adaptador= new Adaptador(this);
        LinearLayoutManager layout= new LinearLayoutManager(this);
        //setear el linearlayaut al recycler view
        contenedorMensajeJ.setLayoutManager(layout);
        //agregar el adaptador
        contenedorMensajeJ.setAdapter(adaptador);


        //darle accion al boton enviar
        buttonEnviarJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //enviar el sms a la base de datos
              databaseReference.push().setValue(new ModeloMensaje(editTextMensajeJ.getText().toString(),editTextNombreJ.getText().toString(),"00:00","1"));
                editTextMensajeJ.setText("");
            }
        });
        //darle accion al boton de galeria
        imageButtonGaleriaJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent para abrir la galeria del telefono
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("image/jpeg");// tipo de intent
                i.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
                startActivityForResult(Intent.createChooser(i,"Selecciona una Imagen"),1);
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


        //agregar un hijo a la referencia
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            //metodo para mostrar en la lista lo de la base de datos
                ModeloMensaje m= dataSnapshot.getValue(ModeloMensaje.class);
                //fijar el
                adaptador.adaptarMensajes(m);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void conexionElementosVista(){
        imagenPerfilJ=(ImageView) findViewById(R.id.imageViewFotoPerfil);
        editTextNombreJ= (EditText) findViewById(R.id.editTextNombreUsuario);
        contenedorMensajeJ= (RecyclerView) findViewById(R.id.contenedorMensaje);
        editTextMensajeJ= (EditText) findViewById(R.id.txtMensaje);
        buttonEnviarJ= (Button) findViewById(R.id.btnEnviar);
        imageButtonGaleriaJ= (ImageButton) findViewById(R.id.imagenButtonGaleria);
    }
    //metodo para bajar la pantall
    public void scrollBar(){
        contenedorMensajeJ.scrollToPosition(adaptador.getItemCount()-1);
    }
///////////////////2020-04-01
protected void onActivityResult(){

}






}
