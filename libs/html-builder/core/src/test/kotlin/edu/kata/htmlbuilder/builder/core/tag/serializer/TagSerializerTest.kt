package edu.kata.htmlbuilder.builder.core.tag.serializer

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.string.shouldEndWith
import io.kotest.matchers.string.shouldStartWith

class TagSerializerTest : FreeSpec({

    "random tag serializer" - {
        val serializer = DivSerializer
        "has correct open" {
            serializer.open().let { tagString ->
                tagString shouldStartWith "<"
                tagString shouldEndWith ">"
                tagString shouldBeEqual "<div>"
            }
        }

        "has correct close" {
            serializer.close().let { tagString ->
                tagString shouldStartWith "</"
                tagString shouldEndWith ">"
                tagString shouldBeEqual "</div>"
            }
        }

        "inlining is correct" {
            serializer.inline().let { emptyInlineTag ->
                emptyInlineTag shouldBeEqual "</div>"
            }
        }
    }



})

