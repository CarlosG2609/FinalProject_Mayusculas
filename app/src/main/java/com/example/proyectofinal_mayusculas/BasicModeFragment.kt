package com.example.proyectofinal_mayusculas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.proyectofinal_mayusculas.databinding.FragmentBasicModeBinding
import com.example.proyectofinal_mayusculas.viewmodels.ProjectViewModel
import com.example.proyectofinal_mayusculas.viewmodels.UsuarioViewModel
import com.example.proyectofinal_mayusculas.viewmodels.UsuariosViewModelFactory

class BasicModeFragment : Fragment() {

    private val viewModel: ProjectViewModel by activityViewModels()
    private val viewModeldb: UsuarioViewModel by activityViewModels{
        UsuariosViewModelFactory(
            (activity?.application as UsuarioApp).database.DaoPrincipal()
        )
    }
    private var _binding: FragmentBasicModeBinding? = null
    private val binding get()= _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBasicModeBinding.inflate(inflater, container, false)

        //AQUI SE AGREGAN LA PARTE DE LAS FUNCIONES DE CADA BOTON
        binding.buttonResultsBasic.setOnClickListener{
            findNavController().navigate(R.id.action_basicModeFragment_to_resultadosFragment)
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}