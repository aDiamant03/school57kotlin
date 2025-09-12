package ru.tbank.education.school.lesson1

fun main(){
    var s = "5 + 3"
    while (s != "exit") {
        println("""Введите через пробел. Пример: 5 + 3.
            |Для выхода введите exit.
        """.trimMargin())
        s = readln()
        val m = s.split(" ")
        val a = m[0].toInt()
        val b = m[2].toInt()
        val z = m[1]
        val r = when (z) {
            "exit" -> break
            "+" -> a + b
            "-" -> a - b
            "*" -> a * b
            "/" -> {
                if (b==0)
                    "null"
                else
                    a.toDouble() / b.toDouble()
            }
            "**" -> {
                var w: Long = 1
                for (i in 0..<b) {
                    w *= a
                }
                w
            }

            else -> {
                println("Функция отсутствует")
            }
        }
        println(r)
    }
}
