package com.example.proyectofinal_mayusculas

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectofinal_mayusculas.databinding.FragmentPuntuacionesBinding
import com.example.proyectofinal_mayusculas.viewmodels.ProjectViewModel
import com.example.proyectofinal_mayusculas.viewmodels.UsuarioViewModel
import com.example.proyectofinal_mayusculas.viewmodels.UsuariosViewModelFactory
import kotlinx.coroutines.launch

class PuntuacionesFragment : Fragment() {

    private val viewModel: ProjectViewModel by activityViewModels()
    private val viewModeldb: UsuarioViewModel by activityViewModels{
        UsuariosViewModelFactory(
            (activity?.application as UsuarioApp).database.DaoPrincipal()
        )
    }
    private var _binding: FragmentPuntuacionesBinding? = null
    private val binding get()= _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPuntuacionesBinding.inflate(inflater, container, false)

        //AQUI SE AGREGAN LA PARTE DE LAS FUNCIONES DE CADA BOTON
        binding.buttonHomeTablaPuntuacion.setOnClickListener{
            viewModel.changeScore(0)
            findNavController().navigate(R.id.action_puntuacionesFragment_to_homeFragment)
        }

        // Inicializar el adaptador y utilizar el RecyclerView
        lifecycleScope.launch {
            val lista = viewModeldb.get10ScoreAsc(viewModel.type)
            if (lista != null) {
                val adapter = RvAdapter(lista)
                binding.rvTodo.adapter = adapter
                binding.rvTodo.layoutManager = LinearLayoutManager(context)
            }
        }

        // A lo mejor aqui hay que añadir la parte del video de la semana 9 LUNES en el minuto 1:15:00,
        // para actualizar y notificar al adaptador que se ha agregado un nuevo elemento a la lista


        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}