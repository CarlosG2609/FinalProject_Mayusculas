package com.example.proyectofinal_mayusculas

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.proyectofinal_mayusculas.databinding.FragmentAdvancedModeBinding
import com.example.proyectofinal_mayusculas.viewmodels.ProjectViewModel
import com.example.proyectofinal_mayusculas.viewmodels.UsuarioViewModel
import com.example.proyectofinal_mayusculas.viewmodels.UsuariosViewModelFactory
import kotlinx.coroutines.launch

class AdvancedModeFragment : Fragment() {

    private val viewModel: ProjectViewModel by activityViewModels()
    private val viewModeldb: UsuarioViewModel by activityViewModels{
        UsuariosViewModelFactory(
            (activity?.application as UsuarioApp).database.DaoPrincipal()
        )
    }
    private var _binding: FragmentAdvancedModeBinding? = null
    private val binding get()= _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAdvancedModeBinding.inflate(inflater, container, false)
        var cont = 1
        viewModel.setQuestionNumber(cont)
        viewModel.resetAnswers()
        loadAnswers()
        loadSentences()
        //viewModel.showAnswers()
        var input = ""
        val ll_main = binding.llAdvancedLayout as LinearLayout
        var randomNumber = (1..38).random()
        var answer = viewModel.getAnswer(randomNumber)
        var phraseDivided = arrayOfNulls<String>(13)
        phraseDivided = viewModel.getSentence(randomNumber).split(" ").toTypedArray()
        var boolAnswers = BooleanArray(20)
        for (i in phraseDivided.indices) {
            val button_dynamic = Button(context)
            button_dynamic.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            button_dynamic.isAllCaps = false
            button_dynamic.text = phraseDivided[i]
            button_dynamic.setOnClickListener(View.OnClickListener{
                button_dynamic.setBackgroundColor(Color.GREEN)
                if(button_dynamic.isPressed){
                    boolAnswers[i] = true
                }
            })
            ll_main.addView(button_dynamic)
        }

        binding.clearButton.setOnClickListener{
            input = ""
            boolAnswers = BooleanArray(20)
        }



        //AQUI SE AGREGAN LA PARTE DE LAS FUNCIONES DE CADA BOTON
        binding.buttonResultsAdvanced.setOnClickListener{
            if(viewModel.questionNumber == 10) {
                input = ""
                var aux = true
                for(i in phraseDivided.indices){
                    if(boolAnswers[i]){
                        if(aux){
                            input = (i+1).toString()
                            aux = false
                        }else{
                            input = input + "," + (i+1).toString()
                        }
                    }
                }
                if(input == ""){
                    input = "0"
                }
                if(answer == input){
                    println("Correcto")
                    viewModel.addRigthAnswer()
                }else{
                    println("Mal")
                }
                findNavController().navigate(R.id.action_advancedModeFragment_to_resultadosFragment)
            }else{
                input = ""
                var aux = true
                for(i in phraseDivided.indices){
                    if(boolAnswers[i]){
                        if(aux){
                            input = (i+1).toString()
                            aux = false
                        }else{
                            input = input + "," + (i+1).toString()
                        }
                    }
                }
                if(input == ""){
                    input = "0"
                }
                println("Answer " + answer)
                println("Input " + input)
                if(answer == input){
                    println("Correcto")
                    viewModel.addRigthAnswer()

                }else{
                    println("Mal")
                }

                viewModel.setQuestionNumber(cont++)
                ll_main.removeAllViews()
                input = ""
                randomNumber = (1..38).random()
                answer = viewModel.getAnswer(randomNumber)

                phraseDivided = viewModel.getSentence(randomNumber).split(" ").toTypedArray()
                boolAnswers = BooleanArray(20)
                //boolAnswers.fill(false, 1,20)
                for (i in phraseDivided.indices) {
                    val button_dynamic = Button(context)
                    button_dynamic.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    button_dynamic.isAllCaps = false
                    button_dynamic.text = phraseDivided[i]
                    button_dynamic.setOnClickListener(View.OnClickListener{
                        button_dynamic.setBackgroundColor(Color.GREEN)
                        if(button_dynamic.isPressed){
                            boolAnswers[i] = true
                        }
                    })
                    ll_main.addView(button_dynamic)
                }

            }
            println("Question Number: " + viewModel.questionNumber)
            println("Right Answers: " + viewModel.rightAnswers)

        }

        // SI SE QUIERE INSERTAR ALGO A LA BASE DE DATOS, SE PUEDE HACER CON viewModeldb.NOMBRE_FUNCION
        // LAS FUNCIONES DISPONIBLES SON LAS MISMAS QUE PARA UsuarioDao
        lifecycleScope.launch {
            // Asi se debe de utilizar, por poner algun ejemplo
            //viewModeldb.getAllUsuarios()
        }


        // Inflate the layout for this fragment
        return binding.root
    }

    private fun loadSentences(){
        var cont = 1
        viewModel.setSentence("la Barca En que me iré lleva una cruz de olvido.", cont++)
        viewModel.setSentence("calderón de la barca es un famoso escritor del siglo de oro español. ", cont++)
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
        viewModel.setSentence("estimada alicia:  espero que te encuentres muy bien.", cont++)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}