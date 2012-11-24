package ScalaWorld.misc

import ScalaWorld.misc

case class Order()

/**
 * Any object with MaybeOrder trait qualifies as a Monad since is accepts
 * programs that manipulate its underlying type Order
 */
trait MaybeOrder {
    def map     (process: misc.Order => Order)      : misc.MaybeOrder // Good order
    def flatMap (process: misc.Order => MaybeOrder) : misc.MaybeOrder // Bad order

}

/**
 * Good orders propogate (unlike BadOrders which stay bad)
 * @param order the order to be processed
 */
case class GoodOrder(order: Order) extends MaybeOrder {
  def map     (process: Order => Order) = GoodOrder(process(order))
  def flatMap (process: Order => MaybeOrder) = process(order)

}

/**
 * Indicates an error has occured while processing the order.
 * Bad orders should not be processed further
 * @param order the order to be processed
 */
case class BadOrder(order: Order) extends MaybeOrder {
  def map     (process: Order => Order)      = BadOrder(order)
  def flatMap (process: Order => MaybeOrder) = BadOrder(order)
}
