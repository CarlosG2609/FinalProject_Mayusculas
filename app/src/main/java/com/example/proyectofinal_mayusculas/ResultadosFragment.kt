package com.example.proyectofinal_mayusculas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
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
        binding.textViewOutputCalificacion.text = (viewModel.rightAnswers.toDouble() / viewModel.questionNumber.toDouble() * 100).toInt().toString()+"/100"

        if (viewModel.repaso.size == 0){
            loadRepaso()
        }

        // Inicializar el adaptador y utilizar el RecyclerView
        val adapter = RvAdapterRules(viewModel.repaso)
        binding.rvTodoRepaso.adapter= adapter
        binding.rvTodoRepaso.layoutManager = LinearLayoutManager(context)


        //AQUI SE AGREGAN LA PARTE DE LAS FUNCIONES DE CADA BOTON
        binding.buttonTablaPuntuacion.setOnClickListener{
            val iCalif= (viewModel.rightAnswers.toDouble() / viewModel.questionNumber.toDouble() * 100).toInt()
            viewModel.changeScore(iCalif)

            // Aqui se puede poner un if dentro de las siguientes lineas para definir en que casos SI y en que casos NO guardar la info en la base de datos
            lifecycleScope.launch{
                if (viewModel.type != "") { // FALTA AGREGAR CONDICION: AND con viewModel.name diferente de "" (para que no se agreguen usuarios sin nombre)
                    viewModeldb.addUsuario(Usuario(viewModel.name, viewModel.score, viewModel.type, viewModel.tiempo))
                    Toast.makeText(context, "Se ha registrado la calificación en la base de datos.", Toast.LENGTH_SHORT).show()
                }
            }

            findNavController().navigate(R.id.action_resultadosFragment_to_puntuacionesFragment)
        }


        // Inflate the layout for this fragment
        return binding.root
    }

    private fun loadRepaso() {
        viewModel.repaso.add(Todorules("","Reglas de mayúsculas ..."))

        if (viewModel.normasA != ""){
            val reglasA= viewModel.normasA.substring(1, viewModel.normasA.length-1).split(", ").toTypedArray()    // Array
            for (i in reglasA.indices){
                for (j in viewModel.rules.indices){
                    if ((reglasA.elementAt(i)+"a").trim() == viewModel.rules.elementAt(j).id.trim()){
                        viewModel.repaso.add(Todorules(viewModel.rules.elementAt(j).id, viewModel.rules.elementAt(j).regla))
                    }
                }
            }
        } else{ viewModel.repaso.add(Todorules("","No hay reglas de mayúsculas por repasar.")) }


        viewModel.repaso.add(Todorules("",""))
        viewModel.repaso.add(Todorules("","Reglas de minúsculas ..."))
        if (viewModel.normasB != ""){
            val reglasB= viewModel.normasB.substring(1, viewModel.normasB.length-1).split(", ").toTypedArray()    // Array
            for (i in reglasB.indices){
                for (j in viewModel.rules.indices){
                    if ((reglasB.elementAt(i)+"b").trim() == viewModel.rules.elementAt(j).id.trim()){
                        viewModel.repaso.add(Todorules(viewModel.rules.elementAt(j).id, viewModel.rules.elementAt(j).regla))
                    }
                }
            }
        } else{ viewModel.repaso.add(Todorules("","No hay reglas de minúsculas por repasar.")) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}