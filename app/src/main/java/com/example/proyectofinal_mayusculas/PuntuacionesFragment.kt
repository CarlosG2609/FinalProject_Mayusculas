package com.example.proyectofinal_mayusculas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectofinal_mayusculas.databinding.FragmentHomeBinding
import com.example.proyectofinal_mayusculas.databinding.FragmentPuntuacionesBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PuntuacionesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PuntuacionesFragment : Fragment() {

    private val viewModel: ProjectViewModel by activityViewModels()

    private var _binding: FragmentPuntuacionesBinding? = null
    private val binding get()= _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPuntuacionesBinding.inflate(inflater, container, false)

        //AQUI SE AGREGAN LA PARTE DE LAS FUNCIONES DE CADA BOTON
        binding.buttonHomeTablaPuntuacion.setOnClickListener{
            findNavController().navigate(R.id.action_puntuacionesFragment_to_homeFragment)
        }

        // Inicializar el adaptador y utilizar el RecyclerView
        val adapter= RvAdapter(viewModel.todo)
        binding.rvTodo.adapter= adapter
        binding.rvTodo.layoutManager= LinearLayoutManager(context)

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