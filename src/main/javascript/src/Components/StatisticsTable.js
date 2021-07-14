import { Table, Button } from "react-bootstrap";


const StatisticsTable = ({data, getter}) => {

    const intToMonth = (monthId) => {
        const months = {
          1: "January",
          2: "February",
          3: "March",
          4: "April",
          5: "May",
          6: "June",
          7: "July",
          8: "August",
          9: "September",
          10: "October",
          11: "November",
          12: "December",
        }
    
        return months[monthId]
      }
    

  return (
      <div className="DataTable">
        <Button style={{"marginLeft" : "10px", "marginBottom": "20px"}} variant="outline-primary" onClick={getter}>Get Statistics</Button>
            {data.length !== 0 && 
            <Table striped bordered hover>
                <thead>
                    <tr>
                        <th>Month</th>
                        <th>Total Order Count</th>
                        <th>Total Book Count</th>
                        <th>Total Purchased Amount</th>
                    </tr>
                </thead>

                <tbody>
                    {data.map((row) => (
                        <tr>
                            <td>{intToMonth(row.month)}</td>
                            <td>{row.totalOrderCount}</td>
                            <td>{row.totalBookCount}</td>
                            <td>{row.totalPurchasedAmount}</td>
                        </tr>
                    ))}
                </tbody>
            </Table>
            }
        </div>
  );
}

export default StatisticsTable;
