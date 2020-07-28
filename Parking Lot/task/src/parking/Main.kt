package parking
import java.util.*

val scanner = Scanner(System.`in`)

fun main() {
    val funcao = scanner.next()
    if (funcao == "park")
        park()
    else if (funcao =="leave")
        leave()
}

fun leave() {
    val vaga = scanner.nextInt()
    if (vaga == 1)
        println("Spot 1 is free.")
    else
        println("There is no car in spot $vaga.")
}

fun park(){
    val placa = scanner.next()
    val cor = scanner.next()
    println("$cor car parked in spot 2.")
}
