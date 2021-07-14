import { Table } from "react-bootstrap";


const DataTable = ({data}) => {


    const keys = (obj) => {
        const _vars = []
        if (!obj){
            return _vars
        }
        
        obj.forEach((row) => {
            Object.keys(row).forEach((r) => {
                if (!_vars.includes(r)){
                    _vars.push(r)
                }
            })
        })
        
        return _vars;
    }

    const values = (obj) => {
        const _vars = []
        if (!obj){
            return _vars
        }
        
        Object.values(obj).map((r) => (
            _vars.push(r)
        ))
        
        return _vars;
    }


  return (
      <div className="DataTable">
        <Table striped bordered hover>
            <thead>
                <tr>
                    {
                    keys(data).map((v) => (
                        <th key={v}>{v}</th>
                    ))
                    }
                </tr>
            </thead>

            <tbody>
                {data.map((row) => (
                    <tr key={row.totalPurchasedAmount * row.totalBookCount * row.totalOrderCount}>
                        {values(row).map((v) => (
                            <td key={v}>{v}</td>
                        ))}
                    </tr>
                ))}
            </tbody>

        </Table>
      </div>
  );
}

export default DataTable;
