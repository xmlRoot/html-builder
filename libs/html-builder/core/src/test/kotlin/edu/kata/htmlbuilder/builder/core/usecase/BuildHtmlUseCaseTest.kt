package edu.kata.htmlbuilder.builder.core.usecase

import io.kotest.core.spec.style.FreeSpec

class BuildHtmlUseCaseTest : FreeSpec({
    val html = BuildHtmlUseCaseImpl()

    "when creating html" - {
        "the result string has correct html structure" {
            html {
                head {
                    title("Test builder")
                    link(href = "https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css", props = mapOf(
                        "rel" to "stylesheet",
                        "integrity" to "sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH",
                        "crossorigin" to "anonymous"
                    ))
                }
                body {
                    span { content = "Paragraph list" }
                    div {
                        innerHtml {
                            p { content = "Paragraph one" }
                            p { content = "Paragraph two" }
                            p { content = "Paragraph three" }
                        }
                    }
                }
            }.let { htmlResult ->
                println(htmlResult.text)
            }
        }
    }
})