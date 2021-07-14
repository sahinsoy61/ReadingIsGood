import { Form } from "react-bootstrap"


const DatePicker = ({innerRef, id, title}) => {


  return (
      <div style={{"marginBottom": "20px"}}>
          <Form.Label htmlFor={id}>{title}</Form.Label>
          <Form.Control
            style={{"width": "25%", "margin" : "0 auto"}}
            ref={innerRef}
            type="date"
            id={id}
            title={title}
          />
      </div>
      
  );
}

export default DatePicker;
