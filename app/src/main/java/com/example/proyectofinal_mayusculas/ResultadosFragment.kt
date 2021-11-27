package com.example.proyectofinal_mayusculas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.proyectofinal_mayusculas.databinding.FragmentResultadosBinding
import com.example.proyectofinal_mayusculas.viewmodels.ProjectViewModel
import com.example.proyectofinal_mayusculas.viewmodels.UsuarioViewModel
import com.example.proyectofinal_mayusculas.viewmodels.UsuariosViewModelFactory
import kotlinx.coroutines.launch
import java.util.*

class ResultadosFragment : Fragment() {

    private val viewModel: ProjectViewModel by activityViewModels()
    private val viewModeldb: UsuarioViewModel by activityViewModels{
        UsuariosViewModelFactory(
            (activity?.application as UsuarioApp).database.DaoPrincipal()
        )
    }
    private var _binding: FragmentResultadosBinding? = null
    private val binding get()= _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultadosBinding.inflate(inflater, container, false)

        // Se escribe la calificacion de usuario almacenado en la corrida actual del programa
        // (por si se cambia de pantalla, que se guarde la info)
        binding.plaintextOutputCalificacion.setText(viewModel.score.toString())

        //AQUI SE AGREGAN LA PARTE DE LAS FUNCIONES DE CADA BOTON
        binding.buttonTablaPuntuacion.setOnClickListener{
            val iCalif= binding.plaintextOutputCalificacion.text.toString().toInt()
            viewModel.changeScore(iCalif)


            // viewModel.todo.add(Todo(viewModel.name, viewModel.score.toInt()))
            // Esta línea anterior ya no nos sirve, porque ahora la base de datos es viewModeldb, y esta anterior fue solo para el viewmodel normal

            // Aqui se puede poner un if dentro de las siguientes lineas para definir en que casos SI y en que casos NO guardar la info en la base de datos
            lifecycleScope.launch{
                if (viewModel.type != "") { // FALTA AGREGAR CONDICION: AND con viewModel.name diferente de "" (para que no se agreguen usuarios sin nombre)
                    viewModeldb.addUsuario(Usuario(viewModel.name, viewModel.score, viewModel.type, Date().toString()))
                    viewModel.changeType("")
                }
            }


            findNavController().navigate(R.id.action_resultadosFragment_to_puntuacionesFragment)
        }


        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}