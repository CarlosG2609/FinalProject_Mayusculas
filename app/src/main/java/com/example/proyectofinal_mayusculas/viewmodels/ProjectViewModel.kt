package com.example.proyectofinal_mayusculas.viewmodels

import androidx.lifecycle.ViewModel
import com.example.proyectofinal_mayusculas.Todo
import com.example.proyectofinal_mayusculas.Todorules

class ProjectViewModel: ViewModel() {

    // Este ViewModel sirve para almacenar valores entre fragments que se obtienen de editText's o
    // de botones en el programa.
    private var _name: String= ""
    private var _score: Int= 0
    private var _type: String= ""
    private val _todos= mutableListOf<Todo>()
    private var _normasA: String= ""
    private var _normasB: String= ""
    private var _sentences = arrayOfNulls<String>(50)
    private var _answers = arrayOfNulls<String>(50)
    private var _appliedRules= arrayOfNulls<String>(50)
    private var _words = arrayOfNulls<String>(100)
    private var _wordAnswers = arrayOfNulls<String>(100)
    private var _questionNumber: Int = 1
    private var _rightAnswers: Int = 0
    private var _rules= mutableListOf<Todorules>()
    private var _repaso= mutableListOf<Todorules>()

    val name get() = _name
    val score get() = _score
    val type get() = _type
    val todo= _todos
    val normasA get() = _normasA
    val normasB get() = _normasB
    val sentence get() = _sentences
    val answers get() = _answers
    val appliedRules get() = _appliedRules
    val word get() = _words
    val wordAnswers get() = _wordAnswers
    val questionNumber get() = _questionNumber
    val rightAnswers get() = _rightAnswers
    val rules= _rules
    val repaso= _repaso

    // Aquí puedo agregar funciones para actualizar la base de datos
    fun changeName(nuevoNombre: String){
        _name= nuevoNombre
    }
    fun changeScore(nuevoScore: Int){
        _score= nuevoScore
    }
    fun changeType(nuevoType: String){
        _type= nuevoType
    }
    fun changeNormasA(nuevoString: String){
        _normasA= nuevoString
    }
    fun changeNormasB(nuevoString: String){
        _normasB= nuevoString
    }
    fun setSentence(s: String, i:Int){
        _sentences[i] = s
    }
    fun setAnswer(s: String, i:Int){
        _answers[i] = s
    }
    fun setAppliedRules(s: String, i: Int){
        _appliedRules[i]= s
    }
    fun setWordAnswer(s:String, i:Int){
        _wordAnswers[i] = s
    }
    fun getWordAnswer(i:Int):String{
        return wordAnswers[i].toString()
    }
    fun setWord(s:String, i:Int){
        _words[i] = s
    }
    fun getWord(i:Int): String{
        return _words[i].toString()
    }
    fun getSentence(i:Int): String{
        return _sentences[i].toString()
    }
    fun getAnswer(i:Int): String{
        return _answers[i].toString()
    }
    fun getAppliedRules(i: Int): String{
        return _appliedRules[i].toString()
    }
    fun setQuestionNumber(i: Int){
        _questionNumber = i
    }
    fun resetAnswers(){
        _rightAnswers = 0
    }

    fun addRigthAnswer(){
        _rightAnswers++
    }
    fun showSentence(){
        for(i in 1..40){
            println(_sentences[i])
        }
    }
    fun showAnswers(){
        for(i in 1..40){
            println(_answers[i])
        }
    }
    fun showWordAnswers(){
        for(i in 1..42){
            println(word[i] + " " + wordAnswers[i])
        }
    }
}