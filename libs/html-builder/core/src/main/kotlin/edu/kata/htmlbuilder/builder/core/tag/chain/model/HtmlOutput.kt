package edu.kata.htmlbuilder.builder.core.tag.chain.model

import edu.kata.htmlbuilder.builder.core.tag.model.InlineTag
import edu.kata.htmlbuilder.builder.core.tag.model.Tag
import edu.kata.htmlbuilder.builder.core.tag.model.TagWithContent
import edu.kata.htmlbuilder.builder.core.tag.model.TagWithInnerHtml
import edu.kata.htmlbuilder.builder.core.tag.model.name

class HtmlOutput(initialLine : StringBuilder = StringBuilder(), private val blankSymbol : String = "") {

    private var parent : HtmlOutput? = null

    constructor(parent: HtmlOutput) : this(StringBuilder()) {
        this.parent = parent
    }

    private val htmlLines : MutableList<Line> = parent?.htmlLines ?: mutableListOf(Line(
        number = 0,
        offset = 0,
        actual = initialLine,
        blankSymbol = blankSymbol
    ))

    private val tokens : MutableMap<String, TagToken> = HashMap()

    private var line : Line = htmlLines.first()

    fun open(tag : Tag) : TagLocation =
        tag.asToken().also { token ->
            tokens[tag.id] = token
            val openTagStr = tag.serializer.open(tag.properties)
            line.append(openTagStr)
        }.asLocation()

    private fun Tag.asToken() = TagToken(
        tag = this,
        properties = properties,
        line = line.number,
        openIndentOffset = line.offset,
        state = OpenTagTokenState()
    )

    fun close(tag : Tag) {
        val token = tokens[tag.id]!!
        when(token.tag){
            is InlineTag -> {}
            is TagWithContent -> line.append(tag.serializer.close())
            is TagWithInnerHtml -> {
                newLine()
                line.moveToOffset(token.openIndentOffset)
                line.append(tag.serializer.close())
                tokens[tag.id] = token.copy(
                    closeLine = line.number,
                    state = CloseTagTokenState(
                        closedIndentOffset = line.offset
                    )
                )
            }
        }
    }

    fun inline(tag : Tag) {
        tag.asToken().also { token ->
            tokens[tag.id] = token
            line.append(tag.serializer.inline(tag.properties))
        }
    }

    fun indent(spaces : Int = 2){
        line.offset = htmlLines[line.number - 1].offset + spaces
        (1 ..  line.offset).forEach { spaces -> line.append(blankSymbol)}
    }

    fun newLine(){
        line = Line(
            number = line.number + 1,
            offset = line.offset,
            actual = StringBuilder(),
            blankSymbol = blankSymbol
        )
        htmlLines.add(line)
    }

    fun content(tag : Tag) {
        when(tag) {
            is TagWithContent -> {
                line.append(tag.content)
            }
            else -> {}
        }
    }

    val html : String
        get() = htmlLines.fold(StringBuilder()) { acc, line -> acc.append(line.actual).append("\n") }.toString()

}

data class Line(
    val number : Int,
    var offset : Int,
    val actual : StringBuilder,
    val blankSymbol : String
) {
    fun append(fragment : String) = actual.append(fragment)

    fun moveToOffset(offset : Int) {
        (1 .. offset).forEach { actual.append(blankSymbol) }
    }

    fun resetToOffset(offset: Int) {
       this.offset = offset
       actual.clear()
       moveToOffset(offset)
    }
}

data class TagLocation(
    val openOffset : Int,
    val openLine : Int
)

fun TagToken.asLocation() = TagLocation(
    openOffset = openIndentOffset,
    openLine = line
)

data class TagToken(
    val tag : Tag,
    val properties : Map<String, String> = emptyMap(),
    val line : Int,
    val closeLine : Int = line,
    val openIndentOffset : Int,
    val state : TagTokenState
) {
    override fun toString(): String =
        "TagToken<${tag.name}> ${when (state) {
            is CloseTagTokenState -> "Closed[$line:$closeLine]"
            is OpenTagTokenState -> "Open[$line]"
        }}"

}

sealed interface TagTokenState

class OpenTagTokenState : TagTokenState{
    val isOpen : Boolean = true
}

class CloseTagTokenState(
    val closedIndentOffset : Int
) : TagTokenState{
    val isOpen : Boolean = false
}
