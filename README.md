# Compose Code Editor (Android)

## Description

**Compose Code Editor** is a code highlighting / editing library for compose

## Usage

```kotlin
// Step 1. Declare Language & Code
val language = CodeLang.Java
val code = """             
    package com.wakaztahir.codeeditor
    
    public static void main(String[] args){
        System.out.println("Hello World")
    }
""".trimIndent()

// Step 2. Create Parser & Theme
val parser = remember { PrettifyParser() }
var themeState by remember { mutableStateOf(CodeThemeType.Monokai) }
val theme = remember(themeState) { themeState.theme() }
```

### Using Text Composable

```kotlin
// Step 3. Parse Code For Highlighting
val parsedCode = remember {
    parseCodeAsAnnotatedString(
        parser = parser,
        theme = theme,
        lang = language,
        code = code
    )
}

// Step 4. Display In A Text Composable
Text(parsedCode)
```

### Using TextField Composable

```kotlin
// Step 3. Create TextFieldValue State For Parsed Code 
var parsedCode by remember {
    mutableStateOf(
        TextFieldValue(
            parseCodeAsAnnotatedString(
                parser = parser,
                theme = theme,
                lang = language,
                code = code
            )
        )
    )
}

// Step 4. Display In A TextField Composable
TextField(
    modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colors.background),
    value = parsedCode,
    onValueChange = {
        parsedCode = it.copy(
            parseCodeAsAnnotatedString(
                parser = parser,
                theme = theme,
                lang = language,
                code = it.text
            )
        )
    }
)
```

## List of available languages & their extensions

C/C++/Objective-C (```"c"```, ```"cc"```, ```"cpp"```, ```"cxx"```, ```"cyc"```, ```"m"```),
C# (```"cs"```), Java (```"java"```), Bash (```"bash"```, ```"bsh"```, ```"csh"```, ```"sh"```),
Python (```"cv"```, ```"py"```, ```"python"```), Perl (```"perl"```, ```"pl"```, ```"pm"```),
Ruby (```"rb"```, ```"ruby"```), JavaScript (```"javascript"```, ```"js"```),
CoffeeScript (```"coffee"```), Rust (```"rc"```, ```"rs"```, ```"rust"```), Appollo (```"apollo"```
, ```"agc"```, ```"aea"```), Basic (```"basic"```, ```"cbm"```), Clojure (```"clj"```),
Css (```"css"```), Dart (```"dart"```), Erlang (```"erlang"```, ```"erl"```), Go (```"go"```),
Haskell (```"hs"```), Lisp (```"cl"```, ```"el"```, ```"lisp"```, ```"lsp"```, ```"scm"```
, ```"ss"```, ```"rkt"```), Llvm (```"llvm"```, ```"ll"```), Lua (```"lua"```),
Matlab (```"matlab"```), ML (OCaml, SML, F#, etc) (```"fs"```, ```"ml"```), Mumps (```"mumps"```),
N (```"n"```, ```"nemerle"```), Pascal (```"pascal"```), R (```"r"```, ```"s"```, ```"R"```
, ```"S"```, ```"Splus"```), Rd (```"Rd"```, ```"rd"```), Scala (```"scala"```), SQL (```"sql"```),
Tex (```"latex"```, ```"tex"```), VB (```"vb"```, ```"vbs"```), VHDL (```"vhdl"```, ```"vhd"```),
Tcl (```"tcl"```), Wiki (```"wiki.meta"```), XQuery (```"xq"```, ```"xquery"```), YAML (```"yaml"```
, ```"yml"```), Markdown (```"md"```, ```"markdown"```), formats (```"json"```, ```"xml"```
, ```"proto"```), ```"regex"```

Didn't found yours? Please, open issue to show your interest & I'll try to add this language in next
releases.

## List of available themes

* Default
* [Monokai](http://www.eclipsecolorthemes.org/?view=theme&id=386)

## Issues

* Does not support kotlin yet , but basic syntax highlighting can be achieved by using another
  language
* Lack of themes
* Everytime user types code in a text field , all the code is parsed again rather than only the
  changed lines which makes it a little inefficient , This is due to compose not supporting
  multiline text editing yet , so it will be fixed in future