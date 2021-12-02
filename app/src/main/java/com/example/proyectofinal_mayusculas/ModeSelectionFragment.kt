package com.example.proyectofinal_mayusculas

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.proyectofinal_mayusculas.databinding.FragmentModeSelectionBinding
import com.example.proyectofinal_mayusculas.viewmodels.ProjectViewModel
import com.example.proyectofinal_mayusculas.viewmodels.UsuarioViewModel
import com.example.proyectofinal_mayusculas.viewmodels.UsuariosViewModelFactory

class ModeSelectionFragment : Fragment() {

    private val viewModel: ProjectViewModel by activityViewModels()
    private val viewModeldb: UsuarioViewModel by activityViewModels{
        UsuariosViewModelFactory(
            (activity?.application as UsuarioApp).database.DaoPrincipal()
        )
    }
    private var _binding: FragmentModeSelectionBinding? = null
    private val binding get()= _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentModeSelectionBinding.inflate(inflater, container, false)

        //AQUI SE AGREGAN LA PARTE DE LAS FUNCIONES DE CADA BOTON
        binding.buttonBasicoReloj.setOnClickListener{
            viewModel.changeType("BasicoReloj")
            viewModel.repaso.clear()
            findNavController().navigate(R.id.action_modeSelectionFragment_to_basicModeFragment)
        }
        binding.buttonAvanzadoReloj.setOnClickListener{
            viewModel.changeType("AvanzadoReloj")
            viewModel.repaso.clear()
            findNavController().navigate(R.id.action_modeSelectionFragment_to_advancedModeFragment)
        }
        binding.buttonBasicoLibre.setOnClickListener{
            viewModel.changeType("BasicoLibre")
            viewModel.repaso.clear()
            findNavController().navigate(R.id.action_modeSelectionFragment_to_basicModeFragment)
        }
        binding.buttonAvanzadoLibre.setOnClickListener{
            viewModel.changeType("AvanzadoLibre")
            viewModel.repaso.clear()
            findNavController().navigate(R.id.action_modeSelectionFragment_to_advancedModeFragment)
        }


        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}