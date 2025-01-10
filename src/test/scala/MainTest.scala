import collection.mutable.Stack
import org.scalatest._
import flatspec._
import matchers._
import models.MenuItem._
import Main._

class MainSpec extends AnyFlatSpec with should.Matchers {

  "calculateBill" should "return no service charge if all items are drinks" in {
    val items = List(cola, coffee, coffee)
    calculateBill(items) should be (items.map(_.price).sum)
  }

  it should "return a 20% service charge if at least one item is hot food" in {
    val items = List(cola, coffee, coffee, steakSandwich)
    calculateBill(items) should be (BigDecimal(items.map(_.price).sum * 1.2)
      .setScale(2, BigDecimal.RoundingMode.HALF_EVEN).doubleValue)
  }

  it should "return a 20% service charge if at least one item is hot food and one is cold" in {
    val items = List(cola, coffee, coffee, steakSandwich, cheeseSandwich)
    calculateBill(items) should be(BigDecimal(items.map(_.price).sum * 1.2)
      .setScale(2, BigDecimal.RoundingMode.HALF_EVEN).doubleValue)
  }

  it should "return a 20% service charge if at least one item is hot food, but a maximum of £20" in {
    val items = List(cola, coffee) ++ List.fill(50)(steakSandwich)
    calculateBill(items) should be(BigDecimal(items.map(_.price).sum + 20)
      .setScale(2, BigDecimal.RoundingMode.HALF_EVEN).doubleValue)
  }

  it should "return a 10% service charge if at least one item is not hot food" in {
    val items = List(cola, coffee, coffee, cheeseSandwich)
    calculateBill(items) should be(BigDecimal(items.map(_.price).sum * 1.1)
      .setScale(2, BigDecimal.RoundingMode.HALF_EVEN).doubleValue)
  }

  it should "return a 10% service charge if at least one item is not hot food, with no limit on a maximum service charge" in {
    val items = List(cola, coffee, coffee) ++ List.fill(50)(cheeseSandwich)
    calculateBill(items) should be(BigDecimal(items.map(_.price).sum * 1.1)
      .setScale(2, BigDecimal.RoundingMode.HALF_EVEN).doubleValue)
  }
}