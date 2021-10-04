// Copyright (C) 2013 Nikhil Dabas
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

import com.wakaztahir.common.prettify.parser.Prettify
import java.util.*
import java.util.regex.Pattern

/**
 * This is similar to the lang-ml.js in JavaScript Prettify.
 *
 * All comments are adapted from the JavaScript Prettify.
 *
 *
 *  Registers a language handler for LLVM. From
 * https://gist.github.com/ndabas/2850418
 *
 *
 * To use, include prettify.js and this file in your HTML page. Then put your
 * code in an HTML tag like <pre class="prettyprint lang-llvm">(my LLVM code)</pre>
 *
 *
 * The regular expressions were adapted from:
 * https://github.com/hansstimer/llvm.tmbundle/blob/76fedd8f50fd6108b1780c51d79fbe3223de5f34/Syntaxes/LLVM.tmLanguage
 *
 * http://llvm.org/docs/LangRef.html#constants describes the language grammar.
 *
 * @author Nikhil Dabas
 */
class LangLlvm : Lang() {
    companion object {
        val fileExtensions: List<String>
            get() = Arrays.asList("llvm", "ll")
    }

    init {
        val _shortcutStylePatterns: MutableList<List<Any?>> = ArrayList()
        val _fallthroughStylePatterns: MutableList<List<Any?>> = ArrayList()

        // Whitespace
        _shortcutStylePatterns.add(
            listOf(
                Prettify.PR_PLAIN, Pattern.compile("^[\t\n\r \\xA0]+"), null, "\t\n\r " + Character.toString(
                    0xA0.toChar()
                )
            )
        )
        // A double quoted, possibly multi-line, string.
        _shortcutStylePatterns.add(
            listOf(
                Prettify.PR_STRING,
                Pattern.compile("^!?\\\"(?:[^\\\"\\\\]|\\\\[\\s\\S])*(?:\\\"|$)"),
                null,
                "\""
            )
        )
        // comment.llvm
        _shortcutStylePatterns.add(
            listOf(
                Prettify.PR_COMMENT,
                Pattern.compile("^;[^\r\n]*"),
                null,
                ";"
            )
        )
        // variable.llvm
        _fallthroughStylePatterns.add(
            Arrays.asList(
                Prettify.PR_PLAIN, Pattern.compile("^[%@!](?:[-a-zA-Z$._][-a-zA-Z$._0-9]*|\\d+)")
            )
        )
        // According to http://llvm.org/docs/LangRef.html#well-formedness
        // These reserved words cannot conflict with variable names, because none of them start with a prefix character ('%' or '@').
        _fallthroughStylePatterns.add(
            listOf(
                Prettify.PR_KEYWORD,
                Pattern.compile("^[A-Za-z_][0-9A-Za-z_]*"),
                null
            )
        )
        // constant.numeric.float.llvm
        _fallthroughStylePatterns.add(
            Arrays.asList(
                Prettify.PR_LITERAL, Pattern.compile("^\\d+\\.\\d+")
            )
        )
        // constant.numeric.integer.llvm
        _fallthroughStylePatterns.add(
            Arrays.asList(
                Prettify.PR_LITERAL, Pattern.compile("^(?:\\d+|0[xX][a-fA-F0-9]+)")
            )
        )
        // punctuation
        _fallthroughStylePatterns.add(
            Arrays.asList(
                Prettify.PR_PUNCTUATION, Pattern.compile("^[()\\[\\]{},=*<>:]|\\.\\.\\.$")
            )
        )
        setShortcutStylePatterns(_shortcutStylePatterns)
        setFallthroughStylePatterns(_fallthroughStylePatterns)
    }

    override fun getFileExtensions(): List<String> {
        return fileExtensions
    }
}