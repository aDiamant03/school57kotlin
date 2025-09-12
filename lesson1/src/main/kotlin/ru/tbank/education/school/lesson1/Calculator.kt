package ru.tbank.education.school.lesson1

fun calculate(s: String): Any {
    val m = s.split(" ")
    if (m.size != 3) {
        val a = m[0].toInt()
        val b = m[1].toInt()
        return a + b
    }
    val a = m[0].toInt()
    val b = m[2].toInt()
    val z = m[1]
    return when (z) {
        "+" -> a + b
        "-" -> a - b
        "*" -> a * b
        "/" -> {
            if (b == 0)
                "null"
            else
                a.toDouble() / b.toDouble()
        }

        "**" -> {
            var w: Long = 1
            for (i in 0..<b) {
                w *= a.toLong()
            }
            w
        }

        else -> {
            "Функция отсутствует"
        }
    }
}

fun main(){
    println("""Введите через пробел. Пример: 5 + 3.
            |Для выхода введите exit.
        """.trimMargin())
    while (true) {
        val s = readln()
        if (s == "exit") break
        val r = calculate(s)
        println(r)
    }
}
