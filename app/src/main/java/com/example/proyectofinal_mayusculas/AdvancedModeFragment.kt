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

        var cont = 1
        viewModel.setQuestionNumber(cont)
        viewModel.resetAnswers()


        var input = ""
        var sNormas= ""
        var sNormasFinal= ""

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


        //AQUI SE AGREGAN LA PARTE DE LAS FUNCIONES DE CADA BOTON
        binding.clearButton.setOnClickListener{
            input = ""
            boolAnswers = BooleanArray(20)
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

                sNormasFinal= sNormas.substring(0, sNormas.length-1)                  // String
                val aNormasFinal= sNormasFinal.split(",").toTypedArray()    // Array
                val sortedNormas= aNormasFinal.distinct().groupBy { it.last() }

                var firstRules= sortedNormas.values.elementAt(0)
                val mutableFirst: MutableList<Int> = mutableListOf()
                for (i in firstRules.indices){
                    mutableFirst.add(firstRules.elementAt(i).substring(0, firstRules.elementAt(i).length -1).toInt())
                }
                val intsFirst = mutableFirst.map { it.toInt() }.toTypedArray().sorted()
                viewModel.changeNormasA(intsFirst.toString())


                var secondRules= sortedNormas.values.elementAt(1)
                val mutableSecond: MutableList<Int> = mutableListOf()
                for (i in secondRules.indices){
                    mutableSecond.add(secondRules.elementAt(i).substring(0, secondRules.elementAt(i).length -1).toInt())
                }
                val intsSecond = mutableSecond.map { it.toInt() }.toTypedArray().sorted()
                viewModel.changeNormasB(intsSecond.toString())
                viewModel.setQuestionNumber(cont++)

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}