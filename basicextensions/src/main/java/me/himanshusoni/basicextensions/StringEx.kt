package me.himanshusoni.basicextensions

import android.text.Spannable
import android.text.style.ForegroundColorSpan
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

fun String.mask(visibleFirst: Int, visibleLast: Int): String {
    var output = ""
    for (i in 0 until this.length) {
        if (i < visibleFirst || i >= (this.length - visibleLast)) {
            output += this[i]
        } else {
            output += "*"
        }
    }
    return output
}


fun String.highlight(query: String?, highlightColor: Int): Spannable {
    query?.let {
        if (!it.isBlank() && this.toLowerCase().contains(it.toLowerCase())) {
            var startPos = this.toLowerCase().indexOf(it.toLowerCase(), 0)
            val spanText = Spannable.Factory.getInstance().newSpannable(this)

            var hasMore: Boolean
            do {
                val endPos = startPos + it.length
                spanText.setSpan(
                    ForegroundColorSpan(highlightColor),
                    startPos,
                    endPos,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                startPos = this.toLowerCase().indexOf(it.toLowerCase(), endPos)
                hasMore = startPos != -1
            } while (hasMore)

            return spanText
        } else {
            return Spannable.Factory.getInstance().newSpannable(this)
        }
    } ?: run {
        return Spannable.Factory.getInstance().newSpannable(this)
    }
}

fun String.md2(): String? = hashUsing("MD2")
fun String.md5(): String? = hashUsing("MD5")
fun String.sha1(): String? = hashUsing("SHA-1")
fun String.sha256(): String? = hashUsing("SHA-256")
fun String.sha384(): String? = hashUsing("SHA-384")
fun String.sha512(): String? = hashUsing("SHA-512")

fun String.hashUsing(algorithm: String): String? = try {
    val md = MessageDigest.getInstance(algorithm)
    val md5Data = BigInteger(1, md.digest(this.toByteArray()))
    String.format("%032x", md5Data)
} catch (e: NoSuchAlgorithmException) {
    e.printStackTrace()
    null
}