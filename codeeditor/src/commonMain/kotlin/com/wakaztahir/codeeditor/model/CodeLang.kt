package com.wakaztahir.codeeditor.model

import com.wakaztahir.codeeditor.prettify.lang.*
import com.wakaztahir.codeeditor.prettify.parser.Prettify

enum class CodeLang(val langProvider: Prettify.LangProvider?, val value: List<String>) {
    Default(null, listOf("default-code", "default-markup")),
    HTML(null, listOf("default-markup")),
    C(null, listOf("c")),
    CPP(null, listOf("cpp")),
    ObjectiveC(null, listOf("cxx")),
    CSharp(null, listOf("cs")),
    Java(null, listOf("java")),
    Bash(null, listOf("bash")),
    Python(null, listOf("python")),
    Perl(null, listOf("perl")),
    Ruby(null, listOf("ruby")),
    JavaScript(null, listOf("javascript")),
    CoffeeScript(null, listOf("coffee")),
    Rust(null, listOf("rust")),
    Appollo({ LangAppollo() },LangAppollo.fileExtensions),
    Basic({ LangBasic() },LangBasic.fileExtensions),
    Clojure({ LangClj() },LangClj.fileExtensions),
    CSS({ LangCss() },LangCss.fileExtensions),
    Dart({ LangDart() },LangDart.fileExtensions),
    Erlang({ LangErlang() },LangErlang.fileExtensions),
    Go({ LangGo() },LangGo.fileExtensions),
    Haskell({ LangHs() },LangHs.fileExtensions),
    Lisp({ LangLisp() },LangLisp.fileExtensions),
    Llvm({ LangLlvm() },LangLlvm.fileExtensions),
    Lua({ LangLua() },LangLua.fileExtensions),
    Matlab({ LangMatlab() },LangMatlab.fileExtensions),
    ML({ LangMl() },LangMl.fileExtensions),
    OCAML(null, listOf("ml")),
    SML(null, listOf("ml")),
    FSharp(null, listOf("fs")),
    Mumps({ LangMumps() },LangMumps.fileExtensions),
    N({ LangN() },LangN.fileExtensions),
    Pascal({ LangPascal() },LangPascal.fileExtensions),
    R({ LangR() },LangR.fileExtensions),
    Rd({ LangRd() },LangRd.fileExtensions),
    Scala({ LangScala() },LangScala.fileExtensions),
    SQL({ LangSql() },LangSql.fileExtensions),
    Tex({ LangTex() },LangTex.fileExtensions),
    VB({ LangVb() },LangVb.fileExtensions),
    VHDL({ LangVhdl() },LangVhdl.fileExtensions),
    Tcl({ LangTcl() },LangTcl.fileExtensions),
    Wiki({ LangWiki() },LangWiki.fileExtensions),
    XQuery({ LangXq() },LangXq.fileExtensions),
    YAML({ LangYaml() },LangYaml.fileExtensions),
    Markdown({ LangMd() },LangMd.fileExtensions),
    JSON(null,listOf("json")),
    XML(null,listOf("xml")),
    Proto(null,listOf("proto")),
    RegEx(null,listOf("regex")),
    Ex({ LangEx() },LangEx.fileExtensions),
    Kotlin({ LangKotlin() },LangKotlin.fileExtensions),
    Lasso({ LangLasso() },LangLasso.fileExtensions),
    Logtalk({ LangLogtalk() },LangLogtalk.fileExtensions),
    Swift({ LangSwift() },LangSwift.fileExtensions),
}