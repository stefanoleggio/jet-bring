package com.example.jet_bring

import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.NativeKeyEvent
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule


@RunWith(AndroidJUnit4::class)
class ListeTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun buttonExits(){
        composeTestRule.onNodeWithText("Frutta e verdura").assertExists()
    }

    @Test
    fun testingCategory(){

        composeTestRule.onNodeWithText("Frutta e verdura").performClick()
        composeTestRule.onNodeWithText("Mela").performClick()
        composeTestRule.onNodeWithText("Aglio").performClick()
        composeTestRule.onNodeWithText("Liste").performClick()


        composeTestRule.onNodeWithText("Mela").assertIsDisplayed()
        composeTestRule.onNodeWithText("Aglio").assertIsDisplayed()


    }

    @Test
    fun testingNavigator(){
        testingCategory()
        composeTestRule.onNodeWithText("Ispirazione").performClick()

        composeTestRule.onNodeWithText("Il buongiorno si vede dal mattino, Sano e delizioso, Brioche con Fragole, Ricetta").assertExists()

        composeTestRule.onNodeWithText("Profilo").performClick()

        composeTestRule.onNodeWithText("Aspetto lista").performClick()
        composeTestRule.onRoot().printToLog("PROVA")
        composeTestRule.onNodeWithContentDescription("Column Mode").performClick()

        composeTestRule.onNodeWithText("Liste").performClick()




    }




}
@RunWith(AndroidJUnit4::class)
class addRicettaTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()


    @Test
    fun addRicetta(){

        composeTestRule.onNodeWithText("Ispirazione").performClick()

        composeTestRule.onNodeWithContentDescription("Add button").performClick()

        // Nome della ricetta e descrizione
        composeTestRule.onNodeWithText("Nome Modello").performTextInput("testing nome")
        composeTestRule.onNodeWithText("Descrizione").performTextInput("testing descrizione")



        composeTestRule.onNodeWithText("Mela, 1").performClick()
        composeTestRule.onNodeWithText("Aglio, 1").performClick()
        composeTestRule.onNodeWithText("Insalata, 1").performClick()

        // modifico descrizione prodotto
        composeTestRule.onNodeWithText("Mela, 1").performGesture {longClick(center, )}

        composeTestRule.onNodeWithText("Descrizione Prodotto").performTextClearance()
        composeTestRule.onNodeWithText("Descrizione Prodotto").performTextInput("tante")
        composeTestRule.onNodeWithText("Conferma", useUnmergedTree = true ).performClick()

        //salvataggio
        composeTestRule.onNodeWithText("Salva").performClick()
        //Vediamo che sia salvata la ricetta
        composeTestRule.onNodeWithText("John Doe, testing descrizione, testing nome , Ricetta", useUnmergedTree = false ).assertExists()



}




}