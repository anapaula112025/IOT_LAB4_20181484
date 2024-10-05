package com.example.telefutbol;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;

public class AppActivity extends AppCompatActivity {

    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        // Configurar el NavController: sugerencia de CHATGPT
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);

        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
            Log.d("NavController", "NavController successfully retrieved.");
        } else {
            Log.e("NavController", "NavHostFragment is null.");
        }

        // Botón para el fragmento "Ligas"
        Button btnLigas = findViewById(R.id.btnLigas);
        btnLigas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Borra todos los fragmentos anteriores en el backStack cuando se navegué a otro fragmento: sugerencia de COPILOT
                navController.navigate(R.id.fragmentLigas, null, new NavOptions.Builder()
                        .setPopUpTo(R.id.nav_graph, true)
                        .build());
            }
        });

        // Botón para el fragmento "Posiciones"
        Button btnPosiciones = findViewById(R.id.btnPosiciones);
        btnPosiciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.fragmentPosiciones, null, new NavOptions.Builder()
                        .setPopUpTo(R.id.nav_graph, true)
                        .build());
            }
        });

        // Botón para el fragmento "Resultados"
        Button btnResultados = findViewById(R.id.btnResultados);
        btnResultados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.fragmentResultados, null, new NavOptions.Builder()
                        .setPopUpTo(R.id.nav_graph, true)
                        .build());
            }
        });

    }


}
