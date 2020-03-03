package com.view

import com.model.Album
import kotlinx.html.body
import kotlinx.html.h1
import kotlinx.html.head
import kotlinx.html.html
import kotlinx.html.li
import kotlinx.html.p
import kotlinx.html.stream.createHTML
import kotlinx.html.title
import kotlinx.html.ul

fun albums(list: List<Album>) = createHTML().html {
        head {
            title("Hello")
        }
        body {
            list.forEach {
                h1 {
                    + "${it.name} ${it.size}"
                }
                p {
                    ul {
                        it.photos.forEach { photo ->
                            li {
                                + photo.id
                            }
                            li {
                               + photo.name
                            }
                        }
                    }
                }
            }
        }
    }
