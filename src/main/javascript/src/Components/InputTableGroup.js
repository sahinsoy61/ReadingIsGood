import { Button, InputGroup, FormControl } from "react-bootstrap"
import DataTable from "./DataTable";

const TableGroup = ({searchId, setSearchId, buttonStyle, getter, data}) => {




  return (
      <>
        <InputGroup className="mb-3">
            <FormControl
            style={{"width": "10%", "margin" : "0 auto"}}
            placeholder="Customer ID"
            aria-label="Customer ID"
            aria-describedby="basic-addon2"
            value={searchId}
            onChange={(e) => {setSearchId(e.target.value)}}
            />
            <Button style={{"marginLeft" : "10px"}} variant={buttonStyle} onClick={getter}>Get All Orders By Customer ID</Button>{' '}
        </InputGroup>
        <DataTable data={data}/>
    </>
  );
}

export default TableGroup;
