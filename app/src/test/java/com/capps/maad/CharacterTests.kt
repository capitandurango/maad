package com.capps.maad

import com.capps.maad.application.extensions.character
import com.capps.maad.domain.responses.Icon
import com.capps.maad.domain.responses.RelatedTopic
import org.junit.Test
import org.junit.Assert.*

class CharacterTests {

    /**
     * Test for retreive the name dor the characters.
     */
    @Test
    fun generateCharacter() {
        val text1 = "Cedric Daniels - Cedric Daniels is a fictional character on the HBO drama The Wire, played by Lance Reddick. Daniels is well regarded in the Baltimore Police Department by making his subordinates focus on decent police work and quality arrests."
        val expectedTest1 = "Cedric Daniels"
        val text2 = "Duquan \\\"Dukie\\\" Weems - Duquan \\\"Dukie\\\" Weems is a fictional character on the HBO drama The Wire, played by Jermaine Crawford. Dukie is a student at Edward Tilghman Middle School. He has a difficult home life because many of the adults in his home are either alcoholics or drug addicts."
        val expectedTest2 = "Duquan \\\"Dukie\\\" Weems"
        val text3 = "Lester Freamon - Lester Freamon is a fictional character on the HBO drama The Wire, played by actor Clarke Peters. Freamon is a detective in the Baltimore Police Department's Major Crimes Unit."
        val expectedTest3 = "Lester Freamon"
        val text4 = "Dr. Hibbert - Dr. Julius M. Hibbert, usually referred to as Dr. Hibbert, is a recurring character on the animated series The Simpsons. His speaking voice is provided by Harry Shearer and his singing voice was by Thurl Ravenscroft, and he first appeared in the episode \\\"Bart the Daredevil\\\"."
        val expectedTest4 = "Dr. Hibbert"
        val text5 = "Hans Moleman - Hans Moleman is a recurring character on the animated television series The Simpsons. He was created by series creator Matt Groening and is voiced by Dan Castellaneta and first appeared in the episode \\\"Principal Charming\\\"."
        val expectedTest5 = "Hans Moleman"

        assertEquals(
            RelatedTopic( text = text1, icon = Icon() ).character(),
            expectedTest1
        )
        assertEquals(
            RelatedTopic( text = text2, icon = Icon() ).character(),
            expectedTest2
        )
        assertEquals(
            RelatedTopic( text = text3, icon = Icon() ).character(),
            expectedTest3
        )
        assertEquals(
            RelatedTopic( text = text4, icon = Icon() ).character(),
            expectedTest4
        )
        assertEquals(
            RelatedTopic( text = text5, icon = Icon() ).character(),
            expectedTest5
        )
    }
}