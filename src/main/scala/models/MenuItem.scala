package models

import models.ItemType.{Drink, Food}
import models.Temperature.{Cold, Hot}

case class MenuItem(name: String, temp: Temperature, price: Double, itemType: ItemType)

object MenuItem {
  val MaxHotFoodServiceCharge = 20.0
  
  val cola = MenuItem("Cola", Cold, 0.5, Drink)
  val coffee = MenuItem("Coffee", Hot, 1, Drink)
  val cheeseSandwich = MenuItem("Cheese Sandwich", Cold, 2, Food)
  val steakSandwich = MenuItem("Steak Sandwich", Hot, 4.5, Food)
}

sealed trait Temperature
object Temperature {
  case object Hot extends Temperature
  case object Cold extends Temperature
}

sealed trait ItemType
object ItemType {
  case object Drink extends ItemType
  case object Food extends ItemType
}
