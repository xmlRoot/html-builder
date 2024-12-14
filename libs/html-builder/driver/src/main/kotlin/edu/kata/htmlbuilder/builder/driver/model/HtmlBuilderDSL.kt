package edu.kata.htmlbuilder.builder.driver.model

import edu.kata.htmlbuilder.builder.driver.model.TagName.DIV
import edu.kata.htmlbuilder.builder.driver.model.TagName.H1
import edu.kata.htmlbuilder.builder.driver.model.TagName.PARAGRAPH
import edu.kata.htmlbuilder.builder.driver.model.TagName.SPAN

interface HtmlBuilderDsl {
    fun head( headerBuilder : HtmlHeaderDsl.() -> Unit)
    fun body( bodyBuilder : HtmlBodyDsl.() -> Unit)
}

interface TagContainer {
    fun tag(name : TagName, tagBuilder: HtmlTagDSL.() -> Unit)
}

interface HtmlHeaderDsl{
    fun title(title : String)
    fun link(href : String, props : Map<String, String>)
}

interface HtmlBodyDsl : HtmlBodyTagListDSl

interface HtmlBodyTagListDSl : TagContainer{
    fun div(tagBuilder : HtmlTagDSL.() -> Unit) = tag(DIV, tagBuilder)
    fun span(tagBuilder : HtmlTagDSL.() -> Unit) = tag(SPAN, tagBuilder)
    fun p(tagBuilder : HtmlTagDSL.() -> Unit) = tag(PARAGRAPH, tagBuilder)
    fun h1(tagBuilder : HtmlTagDSL.() -> Unit) = tag(H1, tagBuilder)
}

interface HtmlTagDSL {
    fun properties(propsBuilder : HtmlTagPropertiesDsl.() -> Unit)
    var content : String
    fun innerHtml(innerBuilder : HtmlBodyTagListDSl.() -> Unit)
}

interface HtmlTagPropertiesDsl {
    fun property(name: String, value: String)
    fun id(value : String) = property("id", value)
    fun className(vararg values : String) = property("class", values.joinToString(separator = " "))
}


