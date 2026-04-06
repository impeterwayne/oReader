# Overview

# Overview
Relevant source files
- [frontend/apps/filemanager/filemanager.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanager.lua)
- [frontend/apps/filemanager/filemanagercollection.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagercollection.lua)
- [frontend/apps/filemanager/filemanagerfilesearcher.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagerfilesearcher.lua)
- [frontend/apps/filemanager/filemanagerhistory.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagerhistory.lua)
- [frontend/apps/filemanager/filemanagerutil.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagerutil.lua)
- [frontend/apps/reader/modules/readerbookmark.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerbookmark.lua)
- [frontend/apps/reader/modules/readerfont.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfont.lua)
- [frontend/apps/reader/modules/readerhighlight.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua)
- [frontend/apps/reader/modules/readerlink.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerlink.lua)
- [frontend/apps/reader/modules/readerpaging.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerpaging.lua)
- [frontend/apps/reader/modules/readerrolling.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerrolling.lua)
- [frontend/apps/reader/modules/readersearch.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readersearch.lua)
- [frontend/apps/reader/modules/readertoc.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readertoc.lua)
- [frontend/apps/reader/modules/readerview.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerview.lua)
- [frontend/apps/reader/readerui.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/readerui.lua)
- [frontend/device/android/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua)
- [frontend/device/cervantes/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/cervantes/device.lua)
- [frontend/device/generic/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua)
- [frontend/device/input.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/input.lua)
- [frontend/device/kindle/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kindle/device.lua)
- [frontend/device/kobo/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua)
- [frontend/device/pocketbook/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/pocketbook/device.lua)
- [frontend/device/sdl/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/sdl/device.lua)
- [frontend/device/sony-prstux/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/sony-prstux/device.lua)
- [frontend/document/credocument.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/credocument.lua)
- [frontend/readcollection.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/readcollection.lua)
- [frontend/ui/uimanager.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua)
- [frontend/ui/widget/filechooser.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/filechooser.lua)
- [frontend/ui/widget/pathchooser.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/pathchooser.lua)
- [platform/android/llapp_main.lua](https://github.com/koreader/koreader/blob/9e6b1587/platform/android/llapp_main.lua)
- [plugins/coverbrowser.koplugin/covermenu.lua](https://github.com/koreader/koreader/blob/9e6b1587/plugins/coverbrowser.koplugin/covermenu.lua)
- [reader.lua](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua)
- [spec/unit/device_spec.lua](https://github.com/koreader/koreader/blob/9e6b1587/spec/unit/device_spec.lua)

KOReader is a document viewer application designed for e-reader devices. It supports multiple document formats (PDF, EPUB, DjVu, CBZ, etc.) and runs on various platforms including Kobo, Kindle, Android, PocketBook, and desktop systems. This page provides a high-level overview of KOReader's architecture, major subsystems, and how they interact.

For detailed information about specific subsystems, see:

- Application entry and initialization: [Application Entry and Lifecycle](/koreader/koreader/3.1-application-entry-and-lifecycle)
- Platform-specific implementations: [Platform Abstraction and Device Support](/koreader/koreader/2.2-platform-abstraction-and-device-support)
- Document format handling: [Document System](/koreader/koreader/5-document-system)
- User interface framework: [Core Framework Systems](/koreader/koreader/3-core-framework-systems)

## Architecture Layers

KOReader's architecture is organized into five primary layers, each with distinct responsibilities:

```
Bootstrap Layer

Platform Abstraction Layer

Document Layer

UI Framework Layer

Application Layer

FileManager
(filemanager/filemanager.lua)

ReaderUI
(reader/readerui.lua)

UIManager
(ui/uimanager.lua)

Widget System
(ui/widget/*)

GestureDetector
(device/gesturedetector.lua)

Dispatcher
(dispatcher.lua)

DocumentRegistry
(document/documentregistry.lua)

CreDocument
(document/credocument.lua)

PdfDocument
(document/pdfdocument.lua)

DjvuDocument
(document/djvudocument.lua)

Device
(device/generic/device.lua)

Input
(device/input.lua)

Screen/Framebuffer

PowerD

reader.lua
(Main Entry)

setupkoenv

G_reader_settings
G_defaults
```

**Sources:**[reader.lua1-323](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L1-L323)[frontend/ui/uimanager.lua1-50](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L1-L50)[frontend/device/generic/device.lua1-144](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua#L1-L144)[frontend/document/documentregistry.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/documentregistry.lua)[frontend/apps/filemanager/filemanager.lua1-57](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanager.lua#L1-L57)[frontend/apps/reader/readerui.lua1-90](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/readerui.lua#L1-L90)

### Layer Responsibilities
LayerPurposeKey Components**Bootstrap**Application initialization, settings loading, platform setup`reader.lua`, `setupkoenv`, `G_reader_settings`**Platform Abstraction**Hardware-independent interfaces for input, display, power`Device`, `Input`, `Screen`, `PowerD`**UI Framework**Event loop, widget lifecycle, gesture recognition, screen refresh`UIManager`, `InputContainer`, `GestureDetector`, `Dispatcher`**Document**File format support, rendering engines, page caching`DocumentRegistry`, `CreDocument`, `PdfDocument`, `DjvuDocument`**Application**User-facing functionality: file browsing and document reading`FileManager`, `ReaderUI`
**Sources:**[reader.lua1-323](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L1-L323)[frontend/ui/uimanager.lua1-1450](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L1-L1450)[frontend/device/generic/device.lua1-800](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua#L1-L800)

## Application Entry Point

The application starts in [reader.lua1-323](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L1-L323) which performs the following initialization sequence:

1. **Environment Setup**[reader.lua22](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L22-L22): Calls `setupkoenv` to configure Lua search paths
2. **Settings Loading**[reader.lua33-40](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L33-L40): Initializes `G_defaults` and `G_reader_settings`
3. **Device Initialization**[reader.lua151](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L151-L151): Creates the `Device` singleton with platform-specific implementation
4. **UI Mirroring Setup**[reader.lua166-167](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L166-L167): Configures bidirectional text and UI mirroring for RTL languages
5. **Application Launch**[reader.lua238-296](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L238-L296): Instantiates either `FileManager` or `ReaderUI` based on command-line arguments or saved state
6. **Event Loop Execution**[reader.lua241-296](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L241-L296): Transfers control to `UIManager:run()`

```
FileManager/ReaderUI
UIManager
Device
setupkoenv
reader.lua
FileManager/ReaderUI
UIManager
Device
setupkoenv
reader.lua
alt
[Open file]
[Open directory or no args]
require("setupkoenv")
Load G_defaults, G_reader_settings
require("device")
Detect platform, instantiate subclass
require("ui/uimanager")
ReaderUI:showReader(file)
FileManager:showFiles(directory)
UIManager:run()
Event loop until quit
```

**Sources:**[reader.lua1-323](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L1-L323)[frontend/device/generic/device.lua198-309](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua#L198-L309)[frontend/ui/uimanager.lua50-119](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L50-L119)

## Main Applications

KOReader has two primary user-facing applications that share infrastructure but serve distinct purposes:

### FileManager vs ReaderUI

```
Shared Infrastructure

ReaderUI

FileManager

FileChooser
(widget/filechooser.lua)

FileManagerHistory
(filemanagerhistory.lua)

FileManagerCollection
(filemanagercollection.lua)

FileManagerFileSearcher
(filemanagerfilesearcher.lua)

ReaderView
(modules/readerview.lua)

ReaderPaging
(modules/readerpaging.lua)

ReaderRolling
(modules/readerrolling.lua)

ReaderHighlight
(modules/readerhighlight.lua)

ReaderBookmark
(modules/readerbookmark.lua)

ReaderToc
(modules/readertoc.lua)

UIManager

DocumentRegistry

Menu/TouchMenu

Settings System
```

**Sources:**[frontend/apps/filemanager/filemanager.lua47-57](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanager.lua#L47-L57)[frontend/apps/reader/readerui.lua75-90](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/readerui.lua#L75-L90)[frontend/ui/widget/filechooser.lua19-74](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/filechooser.lua#L19-L74)[frontend/apps/reader/modules/readerview.lua30-78](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerview.lua#L30-L78)
ApplicationPurposeEntry MethodKey Components**FileManager**Browse filesystem, manage files, view metadata`FileManager:showFiles()`[filemanager.lua1023-1057](https://github.com/koreader/koreader/blob/9e6b1587/filemanager.lua#L1023-L1057)`FileChooser`, `FileManagerHistory`, `FileManagerCollection`, `FileManagerFileSearcher`**ReaderUI**Read documents, annotate, navigate pages`ReaderUI:showReader()`[readerui.lua900-973](https://github.com/koreader/koreader/blob/9e6b1587/readerui.lua#L900-L973)`ReaderView`, `ReaderPaging`/`ReaderRolling`, `ReaderHighlight`, `ReaderBookmark`, `ReaderToc`
Both applications:

- Inherit from `InputContainer`[ui/widget/container/inputcontainer.lua](https://github.com/koreader/koreader/blob/9e6b1587/ui/widget/container/inputcontainer.lua)
- Register with `UIManager` via `UIManager:show()`[ui/uimanager.lua156-198](https://github.com/koreader/koreader/blob/9e6b1587/ui/uimanager.lua#L156-L198)
- Use `DocumentRegistry` to open files [document/documentregistry.lua](https://github.com/koreader/koreader/blob/9e6b1587/document/documentregistry.lua)
- Share the menu system (`Menu`, `TouchMenu`) [ui/widget/menu.lua](https://github.com/koreader/koreader/blob/9e6b1587/ui/widget/menu.lua)

**Sources:**[frontend/apps/filemanager/filemanager.lua1-444](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanager.lua#L1-L444)[frontend/apps/reader/readerui.lua1-1120](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/readerui.lua#L1-L1120)

## Core Subsystems

### Event Processing Pipeline

All user input flows through a standardized pipeline from hardware to application widgets:

```
EV_KEY
EV_ABS

eventAdjustHook
event_map

tap, hold,
swipe, pan

sendEvent()

Dispatcher action

Widget event

handleEvent()

setDirty()

Hardware
Touch/Key Events

Input
(device/input.lua)

GestureDetector
(gesturedetector.lua)

UIManager
(ui/uimanager.lua)

Dispatcher
(dispatcher.lua)

Widget Stack
InputContainer hierarchy
```

**Sources:**[frontend/device/input.lua1-1500](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/input.lua#L1-L1500)[frontend/device/gesturedetector.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/gesturedetector.lua)[frontend/ui/uimanager.lua50-1450](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L50-L1450)[frontend/dispatcher.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/dispatcher.lua)

The pipeline stages:

1. **Hardware Events**[device/input.lua624-850](https://github.com/koreader/koreader/blob/9e6b1587/device/input.lua#L624-L850): Raw events (`EV_KEY`, `EV_ABS`) from input devices
2. **Event Adjustment**[device/input.lua360-486](https://github.com/koreader/koreader/blob/9e6b1587/device/input.lua#L360-L486): Platform-specific transformations (rotation, mirroring)
3. **Gesture Recognition**[device/gesturedetector.lua](https://github.com/koreader/koreader/blob/9e6b1587/device/gesturedetector.lua): State machine converts touch sequences to gestures (tap, hold, swipe, pan)
4. **UIManager Routing**[ui/uimanager.lua673-799](https://github.com/koreader/koreader/blob/9e6b1587/ui/uimanager.lua#L673-L799): Routes to system handlers, dispatcher actions, or widget stack
5. **Widget Propagation**[ui/widget/container/inputcontainer.lua](https://github.com/koreader/koreader/blob/9e6b1587/ui/widget/container/inputcontainer.lua): Events propagate top-to-bottom through widget tree until consumed

**Sources:**[frontend/device/input.lua1-1500](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/input.lua#L1-L1500)[frontend/ui/uimanager.lua673-1450](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L673-L1450)

### Document Rendering System

The document system abstracts different file formats behind a common `Document` interface:

```
Advanced Features

Rendering Engines

Document Providers

getProvider()

getProvider()

getProvider()

getProvider()

Application
FileManager/ReaderUI

DocumentRegistry
(documentregistry.lua)

CreDocument
(credocument.lua)
EPUB, FB2, HTML, etc.

PdfDocument
(pdfdocument.lua)
PDF

DjvuDocument
(djvudocument.lua)
DjVu

PicDocument
Images

crengine
(CREngine)

MuPDF

DjVuLibre

KoptInterface
(koptinterface.lua)
Reflow & OCR

DocCache
(doccache.lua)
TileCacheItem
```

**Sources:**[frontend/document/documentregistry.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/documentregistry.lua)[frontend/document/credocument.lua21-78](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/credocument.lua#L21-L78)[frontend/document/pdfdocument.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/pdfdocument.lua)[frontend/document/djvudocument.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/djvudocument.lua)[frontend/document/koptinterface.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/koptinterface.lua)

**Provider Selection** [document/documentregistry.lua]: `DocumentRegistry:getProvider()` maps file extensions to document classes:

- `.epub`, `.fb2`, `.html`, `.htm`, `.txt`, `.rtf`, `.mobi`, `.azw` → `CreDocument`
- `.pdf` → `PdfDocument`
- `.djvu`, `.djv` → `DjvuDocument`
- `.jpg`, `.png`, `.gif`, `.bmp` → `PicDocument`

**Document Lifecycle**:

1. **Registration** [document/documentregistry.lua]: Provider classes registered with file extension patterns
2. **Opening** [document/document.lua]: `Document:new()` creates instance, manages refcount
3. **Rendering**[document/credocument.lua312-326](https://github.com/koreader/koreader/blob/9e6b1587/document/credocument.lua#L312-L326): Format-specific rendering to blitbuffer
4. **Caching**[document/doccache.lua](https://github.com/koreader/koreader/blob/9e6b1587/document/doccache.lua): Rendered tiles cached in `DocCache` with LRU eviction
5. **Closing** [document/document.lua]: Refcount decremented, resources freed when reaching zero

**Sources:**[frontend/document/documentregistry.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/documentregistry.lua)[frontend/document/document.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/document.lua)[frontend/document/credocument.lua1-578](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/credocument.lua#L1-L578)[frontend/document/doccache.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/doccache.lua)

### Platform Abstraction

The `Device` class hierarchy provides hardware-independent interfaces:

```
Hardware Abstraction

Touch Devices

E-Ink Devices

provides

provides

provides

Generic
(device/generic/device.lua)

Kobo
(device/kobo/device.lua)

Kindle
(device/kindle/device.lua)

PocketBook
(device/pocketbook/device.lua)

Cervantes
(device/cervantes/device.lua)

Android
(device/android/device.lua)

SDL
(device/sdl/device.lua)

Input
Touch/Keys

Screen
Framebuffer

PowerD
Battery/Frontlight

NetworkMgr
Wi-Fi
```

**Sources:**[frontend/device/generic/device.lua27-144](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua#L27-L144)[frontend/device/kobo/device.lua93-160](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L93-L160)[frontend/device/kindle/device.lua75-140](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kindle/device.lua#L75-L140)[frontend/device/pocketbook/device.lua22-76](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/pocketbook/device.lua#L22-L76)[frontend/device/android/device.lua75-140](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua#L75-L140)

**Device Capabilities**[device/generic/device.lua40-143](https://github.com/koreader/koreader/blob/9e6b1587/device/generic/device.lua#L40-L143): Boolean flags queried via methods like `Device:hasWifiToggle()`, `Device:hasFrontlight()`, `Device:canHWInvert()`. Examples:

```
hasBattery(), hasKeyboard(), hasKeys(), hasDPad(), isTouchDevice(),
hasFrontlight(), hasNaturalLight(), canHWInvert(), canSuspend(),
canReboot(), canPowerOff(), canToggleMassStorage()

```

**Platform Detection**[device/generic/device.lua198-250](https://github.com/koreader/koreader/blob/9e6b1587/device/generic/device.lua#L198-L250): The `Device` singleton instantiates the appropriate platform-specific subclass by probing:

- On Android: `android.prop.product`[device/android/device.lua77](https://github.com/koreader/koreader/blob/9e6b1587/device/android/device.lua#L77-L77)
- On Kobo: `/bin/kobo_config.sh` model code [device/kobo/device.lua1670-1900](https://github.com/koreader/koreader/blob/9e6b1587/device/kobo/device.lua#L1670-L1900)
- On Kindle: `/proc/usid` hardware identifier [device/kindle/device.lua377-700](https://github.com/koreader/koreader/blob/9e6b1587/device/kindle/device.lua#L377-L700)
- On PocketBook: InkView API model query [device/pocketbook/device.lua800-1000](https://github.com/koreader/koreader/blob/9e6b1587/device/pocketbook/device.lua#L800-L1000)

**Sources:**[frontend/device/generic/device.lua1-800](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua#L1-L800)[frontend/device/kobo/device.lua1-2500](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L1-L2500)[frontend/device/kindle/device.lua1-1200](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kindle/device.lua#L1-L1200)[frontend/device/pocketbook/device.lua1-1300](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/pocketbook/device.lua#L1-L1300)[frontend/device/android/device.lua1-600](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua#L1-L600)

## Settings and Configuration

KOReader maintains two levels of settings persistence:

```
UI Layer

Configuration Sources

Per-Document Settings

Global Settings

fallback

G_reader_settings
(luasettings.lua)
settings.reader.lua

G_defaults
(luadefaults.lua)

DocSettings
(docsettings.lua)
*.sdr/metadata.lua

CreOptions
(creoptions.lua)
EPUB settings

KoptOptions
(koptoptions.lua)
PDF reflow

ReaderMenu
TouchMenu

ReaderConfig
Quick Settings

Dispatcher
Actions
```

**Sources:**[frontend/luasettings.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/luasettings.lua)[frontend/docsettings.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/docsettings.lua)[frontend/ui/data/creoptions.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/data/creoptions.lua)[frontend/ui/data/koptoptions.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/data/koptoptions.lua)
Settings TypeScopeStorage LocationExample Settings**`G_reader_settings`**Global across all documents`settings.reader.lua`Language, font mappings, default zoom, Wi-Fi state**`G_defaults`**Read-only defaults`frontend/ui/data/defaults.lua`View modes, overlap pixels, gesture zones**`DocSettings`**Per-document`<filename>.sdr/metadata.lua`Last page, bookmarks, highlights, reading position**`CreOptions`**EPUB rendering (per-document)Stored in `DocSettings`Font face, line spacing, margins, CSS**`KoptOptions`**PDF reflow (per-document)Stored in `DocSettings`Contrast, quality, word spacing, column detection
**Settings Access Pattern**:

1. Application calls `G_reader_settings:readSetting(key)`[luasettings.lua](https://github.com/koreader/koreader/blob/9e6b1587/luasettings.lua)
2. If not found, falls back to `G_defaults:readSetting(key)`[luadefaults.lua](https://github.com/koreader/koreader/blob/9e6b1587/luadefaults.lua)
3. Document-specific settings override globals via `DocSettings:readSetting(key)`[docsettings.lua](https://github.com/koreader/koreader/blob/9e6b1587/docsettings.lua)

**Sources:**[frontend/luasettings.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/luasettings.lua)[frontend/luadefaults.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/luadefaults.lua)[frontend/docsettings.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/docsettings.lua)[frontend/ui/data/creoptions.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/data/creoptions.lua)[frontend/ui/data/koptoptions.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/data/koptoptions.lua)

## Module System

Both `FileManager` and `ReaderUI` use a module registration pattern to extend functionality:

**Module Registration**[apps/reader/readerui.lua92-102](https://github.com/koreader/koreader/blob/9e6b1587/apps/reader/readerui.lua#L92-L102):

```
function ReaderUI:registerModule(name, ui_module, always_active)
    if name then
        self[name] = ui_module
        ui_module.name = "reader" .. name
    end
    table.insert(self, ui_module)
    if always_active then
        table.insert(self.active_widgets, ui_module)
    end
end
```

**ReaderUI Modules**[apps/reader/readerui.lua136-258](https://github.com/koreader/koreader/blob/9e6b1587/apps/reader/readerui.lua#L136-L258):

- `view` → `ReaderView` (rendering orchestration)
- `paging` → `ReaderPaging` (discrete page navigation)
- `rolling` → `ReaderRolling` (continuous scroll navigation)
- `highlight` → `ReaderHighlight` (text selection and markup)
- `bookmark` → `ReaderBookmark` (bookmarks and annotations)
- `search` → `ReaderSearch` (full-text search)
- `link` → `ReaderLink` (hyperlink navigation)
- `toc` → `ReaderToc` (table of contents)
- `dictionary` → `ReaderDictionary` (word lookup)
- `wikipedia` → `ReaderWikipedia` (Wikipedia integration)
- `menu` → `ReaderMenu` (configuration interface)
- `config` → `ReaderConfig` (quick settings panel)

**FileManager Modules**[apps/filemanager/filemanager.lua398-430](https://github.com/koreader/koreader/blob/9e6b1587/apps/filemanager/filemanager.lua#L398-L430):

- `history` → `FileManagerHistory` (recently opened files)
- `collections` → `FileManagerCollection` (user-created collections)
- `filesearcher` → `FileManagerFileSearcher` (file search)
- `bookinfo` → `FileManagerBookInfo` (metadata display)

Each module receives events via `handleEvent()` and can modify UI state via `UIManager:setDirty()`.

**Sources:**[frontend/apps/reader/readerui.lua92-430](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/readerui.lua#L92-L430)[frontend/apps/filemanager/filemanager.lua385-430](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanager.lua#L385-L430)

---

# Architecture-Overview

# Architecture Overview
Relevant source files
- [frontend/apps/filemanager/filemanager.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanager.lua)
- [frontend/apps/filemanager/filemanagercollection.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagercollection.lua)
- [frontend/apps/filemanager/filemanagerfilesearcher.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagerfilesearcher.lua)
- [frontend/apps/filemanager/filemanagerhistory.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagerhistory.lua)
- [frontend/apps/filemanager/filemanagerutil.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagerutil.lua)
- [frontend/apps/reader/modules/readerbookmark.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerbookmark.lua)
- [frontend/apps/reader/modules/readerfont.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfont.lua)
- [frontend/apps/reader/modules/readerhighlight.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua)
- [frontend/apps/reader/modules/readerlink.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerlink.lua)
- [frontend/apps/reader/modules/readerpaging.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerpaging.lua)
- [frontend/apps/reader/modules/readerrolling.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerrolling.lua)
- [frontend/apps/reader/modules/readersearch.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readersearch.lua)
- [frontend/apps/reader/modules/readertoc.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readertoc.lua)
- [frontend/apps/reader/modules/readerview.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerview.lua)
- [frontend/apps/reader/readerui.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/readerui.lua)
- [frontend/device/android/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua)
- [frontend/device/cervantes/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/cervantes/device.lua)
- [frontend/device/generic/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua)
- [frontend/device/input.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/input.lua)
- [frontend/device/kindle/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kindle/device.lua)
- [frontend/device/kobo/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua)
- [frontend/device/pocketbook/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/pocketbook/device.lua)
- [frontend/device/sdl/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/sdl/device.lua)
- [frontend/device/sony-prstux/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/sony-prstux/device.lua)
- [frontend/document/credocument.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/credocument.lua)
- [frontend/readcollection.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/readcollection.lua)
- [frontend/ui/uimanager.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua)
- [frontend/ui/widget/filechooser.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/filechooser.lua)
- [frontend/ui/widget/pathchooser.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/pathchooser.lua)
- [platform/android/llapp_main.lua](https://github.com/koreader/koreader/blob/9e6b1587/platform/android/llapp_main.lua)
- [plugins/coverbrowser.koplugin/covermenu.lua](https://github.com/koreader/koreader/blob/9e6b1587/plugins/coverbrowser.koplugin/covermenu.lua)
- [reader.lua](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua)
- [spec/unit/device_spec.lua](https://github.com/koreader/koreader/blob/9e6b1587/spec/unit/device_spec.lua)

## Purpose and Scope

This document describes KOReader's high-level architecture, explaining how the major systems interact to form a complete document reader application. It covers the initialization flow, core framework components, application layer organization, document rendering pipeline, and UI widget system.

For detailed terminology and concept definitions, see [Key Concepts and Terminology](/koreader/koreader/1.2-key-concepts-and-terminology). For build system details, see [Build System and Development Workflow](/koreader/koreader/2.1-build-system-and-development-workflow). For platform-specific implementations, see [Platform Abstraction and Device Support](/koreader/koreader/2.2-platform-abstraction-and-device-support).

## Overall System Architecture

KOReader is organized into several major subsystems that layer from hardware abstraction up to user-facing applications:

**System Architecture - Major Components**

```
Reader Modules

UI Widget System

Document System

Platform Abstraction

Core Framework

Application Layer

Application Entry

require('device')

require('ui/uimanager')

FileManager:showFiles()

ReaderUI:showReader()

show()/close()

broadcastEvent()

Device.input

Device.screen

Device.powerd

UIManager:handleInput()

DocumentRegistry:openDocument()

DocumentRegistry:openDocument()

new()

new()

KoptInterface:new()

registerModule()

registerModule()

registerModule()

registerModule()

extend

extend

executeAction()

executeAction()

reader.lua
G_reader_settings
setupkoenv

FileManager
frontend/apps/filemanager/
filemanager.lua

ReaderUI
frontend/apps/reader/
readerui.lua

UIManager
frontend/ui/uimanager.lua
_window_stack
_task_queue
_dirty

Dispatcher
frontend/dispatcher.lua
registered_actions

Device
frontend/device/generic/
device.lua

Input
frontend/device/input.lua
waitEvent()
handleInput()

Screen
ffi/framebuffer*.lua
ffi/blitbuffer.lua

PowerD
frontend/device/generic/
powerd.lua

DocumentRegistry
frontend/document/
documentregistry.lua
providers

CreDocument
frontend/document/
credocument.lua

PdfDocument
frontend/document/
pdfdocument.lua

KoptInterface
frontend/document/
koptinterface.lua

Widget
WidgetContainer
frontend/ui/widget/

InputContainer
frontend/ui/widget/
container/
inputcontainer.lua

FocusManager
frontend/ui/widget/
container/
focusmanager.lua

ReaderHighlight
frontend/apps/reader/
modules/
readerhighlight.lua

ReaderPaging
readerpaging.lua

ReaderRolling
readerrolling.lua

ReaderAnnotation
readerannotation.lua
```

**Sources:**

- [reader.lua1-323](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L1-L323)
- [frontend/ui/uimanager.lua20-48](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L20-L48)
- [frontend/device/generic/device.lua27-151](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua#L27-L151)
- [frontend/apps/filemanager/filemanager.lua47-444](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanager.lua#L47-L444)
- [frontend/apps/reader/readerui.lua75-444](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/readerui.lua#L75-L444)
- [frontend/document/documentregistry.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/documentregistry.lua)

## Application Entry and Initialization

The application starts at `reader.lua`, which orchestrates environment setup, settings loading, device initialization, and application launch:

```
FileManager or ReaderUI
UIManager
Device
setupkoenv.lua
reader.lua
Operating System
FileManager or ReaderUI
UIManager
Device
setupkoenv.lua
reader.lua
Operating System
alt
[File argument provided]
[Directory argument provided]
[Default startup]
Execute reader.lua
Set locale, line buffering
require("setupkoenv")
Lua paths configured
Apply userpatch.early
Load G_reader_settings
require("device")
Auto-detect platform
Device instance
Device:init()
Initialize screen, input, powerd
require("ui/uimanager")
UIManager:init()
Device:_UIManagerReady(self)
Determine startup mode
(file, directory, or start_with)
ReaderUI:showReader(file)
FileManager:showFiles(directory)
Launch based on start_with setting
UIManager:run()
Event loop runs
exit_code
Device:exit()
os.exit(exit_code)
```

**Key initialization steps:**

1. **Environment Setup**[reader.lua22](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L22-L22) - `setupkoenv` configures Lua module paths
2. **Settings Load**[reader.lua38-40](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L38-L40) - `G_reader_settings` loads persistent configuration
3. **Device Detection**[reader.lua151](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L151-L151) - Platform-specific `Device` class instantiated
4. **UIManager Initialization**[reader.lua181](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L181-L181) - Event loop and widget system prepared
5. **Application Launch**[reader.lua238-282](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L238-L282) - Either `FileManager` or `ReaderUI` started based on arguments

**Sources:**

- [reader.lua1-308](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L1-L308)
- [frontend/ui/uimanager.lua50-119](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L50-L119)
- [frontend/device/generic/device.lua198-328](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua#L198-L328)

## Core Framework Layers

The core framework provides platform abstraction, input handling, and widget lifecycle management:

```
Layer 1: Platform Abstraction

Layer 2: Input Processing

Layer 3: Widget Management

Layer 4: Application Events

provides

provides

provides

raw events

touch events

key events

gestures

named events

handleInput()

sendEvent()

schedule()

setDirty()

broadcasts

receives

Event Objects
ui/event.lua

UIManager
Singleton

_window_stack
Widget Stack

_task_queue
Scheduled Tasks

_dirty
Repaint Queue

Input
device/input.lua

GestureDetector
State Machine

event_map
Keycode Translation

Device
Platform-specific

Screen
Framebuffer

Input Devices
/dev/input/event*

PowerD
Battery/Charging
```

### UIManager: The Central Orchestrator

`UIManager` is a singleton defined in [frontend/ui/uimanager.lua20](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L20-L20) that manages three core responsibilities:
ResponsibilityImplementationKey Methods**Widget Lifecycle**`_window_stack` array [frontend/ui/uimanager.lua33](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L33-L33)`show()`[frontend/ui/uimanager.lua156](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L156-L156)`close()`[frontend/ui/uimanager.lua215](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L215-L215)`setDirty()`[frontend/ui/uimanager.lua581](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L581-L581)**Event Dispatch**`event_handlers` table [frontend/ui/uimanager.lua30](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L30-L30)`handleInput()`[frontend/ui/uimanager.lua1226](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L1226-L1226)`sendEvent()`[frontend/ui/uimanager.lua826](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L826-L826)`broadcastEvent()`[frontend/ui/uimanager.lua857](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L857-L857)**Task Scheduling**`_task_queue` sorted array [frontend/ui/uimanager.lua34](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L34-L34)`schedule()`[frontend/ui/uimanager.lua295](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L295-L295)`scheduleIn()`[frontend/ui/uimanager.lua335](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L335-L335)`nextTick()`[frontend/ui/uimanager.lua353](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L353-L353)
**Widget Stack Management**

The `_window_stack`[frontend/ui/uimanager.lua33](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L33-L33) maintains window-level widgets in display order:

- Toast widgets (highest priority) inserted at top
- Modal widgets inserted above non-modals
- Standard widgets at bottom
- Only widgets in `_window_stack` are checked for `_dirty` flags [frontend/ui/uimanager.lua606-629](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L606-L629)

**UIManager State Variables**

```
UIManager Internal State

painted bottom-to-top

sorted by time

triggers _repaint()

_window_stack
array of {x, y, widget}

_dirty
hash: widget → true

_task_queue
array of {time, action, args}

_refresh_stack
array of refresh regions

event_handlers
event name → handler function
```

**Event Loop Implementation**[frontend/ui/uimanager.lua1339-1457](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L1339-L1457):

```
function UIManager:run()
  while not self._exit_code do
    1. wait_until = calculate next task time from _task_queue
    2. ok, ev = Input:waitEvent(wait_until) -- blocks with timeout
    3. if ev then handleInput(ev) end  -- dispatch to widgets
    4. Run due tasks from _task_queue
    5. _repaint() -- paint dirty widgets to screen BB
    6. _refresh() -- issue screen refresh ioctls
  end
end

```

**Sources:**

- [frontend/ui/uimanager.lua20-48](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L20-L48)
- [frontend/ui/uimanager.lua156-198](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L156-L198)
- [frontend/ui/uimanager.lua215-282](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L215-L282)
- [frontend/ui/uimanager.lua295-378](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L295-L378)
- [frontend/ui/uimanager.lua581-671](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L581-L671)
- [frontend/ui/uimanager.lua826-868](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L826-L868)
- [frontend/ui/uimanager.lua1339-1457](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L1339-L1457)

### Device Abstraction

The `Device` hierarchy provides platform-independent interfaces to hardware:

```
extend

extend

extend

extend

extend

extend

variants

variants

variants

Device:Generic
device/generic/device.lua
Base implementation

Device:Kobo
device/kobo/device.lua

Device:Kindle
device/kindle/device.lua

Device:PocketBook
device/pocketbook/device.lua

Device:Cervantes
device/cervantes/device.lua

Device:Remarkable
device/remarkable/device.lua

Device:SDL
device/sdl/device.lua

KoboNova
Clara HD

KoboStorm
Libra

KoboMonza
Libra Colour
```

**Capability Detection Pattern**[frontend/device/generic/device.lua27-143](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua#L27-L143):

- Boolean function fields: `hasFrontlight()`, `hasWifiToggle()`, `canSuspend()`, etc.
- Platform-specific implementations override these
- Examples:

- `Kobo.hasFrontlight = yes`[frontend/device/kobo/device.lua123](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L123-L123)
- `Kindle.canSuspend = yes`[frontend/device/kindle/device.lua352](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kindle/device.lua#L352-L352)
- `SDL.hasEinkScreen = no`[frontend/device/sdl/device.lua80](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/sdl/device.lua#L80-L80)

**Sources:**

- [frontend/device/generic/device.lua27-151](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua#L27-L151)
- [frontend/device/kobo/device.lua93-160](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L93-L160)
- [frontend/device/kindle/device.lua337-370](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kindle/device.lua#L337-L370)

### Input Event Processing

The `Input` class [frontend/device/input.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/input.lua) processes raw hardware events into application-level events:

**Input Event Processing Pipeline**

```
UIManager Dispatch

GestureDetector (device/gesturedetector.lua)

Input Class (frontend/device/input.lua)

Hardware Layer

C.read(fd, &input_event)

raw input_event

EV_ABS

EV_KEY

/dev/input/event*
EV_KEY, EV_ABS
EV_MSC, EV_SYN

Input:waitEvent()
C.poll() on fds

Input.eventAdjustHook
Device-specific transforms

Input.event_map
scan code → key name

GestureDetector:feedEvent()

State Machine
touching → tapState
→ holdState
→ panState

Gesture Events
tap, hold, swipe
pan, pinch, rotate

UIManager:handleInput()

UIManager:sendEvent()
to top widget
```

**Key Input Structures**

The `Input` class maintains several key tables:

- `event_map`[frontend/device/input.lua128-138](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/input.lua#L128-L138) - maps scan codes to key names (e.g., `[102] = "Home"`)
- `rotation_map`[frontend/device/input.lua140-150](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/input.lua#L140-L150) - remaps keys per screen rotation
- `eventAdjustHook`[frontend/device/input.lua157](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/input.lua#L157-L157) - device-specific coordinate/event transforms
- `event_map_adapter`[frontend/device/input.lua160](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/input.lua#L160-L160) - adapter for non-standard devices

**Event Flow Implementation**[frontend/device/input.lua531-652](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/input.lua#L531-L652):

1. `waitEvent()` calls `C.poll()` on input device file descriptors with timeout
2. Reads raw `struct input_event` (type, code, value) from `/dev/input/event*`
3. `eventAdjustHook()` applies device-specific transforms (coordinate swap/mirror, rotation)
4. Touch events (EV_ABS) → `GestureDetector:feedEvent()`[frontend/device/gesturedetector.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/gesturedetector.lua)
5. Key events (EV_KEY) → `event_map` lookup → named key event
6. Returns Event object to `UIManager:handleInput()`

**Sources:**

- [frontend/device/input.lua48-169](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/input.lua#L48-L169)
- [frontend/device/input.lua531-652](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/input.lua#L531-L652)
- [frontend/device/gesturedetector.lua1-100](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/gesturedetector.lua#L1-L100)

## Application Layer

KOReader has two main application modes that share infrastructure but serve different purposes:

```
Shared Components

ReaderUI Mode

FileManager Mode

contains

contains

contains

contains

contains

contains

registers

uses

uses

managed by

managed by

file selected

Home/Back

FileManager
apps/filemanager/filemanager.lua

FileManagerMenu
Top Menu System

FileChooser
File Browser Widget

FileManagerHistory
Reading History

FileManagerCollection
Book Collections

ReaderUI
apps/reader/readerui.lua

ReaderMenu
Top Menu System

ReaderView
Document Display

Reader Modules

DocumentRegistry
Provider Selection

UIManager
Event Loop

Device
Platform Abstraction
```

### FileManager

`FileManager` provides file browsing, history, and collection management:

**Core Components** [frontend/apps/filemanager/filemanager.lua]:

- `FileChooser` - displays directory contents as a scrollable list
- `FileManagerMenu` - top menu with settings, tools, search
- File operations - open, delete, move, copy via context menus
- Plugin integration - statistics, calibre, cover browser

**Initialization** [frontend/apps/filemanager/filemanager.lua]:

```
function FileManager:init()
    -- Create file chooser widget
    self.file_chooser = FileChooser:new{...}
    
    -- Setup menu system
    self.menu = FileManagerMenu:new{...}
    
    -- Register plugins
    for _, plugin in ipairs(PluginLoader:loadPlugins()) do
        plugin:addToMainMenu(self.menu.menu_items)
    end
end
```

**Sources:**

- [frontend/apps/filemanager/filemanagermenu.lua24-61](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagermenu.lua#L24-L61)

### ReaderUI

`ReaderUI` manages document reading with a modular architecture:

**Module Registration Pattern**[frontend/apps/reader/readerui.lua92-102](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/readerui.lua#L92-L102):

```
function ReaderUI:registerModule(name, ui_module, always_active)
    if name then
        self[name] = ui_module  -- e.g., self.highlight
    end
    table.insert(self, ui_module)  -- ordered list
    if always_active then
        table.insert(self.active_widgets, ui_module)
    end
end
```

**Core Modules**[frontend/apps/reader/readerui.lua137-371](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/readerui.lua#L137-L371):
ModulePurposeFile`ReaderView`Document rendering and display`modules/readerview.lua``ReaderHighlight`Text selection and annotation`modules/readerhighlight.lua``ReaderPaging`Page-based navigation (PDF)`modules/readerpaging.lua``ReaderRolling`Scroll-based navigation (EPUB)`modules/readerrolling.lua``ReaderConfig`Settings UI integration`modules/readerconfig.lua``ReaderDictionary`Word lookup`modules/readerdictionary.lua`
**Conditional Loading**[frontend/apps/reader/readerui.lua227-371](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/readerui.lua#L227-L371):

- `document.info.has_pages` → load paging modules (PDF/DjVu)
- `else` → load rolling modules (EPUB/TXT)
- All documents get core modules (highlight, bookmark, toc)

**Sources:**

- [frontend/apps/reader/readerui.lua75-111](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/readerui.lua#L75-L111)
- [frontend/apps/reader/readerui.lua137-371](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/readerui.lua#L137-L371)

## Document System Architecture

The document system abstracts different rendering engines behind a common interface:

**Document System - Providers and Caching**

```
Cache System (frontend/document/doccache.lua)

FFI Engine Bindings

Document Providers

Document Base Class (frontend/document/document.lua)

DocumentRegistry (frontend/document/documentregistry.lua)

new{file=...}

new{file=...}

new{file=...}

new{file=...}

extend

extend

extend

cre.newDocView()

mupdf.openDocument()

KoptInterface:new()

KoptInterface:new()

cache:insert()

cache:insert()

DocumentRegistry:getProvider()
extension → provider class

DocumentRegistry:openDocument()
file → Document instance

providers table
{pdf = PdfDocument,
epub = CreDocument, ...}

Document
Base class
_readMetadata()
close()
refcount management

CreDocument
frontend/document/credocument.lua
_document (cre userdata)
loadDocument()
renderDocument()

PdfDocument
frontend/document/pdfdocument.lua
_document (mupdf userdata)
drawPage()

DjvuDocument
frontend/document/djvudocument.lua

PicDocument
frontend/document/picdocument.lua

libs/libkoreader-cre
crengine FFI
cre.newDocView()

libs/libkoreader-mupdf
MuPDF FFI
mupdf.openDocument()

KoptInterface
frontend/document/koptinterface.lua
k2pdfopt reflow engine

DocCache
LRU cache singleton

TileCacheItem
Rendered page tiles

ContextCacheItem
Reflow contexts
```

**DocumentRegistry Provider Map**

The `DocumentRegistry`[frontend/document/documentregistry.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/documentregistry.lua) maintains a `providers` table mapping file extensions to Document classes:
ExtensionProvider ClassEngineFile`.epub`, `.fb2`, `.txt`, `.html`, `.htm``CreDocument`CREngine`frontend/document/credocument.lua``.pdf``PdfDocument`MuPDF`frontend/document/pdfdocument.lua``.djvu`, `.djv``DjvuDocument`DjVuLibre`frontend/document/djvudocument.lua``.jpg`, `.png`, `.gif`, `.svg``PicDocument`Native`frontend/document/picdocument.lua`
**Sources:**

- [frontend/document/documentregistry.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/documentregistry.lua)
- [frontend/document/document.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/document.lua)
- [frontend/document/credocument.lua1-100](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/credocument.lua#L1-L100)
- [frontend/document/pdfdocument.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/pdfdocument.lua)
- [frontend/document/koptinterface.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/koptinterface.lua)
- [frontend/document/doccache.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/doccache.lua)

### DocumentRegistry

**Provider Selection** [frontend/document/documentregistry.lua]:

```
function DocumentRegistry:getProvider(filename)
    local extension = util.getFileNameSuffix(filename)
    return self.providers[extension]
end
```

**Provider Registration** [frontend/document/documentregistry.lua]:

- `registerProvider(provider, extensions)` - maps file extensions to provider classes
- Example: `registerProvider(CreDocument, {"epub", "fb2", "txt", "html"})`

### Rendering Pipeline

**Document Opening and Rendering Sequence**:

```
DocCache singleton
FFI Engine (MuPDF/CRE)
Document (PdfDocument/CreDocument)
DocumentRegistry
ReaderUI
DocCache singleton
FFI Engine (MuPDF/CRE)
Document (PdfDocument/CreDocument)
DocumentRegistry
ReaderUI
Document is now open but not loaded
Rendering with cache lookup
alt
[Cache Hit]
[Cache Miss]
DocumentRegistry:openDocument(file)
getProvider(file)
by extension
Provider:new{file=file}
init()
setupCallCache()
mupdf.openDocument()
or cre.newDocView()
Document instance
Document instance
loadDocument()
Load document structure
_readMetadata()
drawPage(pageno) or
renderDocument()
DocCache:check(cache_key)
Cached BlitBuffer
Render page/tile
Raw pixel data
Create BlitBuffer
DocCache:insert(cache_key, bb)
BlitBuffer
paintTo(bb, x, y)
```

**Cache Key Structure**

Document cache uses composite keys:

- `PdfDocument`: `"renderpg-{page}-{dc}-{zoom}-{rotation}-{gamma}-{render_mode}"`
- `CreDocument`: Relies on CREngine's internal disk cache
- `KoptInterface`: `"kctx-{page}-{md5(params)}"`

**Sources:**

- [frontend/document/documentregistry.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/documentregistry.lua)
- [frontend/document/document.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/document.lua)
- [frontend/document/credocument.lua152-326](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/credocument.lua#L152-L326)
- [frontend/document/pdfdocument.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/pdfdocument.lua)
- [frontend/document/koptinterface.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/koptinterface.lua)
- [frontend/document/doccache.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/doccache.lua)

## UI Widget System

The UI widget system is built on a composition pattern with inheritance:

**Widget Class Hierarchy**

```
Primitive Widgets

Dialog Widgets

Menu Widgets

Application Containers

Base Widget Classes

extend

extend

extend

extend

extend

extend

extend

extend

extend

extend

extend

extend

extend

Widget
frontend/ui/widget/widget.lua
paintTo()
getSize()

WidgetContainer
frontend/ui/widget/container/
widgetcontainer.lua
self[1..n] = child widgets

InputContainer
frontend/ui/widget/container/
inputcontainer.lua
registerTouchZones()
handleEvent()

FocusManager
frontend/ui/widget/container/
focusmanager.lua
moveFocusTo()
layout = {{widgets}}

FileManager
frontend/apps/filemanager/
filemanager.lua
InputContainer:extend

ReaderUI
frontend/apps/reader/
readerui.lua
InputContainer:extend

Menu
frontend/ui/widget/menu.lua
item_table
updateItems()

TouchMenu
frontend/ui/widget/
touchmenu.lua
tab_item_table

MenuBar
frontend/ui/widget/
menubar.lua

ButtonDialog
frontend/ui/widget/
buttondialog.lua

InputDialog
frontend/ui/widget/
inputdialog.lua
VirtualKeyboard

ConfirmBox
frontend/ui/widget/
confirmbox.lua

Button
frontend/ui/widget/
button.lua

TextWidget
frontend/ui/widget/
textwidget.lua
RenderText:renderUtf8Text()

ImageWidget
frontend/ui/widget/
imagewidget.lua
```

**Widget Lifecycle Events**

Widgets receive lifecycle events as they're shown and closed:
EventTriggered ByPurposeHandler Pattern`Show``UIManager:show(widget)`[frontend/ui/uimanager.lua186](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L186-L186)Widget is being displayed`widget:handleEvent(Event:new("Show"))``CloseWidget``UIManager:close(widget)`[frontend/ui/uimanager.lua225](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L225-L225)Widget is being removedClean up resources, save state`FlushSettings``UIManager:close(widget)`[frontend/ui/uimanager.lua223](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L223-L223)Save widget settings before closePersist configuration`SetDimensions`Screen rotation/resizeUpdate for new screen sizeRecompute layout
**Sources:**

- [frontend/ui/widget/widget.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/widget.lua)
- [frontend/ui/widget/container/widgetcontainer.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/container/widgetcontainer.lua)
- [frontend/ui/widget/container/inputcontainer.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/container/inputcontainer.lua)
- [frontend/ui/widget/container/focusmanager.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/container/focusmanager.lua)
- [frontend/ui/widget/menu.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/menu.lua)
- [frontend/ui/widget/buttondialog.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/buttondialog.lua)

### Key Widget Patterns

**Widget Composition Pattern**

`WidgetContainer` stores child widgets in array indices [frontend/ui/widget/container/widgetcontainer.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/container/widgetcontainer.lua):

```
-- Example widget composition
local my_widget = WidgetContainer:new{
    self[1] = TextWidget:new{text = "Hello"},
    self[2] = Button:new{text = "OK"},
}
```

Children are painted in order (index 1 first, then 2, etc.), creating a bottom-to-top layering effect.

**Event Propagation Pattern**

`InputContainer:handleEvent()` propagates events through the widget tree:

1. **Children First**: Event sent to each child widget in order [frontend/ui/widget/container/inputcontainer.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/container/inputcontainer.lua)
2. **Stop on True**: If any handler returns `true`, propagation stops immediately
3. **Parent Last**: If no child handles it, parent's handler is called
4. **Touch Zone Priority**: Registered touch zones can override normal propagation

**Touch Zone Registration**

`InputContainer` provides `registerTouchZones()`[frontend/ui/widget/container/inputcontainer.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/container/inputcontainer.lua) for gesture handling:

```
self:registerTouchZones({
    {
        id = "my_tap_zone",
        ges = "tap",
        screen_zone = {
            ratio_x = 0.1, ratio_y = 0.1,
            ratio_w = 0.8, ratio_h = 0.8,
        },
        handler = function(ges) return self:onTap(ges) end,
    },
})
```

**Focus Management Pattern**

`FocusManager` arranges focusable widgets in a 2D grid [frontend/ui/widget/container/focusmanager.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/container/focusmanager.lua):

```
self.layout = {
    { widget1, widget2, widget3 },  -- row 1
    { widget4, widget5, widget6 },  -- row 2
}
```

Arrow keys navigate: Up/Down move between rows, Left/Right within rows. Selected widget receives `focused = true` and can be activated.

**show_parent Convention**

Window-level widgets set `show_parent = self` and propagate it to children:

```
MyWidget:new{
    show_parent = self.show_parent or self,
}
```

This enables children to call `UIManager:setDirty(self.show_parent)` to trigger repaints [frontend/ui/uimanager.lua581-671](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L581-L671)

**Sources:**

- [frontend/ui/widget/widget.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/widget.lua)
- [frontend/ui/widget/container/widgetcontainer.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/container/widgetcontainer.lua)
- [frontend/ui/widget/container/inputcontainer.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/container/inputcontainer.lua)
- [frontend/ui/widget/container/focusmanager.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/container/focusmanager.lua)
- [frontend/ui/uimanager.lua581-671](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L581-L671)

## Inter-System Communication

KOReader uses event broadcasting for loose coupling between systems:

**Event Flow - UIManager Event Dispatch**

```
Event Receivers

UIManager Event Dispatch (frontend/ui/uimanager.lua)

Event Sources

gesture events

Power, Suspend

custom events

default handler

specific handlers

Input
frontend/device/input.lua
waitEvent()

System Events
Suspend, Resume
NetworkConnected

Reader Modules
PageUpdate, UpdateFooter

handleInput(input_event)
line 1226

event_handlers table
line 51

sendEvent(event)
line 826
to top widget only

broadcastEvent(event)
line 857
to all widgets

_window_stack widgets
top-to-bottom propagation

active_widgets
always receive broadcasts

onEventName() methods
in widgets/modules
```

**Event Handler Registration**

`UIManager` maintains an `event_handlers` table [frontend/ui/uimanager.lua51-75](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L51-L75) for system-level events:

```
self.event_handlers = {
    __default__ = function(input_event)
        self:sendEvent(input_event)  -- dispatch to top widget
    end,
    SaveState = function()
        self:flushSettings()
    end,
    Power = function(input_event)
        Device:onPowerEvent(input_event)
    end,
    UsbDevicePlugIn = function(input_event)
        local evdev = table.remove(Input.fake_event_args[input_event])
        self:broadcastEvent(Event:new("EvdevInputInsert", path))
    end,
}
```

**Event Types by Scope**
Event CategoryDispatch MethodRecipientsExamples**Input Events**`sendEvent()`[frontend/ui/uimanager.lua826](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L826-L826)Top widget in `_window_stack``Tap`, `Hold`, `Swipe`, `KeyPress`**Lifecycle Events**Automatic during `show()/close()`Affected widgets only`Show`[frontend/ui/uimanager.lua186](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L186-L186)`CloseWidget`[frontend/ui/uimanager.lua225](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L225-L225)`FlushSettings`[frontend/ui/uimanager.lua223](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L223-L223)**System Events**`broadcastEvent()`[frontend/ui/uimanager.lua857](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L857-L857)All widgets in `_window_stack` + `active_widgets``Suspend`, `Resume`, `NetworkConnected`, `SetDimensions`**Application Events**`broadcastEvent()`All widgets`PageUpdate`, `UpdateFooter`, `ColorRenderingUpdate`
**Event Object Structure**

Events are created using `Event:new()`[frontend/ui/event.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/event.lua):

```
local event = Event:new("EventName", arg1, arg2, ...)
-- event.name = "EventName"
-- event.args = {arg1, arg2, ...}
```

Widgets handle events via methods named `onEventName()`:

```
function MyWidget:onPageUpdate(pageno)
    -- Handle page update
    return true  -- stop propagation
end
```

**Sources:**

- [frontend/ui/uimanager.lua51-75](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L51-L75)
- [frontend/ui/uimanager.lua826-868](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L826-L868)
- [frontend/ui/uimanager.lua1226-1277](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L1226-L1277)
- [frontend/ui/event.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/event.lua)

## Summary

KOReader's architecture separates concerns into clear layers:

1. **Platform Abstraction** - `Device` hierarchy provides hardware-independent interfaces
2. **Core Framework** - `UIManager` orchestrates widget lifecycle, event dispatch, and task scheduling
3. **Input Processing** - `Input` and `GestureDetector` transform hardware events into application events
4. **Application Layer** - `FileManager` and `ReaderUI` provide distinct user-facing modes
5. **Document System** - `DocumentRegistry` abstracts rendering engines behind a common interface
6. **UI Widgets** - Composition-based widget tree with inheritance for input handling

This layered design enables cross-platform support (8+ device families), pluggable document formats (EPUB, PDF, DjVu, etc.), and extensibility through plugins and reader modules.

---

# Key-Concepts-and-Terminology

# Key Concepts and Terminology
Relevant source files
- [frontend/device/android/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua)
- [frontend/device/cervantes/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/cervantes/device.lua)
- [frontend/device/devicelistener.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/devicelistener.lua)
- [frontend/device/generic/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua)
- [frontend/device/gesturedetector.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/gesturedetector.lua)
- [frontend/device/input.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/input.lua)
- [frontend/device/kindle/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kindle/device.lua)
- [frontend/device/kobo/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua)
- [frontend/device/pocketbook/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/pocketbook/device.lua)
- [frontend/device/sdl/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/sdl/device.lua)
- [frontend/device/sony-prstux/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/sony-prstux/device.lua)
- [frontend/dispatcher.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/dispatcher.lua)
- [frontend/ui/uimanager.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua)
- [platform/android/llapp_main.lua](https://github.com/koreader/koreader/blob/9e6b1587/platform/android/llapp_main.lua)
- [plugins/gestures.koplugin/main.lua](https://github.com/koreader/koreader/blob/9e6b1587/plugins/gestures.koplugin/main.lua)
- [plugins/hotkeys.koplugin/main.lua](https://github.com/koreader/koreader/blob/9e6b1587/plugins/hotkeys.koplugin/main.lua)
- [plugins/profiles.koplugin/main.lua](https://github.com/koreader/koreader/blob/9e6b1587/plugins/profiles.koplugin/main.lua)
- [reader.lua](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua)
- [spec/unit/device_spec.lua](https://github.com/koreader/koreader/blob/9e6b1587/spec/unit/device_spec.lua)

This page defines the fundamental concepts, abstractions, and terminology used throughout the KOReader codebase. Understanding these concepts is essential for navigating the architecture and implementation details covered in other sections.

For information about the overall architecture and how these concepts fit together, see [Architecture Overview](/koreader/koreader/1.1-architecture-overview). For specific implementation details of the build system, UI framework, or document rendering, see their respective sections.

---

## Widget System Concepts

### Widget Hierarchy

KOReader's UI is built on a widget composition system where all visual elements inherit from a base widget abstraction. The widget tree is managed by `UIManager` and painted bottom-to-top.

```
extends

extends

extends

extends

extends

extends

extends

extends

extends

Widget
(base class)

WidgetContainer
Display-only container

InputContainer
Handles input events

FocusManager
2D keyboard navigation

MenuItem

Button

Menu

TouchMenuItem

FileManager

ReaderUI
```

**Sources:**[frontend/ui/widget/container/inputcontainer.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/container/inputcontainer.lua)[frontend/ui/widget/container/widgetcontainer.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/container/widgetcontainer.lua)[frontend/ui/widget/focusmanager.lua1-30](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/focusmanager.lua#L1-L30)[frontend/ui/widget/menu.lua88-100](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/menu.lua#L88-L100)[frontend/ui/widget/button.lua37-70](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/button.lua#L37-L70)

#### Key Widget Classes
ClassPurposeInput HandlingExample Usage`Widget`Base class for all UI elementsNoneN/A (abstract)`WidgetContainer`Groups child widgets for layoutNoneFrames, groups`InputContainer`Processes gestures and key eventsYesButtons, menu items`FocusManager`Manages 2D focus for keyboard/DPadYesMenus, dialogs
**Sources:**[frontend/ui/widget/container/widgetcontainer.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/container/widgetcontainer.lua)[frontend/ui/widget/container/inputcontainer.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/container/inputcontainer.lua)[frontend/ui/widget/focusmanager.lua1-50](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/focusmanager.lua#L1-L50)

### Window Stack and show_parent

The **window stack** (`UIManager._window_stack`) is an ordered list of top-level widgets from bottom to top. Only widgets in this stack receive events and get painted.

The **`show_parent`** convention cascades a reference to the window-level widget through all child widgets:

```
-- Child widget initialization pattern
ChildWidget:new{
    show_parent = self.show_parent or self
}
```

This enables child widgets to call `UIManager:setDirty(self.show_parent, ...)` to request repaints of their containing window.

**Sources:**[frontend/ui/uimanager.lua33-48](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L33-L48)[frontend/ui/uimanager.lua533-560](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L533-L560)

---

## Event System Concepts

### Event Structure

Events are simple Lua tables created via `Event:new(name, arg_table)`:

```
Event:new("TapSelect", { pos = { x = 100, y = 200 } })
Event:new("SetDimensions", { dimen = geom_object })
```

**Sources:**[frontend/ui/event.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/event.lua)[frontend/ui/uimanager.lua52-75](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L52-L75)

### Event Flow

```
raw events

touch events

gesture events

all events

1.Check

2.Send top-down

propagate children-first

return true = stop

return false = continue

Hardware Input
Touch/Key/Power

Device.input
Input module

GestureDetector
State machine

UIManager
handleInput()

event_handlers
{Power, Suspend, etc}

_window_stack
sendEvent()

Widget:handleEvent()

Stop propagation

Next widget
```

**Sources:**[frontend/ui/uimanager.lua875-925](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L875-L925)[frontend/device/input.lua200-280](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/input.lua#L200-L280)

#### Event Handlers

Widgets can define event handlers as methods named `on<EventName>`:

```
function MyWidget:onTapSelect(arg, ges)
    -- Handle tap gesture
    return true  -- Stop propagation
end
```

Events propagate **children-first** in array order. If any handler returns `true`, propagation stops immediately.

**Sources:**[frontend/ui/widget/menu.lua499-530](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/menu.lua#L499-L530)[frontend/ui/widget/touchmenu.lua171-223](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/touchmenu.lua#L171-L223)

### Common Event Types
EventTriggerHandler PatternUsage`Show`Widget shown`onShow()`Initialize state, request repaint`CloseWidget`Widget closing`onCloseWidget()`Cleanup, free resources`FlushSettings`Pre-close`onFlushSettings()`Save settings`TapSelect`Tap gesture`onTapSelect(arg, ges)`Button press, menu select`HoldSelect`Hold gesture`onHoldSelect(arg, ges)`Show context menu`SetDimensions`Size change`onSetDimensions(dimen)`Update layout
**Sources:**[frontend/ui/uimanager.lua148-186](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L148-L186)[frontend/ui/uimanager.lua203-270](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L203-L270)

---

## Input and Gesture Concepts

### Input Event Flow

```
EV_ABS, EV_KEY

raw events

adjusted

recognized gestures

non-touch events

Device
/dev/input/eventX

Input:waitEvent()

eventAdjustHook
device-specific

GestureDetector

UIManager
```

**Sources:**[frontend/device/input.lua400-600](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/input.lua#L400-L600)[frontend/ui/uimanager.lua875-900](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L875-L900)

### Gesture Types

Gestures are recognized by `GestureDetector` through a state machine that tracks contact points over time:
GestureDetectionParameters`tap`Quick touch and release`pos`, `time``hold`Touch held for timeout`pos`, `time``swipe`Linear motion`pos`, `direction`, `distance``pan`Continuous motion`pos`, `relative``pinch` / `spread`Two-finger zoom`pos`, `direction`, `distance``two_finger_tap`Simultaneous taps`pos``two_finger_swipe`Two-finger linear motion`pos`, `direction`, `distance`
**Sources:**[frontend/device/gesturedetector.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/gesturedetector.lua)[frontend/device/input.lua103-145](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/input.lua#L103-L145)

### Touch Zones

Widgets register **gesture ranges** to receive gesture events within specific screen areas:

```
self.ges_events = {
    TapSelect = {
        GestureRange:new{
            ges = "tap",
            range = self.dimen,  -- Geom object
        },
    },
}
```

**Sources:**[frontend/ui/widget/menu.lua117-131](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/menu.lua#L117-L131)[frontend/ui/gesturerange.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/gesturerange.lua)

---

## Document System Concepts

### Document Providers

Each document format is handled by a **provider** that implements the `Document` interface:

```
extends

extends

extends

extends

uses

uses

uses

optional

optional

Document
(base class)

CreDocument
EPUB, FB2, HTML, TXT

PdfDocument
PDF

DjvuDocument
DjVu

PicDocument
Images

crengine (C++)
lvdocview

MuPDF (C)
fz_document

DjVuLibre (C)
ddjvu_document

KoptInterface
Reflow & OCR
```

**Sources:**[frontend/document/document.lua16-60](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/document.lua#L16-L60)[frontend/document/credocument.lua21-78](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/credocument.lua#L21-L78)[frontend/document/pdfdocument.lua14-46](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/pdfdocument.lua#L14-L46)[frontend/document/djvudocument.lua5-40](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/djvudocument.lua#L5-L40)

#### Provider Selection

`DocumentRegistry` maps file extensions to providers:

```
-- Registration pattern
DocumentRegistry:addProvider("pdf", "application/pdf", PdfDocument, 100)
DocumentRegistry:addProvider("epub", "application/epub+zip", CreDocument, 90)
```

**Sources:**[frontend/document/documentregistry.lua9-80](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/documentregistry.lua#L9-L80)

### Document Positions

Different document types use different position systems:
Document TypePosition TypeDescriptionExamplePDF/DjVu**Page number**Integer page index (1-based)`{ page = 5 }`EPUB/reflowable**XPointer**DOM path to element`{ xpointer = "/body/div[3]/p[2].0" }`Continuous scroll**Y-position**Pixel offset from top`{ pos = 1250 }`
**Sources:**[frontend/document/credocument.lua719-747](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/credocument.lua#L719-L747)[frontend/document/pdfdocument.lua97-130](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/pdfdocument.lua#L97-L130)

### XPointers (CRE Documents)

**XPointers** are string representations of DOM node positions in reflowable documents:

```
"/body/div[2]/p[3].15"
 └──┬──┘ └─┬─┘ └─┬─┘││
    │      │     │  │└─ Character offset within text node
    │      │     │  └─── Text node indicator
    │      │     └────── 3rd paragraph
    │      └──────────── 2nd div
    └─────────────────── body element

```

**Key XPointer operations:**

- `compareXPointers(xp1, xp2)` - Returns -1, 0, 1 for ordering
- `getNextVisibleWordStart(xp)` - Navigate by word
- `getTextFromXPointers(xp1, xp2)` - Extract text range

**Sources:**[frontend/document/credocument.lua719-778](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/credocument.lua#L719-L778)

---

## Display and Rendering Concepts

### Refresh Types (E-Ink Waveform Modes)

KOReader uses different **refresh types** to optimize E-Ink display updates:
Refresh TypeQualitySpeedFlashUse Case`full`HighestSlowestYesImages, initial display`partial`HighMediumAfter N refreshesText reading (ReaderUI)`ui`MediumFastNoUI elements, menus`fast`LowFastestNoHighlighting, inversions`a2`LowestFastestNoKeyboard, animation`flashui`MediumFastYesShow/close UI elements`flashpartial`HighMediumYes (not on REAGL)Close reader overlays
**Refresh region merging:** Multiple `setDirty()` calls are merged into minimal refresh rectangles during `_repaint()`.

**Sources:**[frontend/ui/uimanager.lua441-507](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L441-L507)[frontend/ui/uimanager.lua569-659](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L569-L659)

### setDirty and Repaint Flow

```
1.setDirty()

2.setDirty()

_repaint()

widget:paintTo(bb)

_refresh()

screen:refreshX()

Widget code

UIManager._dirty
Mark widget dirty

UIManager._refresh_stack
Enqueue refresh

UIManager event loop

Paint dirty widgets
bottom-to-top

BlitBuffer
in-memory image

Merge refresh regions
optimize rectangles

Hardware refresh
mxcfb/sunxi ioctl
```

**Sources:**[frontend/ui/uimanager.lua569-682](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L569-L682)[frontend/ui/uimanager.lua1120-1250](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L1120-L1250)

#### setDirty Usage Patterns

```
-- Mark widget for repaint with refresh
UIManager:setDirty(self.show_parent, "ui", self.dimen)
 
-- Use lambda for geometry computed during paint
UIManager:setDirty(self.show_parent, function()
    return "partial", self.dimen
end)
 
-- Just refresh a region (no widget repaint)
UIManager:setDirty(nil, "full", Geom:new{x=0, y=0, w=100, h=100})
```

**Sources:**[frontend/ui/uimanager.lua558-659](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L558-L659)

### BlitBuffer Types

**BlitBuffer** is the pixel buffer abstraction for images and screen rendering:
TypeBits per PixelColorUsage`TYPE_BB8`8GrayscaleDefault for E-Ink`TYPE_BBRGB16`16RGB565Color E-Ink (older)`TYPE_BBRGB24`24RGB888DjVu color rendering`TYPE_BBRGB32`32RGBA8888Screen FB, CRE rendering`TYPE_BB4`416 grayscaleLegacy (Lua only)
**Sources:**[frontend/ffi/blitbuffer.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ffi/blitbuffer.lua)[frontend/document/document.lua38-45](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/document.lua#L38-L45)

---

## Device Abstraction Concepts

### Device Hierarchy

```
extends

extends

extends

extends

extends

extends

extends

extends

Device:Generic
Base implementation

Device:Kobo

Device:Kindle

Device:PocketBook

Device:Remarkable

Device:Cervantes

Device:SDL
Desktop/Emulator

Kobo:Clara

Kobo:Libra
```

**Sources:**[frontend/device/generic/device.lua27-144](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua#L27-L144)[frontend/device/kobo/device.lua93-160](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L93-L160)[frontend/device/kindle/device.lua337-370](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kindle/device.lua#L337-L370)

### Device Capabilities

Devices expose boolean capability functions to enable/disable features:

```
-- Query device capabilities
if Device:hasMultitouch() then
    -- Enable multi-touch gestures
end
 
if Device:hasFrontlight() then
    -- Show frontlight controls
end
 
if Device:canHWInvert() then
    -- Use hardware inversion for night mode
end
```

**Common capabilities:**
MethodMeaning`isTouchDevice()`Has touch screen`hasMultitouch()`Supports multiple simultaneous touches`hasKeys()`Has physical buttons`hasDPad()`Has directional pad`hasFrontlight()`Has adjustable frontlight`hasNaturalLight()`Has color temperature control`canHWInvert()`Supports hardware color inversion`hasEinkScreen()`Is an E-Ink device`hasColorScreen()`Can display color
**Sources:**[frontend/device/generic/device.lua40-143](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua#L40-L143)[frontend/device/kobo/device.lua93-160](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L93-L160)

### Screen and Input Initialization

Each device provides:

1. **Screen object** (`Device.screen`) - Framebuffer interface with refresh methods
2. **Input object** (`Device.input`) - Input device reading and event translation
3. **Power daemon** (`Device.powerd`) - Battery and power management

```
function Device:init()
    self.screen = require("ffi/framebuffer_xyz"):new{...}
    self.input = require("device/input"):new{device = self}
    self.powerd = require("device/xyz/powerd"):new{device = self}
end
```

**Sources:**[frontend/device/generic/device.lua198-241](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua#L198-L241)[frontend/device/kobo/device.lua677-728](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L677-L728)

---

## Action System Concepts

### Dispatcher Actions

The **Dispatcher** maintains a registry of named actions that can be triggered by gestures, hotkeys, or profiles:

```
execute

execute

execute

broadcast

propagate

Gestures Plugin
Touch mappings

HotKeys Plugin
Keyboard mappings

Profiles Plugin
Auto-execute

Dispatcher
Action registry

Event:new(action)

Reader/FM modules
onDispatcher*()
```

**Sources:**[frontend/dispatcher.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/dispatcher.lua)[plugins/gestures.koplugin](https://github.com/koreader/koreader/blob/9e6b1587/plugins/gestures.koplugin)[plugins/hotkeys.koplugin](https://github.com/koreader/koreader/blob/9e6b1587/plugins/hotkeys.koplugin)

### Action Categories
CategoryDescriptionExample`none`No arguments"Close document"`arg`Fixed argument"Go to page 10"`configurable`User-provided arg"Go to page ?"
**Sources:**[frontend/dispatcher.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/dispatcher.lua)

---

## Configuration System Concepts

### Settings Hierarchy

```
fallback

fallback

loads

G_reader_settings
Global persistent settings
File: settings.reader.lua

doc_settings
Per-document settings
File: book.epub.sdr/metadata.lua

G_defaults
Compile-time defaults
File: defaults.lua

Widget configuration
```

**Access patterns:**

```
-- Global setting
local value = G_reader_settings:readSetting("key")
G_reader_settings:saveSetting("key", value)
 
-- Document setting (requires DocSettings instance)
local doc_settings = DocSettings:open(filepath)
local value = doc_settings:readSetting("key")
doc_settings:saveSetting("key", value)
 
-- With defaults
local value = G_reader_settings:readSetting("key") 
           or G_defaults:readSetting("KEY_CONSTANT")
```

**Sources:**[frontend/luasettings.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/luasettings.lua)[frontend/docsettings.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/docsettings.lua)[defaults.lua](https://github.com/koreader/koreader/blob/9e6b1587/defaults.lua)

---

## Geometry Concepts

### Geom Objects

**Geom** represents rectangles for positioning and hit-testing:

```
local rect = Geom:new{x = 10, y = 20, w = 100, h = 50}
 
-- Common operations
rect:contains(point_geom)           -- Hit test
rect:intersectWith(other_geom)      -- Intersection
rect:combine(other_geom)            -- Union
Geom.boundingBox(geom_array)        -- Minimal enclosing box
```

**Sources:**[frontend/ui/geometry.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/geometry.lua)

---

## Module Organization Concepts

### Reader Modules

Reader functionality is split into modules that register with `ReaderUI`:

```
-- Module registration pattern
local ReaderFooter = WidgetContainer:extend{}
 
function ReaderUI:init()
    self:registerModule("footer", ReaderFooter:new{...})
end
```

Modules receive events and can call `UIManager:broadcastEvent()` to notify other modules.

**Sources:**[frontend/apps/reader/readerui.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/readerui.lua)[frontend/apps/reader/modules/](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/)

---

This terminology and these concepts form the foundation for understanding KOReader's architecture. The widget composition model, event propagation system, document provider abstraction, and device capability detection are used consistently throughout the codebase to maintain separation of concerns and platform portability.

---

# Build-System-and-Platform-Support

# Build System and Platform Support
Relevant source files
- [.gitignore](https://github.com/koreader/koreader/blob/9e6b1587/.gitignore)
- [Makefile](https://github.com/koreader/koreader/blob/9e6b1587/Makefile)
- [README.md](https://github.com/koreader/koreader/blob/9e6b1587/README.md?plain=1)
- [frontend/device/android/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua)
- [frontend/device/cervantes/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/cervantes/device.lua)
- [frontend/device/generic/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua)
- [frontend/device/input.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/input.lua)
- [frontend/device/kindle/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kindle/device.lua)
- [frontend/device/kobo/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua)
- [frontend/device/pocketbook/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/pocketbook/device.lua)
- [frontend/device/sdl/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/sdl/device.lua)
- [frontend/device/sony-prstux/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/sony-prstux/device.lua)
- [frontend/ui/uimanager.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua)
- [kodev](https://github.com/koreader/koreader/blob/9e6b1587/kodev)
- [platform/android/llapp_main.lua](https://github.com/koreader/koreader/blob/9e6b1587/platform/android/llapp_main.lua)
- [platform/appimage/AppRun](https://github.com/koreader/koreader/blob/9e6b1587/platform/appimage/AppRun)
- [spec/unit/device_spec.lua](https://github.com/koreader/koreader/blob/9e6b1587/spec/unit/device_spec.lua)

This section covers KOReader's cross-platform build infrastructure and device abstraction layer. The build system compiles and packages KOReader for 15+ target platforms with different CPU architectures (x86, ARM, ARM64), while the platform abstraction layer provides unified interfaces for hardware capabilities across e-readers, Android devices, and desktop systems.

**Key subsystems covered in this section:**

- **[Build System and Development Workflow](/koreader/koreader/2.1-build-system-and-development-workflow)**: The `kodev` CLI tool and `Makefile`-based build orchestration for compiling, testing, and packaging across platforms
- **[Platform Abstraction and Device Support](/koreader/koreader/2.2-platform-abstraction-and-device-support)**: The `Device` class hierarchy that abstracts hardware differences (input, display, power, WiFi) across Kobo, Kindle, Android, and other platforms

**Importance**: Build system components have an aggregate importance score of 1656.66, making this the highest-importance infrastructure in the codebase. It enables KOReader to run on diverse hardware from e-ink e-readers to Android tablets to desktop systems.

Related pages:

- [Application Entry and Lifecycle](/koreader/koreader/3.1-application-entry-and-lifecycle) - What happens after build artifacts are deployed
- [UI Manager and Event Loop](/koreader/koreader/3.2-ui-manager-and-event-loop) - How platform input events are processed
- [Input Handling and Gesture Detection](/koreader/koreader/3.4-input-handling-and-gesture-detection) - Platform-specific input event translation

---

## Build and Platform Architecture Overview

KOReader's architecture separates build-time concerns (compilation, packaging) from runtime concerns (device capabilities, platform APIs). This separation enables a single codebase to support radically different platforms.

```
Application Layer

Runtime (Device Layer)

Platform Targets

Build Time

Source Code
Lua frontend + C libraries

kodev CLI
Developer interface

Makefile System
Build orchestration

koreader-base
C/C++ compilation
(MuPDF, CRE, LuaJIT)

Android APK
arm/arm64/x86

Kobo Package
ARM e-ink

Kindle Package
ARM e-ink

Linux/AppImage
x86_64

PocketBook, reMarkable
Cervantes, etc.

Device:Generic
Base abstraction

Platform Implementations
Kobo, Kindle, Android, SDL

Capability Detection
hasKeys, hasFrontlight, etc.

Platform APIs
Input, Screen, Power, WiFi

reader.lua
Main entry point

UIManager
Event loop

FileManager, ReaderUI
```

**Diagram 1: Build System to Platform Abstraction Flow**

The build system produces platform-specific artifacts (APKs, packages, tarballs), each containing the same Lua application code but with platform-specific C libraries and launcher scripts. At runtime, the `Device` abstraction layer detects the platform and provides unified APIs.

Sources: [Makefile1-240](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L1-L240)[kodev1-783](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L1-L783)[frontend/device/generic/device.lua1-1037](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua#L1-L1037)

---

## Build System Overview

The build system consists of two layers:

1. **Developer Interface** (`kodev`): Validates targets, manages dependencies, and invokes Make with appropriate parameters
2. **Build Orchestration** (`Makefile`): Compiles C libraries, processes translations, and assembles installation directories

### Supported Platform Targets

KOReader supports 15+ build targets across multiple CPU architectures:
Target FamilySpecific TargetsCPU Architectures**Android**`android-arm`, `android-arm64`, `android-x86`, `android-x86_64`ARMv7, ARM64, x86, x86_64**E-ink Readers**`kobo`, `kindle`, `kindlepw2`, `cervantes`, `pocketbook`, `remarkable`ARM, ARM64**Desktop/Emulator**`emulator`, `linux`, `appimage`, `macos`x86_64, ARM64 (Apple Silicon)
**Target selection** is handled by `setup_target()` in `kodev`[kodev158-209](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L158-L209) which:

- Validates the target is supported for the current command (build, release, etc.)
- Checks host OS requirements (e.g., `macos` target requires macOS host)
- Maps Android architecture variants to `TARGET=android` with `ANDROID_ARCH` set
- Automatically fetches submodules and installs Android NDK/SDK if needed

### Build Process Stages

```
fetchthirdparty
Git submodules

base-all
Compile C libs

mo
Translations

Installation
Copy to INSTALL_DIR

Package
Create release archives
```

**Diagram 2: Build Process Stages**

1. **Dependency fetching**[Makefile181-194](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L181-L194): Clones third-party libraries as git submodules
2. **Base compilation**[Makefile174](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L174-L174): Builds C/C++ libraries (MuPDF, CREngine, LuaJIT) via `koreader-base`
3. **Translation compilation**: Converts `.po` gettext files to `.mo` binaries
4. **Installation**[Makefile140-172](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L140-L172): Assembles the directory structure with Lua code, resources, and compiled binaries
5. **Packaging**: Creates release archives (`.tar.gz`, `.zip`, `.apk`) with platform-specific exclusions [Makefile73-127](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L73-L127)

**Installation directory structure**[Makefile140-172](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L140-L172):

```
koreader-TARGET-MACHINE/koreader/
├── reader.lua              # Main entry point
├── setupkoenv.lua          # Lua path setup
├── frontend/               # Application code
├── plugins/                # Plugin modules
├── l10n/                   # Translations (.mo files)
├── resources/              # UI assets, fonts
├── data/                   # Dictionary data
├── libs/                   # Compiled C libraries
└── git-rev                 # Version identifier

```

For detailed build commands, configuration options, and development workflow, see [Build System and Development Workflow](/koreader/koreader/2.1-build-system-and-development-workflow).

Sources: [Makefile1-240](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L1-L240)[kodev80-96](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L80-L96)[kodev158-209](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L158-L209)

---

## Platform Abstraction Overview

The platform abstraction layer provides a single API for hardware and OS capabilities across diverse platforms. This is implemented through the `Device` class hierarchy in `frontend/device/`.

### Device Class Hierarchy

```
Device:Generic
frontend/device/generic/device.lua
Default implementations

Device:SDL
Emulator/Desktop

Device:Android
frontend/device/android/device.lua

Device:Kobo
frontend/device/kobo/device.lua

Device:Kindle
frontend/device/kindle/device.lua

Device:PocketBook

Device:Cervantes

Device:reMarkable

Kobo_nova
(Clara HD)

Kobo_storm
(Libra H2O)

Kobo_cadmus
(Sage)

Kindle4
Kindle Touch

KindlePW
Paperwhite

KindleOasis
Oasis 2/3
```

**Diagram 3: Device Class Hierarchy**

Each platform extends `Device:Generic`[frontend/device/generic/device.lua27-144](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua#L27-L144) and overrides:

- **Capability flags**: `hasKeys()`, `hasFrontlight()`, `isTouchDevice()`, etc.
- **Platform APIs**: `suspend()`, `resume()`, `initNetworkManager()`, etc.
- **Hardware specifics**: Screen resolution, input device paths, battery sysfs paths

Sources: [frontend/device/generic/device.lua27-144](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua#L27-L144)[frontend/device/kobo/device.lua93-160](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L93-L160)[frontend/device/android/device.lua75-120](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua#L75-L120)

### Capability Detection System

Capability flags control feature availability at runtime. These are boolean functions (returning `true`/`false`) that can be queried by application code.

**Hardware capabilities**[frontend/device/generic/device.lua40-78](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua#L40-L78):

- `hasKeys()`, `hasDPad()`, `hasKeyboard()`: Physical buttons/keyboard presence
- `isTouchDevice()`, `hasMultitouch()`: Touch input support
- `hasEinkScreen()`, `hasColorScreen()`: Display type
- `hasFrontlight()`, `hasNaturalLight()`: Frontlight and color temperature control
- `hasGSensor()`: Accelerometer/gyroscope for auto-rotation

**Software/network capabilities**[frontend/device/generic/device.lua82-143](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua#L82-L143):

- `hasClipboard()`: System clipboard integration
- `hasWifiToggle()`, `hasSeamlessWifiToggle()`: WiFi control
- `canOpenLink()`, `canShareText()`, `canExternalDictLookup()`: External app integration
- `hasOTAUpdates()`: Over-the-air update support

**Example: Android device capabilities**[frontend/device/android/device.lua75-120](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua#L75-L120):

```
local Device = Generic:extend{
    isAndroid = yes,
    hasKeys = yes,
    hasEinkScreen = function() return android.isEink() end,
    hasColorScreen = android.isColorScreen() and yes or no,
    hasFrontlight = android.hasLights,
    hasNaturalLight = android.isWarmthDevice,
    hasClipboard = yes,
    canOpenLink = yes,
    canShareText = yes,
    canExternalDictLookup = yes,
}
```

The Android implementation calls native Java methods (`android.isEink()`, `android.isColorScreen()`) to detect hardware capabilities dynamically.

For detailed device implementations, input event handling, and power management, see [Platform Abstraction and Device Support](/koreader/koreader/2.2-platform-abstraction-and-device-support).

Sources: [frontend/device/generic/device.lua27-144](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua#L27-L144)[frontend/device/android/device.lua75-120](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua#L75-L120)[frontend/device/kobo/device.lua93-160](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L93-L160)

---

## Build-to-Device Pipeline

This diagram shows how build artifacts become running applications on each platform:

```
Application Launch

Device Detection

Runtime Entry

Deployment

Platform Packaging

Build Output

koreader-TARGET-MACHINE/
Installation directory

Android: APK
Build via make/android.mk

Kobo: .tar.gz
Direct extraction

Kindle: .tar.gz
With launcher script

Linux: AppImage
Self-contained bundle

Install APK
via package manager

Extract to .kobo/
Launch from Nickel

Extract to USB
KUAL launcher

Run AppImage
Direct execution

llapp_main.lua
Android activity

koreader.sh
Shell launcher

koreader.sh
Shell launcher

AppRun
Shell wrapper

Device:init()
Detect hardware

Initialize Screen
FB backend

Initialize Input
Event devices

Initialize PowerD
Battery/charging

reader.lua
Main entry point

UIManager:init()
Event loop
```

**Diagram 4: Build to Runtime Pipeline**

**Platform-specific entry points**:

- **Android**[platform/android/llapp_main.lua1-30](https://github.com/koreader/koreader/blob/9e6b1587/platform/android/llapp_main.lua#L1-L30): Lua entry from Java activity, gets file from Intent
- **Kobo/Kindle**: Shell script launcher calls `reader.lua` with arguments
- **AppImage**[platform/appimage/AppRun1-26](https://github.com/koreader/koreader/blob/9e6b1587/platform/appimage/AppRun#L1-L26): Bash wrapper handles restart loops (exit code 85)

**Device initialization**[frontend/device/generic/device.lua198-328](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua#L198-L328):

1. Detect platform (via `os.getenv("PRODUCT")`, file existence checks, etc.)
2. Instantiate appropriate `Device` subclass
3. Initialize `screen` (framebuffer backend)
4. Initialize `input` (open event devices)
5. Initialize `powerd` (battery monitoring)
6. Apply user settings (DPI override, rotation, night mode)

**Application launch**[reader.lua1-580](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L1-L580):

1. `setupkoenv.lua` sets up Lua paths
2. `Device:init()` completes hardware setup
3. `UIManager:init()` starts event loop
4. Launch `FileManager` or `ReaderUI` based on arguments

Sources: [platform/android/llapp_main.lua1-30](https://github.com/koreader/koreader/blob/9e6b1587/platform/android/llapp_main.lua#L1-L30)[platform/appimage/AppRun1-26](https://github.com/koreader/koreader/blob/9e6b1587/platform/appimage/AppRun#L1-L26)[frontend/device/generic/device.lua198-328](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua#L198-L328)

---

## Summary

KOReader's build system and platform abstraction work together to enable a single codebase to run on 15+ platforms:

**Build System** (detailed in [Build System and Development Workflow](/koreader/koreader/2.1-build-system-and-development-workflow)):

- `kodev` CLI: Developer-friendly interface for building, testing, and packaging
- `Makefile`: Orchestrates C library compilation, translation processing, and artifact assembly
- Platform targets: Android (4 architectures), Kobo, Kindle, PocketBook, and desktop systems
- Release packaging: Creates optimized archives with platform-specific exclusions

**Platform Abstraction** (detailed in [Platform Abstraction and Device Support](/koreader/koreader/2.2-platform-abstraction-and-device-support)):

- `Device` class hierarchy: Generic base class with platform-specific subclasses
- Capability detection: Boolean flags enable/disable features based on hardware
- Hardware APIs: Unified interfaces for screen, input, power, and network
- Platform entry points: Native launchers bootstrap the Lua runtime

**Key integration points**:

1. Build system produces `INSTALL_DIR` with platform-specific C libraries
2. Platform launchers (`.sh` scripts, Android activity) initialize the environment
3. `Device:init()` detects hardware and loads appropriate backends
4. Application code queries capability flags to adapt behavior

Sources: [Makefile1-240](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L1-L240)[kodev1-783](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L1-L783)[frontend/device/generic/device.lua1-1037](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua#L1-L1037)[reader.lua1-580](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L1-L580)

## Build Orchestration

KOReader's build system consists of two primary components: the `kodev` developer-facing script and the underlying `Makefile` infrastructure.

### The kodev Script

The `kodev` script [kodev1-783](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L1-L783) provides a user-friendly interface to the build system. It normalizes command-line arguments, validates targets, and invokes `make` with appropriate environment variables.

**Key Functions:**

- **Target validation and setup**: [kodev158-209](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L158-L209)
- **Submodule management**: [kodev109-116](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L109-L116)
- **Build command construction**: [kodev80-96](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L80-L96)

```
TARGET=android
ANDROID_ARCH=arm64

Developer Command
'./kodev build android-arm64'

kodev Script

parse_options()
Parse and validate arguments

setup_target()
Validate target and set env vars

check_submodules()
Ensure dependencies fetched

run_make()
Invoke make with parameters

Makefile
```

**Diagram 1: kodev Command Flow**

Sources: [kodev158-209](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L158-L209)[kodev80-96](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L80-L96)

### Makefile Structure

The main `Makefile`[Makefile1-240](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L1-L240) orchestrates the entire build process, including:

1. **Dependency inclusion**: Includes `koreader-base/Makefile.defs`[Makefile7](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L7-L7) for C library compilation settings
2. **Target-specific rules**: Conditionally includes `make/TARGET.mk`[Makefile211-213](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L211-L213) for platform-specific build steps
3. **Version management**: Generates version strings from git metadata [Makefile9-15](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L9-L15)
4. **Installation**: Copies Lua frontend, resources, and compiled artifacts to the installation directory [Makefile140-172](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L140-L172)

**Key Variables:**
VariableDescriptionExample`TARGET`Platform identifier`android`, `kobo`, `kindle`, `emulator``ANDROID_ARCH`Android CPU architecture`arm`, `arm64`, `x86`, `x86_64``KODEBUG`Enable debug symbols`1` for debug builds`INSTALL_DIR`Output directory`koreader-$(DIST)-$(MACHINE)``KOR_BASE`Path to koreader-baseDefault: `base`
Sources: [Makefile1-240](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L1-L240)

### Build Process Flow

```
Output

Installation Phase

Compilation Phase

Make Orchestration

Developer Interface

kodev build TARGET

Makefile
(main build rules)

base/Makefile.defs
(C library settings)

make/TARGET.mk
(platform-specific rules)

base-all
(compile C libraries:
mupdf, cre, luajit, etc)

mo
(compile translations)

Create INSTALL_DIR

Copy Lua files:
reader.lua, frontend/, plugins/

Copy resources:
fonts, i18n, data/

Copy compiled libraries
from base/

Create symlinks

Build Artifacts:
koreader-TARGET-MACHINE/
```

**Diagram 2: Build Process Stages**

Sources: [Makefile140-172](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L140-L172)[Makefile174](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L174-L174)[kodev339-350](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L339-L350)

---

## Platform Targets

KOReader supports multiple target platforms through the `TARGET` variable. The `kodev` script validates targets and sets up platform-specific environment variables.

### Supported Targets
TargetDescriptionCPU ArchitecturesNotes`emulator`Desktop emulator (default)native x86_64SDL-based, default debug build`android-arm`Android devicesARMv732-bit ARM`android-arm64`Android devicesARMv8/AArch6464-bit ARM`android-x86`Android emulatorsx8632-bit Intel`android-x86_64`Android emulatorsx86_6464-bit Intel`kindle`Amazon KindleARMCompatible with Kindle 4+`kindlehf`Kindle (optimized)ARMFor FW >= 5.16.3`kindlepw2`Kindle Paperwhite 2+ARMWith optimizations`kindle-legacy`Old Kindle devicesARMKindle 2/3/DXG`kobo`Kobo e-readersARMAll Kobo devices`kobov4`Kobo (FW 4.x)ARMMk.7+ devices`kobov5`Kobo (FW 5.x)ARMAll Kobo & Tolino on FW 5.x`cervantes`BQ CervantesARM`pocketbook`PocketBookARM`remarkable`reMarkable tabletARM`remarkable-aarch64`reMarkable 2ARM64`sony-prstux`Sony e-readersARM`ubuntu-touch`Ubuntu TouchARM`appimage`Linux AppImagex86_64Self-contained package`linux`Native Linuxnative, arm, arm64`macos`macOS app bundlex86_64/arm64`win32`Windowsx86Requires Cygwin/MSYS
Sources: [kodev129-154](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L129-L154)[kodev162-189](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L162-L189)[README.md20-54](https://github.com/koreader/koreader/blob/9e6b1587/README.md?plain=1#L20-L54)

### Target Selection Logic

The `setup_target()` function [kodev158-209](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L158-L209) performs:

1. **Platform validation**: Checks if the target is valid for the current command
2. **System requirements**: Verifies the host OS supports building the target (e.g., macOS builds require macOS host)
3. **Architecture mapping**: For Android, maps `android-ARCH` to `TARGET=android` with `ANDROID_ARCH=ARCH`
4. **Dependency fetching**: Automatically calls `fetchthirdparty` if submodules are missing
5. **Toolchain installation**: For Android, automatically installs NDK/SDK if not configured

```
No

Yes

Fail

Pass

Yes

No

Yes

No

setup_target(TARGET)

Valid target
for command?

Host OS
requirement?

TARGET starts
with 'android-'?

Split into:
TARGET='android'
ANDROID_ARCH=suffix

check_submodules()

Android and
missing NDK/SDK?

Install android-ndk
and android-sdk

Target configured

Error: unsupported target
```

**Diagram 3: Target Setup and Validation**

Sources: [kodev158-209](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L158-L209)[kodev202-208](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L202-L208)

### Platform-Specific Files

Platform-specific build rules and device implementations:
PlatformBuild RulesDevice ImplementationEntry PointEmulator`make/emulator.mk``frontend/device/sdl/device.lua`N/A (direct execution)Android`make/android.mk``frontend/device/android/device.lua``platform/android/llapp_main.lua`Kindle`make/kindle.mk``frontend/device/kindle/device.lua`Platform launcher scriptKobo`make/kobo.mk``frontend/device/kobo/device.lua`Platform launcher scriptCervantes`make/cervantes.mk``frontend/device/cervantes/device.lua`Platform launcher script
Sources: [Makefile211-213](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L211-L213)[frontend/device/android/device.lua1-605](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua#L1-L605)

---

## Build Artifacts and Installation

The `all` target [Makefile140-172](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L140-L172) creates the installation directory structure and populates it with necessary files.

### Installation Directory Structure

```
koreader-TARGET-MACHINE/
└── koreader/
    ├── reader.lua              # Main entry point
    ├── setupkoenv.lua          # Environment setup
    ├── frontend/               # Lua frontend code
    ├── plugins/                # Plugin modules
    ├── resources/              # Icons, UI resources
    ├── fonts/                  # Bundled and host fonts
    ├── data/                   # Dictionary data, CSS, etc.
    ├── l10n/                   # Translation files (.mo)
    ├── ota/                    # OTA update metadata
    ├── screenshots/            # Screenshot storage (created)
    ├── libs/                   # Compiled C libraries (from base)
    ├── luajit                  # LuaJIT executable
    └── git-rev                 # Version identifier

```

Sources: [Makefile140-172](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L140-L172)

### Files Copied During Installation

**Core Files**[Makefile67-68](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L67-L68):

- `reader.lua`, `setupkoenv.lua`, `frontend/`, `resources/`, `defaults.lua`, `datastorage.lua`, `l10n/`, `tools/`, `README.md`, `COPYING`

**Resources**:

- Fonts: Symlinked from `resources/fonts/`[Makefile166](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L166-L166)
- Data files: From `koreader-base` and `cr3.css`[Makefile60-64](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L60-L64)

**Platform-Specific**:

- Android: Additional `.lua` files from `platform/android/`[Makefile154-156](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L154-L156)
- Win32: Runtime DLLs [Makefile159-162](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L159-L162)
- Emulator: Test specs [Makefile148-152](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L148-L152)

Sources: [Makefile140-172](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L140-L172)

### Excluded Files in Release Builds

The `UPDATE_PATH_EXCLUDES` and `UPDATE_GLOBAL_EXCLUDES`[Makefile75-124](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L75-L124) define patterns for files excluded from release packages:

**Development artifacts**: `cache/`, `spec/`, `ev_replay.py`, `l10n/templates/`, `*.po` files

**Runtime-created files**: `settings.reader.lua`, `history.lua`, `data/cr3.ini`

**Binary debug symbols**: `*.dbg`, `*.dSYM`

**Temporary files**: `*.swp`, `*.swo`, `.*` (hidden files)

Sources: [Makefile73-127](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L73-L127)

---

## Device Abstraction Layer

KOReader's device abstraction provides a unified interface across platforms while allowing platform-specific customization.

### Device Class Hierarchy

```
Device:Generic
(frontend/device/generic/device.lua)
Base class with default implementations

Device:SDL
(Emulator)
Desktop simulation

Device:Android
(frontend/device/android/device.lua)
Android implementation

Device:Kobo
E-ink reader

Device:Kindle
Amazon devices

Device:Cervantes
BQ devices

Device:PocketBook
PocketBook devices

Device:reMarkable
reMarkable tablet

Kobo
FW 4.x variant

Kobo
FW 5.x variant

Kindle
High-res variant

Kindle PW2+
Optimized

Kindle Legacy
Old models
```

**Diagram 4: Device Class Hierarchy**

Sources: [frontend/device/android/device.lua75-120](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua#L75-L120)

### Device Capability Detection

The `Device` class defines boolean capability flags that control feature availability:

```
Network

Software

Hardware

Device Capabilities

Hardware Features

Software Features

Network Features

hasEinkScreen

hasColorScreen

hasFrontlight

hasNaturalLight

hasKeys

hasDPad

isTouchDevice

hasKeyboard

hasClipboard

hasExternalSD

hasSystemFonts

canOpenLink

canShareText

canExternalDictLookup

hasSeamlessWifiToggle

hasFastWifiStatusQuery

hasOTAUpdates
```

**Diagram 5: Device Capability Flags**

Sources: [frontend/device/android/device.lua75-120](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua#L75-L120)

### Android Device Implementation

The Android device implementation [frontend/device/android/device.lua75-120](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua#L75-L120) demonstrates platform-specific capability definitions:

**Hardware Detection**:

- `hasEinkScreen`: Uses `android.isEink()`[frontend/device/android/device.lua82](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua#L82-L82)
- `hasColorScreen`: `android.isColorScreen`[frontend/device/android/device.lua83](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua#L83-L83)
- `hasFrontlight`: `android.hasLights`[frontend/device/android/device.lua84](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua#L84-L84)
- `hasNaturalLight`: `android.isWarmthDevice`[frontend/device/android/device.lua85](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua#L85-L85)

**Software Features**:

- `hasClipboard = yes`[frontend/device/android/device.lua93](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua#L93-L93)
- `canOpenLink = yes`[frontend/device/android/device.lua98](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua#L98-L98)
- `canShareText = yes`[frontend/device/android/device.lua106](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua#L106-L106)
- `canExternalDictLookup = yes`[frontend/device/android/device.lua111](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua#L111-L111)

**Network**:

- `hasSeamlessWifiToggle = no` (requires system settings UI) [frontend/device/android/device.lua80](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua#L80-L80)
- `hasFastWifiStatusQuery = yes`[frontend/device/android/device.lua96](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua#L96-L96)

**OTA Model Detection**:
The `otaModel()` method [frontend/device/android/device.lua122-136](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua#L122-L136) returns the appropriate update package identifier based on CPU architecture:

```
function Device:otaModel()
    local arch = jit.arch
    if arch == "arm64" then
        model = "android-arm64"
    elseif arch == "x86" then
        model = "android-x86"
    elseif arch == "x64" then
        model = "android-x86_64"
    else
        model = "android"  -- Default ARMv7
    end
    return model, "link"  -- Type: direct APK download
end
```

Sources: [frontend/device/android/device.lua75-136](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua#L75-L136)

### Platform Entry Points

Each platform has its own entry point that sets up the environment and launches `reader.lua`:

**Android**: [platform/android/llapp_main.lua1-30](https://github.com/koreader/koreader/blob/9e6b1587/platform/android/llapp_main.lua#L1-L30)

```
local android = require("android")
require("setupkoenv")  -- Setup Lua paths
local file = android.getIntent()  -- Get file from intent
if android.isDebuggable() then
    arg = {"-d", file}
else
    arg = {file}
end
dofile(android.dir.."/reader.lua")
```

**AppImage**: [platform/appimage/AppRun1-26](https://github.com/koreader/koreader/blob/9e6b1587/platform/appimage/AppRun#L1-L26)

```
KOREADER_DIR="${0%/*}"
cd "${KOREADER_DIR}" || exit
RETURN_VALUE=85
while [ ${RETURN_VALUE} -eq 85 ]; do
    ./reader.lua "${ARGS}"
    RETURN_VALUE=$?
done
```

Sources: [platform/android/llapp_main.lua1-30](https://github.com/koreader/koreader/blob/9e6b1587/platform/android/llapp_main.lua#L1-L30)[platform/appimage/AppRun1-26](https://github.com/koreader/koreader/blob/9e6b1587/platform/appimage/AppRun#L1-L26)

---

## OTA Update System

KOReader implements over-the-air (OTA) updates using zsync for efficient delta updates on most platforms. Android uses direct APK downloads.

### Update Architecture

```
Installation

Update Methods

Version Comparison

Update Manager

User Interface

OTA newer

OTA newer (Android)

Update Menu
(OTAManager:getOTAMenuTable)

Check for Updates

Update Settings
(server, channel)

OTAManager
(frontend/ui/otamanager.lua)

checkUpdate()
Query server for version

fetchAndProcessUpdate()
Coordinate update flow

Local Version
Version:getCurrentRevision()

OTA Version
Parse from zsync/link file

Compare versions

zsync()
Delta update (non-Android)

Direct APK download
(Android)

_buildLocalPackage()
Create tar from current install

zsync2 / spinning_zsync
Compute delta and download

Device:install()
Apply update and restart
```

**Diagram 6: OTA Update Flow**

Sources: [frontend/ui/otamanager.lua1-460](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/otamanager.lua#L1-L460)

### OTA Configuration

**Update Servers**[frontend/ui/otamanager.lua25-35](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/otamanager.lua#L25-L35):

- Primary: `http://ota.koreader.rocks/`
- Mirrors: `koreader-fr.ak-team.com`, `koreader-pl.ak-team.com`, `koreader-na.ak-team.com`, `koreader.ak-team.com`

**Update Channels**[frontend/ui/otamanager.lua36-39](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/otamanager.lua#L36-L39):

- `stable`: Stable releases
- `nightly`: Development/nightly builds

**Settings**:

- Current server: `G_reader_settings:readSetting("ota_server")`
- Current channel: `G_reader_settings:readSetting("ota_channel")`

Sources: [frontend/ui/otamanager.lua23-75](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/otamanager.lua#L23-L75)

### Update Type Detection

The `getOTAType()` method [frontend/ui/otamanager.lua53-57](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/otamanager.lua#L53-L57) calls `Device:otaModel()` to determine the update mechanism:
Update TypePlatformsMethodFilename Template`"ota"`Kobo, Kindle, Cervantes, PocketBook, reMarkablezsync delta`koreader-MODEL-latest-CHANNEL.zsync``"link"`Android, Desktop (SDL)Direct download`koreader-MODEL-latest-CHANNEL``"none"`Unsupported/deprecatedN/AN/A
Sources: [frontend/ui/otamanager.lua53-57](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/otamanager.lua#L53-L57)[frontend/ui/otamanager.lua77-86](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/otamanager.lua#L77-L86)

### Version Checking

The `checkUpdate()` method [frontend/ui/otamanager.lua92-156](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/otamanager.lua#L92-L156) performs the following steps:

1. **Download update metadata**: Fetches `.zsync` file or link file from OTA server [frontend/ui/otamanager.lua105-119](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/otamanager.lua#L105-L119)
2. **Parse OTA version**:

- For `link` type: First line of file contains package filename [frontend/ui/otamanager.lua124-132](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/otamanager.lua#L124-L132)
- For `ota` type: Parse `Filename:` line from zsync metadata [frontend/ui/otamanager.lua134-138](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/otamanager.lua#L134-L138)
3. **Get local version**: Uses `Version:getCurrentRevision()` and normalizes it [frontend/ui/otamanager.lua141-144](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/otamanager.lua#L141-L144)
4. **Compare versions**: Returns OTA version if newer than local [frontend/ui/otamanager.lua150-155](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/otamanager.lua#L150-L155)

**Return values**:

- `ota_version, local_version, link, ota_package`: Update available
- `0`: Already up to date
- `-1`: Device deprecated/unsupported
- `-2`: Unable to determine OTA model
- `nil`: Network error or server unreachable

Sources: [frontend/ui/otamanager.lua92-156](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/otamanager.lua#L92-L156)

### zsync Delta Updates

For platforms supporting zsync (Kobo, Kindle, etc.), KOReader uses efficient delta updates:

#### Building Local Package

The `_buildLocalPackage()` method [frontend/ui/otamanager.lua296-345](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/otamanager.lua#L296-L345) creates a tarball of the current installation:

1. **Check for existing package**: Return if `koreader.installed.tar` already exists [frontend/ui/otamanager.lua299-301](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/otamanager.lua#L299-L301)
2. **Read package index**: Uses `ota/package.index` file listing all installed files [frontend/ui/otamanager.lua302-305](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/otamanager.lua#L302-L305)
3. **Create tarball**: Runs `tar` with specific options to create reproducible archive [frontend/ui/otamanager.lua307-314](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/otamanager.lua#L307-L314)
4. **Visual feedback**: If fbink available, shows progress with FBInk [frontend/ui/otamanager.lua317-343](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/otamanager.lua#L317-L343)

Sources: [frontend/ui/otamanager.lua296-345](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/otamanager.lua#L296-L345)

#### Running zsync

The `zsync()` method [frontend/ui/otamanager.lua347-385](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/otamanager.lua#L347-L385) orchestrates the delta update:

1. **Build local package**: Calls `_buildLocalPackage()` unless doing full download [frontend/ui/otamanager.lua348](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/otamanager.lua#L348-L348)
2. **Select wrapper**: Uses `spinning_zsync` for visual feedback or `zsync2` directly [frontend/ui/otamanager.lua349-360](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/otamanager.lua#L349-L360)
3. **Execute zsync**:

- Delta mode: `zsync2 -i installed.tar -o updated.tar -u SERVER zsync_file`[frontend/ui/otamanager.lua373-383](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/otamanager.lua#L373-L383)
- Full download mode: `zsync2 -o updated.tar -u SERVER zsync_file`[frontend/ui/otamanager.lua363-371](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/otamanager.lua#L363-L371)

Sources: [frontend/ui/otamanager.lua347-385](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/otamanager.lua#L347-L385)

#### Spinning zsync Wrapper

The `spinning_zsync` script [platform/common/spinning_zsync1-55](https://github.com/koreader/koreader/blob/9e6b1587/platform/common/spinning_zsync#L1-L55) provides visual feedback during zsync:

1. **Clear screen**: Uses FBInk to clear display [platform/common/spinning_zsync23](https://github.com/koreader/koreader/blob/9e6b1587/platform/common/spinning_zsync#L23-L23)
2. **Show status**: Displays "Downloading" or "Computing delta" message [platform/common/spinning_zsync17-20](https://github.com/koreader/koreader/blob/9e6b1587/platform/common/spinning_zsync#L17-L20)
3. **Pipe output**: Forwards zsync2 output to FBInk for live progress display [platform/common/spinning_zsync36](https://github.com/koreader/koreader/blob/9e6b1587/platform/common/spinning_zsync#L36-L36)
4. **Handle pipefail**: Works around old busybox versions without pipefail support [platform/common/spinning_zsync4-13](https://github.com/koreader/koreader/blob/9e6b1587/platform/common/spinning_zsync#L4-L13)[platform/common/spinning_zsync34-51](https://github.com/koreader/koreader/blob/9e6b1587/platform/common/spinning_zsync#L34-L51)

Platform-specific wrappers:

- Cervantes: Uses bash wrapper to enable pipefail [platform/cervantes/spinning_zsync1-8](https://github.com/koreader/koreader/blob/9e6b1587/platform/cervantes/spinning_zsync#L1-L8)

Sources: [platform/common/spinning_zsync1-55](https://github.com/koreader/koreader/blob/9e6b1587/platform/common/spinning_zsync#L1-L55)[platform/cervantes/spinning_zsync1-8](https://github.com/koreader/koreader/blob/9e6b1587/platform/cervantes/spinning_zsync#L1-L8)

### Android Direct Download

For Android, updates bypass zsync and use the platform's download manager:

The `Device:download()` method [frontend/device/android/device.lua556-575](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua#L556-L575) in the Android implementation:

1. **Check if already downloaded**: If APK exists, proceed to install [frontend/device/android/device.lua560-561](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua#L560-L561)
2. **Initiate download**: Uses `android.download(link, name)`[frontend/device/android/device.lua559](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua#L559-L559)
3. **Show feedback**: Displays InfoMessage while downloading [frontend/device/android/device.lua562-567](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua#L562-L567)
4. **Fallback to browser**: If download fails, offers to open link in browser [frontend/device/android/device.lua568-574](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua#L568-L574)

The download completes asynchronously, and the app handles the completion event [frontend/device/android/device.lua226-232](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua#L226-L232):

```
elseif ev.code == C.AEVENT_DOWNLOAD_COMPLETE then
    android.ota.isRunning = false
    if android.isResumed() then
        self:install()
    else
        android.ota.isPending = true
    end
end
```

Sources: [frontend/device/android/device.lua556-575](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua#L556-L575)[frontend/device/android/device.lua226-232](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua#L226-L232)

### Installation

After the update package is ready, the installation process varies by platform:

**Android**: [frontend/device/android/device.lua577-590](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua#L577-L590)

```
function Device:install()
    UIManager:show(ConfirmBox:new{
        text = _("Update is ready. Install it now?"),
        ok_callback = function()
            UIManager:broadcastEvent(Event:new("FlushSettings"))
            UIManager:tickAfterNext(function()
                android.ota.install()
                android.ota.isPending = false
            end)
        end,
    })
end
```

**Non-Android**: The install process is typically handled by platform-specific scripts that:

1. Extract the `koreader.updated.tar` archive
2. Replace the old installation
3. Restart the application

Sources: [frontend/device/android/device.lua577-590](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua#L577-L590)

### Release Package Creation

The build system creates release packages with the `update` target and `mkrelease.sh` script:

**Makefile integration**[Makefile130-138](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L130-L138):

```
define mkupdate
cd $(INSTALL_DIR) &&
'$(abspath tools/mkrelease.sh)'
--epoch="$$(git log -1 --format='%cs' "$$(git describe --tags | cut -d- -f1)")"
$(if $(PARALLEL_JOBS),--jobs $(PARALLEL_JOBS))
--manifest=$(or $2,koreader)/ota/package.index
$(foreach a,$1,'$(if $(filter --%,$a),$a,$(abspath $a))') $(or $2,koreader)
$(call release_excludes,$(or $2,koreader)/)
endef
```

This creates:

- Release tarball (`.tar` or `.zip`)
- zsync metadata file (`.zsync`)
- Package index (`ota/package.index`) listing all files

Sources: [Makefile130-138](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L130-L138)[Makefile73-127](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L73-L127)

---

## Development Workflow

### Common Build Commands

**Build for emulator (debug)**:

```
./kodev build
# or explicitly:
./kodev build emulator
```

**Build for specific platform**:

```
./kodev build kobo
./kodev build android-arm64
./kodev build kindle
```

**Run in emulator**:

```
./kodev run
# With custom screen dimensions:
./kodev run -W 1072 -H 1448
# Simulate specific device:
./kodev run -s kobo-clara
```

**Clean build artifacts**:

```
./kodev clean
./kodev clean kobo  # Clean specific target
```

**Create release package**:

```
./kodev release kobo
./kodev release android-arm64
```

Sources: [kodev339-395](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L339-L395)[kodev416-613](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L416-L613)

### Build Flags

**Debug vs Release**:

- `-d, --debug`: Enable debugging symbols (default for emulator) [kodev259-261](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L259-L261)
- `-n, --no-debug`: Disable debugging symbols (default for device targets) [kodev262-264](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L262-L264)

**Build control**:

- `-b, --no-build`: Skip build, only do setup/packaging [kodev256-258](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L256-L258)
- `-v, --verbose`: Make build system verbose [kodev265-268](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L265-L268)

Sources: [kodev215-231](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L215-L231)

### Testing

**Run test suite**:

```
./kodev test          # Run all tests
./kodev test front    # Run frontend tests only
./kodev test base     # Run base C library tests
./kodev test front unit/test_file.lua  # Run specific test
```

**Coverage report**:

```
./kodev cov           # Generate coverage report
./kodev cov --full    # Full line-by-line coverage
```

Sources: [kodev659-700](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L659-L700)[kodev630-657](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L630-L657)

---

## Summary

KOReader's build system provides a sophisticated cross-platform compilation infrastructure supporting 10+ target platforms with different architectures and device capabilities. The `kodev` developer interface abstracts Make complexity while maintaining full control over build parameters. The device abstraction layer enables platform-specific optimizations while maintaining a consistent API. The OTA update system efficiently delivers updates using zsync delta compression for embedded devices and native package managers for desktop/mobile platforms.

**Key Components**:

- `kodev`: Developer-facing build script
- `Makefile`: Build orchestration with platform-specific includes
- `Device` hierarchy: Platform abstraction layer
- `OTAManager`: Over-the-air update system with zsync support

Sources: [kodev1-783](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L1-L783)[Makefile1-240](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L1-L240)[frontend/ui/otamanager.lua1-460](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/otamanager.lua#L1-L460)[frontend/device/android/device.lua1-605](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua#L1-L605)

---

# Build-System-and-Development-Workflow

# Build System and Development Workflow
Relevant source files
- [.gitignore](https://github.com/koreader/koreader/blob/9e6b1587/.gitignore)
- [Makefile](https://github.com/koreader/koreader/blob/9e6b1587/Makefile)
- [README.md](https://github.com/koreader/koreader/blob/9e6b1587/README.md?plain=1)
- [kodev](https://github.com/koreader/koreader/blob/9e6b1587/kodev)
- [platform/appimage/AppRun](https://github.com/koreader/koreader/blob/9e6b1587/platform/appimage/AppRun)

## Purpose and Scope

This page documents KOReader's build infrastructure and developer tooling. It covers the two-tier build system (`kodev` CLI and `Makefile`), supported build targets, development workflows (building, running, testing, debugging), release packaging, and quality assurance tools. For details on platform-specific device abstraction and hardware integration, see [Platform Abstraction and Device Support](/koreader/koreader/2.2-platform-abstraction-and-device-support).

Sources: [Makefile1-240](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L1-L240)[kodev1-783](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L1-L783)[README.md57-64](https://github.com/koreader/koreader/blob/9e6b1587/README.md?plain=1#L57-L64)

---

## Two-Tier Build Architecture

KOReader uses a two-tier build system:

1. **`kodev`**: A bash script providing a developer-friendly CLI with high-level commands, argument parsing, and sensible defaults
2. **`Makefile`**: The underlying build orchestrator handling compilation, linking, platform-specific rules, and artifact installation

```
External Dependencies

Build Outputs

Build System Core

Developer Interface

parse args
setup env

fetchthirdparty

symlinks to

Developer

kodev Script
CLI Commands

Makefile
Build Rules

base/Makefile.defs
Platform Variables

make/*.mk
Platform Rules

INSTALL_DIR/
Symlinked Files

koreader-base/
Native Libraries

l10n/*.mo
Translations

Git Submodules
Third-Party Code

base/thirdparty/
Libraries
```

**Diagram: Two-Tier Build System Architecture**

The `kodev` script abstracts complex `make` invocations, environment setup, and platform detection. It translates user-friendly commands like `kodev build kindle` into appropriate `make` targets with the correct variables. The `Makefile` then handles the actual compilation, dependency management, and installation.

Sources: [kodev79-96](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L79-L96)[kodev158-209](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L158-L209)[Makefile1-240](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L1-L240)

---

## kodev CLI Tool

The `kodev` script is located at the repository root and provides the primary developer interface.

### Command Categories

The script organizes commands into functional groups:
CategoryCommandsPurpose**Building**`build`, `clean`, `release`Compile and package KOReader**Emulator & Android**`run`Execute KOReader on emulator or Android device**Emulator Only**`prompt`, `test`, `cov`, `wbuilder`Development and testing tools**Android Only**`log`View application logs via ADB**Miscellaneous**`activate`, `fetch-thirdparty`, `check`Environment setup and validation
Sources: [kodev712-743](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L712-L743)

### Common Options

Most `kodev` commands accept standard build options:
OptionShortDescriptionDefault`--debug``-d`Enable debugging symbolsOn for emulator, off for devices`--no-debug``-n`Disable debugging symbols-`--no-build``-b`Skip compilation phaseOff`--verbose``-v`Verbose build outputOff
The `KODEBUG` variable controls whether a debug or release build is generated. Debug builds include symbols for debugging, while release builds are optimized for size and performance.

Sources: [kodev215-231](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L215-L231)[kodev256-268](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L256-L268)

### Command Parsing Flow

```
OPTS[], ARGS[]

parse target

TARGET='android'

TARGET='emulator'

TARGET='kobo'

Command Line Args

getopt Parsing

Argument Validation

setup_target()
Platform Setup

check_submodules()
Ensure Dependencies

run_make()
Execute Makefile

Setup Android
NDK/SDK

Set KODEBUG=1

Platform Config
```

**Diagram: kodev Command Processing Pipeline**

The `parse_options()` function uses GNU getopt to parse arguments into `OPTS[]` (options) and `ARGS[]` (positional arguments). The `setup_target()` function validates the target platform and sets appropriate environment variables.

Sources: [kodev233-304](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L233-L304)[kodev158-209](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L158-L209)

### Example: kodev build Command

The `kodev-build()` function demonstrates the typical command flow:

1. Parse options: `parse_options` extracts `--debug`, `--no-build`, etc.
2. Setup target: `setup_target` validates platform (e.g., `kindle`, `kobo`, `android-arm64`)
3. Check submodules: Ensures git submodules are initialized
4. Run make: Invokes `make` with appropriate variables

```
# Build for Kindle with debug symbols
kodev build --debug kindle
 
# Build for emulator without compiling (use existing build)
kodev build --no-build emulator
 
# Build for Android ARM64 in verbose mode
kodev build -v android-arm64
```

Sources: [kodev339-350](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L339-L350)

---

## Makefile Build System

The `Makefile` at the repository root orchestrates the entire build process.

### Core Build Flow

```
all target
Main Entry Point

base target
Compile Native Code

mo target
Build Translations

Installation Phase
Create Directory Structure

Symlinking Phase
Link Source Files

base-all
(from base/Makefile)

koreader-base
Native Libraries

LuaJIT Runtime

Third-Party Libs
MuPDF, CREngine, etc.

l10n//.po
Translation Sources

msgfmt Compiler

l10n//.mo
Compiled Translations

Link Application Files
reader.lua, frontend/, etc.

Link Data Files
fonts, dictionaries, etc.
```

**Diagram: Makefile Build Flow**

The `all` target (default) builds everything needed to run KOReader. It compiles native code via the `base` target, builds translations via `mo`, then sets up the installation directory with symlinks.

Sources: [Makefile140-173](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L140-L173)[Makefile174-179](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L174-L179)

### Key Variables

The `Makefile` uses several key variables to control the build:
VariablePurposeExample`TARGET`Platform to build for`kindle`, `kobo`, `android``KODEBUG`Enable debug build`1` (debug) or empty (release)`MACHINE`Target architecture`arm-kindlepw2-linux-gnueabi``INSTALL_DIR`Installation directory`koreader-emulator-x86_64-linux-gnu``KOR_BASE`koreader-base directory`base``ANDROID_ARCH`Android architecture`arm`, `arm64`, `x86`, `x86_64`
The installation directory name is constructed as: `koreader-${DIST}-${MACHINE}${DEBUG_SUFFIX}`.

Sources: [Makefile4-40](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L4-L40)[Makefile17-25](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L17-L25)

### Version Management

KOReader generates version strings from git metadata:

```
# From Makefile:9-15
RELEASE_DATE := $(shell git show -s --format=format:"%cd" --date=short HEAD)
VERSION := $(shell git describe HEAD)
ifneq (,$(findstring -,$(VERSION)))
	VERSION := $(VERSION)_$(RELEASE_DATE)
endif
```

For release tags like `v2023.04`, the version is just the tag. For development builds, it appends the commit count and date: `v2023.04-123-gabc1234_2023-04-15`.

Sources: [Makefile9-15](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L9-L15)[Makefile142](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L142-L142)

### Installation Phase

The installation phase creates the `INSTALL_DIR` directory structure and populates it with symlinks:

```
INSTALL_DIR/koreader/

Source Files

symlink

symlink

symlink

symlink

reader.lua
setupkoenv.lua
datastorage.lua

frontend/
plugins/
resources/

base/build/output/

Data Files

Application Lua Files

Application Directories

Native Binaries

fonts/
data/
l10n/
```

**Diagram: Installation Directory Structure**

Symlinks are used instead of copies to speed up development iteration. Changes to source files are immediately reflected in the installation directory.

Sources: [Makefile141-173](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L141-L173)[Makefile67-68](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L67-L68)

---

## Build Targets and Platforms

KOReader supports 15+ target platforms with platform-specific build rules in `make/*.mk`.

### Supported Platforms
TargetDescriptionBuild Command`emulator`SDL-based emulator for Linux/macOS`kodev build emulator``appimage`Linux AppImage bundle`kodev build appimage``linux`Native Linux build`kodev build linux``macos`macOS `.app` bundle`kodev build macos``win32`Windows build (experimental)`kodev build win32``android-arm`Android 32-bit ARM`kodev build android-arm``android-arm64`Android 64-bit ARM`kodev build android-arm64``android-x86`Android 32-bit x86`kodev build android-x86``android-x86_64`Android 64-bit x86`kodev build android-x86_64``kindle`Kindle (all models >= Kindle 4)`kodev build kindle``kindlehf`Kindle with HarfBuzz (FW >= 5.16.3)`kodev build kindlehf``kindlepw2`Kindle PaperWhite 2+ (optimized)`kodev build kindlepw2``kindle-legacy`Kindle 2/3/DXG`kodev build kindle-legacy``kobo`Kobo e-readers`kodev build kobo``kobov4`Kobo devices on FW 4.x`kodev build kobov4``kobov5`Kobo devices on FW 5.x`kodev build kobov5``cervantes`BQ Cervantes`kodev build cervantes``pocketbook`PocketBook devices`kodev build pocketbook``remarkable`reMarkable tablet`kodev build remarkable``remarkable-aarch64`reMarkable 2 (64-bit)`kodev build remarkable-aarch64``sony-prstux`Sony PRS-T* devices`kodev build sony-prstux``ubuntu-touch`Ubuntu Touch`kodev build ubuntu-touch`
Sources: [kodev129-154](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L129-L154)[kodev162-189](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L162-L189)

### Platform-Specific Makefiles

Each platform can provide custom build rules in `make/$(TARGET).mk`. These files are conditionally included:

```
# From Makefile:210-213
ifneq (,$(wildcard make/$(TARGET).mk))
  include make/$(TARGET).mk
endif
```

For example, `make/kindle.mk` contains Kindle-specific packaging rules, while `make/android.mk` handles APK building.

Sources: [Makefile210-213](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L210-L213)

### Android Build Process

Android builds require additional setup for NDK and SDK:

```
No

Yes

No

Yes

kodev build android-arm64

NDK Configured?
(ANDROID_NDK_HOME)

SDK Configured?
(ANDROID_HOME)

make android-ndk
Download NDK

make android-sdk
Download SDK

Build Native Libraries
for android-arm64

Package APK
(make/*.apk)
```

**Diagram: Android Build Dependency Resolution**

The `kodev` script automatically installs the Android NDK and SDK if environment variables are not set.

Sources: [kodev203-208](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L203-L208)[Makefile217-221](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L217-L221)

---

## Development Workflow

### Building and Running

The typical development cycle for the emulator:

```
# Initial build (compiles everything)
kodev build
 
# Run in emulator with default screen size (540x720)
kodev run
 
# Run with custom screen dimensions (simulates Kobo Clara)
kodev run --simulate kobo-clara
 
# Run with debugging enabled
kodev run --gdb
 
# Run without rebuilding (faster iteration)
kodev run --no-build
```

Sources: [kodev416-613](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L416-L613)

### Emulator Options

The `kodev run` command supports various emulator configurations:
OptionDescriptionExample`--screen-width=X`Set screen width`--screen-width=1072``--screen-height=X`Set screen height`--screen-height=1448``--screen-dpi=X`Set screen DPI`--screen-dpi=300``--simulate=DEVICE`Use device preset`--simulate=kobo-clara``--disable-touch`Keyboard-only mode`--disable-touch``--gdb[=DEBUGGER]`Run with debugger`--gdb`, `--gdb=cgdb``--valgrind[=OPTS]`Run with Valgrind`--valgrind``--callgrind`Run with callgrind`--callgrind``--wrap=COMMAND`Custom wrapper`--wrap="strace -f"`
Device presets include: `kindle`, `kindle-paperwhite`, `kobo-forma`, `kobo-aura-one`, `kobo-clara`, `kobo-h2o`, `hidpi`.

Sources: [kodev418-425](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L418-L425)[kodev434-446](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L434-L446)[kodev538-565](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L538-L565)

### Debugging Workflow

```
Start Debugging

Choose Tool

GDB / CGDB / DDD
kodev run --gdb

Valgrind Memcheck
kodev run --valgrind

Callgrind Profiler
kodev run --callgrind

Custom Wrapper
kodev run --wrap

Passes --directory base/
--args to debugger

Full leak check
track origins
trace children

Generates callgrind.out.*
for kcachegrind

Execute arbitrary wrapper
e.g., strace, ltrace
```

**Diagram: Debugging Options**

The script automatically detects available debuggers (preferring `ddd`, then `cgdb`, then `gdb`) and configures them with appropriate arguments.

Sources: [kodev474-507](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L474-L507)[kodev509-520](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L509-L520)[kodev467-473](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L467-L473)

### Android Development

For Android devices connected via ADB:

```
# Build and install APK on connected device
kodev run android-arm64
 
# Install pre-built APK and run
kodev run android-arm64 koreader-android-arm64.apk
 
# View live logs
kodev log
```

The `kodev log` command is a Python wrapper that filters and formats `adb logcat` output for KOReader.

Sources: [kodev591-612](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L591-L612)[kodev706-708](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L706-L708)

---

## Testing and Quality Assurance

### Test Runner

KOReader uses `busted` for unit and integration testing:

```
# Run all tests
kodev test
 
# Run only base tests (C/C++ components)
kodev test base
 
# Run only front-end tests (Lua application layer)
kodev test front
 
# Run specific test files
kodev test front spec/unit/readerbookmark_spec.lua
 
# Run with verbose output
kodev test -v
```

The `kodev-test()` function delegates to `base/test-runner/runtests`, which sets up the test environment and invokes `busted` with appropriate arguments.

Sources: [kodev659-700](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L659-L700)

### Test Organization

```
kodev test

Test Suite

all
(default)

base
Native Code Tests

front
Lua Application Tests

bench
Performance Tests

base/test/*.lua

spec/unit/_spec.lua
spec/front/unit/_spec.lua

benchmark files
```

**Diagram: Test Suite Organization**

Test files follow the convention `*_spec.lua` and are organized by subsystem.

Sources: [kodev663-666](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L663-L666)[kodev688-699](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L688-L699)

### Coverage Analysis

Generate coverage reports with `luacov`:

```
# Run tests with coverage tracking
kodev cov
 
# Show full coverage report (per-line)
kodev cov --full
 
# Show coverage from previous run without re-running tests
kodev cov --show-previous
```

Coverage data is written to `luacov.stats.out` and can be viewed in various formats.

Sources: [kodev630-657](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L630-L657)

### Static Analysis

The `check` command runs multiple linters:

```
# Run all checks (luacheck, shellcheck, etc.)
kodev check
```

This invokes `.ci/check.sh`, which runs:

- `luacheck` for Lua code (configured in `.luacheckrc`)
- `shellcheck` for shell scripts
- Other code quality tools

Sources: [kodev317-321](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L317-L321)[Makefile223-229](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L223-L229)

---

## Release Packaging

### Release Build Process

Creating a release package:

```
# Build release package for Kindle
kodev release kindle
 
# Build without fetching updated translations
kodev release --ignore-translation kobo
 
# Create update package from existing build (skip compilation)
kodev release --no-build android-arm64
```

The `release` command performs:

1. Fetches latest translations from Weblate (`make po`)
2. Builds the target (`make all`)
3. Creates update package (`make update`)

Sources: [kodev365-395](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L365-L395)

### Update Package Generation

```
kodev release TARGET

Fetch Translations
make po

Build Target
make all

Create Update Package
make update

tools/mkrelease.sh

Generate package.index
(file list + checksums)

Create .zip / .targz
with exclusions

Exclude Paths
cache/, history/, spec/

Exclude Patterns
*.orig, .swp, .
```

**Diagram: Release Package Creation**

The `tools/mkrelease.sh` script creates compressed archives with OTA (over-the-air) update metadata.

Sources: [kodev387-394](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L387-L394)[Makefile130-138](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L130-L138)

### Release Exclusions

The `Makefile` defines extensive exclusion rules to keep release packages small:

**Path Excludes** (relative to `koreader/`):

- `cache/`, `clipboard/`, `history/` - runtime data
- `data/dict/`, `data/tessdata/` - large dictionaries (user-provided)
- `l10n/templates/`, `l10n/*/*.po` - source translations (only `.mo` needed)
- `spec/` - test files
- `resources/icons/src*` - source assets

**Pattern Excludes** (global):

- `*.orig`, `*.swp`, `*.swo`, `*.un~` - editor artifacts
- `.*` - hidden files

**Runtime-Generated Files**:

- `settings.reader.lua*`, `history.lua` - user settings
- `defaults.*.lua` - runtime defaults

Sources: [Makefile75-126](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L75-L126)

---

## Third-Party Dependencies

### Git Submodules

KOReader uses git submodules for dependencies:

```
# Initialize and update all submodules
kodev fetch-thirdparty
 
# Or via make directly
make fetchthirdparty
```

The `fetchthirdparty` target handles two scenarios:

1. **CI builds**: Shallow clones (depth=1) for speed
2. **Development**: Conditional shallow clones only for configured submodules

```
# From Makefile:181-194
fetchthirdparty:
	git submodule sync --recursive
ifneq (,$(CI))
	git submodule update --depth 1 --jobs 3 --init --recursive
else
	# Force shallow clones of submodules configured as such.
	git submodule update --jobs 3 --depth 1 --init $(shell \
		git config --file=.gitmodules --name-only --get-regexp \
			'^submodule\.[^.]+\.shallow$$' true \
		| sed 's/\.shallow$$/.path/' \
		| xargs -n1 git config --file=.gitmodules \
		)
	# Update the rest.
	git submodule update --jobs 3 --init --recursive
endif
```

This optimizes clone time while allowing developers to work with full history when needed.

Sources: [Makefile181-194](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L181-L194)[kodev109-116](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L109-L116)

### Submodule Check

The `check_submodules()` function ensures dependencies are initialized before building:

```
# From kodev:109-116
check_submodules() {
    if grep -qE '^-' <<<"$(git submodule status)"; then
        TARGET='' run_make fetchthirdparty
    fi
}
```

Submodules in uninitialized state are prefixed with `-` in `git submodule status` output.

Sources: [kodev109-116](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L109-L116)

### Third-Party Libraries

The `koreader-base` submodule includes:

- **MuPDF**: PDF/XPS/EPUB rendering
- **DjVuLibre**: DjVu format support
- **CREngine**: EPUB/FB2/etc. reflow engine
- **LuaJIT**: Lua runtime with JIT compilation
- **FreeType**: Font rendering
- **HarfBuzz**: Text shaping
- **libjpeg-turbo**: JPEG decoding
- **libpng**: PNG decoding
- **Various others**: zlib, openssl, curl, etc.

These are built as part of the `base` target via `base/Makefile`.

Sources: [Makefile174-179](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L174-L179)[Makefile7](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L7-L7)

---

## Makefile Targets Reference

### Common Targets
TargetDescription`all`Build everything (default)`base`Build native code only`base-all`Build base with all dependencies`base-clean`Clean base build artifacts`base-distclean`Deep clean (removes downloads)`mo`Build translation files`mo-clean`Clean translation files`po`Fetch updated translations from Weblate`clean`Clean build artifacts`distclean`Deep clean (removes everything)`re`Clean rebuild`update`Create release package`run`Run KOReader (emulator or Android)`run-prompt`Run Lua REPL in KOReader environment`run-wbuilder`Run widget builder tool`test` / `testfront` / `testbase`Run tests`coverage` / `coverage-full`Generate coverage reports`static-check`Run luacheck linter`fetchthirdparty`Update git submodules`android-ndk`Install Android NDK`android-sdk`Install Android SDK`doc`Build API documentation
Sources: [Makefile1](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L1-L1)[Makefile140](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L140-L140)[Makefile196-233](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L196-L233)

### Variable Reference
VariablePurposeSet By`TARGET`Platform to build forkodev or user`KODEBUG`Debug build flagkodev or user`VERBOSE`Verbose output`--verbose` option`NO_BUILD`Skip build phase`--no-build` option`ANDROID_ARCH`Android architecturekodev (from target)`MACHINE`Target machine tripletMakefile.defs`INSTALL_DIR`Installation directoryCalculated from TARGET`KOR_BASE`koreader-base pathDefault: `base``EMULATE_READER_W/H/DPI`Emulator dimensionskodev run options`RWRAP`Command wrapperkodev run (gdb, valgrind)`RARGS`Runtime argumentskodev run extra args
Sources: [Makefile4-40](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L4-L40)[kodev88-96](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L88-L96)

---

## Development Tools Reference

### Helper Commands

**Activate kodev in shell**:

```
# Add kodev to PATH for current shell session
kodev activate
```

**LuaJIT REPL**:

```
# Interactive Lua prompt with KOReader environment
kodev prompt
 
# With readline support (requires rlwrap)
rlwrap kodev prompt
```

**Widget Builder**:

```
# Visual widget development tool
kodev wbuilder
```

Sources: [kodev310-315](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L310-L315)[kodev401-414](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L401-L414)[kodev615-624](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L615-L624)

### Build System Utilities

The build system provides several internal utilities:

- **`print_quoted()`**: Safely quote shell arguments
- **`run()`**: Execute commands with logging and timing
- **`run_make()`**: Invoke make with standard variables
- **`is_system()`**: Check OS compatibility
- **`show_help()`**: Display command help

Sources: [kodev55-97](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L55-L97)

---

## Troubleshooting

### Common Issues

**Submodules not initialized**:

```
# Error: base/Makefile not found
# Solution:
kodev fetch-thirdparty
```

**Android NDK/SDK not configured**:

```
# Error: ANDROID_NDK_HOME not set
# Solution: kodev automatically installs them
kodev build android-arm64
```

**Build failures after git pull**:

```
# Clean rebuild
kodev clean
kodev build
```

**Stale symlinks in INSTALL_DIR**:

```
# Remove installation directory
rm -rf koreader-*
kodev build
```

Sources: [kodev109-116](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L109-L116)[kodev203-208](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L203-L208)[Makefile196-203](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L196-L203)

---

## Summary

The KOReader build system provides a comprehensive developer experience through:

1. **kodev CLI**: High-level commands for building, running, testing, and debugging
2. **Makefile**: Robust build orchestration supporting 15+ platforms
3. **Cross-compilation**: Automated toolchain setup for embedded devices
4. **Development tools**: Emulator, debugger integration, test runners, coverage analysis
5. **Release packaging**: Automated update package creation with OTA metadata
6. **Dependency management**: Git submodules with optimized clone strategies

The two-tier architecture abstracts complexity while maintaining flexibility for advanced users who can directly invoke `make` targets or modify platform-specific build rules.

Sources: [Makefile1-240](https://github.com/koreader/koreader/blob/9e6b1587/Makefile#L1-L240)[kodev1-783](https://github.com/koreader/koreader/blob/9e6b1587/kodev#L1-L783)

---

# Platform-Abstraction-and-Device-Support

# Platform Abstraction and Device Support
Relevant source files
- [frontend/device/android/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua)
- [frontend/device/cervantes/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/cervantes/device.lua)
- [frontend/device/generic/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua)
- [frontend/device/input.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/input.lua)
- [frontend/device/kindle/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kindle/device.lua)
- [frontend/device/kobo/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua)
- [frontend/device/pocketbook/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/pocketbook/device.lua)
- [frontend/device/sdl/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/sdl/device.lua)
- [frontend/device/sony-prstux/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/sony-prstux/device.lua)
- [frontend/ui/uimanager.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua)
- [platform/android/llapp_main.lua](https://github.com/koreader/koreader/blob/9e6b1587/platform/android/llapp_main.lua)
- [spec/unit/device_spec.lua](https://github.com/koreader/koreader/blob/9e6b1587/spec/unit/device_spec.lua)

## Purpose and Scope

The Platform Abstraction Layer provides unified interfaces for hardware capabilities across 15+ supported platforms (Kobo, Kindle, Android, PocketBook, Cervantes, etc.). This system allows the rest of KOReader to interact with device-specific features (screen, input, power, network) through a common API, with platform-specific implementations handling hardware differences transparently.

For build system and platform compilation details, see [Build System and Development Workflow](/koreader/koreader/2.1-build-system-and-development-workflow). For UI event processing that consumes Device abstractions, see [UI Manager and Event Loop](/koreader/koreader/3.2-ui-manager-and-event-loop) and [Input Handling and Gesture Detection](/koreader/koreader/3.4-input-handling-and-gesture-detection).

## Device Class Hierarchy

All device implementations extend from the `Generic` base class, which defines the standard interface and default implementations. Platform-specific classes override methods and set capability flags to reflect their hardware features.

```
Generic

+Screen screen

+Input input

+PowerD powerd

+bool hasBattery()

+bool hasKeys()

+bool hasFrontlight()

+bool hasWifiToggle()

+bool isTouchDevice()

+bool canSuspend()

+init()

+suspend()

+resume()

+powerOff()

+reboot()

+initNetworkManager()

Kobo

+string model

+bool hasNaturalLight

+bool isMk7

+bool isSunxi

+bool isMTK

+table frontlight_settings

+initEventAdjustHooks()

+toggleChargingLED()

Kindle

+bool isREAGL

+bool isZelda

+bool hasLightSensor

+LipcHandle framework_lipc_handle

+toggleKeyRepeat()

+openInputDevices()

Android

+int firmware_rev

+bool hasOTARunning()

+importFile()

+doShareText()

+doExternalDictLookup()

PocketBook

+bool automagic_sysfs

+table raw_input

+bool inkview_translates_buttons

+associateFileExtensions()

+setAutoStandby()

SDL

+bool isEmulator

+bool isDesktop

+table window

+openLink()

+doExternalDictLookup()

Cervantes

+bool touch_legacy

+bool touch_switch_xy

+bool hasNaturalLightMixer

+initEventAdjustHooks()
```

**Sources:**[frontend/device/generic/device.lua27-144](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua#L27-L144)[frontend/device/kobo/device.lua93-160](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L93-L160)[frontend/device/kindle/device.lua387-422](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kindle/device.lua#L387-L422)[frontend/device/android/device.lua75-120](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua#L75-L120)[frontend/device/pocketbook/device.lua22-76](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/pocketbook/device.lua#L22-L76)[frontend/device/sdl/device.lua64-110](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/sdl/device.lua#L64-L110)[frontend/device/cervantes/device.lua23-55](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/cervantes/device.lua#L23-L55)

## Hardware Capability Flags

The `Generic` device class defines capability flags as functions (returning `true`/`false`) that platform implementations override. These flags control feature availability throughout the application.
Capability FlagPurposeDefault`hasBattery`Device has a battery (vs always plugged in)`yes``hasKeys`Device has physical buttons`no``isTouchDevice`Device has touchscreen`no``hasFrontlight`Device has adjustable frontlight`no``hasNaturalLight`Frontlight supports warmth/color temperature`no``hasWifiToggle`Can programmatically enable/disable Wi-Fi`yes``hasWifiManager`Can manage Wi-Fi networks via wpa_supplicant`no``canSuspend`Can enter low-power suspend state`no``canReboot`Can reboot the device`no``canPowerOff`Can power off the device`no``hasOTAUpdates`Supports over-the-air updates`no``hasEinkScreen`Has e-ink display (vs LCD)`yes``hasColorScreen`Screen supports color`no``canHWInvert`Hardware supports screen inversion`no``canHWDither`Hardware supports dithering`no``hasGSensor`Has accelerometer/gyroscope`no``hasMultitouch`Supports multi-touch inputDefaults to `isTouchDevice`
**Sources:**[frontend/device/generic/device.lua40-143](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua#L40-L143)

## Device Detection and Instantiation

Device detection happens at startup through platform-specific probing. Each platform file returns the appropriate device class instance after hardware detection.

```
Device Classes

Device Probing

reader.lua startup

require('device')

Check PRODUCT env var

Read /proc/usid

Android SDK check

Check /ebrmain/

SDL platform

Read ntxinfo pcb

Parse Kobo model

Parse Kindle model

Android device

PocketBook device

SDL/Desktop device

Cervantes model

Kobo variants:
KoboStorm, KoboNova,
KoboFrost, etc.

Kindle variants:
KindleVoyage,
KindleOasis, etc.

Device class

PocketBook variants

Desktop/Emulator/
AppImage

Cervantes variants

Device:init()

Create Screen object

Create Input object

Create PowerD object

Setup event hooks

UIManager:init()
```

**Sources:**[frontend/device/kobo/device.lua677-897](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L677-L897)[frontend/device/kindle/device.lua632-658](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kindle/device.lua#L632-L658)[frontend/device/android/device.lua138-309](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua#L138-L309)[frontend/device/pocketbook/device.lua110-266](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/pocketbook/device.lua#L110-L266)[frontend/device/sdl/device.lua161-269](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/sdl/device.lua#L161-L269)[frontend/device/cervantes/device.lua110-131](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/cervantes/device.lua#L110-L131)

### Example: Kobo Device Detection

Kobo devices are identified by the `PRODUCT` environment variable and optional `MODEL_NUMBER`:

```
-- From frontend/device/kobo/device.lua
local product_id = os.getenv("PRODUCT")
if product_id == "storm" then
    return KoboStorm  -- Libra
elseif product_id == "nova" then
    return KoboNova   -- Clara HD
elseif product_id == "frost" then
    return KoboFrost  -- Forma
-- ... more models
end
```

Each model class extends `Kobo` with specific configuration:

[frontend/device/kobo/device.lua364-393](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L364-L393) - KoboStorm (Libra) defines:

- `isMk7 = yes` (updated display driver)
- `hasKeys = yes` (page turn buttons)
- `hasNaturalLight = yes` (warmth control)
- `frontlight_settings` table with sysfs paths
- `hasReliableMxcWaitFor = no` (driver quirk)

**Sources:**[frontend/device/kobo/device.lua677-711](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L677-L711)[frontend/device/kobo/device.lua162-598](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L162-L598)

## Core Subsystems

### Screen/Framebuffer Abstraction

Each platform provides a `screen` object implementing a common interface for display operations. Implementations handle platform-specific framebuffer APIs (mxcfb for NTX/Kobo, sunxi for AllWinner SoCs, MTK for MediaTek, Android NDK, SDL).

```
Features

Platform Implementations

Screen Interface

getSize()

getDPI()

refreshFull/refreshPartial()

setRotationMode()

toggleNightMode()

framebuffer_mxcfb
(Kobo, Cervantes,
Kindle, PocketBook)

framebuffer_sunxi
(Kobo Elipsa/Sage)

framebuffer_mxcfb
with MTK quirks
(Kobo Libra Colour)

framebuffer_android
(Android)

framebuffer_SDL3
(Desktop/Emulator)

Waveform modes:
FULL, PARTIAL, UI,
FAST, A2

Hardware rotation

HW inversion

HW dithering
```

**Platform-Specific Screen Initialization:**
PlatformImplementationKey FeaturesKobo (i.MX)`framebuffer_mxcfb`REAGL support, GL16 waveform, HW rotationKobo (Sunxi)`framebuffer_sunxi`G2D rotation, no HW inversionKobo (MTK)`framebuffer_mxcfb`Global inversion flag via `/proc/hwtcon/cmd`Kindle`framebuffer_mxcfb`REAGL for new models, Zelda/Rex driversAndroid`framebuffer_android`ANativeWindow integrationPocketBook`framebuffer_pocketbook`Inkview integration, optional raw modeSDL`framebuffer_SDL3`Window resizing, mouse wheel support
**Sources:**[frontend/device/kobo/device.lua686-728](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L686-L728)[frontend/device/kindle/device.lua632-712](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kindle/device.lua#L632-L712)[frontend/device/android/device.lua139](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua#L139-L139)[frontend/device/pocketbook/device.lua114-184](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/pocketbook/device.lua#L114-L184)[frontend/device/sdl/device.lua180-191](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/sdl/device.lua#L180-L191)[frontend/device/cervantes/device.lua111](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/cervantes/device.lua#L111-L111)

### Input Handling

The `Input` class [frontend/device/input.lua103-203](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/input.lua#L103-L203) provides unified input event processing across all platforms. It translates raw hardware events into KOReader's internal event format.

```
Gesture Detection

Event Processing

Input Class

Input Sources

/dev/input/event*
(Linux evdev)

SDL3 events
(Desktop/Emulator)

Android input
(touch/key events)

PocketBook Inkview
(abstracted input)

Input:open(path)

Input:waitEvent()

eventAdjustHook()

event_map translation

rotation_map

Touch events:
ABS_MT_POSITION_X/Y

Key events:
EV_KEY

Misc events:
MSC_GYRO

Sync events:
SYN_REPORT

GestureDetector

Tap detection

Swipe/Pan detection

Hold detection

Pinch/Spread
```

**Input Event Adjustments:**

Different hardware requires coordinate transformations. Devices register `eventAdjustHook` functions to handle:

- **Axis mirroring**: Some touchscreens have X or Y coordinates reversed
- **Axis swapping**: Touch coordinates may be rotated relative to screen
- **Rotation compensation**: Button mappings change with device orientation
- **Protocol quirks**: Legacy single-touch vs modern multi-touch protocols

Example from Kobo Trilogy C [frontend/device/kobo/device.lua1055-1066](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L1055-L1066):

```
-- Kobo Trilogy C: mirror X, then swap X/Y axes
self.input:registerEventAdjustHook(
    self.input.adjustTouchSwitchXYAndMirrorX,
    self.screen:getWidth() - 1
)
```

**Event Mapping:**

The `event_map` table translates raw Linux input codes to KOReader event names:

[frontend/device/kobo/device.lua827-837](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L827-L837) - Kobo event map:

```
event_map = {
    [59] = "SleepCover",     -- KEY_F1
    [90] = "LightButton",    -- KEY_KPCOMMA
    [102] = "Home",          -- KEY_HOME
    [116] = "Power",         -- KEY_POWER
    [193] = "RPgBack",       -- BTN_7
    [194] = "RPgFwd",        -- BTN_8
}
```

**Sources:**[frontend/device/input.lua1-309](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/input.lua#L1-L309)[frontend/device/kobo/device.lua825-862](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L825-L862)[frontend/device/kindle/device.lua536-591](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kindle/device.lua#L536-L591)[frontend/device/android/device.lua144-244](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua#L144-L244)[frontend/device/pocketbook/device.lua192-229](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/pocketbook/device.lua#L192-L229)

### Power Management

Each device implements a `PowerD` class extending `BasePowerD` to handle battery monitoring, frontlight control, and power state management.

```
BasePowerD

+Device device

+int fl_min

+int fl_max

+int fl_intensity

+bool is_fl_on

+setIntensity(intensity)

+getCapacity()

+isCharging()

+beforeSuspend()

+afterResume()

KoboPowerD

+string battery_sysfs

+string aux_battery_sysfs

+table frontlight_settings

+bool hasNaturalLightMixer

+turnOnFrontlightImpl()

+turnOffFrontlightImpl()

+frontlightIntensityHW()

+getCapacityHW()

KindlePowerD

+bool canTurnFrontlightOff

+LipcHandle lipc_handle

+setIntensityWithLipc()

+toggleFrontlight()

AndroidPowerD

+getCapacityHW()

+getDismissedRate()

+isChargingHW()
```

**Frontlight Control:**

Kobo devices with natural light (warmth control) use either a mixer interface or separate white/warm sysfs paths:

[frontend/device/kobo/device.lua373-382](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L373-L382) - Kobo Storm frontlight settings:

```
frontlight_settings = {
    frontlight_white = "/sys/class/backlight/mxc_msp430.0/brightness",
    frontlight_mixer = "/sys/class/backlight/lm3630a_led/color",
    nl_min = 0,        -- Warmth minimum
    nl_max = 10,       -- Warmth maximum
    nl_inverted = true -- Sysfs uses inverted scale
}
```

**Battery Monitoring:**

Battery status is read from platform-specific sysfs paths:

- Kobo: `/sys/class/power_supply/mc13892_bat/` or `/sys/class/power_supply/battery/`
- Kindle: Uses lipc (IPC) to query `com.lab126.powerd`
- Android: Native API via `android.getBatteryLevel()`

**Sources:**[frontend/device/generic/powerd.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/powerd.lua)[frontend/device/kobo/powerd.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/powerd.lua)[frontend/device/kindle/powerd.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kindle/powerd.lua)[frontend/device/android/powerd.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/powerd.lua)

### Network Management

Network connectivity is handled through `NetworkMgr`, with device-specific implementations provided via `initNetworkManager`:

```
PocketBook Implementation

Android Implementation

Kindle Implementation

Kobo Implementation

NetworkMgr Interface

turnOnWifi()

turnOffWifi()

isConnected()

getNetworkList()

saveNetwork()

./enable-wifi.sh

./disable-wifi.sh

wpa_supplicant
/var/run/wpa_supplicant/

./obtain-ip.sh

lipc commands
com.lab126.wifid

scanList property

profileData hasharray

openWifiSettings()
(system UI)

getNetworkInfo()

inkview.NetConnect()

inkview.NetMgrPing()
(keepalive)
```

**Kobo Network Implementation:**

[frontend/device/kobo/device.lua1216-1251](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L1216-L1251) - Uses wpa_supplicant:

```
function NetworkMgr:turnOnWifi(complete_callback)
    os.execute("./enable-wifi.sh")
    return self:reconnectOrShowNetworkMenu(complete_callback)
end
 
NetworkMgr:setWirelessBackend("wpa_supplicant", {
    ctrl_interface = "/var/run/wpa_supplicant/eth0"
})
```

**Kindle Network Implementation:**

[frontend/device/kindle/device.lua424-525](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kindle/device.lua#L424-L525) - Uses lipc IPC:

```
function NetworkMgr:turnOnWifi(complete_callback)
    kindleEnableWifi(1)  -- Sets com.lab126.wifid enable property
    return self:reconnectOrShowNetworkMenu(complete_callback)
end
 
function NetworkMgr:getNetworkList()
    local scan_list = kindleScanThenGetResults()
    -- Retrieves scanList hasharray via lipc
end
```

**Sources:**[frontend/device/kobo/device.lua1216-1251](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L1216-L1251)[frontend/device/kindle/device.lua424-525](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kindle/device.lua#L424-L525)[frontend/device/android/device.lua315-340](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua#L315-L340)[frontend/device/pocketbook/device.lua360-411](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/pocketbook/device.lua#L360-L411)[frontend/device/cervantes/device.lua154-183](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/cervantes/device.lua#L154-L183)

## Device Initialization Flow

Device initialization follows a specific sequence to ensure all subsystems are properly configured before the UI starts.

```
UIManager
PowerD Object
Input Object
Screen Object
Device Class
Device Module
reader.lua
UIManager
PowerD Object
Input Object
Screen Object
Device Class
Device Module
reader.lua
Device fully initialized
and integrated
require("device")
Probe platform
Select device class
Device:init()
Create screen object
Open framebuffer
Detect rotation, DPI
Create input object
Setup event_map
Create GestureDetector
Open input devices
Create powerd object
Probe battery sysfs
Probe frontlight paths
initEventAdjustHooks()
Setup viewport
Apply saved settings
Device:_UIManagerReady()
Setup event handlers
Device:setEventHandlers()
```

**Initialization Steps:**

1. **Platform Detection**[frontend/device/kobo/device.lua1533-1558](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L1533-L1558): Environment variables, sysfs probes, or file checks identify the platform
2. **Device Class Creation**: Platform-specific device class is instantiated
3. **Screen Initialization**[frontend/device/kobo/device.lua686-728](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L686-L728):

- Framebuffer opened and configured
- DPI, rotation mode detected
- Hardware capabilities probed (HW rotation, inversion, dithering)
4. **Input Setup**[frontend/device/kobo/device.lua825-862](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L825-L862):

- Input devices opened (touch, buttons)
- Event map configured
- Event adjustment hooks registered
- GestureDetector created
5. **PowerD Creation**[frontend/device/kobo/device.lua819-823](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L819-L823):

- Battery sysfs paths detected
- Frontlight paths configured
- Initial state read
6. **Event Hooks**[frontend/device/kobo/device.lua860-862](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L860-L862):

- Touch coordinate transformations registered
- Protocol-specific handlers installed
7. **UIManager Integration**[frontend/ui/uimanager.lua113-114](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L113-L114):

- Device notifies UIManager it's ready
- UIManager registers power event handlers
- Event loop can now process device events

**Sources:**[frontend/device/generic/device.lua198-328](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua#L198-L328)[frontend/device/kobo/device.lua677-862](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L677-L862)[frontend/device/kindle/device.lua632-1025](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kindle/device.lua#L632-L1025)[frontend/ui/uimanager.lua50-119](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L50-L119)

## Platform-Specific Implementations

### Kobo (15+ Models)

**Key Features:**

- NTX E-Ink controllers (i.MX5, i.MX6, i.MX7, AllWinner, MediaTek)
- Natural light (warmth) on select models
- Sleep cover detection
- Charging LED control on some models

**Device Variants:**[frontend/device/kobo/device.lua162-598](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L162-L598) defines models:

- Trilogy series (Touch A/B/C)
- Glo, Aura series
- Clara, Libra, Forma, Elipsa, Sage
- Color models (Libra Colour, Clara Colour)

**Platform Quirks:**

- `hasReliableMxcWaitFor = no` for Libra/Clara 2E: WAIT_FOR_UPDATE_COMPLETE ioctls timeout [frontend/device/kobo/device.lua383-392](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L383-L392)
- `automagic_sysfs = true`: Hardware revisions require runtime sysfs detection [frontend/device/kobo/device.lua734-758](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L734-L758)
- MTK devices use global screen inversion via `/proc/hwtcon/cmd`[frontend/device/kobo/device.lua713-728](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L713-L728)
- Sunxi (AllWinner) SoCs disable HW inversion [frontend/device/kobo/device.lua696](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L696-L696)

**Sources:**[frontend/device/kobo/device.lua93-1558](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L93-L1558)

### Kindle (30+ Models)

**Key Features:**

- Supports frameworks from legacy (K2) to modern (Oasis 3)
- LIPC (Inter-Process Communication) for system integration
- Voyage WhisperTouch pressure sensors
- Oasis/Scribe accelerometer support

**Special Offers Handling:**[frontend/device/kindle/device.lua329-354](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kindle/device.lua#L329-L354) detects ad-supported devices to disable screensaver

**Framework Integration:**[frontend/device/kindle/device.lua367-385](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kindle/device.lua#L367-L385) - When `STOP_FRAMEWORK=yes`, registers with framework to prevent conflicts

**Sources:**[frontend/device/kindle/device.lua387-1717](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kindle/device.lua#L387-L1717)

### Android

**Key Features:**

- Supports ARMv7, ARM64, x86, x86_64 architectures
- SDK version detection (API 16+)
- E-ink device detection
- OTA updates via background downloads

**Lifecycle Events:**[frontend/device/android/device.lua147-233](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua#L147-L233) handles:

- `APP_CMD_RESUME`: Restore UI, handle intents
- `APP_CMD_PAUSE`: Flush settings
- `APP_CMD_CONFIG_CHANGED`: Screen rotation/resize
- `AEVENT_DOWNLOAD_COMPLETE`: OTA update ready

**Sources:**[frontend/device/android/device.lua75-474](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua#L75-L474)

### PocketBook

**Key Features:**

- Inkview SDK integration
- Optional raw input mode (lower latency)
- File extension associations
- Natural light API

**Input Modes:**[frontend/device/pocketbook/device.lua51-60](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/pocketbook/device.lua#L51-L60) - Can use raw `/dev/input/` events (requires root) or Inkview abstraction

**Sources:**[frontend/device/pocketbook/device.lua22-597](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/pocketbook/device.lua#L22-L597)

### SDL (Desktop/Emulator)

**Key Features:**

- Cross-platform (Linux, Windows, macOS)
- Window resizing support
- Drag-and-drop file opening
- Mouse wheel scrolling emulation

**Variants:**[frontend/device/sdl/device.lua118-159](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/sdl/device.lua#L118-L159)

- `AppImage`: Linux AppImage with OTA updates
- `Desktop`: Native desktop build
- `Flatpak`: Sandboxed Linux build
- `Emulator`: E-ink simulation with frontlight
- `UbuntuTouch`: Ubuntu Touch mobile platform

**Sources:**[frontend/device/sdl/device.lua64-429](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/sdl/device.lua#L64-L429)

### Cervantes

**Key Features:**

- NTX-based Spanish e-readers
- Similar architecture to Kobo
- Mass storage toggle support

**Models:**[frontend/device/cervantes/device.lua57-94](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/cervantes/device.lua#L57-L94)

- CervantesTouch (no frontlight)
- CervantesTouchLight
- Cervantes 2013, 3, 4

**Sources:**[frontend/device/cervantes/device.lua23-297](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/cervantes/device.lua#L23-L297)

## Integration with UIManager

`UIManager` receives a reference to the initialized `Device` object and uses it to:

1. **Query Capabilities**: Check flags before showing features
2. **Handle Power Events**: Suspend/resume/power button handling
3. **Access Subsystems**: Get `Screen` for refreshes, `Input` for events, `PowerD` for battery/frontlight

**UIManager Initialization:**

[frontend/ui/uimanager.lua113-114](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L113-L114):

```
function UIManager:init()
    -- Device tells UIManager it's ready
end
 
-- Called by Device:init()
function Device:_UIManagerReady(self)
    UIManager = require("ui/uimanager")
    -- Setup PM event handlers
end
```

**Power Event Handlers:**

[frontend/ui/uimanager.lua51-93](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L51-L93) defines handlers:

- `Power` event: Suspend or resume based on `screen_saver_mode`
- `Suspend` event: Show screensaver, disable Wi-Fi, call `Device:suspend()`
- `Resume` event: Restore Wi-Fi, refresh screen, call `Device:resume()`

**Event Processing Integration:**

[frontend/ui/uimanager.lua14-15](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L14-L15):

```
local Device = require("device")
local Input = Device.input
local Screen = Device.screen
```

UIManager directly uses `Device.input` and `Device.screen` for event loops and rendering.

**Sources:**[frontend/ui/uimanager.lua1-119](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L1-L119)[frontend/device/generic/device.lua844-873](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua#L844-L873)

## Event Processing and Hardware Adjustments

Hardware differences require coordinate transformations and event translations. Devices register hooks during initialization to adjust events before gesture detection.

### Touch Coordinate Transformations

**Common Transformations:**
TransformationFunctionUsageMirror X`adjustTouchMirrorX`Touch X coordinate invertedMirror Y`adjustTouchMirrorY`Touch Y coordinate invertedSwitch X/Y`adjustTouchSwitchXY`Touch axes swapped (rotated 90°)Translate`adjustTouchTranslate`Offset for viewport/bezelSwitch & Mirror X`adjustTouchSwitchXYAndMirrorX`Combined transformation
**Registration Example:**

[frontend/device/kobo/device.lua1055-1067](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L1055-L1067) - Kobo Trilogy C:

```
if self.touch_switch_xy then
    if self.touch_mirrored_x then
        self.input:registerEventAdjustHook(
            self.input.adjustTouchSwitchXYAndMirrorX,
            self.screen:getWidth() - 1
        )
    end
end
```

### Button Rotation Mapping

Physical buttons change function based on screen rotation. The `rotation_map` translates button events:

[frontend/device/input.lua270-277](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/input.lua#L270-L277):

```
rotation_map = {
    [framebuffer.DEVICE_ROTATED_UPRIGHT] = {},
    [framebuffer.DEVICE_ROTATED_CLOCKWISE] = {
        LPgBack = "LPgFwd",
        LPgFwd = "LPgBack",
        RPgBack = "RPgFwd",
        RPgFwd = "RPgBack"
    },
    -- ... more rotations
}
```

Some devices disable rotation mapping when the OS handles it:

[frontend/device/pocketbook/device.lua232-234](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/pocketbook/device.lua#L232-L234):

```
if self.inkview_translates_buttons then
    self.input:disableRotationMap()
end
```

### Protocol-Specific Handlers

**Legacy Single-Touch Protocol:**

[frontend/device/cervantes/device.lua98-108](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/cervantes/device.lua#L98-L108) - Cervantes uses legacy protocol:

```
if self.touch_legacy then
    self.input.handleTouchEv = self.input.handleTouchEvLegacy
end
```

**Multi-Touch Slot Mapping:**

Some devices don't use slot 0 as the first finger:

[frontend/device/kobo/device.lua215](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L215-L215) - Kobo Dahlia:

```
main_finger_slot = 1  -- First finger assigned to slot 1, not 0
```

**Tool Type Detection:**

Devices with pen input detect stylus vs finger:

[frontend/device/input.lua27-29](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/input.lua#L27-L29):

```
local TOOL_TYPE_FINGER = 0
local TOOL_TYPE_PEN    = 1
```

[frontend/device/kobo/device.lua417](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L417-L417) - Kobo Elipsa tracks pressure:

```
pressure_event = C.ABS_MT_PRESSURE
```

### Custom Event Handlers

**Sony PRSTUX Touch ID Injection:**

[frontend/device/sony-prstux/device.lua33-52](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/sony-prstux/device.lua#L33-L52) - Sony driver doesn't report tracking IDs, so fake them:

```
local adjustTouchEvt = function(self, ev)
    if ev.type == C.EV_ABS and ev.code == C.ABS_MT_TOUCH_MAJOR then
        ev.code = C.ABS_MT_TRACKING_ID
        if ev.value ~= 0 then
            ev.value = next_touch_id
        else
            ev.value = -1
        end
        next_touch_id = next_touch_id + 1
    end
end
```

**Kindle Oasis Rotation Events:**

[frontend/device/kindle/device.lua1134-1168](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kindle/device.lua#L1134-L1168) - Oasis uses `ABS_PRESSURE` for accelerometer:

```
if ev.type == C.EV_ABS and ev.code == C.ABS_PRESSURE then
    local rotation = self:fromFSensorOrientation(ev.value)
    return UIManager:onRotation(rotation)
end
```

**Sources:**[frontend/device/input.lua213-309](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/input.lua#L213-L309)[frontend/device/kobo/device.lua1015-1067](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L1015-L1067)[frontend/device/kindle/device.lua1134-1168](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kindle/device.lua#L1134-L1168)[frontend/device/sony-prstux/device.lua33-52](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/sony-prstux/device.lua#L33-L52)

## Summary

The Platform Abstraction Layer enables KOReader to run on 15+ platforms by:

1. **Unified Interface**: `Generic` device class defines common API
2. **Capability Flags**: Boolean functions indicate hardware features
3. **Subsystem Abstraction**: Screen, Input, PowerD provide consistent interfaces
4. **Platform Detection**: Automatic hardware identification at startup
5. **Event Transformation**: Coordinate adjustments and protocol handlers compensate for hardware differences
6. **Network Abstraction**: Platform-specific Wi-Fi implementations behind common interface

This architecture allows the rest of KOReader (UI, reader modules, plugins) to work identically across all platforms, with device differences handled transparently by the abstraction layer.

---

# Core-Framework-Systems

# Core Framework Systems
Relevant source files
- [frontend/device/android/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua)
- [frontend/device/cervantes/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/cervantes/device.lua)
- [frontend/device/devicelistener.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/devicelistener.lua)
- [frontend/device/generic/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua)
- [frontend/device/gesturedetector.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/gesturedetector.lua)
- [frontend/device/input.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/input.lua)
- [frontend/device/kindle/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kindle/device.lua)
- [frontend/device/kobo/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua)
- [frontend/device/pocketbook/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/pocketbook/device.lua)
- [frontend/device/sdl/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/sdl/device.lua)
- [frontend/device/sony-prstux/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/sony-prstux/device.lua)
- [frontend/dispatcher.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/dispatcher.lua)
- [frontend/ui/uimanager.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua)
- [platform/android/llapp_main.lua](https://github.com/koreader/koreader/blob/9e6b1587/platform/android/llapp_main.lua)
- [plugins/gestures.koplugin/main.lua](https://github.com/koreader/koreader/blob/9e6b1587/plugins/gestures.koplugin/main.lua)
- [plugins/hotkeys.koplugin/main.lua](https://github.com/koreader/koreader/blob/9e6b1587/plugins/hotkeys.koplugin/main.lua)
- [plugins/profiles.koplugin/main.lua](https://github.com/koreader/koreader/blob/9e6b1587/plugins/profiles.koplugin/main.lua)
- [reader.lua](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua)
- [spec/unit/device_spec.lua](https://github.com/koreader/koreader/blob/9e6b1587/spec/unit/device_spec.lua)

This document covers KOReader's foundational framework systems that provide the base infrastructure for all application functionality. These systems handle application lifecycle, device abstraction, UI management, input processing, action dispatching, and settings management.

## Application Entry and Lifecycle

KOReader's main entry point is `reader.lua`, which orchestrates the initialization sequence, launches the appropriate application mode, and handles clean shutdown.

### Startup Sequence

**Initialization Flow Diagram**

```
Launch Decision

Device & UI Initialization

Environment Setup

directory or no args

file path

reader.lua execution

setupkoenv.lua
Lua path configuration

userpatch.early
User patches

G_defaults
luadefaults:open()

G_reader_settings
settings.reader.lua

Device = require('device')
Platform detection

CanvasContext:init(Device)
Rendering setup

onetime_migration.lua
Settings upgrades

Bidi.setup(lang_locale)
RTL/text shaping

UIManager = require('ui/uimanager')
Event loop creation

userpatch.late
Developer patches

Command-line args
or start_with setting

FileManager:showFiles()

ReaderUI:showReader()
```

Sources: [reader.lua1-184](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L1-L184)[reader.lua217-282](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L217-L282)

The initialization sequence follows a strict order to ensure dependencies are satisfied:

1. **Environment Setup**: [reader.lua22-56](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L22-L56) configures Lua paths via `setupkoenv.lua`, loads default settings from `G_defaults`, and reads user settings from `G_reader_settings`.
2. **Device Initialization**: [reader.lua151-158](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L151-L158) detects the platform and initializes the `Device` singleton, which sets up screen, input, and power management subsystems.
3. **UI Manager Creation**: [reader.lua181](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L181-L181) instantiates the `UIManager` singleton, establishing the event loop and widget management system.
4. **Launch Decision**: [reader.lua217-282](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L217-L282) determines whether to start `FileManager` (for directory browsing) or `ReaderUI` (for document reading) based on command-line arguments or the `start_with` setting.

### Application Shutdown

**Shutdown Sequence Diagram**

```
Cleanup Sequence

Shutdown Triggers

User exits UI

Exit event
(device shutdown)

UIManager:flushSettings()
Persist in-memory state

Device:exit()
Save settings & cleanup

userpatch.before_exit
User scripts

exitReader()
Final cleanup

userpatch.on_exit
User scripts
```

Sources: [reader.lua285-307](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L285-L307)

The shutdown sequence at [reader.lua285-307](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L285-L307) ensures proper cleanup and settings persistence. `Device:exit()` at [frontend/device/generic/device.lua602-618](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua#L602-L618) flushes `G_reader_settings` to disk and performs platform-specific cleanup like screen restoration and network shutdown.

### Settings Loading and Persistence

**Settings Architecture**
Settings ObjectFile PathScopePersistence`G_defaults``luadefaults.lua`Application defaultsRead-only`G_reader_settings``settings.reader.lua`Global user settingsAuto-flush on change`DocSettings``{filename}.sdr/metadata.lua`Per-document settingsAuto-flush on change
Sources: [reader.lua33-40](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L33-L40)[frontend/docsettings.lua1-100](https://github.com/koreader/koreader/blob/9e6b1587/frontend/docsettings.lua#L1-L100)

`G_reader_settings` is initialized at [reader.lua39-40](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L39-L40) using `LuaSettings:open()`, which provides automatic persistence via `LuaSettings:saveSetting()`. Document-specific settings are managed by `DocSettings` instances, which store configuration in `.sdr` sidecar directories.

## UI Manager and Event Loop

The `UIManager` serves as KOReader's central UI coordination system, managing widget lifecycles, event dispatching, and screen refresh scheduling. It operates as a singleton that maintains the complete UI state.

### Widget Management and Window Stack

```
Task Scheduling

Widget Lifecycle

UIManager Core

Event Processing

event_handlers{}

broadcastEvent()

sendEvent()

_window_stack[]
Widget hierarchy

_task_queue[]
Scheduled actions

_dirty{}
Widgets needing repaint

_refresh_stack[]
Screen refresh queue

show(widget, refreshtype)

close(widget, refreshtype)

setDirty(widget, refreshtype)

schedule(time, action)

scheduleIn(seconds, action)

nextTick(action)
```

The `UIManager` maintains several key data structures:

- `_window_stack`: Array of window objects containing widgets in display order
- `_dirty`: Hash table tracking widgets requiring repainting
- `_task_queue`: Priority queue of scheduled tasks with timestamps
- `_refresh_stack`: Queue of pending screen refresh operations

Sources: [frontend/ui/uimanager.lua20-48](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L20-L48)[frontend/ui/uimanager.lua148-186](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L148-L186)[frontend/ui/uimanager.lua569-659](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L569-L659)

### Event Loop and Refresh Management

```
Refresh Control

Main Event Loop

Refresh Types

full
High fidelity

partial
Text optimized

ui
Mixed content

fast
Monochrome

flashui
Flashing UI

flashpartial
Flashing partial

waitEvent()
Input polling

handleInput()
Event processing

_repaint()
Widget painting

_refresh()
Screen updates

FULL_REFRESH_COUNT
E-ink optimization

refresh_count
Partial refresh counter

currently_scrolling
Scroll state
```

The refresh system optimizes e-ink display updates by tracking refresh counts and automatically promoting partial refreshes to full refreshes based on the `FULL_REFRESH_COUNT` setting.

Sources: [frontend/ui/uimanager.lua17-24](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L17-L24)[frontend/ui/uimanager.lua706-740](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L706-L740)[frontend/ui/uimanager.lua466-507](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L466-L507)

## Device Abstraction Layer

KOReader's device abstraction provides a unified interface across different e-reader hardware platforms while exposing platform-specific capabilities through feature flags.

### Device Hierarchy and Capabilities

```
Model-Specific Variants

Platform Implementations

Generic Device

Generic:extend{}

Capability Flags
hasKeys, isTouchDevice
hasFrontlight, hasWifiToggle
canSuspend, canReboot

Hardware Abstraction
screen, input, powerd

Kobo:extend{Generic}

Kindle:extend{Generic}

PocketBook:extend{Generic}

Remarkable:extend{Generic}

Device:extend{Generic}
(SDL/Desktop)

KoboDaylight
KoboNova
KoboFrost

KindleOasis
KindleVoyage
KindlePW
```

Each device class defines capabilities through boolean functions and provides platform-specific implementations of core subsystems.

Sources: [frontend/device/generic/device.lua27-144](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua#L27-L144)[frontend/device/kobo/device.lua93-160](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L93-L160)[frontend/device/kindle/device.lua337-370](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kindle/device.lua#L337-L370)

### Device Initialization and Integration

```
Platform-Specific Setup

Device:init() Process

UIManager Integration

_UIManagerReady()
Cross-reference setup

Event Handler Setup
Power, Suspend, Resume

Screen Initialization
framebuffer setup

Input Initialization
event device setup

PowerD Initialization
battery/frontlight

Viewport Setup
screen regions

Rotation Management
orientation handling

Framebuffer Backend
mxcfb, sunxi, SDL

Input Devices
touch, buttons, stylus

Power Management
frontlight, battery
```

The initialization process establishes the complete hardware abstraction stack and integrates with `UIManager` for event handling.

Sources: [frontend/device/generic/device.lua198-328](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua#L198-L328)[frontend/device/kobo/device.lua677-865](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L677-L865)[frontend/ui/uimanager.lua114](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L114-L114)

## Input and Gesture Detection System

KOReader's input system processes hardware events through multiple abstraction layers, culminating in gesture recognition for touch interfaces.

### Input Processing Pipeline

```
Event Output

GestureDetector

Input Module

Hardware Layer

Touch Events
ABS_MT_POSITION_X/Y
BTN_TOUCH

Key Events
KEY_PRESS/RELEASE

Sensor Events
MSC_GYRO

event_map{}
Hardware → Logical

rotation_map{}
Orientation handling

event_map_adapter{}
Event transformation

Contact State
active_contacts{}

State Machine
tap, hold, swipe, pan

Gesture Types
single, multi-finger

UI Events
Tap, Hold, Swipe

Key Events
Press, Menu, Back
```

The pipeline transforms raw hardware events into semantic UI events through mapping tables and gesture state machines.

Sources: [frontend/device/input.lua103-204](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/input.lua#L103-L204)[frontend/device/input.lua270-277](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/input.lua#L270-L277)[frontend/device/gesturedetector.lua68-104](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/gesturedetector.lua#L68-L104)

### Gesture Detection State Machine

```
contact_down

quick_release

hold_timeout

movement_threshold

contact_up

movement

contact_up

contact_up

second_tap_in_interval

second_contact

second_contact_hold

movement

distance_change

rotation_detected

Idle

Touch

Tap

Hold

Pan

PanRelease

HoldPan

HoldRelease

HoldPanRelease

DoubleTap

TwoFingerTap

TwoFingerHold

TwoFingerSwipe

Pinch

Rotate
```

The `GestureDetector` maintains contact state and timing information to recognize complex multi-touch gestures.

Sources: [frontend/device/gesturedetector.lua133-200](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/gesturedetector.lua#L133-L200)[frontend/device/gesturedetector.lua400-500](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/gesturedetector.lua#L400-L500)[frontend/device/gesturedetector.lua600-700](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/gesturedetector.lua#L600-L700)

## Action Dispatcher and Profiles

The `Dispatcher` provides centralized action registration and execution, enabling consistent gesture-to-action mapping across the application. It integrates with the Gestures, Hotkeys, and Profiles plugins to provide flexible user customization.

### Dispatcher Architecture and Plugin Integration

**Dispatcher System Overview**

```
Action Sources

Plugin Integration

Action Execution

Action Registry

settingsList{}
Action definitions

registerAction(name, definition)
Runtime registration

Action Categories
none, arg, string
absolutenumber, incrementalnumber

Dispatcher.execute(action, gesture, ges_ev)

UIManager:sendEvent(Event:new())

UIManager:broadcastEvent()

Gestures Plugin
gestures.lua mappings

Hotkeys Plugin
hotkeys.lua mappings

Profiles Plugin
Action sequences

Touch Gestures
tap_corner, swipe, hold

Keyboard Input
modifier + key combos

Auto-Execute
Profile triggers
```

Sources: [frontend/dispatcher.lua48-50](https://github.com/koreader/koreader/blob/9e6b1587/frontend/dispatcher.lua#L48-L50)[frontend/dispatcher.lua53-294](https://github.com/koreader/koreader/blob/9e6b1587/frontend/dispatcher.lua#L53-L294)[plugins/gestures.koplugin/main.lua30-38](https://github.com/koreader/koreader/blob/9e6b1587/plugins/gestures.koplugin/main.lua#L30-L38)[plugins/hotkeys.koplugin/main.lua17-23](https://github.com/koreader/koreader/blob/9e6b1587/plugins/hotkeys.koplugin/main.lua#L17-L23)[plugins/profiles.koplugin/main.lua21-28](https://github.com/koreader/koreader/blob/9e6b1587/plugins/profiles.koplugin/main.lua#L21-L28)

### Gesture and Hotkey Mapping

**Gesture-to-Action Mapping Process**

The Gestures plugin maintains gesture definitions in `gestures.lua` and registers them with `InputContainer` gesture handlers. When a gesture is detected:

1. `InputContainer:onGesture()` receives the gesture event
2. The gesture name is looked up in `gestures.lua`
3. The mapped action is retrieved: `gesture_map[gesture_name]`
4. `Dispatcher.execute(action)` is called to trigger the action

**Hotkeys Mapping Table**
ModifierBase KeysDevice RequirementStorageScreenKB/ShiftUp, Down, Left, Right, Press, Menu`hasScreenKB() or hasKeyboard()``hotkeys.lua`Alt/CtrlAll alphabet keys + base keys`hasKeyboard()``hotkeys.lua`
Sources: [plugins/gestures.koplugin/main.lua112-240](https://github.com/koreader/koreader/blob/9e6b1587/plugins/gestures.koplugin/main.lua#L112-L240)[plugins/hotkeys.koplugin/main.lua26-60](https://github.com/koreader/koreader/blob/9e6b1587/plugins/hotkeys.koplugin/main.lua#L26-L60)

Hotkeys are stored in `hotkeys.lua` and registered via `InputContainer:addKeyBinding()` at [plugins/hotkeys.koplugin/main.lua86-120](https://github.com/koreader/koreader/blob/9e6b1587/plugins/hotkeys.koplugin/main.lua#L86-L120) The plugin differentiates between Kindles (which use Alt) and other devices (which use Ctrl) for the secondary modifier.

### Profiles Plugin and Auto-Execution

**Profile Execution System**

```
Actions

Execution Flow

Trigger Points

Profile Storage

profiles.lua
Profile definitions

data[profile_name]
settings + actions

Application Start
onStart()

Document Open
ReaderReady

Document Close
onCloseDocument

Check autoexec settings

Show confirmation
(if auto_exec_ask)

Execute action sequence

Single Actions
Dispatcher.execute()

Action Sequences
Multiple dispatches
```

Sources: [plugins/profiles.koplugin/main.lua20-80](https://github.com/koreader/koreader/blob/9e6b1587/plugins/profiles.koplugin/main.lua#L20-L80)[plugins/profiles.koplugin/main.lua209-280](https://github.com/koreader/koreader/blob/9e6b1587/plugins/profiles.koplugin/main.lua#L209-L280)

Profiles can be configured to auto-execute on three trigger points defined in `self.autoexec`:

- `"filemanager"`: When FileManager starts
- `"filemanager_on_close_file"`: When closing a document
- `"reader"`: When ReaderUI opens a document

At [plugins/profiles.koplugin/main.lua209-245](https://github.com/koreader/koreader/blob/9e6b1587/plugins/profiles.koplugin/main.lua#L209-L245) the plugin checks `self.autoexec[trigger][profile_name]` to determine if a profile should run. If `profile.settings.auto_exec_ask` is true, a confirmation dialog is shown before execution.

### Action Categories and Parameters

```
Target Systems

Parameter Processing

Action Types

none
Direct event call

arg
Gesture object parameter

string
Enumerated choices

absolutenumber
Set value

incrementalnumber
Relative change

configurable
Document settings

Value Determination
args, min/max, step

Gesture Integration
Distance, direction

Event Creation
Event:new(event, arg)

UIManager Events
ShowMenu, ToggleFooter

Document Events
SetFont, GotoPage

Device Events
ToggleWifi, Suspend

FileManager Events
SetDisplayMode
```

Different action categories support various parameter types, from simple event triggers to complex value calculations based on gesture properties.

Sources: [frontend/dispatcher.lua9-29](https://github.com/koreader/koreader/blob/9e6b1587/frontend/dispatcher.lua#L9-L29)[frontend/dispatcher.lua419-500](https://github.com/koreader/koreader/blob/9e6b1587/frontend/dispatcher.lua#L419-L500)[frontend/dispatcher.lua600-700](https://github.com/koreader/koreader/blob/9e6b1587/frontend/dispatcher.lua#L600-L700)

## Integration and Event Flow

The core framework systems work together to provide a unified application foundation.

### System Integration Flow

```
Application Layer

UI Framework

Device Layer

Hardware Events

Touch/Key Hardware

Device Instance
Platform abstraction

Input Instance
Event processing

Screen Instance
Display management

UIManager
Widget & event coordination

GestureDetector
Touch gesture recognition

Dispatcher
Action execution

FileManager

ReaderUI

Plugins
```

This architecture enables clean separation of concerns while maintaining efficient event processing and consistent behavior across platforms.

Sources: [frontend/ui/uimanager.lua50-120](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L50-L120)[frontend/device/generic/device.lua235-280](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua#L235-L280)[frontend/device/input.lua310-320](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/input.lua#L310-L320)

---

# Application-Entry-and-Lifecycle

# Application Entry and Lifecycle
Relevant source files
- [reader.lua](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua)

This document describes the application startup sequence, initialization phases, and lifecycle management in KOReader. It covers the main entry point in [reader.lua](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua) the ordered initialization of subsystems, application selection logic, and the exit process.

For information about the UI event loop that runs after initialization, see [UI Manager and Event Loop](/koreader/koreader/3.2-ui-manager-and-event-loop). For details on settings persistence mechanisms, see [Settings and Configuration Management](/koreader/koreader/3.6-settings-and-configuration-management). For platform-specific initialization, see [Device Abstraction Layer](/koreader/koreader/3.3-device-abstraction-layer).

## Entry Point

KOReader's entry point is [reader.lua1](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L1-L1) a Lua script executed via the LuaJIT shebang. The script enforces line-buffered stdout output and sets the C locale for consistent numerical formatting before printing the startup banner.

**Sources:**[reader.lua1-19](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L1-L19)

## Initialization Sequence

The initialization process follows a strict ordering to ensure dependencies are loaded correctly:

```
reader.lua execution starts

Configure stdout buffering
Set C locale

require('setupkoenv')
Set Lua/FFI search paths

userpatch.applyPatches
(early_once, early)

Version:getCurrentRevision()

G_defaults = luadefaults:open()

DataStorage initialization

G_reader_settings =
luasettings:open(settings.reader.lua)

Configure C blitter
bb:setUseCBB()

Language setup
gettext.changeLang()

Debug configuration
dbg:turnOn()

Parse command line arguments

Device = require('device')

CanvasContext:init(Device)

Version:updateVersionLog()

onetime_migration.lua

Bidi.setup(lang_locale)
RTL/UI mirroring

Font override from settings

UIManager = require('ui/uimanager')

userpatch.applyPatches(late)

Color rendering checks

Startup script version check

Application launch decision
```

**Initialization Phase Details:**
PhaseModule/FunctionPurposeEnvironment Setup`setupkoenv`Configures Lua package paths and FFI search pathsUser Patches (Early)`userpatch.applyPatches()`Applies user modifications before core loadingDefaults Loading`G_defaults`Loads default settings from `luadefaults`Settings Loading`G_reader_settings`Loads persistent user settings from `settings.reader.lua`C Blitter Config`bb:setUseCBB()` / `bb:enableCBB()`Enables/disables C-based blitting based on settingsLanguage Setup`_.changeLang()`Configures gettext localizationDebug Setup`dbg:turnOn()` / `dbg:setVerbose()`Enables debug logging if configuredDevice Init`Device = require("device")`Loads platform abstraction layerCanvas Init`CanvasContext:init(Device)`Initializes document rendering contextMigration`onetime_migration.lua`Handles settings upgrades between versionsBidi Setup`Bidi.setup()`Configures RTL language support and UI mirroringUIManager`UIManager = require("ui/uimanager")`Loads event loop and widget managerUser Patches (Late)`userpatch.applyPatches(late)`Applies patches after core systems loaded
**Sources:**[reader.lua21-185](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L21-L185)

### Critical Ordering Constraints

Several initialization steps have strict ordering requirements:

1. **`setupkoenv` must precede all other requires** to ensure Lua can find modules [reader.lua22](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L22-L22)
2. **Settings must load before language setup** because locale switching affects string translation [reader.lua38-56](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L38-L56)
3. **C blitter configuration must occur before extensive UI work** to prevent JIT flush issues on Android [reader.lua42-62](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L42-L62)
4. **`Bidi.setup()` must precede UIManager** because widgets may cache mirroring settings during `require()`[reader.lua166-170](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L166-L170)
5. **Device must initialize before CanvasContext** as canvas rendering depends on device capabilities [reader.lua151-155](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L151-L155)

**Sources:**[reader.lua21-181](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L21-L181)

## Global State Initialization

KOReader establishes two global settings objects during startup:

```
Usage

File System

Global State

G_defaults
(LuaDefaults)

G_reader_settings
(LuaSettings)

luadefaults.lua

settings.reader.lua
(in DataStorage dir)

Device initialization

UI components

Applications
```

- **`G_defaults`**: Read-only default values provided by [luadefaults.lua](https://github.com/koreader/koreader/blob/9e6b1587/luadefaults.lua) Accessed via `G_defaults:readSetting(key)`[reader.lua33](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L33-L33)
- **`G_reader_settings`**: Persistent user settings stored in `DataStorage:getDataDir()/settings.reader.lua`. Provides read/write access via `readSetting()`, `saveSetting()`, `delSetting()`[reader.lua39-40](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L39-L40)

These globals are accessible throughout the codebase and persist across application restarts (for `G_reader_settings`).

**Sources:**[reader.lua33-40](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L33-L40)

## Command Line Argument Processing

KOReader supports command line arguments for debugging and file opening:
ArgumentShortEffect`--debug``-d`Enable debug logging`--verbose``-v`Enable verbose debug output`--profile``-p`Enable Lua profiling via `jit.p``--help``-h`Display usage information`<path>`—File to open or directory to browse
The argument parser handles `file://` URIs by converting them to local paths via `getPathFromURI()`[reader.lua96-110](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L96-L110)

File path processing:

- If path is a **file**: Set `file` variable, will launch `ReaderUI`
- If path is a **directory**: Set `directory` variable, will launch `FileManager`
- If no path provided: Use `start_with` setting to determine behavior

**Sources:**[reader.lua71-148](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L71-L148)

## Application Launch Decision

After initialization completes, KOReader determines which application to launch based on command line arguments and saved preferences:

```
Yes

No

Yes

No

Yes

No

No

Yes

'last'

'filemanager'

'history'

'favorites'

'folder_shortcuts'

No

Yes

Initialization complete

exit_code set?
(e.g., update pending)

Skip launch

file argument?

ReaderUI:showReader(file)

directory argument?

FileManager:showFiles(directory)

Read start_with setting

QuickStart shown?

Override start_with='last'
last_file=QuickStart doc

start_with value?

ReaderUI:showReader(last_file)

last_file exists?

FileManager:showFiles(home_dir)

FileManager:showFiles(home_dir)
+instance.history:onShowHist()

FileManager:showFiles(home_dir)
+instance.collections:onShowColl()

FileManager:showFiles(home_dir)
+instance.folder_shortcuts:onShowFolderShortcutsDialog()

Show retry dialog
or fall back to FM

UIManager:run()

exit_code = return value
```

**Launch Logic Details:**

1. **Explicit file argument**: Launch `ReaderUI` directly on the specified file [reader.lua238-241](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L238-L241)
2. **Explicit directory argument**: Launch `FileManager` in that directory [reader.lua242-245](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L242-L245)
3. **No arguments**: Check `start_with` setting [reader.lua249](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L249-L249):

- `"last"`: Open the last file from `lastfile` setting [reader.lua276-280](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L276-L280)
- `"filemanager"`: Open FileManager in home directory [reader.lua282-286](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L282-L286)
- `"history"`: Open FileManager with history overlay [reader.lua288-289](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L288-L289)
- `"favorites"`: Open FileManager with collections overlay [reader.lua290-291](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L290-L291)
- `"folder_shortcuts"`: Open FileManager with shortcuts dialog [reader.lua292-293](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L292-L293)

**QuickStart Override**: If the QuickStart document hasn't been shown yet (`QuickStart:isShown()` returns false), KOReader overrides the user's `start_with` preference to open the QuickStart guide [reader.lua251-255](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L251-L255)

**Last File Retry Logic**: If `start_with="last"` but the file doesn't exist (e.g., external storage not mounted), KOReader displays a retry dialog allowing the user to wait for storage or fall back to FileManager [reader.lua257-275](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L257-L275)

**Sources:**[reader.lua236-296](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L236-L296)

## Main Event Loop

After launching the appropriate application, KOReader enters the main event loop by calling `UIManager:run()`:

```
exit_code = UIManager:run()
```

This function blocks until the application exits, processing events from the input system, managing the widget stack, and coordinating screen refreshes. See [UI Manager and Event Loop](/koreader/koreader/3.2-ui-manager-and-event-loop) for detailed coverage of the event processing cycle.

The `UIManager:run()` return value becomes the process exit code.

**Sources:**[reader.lua241-296](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L241-L296)

## Exit and Cleanup

Application exit follows a structured cleanup sequence:

```
Yes

No

UIManager:run() returns

userpatch.applyPatches
(before_exit)

exitReader()

Device:exit()

Flush G_reader_settings to disk

Profiler active?

Profiler.stop()

Return exit_code

userpatch.applyPatches
(on_exit)

os.exit(reader_retval, true)

Process terminates
```

**Exit Phases:**

1. **UIManager returns**: Event loop exits with a return code [reader.lua241-296](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L241-L296)
2. **Before-exit patches**: User scripts run via `userpatch.applyPatches(userpatch.before_exit)`[reader.lua314](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L314-L314)
3. **exitReader()**: Core cleanup function [reader.lua300-311](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L300-L311)
- Calls `Device:exit()` which flushes settings to disk
- Stops profiler if active
- Returns exit code
4. **On-exit patches**: Final user scripts via `userpatch.applyPatches(userpatch.on_exit)`[reader.lua319](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L319-L319)
5. **Process termination**: `os.exit(reader_retval, true)` with Lua state closure [reader.lua322](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L322-L322)

The second argument `true` to `os.exit()` ensures the Lua state is properly closed, running finalizers and garbage collection.

**Sources:**[reader.lua300-322](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L300-L322)

## User Patch System

KOReader provides four extension points via the `userpatch` module for user customization:

```
Shutdown

Runtime

Initialization

early_once
(first launch only)

early
(before core loading)

late
(after UIManager loaded)

before_exit
(before cleanup)

on_exit
(final hook)

reader.lua starts

Core initialization

UIManager:run()

Device:exit()

Process exit
```

**Patch Point Timing:**
HookLinePurpose`early_once`[reader.lua26](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L26-L26)First launch only, before any modules loaded`early`[reader.lua27](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L27-L27)Every launch, before core modules`late`[reader.lua184](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L184-L184)After UIManager loaded, before app launch`before_exit`[reader.lua314](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L314-L314)Before `Device:exit()` cleanup`on_exit`[reader.lua319](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L319-L319)Final hook before process termination
Users place Lua files in the `patches/` directory to hook these extension points. This system allows modification of KOReader behavior without editing core files.

**Sources:**[reader.lua25-27](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L25-L27)[reader.lua184](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L184-L184)[reader.lua314](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L314-L314)[reader.lua319](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L319-L319)

## Startup Checks and Warnings

Before launching the main application, KOReader performs several validation checks:

### Color Rendering Validation

On first launch on a color device, KOReader shows an informational message about color rendering and enables it [reader.lua189-196](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L189-L196) If color rendering is mistakenly enabled on a grayscale device (e.g., after importing settings from a color device), it displays a warning with options to disable or ignore [reader.lua199-217](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L199-L217)

The warning blocks via `UIManager:runOnce()`, which processes events until the user makes a choice without starting the full event loop.

**Sources:**[reader.lua189-217](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L189-L217)

### Startup Script Version Check

KOReader verifies that the startup script (launcher shell script) matches the application version via `Device:isStartupScriptUpToDate()`[reader.lua221-233](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L221-L233) If outdated, it displays a warning and offers to quit so the user can update the launcher.

This check prevents version mismatches between the launcher (which may be installed by a package manager) and the application code.

**Sources:**[reader.lua221-233](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L221-L233)

## Summary: Complete Lifecycle

The complete KOReader lifecycle:

1. **Process Start**: LuaJIT executes [reader.lua](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua)
2. **Environment Setup**: Configure stdout, locale, search paths
3. **Early Initialization**: Load defaults, settings, configure language/debug
4. **Platform Initialization**: Load Device, CanvasContext, run migrations
5. **Framework Initialization**: Load UIManager, apply late patches
6. **Validation Checks**: Verify color rendering, startup script version
7. **Application Launch**: Launch ReaderUI or FileManager based on arguments/settings
8. **Event Loop**: Process input, manage widgets, refresh screen until exit
9. **Cleanup**: Run before_exit patches, call Device:exit(), stop profiler
10. **Final Hooks**: Run on_exit patches
11. **Process Termination**: Close Lua state and exit with code

This structured approach ensures proper dependency ordering, supports multiple launch modes, and provides extension points for user customization.

**Sources:**[reader.lua1-323](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L1-L323)

---

# UI-Manager-and-Event-Loop

# UI Manager and Event Loop
Relevant source files
- [frontend/device/android/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua)
- [frontend/device/cervantes/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/cervantes/device.lua)
- [frontend/device/generic/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua)
- [frontend/device/input.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/input.lua)
- [frontend/device/kindle/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kindle/device.lua)
- [frontend/device/kobo/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua)
- [frontend/device/pocketbook/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/pocketbook/device.lua)
- [frontend/device/sdl/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/sdl/device.lua)
- [frontend/device/sony-prstux/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/sony-prstux/device.lua)
- [frontend/ui/uimanager.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua)
- [platform/android/llapp_main.lua](https://github.com/koreader/koreader/blob/9e6b1587/platform/android/llapp_main.lua)
- [spec/unit/device_spec.lua](https://github.com/koreader/koreader/blob/9e6b1587/spec/unit/device_spec.lua)

## Purpose and Scope

UIManager (`frontend/ui/uimanager.lua`) is the central orchestrator of KOReader's user interface. It manages the widget stack, dispatches input events, schedules asynchronous tasks, coordinates screen repaints, and runs the main event loop that drives the entire application. This page documents UIManager's responsibilities, the event processing pipeline, task scheduling system, screen refresh optimization, and the main loop execution flow.

For information about input event capture and gesture detection, see [Input Handling and Gesture Detection](/koreader/koreader/3.4-input-handling-and-gesture-detection). For details about the widget hierarchy and lifecycle, see [Widget System and Base Classes](/koreader/koreader/7.1-widget-system-and-base-classes). For device-specific integration, see [Device Abstraction Layer](/koreader/koreader/3.3-device-abstraction-layer).

---

## Architecture Overview

UIManager is a singleton that sits at the heart of KOReader's UI framework. It maintains the widget stack, owns the task queue, coordinates screen updates, and runs the main event loop. All UI activity flows through UIManager.

```
Application Layer

UI Framework Core

Platform Layer

UIManager State

events

power events

handleInput()

sendEvent()
broadcastEvent()

paintTo()

refresh ioctls

_checkTasks()

show/close widgets

show/close widgets

setDirty()

part of

part of

part of

part of

Device
(device abstraction)

Input
(event capture)

Screen
(framebuffer)

UIManager
Singleton

_window_stack
(widget stack)

_task_queue
(scheduled tasks)

_dirty
(widgets to repaint)

_refresh_stack
(queued refreshes)

event_handlers
(system event map)

Window-Level Widgets
(dialogs, menus, etc)

FileManager

ReaderUI
```

**Key Components:**

- **`_window_stack`**: Array of window-level widgets, ordered bottom-to-top, with special handling for modals and toasts
- **`_task_queue`**: Binary heap of scheduled tasks sorted by execution time
- **`_dirty`**: Hash set of widgets requiring repaint
- **`_refresh_stack` / `_refresh_func_stack`**: Queued screen refresh requests
- **`event_handlers`**: Map of system event names to handler functions
- **Main loop**: `run()` method that polls input and executes tasks

Sources: [frontend/ui/uimanager.lua1-48](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L1-L48)[frontend/ui/uimanager.lua50-119](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L50-L119)

---

## Widget Stack Management

UIManager maintains a stack of window-level widgets in `_window_stack`. The stack ordering determines paint order (bottom-to-top) and event propagation (top-to-bottom).

### Stack Organization

```
Widget Stack (_window_stack)

painted last
(on top)

painted first
(on bottom)

handleEvent()

if not consumed

if not consumed

Top: Toast Widgets
(notifications)

Middle: Modal Widgets
(dialogs, keyboards)

Bottom: Standard Widgets
(ReaderUI, FileManager)

Base Layer

Event Propagation
(top-to-bottom)
```

**Insertion Logic** ([frontend/ui/uimanager.lua156-184](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L156-L184)):

1. **Toasts** (`widget.toast == true`): Inserted at the top, above other toasts
2. **Modals** (`widget.modal == true`): Inserted above non-modals but below toasts
3. **Standard widgets**: Inserted below modals

### Core Methods

#### `UIManager:show(widget, refreshtype, refreshregion, x, y, refreshdither)`

Registers and displays a widget.

```
Widget
_window_stack
UIManager
Application
Widget
_window_stack
UIManager
Application
Widget is visible
and scheduled for paint
show(widget, "ui")
Find insertion point
(toast/modal/standard)
Insert at position
handleEvent("Show")
setDirty(widget, "ui")
```

Key behaviors ([frontend/ui/uimanager.lua156-198](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L156-L198)):

- Creates `window` record with `{x, y, widget}`
- Inserts into stack based on widget type
- Calls `setDirty()` to schedule repaint
- Sends `"Show"` event to widget
- Configures `Input.disable_double_tap` and `Input.tap_interval_override` based on widget properties
- Temporarily re-enables input if gestures were disabled

#### `UIManager:close(widget, refreshtype, refreshregion, refreshdither)`

Unregisters a widget and refreshes uncovered widgets.

```
_window_stack
Widget
UIManager
Application
_window_stack
Widget
UIManager
Application
loop
[For each newly
visible widget]
close(widget, "partial")
handleEvent("FlushSettings")
handleEvent("CloseWidget")
Remove widget
Identify covered widgets
setDirty(uncovered_widget)
_refresh("partial")
```

Key behaviors ([frontend/ui/uimanager.lua215-282](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L215-L282)):

- Sends `"FlushSettings"` and `"CloseWidget"` events
- Removes widget from stack
- Marks uncovered widgets as dirty (up to first fullscreen widget)
- Honors dithering hints from uncovered widgets
- Restores `Input.disable_double_tap` setting from remaining widgets

### Widget Inspection Methods

- **`UIManager:getNthTopWidget(n)`**: Returns the nth widget from the top (0-indexed from top)
- **`UIManager:getTopmostVisibleWidget()`**: Returns topmost non-invisible widget
- **`UIManager:isWidgetShown(widget)`**: Checks if widget is in stack
- **`UIManager:isSubwidgetShown(widget, max_depth)`**: Recursively searches for widget in stack

Sources: [frontend/ui/uimanager.lua156-282](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L156-L282)[frontend/ui/uimanager.lua753-810](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L753-L810)

---

## Event System

UIManager coordinates event dispatch between the platform layer (Input, Device) and the application layer (widgets).

### Event Types

```
Event Handlers

Event Sources

Hardware Input
(touch, keys)

System Events
(power, USB)

Application Events
(timers, callbacks)

Fake Events
(suspend, charging)

handleInput()
sendEvent()

event_handlers
(Power, SaveState, etc)

Widget.handleEvent()
```

### System Event Handlers

UIManager maintains a registry of system-level event handlers in `event_handlers` ([frontend/ui/uimanager.lua50-75](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L50-L75)):
EventHandler Behavior`__default__`Calls `sendEvent(input_event)` to dispatch to widgets`SaveState`Calls `flushSettings()` to persist configuration`Power`Delegates to `Device:onPowerEvent()` for suspend/resume`UsbDevicePlugIn`Broadcasts `"EvdevInputInsert"` event`UsbDevicePlugOut`Broadcasts `"EvdevInputRemove"` event
**Power Management Actions** ([frontend/ui/uimanager.lua76-111](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L76-L111)):

- **`poweroff_action`**: Shows screensaver, broadcasts `"PowerOff"` and `"Close"`, then calls `Device:powerOff()`
- **`reboot_action`**: Shows screensaver, broadcasts `"Reboot"` and `"Close"`, then calls `Device:reboot()`

### Event Dispatch Methods

#### `UIManager:sendEvent(event)`

Dispatches an event to widgets in the stack, top-to-bottom, until consumed.

```
Bottom Widget
Middle Widget
Top Widget
UIManager
Input System
Bottom Widget
Middle Widget
Top Widget
UIManager
Input System
Event consumed,
propagation stops
sendEvent(tap_event)
handleEvent(tap_event)
false (not consumed)
handleEvent(tap_event)
true (consumed)
```

Implementation ([frontend/ui/uimanager.lua1025-1055](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L1025-L1055)):

- Iterates `_window_stack` from top to bottom
- Calls `widget:handleEvent(event)`
- Stops if handler returns `true`
- Returns `true` if any handler consumed the event

#### `UIManager:broadcastEvent(event)`

Sends an event to all widgets in the stack, regardless of return value.

```
Event
(e.g., 'FlushSettings')

Widget 1

Widget 2

Widget 3

Widget N

Execute Handler

Execute Handler

Execute Handler

Execute Handler
```

Implementation ([frontend/ui/uimanager.lua1057-1063](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L1057-L1063)):

- Iterates entire `_window_stack` bottom to top
- Calls `widget:handleEvent(event)` for each widget
- Ignores return values
- Used for system-wide notifications (e.g., `"Close"`, `"FlushSettings"`)

Sources: [frontend/ui/uimanager.lua50-119](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L50-L119)[frontend/ui/uimanager.lua1025-1063](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L1025-L1063)

---

## Task Scheduling

UIManager provides a priority queue-based task scheduler for asynchronous execution. Tasks are stored in `_task_queue` and sorted by execution time.

### Task Queue Structure

```
Task Queue (_task_queue)

next to execute

pops tasks where
time <= now

Task 1
time=now+0.5s
action=callback1

Task 2
time=now+1.0s
action=callback2

Task 3
time=now+2.5s
action=callback3

Task N
time=now+10.0s
action=callbackN

_checkTasks()
```

### Scheduling Methods

#### `UIManager:schedule(time, action, ...)`

Schedules a task for execution at an absolute time ([frontend/ui/uimanager.lua295-319](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L295-L319)):

```
-- time is absolute timestamp (from time.now())
-- action is a function reference
-- ... are arguments to pass to action
local when = time.now() + time.s(5)
UIManager:schedule(when, function(arg) print(arg) end, "Hello")
```

**Implementation:** Binary search insertion into `_task_queue` to maintain sort order (descending by time).

#### `UIManager:scheduleIn(seconds, action, ...)`

Schedules a task to run after a delay ([frontend/ui/uimanager.lua335-340](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L335-L340)):

```
-- More convenient than schedule() for relative delays
UIManager:scheduleIn(2.5, function() print("2.5 seconds elapsed") end)
```

#### `UIManager:nextTick(action, ...)`

Schedules a task for the next event loop iteration ([frontend/ui/uimanager.lua353-355](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L353-L355)):

```
-- Executes ASAP, but after current event processing completes
UIManager:nextTick(function() self:updateDisplay() end)
```

Equivalent to `scheduleIn(0, action, ...)`.

#### `UIManager:tickAfterNext(action, ...)`

Schedules a task two event loop iterations from now ([frontend/ui/uimanager.lua369-378](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L369-L378)):

```
-- Useful to ensure UI callbacks execute after repaints
UIManager:tickAfterNext(function() self:showDialog() end)
```

Returns a reference to the wrapper function for early unscheduling.

#### `UIManager:unschedule(action)`

Removes all tasks matching the given action reference ([frontend/ui/uimanager.lua440-449](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L440-L449)):

```
local action = function() print("Hello") end
UIManager:scheduleIn(5, action)
-- ... later
UIManager:unschedule(action)  -- Cancels the scheduled task
```

### Task Execution

```

```

`_checkTasks()` implementation ([frontend/ui/uimanager.lua1137-1198](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L1137-L1198)):

1. Pops tasks from the queue while `task.time <= now`
2. Executes `action(unpack(args))`
3. Handles errors with protected call and logging
4. Returns earliest next task time for input polling optimization

Sources: [frontend/ui/uimanager.lua295-451](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L295-L451)[frontend/ui/uimanager.lua1137-1198](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L1137-L1198)

---

## Screen Refresh Pipeline

UIManager coordinates screen updates through a multi-stage pipeline that marks widgets dirty, repaints them to the framebuffer, and executes optimized refresh ioctls for e-ink displays.

### Refresh Types

KOReader uses different refresh modes optimized for e-ink screens:
TypeUse CaseFlashingQualityCounting`full`Large images, high fidelityYesHighestNo`partial`Text on white backgroundPromoted to flash after N refreshesMediumYes`ui`Mixed UI contentNoMediumNo`fast`Highlighting, inversionsNoLowNo`a2`Keyboard, animationsNoLowestNo`flashui`First show / close UIYesMediumNo`flashpartial`Close UI over textYesMediumNo
**Refresh Counting:**`partial` refreshes increment `refresh_count`. When it reaches `FULL_REFRESH_COUNT` (default 6), the next `partial` is promoted to `full` to clear ghosting ([frontend/ui/uimanager.lua17-24](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L17-L24)).

### The `setDirty()` Method

`UIManager:setDirty(widget, refreshtype, refreshregion, refreshdither)` is the primary interface for requesting repaints and refreshes ([frontend/ui/uimanager.lua581-671](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L581-L671)).

```
setDirty(widget, 'ui')

'all'

window-level widget

nil

invalid

function

value

Application Code

UIManager:setDirty()

Widget type?

Mark all widgets dirty

Mark widget dirty

Skip widget marking

_dirty[widget] = true

Refresh type?

Add to _refresh_func_stack

_refresh(type, region, dither)

Done
(will execute after paintTo)

_refresh_stack
```

**Special Behaviors:**

- **Translucent Widgets** ([frontend/ui/uimanager.lua603-629](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L603-L629)): If a widget has `alpha < 1`, all widgets below it (up to the first fullscreen widget) are also marked dirty to ensure correct blending
- **Dithering Propagation** ([frontend/ui/uimanager.lua590-596](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L590-L596)[frontend/ui/uimanager.lua630-633](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L630-L633)): If any dirty widget has `dithered == true`, the refresh request is marked for dithering
- **Deferred Refresh** ([frontend/ui/uimanager.lua651-659](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L651-L659)): If `refreshtype` is a function, it's queued in `_refresh_func_stack` and executed after `paintTo()` when the widget's dimensions are known

### The Repaint Cycle

```

```

`_repaint()` implementation ([frontend/ui/uimanager.lua1065-1127](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L1065-L1127)):

1. **Skip if no dirty widgets and no refresh functions**
2. **Paint dirty widgets** (bottom-to-top in stack order):

- Only paint widgets up to the first fullscreen widget (optimization)
- Call `widget:paintTo(framebuffer, x, y)`
- Honor dithering hints from widgets
3. **Execute deferred refresh functions** (from `_refresh_func_stack`):

- Call each function to get `(refreshtype, refreshregion, refreshdither)`
- Enqueue the returned refresh via `_refresh()`
4. **Clear state**: Reset `_dirty` and `_refresh_func_stack`

### Refresh Optimization

`_refresh()` merges and optimizes queued refreshes before sending ioctls to the display driver ([frontend/ui/uimanager.lua1200-1390](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L1200-L1390)).

**Key Optimizations:**

1. **Region Merging**: Combines overlapping or adjacent refresh regions
2. **Waveform Mode Promotion**: Upgrades refresh types when necessary (e.g., `partial` → `full` for ghosting prevention)
3. **Dithering Detection**: Applies hardware dithering for image content
4. **Partial Refresh Counting**: Tracks consecutive `partial` refreshes and promotes to `full` at threshold

```
_refresh_stack
(multiple queued refreshes)

Merge Regions
(combine overlapping)

Promote Types
(partial counting)

Apply Dithering
(if hint present)

Screen:refreshFull/Partial/UI()

Framebuffer ioctl
```

Sources: [frontend/ui/uimanager.lua581-671](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L581-L671)[frontend/ui/uimanager.lua1065-1127](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L1065-L1127)[frontend/ui/uimanager.lua1200-1390](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L1200-L1390)

---

## Main Event Loop

The `UIManager:run()` method implements the main event loop that drives the entire application. It never returns until the application exits.

### Event Loop Flow

```
Yes

No

Yes

No

Yes

No

UIManager:run()

Initialize loop state

Main Loop Iteration

_exit_code set?

Cleanup and exit

Check for input events

Input:waitEvent(timeout)

Input received?

handleInput(input_event)

_checkTasks()

setDirty called?

_repaint()

_refresh()

Return exit_code
```

### Core Loop Implementation

The main loop ([frontend/ui/uimanager.lua1481-1603](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L1481-L1603)) follows this pattern:

1. **Poll for input** with timeout based on next scheduled task:

```
local wait_until = self:_checkTasks()  -- Returns next task time
local input_timeout = wait_until and math.huge or -1
local input_event = Input:waitEvent(input_timeout)
```
2. **Handle input** if received:

```
if input_event then
    self:handleInput(input_event)
end
```
3. **Repaint and refresh** if needed:

```
if self._task_queue_dirty then
    self:_repaint()
end
self:_refresh()
```
4. **Check and execute scheduled tasks**:

```
wait_until = self:_checkTasks()
```
5. **Check for quit signal** and loop

### Input Handling

```

```

`handleInput()` implementation ([frontend/ui/uimanager.lua1392-1479](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L1392-L1479)):

1. **Translate event code to name** using `Input.event_map`
2. **Check for fake events** (suspend, USB, etc.) and call `event_handlers[event_name]`
3. **Feed to GestureDetector** for touch events
4. **Apply rotation mapping** for key events
5. **Dispatch via `sendEvent()`** to widget stack

Sources: [frontend/ui/uimanager.lua1392-1603](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L1392-L1603)

---

## Integration with Device and Input

UIManager bridges the platform abstraction layer (Device, Input) with the application layer (widgets).

### Initialization Sequence

```
Input
UIManager
Device
reader.lua
Input
UIManager
Device
reader.lua
Device registers UIManager reference
Device-specific handlers registered
Main event loop starts
Device:init()
Input:new()
Device:_UIManagerReady(self)
UIManager:init()
Initialize event_handlers
Setup poweroff_action, reboot_action
Device:_UIManagerReady(self)
Device:setEventHandlers(UIManager)
UIManager:run()
```

### Device Integration Points

Devices call `UIManager` methods to notify of system events:
Device MethodUIManager Interaction`Device:onPowerEvent(ev)`Calls `UIManager:rescheduleSuspend()`, `UIManager:unschedule()`, broadcasts `"PowerOff"``Device:setEventHandlers()`Registers handlers in `UIManager.event_handlers` (e.g., `Suspend`, `Resume`, `PowerPress`)`Device:_UIManagerReady()`Stores UIManager reference for future callbacks
Examples:

- **Kobo** ([frontend/device/kobo/device.lua1480-1675](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L1480-L1675)): Registers sleep cover, power button, and USB handlers
- **Kindle** ([frontend/device/kindle/device.lua1177-1246](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kindle/device.lua#L1177-L1246)): Sets up Oasis accelerometer and power button handlers
- **Android** ([frontend/device/android/device.lua138-309](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua#L138-L309)): Handles app lifecycle events (pause, resume, configuration changes)

### Input Integration

The `Input` object is owned by `Device` but feeds events into UIManager's main loop:

```
raw events

waitEvent()

owns

feedEvent()

gestures

Hardware
(evdev, SDL, etc)

Input
(device.input)

GestureDetector
(input.gesture_detector)

UIManager
(singleton)
```

`UIManager` references `Input` indirectly via `Device.input`:

- `Input = Device.input` (set in [frontend/ui/uimanager.lua14](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L14-L14))
- Main loop calls `Input:waitEvent()` ([frontend/ui/uimanager.lua1512-1530](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L1512-L1530))
- Gesture detection via `Input.gesture_detector` ([frontend/ui/uimanager.lua1411-1424](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L1411-L1424))

Sources: [frontend/ui/uimanager.lua14](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L14-L14)[frontend/ui/uimanager.lua50-119](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L50-L119)[frontend/device/kobo/device.lua1480-1675](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L1480-L1675)[frontend/device/kindle/device.lua1177-1246](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kindle/device.lua#L1177-L1246)[frontend/device/android/device.lua138-309](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua#L138-L309)

---

## Key Methods Reference

### Widget Lifecycle
MethodDescription`UIManager:show(widget, ...)`Register and display a widget`UIManager:close(widget, ...)`Unregister a widget and refresh underlying widgets`UIManager:setDirty(widget, type, region, dither)`Mark widget for repaint and enqueue refresh
### Task Scheduling
MethodDescription`UIManager:schedule(time, action, ...)`Schedule task at absolute time`UIManager:scheduleIn(seconds, action, ...)`Schedule task after delay`UIManager:nextTick(action, ...)`Schedule task for next loop iteration`UIManager:tickAfterNext(action, ...)`Schedule task two iterations from now`UIManager:unschedule(action)`Cancel scheduled task`UIManager:shiftScheduledTasksBy(shift_time)`Adjust all task times (for suspend/resume)
### Event Dispatch
MethodDescription`UIManager:sendEvent(event)`Send event to widgets (top-to-bottom, stops when consumed)`UIManager:broadcastEvent(event)`Send event to all widgets`UIManager:handleInput(input_event)`Process raw input event
### Main Loop Control
MethodDescription`UIManager:run()`Enter main event loop (never returns)`UIManager:quit(exit_code)`Signal application exit`UIManager:setRunForeverMode()`Configure loop for testing (never exit)
### Screen Management
MethodDescription`UIManager:setRefreshRate(rate, night_rate)`Configure partial-to-full refresh promotion threshold`UIManager:getRefreshRate()`Get current refresh rate settings`UIManager:ToggleNightMode(night_mode)`Switch between day/night refresh rates`UIManager:forceRePaint()`Execute repaint and refresh immediately`UIManager:waitForVSync()`Block until screen refresh completes (debug)
Sources: [frontend/ui/uimanager.lua156-282](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L156-L282)[frontend/ui/uimanager.lua295-451](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L295-L451)[frontend/ui/uimanager.lua581-671](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L581-L671)[frontend/ui/uimanager.lua1025-1063](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L1025-L1063)[frontend/ui/uimanager.lua1481-1603](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L1481-L1603)

---

# Device-Abstraction-Layer

# Device Abstraction Layer
Relevant source files
- [frontend/device/android/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua)
- [frontend/device/cervantes/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/cervantes/device.lua)
- [frontend/device/generic/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua)
- [frontend/device/input.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/input.lua)
- [frontend/device/kindle/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kindle/device.lua)
- [frontend/device/kobo/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua)
- [frontend/device/pocketbook/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/pocketbook/device.lua)
- [frontend/device/sdl/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/sdl/device.lua)
- [frontend/device/sony-prstux/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/sony-prstux/device.lua)
- [frontend/ui/uimanager.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua)
- [platform/android/llapp_main.lua](https://github.com/koreader/koreader/blob/9e6b1587/platform/android/llapp_main.lua)
- [spec/unit/device_spec.lua](https://github.com/koreader/koreader/blob/9e6b1587/spec/unit/device_spec.lua)

The Device Abstraction Layer provides a unified interface for hardware interactions across KOReader's 15+ supported platforms (Kobo, Kindle, Android, PocketBook, Cervantes, etc.). This layer encapsulates platform-specific differences through capability flags, polymorphic implementations, and standardized hardware component interfaces.

**Related Pages**: For information about input event processing, see [Input Handling and Gesture Detection](/koreader/koreader/3.4-input-handling-and-gesture-detection). For power management specifics, capability flags are defined here but the power management implementation is covered in power device modules.

## Architecture Overview

The Device layer uses class inheritance to share common functionality while allowing platform-specific customization. All device implementations extend from the `Generic` base class.

```
Hardware Components

Platform Implementations

Base Abstraction

Generic Device
(device/generic/device.lua)

Kobo
(device/kobo/device.lua)

Kindle
(device/kindle/device.lua)

Android
(device/android/device.lua)

PocketBook
(device/pocketbook/device.lua)

Cervantes
(device/cervantes/device.lua)

SDL/Desktop
(device/sdl/device.lua)

Sony PRSTUX
(device/sony-prstux/device.lua)

Screen
(framebuffer)

Input
(device/input.lua)

PowerD
(power management)
```

**Sources**: [frontend/device/generic/device.lua1-151](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua#L1-L151)[frontend/device/kobo/device.lua93-160](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L93-L160)[frontend/device/kindle/device.lua387-422](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kindle/device.lua#L387-L422)

## The Generic Device Base Class

The `Generic` device class in `device/generic/device.lua` defines the common interface and default implementations for all platforms.

### Capability Flag System

Device capabilities are declared as boolean functions (returning `yes` or `no` function references). This allows runtime capability checks throughout the codebase:
Capability FlagPurposeDefault`hasBattery`Device has a batteryyes`hasKeyboard` / `hasKeys`Physical keys availableno`isTouchDevice`Touch input availableno`hasFrontlight`Frontlight hardware presentno`hasNaturalLight`Color temperature controlno`hasEinkScreen`E-ink display (affects refresh strategy)yes`hasColorScreen`Color display capabilityno`canHWInvert`Hardware-level color inversionno`canHWDither`Hardware dithering supportno`canSuspend` / `canStandby`Power state supportno`canReboot` / `canPowerOff`System controlno`hasWifiToggle`Wi-Fi control availableyes`hasOTAUpdates`OTA update supportno
**Sources**: [frontend/device/generic/device.lua40-144](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua#L40-L144)

### Platform Identification Functions

Boolean functions identify the current platform (all default to `no`):

```
isAndroid, isCervantes, isKindle, isKobo, isPocketBook, 
isRemarkable, isSonyPRSTUX, isSDL, isEmulator, isDesktop
```

**Example usage** from UIManager initialization:

[frontend/ui/uimanager.lua87-90](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L87-L90) - Kobo-specific exit code handling:

```
if Device:isKobo() then
    self:quit(88)
else
    self:quit()
end
```

**Sources**: [frontend/device/generic/device.lua102-112](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua#L102-L112)

### Hardware Component Interfaces

The Generic class defines interfaces to three core hardware components:

```
Device Instance

device.screen
(Framebuffer)

device.input
(Input)

device.powerd
(PowerD)

Platform-specific
Framebuffer implementation

Event device
handlers

Platform-specific
Power management
```

**Sources**: [frontend/device/generic/device.lua27-39](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua#L27-L39)

## Platform-Specific Implementations

### Extension Pattern

Platform implementations extend `Generic` using the `:extend()` method:

[frontend/device/kobo/device.lua93-160](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L93-L160) - Kobo base class:

```
local Kobo = Generic:extend{
    model = "Kobo",
    ota_model = "kobo",
    isKobo = yes,
    isTouchDevice = yes,
    hasOTAUpdates = yes,
    hasFastWifiStatusQuery = yes,
    hasWifiManager = yes,
    hasWifiRestore = yes,
    canStandby = no, -- updated by checkStandby()
    canReboot = yes,
    canPowerOff = yes,
    -- ... device-specific fields
}
```

Individual Kobo models further extend the `Kobo` base class with model-specific overrides:

[frontend/device/kobo/device.lua192-203](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L192-L203) - Kobo Aura One:

```
local KoboDaylight = Kobo:extend{
    model = "Kobo_daylight",
    hasFrontlight = yes,
    touch_phoenix_protocol = true,
    display_dpi = 300,
    hasNaturalLight = yes,
    frontlight_settings = {
        frontlight_white = "/sys/class/backlight/lm3630a_led1b",
        frontlight_red = "/sys/class/backlight/lm3630a_led1a",
        frontlight_green = "/sys/class/backlight/lm3630a_ledb",
    },
}
```

**Sources**: [frontend/device/kobo/device.lua93-598](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L93-L598)[frontend/device/kindle/device.lua387-422](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kindle/device.lua#L387-L422)

### Platform-Specific Initialization

The `:init()` method handles platform-specific hardware setup:

**Kobo Initialization**[frontend/device/kobo/device.lua677-897](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L677-L897):

1. Checks for MXCFB wait reliability settings
2. Initializes framebuffer (mxcfb or sunxi backend)
3. Detects hardware capabilities (automagic sysfs discovery)
4. Sets up power management device
5. Configures input devices with event mappings
6. Initializes touch event handlers

**Kindle Initialization**[frontend/device/kindle/device.lua632-876](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kindle/device.lua#L632-L876):

1. Detects deep sleep/hibernation support via appreg database
2. Instantiates device-specific Input configuration
3. Determines OTA model based on CPU architecture
4. Sets up framework integration (when STOP_FRAMEWORK is not set)

**Android Initialization**[frontend/device/android/device.lua138-309](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua#L138-L309):

1. Creates Android-specific framebuffer
2. Sets up event handlers for app lifecycle (APP_CMD_*)
3. Configures keyboard/touchscreen detection
4. Handles screen timeout and fullscreen settings

**Sources**: [frontend/device/kobo/device.lua677-897](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L677-L897)[frontend/device/kindle/device.lua632-876](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kindle/device.lua#L632-L876)[frontend/device/android/device.lua138-309](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua#L138-L309)

### Platform Detection and Model Differentiation

Platform implementations include device detection logic to return the correct model-specific class.

**Kobo Detection** uses environment variables:

[frontend/device/kobo/device.lua2318-2421](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L2318-L2421) - Product detection:

```
local product_id = getProductId()
if product_id == 0 then
    product_id = getProductIdFallback()
end
 
if product_id == 00 then
    return KoboTrilogyA
elseif product_id == 41 then
    return KoboDragon  -- Aura HD
elseif product_id == 374 then
    return KoboDaylight  -- Aura One
-- ... etc
```

**Kindle Detection** reads `/proc/usid`:

[frontend/device/kindle/device.lua2062-2253](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kindle/device.lua#L2062-L2253) - Model string matching:

```
local GetUSID = function()
    local handle = io.popen("cat /proc/usid 2>/dev/null", "r")
    if handle then
        local usid = handle:read("*line")
        handle:close()
        return usid
    end
end
 
local kindle_sn = GetUSID()
-- Pattern matching like "B013" → KindleVoyage
```

**Sources**: [frontend/device/kobo/device.lua2318-2421](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L2318-L2421)[frontend/device/kindle/device.lua2062-2253](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kindle/device.lua#L2062-L2253)

## Screen and Framebuffer Integration

Each platform initializes a platform-specific framebuffer implementation:

```
Kobo

Kobo Sunxi

Kindle

Android

SDL

PocketBook

Device:init()

require('ffi/framebuffer_mxcfb')

require('ffi/framebuffer_sunxi')

require('ffi/framebuffer_mxcfb')

require('ffi/framebuffer_android')

require('ffi/framebuffer_SDL3')

require('ffi/framebuffer_pocketbook')

device.screen
(Framebuffer object)

Methods:
getRawSize(), getWidth(),
getHeight(), getDPI(),
setRotationMode(),
refreshFull(), etc.
```

**Kobo Framebuffer Setup**[frontend/device/kobo/device.lua686-710](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L686-L710):

```
if self:isSunxi() then
    self.screen = require("ffi/framebuffer_sunxi"):new{
        device = self,
        mxcfb_bypass_wait_for = mxcfb_bypass_wait_for,
        boot_rota = self.boot_rota,
    }
    self.canHWInvert = no  -- Sunxi doesn't support HW inversion
else
    self.screen = require("ffi/framebuffer_mxcfb"):new{
        device = self,
        mxcfb_bypass_wait_for = mxcfb_bypass_wait_for,
        no_cfa_post_processing = G_reader_settings:isTrue("no_cfa_post_processing"),
    }
end
```

**Generic Screen Setup**[frontend/device/generic/device.lua198-286](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua#L198-L286):

- Wraps framebuffer methods to return Geom objects
- Applies DPI overrides from settings
- Sets up viewport if defined
- Configures night mode and dithering

**Sources**: [frontend/device/kobo/device.lua686-710](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L686-L710)[frontend/device/generic/device.lua198-286](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua#L198-L286)

## Input System Integration

The Device layer instantiates and configures the `Input` class with platform-specific event mappings.

### Input Device Instantiation

**Kobo Input Setup**[frontend/device/kobo/device.lua819-867](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L819-L867):

```
self.input = require("device/input"):new{
    device = self,
    event_map = {
        [35] = "SleepCover",  -- KEY_H, Elipsa
        [59] = "SleepCover",
        [90] = "LightButton",
        [102] = "Home",
        [116] = "Power",
        [193] = "RPgBack",
        [194] = "RPgFwd",
        [331] = "Eraser",
        [332] = "Highlighter",
    },
    event_map_adapter = { -- Transform raw events
        SleepCover = function(ev)
            if self.input:isEvKeyPress(ev) then
                return "SleepCoverClosed"
            elseif self.input:isEvKeyRelease(ev) then
                return "SleepCoverOpened"
            end
        end,
    },
    main_finger_slot = self.main_finger_slot or 0,
    pressure_event = self.pressure_event,
}
```

### Touch Event Adjustments

Platform devices register event adjustment hooks to handle coordinate transformations:

[frontend/device/kobo/device.lua859-862](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L859-L862) - Sets up touch event handlers:

```
-- Input handling on Kobo is a thing of nightmares
self:setTouchEventHandler()
-- Handle extra shenanigans if necessary
self:initEventAdjustHooks()
```

**Cervantes Touch Adjustments**[frontend/device/cervantes/device.lua97-108](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/cervantes/device.lua#L97-L108):

```
function Cervantes:initEventAdjustHooks()
    if self.touch_switch_xy and self.touch_mirrored_x then
        self.input:registerEventAdjustHook(
            self.input.adjustTouchSwitchAxesAndMirrorX,
            (self.screen:getWidth() - 1)
        )
    end
    if self.touch_legacy then
        self.input.handleTouchEv = self.input.handleTouchEvLegacy
    end
end
```

**Sources**: [frontend/device/kobo/device.lua819-867](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L819-L867)[frontend/device/cervantes/device.lua97-130](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/cervantes/device.lua#L97-L130)

## Power Management Integration

Each platform instantiates a platform-specific PowerD implementation:

```
Kobo

Kindle

Generic

Device:init()

require('device/kobo/powerd')

require('device/kindle/powerd')

require('device/generic/powerd')

device.powerd

Common Interface:
getCapacity(),
isCharging(),
beforeSuspend(),
afterResume(),
setIntensity() (frontlight)
```

**Kobo PowerD Instantiation**[frontend/device/kobo/device.lua819-823](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L819-L823):

```
self.powerd = require("device/kobo/powerd"):new{
    device = self,
    battery_sysfs = self.battery_sysfs,
    aux_battery_sysfs = self.aux_battery_sysfs,
}
```

**Generic PowerD Fallback**[frontend/device/generic/device.lua238-240](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua#L238-L240):

```
if not self.powerd then
    self.powerd = require("device/generic/powerd"):new{device = self}
end
```

**Sources**: [frontend/device/kobo/device.lua819-823](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L819-L823)[frontend/device/generic/device.lua238-240](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua#L238-L240)

## Network Management

Platform implementations customize the NetworkMgr module to handle Wi-Fi control.

**Kobo Network Manager**[frontend/device/kobo/device.lua1990-2056](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L1990-L2056):

```
function Kobo:initNetworkManager(NetworkMgr)
    function NetworkMgr:turnOnWifi(complete_callback)
        koboEnableWifi(true)
        return self:reconnectOrShowNetworkMenu(complete_callback)
    end
    
    function NetworkMgr:turnOffWifi(complete_callback)
        koboEnableWifi(false)
        if complete_callback then
            complete_callback()
        end
    end
    
    function NetworkMgr:getNetworkInterfaceName()
        return "wlan0"
    end
    
    NetworkMgr:setWirelessBackend("wpa_supplicant", {
        ctrl_interface = "/var/run/wpa_supplicant/wlan0"
    })
end
```

**Kindle Network Manager**[frontend/device/kindle/device.lua424-525](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kindle/device.lua#L424-L525):

- Uses lipc (Lab126 IPC) to communicate with Amazon's wifid service
- Calls `kindleGetScanList()` to retrieve available networks
- Implements `authenticateNetwork()` via lipc commands

**Sources**: [frontend/device/kobo/device.lua1990-2056](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L1990-L2056)[frontend/device/kindle/device.lua424-525](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kindle/device.lua#L424-L525)

## Device Lifecycle and UIManager Integration

### Initialization Sequence

The device initialization happens early in the application startup:

[frontend/ui/uimanager.lua50-119](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L50-L119) - UIManager initialization:

```
function UIManager:init()
    self.event_handlers = {
        __default__ = function(input_event)
            self:sendEvent(input_event)
        end,
        SaveState = function()
            self:flushSettings()
        end,
        Power = function(input_event)
            Device:onPowerEvent(input_event)
        end,
        -- ... more handlers
    }
    
    -- Tell Device that UIManager is now available
    Device:_UIManagerReady(self)
end
```

### UIManager Ready Hook

Devices can perform actions once UIManager is initialized:

**Generic Implementation**[frontend/device/generic/device.lua1174-1178](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua#L1174-L1178):

```
function Device:_UIManagerReady(uimgr)
    -- Let implementations know that UIManager is ready if needed
    if self.UIManagerReady then
        self:UIManagerReady(uimgr)
    end
    -- Set up event handlers if needed
    if self.setEventHandlers then
        self:setEventHandlers(uimgr)
    end
end
```

**Android UIManager Ready**[frontend/device/android/device.lua311-313](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua#L311-L313):

```
function Device:UIManagerReady(uimgr)
    UIManager = uimgr
end
```

**Sources**: [frontend/ui/uimanager.lua50-119](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L50-L119)[frontend/device/generic/device.lua1174-1178](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua#L1174-L1178)

### Event Handlers

Platform devices register custom event handlers in UIManager:

**Kobo Event Handlers**[frontend/device/kobo/device.lua1805-1988](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L1805-L1988):

- `SleepCoverClosed` / `SleepCoverOpened` - Sleep cover state changes
- `Light` - Frontlight toggle
- `Charging` / `NotCharging` - Power connection events
- `UsbPlugIn` / `UsbPlugOut` - USB connection events
- `Suspend` / `Resume` - Power state changes

**Cervantes Event Handlers**[frontend/device/cervantes/device.lua206-279](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/cervantes/device.lua#L206-L279):

- `PowerPress` / `PowerRelease` - Power button events
- `Suspend` / `Resume` - Suspend/resume lifecycle
- `Charging` / `NotCharging` - Charging state
- `UsbPlugIn` / `UsbPlugOut` - USB and mass storage

**Sources**: [frontend/device/kobo/device.lua1805-1988](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L1805-L1988)[frontend/device/cervantes/device.lua206-279](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/cervantes/device.lua#L206-L279)

## Suspend and Resume Handling

The Device layer provides hooks for platform-specific suspend/resume behavior.

### Generic Suspend/Resume Pattern

[frontend/device/generic/device.lua350-430](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua#L350-L430) - Generic power event handler:

```
function Device:onPowerEvent(ev)
    if self.screen_saver_mode then
        if ev == "Power" or ev == "Resume" then
            -- Resume from screensaver
            UIManager:unschedule(self.suspend)
            self:resume()
            -- Show what was behind screensaver
            Screensaver:close()
            self.powerd:afterResume()
        elseif ev == "Suspend" then
            -- Already in screensaver, go back to suspend
            self:rescheduleSuspend()
        end
    elseif ev == "Power" or ev == "Suspend" then
        -- Not in screensaver, enter suspend
        Screensaver:setup()
        Screensaver:show()
        -- Turn off Wi-Fi if needed
        -- Turn off frontlight
        self.powerd:beforeSuspend()
        self:rescheduleSuspend()
    end
end
```

### Platform-Specific Suspend Implementations

**Kobo Suspend**[frontend/device/kobo/device.lua1601-1680](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L1601-L1680):

- Writes to `/sys/power/state-extended` for rtc_wakeup or `/sys/power/state` for mem
- Handles Wi-Fi state preservation
- Manages unexpected wakeups

**Cervantes Suspend**[frontend/device/cervantes/device.lua186-197](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/cervantes/device.lua#L186-L197):

- Executes `./suspend.sh` shell script
- Corresponding `./resume.sh` on resume

**Android Suspend**[frontend/device/android/device.lua177-221](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua#L177-L221):

- Responds to Android app lifecycle events (APP_CMD_PAUSE, APP_CMD_RESUME)
- Does not actually suspend the device (Android OS manages power)

**Sources**: [frontend/device/generic/device.lua350-430](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua#L350-L430)[frontend/device/kobo/device.lua1601-1680](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L1601-L1680)[frontend/device/cervantes/device.lua186-197](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/cervantes/device.lua#L186-L197)

## Platform-Specific Quirks and Workarounds

### Kobo Hardware Variations

Kobo devices have numerous hardware revisions requiring automagic detection:

[frontend/device/kobo/device.lua733-792](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L733-L792) - Automagic sysfs discovery:

```
if self.automagic_sysfs then
    -- Battery path varies by generation
    if util.pathExists("/sys/class/power_supply/battery") then
        self.battery_sysfs = "/sys/class/power_supply/battery"
    elseif util.fileExists("/sys/class/power_supply/bd71827_bat") then
        self.battery_sysfs = "/sys/class/power_supply/bd71827_bat"
    else
        self.battery_sysfs = "/sys/class/power_supply/mc13892_bat"
    end
    
    -- Frontlight controller varies
    if self:hasNaturalLight() then
        if util.fileExists("/sys/class/leds/aw99703-bl_FL1/color") then
            self.frontlight_settings.frontlight_mixer = 
                "/sys/class/leds/aw99703-bl_FL1/color"
        elseif util.fileExists("/sys/class/backlight/lm3630a_led/color") then
            self.frontlight_settings.frontlight_mixer = 
                "/sys/class/backlight/lm3630a_led/color"
        -- ... more variants
        end
    end
end
```

### Kindle Special Offers Detection

[frontend/device/kindle/device.lua329-364](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kindle/device.lua#L329-L364):

```
local function isSpecialOffers()
    -- Check if ad-supported screensavers are loaded
    local haslipc, lipc = pcall(require, "liblipclua")
    if not haslipc then return true end
    
    local lipc_handle = lipc.init("com.github.koreader.device")
    local loaded_blanket_modules = 
        lipc_handle:get_string_property("com.lab126.blanket", "load")
    
    if string.find(loaded_blanket_modules, "ad_screensaver") then
        return true  -- Device shows ads
    else
        return false
    end
end
```

### PocketBook Inkview Integration

[frontend/device/pocketbook/device.lua110-266](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/pocketbook/device.lua#L110-L266):

- Can use raw input (direct evdev) for lower latency when root access available
- Falls back to InkView API when permissions insufficient
- Touch rotation handled differently depending on mode

**Sources**: [frontend/device/kobo/device.lua733-792](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L733-L792)[frontend/device/kindle/device.lua329-364](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kindle/device.lua#L329-L364)[frontend/device/pocketbook/device.lua110-266](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/pocketbook/device.lua#L110-L266)

## Device Capability Query Methods

Common methods for runtime capability checks:
MethodPurpose`Device:hasBattery()`Returns true if device has a battery`Device:hasKeyboard()`Physical keyboard present`Device:isTouchDevice()`Touch input available`Device:hasWifiToggle()`Can control Wi-Fi state`Device:canSuspend()`Suspend to RAM supported`Device:canReboot()`Can reboot device`Device:supportsScreensaver()`Screensaver mode available`Device:hasGSensor()`Gyroscope/accelerometer present`Device:hasNaturalLight()`Color temperature frontlight
These are called throughout the codebase to conditionally enable features:

[frontend/ui/uimanager.lua386-392](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L386-L392) - Checking Wi-Fi capability:

```
if self:hasWifiToggle() then
    local network_manager = require("ui/network/manager")
    if network_manager:isWifiOn() then
        network_manager:disableWifi()
    end
end
```

**Sources**: [frontend/device/generic/device.lua40-144](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua#L40-L144)[frontend/ui/uimanager.lua386-424](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L386-L424)

---

# Input-Handling-and-Gesture-Detection

# Input Handling and Gesture Detection
Relevant source files
- [frontend/device/android/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua)
- [frontend/device/cervantes/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/cervantes/device.lua)
- [frontend/device/devicelistener.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/devicelistener.lua)
- [frontend/device/generic/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua)
- [frontend/device/gesturedetector.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/gesturedetector.lua)
- [frontend/device/input.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/input.lua)
- [frontend/device/kindle/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kindle/device.lua)
- [frontend/device/kobo/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua)
- [frontend/device/pocketbook/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/pocketbook/device.lua)
- [frontend/device/sdl/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/sdl/device.lua)
- [frontend/device/sony-prstux/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/sony-prstux/device.lua)
- [frontend/dispatcher.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/dispatcher.lua)
- [frontend/ui/uimanager.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua)
- [platform/android/llapp_main.lua](https://github.com/koreader/koreader/blob/9e6b1587/platform/android/llapp_main.lua)
- [plugins/gestures.koplugin/main.lua](https://github.com/koreader/koreader/blob/9e6b1587/plugins/gestures.koplugin/main.lua)
- [plugins/hotkeys.koplugin/main.lua](https://github.com/koreader/koreader/blob/9e6b1587/plugins/hotkeys.koplugin/main.lua)
- [plugins/profiles.koplugin/main.lua](https://github.com/koreader/koreader/blob/9e6b1587/plugins/profiles.koplugin/main.lua)
- [spec/unit/device_spec.lua](https://github.com/koreader/koreader/blob/9e6b1587/spec/unit/device_spec.lua)

## Purpose and Scope

This document describes KOReader's input handling pipeline, which processes raw hardware events (key presses, touch contacts, stylus input) and transforms them into high-level gesture events that drive the user interface. The pipeline consists of device-specific event capture, coordinate transformation, gesture recognition via state machine, and event dispatch to the widget system.

For information about how UIManager processes events after gesture detection, see [UI Manager and Event Loop](/koreader/koreader/3.2-ui-manager-and-event-loop). For configurable action binding to gestures, see [Action Dispatcher and Profiles](/koreader/koreader/3.5-action-dispatcher-and-profiles).

---

## Architecture Overview

The input pipeline transforms hardware events into application events through four layers:

```

```

**Data Flow:**

1. **Hardware → Device:** Raw Linux input events (`input_event` structs) from `/dev/input/event*`
2. **Device → Input:** File descriptor management, basic event filtering
3. **Input → GestureDetector:** Touch events (`tev` tables with `x`, `y`, `slot`, `id`, `timev`)
4. **GestureDetector → Input:** Recognized gesture events (e.g., `"Swipe"`, `"TwoFingerPan"`)
5. **Input → UIManager:** Application events wrapped in `Event` objects
6. **UIManager → Widgets:** Events propagated through widget hierarchy

Sources: [frontend/device/input.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/input.lua)[frontend/device/gesturedetector.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/gesturedetector.lua)[frontend/ui/uimanager.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua) Diagram 2 from high-level architecture

---

## Input Class

The `Input` class ([frontend/device/input.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/input.lua)) is the core of the input handling system. It manages event device file descriptors, translates raw kernel events, maintains touch state, and coordinates with `GestureDetector`.

### Key Responsibilities
ResponsibilityImplementation**Event device management**`open()`, `close()`, `opened_devices` hashmap**Event loop**`waitEvent()` blocking on multiple file descriptors**Event translation**`event_map` (scan codes → semantic names)**Post-processing**`event_map_adapter` (functions that transform events)**Coordinate transformation**`eventAdjustHook` chain for rotation/mirroring**Touch state tracking**`MTSlots`, `active_slots`, `ev_slots`**Gesture detection**Delegates to `gesture_detector`**Keyboard state**`modifiers` table (Alt, Ctrl, Shift, etc.)
### Event Maps

Each device defines an `event_map` that translates hardware scan codes to semantic event names:

```

```

Event map adapters provide dynamic translation (e.g., distinguishing press from release):

```

```

Sources: [frontend/device/input.lua107-110](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/input.lua#L107-L110)[frontend/device/kobo/device.lua825-854](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L825-L854)

### Touch State Management

The `Input` class tracks multi-touch contacts using slot-based state:

```

```

**Key fields:**

- `main_finger_slot`: Primary touch slot (usually 0, configurable per device)
- `pen_slot`: Dedicated slot for stylus input (usually `main_finger_slot + 4`)
- `cur_slot`: Current slot being processed (set by `ABS_MT_SLOT` events)
- `MTSlots`: Hash table of active slot data
- `active_slots`: Set of currently touching slot numbers

Sources: [frontend/device/input.lua180-203](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/input.lua#L180-L203)[frontend/device/input.lua241-256](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/input.lua#L241-L256)

### Event Adjustment Hooks

Device-specific coordinate transformations are registered via `registerEventAdjustHook()`:

```

```

Common adjustments:

- **`adjustTouchTranslate`**: Offset for viewport/bezel
- **`adjustTouchMirrorX/Y`**: Horizontal/vertical mirroring
- **`adjustTouchSwitchXY`**: Swap X and Y coordinates
- **`adjustTouchSwitchAxesAndMirrorX`**: Combined swap and mirror

The hooks are applied in registration order during `handleTouchEv()`.

Sources: [frontend/device/input.lua923-936](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/input.lua#L923-L936)[frontend/device/generic/device.lua244-254](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua#L244-L254)

---

## GestureDetector

The `GestureDetector` class ([frontend/device/gesturedetector.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/gesturedetector.lua)) implements a state machine that recognizes gestures from sequences of touch events.

### Supported Gestures
GestureTrigger ConditionsExample Use Case`touch`First contact downStart of any touch interaction`tap`Quick down-up, no movementSelect item, turn page`double_tap`Two taps within intervalZoom, mode toggle`hold`Contact held for durationContext menu, text selection`hold_pan`Move while holdingDrag, scroll with feedback`hold_release`Release after holdComplete hold gesture`pan`Move beyond thresholdScroll, swipe`pan_release`Release after panMomentum scrolling`swipe`Fast directional panPage turn, dismiss`two_finger_tap`Two contacts tap togetherQuick menu`two_finger_pan`Two contacts move togetherZoom/pan PDF`two_finger_swipe`Fast two-finger moveSpecial navigation`pinch`Two contacts move closerZoom out`spread`Two contacts move apartZoom in`rotate`Two contacts rotateRotate view
Sources: [frontend/device/gesturedetector.lua1-28](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/gesturedetector.lua#L1-L28)

### Configuration Parameters

```

```

**Distance thresholds** (scaled by DPI):

- `TWO_FINGER_TAP_REGION`: 20dp - maximum movement for two-finger tap
- `DOUBLE_TAP_DISTANCE`: 50dp - maximum distance between double taps
- `PAN_THRESHOLD`: 35dp - minimum movement to trigger pan
- `MULTISWIPE_THRESHOLD`: 50dp - granularity for multiswipe detection

All parameters are user-configurable via `G_reader_settings`.

Sources: [frontend/device/gesturedetector.lua59-121](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/gesturedetector.lua#L59-L121)

### State Machine

The `GestureDetector` maintains a `Contact` object for each touch slot, implementing per-contact state machines:

```

```

**Multi-touch coordination:**

- When a second contact appears, `GestureDetector` checks for buddy contacts (within ±1 slot)
- Two-finger gestures require both contacts to move in sync
- The state machine transitions for two-finger gestures (e.g., `two_finger_pan`) when both contacts exhibit the same pattern

**Clock source detection:**

- On first event, `GestureDetector` probes which `CLOCK_*` source the kernel uses for event timestamps ([frontend/device/gesturedetector.lua634-659](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/gesturedetector.lua#L634-L659))
- This ensures accurate timing calculations across different kernel versions

Sources: [frontend/device/gesturedetector.lua134-233](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/gesturedetector.lua#L134-L233)[frontend/device/gesturedetector.lua661-1036](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/gesturedetector.lua#L661-L1036)

### Gesture Recognition Example

```

```

Sources: [frontend/device/gesturedetector.lua661-1036](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/gesturedetector.lua#L661-L1036)

---

## Event Processing Pipeline

### Main Event Loop

The core event processing loop resides in `Input:waitEvent()`:

```

```

**Key points:**

- `waitForEvent()` blocks on `poll()` or `epoll_wait()` across all open input devices
- Timeout is calculated from earliest `timer_callback` in the queue
- Touch events accumulate in `MTSlots` until `SYN_REPORT`, then feed to `GestureDetector`
- Key events are immediately translated and returned
- Miscellaneous events (e.g., `MSC_GYRO` for accelerometer) are handled by device-specific hooks

Sources: [frontend/device/input.lua712-886](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/input.lua#L712-L886)

### Touch Event Handling

Touch event processing differs by device protocol:

```

```

**Protocol differences:**

- **Protocol A**: Legacy single-touch, uses `handleTouchEvLegacy()`, fixed slot
- **Protocol B**: Modern multi-touch, uses `handleTouchEv()`, explicit slot management
- **Phoenix**: Kobo-specific, lacks tracking IDs, tracks by proximity ([frontend/device/input.lua551-612](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/input.lua#L551-L612))
- **Snow**: Similar to Phoenix with different coordinate handling ([frontend/device/input.lua614-675](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/input.lua#L614-L675))
- **Trilogy Mk3**: Special translation when `ABS_PRESSURE == 0` ([frontend/device/input.lua677-710](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/input.lua#L677-L710))

Sources: [frontend/device/input.lua459-710](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/input.lua#L459-L710)

---

## Device-Specific Adaptations

Each platform implements device-specific event handling:

### Kobo Devices

```

```

**Initialization** ([frontend/device/kobo/device.lua859-862](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L859-L862)):

1. `setTouchEventHandler()`: Selects protocol handler based on model flags
2. `initEventAdjustHooks()`: Registers coordinate transformations

Sources: [frontend/device/kobo/device.lua825-1145](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua#L825-L1145)

### Kindle Devices

```

```

**Key features:**

- Automatic input device scanning via FBInk ([frontend/device/kindle/device.lua535-576](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kindle/device.lua#L535-L576))
- Frame tap gestures on supported models (e.g., PaperWhite 5)
- Page turn buttons with rotation-aware mapping
- 5-way navigation (older models)

Sources: [frontend/device/kindle/device.lua535-576](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kindle/device.lua#L535-L576)

### Android Devices

```

```

**Key features:**

- Native Android event loop via JNI
- Screen resize detection ([frontend/device/android/device.lua160-175](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua#L160-L175))
- Power events (charging, disconnected)
- Intent handling for file opening
- Clipboard integration ([frontend/device/android/device.lua235-243](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua#L235-L243))

Sources: [frontend/device/android/device.lua144-245](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua#L144-L245)

### PocketBook Devices

PocketBook can use either raw evdev or InkView library:

```

```

**InkView event handling:**

- Translates InkView events to Linux input events
- Special codes: `EVT_HIDE`, `EVT_FOREGROUND`, `EVT_EXIT` ([frontend/device/pocketbook/device.lua206-228](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/pocketbook/device.lua#L206-L228))
- Accelerometer via `QueryGSensor()`

Sources: [frontend/device/pocketbook/device.lua192-265](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/pocketbook/device.lua#L192-L265)

---

## Rotation and Button Mapping

Input events must be rotated to match screen orientation:

### Rotation Maps

```

```

**Button inversion** (user preference):

- `invertButtons()`: Swaps all page turn buttons
- `invertButtonsLeft()`: Swaps left-side buttons only
- `invertButtonsRight()`: Swaps right-side buttons only

Applied during device initialization if `input_invert_page_turn_keys` setting is true.

Sources: [frontend/device/input.lua269-277](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/input.lua#L269-L277)[frontend/device/generic/device.lua153-196](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua#L153-L196)

### Coordinate Rotation

Touch coordinates are rotated by `GestureDetector` to match screen orientation:

```

```

The rotation mode is queried from `Screen:getRotationMode()` on each gesture event.

Sources: [frontend/device/gesturedetector.lua148-175](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/gesturedetector.lua#L148-L175)

---

## Integration with UIManager

After gesture recognition, events flow to `UIManager`:

```

```

**Event routing:**

1. `Input:waitEvent()` returns an `Event` object
2. `UIManager:handleInput(event)` is called
3. If `event_handlers[event.code]` exists, run it (e.g., Power, SaveState)
4. Otherwise, iterate `_window_stack` from top to bottom
5. Call `widget:handleEvent(event)` on each widget
6. Stop if any widget returns `true` (consumed)
7. Run `_repaint()` and `_refresh()` to update screen

**Special event handlers** ([frontend/ui/uimanager.lua51-75](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L51-L75)):

- `SaveState`: Flush settings
- `Power`: Handle power button, suspend/resume
- `UsbDevicePlugIn/Out`: Broadcast USB OTG events

Sources: [frontend/ui/uimanager.lua51-75](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L51-L75)[frontend/ui/uimanager.lua1077-1252](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L1077-L1252)

---

## Configuration and Debugging

### Gesture Timing Configuration

Users can adjust gesture detection parameters via plugins:

**Gestures Plugin** ([plugins/gestures.koplugin/main.lua](https://github.com/koreader/koreader/blob/9e6b1587/plugins/gestures.koplugin/main.lua)):

- Modify `ges_tap_interval_ms`, `ges_double_tap_interval_ms`, etc.
- Configure per-gesture actions via `Dispatcher`
- Define custom multiswipes

**Settings persistence:**

```

```

Sources: [frontend/device/gesturedetector.lua97-103](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/gesturedetector.lua#L97-L103)[plugins/gestures.koplugin/main.lua](https://github.com/koreader/koreader/blob/9e6b1587/plugins/gestures.koplugin/main.lua)

### Debug Logging

Enable input debug logging to trace event flow:

```

```

**Logged information:**

- Raw event type/code/value with symbolic names ([frontend/device/input.lua32-99](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/input.lua#L32-L99))
- Touch slot changes and tracking IDs
- Gesture state transitions in `GestureDetector`
- Coordinate transformations from adjustment hooks

Sources: [frontend/device/input.lua32-99](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/input.lua#L32-L99)[frontend/device/gesturedetector.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/gesturedetector.lua)

---

## Summary

The input handling system transforms hardware events into application gestures through a four-layer pipeline:

1. **Device Layer**: Opens `/dev/input/event*`, reads `input_event` structs
2. **Input Layer**: Translates scan codes via `event_map`, tracks multi-touch state, applies coordinate transformations
3. **Gesture Layer**: Runs per-contact state machines to recognize taps, swipes, pinches, etc.
4. **UIManager Layer**: Dispatches gesture events to widget hierarchy

Key design patterns:

- **Event maps**: Declarative translation of hardware codes to semantic names
- **Adjustment hooks**: Pluggable coordinate transformation chain
- **State machines**: Per-contact gesture recognition with configurable timing
- **Slot tracking**: Robust multi-touch handling across different kernel protocols

This architecture provides a device-independent gesture API to the rest of KOReader while handling the complexity of diverse hardware platforms (Kobo, Kindle, Android, PocketBook, etc.) through platform-specific adapters.

Sources: [frontend/device/input.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/input.lua)[frontend/device/gesturedetector.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/gesturedetector.lua)[frontend/ui/uimanager.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua)[frontend/device/generic/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua)

---

# Action-Dispatcher-and-Profiles

# Action Dispatcher and Profiles
Relevant source files
- [frontend/device/devicelistener.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/devicelistener.lua)
- [frontend/device/gesturedetector.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/gesturedetector.lua)
- [frontend/dispatcher.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/dispatcher.lua)
- [plugins/gestures.koplugin/main.lua](https://github.com/koreader/koreader/blob/9e6b1587/plugins/gestures.koplugin/main.lua)
- [plugins/hotkeys.koplugin/main.lua](https://github.com/koreader/koreader/blob/9e6b1587/plugins/hotkeys.koplugin/main.lua)
- [plugins/profiles.koplugin/main.lua](https://github.com/koreader/koreader/blob/9e6b1587/plugins/profiles.koplugin/main.lua)

## Purpose and Scope

This page documents KOReader's **Action Dispatcher system** and the **Profiles, Gestures, and Hotkeys plugins** that extend it. The Dispatcher provides a unified registry of configurable actions that can be triggered through touch gestures, keyboard shortcuts, or grouped into profiles for batch execution or auto-triggering.

For information about the low-level gesture detection state machine, see [Input Handling and Gesture Detection](/koreader/koreader/3.4-input-handling-and-gesture-detection). For information about the menu system that displays dispatcher actions, see [Menu System and Navigation](/koreader/koreader/4.3-menu-system-and-navigation).

---

## System Architecture

The Dispatcher acts as a central action registry and execution engine, with three main consumers that map user inputs to actions:

```
User Input

Input Mapping Plugins

Dispatcher Core

Action Sources

Built-in Actions
(settingsList)

CreOptions
(EPUB/FB2)

KoptOptions
(PDF/DjVu)

Plugin Actions
(registerAction)

Action Registry
settingsList{}

Dispatcher:execute()

Gestures Plugin
gesture → action

Hotkeys Plugin
keyboard → action

Profiles Plugin
profile → actions[]

Touch Gestures

Keyboard Shortcuts

Menu Selections

UIManager:broadcastEvent()
```

**Sources:**[frontend/dispatcher.lua1-623](https://github.com/koreader/koreader/blob/9e6b1587/frontend/dispatcher.lua#L1-L623)[plugins/gestures.koplugin/main.lua1-290](https://github.com/koreader/koreader/blob/9e6b1587/plugins/gestures.koplugin/main.lua#L1-L290)[plugins/hotkeys.koplugin/main.lua1-82](https://github.com/koreader/koreader/blob/9e6b1587/plugins/hotkeys.koplugin/main.lua#L1-L82)[plugins/profiles.koplugin/main.lua1-82](https://github.com/koreader/koreader/blob/9e6b1587/plugins/profiles.koplugin/main.lua#L1-L82)

---

## Dispatcher Core

### Action Registry Structure

The Dispatcher maintains a central registry (`settingsList`) where each action is defined with metadata about its behavior, UI presentation, and constraints:

```
Action Definition

category
none/arg/string/
absolutenumber/
incrementalnumber/
configurable

event
Event name to broadcast

title
Display text

section
general/device/
screen/filemanager/
reader/rolling/paging

Optional Fields

arg
Static argument

args
Valid values (string)

toggle
Display labels (string)

min/max/step
Numeric constraints

condition
Device capability check

configurable
{name, values}
from CRE/KOPT
```

**Sources:**[frontend/dispatcher.lua1-29](https://github.com/koreader/koreader/blob/9e6b1587/frontend/dispatcher.lua#L1-L29)[frontend/dispatcher.lua55-306](https://github.com/koreader/koreader/blob/9e6b1587/frontend/dispatcher.lua#L55-L306)

### Action Categories

Actions are classified into six categories that determine their execution behavior:
CategoryDescriptionExampleValue Type`none`Direct event call, no arguments`show_menu`Boolean (true)`arg`Event with predefined argument`open_previous_document_in_folder` (arg=true)Boolean (true)`string`Event with selectable string values`set_font` (args from font list)String from `args``absolutenumber`Set numeric value directly`set_frontlight` (0-100)Number (min-max)`incrementalnumber`Adjust numeric value by delta`increase_font` (+delta)Number (delta)`configurable`Update document.configurable field`kopt_line_spacing`Value from `configurable.values`
**Sources:**[frontend/dispatcher.lua10-28](https://github.com/koreader/koreader/blob/9e6b1587/frontend/dispatcher.lua#L10-L28)

### Action Sections

Actions are organized into sections for menu presentation:

```
Action Sections

general
Cross-context actions
(history, dictionary, etc.)

device
Hardware controls
(WiFi, suspend, etc.)

screen
Display settings
(frontlight, night mode)

filemanager
File browser actions
(sort, display mode)

reader
Document navigation
(pages, bookmarks)

rolling
Reflowable docs
(font, margins)

paging
Fixed layout docs
(zoom, reflow)
```

**Sources:**[frontend/dispatcher.lua20](https://github.com/koreader/koreader/blob/9e6b1587/frontend/dispatcher.lua#L20-L20)

### Initialization Process

The Dispatcher initializes by parsing actions from multiple sources:

```
Plugins
KoptOptions
CreOptions
Dispatcher
Application Init
Plugins
KoptOptions
CreOptions
Dispatcher
Application Init
settingsList already contains
built-in actions
Update settingsList entries
with configurable metadata
Update kopt_* entries
Add runtime actions
Dispatcher.initialized = true
Dispatcher:init()
Parse CreOptions[i].options
EPUB/FB2 configurable settings
Parse KoptOptions[i].options
PDF/DjVu reflow settings
broadcastEvent("DispatcherRegisterActions")
registerAction(name, definition)
```

**Sources:**[frontend/dispatcher.lua561-623](https://github.com/koreader/koreader/blob/9e6b1587/frontend/dispatcher.lua#L561-L623)

### Example Action Definitions

Here are representative action definitions showing different categories:

```
-- Category: none (simple event)
show_menu = {
    category = "none",
    event = "ShowMenu",
    title = _("Show menu"),
    general = true
}
 
-- Category: string (value selection)
set_font = {
    category = "string",
    event = "SetFont",
    title = _("Font"),
    rolling = true,
    args_func = require("fontlist").getFontArgFunc
}
 
-- Category: absolutenumber (numeric setting)
set_frontlight = {
    category = "absolutenumber",
    event = "SetFlIntensity",
    min = 0,
    max = Device:getPowerDevice().fl_max,
    title = _("Set frontlight brightness"),
    screen = true,
    condition = Device:hasFrontlight()
}
 
-- Category: incrementalnumber (adjustment)
increase_font = {
    category = "incrementalnumber",
    event = "IncreaseFontSize",
    min = 0.5,
    max = 255,
    step = 0.5,
    title = _("Increase font size"),
    rolling = true
}
 
-- Category: configurable (document setting)
kopt_line_spacing = {
    category = "configurable",
    paging = true,
    -- configurable field populated from KoptOptions
}
```

**Sources:**[frontend/dispatcher.lua72](https://github.com/koreader/koreader/blob/9e6b1587/frontend/dispatcher.lua#L72-L72)[frontend/dispatcher.lua229](https://github.com/koreader/koreader/blob/9e6b1587/frontend/dispatcher.lua#L229-L229)[frontend/dispatcher.lua121](https://github.com/koreader/koreader/blob/9e6b1587/frontend/dispatcher.lua#L121-L121)[frontend/dispatcher.lua230](https://github.com/koreader/koreader/blob/9e6b1587/frontend/dispatcher.lua#L230-L230)[frontend/dispatcher.lua287](https://github.com/koreader/koreader/blob/9e6b1587/frontend/dispatcher.lua#L287-L287)

---

## Action Execution

### Execution API

The Dispatcher provides several methods for executing actions:

```
Dispatcher Execution API

execute(settings, exec_props)

executeOne(action_name, settings)

addArg(settings)

Execute all actions in settings
Handles execution order, QuickMenu,
one-by-one mode

Execute single named action
Returns gesture event or nil

Process arguments for action
Handles numeric, string categories
```

**Sources:**[frontend/dispatcher.lua731-872](https://github.com/koreader/koreader/blob/9e6b1587/frontend/dispatcher.lua#L731-L872)

### Execution Flow

When an action is executed, the Dispatcher processes it based on its category:

```
UIManager
executeOne()
Dispatcher:execute()
Gesture/Hotkey/Profile
UIManager
executeOne()
Dispatcher:execute()
Gesture/Hotkey/Profile
User can trigger actions
from menu
Get value from settings[action]
Get numeric value
Calculate delta
Update document.configurable
alt
[category = "none" or "arg"]
[category = "string"]
[category = "absolutenumber"]
[category = "incrementalnumber"]
[category = "configurable"]
alt
[Execution delay]
loop
[For each action]
alt
[QuickMenu Mode]
[Execute All]
execute(settings, exec_props)
Show QuickMenu widget
executeOne(action, settings)
broadcastEvent(event, arg)
broadcastEvent(event, value)
broadcastEvent(event, number)
broadcastEvent(event, gesture_or_delta)
broadcastEvent(event, configurable)
scheduleIn(delay, next_action)
```

**Sources:**[frontend/dispatcher.lua731-852](https://github.com/koreader/koreader/blob/9e6b1587/frontend/dispatcher.lua#L731-L852)

### Settings Storage Format

Actions and their values are stored in a standardized format:

```
-- Example settings object (gesture, hotkey, or profile)
{
    -- Action keys map to their values
    show_menu = true,                    -- category: none
    set_font = "NotoSans-Regular.ttf",  -- category: string
    set_frontlight = 50,                 -- category: absolutenumber
    increase_font = 2,                   -- category: incrementalnumber
    
    -- Special settings metadata
    settings = {
        name = "My Profile",             -- Display name
        order = {                        -- Execution order
            "set_font",
            "increase_font",
            "show_menu"
        },
        show_as_quickmenu = true,        -- Display as QuickMenu
        execute_one_by_one = 1,          -- Current index (sequential mode)
        anchor_quickmenu = true,         -- Anchor to gesture position
        always_active = true             -- Bypass touch input ignore
    }
}
```

**Sources:**[frontend/dispatcher.lua305](https://github.com/koreader/koreader/blob/9e6b1587/frontend/dispatcher.lua#L305-L305)[frontend/dispatcher.lua663-681](https://github.com/koreader/koreader/blob/9e6b1587/frontend/dispatcher.lua#L663-L681)

---

## Profiles System

### Profile Structure

Profiles are named collections of dispatcher actions stored in `profiles.lua`:

```
Profile Object

Action Mappings
{action_name: value}

settings{}

name: 'Profile Name'

order: [action1, action2, ...]

Execution Mode

Auto-execution Config

UI Options

Normal: Execute all

QuickMenu: Show menu

One-by-one: Sequential

Event Triggers

Execution Delay

Time Restrictions

Conditional Triggers

Show notification

Show in action list
```

**Sources:**[plugins/profiles.koplugin/main.lua39-56](https://github.com/koreader/koreader/blob/9e6b1587/plugins/profiles.koplugin/main.lua#L39-L56)

### Profile Auto-execution

Profiles can be automatically triggered by various system events:

```
System Events

Start
(KOReader launch)

Resume
(wake from sleep)

OutOfScreenSaver
(exit sleep screen)

ReadTimerExpired
(read timer)

SetRotationMode
(orientation change)

PathChanged
(folder navigation)

ReaderReadyAll
(document opened)

CloseDocumentAll
(document closed)

Profile Auto-execution

Check Conditions

Time interval?

Path match?

Rotation match?

Execute Profile
```

**Sources:**[plugins/profiles.koplugin/main.lua304-313](https://github.com/koreader/koreader/blob/9e6b1587/plugins/profiles.koplugin/main.lua#L304-L313)[plugins/profiles.koplugin/main.lua636-703](https://github.com/koreader/koreader/blob/9e6b1587/plugins/profiles.koplugin/main.lua#L636-L703)

### Auto-execution Configuration

Each profile can specify auto-execution behavior:
SettingDescriptionValues`auto_exec_ask`Prompt before executionBoolean`auto_exec_promptly`Execute before event processingBoolean`auto_exec_delay`Custom delay in secondsNumber (1-10)`auto_exec_time_interval`Time window restriction`{start_time, end_time}`
**Conditional Triggers:**

- **SetRotationMode**: Execute only for specific rotation angles (0°, 90°, 180°, 270°)
- **PathChanged**: Execute based on folder path matching (contains, equals, not contains, not equals)
- **ReaderReadyAll/CloseDocumentAll**: Execute based on document path matching

**Sources:**[plugins/profiles.koplugin/main.lua154-303](https://github.com/koreader/koreader/blob/9e6b1587/plugins/profiles.koplugin/main.lua#L154-L303)

### Execution Flow with Profiles

```
UI
USER
Dispatcher
Profile Object
Profiles:autoexec{}
System Event
UI
USER
Dispatcher
Profile Object
Profiles:autoexec{}
System Event
alt
[Has time_interval]
alt
[Has path
condition]
alt
[Has rotation
condition]
alt
[auto_exec_ask]
Execute before main event
alt
[auto_exec_delay]
[auto_exec_promptly]
alt
[settings.notify]
alt
[Conditions met]
loop
[For each profile in autoexec["Resume"]]
Event triggered (e.g., "Resume")
Check conditions
Check current time in range
Check path matches
Check rotation matches
Show confirmation dialog
Confirm/Cancel
Schedule with delay
execute(profile_data, exec_props)
Show notification
```

**Sources:**[plugins/profiles.koplugin/main.lua83-314](https://github.com/koreader/koreader/blob/9e6b1587/plugins/profiles.koplugin/main.lua#L83-L314)

### Profile as Dispatcher Action

Profiles can be registered as dispatcher actions, allowing profiles to trigger other profiles:

```
-- When profile is registered (settings.registered = true)
Dispatcher:registerAction(
    "profile_exec_" .. profile_name,
    {
        category = "none",
        event = "ProfileExecute",
        arg = profile_name,
        title = T(_("Profile %1"), profile_name),
        general = true
    }
)
 
-- This allows:
-- - Profiles to execute other profiles
-- - Gestures to trigger profiles
-- - Hotkeys to trigger profiles
```

**Sources:**[plugins/profiles.koplugin/main.lua65-80](https://github.com/koreader/koreader/blob/9e6b1587/plugins/profiles.koplugin/main.lua#L65-L80)

---

## Gestures Plugin

### Gesture Action Storage

Gestures are stored per-mode (FileManager vs Reader) in `gestures.lua`:

```
gestures.lua

gesture_fm{}

gesture_reader{}

custom_multiswipes{}

Gesture Mappings

Gesture Mappings

tap_top_left_corner:
{action: value, settings: {...}}

multiswipe_west_east:
{action: value, settings: {...}}

double_tap_left_side:
{action: value, settings: {...}}

two_finger_swipe_south:
{action: value, settings: {...}}

multiswipe_custom1: true

multiswipe_custom2: true
```

**Sources:**[plugins/gestures.koplugin/main.lua215-237](https://github.com/koreader/koreader/blob/9e6b1587/plugins/gestures.koplugin/main.lua#L215-L237)

### Gesture Categories

Gestures are organized into categories:

```
Gesture Categories

Tap Corner
4 corners

Hold Corner
4 corners

One Finger Swipe
8 edge directions
+ diagonal

Double Tap
2 sides + 4 corners

Two Finger Tap
4 corners

Two Finger Swipe
8 directions

Spread/Pinch
2 gestures

Rotation
CW/CCW

Multiswipes
30+ predefined
+ custom
```

**Sources:**[plugins/gestures.koplugin/main.lua41-110](https://github.com/koreader/koreader/blob/9e6b1587/plugins/gestures.koplugin/main.lua#L41-L110)

### Gesture Execution Flow

```
UIManager
Dispatcher
Gestures Plugin
GestureRange
Input/GestureDetector
UIManager
Dispatcher
Gestures Plugin
GestureRange
Input/GestureDetector
Set exec_props.ges = ges_event
alt
[settings.anchor_quickme-
nu]
Gesture not mapped,
pass to next handler
alt
[Gesture mapped]
[Pass through]
Gesture detected
onGesture(args, ges_event)
Get current mode
(gesture_fm / gesture_reader)
Map gesture name to action
Get settings for gesture
execute(gesture_settings, exec_props)
Broadcast action events
```

**Sources:**[plugins/gestures.koplugin/main.lua1-290](https://github.com/koreader/koreader/blob/9e6b1587/plugins/gestures.koplugin/main.lua#L1-L290)

### Always Active Gestures

Gestures can be marked as "always active" to bypass the `IgnoreTouchInput` event:

```
-- Checked by InputContainer before processing gestures
function Gestures:isGestureAlwaysActive(ges, multiswipe_directions)
    local gest = self.gestures[ges]
    return gest and (
        gest.toggle_touch_input or           -- Toggles touch input
        gest.touch_input_on or               -- Enables touch input
        (gest.settings and                   -- Manually marked
         gest.settings.always_active)
    )
end
```

**Sources:**[plugins/gestures.koplugin/main.lua197-213](https://github.com/koreader/koreader/blob/9e6b1587/plugins/gestures.koplugin/main.lua#L197-L213)

### Custom Multiswipes

Users can record custom multiswipe gestures:

```
Gestures Plugin
GestureDetector
InputDialog
User
Gestures Plugin
GestureDetector
InputDialog
User
Custom multiswipe now
available in gesture menu
Open multiswipe recorder
Perform multiswipe
onMultiswipe(arg, ges)
Set input text to friendly name
Click "Save"
Add to custom_multiswipes{}
Save to gestures.lua
```

**Sources:**[plugins/gestures.koplugin/main.lua466-534](https://github.com/koreader/koreader/blob/9e6b1587/plugins/gestures.koplugin/main.lua#L466-L534)

---

## Hotkeys Plugin

### Hotkey Storage Format

Hotkeys are stored per-mode (FileManager vs Reader) in `hotkeys.lua`:

```
hotkeys.lua

hotkeys_fm{}

hotkeys_reader{}

type_to_search: bool

press_key_does_hotkeys: bool

Hotkey Mappings

Hotkey Mappings

modifier_plus_up:
{action: value, settings: {...}}

alt_plus_s:
{action: value, settings: {...}}

modifier_plus_menu:
{action: value, settings: {...}}

alt_plus_f:
{action: value, settings: {...}}
```

**Sources:**[plugins/hotkeys.koplugin/main.lua62-82](https://github.com/koreader/koreader/blob/9e6b1587/plugins/hotkeys.koplugin/main.lua#L62-L82)

### Hotkey Categories

Hotkeys are organized by key type and modifier:
CategoryKeysModifiersDevicesCursor keysUp, Down, Left, RightShift/ScreenKBAll with keysPage-turn keysLPgBack, LPgFwd, RPgBack, RPgFwdShift/ScreenKBReader onlyFunction keysBack, Home, Press, MenuShift/ScreenKBAll with keysCursor keysUp, Down, Left, RightAlt/CtrlFull keyboardPage-turn keysLPgBack, LPgFwd, RPgBack, RPgFwdAlt/CtrlFull keyboardFunction keysBack, Home, Press, MenuAlt/CtrlFull keyboardAlphabet keysA-ZAlt/CtrlFull keyboard
**Sources:**[plugins/hotkeys.koplugin/main.lua27-60](https://github.com/koreader/koreader/blob/9e6b1587/plugins/hotkeys.koplugin/main.lua#L27-L60)[plugins/hotkeys.koplugin/main.lua287-401](https://github.com/koreader/koreader/blob/9e6b1587/plugins/hotkeys.koplugin/main.lua#L287-L401)

### Key Event Registration

The Hotkeys plugin registers key events dynamically:

```
Hotkeys:registerKeyEvents()

Override conflicting modules

Determine modifier
(ScreenKB vs Shift)

Map keys to events

ReaderBookmark.key_events = {}

ReaderConfig.key_events = {}

ReaderLink.key_events = {}

ReaderSearch.key_events = {}

ReaderToc.key_events = {}

Cursor keys
+ modifier

Page-turn keys
+ modifier (Reader only)

Function keys
+ modifier

A-Z keys
+ Alt/Ctrl (Keyboard)
```

**Sources:**[plugins/hotkeys.koplugin/main.lua121-180](https://github.com/koreader/koreader/blob/9e6b1587/plugins/hotkeys.koplugin/main.lua#L121-L180)[plugins/hotkeys.koplugin/main.lua422-525](https://github.com/koreader/koreader/blob/9e6b1587/plugins/hotkeys.koplugin/main.lua#L422-L525)

### Hotkey Execution Flow

```
UIManager
Dispatcher
Hotkeys Plugin
Input
UIManager
Dispatcher
Hotkeys Plugin
Input
Allow default behavior
alt
[Hotkey mapped]
[No mapping]
onHotkeyAction(hotkey_name)
Get self.hotkeys[hotkey_name]
Stop text selection (if active)
Prepare exec_props{hotkeys: name}
execute(hotkey_settings, exec_props)
Broadcast action events
return true
return nil
```

**Sources:**[plugins/hotkeys.koplugin/main.lua89-105](https://github.com/koreader/koreader/blob/9e6b1587/plugins/hotkeys.koplugin/main.lua#L89-L105)

### Type-to-Search Feature

When enabled, alphabet keys trigger full-text search in the reader:

```
-- When type_to_search = true
readersearch.key_events.Alphabet = {
    { Device.input.group.Alphabet },
    { "Shift", Device.input.group.Alphabet },
    event = "ShowFulltextSearchInput",
    args = ""
}
 
-- Conflicts to resolve:
-- Remove 'H' shortcut from ReaderHighlight:StartHighlightIndicator
self.ui.highlight.key_events.StartHighlightIndicator = nil
```

**Sources:**[plugins/hotkeys.koplugin/main.lua473-481](https://github.com/koreader/koreader/blob/9e6b1587/plugins/hotkeys.koplugin/main.lua#L473-L481)

---

## Common Patterns

### Dynamic Menu Building

All three plugins (Profiles, Gestures, Hotkeys) use a common pattern for building dynamic menus:

```
function Plugin:genMenu(item_name)
    local sub_items = {}
    
    -- Option 1: Default/Reset
    if has_default then
        table.insert(sub_items, {
            text = T(_("%1 (default)"), default_text),
            checked_func = function()
                return util.tableEquals(current, default)
            end,
            radio = true,
            callback = function()
                current = util.tableDeepCopy(default)
            end
        })
    end
    
    -- Option 2: Pass through / No action
    table.insert(sub_items, {
        text = _("Pass through"),
        checked_func = function()
            return current == nil
        end,
        radio = true,
        callback = function()
            current = nil
        end
    })
    
    -- Add dispatcher action submenu
    Dispatcher:addSubMenu(self, sub_items, data_table, item_name)
    
    return sub_items
end
```

**Sources:**[plugins/gestures.koplugin/main.lua296-372](https://github.com/koreader/koreader/blob/9e6b1587/plugins/gestures.koplugin/main.lua#L296-L372)[plugins/hotkeys.koplugin/main.lua191-239](https://github.com/koreader/koreader/blob/9e6b1587/plugins/hotkeys.koplugin/main.lua#L191-L239)

### Action Name Change Propagation

When a dispatcher action is renamed or removed, all three plugins receive a notification and update their stored references:

```
Hotkeys Plugin
Gestures Plugin
Profiles Plugin
Dispatcher
Hotkeys Plugin
Gestures Plugin
Profiles Plugin
Dispatcher
Action renamed/removed
Handled by Dispatcher:addSubMenu
via callback
broadcastEvent("DispatcherActionNameChanged")
onDispatcherActionNameChanged(action)
Update profile[old_name] → profile[new_name]
Update settings.order[]
onDispatcherActionNameChanged(action)
onDispatcherActionNameChanged(action)
Update hotkey[old_name] → hotkey[new_name]
Update settings.order[]
```

**Sources:**[plugins/profiles.koplugin/main.lua563-588](https://github.com/koreader/koreader/blob/9e6b1587/plugins/profiles.koplugin/main.lua#L563-L588)[plugins/hotkeys.koplugin/main.lua538-573](https://github.com/koreader/koreader/blob/9e6b1587/plugins/hotkeys.koplugin/main.lua#L538-L573)

### Settings Persistence

All three plugins follow a common persistence pattern:

```
-- Load settings
function Plugin:init()
    self.settings_data = LuaSettings:open(settings_path)
    self.data = self.settings_data.data[mode]
    self.updated = false
end
 
-- Mark changes
function Plugin:someAction()
    self.data[key] = value
    self.updated = true
end
 
-- Flush to disk
function Plugin:onFlushSettings()
    if self.settings_data and self.updated then
        self.settings_data:flush()
        self.updated = false
    end
end
 
-- Called on app close or explicit save
```

**Sources:**[plugins/profiles.koplugin/main.lua39-63](https://github.com/koreader/koreader/blob/9e6b1587/plugins/profiles.koplugin/main.lua#L39-L63)[plugins/gestures.koplugin/main.lua215-237](https://github.com/koreader/koreader/blob/9e6b1587/plugins/gestures.koplugin/main.lua#L215-L237)[plugins/hotkeys.koplugin/main.lua62-82](https://github.com/koreader/koreader/blob/9e6b1587/plugins/hotkeys.koplugin/main.lua#L62-L82)

---

## Key Data Structures

### Example Profile Definition

```
profiles.lua:
{
    "Night Reading" = {
        -- Actions
        set_night_mode = "on",
        set_frontlight = 20,
        set_frontlight_warmth = 100,
        
        -- Settings
        settings = {
            name = "Night Reading",
            order = {"set_night_mode", "set_frontlight", "set_frontlight_warmth"},
            registered = true,              -- Show in action list
            notify = true,                  -- Show notification
            auto_exec_ask = false,          -- Don't prompt
            auto_exec_delay = 2,            -- 2 second delay
            auto_exec_time_interval = {"20:00", "06:00"}  -- Evening/night only
        }
    }
}
 
-- Auto-execution mapping (separate from profile definition)
G_reader_settings:
{
    profiles_autoexec = {
        Resume = {
            ["Night Reading"] = true
        },
        SetRotationMode = {
            ["Night Reading"] = {
                [0] = true,  -- Portrait only
            }
        }
    }
}
```

**Sources:**[plugins/profiles.koplugin/main.lua39-56](https://github.com/koreader/koreader/blob/9e6b1587/plugins/profiles.koplugin/main.lua#L39-L56)[plugins/profiles.koplugin/main.lua614-669](https://github.com/koreader/koreader/blob/9e6b1587/plugins/profiles.koplugin/main.lua#L614-L669)

### Example Gesture Mapping

```
gestures.lua:
{
    gesture_reader = {
        tap_top_left_corner = {
            show_menu = true,
            settings = {
                always_active = false
            }
        },
        
        multiswipe_west_east = {
            toggle_bookmark = true,
            settings = {
                always_active = false
            }
        },
        
        two_finger_swipe_south = {
            -- Multiple actions
            decrease_font = 2,
            full_refresh = true,
            settings = {
                order = {"decrease_font", "full_refresh"},
                show_as_quickmenu = false,
                execute_one_by_one = false
            }
        }
    },
    
    custom_multiswipes = {
        multiswipe_east_south_west_north = true,
        multiswipe_custom_pattern_1 = true
    }
}
```

**Sources:**[plugins/gestures.koplugin/main.lua215-237](https://github.com/koreader/koreader/blob/9e6b1587/plugins/gestures.koplugin/main.lua#L215-L237)

### Example Hotkey Mapping

```
hotkeys.lua:
{
    hotkeys_reader = {
        modifier_plus_up = {
            prev_chapter = true
        },
        
        alt_plus_f = {
            fulltext_search = true
        },
        
        alt_plus_s = {
            -- Multiple actions executed as QuickMenu
            flush_settings = true,
            export_annotations = true,
            settings = {
                order = {"flush_settings", "export_annotations"},
                show_as_quickmenu = true
            }
        }
    },
    
    type_to_search = true,
    press_key_does_hotkeys = false
}
```

**Sources:**[plugins/hotkeys.koplugin/main.lua62-82](https://github.com/koreader/koreader/blob/9e6b1587/plugins/hotkeys.koplugin/main.lua#L62-L82)

---

## Integration Points

### Event Broadcasting

All executed actions result in events broadcast through UIManager:

```
-- Dispatcher execution
function Dispatcher:execute(settings, exec_props)
    for action_name, action_value in Dispatcher.iter_func(settings) do
        local ges_ev = self:executeOne(action_name, settings)
        if ges_ev then
            -- Broadcast to all registered handlers
            UIManager:broadcastEvent(Event:new(ges_ev.event, ges_ev.args))
        end
    end
end
```

**Sources:**[frontend/dispatcher.lua731-852](https://github.com/koreader/koreader/blob/9e6b1587/frontend/dispatcher.lua#L731-L852)

### Device Capability Checks

Many actions have conditional availability based on device capabilities:

```
-- Action definition with condition
{
    toggle_wifi = {
        category = "none",
        event = "ToggleWifi",
        title = _("Toggle Wi-Fi"),
        device = true,
        condition = Device:hasWifiToggle()  -- Only if device has WiFi
    }
}
 
-- Checked during menu building
if settingsList[action].condition == false then
    -- Don't show this action
end
```

**Sources:**[frontend/dispatcher.lua113](https://github.com/koreader/koreader/blob/9e6b1587/frontend/dispatcher.lua#L113-L113)

### Document Type Filtering

Actions are filtered based on document type (reflowable vs fixed-layout):

```
-- Action sections
rolling = true,   -- Only for EPUB/FB2/etc (reflowable)
paging = true,    -- Only for PDF/DjVu (fixed layout)
reader = true,    -- Both document types
filemanager = true,  -- File browser only
general = true    -- All contexts
```

**Sources:**[frontend/dispatcher.lua20](https://github.com/koreader/koreader/blob/9e6b1587/frontend/dispatcher.lua#L20-L20)

---

## File Locations
FilePurposeFormat`frontend/dispatcher.lua`Core action registry and execution engineModule`plugins/profiles.koplugin/main.lua`Profile management pluginPlugin`plugins/gestures.koplugin/main.lua`Gesture mapping pluginPlugin`plugins/hotkeys.koplugin/main.lua`Hotkey mapping pluginPlugin`[DataStorage]/profiles.lua`User profile definitionsLuaSettings`[DataStorage]/gestures.lua`User gesture mappingsLuaSettings`[DataStorage]/hotkeys.lua`User hotkey mappingsLuaSettings`G_reader_settings`Auto-execution configurationGlobal settings
**Sources:**[frontend/dispatcher.lua1](https://github.com/koreader/koreader/blob/9e6b1587/frontend/dispatcher.lua#L1-L1)[plugins/profiles.koplugin/main.lua25](https://github.com/koreader/koreader/blob/9e6b1587/plugins/profiles.koplugin/main.lua#L25-L25)[plugins/gestures.koplugin/main.lua39](https://github.com/koreader/koreader/blob/9e6b1587/plugins/gestures.koplugin/main.lua#L39-L39)[plugins/hotkeys.koplugin/main.lua24](https://github.com/koreader/koreader/blob/9e6b1587/plugins/hotkeys.koplugin/main.lua#L24-L24)

---

# Settings-and-Configuration-Management

# Settings and Configuration Management
Relevant source files
- [frontend/apps/filemanager/filemanager.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanager.lua)
- [frontend/apps/filemanager/filemanagercollection.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagercollection.lua)
- [frontend/apps/filemanager/filemanagerfilesearcher.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagerfilesearcher.lua)
- [frontend/apps/filemanager/filemanagerhistory.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagerhistory.lua)
- [frontend/apps/filemanager/filemanagerutil.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagerutil.lua)
- [frontend/apps/reader/modules/readersearch.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readersearch.lua)
- [frontend/apps/reader/readerui.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/readerui.lua)
- [frontend/readcollection.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/readcollection.lua)
- [frontend/ui/widget/filechooser.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/filechooser.lua)
- [frontend/ui/widget/pathchooser.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/pathchooser.lua)
- [plugins/coverbrowser.koplugin/covermenu.lua](https://github.com/koreader/koreader/blob/9e6b1587/plugins/coverbrowser.koplugin/covermenu.lua)
- [reader.lua](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua)

This document covers KOReader's comprehensive settings and configuration management system, which handles user preferences, document-specific configurations, menu organization, and the user interface for modifying settings. For information about action dispatch and gesture handling, see [Action Dispatch and Profiles](#2.3). For details about the UI widget system used in configuration dialogs, see [Widget and Menu System](/koreader/koreader/5.1-document-registry-and-providers).

## Settings Architecture Overview

KOReader's settings system operates on multiple levels with distinct storage mechanisms and scopes. The architecture separates global application settings from document-specific settings, while providing a unified interface for configuration management.

### Settings Architecture Diagram

```
Persistence Mechanisms

Device Integration

Setting Implementation Patterns

Menu Organization Files

Settings Storage Layer

G_reader_settings
Global Application Settings

DocSettings class
Document-specific Settings

G_defaults
System Default Values

common_settings_menu_table.lua
Shared setting definitions

reader_menu_order.lua
Reader UI menu structure

filemanager_menu_order.lua
FileManager menu structure

common_info_menu_table.lua
Info and help menus

checked_func: function()
returns current state

callback: function()
handles setting change

sub_item_table: table
nested menu items

text_func: function()
dynamic menu text

Device capability methods
hasFrontlight(), isTouchDevice()

Platform-specific settings
Android, Kobo, Kindle

Conditional menu item inclusion

Auto-save timer
auto_save_settings_interval_minutes

Document close event
immediate save

Suspend/resume hooks
safety saves
```

**Sources:**[frontend/ui/elements/common_settings_menu_table.lua1-777](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/elements/common_settings_menu_table.lua#L1-L777)[frontend/ui/elements/reader_menu_order.lua1-272](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/elements/reader_menu_order.lua#L1-L272)[frontend/ui/elements/filemanager_menu_order.lua1-212](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/elements/filemanager_menu_order.lua#L1-L212)[frontend/ui/elements/common_info_menu_table.lua1-127](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/elements/common_info_menu_table.lua#L1-L127)

### Core Settings Components

The settings system consists of several key components that work together to manage configuration:
ComponentPurposeImplementation Details`G_reader_settings`Global application preferences`readSetting()`, `saveSetting()`, `flipNilOrFalse()`, `isTrue()``DocSettings`Document-specific settings`getSidecarStorage()`, per-document .sdr foldersMenu order tablesMenu hierarchy and organization`reader_menu_order.lua`, `filemanager_menu_order.lua`Common settings tableShared setting definitions`common_settings_menu_table.lua` functionsDevice capability checksPlatform-specific features`Device:hasFrontlight()`, `Device:isTouchDevice()`
### Setting Definition Patterns

Settings are implemented using consistent patterns throughout the codebase:
PatternUsage ExampleFile Reference`checked_func``function() return G_reader_settings:isTrue("night_mode") end`[common_settings_menu_table.lua267](https://github.com/koreader/koreader/blob/9e6b1587/common_settings_menu_table.lua#L267-L267)`callback``function() G_reader_settings:flipNilOrFalse("setting") end`[common_settings_menu_table.lua294-296](https://github.com/koreader/koreader/blob/9e6b1587/common_settings_menu_table.lua#L294-L296)`text_func`Dynamic menu text based on current values[common_settings_menu_table.lua90-98](https://github.com/koreader/koreader/blob/9e6b1587/common_settings_menu_table.lua#L90-L98)`sub_item_table`Nested menu structures[common_settings_menu_table.lua77-148](https://github.com/koreader/koreader/blob/9e6b1587/common_settings_menu_table.lua#L77-L148)
**Sources:**[frontend/ui/elements/common_settings_menu_table.lua58-64](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/elements/common_settings_menu_table.lua#L58-L64)[frontend/ui/elements/common_settings_menu_table.lua267-271](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/elements/common_settings_menu_table.lua#L267-L271)[frontend/ui/elements/common_settings_menu_table.lua290-296](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/elements/common_settings_menu_table.lua#L290-L296)[frontend/ui/elements/common_settings_menu_table.lua415-425](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/elements/common_settings_menu_table.lua#L415-L425)

## Menu System Organization

The menu system provides a structured hierarchy for accessing settings, with different organizations for the Reader and FileManager contexts. Menu items are dynamically generated based on device capabilities and current context.

### Menu Structure Diagram

```

```

**Sources:**[frontend/ui/elements/reader_menu_order.lua3-12](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/elements/reader_menu_order.lua#L3-L12)[frontend/ui/elements/reader_menu_order.lua69-86](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/elements/reader_menu_order.lua#L69-L86)[frontend/ui/elements/filemanager_menu_order.lua4-11](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/elements/filemanager_menu_order.lua#L4-L11)[frontend/ui/elements/common_settings_menu_table.lua31-38](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/elements/common_settings_menu_table.lua#L31-L38)[frontend/ui/elements/common_settings_menu_table.lua285-309](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/elements/common_settings_menu_table.lua#L285-L309)

### Menu Item Dynamic Generation

Menu items are conditionally included based on device capabilities and context:

```
Platform Specific Checks

true

true

true

true

true

false

true

true

true

Menu Order Tables
reader_menu_order.lua
filemanager_menu_order.lua

Menu Generation Process

common_settings_menu_table.lua

Device Capability Checks

Device:isTouchDevice()

Device:hasFrontlight()

Device:canToggleMassStorage()

Device:canToggleChargingLED()

Device:hasOTAUpdates()

Device:hasExitOptions()

taps_and_gestures menu

frontlight setting

mass_storage_settings

charging_led setting

ota_update menu

Remove exit_menu

Device:isAndroid()

Device:isKobo()

Device:isCervantes()

android_volume_keys
android_haptic_feedback
android_back_button

ignore_sleepcover
ignore_open_sleepcover
pageturn_power

start_bq
```

**Sources:**[frontend/ui/elements/reader_menu_order.lua267-269](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/elements/reader_menu_order.lua#L267-L269)[frontend/ui/elements/filemanager_menu_order.lua208-210](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/elements/filemanager_menu_order.lua#L208-L210)[frontend/ui/elements/common_settings_menu_table.lua31-38](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/elements/common_settings_menu_table.lua#L31-L38)[frontend/ui/elements/common_settings_menu_table.lua53-64](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/elements/common_settings_menu_table.lua#L53-L64)[frontend/ui/elements/common_settings_menu_table.lua216-252](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/elements/common_settings_menu_table.lua#L216-L252)[frontend/ui/elements/common_settings_menu_table.lua337-393](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/elements/common_settings_menu_table.lua#L337-L393)

## Configuration Dialogs and Widgets

The configuration system uses a sophisticated widget hierarchy to present settings in an intuitive interface. The `ConfigDialog` serves as the main container, with specialized widgets for different types of configuration options.

### Configuration Widget Hierarchy

```
Widget Components

Option Types

ConfigDialog
Main Configuration Container

ConfigOption
Individual Option Renderer

OptionTextItem
Text-based Options

OptionIconItem
Icon-based Options

ToggleSwitch
Multi-choice Toggle

ButtonProgressWidget
Progress Bar Style

FrameContainer
Visual Container

HorizontalGroup
Layout Container

TextWidget
Text Display

IconWidget
Icon Display
```

**Sources:**[frontend/ui/widget/configdialog.lua185-187](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/configdialog.lua#L185-L187)[frontend/ui/widget/configdialog.lua39-41](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/configdialog.lua#L39-L41)[frontend/ui/widget/configdialog.lua112-114](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/configdialog.lua#L112-L114)[frontend/ui/widget/toggleswitch.lua32-41](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/toggleswitch.lua#L32-L41)

### Configuration Dialog Workflow

```
UIManager
SettingsStore
ConfigOption
ConfigDialog
User
UIManager
SettingsStore
ConfigOption
ConfigDialog
User
Open Configuration
Initialize Options
Read Current Values
Return Values
Render Options
Display Dialog
Change Setting
onConfigChoose()
Update Setting
Broadcast Event
Update UI
Visual Feedback
```

**Sources:**[frontend/ui/widget/configdialog.lua90-92](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/configdialog.lua#L90-L92)[frontend/ui/widget/configdialog.lua354-426](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/configdialog.lua#L354-L426)[frontend/ui/widget/toggleswitch.lua224-226](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/toggleswitch.lua#L224-L226)

## Settings Persistence and Storage

KOReader employs a multi-tiered storage system that separates global settings from document-specific configurations, with configurable metadata storage locations.

### Settings Storage Architecture

```
Persistence Events

Settings Categories

Document Settings Storage Options

Global Settings

settings.reader.lua
Global Preferences

G_defaults
System Defaults

Book Folder
alongside book files

Sidecar Directory
centralized location

Hash-based Storage
MD5 hash organization

Device Settings
Hardware-specific

Application Settings
UI preferences

Document Settings
Per-book preferences

Module Settings
Feature-specific

Auto-save Timer
Configurable Intervals

Document Close
Immediate Save

Application Exit
Full Save

Suspend/Resume
Safety Save
```

**Sources:**[frontend/ui/elements/common_settings_menu_table.lua574-588](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/elements/common_settings_menu_table.lua#L574-L588)[frontend/ui/elements/common_settings_menu_table.lua619-657](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/elements/common_settings_menu_table.lua#L619-L657)[frontend/ui/elements/common_settings_menu_table.lua529-532](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/elements/common_settings_menu_table.lua#L529-L532)

### Auto-save Settings Configuration

The system includes configurable auto-save functionality with device-specific warnings:
SettingDefaultDescription`auto_save_settings_interval_minutes`15Automatic save intervalAvailable intervalsfalse, 5, 15, 30, 60Minutes or disabled (false)Warning devicesKobo, Kindle, Cervantes, PocketBook, Sony PRSTUXDevices with FAT32 concerns
### Metadata Storage Configuration

The system provides flexible options for storing document metadata and settings:
Storage ModeCode ValueDescriptionUse CaseBook folder`"doc"`Alongside book files (*.sdr folders)Default, portable with libraryCentralized directory`"dir"``DocSettings.getSidecarStorage("dir")`Clean library viewHash-based storage`"hash"``DocSettings.getSidecarStorage("hash")`Multiple copies, rename tolerance
**Sources:**[frontend/ui/elements/common_settings_menu_table.lua529-547](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/elements/common_settings_menu_table.lua#L529-L547)[frontend/ui/elements/common_settings_menu_table.lua574-578](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/elements/common_settings_menu_table.lua#L574-L578)[frontend/ui/elements/common_settings_menu_table.lua590-616](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/elements/common_settings_menu_table.lua#L590-L616)[frontend/ui/elements/common_settings_menu_table.lua660-687](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/elements/common_settings_menu_table.lua#L660-L687)

## Options Definition System

KOReader uses a declarative system for defining configuration options, with separate option sets for different document rendering engines and contexts.

### Options Definition Structure

```
Rendering Context

Option Properties

Option Definition Files

creoptions.lua
EPUB/MOBI Engine

koptoptions.lua
PDF Engine

common_settings_menu_table.lua
Shared Options

name
Setting Identifier

Widget Type
toggle/buttonprogress/icons

values
Possible Values

event
Event to Trigger

callback
Action Function

Document Engine
CRE/KOpt/MuPDF

Device Capabilities
Touch/DPad/etc

Feature State
enabled/disabled
```

**Sources:**[frontend/ui/data/creoptions.lua49-51](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/data/creoptions.lua#L49-L51)[frontend/ui/data/koptoptions.lua39-41](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/data/koptoptions.lua#L39-L41)[frontend/ui/data/creoptions.lua54-101](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/data/creoptions.lua#L54-L101)[frontend/ui/data/koptoptions.lua42-93](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/data/koptoptions.lua#L42-L93)

### Option Definition Example

The options system uses a structured format to define configuration choices:

```
-- Example from creoptions.lua
{
    name = "line_spacing",
    name_text = _("Line Spacing"), 
    buttonprogress = true,
    values = {80, 85, 90, 95, 100, 105, 110, 115, 120, 130, 140, 150},
    default_pos = 5,
    default_value = 100,
    event = "SetLineSpace",
    args = {80, 85, 90, 95, 100, 105, 110, 115, 120, 130, 140, 150},
    name_text_hold_callback = optionsutil.showValues,
}
```

**Sources:**[frontend/ui/data/creoptions.lua361-376](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/data/creoptions.lua#L361-L376)

## Settings Integration with Reader Modules

Settings are tightly integrated with reader modules, allowing features to register their configuration options and respond to setting changes through the event system.

### Settings-Module Integration Flow

```
SettingsStore
EventSystem
UIDocument
ReaderFont
ConfigDialog
SettingsStore
EventSystem
UIDocument
ReaderFont
ConfigDialog
Font Size Change Event
setFontSize()
Save Setting
UpdatePos Event
Re-render Content
Update Complete
Refresh UI
```

**Sources:**[frontend/apps/reader/modules/readerfont.lua217-224](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfont.lua#L217-L224)[frontend/apps/reader/modules/readerfont.lua292-295](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfont.lua#L292-L295)[frontend/ui/widget/configdialog.lua90-92](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/configdialog.lua#L90-L92)

### Module Settings Registration

Reader modules register their settings through the menu system:

```
Menu Integration

Settings Categories

Module Registration

Settings Events

Settings Events

Settings Events

ReaderFont
Font Settings

ReaderTypeset
Layout Settings

ReaderHighlight
Annotation Settings

Font Family/Size
Typography Controls

Margins/Spacing
Page Layout

Colors/Styles
Highlight Options

ui.menu
Main Menu System

Option Tables
Declarative Config

Event Handlers
Setting Application
```

**Sources:**[frontend/apps/reader/modules/readerfont.lua34](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfont.lua#L34-L34)[frontend/apps/reader/modules/readerfont.lua354-367](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfont.lua#L354-L367)[frontend/apps/reader/modules/readertypeset.lua24](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readertypeset.lua#L24-L24)

## Settings Validation and Defaults

The settings system includes comprehensive validation and default value management to ensure system stability and user experience consistency.

### Validation and Default System

```
Yes

No

Yes

No

Yes

No

Setting Change Request

Validation Layer

Type Validation

Range Validation

Device Capability Check

Valid Type?

In Range?

Device Supports?

Apply Default Value

Apply Setting

Save to Storage

Broadcast Change Event
```

**Sources:**[frontend/ui/data/optionsutil.lua29-31](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/data/optionsutil.lua#L29-L31)[frontend/apps/reader/modules/readerfont.lua217-219](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfont.lua#L217-L219)[frontend/ui/widget/configdialog.lua278-280](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/configdialog.lua#L278-L280)

The settings and configuration management system provides a comprehensive framework for managing user preferences across KOReader's diverse feature set, with robust validation, flexible storage options, and seamless integration with the application's modular architecture.

**Sources:**[frontend/ui/elements/common_settings_menu_table.lua1-777](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/elements/common_settings_menu_table.lua#L1-L777)[frontend/ui/widget/configdialog.lua1-1000](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/configdialog.lua#L1-L1000)[frontend/ui/data/creoptions.lua1-500](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/data/creoptions.lua#L1-L500)[frontend/ui/data/koptoptions.lua1-600](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/data/koptoptions.lua#L1-L600)[frontend/ui/data/optionsutil.lua1-301](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/data/optionsutil.lua#L1-L301)

---

# Application-Layer

# Application Layer
Relevant source files
- [frontend/apps/filemanager/filemanager.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanager.lua)
- [frontend/apps/filemanager/filemanagercollection.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagercollection.lua)
- [frontend/apps/filemanager/filemanagerfilesearcher.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagerfilesearcher.lua)
- [frontend/apps/filemanager/filemanagerhistory.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagerhistory.lua)
- [frontend/apps/filemanager/filemanagerutil.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagerutil.lua)
- [frontend/apps/reader/modules/readerbookmark.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerbookmark.lua)
- [frontend/apps/reader/modules/readerfont.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfont.lua)
- [frontend/apps/reader/modules/readerhighlight.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua)
- [frontend/apps/reader/modules/readerlink.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerlink.lua)
- [frontend/apps/reader/modules/readerpaging.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerpaging.lua)
- [frontend/apps/reader/modules/readerrolling.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerrolling.lua)
- [frontend/apps/reader/modules/readersearch.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readersearch.lua)
- [frontend/apps/reader/modules/readertoc.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readertoc.lua)
- [frontend/apps/reader/modules/readerview.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerview.lua)
- [frontend/apps/reader/readerui.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/readerui.lua)
- [frontend/document/credocument.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/credocument.lua)
- [frontend/readcollection.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/readcollection.lua)
- [frontend/ui/widget/filechooser.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/filechooser.lua)
- [frontend/ui/widget/pathchooser.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/pathchooser.lua)
- [plugins/coverbrowser.koplugin/covermenu.lua](https://github.com/koreader/koreader/blob/9e6b1587/plugins/coverbrowser.koplugin/covermenu.lua)

## Purpose and Scope

The Application Layer comprises the two main user-facing applications in KOReader: **FileManager** and **ReaderUI**. This page provides an architectural overview of how these applications are structured, how they relate to each other, and how they build upon the core framework systems.

For details on the framework systems these applications depend on (UIManager, Device abstraction, input handling), see [Core Framework Systems](/koreader/koreader/3-core-framework-systems). For specifics on document loading and rendering, see [Document System](/koreader/koreader/5-document-system).

**Subsections:**

- [File Manager System](/koreader/koreader/4.1-file-manager-system) - Detailed coverage of file browsing, history, collections, and metadata
- [Reader UI and Module System](/koreader/koreader/4.2-reader-ui-and-module-system) - In-depth look at the modular reader architecture
- [Menu System and Navigation](/koreader/koreader/4.3-menu-system-and-navigation) - Menu construction and navigation patterns

## Application Architecture Overview

KOReader's application layer consists of two primary applications that share infrastructure but serve distinct purposes:

```
Entry Point

Framework Layer

Application Layer

launches

launches

uses

uses

uses

uses

uses

uses

openFile()

onClose()

FileManager
File Browsing & Management

ReaderUI
Document Reading

UIManager
Event Loop

Device
Hardware Abstraction

DocumentRegistry
Document Providers

reader.lua
```

**Sources:**[frontend/apps/filemanager/filemanager.lua1-150](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanager.lua#L1-L150)[frontend/apps/reader/readerui.lua1-150](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/readerui.lua#L1-L150)[reader.lua](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua) (from Diagram 1)

### InputContainer Inheritance

Both applications extend `InputContainer`, which provides gesture and key event handling capabilities:
ApplicationBase ClassPrimary PurposeEntry Widget`FileManager``InputContainer`Browse files, manage collections, access history`FileChooser``ReaderUI``InputContainer`Read documents, annotations, navigation`ReaderView`
**Sources:**[frontend/apps/filemanager/filemanager.lua47](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanager.lua#L47-L47)[frontend/apps/reader/readerui.lua75](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/readerui.lua#L75-L75)

### Module Registration Pattern

Both applications use a consistent module registration pattern where feature-specific modules are registered during initialization:

```
calls

stores as self[name]

inserts into self

stores as self[name]

inserts into self

stores as self[name]

inserts into self

if always_active

Application
(FileManager or ReaderUI)

registerModule(name, module, always_active)

Module 1

Module 2

Module N

active_widgets array
```

**Sources:**[frontend/apps/filemanager/filemanager.lua385-395](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanager.lua#L385-L395)[frontend/apps/reader/readerui.lua92-102](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/readerui.lua#L92-L102)

#### FileManager Module Registration

FileManager registers modules for file operations, history, collections, and device management:

```
FileManager:init

screenshot: Screenshoter

menu: FileManagerMenu

history: FileManagerHistory

bookinfo: FileManagerBookInfo

collections: FileManagerCollection

filesearcher: FileManagerFileSearcher

folder_shortcuts: FileManagerShortcuts

languagesupport: LanguageSupport

dictionary: ReaderDictionary

wikipedia: ReaderWikipedia

devicestatus: ReaderDeviceStatus

Plugin modules via PluginLoader
```

**Sources:**[frontend/apps/filemanager/filemanager.lua398-430](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanager.lua#L398-L430)

#### ReaderUI Module Registration

ReaderUI registers 20+ specialized modules for document interaction:

```
Configuration

Content Enhancement

Interaction Features

Display & Navigation

ReaderUI:init

view: ReaderView

paging: ReaderPaging

rolling: ReaderRolling

zooming: ReaderZooming

highlight: ReaderHighlight

bookmark: ReaderBookmark

search: ReaderSearch

link: ReaderLink

toc: ReaderToc

dictionary: ReaderDictionary

wikipedia: ReaderWikipedia

menu: ReaderMenu

config: ReaderConfig

font: ReaderFont

typeset: ReaderTypeset
```

**Sources:**[frontend/apps/reader/readerui.lua135-455](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/readerui.lua#L135-L455)

### Initialization Sequence

Both applications follow a similar initialization pattern:

```
Device
UIManager
setupLayout/init modules
FileManager/ReaderUI
reader.lua
Device
UIManager
setupLayout/init modules
FileManager/ReaderUI
reader.lua
Application ready for events
new instance created
init()
registerModule() for each feature
setupLayout() / addWidgets()
Setup touch zones
handleEvent("SetDimensions")
show(application)
```

**Sources:**[frontend/apps/filemanager/filemanager.lua398-444](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanager.lua#L398-L444)[frontend/apps/reader/readerui.lua112-509](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/readerui.lua#L112-L509)

## FileManager Structure

FileManager is built around the `FileChooser` widget for file navigation:
ComponentTypePurpose`FileChooser`WidgetFile list display, navigation, selection`TitleBar`WidgetPath display, home/menu buttons`FileManagerMenu`ModuleMenu system integration`FileManagerHistory`ModuleRecently opened files`FileManagerCollection`ModuleUser-created collections/favorites`FileManagerFileSearcher`ModuleFile search functionality
**Sources:**[frontend/apps/filemanager/filemanager.lua118-361](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanager.lua#L118-L361)

### FileChooser Integration

The `FileChooser` widget is the core UI component:

```
contains

extends

extends

onFileSelect

onFileHold

showFileDialog

FileManager

FileChooser

BookList base

Menu widget

showFileDialog

ButtonDialog
```

**Sources:**[frontend/apps/filemanager/filemanager.lua140-346](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanager.lua#L140-L346)[frontend/ui/widget/filechooser.lua19-98](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/filechooser.lua#L19-L98)

### File Operations

FileManager provides operations through dialog buttons:

```
Long-press on file/folder

ButtonDialog with operations

Paste

Select mode

Rename

Delete

Cut

Copy

Set reading status

Add to collection

Open with...

Book information
```

**Sources:**[frontend/apps/filemanager/filemanager.lua181-346](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanager.lua#L181-L346)

## ReaderUI Structure

ReaderUI orchestrates document reading through a hub-and-spoke architecture where `ReaderUI` acts as the central hub connecting specialized reader modules:
Module CategoryExamplesPurposeDisplay`ReaderView`, `ReaderPaging`, `ReaderRolling`Page rendering and navigationInteraction`ReaderHighlight`, `ReaderBookmark`, `ReaderLink`Text selection, annotationsConfiguration`ReaderConfig`, `ReaderFont`, `ReaderTypeset`Settings and document optionsContent`ReaderDictionary`, `ReaderWikipedia`, `ReaderSearch`Content lookup and search
**Sources:**[frontend/apps/reader/readerui.lua135-455](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/readerui.lua#L135-L455)

### Document Type Branching

ReaderUI initializes different modules based on document type:

```
true: PDF/DjVu

true

true

true

false: EPUB/other

false

false

false

false

ReaderUI:init

document.info.has_pages?

ReaderPaging

ReaderZooming

ReaderCropping

ReaderKoptListener

ReaderRolling

ReaderFont

ReaderTypeset

ReaderCoptListener

ReaderStyleTweak
```

**Sources:**[frontend/apps/reader/readerui.lua264-372](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/readerui.lua#L264-L372)

### Event Flow in ReaderUI

Reader modules communicate via events broadcast through the UI hierarchy:

```
Document
Module2
Module1
ReaderUI
Input
User
Document
Module2
Module1
ReaderUI
Input
User
alt
[Event consumed]
[Event not consumed]
Gesture/Key
handleEvent(Event)
handleEvent(Event)
Process event
true (stop propagation)
nil/false
handleEvent(Event)
Perform action
Emit new event
```

**Sources:**[frontend/apps/reader/readerui.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/readerui.lua)[frontend/ui/widget/container/inputcontainer.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/container/inputcontainer.lua)

## Application Lifecycle

### FileManager Lifecycle

```
FileManager.showFiles()

setupLayout, registerModules

User navigates

Path change

openFile(document)

Launch ReaderUI

onClose()

Exit or return to launcher

Init

Ready

Browsing

Opening

Closing
```

**Sources:**[frontend/apps/filemanager/filemanager.lua398-444](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanager.lua#L398-L444)[frontend/apps/filemanager/filemanager.lua812-819](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanager.lua#L812-L819)

### ReaderUI Lifecycle

```

```

**Sources:**[frontend/apps/reader/readerui.lua112-509](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/readerui.lua#L112-L509)[frontend/apps/reader/readerui.lua728-785](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/readerui.lua#L728-L785)

### Navigation Between Applications

FileManager and ReaderUI navigation is bidirectional:

```
select file

DocumentRegistry:openDocument

user exits

if FileManager.instance

else

FileManager
file_chooser:onFileSelect

openFile method

ReaderUI
created with document

ReaderUI:onClose

Exit KOReader
```

**Sources:**[frontend/apps/filemanager/filemanager.lua162-171](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanager.lua#L162-L171)[frontend/apps/reader/readerui.lua728-785](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/readerui.lua#L728-L785)

### Document Opening Flow

```
UIManager
ReaderUI
DocumentRegistry
FileManager
FileChooser
UIManager
ReaderUI
DocumentRegistry
FileManager
FileChooser
FileManager remains in memory
onFileSelect(item)
openFile(item.path)
openDocument(file)
Select provider
new ReaderUI{document}
init() - register modules
loadDocument() if EPUB
render() if EPUB
show(ReaderUI)
```

**Sources:**[frontend/apps/filemanager/filemanager.lua162-171](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanager.lua#L162-L171)[frontend/document/documentregistry.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/documentregistry.lua)[frontend/apps/reader/readerui.lua112-509](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/readerui.lua#L112-L509)

## Module Communication Patterns

### Menu Registration

Both applications use a menu registration pattern where modules add items to the main menu:

```
for each module

calls

modifies

displayed in

Application postInitCallback

ui.menu:registerToMainMenu

Module:addToMainMenu

menu_items table

Main Menu UI
```

**Sources:**[frontend/apps/filemanager/filemanager.lua251-253](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanager.lua#L251-L253)[frontend/apps/reader/readerui.lua116-118](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/readerui.lua#L116-L118)

### Settings Persistence

Both applications coordinate settings persistence:

```
G_reader_settings
DocSettings
Reader/FM Module
Application
G_reader_settings
DocSettings
Reader/FM Module
Application
User makes changes
handleEvent("ReadSettings")
readSetting(key)
readSetting(key)
handleEvent("SaveSettings")
saveSetting(key, value)
saveSetting(key, value)
flush()
```

**Sources:**[frontend/apps/reader/readerui.lua459-461](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/readerui.lua#L459-L461)[frontend/apps/filemanager/filemanager.lua815-823](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanager.lua#L815-L823)

## Shared Infrastructure

Both applications share several systems:
SystemUsage in FileManagerUsage in ReaderUI`ReaderDictionary`Word lookup in file browserWord lookup in documents`ReaderWikipedia`Article lookupArticle lookup`FileManagerHistory`Recent files listAccess history from reader`FileManagerCollection`Manage collectionsAdd current book to collection`FileManagerBookInfo`Show book metadataShow book metadata
**Sources:**[frontend/apps/filemanager/filemanager.lua405-416](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanager.lua#L405-L416)[frontend/apps/reader/readerui.lua203-427](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/readerui.lua#L203-L427)

## Summary

The Application Layer provides two complementary user experiences:

1. **FileManager**: File browsing and management centered around the `FileChooser` widget, with modules for history, collections, and search.
2. **ReaderUI**: Document reading with 20+ specialized modules for display, annotation, navigation, and content enhancement.

Both applications:

- Extend `InputContainer` for event handling
- Use module registration pattern for extensibility
- Integrate with the menu system
- Share infrastructure like dictionaries and collections
- Build on framework systems (UIManager, Device, DocumentRegistry)

The applications maintain a clean separation while enabling smooth navigation between file browsing and document reading modes.

---

# File-Manager-System

# File Manager System
Relevant source files
- [frontend/apps/filemanager/filemanager.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanager.lua)
- [frontend/apps/filemanager/filemanagercollection.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagercollection.lua)
- [frontend/apps/filemanager/filemanagerfilesearcher.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagerfilesearcher.lua)
- [frontend/apps/filemanager/filemanagerhistory.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagerhistory.lua)
- [frontend/apps/filemanager/filemanagerutil.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagerutil.lua)
- [frontend/apps/reader/modules/readersearch.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readersearch.lua)
- [frontend/apps/reader/readerui.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/readerui.lua)
- [frontend/readcollection.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/readcollection.lua)
- [frontend/ui/widget/filechooser.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/filechooser.lua)
- [frontend/ui/widget/pathchooser.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/pathchooser.lua)
- [plugins/coverbrowser.koplugin/covermenu.lua](https://github.com/koreader/koreader/blob/9e6b1587/plugins/coverbrowser.koplugin/covermenu.lua)

## Purpose and Scope

This document describes the **FileManager system**, one of KOReader's two main applications (the other being ReaderUI, documented in [4.2](/koreader/koreader/4.2-reader-ui-and-module-system)). FileManager provides file browsing, document management, and access to reading history and collections. It serves as the primary interface for navigating the file system, opening documents, and managing book metadata.

**Related pages:**

- For ReaderUI and document viewing: see [4.2](/koreader/koreader/4.2-reader-ui-and-module-system)
- For document registry and providers: see [5.1](/koreader/koreader/5.1-document-registry-and-providers)
- For menu system details: see [4.3](/koreader/koreader/4.3-menu-system-and-navigation)

---

## Architecture Overview

```
UI Components

Data Persistence

FileManager Application

Feature Modules

FileManager
(InputContainer)

TitleBar
with home/plus icons

FileChooser
extends BookList

FileManagerHistory

FileManagerCollection

FileManagerFileSearcher

FileManagerBookInfo

FileManagerShortcuts

FileManagerMenu

ReadHistory
history.lua

ReadCollection
collection.lua

DocSettings
*.sdr/metadata.lua

BookList
base list widget

ButtonDialog
file actions

PathChooser
folder selection
```

**FileManager Architecture:** The FileManager application (`FM`) instantiates a `FileChooser` widget (`FC`) for file browsing and registers multiple feature modules for specialized functionality. All list-based interfaces (history, collections, search results) reuse the `BookList` base widget. Data persists across three systems: global history, user collections, and per-document settings.

Sources: [frontend/apps/filemanager/filemanager.lua1-444](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanager.lua#L1-L444)[frontend/ui/widget/filechooser.lua1-457](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/filechooser.lua#L1-L457)

---

## FileManager Class

### Instantiation and Singleton Pattern

`FileManager` extends `InputContainer` and follows a singleton pattern. Only one instance can exist at a time, tracked via `FileManager.instance`:

```
Feature Modules
FileManager:init()
FileManager
reader.lua
Feature Modules
FileManager:init()
FileManager
reader.lua
alt
[Instance exists]
FileManager:showFiles(path)
Check FileManager.instance
Close existing instance
Create new instance
setupLayout()
registerModule() for each
Set FileManager.instance
Show FileManager
```

Sources: [frontend/apps/filemanager/filemanager.lua437-443](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanager.lua#L437-L443)[frontend/apps/filemanager/filemanager.lua826-833](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanager.lua#L826-L833)

### Initialization Sequence

The `FileManager:init()` method performs the following initialization:
StepActionCode Reference1Initialize `active_widgets` array[filemanager.lua399](https://github.com/koreader/koreader/blob/9e6b1587/filemanager.lua#L399-L399)2Register core modules (screenshot, menu, history, etc.)[filemanager.lua401-416](https://github.com/koreader/koreader/blob/9e6b1587/filemanager.lua#L401-L416)3Load plugins via `PluginLoader`[filemanager.lua418-430](https://github.com/koreader/koreader/blob/9e6b1587/filemanager.lua#L418-L430)4Setup UI layout (`setupLayout()`)[filemanager.lua432](https://github.com/koreader/koreader/blob/9e6b1587/filemanager.lua#L432-L432)5Initialize gesture listeners[filemanager.lua433](https://github.com/koreader/koreader/blob/9e6b1587/filemanager.lua#L433-L433)6Broadcast initial events[filemanager.lua434-435](https://github.com/koreader/koreader/blob/9e6b1587/filemanager.lua#L434-L435)7Set singleton instance[filemanager.lua443](https://github.com/koreader/koreader/blob/9e6b1587/filemanager.lua#L443-L443)
Sources: [frontend/apps/filemanager/filemanager.lua398-444](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanager.lua#L398-L444)

### Layout Structure

The `setupLayout()` method creates the visual hierarchy:

```
external title bar

tap

hold

tap right

FileManager
(FrameContainer)

TitleBar
left: home | right: plus

FileChooser
(file list)

FrameContainer
padding=0, bordersize=0

onHome()

onShowFolderMenu()

onShowPlusMenu()
```

The title bar displays the current path with abbreviation and folder shortcut indicator (`☆`):

Sources: [frontend/apps/filemanager/filemanager.lua118-361](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanager.lua#L118-L361)

---

## FileChooser Widget

### Class Hierarchy

`FileChooser` extends `BookList`, inheriting list display and pagination functionality:

```
Widget

WidgetContainer

InputContainer

FocusManager

Menu

BookList

FileChooser

+path: string

+show_hidden: boolean

+show_unsupported: boolean

+file_filter: function

+exclude_dirs: table

+exclude_files: table

+path_items: table

+getList() : table

+refreshPath()

+changeToPath()

+goHome()
```

Sources: [frontend/ui/widget/filechooser.lua19-457](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/filechooser.lua#L19-L457)

### Directory Scanning

The `getList(path, collate)` method scans directories and returns two tables:

```
local dirs, files = FileChooser:getList(path, collate)
-- dirs: array of directory items
-- files: array of file items
```

**Exclusion filters** (defined at [filechooser.lua28-72](https://github.com/koreader/koreader/blob/9e6b1587/filechooser.lua#L28-L72)):
Filter TypePattern ExamplesPurpose`exclude_dirs``%.sdr$`, `^%.adobe%-digital%-editions$`System/hidden folders`exclude_files``^%.DS_Store$`, `^Thumbs%.db$`OS metadata files`show_hidden`Files starting with `.`User preference`file_filter``DocumentRegistry:hasProvider()`Supported formats only
Sources: [frontend/ui/widget/filechooser.lua81-150](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/filechooser.lua#L81-L150)

### Item Generation and Sorting

Each item undergoes processing in `getListItem()`:

```
Raw file entry

getListItem()

Set text, path, attr

Apply bidi_wrap_func

Check opened status

Generate mandatory text

Return item table
```

Sorting is handled by collation functions retrieved via `getCollate()`, supporting multiple modes:

- **strcoll**: Locale-aware string comparison
- **access**: Sort by access time (most recent first)
- **date**: Sort by modification time
- **size**: Sort by file size

Sources: [frontend/ui/widget/filechooser.lua152-308](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/filechooser.lua#L152-L308)

### Path Navigation

Navigation state is preserved in `path_items` hash table:
MethodPurposeUpdates `path_items``changeToPath(path, focused_path)`Navigate to new pathVia `refreshPath()``refreshPath()`Reload current directoryYes, stores scroll position`goHome()`Navigate to home directoryVia `changeToPath()``onFolderUp()`Navigate to parentVia `changeToPath()`
Sources: [frontend/ui/widget/filechooser.lua315-379](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/filechooser.lua#L315-L379)

---

## Module System

### Module Registration

Modules are registered via `registerModule(name, ui_module, always_active)`:

```
FileManager

registerModule()

Set self[name] = module

Set module.name = 'filemanager' + name

Insert into self array

If always_active: insert into active_widgets
```

**Always-active modules** receive events even when FileManager is hidden (e.g., when ReaderUI is open).

Sources: [frontend/apps/filemanager/filemanager.lua385-395](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanager.lua#L385-L395)

### Core Modules
Module NameClassPurposeAlways Active`screenshot``Screenshoter`Screen captureYes`menu``FileManagerMenu`Main menuNo`history``FileManagerHistory`Reading history browserNo`bookinfo``FileManagerBookInfo`Book metadata viewerNo`collections``FileManagerCollection`Collection managementNo`filesearcher``FileManagerFileSearcher`File searchNo`folder_shortcuts``FileManagerShortcuts`Quick folder accessNo`dictionary``ReaderDictionary`Word lookup (from FM)No`wikipedia``ReaderWikipedia`Wikipedia lookup (from FM)No
Sources: [frontend/apps/filemanager/filemanager.lua401-416](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanager.lua#L401-L416)

### Module Communication

Modules communicate through:

1. **Direct method calls**: `self.ui.collections:genAddToCollectionButton()`
2. **Event broadcasting**: `UIManager:broadcastEvent(Event:new("PathChanged", path))`
3. **Shared state**: `self.ui.selected_files`, `self.ui.clipboard`

---

## File Operations

### File Dialog System

The file dialog is generated dynamically in `FileChooser:showFileDialog(item)`:

```
is_file

is_folder

User long-press

showFileDialog()

Check item.is_file

Generate file buttons

Generate folder buttons

Paste, Select, Rename

Delete, Cut, Copy

Status buttons (if opened)

Book info, Cover, Description

Set as HOME, Add shortcut

ButtonDialog:new
```

Sources: [frontend/apps/filemanager/filemanager.lua181-346](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanager.lua#L181-L346)

### Button Generation Helpers

`filemanagerutil` provides button generators used across FileManager, History, Collections, and Search:
FunctionPurposeReturns`genStatusButtonsRow()`Reading, Abandoned, Complete buttonsButton row table`genResetSettingsButton()`Reset doc settings with checkboxesSingle button`genShowFolderButton()`Navigate to file's folderSingle button`genBookInformationButton()`Open book info dialogSingle button`genBookCoverButton()`Display book coverSingle button`genBookDescriptionButton()`Show book descriptionSingle button`genExecuteScriptButton()`Execute shell/python scriptSingle button
Sources: [frontend/apps/filemanager/filemanagerutil.lua114-323](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagerutil.lua#L114-L323)

### Clipboard Operations

FileManager maintains a clipboard for copy/cut/paste operations:

```
cutFile(file)

copyFile(file)

self.clipboard = file
self.cutfile = true

self.clipboard = file
self.cutfile = false

pasteFileFromClipboard(dest)

if self.cutfile

if not self.cutfile

Update DocSettings,
ReadHistory,
ReadCollection

Copy DocSettings

Idle

Cut

Copy

Clipboard

Paste

Move

CopyOp

UpdateRefs
```

The paste operation updates all references to the file:

Sources: [frontend/apps/filemanager/filemanager.lua910-962](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanager.lua#L910-L962)

### Batch Operations (Select Mode)

Select mode enables batch file operations:

```
ButtonDialog
FileChooser
FileManager
User
ButtonDialog
FileChooser
FileManager
User
Toggle select mode
self.selected_files = {}
Update title bar icon to "check"
Tap file
Toggle selection
selected_files[path] = true/nil
item.dim = true/nil
updateItems()
Tap plus menu
Show select mode dialog
Delete, Move, Copy, etc.
Choose "Delete"
deleteSelectedFiles()
Iterate selected_files
Update all references
```

Sources: [frontend/apps/filemanager/filemanager.lua510-525](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanager.lua#L510-L525)[filemanager.lua527-769](https://github.com/koreader/koreader/blob/9e6b1587/filemanager.lua#L527-L769)

---

## Data Persistence

### Three-Tier Storage System

```
Per-Document Data

Collection Data

Global Data

referenced by

referenced by

owns

tracks

stores

contains

ReadHistory
history.lua
(chronological list)

ReadCollection
collection.lua
(user-organized groups)

DocSettings
book.pdf.sdr/
metadata.lua

Document File

Last read time

Collection membership

Bookmarks, highlights,
reading position,
book status
```

Sources: [frontend/readhistory.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/readhistory.lua) (not in provided files), [frontend/readcollection.lua1-379](https://github.com/koreader/koreader/blob/9e6b1587/frontend/readcollection.lua#L1-L379)[frontend/docsettings.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/docsettings.lua) (not in provided files)

### ReadCollection Structure

The `ReadCollection` module manages user-created collections:

**Data model:**

```
ReadCollection.coll = {
    ["favorites"] = {
        ["/path/to/book1.epub"] = {
            file = "/path/to/book1.epub",
            text = "book1.epub",
            order = 1,
            attr = { modification = ..., size = ..., access = ... }
        },
        -- more files...
    },
    ["to-read"] = { ... },
    -- more collections...
}
 
ReadCollection.coll_settings = {
    ["favorites"] = {
        order = 1,  -- collection display order
        collate = "strcoll",  -- sort method (nil = manual order)
        folders = { ... }  -- auto-scan folders
    },
    -- more settings...
}
```

**Key operations:**
MethodPurpose`addItem(file, collection_name, attr)`Add file to collection`removeItem(file, collection_name, no_write)`Remove file from collection`updateItem(file, new_filepath)`Update file path after rename/move`isFileInCollection(file, collection_name)`Check membership`updateCollectionFromFolder(collection_name, folders)`Scan folders for new files
Sources: [frontend/readcollection.lua1-379](https://github.com/koreader/koreader/blob/9e6b1587/frontend/readcollection.lua#L1-L379)

### Collection Auto-Update

Collections can automatically scan folders for new files:

```
File operation
(move, rename, delete)

Update ReadHistory

Update ReadCollection

Update DocSettings

ReadHistory:updateItem()

ReadCollection:updateItem()

DocSettings.updateLocation()

Write history.lua

Write collection.lua

Move .sdr directory
```

Sources: [frontend/apps/filemanager/filemanager.lua932-962](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanager.lua#L932-L962)

---

## History and Collections UI

### FileManagerHistory Module

`FileManagerHistory` displays the reading history using `BookList`:

```
Yes

No

FileManagerHistory

onShowHist()

Create BookList

fetchStatuses()

updateItemTable()

Apply filters?

isItemMatch()

Show all items

Status filter?

Search string?

Collection filter?

Build filtered list

Display BookList
```

**Filter types:**

1. **Status filter**: `reading`, `abandoned`, `complete`, `deleted`, `new`, or `all`
2. **Text search**: Search in filename and book metadata
3. **Collection filter**: Show only files in selected collections
4. **Combination**: All filters can be combined

Sources: [frontend/apps/filemanager/filemanagerhistory.lua1-438](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagerhistory.lua#L1-L438)

### FileManagerCollection Module

`FileManagerCollection` manages user collections with advanced filtering:

**Filter system:**

```
No

Yes

User opens collection

onShowColl()

Load collection items

Filters active?

Display all items

Apply filters

Status filter

Author filter

Series filter

Language filter

Keywords filter

isItemMatch()
```

**Metadata filtering:**

The collection dialog provides metadata-based filtering via `showCollDialog()`:

Sources: [frontend/apps/filemanager/filemanagercollection.lua485-561](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagercollection.lua#L485-L561)

### Select Mode in Collections

Collections support select mode for batch operations:
OperationDescriptionUpdates CollectionsRemove from collectionRemoves selected files from current collectionYesMove to collectionMoves files to different collection(s)Yes (both source and dest)Copy to collectionCopies files to additional collection(s)Yes (destinations only)Select in file browserExits to FileManager with files selectedNo immediate write
Sources: [frontend/apps/filemanager/filemanagercollection.lua330-483](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagercollection.lua#L330-L483)

---

## File Search System

### FileManagerFileSearcher Module

The search system scans directories recursively for files matching a pattern:

```
Home

Current

Match

Match

Yes

No

User invokes search

ShowFileSearch dialog

Enter search pattern
and options

Choose search path

Home directory

Current/book folder

doSearch()

Trapper:dismissableRunInSubprocess

getList()

Iterate directories

For each file

isFileMatch()

Check filename

Check metadata
(if enabled)

Add to results

More files?

Display results in BookList
```

**Search options:**
OptionDefaultEffect`case_sensitive``false`Enable case-sensitive matching`include_subfolders``true`Recursively scan subdirectories`include_metadata``false`Search in book title, author, description (requires metadata extraction)
**Wildcard support:**

- `?` matches any single character
- `*` matches zero or more characters
- `.` is escaped to literal dot
- Example: `J*s Bond` matches "James Bond", "Jonas Bond", etc.

Sources: [frontend/apps/filemanager/filemanagerfilesearcher.lua1-521](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagerfilesearcher.lua#L1-L521)

### Metadata Search Integration

When `include_metadata` is enabled, the search checks book properties via `FileManagerBookInfo:getDocProps()`:

```
Document
CoverBrowser cache
FileManagerBookInfo
FileSearcher
Document
CoverBrowser cache
FileManagerBookInfo
FileSearcher
alt
[Metadata cached]
[Not cached]
getDocProps(file, nil, true)
Check cover browser cache
Return cached props
Open and extract metadata
Return props
Increment no_metadata_count
Return doc_props
findInProps(props, search_string)
Return match result
```

If some books have no extracted metadata, a dialog prompts the user to run metadata extraction.

Sources: [frontend/apps/filemanager/filemanagerfilesearcher.lua235-253](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagerfilesearcher.lua#L235-L253)[filemanagerfilesearcher.lua256-282](https://github.com/koreader/koreader/blob/9e6b1587/filemanagerfilesearcher.lua#L256-L282)

---

## PathChooser Widget

`PathChooser` extends `FileChooser` for folder/file selection dialogs:

**Key differences from FileChooser:**
FeatureFileChooserPathChooserPurposeBrowse files, open documentsSelect path for configurationSelectionTap to open/navigateLong-press to chooseTitle barDynamic current pathStatic instruction textCurrent dir optionNot shown"Long-press here to choose current folder"Return valueOpens file in readerCalls `onConfirm(path)` callback
**Configuration options:**

```
PathChooser:new{
    select_directory = true,  -- Allow selecting folders
    select_file = true,       -- Allow selecting files  
    show_files = true,        -- Show files even if select_file=false
    detailed_file_info = true, -- Show size/date in confirm dialog
    file_filter = function(file) ... end, -- Optional file filter
    onConfirm = function(path) ... end,   -- Callback with selected path
}
```

Sources: [frontend/ui/widget/pathchooser.lua1-189](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/pathchooser.lua#L1-L189)

---

## Integration with ReaderUI

### Bidirectional Navigation

FileManager and ReaderUI can launch each other:

```
ReaderUI
UIManager
FileManager
ReaderUI
UIManager
FileManager
User taps file
User presses back
openFile(path)
onShowingReader()
Set tearing_down flag
broadcastEvent("ShowingReader")
onClose()
ReaderUI:showReader(file)
Create ReaderUI instance
show(reader)
showFileManager(file)
broadcastEvent("ShowingReader")
onClose()
FileManager:showFiles(dir, file)
Create FileManager instance
show(filemanager)
```

**Instance management:** Only one of FileManager or ReaderUI can be active at a time. The `ShowingReader` event triggers cleanup in the outgoing instance before the new instance is created.

Sources: [frontend/apps/filemanager/filemanager.lua835-848](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanager.lua#L835-L848)[frontend/apps/reader/readerui.lua569-579](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/readerui.lua#L569-L579)[readerui.lua599-626](https://github.com/koreader/koreader/blob/9e6b1587/readerui.lua#L599-L626)

### File Opening Flow

When a file is selected in FileManager:

1. **Check provider**: `DocumentRegistry:hasProvider(file)` determines if file is supported
2. **Broadcast event**: `UIManager:broadcastEvent(Event:new("ShowingReader"))`
3. **Close FileManager**: `self:onShowingReader()` sets `tearing_down` flag and closes
4. **Open ReaderUI**: `ReaderUI:showReader(file)` creates new reader instance
5. **Document opening**: ReaderUI initializes document and all reader modules

Sources: [frontend/apps/filemanager/filemanager.lua1006-1039](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanager.lua#L1006-L1039)

---

## Plus Menu and Folder Actions

### Plus Menu Dialog

The plus menu (`onShowPlusMenu()`) provides different options based on context:

**In select mode** (files selected):
ButtonActionEnabledDeleteDelete selected filesWhen count > 0MoveCut files to clipboardWhen count > 0CopyCopy files to clipboardWhen count > 0Set statusMark as reading/abandoned/completeWhen count > 0Reset settingsClear doc settingsWhen count > 0Add to collectionAdd to user collectionsWhen count > 0Export highlightsExport annotationsWhen count > 0 and annotations existDeselect allClear selectionWhen count > 0Select allSelect all files in folderAlwaysExit select modeReturn to normal modeAlwaysShow selected listDisplay selected filesWhen count > 0
**In normal mode** (no selection):
ButtonActionSelect filesEnter select modeNew folderCreate folder dialogPastePaste from clipboard (if clipboard has file)Set as HOMESet current folder as homeGo to HOMENavigate to home folderOpen random documentOpen random file (hold: only unopened)Folder shortcutsShow/manage shortcutsAdd/Remove shortcutToggle shortcut for current folderSwitch to SDCardNavigate to external storage (device-specific)Import files herePlatform file picker (device-specific)
Sources: [frontend/apps/filemanager/filemanager.lua527-780](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanager.lua#L527-L780)

### Folder Shortcuts

`FileManagerShortcuts` provides quick access to frequently used folders:

**Storage format:**

```
G_reader_settings:saveSetting("folder_shortcuts", {
    { text = "Documents", folder = "/mnt/storage/Documents" },
    { text = "Books", folder = "/mnt/storage/Books" },
    -- ...
})
```

**UI indicator:** Folders with shortcuts display `☆` prefix in the title bar and file list mandatory column.

Sources: [frontend/apps/filemanager/filemanagershortcuts.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagershortcuts.lua) (not in provided files but referenced extensively)

---

## Advanced Features

### CoverBrowser Integration

The CoverBrowser plugin (if enabled) enhances FileManager with:

1. **Visual browsing**: Displays book covers in mosaic or list mode
2. **Metadata extraction**: Background job to extract book info and covers
3. **Enhanced dialogs**: Adds "Refresh book info" button to file dialogs
4. **Customized BookList**: Hijacks `booklist_menu` to display with covers

```
No

Yes

FileManager

CoverBrowser
enabled?

Standard FileChooser

CoverMenu
(Mosaic/List)

Background extraction

BookInfoManager

SQLite cache

Schedule updates

Refresh items as
metadata arrives
```

Sources: [plugins/coverbrowser.koplugin/covermenu.lua1-209](https://github.com/koreader/koreader/blob/9e6b1587/plugins/coverbrowser.koplugin/covermenu.lua#L1-L209)

### Document Status Management

Book status is stored in multiple locations:

**Status values:**

- `new`: Never opened
- `reading`: Currently reading
- `abandoned`: Started but abandoned
- `complete`: Finished reading

**Storage locations:**

1. **DocSettings** (`metadata.lua`): `summary.status` field
2. **BookList cache**: In-memory cache for quick access
3. **FileManager state**: Current opened document status

**Status workflows:**
TriggerActionFirst open documentStatus auto-set to "reading"Manual status changeUpdate DocSettings, cache, trigger refreshMark completeCan freeze in history (optional setting)Delete fileStatus preserved in history as "deleted"
Sources: [frontend/apps/filemanager/filemanagerutil.lua114-142](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagerutil.lua#L114-L142)

### File Operation Cascading

When files are moved, renamed, or deleted, updates cascade through all systems:

```
File

File

File

Folder

Folder

Folder

File Operation
(move/rename/delete)

Check file
is_file vs folder

DocSettings.updateLocation()

ReadHistory:updateItem()

ReadCollection:updateItem()

DocSettings: iterate .sdr dirs

ReadHistory:updateItemsByPath()

ReadCollection:updateItemsByPath()

Refresh UI
```

Sources: [frontend/apps/filemanager/filemanager.lua932-962](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanager.lua#L932-L962)

---

## Summary

The FileManager system provides:

1. **File browsing** via `FileChooser` extending `BookList` with directory scanning, filtering, and sorting
2. **Module architecture** for extensible features (history, collections, search, shortcuts, book info)
3. **Three-tier persistence** (global history, user collections, per-document settings)
4. **Batch operations** through select mode with multi-file support
5. **Rich file dialogs** with context-sensitive button generation
6. **Bidirectional navigation** with ReaderUI for seamless document opening
7. **Metadata search** with optional book property filtering
8. **Data consistency** through cascading updates across all storage systems

The modular design allows plugins to extend functionality (e.g., CoverBrowser) while maintaining a consistent user interface pattern based on `BookList` for all list-based views.

---

# Reader-UI-and-Module-System

# Reader UI and Module System
Relevant source files
- [frontend/apps/filemanager/filemanager.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanager.lua)
- [frontend/apps/filemanager/filemanagercollection.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagercollection.lua)
- [frontend/apps/filemanager/filemanagerfilesearcher.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagerfilesearcher.lua)
- [frontend/apps/filemanager/filemanagerhistory.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagerhistory.lua)
- [frontend/apps/filemanager/filemanagerutil.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagerutil.lua)
- [frontend/apps/reader/modules/readerbookmark.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerbookmark.lua)
- [frontend/apps/reader/modules/readerfont.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfont.lua)
- [frontend/apps/reader/modules/readerhighlight.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua)
- [frontend/apps/reader/modules/readerlink.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerlink.lua)
- [frontend/apps/reader/modules/readerpaging.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerpaging.lua)
- [frontend/apps/reader/modules/readerrolling.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerrolling.lua)
- [frontend/apps/reader/modules/readersearch.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readersearch.lua)
- [frontend/apps/reader/modules/readertoc.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readertoc.lua)
- [frontend/apps/reader/modules/readerview.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerview.lua)
- [frontend/apps/reader/readerui.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/readerui.lua)
- [frontend/document/credocument.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/credocument.lua)
- [frontend/readcollection.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/readcollection.lua)
- [frontend/ui/widget/filechooser.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/filechooser.lua)
- [frontend/ui/widget/pathchooser.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/pathchooser.lua)
- [plugins/coverbrowser.koplugin/covermenu.lua](https://github.com/koreader/koreader/blob/9e6b1587/plugins/coverbrowser.koplugin/covermenu.lua)

This page explains ReaderUI as a modular document reader application. ReaderUI acts as a central hub that coordinates 15+ specialized modules, each handling a distinct aspect of document interaction (rendering, navigation, annotations, search, etc.). The modular architecture allows features to be added independently while sharing core infrastructure.

## ReaderUI as a Modular Hub

`ReaderUI` ([frontend/apps/reader/readerui.lua75-90](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/readerui.lua#L75-L90)) extends `InputContainer` and serves as the orchestrator for all reader functionality. It instantiates and coordinates modules through a registration pattern, provides shared state (document, settings, UI manager), and routes events between modules.

**ReaderUI Module Registration Pattern**

```
Active Modules (always_active=true)

Core Modules (always_active=false)

Module Storage

ReaderUI:init()

registerModule(name, module, always_active)

self[name] = module
(e.g., self.view, self.highlight)

table.insert(self, module)
(module list for events)

table.insert(self.active_widgets, module)
(always receive events)

view: ReaderView

paging: ReaderPaging

rolling: ReaderRolling

highlight: ReaderHighlight

bookmark: ReaderBookmark

screenshot: Screenshoter
```

Sources: [frontend/apps/reader/readerui.lua92-102](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/readerui.lua#L92-L102)[frontend/apps/reader/readerui.lua112-456](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/readerui.lua#L112-L456)

The `registerModule()` method implements three forms of registration:

1. **Named access**: `self[name] = module` allows `self.ui.highlight`, `self.ui.view`, etc.
2. **Event dispatch**: `table.insert(self, module)` adds to widget hierarchy for event propagation
3. **Always-active**: Modules flagged `always_active` receive events even when dialogs are open

**Module Instantiation Sequence**

The initialization order in `ReaderUI:init()` reflects dependency relationships:
OrderModuleTypeReason for Position1`view` (ReaderView)DisplayMust exist before other modules reference it2`link` (ReaderLink)InteractionRegistered before highlight to intercept taps3`highlight` (ReaderHighlight)InteractionRegistered before menu to intercept taps4`menu` (ReaderMenu)UIRegistered after link/highlight for tap precedence5`toc`, `bookmark`, `annotation`NavigationUse view and highlight references6`dictionary`, `wikipedia`ContentUse highlight for text selection7`paging` or `rolling`NavigationDocument type-specific (if/else branch)8`config`ConfigurationNeeds document.configurable check
Sources: [frontend/apps/reader/readerui.lua135-372](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/readerui.lua#L135-L372)[frontend/apps/reader/readerui.lua440-455](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/readerui.lua#L440-L455)

## Core Display Modules

ReaderUI instantiates different display modules based on document type. All documents use `ReaderView` for rendering, while navigation is handled by either `ReaderPaging` (fixed-layout) or `ReaderRolling` (reflowable).

### ReaderView - Rendering Orchestrator

`ReaderView` ([frontend/apps/reader/modules/readerview.lua30-78](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerview.lua#L30-L78)) extends `OverlapGroup` and serves as the paint target for document content and UI overlays (highlights, footer, dogear). It handles coordinate transformations between screen space and document space, manages the rendering pipeline, and contains child widgets for status display.

**ReaderView Core Responsibilities**

```
Child Widgets

State Management

Coordinate Transforms

Rendering Pipeline

ReaderView

paintTo(bb, x, y)

drawPageBackground()

drawSinglePage()

drawScrollPages()

drawPageView()

drawScrollView()

screenToPageTransform(pos)

pageToScreenTransform(page, rect)

getScreenPageArea(page)

self.state
{page, pos, zoom, rotation, offset, bbox}

self.visible_area

self.page_area

self.highlight
{page_boxes, visible_boxes, temp, saved_drawer}

self.dogear (ReaderDogear)

self.footer (ReaderFooter)

self.flipping (ReaderFlipping)
```

Sources: [frontend/apps/reader/modules/readerview.lua80-150](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerview.lua#L80-L150)[frontend/apps/reader/modules/readerview.lua185-295](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerview.lua#L185-L295)

### ReaderPaging - Fixed Layout Navigation

`ReaderPaging` ([frontend/apps/reader/modules/readerpaging.lua29-40](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerpaging.lua#L29-L40)) handles PDF, DjVu, and image documents with discrete pages. It manages page-level navigation, position tracking within pages, and page flipping modes.

**Key Methods and State**
ComponentPurposeCode Reference`current_page`Currently displayed page number[frontend/apps/reader/modules/readerpaging.lua31](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerpaging.lua#L31-L31)`page_positions`Fractional read positions per page[frontend/apps/reader/modules/readerpaging.lua150](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerpaging.lua#L150-L150)`onGotoViewRel(number)`Navigate forward/backward by pages[frontend/apps/reader/modules/readerpaging.lua277-324](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerpaging.lua#L277-L324)`setPagePosition(page, pos)`Store reading position (0.0-1.0)[frontend/apps/reader/modules/readerpaging.lua187-191](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerpaging.lua#L187-L191)`setupTouchZones()`Register tap areas for page turns[frontend/apps/reader/modules/readerpaging.lua96-147](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerpaging.lua#L96-L147)`onTogglePageFlipping()`Quick page browsing mode[frontend/apps/reader/modules/readerpaging.lua205-224](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerpaging.lua#L205-L224)
Sources: [frontend/apps/reader/modules/readerpaging.lua42-94](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerpaging.lua#L42-L94)[frontend/apps/reader/modules/readerpaging.lua149-224](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerpaging.lua#L149-L224)

### ReaderRolling - Reflowable Document Navigation

`ReaderRolling` ([frontend/apps/reader/modules/readerrolling.lua55-85](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerrolling.lua#L55-L85)) handles EPUB, FB2, and other CREngine-rendered documents. It manages XPointer-based positioning, scroll navigation, view mode switching (page/scroll), and partial rerendering for fast chapter-only updates.

**Key Methods and State**
ComponentPurposeCode Reference`xpointer`Current document position (DOM pointer)[frontend/apps/reader/modules/readerrolling.lua61](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerrolling.lua#L61-L61)`current_pos`Scroll position (pixels from top)[frontend/apps/reader/modules/readerrolling.lua58](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerrolling.lua#L58-L58)`partial_rerendering`Enable fast chapter-only rendering[frontend/apps/reader/modules/readerrolling.lua70](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerrolling.lua#L70-L70)`rendering_state`Track rerendering progress[frontend/apps/reader/modules/readerrolling.lua72-79](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerrolling.lua#L72-L79)`_gotoXPointer(xpointer)`Navigate to XPointer position[frontend/apps/reader/modules/readerrolling.lua1062-1079](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerrolling.lua#L1062-L1079)`onPan(arg, ges)`Handle scroll gestures[frontend/apps/reader/modules/readerrolling.lua828-877](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerrolling.lua#L828-L877)
Sources: [frontend/apps/reader/modules/readerrolling.lua87-114](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerrolling.lua#L87-L114)[frontend/apps/reader/modules/readerrolling.lua157-272](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerrolling.lua#L157-L272)

## Interaction Modules

Interaction modules extend `InputContainer` to handle user gestures and actions. Each specializes in a distinct feature (text selection, bookmarks, links, search, etc.) while integrating with shared systems like the annotation database and event dispatcher.

### Module Overview

**Reader Module Ecosystem**

```
Shared Resources

Navigation & Organization

Text Interaction

Document Management

ReaderAnnotation
annotations table

ReaderBack
back_stack

ReaderThumbnail
PageBrowserWidget

ReaderHighlight
onHold(), saveHighlight()

ReaderSearch
onShowFulltextSearchInput()

ReaderDictionary
onLookupWord()

ReaderWikipedia
lookupWikipedia()

ReaderLink
onTap(), location_stack

ReaderToc
fillToc(), getTocTicks()

ReaderBookmark
toggleBookmark()

ReaderGoto
onShowGotoDialog()

ui.annotation.annotations
(unified storage)

ui.view.state, ui.view.highlight

ui.doc_settings
(DocSettings)
```

Sources: [frontend/apps/reader/readerui.lua135-401](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/readerui.lua#L135-L401)

### ReaderHighlight - Text Selection and Highlighting

`ReaderHighlight` ([frontend/apps/reader/modules/readerhighlight.lua28-41](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L28-L41)) manages text selection through hold gestures, displays a context dialog with actions (highlight, note, dictionary, translate, search), and stores highlights in the annotation system.

**Key Components**
ComponentPurposeCode Reference`_highlight_buttons`Dialog button definitions[frontend/apps/reader/modules/readerhighlight.lua89-191](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L89-L191)`onHold(arg, ges)`Start text selection[frontend/apps/reader/modules/readerhighlight.lua1420-1510](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L1420-L1510)`onHoldPan(arg, ges)`Extend selection[frontend/apps/reader/modules/readerhighlight.lua1512-1600](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L1512-L1600)`saveHighlight(toggle)`Create highlight annotation[frontend/apps/reader/modules/readerhighlight.lua1855-1950](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L1855-L1950)`selected_text`Current selection state[frontend/apps/reader/modules/readerhighlight.lua1420-1510](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L1420-L1510)`highlight_colors`Color options table[frontend/apps/reader/modules/readerhighlight.lua30-40](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L30-L40)
Sources: [frontend/apps/reader/modules/readerhighlight.lua76-257](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L76-L257)[frontend/apps/reader/modules/readerhighlight.lua1420-1950](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L1420-L1950)

### ReaderBookmark - Page Bookmarks

`ReaderBookmark` ([frontend/apps/reader/modules/readerbookmark.lua28-40](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerbookmark.lua#L28-L40)) manages page-level bookmarks, integrates with dogear visual indicators, and provides bookmark navigation. Bookmarks are stored in the unified annotation system.

**Key Methods**
MethodPurposeCode Reference`onToggleBookmark()`Add/remove bookmark at current page[frontend/apps/reader/modules/readerbookmark.lua354-375](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerbookmark.lua#L354-L375)`setDogearVisibility(page)`Show/hide dogear indicator[frontend/apps/reader/modules/readerbookmark.lua403-418](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerbookmark.lua#L403-L418)`onGotoNextBookmark(paging_forward)`Navigate to next bookmark[frontend/apps/reader/modules/readerbookmark.lua449-473](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerbookmark.lua#L449-L473)`getDogearBookmarkIndex()`Binary search for current page bookmark[frontend/apps/reader/modules/readerbookmark.lua378-400](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerbookmark.lua#L378-L400)
Sources: [frontend/apps/reader/modules/readerbookmark.lua42-75](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerbookmark.lua#L42-L75)[frontend/apps/reader/modules/readerbookmark.lua354-473](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerbookmark.lua#L354-L473)

### ReaderLink - Hyperlink Navigation

`ReaderLink` ([frontend/apps/reader/modules/readerlink.lua65-70](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerlink.lua#L65-L70)) detects taps on document links, handles internal navigation and external URLs, and maintains a location history stack for back/forward navigation.

**Key Components**
ComponentPurposeCode Reference`location_stack`Back navigation history[frontend/apps/reader/modules/readerlink.lua66](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerlink.lua#L66-L66)`onTap(arg, ges)`Detect and follow links[frontend/apps/reader/modules/readerlink.lua241-276](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerlink.lua#L241-L276)`onGotoLink(link)`Navigate to internal/external link[frontend/apps/reader/modules/readerlink.lua353-428](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerlink.lua#L353-L428)`addCurrentLocationToStack()`Save position before navigation[frontend/apps/reader/modules/readerlink.lua144-156](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerlink.lua#L144-L156)`_external_link_buttons`External link action definitions[frontend/apps/reader/modules/readerlink.lua143-285](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerlink.lua#L143-L285)
Sources: [frontend/apps/reader/modules/readerlink.lua72-137](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerlink.lua#L72-L137)[frontend/apps/reader/modules/readerlink.lua241-428](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerlink.lua#L241-L428)

### ReaderSearch - Full-Text Search

`ReaderSearch` ([frontend/apps/reader/modules/readersearch.lua22-41](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readersearch.lua#L22-L41)) provides full-text search within documents, displays paginated results, and highlights matches on the current page.

**Key Methods**
MethodPurposeCode Reference`onShowFulltextSearchInput()`Show search input dialog[frontend/apps/reader/modules/readersearch.lua176-235](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readersearch.lua#L176-L235)`searchFromStart(direction)`Execute search query[frontend/apps/reader/modules/readersearch.lua281-338](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readersearch.lua#L281-L338)`onShowSearchResults(searchfor)`Display results in Menu widget[frontend/apps/reader/modules/readersearch.lua430-557](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readersearch.lua#L430-L557)`findAllText(search_string)`Find all matches (paginated)[frontend/apps/reader/modules/readersearch.lua341-428](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readersearch.lua#L341-L428)
Sources: [frontend/apps/reader/modules/readersearch.lua43-92](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readersearch.lua#L43-L92)[frontend/apps/reader/modules/readersearch.lua176-557](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readersearch.lua#L176-L557)

### ReaderToc - Table of Contents

`ReaderToc` ([frontend/apps/reader/modules/readertoc.lua25-36](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readertoc.lua#L25-L36)) extracts and displays the document's table of contents, validates TOC structure, provides chapter navigation, and generates progress ticks for the footer.

**Key Methods**
MethodPurposeCode Reference`fillToc()`Load TOC from document[frontend/apps/reader/modules/readertoc.lua166-180](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readertoc.lua#L166-L180)`validateAndFixToc()`Fix ordering issues in malformed TOCs[frontend/apps/reader/modules/readertoc.lua182-263](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readertoc.lua#L182-L263)`getTocTicks()`Generate chapter markers for footer[frontend/apps/reader/modules/readertoc.lua591-670](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readertoc.lua#L591-L670)`onShowToc()`Display TOC menu[frontend/apps/reader/modules/readertoc.lua303-433](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readertoc.lua#L303-L433)`getNextChapter(cur_pageno, to_page)`Navigate to next chapter[frontend/apps/reader/modules/readertoc.lua672-705](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readertoc.lua#L672-L705)
Sources: [frontend/apps/reader/modules/readertoc.lua38-60](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readertoc.lua#L38-L60)[frontend/apps/reader/modules/readertoc.lua166-705](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readertoc.lua#L166-L705)

### ReaderDictionary and ReaderWikipedia

`ReaderDictionary` ([frontend/apps/reader/modules/readerdictionary.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerdictionary.lua)) provides word lookup in installed dictionaries using the StarDict format, while `ReaderWikipedia` ([frontend/apps/reader/modules/readerwikipedia.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerwikipedia.lua)) fetches Wikipedia articles via API. Both integrate with text selection to display definitions/articles in a popup (`DictQuickLookup` widget).

Sources: [frontend/apps/reader/readerui.lua202-215](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/readerui.lua#L202-L215)

## UI and Configuration Modules

UI modules provide user-facing configuration and status display components that integrate with the reader system.

### ReaderMenu - Top Menu Bar

`ReaderMenu` ([frontend/apps/reader/modules/readermenu.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readermenu.lua)) extends `TouchMenu` to provide the tabbed configuration interface. It collects menu items from all registered modules via `addToMainMenu()` callbacks and organizes them into tabs (File, Typeset, Style, etc.).

Sources: [frontend/apps/reader/readerui.lua160-163](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/readerui.lua#L160-L163)

### ReaderConfig - Quick Settings Panel

`ReaderConfig` ([frontend/apps/reader/modules/readerconfig.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerconfig.lua)) displays a bottom panel with frequently-used document rendering options (font, margins, contrast). It uses `ConfigDialog` to show options specific to the document type (CRE vs KOpt).

Sources: [frontend/apps/reader/readerui.lua230-236](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/readerui.lua#L230-L236)

### ReaderFooter - Status Display

`ReaderFooter` ([frontend/apps/reader/modules/readerfooter.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua)) extends `WidgetContainer` to display reading progress, page numbers, time, battery status, and other information at the bottom of the screen. It is registered as a child widget of `ReaderView` and uses `FooterContainer` for layout.

**Footer Item Types**
ItemDisplaysCode Reference`page_progress`Page X of Y[frontend/apps/reader/modules/readerfooter.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua)`percentage`Reading progress %[frontend/apps/reader/modules/readerfooter.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua)`time`Current time[frontend/apps/reader/modules/readerfooter.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua)`battery`Battery status[frontend/apps/reader/modules/readerfooter.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua)`chapter_title`Current chapter name[frontend/apps/reader/modules/readerfooter.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua)`book_time_to_read`Estimated time remaining[frontend/apps/reader/modules/readerfooter.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua)
Sources: [frontend/apps/reader/modules/readerview.lua131-134](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerview.lua#L131-L134)

## Module Communication Patterns

Modules communicate through events, shared state access, and touch zone priority ordering. This loose coupling allows independent development while maintaining coordinated behavior.

### Event System

**Common Events**
EventEmitted ByHandlersPurpose`PageUpdate`ReaderPagingReaderBookmark, ReaderToc, ReaderFooterUpdate page-dependent state`PosUpdate`ReaderRollingReaderBookmark, ReaderFooterUpdate position-dependent state`AnnotationsModified`ReaderHighlight, ReaderBookmarkReaderView, Statistics pluginsRefresh displays after annotation changes`TocReset`ReaderTocBookMap plugin, StatisticsReload TOC-dependent features`SetDimensions`ReaderUIAll modulesNotify of screen size changes`ReaderReady`ReaderUIAll modulesDocument fully loaded and rendered
Sources: [frontend/apps/reader/readerui.lua459-499](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/readerui.lua#L459-L499)

### Touch Zone Priority

Modules register touch zones with `overrides` to control gesture precedence:

```
-- ReaderLink overrides highlight and page turning
self.ui:registerTouchZones({
    {
        id = "tap_link",
        ges = "tap",
        overrides = {
            "readerhighlight_tap",
            "tap_forward", 
            "tap_backward",
        },
        handler = function(ges) return self:onTap(_, ges) end
    }
})
```

Touch zones are checked in reverse registration order, with `overrides` allowing higher-priority modules to intercept gestures.

Sources: [frontend/apps/reader/modules/readerlink.lua74-114](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerlink.lua#L74-L114)

### Shared State Access

**Module Cross-References**

```
Module References

View State

Document State

Reader Module

self.ui.document
(Document instance)

self.ui.doc_settings
(DocSettings)

self.ui.view
(ReaderView)

view.state
{page, zoom, rotation}

view.highlight
{saved_drawer, saved_color}

self.ui.annotation
(.annotations table)

self.ui.toc
(.toc table)

self.ui.highlight

self.ui.bookmark
```

Sources: [frontend/apps/reader/readerui.lua135-401](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/readerui.lua#L135-L401)

## Module Initialization and Lifecycle

Reader modules follow a standardized initialization and lifecycle pattern that ensures proper sequencing and dependency management.

### Initialization Sequence

**Standard Module Lifecycle**
PhaseMethodPurposeExample1`init()`Basic setup, register eventsSet up touch zones, initialize state2`onReadSettings(config)`Load document settingsRestore last position, user preferences3`onReaderReady()`Post-initialization setupSetup requiring other modules4RuntimeEvent handling`onPageUpdate()`, `onPosUpdate()`5`onSaveSettings()`Persist settingsSave current position, preferences6`onCloseDocument()`CleanupClear caches, unschedule callbacks
### Module-Specific Initialization

**ReaderView Initialization**

- `addWidgets()`: Creates child widgets (`ReaderDogear`, `ReaderFooter`, `ReaderFlipping`)
- `resetLayout()`: Configures widget layout and dimensions
- Sets up rendering state and highlight system

**ReaderPaging/Rolling Initialization**

- `registerKeyEvents()`: Maps keyboard shortcuts to actions
- `setupTouchZones()`: Defines tap/swipe areas for navigation
- Document mode detection and configuration

**Interaction Module Initialization**

- Touch zone registration with priority handling
- Button dialog configuration (`_highlight_buttons`, `_external_link_buttons`)
- Integration with annotation and settings systems

```
Document Integration

Input Module Init

ReaderView Init

Module init()

addWidgets()

resetLayout()

Initialize rendering state

registerKeyEvents()

setupTouchZones()

Configure dialog buttons

onReadSettings()

onReaderReady()

fillToc() / loadSettings()
```

Sources: `readerview.lua:80-124`, `readerpaging.lua:42-49`, `readerrolling.lua:87-114`, `readerhighlight.lua:55-233`, `readertoc.lua:85-95`

## Settings and Configuration Management

Each reader module manages configuration through a two-tier system: global application settings and document-specific settings.

### Settings Architecture

**Global vs Document Settings**

```
Module Configuration

Document Settings (ui.doc_settings)

Global Settings (G_reader_settings)

Settings System

UI Preferences

Input Behavior

Feature Toggles

Default Values

Reading Position

Page Positions

TOC Customizations

Module-specific State

Highlight colors, styles

Footer items, layout

TOC display, ignored levels

Link behavior, external handling
```

### Key Configuration Examples

**ReaderPaging Settings**

- `page_positions`: Table mapping page numbers to reading positions
- `last_page`: Most recent page for document restoration
- `flipping_zoom_mode`, `flipping_scroll_mode`: Page flipping preferences

**ReaderHighlight Settings**

- `highlight_drawing_style`: Default highlight appearance ("lighten", "underscore", etc.)
- `highlight_color`: Default highlight color
- `long_press_action`: Behavior for text long-press

**ReaderToc Settings**

- `toc_ticks_ignored_levels`: Which TOC levels to hide from navigation
- `toc_chapter_navigation_bind_to_ticks`: Link chapter nav to TOC structure
- `alternative_toc`: Use generated TOC instead of document TOC

Sources: `readerpaging.lua:149-163`, `readerhighlight.lua:387-683`, `readertoc.lua:85-95`, `readerfooter.lua:474-526`

---

# Menu-System-and-Navigation

# Menu System and Navigation
Relevant source files
- [frontend/apps/filemanager/filemanagermenu.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagermenu.lua)
- [frontend/apps/reader/modules/readerconfig.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerconfig.lua)
- [frontend/apps/reader/modules/readermenu.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readermenu.lua)
- [frontend/ui/widget/menu.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/menu.lua)
- [frontend/ui/widget/touchmenu.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/touchmenu.lua)

This document describes KOReader's menu system architecture, covering the core menu widgets (`Menu` and `TouchMenu`), application-specific menu implementations (`FileManagerMenu`, `ReaderMenu`, `ReaderConfig`), menu item structure, navigation mechanisms, and dynamic menu construction. For information about the action system that populates menu items, see [Action Dispatcher and Profiles](/koreader/koreader/3.5-action-dispatcher-and-profiles). For widget hierarchy and lifecycle, see [Widget System and Base Classes](/koreader/koreader/7.1-widget-system-and-base-classes).

## Menu Widget Architecture

KOReader provides two primary menu widget types that serve different UI contexts:

**Menu Widget Hierarchy**

```
renders

FocusManager

InputContainer

Menu
(Paginated List)

TouchMenu
(Tabbed Interface)

MenuItem
(List Item)

TouchMenuItem
(Tab Item)

TouchMenuBar
(Icon Tabs)

ItemShortCutIcon
```

Sources: [frontend/ui/widget/menu.lua584-637](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/menu.lua#L584-L637)[frontend/ui/widget/touchmenu.lua457-477](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/touchmenu.lua#L457-L477)

### Menu Widget (Paginated List)

The `Menu` widget displays items in a paginated vertical list. It is used for simple, non-tabbed interfaces like file choosers, search results, and modal selection dialogs.

**Key Features:**

- Paginated item display with configurable items per page
- Title bar with optional subtitle and path display
- Footer with page navigation controls (chevron buttons)
- Search functionality (tap page info to search or go to page)
- Return button for multi-level navigation
- Keyboard and D-pad support

**Structure:**

```
Items

Components

Menu Widget

TitleBar
(optional)

VerticalGroup
(item_group)

HorizontalGroup
(page_info + return_button)

MenuItem 1

MenuItem 2

MenuItem ...

page_info_first_chev

page_info_left_chev

page_info_text
(Button with InputDialog)

page_info_right_chev

page_info_last_chev

page_return_arrow
```

Sources: [frontend/ui/widget/menu.lua685-919](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/menu.lua#L685-L919)

**Menu Initialization:**

The menu calculates layout dimensions based on items per page settings and available screen space:

```
self.perpage = G_reader_settings:readSetting("items_per_page") or self.items_per_page_default (14)
self.available_height = self.inner_dimen.h - top_height - bottom_height
self.item_dimen.h = math.floor(self.available_height / perpage)

```

Sources: [frontend/ui/widget/menu.lua647-683](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/menu.lua#L647-L683)

### TouchMenu Widget (Tabbed Interface)

The `TouchMenu` widget provides a tabbed interface with icon-based tab selection. It is the primary menu system for both FileManager and ReaderUI.

**Key Features:**

- Tabbed navigation via icon bar (`TouchMenuBar`)
- Per-tab item tables with independent pagination
- Page navigation with chevron buttons
- Time and battery status display in footer
- Menu stack for hierarchical navigation
- FocusManager integration for keyboard/D-pad navigation

**TouchMenu Components:**
ComponentClassPurposeTab Bar`TouchMenuBar`Icon-based tab selector at topItem Area`VerticalGroup`Current tab's menu itemsFooter`HorizontalGroup`Up button, page info, device infoNavigationButton chevronsPage forward/back, first/last page
Sources: [frontend/ui/widget/touchmenu.lua479-650](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/touchmenu.lua#L479-L650)

**TouchMenu Layout Calculation:**

```
menu_height = screen_height
items_height = menu_height - bar_height - footer_top_margin - footer_height
self.max_per_page = math.floor(items_height / (item_height + split_height))

```

The menu dynamically adjusts its height based on the number of items on the current page, enabling smooth transitions between tabs with different item counts.

Sources: [frontend/ui/widget/touchmenu.lua645-648](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/touchmenu.lua#L645-L648)

## Menu Item Structure

Menu items are represented as Lua tables with specific fields that control their appearance and behavior.

### MenuItem Fields

**Core Fields:**
FieldTypeDescription`text`stringDisplay text (or use `text_func`)`text_func`functionDynamic text generation`callback`functionAction when tapped`callback_func`functionDynamic callback retrieval`sub_item_table`tableSubmenu items`sub_item_table_func`functionDynamic submenu generation`checked`booleanShow checkmark (or use `checked_func`)`checked_func`functionDynamic checked state`enabled`booleanItem enabled state (default: true)`enabled_func`functionDynamic enabled state`keep_menu_open`booleanDon't close menu after callback`separator`booleanShow separator line after item
Sources: [frontend/ui/widget/menu.lua88-100](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/menu.lua#L88-L100)[frontend/ui/widget/touchmenu.lua46-55](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/touchmenu.lua#L46-L55)

**Advanced Fields:**
FieldTypeDescription`hold_callback`functionLong-press action`hold_keep_menu_open`booleanDon't close on hold (default: true)`checkmark_callback`functionTap on checkmark area action`tap_input` / `tap_input_func`string/functionSend input event on tap`hold_input` / `hold_input_func`string/functionSend input event on hold`help_text` / `help_text_func`string/functionShow on hold (if no hold_callback)`menu_item_id`stringUnique ID for navigation`ignored_by_menu_search`booleanExclude from search results
Sources: [frontend/ui/widget/touchmenu.lua839-930](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/touchmenu.lua#L839-L930)

### MenuItem Rendering

`MenuItem` and `TouchMenuItem` both render items with similar layouts but different styling:

```
TouchMenuItem Layout

Checkmark
(CheckMark/RadioMark)

Text
(TextWidget)

MenuItem Layout

Shortcut Icon
(optional)

State Button
(TOC expand/collapse)

Text
(TextWidget or TextBoxWidget)

Post Text
(e.g., page count)

Dot Leaders
(optional)

Mandatory
(right-aligned info)
```

Sources: [frontend/ui/widget/menu.lua102-462](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/menu.lua#L102-L462)[frontend/ui/widget/touchmenu.lua57-159](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/touchmenu.lua#L57-L159)

**MenuItem Text Rendering Options:**

- **Single-line mode** (`single_line=true`): Uses `TextWidget`, truncates with ellipsis if too long
- **Multi-line mode** (default): Uses `TextBoxWidget`, wraps text across multiple lines
- **Font size adjustment** (`multilines_show_more_text=true`): Reduces font size to fit more text
- **Baseline alignment** (`align_baselines=true`): Aligns text and mandatory baselines
- **Dot leaders** (`with_dots=true`): Fills space between text and mandatory with dots

Sources: [frontend/ui/widget/menu.lua143-312](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/menu.lua#L143-L312)

## Application Menu Implementations

KOReader has three main menu implementations that build on the core menu widgets:

### FileManagerMenu

`FileManagerMenu` provides the top-level menu for the file browser application. It organizes settings and tools into five tabs.

**Tab Structure:**

```
FileManagerMenu

filemanager_settings
🗀 File Browser

setting
⚙ Settings

tools
🔧 Tools

search
🔍 Search

main
☰ Main
```

Sources: [frontend/apps/filemanager/filemanagermenu.lua30-61](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagermenu.lua#L30-L61)

**Menu Construction Process:**

1. Initialize base menu structure with tab icons
2. Build static menu items (settings, history, etc.)
3. Register widgets' menu items via `addToMainMenu()`
4. Load common settings from `common_settings_menu_table.lua`
5. Load common info from `common_info_menu_table.lua`
6. Load exit items from `common_exit_menu_table.lua`
7. Sort and merge using `MenuSorter:mergeAndSort()` with `filemanager_menu_order`

Sources: [frontend/apps/filemanager/filemanagermenu.lua155-871](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagermenu.lua#L155-L871)

**Widget Registration:**

Plugins and modules register themselves to add menu items:

```
-- Plugin registration
self.registered_widgets = {}
-- ...
for _, widget in pairs(self.registered_widgets) do
    widget:addToMainMenu(self.menu_items)
end
```

Sources: [frontend/apps/filemanager/filemanagermenu.lua448-453](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagermenu.lua#L448-L453)

### ReaderMenu

`ReaderMenu` provides the main menu for the document reader. It has seven tabs organized by function.

**Tab Structure:**

```
ReaderMenu

navi
🧭 Navigation

typeset
📝 Typeset

setting
⚙ Settings

tools
🔧 Tools

search
🔍 Search

filemanager
🗀 File Browser

main
☰ Main
```

Sources: [frontend/apps/reader/modules/readermenu.lua23-57](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readermenu.lua#L23-L57)

**Reader-Specific Features:**

- Dynamic tab content based on document type (PDF vs EPUB)
- Document settings save/restore
- Integration with reader modules (highlight, bookmark, TOC, etc.)
- "Previous document" navigation
- DjVu render mode menu (for DjVu documents)

Sources: [frontend/apps/reader/modules/readermenu.lua184-384](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readermenu.lua#L184-L384)

### ReaderConfig (Quick Settings Panel)

`ReaderConfig` provides a compact bottom-panel menu for frequently-accessed document rendering settings.

**ReaderConfig vs ReaderMenu:**
AspectReaderConfigReaderMenuWidget`ConfigDialog``TouchMenu`PositionBottom panelFull screenActivationTap/swipe bottomTap/swipe topContentRendering optionsAll settingsSource`CreOptions` or `KoptOptions`All menu itemsPersistence`config_panel_index``readermenu_tab_index`
Sources: [frontend/apps/reader/modules/readerconfig.lua10-197](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerconfig.lua#L10-L197)

**Options Selection:**

```
if self.document.koptinterface ~= nil then
    self.options = KoptOptions  -- PDF/DjVu with reflow
else
    self.options = CreOptions   -- EPUB/FB2/etc with CREngine
end
```

Sources: [frontend/apps/reader/modules/readerconfig.lua15-19](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerconfig.lua#L15-L19)

## Menu Navigation and Interaction

### Gesture-Based Activation

Both FileManagerMenu and ReaderMenu support configurable gesture activation:

**Activation Modes:**
ModeTap BehaviorSwipe Behavior`"tap"`Opens menuDoes nothing`"swipe"`Does nothingOpens menu`"swipe_tap"` (default)Opens menuOpens menu
Setting: `G_reader_settings:readSetting("activate_menu")`

Sources: [frontend/apps/filemanager/filemanagermenu.lua57-60](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagermenu.lua#L57-L60)[frontend/apps/reader/modules/readermenu.lua63-67](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readermenu.lua#L63-L67)

**Touch Zones:**

```
Bottom Zone - DTAP_ZONE_CONFIG

Top Extended - DTAP_ZONE_MENU_EXT

Top Zone - DTAP_ZONE_MENU

Screen Divided into Touch Zones

Tap → FileManagerMenu or ReaderMenu

Swipe South → FileManagerMenu or ReaderMenu

Tap → FileManagerMenu or ReaderMenu

Swipe South → FileManagerMenu or ReaderMenu

Tap → ReaderConfig (Reader only)

Swipe North → ReaderConfig (Reader only)
```

Sources: [frontend/apps/filemanager/filemanagermenu.lua78-131](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagermenu.lua#L78-L131)[frontend/apps/reader/modules/readermenu.lua98-180](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readermenu.lua#L98-L180)[frontend/apps/reader/modules/readerconfig.lua44-126](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerconfig.lua#L44-L126)

### Page Navigation

Menus support multiple navigation mechanisms:

**Navigation Actions:**
ActionGestureKeyboardResultNext PageTap right chevronPgFwd`onNextPage()` → `page + 1`Prev PageTap left chevronPgBack`onPrevPage()` → `page - 1`First PageTap leftmost chevronShift+PgBack`onFirstPage()` → `page = 1`Last PageTap rightmost chevronShift+PgFwd`onLastPage()` → `page = page_num`Swipe LeftSwipe gesture-Next page (cycles)Swipe RightSwipe gesture-Prev page (cycles)MousewheelPan gesture-Next/prev page
Sources: [frontend/ui/widget/menu.lua960-978](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/menu.lua#L960-L978)[frontend/ui/widget/touchmenu.lua512-518](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/touchmenu.lua#L512-L518)[frontend/ui/widget/menu.lua1131-1178](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/menu.lua#L1131-L1178)

**Page Number Calculation:**

```
self.page_num = math.ceil(#self.item_table / self.perpage)
if target_page > self.page_num then
    self.page = self.page_num  -- clamp to valid range
end
```

Sources: [frontend/ui/widget/menu.lua679](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/menu.lua#L679-L679)

### Menu Search

Both `Menu` and `TouchMenu` implement search functionality, allowing users to quickly find menu items by text.

**Menu Search in TouchMenu:**

```
User taps page_info_text

InputDialog opens
'Enter text, letter or page number'

'Search' button

'Go to letter' button

'Go to page' button

search() function

goToMenuItemMatching(text, true)

onGotoPage(page_num)

Recursive traversal of all tabs
and sub_item_tables

Array of [text, icon, path, walk_text]

Display results in new menu

User selects item

openMenu(path, with_animation)

Navigate through tabs and pages
to highlight target item
```

Sources: [frontend/ui/widget/menu.lua782-844](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/menu.lua#L782-L844)[frontend/ui/widget/touchmenu.lua958-1004](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/touchmenu.lua#L958-L1004)

**Search Algorithm:**

The search function recursively walks the entire menu tree:

1. For each tab's `tab_item_table`:

- Iterate through items, checking `text` or `text_func()` against search string
- Recursively check `sub_item_table` or `sub_item_table_func()`
- Skip items marked `ignored_by_menu_search = true`
- Build path as "tab.item.subitem.subsubitem" (e.g., "2.3.1")
- Store matches with indentation markers for display
2. Return found items as array of `{text, icon, path, walk_text}`

Sources: [frontend/ui/widget/touchmenu.lua963-996](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/touchmenu.lua#L963-L996)

**Menu Navigation After Search:**

When a search result is selected, `openMenu(path, with_animation)` navigates to it:

```
Parse path (e.g., "2.3.1")

Highlight tab icon (if animation)

Switch to tab, get item number

Wrong page & animation

Correct page

Show chevron highlight

Navigate to next/prev page

Intermediate item (has submenu)

Final item (no submenu)

Open submenu, get next item

START

HIGHLIGHT_TAB

OPEN_TAB

CHECK_PAGE

HIGHLIGHT_CHEV

HIGHLIGHT_ITEM

NAV_PAGE

ENTER_ITEM

DONE
```

Sources: [frontend/ui/widget/touchmenu.lua1006-1152](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/touchmenu.lua#L1006-L1152)

### Hierarchical Navigation

Menus support nested submenus with breadcrumb-style navigation:

**Menu Stack:**

```
-- Entering submenu
table.insert(self.item_table_stack, self.item_table)
self.parent_id = item.menu_item_id
self.item_table = sub_item_table
self:updateItems(1)
 
-- Returning to parent
self.item_table = table.remove(self.item_table_stack)
self:updateItems(1, self.parent_id)
```

The `parent_id` is preserved to scroll the parent menu back to the item that opened the submenu.

Sources: [frontend/ui/widget/touchmenu.lua861-866](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/touchmenu.lua#L861-L866)[frontend/ui/widget/touchmenu.lua779-791](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/touchmenu.lua#L779-L791)

**Back Navigation:**
MethodEffectTap up arrow button`backToUpperMenu()`Swipe south`backToUpperMenu(true)` (keep menu open)Back key`onBack()` → `backToUpperMenu()`Menu key`onClose()` → close menu
Sources: [frontend/ui/widget/touchmenu.lua592-594](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/touchmenu.lua#L592-L594)[frontend/ui/widget/touchmenu.lua778-795](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/touchmenu.lua#L778-L795)[frontend/ui/widget/touchmenu.lua823-836](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/touchmenu.lua#L823-L836)

## Dynamic Menu Construction

### Widget Registration

Plugins and reader modules dynamically register menu items:

**Registration Pattern:**

```
-- In plugin or module
function MyWidget:addToMainMenu(menu_items)
    menu_items.my_feature = {
        text = "My Feature",
        callback = function() self:doSomething() end,
    }
end
 
-- In FileManagerMenu or ReaderMenu
function Menu:registerToMainMenu(widget)
    table.insert(self.registered_widgets, widget)
end
 
-- During menu construction
for _, widget in pairs(self.registered_widgets) do
    widget:addToMainMenu(self.menu_items)
end
```

Sources: [frontend/apps/filemanager/filemanagermenu.lua448-453](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagermenu.lua#L448-L453)[frontend/apps/reader/modules/readermenu.lua185-190](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readermenu.lua#L185-L190)[frontend/apps/reader/modules/readermenu.lua553-555](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readermenu.lua#L553-L555)

### Menu Sorting

Menu items are organized using `MenuSorter:mergeAndSort()` with predefined order tables:

**Sorting Process:**

```
Tab Item Table Structure

menu_items
(flat table of all items)

Menu Order Table
(ui/elements/filemanager_menu_order.lua
or ui/elements/reader_menu_order.lua)

MenuSorter:mergeAndSort()

tab_item_table
(array of tabs, each with sub_item_table)

Tab 1: {icon, sub_item_table = [...]}

Tab 2: {icon, sub_item_table = [...]}

Tab 3: {icon, sub_item_table = [...]}
```

Sources: [frontend/apps/filemanager/filemanagermenu.lua867-870](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagermenu.lua#L867-L870)[frontend/apps/reader/modules/readermenu.lua372-375](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readermenu.lua#L372-L375)

**Order Table Example:**

```
-- Partial structure from filemanager_menu_order
{
    filemanager_settings = {
        icon = "appbar.filebrowser",
        sub_item_table = {
            "filebrowser_settings",
            "show_filter",
            "sort_by",
            "reverse_sorting",
            -- ...
        }
    },
    setting = {
        icon = "appbar.settings",
        sub_item_table = {
            "language",
            "font_settings",
            -- ...
        }
    },
    -- ...
}
```

Items not in the order table are appended in undefined order. The `icon` field from `menu_items` overrides the one in the order table if both exist.

Sources: Implied by usage in [frontend/apps/filemanager/filemanagermenu.lua867-870](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagermenu.lua#L867-L870)

### Integration with Dispatcher

Menu items can be automatically generated from Dispatcher actions:

**Dispatcher-Based Menu Items:**

Certain menu sections (like "Profiles" or "Gestures") query the Dispatcher for available actions and build menu items dynamically:

```
-- Example pattern (conceptual)
local dispatcher_actions = Dispatcher:getAvailableActions()
for action_id, action_data in pairs(dispatcher_actions) do
    menu_items[action_id] = {
        text = action_data.text,
        callback = function()
            Dispatcher:execute(action_id)
        end,
    }
end
```

This allows plugins to register actions with Dispatcher, which then automatically appear in menus. For details, see [Action Dispatcher and Profiles](/koreader/koreader/3.5-action-dispatcher-and-profiles).

Sources: Implied by architecture in Diagram 4 of high-level overview

## Menu Configuration

### Items Per Page

Users can configure the number of items displayed per menu page:

**Settings:**
Setting KeyScopeDefaultRange`items_per_page`File browser, menus146-30`items_font_size`File browser, menusAuto-calculated10-72`items_multilines_show_more_text`File browserfalseboolean`keyvalues_per_page`Info lists (portrait)Auto10-30`keyvalues_per_page_landscape`Info lists (landscape)Auto10-30
Sources: [frontend/apps/filemanager/filemanagermenu.lua177-244](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagermenu.lua#L177-L244)[frontend/apps/filemanager/filemanagermenu.lua398-443](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagermenu.lua#L398-L443)

**Font Size Calculation:**

If `items_font_size` is not set, the default is calculated based on `items_per_page`:

```
function Menu.getItemFontSize(items_per_page)
    return math.floor(24 - (items_per_page - 6) * 0.6)
end
```

Higher items per page → smaller font size for better fit.

Sources: Implied by pattern in [frontend/apps/filemanager/filemanagermenu.lua207-214](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagermenu.lua#L207-L214)

### Visual Highlighting

Menus support visual feedback for item selection:

**Flash UI Setting:**

```
if G_reader_settings:isFalse("flash_ui") then
    -- No highlight, immediate callback
    self.menu:onMenuSelect(self.item, tap_on_checkmark)
else
    -- Highlight → wait → unhighlight → callback
    self.item_frame.invert = true
    UIManager:widgetInvert(self.item_frame, x, y, w)
    UIManager:setDirty(nil, "fast", dimen)
    UIManager:forceRePaint()
    UIManager:yieldToEPDC()
    -- Unhighlight and execute
end
```

Sources: [frontend/ui/widget/menu.lua519-544](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/menu.lua#L519-L544)[frontend/ui/widget/touchmenu.lua190-221](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/touchmenu.lua#L190-L221)

### Menu Activation

**Touch Zone Configuration:**

Touch zones are defined in `G_defaults` and can be extended:

- `DTAP_ZONE_MENU`: Standard top menu zone
- `DTAP_ZONE_MENU_EXT`: Extended top menu zone (larger tap area)
- `DTAP_ZONE_CONFIG`: Bottom config menu zone (ReaderUI only)
- `DTAP_ZONE_CONFIG_EXT`: Extended bottom config zone

The extended zones override the standard zones, allowing for larger tap targets.

Sources: [frontend/apps/filemanager/filemanagermenu.lua81-131](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagermenu.lua#L81-L131)[frontend/apps/reader/modules/readermenu.lua101-180](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readermenu.lua#L101-L180)

---

# Document-System

# Document System
Relevant source files
- [frontend/apps/reader/modules/readerbookmark.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerbookmark.lua)
- [frontend/apps/reader/modules/readerfont.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfont.lua)
- [frontend/apps/reader/modules/readerhighlight.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua)
- [frontend/apps/reader/modules/readerlink.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerlink.lua)
- [frontend/apps/reader/modules/readerpaging.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerpaging.lua)
- [frontend/apps/reader/modules/readerrolling.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerrolling.lua)
- [frontend/apps/reader/modules/readertoc.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readertoc.lua)
- [frontend/apps/reader/modules/readerview.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerview.lua)
- [frontend/document/credocument.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/credocument.lua)
- [frontend/document/djvudocument.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/djvudocument.lua)
- [frontend/document/document.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/document.lua)
- [frontend/document/documentregistry.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/documentregistry.lua)
- [frontend/document/koptinterface.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/koptinterface.lua)
- [frontend/document/pdfdocument.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/pdfdocument.lua)
- [frontend/document/picdocument.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/picdocument.lua)

## Purpose and Scope

The Document System provides file format support and rendering infrastructure for KOReader. It abstracts document opening, rendering, and navigation across multiple engines (MuPDF for PDF/DjVu, CREngine for EPUB/MOBI, and image decoders). The system handles provider registration, format detection, tile-based rendering with caching, and optional page reflow via k2pdfopt.

For information about document navigation and reading features, see [Reading Features and Interaction](/koreader/koreader/6-reading-features-and-interaction). For the reader UI that displays documents, see [Reader UI and Module System](/koreader/koreader/4.2-reader-ui-and-module-system).

## Architecture Overview

The Document System consists of three layers:

1. **Provider Registration** (`DocumentRegistry`) - Maps file extensions to document handlers
2. **Document Engines** - Format-specific implementations (`PdfDocument`, `CreDocument`, `DjvuDocument`, `PicDocument`)
3. **Rendering Pipeline** - Tile caching, reflow (`KoptInterface`), and optimization (`DocCache`)

```
Rendering Layer

Document Engine Layer

Provider Layer

openDocument(file)

DocumentRegistry

Provider Mappings
extension → provider
mimetype → extension

Document
(Base Class)

PdfDocument
MuPDF backend

CreDocument
CREngine backend

DjvuDocument
DjVuLibre backend

PicDocument
Image decoder

KoptInterface
Reflow & OCR

DocCache
LRU Cache

TileCacheItem
Rendered Tiles

ContextCacheItem
Reflow Contexts
```

**Sources:**[frontend/document/documentregistry.lua1-270](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/documentregistry.lua#L1-L270)[frontend/document/document.lua13-87](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/document.lua#L13-L87)[frontend/document/koptinterface.lua1-31](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/koptinterface.lua#L1-L31)

## Document Registry and Provider Selection

### Provider Registration

`DocumentRegistry` maintains mappings from file extensions and MIME types to document provider classes. Providers register themselves with weighted priorities to handle file format conflicts.

```
Registry Tables

Registration Process

Document Provider
(PdfDocument, etc.)

register(registry)

addProvider(ext, mime, provider, weight)

providers[]
Array of {ext, mime, provider, weight}

filetype_provider{}
Hash: ext → true

mimetype_ext{}
Hash: mime → ext

known_providers{}
Hash: provider_key → provider
```

**Sources:**[frontend/document/documentregistry.lua31-47](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/documentregistry.lua#L31-L47)

### Provider Selection Algorithm

When opening a document, `DocumentRegistry` selects a provider using this precedence:

1. **Per-document association** - Stored in `DocSettings` sidecar file at `metadata.lua``provider` key
2. **Per-filetype association** - Global setting in `G_reader_settings``provider` table by extension
3. **Highest weighted provider** - From registered providers matching the file extension
4. **Fallback provider** - Text provider for unknown formats

```
Yes

No

Yes

No

Yes

No

Yes

No

Yes

No

openDocument(file)

DocSettings
has provider?

Load from
DocSettings:readSetting('provider')

G_reader_settings
has provider[ext]?

Provider
registered?

Return provider

Load from
G_reader_settings:readSetting('provider')[ext]

Get providers
matching extension

Provider
registered?

Return provider

Sort by weight
highest first

Any providers
found?

Return providers[1]

Return fallback
(txt provider)

Provider
```

**Sources:**[frontend/document/documentregistry.lua87-152](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/documentregistry.lua#L87-L152)

### Document Opening Lifecycle

The `DocumentRegistry:openDocument()` method manages document instances with reference counting to support multiple views of the same file:
StepMethodDescription1`getProvider(file)`Select provider using precedence rules2`openDocument(file)`Check `registry` table for existing instance3Provider constructorCreate new Document instance if not cached4`init()`Initialize document engine, load metadata5Increment refcountTrack number of active references
```
Native Engine
Document Provider
registry{}
DocumentRegistry
Application
Native Engine
Document Provider
registry{}
DocumentRegistry
Application
alt
[Document already open]
[First open]
openDocument(file)
getProvider(file)
Check registry[file]
Return cached instance
Increment refcount
Return document
new{file=file}
_init()
init()
openDocument(file)
Engine handle
_readMetadata()
Document instance
registry[file] = {doc, refcount=1}
Return document
```

**Sources:**[frontend/document/documentregistry.lua180-220](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/documentregistry.lua#L180-L220)[frontend/document/document.lua100-125](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/document.lua#L100-L125)

## Document Providers and Engines

### Base Document Class

The `Document` class defines the abstract interface that all providers implement. It manages document state, rendering configuration, and provides utility methods.

**Key Properties:**
PropertyTypePurpose`file`stringDocument file path`_document`userdataNative engine handle`is_open`booleanDocument successfully opened`is_locked`booleanRequires password unlock`render_color`booleanColor rendering enabled`hw_dithering`booleanHardware dithering enabled`sw_dithering`booleanSoftware dithering enabled`info`tableDocument metadata (has_pages, number_of_pages, doc_height)`configurable`ConfigurableSettings manager
**Key Methods:**

```
-- Lifecycle
Document:init()                              -- Override in subclass
Document:close()                             -- Decrements refcount, closes if zero
Document:unlock(password)                    -- Authenticate locked document
 
-- Page dimensions
Document:getNativePageDimensions(pageno)     -- Returns Geom{w, h} for page
Document:getPageDimensions(pageno, zoom, rot) -- With zoom/rotation applied
 
-- Rendering
Document:renderPage(pageno, rect, zoom, rot, gamma, hinting)
Document:drawPage(bb, x, y, area, pageno, zoom, rot, gamma)
 
-- Metadata
Document:getProps()                          -- Returns {title, authors, series, ...}
Document:_readMetadata()                     -- Populate info table
```

**Sources:**[frontend/document/document.lua16-202](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/document.lua#L16-L202)

### PDF Document (MuPDF)

`PdfDocument` uses MuPDF for PDF rendering and supports optional reflow via k2pdfopt.

```
Configurable Options

Rendering Modes

PdfDocument Structure

PdfDocument

ffi/mupdf
MuPDF bindings

KoptInterface
Reflow engine

Direct Rendering
Native PDF layout

Reflowed Rendering
text_wrap = 1

Optimized Rendering
page_opt = 1

text_wrap
0 or 1

page_opt
0 or 1

auto_straighten
0-10

trim_page
0, 1, 2
```

**Capabilities:**
FeatureSupportNotesColor renderingYesVia `setColorRendering()`Reflowable layoutOptionalVia k2pdfopt when `text_wrap = 1`Page optimizationYesDe-skew, trim, enhance via KoptInterfaceText extractionYes`getPageText()`, `getTextBoxes()`OCRYesVia `koptinterface` with TesseractLinksYesInternal and external linksAnnotationsYesHighlights, notes (writable to PDF)Password protectionYes`needsPassword()`, `authenticatePassword()`
**Sources:**[frontend/document/pdfdocument.lua14-46](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/pdfdocument.lua#L14-L46)[frontend/document/pdfdocument.lua88-99](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/pdfdocument.lua#L88-L99)

### CRE Document (CREngine)

`CreDocument` uses CREngine (CoolReader Engine) for EPUB, FB2, MOBI, HTML, RTF, and CHM formats. It provides advanced typesetting with CSS support.

```
Font Management

Rendering Pipeline

View Modes

CreDocument Architecture

CreDocument

libs/libkoreader-cre
CREngine C++ library

LVDocView
Document + View

PAGE_VIEW_MODE = 1
Discrete pages

SCROLL_VIEW_MODE = 0
Continuous scroll

loadDocument(file, only_metadata)

setupDefaultView()

renderDocument()

drawCurrentViewByPage/Pos()

registerFont(path)

setupFallbackFontFaces()

adjustFontSizes(dpi)
```

**Key Features:**
FeatureDescriptionView modes`PAGE_VIEW_MODE` (paginated) or `SCROLL_VIEW_MODE` (continuous)CSS stylingSupports external CSS (`epub.css`, `fb2.css`) and embedded stylesFont renderingFreeType with fallback font cascadingHyphenationSoft hyphen dictionary support (`data/hyph/`)DOM versionsBackward compatibility for older DOM structuresImage scalingVia `crengine.render.dpi` settingNight modeInverts images automaticallyNonlinear flowsCan hide EPUB nonlinear content (footnotes, sidebars)
**Document Loading:**

1. **Engine initialization** (once): `engineInit()` → `cre.newDocView(width, height, view_mode)`
2. **Setup defaults**: `setupDefaultView()` → CSS, fonts, image scaling, cache settings
3. **Load document**: `loadDocument(file, only_metadata)` → Parse file, build DOM
4. **Render**: `renderDocument()` → Paginate and render based on view mode

**Sources:**[frontend/document/credocument.lua21-189](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/credocument.lua#L21-L189)[frontend/document/credocument.lua224-296](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/credocument.lua#L224-L296)[frontend/document/credocument.lua298-326](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/credocument.lua#L298-L326)

### DjVu Document (DjVuLibre)

`DjvuDocument` uses DjVuLibre to render DjVu scanned documents. It supports OCR and reflow like PDF.

**Capabilities:**
FeatureSupportImplementationColor renderingYesVia `setColorRendering()`Text layerYesNative DjVu text annotationsText extractionYes`getPageText()` with hierarchical structureOCRYesVia `koptinterface`ReflowYesVia `koptinterface`LinksYesInternal page links
**Text Extraction Algorithm:**

DjVu text layers are hierarchical (page → columns → regions → paragraphs → lines → words). `getPageTextBoxes()` flattens this into line-oriented word boxes:

1. Walk tree to find `line` nodes or direct word children
2. For lines: collect words, sort by X coordinate
3. For non-line containers with words: group words into lines by Y threshold
4. Compute bounding box for each line from constituent words

**Sources:**[frontend/document/djvudocument.lua5-53](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/djvudocument.lua#L5-L53)[frontend/document/djvudocument.lua159-199](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/djvudocument.lua#L159-L199)

### Pic Document (Images)

`PicDocument` handles standalone image files (GIF, JPEG, PNG, WebP) as single-page documents.

**Supported Formats:**
ExtensionMIME TypeProvider Weightgifimage/gif100jpg, jpegimage/jpeg80pngimage/png80webpimage/webp80
**Behavior:**

- Single page (always `number_of_pages = 1`)
- No text extraction
- No configurability (no zoom, font settings, etc.)
- Hardware or software dithering enforced on e-ink screens

**Sources:**[frontend/document/picdocument.lua6-65](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/picdocument.lua#L6-L65)

## Rendering Pipeline and Caching

### Tile-Based Rendering Architecture

Documents render pages into blitbuffer tiles cached in `DocCache`. This allows reusing rendered content across page turns and zooms.

```
Cache Insert

Render Pipeline

Cache Lookup

Render Request

Yes

No

Yes

No

ReaderView

renderPage(pageno, rect, zoom, rot, gamma)

Generate cache hash
file|page|zoom|rot|gamma|bbox

DocCache:check(hash, TileCacheItem)

Cache hit?

_document:openPage(pageno)

getPagePix(drawcontext)

drawPage(bb, x, y, area, page, zoom, rot, gamma)

TileCacheItem{bb, excerpt, pageno}

Calculate size
stride * height + 512

DocCache:insert(hash, tile)

willAccept(size)?

Return cached tile

Error: tile too large
```

**Cache Key Construction:**

- **PDF/DjVu** (direct): `file|mod_time|render_color|render_mode|page|configurable_hash|bbox|zoom|rotation|gamma|canvas_size`
- **CRE** (reflow): `file|mod_time|render_color|page|xpointer` (zoom/rotation managed by view mode)
- **Kopt reflow**: `file|mod_time|render_color|render_mode|page|configurable_hash|bbox|canvas_size` (includes all KoptOptions)

**Sources:**[frontend/document/document.lua202-251](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/document.lua#L202-L251)[frontend/document/tilecacheitem.lua1-43](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/tilecacheitem.lua#L1-L43)

### Kopt Interface for Reflow

`KoptInterface` wraps libk2pdfopt to provide text reflow and OCR for PDF and DjVu documents.

**Reflow Process:**

1. **Context creation**: `createContext(doc, pageno, bbox)` → Set rendering parameters (zoom, margins, columns, etc.)
2. **Get page pixmap**: `page:getPagePix(kc, render_mode)` → Render native page into context
3. **Reflow**: `k2pdfopt_reflow_bmp(kc)` → Detect text layout, reflow into optimal arrangement
4. **Extract blitbuffer**: `kc:dstToBlitBuffer()` → Get reflowed bitmap
5. **Cache**: Store context in `DocCache` as `ContextCacheItem`

**Reflow Parameters (from `Configurable`):**
ParameterRangePurpose`text_wrap`0, 1Enable reflow`trim_page`0, 1, 2Auto-detect margins (0=off, 1=auto, 2=semi-auto)`detect_indent`0, 1Detect paragraph indents`max_columns`1-4Maximum columns to detect`auto_straighten`0-10De-skew threshold`font_size`0.2-3.0Zoom factor for reflowed text`page_margin`0.0-1.0Margin as fraction of width`line_spacing`0.8-1.5Space between lines`word_spacing`0.05-0.5Space between words`contrast`0.2-2.0Contrast adjustment
**Background Reflow:**

For faster UI response, reflow can happen in a background thread using pthreads:

1. `kc:setPreCache()` → Mark context as background
2. `pthread_create()` → Launch `k2pdfopt_reflow_bmp` in detached thread
3. Main thread continues, polls `kc:isPreCache()` for completion
4. `waitForContext(kc)` → Block until reflow done
5. Enable multi-core CPU during background work, return to single core when done

**Sources:**[frontend/document/koptinterface.lua107-124](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/koptinterface.lua#L107-L124)[frontend/document/koptinterface.lua293-319](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/koptinterface.lua#L293-L319)[frontend/document/koptinterface.lua321-358](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/koptinterface.lua#L321-L358)

### DocCache Architecture

`DocCache` is an LRU (Least Recently Used) cache managing rendered tiles and reflow contexts.

**Cache Structure:**

```
Operations

Cache Items

DocCache Singleton

DocCache

current_memsize

max_memsize
(default 64MB)

cache_order[]
LRU list

cache{}

TileCacheItem
{bb, excerpt, pageno}

ContextCacheItem
{kctx, size}

CacheItem
{data, size}

check(hash, item_class)

insert(hash, item)

willAccept(size)

free(hash)
```

**Cache Eviction:**

When inserting a new item, if `current_memsize + item.size > max_memsize`:

1. Walk `cache_order` from oldest (index 1) to newest
2. Skip items marked `persistent = true`
3. Call `item:onFree()` to clean up resources
4. Remove from `cache_order` and `cache` table
5. Decrement `current_memsize`
6. Repeat until enough space available

**Persistent Cache Items:**

Items marked `persistent = true` can be saved to disk and reloaded:

- **TileCacheItem**: Saves blitbuffer to `{doc_path}.{hash}.png` or raw file
- **ContextCacheItem**: Serializes KOPTContext to `{doc_path}.{hash}.kctx` using Persist/zstd codec

**Sources:**[frontend/document/doccache.lua1-160](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/doccache.lua#L1-L160) (not in provided files, referenced from diagram), [frontend/document/tilecacheitem.lua1-108](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/tilecacheitem.lua#L1-L108)[frontend/document/koptinterface.lua33-78](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/koptinterface.lua#L33-L78)

### Rendering Performance Optimizations

**Memory Pressure Handling:**

When available RAM is low, `DocCache:memoryPressureCheck()` proactively evicts 50% of cache to prevent OOM crashes.

**Multi-Core Rendering:**

During background reflow or page hinting, `CanvasContext:enableCPUCores(2)` boosts to dual-core. Returns to single core when idle to save power.

**Color Rendering Control:**

- `Document:updateColorRendering()` reads `CanvasContext:isColorRenderingEnabled()`
- Grayscale rendering on B&W screens saves memory (8-bit vs 24/32-bit buffers)
- Can be toggled per-document via `render_color` flag

**Dithering Pipeline:**

For e-ink screens with limited grayscale levels:

1. **Hardware dithering**: Set `hw_dithering = true` if device supports it (faster, better quality)
2. **Software dithering**: Set `sw_dithering = true` for 8-bit framebuffers without HW support
3. Dithering flag passed to blitting operations in ReaderView

**Sources:**[frontend/document/document.lua364-372](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/document.lua#L364-L372)[frontend/document/koptinterface.lua450-486](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/koptinterface.lua#L450-L486)

---

# Document-Registry-and-Providers

# Document Registry and Providers
Relevant source files
- [frontend/document/djvudocument.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/djvudocument.lua)
- [frontend/document/document.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/document.lua)
- [frontend/document/documentregistry.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/documentregistry.lua)
- [frontend/document/koptinterface.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/koptinterface.lua)
- [frontend/document/pdfdocument.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/pdfdocument.lua)
- [frontend/document/picdocument.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/picdocument.lua)

## Purpose and Scope

The Document Registry and Provider system manages document format support in KOReader through a pluggable architecture. `DocumentRegistry` acts as a central registry that maps file extensions and MIME types to document provider implementations, handles document instance lifecycle through reference counting, and allows users to configure provider preferences. This page covers provider registration, the document opening lifecycle, provider selection logic, and reference counting.

For information about document rendering pipelines and caching, see [Rendering Pipeline and Caching](/koreader/koreader/5.3-rendering-pipeline-and-caching). For details on specific rendering engines (MuPDF, CREngine, DjVuLibre), see [Document Rendering Engines](/koreader/koreader/5.2-document-rendering-engines).

---

## System Architecture

The Document Registry implements a factory pattern with reference counting, allowing multiple components to share document instances while tracking their usage.

```
Base Class

Document Providers

DocumentRegistry

Client Code

openDocument

openDocument

openDocument

close

close

select via

search in

lookup

create/reuse

decrement refs

extends

extends

extends

extends

instantiate

instantiate

instantiate

instantiate

FileManager

ReaderUI

Other Components

registry table
{file → {doc, refs}}

providers list
[{ext, mime, provider, weight}]

known_providers table
{provider_key → provider}

filetype_provider table
{ext → bool}

openDocument(file, provider)

closeDocument(file)

getProvider(file)

getReferenceCount(file)

PdfDocument
provider='mupdf'

DjvuDocument
provider='djvulibre'

CreDocument
provider='crengine'

PicDocument
provider='picdocument'

Document
Base Class
```

**Sources:**[frontend/document/documentregistry.lua9-26](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/documentregistry.lua#L9-L26)[frontend/document/documentregistry.lua229-256](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/documentregistry.lua#L229-L256)[frontend/document/documentregistry.lua260-272](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/documentregistry.lua#L260-L272)

---

## Provider Registration System

Providers register themselves with `DocumentRegistry` by calling `addProvider` for each file extension/MIME type combination they support.

### Registration Data Structures
TablePurposeStructure`providers`List of all registered providers`[{extension, mimetype, provider, weight}]``filetype_provider`Quick lookup: extension → has provider`{extension → bool}``mimetype_ext`MIME type → canonical extension`{mimetype → extension}``known_providers`Provider key → provider instance`{provider_key → provider}`
**Sources:**[frontend/document/documentregistry.lua9-25](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/documentregistry.lua#L9-L25)

### Registration Method

```
"Registry Tables"
"DocumentRegistry"
"Provider
(PdfDocument)"
"Registry Tables"
"DocumentRegistry"
"Provider
(PdfDocument)"
Called once per provider at startup
loop
[For each supported format]
Provider now available for documents
register(registry)
addProvider(ext, mime, self, weight)
Insert into providers[]
Set filetype_provider[ext] = true
Set mimetype_ext[mime] = ext
Set known_providers[key] = provider
```

**Sources:**[frontend/document/documentregistry.lua31-47](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/documentregistry.lua#L31-L47)[frontend/document/pdfdocument.lua359-405](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/pdfdocument.lua#L359-L405)

### Example: PdfDocument Registration

`PdfDocument` registers for multiple formats with varying priorities (weights):

```
registry:addProvider("pdf", "application/pdf", self, 100)           -- Native
registry:addProvider("epub", "application/epub+zip", self, 50)     -- Lower priority
registry:addProvider("cbz", "application/vnd.comicbook+zip", self, 100) -- Comic archives
registry:addProvider("jpg", "image/jpeg", self, 90)                -- Images via MuPDF

```

Weight determines precedence when multiple providers support the same format. Higher weight = higher priority.

**Sources:**[frontend/document/pdfdocument.lua359-405](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/pdfdocument.lua#L359-L405)

---

## Document Opening Lifecycle

The opening process involves provider selection, instance creation or reuse, and reference counting.

```
"Document Instance"
"Provider
(e.g., PdfDocument)"
"registry table"
"DocumentRegistry"
"Client
(ReaderUI/FileManager)"
"Document Instance"
"Provider
(e.g., PdfDocument)"
"registry table"
"DocumentRegistry"
"Client
(ReaderUI/FileManager)"
Force GC to reclaim memory
Increment reference count
Select provider if not specified
Open file, read metadata
alt
[Document already open]
[Document not open]
openDocument(file, provider)
collectgarbage() x2
Check registry[file]
{doc, refs=N}
refs = N + 1
Return existing doc
getProvider(file)
new(provider, {file=file})
Create instance
init()
Document instance
registry[file] = {doc=doc, refs=1}
Return new doc
```

**Sources:**[frontend/document/documentregistry.lua229-256](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/documentregistry.lua#L229-L256)

### Reference Counting

Each document maintains a reference count. When a document is opened multiple times (e.g., viewing a cover while the book is open in ReaderUI), the same instance is returned with an incremented reference count.

```
registry[file] = {
    doc = <Document instance>,
    refs = <integer count>
}

```

Closing decrements the reference count. When it reaches 0, the document is finalized:

```
Yes

No

Document:close()

DocumentRegistry:closeDocument(file)

refs == 1?

refs = 0
Remove from registry
Return 0

refs = refs - 1
Return refs

Caller finalizes
document._document:close()

Caller preserves
document instance
```

**Sources:**[frontend/document/documentregistry.lua260-272](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/documentregistry.lua#L260-L272)[frontend/document/document.lua104-125](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/document.lua#L104-L125)

---

## Provider Selection Algorithm

Provider selection uses a multi-tier system with user preferences overriding defaults.

```
No

Yes

Yes

No

Yes

No

getProvider(file)

getProviders(file)

Found providers
for extension?

getFallbackProvider()
Returns 'txt' provider

Check per-document
association

DocSettings has
'provider' key?

Return associated
provider

Check per-filetype
association

G_reader_settings
has provider[ext]?

Return filetype
provider

Return highest
weighted provider

Return provider
```

**Sources:**[frontend/document/documentregistry.lua91-104](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/documentregistry.lua#L91-L104)[frontend/document/documentregistry.lua109-140](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/documentregistry.lua#L109-L140)[frontend/document/documentregistry.lua154-179](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/documentregistry.lua#L154-L179)

### Weight-Based Selection

When multiple providers support a format, `getProviders` returns them sorted by weight (highest first):

```
Format: epub
Providers:
  1. CreDocument (weight=100)  ← Selected
  2. PdfDocument (weight=50)

```

Providers can register the same extension multiple times with different weights for different use cases.

**Sources:**[frontend/document/documentregistry.lua109-140](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/documentregistry.lua#L109-L140)

### User Associations

Users can override default provider selection at two levels:
LevelStorageScopePriorityPer-document`<file>.sdr/metadata.lua`Single fileHighestPer-filetype`G_reader_settings` → `provider[ext]`All files of typeMediumDefaultProvider weightFallbackLowest
**Sources:**[frontend/document/documentregistry.lua154-179](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/documentregistry.lua#L154-L179)[frontend/document/documentregistry.lua207-222](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/documentregistry.lua#L207-L222)

---

## Document Class Hierarchy

All document providers extend the `Document` base class, which defines the common interface.

```
Document

+string file

+table _document

+bool is_open

+bool is_locked

+bool is_edited

+table info

+table bbox

+Configurable configurable

+init()

+close() : bool

+getNativePageDimensions(pageno) : Geom

+getPageCount() : int

+renderPage(...) : TileCacheItem

+drawPage(...)

+getTextBoxes(pageno) : table

+getToc() : table

PdfDocument

+string provider = "mupdf"

+string provider_name = "MuPDF"

+bool is_pdf = true

+KoptInterface koptinterface

+init()

+unlock(password) : bool

+saveHighlight(...)

+deleteHighlight(...)

+writeDocument()

+register(registry)

DjvuDocument

+string provider = "djvulibre"

+string provider_name = "DjVu Libre"

+bool is_djvu = true

+KoptInterface koptinterface

+init()

+getPageTextBoxes(pageno) : table

+register(registry)

CreDocument

+string provider = "crengine"

+string provider_name = "CREngine"

+bool is_cre = true

+init()

+loadDocument(...)

+register(registry)

PicDocument

+string provider = "picdocument"

+string provider_name = "Picture Document"

+bool is_pic = true

+init()

+getUsedBBox(pageno) : table

+register(registry)
```

**Sources:**[frontend/document/document.lua16-46](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/document.lua#L16-L46)[frontend/document/pdfdocument.lua14-21](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/pdfdocument.lua#L14-L21)[frontend/document/djvudocument.lua5-15](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/djvudocument.lua#L5-L15)[frontend/document/picdocument.lua6-12](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/picdocument.lua#L6-L12)

### Provider Identifier Pattern

Each provider defines two identification fields:

- `provider`: Machine-readable key (used in settings, registry lookups)
- `provider_name`: Human-readable name (used in UI)

These fields allow `DocumentRegistry` to look up providers by key and display friendly names to users.

**Sources:**[frontend/document/pdfdocument.lua19-20](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/pdfdocument.lua#L19-L20)[frontend/document/djvudocument.lua13-14](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/djvudocument.lua#L13-L14)

---

## Document Initialization Flow

When a provider is instantiated, initialization follows a predictable pattern:

```
"Native Engine
(MuPDF/DjVu/CRE)"
"Provider:init()"
"Document:_init()"
"Provider Constructor"
"DocumentRegistry"
"Native Engine
(MuPDF/DjVu/CRE)"
"Provider:init()"
"Document:_init()"
"Provider Constructor"
"DocumentRegistry"
Document:new() called
Read page count, mod time
alt
[Document needs
password]
[Document accessible]
provider.new({file=file})
_init()
Initialize base state
links, bbox, configurable
info = {has_pages, doc_height}
init()
Load provider-specific libraries
openDocument(file)
Native document handle
Set provider flags
(is_pdf, is_djvu, etc)
updateColorRendering()
is_locked = true
_readMetadata()
is_open = true
Document instance ready
```

**Sources:**[frontend/document/document.lua55-91](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/document.lua#L55-L91)[frontend/document/pdfdocument.lua23-46](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/pdfdocument.lua#L23-L46)[frontend/document/djvudocument.lua27-46](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/djvudocument.lua#L27-L46)

### Base Initialization

`Document:_init()` sets up common infrastructure:

- `self.links = {}`: Page link storage
- `self.bbox = {}`: Manual crop boundaries
- `self.configurable = Configurable:new{}`: Settings interface
- `self.info`: Document metadata (page count, dimensions)
- Color/dithering flags

**Sources:**[frontend/document/document.lua63-87](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/document.lua#L63-L87)

### Provider-Specific Initialization

Each provider's `init()` method:

1. Loads native libraries (e.g., `require("ffi/mupdf")`)
2. Opens the document via FFI (e.g., `pdf.openDocument(self.file)`)
3. Sets provider identification flags
4. Handles password-protection status
5. Calls `_readMetadata()` to populate page count

**Sources:**[frontend/document/pdfdocument.lua23-46](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/pdfdocument.lua#L23-L46)[frontend/document/djvudocument.lua27-46](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/djvudocument.lua#L27-L46)

---

## Closing and Cleanup

Document cleanup must properly handle reference counting to avoid leaking resources or premature cleanup.

```
No

Yes

No

Yes

Component calls
doc:close()

Document:close()

DocumentRegistry:closeDocument(file)

registry[file]
exists?

error('unregistered file')

refs = refs - 1

refs == 0?

Return refs
Preserve instance

registry[file] = nil
Return 0

Document:close()
returns true

is_open = false

_document:close()

_document = nil

Force GC on next open

Document:close()
returns false

Caller keeps instance
alive
```

**Sources:**[frontend/document/document.lua104-125](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/document.lua#L104-L125)[frontend/document/documentregistry.lua260-272](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/documentregistry.lua#L260-L272)

### Special Case: PDF Writing

`PdfDocument` checks if it's the last reference before writing changes:

```
function PdfDocument:close()
    local DocumentRegistry = require("document/documentregistry")
    if DocumentRegistry:getReferenceCount(self.file) == 1 then
        -- We're the final reference to this Document instance.
        if self.is_edited then
            self:writeDocument()
        end
    end
    Document.close(self)
end
```

This ensures PDF annotations are saved only once when the last reference closes.

**Sources:**[frontend/document/pdfdocument.lua301-313](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/pdfdocument.lua#L301-L313)

---

## Auxiliary Providers

The registry also supports auxiliary (non-document) providers for specialized file handling.

### Auxiliary vs Document Providers
TypeExampleImplements Document APIHas `order` fieldUsageDocument`PdfDocument`YesNoFull document viewingAuxiliary`ImageViewer`NoYesSpecialized viewingAuxiliary`TextEditor` pluginNoYesFile editing
Auxiliary providers are registered via `addAuxProvider`:

```
DocumentRegistry:addAuxProvider({
    provider = "imageviewer",
    order = 10,
    -- No document creation function
})

```

**Sources:**[frontend/document/documentregistry.lua49-57](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/documentregistry.lua#L49-L57)[frontend/document/documentregistry.lua182-193](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/documentregistry.lua#L182-L193)

### Provider Query Methods

```
-- Include only document providers
hasProvider(file, mimetype, false)

-- Include document + auxiliary providers  
hasProvider(file, mimetype, true)

-- Get all auxiliary providers sorted by order
getAuxProviders() → [{provider, order}, ...]

```

**Sources:**[frontend/document/documentregistry.lua63-85](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/documentregistry.lua#L63-L85)[frontend/document/documentregistry.lua182-193](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/documentregistry.lua#L182-L193)

---

## Configuration Interfaces

### Querying Provider Information
MethodPurposeReturns`hasProvider(file, mime, aux?)`Check if file can be opened`bool``getProvider(file, aux?)`Get preferred provider`provider` or `nil``getProviders(file)`Get all applicable providers`{provider}` or `nil``getProviderFromKey(key)`Lookup by provider key`provider` or `nil``getAssociatedProviderKey(file, all?)`Get user-configured provider`string` or `nil``getReferenceCount(file)`Current refcount`int` or `nil`
**Sources:**[frontend/document/documentregistry.lua63-85](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/documentregistry.lua#L63-L85)[frontend/document/documentregistry.lua91-104](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/documentregistry.lua#L91-L104)[frontend/document/documentregistry.lua109-140](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/documentregistry.lua#L109-L140)[frontend/document/documentregistry.lua154-179](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/documentregistry.lua#L154-L179)[frontend/document/documentregistry.lua274-281](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/documentregistry.lua#L274-L281)

### Setting Provider Preferences

```
-- Set provider for single document
DocumentRegistry:setProvider("book.epub", CreDocument, false)
-- Stored in: book.sdr/metadata.lua → provider = "crengine"
 
-- Set provider for all .epub files
DocumentRegistry:setProvider("any.epub", CreDocument, true)
-- Stored in: G_reader_settings → provider = {epub = "crengine"}
 
-- Reset to default
DocumentRegistry:setProvider(file, nil, all)
```

**Sources:**[frontend/document/documentregistry.lua207-222](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/documentregistry.lua#L207-L222)

---

## Module Initialization

The registry loads all providers at module initialization:

```
-- Load document providers
require("document/credocument"):register(DocumentRegistry)
require("document/pdfdocument"):register(DocumentRegistry)
require("document/djvudocument"):register(DocumentRegistry)
require("document/picdocument"):register(DocumentRegistry)
 
-- Load auxiliary built-in
require("ui/widget/imageviewer"):register(DocumentRegistry)
require("ui/widget/textviewer"):register(DocumentRegistry)
```

Each provider's `register()` method calls `addProvider()` for its supported formats.

**Sources:**[frontend/document/documentregistry.lua288-297](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/documentregistry.lua#L288-L297)

---

# Document-Rendering-Engines

# Document Rendering Engines
Relevant source files
- [frontend/apps/reader/modules/readerbookmark.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerbookmark.lua)
- [frontend/apps/reader/modules/readerfont.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfont.lua)
- [frontend/apps/reader/modules/readerhighlight.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua)
- [frontend/apps/reader/modules/readerlink.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerlink.lua)
- [frontend/apps/reader/modules/readerpaging.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerpaging.lua)
- [frontend/apps/reader/modules/readerrolling.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerrolling.lua)
- [frontend/apps/reader/modules/readertoc.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readertoc.lua)
- [frontend/apps/reader/modules/readerview.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerview.lua)
- [frontend/document/credocument.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/credocument.lua)
- [frontend/document/djvudocument.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/djvudocument.lua)
- [frontend/document/document.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/document.lua)
- [frontend/document/documentregistry.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/documentregistry.lua)
- [frontend/document/koptinterface.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/koptinterface.lua)
- [frontend/document/pdfdocument.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/pdfdocument.lua)
- [frontend/document/picdocument.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/picdocument.lua)

This page documents the rendering engines that power document display in KOReader. Each engine wraps a native C/C++ library and provides a consistent Lua interface for rendering different document formats.

For information about how documents are opened and registered, see [Document Registry and Providers](/koreader/koreader/5.1-document-registry-and-providers). For details on the rendering pipeline and caching system, see [Rendering Pipeline and Caching](/koreader/koreader/5.3-rendering-pipeline-and-caching).

## Overview

KOReader supports multiple document formats through specialized rendering engines that wrap native C/C++ libraries:
EngineNative LibraryFormatsProvider KeyFile Extension**CreDocument**CRE (Cool Reader Engine)EPUB, FB2, HTML, TXT, RTF, CHM, MOBI, AZW`crengine`[credocument.lua](https://github.com/koreader/koreader/blob/9e6b1587/credocument.lua)**PdfDocument**MuPDFPDF, XPS, CBZ, CBR, EPUB (fallback)`mupdf`[pdfdocument.lua](https://github.com/koreader/koreader/blob/9e6b1587/pdfdocument.lua)**DjvuDocument**DjVuLibreDjVu`djvulibre`[djvudocument.lua](https://github.com/koreader/koreader/blob/9e6b1587/djvudocument.lua)**PicDocument**stb_image (via pic FFI)PNG, JPEG, GIF, WebP`picdocument`[picdocument.lua](https://github.com/koreader/koreader/blob/9e6b1587/picdocument.lua)**KoptInterface**k2pdfoptReflow/OCR enhancement layerN/A (module)[koptinterface.lua](https://github.com/koreader/koreader/blob/9e6b1587/koptinterface.lua)
All document engines extend the base `Document` class [frontend/document/document.lua16-46](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/document.lua#L16-L46) and implement a common interface for rendering, text extraction, and metadata access. The `KoptInterface` is a module that enhances PDF and DjVu rendering with reflow and OCR capabilities.

**Sources:**[frontend/document/credocument.lua21-78](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/credocument.lua#L21-L78)[frontend/document/pdfdocument.lua14-21](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/pdfdocument.lua#L14-L21)[frontend/document/djvudocument.lua5-15](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/djvudocument.lua#L5-L15)[frontend/document/picdocument.lua6-12](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/picdocument.lua#L6-L12)[frontend/document/koptinterface.lua21-31](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/koptinterface.lua#L21-L31)

## Document Class Hierarchy

```
uses

uses

Document

+file: string

+_document: userdata

+is_open: boolean

+render_color: boolean

+configurable: Configurable

+getNativePageDimensions(pageno)

+renderPage(pageno, rect, zoom, rotation, gamma)

+drawPage(target, x, y, rect, pageno, zoom, rotation)

+getPageTextBoxes(pageno)

+close()

CreDocument

+_view_mode: int

+provider: "crengine"

+engineInit()

+loadDocument(full_document)

+render()

+drawCurrentView(target, x, y, rect, pos)

+getTextFromXPointers(pos0, pos1)

+gotoXPointer(xpointer)

PdfDocument

+koptinterface: KoptInterface

+provider: "mupdf"

+is_reflowable: boolean

+reflowable_font_size: number

+saveHighlight(pageno, item)

+deleteHighlight(pageno, item)

+writeDocument()

DjvuDocument

+koptinterface: KoptInterface

+provider: "djvulibre"

+djvulibre_cache_size: number

+getPageTextBoxes(pageno)

PicDocument

+provider: "picdocument"

+is_pic: true

«module»

KoptInterface

+renderReflowedPage(doc, pageno, rect)

+renderOptimizedPage(doc, pageno, rect)

+getTextBoxes(doc, pageno)

+getOCRWord(doc, pageno, wbox)

+getCachedContext(doc, pageno)
```

**Sources:**[frontend/document/document.lua16-46](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/document.lua#L16-L46)[frontend/document/credocument.lua21-78](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/credocument.lua#L21-L78)[frontend/document/pdfdocument.lua14-21](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/pdfdocument.lua#L14-L21)[frontend/document/djvudocument.lua5-15](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/djvudocument.lua#L5-L15)

## Base Document Class

The `Document` class in [frontend/document/document.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/document.lua) provides the abstract interface that all rendering engines implement.

### Key Responsibilities

- **Page Dimensions**: `getNativePageDimensions(pageno)` returns native page size, cached in `DocCache`
- **Rendering**: `renderPage(pageno, rect, zoom, rotation, gamma, hinting)` returns a `TileCacheItem` with rendered pixels
- **Drawing**: `drawPage(target, x, y, rect, pageno, zoom, rotation, gamma)` blits cached tiles to a target buffer
- **Text Extraction**: `getPageTextBoxes(pageno)` returns line/word bounding boxes (engine-specific)
- **Lifecycle**: `close()` handles reference counting via `DocumentRegistry`

### Color Rendering

All engines support color rendering through `updateColorRendering()`:

```
function Document:updateColorRendering()
    if self.is_color_capable and CanvasContext.is_color_rendering_enabled then
        self.render_color = true
    else
        self.render_color = false
    end
end
```

Engines set `is_color_capable = true` and `color_bb_type` (e.g., `TYPE_BBRGB32`) to enable color. [frontend/document/document.lua373-379](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/document.lua#L373-L379)

**Sources:**[frontend/document/document.lua1-622](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/document.lua#L1-L622)

## CreDocument (CRE Engine)

`CreDocument` wraps the CRE (Cool Reader Engine) library for reflowable document formats. CRE is a sophisticated layout engine that parses HTML/EPUB structure, applies CSS, and performs text shaping with font fallback.

### Engine Initialization

CRE is initialized once per application lifecycle in `engineInit()`:

```
No

Yes

engineInit()

engine_initialized?

require('libs/libkoreader-cre')

cacheInit()

initHyphDict('./data/hyph/')

Register fonts from FontList

regularizeRegisteredFontsWeights()

setAsPreferredFontWithBias()

setOtherFontBiases()

engine_initialized = true

return cre
```

**Key initialization steps:**

1. **Cache setup**: Disk cache for faster re-openings (default 64MB) at `DataStorage/cache/cr3cache`
2. **Hyphenation**: Loads dictionaries from `./data/hyph/`
3. **Font registration**: Iterates `FontList:getFontList()` and calls `cre.registerFont(font_path)` for each
4. **Font weight regularization**: Ensures proper entries at weights 400 and 700 to avoid synthesized fonts
5. **Font biases**: Sets preferred font with bias calculation

**Sources:**[frontend/document/credocument.lua102-150](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/credocument.lua#L102-L150)

### Document Loading and Rendering

```
cre._document
CreDocument
App
cre._document
CreDocument
App
Apply cr3.ini defaults
Set image scaling
Set cache size
Set fallback fonts
new({file=path})
engineInit()
newDocView(width, height, view_mode)
setupDefaultView()
loadDocument()
loadDocument(file, only_metadata)
success
render()
renderDocument()
Document rendered
getFullHeight()
doc_height
```

The two-phase loading allows metadata extraction without full rendering:

- `loadDocument(false)` - metadata only, fast
- `loadDocument(true)` or `loadDocument()` - full document load and layout

**Sources:**[frontend/document/credocument.lua298-326](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/credocument.lua#L298-L326)

### View Modes and XPointers

CRE supports two view modes:

- `SCROLL_VIEW_MODE = 0` - Continuous scroll (rolling mode)
- `PAGE_VIEW_MODE = 1` - Paginated view

Position tracking uses **XPointers** (XML pointers into the DOM tree) rather than page numbers:
MethodDescription`gotoXPointer(xp)`Navigate to an XPointer location`getXPointer()`Get current position as XPointer`getPageXPointer(page)`Get XPointer for a page number`getPosFromXPointer(xp)`Convert XPointer to Y position`getTextFromXPointers(pos0, pos1)`Extract text between two XPointers
**Sources:**[frontend/document/credocument.lua848-949](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/credocument.lua#L848-L949)

### Font Configuration

CreDocument provides extensive font customization:

```
-- Default fonts
default_font = "Noto Serif"
monospace_font = "Droid Sans Mono"
header_font = "Noto Sans"
 
-- Fallback fonts (ordered)
fallback_fonts = {
    "Noto Sans CJK SC",
    "Noto Naskh Arabic",
    "Noto Sans Devanagari UI",
    "Noto Sans Bengali UI",
    "FreeSans",
    "FreeSerif",
    "Noto Serif",
    "Noto Sans",
}
```

Fallback order is carefully designed for optimal glyph coverage and appearance. [frontend/document/credocument.lua32-68](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/credocument.lua#L32-L68)

### Non-Linear Flows

CRE supports EPUB non-linear flows (footnotes, asides). When `hide_nonlinear_flows` is enabled:

- `hasNonLinearFlows()` - checks if document has non-linear content
- `getPageFlow(page)` - returns flow number (0 = linear, >0 = non-linear)
- `getNextPage(page)` / `getPrevPage(page)` - skip non-linear pages
- `cacheFlows()` - builds flow lookup tables

**Sources:**[frontend/document/credocument.lua374-523](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/credocument.lua#L374-L523)

### Drawing Pipeline

```
drawCurrentView()

Allocate BlitBuffer
(render_color ? RGB32 : Gray8)

_document:drawCurrentPage()

Apply night mode
image inversion

Apply smooth scaling

Apply SW dithering

target:blitFrom(buffer)
```

Drawing is page-based rather than tile-based. CRE renders the entire visible area at once. [frontend/document/credocument.lua780-812](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/credocument.lua#L780-L812)

**Sources:**[frontend/document/credocument.lua1-2827](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/credocument.lua#L1-L2827)

## PdfDocument (MuPDF Engine)

`PdfDocument` wraps MuPDF, a powerful PDF rendering library that also supports XPS, CBZ/CBR, and can serve as fallback for EPUB.

### Initialization and Configuration

```
function PdfDocument:init()
    pdf = require("ffi/mupdf")
    self.koptinterface = require("document/koptinterface")
    self.koptinterface:setDefaultConfigurable(self.configurable)
    
    self._document = pdf.openDocument(self.file)
    self:updateColorRendering()
    self.is_reflowable = self._document:isDocumentReflowable()
    self.reflowable_font_size = self:convertKoptToReflowableFontSize()
    self:layoutDocument()
end
```

MuPDF can render reflowable documents (like EPUB opened via MuPDF instead of CRE). Font size is configurable via `layoutDocument()`. [frontend/document/pdfdocument.lua23-46](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/pdfdocument.lua#L23-L46)

### KoptInterface Integration

Unlike CreDocument, PdfDocument delegates many operations to `KoptInterface`:

```
PdfDocument

KoptInterface

renderPage()
→ renderReflowedPage()
or renderOptimizedPage()
or Document.renderPage()

getTextBoxes()
→ getNativeTextBoxes()
or getTextBoxesFromScratch()

getOCRWord()
→ getNativeOCRWord()
or getReflewOCRWord()

getPageBBox()
→ getAutoBBox()
or getSemiAutoBBox()
```

Routing depends on `configurable` settings:

- `text_wrap = 1` → reflow rendering
- `page_opt = 1` → page optimization
- Otherwise → native MuPDF rendering

**Sources:**[frontend/document/pdfdocument.lua347-357](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/pdfdocument.lua#L347-L357)

### Annotation Support

PdfDocument supports PDF annotations (highlights, underlines, strikeouts):
MethodDescription`saveHighlight(pageno, item)`Creates markup annotation from pboxes`deleteHighlight(pageno, item)`Removes matching markup annotation`updateHighlightContents(pageno, item, contents)`Updates annotation text`writeDocument()`Persists changes to PDF file
Annotations are converted to MuPDF quadpoints via `_quadpointsFromPboxes()`. [frontend/document/pdfdocument.lua206-294](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/pdfdocument.lua#L206-L294)

### Text Extraction

Text is extracted via MuPDF's native API:

```
function PdfDocument:getPageTextBoxes(pageno)
    local page = self._document:openPage(pageno)
    local text = page:getPageText()
    page:close()
    return text
end
```

Returns structured text with line/word bounding boxes. Cached in `DocCache` with hash `"textbox|"..file.."|"..pageno`. [frontend/document/pdfdocument.lua101-113](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/pdfdocument.lua#L101-L113)

**Sources:**[frontend/document/pdfdocument.lua1-408](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/pdfdocument.lua#L1-L408)

## DjvuDocument (DjVuLibre Engine)

`DjvuDocument` wraps DjVuLibre for DjVu format support.

### Initialization

```
function DjvuDocument:init()
    local djvu = require("libs/libkoreader-djvu")
    self.koptinterface = require("document/koptinterface")
    self.koptinterface:setDefaultConfigurable(self.configurable)
    
    if not validDjvuFile(self.file) then
        error("Not a valid DjVu file")
    end
    
    self._document = djvu.openDocument(self.file, self.render_color, self.djvulibre_cache_size)
    self:updateColorRendering()
end
```

Validates file format by checking magic string `"AT&TFORM"`. [frontend/document/djvudocument.lua17-46](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/djvudocument.lua#L17-L46)

### Text Layer Processing

DjVu text layers can be deeply nested (page → columns → regions → paragraphs → lines → words). The `getPageTextBoxes()` method flattens this structure:

```
Yes

No

Yes

No

_document:getPageText()

Has line nodes?

collectWords(node)
sortWordsByX()

setLineBbox(words)

lines array

Has direct word children?

collectDirectWords()
groupWordsIntoLines()

setLineBbox(groups)

Recurse into children

Return lines
```

The grouping algorithm:

1. Sorts words by Y position (top to bottom), then X (left to right)
2. Computes dynamic Y threshold based on average word height (50% of avg height)
3. Groups consecutive words within threshold into lines
4. Computes bounding box for each line

**Sources:**[frontend/document/djvudocument.lua59-210](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/djvudocument.lua#L59-L210)

### KoptInterface Integration

Like PdfDocument, DjvuDocument delegates rendering and text operations to `KoptInterface`. All rendering modes (native, reflow, optimize, OCR) are available.

**Sources:**[frontend/document/djvudocument.lua1-308](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/djvudocument.lua#L1-L308)

## PicDocument (Image Engine)

`PicDocument` handles image formats through the `pic` library (wrapper around stb_image).

### Minimal Implementation

```
function PicDocument:init()
    self:updateColorRendering()
    pic = require("ffi/pic")
    pic.color = CanvasContext.is_color_rendering_enabled
    self._document = pic.openDocument(self.file)
    
    -- Enforce dithering
    if CanvasContext:hasEinkScreen() then
        if CanvasContext:canHWDither() then
            self.hw_dithering = true
        elseif CanvasContext.fb_bpp == 8 then
            self.sw_dithering = true
        end
    end
end
```

PicDocument enforces dithering on E-ink screens for better image quality. [frontend/document/picdocument.lua14-39](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/picdocument.lua#L14-L39)

Images are treated as single-page documents. No text extraction or reflowing.

**Sources:**[frontend/document/picdocument.lua1-66](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/picdocument.lua#L1-L66)

## KoptInterface (k2pdfopt Enhancement Layer)

`KoptInterface` is not a document provider but an enhancement layer that provides reflow, optimization, and OCR capabilities for PDF and DjVu documents.

### Architecture

```
k2pdfopt Library

KoptInterface

PdfDocument / DjvuDocument

renderPage()

getTextBoxes()

getOCRWord()

renderReflowedPage()

renderOptimizedPage()

getNativeTextBoxes()

getNativeTextBoxesFromScratch()

getOCRWord()

KOPTContext

k2pdfopt_reflow_bmp()

optimizePage()

getTOCRWord()
```

**Sources:**[frontend/document/koptinterface.lua21-31](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/koptinterface.lua#L21-L31)

### Reflow Rendering

Reflow converts fixed-layout pages into continuous, reflowable text optimized for small screens:

```
function KoptInterface:reflowPage(doc, pageno, bbox, background)
    local kc = self:createContext(doc, pageno, bbox)
    if background then
        kc:setPreCache()
        self.bg_thread = true
    end
    
    -- Calculate zoom
    kc.zoom = (1.5 * kc.zoom * kc.quality * kc.dev_width) / bbox.x1
    
    -- Generate pixmap
    local page = doc._document:openPage(pageno)
    page:getPagePix(kc, doc.render_mode)
    page:close()
    
    -- Reflow in background thread or foreground
    if background then
        local pthread = get_pthread()
        pthread.pthread_create(rf_thread, attr, k2pdfopt_reflow_bmp, kc)
    else
        k2pdfopt_reflow_bmp(kc)
    end
    
    return kc
end
```

Background reflowing uses pthreads to avoid blocking the UI. [frontend/document/koptinterface.lua293-319](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/koptinterface.lua#L293-L319)

### KOPTContext Caching

Reflowed pages are cached as `KOPTContext` objects containing the destination bitmap and reflow metadata:

```
Yes

No

getCachedContext(doc, pageno)

Context in DocCache?

waitForContext(cached.kctx)

return kctx

reflowPage(doc, pageno, bbox, false)

Create ContextCacheItem

DocCache:insert(hash, item)
```

Context hash includes: file, mod_time, render settings, bbox, canvas size. [frontend/document/koptinterface.lua327-358](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/koptinterface.lua#L327-L358)

### Context Serialization

`ContextCacheItem` supports disk persistence:

```
function ContextCacheItem:dump(filename)
    if self.kctx:isPreCache() == 0 then
        local cache_file = Persist:new{
            path = filename,
            codec = "zstd",
        }
        local t = KOPTContext.totable(self.kctx)
        t.cache_size = self.size
        cache_file:save(t)
    end
end
```

Contexts are serialized with zstd compression for faster subsequent opens. [frontend/document/koptinterface.lua41-61](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/koptinterface.lua#L41-L61)

### OCR Support

OCR uses Tesseract through k2pdfopt:

```
Yes

No

getOCRWord(doc, pageno, wbox)

text_wrap = 1?

getReflewOCRWord()

getNativeOCRWord()

getCachedContext()

kc:getTOCRWord('dst', rect, tessocr_data, lang)

createContext()

page:getPagePix()

kc:getTOCRWord('src', rect, tessocr_data, lang)
```

OCR settings:

- `tessocr_data` - Path to Tesseract data (default: `DataStorage/data/tessdata`)
- `ocr_lang` - Language code (default: "eng")
- `ocr_type = -1` - Assumes single uniform block of text

**Sources:**[frontend/document/koptinterface.lua766-846](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/koptinterface.lua#L766-L846)

### BBox Detection

Automatic bounding box detection:
ModeMethodDescriptionAuto`getAutoBBox()`Fully automatic detectionSemi-Auto`getSemiAutoBBox()`Detection within user-specified bboxManual`Document.getPageBBox()`User-defined bbox
Triggered by `trim_page` setting (1 = auto, 2 = semi-auto). [frontend/document/koptinterface.lua184-265](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/koptinterface.lua#L184-L265)

**Sources:**[frontend/document/koptinterface.lua1-1091](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/koptinterface.lua#L1-L1091)

## Common Patterns

### Color Rendering Toggle

All engines implement `updateColorRendering()`:

```
function PdfDocument:updateColorRendering()
    Document.updateColorRendering(self) -- sets self.render_color
    if self._document then
        self._document:setColorRendering(self.render_color)
    end
end
```

MuPDF and DjVuLibre notify the native library. CRE handles it implicitly during rendering.

**Sources:**[frontend/document/pdfdocument.lua48-53](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/pdfdocument.lua#L48-L53)[frontend/document/djvudocument.lua48-53](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/djvudocument.lua#L48-L53)

### Text Extraction Flow

```
Native Library
DocCache
Document
App
Native Library
DocCache
Document
App
Uses k2pdfopt OCR preprocessing
Uses reflow rectmaps
Native text extraction
alt
[forced_ocr = 1 or no native text]
[text_wrap = 1]
alt
[PDF/DjVu with KoptInterface]
[CreDocument]
getTextBoxes(pageno)
Check configurable settings
getNativeTextBoxesFromScratch()
getNativeTextBoxes()
getPageTextBoxes()
getTextFromPositions(x0, y0, x1, y1)
{text, pos0, pos1}
getWordBoxesFromPositions(pos0, pos1)
word_boxes array
Check/Insert
text boxes
```

**Sources:**[frontend/document/koptinterface.lua565-729](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/koptinterface.lua#L565-L729)[frontend/document/credocument.lua682-717](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/credocument.lua#L682-L717)

### Render Mode Selection

```
Yes

No

Yes

No

PdfDocument:renderPage()

text_wrap = 1?

KoptInterface:renderReflowedPage()

page_opt = 1 OR
auto_straighten > 0?

KoptInterface:renderOptimizedPage()

Document:renderPage()

getCachedContext()

kc:dstToBlitBuffer()

Insert TileCacheItem

createContext()

page:getPagePix()

kc:optimizePage()

kc:dstToBlitBuffer()

openPage()

page:draw(dc, bb)
```

Configurable settings determine rendering path. All paths return `TileCacheItem` for consistent caching.

**Sources:**[frontend/document/koptinterface.lua393-492](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/koptinterface.lua#L393-L492)

### Engine Lifecycle

```

```

All engines follow this lifecycle. CreDocument has explicit rendering phase, while others render on-demand.

**Sources:**[frontend/document/document.lua104-125](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/document.lua#L104-L125)[frontend/document/credocument.lua152-189](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/credocument.lua#L152-L189)[frontend/document/pdfdocument.lua23-46](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/pdfdocument.lua#L23-L46)

---

# Rendering-Pipeline-and-Caching

# Document View and Rendering
Relevant source files
- [frontend/document/djvudocument.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/djvudocument.lua)
- [frontend/document/document.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/document.lua)
- [frontend/document/documentregistry.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/documentregistry.lua)
- [frontend/document/koptinterface.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/koptinterface.lua)
- [frontend/document/pdfdocument.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/pdfdocument.lua)
- [frontend/document/picdocument.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/picdocument.lua)

This page covers KOReader's document rendering system, including how different document formats are rendered to pixels, the page caching mechanisms, and the KoptInterface system for text reflow and OCR. This focuses specifically on the rendering pipeline from parsed documents to displayable content.

For information about document format parsing and provider selection, see [Document Registry and Providers](/koreader/koreader/4.1-file-manager-system). For details about the rendering engines themselves (crengine, mupdf, etc.), see [Document Rendering Engines](/koreader/koreader/4.2-reader-ui-and-module-system).

## Rendering Architecture Overview

KOReader's document rendering system transforms document content into pixel data through a multi-layered architecture. Each document type has a specific provider that handles format-specific rendering, with some providers utilizing the KoptInterface for text reflow and OCR enhancements.

```
Rendering Pipeline

Enhancement Layer

Rendering Engines

Document Providers

CreDocument
EPUB, FB2, TXT

PdfDocument
PDF, CBZ, etc.

DjvuDocument
DjVu files

PicDocument
Images

crengine
Text reflow, CSS

mupdf
PDF rendering

djvulibre
DjVu rendering

pic library
Image decoding

KoptInterface
k2pdfoptlib
Text reflow, OCR, Optimization

DrawContext
Zoom, rotation, gamma

BlitBuffer
Pixel data output

DocCache + TileCacheItem
Rendered page tiles
```

**Sources:**[frontend/document/document.lua407-515](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/document.lua#L407-L515)[frontend/document/credocument.lua780-812](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/credocument.lua#L780-L812)[frontend/document/koptinterface.lua393-401](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/koptinterface.lua#L393-L401)[frontend/document/pdfdocument.lua347-357](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/pdfdocument.lua#L347-L357)

## Document Provider Rendering Methods

Each document provider implements a common rendering interface with three key methods that form the rendering pipeline:
MethodPurposeCaching`hintPage`Background page pre-renderingCached result`renderPage`Generate page tile with pixel dataCached in DocCache`drawPage`Blit cached tile to target BlitBufferDirect blitting
### CreDocument Rendering

CreDocument uses crengine for text-based documents and implements a unique rendering approach:

- `drawCurrentView()` renders directly to a BlitBuffer without tile caching
- Supports real-time text reflow and CSS styling
- Handles font rendering and embedded images
- Uses `CanvasContext` for display parameters

```
CreDocument Rendering

gotoPage
Position document

drawCurrentView
Render to BlitBuffer

self.buffer
BlitBuffer instance

target BlitBuffer
Screen output
```

**Sources:**[frontend/document/credocument.lua780-829](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/credocument.lua#L780-L829)[frontend/document/credocument.lua312-326](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/credocument.lua#L312-L326)

### PDF and DjVu Rendering

PdfDocument and DjvuDocument delegate their rendering to KoptInterface, which provides enhanced functionality:

- Standard rendering via native engines (mupdf, djvulibre)
- Optional text reflow via KoptInterface for better readability
- OCR capability for text extraction from scanned documents
- Page optimization for better display quality

**Sources:**[frontend/document/pdfdocument.lua347-357](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/pdfdocument.lua#L347-L357)[frontend/document/djvudocument.lua139-149](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/djvudocument.lua#L139-L149)

## KoptInterface and Text Reflow

The KoptInterface provides advanced document processing capabilities using the k2pdfopt library. It offers three main rendering modes based on configuration:

```
Background Processing

KOPTContext Management

KoptInterface Rendering Modes

text_wrap=1

page_opt=1 OR auto_straighten>0

default

Document Configuration

renderPage
Standard rendering

renderReflowedPage
Text reflow enabled

renderOptimizedPage
Page optimization

createContext
Setup reflow parameters

getCachedContext
Retrieve or create

reflowPage
Background processing

Background Thread
pthread_create

waitForContext
Synchronization

CanvasContext:enableCPUCores
SMP control
```

**Sources:**[frontend/document/koptinterface.lua393-401](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/koptinterface.lua#L393-L401)[frontend/document/koptinterface.lua107-123](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/koptinterface.lua#L107-L123)[frontend/document/koptinterface.lua293-318](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/koptinterface.lua#L293-L318)

### Context Caching and Background Rendering

KoptInterface implements sophisticated caching of reflow contexts to avoid redundant processing:

- `ContextCacheItem` stores reflow contexts with serialization support
- Background threading for non-blocking reflow operations
- SMP (Symmetric Multi-Processing) support for faster rendering
- Memory pressure checking to prevent cache overflow

**Sources:**[frontend/document/koptinterface.lua327-358](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/koptinterface.lua#L327-L358)[frontend/document/koptinterface.lua516-534](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/koptinterface.lua#L516-L534)

## Page Caching System

KOReader implements a comprehensive caching system to optimize rendering performance through the DocCache and TileCacheItem system:

```

```

**Sources:**[frontend/document/document.lua393-405](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/document.lua#L393-L405)[frontend/document/document.lua407-436](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/document.lua#L407-L436)[frontend/document/koptinterface.lua327-332](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/koptinterface.lua#L327-L332)

### Cache Invalidation

The caching system includes cache validity tracking to handle document modifications:

- `tile_cache_validity_ts` timestamp for cache invalidation
- Automatic cache cleanup when documents are modified
- Per-document cache isolation to prevent cross-contamination

**Sources:**[frontend/document/document.lua381-391](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/document.lua#L381-L391)[frontend/document/document.lua427-436](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/document.lua#L427-L436)

## Complete Rendering Pipeline

The following diagram shows the complete flow from document provider selection through pixel output:

```
Output

Tile Generation

Rendering Request

Input Processing

Yes

No

Document File

DocumentRegistry
Provider selection

Document Provider
CreDocument/PdfDocument/etc.

renderPage
pageno, rect, zoom, rotation, gamma

DocCache:check
Existing tile?

Cache Hit?

TileCacheItem:new
BlitBuffer allocation

DrawContext:new
Transform setup

Native Engine Render
crengine/mupdf/djvulibre/kopt

DocCache:insert
Store generated tile

drawPage
Blit to target

Target BlitBuffer
Screen display
```

**Sources:**[frontend/document/document.lua407-515](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/document.lua#L407-L515)[frontend/document/document.lua532-548](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/document.lua#L532-L548)[frontend/document/credocument.lua780-829](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/credocument.lua#L780-L829)[frontend/document/koptinterface.lua408-435](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/koptinterface.lua#L408-L435)

---

# Reading-Features-and-Interaction

# Reading Features and Interaction
Relevant source files
- [frontend/apps/reader/modules/readerbookmark.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerbookmark.lua)
- [frontend/apps/reader/modules/readerfont.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfont.lua)
- [frontend/apps/reader/modules/readerfooter.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua)
- [frontend/apps/reader/modules/readerhighlight.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua)
- [frontend/apps/reader/modules/readerlink.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerlink.lua)
- [frontend/apps/reader/modules/readerpaging.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerpaging.lua)
- [frontend/apps/reader/modules/readerrolling.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerrolling.lua)
- [frontend/apps/reader/modules/readertoc.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readertoc.lua)
- [frontend/apps/reader/modules/readerview.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerview.lua)
- [frontend/document/credocument.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/credocument.lua)
- [spec/unit/autosuspend_spec.lua](https://github.com/koreader/koreader/blob/9e6b1587/spec/unit/autosuspend_spec.lua)
- [spec/unit/commonrequire.lua](https://github.com/koreader/koreader/blob/9e6b1587/spec/unit/commonrequire.lua)
- [spec/unit/filemanager_spec.lua](https://github.com/koreader/koreader/blob/9e6b1587/spec/unit/filemanager_spec.lua)
- [spec/unit/readerbookmark_spec.lua](https://github.com/koreader/koreader/blob/9e6b1587/spec/unit/readerbookmark_spec.lua)
- [spec/unit/readerdictionary_spec.lua](https://github.com/koreader/koreader/blob/9e6b1587/spec/unit/readerdictionary_spec.lua)
- [spec/unit/readerfooter_spec.lua](https://github.com/koreader/koreader/blob/9e6b1587/spec/unit/readerfooter_spec.lua)
- [spec/unit/readerhighlight_spec.lua](https://github.com/koreader/koreader/blob/9e6b1587/spec/unit/readerhighlight_spec.lua)
- [spec/unit/readerlink_spec.lua](https://github.com/koreader/koreader/blob/9e6b1587/spec/unit/readerlink_spec.lua)
- [spec/unit/readerpaging_spec.lua](https://github.com/koreader/koreader/blob/9e6b1587/spec/unit/readerpaging_spec.lua)
- [spec/unit/readerrolling_spec.lua](https://github.com/koreader/koreader/blob/9e6b1587/spec/unit/readerrolling_spec.lua)
- [spec/unit/readersearch_spec.lua](https://github.com/koreader/koreader/blob/9e6b1587/spec/unit/readersearch_spec.lua)
- [spec/unit/readertoc_spec.lua](https://github.com/koreader/koreader/blob/9e6b1587/spec/unit/readertoc_spec.lua)
- [spec/unit/readerui_spec.lua](https://github.com/koreader/koreader/blob/9e6b1587/spec/unit/readerui_spec.lua)
- [spec/unit/readerview_spec.lua](https://github.com/koreader/koreader/blob/9e6b1587/spec/unit/readerview_spec.lua)
- [spec/unit/screenshoter_spec.lua](https://github.com/koreader/koreader/blob/9e6b1587/spec/unit/screenshoter_spec.lua)

This section covers the user-facing interaction features for reading documents in KOReader. It explains how readers interact with text (highlighting, selection, annotation), navigate through documents (page turning, TOC, bookmarks), and monitor reading progress (footer display, statistics).

For document rendering pipeline details, see [Document System](/koreader/koreader/5-document-system). For the underlying UI framework that handles events, see [Core Framework Systems](/koreader/koreader/3-core-framework-systems).

## Overview: ReaderUI Module Architecture

KOReader's reading experience is implemented through `ReaderUI`, which acts as a hub for 15+ specialized reader modules. Each module handles a distinct interaction feature and communicates via events.

```
Status Display

Document Structure

Text Interaction

View & Navigation

ReaderUI Hub

uses

uses

triggers

triggers

uses

ReaderUI
(apps/reader/readerui.lua)

ReaderView
Screen Rendering

ReaderPaging
Page Navigation

ReaderRolling
Scroll Navigation

ReaderHighlight
Selection & Markup

ReaderSearch
Text Search

ReaderDictionary
Word Lookup

ReaderWikipedia
Article Lookup

ReaderToc
Table of Contents

ReaderBookmark
Bookmarks & Notes

ReaderLink
Hyperlink Navigation

ReaderFooter
Progress & Info Bar
```

**Key Modules:**

- `ReaderView` ([readerview.lua30-78](https://github.com/koreader/koreader/blob/9e6b1587/readerview.lua#L30-L78)): Orchestrates screen rendering, manages highlight display, page state
- `ReaderPaging` ([readerpaging.lua29-40](https://github.com/koreader/koreader/blob/9e6b1587/readerpaging.lua#L29-L40)): Handles page-based navigation for PDF/DjVu documents
- `ReaderRolling` ([readerrolling.lua55-85](https://github.com/koreader/koreader/blob/9e6b1587/readerrolling.lua#L55-L85)): Handles continuous scroll navigation for EPUB/reflowable documents
- `ReaderHighlight` ([readerhighlight.lua28-41](https://github.com/koreader/koreader/blob/9e6b1587/readerhighlight.lua#L28-L41)): Manages text selection, highlighting, and annotations
- `ReaderBookmark` ([readerbookmark.lua28-40](https://github.com/koreader/koreader/blob/9e6b1587/readerbookmark.lua#L28-L40)): Manages bookmarks, notes, and highlights storage
- `ReaderToc` ([readertoc.lua25-36](https://github.com/koreader/koreader/blob/9e6b1587/readertoc.lua#L25-L36)): Provides table of contents navigation
- `ReaderFooter` ([readerfooter.lua482-493](https://github.com/koreader/koreader/blob/9e6b1587/readerfooter.lua#L482-L493)): Displays reading progress and status information

Sources: [frontend/apps/reader/modules/readerview.lua1-79](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerview.lua#L1-L79)[frontend/apps/reader/modules/readerpaging.lua1-40](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerpaging.lua#L1-L40)[frontend/apps/reader/modules/readerrolling.lua1-85](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerrolling.lua#L1-L85)[frontend/apps/reader/modules/readerhighlight.lua1-41](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L1-L41)

## Gesture and Touch Input Processing

Reader modules register touch zones and key events to handle user interaction. The input flows from hardware through `GestureDetector` to module-specific handlers.

```
"ReaderView"
"Reader Module
(Highlight/Paging/etc)"
ReaderUI
"GestureDetector"
"Input Handler"
User
"ReaderView"
"Reader Module
(Highlight/Paging/etc)"
ReaderUI
"GestureDetector"
"Input Handler"
User
Touch/Key Event
Raw coordinates
Recognize gesture
(tap/hold/swipe/pan)
Gesture event
Route to registered
touch zone handler
Process interaction
(onTap/onHold/onSwipe)
Update display state
Mark dirty region
Screen refresh
```

### Touch Zone Registration

Each reader module registers screen zones with gesture types and handlers:

**ReaderHighlight Touch Zones** ([readerhighlight.lua292-367](https://github.com/koreader/koreader/blob/9e6b1587/readerhighlight.lua#L292-L367)):

- `readerhighlight_tap`: Full screen, checks for taps on existing highlights
- `readerhighlight_hold`: Full screen, initiates text selection
- `readerhighlight_hold_pan`: Full screen, extends text selection
- `readerhighlight_hold_release`: Full screen, completes selection and shows action dialog

**ReaderPaging Touch Zones** ([readerpaging.lua96-147](https://github.com/koreader/koreader/blob/9e6b1587/readerpaging.lua#L96-L147)):

- `tap_forward`: Forward zone (configurable), advances to next page
- `tap_backward`: Backward zone (configurable), returns to previous page
- `paging_swipe`: Full screen, handles swipe gestures for page turning
- `paging_pan`: Full screen, handles panning within zoomed pages

**ReaderLink Touch Zones** ([readerlink.lua74-114](https://github.com/koreader/koreader/blob/9e6b1587/readerlink.lua#L74-L114)):

- `tap_link`: Full screen, highest priority, follows hyperlinks in documents
- `swipe_link`: Full screen, handles swipe gestures on links

The `overrides` property defines priority order when zones overlap.

Sources: [frontend/apps/reader/modules/readerhighlight.lua292-367](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L292-L367)[frontend/apps/reader/modules/readerpaging.lua96-147](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerpaging.lua#L96-L147)[frontend/apps/reader/modules/readerlink.lua72-115](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerlink.lua#L72-L115)

## Text Selection and Highlighting

The text selection system works differently for page-based (PDF/DjVu) and reflowable (EPUB) documents.

### Highlight State Machine

```
onHold(pos)

onHoldPan(pos)

onHoldPan(pos)

onHoldRelease()

onHoldRelease()

saveHighlight()

onClose()

Idle

Selecting

Extending

ShowDialog

Saved

Store hold_pos
Get word at position
Show temporary highlight

Update selected_text
Extend highlight boxes
Redraw temp highlight

ButtonDialog with actions:
- Highlight
- Add Note
- Copy
- Dictionary
- Wikipedia
- Search
```

### Text Selection Process

**For Paged Documents (PDF/DjVu):**

1. **Word Extraction** ([readerhighlight.lua1052-1097](https://github.com/koreader/koreader/blob/9e6b1587/readerhighlight.lua#L1052-L1097)): On hold, `ReaderHighlight:onHold()` calls `ui.document:getWordFromPosition(pos)` which returns `{word, page, sbox}` where `sbox` is the bounding box.
2. **Selection Extension** ([readerhighlight.lua1115-1171](https://github.com/koreader/koreader/blob/9e6b1587/readerhighlight.lua#L1115-L1171)): `onHoldPan()` calls `ui.document:getTextFromPositions(pos0, pos1)` which returns all text boxes between two positions.
3. **Temporary Highlight** ([readerview.lua524-533](https://github.com/koreader/koreader/blob/9e6b1587/readerview.lua#L524-L533)): Selected boxes are stored in `self.highlight.temp[page]` and drawn during `ReaderView:paintTo()`.

**For Reflowable Documents (EPUB):**

1. **Word Extraction** ([credocument.lua605-654](https://github.com/koreader/koreader/blob/9e6b1587/credocument.lua#L605-L654)): `CreDocument:getWordFromPosition()` uses `document:getTextFromPositions()` which returns text with XPointer positions (`pos0`, `pos1`).
2. **Cross-Page Selection**: XPointers allow selection to span multiple pages. The document engine handles text extraction across page boundaries.
3. **Word Boundaries** ([readerhighlight.lua225-234](https://github.com/koreader/koreader/blob/9e6b1587/readerhighlight.lua#L225-L234)): `ReaderHighlight` registers a `userhyph` callback to improve word selection for hyphenation dictionaries.

### Highlight Storage

Highlights are stored in the unified annotation system:

```
Annotation Record

addAnnotation()

persist

ReaderHighlight
saveHighlight()

ReaderAnnotation
annotations table

DocSettings
metadata.lua

&{
  page: number
  pos0: xpointer
  pos1: xpointer
  text: string
  drawer: 'lighten'|'underscore'|...
  color: 'yellow'|'red'|...
  datetime: timestamp
  note: optional string
&}
```

Each annotation record contains:

- `page`: Page number where highlight appears
- `pos0`, `pos1`: Start/end positions (XPointers for EPUB, coordinates for PDF)
- `text`: The highlighted text content
- `drawer`: Highlight style (`"lighten"`, `"underscore"`, `"strikeout"`, `"invert"`)
- `color`: Highlight color (e.g., `"yellow"`, `"red"`, `"blue"`)
- `datetime`: Creation timestamp
- `note`: Optional attached note text
- `chapter`: Chapter name from TOC (optional)

Sources: [frontend/apps/reader/modules/readerhighlight.lua1052-1171](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L1052-L1171)[frontend/document/credocument.lua605-654](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/credocument.lua#L605-L654)[frontend/apps/reader/modules/readerview.lua524-533](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerview.lua#L524-L533)

### Highlight Drawing

Highlights are rendered during `ReaderView:paintTo()`:

**Saved Highlights** ([readerview.lua534-597](https://github.com/koreader/koreader/blob/9e6b1587/readerview.lua#L534-L597)):

```
1. Iterate through all annotations with drawer field
2. For each page in view, get highlights via getPageBoxes()
3. Transform boxes from page coordinates to screen coordinates
4. Call drawHighlightRect() with drawer style and color

```

**Highlight Styles** ([readerhighlight.lua378-383](https://github.com/koreader/koreader/blob/9e6b1587/readerhighlight.lua#L378-L383)):

- `"lighten"`: Fills bounding box with semi-transparent color
- `"underscore"`: Draws line under text
- `"strikeout"`: Draws line through text
- `"invert"`: Inverts colors in bounding box

**Note Markers** ([readerview.lua680-744](https://github.com/koreader/koreader/blob/9e6b1587/readerview.lua#L680-L744)):
Side line indicators show which highlights have attached notes:

- `"none"`: No indicator
- `"underline"`: Underline at bottom of highlight
- `"sideline"`: Vertical line at page edge
- `"sidemark"`: Small mark at page edge

Sources: [frontend/apps/reader/modules/readerview.lua534-744](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerview.lua#L534-L744)[frontend/apps/reader/modules/readerhighlight.lua378-383](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L378-L383)

## Dictionary and Wikipedia Integration

Text selection integrates with dictionary lookup and Wikipedia article retrieval.

```
Wikipedia Lookup

Dictionary Lookup

can trigger

can trigger

User selects text
via hold gesture

ButtonDialog
Action choices

Dictionary button

Wikipedia button

ReaderDictionary
lookupDict()

DictQuickLookup
Popup widget

StarDict/sdcv
Dictionary data

ReaderWikipedia
lookupWikipedia()

Wikipedia API
HTTP request

DictQuickLookup
HTML content
```

**Dictionary Lookup Flow** ([readerhighlight.lua156-163](https://github.com/koreader/koreader/blob/9e6b1587/readerhighlight.lua#L156-L163)):

1. User taps "Dictionary" button in highlight dialog
2. `ReaderHighlight:lookupDict()` extracts selected word
3. Calls `ui.dictionary:onLookupWord()` with text and word box
4. `ReaderDictionary` queries installed dictionaries via `sdcv` command
5. Results displayed in `DictQuickLookup` popup with scrollable text

**Wikipedia Lookup Flow** ([readerhighlight.lua143-155](https://github.com/koreader/koreader/blob/9e6b1587/readerhighlight.lua#L143-L155)):

1. User taps "Wikipedia" button
2. `ReaderHighlight:lookupWikipedia()` extracts selected text
3. Calls `ui.wikipedia:onLookupWikipedia()` with search term
4. `ReaderWikipedia` makes HTTP request to Wikipedia API
5. HTML article content rendered in `DictQuickLookup` with `ScrollHtmlWidget`

**Word vs. Multi-Word Selection** ([readerhighlight.lua720-730](https://github.com/koreader/koreader/blob/9e6b1587/readerhighlight.lua#L720-L730)):
The setting `"highlight_action_on_single_word"` controls behavior:

- If `false` (default): Single-word selection opens dictionary directly
- If `true`: Single-word selection shows full action dialog like multi-word

Sources: [frontend/apps/reader/modules/readerhighlight.lua143-163](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L143-L163)[frontend/apps/reader/modules/readerhighlight.lua720-730](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L720-L730)

## Navigation: Paging vs. Rolling Modes

KOReader supports two fundamentally different navigation modes depending on document type.

### Navigation Mode Comparison
AspectReaderPaging (PDF/DjVu)ReaderRolling (EPUB/FB2)Module[readerpaging.lua29-40](https://github.com/koreader/koreader/blob/9e6b1587/readerpaging.lua#L29-L40)[readerrolling.lua55-85](https://github.com/koreader/koreader/blob/9e6b1587/readerrolling.lua#L55-L85)Page ConceptFixed pages from documentDynamic pages from renderingPosition TrackingPage number + page position fractionXPointer in DOM treeView ModesPage mode or scroll modePage mode or scroll modeZoomArbitrary zoom levelsFont size + DPI scalingProgress Metric`current_page / number_of_pages`XPointer percentage in document
### ReaderPaging: Page-Based Navigation

**Page State** ([readerpaging.lua187-203](https://github.com/koreader/koreader/blob/9e6b1587/readerpaging.lua#L187-L203)):

```
-- Each page stores a reading position (0.0 to 1.0)
page_positions[page] = fractional_position
```

When font size or margins change, `page_positions` helps restore the approximate reading location within each page.

**Navigation Methods** ([readerpaging.lua273-326](https://github.com/koreader/koreader/blob/9e6b1587/readerpaging.lua#L273-L326)):

- `onGotoViewRel(n)`: Move n pages forward/backward
- `onGotoPage(page)`: Jump to specific page number
- `onGotoPosRel(n)`: Scroll within current page
- `onGotoPercent(percent)`: Jump to percentage of document

**Page Flipping Mode** ([readerpaging.lua205-243](https://github.com/koreader/koreader/blob/9e6b1587/readerpaging.lua#L205-L243)):
A special mode for rapid page browsing:

- Stores original page on entry
- Allows quick navigation via swipe gestures
- Restores original page on exit
- Can be configured with different zoom modes

Sources: [frontend/apps/reader/modules/readerpaging.lua187-326](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerpaging.lua#L187-L326)

### ReaderRolling: Continuous Scroll Navigation

**XPointer Positioning** ([readerrolling.lua36-54](https://github.com/koreader/koreader/blob/9e6b1587/readerrolling.lua#L36-L54)):
ReaderRolling uses XPointers (DOM tree paths) instead of page numbers for precise positioning. XPointers remain valid across:

- Screen orientation changes
- Font size adjustments
- Margin modifications
- View mode switches (page ↔ scroll)

**Three Position Measurements**:

1. **Page number** (page mode): Current page in current view configuration
2. **Progress percentage**: Position in document (0-100%)
3. **XPointer**: Canonical position in document structure

**Partial Re-rendering** ([readerrolling.lua70-85](https://github.com/koreader/koreader/blob/9e6b1587/readerrolling.lua#L70-L85)):
When changing typography settings:

1. Quick partial re-render of current chapter for immediate feedback
2. Full background re-rendering of entire document
3. Seamless reload when background render completes
4. Uses shared memory to coordinate with forked subprocess

```
User changes setting
(font size, margins)

Partial rerender
current chapter only

Mark rendering_state
PARTIALLY_RERENDERED

Fork subprocess
full rerendering

User continues reading
with partial rendering

Background render
completes

Seamless document
reload with cache

Restore XPointer
position
```

**Non-Linear Flows** ([credocument.lua374-393](https://github.com/koreader/koreader/blob/9e6b1587/credocument.lua#L374-L393)):
EPUB documents can have non-linear fragments (footnotes, appendices). When `hide_nonlinear_flows` is enabled:

- Non-linear pages are hidden from normal navigation
- Page count excludes hidden pages
- TOC and links can still access hidden pages
- Display shows `[page/total]flow` notation

Sources: [frontend/apps/reader/modules/readerrolling.lua36-85](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerrolling.lua#L36-L85)[frontend/document/credocument.lua374-523](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/credocument.lua#L374-L523)

## Bookmarks and Table of Contents

### Bookmark System Architecture

```
Display

UI Access

Storage

Bookmark Types

shows

Page Bookmark
(no text)

Highlight
(with text, no note)

Note
(with text and note)

ReaderAnnotation
annotations table

DocSettings
metadata.lua sidecar

Bookmark Menu
ReaderBookmark:onShowBookmark()

Bookmark Search
ReaderBookmark:onSearchBookmark()

Dogear Indicator
ReaderDogear widget

Scrollable List
with text/notes
```

**Bookmark Record Structure** ([readerbookmark.lua28-40](https://github.com/koreader/koreader/blob/9e6b1587/readerbookmark.lua#L28-L40)):

- Page bookmarks: `{page, datetime}` - minimal record
- Highlights: `{page, pos0, pos1, text, drawer, color, datetime}` - includes selection
- Notes: Same as highlights plus `{note}` field

**Dogear Indicator** ([readerview.lua251-253](https://github.com/koreader/koreader/blob/9e6b1587/readerview.lua#L251-L253)):
Visual corner fold appears on pages with bookmarks when `dogear_visible` is true. Controlled by `ReaderDogear` widget which checks if current page is bookmarked.

**Bookmark Display Options** ([readerbookmark.lua109-266](https://github.com/koreader/koreader/blob/9e6b1587/readerbookmark.lua#L109-L266)):

- Items per page: Configurable (6-24) or flexible height
- Font size: Independent from file browser settings
- Sort order: By page number or date, with reverse option
- Content display: Highlighted text, note, or both
- Separator lines: Optional between items
- Highlight colors: Optional color indicators

Sources: [frontend/apps/reader/modules/readerbookmark.lua28-266](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerbookmark.lua#L28-L266)[frontend/apps/reader/modules/readerview.lua251-253](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerview.lua#L251-L253)

### Table of Contents System

**TOC Data Structure** ([readertoc.lua25-36](https://github.com/koreader/koreader/blob/9e6b1587/readertoc.lua#L25-L36)):

```
toc = {
  {title, page, depth, xpointer},  -- TOC entry
  ...
}
```

Each entry contains:

- `title`: Chapter/section heading text
- `page`: Page where section starts
- `depth`: Nesting level (1 = top level)
- `xpointer`: Position in document (EPUB only)

**TOC Validation** ([readertoc.lua182-291](https://github.com/koreader/koreader/blob/9e6b1587/readertoc.lua#L182-L291)):
The `validateAndFixToc()` function checks for and fixes:

- Out-of-order page numbers
- Duplicate page entries
- Invalid XPointers from engine bugs

**Chapter Navigation** ([readertoc.lua578-676](https://github.com/koreader/koreader/blob/9e6b1587/readertoc.lua#L578-L676)):

- `getNextChapter(page)`: Finds next TOC entry after current page
- `getPreviousChapter(page)`: Finds previous TOC entry
- `getChapterPagesLeft(page)`: Calculates pages remaining in chapter
- `isChapterStart(page)`, `isChapterEnd(page)`: Boundary detection

**TOC Ticks** ([readertoc.lua423-572](https://github.com/koreader/koreader/blob/9e6b1587/readertoc.lua#L423-L572)):
Progress bar markers showing chapter positions:

- Generated from TOC depth levels
- Can ignore specific levels via `toc_ticks_ignored_levels`
- Flattened representation for footer display
- Filtered to avoid marker overcrowding

**Alternative TOC** ([readertoc.lua168-179](https://github.com/koreader/koreader/blob/9e6b1587/readertoc.lua#L168-L179)):
For HTML/EPUB documents, can build alternative TOC from heading tags if original TOC is poor quality or missing. Uses heading level heuristics to determine chapter structure.

Sources: [frontend/apps/reader/modules/readertoc.lua25-676](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readertoc.lua#L25-L676)

## Reader Footer: Status and Progress Display

The footer displays reading progress, battery status, time, and other information in a highly configurable status bar.

### Footer Architecture

```
Data Sources

Display Modes (MODE enum)

Footer Components

mode

mode

mode

mode

mode

mode

mode

ReaderFooter
Container widget

TextWidget
Status text

ProgressWidget
Progress bar

LineWidget
Separator line

0: off

1: page_progress

3: time

5: battery

6: percentage

7: book_time_to_read

all_at_once mode:
show multiple items

ui.document
page count, position

ui.statistics
reading speed, time

ui.toc
chapter info

Device:getPowerDevice()
battery level

NetworkMgr
wifi status
```

### Footer Display Modes

**Mode Constants** ([readerfooter.lua35-58](https://github.com/koreader/koreader/blob/9e6b1587/readerfooter.lua#L35-L58)):

```
MODE = {
    off = 0,
    page_progress = 1,        -- "10 / 250"
    pages_left_book = 2,      -- "=> 240 / 250"
    time = 3,                 -- "14:30"
    pages_left = 4,           -- "=> 15" (chapter)
    battery = 5,              -- "B: 75%"
    percentage = 6,           -- "R: 4.0%"
    book_time_to_read = 7,    -- "⏳ 2:30"
    chapter_time_to_read = 8, -- "⤻ 0:45"
    frontlight = 9,           -- "☼ 50%"
    mem_usage = 10,           -- "M 156"
    wifi_status = 11,         -- "W On"
    book_title = 12,
    book_chapter = 13,
    bookmark_count = 14,
    chapter_progress = 15,
    frontlight_warmth = 16,
    custom_text = 17,
    book_author = 18,
    page_turning_inverted = 19,
    dynamic_filler = 20,      -- fills remaining space
    additional_content = 21,
}
```

**All-at-Once Mode** ([readerfooter.lua631-639](https://github.com/koreader/koreader/blob/9e6b1587/readerfooter.lua#L631-L639)):
When enabled, footer shows multiple items simultaneously:

- Items configured via checkboxes in settings
- Separated by configurable separator (bar, bullet, etc.)
- Can hide empty items (e.g., hide battery if > 80%)
- Dynamic filler can add spacing between items

**Mode Switching** ([readerfooter.lua1467-1488](https://github.com/koreader/koreader/blob/9e6b1587/readerfooter.lua#L1467-L1488)):
Users can cycle through modes by tapping the footer. The mode index determines order of modes when cycling.

Sources: [frontend/apps/reader/modules/readerfooter.lua35-639](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua#L35-L639)

### Footer Text Generation

Each mode has a generator function in `footerTextGeneratorMap` ([readerfooter.lua142-480](https://github.com/koreader/koreader/blob/9e6b1587/readerfooter.lua#L142-L480)):

**Page Progress** ([readerfooter.lua242-260](https://github.com/koreader/koreader/blob/9e6b1587/readerfooter.lua#L242-L260)):

```
function(footer)
    if footer.ui.pagemap and footer.ui.pagemap:wantsPageLabels() then
        -- Shows page labels like "xiv / 250"
        return ("%s / %s"):format(getCurrentPageLabel(), getLastPageLabel())
    elseif footer.ui.document:hasHiddenFlows() then
        -- Shows flow notation "[15 / 30]2"
        return ("[%d / %d]%d"):format(page_in_flow, pages_in_flow, flow)
    else
        return ("%d / %d"):format(footer.pageno, footer.pages)
    end
end
```

**Time to Read** ([readerfooter.lua322-335](https://github.com/koreader/koreader/blob/9e6b1587/readerfooter.lua#L322-L335)):
Estimates reading time based on statistics:

- `book_time_to_read`: Time to finish entire book
- `chapter_time_to_read`: Time to finish current chapter
- Calculated from `ui.statistics:getTimeForPages(pages_left)`

**Battery Display** ([readerfooter.lua180-222](https://github.com/koreader/koreader/blob/9e6b1587/readerfooter.lua#L180-L222)):

- Shows battery percentage
- Displays "+" when charging
- Uses dynamic icon in icon mode (shows different battery shapes)
- Handles dual battery devices (e.g., Kobo with cover battery)
- Can auto-hide when above threshold

**Symbol Prefixes** ([readerfooter.lua60-139](https://github.com/koreader/koreader/blob/9e6b1587/readerfooter.lua#L60-L139)):
Three prefix styles:

- `letters`: Text labels like "B:", "R:", "TB:"
- `icons`: Unicode symbols like "🔋", "⏳", "☼"
- `compact_items`: Minimal symbols for space efficiency

### Progress Bar Features

**Progress Bar Positioning** ([readerfooter.lua789-833](https://github.com/koreader/koreader/blob/9e6b1587/readerfooter.lua#L789-L833)):

- `alongside`: Next to text in footer
- `above`: Separate bar above footer text
- `below`: Separate bar below footer text

**TOC Markers** ([readerfooter.lua610-613](https://github.com/koreader/koreader/blob/9e6b1587/readerfooter.lua#L610-L613)):
Vertical tick marks on progress bar showing chapter boundaries:

- Width configurable (1-5 pixels)
- Generated from TOC ticks
- Can show initial position marker

**Chapter Progress Bar** ([readerfooter.lua499](https://github.com/koreader/koreader/blob/9e6b1587/readerfooter.lua#L499-L499)):
Alternative mode showing progress within current chapter instead of entire book.

**Progress Bar Styles** ([readerfooter.lua536-542](https://github.com/koreader/koreader/blob/9e6b1587/readerfooter.lua#L536-L542)):

- `thin`: 3px height, minimal appearance
- `thick`: 7px height, standard appearance

### Footer Auto-Refresh

**Auto-Refresh Scheduling** ([readerfooter.lua876-908](https://github.com/koreader/koreader/blob/9e6b1587/readerfooter.lua#L876-L908)):

```
-- Schedules refresh at top of next minute for time display
UIManager:scheduleIn(61 - tonumber(os.date("%S")), self.autoRefreshFooter)
```

Automatically refreshes footer when displaying:

- Current time
- Battery level
- WiFi status
- Memory usage

Only schedules refresh when footer is visible and relevant mode is active.

**Visibility Management** ([readerfooter.lua856-874](https://github.com/koreader/koreader/blob/9e6b1587/readerfooter.lua#L856-L874)):
Before repainting, checks if footer would be covered by overlapping widgets:

- If `ReaderUI` is topmost: Safe to repaint just footer
- If widget covers footer: Skip repaint to avoid artifacts
- If widget partially overlaps: Repaint entire `ReaderUI`

Sources: [frontend/apps/reader/modules/readerfooter.lua142-908](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua#L142-L908)

## Link Navigation and History

### Link Interaction

**Link Detection** ([readerlink.lua72-115](https://github.com/koreader/koreader/blob/9e6b1587/readerlink.lua#L72-L115)):
The `tap_link` touch zone has highest priority. When user taps:

1. `ReaderLink:onTap(ges)` checks position for links
2. For PDF: `ui.document:getLinkFromPosition(pos)` returns link object
3. For EPUB: `ui.document:getLink(pos)` returns link with `xpointer` target

**Link Types**:

- **Internal links**: Page numbers (PDF) or XPointers (EPUB) within document
- **External links**: HTTP/HTTPS URLs to web resources
- **File links**: Relative paths to other documents

### Link Following

```

```

**Location Stack** ([readerlink.lua66-69](https://github.com/koreader/koreader/blob/9e6b1587/readerlink.lua#L66-L69)):

```
location_stack = {}  -- Stores previous positions
forward_location_stack = {}  -- Stores positions for redo
```

Each stack entry contains:

- Page number or XPointer
- Zoom level
- Scroll position
- View mode

**Navigation Methods** ([readerlink.lua524-630](https://github.com/koreader/koreader/blob/9e6b1587/readerlink.lua#L524-L630)):

- `addCurrentLocationToStack()`: Saves current position before link jump
- `onGoBackLink()`: Returns to previous position in stack
- `onGoForwardLink()`: Moves forward in stack after going back
- `onClearLocationStack()`: Clears navigation history

### External Link Handling

**External Link Dialog** ([readerlink.lua143-216](https://github.com/koreader/koreader/blob/9e6b1587/readerlink.lua#L143-L216)):
Buttons are dynamically shown based on context:

- **Copy**: Always available, copies URL to clipboard
- **QR Code**: Shows QR code of URL
- **Open in browser**: Only if `Device:canOpenLink()`
- **Read online**: Only for Wikipedia URLs
- **Read EPUB**: Only if Wikipedia article was previously saved locally

**Wikipedia Link Detection** ([readerlink.lua24-63](https://github.com/koreader/koreader/blob/9e6b1587/readerlink.lua#L24-L63)):

```
function is_wiki_page(link_url)
    local wiki_lang, wiki_page = link_url:match([[https?://([^%.]+).wikipedia.org/wiki/([^/]+)]])
    if wiki_lang and wiki_page then
        -- Check for saved EPUB in current directory or wikipedia_save_dir
        return wiki_lang, wiki_page, epub_fullpath
    end
end
```

**Footnote Links** ([readerlink.lua129-134](https://github.com/koreader/koreader/blob/9e6b1587/readerlink.lua#L129-L134)):
Special handling for footnote links when `footnote_link_in_popup` is enabled:

- Opens footnote text in popup instead of navigating
- Keeps reading context visible
- Configurable via settings menu

Sources: [frontend/apps/reader/modules/readerlink.lua24-630](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerlink.lua#L24-L630)

## Summary: Event-Driven Reading Flow

All reading features communicate through KOReader's event system. Key event patterns:

**Navigation Events**:

- `GotoViewRel`: Move pages forward/backward
- `GotoPage`: Jump to specific page
- `GotoPercent`: Jump to percentage position
- `PageUpdate`: Page changed (triggers footer update, chapter detection)

**Selection Events**:

- `Hold`: Initiate text selection
- `HoldPan`: Extend selection
- `HoldRelease`: Complete selection, show action dialog

**State Change Events**:

- `SetZoomMode`: Change zoom/view mode
- `ReaderReady`: Document fully loaded
- `UpdateFooter`: Refresh footer display
- `TocReset`: TOC structure changed

This event-driven architecture allows modules to remain loosely coupled while coordinating complex interactions.

Sources: [frontend/apps/reader/modules/readerview.lua1-296](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerview.lua#L1-L296)[frontend/apps/reader/modules/readerhighlight.lua1-257](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L1-L257)[frontend/apps/reader/modules/readerfooter.lua1-657](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua#L1-L657)[frontend/apps/reader/modules/readerlink.lua1-138](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerlink.lua#L1-L138)

---

# Highlight-and-Annotation-System

# Highlight and Annotation System
Relevant source files
- [frontend/apps/reader/modules/readerbookmark.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerbookmark.lua)
- [frontend/apps/reader/modules/readerfont.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfont.lua)
- [frontend/apps/reader/modules/readerhighlight.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua)
- [frontend/apps/reader/modules/readerlink.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerlink.lua)
- [frontend/apps/reader/modules/readerpaging.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerpaging.lua)
- [frontend/apps/reader/modules/readerrolling.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerrolling.lua)
- [frontend/apps/reader/modules/readertoc.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readertoc.lua)
- [frontend/apps/reader/modules/readerview.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerview.lua)
- [frontend/document/credocument.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/credocument.lua)

## Purpose and Scope

This document describes KOReader's highlight and annotation system, which allows users to select text, create highlights, add notes, and manage bookmarks. The system handles text selection across different document types (PDF, DjVu, EPUB), visual highlight rendering, annotation data persistence, and integration with lookup services (dictionary, Wikipedia, translation).

For information about the statistics tracking of annotations, see [Statistics and Reading Tracking](/koreader/koreader/8.3-translation-and-localization). For details on the menu systems that display annotations, see [Menu and TouchMenu Components](/koreader/koreader/7.2-menu-and-touchmenu-components).

---

## System Architecture Overview

```
Persistence

Document Layer

ReaderView Rendering

ReaderAnnotation Module

ReaderHighlight Module

User Interaction Layer

Touch/Hold Gestures
GestureDetector

Keyboard/DPad Input
key_events

ButtonDialog
Highlight Actions

ReaderHighlight
(readerhighlight.lua)

Text Selection
startSelection()

Highlight Indicator
(non-touch devices)

Action Handlers
saveHighlight(), addNote()

ReaderAnnotation
(readerannotation.lua)

annotations array
(sorted by position)

Import/Export
.annotations.lua files

Sort Functions
isItemInPositionOrder*()

ReaderView
(readerview.lua)

highlight.page_boxes
(cached per page)

highlight.visible_boxes
(current view)

drawSavedHighlight()
drawTempHighlight()

Document API
getTextFromPositions()

CreDocument
(XPointers)

PdfDocument
(page positions)

doc_settings
(metadata.lua.sdr)

.annotations.lua
(export format)

PDF Annotation
Writing (optional)
```

**Sources:**[frontend/apps/reader/modules/readerhighlight.lua29-233](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L29-L233)[frontend/apps/reader/modules/readerannotation.lua11-13](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerannotation.lua#L11-L13)[frontend/apps/reader/modules/readerview.lua30-78](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerview.lua#L30-L78)

---

## Core Components

### ReaderHighlight

The `ReaderHighlight` class ([frontend/apps/reader/modules/readerhighlight.lua29](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L29-L29)) is the primary interface for text selection and highlighting. It extends `InputContainer` and manages:
ResponsibilityKey MethodsDescription**Text Selection**`onHold()`, `onHoldPan()`, `onHoldRelease()`Handles touch gestures for selecting text**Non-Touch Selection**`onStartHighlightIndicator()`, `onMoveHighlightIndicator()`Provides crosshair-based selection for devices without touchscreens**Highlight Creation**`saveHighlight()`, `addNote()`Creates highlights and notes from selected text**Action Dialog**`onShowHighlightDialog()`Shows button dialog with actions (highlight, copy, dictionary, etc.)**Style Management**`highlight_colors`, `saved_drawer`, `saved_color`Manages highlight appearance settings
**Sources:**[frontend/apps/reader/modules/readerhighlight.lua29-233](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L29-L233)

### ReaderAnnotation

The `ReaderAnnotation` class ([frontend/apps/reader/modules/readerannotation.lua11](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerannotation.lua#L11-L11)) handles annotation data management and persistence:
ResponsibilityKey MethodsDescription**Data Structure**`annotations` arraySorted array of all annotations (highlights, notes, bookmarks)**Building**`buildAnnotation()`Converts bookmarks/highlights to unified annotation format**Sorting**`sortItems()`, `isItemInPositionOrder*()`Maintains position-based ordering (different for paging/rolling)**Import/Export**`onExportAnnotations()`, `importAnnotations()`Syncs annotations across devices via `.annotations.lua` files**Migration**`migrateToAnnotations()`Converts old bookmark/highlight formats to unified format
**Sources:**[frontend/apps/reader/modules/readerannotation.lua11-252](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerannotation.lua#L11-L252)

### ReaderView Highlight Rendering

The `ReaderView` class ([frontend/apps/reader/modules/readerview.lua30](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerview.lua#L30-L30)) is responsible for drawing highlights on screen:
ComponentPurposeData Structure`highlight.page_boxes`Cached highlight boxes per pageHash table: `page_boxes[page] = {box1, box2, ...}``highlight.visible_boxes`Currently visible highlightsArray populated during `drawSavedHighlight()``highlight.temp`Temporary selection highlightsHash table: `temp[page] = {boxes}``highlight.indicator`Non-touch crosshair positionGeom: `{x, y, w, h}`
**Sources:**[frontend/apps/reader/modules/readerview.lua92-103](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerview.lua#L92-L103)

---

## Annotation Data Model

```
type determined by

drawer field

color field

pos0/pos1 (PDF)

page/pos0/pos1 (EPUB)

Annotation

+string datetime

+string datetime_updated

+string drawer

+string color

+string text

+boolean text_edited

+string note

+string chapter

+number pageno

+string pageref

+string|number page

+string|table pos0

+string|table pos1

+table pboxes

+table ext

«enumeration»

AnnotationTypes

HIGHLIGHT

NOTE

BOOKMARK

«enumeration»

DrawerStyles

lighten

underscore

strikeout

invert

«enumeration»

HighlightColors

red

orange

yellow

green

olive

cyan

blue

purple

gray

PositionPaging

+number page

+number x

+number y

+number zoom

+number rotation

PositionRolling

+string xpointer
```

### Annotation Structure

Each annotation in the `annotations` array has the following fields:

```
{
    datetime         = "2024-01-15 10:30:45",  -- Creation timestamp (immutable)
    datetime_updated = "2024-01-16 14:20:10",  -- Last modification timestamp
    drawer           = "lighten",               -- Highlight style (nil for bookmarks)
    color            = "yellow",                -- Highlight color (nil for bookmarks)
    text             = "selected text here",    -- Highlighted text (editable)
    text_edited      = false,                   -- True if text was manually edited
    note             = "user's note",           -- User annotation (optional)
    chapter          = "Chapter 3",             -- Chapter title at annotation location
    pageno           = 42,                      -- Continuous page number
    pageref          = "xlii",                  -- Reference page label (if available)
    page             = "xpointer or page num",  -- Position reference
    pos0             = "xpointer or {x,y}",     -- Start position
    pos1             = "xpointer or {x,y}",     -- End position
    pboxes           = {{x,y,w,h}, ...},        -- PDF: bounding boxes
    ext              = {page=43, ...},          -- PDF: multi-page highlight extension
}
```

**Sources:**[frontend/apps/reader/modules/readerannotation.lua67-83](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerannotation.lua#L67-L83)

### Position Representation Differences
Document Type`page``pos0` / `pos1`Notes**EPUB/CRE**XPointer stringXPointer stringsExample: `"/body/DocFragment[2]/body/p[5]/text().0"`**PDF/DjVu**Page numberPosition tables: `{page, x, y, zoom, rotation}`Coordinates in document space
**Sources:**[frontend/apps/reader/modules/readerannotation.lua17-30](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerannotation.lua#L17-L30)[frontend/apps/reader/modules/readerannotation.lua48-52](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerannotation.lua#L48-L52)

---

## Text Selection and Highlighting Workflow

### Touch-Based Selection Flow

```
ReaderAnnotation
ReaderView
Document
ReaderHighlight
GestureDetector
User
ReaderAnnotation
ReaderView
Document
ReaderHighlight
GestureDetector
User
alt
[User pans to extend selection]
alt
[User clicks "Highlight"]
alt
[User clicks "Add note"]
Hold gesture on text
onHold(_, ges)
getTextFromPositions(pos)
{text, pos0, pos1}
Store in selected_text
Hold pan gesture
onHoldPan(_, ges)
getTextFromPositions(new_pos)
Update highlight.temp
drawTempHighlight()
Release hold
onHoldRelease()
onShowHighlightDialog()
Show ButtonDialog
saveHighlight(true)
addItem(annotation)
sortItems()
index
Clear temp, refresh
addNote()
Show InputDialog
Enter note text
addItem(annotation with note)
```

**Sources:**[frontend/apps/reader/modules/readerhighlight.lua968-1099](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L968-L1099)[frontend/apps/reader/modules/readerhighlight.lua1101-1175](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L1101-L1175)

### Non-Touch Selection with Highlight Indicator

For devices without touchscreens, KOReader provides a crosshair-based selection system:

```
IndicatorActive

Arrow keys

Update position

Press key

Move with arrows

Press key again

onHighlightPress()

Action completed

onStartHighlightIndicator()

Back key (clear)

Idle

ShowCrosshair

MovingIndicator

SelectionStart

Selecting

SelectionEnd

ShowDialog
```

**Key Functions:**

- `onStartHighlightIndicator()` - Activates crosshair mode
- `onMoveHighlightIndicator(dx, dy, quick)` - Moves crosshair with keyboard
- `onHighlightPress()` - Confirms selection points or shows dialog

**Sources:**[frontend/apps/reader/modules/readerhighlight.lua1290-1442](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L1290-L1442)[frontend/apps/reader/modules/readerhighlight.lua241-263](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L241-L263)

---

## Annotation Storage and Persistence

### Storage Architecture

```
Operations

Persistent Storage

In-Memory State

ReaderAnnotation.annotations
(sorted array)

ReaderView.highlight
(rendering cache)

metadata.lua.sdr/
metadata.lua
(DocSettings)

.annotations.lua
(export file)

PDF file
(embedded annotations)

onSaveSettings()

onReadSettings()

onExportAnnotations()

importAnnotations()

writePdfAnnotation()
```

**Sources:**[frontend/apps/reader/modules/readerannotation.lua112-157](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerannotation.lua#L112-L157)[frontend/apps/reader/modules/readerannotation.lua245-277](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerannotation.lua#L245-L277)

### Sorting Algorithm

Annotations must be kept in position order. The sorting algorithm differs between document types:

**For EPUB/CRE Documents** (`isItemInPositionOrderRolling`):

1. Compare rendered page numbers first
2. If same page, compare XPointers using `document:compareXPointers()`
3. For highlights with identical start positions, compare end positions
4. Page bookmarks always come before highlights on the same page

**For PDF/DjVu Documents** (`isItemInPositionOrderPaging`):

1. Compare page numbers
2. If same page, compare positions using `document:comparePositions()`
3. For highlights with identical start positions, compare end positions
4. Page bookmarks always come before highlights on the same page

**Sources:**[frontend/apps/reader/modules/readerannotation.lua350-439](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerannotation.lua#L350-L439)

### Import/Export System

The annotation export system enables synchronization across devices:
FeatureImplementationFile Format**Export Path**`getExportAnnotationsFilepath()``<book_path>.annotations.lua`**Device ID**Stored in `G_reader_settings`UUID to identify source device**Merge Strategy**Compare `datetime` / `datetime_updated`Keeps newest version of each annotation**Auto-Export**On book closingConfigurable via `annotations_export_on_closing`**Import**On book openingAutomatic if file exists and device_id differs
**Sources:**[frontend/apps/reader/modules/readerannotation.lua255-336](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerannotation.lua#L255-L336)

---

## Highlight Rendering System

### Rendering Pipeline

```
Cache Management

paintTo() Pipeline

true

also drawn

ReaderView:paintTo()

highlight_visible?

drawSavedHighlight()

Build page_boxes cache

pageToScreenTransform()

drawHighlightRect()

highlight.page_boxes
{page → boxes}

highlight.visible_boxes
(current frame)

highlight.temp
(selection preview)
```

**Sources:**[frontend/apps/reader/modules/readerview.lua535-605](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerview.lua#L535-L605)

### Drawing Highlights

The `drawSavedHighlight()` method uses caching to optimize rendering:

**For Page-Based Documents:**

1. Check if `page_boxes[page]` exists (cached)
2. If cached, directly draw from cache
3. If not cached:

- Query `ui.annotation:getPageSavedHighlights(page)`
- Get boxes from `document:getPageBoxesFromPositions()`
- Transform to screen coordinates
- Build cache entry
- Draw and populate `visible_boxes`

**Cache Invalidation:** The cache is cleared on:

- Document reflow (`text_wrap` changes)
- Zoom level changes
- Annotation modifications
- Page re-rendering

**Sources:**[frontend/apps/reader/modules/readerview.lua546-605](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerview.lua#L546-L605)

### Highlight Drawing Styles

```
Note Markers

Drawer Styles

lighten
(color overlay)

underscore
(line below text)

strikeout
(line through text)

invert
(reverse colors)

none
(no marker)

underline
(full underline)

sideline
(margin line)

sidemark
(margin symbol)

Reader Settings
```

**Configuration:**

- Drawer styles: [frontend/apps/reader/modules/readerhighlight.lua354-359](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L354-L359)
- Note markers: [frontend/apps/reader/modules/readerhighlight.lua361-366](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L361-L366)
- Default style: `"lighten"` with `"yellow"` or `"gray"` color
- Configurable in menu: [frontend/apps/reader/modules/readerhighlight.lua405-458](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L405-L458)

**Sources:**[frontend/apps/reader/modules/readerhighlight.lua354-366](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L354-L366)[frontend/apps/reader/modules/readerhighlight.lua405-530](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L405-L530)

---

## Highlight Dialog and Actions

### Action Button System

The highlight dialog presents actions via `ButtonDialog`. Buttons are registered in `_highlight_buttons`:

```
Conditional Actions

Lookup Actions

Core Actions

enable if

enable if

enable if

enable if

01_select
Extend selection

02_highlight
Save highlight

03_copy
Copy to clipboard

04_add_note
Add note

05_wikipedia
Wikipedia lookup

06_dictionary
Dictionary lookup

07_translate
Translation

12_search
Full-text search

08_share_text
(Android only)

09_view_html
(CRE only)

10_user_dict
(user dictionary)

11_follow_link
(if link selected)

ButtonDialog

Device/Selection
Conditions
```

**Button Registration Example:**

```
self._highlight_buttons["02_highlight"] = function(this)
    return {
        text = _("Highlight"),
        enabled = this.hold_pos ~= nil,
        callback = function()
            this:saveHighlight(true)
            this:onClose()
        end,
    }
end
```

**Sources:**[frontend/apps/reader/modules/readerhighlight.lua68-167](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L68-L167)[frontend/apps/reader/modules/readerhighlight.lua169-226](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L169-L226)

### Integration Points

The highlight system integrates with multiple reader features:
IntegrationModuleMethod Called**Dictionary**`ReaderDictionary``lookupDict(index)`**Wikipedia**`ReaderWikipedia``lookupWikipedia()`**Translation**`Translator``translate(index)`**Search**`ReaderSearch``onHighlightSearch()`**User Hyphenation**`UserHyph``modifyUserEntry(text)`**HTML Viewing**Built-in`viewSelectionHTML()`
**Sources:**[frontend/apps/reader/modules/readerhighlight.lua1621-1793](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L1621-L1793)

---

## PDF-Specific Features

### PDF Annotation Writing

KOReader can write highlights directly into PDF files as native PDF annotations:

```
PDFFile
Document
ReaderHighlight
User
PDFFile
Document
ReaderHighlight
User
highlight_write_into_pdf = true
opt
[Has note]
Annotations saved in sidecar only
alt
[File is writable]
[File is read-only]
Enable "Write into PDF"
Create/modify highlight
_checkIfWritable()
writePdfAnnotation("save", item)
Write annotation
writePdfAnnotation("content", item, note)
Write note as popup
Show warning message
Delete highlight
writePdfAnnotation("delete", item)
Remove annotation
```

**Key Features:**

- Annotations are written using native PDF annotation format
- Highlights include color, style, and position
- Notes are written as popup annotations
- Bulk operations: "Write all" and "Delete all from PDF"
- Warning about metadata hash changes

**Sources:**[frontend/apps/reader/modules/readerhighlight.lua558-683](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L558-L683)[frontend/apps/reader/modules/readerhighlight.lua1803-1902](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L1803-L1902)

### PDF Multi-Page Highlights

PDF documents support highlights spanning multiple pages:

**Data Structure:**

```
annotation = {
    page = 10,           -- Start page
    pos0 = {page=10, x=100, y=200},
    pos1 = {page=12, x=300, y=400},
    ext = {              -- Extension information
        page = 12,       -- End page
        -- Additional position data
    },
    pboxes = {...}       -- Boxes on start page
}
```

**Rendering:**

- Each page's boxes are cached separately in `page_boxes`
- Multi-page highlights appear on all spanned pages
- Each page shows only the boxes within its boundaries

**Sources:**[frontend/apps/reader/modules/readerannotation.lua82](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerannotation.lua#L82-L82)

---

## Configuration and Settings

### Highlight Style Settings

All highlight configuration is accessible via the main menu:
SettingPathTypeDefaultDrawing styleHighlights → StyleRadio`"lighten"`Highlight colorHighlights → ColorDialog`"yellow"` / `"gray"`Gray opacityHighlights → OpacitySpin`0.2`Line heightHighlights → Line HeightSpin`100%`Note markerHighlights → Note markerDialog`"none"`
**Sources:**[frontend/apps/reader/modules/readerhighlight.lua387-530](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L387-L530)

### Long-Press Behavior

Configurable long-press actions:

```
Long-press on text

Ask with popup
(default)

Do nothing

Auto-highlight

Select and highlight

Add note

Translate

Wikipedia

Dictionary

Full-text search
```

**Menu Location:** Settings → Long-press on text → Action options

**Sources:**[frontend/apps/reader/modules/readerhighlight.lua368-378](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L368-L378)[frontend/apps/reader/modules/readerhighlight.lua693-723](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L693-L723)

### Dialog Position

The highlight dialog position can be configured:
OptionBehavior**Top**Dialog appears at top of screen**Center**Dialog appears in center (default)**Bottom**Dialog appears at bottom**Highlight position**Dialog appears near selected text
**Sources:**[frontend/apps/reader/modules/readerhighlight.lua380-386](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L380-L386)[frontend/apps/reader/modules/readerhighlight.lua724-747](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L724-L747)

---

## Integration with Bookmarks

The annotation system unifies highlights and page bookmarks:

```
ReaderBookmark

Unified System

Legacy System

migrateToAnnotations

migrateToAnnotations

filter by drawer

filter by drawer

bookmarks array

highlight hash table

annotations array

Bookmark UI

Dogear Indicator

toggleBookmark()

Highlights
(drawer != nil)

Page Bookmarks
(drawer == nil)
```

**Bookmark Features:**

- Page bookmarks are annotations with `drawer == nil`
- Dogear visibility reflects bookmark state
- Bookmarks appear before highlights on same page when sorted
- Integrated display in bookmarks list UI

**Sources:**[frontend/apps/reader/modules/readerbookmark.lua353-410](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerbookmark.lua#L353-L410)[frontend/apps/reader/modules/readerannotation.lua17-84](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerannotation.lua#L17-L84)

---

# Text-Selection-and-Dictionary-Lookup

# Text Selection and Dictionary Lookup
Relevant source files
- [frontend/apps/reader/modules/readerbookmark.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerbookmark.lua)
- [frontend/apps/reader/modules/readerdictionary.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerdictionary.lua)
- [frontend/apps/reader/modules/readerfont.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfont.lua)
- [frontend/apps/reader/modules/readerhighlight.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua)
- [frontend/apps/reader/modules/readerlink.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerlink.lua)
- [frontend/apps/reader/modules/readerpaging.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerpaging.lua)
- [frontend/apps/reader/modules/readerrolling.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerrolling.lua)
- [frontend/apps/reader/modules/readerstyletweak.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerstyletweak.lua)
- [frontend/apps/reader/modules/readertoc.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readertoc.lua)
- [frontend/apps/reader/modules/readerview.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerview.lua)
- [frontend/apps/reader/modules/readerwikipedia.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerwikipedia.lua)
- [frontend/document/credocument.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/credocument.lua)
- [frontend/ui/data/css_tweaks.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/data/css_tweaks.lua)
- [frontend/ui/data/settings_migration.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/data/settings_migration.lua)
- [frontend/ui/translator.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/translator.lua)
- [frontend/ui/widget/dictquicklookup.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/dictquicklookup.lua)
- [frontend/ui/widget/footnotewidget.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/footnotewidget.lua)
- [frontend/ui/widget/htmlboxwidget.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/htmlboxwidget.lua)
- [frontend/ui/widget/scrollhtmlwidget.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/scrollhtmlwidget.lua)
- [frontend/ui/widget/scrolltextwidget.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/scrolltextwidget.lua)
- [frontend/ui/widget/textboxwidget.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/textboxwidget.lua)
- [frontend/ui/wikipedia.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/wikipedia.lua)
- [frontend/util.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua)
- [spec/unit/util_spec.lua](https://github.com/koreader/koreader/blob/9e6b1587/spec/unit/util_spec.lua)

## Purpose and Scope

This page documents text selection mechanisms in KOReader specifically for dictionary and Wikipedia lookup functionality. It covers word extraction, the hold gesture detection that triggers lookups, the `DictQuickLookup` widget that displays results, and integration with dictionary/Wikipedia/translation services. For information about text selection for highlighting and annotations, see [Highlight and Annotation System](/koreader/koreader/6.1-highlight-and-annotation-system). For dictionary management and installation, see [Dictionary and Wikipedia Integration](/koreader/koreader/8.1-dictionary-and-wikipedia-integration).

## Overview

Text selection for dictionary lookup begins with a hold gesture on the word or phrase. The system extracts text using document-specific APIs, then displays results in a `DictQuickLookup` popup widget. The widget shows definitions from installed dictionaries or fetches Wikipedia articles, with support for multiple dictionaries, language detection, and inline translation.

**Core Components:**

- `ReaderHighlight` - Detects hold gestures and extracts text
- `ReaderDictionary` - Manages dictionary queries and `sdcv` backend
- `ReaderWikipedia` - Fetches Wikipedia articles via API
- `DictQuickLookup` - Popup widget displaying lookup results
- `Translator` - Translation service integration
- Document text extraction APIs (`getWordFromPosition`, `getTextFromPositions`)

Sources: [frontend/apps/reader/modules/readerhighlight.lua89-164](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L89-L164)[frontend/apps/reader/modules/readerdictionary.lua67-70](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerdictionary.lua#L67-L70)[frontend/ui/widget/dictquicklookup.lua43-71](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/dictquicklookup.lua#L43-L71)

## Dictionary Lookup Flow

### Lookup Trigger Methods

Dictionary lookup can be initiated in several ways:
MethodTriggerHandlerHold gesture on textTouch device hold`ReaderHighlight:onHold()`Keyboard shortcutAlt+D or Ctrl+D`ReaderDictionary:onShowDictionaryLookup()`Manual inputMenu → Dictionary lookup`ReaderDictionary:onShowDictionaryLookup()`From highlight dialogTap Dictionary button`ReaderHighlight:lookupDict()`From DictQuickLookupSelect text in definition`DictQuickLookup:HoldReleaseText`
Sources: [frontend/apps/reader/modules/readerdictionary.lua183-186](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerdictionary.lua#L183-L186)[frontend/apps/reader/modules/readerhighlight.lua156-164](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L156-L164)

### Dictionary Lookup State Flow

```

```

Sources: [frontend/apps/reader/modules/readerhighlight.lua864-1054](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L864-L1054)[frontend/apps/reader/modules/readerdictionary.lua418-531](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerdictionary.lua#L418-L531)[frontend/ui/widget/dictquicklookup.lua104-225](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/dictquicklookup.lua#L104-L225)

## Word Extraction Mechanisms

### Single Word Extraction

When a hold gesture is detected, `ReaderHighlight:onHold()` extracts the word at the cursor position:

**Extraction Flow:**

```
Paging

Rolling

ReaderHighlight:onHold(ges)

ges.pos → {x, y}

view:screenToPageTransform(pos)

{x, y, page} or {x, y, xpointer}

Document Type

document:getWordFromPosition(pos)

document:getWordFromPosition(pos)
→ getTextFromPositions(x,y,x,y)

PdfDocument returns:
{x0, y0, x1, y1, word}

CreDocument returns:
{word, sbox, pos0, pos1}

util.cleanupSelectedText(word)

Build wordbox table:
{word, sbox, page, pos0, pos1}
```

Sources: [frontend/apps/reader/modules/readerhighlight.lua864-930](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L864-L930)[frontend/apps/reader/modules/readerhighlight.lua1676-1699](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L1676-L1699)

### Text Extraction for Phrases

For multi-word selections (via hold-pan gestures), extraction differs by document type:

**PDF/DjVu (Paging):**

- `getTextFromPositions(pos0, pos1)` called on document
- Returns text and array of box rectangles for each line
- Boxes used for highlighting and position tracking

**EPUB/HTML (Rolling):**

- `getTextFromPositions(x0, y0, x1, y1)` with draw flags
- CREngine returns `{text, pos0, pos1}` where pos0/pos1 are XPointers
- `getWordBoxesFromPositions()` generates visual boxes for rendering

Sources: [frontend/document/credocument.lua605-699](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/credocument.lua#L605-L699)[frontend/apps/reader/modules/readerhighlight.lua1700-1803](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L1700-L1803)

### Text Cleanup

The `util.cleanupSelectedText()` function normalizes extracted text:

```
Input:  "  Hello   world  \n  "
Output: "Hello world"

Cleanup operations:
1. Strip leading/trailing whitespace and newlines
2. Trim spaces around newlines  
3. Collapse consecutive spaces to single space

```

Sources: [frontend/util.lua60-70](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua#L60-L70)

### Dictionary Lookup Trigger Behavior

The `default_highlight_action` setting controls automatic action when text is selected:
Action ValueBehavior`"dictionary"`Immediately invoke `lookupDict()``"wikipedia"`Immediately invoke `lookupWikipedia()``"translate"`Immediately invoke `translate()``"ask"` (default)Show context menu with all options
**Single Word Optimization:**

The `highlight_action_on_single_word` setting affects single-word holds:

- When **false** (default): Single word triggers dictionary directly, bypassing menu
- When **true**: Single word shows full context menu with all action buttons

Sources: [frontend/apps/reader/modules/readerhighlight.lua696-747](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L696-L747)[frontend/apps/reader/modules/readerhighlight.lua895-945](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L895-L945)

## Dictionary Query and Results

### Dictionary Backend Integration

`ReaderDictionary` manages dictionary queries using the `sdcv` command-line tool (StarDict):

**Dictionary Discovery:**

```
No

Yes

ReaderDictionary:init()

available_ifos
cached?

Scan data directories

STARDICT_DATA_DIR
(default: data/dict)

STARDICT_DATA_DIR_ext
(optional)

getIfosInDir()
Recursive .ifo search

Parse each .ifo file:
- bookname=
- sametypesequence=
- lang=

Build available_ifos:
{file, name, is_html,
css, fix_html_func, lang}

sortAvailableIfos()

updateSdcvDictNamesOptions()
Build enabled_dict_names
```

Sources: [frontend/apps/reader/modules/readerdictionary.lua105-165](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerdictionary.lua#L105-L165)[frontend/apps/reader/modules/readerdictionary.lua39-65](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerdictionary.lua#L39-L65)

### Query Execution

**sdcv Command Construction:**

```
sdcv -n -j [dict options] "word"

-n: Don't output word list from stardict
-j: JSON output format
[dict options]: Multiple -u "dictname" for enabled dicts

Example:
sdcv -n -j -u "Webster's Dictionary" -u "Oxford" "hello"

```

The query results are parsed from JSON and converted to a results array for `DictQuickLookup`.

Sources: [frontend/apps/reader/modules/readerdictionary.lua418-531](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerdictionary.lua#L418-L531)

### Dictionary Language Detection

Dictionaries can specify input/output languages in `.ifo` metadata:

```
lang=en-fr  → English to French dictionary
lang=de     → German dictionary (monolingual)

```

`ReaderDictionary` uses this to:

- Filter dictionaries by detected word language
- Present appropriate dictionaries first
- Support language-specific dictionary presets

Sources: [frontend/apps/reader/modules/readerdictionary.lua139-157](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerdictionary.lua#L139-L157)

## DictQuickLookup Widget

### Widget Architecture

`DictQuickLookup` is a popup widget that displays dictionary results with navigation between multiple dictionaries:

```
Content Rendering

DictQuickLookup Widget Structure

Yes

No

DictQuickLookup

MovableContainer

FrameContainer

VerticalGroup

TitleBar
- Dictionary name
- Close button
- Menu button

Lookup word display
- TextWidget (bold)
- Edit button
- Result count

ScrollTextWidget or
ScrollHtmlWidget

ButtonTable
- Previous/Next dict
- Wikipedia/Highlight

is_html?

ScrollHtmlWidget
- Renders with MuPDF
- CSS support
- Image loading

ScrollTextWidget
- Plain text
- TextBoxWidget inside
```

Sources: [frontend/ui/widget/dictquicklookup.lua43-366](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/dictquicklookup.lua#L43-L366)

### Dictionary Navigation

Users can swipe or use buttons to navigate between dictionary results:

**Swipe Navigation:**

- Swipe left/right changes `dict_index`
- `changeDictionary(new_index)` updates content
- Results fetched from `self.results[dict_index]`

**Button Navigation:**

- "⇦ Dict" / "Dict ⇨" buttons at bottom
- Disabled when only one dictionary result
- Shows `X / Y` indicator for dictionary position

Sources: [frontend/ui/widget/dictquicklookup.lua542-638](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/dictquicklookup.lua#L542-L638)

### HTML Dictionary Support

Dictionaries marked with `sametypesequence=h` return HTML definitions:

**HTML Rendering Pipeline:**

```
MuPDF
ScrollHtmlWidget
fix_html_func
DictQuickLookup
ReaderDict
sdcv
MuPDF
ScrollHtmlWidget
fix_html_func
DictQuickLookup
ReaderDict
sdcv
alt
[fix_html_func
exists]
opt
[CSS provided]
JSON with HTML definition
Decode JSON
Create with is_html=true
Check for fix_html_func
Call with raw HTML
Corrected HTML
Create widget with HTML body
Parse and render HTML
Apply custom stylesheet
Rendered content
Display in widget
```

**Custom CSS Support:**

Dictionary `.ifo` files can have companion `.css` files for styling:

```
dict.ifo      → dictionary metadata
dict.css      → custom CSS rules
dict.lua      → HTML fix function

```

Sources: [frontend/apps/reader/modules/readerdictionary.lua72-103](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerdictionary.lua#L72-L103)[frontend/ui/widget/dictquicklookup.lua226-282](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/dictquicklookup.lua#L226-L282)

### Image Handling in Dictionaries

HTML dictionaries can include images referenced via relative paths:

**Image Resolution:**

- Images resolved relative to `.ifo` file location
- `dictionary_resource_directory` tracks the dict's `res/` folder
- Lazy loading: images load on-demand when scrolled into view

**Image Viewing:**

- Long-press on image opens `ImageViewer` with zoom controls
- Useful for pronunciation guides, illustrations, etc.

Sources: [frontend/ui/widget/scrollhtmlwidget.lua1-150](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/scrollhtmlwidget.lua#L1-L150)

## Wikipedia Integration

### Wikipedia Lookup Flow

`ReaderWikipedia` extends `ReaderDictionary` to provide Wikipedia article lookup:

```
User
DictQuickLookup
NetworkMgr
Wikipedia API
ReaderWikipedia
ReaderHighlight
User
DictQuickLookup
NetworkMgr
Wikipedia API
ReaderWikipedia
ReaderHighlight
alt
[Network unavailable]
opt
[User selects different language]
User selects "Wikipedia" button
lookupWikipedia(text)
Check network connectivity
Prompt WiFi enable
NetworkMgr:promptWifiOn()
Determine language
(wiki_languages setting)
HTTP GET to API
wiki_search_params or wiki_full_params
JSON response
Parse JSON results
Extract text, images, langlinks
Create with is_wiki=true
Display with Wikipedia UI
Show article
Tap language menu
Fetch article in new language
HTTP GET with new lang
```

Sources: [frontend/apps/reader/modules/readerwikipedia.lua1-29](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerwikipedia.lua#L1-L29)[frontend/ui/wikipedia.lua1-63](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/wikipedia.lua#L1-L63)

### Wikipedia API Parameters

The `Wikipedia` module constructs two types of queries:

**Search Query** (`wiki_search_params`):

```
{
    action = "query",
    generator = "search",
    gsrsearch = "query text",
    gsrlimit = 30,
    prop = "extracts|info|pageimages",
    exintro = "",
    explaintext = "",
}
```

**Full Article** (`wiki_full_params`):

```
{
    action = "query", 
    prop = "extracts|pageimages|langlinks",
    titles = "Article_Title",
    explaintext = "",
    redirects = "",
    lllimit = 500,
}
```

Sources: [frontend/ui/wikipedia.lua23-54](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/wikipedia.lua#L23-L54)

### Wikipedia Language Management

Users can configure Wikipedia languages for lookup:

**Language Configuration:**

- Stored in `G_reader_settings` as `wikipedia_languages`
- Per-document language saved in doc settings
- Language menu in `DictQuickLookup` for multi-language articles
- Last used language cached for quick access

**Language Detection:**

- Can use document language if available
- Fallback to UI language or English
- User can manually select from configured languages

Sources: [frontend/apps/reader/modules/readerwikipedia.lua31-50](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerwikipedia.lua#L31-L50)[frontend/ui/widget/dictquicklookup.lua84-102](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/dictquicklookup.lua#L84-L102)

## Translation Service Integration

### Translation Flow

`Translator` module provides text translation via Google Translate or Bing:

```
Google

Bing

User selects 'Translate'

ReaderHighlight:translate()

NetworkMgr:promptWifiOn()

Translation
Provider?

Translator:loadPage()
translate.googleapis.com

Translator:loadPage()
api.cognitive.microsofttranslator.com

Parse JSON response

Show in TextViewer
```

**Language Detection:**

- Source language: auto-detect or user-specified
- Target language: UI language or user-specified
- Language settings in `G_reader_settings` as `translator_from_language` and `translator_to_language`

Sources: [frontend/ui/translator.lua1-27](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/translator.lua#L1-L27)[frontend/apps/reader/modules/readerhighlight.lua1864-1904](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L1864-L1904)

### Translation API Integration

The `Translator:loadPage()` method handles API requests:

**Google Translate:**

```
URL: https://translate.googleapis.com/translate_a/single
Parameters:
  client=gtx
  sl=auto (or source lang)
  tl=<target lang>
  dt=t
  q=<text to translate>

```

**Response parsing:**

- JSON array with translation segments
- First segment contains translated text
- Source language detection returned separately

Sources: [frontend/ui/translator.lua1-270](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/translator.lua#L1-L270)

## Dictionary Lookup Settings

### Dictionary Management

Dictionaries are managed through the Settings menu:

**Dictionary Discovery:**

- Scanned from `STARDICT_DATA_DIR` (default: `data/dict/`)
- Optional extension directory: `STARDICT_DATA_DIR_ext`
- Recursive `.ifo` file search, excluding `res/` subdirectories

**Dictionary Control:**

- `dicts_disabled` - Map of disabled dictionary file paths
- `dicts_order` - Map of explicit dictionary ordering
- Dictionaries sorted by order preference, then lexically

**Dictionary Presets:**

- Save/load sets of enabled dictionaries
- Useful for language-specific dictionary groups
- Stored in `G_reader_settings` as `dict_presets`

Sources: [frontend/apps/reader/modules/readerdictionary.lua105-230](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerdictionary.lua#L105-L230)

### Lookup History

Dictionary lookups are tracked in `lookup_history.lua`:

**History Structure:**

```
{
    word = "searched term",
    book_title = "Current Book Title",
    time = os.time(),
}
```

**History Features:**

- Accessible via Menu → Dictionary lookup history
- Grouped by book title
- Tappable to re-lookup word
- Can be disabled with `disable_lookup_history` setting

Sources: [frontend/apps/reader/modules/readerdictionary.lua166-168](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerdictionary.lua#L166-L168)[frontend/apps/reader/modules/readerdictionary.lua234-273](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerdictionary.lua#L234-L273)

### Fuzzy Search

For dictionary lookup, fuzzy matching can find words with slight misspellings:

- **Setting**: `disable_fuzzy_search` (default: false)
- **Behavior**: When enabled, `sdcv` performs approximate matching
- Useful for inflected forms, typos, or partial words

Sources: [frontend/apps/reader/modules/readerdictionary.lua111](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerdictionary.lua#L111-L111)

## DictQuickLookup Features

### Text Selection in Definitions

Users can select text within dictionary definitions for recursive lookup:

**Touch Selection:**

- Hold gesture on text in `ScrollTextWidget` or `ScrollHtmlWidget`
- `HoldReleaseText` gesture event with extracted text
- Hold duration determines lookup domain:

- Short hold (< 3s): Same domain (dict→dict, wiki→wiki)
- Long hold (≥ 3s): Switch domain (dict→wiki, wiki→dict)

**Callback Chain:**

```
DictQuickLookup → HoldReleaseText args function
  → LookupWord or lookupWikipedia
    → New DictQuickLookup instance

```

Sources: [frontend/ui/widget/dictquicklookup.lua161-200](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/dictquicklookup.lua#L161-L200)

### Dictionary Window Sizing

`DictQuickLookup` supports two window size modes:

**Normal Mode:**

- Width: `Screen:getWidth() - 80` (scaled)
- Suitable for quick lookups

**Large Window Mode:**

- Width: `Screen:getWidth() - 2*Size.margin.default`
- Activated by:

- Wikipedia full-page articles (`is_wiki_fullpage`)
- User setting: `dict_largewindow`
- Temporary spread gesture

**Temporary Large Window:**

- Spread gesture on `DictQuickLookup` triggers fullscreen
- Stored in class-level `temp_large_window_request`
- Dismissed on next lookup or manual close

Sources: [frontend/ui/widget/dictquicklookup.lua214-243](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/dictquicklookup.lua#L214-L243)

### Edit and Re-lookup

Users can edit the lookup word and re-query:

- **Edit Button**: Pencil icon next to lookup word
- **Tap**: Edit current lookup word
- **Hold**: Edit original selected word (if different)
- Opens `InputDialog` with pre-filled text
- New lookup initiated on confirmation

Sources: [frontend/ui/widget/dictquicklookup.lua308-324](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/dictquicklookup.lua#L308-L324)

## Integration with Other Systems

### Highlight Storage

Selected text is converted to highlight data structure and passed to:

- `ReaderAnnotation:addItem()` - Stores in annotations array
- `ReaderHighlight:saveHighlight()` - Handles PDF writing if enabled

See [Highlight and Annotation System](/koreader/koreader/6.1-highlight-and-annotation-system) and [Annotation Data Management](/koreader/koreader/6.5-reader-footer-and-status-display) for details.

Sources: [frontend/apps/reader/modules/readerhighlight.lua1990-2069](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L1990-L2069)

### Dictionary and Wikipedia Lookup

Context menu actions delegate to:

- `ReaderDictionary:onLookupWord()` - Dictionary lookup
- `ReaderWikipedia:lookupWikipedia()` - Wikipedia lookup

These modules handle the actual lookup UI and network requests. See [Dictionary and Wikipedia Integration](/koreader/koreader/8.1-dictionary-and-wikipedia-integration).

Sources: [frontend/apps/reader/modules/readerhighlight.lua1805-1862](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L1805-L1862)

### Translation Service

The Translate action uses:

- `Translator:loadPage()` - Google Translate or Bing Translator
- Network connectivity check via `NetworkMgr`
- Results displayed in `TextViewer`

Sources: [frontend/apps/reader/modules/readerhighlight.lua1864-1904](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L1864-L1904)

### Search Integration

The Search action:

- Stores selected text in `self.ui.search` state
- Broadcasts `ShowSearchDialog` event
- `ReaderSearch` module handles the actual search UI

Sources: [frontend/apps/reader/modules/readerhighlight.lua1907-1931](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L1907-L1931)

## Visual Feedback

### Temporary Highlight Rendering

During selection (before dialog is shown), temporary highlights are drawn:

1. **Storage**: `self.highlight.temp[page] = boxes_array`
2. **Rendering**: `ReaderView:drawTempHighlight()` in paint cycle
3. **Style**: Uses `self.highlight.temp_drawer` (default: "invert")
4. **Cleanup**: Cleared when dialog is shown or selection cancelled

Sources: [frontend/apps/reader/modules/readerview.lua524-533](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerview.lua#L524-L533)

### Highlight Indicator (Non-Touch)

The crosshairs indicator for non-touch devices:

```
drawHighlightIndicator(bb, x, y):
  - Draws vertical line at indicator.x
  - Draws horizontal line at indicator.y
  - Lines are Size.border.thick width
  - Forms a crosshairs (+) at selection point

```

Sources: [frontend/apps/reader/modules/readerview.lua507-522](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerview.lua#L507-L522)

### Visual Settings
SettingDefaultPurpose`highlight_lighten_factor`0.2Opacity for "lighten" drawer`highlight_height_pct`100Percentage of line height to highlight`highlight_note_marker`variesVisual marker for notes (underline, sideline, etc.)
Sources: [frontend/apps/reader/modules/readerhighlight.lua459-530](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua#L459-L530)

---

# Navigation-and-Page-Management

# Navigation and Page Management
Relevant source files
- [frontend/apps/reader/modules/readerbookmark.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerbookmark.lua)
- [frontend/apps/reader/modules/readerfont.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfont.lua)
- [frontend/apps/reader/modules/readerhighlight.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua)
- [frontend/apps/reader/modules/readerlink.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerlink.lua)
- [frontend/apps/reader/modules/readerpaging.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerpaging.lua)
- [frontend/apps/reader/modules/readerrolling.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerrolling.lua)
- [frontend/apps/reader/modules/readertoc.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readertoc.lua)
- [frontend/apps/reader/modules/readerview.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerview.lua)
- [frontend/document/credocument.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/credocument.lua)

## Purpose and Scope

This document describes the navigation and page management systems in KOReader, covering how users move through documents and how reading positions are tracked and represented. The system handles two fundamentally different document types: paged documents (PDF, DjVu) and reflowable documents (EPUB, HTML), each with distinct navigation requirements.

For information about:

- Document rendering and display, see [Rendering Pipeline and Caching](/koreader/koreader/5.3-rendering-pipeline-and-caching)
- Highlight and annotation management, see [Highlight and Annotation System](/koreader/koreader/6.1-highlight-and-annotation-system)
- Footer progress display, see [Reader Footer and Status Display](#6.6)
- Table of contents structure and display, see [Bookmarks and Table of Contents](/koreader/koreader/6.4-bookmarks-and-table-of-contents)

---

## Architecture Overview

KOReader implements separate navigation modules for paged and reflowable documents due to their fundamentally different position models. Paged documents use page numbers, while reflowable documents use XPointers (XML path expressions) that adapt to rendering changes.

### Navigation Module Structure

```
Navigation Actions

Position Models

Navigation Modules

paging mode

rolling mode

handles

handles

handles

handles

handles

ReaderUI
(main reader)

ReaderPaging
PDF/DjVu navigation

ReaderRolling
EPUB/HTML navigation

ReaderToc
TOC navigation

ReaderLink
Link following & history

ReaderPageMap
Reference page labels

Page Number
(integer)

XPointer
(string path)

Page Position
(fractional 0-1)

GotoViewRel
Relative page turn

GotoPage
Absolute page jump

GotoXPointer
XPointer jump

GotoPercent
Percentage jump
```

**Sources:**

- [frontend/apps/reader/modules/readerpaging.lua29-40](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerpaging.lua#L29-L40)
- [frontend/apps/reader/modules/readerrolling.lua55-85](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerrolling.lua#L55-L85)
- [frontend/apps/reader/modules/readertoc.lua25-36](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readertoc.lua#L25-L36)
- [frontend/apps/reader/modules/readerlink.lua65-70](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerlink.lua#L65-L70)

---

## Position Tracking and Representation

### Position Models by Document Type
Document TypePosition RepresentationStorage KeyPersistenceCode LocationPDF/DjVu`current_page` (integer)`last_page`Exact page number`ReaderPaging.current_page`PDF/DjVu`page_positions[page]` (0.0-1.0)`page_positions`Fractional position on each page`ReaderPaging.page_positions`EPUB/HTML`xpointer` (string)`last_xpointer`DOM path survives reflow`ReaderRolling.xpointer`EPUB/HTML`current_pos` (integer)N/ARendering-specific position`ReaderRolling.current_pos`
**Sources:**

- [frontend/apps/reader/modules/readerpaging.lua29-40](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerpaging.lua#L29-L40) - `ReaderPaging` definition with `current_page` and `page_positions` fields
- [frontend/apps/reader/modules/readerpaging.lua149-163](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerpaging.lua#L149-L163) - `onReadSettings` and `onSaveSettings` showing persistence
- [frontend/apps/reader/modules/readerrolling.lua55-85](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerrolling.lua#L55-L85) - `ReaderRolling` definition with `current_pos` and `xpointer` fields
- [frontend/apps/reader/modules/readerrolling.lua157-236](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerrolling.lua#L157-L236) - `onReadSettings` loading and setting up `xpointer`

### XPointer System (Reflowable Documents)

XPointers are XML path expressions that identify positions in the document DOM structure. They remain valid across font size changes, margin adjustments, and screen orientation changes, unlike page numbers which are rendering-dependent.

```
XPointer Lifecycle

defines closure

calls

updates

on close

onReadSettings
Load last_xpointer

setupXpointer
Position document

_gotoXPointer
Navigate to position

Update xpointer
After navigation

onSaveSettings
Save last_xpointer
```

The XPointer serves as the **canonical position** for reflowable documents. The `current_page` and `current_pos` values are derived from the XPointer and may change when rendering changes, but the XPointer remains stable.

**Sources:**

- [frontend/apps/reader/modules/readerrolling.lua201-236](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerrolling.lua#L201-L236)
- [frontend/apps/reader/modules/readerrolling.lua503-511](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerrolling.lua#L503-L511)

### Page Position System (Paged Documents)

For paged documents, a fractional position (0.0 to 1.0) is stored for each visited page. This allows the reader to return to approximately the same vertical position on a page even after zoom or reflow changes.

```
stores

retrieves from

persisted by

setPagePosition(page, pos)

getPagePosition(page)

page_positions table
{[page] = position}

Save to doc_settings
```

**Sources:**

- [frontend/apps/reader/modules/readerpaging.lua180-203](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerpaging.lua#L180-L203)

---

## Page Turning Mechanisms

### Input Event Flow

Page turning is initiated through multiple input methods: touch gestures, physical keys, and programmatic events. All methods converge on core navigation handlers.

```
Navigation Implementation

Event Handlers

Touch Zone Registration

Input Sources

paging

rolling

Tap gesture
tap_forward/backward

Swipe gesture
east/west

Key press
RPgFwd/RPgBack

Menu command
Dispatcher action

setupTouchZones
Register screen zones

forward_zone

backward_zone

onSwipe

via touch zone handler

onGotoViewRel(direction)

_gotoPage (paging)

_gotoPos (rolling)

_gotoXPointer (rolling)
```

**Sources:**

- [frontend/apps/reader/modules/readerpaging.lua29-40](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerpaging.lua#L29-L40) - `ReaderPaging` fields including `page_flipping_mode`
- [frontend/apps/reader/modules/readerpaging.lua96-147](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerpaging.lua#L96-L147) - `setupTouchZones` for tap forward/backward handlers
- [frontend/apps/reader/modules/readerrolling.lua358-409](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerrolling.lua#L358-L409) - `setupTouchZones` for rolling mode

### View Modes and Navigation Behavior
ModeDocument TypeNavigation UnitKey HandlerView Mode ConstantPage (single)PDF/DjVuOne page`onGotoViewRel``view.page_scroll = false`Page (scroll)PDF/DjVuMultiple visible pages`onGotoViewRel``view.page_scroll = true`PageEPUB/HTMLOne rendered page`onGotoViewRel``view_mode = "page"` (PAGE_VIEW_MODE)ScrollEPUB/HTMLPixel-based scrolling`onPan` / `onPanRelease``view_mode = "scroll"` (SCROLL_VIEW_MODE)
**Sources:**

- [frontend/apps/reader/modules/readerpaging.lua358-397](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerpaging.lua#L358-L397) - `onSwipe` checking `view.page_scroll`
- [frontend/apps/reader/modules/readerrolling.lua21-24](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerrolling.lua#L21-L24) - `SCROLL_VIEW_MODE` and `PAGE_VIEW_MODE` constants in `CreDocument`
- [frontend/apps/reader/modules/readerrolling.lua539-571](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerrolling.lua#L539-L571) - `onSwipe` handling

### Paging Navigation Implementation

**Diagram: ReaderPaging Navigation Flow**

```
normal

single page

scroll mode

onGotoViewRel(diff)

Check page_flipping_mode

Check view.page_scroll

_gotoPage(page)

onPaging(page_diff, page)

setPagePosition(page, pos)

view.footer:onUpdateFooter()

ui:handleEvent(PageUpdate)
```

The `_gotoPage` function is the core navigation method for paged documents. It:

1. Validates the target page number
2. Updates `current_page`
3. Stores page position if changed
4. Emits `PageUpdate` event
5. Triggers UI refresh

Additionally, the `copyPageState` helper function [frontend/apps/reader/modules/readerpaging.lua16-26](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerpaging.lua#L16-L26) creates deep copies of page state for undo/history operations, preserving page, zoom, rotation, gamma, offset, visible_area, and page_area.

**Sources:**

- [frontend/apps/reader/modules/readerpaging.lua16-26](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerpaging.lua#L16-L26) - `copyPageState` helper function
- [frontend/apps/reader/modules/readerpaging.lua187-203](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerpaging.lua#L187-L203) - `setPagePosition` and `getPagePosition` methods

### Rolling Navigation Implementation

**Diagram: ReaderRolling Navigation Flow**

```
onGotoViewRel(diff)

Get current_pos

Calculate new position

_gotoPos(new_pos)

self.view:gotoPos

self.xpointer = document:getXPointer()

Emit PosUpdate event
```

For reflowable documents, navigation operates on `current_pos` (pixel position) but maintains the canonical `xpointer` after each navigation action. The `getLastProgress` method [frontend/apps/reader/modules/readerrolling.lua411-413](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerrolling.lua#L411-L413) returns the `xpointer` as the canonical progress indicator.

**Sources:**

- [frontend/apps/reader/modules/readerrolling.lua411-413](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerrolling.lua#L411-L413) - `getLastProgress` returning `xpointer`
- [frontend/apps/reader/modules/readerrolling.lua502-510](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerrolling.lua#L502-L510) - `getLastPercent` calculation

---

## Navigation History and Location Stack

`ReaderLink` maintains a location stack that enables "back" navigation similar to a web browser. This is essential for following links and returning to the previous reading position.

### Location Stack Structure

```
Location Data

Location Stack Management

push

pop from

push to

pop from

push to

reset

reset

location_stack
(LIFO array)

forward_location_stack
(LIFO array)

addCurrentLocationToStack

onGoBackLink

onGoForwardLink

onClearLocationStack

page or xpointer

ratio (position)
```

**Sources:**

- [frontend/apps/reader/modules/readerlink.lua609-706](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerlink.lua#L609-L706)

### Book Location Structure

A book location contains:

```
{
    page = <page_number> or <xpointer>,  -- Position reference
    pos0 = <position>,                    -- Start position (for text selection)
    pos1 = <position>,                    -- End position (for text selection)
    ratio = <0.0-1.0>,                    -- Fractional page position
}
```

**Sources:**

- [frontend/apps/reader/modules/readerlink.lua708-744](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerlink.lua#L708-L744)

---

## Table of Contents Navigation

`ReaderToc` provides structured chapter navigation. It maintains a TOC tree and offers various ways to navigate through document structure.

### TOC Data Structure

```
Navigation Methods

TOC Structure

contains

contains

contains

contains

toc array
(sorted by position)

TOC Item

title (string)

page (number/xpointer)

depth (integer)

seq_in_level

getTocIndexByPage

getTocTitleByPage

getChapterPagesLeft

getChapterProgress
```

**Sources:**

- [frontend/apps/reader/modules/readertoc.lua166-353](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readertoc.lua#L166-L353)

### Chapter Navigation

Chapter navigation uses the TOC structure to jump between chapters:

```
onGotoNextChapter

onGotoPrevChapter

getTocIndexByPage
(current position)

Find next/prev
TOC entry

Navigate to
chapter page
```

**Sources:**

- [frontend/apps/reader/modules/readertoc.lua913-973](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readertoc.lua#L913-L973)

### TOC Ticks and Progress Bar

The footer progress bar can display TOC tick marks representing chapter boundaries. The `ticks_flattened` structure provides a flat list of TOC positions for rendering.

**Sources:**

- [frontend/apps/reader/modules/readertoc.lua667-737](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readertoc.lua#L667-L737)

---

## Page Labels and Reference Pages

`ReaderPageMap` handles reference page numbers that may differ from the renderer's page numbers. This is common in EPUBs that reference a specific print edition.

### PageMap Architecture

```
Key Methods

PageMap System

Document page map
(from EPUB metadata)

page_labels_cache
(label → index/page)

Visible page labels
(rendered in margins)

getCurrentPageLabel

getPageLabelProps

updateVisibleLabels

cleanPageLabel
```

**Sources:**

- [frontend/apps/reader/modules/readerpagemap.lua20-89](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerpagemap.lua#L20-L89)
- [frontend/apps/reader/modules/readerpagemap.lua272-304](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerpagemap.lua#L272-L304)

### Page Label Usage

When `use_page_labels` is enabled, the footer and "Go to page" dialog display reference page labels instead of renderer page numbers. The mapping between labels and actual pages is maintained in the cache.

**Sources:**

- [frontend/apps/reader/modules/readerpagemap.lua306-324](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerpagemap.lua#L306-L324)

---

## Special Navigation Modes

### Page Flipping Mode

Page flipping mode allows rapid navigation through a document for skimming purposes. It temporarily changes zoom and scroll settings for easier browsing.

**Diagram: Page Flipping Mode State Transitions**

```
Normal
FlippingMode

onTogglePageFlipping

onTogglePageFlipping

SaveOrigSettings

enterFlippingMode

SetViewVisible

Save orig_reflow_mode,
orig_scroll_mode,
orig_zoom_mode

exitFlippingMode

RestoreSettings

Restore reflow_mode,
scroll_mode,
zoom_mode
```

The flipping mode state is tracked in `ReaderPaging.page_flipping_mode` and `ReaderPaging.flipping_page`. When entering flipping mode, the original settings are saved:

- `orig_reflow_mode` - text wrap setting
- `orig_scroll_mode` - page scroll setting
- `orig_zoom_mode` - zoom mode setting

**Sources:**

- [frontend/apps/reader/modules/readerpaging.lua29-40](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerpaging.lua#L29-L40) - `ReaderPaging` fields including `page_flipping_mode`, `flipping_page`
- [frontend/apps/reader/modules/readerpaging.lua205-224](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerpaging.lua#L205-L224) - `onTogglePageFlipping` implementation
- [frontend/apps/reader/modules/readerpaging.lua244-263](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerpaging.lua#L244-L263) - `enterFlippingMode` and `exitFlippingMode` methods

### Bookmark Flipping Mode

Bookmark flipping mode enables quick navigation between bookmarked pages using swipe gestures. It's a specialized navigation mode where:

- `bookmark_flipping_mode` flag is toggled via `onToggleBookmarkFlipping`
- `orig_flipping_mode` stores the previous flipping visibility state
- `bm_flipping_orig_page` stores the original page to return to
- Swipe gestures trigger `bookmarkFlipping` method instead of normal page navigation

**Sources:**

- [frontend/apps/reader/modules/readerpaging.lua29-40](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerpaging.lua#L29-L40) - `ReaderPaging` fields including `bookmark_flipping_mode`
- [frontend/apps/reader/modules/readerpaging.lua226-242](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerpaging.lua#L226-L242) - `onToggleBookmarkFlipping` implementation
- [frontend/apps/reader/modules/readerpaging.lua291-299](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerpaging.lua#L291-L299) - `bookmarkFlipping` method handling swipe direction

### Partial Rerendering (Rolling)

For EPUB documents, `ReaderRolling` supports partial rerendering where only the current chapter is rerendered when settings change, with a full rerendering happening in the background.

**Diagram: Partial Rerendering State Machine**

```
Style/font setting change

Partial rerender
(current chapter only)
enablePartialRerendering(true)

Background full rerender
(forked subprocess)
shared_state[0] = pid

Cache complete
shared_state[1] = 1

Seamless document reload
ui:reloadDocument()
```

The `rendering_state` field [frontend/apps/reader/modules/readerrolling.lua70-85](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerrolling.lua#L70-L85) tracks the rerendering progress using the `RENDERING_STATE` enum:

- `FULLY_RENDERED` (nil): Normal state
- `PARTIALLY_RERENDERED` (1): Current chapter rerendered
- `FULL_RENDERING_IN_BACKGROUND` (2): Background process active
- `FULL_RENDERING_READY` (3): Cache ready for reload
- `RELOADING_DOCUMENT` (4): Document reload in progress
- `DO_RELOAD_DOCUMENT` (5): Trigger reload

The background rerendering uses a shared memory segment (`shared_state`) [frontend/apps/reader/modules/readerrolling.lua23-33](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerrolling.lua#L23-L33) for IPC between the main process and the forked subprocess:

- `shared_state[0]`: PID of current subprocess
- `shared_state[1]`: Set by subprocess when rendering done, waiting to save cache
- `shared_state[2]`: Set by main process when subprocess can proceed with saving cache

**Sources:**

- [frontend/apps/reader/modules/readerrolling.lua23-33](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerrolling.lua#L23-L33) - `shared_state` mmap'd memory for IPC
- [frontend/apps/reader/modules/readerrolling.lua70-85](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerrolling.lua#L70-L85) - `RENDERING_STATE` enum definition
- [frontend/apps/reader/modules/readerrolling.lua443-500](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerrolling.lua#L443-L500) - `partial_rerendering` menu item and state management

---

## Navigation Event Summary
Event NameTriggerHandler (Paging)Handler (Rolling)`GotoViewRel`Relative page turn`onGotoViewRel``onGotoViewRel``GotoPage`Absolute page jump`onGotoPage`N/A`GotoXPointer`XPointer navigationN/A`onGotoXPointer``GotoPercent`Percentage jump`onGotoPercent``onGotoPercent``GotoPosRel`Relative position pan`onGotoPosRel`N/A`GotoNextChapter`Chapter navigationVia TOCVia TOC`GotoPrevChapter`Chapter navigationVia TOCVia TOC`PageUpdate`Page changedEmittedN/A`PosUpdate`Position changedN/AEmitted
**Sources:**

- [frontend/apps/reader/modules/readerpaging.lua53-84](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerpaging.lua#L53-L84)
- [frontend/apps/reader/modules/readerrolling.lua118-153](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerrolling.lua#L118-L153)

---

# Bookmarks-and-Table-of-Contents

# Bookmarks and Table of Contents
Relevant source files
- [frontend/apps/reader/modules/readerbookmark.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerbookmark.lua)
- [frontend/apps/reader/modules/readerfont.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfont.lua)
- [frontend/apps/reader/modules/readerhighlight.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerhighlight.lua)
- [frontend/apps/reader/modules/readerlink.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerlink.lua)
- [frontend/apps/reader/modules/readerpaging.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerpaging.lua)
- [frontend/apps/reader/modules/readerrolling.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerrolling.lua)
- [frontend/apps/reader/modules/readertoc.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readertoc.lua)
- [frontend/apps/reader/modules/readerview.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerview.lua)
- [frontend/document/credocument.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/credocument.lua)

## Purpose and Scope

This document describes the bookmark and Table of Contents (TOC) systems in KOReader's reader module. These systems allow users to mark specific locations in documents and navigate via the document's structural hierarchy. For information about highlights and notes, see [Highlight and Annotation System](/koreader/koreader/6.1-highlight-and-annotation-system), which also covers the unified annotation storage mechanism used by bookmarks.

## Overview

KOReader provides two complementary navigation aids:

- **Bookmarks**: User-created markers at specific document locations (page bookmarks), stored alongside highlights in a unified annotation system
- **Table of Contents**: Document-provided structural navigation extracted from the book's metadata or generated as an alternative

Both systems are implemented as reader modules that integrate with the broader reader feature set, including the footer display, progress indicators, and navigation history.

**Key Modules:**

- `ReaderBookmark` ([frontend/apps/reader/modules/readerbookmark.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerbookmark.lua)) - Bookmark creation, management, and browsing UI
- `ReaderToc` ([frontend/apps/reader/modules/readertoc.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readertoc.lua)) - TOC extraction, validation, and navigation UI
- `ReaderAnnotation` - Unified storage for bookmarks and highlights (see [Highlight and Annotation System](/koreader/koreader/6.1-highlight-and-annotation-system))

## Bookmark System Architecture

### Component Overview

```
Data Model

Visual Indicators

Storage Layer

Bookmark UI

manages

manages

add/remove

update visibility

trigger update

contains

contains

sorted with

ReaderBookmark
InputContainer

Bookmark Menu
List Display

Bookmark Search

ReaderAnnotation
annotations array

ReaderDogear
Corner Indicator

ReaderFooter
Bookmark Count

Page Bookmark
drawer: nil

Highlight
drawer: string
```

**Diagram: Bookmark System Components**

Sources: [frontend/apps/reader/modules/readerbookmark.lua1-1100](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerbookmark.lua#L1-L1100)

### Bookmark Data Model

Bookmarks in KOReader are stored as a specific type of annotation with no `drawer` field. They coexist with highlights in the `ReaderAnnotation.annotations` array, sorted by position order.

**Bookmark Structure:**

```
{
    datetime         -- creation timestamp (ISO format string)
    datetime_updated -- last modification timestamp (if edited)
    drawer           -- nil for bookmarks, string for highlights ("lighten", "underscore", etc.)
    text             -- display text: chapter info or custom text
    note             -- user-added note text (optional)
    chapter          -- chapter title extracted from TOC
    page             -- xpointer string (EPUB) or page number (PDF/DjVu)
    pos0             -- start position {x, y, page} (PDF/DjVu highlights only)
    pos1             -- end position {x, y, page} (PDF/DjVu highlights only)
}
```

**Key distinction**: Page bookmarks have `drawer == nil`, while highlights have a drawer type like `"lighten"`, `"underscore"`, `"strikeout"`, or `"invert"`.

**Creation**: When a bookmark is created at line [frontend/apps/reader/modules/readerbookmark.lua413-418](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerbookmark.lua#L413-L418) it queries `ReaderToc:getTocTitleByPage()` to populate the `chapter` field and formats the `text` field as "in [chapter]" if a chapter title exists.

Sources: [frontend/apps/reader/modules/readerbookmark.lua28-40](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerbookmark.lua#L28-L40)[frontend/apps/reader/modules/readerbookmark.lua389-421](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerbookmark.lua#L389-L421)

### Bookmark Creation and Management

```
ReaderView
ReaderToc
ReaderAnnotation
ReaderBookmark
User
ReaderView
ReaderToc
ReaderAnnotation
ReaderBookmark
User
alt
[Bookmark exists]
[No bookmark]
onToggleBookmark()
getCurrentPageNumber()
getDogearBookmarkIndex(page)
table.remove(annotations, index)
index = -index (negative)
getTocTitleByPage(page)
chapter title
Create bookmark item
addItem(item)
positive index
Event("AnnotationsModified")
dogear:onSetDogearVisibility()
footer:maybeUpdateFooter()
```

**Diagram: Bookmark Toggle Flow**

The bookmark toggle operation [frontend/apps/reader/modules/readerbookmark.lua389-421](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerbookmark.lua#L389-L421):

1. Converts page number to xpointer for EPUB documents via `document:getPageXPointer(pageno)`
2. Calls `getDogearBookmarkIndex(pn_or_xp)` to check if bookmark exists at the position
3. If found, removes it from the annotations array via `table.remove(self.ui.annotation.annotations, index)`
4. If not found:

- Queries `ReaderToc:getTocTitleByPage(pn_or_xp)` for chapter information
- Creates bookmark item with `page`, `text`, and `chapter` fields
- Calls `ReaderAnnotation:addItem(item)` to insert into sorted annotations array
5. Broadcasts `Event:new("AnnotationsModified", { item, index_modified = index })` event
6. Updates visual indicators (dogear visibility, footer bookmark count)

**Note**: The `getDogearBookmarkIndex()` function [frontend/apps/reader/modules/readerbookmark.lua443-468](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerbookmark.lua#L443-L468) uses binary search to efficiently find bookmarks (items with `drawer == nil`) at a specific page position.

Sources: [frontend/apps/reader/modules/readerbookmark.lua375-387](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerbookmark.lua#L375-L387)[frontend/apps/reader/modules/readerbookmark.lua389-421](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerbookmark.lua#L389-L421)[frontend/apps/reader/modules/readerbookmark.lua443-468](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerbookmark.lua#L443-L468)

### Bookmark Browsing Interface

The bookmark browser displays all annotations (bookmarks, highlights, notes) in a unified menu with filtering and sorting options.

**Key Features:**

- **Display prefixes**: Visual indicators from `display_prefix` table [frontend/apps/reader/modules/readerbookmark.lua30-34](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerbookmark.lua#L30-L34):

- `"\u{2592}\u{2002}"` (medium shade) for highlights
- `"\u{F040}\u{2002}"` (pencil icon) for notes
- `"\u{F097}\u{2002}"` (empty bookmark icon) for bookmarks
- **Configurable sorting**: By page number or date, with reverse option (`bookmarks_items_sorting`, `bookmarks_items_reverse_sorting`)
- **Flexible item heights**: Max lines per bookmark setting (1-10 or disabled via `bookmarks_items_max_lines`)
- **Search functionality**: Full-text search across all annotation text in `onSearchBookmark()`[frontend/apps/reader/modules/readerbookmark.lua903-1087](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerbookmark.lua#L903-L1087)
- **Items per page**: Configurable (6-24 items via `bookmarks_items_per_page`)
- **Font size**: Separate from file browser settings (`bookmarks_items_font_size`)

**Menu Generation:**
The bookmark menu is built on-demand in `getBookmarkMenuItems()`, which:

1. Retrieves `self.ui.annotation.annotations` array
2. Filters annotations by type if needed
3. Formats each item with `display_prefix`, text excerpt, and page/date metadata
4. Applies sorting (by page via `isBookmarkInPageOrder()` or by date)
5. Creates `Menu` widget with configured layout parameters

Sources: [frontend/apps/reader/modules/readerbookmark.lua30-40](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerbookmark.lua#L30-L40)[frontend/apps/reader/modules/readerbookmark.lua109-318](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerbookmark.lua#L109-L318)[frontend/apps/reader/modules/readerbookmark.lua903-1087](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerbookmark.lua#L903-L1087)

### Dogear Visualization

The dogear is a visual indicator (corner fold) shown when the current page has a bookmark.

```
setDogearVisibility

paintTo

onToggleBookmark

Yes

No

ReaderBookmark

ReaderDogear

ReaderView

Has bookmark?

Show dogear

Hide dogear

UIManager:setDirty
```

**Diagram: Dogear Update Flow**

The dogear visibility is controlled by `ReaderBookmark:setDogearVisibility()`[frontend/apps/reader/modules/readerbookmark.lua423-426](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerbookmark.lua#L423-L426) which:

1. Calls `isPageBookmarked(pn_or_xp)` to check if current page has a bookmark
2. Invokes `self.view.dogear:onSetDogearVisibility(visible)` to update the visual state

Updated on:

- Page changes: `onPageUpdate()` and `onPosUpdate()` event handlers
- Bookmark toggle operations: `onToggleBookmark()` triggers dogear refresh
- Document opening: Initial dogear state set after document load

Sources: [frontend/apps/reader/modules/readerbookmark.lua423-431](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerbookmark.lua#L423-L431)[frontend/apps/reader/modules/readerview.lua126-150](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerview.lua#L126-L150)

## Table of Contents System

### TOC Data Structure

The TOC is represented as a flat array of items with depth indicators, stored in `ReaderToc.toc`:

```
toc[i] = {
    title         -- chapter/section title
    page          -- xpointer (EPUB) or page number (PDF)
    depth         -- nesting level (1, 2, 3, ...)
    xpointer      -- xpointer string (EPUB only)
    chapter_length -- pages in this chapter (computed)
    seq_in_level  -- sequence number at this depth
}

```

**TOC Sources:**

1. **Document TOC**: Extracted from document metadata (EPUB `<nav>`, PDF outline)
2. **Alternative TOC**: Generated from document structure (headings, font changes)
3. **Handmade TOC**: User-created custom TOC

Sources: [frontend/apps/reader/modules/readertoc.lua25-36](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readertoc.lua#L25-L36)[frontend/apps/reader/modules/readertoc.lua166-180](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readertoc.lua#L166-L180)[frontend/document/credocument.lua1-100](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/credocument.lua#L1-L100)

### TOC Extraction and Validation

```
Yes

No

Enabled

No

Yes

No

fillToc called

toc exists?

Return

Alternative TOC?

buildAlternativeToc

getToc from document

validateAndFixToc

Quick scan for order

Has bogus pages?

Fix wrong page numbers

Compute max_depth

Compute seq_in_level

TOC ready
```

**Diagram: TOC Loading and Validation**

The validation process [frontend/apps/reader/modules/readertoc.lua182-322](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readertoc.lua#L182-L322) handles cases where TOC entries have incorrect or out-of-order page numbers:

- Performs binary search to detect bogus entries
- Fixes page numbers by setting them to adjacent valid values
- Keeps original page in `orig_page` field for display
- Computes `seq_in_level` for each item during validation

**Common TOC Issues:**

- Duplicate IDs causing wrong page mappings in source documents
- Non-linear page order due to footnotes, endnotes, or appendices
- Missing or empty title strings (cleaned up in `cleanUpTocTitle()`[frontend/apps/reader/modules/readertoc.lua97-103](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readertoc.lua#L97-L103) replacing empty with "–")
- Pages that decrease rather than increase in the TOC array

**Algorithm Details**: The fix algorithm [frontend/apps/reader/modules/readertoc.lua228-310](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readertoc.lua#L228-L310) determines whether to adjust previous or following items by comparing:

- `nb_prev` vs `nb_next`: Count of items needing adjustment in each direction
- `nb_prev_main` vs `nb_next_main`: Count of items in main (linear) flow only
- Prioritizes moving non-linear flow items over linear flow items
- Preserves original page in `orig_page` field when fixing

Sources: [frontend/apps/reader/modules/readertoc.lua166-322](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readertoc.lua#L166-L322)[frontend/apps/reader/modules/readertoc.lua97-103](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readertoc.lua#L97-L103)

### TOC Navigation Functions

**Key Navigation APIs:**
FunctionLine ReferencePurposeReturns`getTocIndexByPage(page, skip_ignored)`[389-412](https://github.com/koreader/koreader/blob/9e6b1587/389-412)Find TOC entry for pageIndex or nil`getAccurateTocIndexByXPointer(xptr, skip_ignored)`[414-450](https://github.com/koreader/koreader/blob/9e6b1587/414-450)Find TOC entry using XPointer comparison (EPUB)Index or nil`getTocTitleByPage(pn_or_xp)`[452-459](https://github.com/koreader/koreader/blob/9e6b1587/452-459)Get chapter title for pageTitle string`getFullTocTitleByPage(pn_or_xp)`[461-480](https://github.com/koreader/koreader/blob/9e6b1587/461-480)Get full chapter path (all levels)Array of titles`getChapterPagesLeft(pn_or_xp, pageno)`[727-755](https://github.com/koreader/koreader/blob/9e6b1587/727-755)Pages remaining in chapterNumber`isChapterStart(pn_or_xp, pageno)`[757-768](https://github.com/koreader/koreader/blob/9e6b1587/757-768)Check if page starts chapterBoolean`isChapterEnd(pn_or_xp, pageno)`[770-781](https://github.com/koreader/koreader/blob/9e6b1587/770-781)Check if page ends chapterBoolean`isChapterSecondPage(pn_or_xp, pageno)`[783-794](https://github.com/koreader/koreader/blob/9e6b1587/783-794)Check if page is second in chapterBoolean`getNextChapter(cur_pageno, direction)`[796-835](https://github.com/koreader/koreader/blob/9e6b1587/796-835)Get next/previous chapter pagePage number or nil`getPreviousChapter(cur_pageno)`[837-840](https://github.com/koreader/koreader/blob/9e6b1587/837-840)Get previous chapter pagePage number or nil
These functions support:

- Chapter-based page turning
- Footer display ("TC:" chapter time to read)
- Full-page refresh on chapter boundaries
- Bookmark chapter annotation

All functions in the table above are from [frontend/apps/reader/modules/readertoc.lua389-840](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readertoc.lua#L389-L840)

### TOC Menu Interface

The TOC browser displays the document structure in a hierarchical menu with expand/collapse functionality.

**TOC Display Features:**

- **Collapsible structure**: Configurable default depth (usually 2 levels)
- **Expanded node tracking**: Remembers user-expanded sections
- **Current location indicator**: Highlights current chapter in bold
- **Page/chapter info**: Shows page numbers or chapter lengths
- **Font size control**: Separate from other menus
- **Chapter progress**: Optional chapter progress percentage

```
Ignored levels

Collapsed

Select chapter

Toggle expand

onShowToc

fillToc

buildTocMenu

Apply filters?

Skip toc_ticks_ignored_levels

expandParentNodes

getTocMenuItems

Mark current chapter

Create Menu widget

UIManager:show

User interaction

Action?

Navigate to page

Update expanded_nodes

Rebuild menu
```

**Diagram: TOC Menu Display Flow**

The menu building process [frontend/apps/reader/modules/readertoc.lua267-450](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readertoc.lua#L267-L450):

1. Ensures TOC is loaded and validated
2. Applies collapsed/expanded state based on `collapsed_toc` and `expanded_nodes`
3. Marks current chapter based on current page position
4. Creates Menu widget with custom item formatting
5. Handles tap events to navigate or expand/collapse nodes

Sources: [frontend/apps/reader/modules/readertoc.lua842-950](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readertoc.lua#L842-L950)[frontend/apps/reader/modules/readertoc.lua72-81](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readertoc.lua#L72-L81)

## Integration Points

### TOC Ticks on Progress Bar

The TOC provides tick marks on the progress bar to indicate chapter boundaries [frontend/apps/reader/modules/readertoc.lua851-965](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readertoc.lua#L851-L965)

**Tick Generation:**

1. `buildTocTicks()` creates a hierarchical tick structure
2. Flattens to `ticks_flattened` array
3. Filters by `toc_ticks_ignored_levels` to create `ticks_flattened_filtered`
4. Progress bar widget renders ticks at appropriate positions

**Configurable Options:**

- Show/hide specific depth levels
- Bind chapter navigation to ticks
- Bind chapter title display to ticks

Sources: [frontend/apps/reader/modules/readertoc.lua509-606](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readertoc.lua#L509-L606)

### Chapter Information in Bookmarks

When creating a bookmark, `ReaderBookmark` queries `ReaderToc` to get the chapter title [frontend/apps/reader/modules/readerbookmark.lua385-396](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerbookmark.lua#L385-L396):

```
local chapter = self.ui.toc:getTocTitleByPage(pn_or_xp)
if chapter == "" then
    chapter = nil
else
    text = T(_("in %1"), chapter)
end

```

This chapter information:

- Appears in the bookmark text field
- Helps users identify bookmark locations
- Updates when TOC is regenerated

Sources: [frontend/apps/reader/modules/readerbookmark.lua404-418](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerbookmark.lua#L404-L418)

### Bookmark Flipping Mode

Bookmark flipping mode allows rapid navigation between bookmarks using swipe gestures [frontend/apps/reader/modules/readerpaging.lua226-299](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerpaging.lua#L226-L299)

**Activation:**

- Menu option: "Bookmark browsing mode"
- Available in paging mode only

**Behavior:**

- Swipe east: Previous bookmark
- Swipe west: Next bookmark
- Enters special flipping state with page/zoom mode preserved
- Shows flipping indicator in top-left corner

**Events:**

- `GotoPreviousBookmark`
- `GotoNextBookmark`

Sources: [frontend/apps/reader/modules/readerpaging.lua206-263](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerpaging.lua#L206-L263)[frontend/apps/reader/modules/readerbookmark.lua96-108](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerbookmark.lua#L96-L108)

### Footer Integration

The footer can display bookmark count using the `bookmark_count` mode [frontend/apps/reader/modules/readerfooter.lua220-228](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua#L220-L228):

```
bookmark_count = function(footer)
    local bookmark_count = footer.ui.annotation:getNumberOfAnnotations()
    if footer.settings.all_at_once and 
       footer.settings.hide_empty_generators and 
       bookmark_count == 0 then
        return ""
    end
    local prefix = symbol_prefix[symbol_type].bookmark_count
    return prefix .. " " .. tostring(bookmark_count)
end
```

The footer automatically updates when bookmarks are added/removed via the `maybeUpdateFooter()` call [frontend/apps/reader/modules/readerbookmark.lua385](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerbookmark.lua#L385-L385)

Sources: [frontend/apps/reader/modules/readerbookmark.lua375-386](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerbookmark.lua#L375-L386)

## Data Persistence

### Storage in Annotations Array

Both bookmarks and highlights are stored in the unified `ReaderAnnotation.annotations` array, maintained in position order.

**Sorting Algorithm:**
Annotations are kept in position order within the `ReaderAnnotation.annotations` array. When bookmarks are displayed, they use:

- EPUB/DJVU: XPointer or position comparison
- PDF: Position comparison based on `pos0` coordinates

**Position ordering rules** (from `isBookmarkInPageOrder()`[frontend/apps/reader/modules/readerbookmark.lua433-440](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerbookmark.lua#L433-L440)):

1. Compare page numbers first (earlier pages come first)
2. If same page: bookmarks (no `drawer`) come before highlights (have `drawer`)
3. For highlights on same page: use position comparison from start coordinates

**Key Function:**

- `isBookmarkInPageOrder(a, b)` - Comparison function for sorting bookmarks and highlights

Sources: [frontend/apps/reader/modules/readerbookmark.lua433-440](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerbookmark.lua#L433-L440)

### TOC Caching and Updates

The TOC is cached in `ReaderToc.toc` and persists across page changes. It is regenerated when:

- Document is rerendered (`onDocumentRerendered`)
- Alternative TOC is enabled/disabled (`onUpdateToc`)
- Page labels settings change (`onUsePageLabelsUpdated`)

**Computed Fields:**

- `chapter_length` - Computed by `completeTocWithChapterLengths()`[frontend/apps/reader/modules/readertoc.lua324-353](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readertoc.lua#L324-L353)
- `seq_in_level` - Computed during validation [frontend/apps/reader/modules/readertoc.lua216-220](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readertoc.lua#L216-L220)
- `ticks`, `ticks_flattened` - Computed for progress bar [frontend/apps/reader/modules/readertoc.lua851-900](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readertoc.lua#L851-L900)

These computed fields are recalculated as needed and not saved to persistent storage.

Sources: [frontend/apps/reader/modules/readertoc.lua109-132](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readertoc.lua#L109-L132)[frontend/apps/reader/modules/readertoc.lua324-387](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readertoc.lua#L324-L387)[frontend/apps/reader/modules/readertoc.lua85-95](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readertoc.lua#L85-L95)

### Settings Persistence

**ReaderBookmark Settings:**

```
-- Per-document (doc_settings)
annotations              -- unified bookmark/highlight array
 
-- Global (G_reader_settings)
bookmarks_items_per_page -- list display density
bookmarks_items_font_size -- list font size
bookmarks_items_max_lines -- flexible height setting
bookmarks_items_text_type -- what to show: text/all/note
bookmarks_items_sorting   -- page or date
bookmarks_items_reverse_sorting -- reverse order
annotations_export_on_closing -- auto-export on close
```

**ReaderToc Settings:**

```
-- Per-document (doc_settings)
toc_ticks_ignored_levels  -- hidden tick depths
toc_chapter_navigation_bind_to_ticks -- use ticks for nav
toc_chapter_title_bind_to_ticks -- use ticks for title
alternative_toc           -- use alternative TOC
 
-- Global (G_reader_settings)
toc_items_per_page        -- TOC menu density
toc_items_font_size       -- TOC menu font size
```

Sources: [frontend/apps/reader/modules/readerbookmark.lua45-59](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerbookmark.lua#L45-L59)[frontend/apps/reader/modules/readertoc.lua85-95](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readertoc.lua#L85-L95)

## Summary

The bookmark and TOC systems provide complementary navigation mechanisms:

- **Bookmarks** are user-created location markers stored as annotations without a `drawer` field
- **TOC** is document-provided structure, validated and enhanced with computed metadata
- Both integrate with progress indicators, footer display, and navigation history
- Data is persisted in the unified `annotations` array (bookmarks) and cached in memory (TOC)
- Menu interfaces use the generic `Menu` widget with custom formatting and interaction handlers

Key architectural patterns:

1. Unified storage with type discrimination (`drawer` field presence)
2. Position-based sorting with format-specific comparison functions
3. On-demand menu generation with caching where appropriate
4. Event-driven UI updates (`AnnotationsModified`, `TocReset`)
5. Integration via query APIs (`getTocTitleByPage`, `isPageBookmarked`)

---

# Reader-Footer-and-Status-Display

# Reader Footer and Status Display
Relevant source files
- [frontend/apps/reader/modules/readerfooter.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua)
- [spec/unit/autosuspend_spec.lua](https://github.com/koreader/koreader/blob/9e6b1587/spec/unit/autosuspend_spec.lua)
- [spec/unit/commonrequire.lua](https://github.com/koreader/koreader/blob/9e6b1587/spec/unit/commonrequire.lua)
- [spec/unit/filemanager_spec.lua](https://github.com/koreader/koreader/blob/9e6b1587/spec/unit/filemanager_spec.lua)
- [spec/unit/readerbookmark_spec.lua](https://github.com/koreader/koreader/blob/9e6b1587/spec/unit/readerbookmark_spec.lua)
- [spec/unit/readerdictionary_spec.lua](https://github.com/koreader/koreader/blob/9e6b1587/spec/unit/readerdictionary_spec.lua)
- [spec/unit/readerfooter_spec.lua](https://github.com/koreader/koreader/blob/9e6b1587/spec/unit/readerfooter_spec.lua)
- [spec/unit/readerhighlight_spec.lua](https://github.com/koreader/koreader/blob/9e6b1587/spec/unit/readerhighlight_spec.lua)
- [spec/unit/readerlink_spec.lua](https://github.com/koreader/koreader/blob/9e6b1587/spec/unit/readerlink_spec.lua)
- [spec/unit/readerpaging_spec.lua](https://github.com/koreader/koreader/blob/9e6b1587/spec/unit/readerpaging_spec.lua)
- [spec/unit/readerrolling_spec.lua](https://github.com/koreader/koreader/blob/9e6b1587/spec/unit/readerrolling_spec.lua)
- [spec/unit/readersearch_spec.lua](https://github.com/koreader/koreader/blob/9e6b1587/spec/unit/readersearch_spec.lua)
- [spec/unit/readertoc_spec.lua](https://github.com/koreader/koreader/blob/9e6b1587/spec/unit/readertoc_spec.lua)
- [spec/unit/readerui_spec.lua](https://github.com/koreader/koreader/blob/9e6b1587/spec/unit/readerui_spec.lua)
- [spec/unit/readerview_spec.lua](https://github.com/koreader/koreader/blob/9e6b1587/spec/unit/readerview_spec.lua)
- [spec/unit/screenshoter_spec.lua](https://github.com/koreader/koreader/blob/9e6b1587/spec/unit/screenshoter_spec.lua)

## Purpose and Scope

This document describes the Reader Footer system, which provides a customizable status bar displayed at the bottom of the reading screen. The footer shows reading progress information, including page numbers, percentage read, time estimates, battery status, and other configurable indicators. It also displays an optional progress bar with table of contents markers.

For information about the full reader UI structure, see [Reader UI and Module System](/koreader/koreader/4.2-reader-ui-and-module-system). For table of contents integration, see [Bookmarks and Table of Contents](/koreader/koreader/6.4-bookmarks-and-table-of-contents).

---

## Architecture Overview

The `ReaderFooter` module is implemented as a `WidgetContainer` that integrates with the reader view system. It is initialized as part of `ReaderView` and registers itself as a view module to participate in the rendering pipeline.

### Component Relationships

```
contains

creates in init()

creates in init()

getTimeForPages()

getTocTitleByPage()
getChapterPagesLeft()

getNumberOfAnnotations()

expandString()

scheduleIn()
unschedule()

getPowerDevice()

queries

readSetting('footer')
saveSetting()

per-book overrides

ReaderView
self.view

ReaderFooter
self.view.footer

ProgressWidget
self.progress_bar

TextWidget
self.footer_text

ReaderStatistics
self.ui.statistics

ReaderToc
self.ui.toc

ReaderAnnotation
self.ui.annotation

BookInfoManager
self.ui.bookinfo

UIManager

Device

PowerDevice
Device:getPowerDevice()

G_reader_settings

doc_settings
```

Sources: [frontend/apps/reader/modules/readerfooter.lua482-657](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua#L482-L657)[frontend/apps/reader/modules/readerview.lua20-30](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerview.lua#L20-L30)

### Footer Widget Hierarchy

The footer is composed of multiple widget layers that control layout and content positioning:

```
contains

contains

optional

contains

contains

left margin

content

content

right margin

contains

BottomContainer
self.footer_positioner
self[1]

FrameContainer
self.footer_content

VerticalGroup
self.vertical_frame

LineWidget
self.separator_line
(if bottom_horizontal_separator)

LeftContainer/CenterContainer/
RightContainer
self.footer_container
(depends on settings.align)

HorizontalGroup
self.horizontal_group

HorizontalSpan
margin_span

ProgressWidget
self.progress_bar

RightContainer
self.text_container

HorizontalSpan
margin_span

TextWidget
self.footer_text
```

Sources: [frontend/apps/reader/modules/readerfooter.lua775-847](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua#L775-L847)

---

## Display Modes

The footer supports multiple display modes, each showing different status information. Modes are defined in the `MODE` enumeration and can be combined when `all_at_once` mode is enabled.

### Mode Enumeration

The `MODE` table maps mode names to numeric identifiers:
Mode IDNameDescription0`off`Footer hidden1`page_progress`Current page / total pages2`pages_left_book`Pages remaining in book3`time`Current time4`pages_left`Pages left in chapter5`battery`Battery percentage6`percentage`Percentage read7`book_time_to_read`Estimated time to finish book8`chapter_time_to_read`Estimated time to finish chapter9`frontlight`Frontlight level10`mem_usage`Memory usage in MiB11`wifi_status`WiFi on/off status12`book_title`Book title13`book_chapter`Current chapter title14`bookmark_count`Number of annotations15`chapter_progress`Progress within chapter16`frontlight_warmth`Warmth level (natural light)17`custom_text`User-defined text18`book_author`Book author19`page_turning_inverted`Page turn direction indicator20`dynamic_filler`Spacing filler
Sources: [frontend/apps/reader/modules/readerfooter.lua33-55](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua#L33-L55)

### Symbol Prefixes

Each mode can display with different prefix styles controlled by the `item_prefix` setting:

```
selects

selects

selects

Display Mode
e.g., battery

letters prefix
B: 75%

icons prefix
🔋 75%

compact_items prefix
🔋

settings.item_prefix
user choice
```

The `symbol_prefix` table defines three style variants:

- `letters`: Text prefixes like "B:" for battery, "R:" for percentage read
- `icons`: Unicode icon symbols like "" for battery, "⌚" for time
- `compact_items`: Compact icons without additional text

Sources: [frontend/apps/reader/modules/readerfooter.lua57-136](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua#L57-L136)

---

## Text Generation System

Status text is generated by the `footerTextGeneratorMap`, a table of functions that produce formatted strings for each display mode.

### Generator Function Flow

```
self.ui.toc
self.ui.statistics
PowerDevice
footerTextGeneratorMap
ReaderFooter
self.ui.toc
self.ui.statistics
PowerDevice
footerTextGeneratorMap
ReaderFooter
alt
[mode == "battery"]
alt
[mode == "book_time_to_read"]
alt
[mode == "book_chapter"]
alt
[settings[mode] == true]
loop
[for each mode in mode_index]
alt
[settings.all_at_once]
[single mode display]
updateFooterText()
genAllFooterText()
footerTextGeneratorMap<FileRef file-url='https://github.com/koreader/koreader/blob/9e6b1587/mode' undefined file-path='mode'>Hii</FileRef>
getCapacity()
isCharging()
getBatterySymbol()
battery data
getTimeForPages(pages_left)
time estimate or nil
getTocTitleByPage(pageno)
chapter title
text string [, merge flag]
table.concat(texts, separator)
footerTextGeneratorMap<FileRef file-url='https://github.com/koreader/koreader/blob/9e6b1587/mode_name' undefined file-path='mode_name'>Hii</FileRef>
text string
self.footer_text:setText(text)
```

Sources: [frontend/apps/reader/modules/readerfooter.lua142-480](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua#L142-L480)[frontend/apps/reader/modules/readerfooter.lua1168-1236](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua#L1168-L1236)

### Key Generator Functions

**Frontlight Generator**

```
frontlight = function(footer)
    local symbol_type = footer.settings.item_prefix
    local prefix = symbol_prefix[symbol_type].frontlight
    local powerd = Device:getPowerDevice()
    if powerd:isFrontlightOn() then
        if Device:isCervantes() or Device:isKobo() then
            return (prefix .. " %d%%"):format(powerd:frontlightIntensity())
        else
            return (prefix .. " %d"):format(powerd:frontlightIntensity())
        end
    else
        -- May return empty string if hide_empty_generators enabled
        return T(_("%1 Off"), prefix)
    end
end
```

**Battery Generator**

The battery generator handles both single-battery and auxiliary battery devices, computing appropriate icons based on charge level and charging state. When `item_prefix` is `"icons"` or `"compact_items"`, it calls `powerd:getBatterySymbol()` to get a dynamic icon representing charge level. For dual-battery devices, it sums the percentages for display but averages them for icon selection.

**Time Estimate Generators**

Time-to-read estimates integrate with `ReaderStatistics`:

- `book_time_to_read`: Calls `self.ui.statistics:getTimeForPages(left)` where `left = self.ui.document:getTotalPagesLeft(self.pageno)`
- `chapter_time_to_read`: Calls `self.ui.statistics:getTimeForPages(left)` where `left = self.ui.toc:getChapterPagesLeft(self.pageno, true)` with fallback

Returns `_("N/A")` if `self.ui.statistics` is nil (statistics plugin disabled).

**Chapter Information**

The `book_chapter` generator calls `self.ui.toc:getTocTitleByPage(footer.pageno)` and truncates the result to fit within `settings.book_chapter_max_width_pct` of screen width using `getFittedText()`.

Sources: [frontend/apps/reader/modules/readerfooter.lua145-407](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua#L145-L407)

### Empty Generator Handling

When `hide_empty_generators` is enabled and `all_at_once` mode is active, generators can return empty strings to hide unavailable information (e.g., frontlight off, battery above threshold, WiFi off).

Sources: [frontend/apps/reader/modules/readerfooter.lua153-157](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua#L153-L157)[frontend/apps/reader/modules/readerfooter.lua519](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua#L519-L519)

---

## Progress Bar

The footer displays an optional progress bar using the `ProgressWidget` component, which can show reading position and table of contents markers.

### Progress Bar Configuration

```
Footer Settings

disable_progress_bar
bool

progress_bar_position
string

progress_style_thin
bool

toc_markers
bool

initial_marker
bool

chapter_progress_bar
bool

Positions:
alongside | above | below

Height:
thick (7px) | thin (3px)
```

Sources: [frontend/apps/reader/modules/readerfooter.lua475-477](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua#L475-L477)[frontend/apps/reader/modules/readerfooter.lua512-518](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua#L512-L518)

### Progress Bar Rendering

The progress bar width and position are calculated based on layout settings:

**Alongside Layout**: Progress bar appears to the left of footer text

```
[====Progress Bar====] [Status Text]

```

**Above/Below Layout**: Progress bar spans full width, text positioned separately

```
[==========Progress Bar==========]
         [Status Text]

```

The progress bar receives its parameters in `updateFooterTextGenerator()`:

- `width`: Calculated based on screen width, margins, and text width
- `height`: Determined by `progress_style_thin` setting
- `percentage`: Current reading position (0.0 to 1.0)
- `ticks`: Array of ToC chapter positions
- `initial_pos_marker`: Whether to show a marker at initial reading position

Sources: [frontend/apps/reader/modules/readerfooter.lua1168-1236](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua#L1168-L1236)[frontend/apps/reader/modules/readerfooter.lua616-628](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua#L616-L628)

### ToC Markers

When `toc_markers` is enabled, the footer displays vertical lines on the progress bar indicating chapter boundaries:

```
getTocTicksFlattened()

returns filtered ticks

sets ticks property

filters depth levels

ReaderFooter

ReaderToc

ProgressWidget

toc_ticks_ignored_levels
per-book setting
```

The `getTocTicksFlattened()` method returns an array of page positions, optionally filtering out specified depth levels based on the `toc_ticks_ignored_levels` setting.

Sources: [frontend/apps/reader/modules/readerfooter.lua1295-1299](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua#L1295-L1299)[frontend/apps/reader/modules/readertoc.lua86-89](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readertoc.lua#L86-L89)

---

## Layout and Positioning

The footer's layout system controls positioning, alignment, and dimensions of status elements.

### Container Structure

The footer creates a hierarchical container structure in `updateFooterContainer()`:

```
dimen = screen size

if bottom_horizontal_separator

dimen.h = footer height

margin_span

margin_span

self[1] = BottomContainer

VerticalGroup

LineWidget
(optional separator)

Alignment Container
Left/Center/Right

HorizontalGroup

HorizontalSpan

ProgressWidget or
TextContainer

HorizontalSpan
```

Sources: [frontend/apps/reader/modules/readerfooter.lua737-791](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua#L737-L791)

### Alignment Modes

The `align` setting controls horizontal positioning:
SettingContainer TypeEffect`"left"``LeftContainer`Footer content aligned to left edge`"right"``RightContainer`Footer content aligned to right edge`"center"``CenterContainer`Footer content centered
Sources: [frontend/apps/reader/modules/readerfooter.lua766-777](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua#L766-L777)

### Dimensions

Footer dimensions are configurable:

- **Height**: `container_height` setting (default from `DMINIBAR_CONTAINER_HEIGHT`), scaled by `Screen:scaleBySize()`
- **Bottom Padding**: `container_bottom_padding` setting, scaled
- **Horizontal Margin**: `horizontal_margin` property, based on `Size.span.horizontal_default`

The `reclaim_height` setting determines whether the footer overlaps the document view when hidden/shown.

Sources: [frontend/apps/reader/modules/readerfooter.lua464-467](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua#L464-L467)[frontend/apps/reader/modules/readerfooter.lua585-586](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua#L585-L586)[frontend/apps/reader/modules/readerfooter.lua479](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua#L479-L479)

---

## Configuration and Settings

The footer system provides extensive configuration through the `settings` table, which merges per-book and global settings.

### Settings Initialization

```
doc_settings
default_settings
G_reader_settings
ReaderFooter:init()
doc_settings
default_settings
G_reader_settings
ReaderFooter:init()
Per-book overrides applied
on document open via
onReadSettings()
readSetting("footer")
global footer settings
merge with default_settings
self.settings table
```

The default settings are defined in `ReaderFooter.default_settings` (lines 474-526). Key defaults include:

- `disable_progress_bar = false` (progress bar enabled)
- `all_at_once = false` (single mode display)
- `page_progress = true`, `time = true`, `pages_left = true` (enabled modes)
- `item_prefix = "icons"` (icon-style prefixes)
- `text_font_size = 14`, `text_font_bold = false` (typography)

Sources: [frontend/apps/reader/modules/readerfooter.lua474-526](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua#L474-L526)[frontend/apps/reader/modules/readerfooter.lua529](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua#L529-L529)

### Mode Ordering and Cycling

The footer maintains a `mode_index` array that defines the order of modes for cycling through single-mode displays:

```
if exists

if no order saved

cycles to next

updates

settings.order
saved mode order

mode_index array
runtime order

mode_list table
name to index lookup

MODE enumeration

User taps footer

self.mode
current mode index
```

When the user taps the footer (with `lock_tap` disabled), `onTapFooter()` advances `self.mode` to the next index in `mode_index`, cycling through available display modes.

Sources: [frontend/apps/reader/modules/readerfooter.lua547-582](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua#L547-L582)[frontend/apps/reader/modules/readerfooter.lua1048-1084](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua#L1048-L1084)

### Font Configuration

Text appearance is controlled by:

- `text_font_face`: Fixed at `"ffont"` (system font)
- `text_font_size`: Configurable size (default 14)
- `text_font_bold`: Boolean for bold text

The `footer_text_face` is created in `init()` using `Font:getFace()` and applied to the `TextWidget` instance.

Sources: [frontend/apps/reader/modules/readerfooter.lua463](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua#L463-L463)[frontend/apps/reader/modules/readerfooter.lua607-612](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua#L607-L612)

---

## Integration with Statistics

The footer integrates with `ReaderStatistics` to provide time-to-read estimates based on reading speed tracking.

### Time Estimate Flow

```
Statistics Database
ReaderStatistics
footerTextGeneratorMap
ReaderFooter
Statistics Database
ReaderStatistics
footerTextGeneratorMap
ReaderFooter
If stats disabled,
returns "N/A"
book_time_to_read()
get pages left
getTimeForPages(pages_left)
query reading speed
average page read time
calculate estimate
"5m" or "1h 23m"
formatted text with prefix
```

The `book_time_to_read` generator (lines 319-325) and `chapter_time_to_read` generator (lines 326-332) call `self.ui.statistics:getTimeForPages(left)` where `left` is the number of pages remaining.

If the statistics plugin is not available (`self.ui.statistics` is nil), the generators return `_("N/A")`.

Sources: [frontend/apps/reader/modules/readerfooter.lua319-332](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua#L319-L332)

### Pages Left Calculation

For chapter-based estimates, the footer uses `ReaderToc`:

```
local left = footer.ui.toc:getChapterPagesLeft(footer.pageno) 
          or footer.ui.document:getTotalPagesLeft(footer.pageno)
```

This calls `getChapterPagesLeft()` which computes pages remaining in the current chapter based on ToC structure. If unavailable, it falls back to total pages left in the document.

Sources: [frontend/apps/reader/modules/readerfooter.lua294](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua#L294-L294)[frontend/apps/reader/modules/readertoc.lua846-865](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readertoc.lua#L846-L865)

---

## Update and Refresh Mechanism

The footer updates its display in response to various events and implements optimizations to minimize unnecessary redraws.

### Update Trigger Events

```
Event Sources

ReaderFooter:onPageUpdate(pageno)

ReaderFooter:onPosUpdate(pos, pageno)

ReaderFooter:onBatteryUpdate()

ReaderFooter:onFrontlightStateChanged()

autoRefreshFooter callback

ReaderFooter:onNetworkConnected()

ReaderFooter:onNetworkDisconnected()

updateFooter()
updateFooterText()
updateFooterPage()
updateFooterPos()

UIManager:setDirty(self.view.dialog, 'ui')
```

The main update methods are:

- `updateFooter()`: Full update including page numbers and progress bar
- `updateFooterText()`: Text-only update (preserves existing dimensions)
- `updateFooterPage(pageno)`: Page-specific update for paging documents
- `updateFooterPos(pos, pageno)`: Position-specific update for rolling documents

Each calls `updateFooterTextGenerator()` to regenerate text and progress bar state, then calls `UIManager:setDirty()` to queue a repaint.

Sources: [frontend/apps/reader/modules/readerfooter.lua1238-1293](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua#L1238-L1293)

### Visibility and Auto-refresh

The footer visibility is controlled by:

- `self.view.footer_visible`: Boolean flag set/checked by various methods
- `self.mode == self.mode_list.off`: Footer in "off" mode
- `self.has_no_mode and self.settings.disable_progress_bar`: No enabled modes and no progress bar

**Auto-refresh Scheduling**

When `settings.auto_refresh_time` is enabled and time-based modes are active, the footer schedules periodic updates:

```
function ReaderFooter:rescheduleFooterAutoRefreshIfNeeded()
    local schedule = false
    if self.settings.auto_refresh_time then
        if self.settings.all_at_once then
            if self.settings.time or self.settings.battery 
               or self.settings.wifi_status or self.settings.mem_usage then
                schedule = true
            end
        else
            if self.mode == self.mode_list.time 
               or self.mode == self.mode_list.battery
               or self.mode == self.mode_list.wifi_status 
               or self.mode == self.mode_list.mem_usage then
                schedule = true
            end
        end
    end
    if schedule then
        UIManager:scheduleIn(61 - tonumber(os.date("%S")), self.autoRefreshFooter)
    end
end
```

The `autoRefreshFooter` callback is created lazily on first schedule. It calls `onUpdateFooter()` with visibility checks via `shouldBeRepainted()` to avoid painting over fullscreen widgets.

Sources: [frontend/apps/reader/modules/readerfooter.lua624-647](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua#L624-L647)[frontend/apps/reader/modules/readerfooter.lua876-908](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua#L876-L908)

---

## Presets System

The footer supports saving and loading configuration presets through the `Presets` framework.

### Preset Structure

```
registered as

preset_obj table

presets array
stored in G_reader_settings

buildPreset()
creates preset from
current settings

loadPreset(preset)
applies preset settings

Dispatcher
load_footer_preset action
```

The preset object is initialized in `init()`:

```
self.preset_obj = {
    presets = G_reader_settings:readSetting("footer_presets", {}),
    dispatcher_name = "load_footer_preset",
    buildPreset = function() return self:buildPreset() end,
    loadPreset = function(preset) self:loadPreset(preset) end,
}
```

This integrates with the `Presets` module to allow users to save footer configurations and switch between them via the dispatcher system.

Sources: [frontend/apps/reader/modules/readerfooter.lua658-663](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua#L658-L663)[frontend/apps/reader/modules/readerfooter.lua1426-1454](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua#L1426-L1454)

### Building a Preset

The `buildPreset()` method creates a preset table containing all footer configuration:

```
function ReaderFooter:buildPreset()
    local preset = {}
    -- Copy all mode enable flags
    for i = 0, #self.mode_index do
        preset[self.mode_index[i]] = self.settings[self.mode_index[i]]
    end
    -- Copy layout and appearance settings
    preset.all_at_once = self.settings.all_at_once
    preset.disable_progress_bar = self.settings.disable_progress_bar
    preset.toc_markers = self.settings.toc_markers
    preset.progress_bar_position = self.settings.progress_bar_position
    preset.progress_style_thin = self.settings.progress_style_thin
    preset.bottom_horizontal_separator = self.settings.bottom_horizontal_separator
    preset.align = self.settings.align
    preset.item_prefix = self.settings.item_prefix
    -- ... additional settings
    return preset
end
```

The generated preset can be stored in `G_reader_settings:readSetting("footer_presets")` and loaded via dispatcher action `"load_footer_preset"`.

Sources: [frontend/apps/reader/modules/readerfooter.lua1426-1443](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua#L1426-L1443)

---

## Hold Gesture and Skim Mode

The footer supports a hold gesture that can either display a menu or activate skim mode for quick navigation.

### Hold Menu vs. Skim Widget

```
false (default)

true

ReaderFooter:onHoldFooter()

settings.skim_widget_on_hold

UIManager:show(footer_hold_menu)

UIManager:show(SkimToWidget)

MenuItems:
- 'Visibility'
- 'Mode at this position'
- footer_sub_menu_table

User drags slider
calls onGotoPage()
```

When `settings.skim_widget_on_hold` is disabled (default), `onHoldFooter()` creates and shows `footer_hold_menu`:

```
self.footer_hold_menu = Menu:new{
    title = _("Status bar"),
    item_table = {
        { text = _("Visibility"), ... },
        { text = _("Mode at this position"), ... },
        footer_sub_menu_table,
    }
}
```

When enabled, it creates a `SkimToWidget` that provides a slider-based navigation interface:

```
UIManager:show(SkimToWidget:new{
    document = self.ui.document,
    ui = self.ui,
    callback_switch_to_goto = function() ... end,
})
```

Sources: [frontend/apps/reader/modules/readerfooter.lua1086-1136](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua#L1086-L1136)[frontend/apps/reader/modules/readerfooter.lua535](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua#L535-L535)

---

## Additional Features

### Bookmark Count Display

The `bookmark_count` mode displays the total number of annotations (highlights + notes + bookmarks):

```
bookmark_count = function(footer)
    local bookmark_count = footer.ui.annotation:getNumberOfAnnotations()
    if footer.settings.all_at_once and footer.settings.hide_empty_generators 
       and bookmark_count == 0 then
        return ""
    end
    local symbol_type = footer.settings.item_prefix
    local prefix = symbol_prefix[symbol_type].bookmark_count
    return prefix .. " " .. tostring(bookmark_count)
end
```

This calls `self.ui.annotation:getNumberOfAnnotations()` which returns the total count from `self.annotations` table. The count updates when annotations are added/removed, triggering footer refresh via `onUpdateFooter()`.

Sources: [frontend/apps/reader/modules/readerfooter.lua223-231](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua#L223-L231)

### Custom Text with Variable Expansion

The `custom_text` mode allows user-defined text with variable expansion through `BookInfoManager:expandString()`:

```
custom_text = function(footer)
    -- if custom_text contains only spaces, merge with adjacent items
    local merge = footer.custom_text:gsub(" ", "") == ""
    return footer.ui.bookinfo:expandString(footer.custom_text)
           :rep(footer.custom_text_repetitions), merge
end
```

Users can set custom text via `set_custom_text()` which shows a `MultiInputDialog`. The text can include variables like `%{title}`, `%{author}`, `%{page}`, `%{series}` which are expanded by `BookInfoManager:expandString()`. The `custom_text_repetitions` setting allows repeating the pattern multiple times.

Custom text is stored in:

- `G_reader_settings:readSetting("reader_footer_custom_text")` (default: "KOReader")
- `G_reader_settings:readSetting("reader_footer_custom_text_repetitions")` (default: 1)

Sources: [frontend/apps/reader/modules/readerfooter.lua408-413](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua#L408-L413)[frontend/apps/reader/modules/readerfooter.lua643-646](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua#L643-L646)[frontend/apps/reader/modules/readerfooter.lua704-764](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua#L704-L764)

### Dynamic Filler

The `dynamic_filler` mode calculates spacing to fill remaining horizontal space:

```
dynamic_filler = function(footer)
    local margin = footer.horizontal_margin
    local max_width = math.floor(footer._saved_screen_width - 2 * margin)
    local text_width = -- calculated from other items
    local filler_space = " "
    local filler_nb = math.floor((max_width - text_width + separator_width) 
                                 / footer.filler_space_width)
    if filler_nb > 0 then
        return filler_space:rep(filler_nb), true
    end
end
```

This ensures proper spacing when `all_at_once` mode displays multiple items.

Sources: [frontend/apps/reader/modules/readerfooter.lua411-455](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua#L411-L455)

### Page Labels Integration

For documents with page labels (reference page numbers), the `page_progress` generator checks if `ui.pagemap:wantsPageLabels()` returns true and displays reference page numbers instead of sequential page numbers:

```
if footer.ui.pagemap and footer.ui.pagemap:wantsPageLabels() then
    return ("%s / %s"):format(
        footer.ui.pagemap:getCurrentPageLabel(true),
        footer.ui.pagemap:getLastPageLabel(true))
end
```

Sources: [frontend/apps/reader/modules/readerfooter.lua240-243](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerfooter.lua#L240-L243)[frontend/apps/reader/modules/readerpagemap.lua272-274](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerpagemap.lua#L272-L274)

---

# User-Interface-Components

# User Interface Components
Relevant source files
- [frontend/apps/filemanager/filemanagermenu.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagermenu.lua)
- [frontend/apps/filemanager/filemanagersetdefaults.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagersetdefaults.lua)
- [frontend/apps/reader/modules/readerconfig.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerconfig.lua)
- [frontend/apps/reader/modules/readerdictionary.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerdictionary.lua)
- [frontend/apps/reader/modules/readermenu.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readermenu.lua)
- [frontend/apps/reader/modules/readerstyletweak.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerstyletweak.lua)
- [frontend/apps/reader/modules/readerwikipedia.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerwikipedia.lua)
- [frontend/device/android/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua)
- [frontend/device/cervantes/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/cervantes/device.lua)
- [frontend/device/generic/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua)
- [frontend/device/input.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/input.lua)
- [frontend/device/kindle/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kindle/device.lua)
- [frontend/device/kobo/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua)
- [frontend/device/pocketbook/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/pocketbook/device.lua)
- [frontend/device/sdl/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/sdl/device.lua)
- [frontend/device/sony-prstux/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/sony-prstux/device.lua)
- [frontend/document/tilecacheitem.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/tilecacheitem.lua)
- [frontend/ui/data/css_tweaks.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/data/css_tweaks.lua)
- [frontend/ui/data/settings_migration.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/data/settings_migration.lua)
- [frontend/ui/elements/screen_dpi_menu_table.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/elements/screen_dpi_menu_table.lua)
- [frontend/ui/elements/screensaver_menu.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/elements/screensaver_menu.lua)
- [frontend/ui/language.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/language.lua)
- [frontend/ui/renderimage.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/renderimage.lua)
- [frontend/ui/screensaver.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/screensaver.lua)
- [frontend/ui/translator.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/translator.lua)
- [frontend/ui/uimanager.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua)
- [frontend/ui/widget/dictquicklookup.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/dictquicklookup.lua)
- [frontend/ui/widget/footnotewidget.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/footnotewidget.lua)
- [frontend/ui/widget/htmlboxwidget.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/htmlboxwidget.lua)
- [frontend/ui/widget/imageviewer.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/imageviewer.lua)
- [frontend/ui/widget/imagewidget.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/imagewidget.lua)
- [frontend/ui/widget/inputdialog.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/inputdialog.lua)
- [frontend/ui/widget/inputtext.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/inputtext.lua)
- [frontend/ui/widget/menu.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/menu.lua)
- [frontend/ui/widget/multiinputdialog.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/multiinputdialog.lua)
- [frontend/ui/widget/screensaverlockwidget.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/screensaverlockwidget.lua)
- [frontend/ui/widget/screensaverwidget.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/screensaverwidget.lua)
- [frontend/ui/widget/screenshoter.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/screenshoter.lua)
- [frontend/ui/widget/scrollhtmlwidget.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/scrollhtmlwidget.lua)
- [frontend/ui/widget/scrolltextwidget.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/scrolltextwidget.lua)
- [frontend/ui/widget/textboxwidget.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/textboxwidget.lua)
- [frontend/ui/widget/textviewer.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/textviewer.lua)
- [frontend/ui/widget/touchmenu.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/touchmenu.lua)
- [frontend/ui/widget/virtualkeyboard.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua)
- [frontend/ui/wikipedia.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/wikipedia.lua)
- [frontend/util.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua)
- [platform/android/llapp_main.lua](https://github.com/koreader/koreader/blob/9e6b1587/platform/android/llapp_main.lua)
- [resources/icons/mdlight/triangle.svg](https://github.com/koreader/koreader/blob/9e6b1587/resources/icons/mdlight/triangle.svg)
- [spec/unit/device_spec.lua](https://github.com/koreader/koreader/blob/9e6b1587/spec/unit/device_spec.lua)
- [spec/unit/util_spec.lua](https://github.com/koreader/koreader/blob/9e6b1587/spec/unit/util_spec.lua)

## Purpose and Scope

This document covers KOReader's reusable UI widget system and display components. Widgets are self-contained visual elements that handle rendering, user input, and state management. The widget system provides the building blocks for both the FileManager and ReaderUI applications.

For information about how UIManager orchestrates widget lifecycle and event dispatch, see [UI Manager and Event Loop](/koreader/koreader/3.2-ui-manager-and-event-loop). For application-level UI structures like FileManager and ReaderUI menus, see [Application Layer](/koreader/koreader/4-application-layer).

## Widget System Overview

KOReader's UI is built on a hierarchical widget system where all visual elements inherit from a common base class. Widgets are responsible for:

- **Rendering**: Implementing `paintTo(bb, x, y)` to draw themselves onto a blitbuffer
- **Layout**: Computing their dimensions via `getSize()`
- **Event handling**: Processing input events through the `handleEvent(event)` interface
- **Lifecycle management**: Responding to `Show`, `CloseWidget`, and `FlushSettings` events

The system uses a tree structure where container widgets manage child widgets, propagating events and coordinating layout. All widgets ultimately render to a framebuffer managed by UIManager.

Sources: [frontend/ui/uimanager.lua1-1600](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L1-L1600)[frontend/ui/widget/menu.lua1-100](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/menu.lua#L1-L100)

## Widget Class Hierarchy

```
Complex Widgets

Display Widgets

Interactive Widgets

Container Widgets

Base Classes

Widget
paintTo(), getSize()

WidgetContainer
Contains child widgets

InputContainer
Gesture handling

FocusManager
Focus navigation

FrameContainer
Border and padding

CenterContainer
Centers content

LeftContainer

RightContainer

BottomContainer

VerticalGroup
Stacks vertically

HorizontalGroup
Stacks horizontally

OverlapGroup
Layers widgets

UnderlineContainer

MovableContainer
Drag and drop

Button

IconButton

CheckButton

MenuItem

TouchMenuItem

TextWidget
Single line text

TextBoxWidget
Multi-line text

ScrollTextWidget
Scrollable text

ScrollHtmlWidget
HTML rendering

ImageWidget
Image display

LineWidget

Menu
Paginated list

TouchMenu
Tabbed menu

VirtualKeyboard

InputText

InputDialog

DictQuickLookup
Dictionary popup
```

**Key Characteristics:**

- **Widget**: Base class with minimal interface - just `paintTo()` and `getSize()`
- **WidgetContainer**: Adds array-like child widget storage (`self[1]`, `self[2]`, etc.)
- **InputContainer**: Adds gesture event registration via `ges_events` and `key_events` tables
- **FocusManager**: Adds keyboard/d-pad navigation between focusable child widgets

Sources: [frontend/ui/widget/container/widgetcontainer.lua1-50](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/container/widgetcontainer.lua#L1-L50)[frontend/ui/widget/container/inputcontainer.lua1-100](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/container/inputcontainer.lua#L1-L100)[frontend/ui/widget/focusmanager.lua1-100](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/focusmanager.lua#L1-L100)

## Widget Lifecycle

```
Screen Device
Widget
UIManager
Application
Screen Device
Widget
UIManager
Application
Widget Creation
Registration
Rendering
Event Handling
alt
[Event consumed]
[Event not handled]
Cleanup
new{params}
init()
show(widget, refreshtype)
Insert into _window_stack
handleEvent(Show)
setDirty(widget)
_repaint() on next tick
paintTo(bb, x, y)
Render self and children
Screen refresh ioctl
handleEvent(input_event)
Process event
return true
return false/nil
close(widget)
handleEvent(FlushSettings)
handleEvent(CloseWidget)
Remove from _window_stack
setDirty(uncovered widgets)
```

**Event Propagation Rules:**

- Events propagate to children first, in array order (`self[1]`, `self[2]`, ...)
- If any child returns `true`, propagation stops immediately for that widget tree
- Events bubble from innermost widget outward through the window stack
- Window-level widgets (those passed to `UIManager:show()`) are stored in `_window_stack`

Sources: [frontend/ui/uimanager.lua156-198](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L156-L198)[frontend/ui/uimanager.lua215-282](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L215-L282)

## Container Widgets

Container widgets manage child widget layout and provide common positioning patterns:
ContainerPurposeKey Properties`FrameContainer`Border, background, padding`bordersize`, `background`, `padding`, `radius``CenterContainer`Centers child widget`dimen` - dimensions to center within`LeftContainer`Aligns child to left`dimen``RightContainer`Aligns child to right`dimen``BottomContainer`Aligns child to bottom`dimen``VerticalGroup`Stacks children vertically`align` - "left", "center", "right"`HorizontalGroup`Stacks children horizontally`align` - "top", "center", "bottom"`OverlapGroup`Layers children (z-index)Children drawn in array order`UnderlineContainer`Adds line below child`linesize`, `color`, `vertical_align``MovableContainer`Draggable container`alpha` for transparency
**Common Layout Pattern:**

```
FrameContainer
  └─ VerticalGroup
      ├─ HorizontalGroup (row 1)
      │   ├─ LeftContainer
      │   │   └─ TextWidget
      │   └─ RightContainer
      │       └─ TextWidget
      └─ HorizontalGroup (row 2)
          └─ CenterContainer
              └─ Button

```

Sources: [frontend/ui/widget/container/framecontainer.lua1-50](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/container/framecontainer.lua#L1-L50)[frontend/ui/widget/container/centercontainer.lua1-50](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/container/centercontainer.lua#L1-L50)[frontend/ui/widget/verticalgroup.lua1-50](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/verticalgroup.lua#L1-L50)[frontend/ui/widget/horizontalgroup.lua1-50](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/horizontalgroup.lua#L1-L50)

## Menu Widgets

### Menu vs TouchMenu

KOReader provides two menu widget types for different interaction models:

**Menu** - Paginated list menu:

- Displays items in a scrollable list with page navigation
- Used in: file browser, history, search results, collection browser
- Supports keyboard shortcuts (Q, W, E, R, ...) when `is_enable_shortcut` is true
- Items can be multi-line with font size adjustment
- Implements `MenuItem` for each list item

**TouchMenu** - Tabbed menu:

- Organizes items into tabs with icon buttons
- Used in: FileManagerMenu, ReaderMenu for settings and actions
- Top bar shows tab icons, content area shows current tab's items
- Supports sub-menus (nested item tables)
- Implements `TouchMenuItem` for each item

Sources: [frontend/ui/widget/menu.lua584-650](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/menu.lua#L584-L650)[frontend/ui/widget/touchmenu.lua1-100](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/touchmenu.lua#L1-L100)

### Menu Architecture

```
Menu Item Structure

TouchMenu Widget (Tabbed)

Menu Widget (Paginated)

Menu
items_per_page
font_size

TitleBar
with search

VerticalGroup

MenuItem

MenuItem

page_info_text

page_return_arrow

TouchMenu
tab_item_table

TouchMenuBar
Icon buttons

VerticalGroup

TouchMenuItem

TouchMenuItem

item_table entry

text or text_func

callback function

sub_item_table

checked_func

enabled_func

separator flag

keep_menu_open

hold_callback
```

**MenuItem Properties:**
PropertyTypePurpose`text` / `text_func`string / functionDisplay text (function allows dynamic text)`callback`functionAction to execute on tap`sub_item_table`tableChild menu items (creates submenu)`checked_func`functionReturns true to show checkmark`enabled_func`functionReturns true if item is enabled`separator`booleanDraw separator line after item`keep_menu_open`booleanDon't close menu after callback`hold_callback`functionAction on hold gesture`help_text`stringShown on hold if no hold_callback
Sources: [frontend/ui/widget/menu.lua88-462](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/menu.lua#L88-L462)[frontend/ui/widget/touchmenu.lua46-160](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/touchmenu.lua#L46-L160)

### MenuItem Rendering

```
MenuItem Layout

FrameContainer
width=dimen.w

UnderlineContainer
Line below

HorizontalGroup

CenterContainer
Checkmark/RadioMark

TextWidget or
TextBoxWidget

RightContainer
Mandatory text
```

**Text Rendering Modes:**

- **Single-line mode** (`single_line=true`): Uses `TextWidget`, truncates with ellipsis if too long
- **Multi-line mode** (`single_line=false`): Uses `TextBoxWidget`, wraps text across lines
- **Font size adjustment** (`multilines_show_more_text=true`): Binary search for largest font that fits

The menu system supports:

- **Mandatory text**: Right-aligned secondary text (file size, page count, etc.)
- **Dot leaders**: Line of dots between text and mandatory (set `with_dots=true`)
- **Baseline alignment**: Aligns text and mandatory baselines when `align_baselines=true`
- **State indicators**: Buttons for expandable tree items (used in TOC)

Sources: [frontend/ui/widget/menu.lua102-462](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/menu.lua#L102-L462)[frontend/ui/widget/menu.lua220-312](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/menu.lua#L220-L312)

## Virtual Keyboard

The `VirtualKeyboard` widget provides on-screen text input for touch devices. It supports multiple layouts, languages, and input modes.

```
Keyboard Layouts

VirtualKey Widget

VirtualKeyboard Structure

references

VirtualKeyboard
main widget

BottomContainer
Anchored to bottom

FrameContainer
Background

VerticalGroup

HorizontalGroups
One per row

VirtualKey widgets

Reference to InputText

VirtualKey

FrameContainer

TextWidget or
ImageWidget (icon)

Tap / Hold / Swipe

VirtualKeyboard
layout management

keyboard_layouts table

keys array
rows of key definitions

width array
key width multipliers

Shift / Sym / Layers
```

**Key Features:**

- **Multi-layer support**: Normal, Shift, Symbol, and language-specific layers
- **Hold-for-alternatives**: Long-press shows popup with character variants (e.g., hold "a" for "á, à, â, ä, ã")
- **Swipe gestures**: Swipe up/down on keys for additional actions
- **Language switching**: Button to switch between keyboard layouts
- **Layout persistence**: Remembers last used layout per language

Sources: [frontend/ui/widget/virtualkeyboard.lua1-100](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua#L1-L100)[frontend/ui/widget/virtualkeyboard.lua300-500](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua#L300-L500)

### Keyboard Layout Structure

Layouts are defined in [frontend/ui/widget/virtualkeyboard.lua100-300](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua#L100-L300) with this structure:

```
{
    keys = {
        -- Row 1
        { "q", "w", "e", "r", ... },
        -- Row 2  
        { "a", "s", "d", "f", ... },
        -- Row 3
        { "z", "x", "c", "v", ... },
    },
    width = { 1, 1, 1, 1, 1.5, ... }, -- Width multipliers
    shift_keys = { ... }, -- Shifted key values
    alt_keys = { ... },   -- Alternative characters on hold
    symbol_keys = { ... }, -- Symbol layer
}
```

**Special Key Types:**

- **Label-only keys**: `{label = "Shift"}` - triggers layout change
- **Icon keys**: `{icon = "path/to/icon.png"}` - displays image
- **Width modifiers**: `width` array controls key sizes (1.0 = standard, 1.5 = wider)
- **Callback keys**: `{callback = function() ... end}` - custom action

The keyboard interacts bidirectionally with `InputText`:

- `VirtualKeyboard` stores reference to `input_box` (the `InputText` widget)
- `InputText` stores reference to `keyboard`
- Key presses call `input_box:addChars(char)` to insert text
- InputText notifies keyboard of cursor position for suggestions

Sources: [frontend/ui/widget/virtualkeyboard.lua58-150](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua#L58-L150)[frontend/ui/widget/inputtext.lua1-100](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/inputtext.lua#L1-L100)

## Input Widgets

### InputText

`InputText` is a text entry field widget that displays editable text with cursor positioning and selection support.

**Key Properties:**
PropertyTypePurpose`text`stringCurrent text content`hint`stringPlaceholder text when empty`input_type`string"number" for numeric keyboard`text_type`string"password" to mask text`focused`booleanWhether widget has focus`scroll`booleanEnable scrolling for long text`cursor_at_end`booleanInitial cursor position`edit_callback`functionCalled on text change`scroll_callback`functionCalled on scroll`scroll_by_pan`booleanEnable touch scrolling
**Internal Structure:**

```
Display Mode: Scrollable

Display Mode: Not Scrollable

InputText
Main widget

FrameContainer
Border and focus

TextBoxWidget
Renders text with cursor

ScrollTextWidget
Scrollable container

TextBoxWidget
Inside scrollable area
```

**Cursor and Selection:**

- Cursor position tracked in `charpos` (character index)
- Selection tracked with `charpos` and `sel_end`
- Cursor rendering handled by `TextBoxWidget` with `cursor_line` parameter
- Selection shown with highlighted background via `TextBoxWidget._bb` inversion

**Text Entry Flow:**

1. User taps `InputText` → triggers `onShowKeyboard()`
2. `InputText` instantiates `VirtualKeyboard` if not exists
3. Keyboard appears via `UIManager:show(self.keyboard)`
4. User types on keyboard → keyboard calls `self.input_box:addChars(char)`
5. `InputText:addChars()` updates text and calls `edit_callback`
6. Widget redraws with new text

Sources: [frontend/ui/widget/inputtext.lua24-300](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/inputtext.lua#L24-L300)[frontend/ui/widget/inputtext.lua400-600](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/inputtext.lua#L400-L600)

### InputDialog

`InputDialog` wraps `InputText` in a full dialog with buttons and additional UI elements:

```
Button Configuration

Dialog Structure

InputDialog
Modal dialog

TitleBar
with title text

TextBoxWidget
Optional description

VerticalGroup

InputText

CheckButton
Show/hide password

Button
Reset to default

ButtonTable
OK/Cancel buttons

OK button
callback(text)

Cancel button
close dialog

Additional buttons
```

**Usage Pattern:**

```
local input_dialog = InputDialog:new{
    title = "Enter text",
    input = "default value",
    input_type = "text",
    buttons = {
        {
            {
                text = "Cancel",
                callback = function()
                    UIManager:close(input_dialog)
                end,
            },
            {
                text = "OK",
                is_enter_default = true,
                callback = function()
                    local text = input_dialog:getInputText()
                    -- Process text...
                    UIManager:close(input_dialog)
                end,
            },
        },
    },
}
UIManager:show(input_dialog)
input_dialog:onShowKeyboard()
```

**Special Features:**

- **Multi-input dialogs**: `MultiInputDialog` extends this to support multiple input fields
- **Validation**: `is_enter_default` button is disabled if input is invalid
- **Input hints**: Shows placeholder text when field is empty
- **Keyboard management**: Automatically shows/hides keyboard
- **Password toggle**: `show_password_toggle` adds button to reveal masked text

Sources: [frontend/ui/widget/inputdialog.lua1-100](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/inputdialog.lua#L1-L100)[frontend/ui/widget/inputdialog.lua150-400](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/inputdialog.lua#L150-L400)

## Text Display Widgets

### TextWidget vs TextBoxWidget

KOReader uses different text rendering widgets depending on layout requirements:
WidgetUse CaseLayoutWrapping`TextWidget`Single lineFixed heightTruncates with ellipsis`TextBoxWidget`Multi-lineVariable heightWraps at word boundaries`ScrollTextWidget`Long textScrollableWraps, allows scrolling`ScrollHtmlWidget`HTML contentScrollableRenders HTML with MuPDF
**TextWidget:**

- Renders a single line of text with a given `Font` face
- Properties: `text`, `face`, `fgcolor`, `bgcolor`, `bold`, `max_width`
- Supports `max_width` for automatic truncation
- Used in: menu items, buttons, labels

**TextBoxWidget:**

- Renders multi-line text with line wrapping
- Properties: `text`, `face`, `width`, `height`, `alignment`, `line_spacing`
- Supports justified, left, right, center alignment
- Can compute optimal height via `height_adjust=true`
- Used in: descriptions, message boxes, book text preview

Sources: [frontend/ui/widget/textwidget.lua1-100](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/textwidget.lua#L1-L100)[frontend/ui/widget/textboxwidget.lua36-200](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/textboxwidget.lua#L36-L200)

### TextBoxWidget Rendering Engine

`TextBoxWidget` uses two rendering backends:

**1. Legacy Lua renderer** (default for simple text):

- Splits text into paragraphs and words
- Wraps at word boundaries respecting language rules
- Handles hyphenation via `Utf8Proc.textwrap()`

**2. XText C++ renderer** (optional, for complex formatting):

- Enabled with `use_xtext=true`
- Better performance for large texts
- Supports more typographic features
- Required for justified alignment with hyphenation

```
Output

Rendering Paths

TextBoxWidget

Lua Renderer
_splitCharWidthChars()

XText Renderer
xtext.lua FFI

self.vertical_string_list
Array of lines

self._xtext object
C++ managed

paintTo(bb, x, y)

Blit each line to bb
```

**Text Selection in TextBoxWidget:**

The widget supports touch-based text selection:

- `HoldStartText` gesture begins selection at touch point
- `HoldPanText` extends selection as user drags
- `HoldReleaseText` triggers callback with selected text
- Selection uses `_findWordBoxAtPosition()` to map touch coordinates to character positions
- Selected text is inverted using blitbuffer inversion

Sources: [frontend/ui/widget/textboxwidget.lua36-100](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/textboxwidget.lua#L36-L100)[frontend/ui/widget/textboxwidget.lua500-800](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/textboxwidget.lua#L500-L800)

### ScrollTextWidget and ScrollHtmlWidget

Both widgets provide scrollable text viewing with page-based or line-based scrolling:

**ScrollTextWidget** (plain text):

- Wraps `TextBoxWidget` in a scrollable container
- Supports vertical scrolling with pan gestures
- Used in: text viewer, long descriptions

**ScrollHtmlWidget** (HTML content):

- Renders HTML using MuPDF's HTML engine
- Supports images, links, basic CSS
- Used in: dictionary definitions, Wikipedia articles
- Lazy-loads images on scroll for performance

```
Scrolling State

ScrollHtmlWidget

ScrollTextWidget

ScrollTextWidget

FrameContainer
with scrollbar

TextBoxWidget
full text

Virtual viewport
visible portion

ScrollHtmlWidget

FrameContainer
with scrollbar

htmlboxwidget
renders HTML

Lazy image loading

virtual_line_num
scroll position

height
visible height

_bb:getHeight()
total content height
```

**Scrollbar Rendering:**

Both widgets render a scrollbar indicator showing:

- Current position in content
- Visible viewport size relative to total content
- Calculated as: `scroll_indicator_height = (visible_height / total_height) * visible_height`

Sources: [frontend/ui/widget/scrolltextwidget.lua1-100](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/scrolltextwidget.lua#L1-L100)[frontend/ui/widget/scrollhtmlwidget.lua1-100](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/scrollhtmlwidget.lua#L1-L100)

## Specialized Widgets

### DictQuickLookup

`DictQuickLookup` displays dictionary definitions and Wikipedia articles in a popup:

```
Content Modes

Button Actions

DictQuickLookup Structure

DictQuickLookup
main popup

MovableContainer
draggable

FrameContainer
border

VerticalGroup

TitleBar
word + dict name

ScrollTextWidget or
ScrollHtmlWidget

ButtonTable
actions

Search
in current doc

Highlight
selection

Wikipedia
article

Previous
dictionary

Next
dictionary

Plain text definition
ScrollTextWidget

HTML definition
ScrollHtmlWidget

Embedded images
from dict resources
```

**Multi-Dictionary Support:**

The widget can cycle through multiple dictionary results:

- `dict_index` tracks current dictionary (1-based)
- Previous/Next buttons switch between dictionaries
- Each dictionary entry in `results` array contains:

- `definition`: text content
- `is_html`: boolean flag
- `dictionary`: dictionary name
- `images`: array of image resources

**Window Management:**

`DictQuickLookup` maintains a static `window_list` to handle nested lookups:

- When user selects text in a definition, a new lookup opens
- New window is added to `window_list`
- Closing propagates through the list
- This supports chaining multiple lookups

**Smart Positioning:**

The widget tries to position itself to avoid covering the selected word:

- Receives `word_boxes` parameter with selection coordinates
- Calculates available space above/below selection
- Prefers showing above selection if space allows
- Falls back to largest available space

Sources: [frontend/ui/widget/dictquicklookup.lua43-500](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/dictquicklookup.lua#L43-L500)[frontend/ui/widget/dictquicklookup.lua700-1000](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/dictquicklookup.lua#L700-L1000)

### ImageWidget

`ImageWidget` displays images from files or memory buffers:

**Features:**

- **Multiple sources**: Load from file path, Blitbuffer, or raw image data
- **Scaling**: `width`, `height`, `scale_factor` for resizing
- **Rotation**: `rotation_angle` for 90/180/270 degree rotation
- **Alpha blending**: `alpha` property for transparency
- **Image formats**: PNG, JPEG, GIF, WebP, BMP via `RenderImage` module

**Rendering Pipeline:**

```
Display

Processing

Image Sources

file path

image_bb
Blitbuffer

image data string

RenderImage
decode image

Scale to dimensions

Apply rotation

Cache in _bb

paintTo(bb, x, y)

Blit to target bb
```

**Memory Management:**

- Decoded images cached in `_bb` (Blitbuffer)
- `free()` method releases cached buffer
- Important for large images to avoid memory leaks
- Widget lifecycle: init → cache → paintTo → free on CloseWidget

**Common Use Cases:**

- Book covers in file browser
- Dictionary embedded images
- Icons and logos
- Screensaver images
- Comic/manga pages

Sources: [frontend/ui/widget/imagewidget.lua1-100](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/imagewidget.lua#L1-L100)[frontend/ui/widget/imagewidget.lua200-400](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/imagewidget.lua#L200-L400)

### ScreenSaverWidget

The screensaver system displays custom content when the device sleeps:

```
Layout Components

Content Modes

Widget Types

Screensaver Module

Screensaver
frontend/ui/screensaver.lua

setup(event, message)

show()

close()

ScreenSaverWidget
content display

ScreenSaverLockWidget
unlock screen

Book cover

Text message

Reading progress

Custom image

Random image from folder

Disabled / black screen

Background
color or image

Overlay message
customizable position

BookStatusWidget
progress bars
```

**Configuration Options:**

Settings stored in `G_reader_settings`:
SettingValuesPurpose`screensaver_type`cover, message, progress, image, random, disableContent to show`screensaver_show_message`booleanShow overlay message`screensaver_img_background`black, white, noneBackground for images`screensaver_msg_background`black, white, noneBackground for messages`screensaver_message_container`box, noneContainer style`screensaver_message_vertical_position`0-100Message Y position (percentage)`screensaver_delay`disable, 1s, 5s, ...Delay before showing`screensaver_stretch_images`booleanStretch to fill screen
**Content Generation:**

The screensaver builds its content in `Screensaver:_buildWidget()`:

1. **Determine content**: Based on `screensaver_type` and current context
2. **Load image or text**: From book cover, custom path, or message string
3. **Apply transformations**: Scaling, rotation, background color
4. **Add overlays**: Message text with positioning and transparency
5. **Create widget tree**: Using `ScreenSaverWidget` container
6. **Show via UIManager**: `UIManager:show(widget, "full")`

**Lock Screen:**

When `screensaver_delay` is set, the device shows the screensaver but remains awake:

- `ScreenSaverLockWidget` displays "Tap to unlock" message
- Requires tap/swipe gesture to dismiss
- Used to prevent accidental screen wakes in pocket/bag
- Delay duration: disable, 1s, 5s, 10s, 20s, 30s, 45s, 60s, 90s, 2min, 5min, 10min, 30min, 60min

Sources: [frontend/ui/screensaver.lua64-300](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/screensaver.lua#L64-L300)[frontend/ui/screensaver.lua400-700](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/screensaver.lua#L400-L700)[frontend/ui/widget/screensaverwidget.lua1-100](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/screensaverwidget.lua#L1-L100)[frontend/ui/widget/screensaverlockwidget.lua1-50](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/screensaverlockwidget.lua#L1-L50)

## Widget Refresh and Rendering

Widgets don't refresh themselves directly. Instead, they request refreshes through UIManager:

**Refresh Request Pattern:**

```
-- Mark widget dirty and request refresh
UIManager:setDirty(self.show_parent, "ui", self.dimen)
```

**Refresh Types:**
TypeUse CaseE-ink QualityFlash`full`Large images, high-fidelityHighestYes`partial`Text on white backgroundMediumAfter N refreshes`ui`Mixed UI contentMediumNo`[ui]`UI, non-mergedMediumNo`fast`Inverted highlightsLow (B&W)No`a2`Typing, keyboardLow (B&W)No`flashui`Initial UI displayMediumYes`flashpartial`Closing UI over textMediumYes (not on REAGL)
**The `show_parent` Convention:**

Widgets store a reference to their window-level parent via `show_parent`:

- Window-level widget: the widget passed to `UIManager:show()`
- Child widgets: cascade `show_parent` from parent during init
- Pattern: `MyWidget:new{ show_parent = self.show_parent or self }`

This ensures `setDirty()` can find the correct widget to repaint, as UIManager only tracks window-level widgets in `_window_stack`.

**Refresh Region:**

The `dimen` parameter specifies the screen region to refresh:

- Can be the widget's full `dimen`
- Can be a smaller region for partial updates
- Omitting `dimen` causes full-screen refresh
- Refresh regions are optimized/merged by UIManager before actual screen refresh

Sources: [frontend/ui/uimanager.lua453-671](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L453-L671)[frontend/ui/uimanager.lua1100-1300](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L1100-L1300)

---

# Widget-System-and-Base-Classes

# Widget System and Base Classes
Relevant source files
- [frontend/device/android/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/android/device.lua)
- [frontend/device/cervantes/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/cervantes/device.lua)
- [frontend/device/generic/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua)
- [frontend/device/input.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/input.lua)
- [frontend/device/kindle/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kindle/device.lua)
- [frontend/device/kobo/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/kobo/device.lua)
- [frontend/device/pocketbook/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/pocketbook/device.lua)
- [frontend/device/sdl/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/sdl/device.lua)
- [frontend/device/sony-prstux/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/sony-prstux/device.lua)
- [frontend/ui/uimanager.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua)
- [platform/android/llapp_main.lua](https://github.com/koreader/koreader/blob/9e6b1587/platform/android/llapp_main.lua)
- [spec/unit/device_spec.lua](https://github.com/koreader/koreader/blob/9e6b1587/spec/unit/device_spec.lua)

## Purpose and Scope

This document covers the foundational widget system in KOReader, which provides the base classes and infrastructure for all UI components. It explains the core widget classes (`Widget`, `WidgetContainer`, `InputContainer`, `FocusManager`), their lifecycle methods, event handling mechanisms, and how widgets integrate with UIManager.

For information about specific UI components built on this foundation (menus, keyboards, text displays), see [Menu and TouchMenu Components](/koreader/koreader/7.2-menu-and-touchmenu-components), [Input and Virtual Keyboard](/koreader/koreader/7.3-input-and-virtual-keyboard), and [Text Display Widgets](/koreader/koreader/7.4-text-display-widgets). For details on how UIManager orchestrates widgets, see [UI Manager and Event Loop](/koreader/koreader/3.2-ui-manager-and-event-loop).

---

## Base Widget Class Hierarchy

The widget system is built on a hierarchy of base classes, each adding specific capabilities:

```
Widget

+string name

+string id

+Geom dimen

+boolean modal

+boolean toast

+boolean invisible

+boolean covers_fullscreen

+boolean dithered

+number alpha

+init()

+getSize() : Geom

+paintTo(bb, x, y)

+handleEvent(event) : boolean

+onShow()

+onCloseWidget()

+onFlushSettings()

+free()

WidgetContainer

+table[] children

+Widget show_parent

+addWidget(widget)

+paintTo(bb, x, y)

+handleEvent(event) : boolean

+free()

InputContainer

+table ges_events

+table key_events

+boolean is_always_active

+boolean ignore_input

+static setIgnoreTouchInput(state)

+onInput(input_event)

+onGesture(gesture_event)

FocusManager

+Widget selected

+table layout

+number current

+boolean is_fresh

+moveFocusTo(x, y, direction)

+getFocusItem() : Widget

+onFocusMove(args)
```

**Sources:**[frontend/ui/uimanager.lua156-282](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L156-L282)[frontend/ui/uimanager.lua581-671](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L581-L671)

---

## Widget Base Class

The `Widget` class is the foundation of all UI components in KOReader. Every visual element inherits from this class, which defines the basic interface for rendering, event handling, and lifecycle management.

### Core Properties
PropertyTypePurpose`name`stringHuman-readable widget identifier for debugging`id`stringUnique identifier for the widget instance`dimen`GeomBounding box (x, y, w, h) computed during `paintTo``show_parent`WidgetReference to the window-level parent widget`modal`booleanIf true, widget is stacked above non-modal widgets`toast`booleanIf true, widget is stacked on top (above modals)`invisible`booleanIf true, widget is not painted or refreshed`covers_fullscreen`booleanOptimization hint: widget covers entire screen`dithered`booleanHint to enable dithering for this widget's content`alpha`numberTranslucency level (0.0-1.0), requires special handling`honor_silent_mode`booleanWhether widget respects UIManager silent mode`disable_double_tap`booleanOverride for double-tap gesture handling`tap_interval_override`numberCustom tap interval in milliseconds
### Core Methods

```
-- Initialization: called when widget is created
function Widget:init() end
 
-- Returns widget geometry (usually self.dimen)
function Widget:getSize() return self.dimen end
 
-- Render the widget to a BlitBuffer at coordinates (x, y)
-- This is where self.dimen is typically computed
function Widget:paintTo(bb, x, y) end
 
-- Handle an input event; return true to stop propagation
function Widget:handleEvent(event) return false end
 
-- Lifecycle callbacks
function Widget:onShow() end           -- Called when UIManager:show() is invoked
function Widget:onCloseWidget() end    -- Called when widget is being removed
function Widget:onFlushSettings() end  -- Called to persist widget state
 
-- Resource cleanup for non-Lua resources (FFI, file handles, etc.)
function Widget:free() end
```

**Sources:**[frontend/ui/uimanager.lua156-198](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L156-L198)[frontend/ui/uimanager.lua215-282](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L215-L282)

---

## WidgetContainer

`WidgetContainer` extends `Widget` to support containing child widgets, forming the basis for composite UI structures. It manages a collection of child widgets and propagates rendering and event handling to them.

### Child Widget Management

```

```

### Key Behaviors

**Painting:** When `WidgetContainer:paintTo(bb, x, y)` is called, it iterates through `self.children` in array order and calls `paintTo` on each child. Children are painted bottom-to-top (first child in array is painted first, thus appears below later children).

**Event Handling:** Events propagate through children in array order. If any child's `handleEvent` returns `true`, propagation stops immediately. This allows child widgets to "consume" events and prevent them from reaching siblings or the parent.

**Parent References:** The `show_parent` convention allows child widgets to reference their window-level parent. When creating child widgets, containers typically pass `show_parent = self.show_parent or self`, ensuring the reference chains up to the widget passed to `UIManager:show()`.

**Sources:**[frontend/ui/uimanager.lua546-565](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L546-L565)

---

## InputContainer

`InputContainer` extends `WidgetContainer` to provide declarative input event handling through gesture and keyboard event maps. This is the primary mechanism for widgets to respond to user interaction.

### Event Registration

Widgets declare event handlers using two tables:

```
-- Gesture events (touch, swipe, pan, etc.)
self.ges_events = {
    TapEvent = {
        GestureRange:new{
            ges = "tap",
            range = self.dimen,  -- Hit area
        },
    },
    SwipeEvent = {
        GestureRange:new{
            ges = "swipe",
            range = self.dimen,
            direction = "west",  -- Optional constraint
        },
    },
}
 
-- Keyboard events
self.key_events = {
    Close = { { "Back" } },  -- Maps "Back" key to "Close" event
    Select = { { "Press" } },
}
```

### Event Flow Through InputContainer

```
Widget
InputContainer
UIManager
Input
GestureDetector
Widget
InputContainer
UIManager
Input
GestureDetector
alt
[Gesture matches ges_events]
[Keyboard event matches key_events]
[No match]
Gesture detected
sendEvent(gesture_event)
handleEvent(gesture_event)
Check hit test (point in range)
onTapEvent() or onSwipeEvent()
return true/false
onClose() or onSelect()
return true/false
Propagate to children
return true (consumed) or false
```

### Input Disable Mechanism

`InputContainer` provides a global mechanism to ignore touch input:

```
-- Disable all touch input (e.g., during screen refresh)
InputContainer:setIgnoreTouchInput(true)
 
-- Re-enable
InputContainer:setIgnoreTouchInput(false)
```

When disabled, all InputContainer instances ignore touch gestures but still process keyboard events. UIManager may automatically re-enable input when showing a widget if it was previously disabled.

**Sources:**[frontend/ui/uimanager.lua122-125](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L122-L125)[frontend/ui/uimanager.lua191-197](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L191-L197)[frontend/ui/uimanager.lua278-281](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L278-L281)

---

## FocusManager

`FocusManager` extends `WidgetContainer` to provide keyboard-based focus navigation for devices with physical keys but no touch input. It maintains a focus state and allows directional navigation between focusable child widgets.

### Focus Navigation

Focus managers maintain a `layout` table describing the spatial arrangement of focusable widgets and support directional movement:

```
-- Move focus in a direction
function FocusManager:moveFocusTo(x, y, direction)
    -- direction: "up", "down", "left", "right"
end
 
-- Get currently focused widget
function FocusManager:getFocusItem()
    return self.layout[self.current]
end
 
-- Handle focus movement events
function FocusManager:onFocusMove(args)
    self:moveFocusTo(args.x, args.y, args.direction)
end
```

This is primarily used on devices like older Kindles with D-pad navigation but no touchscreen.

**Sources:**[frontend/device/generic/device.lua49](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/generic/device.lua#L49-L49)[frontend/device/input.lua113-114](https://github.com/koreader/koreader/blob/9e6b1587/frontend/device/input.lua#L113-L114)

---

## Widget Lifecycle

Understanding the widget lifecycle is crucial for proper resource management and integration with UIManager.

```

```

### Initialization Phase

**Widget Creation:**

```
local MyWidget = Widget:extend{
    name = "MyWidget",
    -- default properties
}
 
function MyWidget:init()
    -- Initialize state
    -- Set up child widgets
    self.dimen = Geom:new{w = 100, h = 50}
end
 
-- Instantiation
local widget = MyWidget:new{
    -- override properties
}
-- init() is automatically called
```

**Registration with UIManager:**

```
UIManager:show(widget, refreshtype, refreshregion, x, y, refreshdither)
-- Widget is added to _window_stack
-- Position (x, y) is stored in window table
-- Widget is marked dirty for painting
-- handleEvent("Show") is sent to widget
```

### Painting Phase

**When UIManager:_repaint() Runs:**

1. UIManager iterates through `_window_stack` bottom-to-top
2. For each dirty widget, calls `widget:paintTo(bb, window.x, window.y)`
3. Widget computes its `dimen` during `paintTo` if not already set
4. Child widgets are painted recursively
5. After painting, refresh callbacks are executed

**Key Points:**

- `dimen` is computed during `paintTo`, not during `init`
- Coordinates passed to `paintTo` are absolute screen coordinates
- BlitBuffer (`bb`) is the screen's backing buffer

**Sources:**[frontend/ui/uimanager.lua1161-1280](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L1161-L1280)

### Event Handling Phase

**Event Propagation Rules:**

1. Events are sent via `UIManager:sendEvent(event)` or `UIManager:broadcastEvent(event)`
2. For `sendEvent`: UIManager calls `handleEvent` on window-level widgets top-to-bottom
3. For `broadcastEvent`: All window-level widgets receive the event (no short-circuit)
4. Within a widget tree, events propagate children-first in array order
5. If any `handleEvent` returns `true`, propagation stops for that branch

**Common Events:**

- `"Show"`: Widget has been added to the stack
- `"CloseWidget"`: Widget is being removed
- `"FlushSettings"`: Save persistent state
- Input events: Gestures, key presses (see InputContainer)

**Sources:**[frontend/ui/uimanager.lua1027-1065](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L1027-L1065)[frontend/ui/uimanager.lua524-543](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L524-L543)

### Cleanup Phase

**Close Sequence:**

```
UIManager:close(widget, refreshtype, refreshregion, refreshdither)
-- 1. Send "FlushSettings" event -> widget saves state
-- 2. Send "CloseWidget" event -> widget cleans up
-- 3. Remove from _window_stack
-- 4. Clear from _dirty table
-- 5. Mark uncovered widgets as dirty
-- 6. Call widget:free() if method exists
```

**Resource Cleanup Best Practices:**

```
function MyWidget:onCloseWidget()
    -- Release references to large objects
    self.large_data = nil
    
    -- Close file handles
    if self.file then
        self.file:close()
        self.file = nil
    end
    
    -- Clean up child widgets with resources
    for _, child in ipairs(self.children) do
        if child.free then
            child:free()
        end
    end
end
 
function MyWidget:free()
    -- Free FFI/C resources
    if self.ffi_resource then
        C.free(self.ffi_resource)
        self.ffi_resource = nil
    end
end
```

**Sources:**[frontend/ui/uimanager.lua215-282](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L215-L282)[frontend/ui/uimanager.lua536-537](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L536-L537)

---

## Integration with UIManager

Widgets are passive objects; UIManager is the active orchestrator that manages their lifecycle, handles repainting, and routes events.

### Window Stack Management

```
UIManager._window_stack

above

above

above

above

Window 1
{x=0, y=0, widget=FileManager}

Window 2
{x=0, y=0, widget=ReaderUI}

Window 3
{x=50, y=100, widget=InfoMessage
modal=true}

Window 4
{x=0, y=0, widget=VirtualKeyboard
modal=true}

Window 5
{x=100, y=50, widget=Notification
toast=true}

Standard widgets
stack normally

Modal widgets
stack together

Toast widgets
always on top
```

**Insertion Rules:**

- Toast widgets are inserted at the top of existing toasts
- Modal widgets are inserted at the top of existing modals
- Standard widgets are inserted below modals and toasts

### The setDirty Pattern

Widgets don't repaint themselves directly. Instead, they ask UIManager to schedule a repaint:

```
-- Mark widget dirty and schedule refresh
UIManager:setDirty(widget, refreshtype, refreshregion, refreshdither)
 
-- Common patterns:
 
-- 1. Full widget refresh (region computed during paintTo)
UIManager:setDirty(self, "ui")
 
-- 2. Partial refresh with known region
UIManager:setDirty(self, "partial", self.dimen)
 
-- 3. Refresh via callback after painting
UIManager:setDirty(self, function()
    return "ui", self.dimen
end)
 
-- 4. Refresh without repainting (nil widget)
UIManager:setDirty(nil, "full")
 
-- 5. Repaint all widgets
UIManager:setDirty("all", "full")
```

**Refresh Types:**

- `"full"`: High-fidelity flashing refresh (for images)
- `"partial"`: Medium fidelity (for text), may be promoted to full after FULL_REFRESH_COUNT
- `"ui"`: Medium fidelity (mixed content), no promotion
- `"fast"`: Low fidelity (monochrome, inversion highlights)
- `"flashui"`, `"flashpartial"`: Flashing variants to avoid ghosting
- `"[partial]"`, `"[ui]"`: Non-merging variants for precise control

**Translucency Handling:**

Translucent widgets require special treatment. When `UIManager:setDirty()` is called on a translucent widget (one with `0 < alpha < 1`), UIManager automatically marks all widgets below it as dirty too, ensuring the background is freshly painted for proper alpha blending.

**Sources:**[frontend/ui/uimanager.lua453-671](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L453-L671)[frontend/ui/uimanager.lua598-634](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L598-L634)

---

## The show_parent Convention

The `show_parent` field is a critical convention for widget communication and proper integration with UIManager.

### Purpose

Child widgets need to call `UIManager:setDirty()` to request repaints, but `setDirty` only accepts window-level widgets (those passed to `UIManager:show()`). The `show_parent` field provides a reference chain from any nested child widget up to its window-level ancestor.

### Pattern Implementation

```
-- In a window-level widget
function ParentWidget:init()
    self.child_widget = ChildWidget:new{
        -- If self is already window-level, use self
        -- Otherwise, cascade the existing show_parent
        show_parent = self.show_parent or self,
    }
end
 
-- In the child widget
function ChildWidget:onSomeEvent()
    -- Child can now request repaints via show_parent
    UIManager:setDirty(self.show_parent, "ui")
end
```

### Debug Validation

UIManager includes debug guards that validate `setDirty` calls. If a widget passed to `setDirty` is not in `_window_stack`, a debug warning is logged. This helps catch cases where `show_parent` was not properly set up.

**Sources:**[frontend/ui/uimanager.lua546-563](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L546-L563)[frontend/ui/uimanager.lua672-694](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L672-L694)

---

## Common Widget Patterns

### Movable Widgets with Transparency

Widgets can be wrapped in a `MovableContainer` to allow repositioning and support translucency:

```
-- Convention: store MovableContainer in self.movable
self.movable = MovableContainer:new{
    alpha = 0.8,  -- 80% opaque
    -- widget contents
}
 
-- UIManager checks self.show_parent.movable for translucency
-- Button widget checks self.show_parent.movable to handle translucent overlays
```

This convention allows UIManager and other widgets to detect translucency and handle it correctly (e.g., marking background widgets dirty).

### Covers Fullscreen Optimization

Widgets that cover the entire screen can set `covers_fullscreen = true`. UIManager uses this to optimize `close()` operations: when closing widgets, it stops marking lower widgets as dirty once it reaches a fullscreen widget, since those lower widgets are completely hidden anyway.

```
local MyFullscreenWidget = WidgetContainer:extend{
    covers_fullscreen = true,
}
```

**Sources:**[frontend/ui/uimanager.lua246-256](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L246-L256)

### Silent Mode

Widgets that should respect "do not disturb" type modes can set `honor_silent_mode = true`. When UIManager is in silent mode (`UIManager:setSilentMode(true)`), it refuses to show such widgets:

```
local Notification = Widget:extend{
    honor_silent_mode = true,
}
 
-- Will not show if silent mode is active
UIManager:show(notification)
```

**Sources:**[frontend/ui/uimanager.lua127-133](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L127-L133)[frontend/ui/uimanager.lua161-164](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L161-L164)

### Modal and Toast Widgets

**Modal widgets** block interaction with widgets below them:

```
local Dialog = Widget:extend{
    modal = true,
}
```

**Toast widgets** (notifications, progress indicators) appear on top of everything:

```
local Toast = Widget:extend{
    toast = true,
}
```

The stacking order ensures toasts are always visible and modals properly block lower UI.

**Sources:**[frontend/ui/uimanager.lua168-182](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L168-L182)

---

## Event Handling Details

### Event Structure

Events in KOReader are simple tables with a name and optional arguments:

```
local Event = require("ui/event")
 
-- Create an event
local event = Event:new("MyEvent", arg1, arg2, ...)
 
-- Arguments stored in event.args
event.args[1]  -- arg1
event.args[2]  -- arg2
```

### Event Propagation Example

```
sendEvent

propagate to children

continue propagation

stops here

UIManager

ParentWidget
handleEvent returns false

Child1
handleEvent returns false

Child2
handleEvent returns TRUE

Child3
not reached
```

When `Child2` returns `true`, the event does not reach `Child3` or propagate further up the tree.

### Close Event vs. CloseWidget Event

**Close Event:** A special broadcast event (sent to all widgets) triggered by:

- System poweroff/reboot (UIManager broadcasts "Close")
- InputContainer binding the "Back" key to generate a "Close" event
- Used to programmatically close `show`n widgets

**CloseWidget Event:** Sent by UIManager during `UIManager:close(widget)` to notify the specific widget that it's being closed. Not broadcast, only sent to the widget being closed.

Best practice: Don't implement custom `onClose` handlers unless you need the special broadcast/programmatic close semantics. Use `onCloseWidget` for normal cleanup.

**Sources:**[frontend/ui/uimanager.lua538-543](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L538-L543)

---

## Summary Table of Key Methods
MethodCalled ByPurpose`Widget:init()`Constructor (`new`)Initialize widget state, create children`Widget:paintTo(bb, x, y)`UIManager:_repaint()Render widget to screen buffer`Widget:handleEvent(event)`UIManager:sendEvent()Process input/system events`Widget:onShow()`UIManager:show()Called when widget becomes visible`Widget:onCloseWidget()`UIManager:close()Clean up before removal`Widget:onFlushSettings()`UIManager:close()Persist widget state`Widget:free()`UIManager:close()Release FFI/C resources`Widget:getSize()`VariousQuery widget dimensions
**Sources:**[frontend/ui/uimanager.lua156-282](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L156-L282)[frontend/ui/uimanager.lua1161-1280](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/uimanager.lua#L1161-L1280)

---

# Menu-and-TouchMenu-Components

# Menu and TouchMenu Components
Relevant source files
- [frontend/apps/filemanager/filemanagermenu.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagermenu.lua)
- [frontend/apps/reader/modules/readerconfig.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerconfig.lua)
- [frontend/apps/reader/modules/readermenu.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readermenu.lua)
- [frontend/ui/widget/menu.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/menu.lua)
- [frontend/ui/widget/touchmenu.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/touchmenu.lua)

This document covers KOReader's menu widget implementations: `Menu` for keyboard-based navigation on non-touch devices and `TouchMenu` for touch-based hierarchical menus with tab support. These widgets provide the core menu display and navigation capabilities used throughout the application.

For information about the widget base classes (`InputContainer`, `FocusManager`), see page 7.1. For application-specific menu integration (`FileManagerMenu`, `ReaderMenu`), see pages 4.1 and 4.2.

## Overview: Menu vs TouchMenu

KOReader provides two menu widget implementations optimized for different device types:
WidgetDevice TypeBase ClassPrimary NavigationTab Support`Menu`Non-touch, keyboard`FocusManager`Arrow keys, shortcutsNo`TouchMenu`Touch devices`FocusManager`Touch gestures, swipesYes (via `TouchMenuBar`)
Both widgets share common capabilities:

- Display paginated lists of menu items
- Support hierarchical navigation (submenus)
- Maintain a navigation stack (`item_table_stack`)
- Handle page navigation (next/previous/first/last)
- Provide visual feedback on item selection

**Menu Widget Class Hierarchy**

```
FocusManager
(keyboard navigation)

InputContainer
(gesture handling)

Menu
frontend/ui/widget/menu.lua

TouchMenu
frontend/ui/widget/touchmenu.lua

MenuItem
Single item in Menu

TouchMenuItem
Single item in TouchMenu

TouchMenuBar
Tab bar with icons

WidgetContainer

ItemShortCutIcon
Keyboard shortcut display
```

Sources: [frontend/ui/widget/menu.lua569-622](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/menu.lua#L569-L622)[frontend/ui/widget/touchmenu.lua457-475](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/touchmenu.lua#L457-L475)

## Menu Widget (Non-Touch Devices)

The `Menu` widget provides a keyboard-navigable menu for devices with physical keys. It extends `FocusManager` to support D-Pad navigation and keyboard shortcuts.

### Menu Class Structure

**Key Properties**
PropertyTypeDescription`item_table``table`Array of menu item definitions`item_table_stack``table`Stack for hierarchical navigation`page``number`Current page number`perpage``number`Items displayed per page`item_shortcuts``table`Key mappings for item selection (Q,W,E,R,...)`title_bar``TitleBar`Optional title display`page_info_text``Button`Page number display with search`page_return_arrow``Button`Back button for submenu navigation
**Menu Initialization Flow**

```
Menu:init()

_recalculateDimen()
Calculate item heights

Setup title_bar
(TitleBar widget)

Setup pagination controls
(chevron buttons)

Register gesture events
(tap, swipe, pan)

Register key events
(shortcuts, navigation)

updateItems()
Build item_group

For each item on page:
Create MenuItem widget

Add to item_group
VerticalGroup
```

Sources: [frontend/ui/widget/menu.lua670-963](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/menu.lua#L670-L963)

### MenuItem Rendering

Each `MenuItem` handles layout and rendering of a single menu entry, supporting text, mandatory information, shortcuts, and tree structures.

**MenuItem Layout Components**

```
MenuItem
InputContainer

state_button
(TOC expand/collapse)

item_name
(TextWidget or TextBoxWidget)

post_text_widget
(optional, e.g., chapter length)

dots_widget
(dot leaders)

mandatory_widget
(file size, page number)

ItemShortCutIcon
(keyboard shortcut)

HorizontalGroup

UnderlineContainer
(focus indicator)
```

**MenuItem Text Display Logic**

The `MenuItem` widget supports multiple text display modes:
ModeSettingBehaviorSingle line`single_line=true`Text truncated with ellipsis if too longMulti-line`multilines_forced=true`Uses `TextBoxWidget`, wraps textAuto font size`multilines_show_more_text=true`Reduces font size to fit more textWith dots`with_dots=true`Shows dot leaders between text and mandatory
Sources: [frontend/ui/widget/menu.lua88-447](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/menu.lua#L88-L447)[frontend/ui/widget/menu.lua225-385](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/menu.lua#L225-L385)

### Menu Pagination System

The `Menu` widget automatically paginates items and provides navigation controls.

**Pagination Calculation**

```
-- From Menu:_recalculateDimen()
self.available_height = self.inner_dimen.h - top_height - bottom_height
self.item_dimen = Geom:new{
    w = self.inner_dimen.w,
    h = math.floor(self.available_height / perpage),
}
self.page_num = self:getPageNumber(#self.item_table)
```

**Page Navigation Methods**
MethodActionKey Binding`onNextPage()`Go to next pagePgFwd`onPrevPage()`Go to previous pagePgBack`onFirstPage()`Go to first pageShift+PgBack`onLastPage()`Go to last pageShift+PgFwd`onGotoPage(nb)`Go to specific pageInput dialog
Sources: [frontend/ui/widget/menu.lua632-668](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/menu.lua#L632-L668)[frontend/ui/widget/menu.lua1279-1317](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/menu.lua#L1279-L1317)

### Menu Item Selection and Actions

**Selection Event Flow**

```
UIManager
Callback
Menu
MenuItem
User
UIManager
Callback
Menu
MenuItem
User
alt
[flash_ui enabled]
alt
[keep_menu_ope-
n]
alt
[has sub_item_table]
[has callback]
Tap or keyboard shortcut
onTapSelect()
Invert colors (highlight)
forceRePaint()
Invert colors (unhighlight)
onMenuSelect(entry, pos)
Push current table to stack
updateItems(1) with sub_item_table
entry.callback()
updateItems() to refresh
closeMenu()
```

Sources: [frontend/ui/widget/menu.lua499-564](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/menu.lua#L499-L564)[frontend/ui/widget/menu.lua1056-1177](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/menu.lua#L1056-L1177)

## TouchMenu Widget (Touch Devices)

The `TouchMenu` widget provides a tab-based hierarchical menu optimized for touch interaction. It extends `FocusManager` for optional keyboard support while primarily using gesture handlers.

### TouchMenu Class Structure

**Key Properties**
PropertyTypeDescription`tab_item_table``table`Array of tab definitions with items`item_table_stack``table`Stack for submenu navigation`cur_tab``number`Currently active tab index`bar``TouchMenuBar`Tab bar with icon buttons`item_group``VerticalGroup`Container for menu items`page``number`Current page within active tab`max_per_page``number`Maximum items per page (default: 10)
**TouchMenu Initialization Flow**

```
TouchMenu:init()

Create TouchMenuBar
(icon tabs)

Setup footer
(page info, time, battery)

Register gestures
(tap, swipe)

Register key events
(Back, Menu, PgFwd/Back)

bar:switchToTab(last_index)

switchMenuTab(tab_num)

Set item_table from
tab_item_table[tab_num]

updateItems(target_page)

For each item on page:
Create TouchMenuItem

Add to item_group
```

Sources: [frontend/ui/widget/touchmenu.lua477-647](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/touchmenu.lua#L477-L647)[frontend/ui/widget/touchmenu.lua753-772](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/touchmenu.lua#L753-L772)

### TouchMenuBar (Tab Navigation)

The `TouchMenuBar` widget displays icon tabs at the top of a `TouchMenu`, with visual indicators for the active tab.

**TouchMenuBar Structure**

```
TouchMenuBar
InputContainer

icon_widgets[]
(array of IconButton)

icon_seps[]
(LineWidget separators)

bar_sep
(LineWidget with empty_segments)

IconButton (tab 1)

IconButton (tab 2)

IconButton (tab N)

callback()
Updates bar_sep.empty_segments
Calls menu:switchMenuTab(k)

empty_segments
(highlights active tab)
```

**Tab Switching Mechanism**

When a tab icon is clicked, the `TouchMenuBar` updates the underline separator to highlight the active tab:

```
-- From TouchMenuBar:init() icon button callback
ib.callback = function()
    self.bar_sep.empty_segments = {
        { s = _start_seg, e = _end_seg }  -- Active tab position
    }
    -- Update separator styles
    for i, sep in ipairs(self.icon_seps) do
        sep.style = current_icon and "solid" or "none"
    end
    self.menu:switchMenuTab(k)
end
```

Sources: [frontend/ui/widget/touchmenu.lua281-453](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/touchmenu.lua#L281-L453)

### TouchMenuItem Rendering

`TouchMenuItem` handles the display and interaction for individual menu items in a `TouchMenu`.

**TouchMenuItem Layout**

```
TouchMenuItem
InputContainer

CheckMark or RadioMark
(if checkable)

TextWidget
(item text)

Size.padding.default

max_width calculation
(dimen.w - padding - checkmark)

HorizontalGroup

FrameContainer
(with bordersize=0)

UnderlineContainer
(focus indicator)
```

**TouchMenuItem Features**
FeaturePropertyDescriptionCheckmark`item.checked_func()`Shows checkmark if item is checkedRadio button`item.radio=true`Shows radio mark instead of checkmarkEnabled state`item.enabled_func()`Grays out text if disabledCustom font`item.font_func()`Allows custom font per item (e.g., ReaderFont)Help text`item.help_text`Shows on hold when disabled
Sources: [frontend/ui/widget/touchmenu.lua46-159](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/touchmenu.lua#L46-L159)

### TouchMenu Item Selection and Flash UI

`TouchMenuItem` provides visual feedback during selection with the "flash_ui" system.

**Touch Event Processing with Flash UI**

```
Callback
UIManager
TouchMenu
TouchMenuItem
User
Callback
UIManager
TouchMenu
TouchMenuItem
User
alt
[flash_ui enabled]
alt
[keep_men-
u_open]
alt
[tap_on_checkmark]
[has sub_item_table]
[has callback]
alt
[flash_ui
enabled]
Tap gesture
onTapSelect(ges)
Set item_frame.invert = true
widgetInvert() + setDirty()
forceRePaint()
yieldToEPDC()
Set item_frame.invert = false
widgetInvert()
onMenuSelect(item, tap_on_checkmark)
item.checkmark_callback()
updateItems()
Push to item_table_stack
updateItems(1) with sub_item_table
item.callback()
updateItems()
closeMenu()
forceRePaint()
```

Sources: [frontend/ui/widget/touchmenu.lua171-223](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/touchmenu.lua#L171-L223)[frontend/ui/widget/touchmenu.lua835-880](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/touchmenu.lua#L835-L880)

## Menu Pagination System

Both `Menu` and `TouchMenu` support automatic pagination when the number of items exceeds the available space.

### Pagination Calculations

**Menu Widget Pagination**

```
-- From Menu:_recalculateDimen()
local perpage = self.items_per_page 
    or G_reader_settings:readSetting("items_per_page") 
    or self.items_per_page_default  -- 14
 
self.item_dimen = Geom:new{
    w = self.inner_dimen.w,
    h = math.floor(self.available_height / perpage),
}
 
self.page_num = math.ceil(#self.item_table / perpage)
```

**TouchMenu Widget Pagination**

```
-- From TouchMenu:updateItems()
local menu_height = self.item_group:getSize().h
local items_height = menu_height - self.bar:getSize().h 
    - self.footer_top_margin:getSize().h 
    - self.footer:getSize().h
 
self.max_per_page = math.floor(items_height / 
    (self.item_height + self.split_line:getSize().h))
 
self.perpage = math.min(self.max_per_page, 
    self.item_table.max_per_page or self.max_per_page_default)  -- 10
 
self.page_num = math.ceil(#self.item_table / self.perpage)
```

Sources: [frontend/ui/widget/menu.lua632-668](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/menu.lua#L632-L668)[frontend/ui/widget/touchmenu.lua649-664](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/touchmenu.lua#L649-L664)

### Page Navigation Controls

**Menu Navigation UI**

```
Menu Footer

tap

tap

hold

tap

tap

page_info_first_chev
(chevron.first)

page_info_left_chev
(chevron.left)

page_info_text
Page X of Y + search

page_info_right_chev
(chevron.right)

page_info_last_chev
(chevron.last)

onFirstPage()

onPrevPage()

Search/Go to page dialog

onNextPage()

onLastPage()
```

**TouchMenu Navigation UI**

```
TouchMenu Footer

tap

hold

tap

hold

time_info
(Button: time + battery)

page_info_left_chev
(chevron.left)

page_info_text
Page X of Y

page_info_right_chev
(chevron.right)

up_button
(IconButton: chevron.up)

onPrevPage()

onFirstPage()

onNextPage()

onLastPage()

backToUpperMenu()
```

Sources: [frontend/ui/widget/menu.lua727-829](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/menu.lua#L727-L829)[frontend/ui/widget/touchmenu.lua538-608](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/touchmenu.lua#L538-L608)

### Page Update and Refresh

**updateItems() Process**

```
updateItems(target_page, target_item_id)

Calculate page number
(target_page or search for item_id)

item_group:clear()

Add menu bar/title
(TouchMenu only)

Loop: idx_offset to idx_offset + perpage

Create MenuItem/TouchMenuItem

Add to item_group

Add separator if needed

Next item

Add footer
(page info, device info)

Update page navigation buttons

UIManager:setDirty()
```

Sources: [frontend/ui/widget/menu.lua983-1040](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/menu.lua#L983-L1040)[frontend/ui/widget/touchmenu.lua649-751](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/touchmenu.lua#L649-L751)

## Touch Interaction System

The widget system handles touch gestures through a zone-based registration system that allows widgets to register touch zones and gesture handlers.

### Gesture Registration and Handling

```
Menu Activation Zones

FileManagerMenu

filemanager_tap zone

filemanager_ext_tap zone

filemanager_swipe zone

ReaderMenu

readermenu_tap zone

readermenu_swipe zone

readermenu_pan zone

Gesture Event Flow

Touch hardware

GestureDetector

UIManager:handleInput()

Widget gesture handlers

onTap*

onHold*

onSwipe*

onPan*

Touch Zone Registration

Widget

registerTouchZones()

GestureRange definitions

_ordered_touch_zones[]
```

Sources: [frontend/apps/filemanager/filemanagermenu.lua78-131](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagermenu.lua#L78-L131)[frontend/apps/reader/modules/readermenu.lua98-180](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readermenu.lua#L98-L180)[frontend/ui/widget/touchmenu.lua493-508](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/touchmenu.lua#L493-L508)

This widget and menu system provides the foundation for KOReader's user interface, supporting both touch and non-touch devices while maintaining consistent interaction patterns across the FileManager and Reader applications.

---

# Input-and-Virtual-Keyboard

# Input and Virtual Keyboard
Relevant source files
- [frontend/apps/filemanager/filemanagersetdefaults.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagersetdefaults.lua)
- [frontend/ui/elements/screen_dpi_menu_table.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/elements/screen_dpi_menu_table.lua)
- [frontend/ui/language.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/language.lua)
- [frontend/ui/widget/inputdialog.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/inputdialog.lua)
- [frontend/ui/widget/inputtext.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/inputtext.lua)
- [frontend/ui/widget/multiinputdialog.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/multiinputdialog.lua)
- [frontend/ui/widget/virtualkeyboard.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua)

This page documents KOReader's text input system, covering the virtual keyboard, text entry widgets, and multi-language input support. The system provides on-screen keyboard functionality for touch devices, physical keyboard integration, and sophisticated text editing capabilities.

For information about the overall widget hierarchy, see [Widget System and Base Classes](/koreader/koreader/7.1-widget-system-and-base-classes). For text display widgets like `TextBoxWidget` and `ScrollTextWidget`, see [Text Display Widgets](/koreader/koreader/7.4-text-display-widgets).

## Overview and Architecture

The text input system in KOReader consists of three main components:
ComponentPurposeKey Features**VirtualKeyboard**On-screen keyboardMulti-language layouts, layer system (shift/symbol/umlaut), hold-for-alternatives popup**InputText**Text entry fieldCursor management, text selection, scrolling, password mode, keyboard integration**InputDialog**Modal input dialogWraps InputText with title/buttons, keyboard visibility management, fullscreen mode
### Component Interaction Flow

```
Keyboard Layer

Text Entry Layer

User Interface Layer

addChar()

delChar()

InputDialog
(Modal Container)

TitleBar

ButtonTable

InputText
(InputContainer)

TextBoxWidget or
ScrollTextWidget

Cursor Position
charpos, charlist

VirtualKeyboard
(FocusManager)

VirtualKey[i,j]
(InputContainer)

VirtualKeyPopup
(Hold Alternatives)
```

**Sources:**[frontend/ui/widget/inputdialog.lua123-201](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/inputdialog.lua#L123-L201)[frontend/ui/widget/inputtext.lua24-70](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/inputtext.lua#L24-L70)[frontend/ui/widget/virtualkeyboard.lua782-845](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua#L782-L845)

## VirtualKeyboard: On-Screen Keyboard System

The `VirtualKeyboard` widget provides a full on-screen keyboard with multiple layouts, layers, and input modes.

**Sources:**[frontend/ui/widget/virtualkeyboard.lua782-883](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua#L782-L883)

### VirtualKeyboard Structure

The `VirtualKeyboard` extends `FocusManager` and manages a grid of `VirtualKey` widgets. Each key can have multiple character variants depending on the current layer.

#### Keyboard State and Properties

```
Layout System

Special Key Tables

Keyboard State

defines

defines

switches

defines

KEYS[row][col][layer]
(Layout Definition)

keyboard_layer
(1-12)

shiftmode
(boolean)

symbolmode
(boolean)

umlautmode
(boolean)

shiftmode_keys
{label: true}

symbolmode_keys
{label: true}

utf8mode_keys
(layout switcher)

umlautmode_keys
(diacritic layer)

keyboard_layout
(language code)

lang_to_keyboard_layout
{lang: layout_file}

ui/data/keyboardlayouts/
XX_keyboard.lua
```

**Sources:**[frontend/ui/widget/virtualkeyboard.lua782-845](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua#L782-L845)[frontend/ui/widget/virtualkeyboard.lua986-1007](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua#L986-L1007)

### Keyboard Layer System

The keyboard layer system allows each key to display different characters based on the current mode. Layers are numbered 1-12 and calculated from three boolean mode flags:

```
keyboard_layer = 2 - shift + 2*symbol + 4*umlaut

```
LayerShiftSymbolUmlautTypical Use2falsefalsefalse**Default lowercase**1truefalsefalseUppercase letters4falsetruefalse**Symbols and numbers**3truetruefalseAlternate symbols6falsefalsetrue**Accented characters (lowercase)**5truefalsetrueAccented characters (uppercase)8falsetruetrueSymbols with umlauts7truetruetrueAlternate symbols with umlauts
**Sources:**[frontend/ui/widget/virtualkeyboard.lua986-1007](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua#L986-L1007)

#### Layer Switching Flow

```
UIManager
Keyboard Layout
VirtualKey
VirtualKeyboard
User
UIManager
Keyboard Layout
VirtualKey
VirtualKeyboard
User
Calculate layer from
shift/symbol/umlaut
Key shows character
from new layer
loop
[For each key position]
Tap shift key
Check shiftmode_keys
setLayer("Shift")
Toggle shiftmode
initLayer(nil)
addKeys()
Get KEYS[row][col][layer]
Key definition
Create new VirtualKey
_refresh(want_flash=true)
setDirty(keyboard)
```

**Sources:**[frontend/ui/widget/virtualkeyboard.lua58-78](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua#L58-L78)[frontend/ui/widget/virtualkeyboard.lua986-1007](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua#L986-L1007)[frontend/ui/widget/virtualkeyboard.lua1009-1115](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua#L1009-L1115)

### VirtualKey: Individual Key Widget

Each key on the keyboard is a `VirtualKey` widget (extends `InputContainer`) that responds to tap, hold, and swipe gestures.

#### Key Initialization and Callbacks

```
Shift/Caps

Symbol

UTF-8/Globe

Umlaut/Äéß

Backspace

Arrow Keys

Regular Char

VirtualKey:init()

Key
type?

callback = setLayer('Shift')
hold_callback = caps lock

callback = setLayer('Sym')

callback = cycle layouts
hold_callback = layout dialog

callback = setLayer('Äéß')

callback = delChar()
hold_callback = delToStartOfLine()
swipe west = delWord(left)
swipe north = delWord()

callback = move cursor
hold_callback = jump to line end

callback = addChar(key)
hold_callback = show popup
swipe = insert variant

Create label widget
(TextWidget or ImageWidget)

Wrap in FrameContainer
with background color
```

**Sources:**[frontend/ui/widget/virtualkeyboard.lua58-199](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua#L58-L199)[frontend/ui/widget/virtualkeyboard.lua400-485](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua#L400-L485)

#### Key Gesture Handlers
GestureMethodBehaviorVisual Feedback**Tap**`onTapSelect()`Execute `callback()`Invert key colors, haptic feedback**Hold**`onHoldSelect()`Execute `hold_callback()` (if defined)Invert colors, show popup for char variants**Swipe**`onSwipeKey()`Execute `swipe_callback()` with directionInvert colors, insert swipe variant**Hold Release**`onHoldReleaseKey()`Execute `callback()` (for continuous keys like Del)-**Pan Release**`onPanReleaseKey()`Execute `callback()` (for drag operations)-
**Sources:**[frontend/ui/widget/virtualkeyboard.lua400-509](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua#L400-L509)

### VirtualKeyPopup: Hold-for-Alternatives

When a user holds a key that has character variants (defined in `key_chars`), a `VirtualKeyPopup` appears showing alternative characters that can be selected.

#### Popup Structure and Layout

```
Popup Layout Grid

original key

Positioning

Parent VirtualKey
position

Calculate offset to
center popup on
middle key

Check screen
boundaries

Adjust position
if overflow

VirtualKeyPopup
(FocusManager)

Extra Row
(key_chars[2-7])

Top Row
(northwest, north, northeast)

Middle Row
(west, ORIGINAL, east)

Bottom Row
(southwest, south, southeast)

Highlighted with
LIGHT_GRAY background
```

**Sources:**[frontend/ui/widget/virtualkeyboard.lua511-780](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua#L511-L780)

#### Popup Key Definitions

Keys in the popup are defined in the parent key's `key_chars` table with directional names:

```
-- Example for letter 'e' with diacritics
key_chars = {
    [1] = "e",              -- Original character (center)
    northwest = "è",
    north = "é",
    northeast = "ê",
    west = "ë",
    east = "ē",
    southwest = "ė",
    south = "ę",
    southeast = "ě",
    -- Can also have functions:
    north_func = function() ... end,
}
```

**Sources:**[frontend/ui/widget/virtualkeyboard.lua538-683](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua#L538-L683)

#### Popup Interaction Flow

```
UIManager
InputText
VirtualKeyPopup
VirtualKey
User
UIManager
InputText
VirtualKeyPopup
VirtualKey
User
Special handling:
Release on center = type char
Release on edge = select that char
alt
[User taps variant]
[User releases hold]
[User taps outside]
Hold gesture
Check if key_chars defined
new{parent_key = self}
Build grid layout
Position centered on parent
show(popup)
Tap variant key
addChar(variant)
close(popup)
Hold release
addChar(selected)
close(popup)
Tap outside
close(popup)
```

**Sources:**[frontend/ui/widget/virtualkeyboard.lua178-184](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua#L178-L184)[frontend/ui/widget/virtualkeyboard.lua518-529](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua#L518-L529)[frontend/ui/widget/virtualkeyboard.lua641-675](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua#L641-L675)

### Keyboard Layout Files

Keyboard layouts are defined in `frontend/ui/data/keyboardlayouts/` with language-specific files (e.g., `en_keyboard.lua`, `de_keyboard.lua`).

#### Layout File Structure

```
return {
    min_layer = 1,
    max_layer = 4,
    shiftmode_keys = {[""] = true},      -- Keys that toggle shift
    symbolmode_keys = {["Sym"] = true},  -- Keys that toggle symbol layer
    utf8mode_keys = {["🌐"] = true},     -- Keys that switch language
    keys = {
        -- Each row is a table
        {
            -- Each key position is a table
            {
                -- Each layer (1-12) has a character or table
                [1] = "Q",  -- Shift layer
                [2] = "q",  -- Default layer
                [3] = "1",  -- Shift+Symbol layer
                [4] = "1",  -- Symbol layer
            },
            -- Key with variants for hold-to-show-alternatives
            {
                [1] = "E",
                [2] = {
                    key = "e",
                    label = "e",
                    north = "é",
                    northeast = "ê",
                    northwest = "è",
                    -- ... more variants
                },
                -- ...
            },
            -- ...
        },
        -- More rows...
    },
}
```

**Sources:** Based on keyboard layout structure pattern from [frontend/ui/widget/virtualkeyboard.lua847-883](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua#L847-L883)

#### Language to Layout Mapping

The `lang_to_keyboard_layout` table in `VirtualKeyboard` maps ISO language codes to layout files:

```
Mapping Table

Language Code
(from G_reader_settings)

lang_to_keyboard_layout

'en' → 'en_keyboard'

'de' → 'de_keyboard'

'ar' → 'ar_keyboard'

'zh_CN' → 'zh_CN_keyboard'

... 30+ languages

ui/data/keyboardlayouts/
XX_keyboard.lua
```

**Sources:**[frontend/ui/widget/virtualkeyboard.lua807-844](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua#L807-L844)

### Layout Switching

Users can switch keyboard layouts by tapping or swiping the globe key (UTF-8 mode key).

#### Layout Switching Flow

```
UIManager
KeyboardLayoutDialog
G_reader_settings
VirtualKeyboard
Globe Key (🌐)
User
UIManager
KeyboardLayoutDialog
G_reader_settings
VirtualKeyboard
Globe Key (🌐)
User
genKeyboardLayoutKeyChars
maps directions to layouts
alt
[Tap to cycle through enabled layouts]
[Hold to open layout selection dialog]
[Swipe for quick layout switch]
Tap
Get keyboard_layouts list
["en", "de", "fr"]
Find current in list
Advance to next (wrap around)
setKeyboardLayout(next_layout)
init() with new layout
_refresh(want_flash=true)
Hold
new{...}
show(dialog)
Select layout(s)
Save enabled layouts
setKeyboardLayout(selected)
Swipe (north/south/east/west)
Get layout from direction
setKeyboardLayout(layout)
```

**Sources:**[frontend/ui/widget/virtualkeyboard.lua79-126](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua#L79-L126)[frontend/ui/widget/virtualkeyboard.lua333-361](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua#L333-L361)[frontend/ui/widget/virtualkeyboard.lua906-921](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua#L906-L921)

## InputText: Text Entry Field Widget

`InputText` is the text entry field widget that displays editable text with a cursor. It extends `InputContainer` and integrates with either `VirtualKeyboard` or `PhysicalKeyboard` depending on device capabilities.

### InputText Architecture

```
Keyboard Integration

Display Widget

Text Storage

addChar()

delChar()

InputText
(InputContainer)

charlist
(table of chars)

charpos
(cursor position)

text
(full string)

text_widget
(TextBoxWidget or
ScrollTextWidget)

_frame_textwidget
(FrameContainer)

_password_toggle
(CheckButton)

keyboard
(VirtualKeyboard or
PhysicalKeyboard)
```

**Sources:**[frontend/ui/widget/inputtext.lua24-70](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/inputtext.lua#L24-L70)[frontend/ui/widget/inputtext.lua392-428](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/inputtext.lua#L392-L428)

### Text Editing Operations

`InputText` provides methods for text manipulation that are called by keyboard keys:

#### Character Operations

```
Cursor Navigation

Delete Operations

Insert Operations

addChar(char)
Insert at charpos

addChars(text)
Insert string

delChar()
Backspace

delNextChar()
Delete key

delWord(left)
Delete word

delToStartOfLine()
Delete to line start

delAll()
Clear all text

leftChar()
Move left

rightChar()
Move right

upLine()
Move up

downLine()
Move down

goToHome()
Start of text

goToEnd()
End of text

goToStartOfLine()

goToEndOfLine()

initTextBox()
Recreate text_widget

resyncPos()
Update charpos
```

**Sources:** Character operations at [frontend/ui/widget/inputtext.lua774-1066](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/inputtext.lua#L774-L1066) cursor navigation at [frontend/ui/widget/inputtext.lua983-1130](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/inputtext.lua#L983-L1130)

#### Text Box Recreation

Every time the text content changes, `InputText` recreates the underlying `TextBoxWidget` or `ScrollTextWidget`:

```
UIManager
TextWidget (new)
TextWidget (old)
InputText
Keyboard
UIManager
TextWidget (new)
TextWidget (old)
InputText
Keyboard
Release resources
alt
[scroll = true]
opt
[edit_callback
defined]
addChar("x")
Insert in charlist[charpos]
charpos++
initTextBox()
free(true)
Determine show_text
(password mode check)
ScrollTextWidget:new{...}
TextBoxWidget:new{...}
text_widget
resyncPos()
getCharPos()
(charpos, top_line_num)
setDirty(parent, "ui", dimen)
edit_callback(is_text_edited)
```

**Sources:**[frontend/ui/widget/inputtext.lua433-599](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/inputtext.lua#L433-L599)

### Keyboard Integration

`InputText` dynamically selects between `VirtualKeyboard` and `PhysicalKeyboard` based on device capabilities:

```
isTouchDevice or
hasDPad

No touch,
no DPad

InputText.initInputEvents()

Device
capabilities?

Keyboard =
VirtualKeyboard

Keyboard =
PhysicalKeyboard

initTouchEvents()

initDPadEvents()

Physical keyboard
event handlers

InputText:initKeyboard()

self.keyboard =
Keyboard:new{
keyboard_layer = ...
inputbox = self
}
```

**Sources:**[frontend/ui/widget/inputtext.lua232-244](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/inputtext.lua#L232-L244)[frontend/ui/widget/inputtext.lua601-607](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/inputtext.lua#L601-L607)

#### Keyboard Reference Relationship

The relationship between `InputText` and `VirtualKeyboard` is bidirectional:

- `InputText.keyboard` → points to the `VirtualKeyboard` instance
- `VirtualKeyboard.inputbox` → points back to the `InputText` instance

This allows:

- `InputText` to call `keyboard:showKeyboard()`, `keyboard:hideKeyboard()`
- `VirtualKeyboard` keys to call `inputbox:addChar()`, `inputbox:delChar()`, etc.

**Sources:**[frontend/ui/widget/inputtext.lua601-607](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/inputtext.lua#L601-L607)[frontend/ui/widget/virtualkeyboard.lua782-845](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua#L782-L845)

### Password Mode

When `text_type = "password"`, `InputText` displays asterisks instead of actual characters:

```
Yes

No

toggles

charlist =
{'p', 'a', 's', 's'}

show_charlist

text_type =
'password'?

Replace with *
(except last entered)

Use original charlist

TextBoxWidget displays
show_text =
table.concat(show_charlist)

Show password
CheckButton
```

**Sources:**[frontend/ui/widget/inputtext.lua441-472](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/inputtext.lua#L441-L472)[frontend/ui/widget/inputtext.lua474-494](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/inputtext.lua#L474-L494)

### Clipboard Integration

On devices with clipboard support, `InputText` provides hold gesture clipboard operations:

```
UIManager
TextViewer
Device.input (clipboard)
InputText
User
UIManager
TextViewer
Device.input (clipboard)
InputText
User
User sets selection
start and end with
hold gestures
alt
[Copy all]
[Copy line]
[Copy word]
[Paste]
[Select mode]
Hold in text box
getClipboardText()
clipboard_value
Show clipboard dialog
Display clipboard content
Tap "Copy all"
setClipboardText(all_text)
Show notification
Tap "Copy line"
getStringPos()
line start/end positions
setClipboardText(line_text)
Tap "Copy word"
getStringPos(word=true)
word positions
setClipboardText(word_text)
Tap "Paste"
addChars(clipboard_value)
Tap "Select"
do_select = true
```

**Sources:**[frontend/ui/widget/inputtext.lua246-359](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/inputtext.lua#L246-L359)

## InputDialog: Modal Input Dialog

`InputDialog` extends `FocusManager` and wraps `InputText` in a modal dialog with title, description, and button rows. It's the primary widget for user text input throughout KOReader.

### InputDialog Structure

```
Container Hierarchy

Layout Components

InputDialog
(FocusManager)

title_bar
(TitleBar)

VerticalSpan
(before input)

_input_widget
(InputText)

VerticalSpan
(after input)

button_table
(ButtonTable)

VerticalGroup

dialog_frame
(FrameContainer)

movable
(MovableContainer)

CenterContainer
(adjust for keyboard)
```

**Sources:**[frontend/ui/widget/inputdialog.lua203-478](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/inputdialog.lua#L203-L478)

### InputDialog Initialization

`InputDialog` performs complex height calculations to ensure the dialog fits on screen with or without the keyboard:

```
VirtualKeyboard
Real InputText
Dummy InputText
InputDialog
VirtualKeyboard
Real InputText
Dummy InputText
InputDialog
Cleanup dummy widget
alt
[Text fits in available
height]
[Text overflows]
alt
[keyboard_visible]
Calculate available space
new{for_measurement_only=true}
Create text widget
getTextHeight(), getLineHeight()
getKeyboardDimen()
keyboard_height
onCloseWidget()
available_height = screen_height
- border - title - buttons
- keyboard_height
text_height = measured height
text_height = floor(available/
line_height) * line_height
cursor_at_end = false
new{height=text_height, ...}
new{inputbox=self}
keyboard instance
_input_widget
onShowKeyboard()
showKeyboard()
```

**Sources:**[frontend/ui/widget/inputdialog.lua289-396](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/inputdialog.lua#L289-L396)

### Keyboard Visibility Management

`InputDialog` manages keyboard visibility and handles keyboard height changes:

```
true

false

triggers

triggers

onShowKeyboard()

onCloseKeyboard()

toggleKeyboard()

onKeyboardHeightChanged()

_input_widget:
onShowKeyboard()

VirtualKeyboard:
showKeyboard()

keyboard_visible = true

_input_widget:
onCloseKeyboard()

VirtualKeyboard:
hideKeyboard()

keyboard_visible = false

keyboard_visible?

Save current state

reinit()

Restore keyboard visibility
```

**Sources:**[frontend/ui/widget/inputdialog.lua609-647](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/inputdialog.lua#L609-L647)[frontend/ui/widget/inputdialog.lua640-700](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/inputdialog.lua#L640-L700)

### Common InputDialog Usage Patterns

#### Simple Text Input

```
local InputDialog = require("ui/widget/inputdialog")
local UIManager = require("ui/uimanager")
 
local input_dialog
input_dialog = InputDialog:new{
    title = "Enter text",
    input = "default value",
    input_hint = "hint text",
    buttons = {
        {
            {
                text = "Cancel",
                callback = function()
                    UIManager:close(input_dialog)
                end,
            },
            {
                text = "OK",
                is_enter_default = true,
                callback = function()
                    local text = input_dialog:getInputText()
                    -- Process text...
                    UIManager:close(input_dialog)
                end,
            },
        },
    },
}
UIManager:show(input_dialog)
input_dialog:onShowKeyboard()
```

**Sources:** Pattern based on usage in [frontend/apps/filemanager/filemanagersetdefaults.lua92-128](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagersetdefaults.lua#L92-L128)

#### Number Input

```
local input_dialog
input_dialog = InputDialog:new{
    title = "Enter number",
    input = "0",
    input_type = "number",  -- Shows numeric keyboard layer
    buttons = {...},
}
```

**Sources:**[frontend/ui/widget/inputtext.lua601-607](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/inputtext.lua#L601-L607) (keyboard_layer selection)

#### Fullscreen Text Editor

```
local input_dialog
input_dialog = InputDialog:new{
    title = "Edit text",
    input = long_text,
    fullscreen = true,
    condensed = true,
    allow_newline = true,
    cursor_at_end = false,
    add_nav_bar = true,  -- Adds Home/End/Up/Down buttons
    save_callback = function(content, closing)
        -- Save the content
        return true  -- success
    end,
}
```

**Sources:**[frontend/ui/widget/inputdialog.lua46-73](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/inputdialog.lua#L46-L73)

## Multi-Language Support

KOReader's input system supports 30+ languages with specialized keyboard layouts.

### Language Configuration

```
Keyboard Module

Language Settings

false

true

User Action

G_reader_settings
'language'

G_reader_settings
'keyboard_layout'

G_reader_settings
'keyboard_layouts'
(enabled list)

G_reader_settings
'keyboard_layout_default'

G_reader_settings
'keyboard_remember_layout'

VirtualKeyboard.
lang_to_keyboard_layout

getKeyboardLayout()

setKeyboardLayout(lang)

Load keyboard
layout file

User switches layout

init() with new layout
```

**Sources:**[frontend/ui/widget/virtualkeyboard.lua897-904](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua#L897-L904)[frontend/ui/widget/virtualkeyboard.lua906-921](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua#L906-L921)

### Supported Languages

The system includes keyboard layouts for:
Language CodeKeyboard LayoutFeatures`en`EnglishStandard QWERTY`de`GermanUmlauts (ä, ö, ü, ß) on dedicated layer`fr`FrenchAccented characters (é, è, ê, ç)`es`SpanishÑ, accented vowels`ar`ArabicRight-to-left script support`ru`RussianCyrillic alphabet`ja`JapaneseHiragana, Katakana, Kanji input`zh_CN`Simplified ChinesePinyin input with candidates`ko_KR`KoreanHangul input...20+ moreVarious scripts and layouts
**Sources:**[frontend/ui/widget/virtualkeyboard.lua807-844](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua#L807-L844)

### RTL Language Support

For right-to-left languages (Arabic, Hebrew, Persian), KOReader provides:

1. **UI Mirroring**: The entire keyboard layout can be mirrored
2. **Text Direction**: `InputText` supports `para_direction_rtl` and `auto_para_direction`
3. **Bidirectional Text**: XText backend handles mixed LTR/RTL text

```
affects

affects

Language
Module

isLanguageRTL(lang)

languages_rtl = {
ar, fa, he, ur, ...
}

InputText

VirtualKeyboard

para_direction_rtl

auto_para_direction
```

**Sources:**[frontend/ui/language.lua58-93](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/language.lua#L58-L93)

## Physical Keyboard Integration

On devices with physical keyboards, `InputText` responds to key events directly:

### Key Event Handling

```
false

true

No mods

Ctrl

Shift or ScreenKB

Sym (Kindle)

onKeyPress(key)

focused?

Return false
(let event propagate)

Modifiers?

Backspace → delChar()
Del → delChar/delNextChar()
Left/Right → move cursor
Up/Down → move line
Press → addChars('
')
Tab → addChars('    ')

Ctrl+U → delToStartOfLine()
Ctrl+H → delChar()

Shift+Back → delToStartOfLine()
Shift+Del → delWord()
Shift+arrows → move cursor
Shift+Home → toggle KB

Use sym_key_map
for special chars

Return true
(event handled)
```

**Sources:**[frontend/ui/widget/inputtext.lua637-767](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/inputtext.lua#L637-L767)

### Sym Key Mapping (Kindle Keyboards)

Kindle devices with physical keyboards use a Sym modifier for special characters:

```
-- sym_key_map (simplified)
{
    ["Q"] = "!", ["W"] = "?", ["E"] = "-", ["R"] = "_",
    ["A"] = "<", ["S"] = ">", ["D"] = "(", ["F"] = ")",
    ["U"] = "7", ["I"] = "8", ["O"] = "9", ["P"] = "0",
    -- ... more mappings
}
```

**Sources:**[frontend/ui/widget/inputtext.lua628-632](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/inputtext.lua#L628-L632)

## Configuration Options

### User-Configurable Settings
Setting KeyTypeDefaultEffect`keyboard_key_font_size`number22Font size for key labels`keyboard_key_bold`booleanfalseBold key labels`keyboard_key_compact`booleanfalseReduce key height (48px vs 64px)`keyboard_key_border`booleantrueShow borders between keys`flash_keyboard`booleantrueVisual feedback on key press`keyboard_swipes_enabled`booleantrueAllow swipe gestures on keys`keyboard_remember_layout`booleantrueRemember last used layout`keyboard_layout_default`string(from language)Default layout when not remembering`keyboard_layouts`table{"en"}List of enabled layouts for cycling`ges_tap_interval_on_keyboard_ms`number0Custom tap interval override`virtual_keyboard_enabled`booleantrueShow virtual keyboard (vs physical only)
**Sources:**[frontend/ui/widget/virtualkeyboard.lua59-61](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua#L59-L61)[frontend/ui/widget/virtualkeyboard.lua317](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua#L317-L317)[frontend/ui/widget/virtualkeyboard.lua861-866](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua#L861-L866)

### Keyboard Visual Feedback

When `flash_keyboard` is enabled, key presses show visual feedback:

```
UIManager
VirtualKey
User
UIManager
VirtualKey
User
Set inner_bordersize
to focused_bordersize
Wait for screen update
Reset inner_bordersize to 0
alt
[flash_keyboard enabled]
Tap
invert(true)
setDirty(keyboard, "a2")
forceRePaint()
yieldToEPDC()
Execute callback()
invert(false)
forceRePaint()
Execute callback()
```

**Sources:**[frontend/ui/widget/virtualkeyboard.lua400-419](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua#L400-L419)[frontend/ui/widget/virtualkeyboard.lua502-509](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua#L502-L509)

## Summary

The text display and input system in KOReader provides:

1. **Flexible rendering backends**: Classic (simple) and XText (advanced with bidirectional and complex script support)
2. **Rich text layout**: Line wrapping, justification, alignment, image integration
3. **Interactive features**: Text selection, cursor positioning, scrolling
4. **Performance optimizations**: Line caching, measurement-only mode, partial refreshes
5. **Extensive utility functions**: UTF-8 handling, CJK support, line breaking rules

These widgets form the foundation for displaying content throughout KOReader, from dictionary definitions and Wikipedia articles to configuration dialogs and file browser details.

**Key classes:**

- `TextBoxWidget`[frontend/ui/widget/textboxwidget.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/textboxwidget.lua)
- `ScrollTextWidget`[frontend/ui/widget/scrolltextwidget.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/scrolltextwidget.lua)
- `util` module [frontend/util.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua)
- XText library [libs/libkoreader-xtext](https://github.com/koreader/koreader/blob/9e6b1587/libs/libkoreader-xtext)

---

# Text-Display-Widgets

# Text Display Widgets
Relevant source files
- [frontend/apps/reader/modules/readerdictionary.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerdictionary.lua)
- [frontend/apps/reader/modules/readerstyletweak.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerstyletweak.lua)
- [frontend/apps/reader/modules/readerwikipedia.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerwikipedia.lua)
- [frontend/ui/data/css_tweaks.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/data/css_tweaks.lua)
- [frontend/ui/data/settings_migration.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/data/settings_migration.lua)
- [frontend/ui/translator.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/translator.lua)
- [frontend/ui/widget/dictquicklookup.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/dictquicklookup.lua)
- [frontend/ui/widget/footnotewidget.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/footnotewidget.lua)
- [frontend/ui/widget/htmlboxwidget.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/htmlboxwidget.lua)
- [frontend/ui/widget/scrollhtmlwidget.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/scrollhtmlwidget.lua)
- [frontend/ui/widget/scrolltextwidget.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/scrolltextwidget.lua)
- [frontend/ui/widget/textboxwidget.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/textboxwidget.lua)
- [frontend/ui/wikipedia.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/wikipedia.lua)
- [frontend/util.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua)
- [spec/unit/util_spec.lua](https://github.com/koreader/koreader/blob/9e6b1587/spec/unit/util_spec.lua)

## Purpose and Scope

This page documents the text display widgets in KOReader, which are responsible for rendering and presenting text content to users. These widgets handle plain text and HTML rendering, text wrapping, scrolling, text selection, image embedding, and typography features like justification and bidirectional text support.

For information about the base widget system and lifecycle, see [Widget System and Base Classes](/koreader/koreader/7.1-widget-system-and-base-classes). For input-related widgets like text entry fields, see [Input and Virtual Keyboard](/koreader/koreader/7.3-input-and-virtual-keyboard).

## Widget Hierarchy

Text display widgets form a layered architecture where simpler widgets are composed into more complex ones:

```
Supporting Widgets

HTML Widgets

Scrollable Widgets

Core Text Widgets

Base Classes

wrapped by

wrapped by

used by

used by

Widget

WidgetContainer

InputContainer

TextWidget
Simple single-line text

TextBoxWidget
Multi-line with wrapping

ScrollTextWidget
TextBoxWidget + scroll bar

ScrollHtmlWidget
HtmlBoxWidget + scroll bar

HtmlBoxWidget
MuPDF-based HTML rendering

VerticalScrollBar
Visual scroll indicator
```

**Sources:**[frontend/ui/widget/textboxwidget.lua1-300](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/textboxwidget.lua#L1-L300)[frontend/ui/widget/scrolltextwidget.lua1-50](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/scrolltextwidget.lua#L1-L50)[frontend/ui/widget/scrollhtmlwidget.lua1-35](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/scrollhtmlwidget.lua#L1-L35)[frontend/ui/widget/htmlboxwidget.lua1-100](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/htmlboxwidget.lua#L1-L100)

## TextBoxWidget - Core Text Rendering

`TextBoxWidget` is the foundational widget for displaying multi-line text with word wrapping. It handles text layout, line breaking, text selection, cursor positioning, and embedded images.

### Key Features
FeatureDescription**Text Wrapping**Automatic word wrapping based on width, with support for CJK line breaking rules**Rendering Modes**Traditional rendering or XText for advanced typography**Text Selection**Support for touch-based and keyboard-based text selection with highlighting**Editing**Optional cursor and text editing capabilities**Images**Embedded images displayed alongside text**Alignment**Left, center, right, or justified text alignment**Bidirectional Text**RTL/LTR support with auto-detection per paragraph**Poor Text Formatting**Simple inline bold formatting via control characters
### Architecture

```
Selection & Editing

Rendering

Text Processing

TextBoxWidget Core

used by

used by

init()
Initialize face, line height

_computeTextDimensions()
Measure or split text

_updateLayout()
Build widget tree

_evalCharWidthList()
Measure each character

_measureWithXText()
Use XText library

_splitToLines()
Break into lines

paintTo(bb, x, y)
Draw to blitbuffer

drawSingleTextLine()
Render one line

drawCursor()
Draw cursor/selection

getCharPosAtXY(x, y)
Convert coords to char index

moveCursorToCharPos(pos)
Position cursor

updateHighlightedText()
Selection rectangles
```

**Sources:**[frontend/ui/widget/textboxwidget.lua137-330](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/textboxwidget.lua#L137-L330)

### Text Measurement and Line Breaking

`TextBoxWidget` supports two text measurement approaches:

**Traditional Mode** (use_xtext=false):

- Splits text into UTF-8 characters using `util.splitToChars()`
- Measures each distinct character width via `RenderText:sizeUtf8Text()`
- Breaks lines by accumulating character widths until exceeding target width
- Uses `util.isSplittable()` to determine valid break points

**XText Mode** (use_xtext=true, default):

- Delegates to the C++ XText library for better typography
- Provides improved line breaking, especially for CJK and mixed scripts
- Handles bidirectional text more accurately
- Supports tab stops

Sources: [frontend/ui/widget/textboxwidget.lua232-330](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/textboxwidget.lua#L232-L330)[frontend/ui/widget/textboxwidget.lua389-437](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/textboxwidget.lua#L389-L437)

### Text Selection and Highlighting

```
Highlight Logic
TextBoxWidget
User
Highlight Logic
TextBoxWidget
User
Store hold_start_pos
Store hold_end_pos
Hold gesture (touch)
onHoldStartText(pos)
getCharPosAtXY(x, y)
Pan gesture
onHoldPanText(pos)
getCharPosAtXY(x, y)
updateHighlightedText()
Build selection rectangles
Trigger repaint
Release gesture
onHoldReleaseText()
getSelectedText()
Return selected text
```

The widget converts touch coordinates to character positions, then builds a list of rectangles (`highlight_rects`) covering the selected text. These rectangles are drawn with a lightened background color when `highlight_text_selection=true`.

**Sources:**[frontend/ui/widget/textboxwidget.lua882-1046](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/textboxwidget.lua#L882-L1046)[frontend/ui/widget/textboxwidget.lua1313-1440](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/textboxwidget.lua#L1313-L1440)

### Image Support

`TextBoxWidget` can display images alongside text:

```
images = {
    {
        width = 200,     -- display width
        height = 150,    -- display height
        bb = blitbuffer, -- image data (or nil)
        load_bb_func = function(hi_res) ... end, -- lazy loader
        hi_width = 800,  -- optional high-res version
        hi_height = 600,
        hi_bb = nil,
    },
    ...
}
```

Images are positioned at the top-right of each scrolled page. When present, text wrapping adjusts to leave room for the image.

**Sources:**[frontend/ui/widget/textboxwidget.lua81-103](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/textboxwidget.lua#L81-L103)[frontend/ui/widget/textboxwidget.lua341-383](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/textboxwidget.lua#L341-L383)

## ScrollTextWidget - Scrollable Plain Text

`ScrollTextWidget` wraps `TextBoxWidget` and adds vertical scrolling capability with a scroll bar. This is the standard widget for displaying long plain text content.

### Component Structure

```
ScrollTextWidget

HorizontalGroup

TextBoxWidget
(text content)

HorizontalSpan
(spacing)

VerticalScrollBar
(scroll indicator)
```

### Scrolling Interface
MethodPurpose`scrollToRatio(ratio)`Scroll to a position (0.0 = top, 1.0 = bottom)`scrollText(direction)`Scroll by one page (+1 = down, -1 = up)`updateScrollBar(is_partial)`Update scroll bar position and trigger refresh`getVisibleHeightRatios()`Returns (low, high) ratios for visible portion
### Gesture Handling

- **Swipe up/down:** Scroll by one page
- **Tap left/right:** Scroll up/down by one page
- **PgFwd/PgBack keys:** Scroll by one page
- **Pan gestures:** Optional line-by-line scrolling when `scroll_by_pan=true`

**Sources:**[frontend/ui/widget/scrolltextwidget.lua1-270](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/scrolltextwidget.lua#L1-L270)

## ScrollHtmlWidget - Scrollable HTML Content

`ScrollHtmlWidget` provides the same scrolling interface as `ScrollTextWidget`, but wraps `HtmlBoxWidget` for rendering HTML content. It's used extensively in dictionary lookups and Wikipedia article display.

### Usage Pattern

```
ScrollHtmlWidget:new{
    html_body = "<html><body>...</body></html>",
    is_xhtml = false,
    css = "body { margin: 0; }",
    default_font_size = 24,
    width = 600,
    height = 800,
    dialog = parent_dialog,
    highlight_text_selection = true,
    html_link_tapped_callback = function(link) ... end,
}
```

### Key Properties
PropertyTypePurpose`html_body`stringHTML content to render`is_xhtml`booleanWhether content is XHTML (stricter parsing)`css`stringCSS stylesheet to apply`default_font_size`numberBase font size in pixels`html_resource_directory`stringBase path for resolving relative resource URLs`highlight_text_selection`booleanEnable text selection highlighting`html_link_tapped_callback`functionCalled when user taps a link
**Sources:**[frontend/ui/widget/scrollhtmlwidget.lua1-200](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/scrollhtmlwidget.lua#L1-L200)

## HtmlBoxWidget - MuPDF HTML Rendering

`HtmlBoxWidget` renders HTML content using MuPDF's HTML/CSS engine. It handles pagination, text selection within HTML, and link detection.

### Rendering Pipeline

```
Interaction

Display

HtmlBoxWidget

enables

enables

HTML + CSS content

setContent()
Parse HTML and CSS

Create MuPDF document

Layout with font size,
page dimensions

Split into pages

setPageNumber(n)

_render()
Draw page to blitbuffer

Extract text structure
for selection

onTap()
Detect link hits

onHold/Pan/Release
Text selection

getSelectedText()
```

**Sources:**[frontend/ui/widget/htmlboxwidget.lua150-400](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/htmlboxwidget.lua#L150-L400)

### Text Selection in HTML

Unlike `TextBoxWidget`, `HtmlBoxWidget` performs text selection by:

1. Using MuPDF's `getPageText()` to extract a structured representation of text as lines and words
2. Converting touch coordinates to line/word indices via `getWordIndices()`
3. Building selection rectangles spanning the selected words
4. Drawing rectangles during `paintTo()`

This approach works with MuPDF's understanding of the HTML layout, including handling of RTL text and complex layouts.

**Sources:**[frontend/ui/widget/htmlboxwidget.lua42-150](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/htmlboxwidget.lua#L42-L150)

### CSS and Styling

`HtmlBoxWidget` accepts a CSS stylesheet string that MuPDF applies during layout. Common use cases:

- **Dictionary definitions:** Custom CSS per dictionary ([frontend/apps/reader/modules/readerdictionary.lua73-103](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerdictionary.lua#L73-L103))
- **Wikipedia articles:** Default CSS with responsive margins ([frontend/ui/widget/footnotewidget.lua30-112](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/footnotewidget.lua#L30-L112))
- **Footnotes:** Condensed CSS for compact display ([frontend/ui/widget/footnotewidget.lua42-112](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/footnotewidget.lua#L42-L112))

The CSS can control margins, fonts, text alignment, line height, and element display properties.

## Integration with Other Systems

### Dictionary Quick Lookup

`DictQuickLookup` uses both `ScrollTextWidget` and `ScrollHtmlWidget` depending on whether the dictionary provides plain text or HTML definitions:

```
true

false

DictQuickLookup

is_html?

Create ScrollHtmlWidget
with dictionary CSS

Create ScrollTextWidget
with content_face

Display in popup dialog
```

The widget choice is made in `DictQuickLookup:_instantiateScrollWidget()` based on the `is_html` flag from the dictionary metadata.

**Sources:**[frontend/ui/widget/dictquicklookup.lua1256-1356](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/dictquicklookup.lua#L1256-L1356)

### Wikipedia Article Display

Wikipedia articles are always displayed as HTML via `ScrollHtmlWidget`:

1. `Wikipedia:getFullPageHtml()` fetches parsed HTML from the MediaWiki API
2. Images are extracted and embedded using separate HTTP requests
3. CSS is applied to format the article for small screens
4. The result is shown in a `DictQuickLookup` dialog with `is_wiki=true`

**Sources:**[frontend/ui/wikipedia.lua336-344](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/wikipedia.lua#L336-L344)[frontend/apps/reader/modules/readerwikipedia.lua228-301](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerwikipedia.lua#L228-L301)

### Footnote Display

The `FootnoteWidget` uses `ScrollHtmlWidget` to display footnotes at the bottom of the screen:

```
ScrollHtmlWidget:new{
    html_body = footnote_html,
    css = condensed_css,  -- Tight margins and spacing
    default_font_size = doc_font_size,
    height = screen_height / 3,  -- Bottom third of screen
}
```

Custom CSS makes footnotes more compact than regular text to maximize visible document area.

**Sources:**[frontend/ui/widget/footnotewidget.lua180-280](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/footnotewidget.lua#L180-L280)

### Text Selection Callbacks

Both `ScrollTextWidget` and `ScrollHtmlWidget` support text selection callbacks used for dictionary lookup:

```
-- In TextBoxWidget (wrapped by ScrollTextWidget)
onHoldReleaseText = function(text, hold_duration)
    if hold_duration >= 3.0 then
        -- Long hold: switch domain (dict ↔ wiki)
        lookup_wikipedia = not lookup_wikipedia
    end
    
    if lookup_wikipedia then
        self:lookupWikipedia(false, text, ...)
    else
        self.ui:handleEvent(Event:new("LookupWord", text, ...))
    end
end
```

This pattern enables seamless lookup of selected text without leaving the current view.

**Sources:**[frontend/ui/widget/dictquicklookup.lua179-200](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/dictquicklookup.lua#L179-L200)[frontend/ui/widget/textboxwidget.lua1377-1389](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/textboxwidget.lua#L1377-L1389)

## Typography and Text Processing

### Text Splitting and Word Breaking

The `util` module provides helper functions used by text widgets:
FunctionPurpose`util.splitToChars(text)`Split UTF-8 text into array of characters`util.splitToWords(text)`Split text into words and punctuation`util.isSplittable(c, next_c, prev_c)`Check if character is a valid line break point`util.isCJKChar(c)`Detect CJK characters requiring special handling`util.cleanupSelectedText(text)`Trim whitespace from selected text
**Sources:**[frontend/util.lua469-667](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua#L469-L667)

### CJK and Non-Splittable Characters

Special rules prevent line breaks after certain punctuation in CJK text:

```
-- Characters that shouldn't appear at start of line
local cjk_non_splittable_tailers = "!%),.:;?]}¢°·'\"†‡›℃∶、。〃〆..."
 
-- Characters that shouldn't appear at end of line  
local cjk_non_splittable_leaders = "$(£¥·'\"〈《「『【〔〖〝﹙﹛..."
```

The `isSplittable()` function checks these rules to avoid awkward line breaks in Chinese, Japanese, and Korean text.

**Sources:**[frontend/util.lua594-667](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua#L594-L667)

### Right-to-Left Text Support

Both `TextBoxWidget` (via XText) and `HtmlBoxWidget` (via MuPDF) support bidirectional text:

- **Auto-detection:**`auto_para_direction=true` detects paragraph direction from content
- **Explicit direction:**`para_direction_rtl` forces RTL or LTR
- **Alignment:**`alignment_strict=false` inverts alignment for RTL paragraphs

**Sources:**[frontend/ui/widget/textboxwidget.lua108-121](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/textboxwidget.lua#L108-L121)[frontend/ui/widget/scrolltextwidget.lua38-43](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/scrolltextwidget.lua#L38-L43)

## Performance Considerations

### Blitbuffer Caching

`HtmlBoxWidget` caches rendered pages in blitbuffers to avoid re-rendering on scroll:

```
function HtmlBoxWidget:freeBb()
    if self.bb then
        self.bb:free()
        self.bb = nil
    end
end
```

The cached blitbuffer is freed when content changes or the widget is closed.

**Sources:**[frontend/ui/widget/htmlboxwidget.lua400-450](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/htmlboxwidget.lua#L400-L450)

### Lazy Image Loading

Images in `TextBoxWidget` support lazy loading via `load_bb_func`:

```
if image.bb == nil and image.load_bb_func then
    image.load_bb_func(false)  -- Load low-res version
    -- Widget will refresh after loading
end
```

This allows displaying text immediately while images load asynchronously.

**Sources:**[frontend/ui/widget/textboxwidget.lua1780-1850](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/textboxwidget.lua#L1780-L1850)

### Measurement-Only Mode

Widgets can be created with `for_measurement_only=true` to compute text height without triggering repaints:

```
local test_widget = ScrollTextWidget:new{
    text = "z",
    face = font_face,
    for_measurement_only = true,
}
local line_height = test_widget:getLineHeight()
test_widget:free(true)
```

**Sources:**[frontend/ui/widget/textboxwidget.lua645-655](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/textboxwidget.lua#L645-L655)[frontend/ui/widget/scrolltextwidget.lua47-48](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/scrolltextwidget.lua#L47-L48)

---

# Screensaver-System

# Screensaver System
Relevant source files
- [frontend/document/tilecacheitem.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/document/tilecacheitem.lua)
- [frontend/ui/elements/screensaver_menu.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/elements/screensaver_menu.lua)
- [frontend/ui/renderimage.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/renderimage.lua)
- [frontend/ui/screensaver.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/screensaver.lua)
- [frontend/ui/widget/imageviewer.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/imageviewer.lua)
- [frontend/ui/widget/imagewidget.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/imagewidget.lua)
- [frontend/ui/widget/screensaverlockwidget.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/screensaverlockwidget.lua)
- [frontend/ui/widget/screensaverwidget.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/screensaverwidget.lua)
- [frontend/ui/widget/screenshoter.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/screenshoter.lua)
- [frontend/ui/widget/textviewer.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/textviewer.lua)
- [resources/icons/mdlight/triangle.svg](https://github.com/koreader/koreader/blob/9e6b1587/resources/icons/mdlight/triangle.svg)

The screensaver system manages the sleep screen display that appears when the device enters suspend mode. It provides multiple display modes (book covers, custom images, reading progress, status widgets, or messages) with extensive customization options for backgrounds, text overlays, image scaling, and delayed wake-up mechanisms.

For related UI components used by the screensaver, see [Text Display Widgets](/koreader/koreader/7.4-text-display-widgets) and [Image Display Components](/koreader/koreader/7.1-widget-system-and-base-classes).

## Purpose and Scope

The screensaver system serves as the visual interface during device sleep, providing:

- **Multiple display modes**: Book covers, random images, reading progress, book status, custom messages, or as-is screen
- **Customizable overlays**: Text messages with configurable positioning, opacity, and styling
- **Image management**: Random or sequential image selection from folders, with rotation and scaling options
- **Lock mechanisms**: Optional gesture-based or tap-based unlock patterns with delayed wake-up
- **Power integration**: Coordination with device suspend/resume cycles and screen refresh optimization

The system consists of three main components: the core `Screensaver` module for setup and configuration, `ScreenSaverWidget` for event handling and display, and `ScreenSaverLockWidget` for optional gesture-based unlocking.

**Sources:**[frontend/ui/screensaver.lua1-728](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/screensaver.lua#L1-L728)[frontend/ui/widget/screensaverwidget.lua1-94](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/screensaverwidget.lua#L1-L94)[frontend/ui/widget/screensaverlockwidget.lua1-163](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/screensaverlockwidget.lua#L1-L163)

## Display Modes

The screensaver supports seven distinct display modes, each configured via the `screensaver_type` setting:
ModeSetting ValueDescriptionRequirementsBook Cover`"cover"`Shows cover of currently open or last-opened bookValid `lastfile` existsCustom Cover`"document_cover"`Shows specific image or document coverUser-selected file in `screensaver_document_cover`Random Image`"random_image"`Shows random/sequential image from folderFolder path in `screensaver_dir`Reading Progress`"readingprogress"`Shows statistics plugin progress widgetStatistics plugin enabledBook Status`"bookstatus"`Shows BookStatusWidget with metadataDocument currently openAs-Is`"disable"`Leaves screen unchangedNoneMessage Only(with `screensaver_show_message`)Shows only text messageNone
Each mode has automatic fallbacks: if required resources are unavailable (e.g., no book cover found), the system falls back to `random_image` mode, and ultimately to the fallback image `resources/koreader.png`.

**Sources:**[frontend/ui/screensaver.lua346-448](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/screensaver.lua#L346-L448)[frontend/ui/elements/screensaver_menu.lua46-210](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/elements/screensaver_menu.lua#L46-L210)

## System Architecture

```
Support Systems

Configuration

Display Content

Main Components

setup(event)

setup()

show()

show() with gesture

wraps

wraps

wraps

wraps

image_file

ui.statistics

ui

configures

setMessage()

screen_saver_mode=true

screen_saver_lock=true

show/close

show/close

getCoverImage()

refreshFull()

onExitScreensaver

onExitScreensaver

Screensaver
(Module)

ScreenSaverWidget
(InputContainer)

ScreenSaverLockWidget
(InputContainer)

ImageWidget

BookStatusWidget

ReaderProgress Widget

InfoMessage/
TextBoxWidget

G_reader_settings
(screensaver_*)

screensaver_menu.lua

DocSettings
(exclude_screensaver)

Device
(screen_saver_mode,
screen_saver_lock)

UIManager

BookInfo
(getCoverImage)

Screen
```

**Sources:**[frontend/ui/screensaver.lua64-78](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/screensaver.lua#L64-L78)[frontend/ui/widget/screensaverwidget.lua10-94](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/screensaverwidget.lua#L10-L94)[frontend/ui/widget/screensaverlockwidget.lua11-163](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/screensaverlockwidget.lua#L11-L163)

## Configuration System

### Settings Hierarchy

The screensaver uses a hierarchical settings system with defaults, global settings, per-document overrides, and event-specific prefixes:

```
setup(event)

setup()

setup()

Default Values
(lines 28-62)

G_reader_settings
(screensaver_*)

Prefixed Settings
(poweroff_*, reboot_*)

Per-Document
(exclude_screensaver)

Effective Settings
```

Settings are resolved with precedence: prefixed settings (for poweroff/reboot events) > global settings > defaults. Per-document exclusions can override cover display.

**Sources:**[frontend/ui/screensaver.lua28-62](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/screensaver.lua#L28-L62)[frontend/ui/screensaver.lua346-448](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/screensaver.lua#L346-L448)

### Key Settings
SettingTypeDefaultPurpose`screensaver_type`string`"disable"`Display mode selection`screensaver_show_message`boolean`false`Enable text message overlay`screensaver_message`string`"Sleeping"`Custom message text (supports placeholders)`screensaver_img_background`string`"black"`Background fill for image modes`screensaver_msg_background`string`"none"`Background fill for message mode`screensaver_delay`string`"disable"`Wake-up delay: `"disable"`, `"1"`, `"3"`, `"5"`, `"tap"`, `"gesture"``screensaver_stretch_images`boolean`false`Enable image stretching to fit screen`screensaver_stretch_limit_percentage`number`8`Max aspect ratio divergence for stretching`screensaver_rotate_auto_for_best_fit`boolean`false`Auto-rotate images for optimal fit`screensaver_message_vertical_position`number`50`Message position: 0=bottom, 50=center, 100=top`screensaver_message_alpha`number`100`Message container opacity percentage`screensaver_message_container`string`"box"`Container style: `"box"` or `"banner"``screensaver_dir`string`nil`Folder path for random images`screensaver_document_cover`string`nil`Specific image file path`screensaver_cycle_images_alphabetically`boolean`false`Sequential vs random image selection`screensaver_cycle_index`number`0`Current position in sequential cycle`screensaver_max_files`number`256`Max files to scan in random image folder`screensaver_hide_fallback_msg`boolean`false`Hide poweroff/reboot overlay messages
**Sources:**[frontend/ui/screensaver.lua28-62](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/screensaver.lua#L28-L62)[frontend/ui/elements/screensaver_menu.lua1-301](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/elements/screensaver_menu.lua#L1-L301)

### Book Exclusion Rules

The screensaver can exclude certain books from cover display based on document settings and reading status:

```
No

Yes

Yes

No

Yes

Yes

Yes

No

No

No

screensaver_type
== 'cover'

lastfile
exists?

excluded = false

doc_settings:
exclude_screensaver?

excluded = true

Check book
status

screensaver_exclude_
finished_books
& book finished?

screensaver_exclude_
on_hold_books
& book on hold?

screensaver_hide_
cover_in_filemanager
& in FileManager?

Continue

Fallback to
random_image mode

Show cover
```

**Sources:**[frontend/ui/screensaver.lua381-421](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/screensaver.lua#L381-L421)[frontend/ui/screensaver.lua200-208](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/screensaver.lua#L200-L208)

## Lifecycle and State Management

### Setup Phase

The `Screensaver:setup(event, event_message)` method initializes the screensaver state before display:

```
DocumentRegistry
BookInfo
ReaderUI/FileManager
G_reader_settings
Screensaver
Caller
DocumentRegistry
BookInfo
ReaderUI/FileManager
G_reader_settings
Screensaver
Caller
Sets prefix ("poweroff_" or "reboot_")
alt
[Event-specific prefix]
alt
[No cover found]
alt
[Not excluded]
[Excluded]
alt
[Mode is "cover"]
Selects random or sequential image
alt
[Mode is "random_image"]
alt
[Required plugin not
available]
alt
[Mode is
"bookstatus" or
"readingprogress"]
setup(event, event_message)
Get instance
Read screensaver_type
Read background settings
Check "event_screensaver_type"
Read lastfile
Check exclusion rules
getCoverImage()
Fallback to random_image
Fallback to random_image
Read screensaver_dir
_getRandomImage(dir)
Fallback to random_image
Determine effective background
Setup complete
```

**Sources:**[frontend/ui/screensaver.lua346-448](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/screensaver.lua#L346-L448)

### Image Selection Logic

The `_getRandomImage(dir)` function implements two selection strategies:

```
-- Sequential (alphabetical) mode
if G_reader_settings:isTrue("screensaver_cycle_images_alphabetically") then
    -- Find all images, sort naturally
    local files = {}
    util.findFiles(dir, match_func, false, max_files)
    table.sort(files, natsort_cmp())
    
    -- Get next in sequence, wrap around
    local index = (G_reader_settings:readSetting("screensaver_cycle_index", 0) + 1)
    if index > #files then index = 1 end
    G_reader_settings:saveSetting("screensaver_cycle_index", index)
    return files[index]
else
    -- Random mode (default)
    return filemanagerutil.getRandomFile(dir, match_func, max_files)
end
```

**Sources:**[frontend/ui/screensaver.lua85-121](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/screensaver.lua#L85-L121)

### Display Phase

```
UIManager
ScreenSaverLockWidget
ScreenSaverWidget
Screen
Device
Screensaver
UIManager
ScreenSaverLockWidget
ScreenSaverWidget
Screen
Device
Screensaver
Store orig_rotation_mode
alt
[modeExpectsPortrait() and Landscape]
alt
[isSunxi()]
alt
[modeIsImage() and hasEinkScreen]
ImageWidget, BookStatusWidget,
ReaderProgress, or InfoMessage
alt
[show_message]
alt
[overlay_messa-
ge]
alt
[screensaver_delay == "gesture"]
screen_saver_mode = true
Check orig_rotation_mode
setRotationMode(UPRIGHT)
clear()
refreshFull()
usleep(150ms)
Build widget based on mode
Create message overlay
CustomPositionContainer with alpha
addOverlayMessage()
new(widget, background)
Init gesture events
show(screensaver_widget, "full")
new(ui, orig_dimen)
setupGestureEvents()
show(screensaver_lock_widget)
```

**Sources:**[frontend/ui/screensaver.lua450-674](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/screensaver.lua#L450-L674)[frontend/ui/widget/screensaverwidget.lua16-59](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/screensaverwidget.lua#L16-L59)

### Close Phase

The close mechanism supports multiple triggers and optional delays:

```
show()

close() called

screensaver_delay == "disable"

screensaver_delay is number

screensaver_delay == "tap"

screensaver_delay == "gesture"

scheduleIn(delay_seconds)

Timer expires

Any key/tap event

onResume()

Gesture matches config

Return true to caller

close_widget()

onCloseWidget()

OutOfScreenSaver

cleanup()

Displayed

DelayCheck

Immediate

Scheduled

WaitTap

WaitGesture

Delayed

Closing

ShowMessage

CleanupWidget

RestoreRotation

BroadcastEvent

CleanupState

ScreenSaverLockWidget shows
InfoMessage with instructions
```

**Sources:**[frontend/ui/screensaver.lua676-727](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/screensaver.lua#L676-L727)[frontend/ui/widget/screensaverwidget.lua47-79](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/screensaverwidget.lua#L47-L79)[frontend/ui/widget/screensaverlockwidget.lua114-160](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/screensaverlockwidget.lua#L114-L160)

## Widget Hierarchy

The screensaver builds a widget tree depending on the display mode and overlay options:

```
Optional Overlay

Message Widget Detail

Content Branch C: Message Only

Content Branch B: Widget Modes

Content Branch A: Image Modes

cover/random_image

with message

bookstatus

readingprogress

with message

disable + message

is

is

is

box mode

banner mode

with overlay_message

ScreenSaverWidget
(InputContainer, modal)

FrameContainer
(fullscreen, background)

ImageWidget

OverlapGroup

CustomPositionContainer
(message widget)

BookStatusWidget

ReaderProgress Widget

OverlapGroup

CustomPositionContainer
(message widget)

CustomPositionContainer
(message widget)

CustomPositionContainer
(vertical_position, alpha)

InfoMessage.movable
(container='box')

TextBoxWidget
(container='banner')

RightContainer
(OverlapGroup with event_message)
```

The `ScreenSaverWidget` is always the top-level container. When gesture unlock is enabled, `ScreenSaverLockWidget` is added as a separate modal widget on top of the stack, not as a child of `ScreenSaverWidget`.

**Sources:**[frontend/ui/screensaver.lua504-662](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/screensaver.lua#L504-L662)[frontend/ui/widget/screensaverwidget.lua16-38](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/screensaverwidget.lua#L16-L38)

## Image Rendering Pipeline

Image display modes (`cover`, `document_cover`, `random_image`) use `ImageWidget` with specific configurations:

```
Rendering Path

ImageWidget Configuration

Image Source

image_file
(path)

image
(BlitBuffer)

width, height
(screen dimensions)

scale_factor
(0 or nil)

stretch_limit_percentage

rotation_angle

alpha = true

ImageWidget:_render()

_loadfile() or _loadimage()

Apply rotation_angle

Scale/stretch operation

ImageCache (for files)

Final BlitBuffer
```

### Rotation Logic

Images can be auto-rotated for best fit when `screensaver_rotate_auto_for_best_fit` is enabled:

```
if G_reader_settings:isTrue("screensaver_rotate_auto_for_best_fit") then
    local angle = rotation_mode == 3 and 180 or 0  -- Match device orientation
    
    -- Check if image and screen orientations differ
    if (image:getWidth() < image:getHeight()) ~= (screen_w < screen_h) then
        -- Landscape image on portrait screen, or vice versa
        local invert = G_reader_settings:isTrue("imageviewer_rotation_landscape_invert")
        angle = angle + (invert and -90 or 90)
    end
    
    widget_settings.rotation_angle = angle
end
```

**Sources:**[frontend/ui/screensaver.lua517-540](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/screensaver.lua#L517-L540)[frontend/ui/widget/imagewidget.lua254-377](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/imagewidget.lua#L254-L377)

### Scale Factor Resolution

The `ImageWidget` scale factor is determined by the `screensaver_stretch_images` and `stretch_limit_percentage` settings:
Configurationscale_factor ValueBehavior`stretch_images=false``0`Scale to fit, maintain aspect ratio`stretch_images=true`, no limit`nil`Stretch to fill width×height`stretch_images=true`, with limit`0` if ratio divergence > limit, else `nil`Conditional stretch based on aspect ratio similarity
**Sources:**[frontend/ui/screensaver.lua507-512](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/screensaver.lua#L507-L512)[frontend/ui/widget/imagewidget.lua310-344](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/imagewidget.lua#L310-L344)

## Lock and Unlock Mechanisms

### ScreenSaverLockWidget

When `screensaver_delay == "gesture"`, the `ScreenSaverLockWidget` provides gesture-based unlocking:

```
Gesture Cloning Detail

Yes

No

Yes, first

Yes, subsequent

No

ScreenSaverLockWidget:init()

setupGestureEvents()

Clone gestures from
ui.gestures.gestures

screensaver_delay
== 'gesture'?

Register simple Tap handler

For each gesture
with 'exit_screensaver' action

Create handler that calls
ui_gesture.handler()

Handler emits
ExitScreensaver event

gesture ==
'multiswipe'?

Add to self.ges_events

Skip (already added)

Create onTriggerExitScreensaver_X()

Calls ui_gesture.handler(ev)
```

The widget clones configured `exit_screensaver` gestures from `ReaderUI` or `FileManager` because it sits at the top of the UI stack and would otherwise block those gestures from reaching the original handlers.

**Sources:**[frontend/ui/widget/screensaverlockwidget.lua39-90](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/screensaverlockwidget.lua#L39-L90)

### Resume/Suspend Handling

The lock widget manages the `Device.screen_saver_lock` flag and displays instructions on resume:

```
InfoMsg
Screen
Device
ScreenSaverLockWidget
PowerManager
InfoMsg
Screen
Device
ScreenSaverLockWidget
PowerManager
Device resumes from suspend
alt
[screensaver_show_exit_message != false]
User performs exit gesture
Device suspends again
alt
[is_infomessage_visible]
onResume()
screen_saver_lock = true
Get exit message
new(text)
paintTo(Screen.bb)
refreshFull()
is_infomessage_visible = true
onExitScreensaver()
onClose()
onSuspend()
screen_saver_lock = false
setDirty("all", "full")
is_infomessage_visible = false
```

The `screen_saver_lock` flag tells `Device` that subsequent power button presses should suspend again rather than resume, allowing the device to remain locked until the correct gesture is performed.

**Sources:**[frontend/ui/widget/screensaverlockwidget.lua92-160](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/screensaverlockwidget.lua#L92-L160)[frontend/ui/widget/screensaverwidget.lua81-92](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/screensaverwidget.lua#L81-L92)

## Device Integration

### Power Management Flags

The screensaver system uses two `Device` flags to coordinate with power management:
FlagSet ByPurpose`Device.screen_saver_mode``Screensaver:show()`Indicates screensaver is displayed; prevents `Device:onPowerEvent()` from treating power button as resume`Device.screen_saver_lock``ScreenSaverLockWidget:onResume()`Indicates gesture lock is active; makes power button send back to suspend`Device.orig_rotation_mode``Screensaver:show()`Stores original rotation to restore on close
```

```

**Sources:**[frontend/ui/screensaver.lua454-455](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/screensaver.lua#L454-L455)[frontend/ui/widget/screensaverwidget.lua81-92](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/screensaverwidget.lua#L81-L92)[frontend/ui/widget/screensaverlockwidget.lua143-154](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/screensaverlockwidget.lua#L143-L154)

### Screen Refresh Optimization

On e-ink devices, the screensaver optimizes refresh modes based on display mode:

```
-- For image modes on e-ink, flash to white first to eliminate ghosting
if Device:hasEinkScreen() and self:modeIsImage() then
    if self:withBackground() then
        Screen:clear()
    end
    Screen:refreshFull(0, 0, screen_w, screen_h)
    
    -- On Kobo sunxi, add delay to avoid refresh glitches
    if Device:isKobo() and Device:isSunxi() then
        ffiUtil.usleep(150 * 1000)  -- 150ms
    end
end
```

The final screensaver display uses `"full"` refresh mode to ensure clean rendering, and `dithered = true` for optimal e-ink display quality.

**Sources:**[frontend/ui/screensaver.lua488-498](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/screensaver.lua#L488-L498)[frontend/ui/screensaver.lua661-662](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/screensaver.lua#L661-L662)

## Message System

### Message Placeholders

Custom screensaver messages support placeholder expansion via `BookInfo:expandString()`. Available placeholders include:

- `%c`: Current page number
- `%t`: Total pages
- `%p`: Current percentage
- `%T`: Title
- `%A`: Authors
- `%S`: Series
- `%b`: Book progress information
- Plus date/time formats

**Sources:**[frontend/ui/screensaver.lua581-582](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/screensaver.lua#L581-L582)[frontend/ui/screensaver.lua210-255](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/screensaver.lua#L210-L255)

### Message Positioning

The message overlay uses `CustomPositionContainer` with configurable vertical position and alpha:

```
-- vertical_percentage: 0=bottom, 50=middle, 100=top
local message_widget = CustomPositionContainer:new{
    widget = content_widget,  -- InfoMessage or TextBoxWidget
    vertical_position = 1 - (vertical_percentage / 100),  -- Inverted for CustomPositionContainer
    alpha = alpha_value / 100,  -- Convert percentage to 0.0-1.0
}
```

Two container styles are supported:

- `"box"`: Uses `InfoMessage:new()` with dismissable=false, creating a framed box
- `"banner"`: Uses `TextBoxWidget:new()` with alignment="center", creating a full-width banner

**Sources:**[frontend/ui/screensaver.lua596-620](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/screensaver.lua#L596-L620)

### Overlay Messages

For poweroff/reboot events, an additional overlay can be added using `addOverlayMessage()`:

```
Base Widget
(ImageWidget or other)

OverlapGroup

RightContainer

FrameContainer
(white background)

TextWidget or
TextBoxWidget

VerticalSpan
(if widget_height > 0)

Positioned at right edge
Below base widget if at top
```

This creates a white-background overlay message (e.g., "Rebooting..." or "Powering off...") that distinguishes special events from normal suspend.

**Sources:**[frontend/ui/screensaver.lua123-176](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/screensaver.lua#L123-L176)[frontend/ui/screensaver.lua645-647](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/screensaver.lua#L645-L647)

## Event Flow

### Complete Event Sequence

```
UIManager
Device
ScreenSaverLockWidget
ScreenSaverWidget
Screensaver
PowerManager/UIManager
UIManager
Device
ScreenSaverLockWidget
ScreenSaverWidget
Screensaver
PowerManager/UIManager
Suspend requested
alt
[Gesture lock enabled]
Device suspends
Device resumes
delayed_close = true
Wait...
alt
[screensaver_delay is number]
Wait for gesture...
alt
[screensaver_delay == "gesture"]
Wait for input...
alt
[screensaver_delay == "tap"
or "disable"]
setup()
Load settings, select mode
Load image/create widgets
show()
screen_saver_mode = true
new(widget, background)
show(screensaver_widget)
new(ui, orig_dimen)
show(screensaver_lock_widget)
close()
scheduleIn(delay, close_widget)
close_widget()
onResume()
screen_saver_lock = true
showWaitForGestureMessage()
onExitScreensaver()
close(self)
onClose()
onTap() or onAnyKeyPressed()
onClose()
close(self)
onCloseWidget()
Restore orig_rotation_mode
setDirty("all", "full")
broadcastEvent(OutOfScreenSaver)
cleanup()
screen_saver_mode = false
screen_saver_lock = false
```

**Sources:**[frontend/ui/screensaver.lua346-727](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/screensaver.lua#L346-L727)[frontend/ui/widget/screensaverwidget.lua40-79](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/screensaverwidget.lua#L40-L79)[frontend/ui/widget/screensaverlockwidget.lua92-160](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/screensaverlockwidget.lua#L92-L160)

---

This documentation covers the screensaver system's architecture, configuration, display modes, widget hierarchy, image rendering, lock mechanisms, and device integration. The system provides a flexible sleep screen interface with extensive customization options while maintaining coordination with device power management and screen refresh optimization for e-ink displays.

---

# Utilities-and-Content-Enhancement

# Utilities and Content Enhancement
Relevant source files
- [frontend/apps/filemanager/filemanagersetdefaults.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagersetdefaults.lua)
- [frontend/apps/reader/modules/readerdictionary.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerdictionary.lua)
- [frontend/apps/reader/modules/readerstyletweak.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerstyletweak.lua)
- [frontend/apps/reader/modules/readerwikipedia.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerwikipedia.lua)
- [frontend/ui/data/css_tweaks.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/data/css_tweaks.lua)
- [frontend/ui/data/settings_migration.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/data/settings_migration.lua)
- [frontend/ui/elements/screen_dpi_menu_table.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/elements/screen_dpi_menu_table.lua)
- [frontend/ui/language.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/language.lua)
- [frontend/ui/translator.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/translator.lua)
- [frontend/ui/widget/dictquicklookup.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/dictquicklookup.lua)
- [frontend/ui/widget/footnotewidget.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/footnotewidget.lua)
- [frontend/ui/widget/htmlboxwidget.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/htmlboxwidget.lua)
- [frontend/ui/widget/inputdialog.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/inputdialog.lua)
- [frontend/ui/widget/inputtext.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/inputtext.lua)
- [frontend/ui/widget/multiinputdialog.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/multiinputdialog.lua)
- [frontend/ui/widget/scrollhtmlwidget.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/scrollhtmlwidget.lua)
- [frontend/ui/widget/scrolltextwidget.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/scrolltextwidget.lua)
- [frontend/ui/widget/textboxwidget.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/textboxwidget.lua)
- [frontend/ui/widget/virtualkeyboard.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua)
- [frontend/ui/wikipedia.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/wikipedia.lua)
- [frontend/util.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua)
- [spec/unit/util_spec.lua](https://github.com/koreader/koreader/blob/9e6b1587/spec/unit/util_spec.lua)

## Purpose and Scope

This document covers KOReader's utility libraries and content enhancement services used throughout the application. These cross-cutting concerns include:

- The `util` module with string, table, and file operations
- Dictionary and Wikipedia lookup integration
- Text translation services
- Virtual keyboard and text input components
- Text display widgets (plain text and HTML)
- Localization and right-to-left language support
- CSS-based content tweaking for documents

For information about the widget framework and UI components, see [User Interface Components](/koreader/koreader/7-user-interface-components). For document rendering and format support, see [Document System](/koreader/koreader/5-document-system).

## Core Utility Functions

The `util` module ([frontend/util.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua)) provides foundational helper functions used throughout the codebase for string manipulation, table operations, and file system access.

### String Operations

```
UTF-8 Processing

Text Splitting

Text Cleaning

util.stripPunctuation()

util.cleanupSelectedText()

util.trim()
util.ltrim()
util.rtrim()

util.gsplit()
Pattern-based split

util.splitToChars()
UTF-8 character array

util.splitToWords()
CJK-aware

UTF8_CHAR_PATTERN

WTF-8 surrogate handling

util.unicodeCodepointToUtf8()
```

**Text Cleaning**: The module provides several text cleaning functions. `util.stripPunctuation()` removes punctuation marks around text including ASCII and Unicode punctuation (U+2000-U+206F ranges). `util.cleanupSelectedText()` is specialized for text selection, trimming spaces and newlines while collapsing consecutive whitespace.

**Pattern-Based Splitting**: The `util.gsplit()` function splits strings by pattern while optionally capturing both delimiters and split strings. This is more powerful than simple string.gmatch() as it can return delimiters alongside the split content without manual position tracking.

**UTF-8 Character Handling**: Text is split into UTF-8 characters using the `UTF8_CHAR_PATTERN` ([frontend/util.lua456](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua#L456-L456)). The splitter handles WTF-8 (a superset of UTF-8 that includes UTF-16 surrogates), converting surrogate pairs (0xD800-0xDBFF followed by 0xDC00-0xDFFF) into proper Unicode codepoints.

Sources: [frontend/util.lua23-520](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua#L23-L520)

### CJK Text Support

```
Text Processing

Line Breaking

CJK Detection

util.isCJKChar()

Unicode Ranges
0x1100-0x3134F

Hangul, CJK Unified
Ideographs, Extensions

util.isSplittable()

cjk_non_splittable_leaders
Opening brackets, quotes

cjk_non_splittable_tailers
Closing brackets, punctuation

util.splitToWords()

Each CJK char is a word

Mixed CJK/Latin handling
```

**Character Detection**: The `util.isCJKChar()` function checks if a character falls within CJK Unicode ranges covering Hangul Jamo, CJK Unified Ideographs (including extensions B-G), and related blocks. The function uses early-exit optimization, immediately returning false for characters below U+1100.

**Line Breaking Rules**: The `util.isSplittable()` function implements East Asian line breaking rules per Unicode standards. It prevents line breaks before certain punctuation marks (closing brackets, commas, periods) and after opening punctuation (opening brackets, quotes). Rules are defined in lookup tables `cjk_non_splittable_leaders` and `cjk_non_splittable_tailers`.

**Word Splitting**: For CJK text, `util.splitToWords()` treats each character as a potential word boundary, whereas Latin text is split at whitespace and punctuation. Mixed CJK/Latin text is handled by detecting CJK characters and splitting accordingly.

Sources: [frontend/util.lua525-667](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua#L525-L667)[frontend/util.lua594-628](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua#L594-L628)

### Table and Array Operations

The module provides comprehensive table manipulation functions:
FunctionPurposeLines`util.tableEquals()`Deep comparison with optional metatable checking[146-175](https://github.com/koreader/koreader/blob/9e6b1587/146-175)`util.tableDeepCopy()`Creates copies avoiding circular references[184-202](https://github.com/koreader/koreader/blob/9e6b1587/184-202)`util.tableSize()`Counts keys in table[207-211](https://github.com/koreader/koreader/blob/9e6b1587/207-211)`util.arrayAppend()`Appends array t2 to t1[262-266](https://github.com/koreader/koreader/blob/9e6b1587/262-266)`util.arrayRemove()`Filters array in-place with callback[286-304](https://github.com/koreader/koreader/blob/9e6b1587/286-304)`util.arrayReverse()`Reverses array in-place[308-315](https://github.com/koreader/koreader/blob/9e6b1587/308-315)`util.bsearch()`Binary search in sorted array[369-385](https://github.com/koreader/koreader/blob/9e6b1587/369-385)`util.bsearch_left()`Leftmost insertion point[391-405](https://github.com/koreader/koreader/blob/9e6b1587/391-405)`util.bsearch_right()`Rightmost insertion point[411-425](https://github.com/koreader/koreader/blob/9e6b1587/411-425)
**Binary Search Variants**: Three binary search implementations serve different needs - `bsearch()` finds exact matches, while `bsearch_left()` and `bsearch_right()` return insertion points for maintaining sorted arrays.

Sources: [frontend/util.lua146-435](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua#L146-L435)

### File System Utilities

```
System Info

File Operations

Directory Operations

util.findFiles()
Recursive scan

util.makePath()
mkdir -p

util.removePath()
Remove empty dirs

util.isEmptyDir()

util.getSafeFilename()
Sanitize names

util.pathExists()

util.fileExists()

util.directoryExists()

util.getFilesystemType()
Parse /proc/mounts

util.calcFreeMem()
Parse /proc/meminfo

util.diskUsage()
Run df command
```

**Safe Filename Generation**: The `util.getSafeFilename()` function sanitizes filenames for cross-platform compatibility. It replaces invalid characters (`\/:*?"<>|`) with underscores, trims trailing dots and spaces, and enforces length limits (default 240 chars for filename, 10 for extension). On Android or VFAT filesystems, stricter validation is applied.

**Directory Creation**: `util.makePath()` creates directories recursively like `mkdir -p`, handling both absolute and relative paths. It returns true on success or nil with error message on failure.

**Memory Calculation**: `util.calcFreeMem()` parses `/proc/meminfo` to compute available memory. It prioritizes the `MemAvailable` field if present, falling back to calculating `MemFree + Buffers + Cached`. Returns 85% of calculated value for safety margin.

Sources: [frontend/util.lua674-951](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua#L674-L951)

## Dictionary and Wikipedia Integration

KOReader integrates dictionary lookup and Wikipedia article retrieval through a unified interface, allowing users to look up selected text without leaving their document.

### Dictionary System Architecture

```
Settings

Query Execution

Dictionary Metadata

ReaderDictionary Module

ReaderDictionary

init()
Scan dictionary dirs

onLookupWord()

cleanSelection()

.ifo files
StarDict format

available_ifos[]
name, is_html, lang

*.css custom styles

*.lua fix_html_func

sdcv CLI
StarDict client

Trapper:dismissablePopen()
Interruptible

Parse JSON/plain output

tidyMarkup()

dicts_order
User preference

dicts_disabled

preferred_dictionaries
Per-book

enabled_dict_names[]
```

**Dictionary Discovery**: On initialization, `ReaderDictionary:init()` scans `STARDICT_DATA_DIR` (default `{DATA_DIR}/dict`) and `{DATA_DIR}/dict_ext` for `.ifo` files using recursive `getIfosInDir()`. Each dictionary is stored in the `available_ifos` table with:

- `file` - path to .ifo file
- `name` - from `bookname=` field
- `is_html` - whether output is HTML (from `sametypesequence=h`)
- `lang` - input/output language codes (from `lang=` field)
- `css` - optional custom stylesheet from parallel `.css` file
- `fix_html_func` - optional HTML cleanup function from parallel `.lua` file

**Dictionary Ordering**: Users can reorder dictionaries via `showDictionariesMenu()` which displays a `SortWidget`. The order is persisted in `G_reader_settings:readSetting("dicts_order")` and applied by `sortAvailableIfos()` using either explicit position or lexical comparison.

**Per-Book Priority**: The `showPreferredDictsDialog()` method displays a `ButtonDialog` where users select preferred dictionaries for the current book. Selected dictionaries show circled numbers (①②③...) via Unicode codepoints U+2460-2473. These are stored in `self.preferred_dictionaries` and inserted first when building `enabled_dict_names`.

Sources: [frontend/apps/reader/modules/readerdictionary.lua39-230](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerdictionary.lua#L39-L230)

### Dictionary Lookup Flow

```
DictQuickLookup
sdcv
Trapper
ReaderDictionary
ReaderHighlight
User
DictQuickLookup
sdcv
Trapper
ReaderDictionary
ReaderHighlight
User
Execute with -u options
for enabled dicts
Select text
onLookupWord(word, is_sane, boxes)
cleanSelection()
Strip punctuation, clean
wrap(function)
dismissablePopen(sdcv ...)
JSON or plain text
Parse results
tidyMarkup()
Clean HTML
new{results, word}
Show popup
```

**Selection Cleaning**: The `cleanSelection()` method processes the selected text by:

1. Stripping punctuation via `util.stripPunctuation()`
2. Normalizing Unicode (NFD → NFC)
3. Applying `util.cleanupSelectedText()` to trim whitespace
4. Limiting length to 512 characters

**SDCV Invocation**: The `stardictLookup()` method builds an sdcv command with multiple `-u "dictionary_name"` options for enabled dictionaries. The command is executed via `Trapper:dismissablePopen()` to allow user interruption. Output format is JSON if supported, otherwise plain text.

**HTML Cleanup**: For HTML dictionaries, `tidyMarkup()` performs cleanup:

- Fixes common HTML issues (unclosed tags, malformed attributes)
- Applies dictionary-specific `fix_html_func` if defined
- Ensures valid XHTML for MuPDF rendering

**Result Display**: Results are passed to `DictQuickLookup` widget with metadata including dictionary name, whether HTML, custom CSS, and language information.

Sources: [frontend/apps/reader/modules/readerdictionary.lua587-981](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerdictionary.lua#L587-L981)

### Wikipedia Integration

```
Processing

API Query Types

Wikipedia Class

ReaderWikipedia Module

ReaderWikipedia
extends ReaderDictionary

onLookupWikipedia()

wiki_languages[]
User-configured

wiki_last_language

Wikipedia

wiki_server
https://{lang}.wikipedia.org

wiki_path
/w/api.php

wiki_search_params
action=query&generator=search

wiki_full_params
action=query&prop=extracts

wiki_phtml_params
action=parse&prop=text

wiki_images_params
action=parse (images only)

getUrlContent()

JSON parsing

prettifyText()

extractWikipediaImages()

createEpub()
```

**Language Configuration**: Users configure Wikipedia languages in Settings → Dictionary settings → Wikipedia settings. The language list is stored in `G_reader_settings:readSetting("wikipedia_languages")`. If not set, the system uses UI language and document language as hints.

**API Endpoints**: The `Wikipedia` class defines four query types as module constants:

- `WIKIPEDIA_INTRO` (1) - Search results using `generator=search` with `prop=extracts|info|pageimages`
- `WIKIPEDIA_FULL` (2) - Full article text using `prop=extracts|pageimages|langlinks`
- `WIKIPEDIA_PHTML` (3) - Full HTML using `action=parse` with `prop=text|tocdata|displaytitle|revid`
- `WIKIPEDIA_IMAGES` (4) - HTML for image extraction using `action=parse` with `prop=text`

**Network Layer**: The `getUrlContent()` function handles HTTP requests with:

- Configurable timeout and maxtime
- Cookie extraction from `set-cookie` headers
- Cookie reuse for image requests (better rate limiting)
- Socket timeout handling and error reporting

**Text Prettification**: For full articles, `prettifyText()` enhances formatting:

- Adds section spacing and bullet points
- Formats Wikipedia-specific markers (== Section ==)
- Improves list formatting and indentation
- Trims excessive newlines

**EPUB Export**: The `createEpub()` method generates EPUB files:

1. Fetches article HTML and images
2. Downloads and embeds all images
3. Creates EPUB structure (OEBPS, META-INF, mimetype)
4. Generates navigation (NCX, nav.xhtml)
5. Packages into ZIP format

Sources: [frontend/ui/wikipedia.lua1-876](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/wikipedia.lua#L1-L876)[frontend/apps/reader/modules/readerwikipedia.lua1-420](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerwikipedia.lua#L1-L420)

### DictQuickLookup Widget

```
Features

Navigation

Content Rendering

Widget Layout

DictQuickLookup

TitleBar
dict_title

Lookup word
lookup_word_text

Content area

ButtonTable
button_table

ScrollTextWidget
Plain text mode

ScrollHtmlWidget
HTML mode

images[]
Lazy-loaded

◁◁ Previous dict

▷▷ Next dict

Wikipedia / Full article

Search / Language selector

Text selection
HoldReleaseText

HTML link tapping

Fullpage mode

Save as EPUB
(Wikipedia)

Temporary large window
spread gesture
```

**Content Display**: The widget chooses between `ScrollTextWidget` (plain text) and `ScrollHtmlWidget` (HTML) based on the `is_html` flag from dictionary metadata. The choice is made in `changeDictionary()` which rebuilds the content widget.

**Dictionary Switching**: Navigation buttons use `onChangeToPrevDict()` and `onChangeToNextDict()` methods. Hold gestures jump to first/last dictionary via `changeToFirstDict()` and `changeToLastDict()`.

**Text Selection for Nested Lookup**: The `HoldReleaseText` gesture handler allows selecting text within definitions for nested lookups. Hold duration determines lookup domain:

- < 3 seconds: same domain (dict→dict or wiki→wiki)
- ≥ 3 seconds: switch domain (dict→wiki or wiki→dict)

**Image Lazy-Loading**: Wikipedia images use lazy-loading via `load_bb_func` callbacks. When an image scrolls into view, its callback is invoked to fetch and decode the image. This prevents blocking the UI on initial display.

**Window Stacking**: Multiple `DictQuickLookup` windows can be stacked, tracked via the static `window_list` class variable. The `onHoldClose()` method can close all stacked windows simultaneously.

**Temporary Large Window**: A spread gesture sets `DictQuickLookup.temp_large_window_request = {is_large_window = true}` to toggle fullscreen mode. The next opened dictionary window reads this request and expands to full screen.

Sources: [frontend/ui/widget/dictquicklookup.lua1-1500](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/dictquicklookup.lua#L1-L1500)

## Translation Services

The `Translator` module provides text translation using Google Translate's public API endpoint.

### Translation System

```
UI Integration

API

Translator Module

Input

Text to translate

Source language
('auto' for detect)

Target language

checkLanguageSupport()

Build API URL

HTTP GET request

Parse JSON response

translate.googleapis.com
/translate_a/single

client=gtx
sl={src}&tl={tgt}
dt=t&q={text}

DictQuickLookup

Translate button
in Results menu

Show translation
```

**Language Support**: The module supports 249 languages as of 2024, defined in `SUPPORTED_LANGUAGES` table with localized names. Language codes include both standard ISO codes (e.g., "en", "fr") and extended codes (e.g., "zh_CN", "zh_TW").

**API Integration**: The `translate()` method constructs URLs in format:

```
https://translate.googleapis.com/translate_a/single?client=gtx&sl=<source>&tl=<target>&dt=t&q=<text>

```

The `client=gtx` parameter uses Google Translate's public API endpoint.

**Response Parsing**: The API returns JSON with structure `[[[translated_text, original_text, null, null, 3]], null, "detected_language"]`. The parser extracts the translation from `response[1][1][1]` and detected language from `response[3]`.

**Language Detection**: When source language is "auto", Google Translate automatically detects the language. The detected language is returned in the response and displayed to the user.

**Error Handling**: The module checks for language support before making requests via `checkLanguageSupport()`. Network errors display appropriate InfoMessages.

**UI Integration**: Translation is accessible from `DictQuickLookup`'s Results menu (hamburger icon). The menu item appears when `ui.translator:isTranslationAvailable()` returns true.

Sources: [frontend/ui/translator.lua1-500](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/translator.lua#L1-L500)[frontend/ui/widget/dictquicklookup.lua1040-1113](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/dictquicklookup.lua#L1040-L1113)

## Virtual Keyboard and Text Input

KOReader provides comprehensive text input through an on-screen virtual keyboard and associated text entry widgets.

### Virtual Keyboard Architecture

```
Input Integration

Key Types

VirtualKey Widget

VirtualKeyboard Widget

VirtualKeyboard
extends WidgetContainer

Current layout
(en, fr, de, etc.)

Current layer
(default, Shift, Sym, Äéß)

Grid of VirtualKey widgets

VirtualKey
extends InputContainer

Label or icon

callback function

hold_callback

swipe_callback

VirtualKeyPopup
(alternatives)

Character keys

Special keys
(Shift, Sym, ←→↑↓)

Space, Enter, Del

🌐 Layout switcher

inputbox
(InputText widget)

keyboard:addChar()

keyboard:delChar()

leftChar(), rightChar()
upLine(), downLine()
```

**Keyboard Layouts**: The virtual keyboard supports multiple language layouts stored in `frontend/ui/data/keyboardlayouts/` as Lua tables. Users select enabled layouts via Settings → Text settings → Virtual keyboard → Manage keyboard layouts. The active layout is stored in `G_reader_settings:readSetting("keyboard_layout")`.

**Layout Switching**: Tapping the 🌐 key cycles through enabled layouts. The `utf8mode_keys` callback invokes `setKeyboardLayout()` to switch. Holding the key shows a `VirtualKeyPopup` with all layouts (if 2+ enabled) or opens `KeyboardLayoutDialog` for configuration.

**Layers**: Each layout defines multiple layers accessed via modifier keys:

- Default layer: lowercase letters and basic symbols
- Shift layer: uppercase letters, accessed via shift key
- Sym layer: numbers and symbols, accessed via Sym key
- Äéß layer: accented characters (some layouts), accessed via Äéß key

The shift key behavior varies - standard shift is `release_shift=true` (releases after one character), while capslock is `release_shift=false`.

**Key Actions**: Each `VirtualKey` widget supports three interaction modes:

- `onTapSelect()` - executes `callback`
- `onHoldSelect()` - executes `hold_callback` (often shows popup)
- `onSwipeKey()` - executes `swipe_callback` with direction

**Character Alternatives**: Many keys define `key_chars` table with alternatives in different swipe directions (north, south, east, west, northeast, etc.). Holding shows these in a `VirtualKeyPopup` grid.

**Bidirectional Reference**: The keyboard stores reference to its input field in `self.inputbox`, and the input field stores keyboard reference in `self.keyboard`. This enables tight integration for operations like adding/deleting characters.

Sources: [frontend/ui/widget/virtualkeyboard.lua1-1100](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua#L1-L1100)

### InputText Widget

```
Clipboard

Cursor Movement

Editing Operations

Display

InputText Storage

text (string)

charlist (UTF-8 char table)

charpos (cursor index)

top_line_num (scroll pos)

FrameContainer
border and focus

TextBoxWidget or
ScrollTextWidget

LineWidget
(cursor indicator)

addChar(char)

addChars(string)

delChar()

delWord()

delToStartOfLine()

leftChar()

rightChar()

upLine()

downLine()

goToStartOfLine()

goToEndOfLine()

selection_start_pos

Copy to clipboard

Paste from clipboard
```

**Character Storage**: Text is stored in two forms:

- `self.text` - the original string
- `self.charlist` - array of UTF-8 characters via `util.splitToChars()`

This dual storage enables efficient character-level operations while maintaining the original string. The cursor position `self.charpos` is an index into `charlist` (can be 1 to #charlist+1).

**Display Widget Selection**: `InputText` uses different widgets based on configuration:

- `TextBoxWidget` - when height is specified (fixed size)
- `ScrollTextWidget` - when `scroll=true` or height is nil (scrollable)

The choice is made in `initTextBox()` which is called during `init()` and whenever text is modified.

**Editing Operations**:

- `addChar()` - inserts at cursor position by splicing into charlist
- `delChar()` - removes character before cursor
- `delWord()` - deletes backward to word boundary or forward from cursor
- `delToStartOfLine()` - deletes from cursor to line start

Each operation modifies `charlist`, updates `charpos`, regenerates `self.text` via `table.concat(charlist)`, and calls `initTextBox()` to rebuild the display widget.

**UTF-8 Safety**: The `checkTextEditability()` method verifies that text is reversible (concat(splitToChars(text)) == text). This prevents corruption when editing binary content. When not editable, operations display a warning notification.

**Clipboard Integration**: On devices with clipboard support, long-press shows a dialog with options:

- Copy all / Copy line / Copy word
- Delete all
- Select (for range selection)
- Paste

Selection mode is activated by tapping "Select", then long-pressing at start and end positions to define the range.

**Gesture Handling**: The widget handles:

- `TapTextBox` - move cursor to tap position via `text_widget:moveCursorToXY()`
- `HoldTextBox` - show clipboard dialog
- `SwipeTextBox` - diagonal swipes trigger full refresh

Sources: [frontend/ui/widget/inputtext.lua1-900](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/inputtext.lua#L1-L900)

### InputDialog

```
Features

Callbacks

Modes

InputDialog Structure

InputDialog
extends FocusManager

TitleBar

Description text

InputText widget

ButtonTable

CheckButton widgets
(optional)

Standard mode

Fullscreen mode
(fullscreen=true)

Save/Close mode
(save_callback)

Scroll navigation
(add_nav_bar)

enter_callback

save_callback

reset_callback

close_callback

edited_callback

strike_callback

MultiInputDialog
(multiple fields)

Password mode
(text_type='password')

Number input
(input_type='number')

Read-only mode
(readonly=true)
```

**Component Layout**: `InputDialog` assembles components vertically:

1. `TitleBar` with title and optional description
2. `VerticalSpan` padding
3. `InputText` widget (main text entry)
4. Optional `CheckButton` widgets
5. `VerticalSpan` padding
6. `ButtonTable` with action buttons

**Fullscreen Mode**: Setting `fullscreen=true` creates an editor that fills the screen minus keyboard. Typically combined with:

- `condensed=true` - reduces padding
- `allow_newline=true` - enables multiline input
- `add_scroll_buttons=true` or `add_nav_bar=true` - adds navigation

**Save/Close Buttons**: When `save_callback` is provided, the dialog automatically adds:

- Reset button (if `reset_callback` provided)
- Save button - calls `save_callback(content, closing)` where `closing` indicates if dialog is closing
- Close button - prompts to save if text modified

The callback can return `nil/true` (success) or `false, error_msg` (failure).

**Scroll Navigation**: Setting `add_nav_bar=true` adds a bottom row with Home/End/Up/Down buttons. Alternatively, `add_scroll_buttons=true` adds Up/Down to first button row.

**MultiInputDialog**: A variant that accepts multiple `InputText` fields defined in `fields` array. Each field can have its own `input_type`, `text`, `hint`, and `description`.

**Password Mode**: Setting `text_type="password"` shows dots instead of characters. A checkbox toggles visibility. Force-disabled on non-touch devices since focus doesn't work with the checkbox.

Sources: [frontend/ui/widget/inputdialog.lua1-1000](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/inputdialog.lua#L1-L1000)[frontend/ui/widget/multiinputdialog.lua1-300](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/multiinputdialog.lua#L1-L300)

## Text Display Widgets

KOReader provides several widgets for displaying text content with support for scrolling, formatting, and interactive features.

### TextBoxWidget Architecture

```
Features

Layout Options

Text Processing

Rendering Paths

TextBoxWidget Core

TextBoxWidget
extends InputContainer

text or charlist

face (font)

width, height

Legacy mode
RenderText

XText mode
libkoreader-xtext
(use_xtext=true)

_splitToLines()

vertical_string_list[]

Justification padding
(idx_pad)

Image positioning

alignment
(left/center/right)

justified

para_direction_rtl
auto_para_direction

lang

Virtual scrolling
(virtual_line_num)

Editable cursor
(editable=true)

Text selection
(highlight_text_selection)

Embedded images
```

**Rendering Modes**: TextBoxWidget supports two rendering backends selected via `use_xtext` flag:

- **Legacy mode**: Uses `RenderText` module with `sizeUtf8Text()` and custom text shaping
- **XText mode**: Uses `libkoreader-xtext` FFI library with HarfBuzz for advanced text shaping, BiDi, and better performance

The mode is set globally via Settings → Text settings → Use XText (default: enabled).

**Text Measurement**: In legacy mode, `_evalCharWidthList()` measures each unique character's width via `RenderText:sizeUtf8Text()` and stores in `char_width` table. In XText mode, `_measureWithXText()` creates an `xtext` object that handles all measurement internally.

**Line Breaking**: The `_splitToLines()` method breaks text into lines:

1. Iterates through characters tracking current line width
2. Checks if word fits on current line
3. If not, wraps to next line
4. Handles CJK text where each character can be a break point (via `util.isSplittable()`)
5. Respects non-splittable sequences (punctuation rules)

**Justification**: When `justified=true`, the widget calculates padding for each character to distribute remaining space. Padding is stored in `idx_pad` table and applied during rendering.

**BiDi Support**: With XText enabled, the widget supports:

- `para_direction_rtl` - explicit RTL direction
- `auto_para_direction` - detect paragraph direction from text
- `alignment_strict` - when false, inverts alignment for RTL paragraphs

**Embedded Images**: The `images` parameter accepts an array of image definitions:

```
{
  width = 100, height = 100,
  bb = blitbuffer,  -- may be nil initially
  hi_width = 200, hi_height = 200, -- high-res version
  hi_bb = blitbuffer,
  title = "Image title",
  caption = "Caption text",
  load_bb_func = function(hi) ... end  -- lazy loader
}
```

Images are positioned at top-right of pages, and text flows around them with reduced width.

**Scrolling**: When `height` is specified, the widget shows only `lines_per_page` lines starting at `virtual_line_num`. Scrolling updates `virtual_line_num` and repaints.

**Editable Mode**: When `editable=true`, the widget displays a cursor at `charpos` and supports text selection. The cursor is a `LineWidget` drawn at the character position.

Sources: [frontend/ui/widget/textboxwidget.lua1-2500](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/textboxwidget.lua#L1-L2500)

### ScrollTextWidget

```
Gestures

ScrollTextWidget

ScrollTextWidget
extends InputContainer

TextBoxWidget
(internal)

VerticalScrollBar

HorizontalGroup

Pan (scroll by lines)

Swipe (page scroll)

Tap (image preview)
```

**Composition**: `ScrollTextWidget` wraps a `TextBoxWidget` and adds:

- `VerticalScrollBar` showing scroll position
- `HorizontalGroup` arranging text and scroll bar
- Gesture handlers for scrolling

**Scroll Position**: The scroll bar's `low` and `high` values represent the visible portion as fractions:

```
low = (virtual_line_num - 1) / total_lines
high = (virtual_line_num - 1 + visible_lines) / total_lines
```

**Pan Scrolling**: When `scroll_by_pan=true`, pan gestures scroll by lines. The `onPan` handler updates `virtual_line_num` based on gesture distance.

**Scroll Callback**: If `scroll_callback` is provided, it's called with `(low, high)` whenever scroll position changes. This enables external widgets to track scroll state.

**Image Tap**: When text contains embedded images and user taps on an image area, the widget shows an `ImageViewer` with the high-resolution version.

Sources: [frontend/ui/widget/scrolltextwidget.lua1-200](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/scrolltextwidget.lua#L1-L200)

### HTML Display System

```
ScrollHtmlWidget

Interaction

Rendering

HtmlBoxWidget

HtmlBoxWidget
extends InputContainer

setContent(html, css, dpi)

MuPDF HTML parser

Rendered pages

Parse HTML + CSS

Layout at DPI

drawPage()

Blitbuffer

extractLinks()

extractTextboxes()

Text selection

Link tapping

ScrollHtmlWidget

VerticalScrollBar

Page scrolling
```

**HTML Rendering**: `HtmlBoxWidget` uses MuPDF to render HTML content. The `setContent()` method:

1. Creates a MuPDF document from HTML string via `Mupdf.openDocumentFromText()`
2. Applies custom CSS by combining with default styles
3. Sets rendering DPI (default: 96)
4. Lays out pages with specified dimensions

**Link Extraction**: The `extractLinks()` method retrieves links from rendered pages via MuPDF's page link API. Each link is stored with bounding box coordinates and URI.

**Text Selection**: The widget supports text selection via hold gestures:

1. `onHoldStartText` - begin selection at touch point
2. `onHoldPanText` - extend selection as user drags
3. `onHoldReleaseText` - finalize selection and extract text

Selected text is highlighted by drawing rectangles over the selection boxes returned by MuPDF.

**Link Tapping**: When user taps in a link's bounding box, the `html_link_tapped_callback` is invoked with the link object. This enables:

- Dictionary lookups (href="bword://word")
- Internal navigation (href="#anchor")
- External links (filtered for security)

**ScrollHtmlWidget**: Wraps `HtmlBoxWidget` with scrolling support. It tracks the current page number and updates the scroll bar accordingly. Pan gestures scroll by pages.

**CSS Customization**: The `css` parameter allows custom styling. The widget combines it with MuPDF's default styles. Common customizations include font sizes, margins, and colors.

Sources: [frontend/ui/widget/htmlboxwidget.lua1-500](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/htmlboxwidget.lua#L1-L500)[frontend/ui/widget/scrollhtmlwidget.lua1-200](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/scrollhtmlwidget.lua#L1-L200)

### FootnoteWidget

The `FootnoteWidget` displays footnotes and endnotes in a popup at the bottom of the screen.

**Rendering**: Uses `ScrollHtmlWidget` internally with custom CSS for footnote-specific styling. The CSS includes margin adjustments, list formatting, and responsive sizing.

**Link Handling**: Footnotes can contain internal links (e.g., back to reference in main text). These are handled via `html_link_tapped_callback` which can:

- Close the footnote and navigate to link target
- Open nested footnotes if link points to another footnote
- Trigger dictionary lookups for "bword://" links

**Auto-Dismiss**: The widget can auto-dismiss after a timeout if no interaction occurs. The timeout starts when the footnote is shown and resets on any gesture.

Sources: [frontend/ui/widget/footnotewidget.lua1-600](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/footnotewidget.lua#L1-L600)

## Localization and Language Support

The `Language` module manages UI localization and provides support for right-to-left (RTL) languages.

### Language System

```
Settings

UI Adaptation

Gettext Integration

Language Module

Language module

language_names
(60+ languages)

languages_rtl
(13 RTL languages)

gettext

.po files
(translations)

.mo files
(compiled)

_.changeLang()

isLanguageRTL()

BD module
(BiDi layout)

VirtualKeyboard
layout selection

TextBoxWidget
para_direction_rtl

G_reader_settings
language

Language menu

Restart required
```

**Supported Languages**: The `language_names` table defines 60+ languages with localized names:

```
language_names = {
  en = "English",
  ar = "عربى",
  zh_CN = "简体中文",
  he = "עִבְרִית",
  ...
}
```

**RTL Languages**: The `languages_rtl` table marks 13 right-to-left languages:

- Arabic (ar, arz)
- Hebrew (he)
- Persian (fa)
- Urdu (ur)
- Kurdish (ku, ckb)
- Yiddish (yi)
- Pashto (ps)
- And others

**RTL Detection**: The `isLanguageRTL()` method extracts the base language code (before underscore) and checks if it's in `languages_rtl`:

```
function Language:isLanguageRTL(lang_locale)
  local lang = lang_locale:match("^([^_]+)")
  return self.languages_rtl[lang] or false
end
```

**Language Switching**: The `changeLanguage()` method:

1. Calls `_.changeLang(lang_locale)` to update gettext
2. Saves to `G_reader_settings:saveSetting("language")`
3. Requests restart via `UIManager:askForRestart()`

Restart is required because many UI strings are cached in menu structures and widget templates.

**Menu Generation**: The `getLangMenuTable()` method builds a menu with radio buttons for language selection. Each item uses `genLanguageSubItem()` which creates a menu entry with:

- Text: localized language name
- Checked function: compares against current language
- Callback: calls `changeLanguage()`

Sources: [frontend/ui/language.lua1-160](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/language.lua#L1-L160)

### BiDi and Layout Mirroring

```
Widget Mirroring

BD Module (BiDi)

Input Handling

VirtualKeyboard

RTL keyboard layouts

InputText

Text Rendering

TextBoxWidget

para_direction_rtl

auto_para_direction

libkoreader-xtext

BD module

mirroredUILayout()

wrap()

ltr() / rtl() / auto()

HorizontalGroup

Reverse child order

allow_mirroring flag
```

**UI Mirroring**: The `BD` (BiDi) module provides `mirroredUILayout()` which returns true when an RTL language is active. Widgets use this to reverse their layout:

- `HorizontalGroup` reverses child order when `allow_mirroring=true`
- Button arrangements flip left-to-right
- Alignment values are automatically inverted

**Text Direction**: `TextBoxWidget` supports three direction modes:

- `para_direction_rtl=true` - force RTL
- `para_direction_rtl=false` - force LTR
- `auto_para_direction=true` - detect from text content (using Unicode BiDi algorithm in XText)

**Keyboard Layouts**: The virtual keyboard automatically loads RTL-specific layouts (e.g., Arabic, Hebrew keyboards) when those languages are selected. The layout files define character positions appropriate for RTL typing.

**Direction Marks**: The `BD` module provides functions to wrap text with Unicode direction marks:

- `BD.ltr(text)` - wraps with Left-to-Right mark (U+200E)
- `BD.rtl(text)` - wraps with Right-to-Left mark (U+200F)
- `BD.auto(text)` - wraps with appropriate mark based on content

**Menu Items**: Many menu items use `BD.wrap()` or `BD.auto()` to ensure text displays correctly in both LTR and RTL UIs.

Sources: [frontend/ui/bidi.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/bidi.lua) (not provided but referenced), [frontend/ui/widget/textboxwidget.lua110-115](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/textboxwidget.lua#L110-L115)

## Content Enhancement

KOReader provides CSS-based content enhancement through the style tweaks system and HTML rendering capabilities.

### CSS Tweaks System

```
Application

Settings Storage

UI Management

Tweak Definitions

css_tweaks.lua

Categories
(tables)

Individual tweaks
(tables)

id, title, css
description, priority
conflicts_with

ReaderStyleTweak

buildTweakMenu()

onToggleTweak()

handleConflicts()

DocSettings
css_tweaks

G_reader_settings
cre_custom_css

DEFAULT_GLOBAL_STYLE_TWEAKS

CreDocument

setStyleSheet()

Re-render document
```

**Tweak Structure**: Each tweak in `css_tweaks.lua` is defined as a table:

```
{
  id = "margin_body_0",
  title = _("Ignore publisher page margins"),
  description = _("Force page margins to be 0..."),
  css = [[body { margin: 0 !important; }]],
  priority = 0,  -- optional, default 0
  conflicts_with = "other_tweak_id",  -- optional
}
```

**Categories**: Tweaks are organized hierarchically:

- "Pages and margins" [frontend/ui/data/css_tweaks.lua34-176](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/data/css_tweaks.lua#L34-L176)
- Horizontal margins
- Vertical margins
- Page breaks and blank pages
- Widows and orphans
- "Text" [frontend/ui/data/css_tweaks.lua242-490](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/data/css_tweaks.lua#L242-L490)
- Text alignment
- Text sizes
- Line spacing
- "Styles" [frontend/ui/data/css_tweaks.lua492-720](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/data/css_tweaks.lua#L492-L720)
- Boldness, italics
- Colors and backgrounds
- "Paragraphs" [frontend/ui/data/css_tweaks.lua722-957](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/data/css_tweaks.lua#L722-L957)
- Indentation
- Spacing
- "Footnotes and endnotes" [frontend/ui/data/css_tweaks.lua959-1133](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/data/css_tweaks.lua#L959-L1133)

**Priority System**: Tweaks with higher `priority` values are appended later in the CSS, allowing them to override lower-priority tweaks. Default priority is 0.

**Conflict Resolution**: When enabling a tweak with `conflicts_with`, the conflicting tweak(s) are automatically disabled. The `conflicts_with` field can be:

- A string (single conflicting ID)
- A table/array (multiple conflicting IDs)
- A function that returns true for conflicting IDs

**Global vs Per-Book**: Tweaks can be enabled:

- **Per-book**: stored in `DocSettings` under `css_tweaks` key
- **Globally**: stored in `G_reader_settings` under `cre_custom_css` key

Default global tweaks are defined in `DEFAULT_GLOBAL_STYLE_TWEAKS` table.

**Menu Building**: The `buildTweakMenu()` method recursively processes the tweak definition tree to build menu items with checkmarks and callbacks.

Sources: [frontend/ui/data/css_tweaks.lua1-1200](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/data/css_tweaks.lua#L1-L1200)[frontend/apps/reader/modules/readerstyletweak.lua1-800](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerstyletweak.lua#L1-L800)

### CSS Application Flow

```
Render
CreDocument
ReaderStyleTweak
Menu
User
Render
CreDocument
ReaderStyleTweak
Menu
User
Combine tweaks by priority
Toggle tweak
onToggleTweak(tweak_id)
Check conflicts
Update css_tweaks table
buildStyleSheet()
setStyleSheet(css)
Re-render with new CSS
Document updated
```

**Enabling a Tweak**: When user toggles a tweak:

1. `onToggleTweak()` is called with tweak ID
2. Method checks for conflicts and disables conflicting tweaks
3. Updates `self.enabled_tweaks` table
4. Calls `onApplyStyleSheet()` to rebuild and apply CSS

**Building Stylesheet**: The `buildStyleSheet()` method:

1. Collects all enabled tweaks
2. Sorts by priority (lower first)
3. Concatenates CSS with newlines
4. Returns combined stylesheet string

**Applying to Document**: The `onApplyStyleSheet()` method:

1. Calls `buildStyleSheet()` to get CSS
2. Invokes `ui.document:setStyleSheet(css)` on CreDocument
3. Triggers `ReaderView:onReloadDocument()` for re-render

**Live Updates**: Changes take effect immediately without closing the document. The document is re-rendered with the new CSS, and the current reading position is maintained.

**TweakInfoWidget**: Holding a tweak menu item shows `TweakInfoWidget` displaying:

- Tweak title and description
- CSS code in a scrollable text box (copyable to clipboard on devices with keyboard)
- "Use on all books" / "Don't use on all books" button
- "Show in action list" toggle for Dispatcher integration

Sources: [frontend/apps/reader/modules/readerstyletweak.lua266-661](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerstyletweak.lua#L266-L661)

### HTML Rendering and Styling

**MuPDF Integration**: HTML content is rendered via MuPDF's HTML/EPUB engine. The engine supports:

- HTML5 parsing with error recovery
- CSS 2.1 styling with some CSS3 features
- Embedded images and links
- Text selection and extraction

**Custom CSS**: Widgets accepting HTML can specify custom CSS:

```
HtmlBoxWidget:new{
  html_body = "<html>...</html>",
  css = [[
    body { font-size: 16px; }
    p { margin: 1em 0; }
  ]],
  default_font_size = 20,
}
```

**Footnote Styling**: `FootnoteWidget` uses specialized CSS defined in `DEFAULT_CSS` to format footnotes consistently. The CSS includes proper margin handling, list styling, and responsive font sizing.

**Wikipedia Styling**: Wikipedia articles receive custom styling to improve readability, with adjustments for section headers, lists, and images.

Sources: [frontend/ui/widget/htmlboxwidget.lua179-199](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/htmlboxwidget.lua#L179-L199)[frontend/ui/widget/footnotewidget.lua24-180](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/footnotewidget.lua#L24-L180)

---

This document covered KOReader's utility functions and content enhancement services. For information about the reader UI and document features, see [Reading Features and Interaction](/koreader/koreader/6-reading-features-and-interaction). For the widget framework, see [User Interface Components](/koreader/koreader/7-user-interface-components).

---

# Dictionary-and-Wikipedia-Integration

# Dictionary and Wikipedia Integration
Relevant source files
- [frontend/apps/reader/modules/readerdictionary.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerdictionary.lua)
- [frontend/apps/reader/modules/readerstyletweak.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerstyletweak.lua)
- [frontend/apps/reader/modules/readerwikipedia.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerwikipedia.lua)
- [frontend/ui/data/css_tweaks.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/data/css_tweaks.lua)
- [frontend/ui/data/settings_migration.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/data/settings_migration.lua)
- [frontend/ui/translator.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/translator.lua)
- [frontend/ui/widget/dictquicklookup.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/dictquicklookup.lua)
- [frontend/ui/widget/footnotewidget.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/footnotewidget.lua)
- [frontend/ui/widget/htmlboxwidget.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/htmlboxwidget.lua)
- [frontend/ui/widget/scrollhtmlwidget.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/scrollhtmlwidget.lua)
- [frontend/ui/widget/scrolltextwidget.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/scrolltextwidget.lua)
- [frontend/ui/widget/textboxwidget.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/textboxwidget.lua)
- [frontend/ui/wikipedia.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/wikipedia.lua)
- [frontend/util.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua)
- [spec/unit/util_spec.lua](https://github.com/koreader/koreader/blob/9e6b1587/spec/unit/util_spec.lua)

This document covers KOReader's dictionary lookup and Wikipedia article retrieval systems. These features allow users to look up word definitions and Wikipedia articles directly from selected text in documents.

For information about text selection and highlighting mechanisms, see [Highlight and Annotation System](/koreader/koreader/6.1-highlight-and-annotation-system). For text rendering and display, see [Text Display Widgets](/koreader/koreader/7.4-text-display-widgets).

---

## Purpose and Scope

The dictionary and Wikipedia integration provides in-document lookup capabilities:

- **Dictionary Lookup**: Queries StarDict-format dictionaries via the `sdcv` command-line tool
- **Wikipedia Lookup**: Fetches article content from Wikipedia's Web API
- **Unified Display**: Both systems share the `DictQuickLookup` widget for consistent presentation
- **Text Processing**: Utilities clean and prepare selected text for queries
- **Multi-language Support**: Dictionary ordering, Wikipedia language selection, and translation services

---

## Architecture Overview

```
Data Sources

Display Layer

Lookup Execution

Reader Modules

selected text

selected text

cleanSelection()

cleanSelection()

stardictLookup()

onLookupWikipedia()

reads

HTTP GET

creates

creates

plain definitions

HTML articles

ReaderDictionary

ReaderWikipedia

ReaderHighlight
(text selection)

sdcv command
(StarDict lookup)

Wikipedia:loadPage()
(API client)

util.cleanupSelectedText()

DictQuickLookup
(main widget)

ScrollTextWidget
(plain text)

ScrollHtmlWidget
(HTML content)

*.ifo files
(StarDict dicts)

Wikipedia API
en.wikipedia.org/w/api.php
```

**Sources**: [frontend/apps/reader/modules/readerdictionary.lua1-900](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerdictionary.lua#L1-L900)[frontend/apps/reader/modules/readerwikipedia.lua1-400](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerwikipedia.lua#L1-L400)[frontend/ui/widget/dictquicklookup.lua1-1400](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/dictquicklookup.lua#L1-L1400)

---

## Module Initialization and Discovery

### ReaderDictionary Initialization

The `ReaderDictionary` module extends `InputContainer` and manages dictionary discovery, ordering, and lookup execution.

**Dictionary Discovery Process**:

1. **Scan directories**: [frontend/apps/reader/modules/readerdictionary.lua128-162](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerdictionary.lua#L128-L162)

- Primary: `STARDICT_DATA_DIR` or `DataStorage:getDataDir()/data/dict`
- Extension: `{data_dir}_ext`
- Recursively finds all `.ifo` files
2. **Parse metadata**: [frontend/apps/reader/modules/readerdictionary.lua139-160](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerdictionary.lua#L139-L160)

- Extracts `bookname` from `.ifo` file
- Detects HTML format (`sametypesequence=h`)
- Parses language codes (`lang=en-fr`)
- Loads custom CSS and HTML fix functions
3. **Sort and filter**: [frontend/apps/reader/modules/readerdictionary.lua188-201](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerdictionary.lua#L188-L201)

- Applies user-defined ordering (`dicts_order`)
- Filters disabled dictionaries (`dicts_disabled`)

```
ReaderDictionary:init()

getIfosInDir()

*.ifo files

Parse metadata

available_ifos[]

sortAvailableIfos()

updateSdcvDictNamesOptions()

enabled_dict_names[]
(for -u options)
```

**Key Data Structures**:
VariableTypePurpose`available_ifos`tableModule-local cache of all discovered dictionaries`enabled_dict_names`arrayOrdered list of dictionary names to query`dicts_order`hashUser-specified ordering: `{ifo_path = order_num}``dicts_disabled`hashDisabled dictionaries: `{ifo_path = true}``preferred_dictionaries`arrayPer-book dictionary priority
**Sources**: [frontend/apps/reader/modules/readerdictionary.lua105-230](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerdictionary.lua#L105-L230)

### ReaderWikipedia Initialization

`ReaderWikipedia` extends `ReaderDictionary` and adds Wikipedia-specific functionality:

[frontend/apps/reader/modules/readerwikipedia.lua31-41](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerwikipedia.lua#L31-L41)

```
function ReaderWikipedia:init()
    self:registerKeyEvents()
    self.wiki_languages = {}
    -- Read from G_reader_settings for FileBrowser
    self.wiki_last_language = G_reader_settings:readSetting("wikipedia_last_language")
    self.ui.menu:registerToMainMenu(self)
    if not wikipedia_history then
        wikipedia_history = LuaData:open(DataStorage:getSettingsDir() .. "/wikipedia_history.lua", "WikipediaHistory")
    end
end
```

**Language initialization**: [frontend/apps/reader/modules/readerwikipedia.lua324-369](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerwikipedia.lua#L324-L369)

- Attempts to use user-configured `wikipedia_languages`
- Falls back to document language + UI language
- Stores in `self.wiki_languages` array (ordered by preference)

**Sources**: [frontend/apps/reader/modules/readerwikipedia.lua25-369](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerwikipedia.lua#L25-L369)

---

## Dictionary Lookup Flow

### Text Selection to Lookup

```
DictQuickLookup
sdcv process
util module
ReaderDictionary
ReaderHighlight
User
DictQuickLookup
sdcv process
util module
ReaderDictionary
ReaderHighlight
User
stripPunctuation()
cleanupSelectedText()
Build command:
./sdcv -nj -u "Dict1" -u "Dict2" "word"
alt
[Still no
results]
alt
[No
results]
alt
[Fuzzy search
disabled]
[Fuzzy search
enabled]
Long-press and select text
Extract selected text
onLookupWord(word, is_sane, boxes, highlight)
cleanSelection(word, is_sane)
cleaned_word
Check disable_fuzzy_search setting
Wrap in Trapper:wrap()
stardictLookup(word, enabled_dict_names, fuzzy)
Run with exact word
Run with word
Run with word*
Run with *word*
JSON results
Parse and clean definitions
Create DictQuickLookup widget
Display results
```

**Sources**: [frontend/apps/reader/modules/readerdictionary.lua587-900](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerdictionary.lua#L587-L900)[frontend/util.lua23-70](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua#L23-L70)

### Dictionary Command Construction

The `stardictLookup` function builds the `sdcv` command: [frontend/apps/reader/modules/readerdictionary.lua726-817](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerdictionary.lua#L726-L817)

**Command structure**:

```
./sdcv -nj --utf8-output --utf8-input -u "Dict Name 1" -u "Dict Name 2" "lookup_word"

```

**Options**:

- `-n`: No control character sequences (plain text output)
- `-j`: Output in JSON format
- `--utf8-output`, `--utf8-input`: UTF-8 encoding
- `-u "Dict Name"`: Multiple `-u` options specify dictionary order and selection

**Fuzzy search strategy**: [frontend/apps/reader/modules/readerdictionary.lua777-812](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerdictionary.lua#L777-L812)

1. Try exact word
2. If no results and fuzzy enabled: try `word*` (suffix wildcard)
3. If still no results: try `*word*` (full wildcard)

**Sources**: [frontend/apps/reader/modules/readerdictionary.lua726-817](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerdictionary.lua#L726-L817)

### Result Processing

After receiving JSON output from `sdcv`, the module processes results: [frontend/apps/reader/modules/readerdictionary.lua818-900](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerdictionary.lua#L818-L900)

```
Yes

No

Raw JSON from sdcv

JSON.decode()

For each result

Extract definition

is_html?

Apply fix_html_func

Add custom CSS

Strip trailing newlines

results[] table
```

**Result structure**:

```
results = {
    {
        word = "lookup_word",
        definition = "definition text or HTML",
        dict = "Dictionary Name",
        is_html = true/false,
        css = "custom CSS string",  -- for HTML dicts
    },
    -- ... more results
}
```

**HTML dictionary customization**: [frontend/apps/reader/modules/readerdictionary.lua72-103](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerdictionary.lua#L72-L103)

- Custom CSS: Read from `{dict_name}.css` file
- HTML fixing: Execute Lua function from `{dict_name}.lua` file

**Sources**: [frontend/apps/reader/modules/readerdictionary.lua818-900](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerdictionary.lua#L818-L900)[frontend/apps/reader/modules/readerdictionary.lua72-103](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerdictionary.lua#L72-L103)

---

## Wikipedia Integration

### Wikipedia Lookup Flow

```
DictQuickLookup
Wikipedia API
Wikipedia module
ReaderWikipedia
User
DictQuickLookup
Wikipedia API
Wikipedia module
ReaderWikipedia
User
Set up wiki_languages array
Query: generator=search
prop=extracts|pageimages
Query: prop=extracts|pageimages|langlinks
alt
[Intro search]
[Full page]
Scale thumbnails
Set up lazy loading
alt
[Show images enabled]
Format sections
Add headers
Clean lists
alt
[Prettify enabled]
Select text or tap Wikipedia button
onLookupWikipedia(word, is_fullpage, text, lang)
cleanSelection(word)
initLanguages()
searchAndGetIntros(text, lang)
loadPage(text, lang, WIKIPEDIA_INTRO)
getFullPage(title, lang)
loadPage(title, lang, WIKIPEDIA_FULL)
JSON response
Parse pages object
addImages(page, lang, show_more, size_factor)
prettifyText(extract)
pages object
Create DictQuickLookup widget
Display Wikipedia content
```

**Sources**: [frontend/apps/reader/modules/readerwikipedia.lua385-550](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerwikipedia.lua#L385-L550)[frontend/ui/wikipedia.lua216-333](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/wikipedia.lua#L216-L333)

### Wikipedia API Client

The `Wikipedia` module ([frontend/ui/wikipedia.lua23-96](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/wikipedia.lua#L23-L96)) defines API parameters:

**Search query parameters** (intro text):

```
wiki_search_params = {
    action = "query",
    generator = "search",
    gsrnamespace = "0",
    gsrlimit = 30,  -- max results
    exlimit = "max",
    prop = "extracts|info|pageimages",
    format = "json",
    explaintext = "",  -- plain text
    exintro = "",      -- intro only
}
```

**Full page parameters**:

```
wiki_full_params = {
    action = "query",
    prop = "extracts|pageimages|langlinks",
    format = "json",
    explaintext = "",
    redirects = "",
    lllimit = 500,  -- language links
}
```

**URL construction**: [frontend/ui/wikipedia.lua216-245](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/wikipedia.lua#L216-L245)

```
https://{lang}.wikipedia.org/w/api.php?{params}&gsrsearch={text}

```

**Sources**: [frontend/ui/wikipedia.lua23-286](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/wikipedia.lua#L23-L286)

### Text Prettification

For full Wikipedia pages, the `prettifyText` function formats plain text: [frontend/ui/wikipedia.lua439-595](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/wikipedia.lua#L439-L595)

**Formatting operations**:

1. **Section headers**: Lines ending with `==` → bolded headers
2. **Subsections**: Lines with `===` prefix → indented bold text
3. **List items**: Lines starting with bullet/dash → formatted with `•` or indentation
4. **Paragraph spacing**: Adds spacing between sections
5. **Line wrapping**: Handles long lines and list continuations

```
-- Example transformation
"== History =="          → "\u{2009}▒▒▒ History ▒▒▒"  (with PTF_BOLD markers)
"• First item"           → "  • First item"
"=== Subsection ==="     → "  ▒▒▒ Subsection ▒▒▒"
```

**Sources**: [frontend/ui/wikipedia.lua439-595](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/wikipedia.lua#L439-L595)

### Image Handling

Wikipedia images are loaded lazily: [frontend/ui/wikipedia.lua596-700](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/wikipedia.lua#L596-L700)

```
Yes

Wikipedia page object

Has thumbnail?

Extract image URL

Calculate scaled dimensions

Create image table entry

bb = nil
(lazy load)

Set load_bb_func

Callback fetches image
when needed
```

**Image data structure**: [frontend/ui/wikipedia.lua596-655](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/wikipedia.lua#L596-L655)

```
image = {
    width = scaled_width,
    height = scaled_height,
    bb = nil,  -- loaded on demand
    load_bb_func = function(high_res)
        -- Fetch and render image
        -- high_res: false for thumbnail, true for full size
    end,
    title = article_title,
    caption = extract_text,
}
```

**Cookie-based rate limiting**: [frontend/ui/wikipedia.lua126-196](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/wikipedia.lua#L126-L196)

- First request caches Wikipedia cookies
- Subsequent image requests reuse cookies for better rate limits (70/30 bucket vs default 25/5)

**Sources**: [frontend/ui/wikipedia.lua596-700](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/wikipedia.lua#L596-L700)[frontend/ui/wikipedia.lua126-196](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/wikipedia.lua#L126-L196)

---

## DictQuickLookup Widget

The `DictQuickLookup` widget ([frontend/ui/widget/dictquicklookup.lua43-71](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/dictquicklookup.lua#L43-L71)) displays both dictionary and Wikipedia results.

### Widget Architecture

```
State Management

Content Display

Components

DictQuickLookup

TitleBar
(dict/wiki name)

Lookup word
(TextWidget + IconButton)

Content area

ButtonTable
(navigation)

ScrollTextWidget
(plain text)

ScrollHtmlWidget
(HTML content)

results[]
(all definitions)

dict_index
(current result)

DictQuickLookup.window_list
(static, stacked lookups)
```

**Sources**: [frontend/ui/widget/dictquicklookup.lua104-614](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/dictquicklookup.lua#L104-L614)

### Initialization Parameters

[frontend/ui/widget/dictquicklookup.lua43-71](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/dictquicklookup.lua#L43-L71)
ParameterTypePurpose`results`tableArray of definition/article objects`lookupword`stringOriginal query word`word`stringDisplay word (may differ from lookupword)`is_wiki`booleanWikipedia mode flag`is_wiki_fullpage`booleanFull article vs intro`dict_index`numberCurrently displayed result (1-based)`word_boxes`tableSelection rectangles to avoid covering`refresh_callback`functionCalled before full refresh`dict_close_callback`functionCalled when widget closes
**Multiple lookup windows**: [frontend/ui/widget/dictquicklookup.lua68](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/dictquicklookup.lua#L68-L68)

- Static `window_list` table tracks all open `DictQuickLookup` instances
- Enables stacked lookups: select word in a definition → open new lookup
- Closing top window returns to previous window

**Sources**: [frontend/ui/widget/dictquicklookup.lua43-71](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/dictquicklookup.lua#L43-L71)[frontend/ui/widget/dictquicklookup.lua104-226](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/dictquicklookup.lua#L104-L226)

### Dictionary vs Wikipedia Mode

The widget adapts based on `is_wiki` flag: [frontend/ui/widget/dictquicklookup.lua227-614](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/dictquicklookup.lua#L227-L614)

**Dictionary mode**:

- Title: Dictionary name (left-aligned)
- Content: Plain text or HTML definition
- Buttons: Prev/Next dict, Highlight, Wikipedia, Search, Close
- Navigation: Cycle through `enabled_dict_names`

**Wikipedia mode (intro)**:

- Title: Wikipedia (centered)
- Content: Plain text intro
- Buttons: Full article, Language selector, Close
- Navigation: Select language from `wiki_languages`

**Wikipedia mode (full page)**:

- Title: Wikipedia (centered)
- Content: Plain text or HTML with images
- Buttons: Save as EPUB, Close
- Navigation: N/A (single page)

```
Tap Wikipedia button

Tap Full Article

Hold 3+ sec on text

Back

Dictionary Mode

Wikipedia Intro

Wikipedia Full Page
```

**Sources**: [frontend/ui/widget/dictquicklookup.lua227-614](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/dictquicklookup.lua#L227-L614)

### Button Configuration

Dictionary mode buttons: [frontend/ui/widget/dictquicklookup.lua448-598](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/dictquicklookup.lua#L448-L598)

```
buttons = {
    {
        { id = "prev_dict", text = "◁◁", enabled = self:isPrevDictAvaiable() },
        { id = "highlight", text = _("Highlight"), enabled = self.highlight ~= nil },
        { id = "next_dict", text = "▷▷", enabled = self:isNextDictAvaiable() },
    },
    {
        { id = "wikipedia", text = _("Wikipedia") },
        { id = "search", text = _("Search"), enabled = self:canSearch() },
        { id = "close", text = _("Close") },
    },
}
```

**Special button behaviors**:

- **Prev/Next dict**: Cycle through `results` array
- **Highlight**: Toggle `save_highlight` flag (updates button text)
- **Wikipedia**: Launches Wikipedia lookup with same word
- **Search**: Opens highlight search in ReaderUI
- **Close**: Calls `dict_close_callback`, removes from `window_list`

**Link following**: [frontend/ui/widget/dictquicklookup.lua584-597](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/dictquicklookup.lua#L584-L597)

- If selected text is part of a link, shows "Follow Link" button
- Tapping executes `self.ui.link:onGotoLink(link)`

**Sources**: [frontend/ui/widget/dictquicklookup.lua448-614](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/dictquicklookup.lua#L448-L614)

### Content Update Flow

When switching between definitions: [frontend/ui/widget/dictquicklookup.lua1159-1290](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/dictquicklookup.lua#L1159-L1290)

```
Yes

No

changeDictionary(index)

Update dict_index

Extract definition from results[index]

is_html?

Create ScrollHtmlWidget

Create ScrollTextWidget

Free old content widget

Replace in widget tree

Update UI
```

**Image handling**: [frontend/ui/widget/dictquicklookup.lua233-286](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/dictquicklookup.lua#L233-L286)

- Images passed to `ScrollTextWidget` as `images` parameter
- Widget displays images in top-right corner of pages
- Line wrapping adjusts to avoid image overlap

**Sources**: [frontend/ui/widget/dictquicklookup.lua1159-1290](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/dictquicklookup.lua#L1159-L1290)[frontend/ui/widget/dictquicklookup.lua233-286](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/dictquicklookup.lua#L233-L286)

---

## Text Cleaning and Processing

### Text Cleaning Pipeline

Before lookup, selected text undergoes cleaning: [frontend/apps/reader/modules/readerdictionary.lua587-605](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerdictionary.lua#L587-L605)

```
util module

Raw selected text

cleanSelection(text, is_sane)

stripPunctuation()

cleanupSelectedText()

Collapse whitespace

Cleaned text for lookup
```

### stripPunctuation()

[frontend/util.lua23-28](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua#L23-L28)

```
function util.stripPunctuation(text)
    if not text then return end
    -- strip ASCII punctuation marks around text
    -- and strip any generic punctuation marks (U+2000 - U+206F) in the text
    return text:gsub("\226[\128-\131][\128-\191]", ''):gsub("^%p+", ''):gsub("%p+$", '')
end
```

- Removes leading/trailing ASCII punctuation
- Strips Unicode punctuation range U+2000-206F (general punctuation)
- Preserves internal punctuation (e.g., "world" from "hello, world")

**Sources**: [frontend/util.lua23-28](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua#L23-L28)

### cleanupSelectedText()

[frontend/util.lua60-70](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua#L60-L70)

```
function util.cleanupSelectedText(text)
    -- Trim spaces and new lines at start and end
    text = text:gsub("^[\n%s]*", "")
    text = text:gsub("[\n%s]*$", "")
    -- Trim spaces around newlines
    text = text:gsub("%s*\n%s*", "\n")
    -- Trim consecutive spaces (that would probably have collapsed
    -- in rendered CreDocuments)
    text = text:gsub("%s%s+", " ")
    return text
end
```

- Normalizes whitespace for consistent lookups
- Collapses multiple spaces to single space
- Preserves newlines but trims surrounding whitespace

**Sources**: [frontend/util.lua60-70](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua#L60-L70)

### CJK Text Handling

The `util` module provides CJK-aware text processing: [frontend/util.lua522-667](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua#L522-L667)

**Character detection**: [frontend/util.lua525-555](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua#L525-L555)

```
function util.isCJKChar(c)
    if #c < 3 then return false end  -- CJK chars need 3+ UTF-8 bytes
    local code = ffiUtil.utf8charcode(c)
    return code >= 0x1100 and (
        (code >= 0x1100 and code <= 0x11FF) or  -- Hangul Jamo
        (code >= 0x2E80 and code <= 0x9FFF) or  -- CJK blocks
        -- ... more ranges
    )
end
```

**Line breaking rules**: [frontend/util.lua635-667](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua#L635-L667)

- CJK characters are generally splittable (word boundaries)
- Exceptions: punctuation that shouldn't appear at line start/end
- Non-splittable tailers: `!%),.:;?]}¢°·'\"†‡›℃∶、。〃` (CJK-specific)
- Non-splittable leaders: `$(£¥·'\"〈《「『【〔〖〝﹙` (CJK-specific)

**Sources**: [frontend/util.lua522-667](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua#L522-L667)

---

## Content Display Widgets

### ScrollTextWidget

Used for plain text content: [frontend/ui/widget/scrolltextwidget.lua20-48](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/scrolltextwidget.lua#L20-L48)

```
TextBoxWidget Features

ScrollTextWidget

TextBoxWidget
(text rendering)

VerticalScrollBar

_splitToLines()

Line height calculation

Text wrapping

Image display

scrollToRatio()

Update TBW virtual_line_num
```

**Initialization**: [frontend/ui/widget/scrolltextwidget.lua50-129](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/scrolltextwidget.lua#L50-L129)

- Creates `TextBoxWidget` with reduced width (makes room for scroll bar)
- Calculates `lines_per_page` from visible/total line counts
- Enables scroll bar only if content exceeds viewport

**Scrolling**: [frontend/ui/widget/scrolltextwidget.lua214-288](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/scrolltextwidget.lua#L214-L288)

- Swipe north/south: scroll by page
- Tap left/right: scroll by page
- Drag scroll bar: jump to ratio
- Keyboard: PgFwd/PgBack

**Sources**: [frontend/ui/widget/scrolltextwidget.lua20-288](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/scrolltextwidget.lua#L20-L288)

### ScrollHtmlWidget

Used for HTML content: [frontend/ui/widget/scrollhtmlwidget.lua18-33](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/scrollhtmlwidget.lua#L18-L33)

```
HtmlBoxWidget Features

ScrollHtmlWidget

HtmlBoxWidget
(MuPDF rendering)

VerticalScrollBar

MuPDF HTML parser

Multi-page layout

Link detection

Text selection

scrollToRatio()

Update HBW page_number
```

**Content setup**: [frontend/ui/widget/scrollhtmlwidget.lua35-90](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/scrollhtmlwidget.lua#L35-L90)

```
self.htmlbox_widget:setContent(
    self.html_body,
    self.css,
    self.default_font_size,
    self.is_xhtml,
    nil,
    self.html_resource_directory
)
```

**Page-based scrolling**: [frontend/ui/widget/scrollhtmlwidget.lua111-175](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/scrollhtmlwidget.lua#L111-L175)

- HTML is rendered into discrete pages by MuPDF
- Scrolling jumps between full pages (not smooth scroll)
- Scroll bar shows `(page_number-1) / page_count` to `page_number / page_count`

**Sources**: [frontend/ui/widget/scrollhtmlwidget.lua18-197](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/scrollhtmlwidget.lua#L18-L197)

### HTML Rendering with MuPDF

`HtmlBoxWidget` uses MuPDF for HTML rendering: [frontend/ui/widget/htmlboxwidget.lua152-250](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/htmlboxwidget.lua#L152-L250)

**CSS injection**: [frontend/ui/widget/htmlboxwidget.lua200-240](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/htmlboxwidget.lua#L200-L240)

1. Base styles (margins, font, etc.)
2. Custom CSS from dictionary (if provided)
3. Wikipedia-specific styles (if `is_wiki`)
4. Prettification CSS (indentation, spacing)

**Link tap handling**: [frontend/ui/widget/htmlboxwidget.lua433-474](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/htmlboxwidget.lua#L433-L474)

- Detects taps on links: `html_link_tapped_callback(dictionary, link)`
- Used by `ReaderDictionary:onHtmlDictionaryLinkTapped()` for internal dictionary links
- Protocol: `bword://word` or plain `word`

**Text selection**: [frontend/ui/widget/htmlboxwidget.lua476-654](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/htmlboxwidget.lua#L476-L654)

- Hold gesture starts selection
- Pan gesture extends selection
- Release creates selection rectangles
- Can trigger new lookup from selected text

**Sources**: [frontend/ui/widget/htmlboxwidget.lua152-654](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/htmlboxwidget.lua#L152-L654)

---

## Settings and Configuration

### Dictionary Settings

Main menu location: Menu → Search → Dictionary Settings

**Dictionary management**: [frontend/apps/reader/modules/readerdictionary.lua274-308](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerdictionary.lua#L274-L308)

- **Manage dictionaries**: Open `SortWidget` to reorder/enable/disable dictionaries
- **Dictionary presets**: Save/load sets of enabled dictionaries (via `Presets` system)
- **Download dictionaries**: Links to dictionary download sources

**Per-book dictionary priority**: [frontend/apps/reader/modules/readerdictionary.lua417-585](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerdictionary.lua#L417-L585)

```
Dictionary State

showPreferredDictsDialog()

Generate ButtonDialog

enabled_dict_names
(globally enabled)

preferred_dictionaries
(per-book priority)

doc_disabled_dicts
(per-book disabled)

Button labels:
① Dict (preferred)
⊗ Dict (disabled)
Dict (normal)
```

- Tap button: Toggle preferred status
- Hold button: Toggle disabled status for this book
- Preferred dictionaries appear at top of results
- Per-book disabled dicts excluded even if globally enabled

**Sources**: [frontend/apps/reader/modules/readerdictionary.lua274-585](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerdictionary.lua#L274-L585)

### Lookup History

**Dictionary history**: [frontend/apps/reader/modules/readerdictionary.lua166-168](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerdictionary.lua#L166-L168)

```
lookup_history = LuaData:open(DataStorage:getSettingsDir() .. "/lookup_history.lua", "LookupHistory")
```

**Storage format**:

```
{
    lookup_history = {
        { word = "hello", book_title = "My Book", time = 1234567890 },
        -- ...
    }
}
```

**Wikipedia history**: [frontend/apps/reader/modules/readerwikipedia.lua38-40](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerwikipedia.lua#L38-L40)

```
wikipedia_history = LuaData:open(DataStorage:getSettingsDir() .. "/wikipedia_history.lua", "WikipediaHistory")
```

**Storage format**:

```
{
    wikipedia_history = {
        { word = "Ebook", book_title = "My Book", time = 1234567890, page = true, lang = "en" },
        -- ...
    }
}
```

- `page`: `true` for full page, `nil` for intro search
- `lang`: Wikipedia language code used

**History display**: [frontend/apps/reader/modules/readerdictionary.lua244-272](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerdictionary.lua#L244-L272)

- Menu → Search → Dictionary/Wikipedia lookup history
- Shows reverse chronological list
- Grouped by book title
- Tap entry: Re-perform lookup

**Sources**: [frontend/apps/reader/modules/readerdictionary.lua166-272](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerdictionary.lua#L166-L272)[frontend/apps/reader/modules/readerwikipedia.lua38-129](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerwikipedia.lua#L38-L129)

### Wikipedia Settings

Main menu location: Menu → Search → Wikipedia Settings

**Language configuration**: [frontend/apps/reader/modules/readerwikipedia.lua147-200](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerwikipedia.lua#L147-L200)

- Input dialog: Space-separated language codes (e.g., `en fr zh`)
- Saved in `G_reader_settings:wikipedia_languages`
- Defaults to UI language + document language if not set

**EPUB export settings**: [frontend/apps/reader/modules/readerwikipedia.lua202-265](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerwikipedia.lua#L202-L265)

- **Save folder**: Default or custom directory
- **Save in book folder**: Save EPUB alongside current book
- **Include images**: Ask/Always/Never
- **Image quality**: Ask/Standard/Higher

**Prettification toggle**: [frontend/ui/wikipedia.lua87](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/wikipedia.lua#L87-L87)

```
wiki_prettify = G_reader_settings:nilOrTrue("wikipedia_prettify")
```

- Enabled by default
- Formats section headers, lists, spacing

**Sources**: [frontend/apps/reader/modules/readerwikipedia.lua143-298](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerwikipedia.lua#L143-L298)[frontend/ui/wikipedia.lua87](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/wikipedia.lua#L87-L87)

### Fuzzy Search

**Global setting**: `disable_fuzzy_search` in `G_reader_settings`

**Per-book override**: `disable_fuzzy_search` in `DocSettings`

**Menu item**: [frontend/apps/reader/modules/readerdictionary.lua309-334](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerdictionary.lua#L309-L334)

- Checkmark shows current state (per-book or global)
- Tap: Toggle per-book setting
- Hold: Set as global default (shows ★ indicator)

**Behavior**: [frontend/apps/reader/modules/readerdictionary.lua594-604](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerdictionary.lua#L594-L604)

```
local disable_fuzzy_search
if self.ui.doc_settings then
    disable_fuzzy_search = self.disable_fuzzy_search  -- per-book
else
    disable_fuzzy_search = self.disable_fuzzy_search_fm  -- FileManager
end
```

**Sources**: [frontend/apps/reader/modules/readerdictionary.lua309-604](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerdictionary.lua#L309-L604)

---

## External Dictionary Integration

For Android/iOS, KOReader can launch external dictionary apps: [frontend/apps/reader/modules/readerdictionary.lua430-479](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerdictionary.lua#L430-L479)

**Implementation**: [device/android/device.lua](https://github.com/koreader/koreader/blob/9e6b1587/device/android/device.lua) (not shown in provided files)

- Checks if external dictionary apps are installed
- Builds intent to launch with selected word
- Returns control to KOReader after lookup

**Settings UI**: [frontend/apps/reader/modules/readerdictionary.lua430-479](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerdictionary.lua#L430-L479)

- **Use external dictionary**: Enable/disable feature
- **Dictionary selection**: Choose from installed apps
- List generated by `Device:getExternalDictLookupList()`

**Sources**: [frontend/apps/reader/modules/readerdictionary.lua430-479](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerdictionary.lua#L430-L479)

---

## Gesture Integration

### Text Selection for Lookup

From `ReaderHighlight`, text selection triggers dictionary/Wikipedia lookup:

**Hold and release**: [frontend/ui/widget/dictquicklookup.lua161-199](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/dictquicklookup.lua#L161-L199)

- Hold < 3 seconds: Same domain (dict→dict, wiki→wiki)
- Hold ≥ 3 seconds: Switch domain (dict→wiki, wiki→dict)

**Context passing**: [frontend/apps/reader/modules/readerdictionary.lua587-605](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerdictionary.lua#L587-L605)

```
function ReaderDictionary:onLookupWord(word, is_sane, boxes, highlight, link, dict_close_callback)
    -- word: selected text
    -- is_sane: true if text is "clean" (from dictionary, not from document)
    -- boxes: selection rectangles (to avoid covering word)
    -- highlight: reference to highlight object (for unhighlight on close)
    -- link: selected link (for "Follow Link" button)
    -- dict_close_callback: called when widget closes
end
```

**Sources**: [frontend/ui/widget/dictquicklookup.lua161-199](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/dictquicklookup.lua#L161-L199)[frontend/apps/reader/modules/readerdictionary.lua587-605](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerdictionary.lua#L587-L605)

### Stacked Lookups

Multiple `DictQuickLookup` windows can be open simultaneously: [frontend/ui/widget/dictquicklookup.lua68](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/dictquicklookup.lua#L68-L68)

```
Select 'word'

Select text in definition

Select text in definition

Close

Close

Close

Reading book

DictQuickLookup 1
'word'

DictQuickLookup 2
'definition'

DictQuickLookup 3
'synonym'
```

**Implementation**: [frontend/ui/widget/dictquicklookup.lua1441-1454](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/dictquicklookup.lua#L1441-L1454)

```
-- Add to static window list
table.insert(DictQuickLookup.window_list, self)
 
-- On close
function DictQuickLookup:onClose(do_not_close_any_previous)
    table.remove(DictQuickLookup.window_list)
    if not do_not_close_any_previous then
        -- Only unhighlight if this is the last window
        if #DictQuickLookup.window_list == 0 and self.highlight then
            self.highlight:clear()
        end
    end
end
```

**Hold on Close button**: Closes all stacked windows at once

**Sources**: [frontend/ui/widget/dictquicklookup.lua68](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/dictquicklookup.lua#L68-L68)[frontend/ui/widget/dictquicklookup.lua1441-1507](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/dictquicklookup.lua#L1441-L1507)

---

## Translation Integration

The `Translator` module provides translation services: [frontend/ui/translator.lua1-23](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/translator.lua#L1-L23)

**Google Translate API**:

```
https://translate.googleapis.com/translate_a/single?client=gtx&sl=auto&tl=fr&dt=t&q=text

```

**Integration with DictQuickLookup**: [frontend/ui/widget/dictquicklookup.lua23](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/dictquicklookup.lua#L23-L23)

```
local Translator = require("ui/translator")
```

**Usage**: From Wikipedia lookup, users can translate article text to another language

**249 supported languages**: [frontend/ui/translator.lua27-241](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/translator.lua#L27-L241)

- Includes major world languages, regional variants, and specialized scripts
- Auto-detection via `sl=auto` parameter

**Sources**: [frontend/ui/translator.lua1-241](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/translator.lua#L1-L241)[frontend/ui/widget/dictquicklookup.lua23](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/dictquicklookup.lua#L23-L23)

---

# Utility-Functions-and-Text-Processing

# Utility Functions and Text Processing
Relevant source files
- [frontend/apps/reader/modules/readerdictionary.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerdictionary.lua)
- [frontend/apps/reader/modules/readerstyletweak.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerstyletweak.lua)
- [frontend/apps/reader/modules/readerwikipedia.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerwikipedia.lua)
- [frontend/ui/data/css_tweaks.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/data/css_tweaks.lua)
- [frontend/ui/data/settings_migration.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/data/settings_migration.lua)
- [frontend/ui/translator.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/translator.lua)
- [frontend/ui/widget/dictquicklookup.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/dictquicklookup.lua)
- [frontend/ui/widget/footnotewidget.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/footnotewidget.lua)
- [frontend/ui/widget/htmlboxwidget.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/htmlboxwidget.lua)
- [frontend/ui/widget/scrollhtmlwidget.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/scrollhtmlwidget.lua)
- [frontend/ui/widget/scrolltextwidget.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/scrolltextwidget.lua)
- [frontend/ui/widget/textboxwidget.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/textboxwidget.lua)
- [frontend/ui/wikipedia.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/wikipedia.lua)
- [frontend/util.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua)
- [spec/unit/util_spec.lua](https://github.com/koreader/koreader/blob/9e6b1587/spec/unit/util_spec.lua)

This page documents the utility functions provided by [frontend/util.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua) which serves as the central helper module for string manipulation, text processing, table operations, and file system utilities. These functions are used extensively throughout the KOReader codebase, particularly in dictionary lookup, text selection, and file management operations.

For dictionary integration that uses these utilities, see [Dictionary and Wikipedia Integration](/koreader/koreader/8.1-dictionary-and-wikipedia-integration). For text display widgets that rely on text processing functions, see [Text Display and Input Widgets](/koreader/koreader/7.5-screensaver-system).

## Overview

The `util` module ([frontend/util.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua)) provides a comprehensive set of helper functions organized into several categories:

```
util module
frontend/util.lua

String Manipulation

Text Processing & CJK

Table Operations

File System Utilities

Data Encoding & Formatting

stripPunctuation()
ltrim(), rtrim(), trim()
cleanupSelectedText()
gsplit()

splitToChars()
isCJKChar(), hasCJKChar()
splitToWords()
isSplittable()

tableEquals(), tableDeepCopy()
tableSize(), tableMerge()
arrayAppend(), arrayRemove()
bsearch() variants

makePath(), removePath()
getSafeFilename()
findFiles(), fileExists()
splitFilePathName()

partialMD5()
urlencode(), urldecode()
getFriendlySize()
htmlToPlainText()

ReaderDictionary

cleanSelection()

TextBoxWidget

FileManager

BookInfoManager
```

**Diagram: Overview of util module organization and key consumers**

The module uses external dependencies including:

- `ffi/utf8proc` (Utf8Proc) for Unicode normalization
- `ffi/util` (ffiUtil) for low-level UTF-8 operations and template strings
- `ffi/sha2` (md5) for MD5 hashing
- `libs/libkoreader-lfs` (lfs) for file system operations
- `gettext` for localization utilities

Sources: [frontend/util.lua1-18](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua#L1-L18)[frontend/apps/reader/modules/readerdictionary.lua28-32](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/reader/modules/readerdictionary.lua#L28-L32)[frontend/ui/widget/textboxwidget.lua32](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/textboxwidget.lua#L32-L32)

## String Manipulation

The util module provides a comprehensive set of string manipulation functions for cleaning, trimming, and processing text.

### Trimming Functions

**Diagram: Whitespace trimming operations**

```
Input String

ltrim()

rtrim()

trim()

cleanupSelectedText()

gsub('^%s*', '')
Remove leading whitespace

Find last non-whitespace
Return s:sub(1, n)

s:match('^%s*()')...
Remove both ends

Trim + collapse
spaces around newlines

' hello '

'hello'

'text
more'

'text
more'
```
FunctionPurposeImplementationUsage`ltrim(s)`Remove leading whitespace`s:gsub("^%s*", "")`Text input cleanup`rtrim(s)`Remove trailing whitespaceFind last non-space, `s:sub(1, n)`Line processing`trim(s)`Remove both leading and trailing`s:match"^%s*()"` patternGeneral text cleanup`cleanupSelectedText(text)`Trim and collapse whitespaceMultiple gsub patternsText selection normalization
The `cleanupSelectedText()` function is specifically designed for text selection in readers, removing leading/trailing whitespace, collapsing spaces around newlines, and reducing consecutive spaces to single spaces—behavior that matches how rendered text appears.

Sources: [frontend/util.lua31-70](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua#L31-L70)[frontend/util.lua60-70](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua#L60-L70)

### Punctuation and HTML Handling

**Diagram: Text cleaning pipeline**

```
Has HTML

No HTML

Yes

No

Raw Text

stripPunctuation()

Strip ASCII punctuation
gsub('^%p+', '')
gsub('%p+$', '')

Strip Unicode punctuation
U+2000 - U+206F range

HTML/Text Mixed

htmlToPlainTextIfHtml()

Detect HTML tags
< > brackets

htmlToPlainText()
Strip tags, decode entities

Return as-is

Double-encoded?
< > &

Decode again

Plain text

Book titles
Selected text
Search queries

Metadata fields
Description text
```

The `stripPunctuation()` function removes both ASCII punctuation (using Lua patterns `^%p+` and `%p+$`) and Unicode general punctuation marks in the range U+2000 to U+206F. The HTML detection in `htmlToPlainTextIfHtml()` uses a simple heuristic: if the text contains `<` followed by alphanumeric characters and `>`, it's treated as HTML.

Sources: [frontend/util.lua20-28](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua#L20-L28)[frontend/util.lua1196-1228](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua#L1196-L1228)

### String Splitting

The `gsplit()` function provides a versatile string splitting iterator that can optionally capture delimiters and empty entities:

```

```

Sources: [frontend/util.lua89-135](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua#L89-L135)[spec/unit/util_spec.lua19-57](https://github.com/koreader/koreader/blob/9e6b1587/spec/unit/util_spec.lua#L19-L57)

## Text Processing and CJK Support

KOReader provides extensive Unicode and CJK (Chinese, Japanese, Korean) text processing capabilities, essential for proper text selection, word wrapping, and dictionary lookup.

### UTF-8 Character Processing

**Diagram: UTF-8 character splitting and WTF-8 handling**

```
Yes

No

Yes, after high

No

No

Yes

splitToChars(text)

Iterate with UTF8_CHAR_PATTERN
'[%z\1-\127\194-\253][\128-\191]*'

Get charcode via
ffiUtil.utf8charcode()

High surrogate?
0xD800-0xDBFF

Store hi_surrogate

Low surrogate?
0xDC00-0xDFFF

Combine surrogates
charcode = (hi-0xD800)<<10
+ (lo-0xDC00) + 0x10000

Regular char
Add to table

unicodeCodepointToUtf8()
Convert to valid UTF-8

Next is low?

Add stored high as-is

WTF-8 Example:
'\xed\xa0\x80\xed\xbd\x85'
Invalid UTF-8 surrogates

Valid UTF-8:
'\xf0\x90\x8d\x85'
U+10345
```

The `splitToChars()` function handles WTF-8 (Wobbly Transformation Format-8), a superset of UTF-8 that includes UTF-16 surrogates. This is necessary because some poorly-formed producers or JSON decoders may emit invalid UTF-8 sequences that need to be corrected.

Sources: [frontend/util.lua453-520](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua#L453-L520)[frontend/util.lua473-489](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua#L473-L489)

### CJK Character Detection

**Diagram: CJK Unicode block coverage**

```
No

Yes

Found

None found

isCJKChar(c)

Character length
#c >= 3?

Return false
Codepoint < U+1100

ffiUtil.utf8charcode(c)

BMP Plane 0 Ranges

SIP Plane 2 Ranges

TIP Plane 3 Ranges

U+1100-U+11FF Hangul Jamo
U+2E80-U+9FFF CJK Main
U+A960-U+A97F Hangul Ext-A
U+AC00-U+D7AF Hangul Syllables
U+F900-U+FAFF CJK Compat
U+FE30-U+FE4F CJK Compat Forms
U+FF00-U+FFEF Halfwidth/Fullwidth

U+20000-U+2A6DF CJK Ext B
U+2A700-U+2B73F CJK Ext C
U+2B740-U+2B81F CJK Ext D
U+2B820-U+2CEAF CJK Ext E
U+2CEB0-U+2EBEF CJK Ext F
U+2F800-U+2FA1F CJK Compat Supp

U+30000-U+3134F CJK Ext G

hasCJKChar(str)

Iterate characters
with gsplit pattern

Call isCJKChar(c)
for each

Return true

Return false
```

The CJK detection algorithm uses a shortcut: since the smallest CJK codepoint is U+1100 (requiring 3 UTF-8 bytes), any character shorter than 3 bytes is immediately excluded. The function then checks against comprehensive Unicode block ranges covering all CJK scripts.

Sources: [frontend/util.lua522-567](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua#L522-L567)[frontend/util.lua525-532](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua#L525-L532)

### Word Splitting and Line Breaking

**Diagram: Text splitting for dictionary lookup and line wrapping**

```
No

Yes

CJK char

Space

Other

Yes

No

Yes

No

Yes

No

Yes

No

Yes

No

splitToWords(text)

gsplit('[%s%p]+', true)
Split on space/punctuation

Word has CJK?
hasCJKChar()

Add word as-is

gsplit('[\192-\255][\128-\191]+', true)
Split multi-byte chars

Add each CJK char
and space separately

Word list for
dictionary lookup

isSplittable(c, next_c, prev_c)

Character type?

Check CJK rules

Check space rules

Not splittable

cjk_non_splittable?
e.g., '—…‥'

Not splittable

next_c in
cjk_non_splittable_tailers?
e.g., '!%),.:;?'

prev_c in
cjk_non_splittable_leaders?
e.g., '$([{£¥'

Splittable

next_c in
non_splittable_space_tailers?
e.g., ':;,.!?'

prev_c in
non_splittable_space_leaders?
e.g., '([{$'
```

The `splitToWords()` function handles multilingual text by treating CJK characters individually (each character is a word) while keeping Western words intact. The `isSplittable()` function implements East Asian line breaking rules from [Wikipedia](https://en.wikipedia.org/wiki/Line_breaking_rules_in_East_Asian_languages), preventing line breaks before certain punctuation marks or after opening brackets.

Sources: [frontend/util.lua569-667](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua#L569-L667)[frontend/util.lua594-628](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua#L594-L628)[spec/unit/util_spec.lua59-112](https://github.com/koreader/koreader/blob/9e6b1587/spec/unit/util_spec.lua#L59-L112)

## Table Operations

The util module provides a comprehensive set of table manipulation functions for comparison, copying, searching, and array operations.

### Comparison and Copying

```
Yes

No

No

Yes

Yes

Yes

No

No

Yes

Yes

No

tableEquals(o1, o2, ignore_mt)

o1 == o2?

return true

Both tables?

return false

ignore_mt == false?

Has __eq metamethod?

Use metamethod

Compare all keys

Recursively compare values

tableDeepCopy(o, seen)

seen table prevents cycles

type(o) == 'table'?

Return o

seen[o]?

Return seen[o]

Create new table no

Copy keys and values recursively

Copy metatable

tableSize(t)

count = 0

for _ in pairs(t)

count = count + 1
```

**Diagram: Table comparison and copying operations**
FunctionPurposeImplementationNotes`tableEquals(o1, o2, ignore_mt)`Deep comparisonRecursive key/value checkRespects `__eq` metamethod unless `ignore_mt``tableDeepCopy(o, seen)`Deep copy with cycle detection`seen` table tracks visitedCopies metatables`tableSize(t)`Count table entries`pairs()` iteratorFor non-array tables`tableMerge(t1, t2)`Merge t2 into t1`t1[k] = v` for all k,v in t2Overwrites existing keys
Sources: [frontend/util.lua146-175](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua#L146-L175)[frontend/util.lua184-202](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua#L184-L202)[frontend/util.lua207-211](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua#L207-L211)[frontend/util.lua431-435](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua#L431-L435)

### Nested Table Access

```
No

Yes

Yes

No

Yes

No

tableGetValue(t, ...)

keys = {...}

q = t

For each key

type(q) == 'table'?

return nil

q = q[key]

q == nil?

More keys?

return q

tableSetValue(t, value, ...)

Create missing tables

Set value at last key

tableRemoveValue(t, ...)

Set to nil

Remove empty parent tables
```

**Diagram: Safe nested table access functions**

```
-- Example: tableGetValue
local settings = { display = { brightness = 80 } }
local brightness = util.tableGetValue(settings, "display", "brightness") -- 80
 
-- Example: tableSetValue  
util.tableSetValue(settings, 100, "display", "brightness")
-- Creates intermediate tables if needed
 
-- Example: tableRemoveValue
util.tableRemoveValue(settings, "display", "brightness")
-- Removes empty parent tables automatically
```

Sources: [frontend/util.lua217-257](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua#L217-L257)

### Array Operations

```
true

false

true

false

arrayAppend(t1, t2)

for _, v in ipairs(t2)

table.insert(t1, v)

arrayRemove(t, keep_cb)

j = 1, n = #t

for i = 1, n

keep_cb(t, i, j)?

t[j] = t[i]

j = j + 1

t[i] = nil

arrayReverse(t)

i = 1, j = #t

while i < j

swap t[i] and t[j]

i++, j--

arrayContains(t, v, cb)

for _k, _v in ipairs(t)

cb(_v, v)?

return _k

continue
```

**Diagram: Array manipulation functions**

The `arrayRemove()` function uses a swap-and-pop strategy while preserving order, making it more efficient than repeated `table.remove()` calls:

```
local foo = { "a", "b", "c", "b", "d" }
util.arrayRemove(foo, function(t, i, j)
    return t[i] ~= "b"  -- Keep everything except "b"
end)
-- Result: { "a", "c", "d" }
```

Sources: [frontend/util.lua262-331](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua#L262-L331)[frontend/util.lua286-304](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua#L286-L304)

### Binary Search

```
No

Yes

Yes

No

Yes

No

bsearch(array, value)

lo = 1, hi = #array

lo <= hi?

return nil, lo

mid = (lo+hi)>>1

array[mid] > value?

hi = mid - 1

array[mid] < value?

lo = mid + 1

return mid

bsearch_left(array, value)

Find leftmost insertion point

array[mid] >= value

bsearch_right(array, value)

Find rightmost insertion point

array[mid] > value
```

**Diagram: Binary search variants for sorted arrays**
FunctionPurposeReturn ValueUse Case`bsearch(array, value)`Find exact match`index` or `nil, insertion_point`Membership testing`bsearch_left(array, value)`Leftmost insertion point`index`Insert maintaining order`bsearch_right(array, value)`Rightmost insertion point`index`Range queries
All binary search functions use `bit.rshift(lo + hi, 1)` for mid calculation to avoid overflow.

Sources: [frontend/util.lua369-425](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua#L369-L425)

## File System Utilities

The util module provides extensive file system operations for path manipulation, file existence checks, and safe filename generation.

### Path Operations

```
Yes

No

No

Yes

No

Yes

Yes

No ENOTEMPTY

Yes

No

Yes

makePath(path)

Directory exists?

return true

Split into components

For each component

Component dir exists?

lfs.mkdir(component)

Continue

Success?

return nil, err

removePath(path)

component = path

repeat

Is directory?

lfs.rmdir(component)

Success?

return nil, err

component = parent

parent == '.' or '/'?

return true
```

**Diagram: Directory creation and removal (like mkdir -p and rmdir -p)**
FunctionPurposeBehaviorNotes`makePath(path)`Create directory treeLike `mkdir -p`Creates intermediate dirs, no error if exists`removePath(path)`Remove empty dirsLike `rmdir -p`Removes only empty directories`removeFile(file)`Delete fileLike `rm`Validates file mode before removal`fileExists(path)`Check file exists`io.open()` testReturns `true` if file is readable`pathExists(path)`Check path exists`lfs.attributes()`Works for files or directories`directoryExists(path)`Check directory`lfs.attributes(path, "mode")`Returns `true` if mode == "directory"
Sources: [frontend/util.lua855-920](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua#L855-L920)

### Safe Filename Generation

```

```

**Diagram: Safe filename generation with filesystem-specific rules**

The function adapts to filesystem limitations:

- **VFAT/FAT32**: Prohibits `\ / : * ? " < > |` and trailing dots/spaces (Windows compatibility)
- **Other filesystems**: Only prohibits `/` (Unix standard)
- Defaults to VFAT rules on Android to be safe

```
-- Example usage
local safe = util.getSafeFilename("Book: Title (2023)", "/mnt/sdcard", 240, 10)
-- On VFAT: "Book_ Title (2023)"
-- The colon is replaced with underscore
 
local long_title = "Very Long Book Title That Exceeds The Filesystem Limit For Filenames"
local safe = util.getSafeFilename(long_title, path, 240, 10)
-- Result is truncated to 240 chars with proper UTF-8 boundaries
```

Sources: [frontend/util.lua990-1027](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua#L990-L1027)[frontend/util.lua962-968](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua#L962-L968)

### File Path Parsing

```
No

Yes

No

Yes

Yes

No

Yes

No

splitFilePathName(file)

Has '/' ?

return '', file

file:match('(./)(.)' )

directory, filename

splitFileNameSuffix(file)

Has '.' ?

return file, ''

file:match('(.)%.(.)')

filename, extension

getFileNameSuffix(file)

splitFileNameSuffix()

return suffix

getScriptType(file)

getFileNameSuffix()

ext == 'sh'?

return 'shell'

ext == 'py'?

return 'python'

return nil
```

**Diagram: File path and extension parsing functions**

Sources: [frontend/util.lua1034-1068](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua#L1034-L1068)

### Directory Scanning

The `findFiles()` function recursively scans directories with optional file count limits:

```
Yes

No

Yes, recursive

No

Yes

findFiles(dir, cb, recursive, max_files)

scan(current)

lfs.dir(current)

for f in iter

max_files reached?

return

path = current/f

lfs.attributes(path)

mode == 'directory'?

scan(path)

mode == 'file' or 'link'?

cb(path, f, attr)

count++
```

**Diagram: Recursive directory scanning with callback**

```
-- Example: Find all EPUB files
local epub_files = {}
util.findFiles("/mnt/books", function(path, filename, attr)
    if filename:match("%.epub$") then
        table.insert(epub_files, path)
    end
end, true, 1000) -- Recursive, max 1000 files
```

The callback receives three arguments: full path, filename, and `lfs.attributes()` result. The scanner follows symlinks (checking their attributes) but doesn't prevent cycles.

Sources: [frontend/util.lua788-810](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua#L788-L810)

## Data Encoding and Formatting

### Size and Time Formatting

```
Yes

No

Yes

No

Yes

No

Yes

No

getFriendlySize(size)

size < 1024?

return 'size B'

size < 1024^2?

return 'size/1024 KB'

size < 1024^3?

return 'size/1024^2 MB'

return 'size/1024^3 GB'

secondsToClock(sec, withoutSeconds)

h = sec / 3600

m = (sec % 3600) / 60

s = sec % 60

withoutSeconds?

return 'h:m'

return 'h:m:s'
```

**Diagram: Human-friendly size and time formatting**
FunctionPurposeExample OutputNotes`getFriendlySize(size)`Format bytes"1.5 MB", "523 KB"Uses 1024-based units`getFormattedSize(size)`Format with separators"1,234,567 B"Adds thousand separators`secondsToClock(sec, nohours)`Format duration"1:23:45", "23:45"Optionally hide hours
Sources: [frontend/util.lua1073-1115](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua#L1073-L1115)

### URL Encoding

```
Yes

No

urlencode(str)

For each byte

Unreserved char?
A-Za-z0-9-_.~

Keep as-is

Convert to %XX

string.format('%%%02X', byte)

urldecode(str)

gsub('%%(%x%x)')

tonumber(hex, 16)

string.char(num)
```

**Diagram: URL percent-encoding and decoding**

```
local encoded = util.urlencode("Hello World!")
-- Result: "Hello%20World%21"
 
local decoded = util.urldecode("Hello%20World%21")
-- Result: "Hello World!"
```

Sources: [frontend/util.lua1325-1346](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua#L1325-L1346)

### HTML Processing

```
No

Yes

Yes

No

htmlToPlainTextIfHtml(text)

Has HTML tags?
< alphanumeric >

return text

htmlToPlainText(text)

Strip tags: gsub('<[^>]*>', '')

Decode entities

&&xHHHH; -> char

&&DDDD; -> char

&entity; -> char
< > & " etc.

Still has encoded?
< > &

Decode again (double-encoded)

Clean whitespace
```

**Diagram: HTML to plain text conversion with entity decoding**

The HTML processor handles:

- **Tag stripping**: Removes all `<tag>` constructs
- **Hex entities**: `&#xA0;` → character
- **Decimal entities**: `&#160;` → character
- **Named entities**: `&lt;``&gt;``&amp;``&quot;``&apos;``&nbsp;`
- **Double-encoding**: Automatically detects and decodes again if needed

Sources: [frontend/util.lua1196-1320](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua#L1196-L1320)

### MD5 Hashing

```
No

Yes

partialMD5(file)

io.open(file, 'rb')

fh:seek('end')

size = fh:seek()

size > 100KB?

Read entire file

fh:seek('set', size/2 - 50KB)

Read 100KB from middle

md5(sample)

return hash_hex
```

**Diagram: Partial MD5 for large file identification**

The `partialMD5()` function optimizes for large files by only hashing a 100KB sample from the middle of the file when size > 100KB. For smaller files, it hashes the entire content. This provides fast file identification for duplicates while remaining unique enough for practical purposes.

Sources: [frontend/util.lua1120-1144](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua#L1120-L1144)

### Unicode Conversion

```
Yes

No

Yes

No

Yes

No

unicodeCodepointToUtf8(c)

c < 0x80?

1 byte: 0xxxxxxx

c < 0x800?

2 bytes: 110xxxxx 10xxxxxx

c < 0x10000?

3 bytes: 1110xxxx 10xxxxxx 10xxxxxx

4 bytes: 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
```

**Diagram: Unicode codepoint to UTF-8 encoding**

This function manually constructs UTF-8 byte sequences using bitwise operations (`lshift`, `rshift`, `band`, `bor`). Used internally by `splitToChars()` for WTF-8 surrogate pair conversion.

Sources: [frontend/util.lua1351-1370](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua#L1351-L1370)[frontend/util.lua13-16](https://github.com/koreader/koreader/blob/9e6b1587/frontend/util.lua#L13-L16)

## Usage Patterns

### Typical Geometry Operations

```
-- Widget positioning
local button_area = Geom:new{
    x = 100, y = 50, 
    w = Screen:scaleBySize(200), 
    h = Screen:scaleBySize(80)
}
 
-- Hit testing
if touch_area:intersectWith(button_area) then
    -- Handle button press
end
 
-- Layout calculation  
local container_bounds = Geom.boundingBox({widget1.dimen, widget2.dimen})
```

### Typical Time Operations

```
-- Animation timing
local start_time = time.now()
local duration = time.ms(300) -- 300ms animation
 
-- Performance measurement
local operation_start = time.now()
-- ... do work ...
local elapsed = time.since(operation_start)
print("Operation took: " .. time.to_ms(elapsed) .. "ms")
 
-- Event scheduling
local timeout = time.s(5) -- 5 second timeout
UIManager:scheduleIn(timeout, function() 
    -- Handle timeout
end)
```

Sources: [frontend/ui/geometry.lua6-7](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/geometry.lua#L6-L7)[frontend/ui/time.lua76-86](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/time.lua#L76-L86)

---

# Translation-and-Localization

# Translation and Localization
Relevant source files
- [frontend/apps/filemanager/filemanagersetdefaults.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/apps/filemanager/filemanagersetdefaults.lua)
- [frontend/ui/elements/screen_dpi_menu_table.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/elements/screen_dpi_menu_table.lua)
- [frontend/ui/language.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/language.lua)
- [frontend/ui/widget/inputdialog.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/inputdialog.lua)
- [frontend/ui/widget/inputtext.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/inputtext.lua)
- [frontend/ui/widget/multiinputdialog.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/multiinputdialog.lua)
- [frontend/ui/widget/virtualkeyboard.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua)
- [reader.lua](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua)

This document describes KOReader's internationalization (i18n) and localization (l10n) system, which enables the application to display UI text in multiple languages and support different text rendering requirements such as right-to-left (RTL) scripts. The system encompasses translation management via gettext, language selection, RTL language support with UI mirroring, and language-specific keyboard layouts.

For information about text rendering and font handling, see the Font System documentation. For virtual keyboard implementation details, see [User Interface Components](/koreader/koreader/7-user-interface-components) and specifically [Input and Virtual Keyboard](/koreader/koreader/7.3-input-and-virtual-keyboard).

## System Overview

KOReader's localization system is built on several interconnected components that work together to provide a multilingual user experience:

```
Text Rendering

Input System

RTL Support

Language Management

Translation Infrastructure

compiled to

loaded by

_() function

read on startup

changeLang()

isLanguageRTL()

UI mirroring

language code

loads layout

gettext module
Translation loader

*.po files
Translation sources

*.mo files
Compiled translations

Language module
frontend/ui/language.lua

G_reader_settings
language setting

Bidi module
frontend/ui/bidi.lua

languages_rtl table
RTL language list

VirtualKeyboard
lang_to_keyboard_layout

Keyboard layouts
frontend/ui/data/keyboardlayouts/

TextWidget/TextBoxWidget
lang, para_direction_rtl

InputText
auto_para_direction
```

**Diagram 1: Translation and Localization System Architecture**

Sources: [frontend/ui/language.lua1-176](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/language.lua#L1-L176)[reader.lua50-56](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L50-L56)[reader.lua165-170](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L165-L170)[frontend/ui/widget/virtualkeyboard.lua807-904](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua#L807-L904)

## gettext Integration

KOReader uses the standard GNU gettext system for managing translations. The `gettext` module provides the foundation for all UI string translations.

### Translation Loading and Usage

```
UI Components
*.mo files
gettext module
G_reader_settings
reader.lua
UI Components
*.mo files
gettext module
G_reader_settings
reader.lua
All UI strings use _() wrapper
readSetting("language")
lang_locale (e.g., "de", "ar")
require("gettext")
_.changeLang(lang_locale)
Load translation catalog
Translation mappings
_("English text")
Translated text
```

**Diagram 2: Translation Loading Flow at Startup**

The translation system is initialized early in the application lifecycle:

1. **Early Initialization**: [reader.lua50-56](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L50-L56) reads the language setting before loading any UI components
2. **Language Override**: Environment variable `KO_RTL` can be set to test RTL behavior with Arabic
3. **Translation Function**: The `_()` function (from gettext) is used throughout the codebase to mark translatable strings

Sources: [reader.lua38-56](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L38-L56)[frontend/ui/language.lua3](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/language.lua#L3-L3)

### Translation File Structure
File TypeLocationPurpose`*.po`Source repositoryHuman-editable translation sources`*.mo`Compiled during buildBinary translation catalogs loaded at runtimeBuild processMakefileConverts `.po` files to `.mo` files
Sources: High-level diagrams mention

## Language Module

The `Language` module ([frontend/ui/language.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/language.lua)) provides a high-level interface for language management and is the primary API for language-related operations.

### Supported Languages

The module maintains a comprehensive mapping of language codes to display names:

```
Language Module API

Language Names Mapping

Language codes:
en, de, fr, ar, zh_CN, etc.

Display names:
English, Deutsch, Français,
عربى, 简体中文, etc.

getLanguageName(lang_locale)

isLanguageRTL(lang_locale)

changeLanguage(lang_locale)

getLangMenuTable()
```

**Diagram 3: Language Module API and Data Structures**

Sources: [frontend/ui/language.lua5-113](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/language.lua#L5-L113)

The `language_names` table [frontend/ui/language.lua6-57](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/language.lua#L6-L57) contains mappings for 50+ languages, including:
Language CodeDisplay NameScript Type`en`EnglishLTR`de`DeutschLTR`fr`FrançaisLTR`ar`عربىRTL`he`עִבְרִיתRTL`fa`فارسیRTL`ja`日本語LTR (CJK)`zh_CN`简体中文LTR (CJK)`ko_KR`한국어LTR (CJK)
### Language Selection Flow

```
UIManager
G_reader_settings
gettext
Language module
Language Menu
User
UIManager
G_reader_settings
gettext
Language module
Language Menu
User
Restart required for full effect
Select language
changeLanguage(lang_locale)
_.changeLang(lang_locale)
Translation loaded
saveSetting("language", lang_locale)
Setting saved
askForRestart()
Show restart dialog
```

**Diagram 4: Language Change Process**

The language change process is handled by [frontend/ui/language.lua95-100](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/language.lua#L95-L100):

1. `_.changeLang(lang_locale)` updates the gettext translation catalog
2. Setting is persisted to `G_reader_settings`
3. `UIManager:askForRestart()` prompts the user to restart KOReader
4. On restart, [reader.lua50-56](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L50-L56) reads the new language setting and initializes accordingly

Sources: [frontend/ui/language.lua95-100](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/language.lua#L95-L100)

### Language Menu Generation

The `getLangMenuTable()` function [frontend/ui/language.lua115-173](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/language.lua#L115-L173) dynamically generates the language selection menu with:

- Radio button behavior (only one language selected at a time)
- Checked state based on current `G_reader_settings:readSetting("language")`
- Callback that triggers `changeLanguage()`
- Cached menu table for performance

Sources: [frontend/ui/language.lua115-173](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/language.lua#L115-L173)

## RTL Language Support and UI Mirroring

KOReader provides comprehensive support for right-to-left (RTL) languages including Arabic, Hebrew, Persian, and Urdu. This support includes both text rendering direction and UI layout mirroring.

### RTL Language Detection

The `languages_rtl` table [frontend/ui/language.lua62-76](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/language.lua#L62-L76) lists all supported RTL languages:

```
languages_rtl = {
    ar  = true, -- Arabic
    arz = true, -- Egyptian Arabic
    ckb = true, -- Sorani (Central Kurdish)
    dv  = true, -- Divehi
    fa  = true, -- Persian
    he  = true, -- Hebrew
    ks  = true, -- Kashmiri
    ku  = true, -- Kurdish
    ps  = true, -- Pashto
    sd  = true, -- Sindhi
    ug  = true, -- Uyghur
    ur  = true, -- Urdu
    yi  = true, -- Yiddish
}
```

The `isLanguageRTL()` function [frontend/ui/language.lua83-93](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/language.lua#L83-L93) checks if a given language code represents an RTL language:

- Extracts the base language code (before `_` separator)
- Looks up the code in the `languages_rtl` table
- Returns `true` for RTL languages, `false` otherwise

Sources: [frontend/ui/language.lua62-93](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/language.lua#L62-L93)

### Bidi Setup and UI Mirroring

The Bidi (bidirectional text) module is initialized early in the application lifecycle:

```
Bidi Configuration

Startup Sequence

RTL language

RTL language

reader.lua startup

Read language setting

Bidi.setup(lang_locale)

Load UIManager and widgets

Check if language is RTL

Enable UI mirroring

Configure text shaping

Warning: UIManager and widgets
must not be loaded before
Bidi.setup()
```

**Diagram 5: Bidi Initialization Sequence**

Critical timing requirement from [reader.lua165-170](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L165-L170):

- `Bidi.setup(lang_locale)` must be called **before** loading `UIManager` and any widgets
- Widgets may cache Bidi mirroring settings during initialization
- Loading widgets before Bidi setup results in incorrect UI layout

When an RTL language is active:

- **UI Mirroring**: Menu items, buttons, and containers are mirrored horizontally
- **Text Direction**: Text flows right-to-left within text widgets
- **Icon Positions**: Icons and indicators appear on the right side instead of left
- **Gesture Interpretation**: Swipe directions may be inverted for natural navigation

Sources: [reader.lua165-170](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L165-L170)[frontend/ui/language.lua83-93](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/language.lua#L83-L93)

## Keyboard Layout Integration

KOReader's virtual keyboard system supports language-specific layouts that match the selected UI language or allow independent keyboard language selection.

### Language-to-Keyboard Mapping

```
Keyboard Layouts

VirtualKeyboard

Language Settings

G_reader_settings
language

G_reader_settings
keyboard_layout

G_reader_settings
keyboard_layout_default

G_reader_settings
keyboard_layouts array

lang_to_keyboard_layout
mapping table

getKeyboardLayout()

setKeyboardLayout(layout)

en_keyboard

de_keyboard

ar_keyboard

ru_keyboard

zh_keyboard

35+ other layouts
```

**Diagram 6: Keyboard Layout Selection System**

The `lang_to_keyboard_layout` mapping [frontend/ui/widget/virtualkeyboard.lua807-838](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua#L807-L838) associates language codes with keyboard layout files:
Language CodeKeyboard Layout FileNotes`en``en_keyboard`Default fallback`ar``ar_keyboard`RTL layout`de``de_keyboard`Includes umlauts`fr``fr_keyboard`AZERTY layout`ru``ru_keyboard`Cyrillic`ja``ja_keyboard`Complex input with submenu`zh``zh_keyboard`Complex input with submenu`zh_CN``zh_CN_keyboard`Simplified Chinese
Sources: [frontend/ui/widget/virtualkeyboard.lua807-844](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua#L807-L844)

### Keyboard Layout Selection Logic

The `getKeyboardLayout()` function [frontend/ui/widget/virtualkeyboard.lua897-904](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua#L897-L904) determines which keyboard layout to use:

```
false AND not forced

true OR forced

getKeyboardLayout()

Check keyboard_remember_layout setting

Check keyboard_state.force_current_layout

Use keyboard_layout_default
or keyboard_layout

Read keyboard_layout setting

Save keyboard_layout to settings

Return keyboard_layout
```

**Diagram 7: Keyboard Layout Resolution Flow**

Layout selection behavior:

1. If `keyboard_remember_layout` is false (and not forced), uses `keyboard_layout_default` or falls back to `keyboard_layout`
2. Otherwise, uses the current `keyboard_layout` setting
3. The result is saved back to `keyboard_layout` setting
4. If no setting exists, falls back to the UI language from `G_reader_settings:readSetting("language")`

Sources: [frontend/ui/widget/virtualkeyboard.lua897-904](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua#L897-L904)

### Multi-Layout Keyboard Switching

Users can enable multiple keyboard layouts and switch between them:

```
Keyboard Layout
G_reader_settings
UTF8 Mode Key (🌐)
VirtualKeyboard
User
Keyboard Layout
G_reader_settings
UTF8 Mode Key (🌐)
VirtualKeyboard
User
Keyboard height may change
Tap globe key
Read keyboard_layouts array
[en, de, ru]
Find current in array
Select next layout
setKeyboardLayout(next_layout)
init() - reload keyboard
Load new layout file
_refresh(true) - flash update
onKeyboardHeightChanged()
Hold globe key
Show popup with all layouts
```

**Diagram 8: Multi-Layout Keyboard Switching**

The UTF8 mode key (globe icon 🌐) provides layout switching [frontend/ui/widget/virtualkeyboard.lua79-126](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua#L79-L126):

- **Tap**: Cycles through enabled layouts in `keyboard_layouts` array
- **Hold**: Shows popup with all enabled layouts or opens layout selection dialog
- **Swipe**: Executes directional shortcuts to specific layouts (if configured)

Sources: [frontend/ui/widget/virtualkeyboard.lua79-126](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua#L79-L126)[frontend/ui/widget/virtualkeyboard.lua906-921](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua#L906-L921)

### Complex Input Methods

Some languages require special input handling beyond simple character mapping:

The `lang_has_submenu` table [frontend/ui/widget/virtualkeyboard.lua840-844](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua#L840-L844) identifies languages with complex input:

```
lang_has_submenu = {
    ja = true,   -- Japanese (hiragana, katakana, kanji conversion)
    zh = true,   -- Chinese (pinyin input with character selection)
    zh_CN = true -- Simplified Chinese
}
```

These keyboards implement additional features:

- **Japanese**: Multiple input modes (hiragana, katakana, romaji)
- **Chinese**: Pinyin input with candidate character/word selection
- **Wrapper Functions**: `keyboard.wrapInputBox()` can add special UI elements

Sources: [frontend/ui/widget/virtualkeyboard.lua840-844](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua#L840-L844)[frontend/ui/widget/virtualkeyboard.lua870-872](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua#L870-L872)

## Text Rendering and Direction Handling

Text widgets in KOReader support language-specific rendering requirements including paragraph direction, alignment, and text shaping.

### Text Widget Language Parameters

Multiple text widget classes support language-aware rendering:

```
TextBoxWidget

+string lang

+bool para_direction_rtl

+bool auto_para_direction

+bool alignment_strict

InputText

+string lang

+bool para_direction_rtl

+bool auto_para_direction

+bool alignment_strict

InputDialog

+string lang

+bool para_direction_rtl

+bool auto_para_direction

+bool alignment_strict

MultiInputDialog

+string lang

+bool para_direction_rtl

+bool auto_para_direction

+bool alignment_strict
```

**Diagram 9: Text Widget Language Parameter Inheritance**
ParameterTypePurpose`lang`stringLanguage code for text shaping (e.g., "ar", "he")`para_direction_rtl`booleanForce RTL paragraph direction`auto_para_direction`booleanAuto-detect direction from text content`alignment_strict`booleanStrict alignment rules for mixed-direction text`alignment`stringText alignment: "left", "right", "center"`justified`booleanJustify text to both margins
Sources: [frontend/ui/widget/inputtext.lua48-54](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/inputtext.lua#L48-L54)[frontend/ui/widget/inputdialog.lua185-190](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/inputdialog.lua#L185-L190)[frontend/ui/widget/multiinputdialog.lua152-157](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/multiinputdialog.lua#L152-L157)

### Language-Aware Text Input

The `InputText` widget [frontend/ui/widget/inputtext.lua](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/inputtext.lua) propagates language settings through the widget hierarchy:

1. **InputDialog** receives language parameters from caller
2. **InputText** is initialized with these parameters [frontend/ui/widget/inputdialog.lua361-389](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/inputdialog.lua#L361-L389)
3. **TextBoxWidget** or **ScrollTextWidget** inherits the settings [frontend/ui/widget/inputtext.lua522-565](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/inputtext.lua#L522-L565)
4. Text is rendered with proper shaping for the specified language

```
new{lang='ar'}

creates

creates

renders

Caller Code

InputDialog
lang='ar'
auto_para_direction=true

InputText
inherits lang settings

TextBoxWidget
applies text shaping

Rendered Text
RTL paragraph flow
```

**Diagram 10: Language Parameter Flow in Text Input**

Sources: [frontend/ui/widget/inputtext.lua433-565](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/inputtext.lua#L433-L565)[frontend/ui/widget/inputdialog.lua361-389](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/inputdialog.lua#L361-L389)

## Configuration and Settings

The localization system is configurable through various settings stored in `G_reader_settings`:
Setting KeyTypePurposeDefault`language`stringCurrent UI languageSystem locale or "en"`keyboard_layout`stringCurrent keyboard layoutMatches `language``keyboard_layout_default`stringDefault keyboard layoutnil`keyboard_layouts`arrayEnabled keyboard layouts for switchingnil`keyboard_remember_layout`booleanRemember last used keyboard layouttrue`virtual_keyboard_enabled`booleanEnable virtual keyboardtrue
Sources: [frontend/ui/language.lua95-100](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/language.lua#L95-L100)[frontend/ui/widget/virtualkeyboard.lua897-904](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua#L897-L904)[frontend/ui/widget/inputtext.lua145](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/inputtext.lua#L145-L145)[frontend/ui/widget/inputdialog.lua226-228](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/inputdialog.lua#L226-L228)

### Debug and Testing

The system includes debugging capabilities:

1. **RTL Testing**: Set environment variable `KO_RTL=1` to force Arabic language [reader.lua52](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L52-L52)
2. **Keyboard State**: `keyboard_state.force_current_layout` flag for testing [frontend/ui/widget/virtualkeyboard.lua27-28](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua#L27-L28)
3. **Language Verification**: Can verify translation loading through logging

Sources: [reader.lua50-56](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L50-L56)[frontend/ui/widget/virtualkeyboard.lua26-28](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua#L26-L28)

## Translation Workflow

The typical workflow for adding or updating translations:

```
Source Code
_('English text')

Extract strings
xgettext

koreader.pot
Translation template

Update *.po files
msgmerge

Translators edit *.po

Compile to *.mo
msgfmt

Build process
Makefile

Install *.mo files
Application bundle

Runtime loading
gettext
```

**Diagram 11: Translation File Processing Pipeline**

Sources: High-level system description

## Summary

KOReader's translation and localization system provides comprehensive multilingual support through:

1. **gettext Integration**: Standard translation system with `.po`/`.mo` files
2. **Language Module**: High-level API for language management and selection
3. **RTL Support**: Full bidirectional text rendering and UI mirroring
4. **Keyboard Layouts**: 35+ language-specific keyboard layouts with multi-layout switching
5. **Text Rendering**: Language-aware text shaping and direction handling

The system is initialized early in application startup [reader.lua50-56](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L50-L56) and affects all UI components through the `_()` translation function, Bidi configuration, and language parameters passed to text widgets.

Sources: [reader.lua50-170](https://github.com/koreader/koreader/blob/9e6b1587/reader.lua#L50-L170)[frontend/ui/language.lua1-176](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/language.lua#L1-L176)[frontend/ui/widget/virtualkeyboard.lua807-921](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/virtualkeyboard.lua#L807-L921)[frontend/ui/widget/inputtext.lua48-565](https://github.com/koreader/koreader/blob/9e6b1587/frontend/ui/widget/inputtext.lua#L48-L565)