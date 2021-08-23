package com.kotlin.Lotto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.NumberPicker
import android.widget.Toast
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    private val clearButton : Button by  lazy {
        findViewById<Button>(R.id.clearButton)
    }
    private val addButton : Button by lazy {
        findViewById<Button>(R.id.addButton)
    }
    private val runButton : Button by lazy {
        findViewById<Button>(R.id.runButton)
    }
    private val numberPicker : NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker)
    }
    private var didRun = false //이미 실행되었는지 체크할 변수
    private val pickNumberSet = hashSetOf<Int>()//똑같은 숫자는 더이상 Add되지 않도록

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberPicker.minValue = 1
        numberPicker.maxValue = 45

        initRunButton()
        initAddButton()
    }
    private fun initAddButton(){
        addButton.setOnClickListener {
            if(didRun){
                Toast.makeText(this,"초기화 후에 시도해주세요 ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (pickNumberSet.size >= 5){
                Toast.makeText(this,"번호는 5개까지만 선택 할 수 있습니다. ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
           // if (pickNumberSet.contains(numberPicker.value))
        }
    }
    private fun initRunButton() {
        runButton.setOnClickListener(){
            val list = getRandomNumber()
            Log.d("MainActivity",list.toString())
        }
    }
    private fun getRandomNumber(): List<Int> {
        val numberList = mutableListOf<Int>().apply{
            for (i in 1..45){
                this.add(i)
            }
            this.shuffle()
        }
        val newList  = numberList.subList(0,6)
        return newList.sorted()
    }
}