import models.Car
import models.Color
import models.Make
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


fun main() {

    var isRunning = true

    val dealership = Dealership()

    do {
        isRunning = menu(dealership) != 0
    } while (isRunning)

}

fun menu(dealership: Dealership): Int {
    println("Select an option")
    println("1. Add a car")
    println("2. Sell a car")
    println("3. Sell a Chevy")
    println("4. Sell a Ford")
    println("5. Show car list")
    println("6. Exit")

    val response = readln()
    var selection = 0

    try {
        selection = response.toInt()
        val formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.ENGLISH)
        when (selection){
            1 -> {
                val newCar = Car()
                println("Name of the car:")
                newCar.name = readln()
                println("Make of the car:")
                println("1 Chevy")
                println("2 Ford")
                val make = readln().toInt()
                newCar.make = when (make) {
                    1 -> Make.CHEVY
                    2 -> Make.FORD
                    else -> Make.DEFAULT
                }
                println("Color of the car:")
                println("1 Red")
                println("2 Green")
                println("3 Blue")
                val color = readln().toInt()
                newCar.color = when (color) {
                    1 -> Color.RED
                    2 -> Color.GREEN
                    3 -> Color.BLUE
                    else -> Color.DEFAULT
                }
                println("Date of registration in format MM/dd/yyyy")
                val date = LocalDate.parse(readln(), formatter)
                dealership.addCar(newCar, date)
                return 1
            }
            2, 3, 4 -> {
                val make = when (selection) {
                    2 -> Make.DEFAULT
                    3 -> Make.CHEVY
                    4-> Make.FORD
                    else -> Make.DEFAULT
                }
                val car = dealership.sellCar(make)
                if(car != null) {
                    println("Car sold = ${car.name}/${car.make}/${car.color}")
                    return 1
                } else {
                    println("No cars on list")
                }
            }
            5 -> {
                val list = dealership.showCarList()
                for (dealershipCar in list){
                    println("Car = ${dealershipCar.car.name}/${dealershipCar.car.make}/${dealershipCar.car.color} Date of registration = ${dealershipCar.arrivalDate.format(formatter)}")
                }
            }
            6 -> {
                return 0
            }
            else -> {
                println("Wrong selection, try again")
            }
        }
    } catch (e: Exception) {
        println("Not a numeric selection, try again")

    }

    return -1
}

