import models.ItemType.{Drink, Food}
import models.MenuItem
import models.MenuItem.MaxHotFoodServiceCharge
import models.Temperature.Hot

object Main extends App {

  def calculateBill(items: List[MenuItem]): Double = {
    val itemsCost = items.map(_.price).sum
    val serviceCharge = addServiceCharge(items, itemsCost)
    roundMonetaryValue(itemsCost + serviceCharge)
  }
  
  private def addServiceCharge(items: List[MenuItem], billPrice: Double): Double = {
    items match {
      case _ if items.forall(_.itemType == Drink) => 0
      case hot if hot.exists(item => item.temp == Hot && item.itemType == Food) =>
        billPrice * 0.2 match {
          case underLimit if underLimit < 20 => underLimit
          case _ => MaxHotFoodServiceCharge
        }
      case food if food.exists(_.itemType == Food) => billPrice * 0.1
      case _ => 0
    }
  }
  
  private def roundMonetaryValue(amount: Double): Double = {
    BigDecimal(amount).setScale(2, BigDecimal.RoundingMode.HALF_EVEN).doubleValue
  }

}
