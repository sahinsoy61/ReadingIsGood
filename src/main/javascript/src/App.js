import { useState, useRef } from "react";
import axios from "axios";

import TableGroup from "./Components/TableGroup";
import InputTableGroup from "./Components/InputTableGroup"
import DateTableGroup from "./Components/DatePicker";
import StatisticsTable from "./Components/StatisticsTable"

import { Tabs, Tab } from "react-bootstrap";

import "./App.css"


const App = () => {

  const [customers, setCustomers] = useState([])
  const [books, setBooks] = useState([])
  const [orders, setOrders] = useState([])
  const [ordersByCustomer, setOrdersByCustomer] = useState([])
  const [ordersByDate, setOrdersByDate] = useState([])
  const [statistics, setStatistics] = useState([])


  const [searchId, setSearchId] = useState("")


  const startDateRef = useRef("")
  const endDateRef = useRef("")



  const getData = async (endPoint, setter) => {
    const url = `http://localhost:8080/api/${endPoint}`
    const res = await axios.request({method : "get", url, headers: {'X-Requested-With': 'XMLHttpRequest'}})
    if (setter){
      setter(res.data)
    }
    return res.data
  }

  const _getOrdersByDate = (dateJSON) => {
    const url = `http://localhost:8080/api/order/orders/date/`
    axios.request({method : "post", url, data : dateJSON})
    .then((response) => {
      setOrdersByDate(response.data)
    })
    .catch((err) => {
      console.log(err)
    })
  }

  const getOrdersByDate = () => {
    const dateJSON = {
      startDate : startDateRef.current.value,
      endDate : endDateRef.current.value
    }

    return _getOrdersByDate(dateJSON)
  }

  const getCustomersData = () => {
    getData("customer/all", setCustomers)
  }

  const getBooksData = () => {
    getData("book/all", setBooks)
  }

  const getOrdersData = () => {
    getData("order/all", setOrders)
  }

  const getOrdersByCustomerData = () => {
    getData(`customer/orders/${searchId}`, setOrdersByCustomer)
  }

  const getStatisticsData = async () => {
    let month = 1

    let totalOrderCount = 0
    let totalBookCount = 0
    let totalPurchasedAmount = 0


    while (month < 13){
      totalOrderCount = await getData(`statistics/orderCount/${month}`)

      totalBookCount = await getData(`statistics/bookCount/${month}`)
      totalPurchasedAmount = await getData(`statistics/purchasedAmount/${month}`)

      const monthInfo = {
        month,
        totalOrderCount,
        totalBookCount,
        totalPurchasedAmount
      }

      if (totalOrderCount + totalBookCount + totalPurchasedAmount > 0){
        const inArray = statistics.some((s) => s.month === monthInfo.month)
        
        if (!inArray){
          setStatistics(prev => [...prev, monthInfo])
        }
      }

      month++
    }
  }

  
  return (
      <div className="App">

        <Tabs defaultActiveKey="getCustomers" id="mainTab" className="mb-3" style={{"width" : "90%", "margin" : "0 auto"}}>

          <Tab className="displayTab" eventKey="getCustomers" title="Get All Customers">
            <TableGroup getter={getCustomersData} data={customers} text="Get All Customers"/>
          </Tab>

          <Tab className="displayTab" eventKey="getBooks" title="Get All Books">
            <TableGroup getter={getBooksData} data={books} text="Get All Books"/>
          </Tab>

          <Tab className="displayTab" eventKey="getOrders" title="Get All Orders">
            <TableGroup getter={getOrdersData} data={orders} text="Get All Orders"/>
          </Tab>

          <Tab className="displayTab" eventKey="getOrdersDates" title="Get Orders Between Dates">
            <DateTableGroup innerRef={startDateRef} id="startDate" title="Start Date"/>
            <DateTableGroup innerRef={endDateRef} id="endDate" title="End Date"/>
            {ordersByDate !== [] && <TableGroup getter={getOrdersByDate} data={ordersByDate} text="Get Orders Between Dates" />}
          </Tab>

          <Tab className="displayTab" eventKey="getOrdersCustomerId" title="Get All Orders By Customer ID">
            <InputTableGroup searchId={searchId} setSearchId={setSearchId} getter={getOrdersByCustomerData} data={ordersByCustomer} />
          </Tab>

          <Tab className="displayTab" eventKey="getStats" title="Get Statistics">
            <StatisticsTable data={statistics} getter={getStatisticsData}/>
          </Tab>

        </Tabs>

        </div>
  );
}

export default App;
