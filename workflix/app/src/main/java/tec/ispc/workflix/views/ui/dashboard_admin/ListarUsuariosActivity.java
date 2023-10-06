package tec.ispc.workflix.views.ui.dashboard_admin;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tec.ispc.workflix.R;
import tec.ispc.workflix.models.Persona;
import tec.ispc.workflix.models.Usuario;
import tec.ispc.workflix.utils.Apis;
import tec.ispc.workflix.utils.UsuarioService;
import tec.ispc.workflix.views.FalsoMain;
import tec.ispc.workflix.views.ui.back.PersonaAdapter;
import tec.ispc.workflix.views.ui.back.UsuarioAdapter;

public class ListarUsuariosActivity extends AppCompatActivity {
    UsuarioService usuarioService;
    List<Usuario> listarUsuario= new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_usuarios);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView=(ListView)findViewById(R.id.listViewUsuarios);
        listUsuario();


    }
    public void listUsuario(){
        usuarioService= Apis.getUsuarioService();
        Call<List<Usuario>> call=usuarioService.getUsuarios();
        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if(response.isSuccessful()) {
                    listarUsuario = response.body();
                    listView.setAdapter(new UsuarioAdapter(ListarUsuariosActivity.this,R.layout.content_listar,listarUsuario));
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
    }
}