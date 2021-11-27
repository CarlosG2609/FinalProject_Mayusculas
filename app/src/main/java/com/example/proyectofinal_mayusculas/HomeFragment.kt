package com.example.proyectofinal_mayusculas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.proyectofinal_mayusculas.databinding.FragmentHomeBinding
import com.example.proyectofinal_mayusculas.viewmodels.ProjectViewModel
import com.example.proyectofinal_mayusculas.viewmodels.UsuarioViewModel
import com.example.proyectofinal_mayusculas.viewmodels.UsuariosViewModelFactory

class HomeFragment : Fragment() {

    private val viewModel: ProjectViewModel by activityViewModels()
    private val viewModeldb: UsuarioViewModel by activityViewModels{
        UsuariosViewModelFactory(
            (activity?.application as UsuarioApp).database.DaoPrincipal()
        )
    }
    private var _binding: FragmentHomeBinding? = null
    private val binding get()= _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        // Se escribe el nombre de usuario almacenado en la corrida actual del programa
        // (por si se cambia de pantalla, que se guarde la info)
        binding.plaintextInputNombre.setText(viewModel.name)

        //AQUI SE AGREGAN LA PARTE DE LAS FUNCIONES DE CADA BOTON
        binding.buttonReglas.setOnClickListener{
            val sNombre= binding.plaintextInputNombre.text.toString()
            viewModel.changeName(sNombre)
            findNavController().navigate(R.id.action_homeFragment_to_rulesFragment)
        }
        binding.buttonEjercicios.setOnClickListener{
            val sNombre= binding.plaintextInputNombre.text.toString()
            viewModel.changeName(sNombre)
            findNavController().navigate(R.id.action_homeFragment_to_modeSelectionFragment)
        }


        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}