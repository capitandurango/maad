package com.capps.maad.application.extensions

import com.capps.maad.domain.responses.RelatedTopic

/**
 * Return the name from the character, based on the field [text].
 */
fun RelatedTopic.character() : String? {
    val pattern = "(.w*)*\\s-".toRegex()
    val ans : MatchResult? = pattern.find(text)
    return ans?.value?.substring(startIndex = 0, endIndex = ans.value.length.minus(2))
}