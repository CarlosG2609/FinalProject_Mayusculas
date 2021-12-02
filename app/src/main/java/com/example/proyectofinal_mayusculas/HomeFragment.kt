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
            loadAnswers()
            loadSentences()
            loadAppliedRules()
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
        viewModel.rules.add(Todorules(" 1a", "Los nombres propios se escriben con mayúscula inicial (de personas, ciudades, ríos, festividades, disciplinas científicas), o los apodos o sobrenombres que acompañan o sustituyen a un nombre propio, nombres comunes que toman el valor de un nombre propio (antonomasia)."))
        viewModel.rules.add(Todorules(" 2a", "La primera letra de cada enunciado y después de punto."))
        viewModel.rules.add(Todorules(" 3a", "La primera palabra después de las fórmulas de cortesía con las que inician las cartas y otros documentos formales."))
        viewModel.rules.add(Todorules(" 4a", "Las siglas o acrónimos se escriben con mayúscula."))
        viewModel.rules.add(Todorules(" 5a", "Los sustantivos y adjetivos que forman parte de los nombres de instituciones, entidades, organismo, partidos políticos, etc; o de un plan, programa o proyecto; o materias y cursos; o de congresos y reuniones académicas, técnicas, culturales o políticas."))
        viewModel.rules.add(Todorules(" 6a", "Los sustantivos colectivos que significan entidades o corporaciones."))
        viewModel.rules.add(Todorules(" 7a", "Las fórmulas de tratamiento abreviadas: Sto., Lic., Sr., Ud."))
        viewModel.rules.add(Todorules(" 8a", "En el caso de las guerras y batallas se escriben con mayúsculas las palabras que especifican de cuál conflicto se habla, excepto los dos mundiales."))
        viewModel.rules.add(Todorules(" 9a", "Los símbolos de carácter internacional mantienen su escritura fija de mayúscula o minúscula."))
        viewModel.rules.add(Todorules("10a", "Los artículos cuando forman parte del nombre propio; además, en este caso, no se contraen."))
        viewModel.rules.add(Todorules("11a", "Los nombres de premios y, en los grandes premios internacionales, la categoría también."))
        viewModel.rules.add(Todorules("12a", "Los puntos cardinales únicamente se escriben con mayúscula si son parte de un nombre propio."))
        viewModel.rules.add(Todorules("13a", "Después de dos puntos, cuando señalan el comienzo de un fragmento con sentido independiente."))
        viewModel.rules.add(Todorules("14a", "Si los signos de interrogación y exclamación encierran un enunciado completo, este iniciará con mayúscula y la palabra después del signo de cierre lleva mayúsculas."))
        viewModel.rules.add(Todorules("15a", "En organismos, entidades, instituciones, cuando el sustantivo común genérico especifica la clase a la que pertenece la entidad y en teatros o museos,  cuando el sustantivo común se refiere a la institución cultural que representan y no solamente al edificio donde están ubicados."))
        viewModel.rules.add(Todorules("16a", "Únicamente si los nombres genéricos para referirse a personas (Fulano de Tal, Perengano) se utilizan para nombrar a un personaje ficticio."))
        viewModel.rules.add(Todorules("",""))
        viewModel.rules.add(Todorules("","Se escriben con minúscula ..."))
        viewModel.rules.add(Todorules(" 1b", "Vayan o no acompañados del nombre de la persona, se escribe con minúscula la palabra que indica el cargo que dicha persona ostenta."))
        viewModel.rules.add(Todorules(" 2b", "Los nombres de penínsulas, cuando son un adjetivo que se refiere a un topónimo."))
        viewModel.rules.add(Todorules(" 3b", "En general, las categorías de los premios y el nombre del premio cuando se refiere a la persona que lo recibió o al objeto (estatuilla, por ejemplo)."))
        viewModel.rules.add(Todorules(" 4b", "Los puntos cardinales y las líneas imaginarias no son nombres propios."))
        viewModel.rules.add(Todorules(" 5b", "En una cita textual, después de los puntos suspensivos iniciales que indican que se omite el principio del fragmento citado."))
        viewModel.rules.add(Todorules(" 6b", "Si un texto comienza con una cifra, la palabra siguiente inicia con minúscula."))
        viewModel.rules.add(Todorules(" 7b", "Los símbolos de carácter internacional mantienen su escritura fija de mayúscula o minúscula."))
        viewModel.rules.add(Todorules(" 8b", "Los días de la semana, los meses del año y las estaciones son nombres comunes."))
        viewModel.rules.add(Todorules(" 9b", "Es preferible escribir las direcciones electrónicas tal como aparecen en el buscador, es decir, con minúscula. Se sugiere no ponerlas al inicio de un enunciado."))
        viewModel.rules.add(Todorules("10b", "Las aposiciones explicativas que usualmente acompañan a nombres de personas o ciudades."))
        viewModel.rules.add(Todorules("11b", "Cuando un nombre propio se usa para llamar un objeto común."))
        viewModel.rules.add(Todorules("12b", "En los nombres geográficos, el sustantivo genérico (río, océano, mar, sierra, golfo, ciudad, estrecho) es común porque sirve como clasificador."))
        viewModel.rules.add(Todorules("13b", "Los artículos o preposiciones en nombres de pila."))
        viewModel.rules.add(Todorules("14b", "Los nombres para referirse a personas de modo genérico(fulano, zutano, mengano, etc.) se escriben con minúscula."))
    }
    private fun loadSentences(){
        var cont = 1
        viewModel.setSentence("la barca en que me iré lleva una cruz de olvido.", cont++)
        viewModel.setSentence("calderón de la barca es un famoso escritor del siglo de oro español.", cont++)
        viewModel.setSentence("la dirección es https://docs.google.com", cont++)
        viewModel.setSentence("la organización mundial de la salud no ha avalado todas las vacunas.", cont++)
        viewModel.setSentence("méxico tiene una gran diversidad de paisajes naturales.", cont++)
        viewModel.setSentence("la capital de nuevo leon es monterrey.", cont++)
        viewModel.setSentence("la primera guerra mundial inició el 28 de julio de 1914.", cont++)
        viewModel.setSentence("el presidente biden recibirá a los embajadores el día de mañana.", cont++)
        viewModel.setSentence("el doctor leal verá al paciente el sábado.", cont++)
        viewModel.setSentence("el dr. leal verá al paciente el sábado.", cont++)
        viewModel.setSentence("llegó el visitante de el salvador.", cont++)
        viewModel.setSentence("la península itálica tiene forma de bota.", cont++)
        viewModel.setSentence("la península de yucatán cuenta con riquezas naturales dignas de visitarse.", cont++)
        viewModel.setSentence("la actriz posó junto a su oscar.", cont++)
        viewModel.setSentence("el periódico el norte es originario de monterrey.", cont++)
        viewModel.setSentence("¿cuáles son los países que conforman europa del este?", cont++)
        viewModel.setSentence("... porque cantando se alegran, cielito lindo, los corazones.", cont++)
        viewModel.setSentence("100 recetas de la buena cocina", cont++)
        viewModel.setSentence("... que digan que estoy dormido y que me traigan aquí.", cont++)
        viewModel.setSentence("la dirección https://dle.rae.es/diccionario está en la lista de favoritos.", cont++)
        viewModel.setSentence("por favor me traes de la tienda un foco de 20 w.", cont++)
        viewModel.setSentence("estimada alicia: espero que te encuentres muy bien.", cont++)
        viewModel.setSentence("¿no tienes frío? ¡está helando!", cont++)
        viewModel.setSentence("daniel, ¿me puedes traer cinco manzanas, por favor?", cont++)
        viewModel.setSentence("advertencia: este medicamento debe mantenerse en refrigeración.", cont++)
        viewModel.setSentence("en la fiesta saludé al flaco, te mandó saludos.", cont++)
        viewModel.setSentence("en mi equipo quedaron mis amigos chícharo y güero.", cont++)
        viewModel.setSentence("monterrey, la ciudad de las montañas, tiene clima extremoso.", cont++)
        viewModel.setSentence("la vida de cervantes, el manco de lepanto, es tan interesante como su obra.", cont++)
        viewModel.setSentence("tía, te traigo un quijote para tu colección.", cont++)
        viewModel.setSentence("pude ver un picasso en el museo de nueva york", cont++)
        viewModel.setSentence("estudió pintura en la academia de san carlos", cont++)
        viewModel.setSentence("el museo de historia mexicana inauguró una exposición temporal", cont++)
        viewModel.setSentence("el río santa catarina cruza la ciudad de monterrey", cont++)
        viewModel.setSentence("en el paso, texas, fue la huelga de farah.", cont++)
        viewModel.setSentence("muy cerca de monterrey está el municipio de el carmen.", cont++)
        viewModel.setSentence("maría de los ángeles mastretta guzmán es una reconocida escritora mexicana", cont++)
        viewModel.setSentence("hace rato, llegó un fulano y preguntó por miguel.", cont++)
    }
    private fun loadAnswers(){
        var cont = 1
        viewModel.setAnswer("1", cont++)
        viewModel.setAnswer("1,4,10,12", cont++)
        viewModel.setAnswer("1", cont++)
        viewModel.setAnswer("1,2,3,6", cont++)
        viewModel.setAnswer("1", cont++)
        viewModel.setAnswer("1,4,5,7", cont++)
        viewModel.setAnswer("1,2,3,4", cont++)
        viewModel.setAnswer("1,3", cont++)
        viewModel.setAnswer("1,3", cont++)
        viewModel.setAnswer("1,2,3", cont++)
        viewModel.setAnswer("1,5,6", cont++)
        viewModel.setAnswer("1", cont++)
        viewModel.setAnswer("1,4", cont++)
        viewModel.setAnswer("1", cont++)
        viewModel.setAnswer("1,3,4,8", cont++)
        viewModel.setAnswer("1,7,9", cont++)
        viewModel.setAnswer("0", cont++)
        viewModel.setAnswer("0", cont++)
        viewModel.setAnswer("0", cont++)
        viewModel.setAnswer("1", cont++)
        viewModel.setAnswer("1,12", cont++)
        viewModel.setAnswer("1,2,4", cont++)
        viewModel.setAnswer("1,4", cont++)
        viewModel.setAnswer("1", cont++)
        viewModel.setAnswer("1,2", cont++)
        viewModel.setAnswer("1,6", cont++)
        viewModel.setAnswer("1,7,9", cont++)
        viewModel.setAnswer("1", cont++)
        viewModel.setAnswer("1,4,8", cont++)
        viewModel.setAnswer("1", cont++)
        viewModel.setAnswer("1,9,10", cont++)
        viewModel.setAnswer("1,5,7,8", cont++)
        viewModel.setAnswer("1,2,4,5", cont++)
        viewModel.setAnswer("1,3,4,9", cont++)
        viewModel.setAnswer("1,2,3,4,9", cont++)
        viewModel.setAnswer("1,4,9,10", cont++)
        viewModel.setAnswer("1,4,5,6", cont++)
        viewModel.setAnswer("1,9", cont++)
    }
    private fun loadAppliedRules(){
        var cont = 1
        viewModel.setAppliedRules("2a", cont++)
        viewModel.setAppliedRules("1a,2a", cont++)
        viewModel.setAppliedRules("9b,2a", cont++)
        viewModel.setAppliedRules("2a,5a", cont++)
        viewModel.setAppliedRules("1a", cont++)
        viewModel.setAppliedRules("1a,2a", cont++)
        viewModel.setAppliedRules("8a", cont++)
        viewModel.setAppliedRules("1a,2a,1b", cont++)
        viewModel.setAppliedRules("1a,2a,7a", cont++)
        viewModel.setAppliedRules("1a,2a,7a", cont++)
        viewModel.setAppliedRules("1a,2a,10a", cont++)
        viewModel.setAppliedRules("2a", cont++)
        viewModel.setAppliedRules("1a,2a", cont++)
        viewModel.setAppliedRules("2a,3b", cont++)
        viewModel.setAppliedRules("2a,1a,12a", cont++)
        viewModel.setAppliedRules("2a,1a,12a", cont++)
        viewModel.setAppliedRules("5b", cont++)
        viewModel.setAppliedRules("6b", cont++)
        viewModel.setAppliedRules("5b", cont++)
        viewModel.setAppliedRules("2a,9b", cont++)
        viewModel.setAppliedRules("2a,9a", cont++)
        viewModel.setAppliedRules("1a,2a,3a", cont++)
        viewModel.setAppliedRules("2a,14a", cont++)
        viewModel.setAppliedRules("1a", cont++)
        viewModel.setAppliedRules("2a,13a", cont++)
        viewModel.setAppliedRules("1a,2a", cont++)
        viewModel.setAppliedRules("1a,2a", cont++)
        viewModel.setAppliedRules("1a,2a,10b", cont++)
        viewModel.setAppliedRules("1a,2a,10b", cont++)
        viewModel.setAppliedRules("2a,11b", cont++)
        viewModel.setAppliedRules("1a,2a,11b", cont++)
        viewModel.setAppliedRules("1a,2a,15a", cont++)
        viewModel.setAppliedRules("1a,2a,15a", cont++)
        viewModel.setAppliedRules("1a,3a,12b", cont++)
        viewModel.setAppliedRules("1a,2a,10a", cont++)
        viewModel.setAppliedRules("1a,2a,10a", cont++)
        viewModel.setAppliedRules("1a,10b", cont++)
        viewModel.setAppliedRules("1a,2a,14b", cont++)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}