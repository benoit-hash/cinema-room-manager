package cinema

import java.text.DecimalFormat

var a: Int = 0 // rows
var b: Int = 0 // seats
var tableau = mutableListOf(
    mutableListOf<String>()
)
var c: Int = 0 // budget
var d: Int = 0 // bought
var e: Int = 0 // income

fun constTableau(rows: Int, cols: Int) {
    val firstRow = MutableList(cols + 1){" "}
    val row = MutableList(cols){"S"}
    row.add(0, " ")
    for (i in 1..cols) {
        firstRow[i] = "$i"
    }
    tableau.removeAt(0)
    tableau.add(0, firstRow)
    for (j in 1..rows) {
        row[0] = "$j"
        tableau.add(row.toMutableList())
    }
    if (a * b > 60) {
        c = a * b * 8 + (a / 2) * b * 2
    } else {
        c = a * b * 10
    }
}
fun printTableau() {
    println("Cinema:")
    for (row in tableau) {
        println(row.joinToString(" "))
    }
    menu()
}

fun getStats() {
    val df = DecimalFormat("#,##0.00")
    val percent = df.format(d.toDouble() / (a * b) * 100)
    println("Number of purchased tickets: $d\n" +
            "Percentage: $percent%\n" +
            "Current income: \$$e\n" +
            "Total income: \$$c")
    menu()
}
fun buyTicket() {
    println("Enter a row number:")
    val c1 = readLine()!!.toInt()
    println("Enter a seat number in that row:")
    val d1 = readLine()!!.toInt()
    if (c1 > a || d1 > b) {
        println("Wrong input!")
        buyTicket()
    }
    if (tableau[c1][d1] == "B") {
        println("That ticket has already been purchased!")
        buyTicket()
    }
    if (a * b <= 60) {
        d += 1
        e += 10
        println("Ticket price: $10")
    } else if (c1 <= a / 2) {
        d += 1
        e += 10
        println("Ticket price: $10")
    } else {
        d += 1
        e += 8
        println("Ticket price: $8")
    }
    tableau[c1][d1] = "B"
    menu()
}

fun menu() {
    println("1. Show the seats")
    println("2. Buy a ticket")
    println("2. Statistics")
    println("0. Exit")
    when (readLine()!!.toInt()) {
        1 -> printTableau()
        2 -> buyTicket()
        3 -> getStats()
        0 -> 1
        else -> menu()
    }
}

fun main() {
    println("Enter the number of rows:")
    a = readLine()!!.toInt()
    println("Enter the number of seats in each row:")
    b = readLine()!!.toInt()
    constTableau(a, b)
    menu()

}
