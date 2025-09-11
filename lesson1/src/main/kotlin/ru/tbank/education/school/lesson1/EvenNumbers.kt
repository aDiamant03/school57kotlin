package ru.tbank.education.school.lesson1

fun main() {
    val s = readln()
    val m = s.split(" ").map { it.toInt() }.toIntArray()
    var sum = 0;
    for (i in m){
        if (i % 2 == 0){
            sum += i
        }
    }
    println(sum)
}