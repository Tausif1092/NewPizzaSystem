package services

import model.Order
import myUtility.{ResultSetToList, DataBaseConnection}

/**
  * Created by tsn3316 on 5/5/16.
  */
class Bill {

  val db=new DataBaseConnection()
  val rsToList=new ResultSetToList()

  def getOrderDetail(orderId:Int)={
    val sql="select * from order_detail where detail_id="+orderId
    val orderDetails=db.getResultSet(sql)
    rsToList.getOrderList(orderDetails)
  }

  def getOrderTotal(orderList:List[Order])={
    getSum(orderList,0,0)
  }

  private def getSum(orderList:List[Order],sum:Int,index:Int):Int={
    if(index==orderList.length){
      sum
    }else{
      getSum(orderList,orderList(index).total+sum,index+1)
    }
  }

  def getServiceTax(total:Int):Int={
    math.round(total*5/100)
  }

  def getVat(total:Int)={
    math.round(total*6/100)
  }
}
