package com.example.proyectofinal_mayusculas

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Chronometer
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.activityViewModels
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
        val tiempo= binding.simpleChronometer

        if (viewModel.type=="AvanzadoReloj"){ tiempo.start() }

        var cont = 1
        viewModel.setQuestionNumber(cont)
        viewModel.resetAnswers()

        var input = ""
        var sNormas= ""
        var sNormasFinal= ""

        val ll_main = binding.llAdvancedLayout as LinearLayout
        var randomNumber = (1..38).random()
        var answer = viewModel.getAnswer(randomNumber)
        var phraseDivided = viewModel.getSentence(randomNumber).split(" ").toTypedArray()
        var boolAnswers = BooleanArray(20)
        for (i in phraseDivided.indices) {
            val button_dynamic = Button(context)
            button_dynamic.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            button_dynamic.setBackgroundColor(Color.parseColor("#C3BCBB"))
            button_dynamic.isAllCaps = false
            button_dynamic.text = phraseDivided[i]
            if(phraseDivided.size > 9){
                button_dynamic.textSize = 12.toFloat()
            }else if(phraseDivided.size in 6..9){
                button_dynamic.textSize = 20.toFloat()
            }else{
                button_dynamic.textSize = 30.toFloat()
            }
            button_dynamic.setOnClickListener(View.OnClickListener{
                button_dynamic.setBackgroundColor(Color.parseColor("#1a7ece"))
                if(button_dynamic.isPressed){
                    boolAnswers[i] = true
                }
            })
            ll_main.addView(button_dynamic)
        }


        //AQUI SE AGREGAN LA PARTE DE LAS FUNCIONES DE CADA BOTON
        binding.clearButton.setOnClickListener{
            input = ""
            boolAnswers = BooleanArray(20)
            for (i in phraseDivided.indices){
                ll_main.getChildAt(i).setBackgroundColor(Color.parseColor("#C3BCBB"))
            }
            Toast.makeText(context, "Se han reseteado las respuestas de esta pregunta.", Toast.LENGTH_SHORT).show()
        }

        binding.buttonResultsAdvanced.setOnClickListener{
            if(viewModel.questionNumber == 9) {
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
                    Toast.makeText(context, "Respuesta Correcta", Toast.LENGTH_SHORT).show()
                    viewModel.addRigthAnswer()
                }else{
                    Toast.makeText(context, "Respuesta Incorrecta", Toast.LENGTH_SHORT).show()
                    sNormas+= viewModel.getAppliedRules(randomNumber)+","
                }

                if (sNormas != ""){
                    sNormasFinal= sNormas.substring(0, sNormas.length-1)                  // String
                    val aNormasFinal= sNormasFinal.split(",").toTypedArray()    // Array
                    val sortedNormas= aNormasFinal.distinct().groupBy { it.last() }

                    var firstRules= sortedNormas.values.elementAtOrNull(0)
                    if (firstRules != null){
                        val mutableFirst: MutableList<Int> = mutableListOf()
                        for (i in firstRules.indices){
                            mutableFirst.add(firstRules.elementAt(i).substring(0, firstRules.elementAt(i).length -1).toInt())
                        }
                        val intsFirst = mutableFirst.map { it.toInt() }.toTypedArray().sorted()
                        viewModel.changeNormasA(intsFirst.toString())
                    } else{ viewModel.changeNormasA("") }

                    var secondRules= sortedNormas.values.elementAtOrNull(1)
                    if (secondRules != null) {
                        val mutableSecond: MutableList<Int> = mutableListOf()
                        for (i in secondRules.indices){
                            mutableSecond.add(secondRules.elementAt(i).substring(0, secondRules.elementAt(i).length -1).toInt())
                        }
                        val intsSecond = mutableSecond.map { it.toInt() }.toTypedArray().sorted()
                        viewModel.changeNormasB(intsSecond.toString())
                    } else{ viewModel.changeNormasB("") }
                } else{
                    viewModel.changeNormasA("")
                    viewModel.changeNormasB("")
                }

                viewModel.setQuestionNumber(cont++)
                if (viewModel.type=="AvanzadoReloj"){ tiempo.stop() }
                viewModel.changeTiempo(tiempo.text.toString())

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
                if(answer == input){
                    Toast.makeText(context, "Respuesta Correcta", Toast.LENGTH_SHORT).show()
                    viewModel.addRigthAnswer()
                }else{
                    sNormas+= viewModel.getAppliedRules(randomNumber)+","
                    Toast.makeText(context, "Respuesta Incorrecta", Toast.LENGTH_SHORT).show()
                }

                viewModel.setQuestionNumber(cont++)
                ll_main.removeAllViews()
                input = ""
                randomNumber = (1..38).random()
                answer = viewModel.getAnswer(randomNumber)
                phraseDivided = viewModel.getSentence(randomNumber).split(" ").toTypedArray()
                boolAnswers = BooleanArray(20)
                for (i in phraseDivided.indices) {
                    val button_dynamic = Button(context)
                    button_dynamic.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    button_dynamic.setBackgroundColor(Color.parseColor("#C3BCBB"))
                    button_dynamic.isAllCaps = false
                    button_dynamic.text = phraseDivided[i]
                    if(phraseDivided.size > 9){
                        button_dynamic.textSize = 12.toFloat()
                    }else if(phraseDivided.size in 6..9){
                        button_dynamic.textSize = 20.toFloat()
                    }else{
                        button_dynamic.textSize = 30.toFloat()
                    }
                    button_dynamic.setOnClickListener(View.OnClickListener{
                        button_dynamic.setBackgroundColor(Color.parseColor("#1a7ece"))
                        if(button_dynamic.isPressed){
                            boolAnswers[i] = true
                        }
                    })
                    ll_main.addView(button_dynamic)
                }
                if (viewModel.questionNumber == 9){ binding.buttonResultsAdvanced.text= "Ver Resultados" }
            }
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}