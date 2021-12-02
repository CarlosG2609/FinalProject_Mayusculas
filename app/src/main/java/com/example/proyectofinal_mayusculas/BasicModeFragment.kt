package com.example.proyectofinal_mayusculas

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.view.size
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
        var cont = 1
        viewModel.setQuestionNumber(cont)
        viewModel.resetAnswers()
        loadWord()
        loadWordAnswers()
        var input = ""
        val ll_main = binding.linearLayoutBasic as LinearLayout
        var randomNumber = (1..42).random()
        var answer = viewModel.getWordAnswer(randomNumber)
        var wordCase = viewModel.getWord(randomNumber)
        var boolAnswers = BooleanArray(30)
        var wordDivided = "Hi"
            wordDivided = wordCase
            for( i in wordDivided.indices){
                val button_dynamic = Button(context)
                button_dynamic.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                button_dynamic.setBackgroundColor(Color.parseColor("#C3BCBB"))
                button_dynamic.isAllCaps = false
                button_dynamic.text = wordDivided[i].toString()
                if(wordDivided.length > 9){
                    button_dynamic.textSize = 15.toFloat()
                }else if(wordDivided.length >5 && wordDivided.length <= 9){
                    button_dynamic.textSize = 20.toFloat()
                }else{
                    button_dynamic.textSize = 30.toFloat()
                }
                button_dynamic.setOnClickListener{
                    button_dynamic.setBackgroundColor(Color.parseColor("#1a7ece"))
                    if(button_dynamic.isPressed){
                        boolAnswers[i] = true
                    }
                }
                ll_main.addView(button_dynamic)
            }

        binding.clearBasicButton.setOnClickListener{
            input = ""
            boolAnswers = BooleanArray(30)
            for (i in wordDivided.indices){
                ll_main.getChildAt(i).setBackgroundColor(Color.parseColor("#C3BCBB"))
            }
            println("Reset")
        }

        //viewModel.showWordAnswers()
        //AQUI SE AGREGAN LA PARTE DE LAS FUNCIONES DE CADA BOTON
        binding.buttonResultsBasic.setOnClickListener{
            if(viewModel.questionNumber == 10) {
                input = ""
                var aux = true
                for(i in wordDivided.indices){
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
                findNavController().navigate(R.id.action_basicModeFragment_to_resultadosFragment)

            }else{
                input = ""
                var aux = true
                    for(i in wordDivided.indices){
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
                ll_main.removeAllViews()
                viewModel.setQuestionNumber(cont++)
                 randomNumber = (1..42).random()
                 boolAnswers = BooleanArray(30)
                 answer = viewModel.getWordAnswer(randomNumber)
                 wordCase = viewModel.getWord(randomNumber)
                 var wordDivided: String
                    wordDivided = wordCase
                    for( i in wordDivided.indices){
                        val button_dynamic = Button(context)
                        button_dynamic.layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )
                        button_dynamic.setBackgroundColor(Color.parseColor("#C3BCBB"))
                        button_dynamic.isAllCaps = false
                        button_dynamic.text = wordDivided[i].toString()
                        if(wordDivided.length > 9){
                            button_dynamic.textSize = 15.toFloat()
                        }else if(wordDivided.length >5 && wordDivided.length <= 9){
                            button_dynamic.textSize = 20.toFloat()
                        }else{
                            button_dynamic.textSize = 30.toFloat()
                        }
                        button_dynamic.setOnClickListener{
                            button_dynamic.setBackgroundColor(Color.parseColor("#1a7ece"))
                            if(button_dynamic.isPressed){
                                boolAnswers[i] = true
                            }
                        }
                        ll_main.addView(button_dynamic)
                    }

            }
            println("Question Number: " + viewModel.questionNumber)
            println("Right Answers: " + viewModel.rightAnswers)
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun loadWord(){
        var cont = 1
        viewModel.setWord("sierra", cont++)
        viewModel.setWord("sierra madre", cont++)
        viewModel.setWord("sierra nevada", cont++)
        viewModel.setWord("madre", cont++)
        viewModel.setWord("oriente", cont++)
        viewModel.setWord("poniente", cont++)
        viewModel.setWord("oeste", cont++)
        viewModel.setWord("norte", cont++)
        viewModel.setWord("sur", cont++)
        viewModel.setWord("primavera", cont++)
        viewModel.setWord("verano", cont++)
        viewModel.setWord("mario", cont++)
        viewModel.setWord("guadalupe", cont++)
        viewModel.setWord("otoño", cont++)
        viewModel.setWord("río amazonas", cont++)
        viewModel.setWord("río de janeiro", cont++)
        viewModel.setWord("invierno", cont++)
        viewModel.setWord("imss", cont++)
        viewModel.setWord("onu", cont++)
        viewModel.setWord("otan", cont++)
        viewModel.setWord("enero", cont++)
        viewModel.setWord("febrero", cont++)
        viewModel.setWord("marzo", cont++)
        viewModel.setWord("abril", cont++)
        viewModel.setWord("mayo", cont++)
        viewModel.setWord("junio", cont++)
        viewModel.setWord("julio", cont++)
        viewModel.setWord("agosto", cont++)
        viewModel.setWord("septiembre", cont++)
        viewModel.setWord("octubre", cont++)
        viewModel.setWord("noviembre", cont++)
        viewModel.setWord("diciembre", cont++)
        viewModel.setWord("lunes", cont++)
        viewModel.setWord("martes", cont++)
        viewModel.setWord("miércoles", cont++)
        viewModel.setWord("jueves", cont++)
        viewModel.setWord("viernes", cont++)
        viewModel.setWord("sábado", cont++)
        viewModel.setWord("domingo", cont++)
        viewModel.setWord("pcch", cont++)
        viewModel.setWord("mw", cont++)
        viewModel.setWord("mwh", cont++)
    }

    private fun loadWordAnswers(){
        var cont = 1
        viewModel.setWordAnswer("0", cont++)
        viewModel.setWordAnswer("1,8", cont++)
        viewModel.setWordAnswer("1,8", cont++)
        viewModel.setWordAnswer("0", cont++)
        viewModel.setWordAnswer("0", cont++)
        viewModel.setWordAnswer("0", cont++)
        viewModel.setWordAnswer("0", cont++)
        viewModel.setWordAnswer("0", cont++)
        viewModel.setWordAnswer("0", cont++)
        viewModel.setWordAnswer("0", cont++)
        viewModel.setWordAnswer("0", cont++)
        viewModel.setWordAnswer("1", cont++)
        viewModel.setWordAnswer("1", cont++)
        viewModel.setWordAnswer("0", cont++)
        viewModel.setWordAnswer("5", cont++)
        viewModel.setWordAnswer("1,8", cont++)
        viewModel.setWordAnswer("0", cont++)
        viewModel.setWordAnswer("1,2,3,4", cont++)
        viewModel.setWordAnswer("1,2,3", cont++)
        viewModel.setWordAnswer("1,2,3,4", cont++)
        viewModel.setWordAnswer("0", cont++)
        viewModel.setWordAnswer("0", cont++)
        viewModel.setWordAnswer("0", cont++)
        viewModel.setWordAnswer("0", cont++)
        viewModel.setWordAnswer("0", cont++)
        viewModel.setWordAnswer("0", cont++)
        viewModel.setWordAnswer("0", cont++)
        viewModel.setWordAnswer("0", cont++)
        viewModel.setWordAnswer("0", cont++)
        viewModel.setWordAnswer("0", cont++)
        viewModel.setWordAnswer("0", cont++)
        viewModel.setWordAnswer("0", cont++)
        viewModel.setWordAnswer("0", cont++)
        viewModel.setWordAnswer("0", cont++)
        viewModel.setWordAnswer("0", cont++)
        viewModel.setWordAnswer("0", cont++)
        viewModel.setWordAnswer("0", cont++)
        viewModel.setWordAnswer("0", cont++)
        viewModel.setWordAnswer("0", cont++)
        viewModel.setWordAnswer("1,2,3", cont++)
        viewModel.setWordAnswer("1,2", cont++)
        viewModel.setWordAnswer("1,2", cont++)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}