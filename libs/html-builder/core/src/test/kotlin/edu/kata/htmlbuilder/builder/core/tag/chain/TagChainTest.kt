package edu.kata.htmlbuilder.builder.core.tag.chain

import edu.kata.htmlbuilder.builder.core.tag.chain.model.HtmlOutput
import edu.kata.htmlbuilder.builder.core.tag.model.body
import edu.kata.htmlbuilder.builder.core.tag.model.div
import edu.kata.htmlbuilder.builder.core.tag.model.p
import io.kotest.core.spec.style.FreeSpec

class TagChainTest : FreeSpec({

    "traverse a chain with" - {
        "depth of 1" - {
            val body = body(listOf(
                div("div 1"),
                div("div 2"),
                div("div 3", properties = mapOf("class" to "row")),
                div(innerHtml = listOf(
                    p(content = "First paragraph"),
                    p(content = "Second paragraph"),
                    p(content = "Third paragraph"),
                    div(innerHtml = listOf(
                        p(content = "Fourth paragraph"),
                        p(content = "Fifth paragraph"),
                        p(content = "Sixth paragraph"),
                    ), properties = mapOf("class" to "third second"))
                ), properties = mapOf("class" to "row second")
                )
            ))
            "when all tags are content only" {
                val output = HtmlOutput()
                body.render(output).let {
                    println(output.html)
                }
            }
        }
    }

})