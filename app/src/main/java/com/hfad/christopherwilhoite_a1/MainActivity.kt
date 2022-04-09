package com.hfad.christopherwilhoite_a1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find the number of people inputted into EditText
        val numPeople: EditText = findViewById(R.id.num_people_edittext)
        // Button to calculate the number of pizzas needed and the cost per pizza
        val calculatePizzaTotal: Button = findViewById(R.id.calc_pizza_button)
        // RadioGroup variable where the How Hungry? RadioButton selection will be
        val radioGroup: RadioGroup = findViewById(R.id.radio_group)
        // TextView to Display how many total pizzas are needed
        val totalPizzasNeeded: TextView = findViewById(R.id.total_pizzas_needed_textview)

        // Create references for the second layout displayed
        val costPerPizzaTextView: TextView = findViewById(R.id.cost_per_pizza_textview)
        val costPerPizzaLinearLayout: LinearLayout = findViewById(R.id.cost_per_pizza_linear_layout)
        val costPerPizzaEditText: EditText = findViewById(R.id.cost_per_pizza)
        val refreshCostButton: Button = findViewById(R.id.refresh_cost_button)
        val totalCostTextView: TextView = findViewById(R.id.total_cost)
        val totalCostOfPizzaLinearLayout: LinearLayout = findViewById(R.id.total_cost_of_pizza)

        // Calculate how many pizzas are needed when the button is clicked
        calculatePizzaTotal.setOnClickListener{
            // Get the amount entered into the EditText prompt
            val inputNumPeople: String = numPeople.text.toString()
            // Get the selected radio button from radio_group
            val selectedRadioButton = radioGroup.checkedRadioButtonId

            // Variable for RadioButton to be selected for How Hungry?
            val radioButton: RadioButton = findViewById(selectedRadioButton)
            // Find the hunger level of everyone (Float)
            val hungerLevel = getHunger(radioButton.text.toString())
            // Find the total pizzas needed for everyone
            totalPizzasNeeded.text = calcTotalPizzasNeeded(hungerLevel, inputNumPeople.toFloat()).toString()

            // Set visibility for all invisible components to visible
            costPerPizzaLinearLayout.visibility = View.VISIBLE
            refreshCostButton.visibility = View.VISIBLE
            totalCostTextView.visibility = View.VISIBLE
            val inputCostPerPizza: String = costPerPizzaEditText.text.toString()
            val totalCostReturn = '$' + calcTotalCost(inputCostPerPizza, totalPizzasNeeded.text.toString())
            totalCostTextView.text = totalCostReturn
            totalCostOfPizzaLinearLayout.visibility = View.VISIBLE
        }

        /* When the user changes the cost per pizza, the total cost will update accordingly*/
        refreshCostButton.setOnClickListener{
            // Get the inputted amount for the Cost Per Pizza
            val inputCostPerPizza: String = costPerPizzaEditText.text.toString()
            val totalCostReturn = '$' + calcTotalCost(inputCostPerPizza, totalPizzasNeeded.text.toString())
            totalCostTextView.text = totalCostReturn
        }
    }

    /* Get the hunger level from the radio buttons*/
    fun getHunger(hunger: String) : Float {
        val lightHunger: Float = 1.0F
        val mediumHunger: Float = 2.0F
        val ravenousHunger: Float = 4.0F
        return when (hunger) {
            "Light" -> lightHunger
            "Medium" -> mediumHunger
            else -> ravenousHunger
        }
    }

    /* Calculate the total pizzas needed */
    fun calcTotalPizzasNeeded(hungerLevel: Float, totalNumberOfPeople: Float): Int {
        return ceil((hungerLevel * totalNumberOfPeople) / 8.0F).toInt()
    }

    /* Calculate the total cost for all of the pizzas */
    fun calcTotalCost(costPerPizza: String, totalPizzasNeeded: String) : String {
        val CPP = costPerPizza.toFloat()
        val TPN = totalPizzasNeeded.toFloat()
        val returnVar = (Math.round((CPP * TPN) * 100) / 100.0).toString()
        return returnVar
    }

}
