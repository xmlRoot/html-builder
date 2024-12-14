package edu.kata.htmlbuilder.builder.driver.model

sealed interface TagName {
    val value : String

    data object DIV : TagName {
        override val value: String = "div"
    }
    data object SPAN : TagName {
        override val value: String = "span"
    }
    data object HTML : TagName {
        override val value: String = "html"
    }
    data object BODY : TagName {
        override val value: String = "body"
    }
    data object HEAD : TagName {
        override val value: String = "head"
    }
    data object TITLE : TagName {
        override val value: String = "title"
    }
    data object LINK : TagName {
        override val value: String = "link"
    }
    data object PARAGRAPH : TagName {
        override val value: String = "p"
    }
    data object H1 : TagName {
        override val value: String = "h1"
    }
}