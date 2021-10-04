// Copyright (C) 2011 Martin S.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.wakaztahir.common.prettify.lang

import com.wakaztahir.common.prettify.lang.Lang
import com.wakaztahir.common.prettify.parser.Prettify
import com.wakaztahir.common.prettify.lang.LangCss.LangCssKeyword
import com.wakaztahir.common.prettify.lang.LangCss.LangCssString
import com.wakaztahir.common.prettify.lang.LangMatlab
import com.wakaztahir.common.prettify.lang.LangMatlab.LangMatlabIdentifier
import com.wakaztahir.common.prettify.lang.LangMatlab.LangMatlabOperator
import com.wakaztahir.common.prettify.lang.LangN
import com.wakaztahir.common.prettify.lang.LangWiki.LangWikiMeta
import com.wakaztahir.common.prettify.lang.LangXq
import java.util.*
import java.util.regex.Pattern

/**
 * This is similar to the lang-tex.js in JavaScript Prettify.
 *
 * All comments are adapted from the JavaScript Prettify.
 *
 *
 *
 * Support for tex highlighting as discussed on
 * [meta.tex.stackexchange.com](http://meta.tex.stackexchange.com/questions/872/text-immediate-following-double-backslashes-is-highlighted-as-macro-inside-a-code/876#876).
 *
 * @author Martin S.
 */
class LangTex : Lang() {
    companion object {
        val fileExtensions: List<String>
            get() = Arrays.asList(*arrayOf("latex", "tex"))
    }

    init {
        val _shortcutStylePatterns: MutableList<List<Any>?> = ArrayList()
        val _fallthroughStylePatterns: MutableList<List<Any>?> = ArrayList()

        // whitespace
        _shortcutStylePatterns.add(
            listOf(
                listOf(
                    Prettify.PR_PLAIN, Pattern.compile("^[\\t\\n\\r \\xA0]+"), null, "\t\n\r " + Character.toString(
                        0xA0.toChar()
                    )
                )
            )
        )
        // all comments begin with '%'
        _shortcutStylePatterns.add(
            listOf(
                listOf(
                    Prettify.PR_COMMENT,
                    Pattern.compile("^%[^\\r\\n]*"),
                    null,
                    "%"
                )
            )
        )
        //[PR['PR_DECLARATION'], /^\\([egx]?def|(new|renew|provide)(command|environment))\b/],
        // any command starting with a \ and contains
        // either only letters (a-z,A-Z), '@' (internal macros)
        _fallthroughStylePatterns.add(
            Arrays.asList(
                *arrayOf<Any>(
                    Prettify.PR_KEYWORD,
                    Pattern.compile("^\\\\[a-zA-Z@]+")
                )
            )
        )
        // or contains only one character
        _fallthroughStylePatterns.add(Arrays.asList(*arrayOf<Any>(Prettify.PR_KEYWORD, Pattern.compile("^\\\\."))))
        // Highlight dollar for math mode and ampersam for tabular
        _fallthroughStylePatterns.add(Arrays.asList(*arrayOf<Any>(Prettify.PR_TYPE, Pattern.compile("^[$&]"))))
        // numeric measurement values with attached units
        _fallthroughStylePatterns.add(
            Arrays.asList(
                *arrayOf<Any>(
                    Prettify.PR_LITERAL,
                    Pattern.compile(
                        "[+-]?(?:\\.\\d+|\\d+(?:\\.\\d*)?)(cm|em|ex|in|pc|pt|bp|mm)",
                        Pattern.CASE_INSENSITIVE
                    )
                )
            )
        )
        // punctuation usually occurring within commands
        _fallthroughStylePatterns.add(
            Arrays.asList(
                *arrayOf<Any>(
                    Prettify.PR_PUNCTUATION,
                    Pattern.compile("^[{}()\\[\\]=]+")
                )
            )
        )
        setShortcutStylePatterns(_shortcutStylePatterns)
        setFallthroughStylePatterns(_fallthroughStylePatterns)
    }
}