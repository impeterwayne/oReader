package com.genesys.core.domain.repository.library

object LibraryFileSupport {
    val supportedExtensions = setOf(
        "epub", "pdf", "fb2", "mobi", "azw", "azw3", "djvu", "djv", "cbz", "txt", "rtf", "htm", "html"
    )

    val supportedMimeTypes = arrayOf(
        "application/epub+zip",
        "application/pdf",
        "application/x-fictionbook+xml",
        "application/x-mobipocket-ebook",
        "application/vnd.amazon.ebook",
        "image/vnd.djvu",
        "application/vnd.comicbook+zip",
        "application/x-cbz",
        "text/plain",
        "text/html",
        "application/rtf",
        "application/octet-stream"
    )
}
