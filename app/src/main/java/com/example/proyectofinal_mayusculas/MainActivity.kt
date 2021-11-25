package com.example.proyectofinal_mayusculas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.proyectofinal_mayusculas.databinding.ActivityMainBinding
import com.example.proyectofinal_mayusculas.viewmodels.ProjectViewModel
import com.example.proyectofinal_mayusculas.viewmodels.UsuarioViewModel
import com.example.proyectofinal_mayusculas.viewmodels.UsuariosViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mProjectViewModel: ProjectViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Init View Model
        mProjectViewModel= ViewModelProvider(this).get(ProjectViewModel::class.java)

        // Init Room Database (se usa ViewModel para la DB para acceder desde cualquier fragment)
        var viewModeldb= ViewModelProvider(this,
                    UsuariosViewModelFactory((application as UsuarioApp).database.DaoPrincipal()))
                    .get(UsuarioViewModel::class.java)

        //Esta parte de abajo es SOLO DE EJEMPLO, para saber como utilizarlo
        lifecycleScope.launch {
            //viewModeldb.getAllUsuarios()
        }
    }
}