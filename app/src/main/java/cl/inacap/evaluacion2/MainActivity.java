package cl.inacap.evaluacion2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import cl.inacap.evaluacion2.modelo.ComprasDatabaseHelper;
import cl.inacap.evaluacion2.modelo.Producto;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ComprasDatabaseHelper helper=new ComprasDatabaseHelper(this);

        Button verLista=(Button) findViewById(R.id.ver_lista);
        verLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    ArrayList<Producto> productos= (ArrayList<Producto>) helper.listaProductos();
                    Intent intent=new Intent(MainActivity.this,ListaProductosActivity.class);
                    startActivity(intent);
                }
                catch (Exception ex)
                {
                    Toast.makeText(MainActivity.this, "El Carrito Esta Vacio",Toast.LENGTH_SHORT).show();
                }

            }
        });

        Button botonNuevo=(Button) findViewById(R.id.botonNuevo);
        botonNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, NuevoProductoActivity.class);
                startActivity(intent);
            }
        });

        Button botonEliminar=(Button) findViewById(R.id.botonEliminar);
        botonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg=helper.eliminarComprados();
                Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }
}