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
                println("Answer $answer")
                println("Input $input")
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}