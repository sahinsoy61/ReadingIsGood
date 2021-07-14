import { Button } from "react-bootstrap"
import DataTable from "./DataTable";


const TableGroup = ({getter, data, text}) => {


  return (
    <>
      <Button variant="outline-primary" onClick={getter}>{text}</Button>
      <DataTable data={data}/>
    </>  
  );
}

export default TableGroup;
