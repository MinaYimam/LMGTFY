// this app is Let me google that for you app
// this app is kept in a funtion and has on create funtion on top
// we have the search text, search button adn search confirmation
//this attributs helps the app running





package com.example.lmgtfy

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.widget.doAfterTextChanged

class MainActivity : AppCompatActivity() {
//private lateinit will import the attribute and we will give each attribute a role
    private lateinit var searchText: EditText
    private lateinit var searchButton: Button
    private lateinit var searchConfirmation: TextView
// the override oncreate function is the main function of this app
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        searchText = findViewById(R.id.enter_search_text)
        searchButton = findViewById(R.id.search_button)
        searchConfirmation = findViewById(R.id.show_search_text)
//search text is editable and is typed by the user
        searchText.doAfterTextChanged {
            echoUserSearchTerm()

        }
//search button is a on click listner whis takes the user to the website so the user can search for whatver the user ha typed
        searchButton.setOnClickListener {
            launchSearch()
        }

    }
//this is one of the private funtion that launch the text and will identify the search text is blank
    //or if the search text has letter inside of it or not
// of the search text space has a letter it will ake a toast message (pop up message) to confirm and
    //shpw the typed message to the user if not it will return and it will show a toast message asking
    //the user to type something
    private fun launchSearch(){
        val text = searchText.text
        if (text.isBlank()) {
            //TODO show user a message to enter text
            Toast.makeText(this, getString(R.string.no_text_error_message), Toast.LENGTH_SHORT)
                .show()
        } else {
            Toast.makeText(
                this,
                getString(R.string.searching_confirmation_message, text),
                Toast.LENGTH_LONG
            ).show()
            //TODO show a toast confirmation
            //TODO launch web browser to search Google

            googleSearch(text.toString())
            // added to string so that the text is editable and will not have an error
        }
    }

// this is the second funtion of the app and this is the most important funtion of the app
// this will let the user go to another app and go to google website so it can search whatever the user
    //has typed in the searchtext
//uri parse will analyze the result whixh is the web address and open it so it can be used by the user
//intent will decide what the developer wants and it can have different action and this intent is to
    //action view which will view the uri
    private fun googleSearch(text: String){
        val webAddress = "https://www.google.com/search?q=$text"
        val uri = Uri.parse(webAddress)
        val browserIntent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(browserIntent)
    }
//this is the last funtion of this app
//this is a funtion that is on the bottom and under the button and it will view  the typed string
// if the search text is blank and it doesnt have any text inside it will make dot dot to show it pending
    //or to let the app is waiting the user to type something
    private fun echoUserSearchTerm() {
        val text = searchText.text.trim()
        if (text.isNotBlank()) {
            searchConfirmation.text = getString(R.string.search_display_text, text)
        } else {
            searchConfirmation.text = getString(R.string.search_display_text, "....")

        }

    }
}