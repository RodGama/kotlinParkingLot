package parking
import java.util.*

val scanner = Scanner(System.`in`)

class ParkingLot {
    var qtdVagas = 0
    private var vagas: MutableList<Vaga> = mutableListOf()

    constructor(vagas: Int){
        this.qtdVagas = vagas
        for(i in 0 until vagas){
            var vaga = Vaga("","", i)
            this.vagas.add(vaga)

        }
        if (qtdVagas>0)
            println("Created a parking lot with $qtdVagas spots.")
    }

    fun park(){
        var vaga = -1
        for(i in this.vagas.indices){
            if(!vagas[i].ocupado){
                vaga=i
                break
            }
        }
        if(vaga == -1){
            println("Sorry, the parking lot is full.")
        }
        else{
            val placa = scanner.next()
            val cor = scanner.next()
            this.vagas[vaga].car = placa
            this.vagas[vaga].color = cor
            this.vagas[vaga].ocupado = true
            println("$cor car parked in spot ${vaga+1}.")
        }
    }

    fun leave() {
        val vaga = scanner.nextInt()
        if(!this.vagas[vaga-1].ocupado)
            println("There is no car in spot $vaga.")
        else{
            this.vagas[vaga-1].ocupado = false
            this.vagas[vaga-1].color = ""
            this.vagas[vaga-1].car = ""
            println("Spot $vaga is free.")
        }

    }

    fun listaCarros() {
        var vagasOcupadas = this.vagas.filter { x-> x.ocupado }
        if(vagasOcupadas.isEmpty())
            println("Parking lot is empty.")
        else{
            for(i in this.vagas.indices){
                if(this.vagas[i].ocupado)
                    println("${i+1} ${this.vagas[i].car} ${this.vagas[i].color}")
            }
        }
    }

    fun listaCarrosPorCor() {
        val cor = scanner.next()
        var carros = this.vagas.filter { x-> x.color.toLowerCase() == cor.toLowerCase() }
        if(carros.isEmpty())
            println("No cars with color $cor were found.")
        else{
            for(i in carros.indices){
                if(carros.size==1){
                    println(carros[i].car)
                    break
                }
                when(i){
                    0->print(carros[i].car)
                    carros.lastIndex ->print(", ${carros[i].car}\n")
                    else->print(", ${carros[i].car}")
                }
            }
        }
    }

    fun listaVagasPorCor() {
        val cor = scanner.next()
        var carros = this.vagas.filter { x-> x.color.toLowerCase() == cor.toLowerCase() }
        if(carros.isEmpty())
            println("No cars with color $cor were found.")
        else{
            for(i in carros.indices){
                if(carros.size==1){
                    println(carros[i].spot+1)
                    break
                }
                when(i){
                    0->print(carros[i].spot+1)
                    carros.lastIndex ->print(", ${carros[i].spot+1}\n")
                    else->print(", ${carros[i].spot+1}")
                }
            }
        }
    }

    fun listaVagasPorCarro() {
        val placa = scanner.next()
        var vagasCarro = this.vagas.filter { x-> x.car == placa }
        if(vagasCarro.isEmpty())
            println("No cars with registration number $placa were found.")
        else{
            for(i in vagasCarro.indices){
                if(vagasCarro.size==1){
                    println(vagasCarro[i].spot+1)
                    break
                }
                when(i){
                    0->print(vagasCarro[i].spot+1)
                    vagasCarro.lastIndex ->print(", ${vagasCarro[i].spot+1}\n")
                    else->print(", ${vagasCarro[i].spot+1}")
                }
            }
        }
    }
}

class Vaga(carro: String, var color: String, var spot: Int) {
    var car = carro
    var ocupado: Boolean = false
}


fun main() {
    var funcao = scanner.nextLine().split(" ")
    var parkingLot = ParkingLot(0)
    while(parkingLot.qtdVagas==0){
        if(funcao[0] == "exit" )
            break
        else if (funcao[0] != "create")
            println("Sorry, a parking lot has not been created.")
        else{
            val qtdVagas = funcao[1].toInt()
            parkingLot = ParkingLot(qtdVagas)
            break
        }

        funcao = scanner.nextLine().split(" ")
    }

    if(funcao[0] != "exit") {
        var funcaoAux = scanner.next()
        while (funcaoAux != "exit") {
            if (funcaoAux == "park")
                parkingLot.park()
            else if (funcaoAux == "leave")
                parkingLot.leave()
            else if (funcaoAux == "exit")
                break
            else if (funcaoAux == "status")
                parkingLot.listaCarros()
            else if (funcaoAux == "reg_by_color")
                parkingLot.listaCarrosPorCor()
            else if (funcaoAux == "spot_by_color")
                parkingLot.listaVagasPorCor()
            else if (funcaoAux == "spot_by_reg")
                parkingLot.listaVagasPorCarro()
            else if (funcaoAux == "create")
                parkingLot = ParkingLot(scanner.nextInt())
            funcaoAux = scanner.next()
        }
    }
}
