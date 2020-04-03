package com.example.chatfirebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private ImageView imagenPerfilJ;
    private EditText NombreJ;
    private RecyclerView contenedorMensajeJ;
    private EditText MensajeJ;
    private Button buttonEnviarJ;
    private ImageButton imageButtonGaleriaJ;
    private ImageButton borrarNombre;
    private static final int numEnviarFoto=1;


    //herramientas para la base de datos de mensajes.
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    //herramientas para la base de datos para fotos
    private FirebaseStorage storageFotos;
    private StorageReference referenceFotos;

    //instanciar la clase adptador
    private Adaptador adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NombreJ = (EditText) findViewById(R.id.editTextNombreUsuario);
        imagenPerfilJ=(ImageView) findViewById(R.id.imageViewFotoPerfil);
        contenedorMensajeJ = (RecyclerView) findViewById(R.id.contenedorMensaje);
        MensajeJ = (EditText) findViewById(R.id.txtMensaje);
        buttonEnviarJ= (Button) findViewById(R.id.btnEnviar);
        imageButtonGaleriaJ= (ImageButton) findViewById(R.id.imagenButtonGaleria);
        borrarNombre=(ImageButton) findViewById(R.id.borrarNombre);





    //inicializar objetos de data base de mensajes
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("chat");//carpeta de mensajes en base de datos
     //inicializar objetos de storage de fotos
        storageFotos=FirebaseStorage.getInstance();
        //instanciar la clase adptador
        adaptador= new Adaptador(this);
        LinearLayoutManager layout= new LinearLayoutManager(this);
        //setear el linearlayaut al recycler view
        contenedorMensajeJ.setLayoutManager(layout);
        //agregar el adaptador
        contenedorMensajeJ.setAdapter(adaptador);


        final Date time = new Date();
        final DateFormat hourFormat = new SimpleDateFormat("HH:mm");

        //darle accion al boton enviar
        buttonEnviarJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //obtener la hora
                String hora= hourFormat.format(time);
                    //enviar el sms a la base de datos
                     databaseReference.push().setValue(new ModeloMensaje(MensajeJ.getText().toString(),"",  NombreJ.getText().toString(), "", hora, "1"));
                     MensajeJ.setText("");

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
                startActivityForResult(Intent.createChooser(i,"Selecciona una Imagen"),numEnviarFoto);
            }
        });

        //darle accion al boton de borrar nombre
        borrarNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NombreJ.setText("");
            }
        });
        //darle accion para seleccionar foto de perfil

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

    //metodo para bajar la pantall
    public void scrollBar(){
        contenedorMensajeJ.scrollToPosition(adaptador.getItemCount()-1);
    }
///////////////////2020-04-01

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==numEnviarFoto && resultCode==RESULT_OK){
            Uri u = data.getData();
            //instanciar referencia de la base de datos de fotos
            referenceFotos=storageFotos.getReference("imagenesChat");//imagenes de los chats
            final StorageReference fotoReferencia= referenceFotos.child(u.getLastPathSegment());// obtener la key de nuestras imagenes
            fotoReferencia.putFile(u).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


               Uri u= taskSnapshot.getUploadSessionUri();

               ModeloMensaje m= new ModeloMensaje((NombreJ.getText().toString()+" Envio una Imagen"),u.toString(),NombreJ.getText().toString(),"","00:00","2");
               databaseReference.push().setValue(m);
                }
            });


        }
    }

    protected void onActivityResult(){

}

}
