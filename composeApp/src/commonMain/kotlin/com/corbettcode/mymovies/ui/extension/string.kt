

fun String.format(vararg args: Any): String {
    val format = this
    val stringBuilder = StringBuilder()

    var currentIndex = 0
    var i = 0
    while (i < format.length) {
        if (format[i] == '%') {
            if (i + 1 < format.length) {
                val marker = format[i + 1]

                val value = args[currentIndex++]
                val cadenaFormateada = when (marker) {
                    's' -> value.toString()
                    'd' -> value.toString().toInt()
                    'f' -> value.toString().toDouble()
                    else -> ""
                }

                stringBuilder.append(cadenaFormateada)
                i += 2
            } else {
                stringBuilder.append('%')
                i++
            }
        } else {
            stringBuilder.append(format[i])
            i++
        }
    }

    return stringBuilder.toString()
}