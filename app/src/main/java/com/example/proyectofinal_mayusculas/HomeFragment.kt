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

        if (viewModel.rules.size == 0){
            loadRules()
        }

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

    private fun loadRules() {
        viewModel.rules.add(Todorules("","Se escriben con mayúscula ..."))
        viewModel.rules.add(Todorules("1", "Los nombres propios se escriben con mayúscula inicial (de personas, ciudades, ríos, festividades, disciplinas)."))
        viewModel.rules.add(Todorules("2", "La primera letra de cada enunciado y después de punto."))
        viewModel.rules.add(Todorules("3", "La primera palabra después de las fórmulas de cortesía con las que inician las cartas y otros documentos formales."))
        viewModel.rules.add(Todorules("4", "Las siglas o acrónimos se escriben con mayúscula."))
        viewModel.rules.add(Todorules("5", "Los sustantivos y adjetivos que forman parte de los nombres de instituciones, entidades, organismo, partidos políticos, etc; o de un plan, programa o proyecto; o materias y cursos; o de congresos y reuniones académicas, técnicas, culturales o políticas."))
        viewModel.rules.add(Todorules("6", "Los sustantivos colectivos que significan entidades o corporaciones."))
        viewModel.rules.add(Todorules("7", "Las fórmulas de tratamiento abreviadas: Sto., Lic., Sr., Ud."))
        viewModel.rules.add(Todorules("8", "En el caso de las guerras y batallas se escriben con mayúsculas las palabras que especifican de cuál conflicto se habla, excepto los dos mundiales."))
        viewModel.rules.add(Todorules("9", "Los símbolos de carácter internacional mantienen su escritura fija de mayúscula o minúscula."))
        viewModel.rules.add(Todorules("10", "Los artículos cuando forman parte del nombre propio; además, en este caso, no se contraen."))
        viewModel.rules.add(Todorules("11", "Los nombres de premios y, en los grandes premios internacionales, la categoría también."))
        viewModel.rules.add(Todorules("12", "Los puntos cardinales únicamente se escriben con mayúscula si son parte de un nombre propio."))
        viewModel.rules.add(Todorules("13", "Después de dos puntos, cuando señalan el comienzo de un fragmento con sentido independiente."))
        viewModel.rules.add(Todorules("14", "Si los signos de interrogación y exclamación encierran un enunciado completo, este iniciará con mayúscula y la palabra después del signo de cierre lleva mayúsculas."))
        viewModel.rules.add(Todorules("15", "En organismos, entidades, instituciones, cuando el sustantivo común genérico especifica la clase a la que pertenece la entidad y en teatros o museos,  cuando el sustantivo común se refiere a la institución cultural que representan y no solamente al edificio donde están ubicados."))
        viewModel.rules.add(Todorules("16", "Únicamente si los nombres genéricos para referirse a personas (Fulano de Tal, Perengano) se utilizan para nombrar a un personaje ficticio."))
        viewModel.rules.add(Todorules("",""))
        viewModel.rules.add(Todorules("","Se escriben con minúscula ..."))
        viewModel.rules.add(Todorules("1b", "Vayan o no acompañados del nombre de la persona, se escribe con minúscula la palabra que indica el cargo que dicha persona ostenta."))
        viewModel.rules.add(Todorules("2b", "Los nombres de penínsulas, cuando son un adjetivo que se refiere a un topónimo."))
        viewModel.rules.add(Todorules("3b", "En general, las categorías de los premios y el nombre del premio cuando se refiere a la persona que lo recibió o al objeto (estatuilla, por ejemplo)."))
        viewModel.rules.add(Todorules("4b", "Los puntos cardinales y las líneas imaginarias no son nombres propios."))
        viewModel.rules.add(Todorules("5b", "En una cita textual, después de los puntos suspensivos iniciales que indican que se omite el principio del fragmento citado."))
        viewModel.rules.add(Todorules("6b", "Si un texto comienza con una cifra, la palabra siguiente inicia con minúscula."))
        viewModel.rules.add(Todorules("7b", "Los símbolos de carácter internacional mantienen su escritura fija de mayúscula o minúscula."))
        viewModel.rules.add(Todorules("8b", "Los días de la semana, los meses del año y las estaciones son nombres comunes."))
        viewModel.rules.add(Todorules("9b", "Es preferible escribir las direcciones electrónicas tal como aparecen en el buscador, es decir, con minúscula. Se sugiere no ponerlas al inicio de un enunciado."))
        viewModel.rules.add(Todorules("10b", "Las aposiciones explicativas que usualmente acompañan a nombres de personas o ciudades."))
        viewModel.rules.add(Todorules("11b", "Cuando un nombre propio se usa para llamar un objeto común."))
        viewModel.rules.add(Todorules("12b", "En los nombres geográficos, el sustantivo genérico (río, océano, mar, sierra, golfo, ciudad, estrecho) es común porque sirve como clasificador."))
        viewModel.rules.add(Todorules("13b", "Los artículos o preposiciones en nombres de pila."))
        viewModel.rules.add(Todorules("14b", "Los nombres para referirse a personas de modo genérico(fulano, zutano, mengano, etc.) se escriben con minúscula."))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}