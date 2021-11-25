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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ResultadosFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResultadosFragment : Fragment() {

    private val viewModel: ProjectViewModel by activityViewModels()
    private val viewModeldb: UsuarioViewModel by activityViewModels{
        UsuariosViewModelFactory(
            (activity?.application as UsuarioApp).database.DaoPrincipal()
        )
    }

    private var _binding: FragmentResultadosBinding? = null
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
            lifecycleScope.launch{ viewModeldb.addUsuario(Usuario(viewModel.name, viewModel.score, Date().toString())) }


            findNavController().navigate(R.id.action_resultadosFragment_to_puntuacionesFragment)
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
         * @return A new instance of fragment ResultadosFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ResultadosFragment().apply {
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