import models.Car
import models.DealershipRegistration
import models.Make
import java.time.LocalDate

class Dealership {

    private val carList = mutableListOf<DealershipRegistration>()

    fun addCar(car : Car, date: LocalDate) {
        carList.add(DealershipRegistration(car, date))
    }

    fun sellCar(make: Make) : Car? {

        if (carList.size != 0) {
            carList.sortBy { it.arrivalDate }

            val carSold = if (make == Make.DEFAULT) {
                carList.first()
            } else {
                val reducedList = carList.filter { it.car.make == make }
                reducedList.first()
            }

            carList.remove(carSold)
            return carSold.car
        }

        return null

    }

    fun showCarList(): List<DealershipRegistration> {
        carList.sortBy { it.arrivalDate }
        return carList
    }

}