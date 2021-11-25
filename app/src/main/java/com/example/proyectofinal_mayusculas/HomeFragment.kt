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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private val viewModel: ProjectViewModel by activityViewModels()
    private val viewModeldb: UsuarioViewModel by activityViewModels{
        UsuariosViewModelFactory(
            (activity?.application as UsuarioApp).database.DaoPrincipal()
        )
    }

    private var _binding: FragmentHomeBinding? = null
    private val binding get()= _binding!!

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}