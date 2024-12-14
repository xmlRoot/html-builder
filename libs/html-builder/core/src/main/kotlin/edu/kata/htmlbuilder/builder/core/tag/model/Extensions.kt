package edu.kata.htmlbuilder.builder.core.tag.model

import edu.kata.htmlbuilder.builder.core.tag.serializer.BodySerializer
import edu.kata.htmlbuilder.builder.core.tag.serializer.DivSerializer
import edu.kata.htmlbuilder.builder.core.tag.serializer.HeadSerializer
import edu.kata.htmlbuilder.builder.core.tag.serializer.HtmlSerializer
import edu.kata.htmlbuilder.builder.core.tag.serializer.ParagraphSerializer
import edu.kata.htmlbuilder.builder.driver.model.TagName
import java.util.UUID

val Tag.name : TagName
    get() = serializer.token

fun tagId() = UUID.randomUUID().toString()

fun p(content : String, properties : Map<String, String> = emptyMap()) =
    TagWithContent(id = tagId(), serializer = ParagraphSerializer, content = content, properties = properties)
fun div(content : String, properties : Map<String, String> = emptyMap()) =
    TagWithContent(id = tagId(), serializer = DivSerializer, content = content, properties = properties)
fun div(innerHtml : List<Tag>, properties : Map<String, String> = emptyMap()) =
    TagWithInnerHtml(id = tagId(), serializer = DivSerializer, innerHtml = innerHtml, properties = properties)

fun head(innerHtml : List<Tag>, properties : Map<String, String> = emptyMap()) =
    TagWithInnerHtml(id = tagId(), serializer = HeadSerializer, innerHtml = innerHtml, properties = properties)

fun body(innerHtml : List<Tag>, properties : Map<String, String> = emptyMap()) =
    TagWithInnerHtml(id = tagId(), serializer = BodySerializer, innerHtml = innerHtml, properties = properties)

fun html(innerHtml : List<Tag>, properties : Map<String, String> = emptyMap()) =
    TagWithInnerHtml(id = tagId(), serializer = HtmlSerializer, innerHtml = innerHtml, properties = properties)