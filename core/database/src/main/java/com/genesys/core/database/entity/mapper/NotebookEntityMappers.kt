package com.genesys.core.database.entity.mapper

import com.genesys.core.database.entity.NotebookEntity
import com.genesys.core.database.entity.NotebookFolderEntity
import com.genesys.core.database.entity.NotebookImageEntity
import com.genesys.core.database.entity.NotebookKeyValueEntity
import com.genesys.core.database.entity.NotebookPageEntity
import com.genesys.core.database.entity.NotebookStrokeEntity
import com.genesys.core.model.notebook.Notebook
import com.genesys.core.model.notebook.NotebookFolder
import com.genesys.core.model.notebook.NotebookImage
import com.genesys.core.model.notebook.NotebookKeyValue
import com.genesys.core.model.notebook.NotebookPage
import com.genesys.core.model.notebook.NotebookStroke

// ── Notebook ───────────────────────────────────────────────

fun NotebookEntity.toDomain(): Notebook = Notebook(
    id = id,
    title = title,
    openPageId = openPageId,
    pageIds = pageIds,
    parentFolderId = parentFolderId,
    defaultBackground = defaultBackground,
    defaultBackgroundType = defaultBackgroundType,
    linkedExternalUri = linkedExternalUri,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun Notebook.toEntity(): NotebookEntity = NotebookEntity(
    id = id,
    title = title,
    openPageId = openPageId,
    pageIds = pageIds,
    parentFolderId = parentFolderId,
    defaultBackground = defaultBackground,
    defaultBackgroundType = defaultBackgroundType,
    linkedExternalUri = linkedExternalUri,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun List<NotebookEntity>.toDomainNotebooks(): List<Notebook> = map { it.toDomain() }

// ── Folder ─────────────────────────────────────────────────

fun NotebookFolderEntity.toDomain(): NotebookFolder = NotebookFolder(
    id = id,
    title = title,
    parentFolderId = parentFolderId,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun NotebookFolder.toEntity(): NotebookFolderEntity = NotebookFolderEntity(
    id = id,
    title = title,
    parentFolderId = parentFolderId,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun List<NotebookFolderEntity>.toDomainFolders(): List<NotebookFolder> = map { it.toDomain() }

// ── Page ───────────────────────────────────────────────────

fun NotebookPageEntity.toDomain(): NotebookPage = NotebookPage(
    id = id,
    scroll = scroll,
    notebookId = notebookId,
    background = background,
    backgroundType = backgroundType,
    parentFolderId = parentFolderId,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun NotebookPage.toEntity(): NotebookPageEntity = NotebookPageEntity(
    id = id,
    scroll = scroll,
    notebookId = notebookId,
    background = background,
    backgroundType = backgroundType,
    parentFolderId = parentFolderId,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun List<NotebookPageEntity>.toDomainPages(): List<NotebookPage> = map { it.toDomain() }

// ── Stroke ─────────────────────────────────────────────────

fun NotebookStrokeEntity.toDomain(): NotebookStroke = NotebookStroke(
    id = id,
    size = size,
    pen = pen,
    color = color,
    maxPressure = maxPressure,
    top = top,
    bottom = bottom,
    left = left,
    right = right,
    points = points,
    pageId = pageId,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun NotebookStroke.toEntity(): NotebookStrokeEntity = NotebookStrokeEntity(
    id = id,
    size = size,
    pen = pen,
    color = color,
    maxPressure = maxPressure,
    top = top,
    bottom = bottom,
    left = left,
    right = right,
    points = points,
    pageId = pageId,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun List<NotebookStrokeEntity>.toDomainStrokes(): List<NotebookStroke> = map { it.toDomain() }

// ── Image ──────────────────────────────────────────────────

fun NotebookImageEntity.toDomain(): NotebookImage = NotebookImage(
    id = id,
    x = x,
    y = y,
    height = height,
    width = width,
    uri = uri,
    pageId = pageId,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun NotebookImage.toEntity(): NotebookImageEntity = NotebookImageEntity(
    id = id,
    x = x,
    y = y,
    height = height,
    width = width,
    uri = uri,
    pageId = pageId,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun List<NotebookImageEntity>.toDomainImages(): List<NotebookImage> = map { it.toDomain() }

// ── KeyValue ───────────────────────────────────────────────

fun NotebookKeyValueEntity.toDomain(): NotebookKeyValue = NotebookKeyValue(
    key = key,
    value = value
)

fun NotebookKeyValue.toEntity(): NotebookKeyValueEntity = NotebookKeyValueEntity(
    key = key,
    value = value
)
